//teamC�̃v���W�F�N�g
package action2D;

import javax.vecmath.Vector2d;

import framework.RWT.RWTFrame3D;
import framework.RWT.RWTVirtualController;
import framework.game2D.Ground2D;
import framework.game2D.OvergroundActor2D;
import framework.game2D.Velocity2D;
import framework.gameMain.SimpleActionGame;
import framework.model3D.Object3D;
import framework.model3D.Universe;
import framework.physics.PhysicsUtility;


public class TemplateAction2D extends SimpleActionGame {
	
	private Player player;
	private Enemy enemy_1;
	private Enemy enemy_2;
	int invisible=0;//���G����
	//�e�X�g���A�C�e���ϐ�
	private Item item;
	int get_item=1;//�A�C�e���p�̊֐��A�擾�̍ۂɌ��������悤�B
	int Enemy_life_1=1;
	int Player_life=3;//player��LIFE
	int Player_count;

	private Ground2D stage;

	// ���ƂŐ݌v�ύX
	// Enemy�N���X�ł��̒l���g���������߁B
	public static final int RANGE = 30;

	// �v���C���[�̌��݂̑��x����������O���[�o���ϐ�
	private Velocity2D curV;
	
	
	@Override
	public void init(Universe universe) {
		player = new Player();
		player.setPosition(0.0, 0.0);
		player.setDirection(0.0, 0.0);
		universe.place(player); // universe�ɒu���B��Ŏ�菜����悤�ɃI�u�W�F�N�g��z�u����B

		enemy_1 = new Enemy();
		enemy_1.setPosition(0.0, 5.0);
		enemy_1.setDirection(1.0, 0.0);
		universe.place(enemy_1); // universe�ɒu���B��Ŏ�菜����悤�ɃI�u�W�F�N�g��z�u����B

		enemy_2 = new Enemy();
		enemy_2.setPosition(0.0, 5.0);
		enemy_2.setDirection(1.0, 0.0);
		universe.place(enemy_2); // universe�ɒu���B��Ŏ�菜����悤�ɃI�u�W�F�N�g��z�u����B
		
		item = new Item();
		if(get_item==1){
		item.setPosition(5.0, 0.0);
		item.setDirection(1.0, 0.0);
		universe.place(item);
		}// universe�ɒu���B��Ŏ�菜����悤�ɃI�u�W�F�N�g��z�u����
		
		// �X�e�[�W��3D�f�[�^��ǂݍ��ݔz�u����
		stage = new Ground2D("data\\stage3\\stage3.wrl",
				"data\\images\\teamC\\wall.jpg", windowSizeWidth, windowSizeHeight);
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
		if (virtualController.isKeyDown(0, RWTVirtualController.LEFT)) {
			player.movePositionLeft(0.1);//���x�ύX
		}
		// �E
		else if (virtualController.isKeyDown(0, RWTVirtualController.RIGHT)) {
			player.movePositionRight(0.1);//���x�ύX
		}
		// ��
		if (virtualController.isKeyDown(0, RWTVirtualController.UP)) {
			// �W�����v//W�L�[����SPACE�L�[�ɕύX�\��
			if (player.isOnGround()) {
				curV.setY(10.0);
				player.setVelocity(curV);
			}
		}
		// ��
		else if (virtualController.isKeyDown(0, RWTVirtualController.DOWN)) {
			player.movePositionDown(0.01);
		}
		
		//���ɂ��Ă�����G���E�A���ɓ���
		if(enemy_1.isOnGround()){
			enemy_1.movePositionRight(0.05);
		}
		if(enemy_2.isOnGround()){
			enemy_2.movePositionLeft(0.1);
		}
		
		player.motion(interval, stage);
		enemy_1.motion(interval, stage);
		enemy_2.motion(interval, stage);

		// �Փ˔���i�v���C���[�ƓG�j
		if (player.checkCollision(enemy_1)) {
			if(Enemy_life_1==1){
				if(invisible==0){
				System.out.println("1�Ԃ̓G�ɐڐG�����I");
				Player_life--;
				System.out.println("���݂̃��C�t��"+Player_life+"�ł�");
				invisible=60;
				}
				}
		}else if(player.checkCollision(enemy_2)) {
			if(invisible==0){
				System.out.println("2�Ԃ̓G�ɐڐG�����I");
				Player_life--;
				System.out.println("���݂̃��C�t��"+Player_life+"�ł�");
			invisible=60;
			}
			}//BOSS��ITEM��ǉ�����
		else if(get_item==1){
			if(player.checkCollision(item)){
			System.out.println("�A�C�e�����擾����");
			Player_life++;
			System.out.println("���݂̃��C�t��"+Player_life+"�ł�");
			get_item=0;
			universe.displace(item);
			}
		}
		//�G�̏��Ŕ���
		//if(Enemy_life_1==1){
			//if(enemy_1.checkCollision(player)){
				//Enemy_life_1=-1;
				//universe.displace(enemy_1);
			//}
		//}
		if(invisible!=0){
			invisible--;
		}
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
