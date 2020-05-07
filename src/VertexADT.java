import java.util.LinkedList;

public interface VertexADT<V>{

    public LinkedList<V> pathFromRoot();

    public double getMinDistance();

    public void setMinDistance(double distance);

}
