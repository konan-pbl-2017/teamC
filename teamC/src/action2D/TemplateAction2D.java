//teamC�̃v���W�F�N�g
package action2D;

import javax.vecmath.Vector2d;

import framework.RWT.RWTContainer;
import framework.RWT.RWTFrame3D;
import framework.RWT.RWTVirtualController;
import framework.game2D.Ground2D;
import framework.game2D.OvergroundActor2D;
import framework.game2D.Sprite;
import framework.game2D.Velocity2D;
import framework.gameMain.IGameState;
import framework.gameMain.SimpleActionGame;
import framework.model3D.Object3D;
import framework.model3D.Universe;
import framework.physics.PhysicsUtility;


public class TemplateAction2D extends SimpleActionGame {
	
	private Player player;
	private Enemy enemy_1;
	private Enemy enemy_2;
	private Bullet bullet;
	int invisible=0;//���G����
	//�e�X�g���A�C�e���ϐ�
	private Item item;
	int get_item=1;//�A�C�e���p�̊֐��A�擾�̍ۂɌ��������悤�B
	int Enemy_life_1=1;
	int Enemy_life_2=1;
	int Player_life=3;//player��LIFE
	int Player_count;
	int player_attack=0;

	private Enemy_2 boss;
	int BOSS_move=0;
	int BOSS_patarn=0;
	int BOSS_TIME=0;
	int BOSS_LIFE=3;
	int BOSS_invisible=0;
	
	private IGameState initialGameState = null;
	private IGameState finalGameState = null;
	private IGameState ClearGameState = null;
	private Ground2D stage;
//	private Ground2D clear;
	//private Sprite clear;
	
	// ���ƂŐ݌v�ύX
	// Enemy�N���X�ł��̒l���g���������߁B
	public static final int RANGE = 30;

	// �v���C���[�̌��݂̑��x����������O���[�o���ϐ�
	private Velocity2D curV;
	
	public TemplateAction2D() {
		super();
		initialGameState = new IGameState() {
			@Override
			public void init(RWTFrame3D frame) {
				TemplateAction2D.this.frame = frame;
				RWTContainer container = new StartContainer(TemplateAction2D.this);
				changeContainer(container);
			}
			@Override
			public boolean useTimer() {
				return false;
			}
			@Override
			public void update(RWTVirtualController virtualController, long interval) {
			}
		};
		finalGameState = new IGameState() {
			@Override
			public void init(RWTFrame3D frame) {
				TemplateAction2D.this.frame = frame;
				RWTContainer container = new GameOverContainer(TemplateAction2D.this);
				changeContainer(container);
			}
			@Override
			public boolean useTimer() {
				return false;
			}
			@Override
			public void update(RWTVirtualController virtualController, long interval) {
			}
		};
		ClearGameState = new IGameState() {
			@Override
			public void init(RWTFrame3D frame) {
				TemplateAction2D.this.frame = frame;
				RWTContainer container = new EndingContainer(TemplateAction2D.this);
				changeContainer(container);
			}
			@Override
			public boolean useTimer() {
				return false;
			}
			@Override
			public void update(RWTVirtualController virtualController, long interval) {
			}
		};
		setCurrentGameState(initialGameState);		
	}
	
	@Override
	public void init(Universe universe) {
		player = new Player();
		player.setPosition(0.0, 10.0);
		player.setDirection(0.0, 0.0);
		((Object3D)player.getBody()).scale(0.1);
		universe.place(player); // universe�ɒu���B��Ŏ�菜����悤�ɃI�u�W�F�N�g��z�u����B

		enemy_1 = new Enemy();
		enemy_1.setPosition(0.0, 5.0);
		enemy_1.setDirection(1.0, 0.0);
		universe.place(enemy_1); // universe�ɒu���B��Ŏ�菜����悤�ɃI�u�W�F�N�g��z�u����B

		enemy_2 = new Enemy();
		enemy_2.setPosition(0.0, 5.0);
		enemy_2.setDirection(1.0, 0.0);
		universe.place(enemy_2); // universe�ɒu���B��Ŏ�菜����悤�ɃI�u�W�F�N�g��z�u����B
		
		//�{�X�̐ݒ�
		boss = new Enemy_2();
		boss.setPosition(0.0, 5.0);
		boss.setDirection(1.0, 0.0);
		universe.place(boss); // universe�ɒu���B��Ŏ�菜����悤�ɃI�u�W�F�N�g��z�u����B
				
		bullet = new Bullet();
		
		item = new Item();
		if(get_item==1){
		item.setPosition(5.0, 0.0);
		item.setDirection(1.0, 0.0);
		universe.place(item);
		}// universe�ɒu���B��Ŏ�菜����悤�ɃI�u�W�F�N�g��z�u����
		
		// �X�e�[�W��3D�f�[�^��ǂݍ��ݔz�u����
		stage = new Ground2D("data\\images\\teamC\\stage.obj",
				"data\\images\\teamC\\wall.jpg", windowSizeWidth, windowSizeHeight, 0.09);
		universe.place(stage);

		// �\���͈͂����߂�i���オ���_�Ƃ��Ă��̌��_���畝�A�������v�Z����j
		setViewRange(RANGE, RANGE);
		}

	@Override
	public RWTFrame3D createFrame3D() {
		// TODO Auto-generated method stub
		RWTFrame3D f = new RWTFrame3D();
		f.setSize(800, 800);
		// f.setExtendedState(Frame.MAXIMIZED_BOTH);
		f.setTitle("Template for Action 2DGame");
		return f;
		}

	@Override
	public void progress(RWTVirtualController virtualController, long interval) {
		curV = player.getVelocity();

		// �Î~��Ԃ̓v���C���[��x��0�ɂ���B�i��Ŋ����čs���Ă��܂����߁j
		curV.setX(0.0);
		player.setVelocity(curV);
		
		// �L�[����̏���
		// ��
		if (virtualController.isKeyDown(1, RWTVirtualController.LEFT)) {
			player.movePositionLeft(0.05);// ���x�ύX
		}
		// �E
		else if (virtualController.isKeyDown(1, RWTVirtualController.RIGHT)) {
			player.movePositionRight(0.05);// ���x�ύX
		}
		// ��
		if (virtualController.isKeyDown(0, RWTVirtualController.BUTTON_C)) {
			// �W�����v//W�L�[����SPACE�L�[�ɕύX�\��
			if (player.isOnGround()) {
				curV.setY(20.0);
				player.setVelocity(curV);
			}
			player_attack++;
		}
		// ��
		else if (virtualController.isKeyDown(0, RWTVirtualController.DOWN)) {
			player.movePositionDown(0);
		}

		//���ɂ��Ă�����G���E�A���ɓ���
		if(enemy_1.isOnGround()){
			enemy_1.movePositionRight(0.05);
		}
		if(enemy_2.isOnGround()){
			enemy_2.movePositionLeft(0.1);
		}
		
		bullet.movePositionRight(0.5);//�e�ۂ̓���
		
		//�{�X�̐ݒ�
		if (boss.isOnGround()) {
			if(BOSS_patarn==0){
				boss.movePositionRight(0.05);
				BOSS_TIME++;
				}else if(BOSS_patarn==1){
					boss.movePositionLeft(0.05);
					BOSS_TIME++;
					universe.displace(bullet);
					}else if(BOSS_patarn==2){
					//bullet = new Bullet();
					//bullet.setPosition(0.0, -4.0);
					//bullet.setDirection(1.0, 0.0);
					//universe.place(bullet);
					BOSS_TIME++;
					}
			}	
		if(BOSS_TIME==60){
			BOSS_TIME=0;
			BOSS_patarn++;
			}
		if(BOSS_patarn==3){
			BOSS_patarn=0;
			}
					
		player.motion(interval, stage);
		enemy_1.motion(interval, stage);
		enemy_2.motion(interval, stage);
		boss.motion(interval,stage);
		// �Փ˔���i�v���C���[�ƓG�j
		if(player.isOnGround()){
			player_attack=0;
			}
		if(player_attack==0){
			if (player.checkCollision(enemy_1)) {
				if(Enemy_life_1==1){
					if(invisible==0){
						System.out.println("1�Ԃ̓G�ɐڐG�����I");
						Player_life--;
						System.out.println("���݂̃��C�t��"+Player_life+"�ł�");
						invisible=60;
						}
					}
				}
			}
		if (player.checkCollision(enemy_1)) {
			if(player_attack>0){
				if(Enemy_life_1==1){
					System.out.println("1�Ԃ̓G��|����");
					Enemy_life_1--;
					universe.displace(enemy_1);							}
				}
			}
		if(player_attack==0){
			if (player.checkCollision(enemy_2)) {
				if(Enemy_life_2==1){
					if(invisible==0){
						System.out.println("2�Ԃ̓G�ɐڐG�����I");
						Player_life--;
						System.out.println("���݂̃��C�t��"+Player_life+"�ł�");
						invisible=60;
						}
					}
				}
			}
		if (player.checkCollision(enemy_2)) {
			if(player_attack>0){
				if(Enemy_life_2==1){
					System.out.println("2�Ԃ̓G��|����");
					Enemy_life_2--;
					universe.displace(enemy_2);
					}
				}
			}
		if(player_attack==0){
			if (player.checkCollision(boss)) {
				if(BOSS_LIFE>0){
					if(invisible==0){
						System.out.println("�{�X�ɐڐG�����I");
						Player_life--;
						System.out.println("���݂̃��C�t��"+Player_life+"�ł�");
						invisible=60;
						}
					}
				}
			}
		if (player.checkCollision(boss)) {
			if(player_attack>0){
				if(BOSS_invisible==0){
					if(BOSS_LIFE>0){
						System.out.println("�{�X�֍U�������B");
						BOSS_LIFE--;
						System.out.println("�{�X�̃��C�t��"+BOSS_LIFE+"�ł��B");
						BOSS_invisible=30;
						curV.setY(10.0);
						player.setVelocity(curV);
						if(BOSS_LIFE==0){
							universe.displace(boss);
							ending();
							}
						}
					}
				}
			}
		//if(player.checkCollision(bullet)) {
		//if(invisible==0){
		//System.out.println("3�Ԃ̓G(�e��)�ɐڐG�����I");
		//Player_life--;
		//System.out.println("���݂̃��C�t��"+Player_life+"�ł�");
		//invisible=60;
		//}
		//}
		//BOSS��ITEM��ǉ�����
		if(get_item==1){
			if(player.checkCollision(item)){
				System.out.println("�A�C�e�����擾����");
				Player_life++;
				System.out.println("���݂̃��C�t��"+Player_life+"�ł�");
				get_item=0;
				universe.displace(item);
				}
			}
			
		if(invisible!=0){
			invisible--;
			}
		if(BOSS_invisible!=0){
			BOSS_invisible--;
			}
		
		if(Player_life==0){
			gameover();
		}
		//ending();
		//clear = new Ground2D("null",
		//"data\\images\\clear.png", windowSizeWidth, windowSizeHeight);
		//universe.place(clear);
			
			
		//clear = new Sprite("data\\images\\clear.png");
		//universe.place(clear);
		//setViewRange(5, 5);
		//clear.setPosition(0.0, 0.0);
			
			}
		
	public void restart() {
		stop();
		setCurrentGameState(initialGameState);
		get_item=1;
		Player_life=3;
		BOSS_LIFE=3;
		start();
		}
	
	public void play() {
		stop();
		setCurrentGameState(this);
		start();
		}
	
	public void ending() {
		stop();
		setCurrentGameState(ClearGameState);
		start();
		}
	
	public void gameover() {
		stop();
		setCurrentGameState(finalGameState);
		start();
		}


	/**
	 * �Q�[���̃��C��
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		TemplateAction2D game = new TemplateAction2D();
		game.setFramePolicy(5, 33, false);
		game.start();
		}

	@Override
	public OvergroundActor2D getOvergroundActor() {
		return player;
		}
	}
