// inspired by code by Joseph Mack, http://www.austintek.com/mvc/


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;

public class DoodleMain extends JFrame {
	static Menu menu;
	static JFrame frame;
	
	DoodleMain(String title, Model model_) {
		super(title);
		// MENU-START ----------------------------------------------------------
		menu = new Menu(model_);
		setJMenuBar(menu);
		// MENU-END ------------------------------------------------------------
	}

	public static void main(String[] args) {

		JSplitPane splitPane;	
		// create Model and initialize it
		Model model = new Model();

		frame = new DoodleMain("Doodler", model);
		
		// create Canvas
		Canvas canvas = new Canvas(model);
		model.addObserver(canvas);

		menu.canvas = canvas;

		// create Palette
		Palette palette = new Palette(model);
		model.addObserver(palette);

		// create Thickness
		Thickness thickness = new Thickness(model);
		model.addObserver(thickness);

		// create Replay
		Replay replay = new Replay(model, canvas);
		model.addObserver(replay);
		canvas.replay = replay;
		
		// let all the views know that they're connected to the model
		model.notifyObservers();
		
		// create the window
		JPanel panel = new JPanel(new BorderLayout());
		frame.getContentPane().add(panel);
		
		// LEFT-START ----------------------------------------------------------
		JPanel leftPanel = new JPanel(new BorderLayout());
		panel.add(new JScrollPane(leftPanel), BorderLayout.WEST);

		JPanel palettePanel = new JPanel(new GridLayout());
		palettePanel.setPreferredSize(new Dimension(100,200));
		palettePanel.add(palette);
		leftPanel.add(palettePanel, BorderLayout.CENTER);
		
		JPanel thicknessPanel = new JPanel(new GridLayout());
		thicknessPanel.add(thickness);
		leftPanel.add(thicknessPanel, BorderLayout.SOUTH);

		// LEFT-END -------------------------------------------------------------

		// CANVAS-START ---------------------------------------------------------
		JPanel canvasPanel = new JPanel(new GridBagLayout());
		// canvasPanel.add(new JButton("Canvas"));
		panel.add(new JScrollPane(canvas), BorderLayout.CENTER);
		// // p.add(canvas, c);
		// CANVAS-END -----------------------------------------------------------

		// REPLAY-START ---------------------------------------------------------
		JPanel replayPanel = new JPanel(new BorderLayout());
		panel.add(new JScrollPane(replay), BorderLayout.SOUTH);
		// REPLAY-END -----------------------------------------------------------

		// frame.setContentPane(panel);
		frame.setPreferredSize(new Dimension(700,515));
		frame.pack();
		menu.fullscreen.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
			}
		});
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}