/**
 * Created by yuqisu on 10/2/16.
 */
public interface BST<T extends Comparable<T>> {
    public void insert(T x);
    public void delete(T x);
    public boolean lookup(T x);
    public void printPreOrder();
    public void printInOrder();
    public void printPostOrder();
}
