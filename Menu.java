// inspired by code by Joseph Mack, http://www.austintek.com/mvc/

import javax.swing.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

class Menu extends JMenuBar implements Observer {
	// the view's main user interface
	private JMenu file; 
	private JMenu view;
	public JMenuItem fullscreen;
	public JMenuItem fitscreen;
	JFileChooser fc;
	Canvas canvas = null;

	// the model that this view is showing
	private Model model;

	Menu(Model model_) {
		fc = new JFileChooser();

		file = new JMenu("File");
		
		JMenuItem load = new JMenuItem("Load", 'L');
		load.setMnemonic(KeyEvent.VK_L);
		load.setToolTipText("Load Existing Doodle");
		load.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				int returnVal = fc.showOpenDialog(Menu.this);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					ArrayList <CanvasHistory> history = new ArrayList<CanvasHistory>();
					try {
						ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fc.getSelectedFile()));
						HistoryCount metadata1 = new HistoryCount(0);
						HistoryCount metadata2 = new HistoryCount(0);
						HistoryCount metadata3 = new HistoryCount(0);
						try {
							metadata1 = (HistoryCount)ois.readObject();
							metadata2 = (HistoryCount)ois.readObject();
							metadata3 = (HistoryCount)ois.readObject();
							model.setTime(metadata2.count);
							model.setPlayTime(metadata3.count);
							// System.out.println(metadata.count);
						} catch (Exception e) {
							System.out.println(e);
						}
						for (int i = 0; i < metadata1.count; i++) {
							try {
								CanvasHistory temp = (CanvasHistory)ois.readObject();
								// System.out.println(temp.blank);
								history.add(temp);
							} catch (Exception e) {
								System.out.println(e);
							}
						    System.out.println(i);
						}

					} catch (Exception e) {
						System.out.println(e);
					}
					model.loadPoints(history);
					canvas.updateUI();
				}
			}
		});

		// JMenuItem create = new JMenuItem("Create", 'C');
		// create.setMnemonic(KeyEvent.VK_C);
		// create.setToolTipText("Create New Doodle");
		// create.addActionListener(new ActionListener() {
		// 	@Override
		// 	public void actionPerformed(ActionEvent event) {
		// 		System.exit(0);
		// 	}
		// });

		JMenuItem save = new JMenuItem("Save", 'S');
		save.setMnemonic(KeyEvent.VK_S);
		save.setToolTipText("Save Doodle");
		save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				int returnVal = fc.showSaveDialog(Menu.this);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					ArrayList <CanvasHistory> history = model.getPointsUpto(model.getTime());
					HistoryCount metadata1 = new HistoryCount(history.size());
					HistoryCount metadata2 = new HistoryCount(model.getTime());
					HistoryCount metadata3 = new HistoryCount(model.getPlayTime());
					System.out.println("SAVE");
					// System.out.println(metadata.count);
					try {
						ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fc.getSelectedFile()+".txt"));
						try {
							oos.writeObject(metadata1);
							oos.writeObject(metadata2);
							oos.writeObject(metadata3);
						} catch (Exception e) {
							System.out.println(e);
						}
						for (int i = 0; i < history.size(); i++) {
							try {
								oos.writeObject(history.get(i));
							} catch (Exception e) {
								System.out.println(e);
							}
						}

					} catch (Exception e) {
						System.out.println(e);
					}
				}
			}
		});

		JMenuItem exit = new JMenuItem("Exit", 'E');
		exit.setMnemonic(KeyEvent.VK_E);
		exit.setToolTipText("Exit application");
		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				System.exit(0);
			}
		});

		view = new JMenu("View");

		fullscreen = new JMenuItem("Full Screen", 'U');
		fullscreen.setMnemonic(KeyEvent.VK_U);
		fullscreen.setToolTipText("Full/Regular Screen View Toggle");


		fitscreen = new JMenuItem("Fit Screen", 'I');
		fitscreen.setMnemonic(KeyEvent.VK_I);
		fitscreen.setToolTipText("Fit Application Window on Screen");
		fitscreen.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				// System.exit(0);
			}
		});

		file.add(load);
		// file.add(create);
		file.add(save);
		file.add(exit);

		view.add(fullscreen);
		view.add(fitscreen);

		this.add(file);
		this.add(view);
		
		// set the model
		model = model_;
	}

	// Observer interface 
	@Override
	public void update(Observable o, Object arg) {
		System.out.println("Menu: updateView");
	}
}
