package action2D;

import java.awt.Color;
 import java.awt.Event;
 import java.awt.Font;
 import java.awt.GraphicsConfiguration;

import framework.RWT.RWTContainer;
 import framework.RWT.RWTImage;
 import framework.RWT.RWTLabel;
 import framework.RWT.RWTVirtualController;
 import framework.RWT.RWTVirtualKey;
 import framework.game2D.Ground2D;
 import framework.model3D.Universe;

public class StartContainer extends RWTContainer {
  private TemplateAction2D game;

 public StartContainer(TemplateAction2D game) {
   this.game = game;
  }

 @Override
  public void build(GraphicsConfiguration gc) {
	 
	//RWTImage image = new RWTImage("data\\images\\teamC\\start.jpg");
	//universe.place(image);
	//image.setRelativePosition(0.25f, 0.5f);
	//addWidget(image);
	   
   RWTLabel startLabel = new RWTLabel();
   startLabel.setColor(Color.BLUE);
   startLabel.setString("GameStart!");
   startLabel.setRelativePosition(0.15f, 0.5f);
   Font f = new Font("", Font.PLAIN, 60);
   startLabel.setFont(f);
   addWidget(startLabel);
   
   RWTLabel startLabe2 = new RWTLabel();
   startLabe2.setColor(Color.BLUE);
   startLabe2.setString("Press ShiftKey");
   startLabe2.setRelativePosition(0.25f, 0.65f);
   Font f2 = new Font("", Font.PLAIN, 30);
   startLabe2.setFont(f2);
   addWidget(startLabe2);
   
   RWTLabel startLabe3 = new RWTLabel();
   startLabe3.setColor(Color.RED);
   startLabe3.setString("藤岡さんの冒険");
   startLabe3.setRelativePosition(0.05f, 0.25f);
   Font f3 = new Font("", Font.PLAIN, 65);
   startLabe3.setFont(f3);
   addWidget(startLabe3);
   
   RWTLabel startLabe4 = new RWTLabel();
   startLabe4.setString("操作説明");
   startLabe4.setRelativePosition(0.77f, 0.80f);
   Font f4 = new Font("", Font.PLAIN, 15);
   startLabe4.setFont(f4);
   addWidget(startLabe4);
   
   RWTLabel startLabe5 = new RWTLabel();
   startLabe5.setString("カーソルキー'←' 左へ進む");
   startLabe5.setRelativePosition(0.73f, 0.84f);
   Font f5 = new Font("", Font.PLAIN, 10);
   startLabe5.setFont(f5);
   addWidget(startLabe5);
   
   RWTLabel startLabe6 = new RWTLabel();
   startLabe6.setString("カーソルキー'→' 右へ進む");
   startLabe6.setRelativePosition(0.73f, 0.88f);
   startLabe6.setFont(f5);
   addWidget(startLabe6);
   
   RWTLabel startLabe7 = new RWTLabel();
   startLabe7.setString("     SpaceKey ジャンプ");
   startLabe7.setRelativePosition(0.73f, 0.92f);
   startLabe7.setFont(f5);
   addWidget(startLabe7);
  
   
  }

 @Override
  public void keyPressed(RWTVirtualKey key) {
   if (key.getVirtualKey() == RWTVirtualController.BUTTON_A) {
    game.play();
   }
  }

 @Override
  public void keyReleased(RWTVirtualKey key) {
   // TODO Auto-generated method stub

 }

 @Override
  public void keyTyped(RWTVirtualKey key) {
   // TODO Auto-generated method stub

 }

}



