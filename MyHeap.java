

/**
 * Created by yuqisu on 10/4/16.
 */
interface Heap<T extends Comparable<T>> {

    public void insert(T item);
    public boolean isEmpty();
    public int size();
    public T deleteMin();
}
public class MyHeap<T extends Comparable<T>> implements Heap{
    public T[] arr;
    public int size;
    public final int capcity=100;
    MyHeap(){
        size = 0;
        arr = (T[])new Comparable[capcity];

    }
    public MyHeap(int capcity){

        arr = (T[])new Comparable[capcity];
    }
    public MyHeap(T[] array){
        size = array.length;
        arr = (T[]) new Comparable[array.length+1];

        System.arraycopy(array, 0, arr, 1, array.length);//we do not use 0 index



    }


    @Override
    public void insert(Comparable item) {
        //TODO
        if (size == arr.length-1){
            resizeArray();
        }
        size++;
        int index = size;
        arr[index] = (T) item;

        bubbleUp();

    }

    @Override
    public boolean isEmpty() {
        return (size==0);
    }

    @Override
    public int size() {
        return arr.length;
    }

    @Override
    public T deleteMin() {
        T delete = arr[1];
        arr[1] = arr[size];
        arr[size] = null;
        size--;
        bubbleDown(1);
        return delete;
    }
    public void bubbleUp(){
        int index = this.size;
        while (index>1 &&arr[index].compareTo(arr[index/2])<0){
            swap(index,index/2);
            index = index/2;
        }

    }
    public void bubbleDown(int k) {
        T tmp = arr[k];
        int child;

        for(; 2*k <= size; k = child)
        {
            child = 2*k;

            if(child != size &&
                    arr[child].compareTo(arr[child + 1]) > 0) child++;

            if(tmp.compareTo(arr[child]) > 0)  arr[k] = arr[child];
            else
                break;
        }
        arr[k] = tmp;
    }
    public void printHeap(){
        for(int i=1;i<=size;i++){

            System.out.println(arr[i]);
        }
    }

    public void resizeArray(){
        T[] old= arr;
        arr = (T[]) new Comparable[arr.length*2-1];
        System.arraycopy(old,1,arr,1,size);

    }
    public void swap(int index1, int index2){
        T temp= arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }
    public void heapify(T[] array){

        size=array.length;
        arr=(T[])new Comparable[size+1];
        System.arraycopy(array,0,arr,1,size);
        for (int k = size/2; k > 0; k--)
        {
            bubbleDown(k);
        }

    }

}
