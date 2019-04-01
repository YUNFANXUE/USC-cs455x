// Name: Yunfan Xue
// USC NetID: yunfanxu
// CS 455 PA1
// Fall 2018

/**
 * Bar class
 * A labeled bar that can serve as a single bar in a bar graph.
 * The text for the label is centered under the bar.
 *
 * NOTE: we have provided the public interface for this class. Do not change
 * the public interface. You can add private instance variables, constants,
 * and private methods to the class. You will also be completing the
 * implementation of the methods given.
 *
 * @param barBottom  location of the bottom of the label
 * @param barHt  the exact pixel that the bar need
 * @param barLeft location of the left side of the bar
 * @param barWidth  width of the bar(in pixels)
 * @param barTop  location of the top of the bar
 * @param barColor color of the bar
 * @param barStr  label info of the bar
 * @param labelColor label color.
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;

public class Bar {
    private int barBottom;
    private int barHt; // barHight with pixels
    private int barLeft;
    private int barWidth;
    private int barTop;
    private Color barColor;
    private String barStr;
    private Color labelColor = new Color(0, 0, 0);
   
    /**
      Creates a labeled bar.  You give the height of the bar in application
      units (e.g., population of a particular state), and then a scale for how
      tall to display it on the screen (parameter scale). 
  
      @param bottom  location of the bottom of the label
      @param left  location of the left side of the bar
      @param width  width of the bar (in pixels)
      @param barHeight  height of the bar in application units
      @param scale  how many pixels per application unit
      @param color  the color of the bar
      @param label  the label at the bottom of the bar
    */
    public Bar(int bottom, int left, int width, int barHeight,
              double scale, Color color, String label) {
	barBottom = bottom;
	barLeft = left;
	barWidth = width;
	barHt =(int) (barHeight * scale) / 100;
	barTop = barBottom - barHt - 15; // set 15 pixels space to draw a label
	barColor = color;
	barStr = label;
   }
   
    /**
      Draw the labeled bar. 
      @param g2  the graphics context
    */
    public void draw(Graphics2D g2) {
	//Draw a Rect with "barColor" color
	g2.setColor(barColor);
	Rectangle barBody = new Rectangle(barLeft, barTop, barWidth, barHt);
	g2.fill(barBody);
	
	//Draw a label 
	Font labelFont = g2.getFont();
	FontRenderContext labelContext = g2.getFontRenderContext();
	Rectangle2D labelBounds = labelFont.getStringBounds(barStr,labelContext);
	int widthLabel = (int)labelBounds.getWidth();
	int labelLeft = (int)(barLeft + 0.5 * barWidth - 0.5 * widthLabel);
	
	//set the label color to black
	g2.setColor(labelColor);

	//draw the string at the bottom position
	g2.drawString(barStr, labelLeft, barBottom);
   }
}
