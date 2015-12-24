import java.util.*;
import java.io.*;
import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.swing.event.*;

public class GraphWindow extends JFrame implements MouseListener, MouseMotionListener, KeyListener {

    public MultiGraph graph;
    public int WIDTH, HEIGHT;

    public GraphWindow(MultiGraph g, int width, int height) {
        super(g.title);
        graph = g;
        WIDTH = width;
        height = height;

        setResizable(false);
        setSize(width,height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel graphIcon = new JLabel(new ImageIcon(graph.getPlotImage()));
        getContentPane().add(graphIcon);
        setVisible(true);
	
	addMouseListener(this);
        addMouseMotionListener( this );
        addKeyListener ( this ) ;
    }

    /*----------Keyboard Input. Event generated function calls.----------------------*/

    public void keyTyped ( KeyEvent e ) { }
    public void keyPressed ( KeyEvent e) {
        if(e.getKeyCode()== KeyEvent.VK_R);
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
	System.out.println(new Point(xP,yP));
    }
    public void mouseReleased( MouseEvent e ) {}  // called after a button is released
    public void mouseMoved( MouseEvent e )    {}  // called during motion when no buttons are down
    public void mouseDragged( MouseEvent e )  {   // called during motion with buttons down
        int xP=e.getX();
        int yP=e.getY();
    }
}
