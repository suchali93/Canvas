// inspired by code by Joseph Mack, http://www.austintek.com/mvc/

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

class Thickness extends JPanel implements Observer {

	// the model that this view is showing
	private Model model;
	JSlider slider = new JSlider(JSlider.VERTICAL, 1, 10, 2);

	Thickness(Model model_) {
		// create UI
		
		slider.setMinorTickSpacing(1);
		slider.setMajorTickSpacing(1);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		slider.setLabelTable(slider.createStandardLabels(9));
		
		this.setLayout(new GridLayout());
		this.add(slider);

		// set the model
		model = model_;
		
		// setup the event to go to the "controller"
		// (this anonymous class is essentially the controller)		
		addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					// model.file();
				}
		});

		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int thickness = slider.getValue();
				model.changeThickness(thickness);
			}
		});
	}

	// Observer interface 
	@Override
	public void update(Observable o, Object arg) {
		System.out.println("Thickness: updateView");
		// just displays an 'X' for each counter value
		// if (model.getCounterValue() > 0)
		// 	this.add(new JLabel("P"));		
	}
}
