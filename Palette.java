// inspired by code by Joseph Mack, http://www.austintek.com/mvc/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

class Palette extends JPanel implements Observer {

	// the model that this view is showing
	private Model model;
	private int colorR = 5;
	private int colorC = 3;
	public Color origColor = Color.BLACK;
	private JButton c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, colorChooser, custom;

	Palette(Model model_) {
		// create UI
		// setBackground(Color.WHITE);
		c1 = new JButton();
		c1.setBackground(Color.BLACK);
		c1.setPreferredSize(new Dimension(25,25));
		c2 = new JButton();
		c2.setBackground(new Color(128, 128, 128));
		c2.setPreferredSize(new Dimension(25,25));
		c3 = new JButton();
		c3.setBackground(new Color(192, 192, 192));
		c3.setPreferredSize(new Dimension(25,25));
		c4 = new JButton();
		c4.setBackground(Color.WHITE);
		c4.setPreferredSize(new Dimension(25,25));
		c5 = new JButton();
		c5.setBackground(new Color(128, 0, 0));
		c5.setPreferredSize(new Dimension(25,25));
		c6 = new JButton();
		c6.setBackground(Color.RED);
		c6.setPreferredSize(new Dimension(25,25));
		c7 = new JButton();
		c7.setBackground(Color.YELLOW);
		c7.setPreferredSize(new Dimension(25,25));
		c8 = new JButton();
		c8.setBackground(new Color(0, 128, 0));
		c8.setPreferredSize(new Dimension(25,25));
		c9 = new JButton();
		c9.setBackground(new Color(0, 255, 0));
		c9.setPreferredSize(new Dimension(25,25));
		c10 = new JButton();
		c10.setBackground(new Color(0, 128, 128));
		c10.setPreferredSize(new Dimension(25,25));
		c11 = new JButton();
		c11.setBackground(new Color(0, 255, 255));
		c11.setPreferredSize(new Dimension(25,25));
		c12 = new JButton();
		c12.setBackground(new Color(0, 0, 128));
		c12.setPreferredSize(new Dimension(25,25));
		c13 = new JButton();
		c13.setBackground(Color.BLUE);
		c13.setPreferredSize(new Dimension(25,25));
		c14 = new JButton();
		c14.setBackground(new Color(128, 0, 128));
		c14.setPreferredSize(new Dimension(25,25));
		c15 = new JButton();
		c15.setBackground(new Color(255, 0, 255));
		c15.setPreferredSize(new Dimension(25,25));

		custom = new JButton();
		custom.setBackground(Color.BLACK);
		custom.setPreferredSize(new Dimension(25,25));
		
		colorChooser = new JButton("Custom");
		colorChooser.setPreferredSize(new Dimension(85, 25));
		colorChooser.setMargin(new Insets(0,0,0,0));

		JPanel colorPanel = new JPanel();
		colorPanel.add(c1);
		colorPanel.add(c2);
		colorPanel.add(c3);
		colorPanel.add(c4);
		colorPanel.add(c5);
		colorPanel.add(c6);
		colorPanel.add(c7);
		colorPanel.add(c8);
		colorPanel.add(c9);
		colorPanel.add(c10);
		colorPanel.add(c11);
		colorPanel.add(c12);
		colorPanel.add(c13);
		colorPanel.add(c14);
		colorPanel.add(c15);
		colorPanel.add(colorChooser);
		colorPanel.add(custom);

		this.setLayout(new GridLayout());
		this.add(colorPanel);

		// set the model
		model = model_;
		
		// setup the event to go to the "controller"
		// (this anonymous class is essentially the controller)
		colorChooser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color c = JColorChooser.showDialog(null, "Choose a Color", custom.getBackground());
				if(c==null) {
					c = origColor;	
				} else {
					origColor = c;
				}
				custom.setBackground(c);
				model.changeColor(c);
			}
		});
		c1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color c = c1.getBackground();
				if(c==null) {
					c = origColor;	
				} else {
					origColor = c;
				}			
				custom.setBackground(c);
				model.changeColor(c);
			}
		});
		c2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color c = c2.getBackground();
				if(c==null) {
					c = origColor;	
				} else {
					origColor = c;
				}			
				custom.setBackground(c);
				model.changeColor(c);
			}
		});
		c3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color c = c3.getBackground();
				if(c==null) {
					c = origColor;	
				} else {
					origColor = c;
				}			
				custom.setBackground(c);
				model.changeColor(c);
			}
		});
		c4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color c = c4.getBackground();
				if(c==null) {
					c = origColor;
				} else {
					origColor = c;
				}
				custom.setBackground(c);
				model.changeColor(c);			
			}
		});
		c5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color c = c5.getBackground();
				if(c==null) {
					c = origColor;
				} else {
					origColor = c;
				}
				custom.setBackground(c);	
				model.changeColor(c);		
			}
		});
		c6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color c = c6.getBackground();
				if(c==null) {
					c = origColor;
				} else {
					origColor = c;
				}
				custom.setBackground(c);	
				model.changeColor(c);		
			}
		});
		c7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color c = c7.getBackground();
				if(c==null) {
					c = origColor;
				} else {
					origColor = c;
				}
				custom.setBackground(c);	
				model.changeColor(c);		
			}
		});
		c8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color c = c8.getBackground();
				if(c==null) {
					c = origColor;
				} else {
					origColor = c;
				}
				custom.setBackground(c);
				model.changeColor(c);	
				model.changeColor(c);		
			}
		});
		c9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color c = c9.getBackground();
				if(c==null) {
					c = origColor;
				} else {
					origColor = c;
				}
				custom.setBackground(c);
				model.changeColor(c);			
			}
		});
		c10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color c = c10.getBackground();
				if(c==null) {
					c = origColor;
				} else {
					origColor = c;
				}
				custom.setBackground(c);
				model.changeColor(c);			
			}
		});
		c11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color c = c11.getBackground();
				if(c==null) {
					c = origColor;
				} else {
					origColor = c;
				}
				custom.setBackground(c);
				model.changeColor(c);			
			}
		});
		c12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color c = c12.getBackground();
				if(c==null) {
					c = origColor;
				} else {
					origColor = c;
				}
				custom.setBackground(c);
				model.changeColor(c);			
			}
		});
		c13.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color c = c13.getBackground();
				if(c==null) {
					c = origColor;
				} else {
					origColor = c;
				}
				custom.setBackground(c);
				model.changeColor(c);			
			}
		});
		c14.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color c = c14.getBackground();
				if(c==null) {
					c = origColor;
				} else {
					origColor = c;
				}
				custom.setBackground(c);
				model.changeColor(c);			
			}
		});
		c15.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color c = c15.getBackground();
				if(c==null) {
					c = origColor;
				} else {
					origColor = c;
				}
				custom.setBackground(c);
				model.changeColor(c);			
			}
		});
	}

	// Observer interface 
	@Override
	public void update(Observable o, Object arg) {
		System.out.println("Palette: updateView");
		// just displays an 'X' for each counter value
		// if (model.getCounterValue() > 0)
		// 	this.add(new JLabel("P"));		
	}
}
