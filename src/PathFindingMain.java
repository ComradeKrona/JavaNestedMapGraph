import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class PathFindingMain {

    private static final int maxReadInCount = Integer.MAX_VALUE;
    private static  final int printableGraphCount = 30;

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Max City Count: " + maxReadInCount);
        System.out.println("Printable Graph Count: " + printableGraphCount + " Nodes");
        System.out.println("In order for my program to read in a vertex coordinate file, the file MUST have the string \"cord\", \"coord\", \"coordinate\" or \"location\" in it");
        System.out.println();

        File directory = new File("GraphFiles");
        String[] subFiles = directory.list();

        Scanner reader = new Scanner(System.in);

        boolean file = false;

        while(!file) {
            System.out.println("Pick a File Number: ");

            assert subFiles != null;
            for(int i = 0; i < subFiles.length; i++){
                System.out.println(i + " - " + subFiles[i]);
            }
            try {
                int input = reader.nextInt();

                if (input > -1 && input < subFiles.length){
                    file = true;
                    readFromFile(subFiles[input]);
                } else {
                    System.out.println("Number does not match to a file, please try again");
                }
            } catch (InputMismatchException e){
                System.out.println("Input must be an integer, please try again");
            }

            System.out.println();
        }
    }

    public static void readFromFile(String fileName) throws FileNotFoundException {
        System.out.println();

        File passedFile = new File("GraphFiles/" + fileName);
        String name = fileName;

        PathFindingGraph myGraph = new PathFindingGraph();
        HashMap<String, City> cityHashMap = new HashMap<>();

        if (passedFile.isDirectory()){
            String[] fileList = passedFile.list();

            assert fileList != null;
            if (fileList.length != 2){
                throw new Error("Unknown Files");
            } else {
                System.out.println("Reading " + fileName + " " + Arrays.toString(fileList));

                if (fileList[0].toLowerCase().contains("cord") || fileList[0].toLowerCase().contains("location") || fileList[0].toLowerCase().contains("coordinate") || fileList[0].toLowerCase().contains("coord")){
                    readCordFile(myGraph, cityHashMap, new File("GraphFiles/" + fileName + "/" + fileList[0].trim()), fileList[0].trim());
                    passedFile = new File("GraphFiles/" + fileName + "/" + fileList[1]);
                    name = fileList[1];
                } else {
                    readCordFile(myGraph, cityHashMap, new File("GraphFiles/" + fileName + "/" + fileList[1].trim()), fileList[1].trim());
                    passedFile = new File("GraphFiles/" + fileName + "/" + fileList[0]);
                    name = fileList[0];
                }
            }
        }

        Scanner fileReader = new Scanner(passedFile);

        int index = 0;

        long start = System.currentTimeMillis();

        while(fileReader.hasNext()
                    && index < maxReadInCount
        ){
            String[] inputs = fileReader.nextLine().split(",");

            if (cityHashMap.get(inputs[0].trim()) == null){
                cityHashMap.put(inputs[0].trim(), new City(inputs[0].trim()));
            }

            City to = cityHashMap.get(inputs[0].trim());

            if (cityHashMap.get(inputs[1].trim()) == null){
                cityHashMap.put(inputs[1].trim(), new City(inputs[1].trim()));
            }

            City from = cityHashMap.get(inputs[1].trim());

            myGraph.addEdge(to, from, new Road(Double.valueOf(inputs[2].trim())));
            index++;
        }

        fileReader.close();

        long end = System.currentTimeMillis();

        System.out.println("Finished reading " + name + " (" + (end - start) + " milliseconds)");
        System.out.println("Vertices: " + myGraph.getNumVertices() + " | Edges: " + myGraph.getNumEdges());

        if (myGraph.getNumVertices() <= printableGraphCount){
            System.out.println();
            System.out.println(myGraph);
        }

        pickSearchingMethod(myGraph, cityHashMap);
    }

    private static void readCordFile(PathFindingGraph myGraph, HashMap<String,City> cityHashMap, File file, String fileName) throws FileNotFoundException {
        Scanner reader = new Scanner(file);

        long start = System.currentTimeMillis();

        while (reader.hasNext()){
            String[] inputs = reader.nextLine().split(",");

            for(int i = 0; i < inputs.length; i++){
                inputs[i] = inputs[i].trim();
            }

            if (inputs[0].contains("\uFEFF")){
                inputs[0] = inputs[0].substring(0, inputs[0].indexOf("\uFEFF")) + inputs[0].substring(inputs[0].indexOf("\uFEFF") + 1);
            }

            City node;

            if (cityHashMap.get(inputs[0]) == null){
                node = new City(inputs[0]);
            } else {
                node = cityHashMap.get(inputs[0]);
            }

            node.setxCord(Double.parseDouble(inputs[1]));
            node.setyCord(Double.parseDouble(inputs[2]));

            cityHashMap.put(inputs[0], node);
            myGraph.addVertex(node);
        }

        long end = System.currentTimeMillis();

        System.out.println("Finished reading " + fileName + " (" + (end - start) + " milliseconds)");
        System.out.println("Vertices: " + myGraph.getNumVertices() + " | Edges: " + myGraph.getNumEdges());
    }

    private static void pickSearchingMethod(PathFindingGraph myGraph, HashMap<String, City> pass) {
        Scanner reader = new Scanner(System.in);

        boolean picked = false;

        while(!picked){
            System.out.println();
            System.out.println("Pick a Search Method Number: ");
            System.out.println("0 - Dijkstra");
            System.out.println("1 - A*");

            String input = reader.nextLine();

            try {
                if (Integer.parseInt(input) > -1 && Integer.parseInt(input) < 2){
                    picked = true;
                    pickRootAndGoal(myGraph, Integer.parseInt(input), pass);
                } else {
                    System.out.println("Number does not match to a search method, please try again");
                }
            } catch (NumberFormatException e){
                System.out.println("Input must be an integer, please try again");
            }

        }
    }

    private static void pickRootAndGoal(PathFindingGraph myGraph, int searchMethod, HashMap<String, City> pass) {
        Scanner reader = new Scanner(System.in);

        boolean definedRoot = false;

        while(!definedRoot){
            System.out.println();
            System.out.println("Choose a root in the graph: ");

            String input = reader.nextLine().toLowerCase().trim();

            if (pass.get(input) == null){
                System.out.println("Graph does not contain vertex " + input + ", please try again");
            } else {
                System.out.println("Set root to " + pass.get(input));
                try {
                    myGraph.setRoot(pass.get(input));
                    definedRoot = true;
                } catch (NullPointerException e){
                    System.out.println("Null Object");
                }


            }
        }

        boolean definedGoal = false;
        City goal = null;

        while(!definedGoal){
            System.out.println();
            System.out.println("Choose a goal in the graph: ");

            String input = reader.nextLine().trim();

            City node = pass.get(input);

            if (node == null){
                System.out.println("Graph does not contain  vertex " + input + ", please try again");
            } else {
                System.out.println("Set goal to " + node);
                goal = node;
                definedGoal = true;
            }
        }

        System.out.println();

        switch (searchMethod){
            case 0:
                myGraph.dijkstra(goal);
                break;
            case 1:
                myGraph.aStar(goal);
                break;
            default:
                System.exit(69);
        }

        again(myGraph, pass);
    }

    private static void again(PathFindingGraph myGraph, HashMap<String, City> pass) {

        Scanner reader = new Scanner(System.in);

        boolean validAnswer = false;

        while (!validAnswer){
            System.out.println();
            System.out.println("Search Again?");
            System.out.println("(yes/y or no/n)");

            String input = reader.nextLine().toLowerCase();

            if (input.equals("yes") || input.equals("y")){
                validAnswer = true;
                pickSearchingMethod(myGraph, pass);
            } else if (input.equals("no") || input.equals("n")){
                validAnswer = true;
                System.exit(0);
            } else {
                System.out.println("Incorrect input, please try again");
            }
        }
    }
}