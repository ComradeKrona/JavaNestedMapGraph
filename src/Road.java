public class Road extends Edge<Road> implements Comparable<Road>{

    public String getName() {
        return name;
    }

    private String name;

    public String toString(){
        return "" + name;
    }

    Road(String weight, double value){
        super(value);
        name = weight;
    }

    Road(Double weight){
        super(weight);
        name = "" + weight;
    }
}
