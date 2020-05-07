import java.util.*;

public class Graph<V extends Comparable<V>, E extends Comparable<E>> implements GraphADT<V, E>{

    //Actual holds all of the vertices and edges
    HashMap<V, TreeMap<V, E>> graph = new HashMap<>();

    //Graph's root vertex
    private V root;

    //SE
    private int numVertices = 0;
    private int numEdges = 0;

    //Whether the graph is directed or not
    private boolean isNotDirected;

    /** Constructs a graph
     *
     * @param directed - boolean, whether graph is directed or not
     */
    Graph(boolean directed){
        this.isNotDirected = directed;
    }

    /**Constructs a graph through super constructor (directed is false)
     *
     */
    Graph(){
        this(true);
    }

    /** Produces a list of all of the graph's edges
     *
     * @return - List of all the graph's edges
     */
    public List<E> getEdges() {
        LinkedList<E> returnList = new LinkedList<>();

        for(Map.Entry<V, TreeMap<V, E>> GraphMap : graph.entrySet()) {
            returnList.addAll(getEdges(GraphMap.getKey()));
        }

        return returnList;
    }

    /** Produces a list of all of the graph's vertices
     *
     * @return - List of all the grpah's vertices
     */
    public List<V> getVertices() {
        LinkedList<V> value = new LinkedList<>();

        for (V items : graph.keySet()){
            value.add(items);
        }

        return value;
    }

    /** SE
     *
     * @param given - given vertex
     * @return - whether graph contains that vertex or not
     */
    public boolean contains(V given) {
        if (graph.containsKey(given)){
            return true;
        } else {
            return false;
        }
    }

    /** Produces a list of all of the vertex's edges (not their neighbors)
     *
     * @param given - given vertex
     * @return - List of the all that vertex's edges
     */
    public List<E> getEdges(V given) {
        if (graph.containsKey(given)) {
            LinkedList<E> returnList = new LinkedList<>();

            try {
                for (Map.Entry<V, E> edges : graph.get(given).entrySet()) {
                    returnList.add(edges.getValue());
                }
            } catch (IndexOutOfBoundsException e) {
                return null;
            }
            return returnList;
        } else{
            return null;
        }
    }

    /** Produces a list of all of the vertex's neighbors (not that weight of that edge)
     *
     * @param given - given vertex
     * @return - List of hte all that vertxe's neighbors
     */
    public List<V> getNeighbors(V given) {
        if (graph.containsKey(given)){
            TreeMap<V, E> targetVertex = graph.get(given);

            List<V> answer = new LinkedList<>();

            for (V items : targetVertex.keySet()){
                answer.add(items);
            }

            return answer;
        } else {
            return null;
        }
    }

    /** Produces the weight the graph holds from and to a given vertex
     *
     * @param from the originating vertex
     * @param to the ending vertex
     * @return - weight of that edge
     */
    public E getEdge(V from, V to) {
        if (graph.containsKey(from) && graph.containsKey(to)){
            return graph.get(from).get(to);
        } else {
            return null;
        }
    }

    /** SE
     *
     * @return - Whatever the graph is empty or not
     */
    public boolean isEmpty() {
        return graph.isEmpty();
    }

    /** SE
     *
     * @return - Number of Vertices
     */
    public int getNumVertices() {
        return numVertices;
    }

    /** SE
     *
     * @return - Number of Edges
     */
    public int getNumEdges() {
        return numEdges;
    }

    /** SE
     *
     * @return - Graph's held root
     */
    public V getRoot() {
        return root;
    }

    /** Sets the graph's root to a given vertex if the graph contains that vertex
     *
     * @param given - given vertex
     */
    public void setRoot(V given) {
       if (graph.containsKey(given)){
           root = given;
        }
    }

    /** SE
     *
     * @param given1 - given vertex1
     * @param given2 - given vertex2
     * @return - Whatever there is a edge between two give vertices
     */
    public boolean isConnected(V given1, V given2) {
        if (graph.containsKey(given1) && graph.containsKey(given2)){
            if (graph.get(given1).containsKey(given2) || graph.get(given2).containsKey(given1)){
                return true;
            }
        }

        return false;
    }

    /** Adds a vertex to the graph if the graph doesn't already contain it
     *
     * @param given - given vertex
     * @return - Boolean, if the vertex was added to the graph or not
     */
    public boolean addVertex(V given) {
        if (!graph.containsKey(given)){
            TreeMap<V, E> treeMap = new TreeMap<>();
            graph.put(given, treeMap);

            numVertices++;

            return true;
        } else {
            return false;
        }
    }

    /** Adds an edge to the graph between two given vertices
     *
     * Adds the two vertices to the graph if the graph doesn't already contain them
     *
     * @param from - vertex edge is from
     * @param to - vertex edge is to
     * @param weight - weight of the edge between the two vertices
     * @return - Boolean, if the edge was dded to teh graph or not
     */
    public boolean addEdge(V from, V to, E weight) {
        if (!contains(from)){
            addVertex(from);
        }

        if (!contains(to)){
            addVertex(to);
        }

            graph.get(from).put(to, weight);

            numEdges++;

            //If the Graph is directed
            if (!isNotDirected){
                graph.get(to).put(from, weight);
                numEdges++;
            }

            return true;
    }

    /** Removes vertex from graph and all references/edges to that vertex
     *
     * @param given - given vertex
     * @return - boolean, whatever the vertex was removed or not
     */
    public boolean removeVertex(V given) {
        if (graph.containsKey(given)){
            graph.remove(given);

            for(Map.Entry<V, TreeMap<V, E>> edgeMap : graph.entrySet()){
                edgeMap.getValue().remove(given);
            }

            numVertices--;

            return true;
        }

        return false;
    }

    /** Removes edge connecting two vertices
     *
     * @param from - vertex edge is from
     * @param to - vertex edge is to
     * @return - boolean, whatever the edge was removed or not
     */
    public boolean removeEdge(V from, V to) {
        if (graph.containsKey(from) && graph.containsKey(to)){
            graph.get(from).remove(to);

            return true;
        }

        return false;
    }

    /** SE
     *
     * @return - an iterator to go over all of the vertices in the graph
     */
    public Iterator iterator() {
        return graph.keySet().iterator();
    }

    /** SE
     *
     * @return - string representative of the graph
     */
    public String toString(){
        String temp = "Graph (Root: " + root + "): \n";

        for(Map.Entry<V, TreeMap<V, E>> GraphMap : graph.entrySet()){
            temp += GraphMap.getKey() + " --> ";

            for(Map.Entry<V, E> edgeMap : GraphMap.getValue().entrySet()){
                temp += edgeMap.getKey() + " (" + edgeMap.getValue() + "), ";
            }

            if (!GraphMap.getValue().entrySet().isEmpty()) {
                temp = temp.substring(0, temp.length() - 2);
            }

            temp += "\n";
        }

        return temp.substring(0, temp.length()-2);
    }
}