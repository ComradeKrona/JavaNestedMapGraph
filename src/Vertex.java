import java.util.LinkedList;

public class Vertex<T extends Vertex> implements VertexADT<T> {


    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    private boolean visited;
    private double distanceFromRoot;
    private LinkedList<T> pathFromRoot = new LinkedList<>();

    public double getxCord() {
        return xCord;
    }

    public void setxCord(double xCord) {
        this.xCord = xCord;
    }

    public double getyCord() {
        return yCord;
    }

    public void setyCord(double yCord) {
        this.yCord = yCord;
    }

    private double xCord;
    private double yCord;

    public double getDistanceFromGoal() {
        return distanceFromGoal;
    }

    public void setDistanceFromGoal(double distanceFromGoal) {
        this.distanceFromGoal = distanceFromGoal;
    }

    private double distanceFromGoal;

    public LinkedList<T> getPathFromRoot() {
        return pathFromRoot;
    }

    public void setPathFromRoot(LinkedList<T> pathFromRoot) {
        this.pathFromRoot = pathFromRoot;
    }

    Vertex(){
        distanceFromRoot = Integer.MAX_VALUE;
        distanceFromGoal = Integer.MIN_VALUE;
    }

    @Override
    public LinkedList<T> pathFromRoot() {
        return pathFromRoot;
    }

    @Override
    public double getMinDistance() {
        return distanceFromRoot;
    }

    @Override
    public void setMinDistance(double distance) {
        distanceFromRoot = distance;
    }

    Vertex(String value) {
        this(value, 0, 0);
    }

    Vertex(String value, double xCord, double yCord){
        hold = value;
        distanceFromRoot = Integer.MAX_VALUE;
        distanceFromGoal = 0.0;
        this.xCord = xCord;
        this.yCord = yCord;
        visited = false;
    }

    public String getHold() {
        return hold;
    }

    private String hold;

    public String toString(){
        return hold + " (x: " + this.getxCord() +", y: " + this.getyCord() + ") " + "(dRoot: " + this.distanceFromRoot + ", dGoal: "  + this.getDistanceFromGoal() +")";
    }

    @Override
    public boolean equals(Object obj) {
        return this.hold.equals(((Vertex) obj).hold);
    }

    @Override
    public int hashCode() {
        return hold.hashCode();
    }

    public int compareTo(Vertex o) {
        return hold.compareTo(o.hold);
    }
}
