import java.awt.Color;
import java.util.ArrayList;	

public class Graph {
  public int pointSize;
  public Color  color;
  public ArrayList<Point> data;
  public String title;
  
  public Graph(ArrayList<Point> points) {
    data = points;
    color = null;
    pointSize = 1;
    title = "";
  }
  public Graph(ArrayList<Point> points, Color pointColor) {
    this(points);
    color = pointColor;
  }
  public Graph(ArrayList<Point> points, int ptSize) {
    this(points);
    pointSize = ptSize;
  }
  public Graph(ArrayList<Point> points, int ptSize, Color ptColor) {
    this(points);
    pointSize = ptSize;
    color = ptColor;
  }
  public Graph setPointSize(int ptSize) {
    pointSize = ptSize;
    return this;
  }
  public Graph setColor(Color clr) {
    color = clr;
    return this;
  }
}