//teamCのプロジェクト
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
	int invisible=0;//無敵時間
	//テスト中アイテム変数
	private Item item;
	int get_item=1;//アイテム用の関数、取得の際に減少させよう。
	int Enemy_life_1=1;
	int Player_life=3;//playerのLIFE
	int Player_count;

	private Ground2D stage;

	// あとで設計変更
	// Enemyクラスでこの値を使いたいため。
	public static final int RANGE = 30;

	// プレイヤーの現在の速度が代入されるグローバル変数
	private Velocity2D curV;
	
	
	@Override
	public void init(Universe universe) {
		player = new Player();
		player.setPosition(0.0, 0.0);
		player.setDirection(0.0, 0.0);
		universe.place(player); // universeに置く。後で取り除けるようにオブジェクトを配置する。

		enemy_1 = new Enemy();
		enemy_1.setPosition(0.0, 5.0);
		enemy_1.setDirection(1.0, 0.0);
		universe.place(enemy_1); // universeに置く。後で取り除けるようにオブジェクトを配置する。

		enemy_2 = new Enemy();
		enemy_2.setPosition(0.0, 5.0);
		enemy_2.setDirection(1.0, 0.0);
		universe.place(enemy_2); // universeに置く。後で取り除けるようにオブジェクトを配置する。
		
		item = new Item();
		if(get_item==1){
		item.setPosition(5.0, 0.0);
		item.setDirection(1.0, 0.0);
		universe.place(item);
		}// universeに置く。後で取り除けるようにオブジェクトを配置する
		
		// ステージの3Dデータを読み込み配置する
		stage = new Ground2D("data\\stage3\\stage3.wrl",
				"data\\images\\teamC\\wall.jpg", windowSizeWidth, windowSizeHeight);
		universe.place(stage);

		// 表示範囲を決める（左上が原点としてその原点から幅、高さを計算する）
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

		// 静止状態はプレイヤーのxを0にする。（坂で滑って行ってしまうため）
		curV.setX(0.0);
		player.setVelocity(curV);

		// キー操作の処理
		// 左
		if (virtualController.isKeyDown(0, RWTVirtualController.LEFT)) {
			player.movePositionLeft(0.1);//速度変更
		}
		// 右
		else if (virtualController.isKeyDown(0, RWTVirtualController.RIGHT)) {
			player.movePositionRight(0.1);//速度変更
		}
		// 上
		if (virtualController.isKeyDown(0, RWTVirtualController.UP)) {
			// ジャンプ//WキーからSPACEキーに変更予定
			if (player.isOnGround()) {
				curV.setY(10.0);
				player.setVelocity(curV);
			}
		}
		// 下
		else if (virtualController.isKeyDown(0, RWTVirtualController.DOWN)) {
			player.movePositionDown(0.01);
		}
		
		//床についていたら敵が右、左に動く
		if(enemy_1.isOnGround()){
			enemy_1.movePositionRight(0.05);
		}
		if(enemy_2.isOnGround()){
			enemy_2.movePositionLeft(0.1);
		}
		
		player.motion(interval, stage);
		enemy_1.motion(interval, stage);
		enemy_2.motion(interval, stage);

		// 衝突判定（プレイヤーと敵）
		if (player.checkCollision(enemy_1)) {
			if(Enemy_life_1==1){
				if(invisible==0){
				System.out.println("1番の敵に接触した！");
				Player_life--;
				System.out.println("現在のライフは"+Player_life+"です");
				invisible=60;
				}
				}
		}else if(player.checkCollision(enemy_2)) {
			if(invisible==0){
				System.out.println("2番の敵に接触した！");
				Player_life--;
				System.out.println("現在のライフは"+Player_life+"です");
			invisible=60;
			}
			}//BOSSとITEMを追加する
		else if(get_item==1){
			if(player.checkCollision(item)){
			System.out.println("アイテムを取得した");
			Player_life++;
			System.out.println("現在のライフは"+Player_life+"です");
			get_item=0;
			universe.displace(item);
			}
		}
		//敵の消滅判定
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
	 * ゲームのメイン
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
