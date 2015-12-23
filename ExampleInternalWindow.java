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
    
//----------------------Some global constants-----------------------
    //Graph object:
    MultiGraph finalGraph;

//---------Generate Display points------------
    public void run() {
      generateGraphs();
      finalGraph.getPlotFrame(width,height);
    }
    public ArrayList<Point> generateGraphPoints(double sineFreq)
    {
        ArrayList<Point> out = new ArrayList<Point>();
        for(double x = -6.0; x< 6.0; x+=0.05)
            out.add(new Point(x,0.001*Math.sin(sineFreq*(x))));
        return out;
    }

/*----------Main function. Calls constructor of whole class-----------*/
    public static void main(String[] args) {
	(new ExampleInternalWindow()).run();
    }


//------------Make individual plots and add them to plot object
    public void generateGraphs() {
        ArrayList<Graph> graphs = new ArrayList<Graph>();
        graphs.add( (new Graph(generateGraphPoints(0.5))).setPointSize(2).setColor(Color.BLACK).setTitle("A sine Graph!") 	);
        graphs.add( (new Graph(generateGraphPoints(0.8))).setPointSize(4).setTitle("A sine Graph!") 				);
        graphs.add(  new Graph(generateGraphPoints(1.0)).setTitle("A sine Graph!") 						);
        graphs.add(  new Graph(generateGraphPoints(2.0)).setTitle("A sine Graph!")						);
        //----------Make Plotting object---------------
        finalGraph = new MultiGraph(graphs);
    }


}






