Helper classes for easy plotting of multiple data sets.
In order to create a plot, simply instantiate MultiGraph with some data and then plot!

---------------
Usage
-------------

  //First, load the data into an ArrayList of points

  ArrayList<Point> data1 = new ArrayList<Point>();
  data1.add(new Point(0  ,0  ));
  data1.add(new Point(0.5,0.5));
  data1.add(new Point(1  ,1  ));
  data1.add(new Point(2  ,2  ));

  ArrayList<Point> data2 = new ArrayList<Point>();
  data2.add(new Point(0  ,0   ));
  data2.add(new Point(0.5,0.25));
  data2.add(new Point(1  ,1   ));
  data2.add(new Point(2  ,4   ));

  //Set Individual plot parameters
  
  Graph linear = 		new Graph(data1);
  linear.setTitle("Linear");

  Graph quadratic = 		new Graph(data2);
  quadratic.setTitle("Quadratic");

  //Create List of plots
  
  ArrayList<Graph> graphs = new ArrayList<Graph>();
  graphs.add(linear);
  graphs.add(quadratic);
  
  //Final graph object
  MultiGraph finalPlot = new MultiGraph(graphs);
  
  //Show 500x500 plot window
  finalPlot.plotFrame(500,500);