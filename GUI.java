
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import java.awt.*;

import java.awt.geom.Point2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * Created by yuqisu on 11/2/16.
 */
public class GUI extends Application {
    static int number;
    static double[] coordinates;
    static int click = 0;
    static Tree bst;
    double newSx;
    double newSy;
    double newEx;
    double newEy;
    double x1;
    double y1;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Point Locator designed by Yuqi");
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("X-Axis");
        final LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);

        XYChart.Series line0 = new XYChart.Series();
        line0.setName("line0");
        line0.getData().add(new XYChart.Data(coordinates[0], coordinates[1]));
        line0.getData().add(new XYChart.Data(coordinates[2], coordinates[3]));

        XYChart.Series line1 = new XYChart.Series();
        line1.setName("line1");
        line1.getData().add(new XYChart.Data(coordinates[4], coordinates[5]));
        line1.getData().add(new XYChart.Data(coordinates[6], coordinates[7]));

        XYChart.Series line2 = new XYChart.Series();
        line2.setName("line2");
        line2.getData().add(new XYChart.Data(coordinates[8], coordinates[9]));
        line2.getData().add(new XYChart.Data(coordinates[10], coordinates[11]));

        XYChart.Series line3 = new XYChart.Series();
        line3.setName("line3");
        line3.getData().add(new XYChart.Data(coordinates[12], coordinates[13]));
        line3.getData().add(new XYChart.Data(coordinates[14], coordinates[15]));

        XYChart.Series line4 = new XYChart.Series();
        line4.setName("line4");
        line4.getData().add(new XYChart.Data(coordinates[16], coordinates[17]));
        line4.getData().add(new XYChart.Data(coordinates[18], coordinates[19]));

        Node chartBackground = lineChart.lookup(".chart-plot-background");

        chartBackground.setOnMousePressed(mouseEvent -> {


            DecimalFormat format = new DecimalFormat("#0.00");
            if(click>1){
                lineChart.getData().remove(5);
                click=0;
            }
            XYChart.Series line5 = new XYChart.Series();
            line5.setName("line5");
            double xCordinate = (double) xAxis.getValueForDisplay(mouseEvent.getX());
            double yCordinate =  (double)yAxis.getValueForDisplay(mouseEvent.getY());
            if (click==0){
                x1= xCordinate;
                y1 = yCordinate;
                System.out.println("the coordinate click is ("+format.format(x1)+","+format.format(y1)+") ");
                newSx = x1;
                newSy = y1;
                click++;

            }else if (click ==1){
                double x2= xCordinate;
                double y2 = yCordinate;
                newEx = x2;
                newEy = y2;
                System.out.println("the coordinate click is ("+format.format(x2)+","+format.format(y2)+") ");

                line5.getData().add(new XYChart.Data(x1,x1));
                line5.getData().add(new XYChart.Data(x2,y2));
                lineChart.getData().add(line5);
                Point.Double start = new Point.Double(newSx,newSy);
                Point.Double end = new Point2D.Double(newEx,newEy);
                click++;
//                bst.printInseLine(start,end,bst.root);

            }


        });
        Scene scene = new Scene(lineChart, 600, 600);
        lineChart.getData().addAll(line0,line1,line2,line3,line4);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        readFile("inputGUI.txt");
        launch(args);


    }

    public static void readFile(String input) {
        Scanner sc = null;
        try {
            sc = new Scanner(new File(input));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        number = Integer.valueOf(sc.nextLine());
        coordinates = new double[number*4];

        for (int i = 0; i < number*4; i=i+4) {
            String x = sc.nextLine();
            String[] coord = x.split(" ");
            double x1 = Double.parseDouble(coord[0]);
            double y1 = Double.parseDouble(coord[1]);
            double x2 = Double.parseDouble(coord[2]);
            double y2 = Double.parseDouble(coord[3]);
            coordinates[i] = x1;
            coordinates[i+1]= y1;
            coordinates[i+2] = x2;
            coordinates[i+3] = y2;
        }
    }
}
