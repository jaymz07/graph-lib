/*Example of using the MultiGraph class for plotting data.*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;
import java.io.*;

public class ExampleInternalWindow {

//------------Window settings------------------------
    int width = 500, height = 500;

//---------Generate Display points------------
    public void run() {
      
      ArrayList<Graph> data = generateGraphs();
      MultiGraph finalGraph = (new MultiGraph(data)).setTitle("Some Sine Graphs");
      finalGraph.plotFrame(width,height);
      
    }
    //------------Make a set of plots to show on common axes----------------
    public ArrayList<Graph> generateGraphs() {
        ArrayList<Graph> graphs = new ArrayList<Graph>();
        graphs.add( (new Graph(generateSineGraph(0.5))).setPointSize(2).setColor(Color.BLACK).setTitle("A sine Graph!") 	);
        graphs.add( (new Graph(generateSineGraph(0.8))).setPointSize(4).setColor(Color.GRAY).setTitle("A sine Graph!") 				);
        graphs.add(  new Graph(generateSineGraph(1.0)).setTitle("A sine Graph!") 						);
        graphs.add(  new Graph(generateSineGraph(2.0)).setTitle("A sine Graph!")						);
        return graphs;
    }
    //-----------Method for generating plot points----------
    public ArrayList<Point> generateSineGraph(double sineFreq)
    {
        ArrayList<Point> out = new ArrayList<Point>();
        for(double x = -6.0; x<= 6.0; x+=0.05)
            out.add(new Point(x,0.001*Math.sin(sineFreq*x)));
        return out;
    }

/*----------Main function. Calls constructor of whole class-----------*/
    public static void main(String[] args) {
	(new ExampleInternalWindow()).run();
    }


}