import java.lang.Integer;
import java.util.*;

class Main {
  
    public static void main(String[] args) {
      try{

        Heap<Integer> heap = new Heap<Integer>();
        heap.insert(new Integer(10));
        heap.insert(new Integer(30));
        heap.insert(new Integer(15));

        System.out.println(heap);
        } catch (NoSuchElementException e) {
          System.out.println("Invalid command: " + e.toString());
        }
    }
}