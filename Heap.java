import java.lang.reflect.Array;
import java.util.*;

public class Heap<T extends Comparable<T>> {
  private static int MAX_SIZE = 16;
  private T[] array;
  private int count;

  public Heap() {
    array = (T[])new Comparable[MAX_SIZE];
    count = 0;
  }

  //O(log n)
  public void insert(T value) {
    if (count == array.length) {
      // grow the array
      T[] newarray = (T[])new Comparable[array.length * 2];
      System.arraycopy(array, 0, newarray, 0, count);
      array = newarray;
    }
    
    array[count] = value;
    siftUp(count);
    count++;
  }

  //O(log n)
  public T removeHighestPriority() {
    T min = getHighestPriority();

    array[0] = array[count-1];
    count--;

    siftDown(0);

    return min;
  }
  
  //O(1)
  public T getHighestPriority() {
    if(count == 0) {
      throw new NoSuchElementException(); 
    }

    return array[0];
  }

    protected void siftDown(int index) {
    int leftIndex = getLeftChildIndex(index);
    int rightIndex = getRightChildIndex(index);

    //Find minimum of left and right child elements.
    int smallerIndex = -1;

    if(leftIndex != -1 && rightIndex != -1 ) {
        smallerIndex = getElementAtIndex(leftIndex).compareTo(getElementAtIndex(rightIndex)) < 0 ? leftIndex: rightIndex;
    } else if(leftIndex != -1) {
      smallerIndex = leftIndex;
    } else if(rightIndex != -1) {
      smallerIndex = rightIndex;
    }
    
    if(smallerIndex == -1) {
      return;
    }

     //Compare the smaller c hild with the current index to see if a swap
     // and further sift down is needed.
    if(getElementAtIndex(smallerIndex).compareTo(getElementAtIndex(index)) < 0 ) {
      swap(smallerIndex,index);
      siftDown(smallerIndex);
    }
  }

  protected void sift_Up(int index) {
    // Store a reference to the element being sifted.
    T toSift = getElementAtIndex(index);
    int parentIndex = getParentIndex(index);

    if(parentIndex !=-1 && toSift.compareTo(getElementAtIndex(parentIndex)) <= 0) {
      swap(index,parentIndex);
    }
    
    siftUp(index);      
   
  }
  /*
    * siftUp - sift the element in array[i] up into its
    * correct position in the heap.
    */
  private void siftUp(int i) {
      // Store a reference to the element being sifted.
      T toSift = getElementAtIndex(i);

      // Find where the sifted element belongs.
      int child = i;
      while (child > 0) {
          int parent = (child - 1)/2;
          
          // Check if we're done.
          if (toSift.compareTo(getElementAtIndex(parent)) <= 0) {
              break;
          }
          
          // If not, move parent down and move up one level in the tree.
          //array[child] = array[parent];
          swap(parent,child);
          child = parent;
      }
      
      array[child] = toSift;
  }
  
    /*
    * siftDown - sift the element in array[i] down into its
    * correct position in the heap.
    */
  private void sift_Down(int i) {
      // Store a reference to the element being sifted.
      T toSift = array[i];
      
      // Find where the sifted element belongs.
      int parent = i;
      int child = 2 * parent + 1;
      while (child < count) {
          // If the right child is bigger, compare with it.
          if (child < count - 1  &&
              array[child].compareTo(array[child + 1]) < 0) {
              child = child + 1;
          }
          
          // Check if we're done.
          if (toSift.compareTo(array[child]) >= 0) {
              break;
          }
          
          // If not, move child up and move down one level in the tree.
          array[parent] = array[child];
          parent = child;
          child = 2 * parent + 1;
      }
      
      array[parent] = toSift;
  }
  
  protected void swap(int index1, int index2) {
    T val = array[index1];
    array[index1] = array[index2];
    array[index2] = val;
  }

  public int getCount() {
    return count;
  }

  public boolean isEmpty() {
    return count == 0;
  }

  public boolean isFull(){
    return count == array.length;
  }
  
  public T getElementAtIndex(int index) {
    return array[index];
  }
  
  public int getLeftChildIndex(int index) {
    int leftChildIndex = 2 * index + 1;
    if(leftChildIndex >= count) {
      return -1;
    }

    return leftChildIndex;
  }

  public int getRightChildIndex(int index) {
    int rightChildIndex = 2 * index + 2;
    if(rightChildIndex >= count) {
      return -1;
    }

    return rightChildIndex;
  }

  public int getParentIndex(int index) {
    if(index < 0 || index > count) {
      return -1;
    }

    return (index - 1) / 2;
  }

     /**
     * toString - create a string representation of the heap of the form
     * { ( root ) ( values in level 1 ) ( values in level 2 ) ... }
     */
    public String toString() {
        String str = "{ ";
        
        int start = 0;
        int levelSize = 1;
        while (start < count) {
            // print all of the values at the current level of the tree
            str += "( ";
            for (int i = start; i < start + levelSize && i < count; i++)
                str += (array[i] + " ");
            str += ") ";
            
            // move down to the next level
            start += levelSize;
            levelSize *= 2;
        }
        
        str += "}";
        return str;
    }
}
