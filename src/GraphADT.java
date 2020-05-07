import java.util.List;

/**
 *
 * This interface is meant to model the Graph abstract data type.  A simpleGraph consists of Vertices of type <V>
 *     and Edges of type <E>.  In order to be able to retrieve vertices and edges in the simpleGraph, they must both
 *     implement the Comparable interface.  Because <V> and <E> are both generic, they extend Comparable.
 *
 * The collection of vertices is a set.
 *
 * @param <V> a collection of vertices
 * @param <E> a collection of edges
 */
public interface GraphADT<V extends Comparable<V>, E extends Comparable<E>>  extends Iterable<V> {

    /**
     * This method will return an unordered List<E> of all the edges that are contained in the simpleGraph
     *
     * @return List<E> the list of all edges
     */
    public List<E> getEdges();


    /**
     * This method will return an unordered List<E> of all the vertices that are contained in the simpleGraph
     *
     * @return List<E> the list of all vertices
     */
    public List<V> getVertices();


    /**
     * This method returns a List<E> of all the edges that are connected to vertex v
     *
     * @param v the node that we want the connected edges to
     * @return a List<E> of the edges that are connected to vertex v
     */
    public List<E> getEdges(V v);


    /**
     * This method returns a List<E> of all the vertices that are connected to vertex v
     *
     * @param v the node that we want the connected vertices to
     * @return a List<E> of the vertices that are connected to vertex v
     */
    public List<V> getNeighbors(V v);


    /**
     * This method returns a single edge E that is connected to between vertex <code>from</code> and vertex <code>to</code>
     *
     * @param from the originating vertex
     * @param to the ending vertex
     * @return the edge that connects the two vertices <code>from</code> and <code>to</code>; returns null if the edge does not exist
     */
    public E getEdge(V from, V to);


    /**
     * Returns a text based version of the simpleGraph in the following form
     *
     * A -> B, C
     * B -> A, D
     * C -> A
     * D -> B
     *
     * @return the text representation of the simpleGraph
     */
    public String toString();

    /**
     * Returns true if there are no vertices in the simpleGraph
     *
     *  @return true if the simpleGraph is empty, false otherwise
     */
    public boolean isEmpty();

    public int getNumVertices();

    public int getNumEdges();

    public V getRoot();

    public void setRoot(V v);




    /**
     *
     * Although
     */
   // public Graph createGraph(V[] nodes, E[] edges, V startHere);



    /**
     * @return true if an edge exists between v1 and v2
     */
    public boolean isConnected(V v1, V v2);

    /**
     * Attempts to insert vertex in simpleGraph whose vertices have distinct values that differ from new vertex’s value
     *
     * @return true if vertex v was successfully added to the simpleGraph.
     */
    public boolean addVertex(V v);

    /**
     * Attempts to insert edge between two given vertices in a simpleGraph.
     *
     * @return true if the edge between v1 and v2 was successfully added to the simpleGraph.
     */
    public boolean addEdge(V from, V to, E e);

    /**
     * Attempts to remove specified vertex from simpleGraph and any edges between the vertex and other vertices .
     *
     * @return true if the vertex v and its attendant edges were removed.
     */

    public boolean removeVertex(V v);

    /**
     * Attempts to remove edge between two vertices v1 and v2 in the simpleGraph.
     *
     * @return true if the edge between v1 and v2 was successfully removed from the simpleGraph.
     */
    public boolean removeEdge(V from, V to);


}
