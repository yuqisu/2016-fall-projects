import com.sun.org.apache.xpath.internal.operations.String;
import java.awt.*;

/**
 * Created by yuqisu on 10/2/16.
 */
public class MyTreeNode<T extends Comparable<T>> {
    public T data;
    public MyTreeNode<T> leftChild;
    public MyTreeNode<T> rightChild;
    public MyTreeNode<T> parent;
    public PointTest test;
    public  Point.Double pStart;
    public  Point.Double pEnd;
    public int name;
    java.lang.String check1="";

    MyTreeNode(T data,MyTreeNode<T> parent){
        this.data = data;
        this.parent = parent;
    }
    MyTreeNode(Point.Double p1,Point.Double p2, int name){
            pStart = p1;
            pEnd = p2;
            this.name = name;

    }
    public java.lang.String getp(){
        return "Start point:("+ pStart.getX()+","+pStart.getY()+") "+
                "End point:("+ pEnd.getX()+","+pEnd.getY()+")";
    }

    public static void main(String[]args){
        Point.Double x1 = new Point.Double(0,0);
        Point.Double x2 = new Point.Double(1,1);
        Point.Double x3 = new Point.Double(1,0);
        Point.Double x4 = new Point.Double(0,1);
        Point.Double x5 = new Point.Double(0,0.5);
        Point.Double x6 = new Point.Double(0.1,0.2);
        Tree theTree = new Tree();
        theTree.insertLine(x1,x2);
        theTree.insertLine(x3,x4);
        theTree.insertLine(x5,x6);





    }
    /*
    build the newTree
     */
    public MyTreeNode<T> insertLine(Point.Double p1,Point.Double p2,MyTreeNode curr){
        MyTreeNode newLine= new MyTreeNode(p1,p2,name);
        //when the tree is null
        if (curr==null){
            return newLine;

        }//when the newline is at the counterclockwise
        else if (test.ccw(p1,curr.pStart,curr.pEnd)==PointTest.COUNTERCLOCKWISE
                && test.ccw(p2,curr.pStart,curr.pEnd)==PointTest.COUNTERCLOCKWISE){
            curr.leftChild = insertLine(p1,p2,curr.leftChild);

        }else if(test.ccw(p1,curr.pStart,curr.pEnd)==PointTest.CLOCKWISE
                && test.ccw(p2,curr.pStart,curr.pEnd)==PointTest.CLOCKWISE){//when the newline is at the clockwise
            curr.rightChild = insertLine(p1,p2,curr.rightChild);

        }
        else if(test.ccw(p1,curr.pStart,curr.pEnd)==PointTest.COLINEAR ){ //when they are colinear
           if (test.ccw(p2,curr.pStart,curr.pEnd)==PointTest.COUNTERCLOCKWISE
                   ) {
                   curr.leftChild = insertLine(p1,p2,curr.leftChild);
               curr.rightChild = insertLine(p1,p2,curr.rightChild);
           }

        }else if (test.ccw(p2,curr.pStart,curr.pEnd)==PointTest.COLINEAR){
            if (test.ccw(p1,curr.pStart,curr.pEnd)==PointTest.COUNTERCLOCKWISE){
            }
        }
        else {//when they intersect
            Point.Double intersect = intersectPoint(p1,p2,curr);//get the intersection point
            if (test.ccw(p1,curr.pStart,curr.pEnd)==PointTest.COUNTERCLOCKWISE ){
                curr.leftChild = insertLine(p1,intersect,curr.leftChild);
                curr.rightChild = insertLine(intersect,p2,curr.rightChild);
            }else if (test.ccw(p1,curr.pStart,curr.pEnd)==PointTest.CLOCKWISE){
                curr.leftChild = insertLine(intersect,p2,curr.leftChild);
                curr.rightChild = insertLine(p1,intersect,curr.rightChild);
            }


        }
        return curr;
    }

    public void printInseLine(Point.Double x1,Point.Double x2,MyTreeNode curr){
        if (test.ccw(x1,curr.pStart,curr.pEnd)==PointTest.COUNTERCLOCKWISE
                && test.ccw(x2,curr.pStart,curr.pEnd)==PointTest.CLOCKWISE
            ||test.ccw(x1,curr.pStart,curr.pEnd)==PointTest.CLOCKWISE
                && test.ccw(x2,curr.pStart,curr.pEnd)==PointTest.COUNTERCLOCKWISE){
            System.out.println(curr.name);
//            System.out.println(curr.getp());
        }
        else if (test.ccw(x1,curr.pStart,curr.pEnd)==PointTest.COLINEAR
                || test.ccw(x2,curr.pStart,curr.pEnd)==PointTest.COLINEAR){
            System.out.println("point are colinear with Line "+curr.name);
            System.out.println(curr.getp());

        }
        else if (test.ccw(x1,curr.pStart,curr.pEnd)==PointTest.COLINEAR
                && test.ccw(x2,curr.pStart,curr.pEnd)==PointTest.COLINEAR){
            System.out.print("xxx");

        }
//            System.out.print(" ");
        /*
    check whether two are fall into the same region
     */
    }
    public int checkRegion(Point.Double p1, Point.Double p2, MyTreeNode curr){

        if (check(p1,curr).compareTo(check(p2,curr))==0) {
            return -1;

        }
        else{
           return 0;
        }

    }
    public java.lang.String check(Point.Double p1,MyTreeNode curr){
        check1="";
        while (curr!=null) {
            if (test.ccw(p1, curr.pStart, curr.pEnd) == PointTest.COUNTERCLOCKWISE) {
                check1 += "L";
                curr = curr.leftChild;
            }
            else if (test.ccw(p1, curr.pStart, curr.pEnd) == PointTest.CLOCKWISE) {
                check1 += "R";
                curr = curr.rightChild;
            }else if (test.ccw(p1, curr.pStart, curr.pEnd) == PointTest.COLINEAR){
                break;
            }
        }
        return check1;
    }
    /*
    find the intersection point
     */
    public Point.Double intersectPoint(Point.Double p1,Point.Double p2,MyTreeNode curr){
        double x1= p1.getX();
        double y1 = p1.getY();
        double x2= p2.getX();
        double y2 = p2.getY();
        double x3 = curr.pStart.getX();
        double y3 = curr.pStart.getY();
        double x4 = curr.pEnd.getX();
        double y4 = curr.pEnd.getY();
        double Px=((x1*y2-y1*x2)*(x3-x4)-(x1-x2)*(x3*y4-y3*x4))/((x1-x2)*(y3-y4)-(y1-y2)*(x3-x4));
        double Py = ((x1*y2-y1*x2)*(y3-y4)-(y1-y2)*(x3*y4-y3*x4))/((x1-x2)*(y3-y4)-(y1-y2)*(x3-x4));
        return new Point.Double(Px,Py);
    }

    public int countIn(MyTreeNode curr){
        int count = 0;
        if (curr==null){
            return 0;
        } else
        count = countIn(curr.leftChild)+countIn(curr.rightChild)+1;
        return count;
    }
    public int internalPath(MyTreeNode curr){
        if (curr==null){
            return 0;
        }else
        return internalPath(curr.leftChild)+ internalPath(curr.rightChild)+countIn(curr)-1;
    }
    public MyTreeNode<T> insert(MyTreeNode<T> curr,MyTreeNode<T> newNode) {
        if (curr==null){
            return newNode;
        }
        else if(newNode.data.compareTo(curr.data)>0){
            curr.rightChild=insert(curr.rightChild,newNode);
        }else{
            curr.leftChild=insert(curr.leftChild,newNode);
        }
        return curr;
    }
    public void printInOrder(MyTreeNode curr){
        if(curr!=null){
            printInOrder(curr.leftChild);
            System.out.println(curr.name);
            System.out.println(curr.getp());;
            printInOrder(curr.rightChild);
        }
    }
    public void printPreOrder(MyTreeNode curr){
        if(curr!=null) {
            System.out.println(curr.name);
            System.out.println(curr.getp());;
            printPreOrder(curr.leftChild);
            printPreOrder(curr.rightChild);
        }
    }
    public void printPostOrder(MyTreeNode curr){

        if(curr!=null) {

            printPostOrder(curr.leftChild);
            printPostOrder(curr.rightChild);
            System.out.println(curr.name);
            System.out.println(curr.getp());;
        }
    }
    public static void delete(MyTreeNode deleted,MyTreeNode root){
        MyTreeNode curr = root;
        MyTreeNode parent = root;
        boolean isLeft = false;

            while (deleted.data!= curr.data) {
                parent = curr;
                if (deleted.data.compareTo(curr.data) < 0) {
                    isLeft = true;
                    curr = curr.leftChild;
                } else {
                    isLeft = false;
                    curr = curr.rightChild;
                }
            }
            if (curr.leftChild == null && curr.rightChild == null) {
                if (curr == root){
                    root = null;
                }else if (isLeft){
                    parent.leftChild=null;
                }else
                    parent.rightChild = null;

            }else if(curr.rightChild == null){
                if (curr==root){
                    root = curr.leftChild;
                }else if (isLeft){
                    parent.leftChild = curr.leftChild;
                }else
                    parent.rightChild = curr.leftChild;

            }else if(curr.leftChild == null){
                if (curr==root){
                    root = curr.rightChild;
                }else if (isLeft){
                    parent.leftChild = curr.rightChild;}
                else
                    parent.rightChild = curr.rightChild;
            }else{
                MyTreeNode newNode = replaceNode(curr);
                if (curr == root){
                    root = newNode;
                }
                else if(isLeft){
                    parent.leftChild = newNode;
                }else{
                    parent.rightChild = newNode;
                    newNode.leftChild = curr.leftChild;
                }

            }

    }
    public static MyTreeNode replaceNode(MyTreeNode deleted){
        MyTreeNode replaceParent = deleted;
        MyTreeNode replace = deleted;
        MyTreeNode curr = deleted.rightChild;
        while (curr!=null){
            replaceParent =replace;
            replace = curr;
            curr=curr.leftChild;
        }
        if(replace!= deleted.rightChild) {
            replaceParent.leftChild =replace.rightChild;
            replace.rightChild = deleted.rightChild;
            replace.leftChild = deleted.leftChild;
        }
        return replace;
    }

}

