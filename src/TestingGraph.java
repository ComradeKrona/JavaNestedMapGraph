public class TestingGraph {

    public static void main(String[] args) {
        PathFindingGraph myGraph = new PathFindingGraph();

        City cityOne = new City("1", 1.0, 3.0);
        City cityTwo = new City("2", 9.0, -3.0);
        City cityThree = new City("3", -40.0, 10.0);

        myGraph.addVertex(cityOne);
        myGraph.addVertex(cityThree);
        myGraph.addVertex(cityTwo);

        myGraph.addEdge(cityOne, cityTwo, new Road(4.0));
        myGraph.addEdge(cityOne, cityThree, new Road(4.0));

        myGraph.setRoot(new City("1"));

        /*System.out.println();
        System.out.println(myGraph);
        System.out.println();

        System.out.println(myGraph.getNeighbors(myGraph.getVertices().get(0)));

        for (City items : myGraph.getVertices()){
            myGraph.distanceFormula(items, cityThree);
        }

        System.out.println();
        System.out.println(myGraph);
        System.out.println(); */

        System.out.println(myGraph);
        System.out.println();

        for(City item : myGraph.getNeighbors(myGraph.getVertices().get(0))){
            item.setDistanceFromGoal(7.5);
        }

        System.out.println(myGraph);




    }

}
