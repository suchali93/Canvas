import java.io.Serializable;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.awt.image.*;

class CanvasHistory implements Serializable {
	public int xCoord, yCoord, thickness;
	public Color color;
	public int time;
	public boolean blank;

	CanvasHistory(int x, int y, int th, Color c, int t, boolean b) {
		xCoord = x;
		yCoord = y;
		thickness = th;
		color = c;
		time = t;
		blank = b;
	}
}