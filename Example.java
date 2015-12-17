/* Java Program for generating analog X,Y signals for a laser projector using the analog soundcard output.
 * Drawing on graphical window reflects the laser output.
 * This class defines a window, gets some points from mouse input and passes these points to a seperate
 * thread defined by LaserOutputThread.java, which handles all audio output.
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;
import java.io.*;
import java.net.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Example implements MouseListener, MouseMotionListener, KeyListener {
    
  //-------------------Main Program Parameters-----------------------
    //Window settings
    int width = 500, height = 500;
    
  //----------------------Some global constants-----------------------    
    JFrame frame;
    DrawPanel drawPanel;
    Graphics page;
    MultiGraph finalGraph;
    Point view = new Point(0,0);
    Point mousePoint = new Point(0,0);
    
  //---------Generate Display points------------
    public ArrayList<Point> generateGraphPoints(double sineFreq)
    {
        ArrayList<Point> out = new ArrayList<Point>();
        for(double x = -5.0; x< 5.0; x+=0.05)
	  out.add(new Point(x-view.x,3*Math.sin(sineFreq*(x-view.x))));
        return out;
    }
    
  /*----------Main function. Calls constructor of whole class-----------*/
    public static void main(String[] args) {
        for(String s : args)
            System.out.println(s);
        (new Example()).run() ;
    }
    
    public void run() {

        /*---------Make window-----------*/
        frame = new JFrame("Tunneling Demo");
	
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setSize(width,height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //drawPanel actually writes to the screen. frame is just a container
        drawPanel = new DrawPanel();
        frame.getContentPane().add(BorderLayout.CENTER, drawPanel);

        //Allow keboard and mouse input for the window
        //also tells frame to call method overrides for the listeners
        frame.addMouseListener(this);
        frame.addMouseMotionListener( this );
        frame.addKeyListener ( this ) ;
        
        //--------Generate Graphs and add them to list-----------
        generateGraphs();
        

    }
    
    public void generateGraphs() {
	ArrayList<Graph> graphs = new ArrayList<Graph>();
        graphs.add((new Graph(generateGraphPoints(0.5))).setPointSize(2).setColor(Color.BLACK));
        graphs.add((new Graph(generateGraphPoints(0.8))).setPointSize(4));
        graphs.add(new Graph(generateGraphPoints(1.0)));
        graphs.add(new Graph(generateGraphPoints(2.0)));
        //----------Make Plotting object---------------
        finalGraph = new MultiGraph(graphs);
    }

    /*----------------Nested class defining object to be painted to screen.--------------------
    	  Simply overrides paintComponent() defined in JPanel*/
    class DrawPanel extends JPanel {
        public void paintComponent(Graphics g) {
            page = g;
            //page = iOut.getGraphics();

            //White background
            page.setColor(Color.WHITE);
            page.fillRect(0,0,width,height);
            
            //------Plot Command-------
            finalGraph.findBounds();
            finalGraph.printGraph(page,width,height);
        }
    }

    /*----------Keyboard Input. Event generated function calls.----------------------*/

    public void keyTyped ( KeyEvent e ) { }
    public void keyPressed ( KeyEvent e) {
        //System.out.println("KEY");
        if(e.getKeyCode()== KeyEvent.VK_C) {
        }

        frame.repaint();
    }
    public void keyReleased ( KeyEvent e ) {}

    /*-----------Mouse Input. Event generated function calls------------------------*/
    public void mouseEntered( MouseEvent e ) {
        // called when the pointer enters the applet's rectangular area
    }
    public void mouseExited( MouseEvent e ) {}  // called when the pointer leaves the applet's rectangular area
    public void mouseClicked( MouseEvent e ) {}
    public void mousePressed( MouseEvent e ) {  // called after a button is pressed down
        int xP=e.getX();
        int yP=e.getY();
        mousePoint = new Point(xP,yP);
    }
    public void mouseReleased( MouseEvent e ) {}  // called after a button is released
    public void mouseMoved( MouseEvent e )    {}  // called during motion when no buttons are down
    public void mouseDragged( MouseEvent e )  {   // called during motion with buttons down
        int xP=e.getX();
        int yP=e.getY();
        view.x += (xP-mousePoint.x)*0.1;
	mousePoint = new Point(xP,yP);
	generateGraphs();
	frame.repaint();
    }
 
}






