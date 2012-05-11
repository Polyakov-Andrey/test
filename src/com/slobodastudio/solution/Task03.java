package com.slobodastudio.solution;

import com.slobodastudio.loger.LoggerHelper;
import com.slobodastudio.tasks.ITask;
import com.slobodastudio.tasks.TaskAbstract;
import com.slobodastudio.solution.task03.Edge;
import com.slobodastudio.solution.task03.FloydWarshall;
import com.slobodastudio.solution.task03.Node;
import com.slobodastudio.tasks.InvalidFileFormatExeption;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * You are given a list of cities. Each direct connection between two cities has
 * its transportation cost (an integer bigger than 0). The goal is to find the
 * paths of minimum cost between pairs of cities. Assume that the cost of each
 * path (which is the sum of costs of all direct connections belongning to this
 * path) is at most 200000. The name of a city is a string containing characters
 * a,...,z and is at most 10 characters long.
 * <pre>
 *
 * Input
 *
 * s [the number of tests <= 10]
 * n [the number of cities <= 10000]
 * NAME [city name]
 * p [the number of neighbours of city NAME]
 * nr cost [nr - index of a city connected to NAME (the index of the first city is 1)]
 *         [cost - the transportation cost]
 * r [the number of paths to find <= 100]
 * NAME1 NAME2 [NAME1 - source, NAME2 - destination] [empty line separating the tests]
 *
 * Output
 *
 * cost [the minimum transportation cost from city NAME1 to city NAME2 (one per line)]
 * </pre>
 *
 * @author Andrey Polyakov
 * @version 1.0
 */
public class Task03 extends TaskAbstract implements ITask {

    class Data {

        // the list cities
        private List<Node> cities = new ArrayList<Node>();
        // the list of neighbours of cities
        private List<Edge> edges = new ArrayList<Edge>();
//         the number of rute to find
        private HashMap<Integer, Edge> rutes = new HashMap<Integer, Edge>();
//         the list of rute
        private ArrayList<List<Node>> listPath = new ArrayList<List<Node>>();
    }
//      
    List<Data> graphs = new ArrayList<Data>();

    public Task03(String nameFileIn, String nameFileOut) {
        assert (nameFileIn != null) : "The name of input file is empty.";
        assert (nameFileOut != null) : "The name of out file is empty.";

        File fileIn, fileOut = null;

        try {
            fileIn = new File(nameFileIn);
            if (fileIn.exists()) {
                this.setFileNameIn(fileIn);
            }

            fileOut = new File(nameFileOut);
            if (fileOut.exists()) {
                if (!fileOut.delete()) {
                    throw new IOException("The file " + fileOut.getName() + " is not delate.");
                } else {
                    if (!fileOut.createNewFile()) {
                        throw new IOException("The file " + fileOut.getName() + " is not created.");
                    }
                    this.setFileNameOut(fileOut);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Task03.class.getName()).log(Level.SEVERE, "The file " + nameFileOut + "wasn't created.", ex);
        }

        assert (fileOut.exists()) : "The file " + nameFileOut + "wasn't created.";
    }

    @Override
    public void loadFromFile() {

        class LoadFile {

            int line;                       // line in file
            int s = 0;                      // the number of tests
            Scanner inRecord;               // pointer on record of test
            final Data data = new Data();

            public LoadFile(Scanner in, int line) {
                this.inRecord = in;
                this.line = line;
            }

            public Data read() throws InvalidFileFormatExeption {
                readCity();
                readRutes();
                return data;
            }

            private void readCity() throws InvalidFileFormatExeption {
                int numberOfCity = 0; // the number of cities
                if (inRecord.hasNextInt()) {
                    numberOfCity = inRecord.nextInt();
                    line++;
                } else {
                    throw new InvalidFileFormatExeption("The format of file is wrong, in line " + line + " is expected to integer");
                }
                // initialization of the cities inRecord objects of Node, the first city corresponds to object of Node (0)
                for (int city = 0; city < numberOfCity; city++) {
                    data.cities.add(new Node(city));
                }

                for (int city = 0; city < numberOfCity; city++) {
                    if (inRecord.hasNextLine()) {
                        data.cities.get(city).setName(inRecord.next());
                        line++;
                    } else {
                        throw new InvalidFileFormatExeption("The format of file is wrong, in line " + line + " is expected to line");
                    }
                    readEdges(city);
                }
            }

            private void readEdges(int fromCity) throws InvalidFileFormatExeption {
                int p = 0;                          // the number of neighbours of city
                if (inRecord.hasNextInt()) {
                    p = inRecord.nextInt();
                    line++;
                }

                for (int i = 0; i < p; i++) {
                    int toCity = 0;
                    if (inRecord.hasNextInt()) {
                        toCity = inRecord.nextInt();
                    } else {
                        throw new InvalidFileFormatExeption("The format of file is wrong, in line " + line + " is expected to integer");
                    }
                    int cost = 0;
                    if (inRecord.hasNextInt()) {
                        cost = inRecord.nextInt();
                        line++;
                    } else {
                        throw new InvalidFileFormatExeption("The format of file is wrong, in line " + line + " is expected to integer");
                    }

                    Edge edge = new Edge(data.cities.get(fromCity), data.cities.
                            get(toCity - 1), cost);
                    data.edges.add(edge);
                }
            }

            private void readRutes() throws InvalidFileFormatExeption {
                int r = 0; //  // the number of rute to find 
                if (inRecord.hasNextInt()) {
                    r = inRecord.nextInt();
                    line++;
                }
                for (int i = 0; i < r; i++) {

                    String strFrom = null;
                    if (inRecord.hasNext()) {
                        strFrom = inRecord.next();
                    } else {
                        throw new InvalidFileFormatExeption("The format of file is wrong, in line " + line + " is expected to city");
                    }

                    String strTo = null;
                    if (inRecord.hasNext()) {
                        strTo = inRecord.next();
                        line++;
                    } else {
                        throw new InvalidFileFormatExeption("The format of file is wrong, in line " + line + " is expected to city");
                    }

                    Node cityFrom = null;
                    Node cityTo = null;

                    for (int j = 0; j < data.cities.size(); j++) {
                        if ((cityFrom == null) && (data.cities.get(j).getName().
                                equals(strFrom))) {
                            cityFrom = data.cities.get(j);
                        }
                        if ((cityTo == null) && (data.cities.get(j).getName().
                                equals(strTo))) {
                            cityTo = data.cities.get(j);
                        }
                    }
                    Edge edge = new Edge(cityFrom, cityTo, -1);
                    data.rutes.put(i, edge);
                }
            }
        } // end of class LoadFile
        Scanner in = null;
        int s = 0;
        try {
            in = new Scanner(getFileNameIn());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Task03.class.getName()).log(Level.SEVERE, null, ex);
        }
        int line = 0;

        if (in.hasNextInt()) {
            s = in.nextInt();
            line++;
        }
        for (int i = 0; i < s; i++) {
            LoadFile record = new LoadFile(in, line);
            try {
                graphs.add(record.read());
            } catch (InvalidFileFormatExeption ex) {
                Logger.getLogger(Task03.class.getName()).log(Level.SEVERE, null, ex);
            }

            in = record.inRecord;
            line = record.line;

            if (in.hasNextLine()) {
                in.nextLine();
                line++;
            }
        }
        in.close();
    }

    @Override
    public void solution() {

        for (Data data : graphs) {  // for each graph to calculate the rute

            FloydWarshall floydeWarshall = new FloydWarshall(data.cities, data.edges);

            for (int i = 0; i < data.rutes.size(); i++) {
                floydeWarshall.getShortestDistance(data.rutes.get(i));
                data.listPath.add(floydeWarshall.getShortestPath(data.rutes.get(i)));
            }
        }
    }

    @Override
    public void writeToFile() {
        PrintWriter out = null;
        try {
            out = new PrintWriter(getFileNameOut());
            for (Data data : graphs) {
                for (int i = 0; i < data.rutes.size(); i++) {
                    out.println(data.rutes.get(i).getWeight());
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Task03.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            out.close();
        }
        for (Data data : graphs) {
            System.out.println("----------------");
            System.out.println(data.cities);
            System.out.println(data.edges);
            System.out.println(data.rutes);
            System.out.println(data.listPath);
        }
    }

    public static void main(String[] args) throws FileNotFoundException {

       // LoggerHelper.createFileLoger(Task03.class.getName(), "task03.log");

        Task03 task03 = new Task03("in0301.dat", "out.dat");
        task03.loadFromFile();
        task03.solution();
        task03.writeToFile();
    }
}
