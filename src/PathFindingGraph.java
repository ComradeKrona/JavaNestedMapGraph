import java.util.*;

public class PathFindingGraph extends Graph<City, Road> {
    //I accidently called most of the vertices "nodes" as a mistake (I know that they are two different things)
    //I try to fix some of it; however, I don't think I got everything

    /** Finds the minimum distance and path from the graph's root to a given goal based on Dijkstra's Method
     *
     * @param toFind - goal which root must path to
     * @return - List of vertices in order to reach goal from root
     */
    public List<City> dijkstra(City toFind) {
        //Resets all vertex values that might have been changed prior
        reset();

        //SE
        long start = System.currentTimeMillis();

        //Minheap which holds the vertices
        //compare based on distance from root
        PriorityQueue<City> minheap = new PriorityQueue<>(new Comparator<City>() {
            @Override
            public int compare(City o1, City o2) {
                return (int) (o1.getMinDistance() - o2.getMinDistance());
            }
        });

        //Set vertex's values to defaults
        this.getRoot().setMinDistance(0);
        this.getRoot().setPathFromRoot(new LinkedList<>());

        //Adding vertex to minheap
        minheap.add(this.getRoot());

        //Boolean to is if goal has been found
        boolean found = false;

        //Main loop which goes through minheap, will stop if the minheap is empty
        while (!minheap.isEmpty()) {
            //Removes the vertex on top of the minheap
            City currentNode = minheap.remove();

            //if the currentNode is the goal vertex
            if (currentNode.equals(toFind)) {
                //Set goal vertex's values equal to current vertex's and set found to true, break
                currentNode.getPathFromRoot().add(currentNode);
                toFind.setPathFromRoot(currentNode.getPathFromRoot());
                toFind.setMinDistance(currentNode.getMinDistance());
                found = true;
                break;

            } else {
                //If the vertex hasn't been visited before in the while loop
                if (!currentNode.isVisited()) {
                    //List of the currentNode's neighbors
                    List<City> neighbors = this.getNeighbors(currentNode);

                    for (int i = 0; i < neighbors.size(); i++) {
                        City neighborNode = neighbors.get(i);

                        //If the path from currentNode to neighbor is less than the path neighbor currently holds
                        if ((currentNode.getMinDistance() + this.getEdge(currentNode, neighborNode).getWeight() < neighborNode.getMinDistance())) {
                            //Readd/add neighbor to minheap
                            addToMinHeap(neighborNode, currentNode, minheap);
                        }
                    }
                }
            }

            //Set currentNode to visited
            currentNode.setVisited(true);
        }

        //SE
        long end = System.currentTimeMillis();

        //If a path to from root to goal is found
        if (found) {
            System.out.println("Distance: " + toFind.getMinDistance() + " | Vertices: " + toFind.getPathFromRoot().size());
        } else {
            System.out.println("Distance: Unreachable");
        }

        System.out.println("Time: " + (end - start) + " milliseconds");
        System.out.println("Path: " + toFind.getPathFromRoot());

        return toFind.getPathFromRoot();
    }

    /**
     * Changes values of neighborNode based on path from currentNode and adds it to the minheap
     *
     * @param neighborNode - Vertex added to minheap (is neighbor to currentNode)
     * @param currentNode - Vertex that connects to neighbor node
     * @param minheap - minheap which neighborNode is be added to
     */
    private void addToMinHeap(City neighborNode, City currentNode, PriorityQueue<City> minheap) {
        minheap.remove(neighborNode);
        neighborNode.setPathFromRoot(new LinkedList<>(currentNode.getPathFromRoot()));
        neighborNode.getPathFromRoot().add(currentNode);
        neighborNode.setMinDistance(currentNode.getMinDistance() + this.getEdge(currentNode, neighborNode).getWeight());
        minheap.add(neighborNode);
    }

    /**
     * Rests all of the changed factors in a path finding method for each vertex
     */
    private void reset() {
        for (City item : this.getVertices()) {
            item.setVisited(false);
            item.setPathFromRoot(null);
            item.setMinDistance(Integer.MAX_VALUE);
        }
    }
    /** Finds the minimum distance and path from the graph's root to a given goal based on AStar's Method
     *
     * (Only going to comment things changed/different from Dijkstra)
     *
     * @param toFind - goal which root must path to
     * @return - List of vertices in order to reach goal from root
     */
    public LinkedList<City> aStar(City toFind) {
        reset();

        long start = System.currentTimeMillis();

        //Calculate the distance from each vertex to the goal
        for(City item : this.getVertices()){
            distanceFormula(item, toFind);
        }

        //compare based on distance from root + distance from goal
        PriorityQueue<City> minheap = new PriorityQueue<>(new Comparator<City>() {
            @Override
            public int compare(City o1, City o2) {
                return (int) ((o1.getMinDistance() + o1.getDistanceFromGoal()) - (o2.getMinDistance() + o2.getDistanceFromGoal()));
            }
        });

        this.getRoot().setMinDistance(0);
        this.getRoot().setPathFromRoot(new LinkedList<>());

        minheap.add(this.getRoot());
        boolean found = false;

        while (!minheap.isEmpty()) {
            City currentNode = minheap.remove();

            if (currentNode.equals(toFind)) {
                currentNode.getPathFromRoot().add(currentNode);
                toFind.setPathFromRoot(currentNode.getPathFromRoot());
                toFind.setMinDistance(currentNode.getMinDistance());
                found = true;
                break;

            } else {
                if (!currentNode.isVisited()) {
                    List<City> neighbors = this.getNeighbors(currentNode);

                    for (int i = 0; i < neighbors.size(); i++) {
                        City neighborNode = neighbors.get(i);

                        if ((currentNode.getMinDistance() + this.getEdge(currentNode, neighborNode).getWeight() < neighborNode.getMinDistance())) {
                            addToMinHeap(neighborNode, currentNode, minheap);
                        }
                    }
                }
            }

            currentNode.setVisited(true);
        }

        long end = System.currentTimeMillis();

        if (found) {
            System.out.println("Distance: " + toFind.getMinDistance() + " | Vertices: " + toFind.getPathFromRoot().size());
        } else {
            System.out.println("Distance: Unreachable");
        }

        System.out.println("Time: " + (end - start) + " milliseconds");
        System.out.println("Path: " + toFind.getPathFromRoot());

        return toFind.getPathFromRoot();
    }

    /** Sets DistanceFromGoal in a given vertex based off of the goal for the path finding method
     *
     * @param node - City which distanceFromGoal is being changed
     * @param toFind - Goal City
     */
    public void distanceFormula(City node, City toFind) {
        //Distance is calculated through the use of the distance formula
        node.setDistanceFromGoal(Math.sqrt(Math.pow((node.getxCord() - toFind.getxCord()), 2) + Math.pow((node.getyCord() - toFind.getyCord()), 2)));
    }
}