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

public class GameOverContainer extends RWTContainer {
	private TemplateAction2D game;

	public GameOverContainer(TemplateAction2D game) {
		this.game = game;
	}

	@Override
	public void build(GraphicsConfiguration gc) {
		RWTLabel startLabel = new RWTLabel();
		startLabel.setColor(Color.DARK_GRAY);
		startLabel.setString("Game Over");
		startLabel.setRelativePosition(0.05f, 0.25f);
		Font f = new Font("", Font.PLAIN, 80);
		startLabel.setFont(f);
		addWidget(startLabel);
		
		RWTLabel startLabe2 = new RWTLabel();
		startLabe2.setColor(Color.LIGHT_GRAY);
		startLabe2.setString("ここまでの作業は全部無駄！");
		startLabe2.setRelativePosition(0.09f, 0.4f);
		Font f1 = new Font("", Font.PLAIN, 30);
		startLabe2.setFont(f1);
		addWidget(startLabe2);
		
		RWTLabel startLabe3 = new RWTLabel();
		startLabe3.setColor(Color.RED);
		startLabe3.setString("play restart");
		startLabe3.setRelativePosition(0.70f, 0.84f);
		Font f2 = new Font("", Font.PLAIN, 20);
		startLabe3.setFont(f2);
		addWidget(startLabe3);
		
		RWTLabel startLabe4 = new RWTLabel();
		startLabe4.setColor(Color.RED);
		startLabe4.setString("Press Shiftkey");
		startLabe4.setRelativePosition(0.67f, 0.9f);
		startLabe4.setFont(f2);
		addWidget(startLabe4);
		   
		RWTLabel startLabe5 = new RWTLabel();
		startLabe5.setColor(Color.LIGHT_GRAY);
		startLabe5.setString("ごめんねぇ、僕のせいで無駄な作業させちゃって by新田");
		startLabe5.setRelativePosition(0.05f, 0.5f);
		Font f5 = new Font("", Font.PLAIN, 18);
		startLabe5.setFont(f5);
		addWidget(startLabe5);
		
		RWTImage image = new RWTImage("data\\images\\teamC\\gameover.jpg");
		addWidgetOnBack(image);
		
		repaint();
	}

	@Override
	public void keyPressed(RWTVirtualKey key) {
		if (key.getVirtualKey() == RWTVirtualController.BUTTON_A) {
			game.restart();
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
