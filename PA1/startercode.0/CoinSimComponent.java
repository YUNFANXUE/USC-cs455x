// Name: Yunfan Xue
// USC NetID: yunfanxu
// CS 455 PA1
// Fall 2018

/**
 * CoinSimComponent.java
 * Extends JComponent.
 * Constructor initializes any necessary data and runs the simulation.
 * Overrides paintComponent to draw the bar graph, using Bar objects for each bar in the graph.
 * This class uses the CoinTossSimulator and Bar class.
 * 
 * Get the coin simulator data and draw three bars on the window.
 * 
 * @param trialsNum store number of trials
 * @param width  bar width
 * @param LABEL_BELOW  the pixels space that we provide to draw a label.
 * @param verticalBuffer  vb, the distance between bar bottom and window bottom
 * @param bottom  the position of the bottom of the bar.
 * @param widthBetweenBar the distance betweent the bar.
 * @param firBarHeight  the percentage of the first bar(as known as the units)
 * @param secondBarHeight the percentage of the second bar
 * @param thirdBarHeight  the percentage of the third bar.
 * @param numTwoHeads  store the number data of two heads
 * @param numHeadTrials store the number data of head tail.
 * @param numTwoTails  store the number data of two tails.
 * @param scale  store the scale data.
 * */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;

public class CoinSimComponent extends JComponent {

    private int trialsNum;
    private final int width;
    private final int LABEL_BELOW;
    private final int verticalBuffer;
    private int bottom;
    private int widthBetweenBar;
    private int firstBarHeight; //barHeight should be equal to the percantage of the bar
    private int secondBarHeight;
    private int thirdBarHeight;
    private int numTwoHeads;
    private int numHeadTails;
    private int numTwoTails;
    private double scale;

    /**initial the CoinSimComponent.
     * set bar width to 60;
     * verticalBuffer to 30;
     * create a simulator and input trialsNum to get the data of coin toss simulation.
     * set LABEL_BELOW to 15;
     * */
    public CoinSimComponent(int trialsNum){
	this.trialsNum = trialsNum;
	width = 60;
	verticalBuffer = 30;
	CoinTossSimulator simulator = new CoinTossSimulator();
	simulator.run(this.trialsNum);
	
        firstBarHeight = Math.round(simulator.getTwoHeads() * 100 / simulator.getNumTrials()); //barHeight should be equal to the percantage of the bar
        secondBarHeight = Math.round(simulator.getHeadTails() * 100 / simulator.getNumTrials());
        thirdBarHeight = Math.round(simulator.getTwoTails() * 100 / simulator.getNumTrials());
	numTwoHeads = simulator.getTwoHeads();
	numHeadTails = simulator.getHeadTails();
	numTwoTails = simulator.getTwoTails();
	LABEL_BELOW = 15;
    }

    //paint three bar
    public void paintComponent(Graphics g) {
	
	/**Parameter Setting.
	 * @param verticalBuffer the top and bottom distance from the window top or window bottom
	 * @param bottom  location of the bottom of the label
         * @param widthBetweenBar width between the bars
         * @param width  width of the bar (in pixels)
         * @param barHeight  height of the bar in application units
         * @param scale  how many pixels per application unit
	 **/
	
        bottom = getHeight() - verticalBuffer;
        widthBetweenBar = ( getWidth() - 3 * width ) / 4;
        scale = (getHeight() - 2 * verticalBuffer - LABEL_BELOW); //scale = (window height - 2 * vertical buffer) / 100 percent
	
	Color firstBarColor = new Color(255, 0, 0); //set the first bar to red
	Color secondBarColor = new Color(0, 255, 0); //set the second bar to green
	Color thirdBarColor = new Color(0, 0, 255); // set the third bar to blue
	Graphics2D g2 = (Graphics2D) g;

	//Bar(int bottom, int left, int width, int barHeight, double scale, Color color, String label)
	Bar bar1 = new Bar(bottom, widthBetweenBar, width, firstBarHeight, scale, firstBarColor,
		           "Two Heads: " + numTwoHeads + " (" + firstBarHeight + "%)");

	Bar bar2 = new Bar(bottom, (width + 2 * widthBetweenBar), width, secondBarHeight, scale, secondBarColor,
		           "A Head and a Tail: " + numHeadTails + " (" + secondBarHeight + "%)" );

	Bar bar3 = new Bar(bottom, (2 * width + 3 * widthBetweenBar), width, thirdBarHeight, scale, thirdBarColor,
		           "Two Tails: " + numTwoTails + " (" + thirdBarHeight + "%)");
	
	//draw three bars
	bar1.draw(g2);
	bar2.draw(g2);
	bar3.draw(g2);
    }
}
