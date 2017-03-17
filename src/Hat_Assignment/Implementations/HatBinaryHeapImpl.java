package Hat_Assignment.Implementations;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.stream.Stream;

/**
 * Created by Stephen Adu on 07/03/2017.
 */
public class HatBinaryHeapImpl<T extends Comparable<T>> extends PriorityQueue<T> {

    private static final int DEFAULT_CAPACITY = 10;
    protected T[] array;
    protected int size;

    @SuppressWarnings("unchecked")
    public HatBinaryHeapImpl(){
        array = (T[]) new Comparable[DEFAULT_CAPACITY];
        size = 0;
    }

/*    public void add(T value){
        //Expand array if needed
        if (size >= array.length - 1){
            array = this.resize();
        }

        size++;
        int index = size;
        array[index] = value;

        bubbleUp();

    }*/

    public boolean isEmpty(){
        return size == 0;
    }

    public T peek(){
        if (this.isEmpty()){
            throw new IllegalStateException();
        }
        return array[1];
    }


    public T remove(){
        T result = peek();

        array[1] = array[size];
        array[size] = null;
        size--;

        bubbleDown();
        return result;
    }

    public String toString(){
        return Arrays.toString(array);
    }




    protected void bubbleDown(){
        int index = 1;

        //Bubble down
        while (hasLeftChild(index)){
            int smallerChild = leftIndex(index);

            if (hasRightChild(index) && array[leftIndex(index)].compareTo(array[rightIndex(index)]) > 0){
                smallerChild = rightIndex(index);
            }

            if (array[index].compareTo(array[smallerChild]) > 0){
                swap(index, smallerChild);
            }else{
                break;
            }

            index = smallerChild;

        }
    }

    protected void bubbleUp(){
        int index = this.size;
        while (hasParent(index) && (parent(index).compareTo(array[index]) > 0)){
            swap(index, parentIndex(index));
            index = parentIndex(index);
        }
    }

    protected boolean hasParent(int i){
        return i > 1;
    }

    protected int leftIndex(int i){
        return i * 2;
    }

    protected int rightIndex(int i){
        return i * 2 + 1;
    }

    protected boolean hasLeftChild(int i){
        return leftIndex(i) <= size;
    }

    protected boolean hasRightChild(int i){
        return rightIndex(i) <= size;
    }

    protected T parent(int i){
        return array[parentIndex(i)];
    }

    protected int parentIndex(int i){
        return i/2;
    }

    protected T[] resize(){
        return Arrays.copyOf(array, array.length * 2);
    }

    protected void swap(int index1, int index2){
        T tmp = array[index1];
        array[index1] = array[index2];
        array[index2] = tmp;
    }

}
