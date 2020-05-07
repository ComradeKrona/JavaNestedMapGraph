# JavaNestedMapGraph
Data Structures (Spring 2020) - Java

This is a project I worked on in my senior Data Structures class. The project was to create a graph from scratch and then to implement Dijkstra and A* using it. I decided to use a nested map structure (a hashmap with a vertex key and a treemap value which had a vertex key and an edge value). 

# Summary of the Files:
GraphFiles - Directory that holds files for the program to read in (vertex1, vertex2, weight of edge between them)
  AStarFiles - Files designed to test the AStar method
    Cords - File that holds the cords for the vertices in the graph file
    graph - File that holds the connections between vertices
  PensylvaniagraphFiles - Files given by my teacher to grade our program
    penn_coords.csv - CSV File that holds the cords for the vertices in the pensylvania.csv (coords were randomily generator, they are not based on real information)
    pensylvania.csv - CSV file that hold the conenctions between vertices (I believe that those are based on real information)
  simpleGraph - File that holds the first graph that my teacher gave me to test my program
   fixingDijkstraData - My testing file to make sure my dijkstra implemtation was working correctly
src - Folder that holds the rest of the files
  City - My extension of Vertex, holds nothing new
  Edge - Implements EdgeADT, holds the numerical weight
  EdgeADT - Interface given by my teacher to create a Edge class
  Graph - Graph class were everything is based in, I have fully documented the code here
  Main - An old main where I created a graph from scratch
  PathFindingGraph - An extension of graph designed to run Dijkstra and A*, fully documented
  PathFindingMain - Where the main function is located to run my program, start here
  Road - My extension of Edge, holds a string
  TestingGraph - Code provided by my teacher to test my graph class, creates a graph from scratch
  Vertex - Implements VertexADT, holds a bunch of functions and varibles needed from the Graph class
  VertexADT - Interface given by my teahcer to create a Vetex class
  
  # Where to Start:
  
  I suggest running the PathFindingMain. That showcases my work the best and is interactable.



