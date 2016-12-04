import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by yuqisu on 10/27/16.
 */
public class PointTest {
    static final int COUNTERCLOCKWISE = -1;
    static final int CLOCKWISE = 1;
    static final int COLINEAR = 0;
    static String x = "";
    static Tree bst = new Tree();
    static MyTreeNode[] arrays;

    public static void main(String[] args) {

        Scanner sc = null;
        try {
            sc = new Scanner(new File("input.txt"));
        } catch (FileNotFoundException e) {
            System.out.print("no file found,CHECK");
        }
//
        int number = Integer.valueOf(sc.nextLine());
        arrays = new MyTreeNode[number];
        for (int i = 0; i < number; i++) {

            x = sc.nextLine();

            String[] coord = x.split(" ");

            Point.Double Start = new Point.Double(Double.parseDouble(coord[0]), Double.parseDouble(coord[1]));
            Point.Double End = new Point.Double(Double.parseDouble(coord[2]), Double.parseDouble(coord[3]));
            bst.insertLine(Start, End);
            arrays[i] = new MyTreeNode(Start, End, i);


        }

//            bst.printPreOrder();
        System.out.println("the number of external nodes is " + bst.countOut());
        System.out.println("the average length path is  " + bst.aveLength());
        while (sc.hasNextLine()) {
            String newPoint = sc.nextLine();
            if (newPoint.trim().length() != 0) {
                String[] coord1 = newPoint.split(" ");
                Point.Double Start = new Point.Double(Double.parseDouble(coord1[0]), Double.parseDouble(coord1[1]));
                Point.Double End = new Point.Double(Double.parseDouble(coord1[2]), Double.parseDouble(coord1[3]));

                if (bst.checkRegion(Start, End) != -1) {
                    for (int j = 0; j < arrays.length; j++) {
                        bst.printInseLine(Start, End, arrays[j]);
                    }
                } else
                    System.out.println(bst.checkRegion(Start, End));
            }
        }
    }


    public static int ccw(Point.Double p0, Point.Double p1, Point.Double p2) {
        double dx1 = p1.x - p0.x;
        double dy1 = p1.y - p0.y;
        double dx2 = p2.x - p0.x;
        double dy2 = p2.y - p0.y;
        if (dx1 * dy2 > dy1 * dx2) return COUNTERCLOCKWISE;
        else if (dx1 * dy2 < dy1 * dx2) return CLOCKWISE;
        else if ((dx1 * dx2 < 0) || (dy1 * dy2 < 0)) return CLOCKWISE;
        else if ((dx1 * dx1 + dy1 * dy1) < (dx2 * dx2 + dy2 * dy2)) return COUNTERCLOCKWISE;
        else return COLINEAR;
    }

}




