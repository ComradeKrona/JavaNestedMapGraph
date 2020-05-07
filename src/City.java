public class City extends Vertex<City> implements Comparable<City>{

    City(String name){
      super(name);
    }

    City(String name, double x, double y){
        super(name, x, y);
    }

    @Override
    public int compareTo(City o) {
       return super.compareTo(o);
    }
}
