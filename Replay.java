// inspired by code by Joseph Mack, http://www.austintek.com/mvc/

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JSlider;
import javax.swing.ImageIcon;
import javax.swing.Icon;
import javax.swing.BorderFactory;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

class Replay extends JPanel implements Observer {

	// the view's main user interface
	public JButton play;
	public JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 0, 0);
	private JButton start;
	private JButton end;
	private JButton direction;

	Timer timer;

	// the model that this view is showing
	private Model model;
	private Canvas canvas;

	public void updateSlider(int t) {
		slider.setMaximum(model.getTime()+1);
		slider.setValue(t+1);
	}

	public void continuePlay() {
		play.doClick();
		try {
			Thread.sleep(1000);
		} catch(InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}
	
	Replay(Model model_, Canvas canvas_) {
		// create the replay bar UI

		direction = new JButton("Forward");

		final Icon playIcon = new ImageIcon("play.png");
		final Icon pauseIcon = new ImageIcon("pause.png");

		play = new JButton(playIcon);
		play.setBorder(BorderFactory.createEmptyBorder());
		play.setContentAreaFilled(false);
		play.setEnabled(false);

		// slider.addChangeListener(this);

		slider.setMinorTickSpacing(1);
		slider.setMajorTickSpacing(5);
		slider.setPaintTicks(true);
		slider.setEnabled(false);


		start = new JButton("Start");
		end = new JButton("End");
		JPanel bottomButtonPanel = new JPanel();
		bottomButtonPanel.add(direction);
		bottomButtonPanel.add(play);

		bottomButtonPanel.add(slider);

		bottomButtonPanel.add(start);
		bottomButtonPanel.add(end);

		this.setLayout(new BorderLayout());
		this.add(bottomButtonPanel, BorderLayout.CENTER);
		
		// set the model
		model = model_;
		canvas = canvas_;


		class Interrupt extends TimerTask {
			public void run() {
				play.doClick();
			}
		}

		// setup the event to go to the "controller"
		// (this anonymous class is essentially the controller)

		direction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				direction.setText(direction.getText()=="Forward"? "Backward" : "Forward");
			}
		});


		play.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(model.playing == false) {
					timer = new Timer();
					timer.schedule(new Interrupt(), 0, 1000);
				}
				System.out.println(model.getTime());
				System.out.println(model.getPlayTime());
				model.setDraw(false);
				if (slider.getValue() < slider.getMaximum() && direction.getText()=="Forward") {
					model.playing = true;
					model.setPlayTime(slider.getValue());
					slider.setValue(slider.getValue()+1);
				} else if(slider.getValue() > 0 && direction.getText()=="Backward") {
					model.playing = true;
					model.setPlayTime(slider.getValue());
					slider.setValue(slider.getValue()-1);
				} else {
					model.playing = false;
					timer.cancel();
					timer.purge();
				}
				canvas.updateUI();
			}
		});

		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int tick = slider.getValue();
				slider.setMaximum(model.getTime()+1);
				model.changePlayTick(tick);
				model.setPlayTime(tick-1);
				canvas.updateUI();
				model.setDraw(false);
			}
		});

		start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.start();
				model.setDraw(false);
				slider.setValue(-1);  // PROBLEM??
				timer.cancel();
				timer.purge();
				model.playing = false;
			}
		});	

		end.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.end();
				model.setDraw(false);
				slider.setValue(slider.getMaximum());
				timer.cancel();
				timer.purge();
				model.playing = false;
			}
		});	
	}

	// Observer interface 
	@Override
	public void update(Observable arg0, Object arg1) {
		System.out.println("Replay: updateView");
		// button.setText(Integer.toString(model.getCounterValue()));
	}
}