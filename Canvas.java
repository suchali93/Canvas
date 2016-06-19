// inspired by code by Joseph Mack, http://www.austintek.com/mvc/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.awt.image.*;

class Canvas extends JPanel implements Observer {

	// the model that this view is showing
	private Model model;
	public Replay replay;

	private int pos_x = 0;
	private int pos_y = 0;
	private int drag_x = 0;
	private int drag_y = 0;

	Canvas(Model model_) {
		// create UI
		setBackground(Color.WHITE);
		setLayout(new FlowLayout(FlowLayout.LEFT));

		// set the model
		model = model_;
		
		// setup the event to go to the "controller"
		// (this anonymous class is essentially the controller)		
		addMouseListener(new MouseAdapter() {
			// private boolean paint = false;
			@Override
			public void mousePressed(MouseEvent e) {
				pos_x = e.getX();
				pos_y = e.getY();
				drag_x = pos_x;
				drag_y = pos_y;

				// paint = true;
				model.setDraw(true);
				model.addPoint(e.getX(), e.getY(), false);
				model.addPoint(e.getX(), e.getY(), false);
				repaint();
				// System.out.println("Mouse Pressed");
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				pos_x = -1;
				pos_x = -1;
				drag_x = -1;
				drag_y = -1;

				// paint = false;
				replay.updateSlider(model.getTime());
				model.addPoint(e.getX(), e.getY(), true);
				// System.out.println("Mouse Released");
			}
		});

		addMouseMotionListener(new MouseMotionListener() {
			@Override
			public void mouseDragged(MouseEvent e) {
				pos_x = drag_x;
				pos_y = drag_y;

				drag_x = e.getX();
				drag_y = e.getY();

				drag_x = e.getX();
				drag_y = e.getY();

				model.addPoint(e.getX(), e.getY(), false);

				repaint();
			}

			@Override
			public void mouseMoved(MouseEvent e) {
				// _start = e.getPoint();
			}
		});
	}

	// public void externalRepaint() {
	// 	// removeAll(); 
	// 	updateUI();
	// 	revalidate();
	// 	repaint();
	// 	// model.addObserver(this);
	// }

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		int time;
		if(model.getDraw() == true) {
			time = model.getTime();
		} else {
			time = model.getPlayTime();
		}

		// if (model.playing == true) {
		// 	time = model.getPlayTime();
		// 	replay.continuePlay();
		// 	try {
		// 	    Thread.sleep(1000);
		// 	} catch(InterruptedException ex) {
		// 		System.out.println("WTF");
		// 	    Thread.currentThread().interrupt();
		// 	}
		// }

		ArrayList<CanvasHistory> history = model.getPointsUpto(time);

		if(history.size() >= 1) { replay.play.setEnabled(true); replay.slider.setEnabled(true); }
		for(int i=1; i<history.size(); i++) {
			if((history.get(i-1)).blank == true) continue;
			g2.setColor((history.get(i)).color);
			g2.setStroke(new BasicStroke((history.get(i)).thickness));
			g2.drawLine((history.get(i)).xCoord, (history.get(i)).yCoord, (history.get(i-1)).xCoord, (history.get(i-1)).yCoord);
		}
	}

	// Observer interface 
	@Override
	public void update(Observable o, Object arg) {
		System.out.println("Canvas: updateView");
		// just displays a 'C' for each counter value
		// if (model.getCounterValue() > 0)
		// 	this.add(new JLabel("C"));		
	}
}