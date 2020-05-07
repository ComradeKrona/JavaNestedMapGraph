public class Edge<T extends Edge> implements EdgeADT, Comparable<T>{

    private double weight;

    Edge(){
        this(0.0);
    }

    Edge(double bound){
        weight = bound;
    }

    @Override
    public double getWeight() {
        return weight;
    }


    @Override
    public int compareTo(T o) {
        return (int) (weight - o.getWeight());
    }
}
