import javax.swing.*;
import java.awt.*;
import java.awt.Graphics;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.List;

/**
 * Created by yuqisu on 11/10/16.
 */
public class Graph {
    public Intersect intersect;
    public static int vertexCount;
    public static List<List<Intersect>> adjList;
    static boolean[] isVisited;
    static double[] shortdistance;
    static double MSTdistance;
    MyHeap<Edge> edges;
    // check the previous node
    static Intersect[] pre;
    // create a map with Intersection and id
    Map<String, Integer> position;
    static ArrayList<Intersect> drawShortestPath;
    static ArrayList<Edge> MST;
    Stack<Intersect> path;
    public int[] parent;
    static double minX = 100;
    static double minY = 100;
    static double maxX = 0;
    static double maxY = -100;

    Graph() {
        adjList = new ArrayList();
        position = new HashMap<>();
        path = new Stack<>();
        edges = new MyHeap<>();
        drawShortestPath = new ArrayList<>();
        MST = new ArrayList();


    }
//read the file freom txt and building the graph
    public void readFile(String fileName) {
        Scanner sc;
        try {
            sc = new Scanner(new File(fileName));
            while (sc.hasNextLine()) {
                String c = sc.nextLine();
                String[] read = c.split("\t");
                if (read[0].equals("i")) {
                    double x = Double.parseDouble(read[2]);
                    if (x < minX) {
                        minX = x;
                    }
                    if (x > maxX) {
                        maxX = x;
                    }
                    double y = Double.parseDouble(read[3]);
                    if (y < minY) {
                        minY = y;
                    }
                    if (y > maxY) {
                        maxY = y;
                    }
                    Point2D.Double location = new Point2D.Double(x, y);
                    intersect = new Intersect(read[1], location);
                    insertVertex(intersect);

                } else if (read[0].equals("r")) {
                    insertEdge(read[1], read[2], read[3]);

                }
            }
            System.out.println("finished");
        } catch (FileNotFoundException e) {
            System.out.print("no file found,CHECK");
        }

    }
/*
insert the vertex into the graph
 */
    public void insertVertex(Intersect intersect) {
//        intersect.parent = vertexCount;
        List<Intersect> a = new ArrayList();
        adjList.add(a);
        a.add(intersect);
        position.put(intersect.name, vertexCount);
        vertexCount++;

    }
/*
insert the edge into the map
 */
    public void insertEdge(String name, String start, String end) {
        int a = position.get(start);
        int b = position.get(end);
        Intersect newNode1 = new Intersect(adjList.get(b).get(0).name, new Point2D.Double(adjList.get(b).get(0).location.getX(), adjList.get(b).get(0).location.getY()));
        newNode1.setDistance(distanceCalculate(adjList.get(a).get(0), newNode1));
        Intersect newNode2 = new Intersect(adjList.get(a).get(0).name, new Point2D.Double(adjList.get(a).get(0).location.getX(), adjList.get(a).get(0).location.getY()));
        newNode2.setDistance(distanceCalculate(adjList.get(b).get(0), newNode2));
        adjList.get(a).add(newNode1);
        adjList.get(b).add(newNode2);
        Edge newEdge = new Edge(name, newNode1, newNode2, distanceCalculate(newNode1, newNode2));
        edges.insert(newEdge);


    }

//    public void printList() {
//        for (int i = 0; i < adjList.size(); i++) {
//            for (int j = 0; j < adjList.get(i).size(); j++) {
//                System.out.println(adjList.get(i).get(j).name);
//                System.out.println(adjList.get(i).get(j).distance);
//
//            }
//            System.out.println();
//        }
//
//    }

/*
calculate the distance between two intersection points
 */
private static double distanceCalculate(Intersect a,Intersect b) {
    double lat1 = a.location.getX();
    double lon1 = a.location.getY();
    double lat2 = b.location.getX();
    double lon2 = b.location.getY();
    double theta = lon1 - lon2;
    double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
    dist = Math.acos(dist);
    dist = rad2deg(dist);
    dist = dist * 60 * 1.1515;

    return (dist);
}
    private static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }
    private static double rad2deg(double rad) {
        return (rad * 180 / Math.PI);
    }





    public void applyDijkstra(String startVertex, String endVertex) {
        // initialize the shortdistance array
        shortdistance = new double[vertexCount];
        isVisited = new boolean[vertexCount];
        pre = new Intersect[vertexCount];
        for (int i = 0; i < vertexCount; i++) {
            shortdistance[i] = Double.MAX_VALUE;
        }
        // create a priority queue
//       Distance comparator = new Distance();
//        PriorityQueue<Intersect> priorityQueue = new PriorityQueue(vertexCount, comparator);
        MyHeap<Intersect> priorityQueue = new MyHeap(vertexCount);
        int start = position.get(startVertex);

        for (int j = 1; j < adjList.get(start).size(); j++) {
            priorityQueue.insert(adjList.get(start).get(j));
            pre[position.get(adjList.get(start).get(j).name)] = adjList.get(start).get(0);
        }
        isVisited[position.get(adjList.get(start).get(0).name)] = true;
        shortdistance[position.get(adjList.get(start).get(0).name)] = 0;


        while (!priorityQueue.isEmpty()) {

            Intersect node = priorityQueue.deleteMin();
            double dis = node.distance;
            int index = position.get(node.name);
            if (!isVisited[index]) {
                isVisited[index] = true;
                shortdistance[index] = dis;
            }
            for (int v = 0; v < adjList.get(index).size(); v++) {
                if (!isVisited[position.get(adjList.get(index).get(v).name)]) {
                    if (shortdistance[position.get(adjList.get(index).get(v).name)] > shortdistance[index]
                            + adjList.get(index).get(v).distance) {
                        adjList.get(index).get(v).distance = shortdistance[index]
                                + adjList.get(index).get(v).distance;
                        shortdistance[position.get(adjList.get(index).get(v).name)] = shortdistance[index];
                        priorityQueue.insert(adjList.get(index).get(v));
                        pre[position.get(adjList.get(index).get(v).name)] = node;
                    }

                }

            }
        }

        int end = position.get(endVertex);
        int loop = -1;
        path.push(adjList.get(end).get(0));
        if (pre[end] != null) {
            String check = pre[end].name;
            while (loop != 0) {
                path.push(adjList.get(position.get(check)).get(0));
                loop = position.get(check);
                if (loop == position.get(startVertex)) {
                    loop = 0;
                } else
                    check = pre[loop].name;
            }
            System.out.println("the shortest length is " + shortdistance[end]);
            System.out.println("the shortest path is: ");
            while (!path.isEmpty()) {
                Intersect tmp = path.pop();
                drawShortestPath.add(tmp);
                System.out.println(tmp.name);
            }
        } else {
            System.out.println("no such road exists");
        }
    }

    public void minimumSpanningTree() {
        parent = new int[vertexCount];
        for (int i=0;i<vertexCount;i++){
            parent[i]=-1;
        }
        while (!edges.isEmpty()) {
            Edge temp = edges.deleteMin();

            int starti = position.get(temp.start.name);
            int endi =position.get(temp.end.name);
            int startP = findParent(starti);
            int endP = findParent(endi);

            if(startP!=endP){
                if (startP==endi || endP==starti){
                    continue;
                }else {
                    MST.add(temp);
                    MSTdistance += temp.distance;
                    if (startP == -1) {
                        parent[starti] = endP;
                    } else if (endP == -1) {
                        parent[endi] = startP;
                    } else if (startP > endP) {
                        parent[startP] = endP;
                    } else if (endP > startP) {
                        parent[endP] = startP;
                    }
                }

            }else if (startP==-1){

                MST.add(temp);
                MSTdistance += temp.distance;
                if (starti>endi){
                    parent[starti]=endi;

                }else
                    parent[endi]=starti;
            }else continue;

        }

        System.out.print("\nthe minimum spanned tree distance is "+MSTdistance);
        System.out.println("the minimum spanned tree contains edges");



        for (Edge c : MST) {

            System.out.println(c.name);
        }
        System.out.println();

    }

    public int findParent (int i) {
        if (parent[i]!=-1){
            int c =i;
            while (parent[c]!=-1){
                c = parent[c];
                if (parent[c]==-1){
                    break;
                }
                if (c==parent[c]){
                    break;
                }
            }
            return c;
        }else
        return -1;
  }
    /*
    Graphics class that draw the map
     */
    static class MapDraw extends JPanel {
        String choice;

        MapDraw(String map,String choice){
            setBackground(Color.black);
            this.choice = choice;
        }
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (choice=="map"){
            drawmap(g);}
// test for drawshortest map
            if (choice=="shortestpath"){
                drawmap(g);
            drawShortest(g);}
//test for draw MST , temporally commented right now
            if (choice=="MST"){
                drawmap(g);
            drawMST(g);}

        }
        public void drawmap(Graphics g){
            Graphics2D g2 = (Graphics2D) g;
            g2.setColor(Color.white);
            for (int i = 0; i < adjList.size(); i++) {
                for (int j = 0; j < adjList.get(i).size(); j++) {
                    Shape l1 = new Line2D.Double(
                            scaleY(adjList.get(i).get(0).location.getY()),
                            getHeight() - scaleX(adjList.get(i).get(0).location.getX()),
                            scaleY(adjList.get(i).get(j).location.getY()),
                            getHeight() - scaleX(adjList.get(i).get(j).location.getX()));

                    g2.draw(l1);
                }
            }

        }
        public void drawShortest(Graphics g){
            Graphics2D g2 = (Graphics2D) g;
            g2.setColor(Color.green);
            for (int i =0;i<drawShortestPath.size()-1;i++){

                Point2D.Double start;
                Point2D.Double end;

                start = new Point2D.Double(scaleY(drawShortestPath.get(i).location.getY()),getHeight()-scaleX(drawShortestPath.get(i).location.getX()));
                end = new Point2D.Double(scaleY(drawShortestPath.get(i+1).location.getY()),getHeight()-scaleX(drawShortestPath.get(i+1).location.getX()));

                Shape shortestPath = new Line2D.Double(start,end);
                g2.draw(shortestPath);
            }
        }
        public void drawMST(Graphics g){
            Graphics2D g2 = (Graphics2D) g;
            g2.setColor(Color.orange);
            for (int i = 0; i < MST.size(); i++) {
                Point2D.Double start;
                Point2D.Double end;
                start = new Point2D.Double(scaleY(MST.get(i).start.location.getY()), getHeight() - scaleX(MST.get(i).start.location.getX()));
                end = new Point2D.Double(scaleY(MST.get(i).end.location.getY()), getHeight() - scaleX(MST.get(i).end.location.getX()));
                Shape MST = new Line2D.Double(start, end);
                g2.draw(MST);
            }
        }

        public double scaleX(double number) {

            double sca = ((double) getHeight() * (number - minX)) / (maxX - minX);

            return sca;
        }

        public double scaleY(double number) {

            double sca = ((double) getWidth() * (number - minY)) / (maxY - minY);
            return sca;
        }


    }


}
