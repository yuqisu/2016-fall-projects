import java.awt.*;

/**
 * Created by yuqisu on 10/2/16.
 */
class Tree implements BST{
    MyTreeNode root;
    int i = 0;


    @Override
    public void insert(Comparable x) {
        MyTreeNode newNode = new MyTreeNode(x,root);
        if (root == null) {
            root = newNode;
        } else {
            MyTreeNode curr = root;
            curr.insert(curr, newNode);
        }

    }
    /*
    insert the newLine
     */
    public void insertLine(Point.Double x1, Point.Double x2){
        MyTreeNode newLine  = new MyTreeNode(x1,x2,i);
        if (root==null){
            root = newLine;
            i++;
        }else{
            i++;
          root = newLine.insertLine(x1,x2,root);
        }

    }
    /*
    return 1 if two points are separated
    return 0 if fall into same region;
     */
    public int checkRegion(Point.Double x1, Point.Double x2){
       return root.checkRegion(x1,x2,root);

    }
    /*
    print out the line that separated two points
     */
    public void printInseLine(Point.Double x1, Point.Double x2,MyTreeNode curr){
         curr.printInseLine(x1,x2,curr);
    }
    /*
    print out the external nodes
     */
    public int countOut(){
       return root.countIn(root)+1;
    }
    /*
    print out the average length path
     */
    public int aveLength(){
        return (root.internalPath(root)+2*root.countIn(root))/(countOut());
    }



    @Override
    public void delete(Comparable x) {
        MyTreeNode newNode = new MyTreeNode(x,root);
        if (lookup(x)){
            newNode.delete(newNode,root);
        }
    }


    @Override
    public boolean lookup(Comparable x) {

        MyTreeNode curr = root;
        while (x!=curr.data){
            if (x.compareTo(curr.data)<0){
                curr = curr.leftChild;
            }else{
                curr = curr.rightChild;
            }
            if (curr == null){
                return false;
            }
        }
        return true;
    }

    @Override
    public void printPreOrder() {
        root.printPreOrder(root);
    }

    @Override
    public void printInOrder() {
        root.printInOrder(root);
    }

    @Override
    public void printPostOrder() {
        root.printPostOrder(root);
    }
}

