

/**
 * Created by yuqisu on 11/10/16.
 */
public class Edge implements Comparable<Edge> {
    public final String name;
    public final Intersect start, end;
    public double distance;
    public Edge(String name, Intersect start, Intersect end,double distance){
        this.name = name;
        this.start = start;
        this.end = end;
        this.distance = distance;
    }

    public void printEdge(){
        System.out.println("the edge if from "+start
                +" to "+end);
    }

    @Override
    public int compareTo(Edge o) {
        if (distance < o.distance) {
            return -1;
        } else if (distance > o.distance) {
            return 1;
        } else return (name.compareTo(o.name));
    }

}

