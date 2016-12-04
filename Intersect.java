import java.awt.geom.Point2D;

/**
 * Created by yuqisu on 11/12/16.
 */
public class Intersect implements Comparable<Intersect> {
    public String name;
    public double distance;
    public Point2D.Double location;
    public int rank;
    public int parent;
    public Intersect(String name, Point2D.Double location){
        this.name = name;
        this.location = location;

    }


    public void setDistance(double distance){
        this.distance = distance;
    }


    @Override
    public int compareTo(Intersect o) {
        if (distance < o.distance) {
            return -1;
        } else if (distance > o.distance) {
            return 1;
        } else return (name.compareTo(o.name));
    }
    }

