// City and Road will extend your Vertex and Edge classes respectively

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        City city1 = new City("Chicago");
        City city2 = new City("Cleveland");
        City city3 = new City("Dallas");

        Road road1 = new Road("I-90", 9.0);
        Road road2 = new Road("I-275", 8.0);

        ArrayList<Road> theRoads = new ArrayList<>();
        ArrayList<City> theCities = new ArrayList<>();

        theRoads.add(road1);
        theRoads.add(road2);
        theCities.add(city1);
        theCities.add(city2);
        theCities.add(city3);

        Graph<City, Road> myGraph = new Graph<>();

        for(City city:theCities)
            myGraph.addVertex(city);

        // creates a link between Chicago and Cleveland
        myGraph.addEdge(city1,city2,road1);

        // creates a link between Chicago and Dallas
        myGraph.addEdge(city1,city3,road2);

        // expected output:
        // Chicago -> Cleveland, Dallas
        // Cleveland ->
        // Dallas ->
        System.out.println(myGraph);

        // expected output:
        // I-90
        System.out.println(myGraph.getEdge(city1,city2).getName());

        myGraph.removeVertex(city2);
        System.out.println("After removal");
        System.out.println(myGraph);



    }
}