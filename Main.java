import java.lang.Integer;
import java.util.*;

class Main {
  
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Graph g = new Graph();
        
        
        g.initFromFile("highway_graph.txt");
        
        System.out.println("Graph Connected: " + g.isConnected());
        System.out.print("starting point: ");
        String start = in.nextLine();

        start= start!=null?"Concord":start;
        
        System.out.println("depth-first traversal from " + start + ":");
        g.depthFirstTrav(start);
        
        System.out.println("breadth-first traversal from " + start + ":");
        g.breadthFirstTrav(start);
        
        System.out.println("Dijkstra's algorithm from " + start + ":");
        g.dijkstra(start);
        
        System.out.println("Prim's algorithm from " + start + ":");
        g.prim(start);

       
    }
}