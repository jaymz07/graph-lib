import java.util.*;
import java.io.*;
import java.applet.*;
import java.awt.*;

public class MultiGraph
{
    public int WIDTH=1000;
    public int HEIGHT=600;
    public ArrayList<Graph> graphs;
    public ArrayList<ArrayList<Point>> 	sPoints = null;
    public int pSize=1;
    public double minX, minY, maxX, maxY;

    private double rangeX=0, rangeY=0, ppuX=0, ppuY=0;
    public ArrayList<Color> 	colors = null;
    public ArrayList<Double> 	pointSizes = null;

    public MultiGraph(ArrayList<Graph> graphList) {
        graphs=graphList;

        //Find bounds of data and set that as default plot range
        minX=graphs.get(0).data.get(0).getX();
        minY=graphs.get(0).data.get(0).getY();
        maxX=minX;
        maxY=minY;

        for(int i=0; i<graphs.size(); i++) {
	    ArrayList<Point> graphData = graphs.get(i).data;
            for(int j=0; j<graphData.size(); j++) {
                minX=Math.min(minX,graphData.get(j).x);
                minY=Math.min(minY,graphData.get(j).y);
                maxX=Math.max(maxX,graphData.get(j).x);
                maxY=Math.max(maxY,graphData.get(j).y);
            }
        }
    }
    
   /* public MultiGraph(ArrayList<ArrayList<Point>> a, int w, int h)
    {
	ArrayList<Graph> graphList = new ArrayList<Graph>();
	for(ArrayList<Point> dataList : a)
	  graphList.add(new Graph(dataList));
	page = b;
	WIDTH = w;
	HEIGHT =h;
    }*/
    //------------Object builder patterns-------------
    public MultiGraph setMaxX(double max_X) {
        maxX = max_X;
        sPoints = null;
        return this;
    }
    public MultiGraph setMaxY(double max_Y) {
        maxY = max_Y;
        sPoints = null;
        return this;
    }
    public MultiGraph setMinX(double min_X) {
        minX = min_X;
        sPoints = null;
        return this;
    }
    public MultiGraph setMinY(double min_Y) {
        minY = min_Y;
        sPoints = null;
        return this;
    }

    public void setColors() {
        int numUncoloredGraphs = 0, count = 0;
        for(Graph g : graphs)
            if(g.color == null)
                numUncoloredGraphs ++;
        ArrayList<Color> colorsOut = new ArrayList<Color>();
        for(Graph g : graphs) {
            if(g.color == null) {
                colorsOut.add(getColorValue(count,0,numUncoloredGraphs));
                count++;
            }
            else
                colorsOut.add(g.color);
        }
        colors = colorsOut;
    }

    public void generateScreenPoints()
    {
        setPlotParams();
        sPoints=new ArrayList<ArrayList<Point>>();

        for(int i=0; i<graphs.size(); i++)
        {
            ArrayList<Point> pts= new ArrayList<Point>();
            for(int j=0; j<graphs.get(i).data.size(); j++)
                pts.add(scrPoint(graphs.get(i).data.get(j)));
            sPoints.add(pts);
        }
    }
    public void printGraph(Graphics page, int w, int h)
    {
	WIDTH = w;
	HEIGHT =h;
	setPlotParams();
	
        if(sPoints == null)
            generateScreenPoints();
            
        if(colors == null)
            setColors();

        //page.setColor(Color.WHITE);
        //page.fillRect(0,0,WIDTH,HEIGHT);

        for(int i=0; i<sPoints.size(); i++)
        {
            page.setColor(colors.get(i));
            int x=(int)sPoints.get(i).get(0).getX(), y=(int)sPoints.get(i).get(0).getY();
            pSize = graphs.get(i).pointSize;

            for(int j =0; j<sPoints.get(i).size(); j++)
            {
                Point p = sPoints.get(i).get(j);
                if(pSize>1)
                    page.fillRect((int)p.getX()-pSize/2,(int)p.getY()-pSize/2,pSize,pSize);
                page.drawLine(x,y,(int)p.getX(),(int)p.getY());
                x=(int)p.getX();
                y=(int)p.getY();
            }
        }

        page.setColor(Color.BLACK);

        if(minX<0&&maxX>0)
        {
            Point a = scrPoint(new Point(0,maxY));
            Point b = scrPoint(new Point(0,minY));
            page.drawLine((int)a.getX(),(int)a.getY(),(int)b.getX(),(int)b.getY());
        }
        if(minY<0&&maxY>0)
        {
            Point a = scrPoint(new Point(maxX,0));
            Point b = scrPoint(new Point(minX,0));
            page.drawLine((int)a.getX(),(int)a.getY(),(int)b.getX(),(int)b.getY());
        }
    }

    private void setPlotParams() {
        rangeX=maxX-minX;
        rangeY=maxY-minY;
        ppuY=(WIDTH)/(rangeX);
        ppuX=(HEIGHT)/(rangeY);
    }

    public Point scrPoint(Point p)
    {
        return new Point(((p.getX()-minX))*ppuY,HEIGHT-(p.getY()-minY)*ppuX);
    }
    public Color getColorValue(double in, double min, double max)
    {
        float val=(float)(Math.pow((in-min),1)/Math.pow((max-min),1));
        Color c = Color.getHSBColor(val,1.0f,0.75f);
        return new Color(c.getRGB());
    }

}
