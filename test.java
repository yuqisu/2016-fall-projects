import javax.swing.*;
import java.awt.*;

/**
 * Created by yuqisu on 11/12/16.
 */
public class test  {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        Graph test = new Graph();
        String map =args[0];
        String function = args[1];
        if (map.equals("ur.txt")){
            test.readFile("ur.txt");
        }else if (map.equals("monroe.txt")){
            test.readFile("monroe.txt");
        }else if (map.equals("nys.txt")){
            test.readFile("nys.txt");
        }
        if (function.equals("[-show]")){
            JFrame frame = new JFrame("map");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1100,700);
            Graph.MapDraw p = new Graph.MapDraw(map,"map");
            frame.add(p);
            frame.setVisible(true);
        }else if (function.equals("[-meridianmap]")){
            test.minimumSpanningTree();
            JFrame frame = new JFrame("map");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1100,700);
            Graph.MapDraw p = new Graph.MapDraw(map,"MST");
            frame.add(p);
            frame.setVisible(true);
        }else{
            JFrame frame = new JFrame("map");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1100,700);
            Graph.MapDraw p = new Graph.MapDraw(map,"shortestpath");
            frame.add(p);
            frame.setVisible(true);
            test.applyDijkstra(args[2],args[3].substring(0,args[3].length()-1));
        }


        long endTime   = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("\nrun time is "+totalTime);

    }
}
