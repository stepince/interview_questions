import java.util.*;

/*
unweighted directed
Time Complexity : O(V + E)
Auxiliary Space : O(V)


algo:

   queue = new Queue();
   queue.add(src)
   init distance to -1
   distance(src)=0;
   while( !queue.empty()) {
       node = queue.remove();
       adj = loop all adjacency node
       if ( distance(adj) != -1  ){
           queue.add(adj)
           distance(adj) = distance(node) + 1;
       }
   }
   // this can be shorted circuited
   return distance[dest];
 */
public class ShortestPath {

    // Graph node class
    public static class Node {
        private final int value;
        private final List<Node> adjList = new LinkedList<>();
        
        // Basic constructor
        public Node(int value) {
            this.value = value;
        }

        public void add(Node n) {
            this.adjList.add(n);
        }

        public List<Node> getAll(){
            return this.adjList;
        }
    }

     public static int find( Node[] graph, int src, int dest){
         int[] distance = new int[graph.length];
         Arrays.fill(distance,-1);
         distance[src] = 0;
         Queue<Node> queue = new ArrayDeque<>();
         queue.add(graph[src]);

         while (!queue.isEmpty()){
             Node n = queue.poll();
             for ( Node nei: n.getAll() ){
                 // this is also a visited check
                 if ( distance[nei.value] == -1 ) {
                     queue.add(nei);
                     distance[nei.value] = distance[n.value] + 1;
                 }
             }
         }
         return distance[dest];
     }

     public static void main(String[] args){

        /* Construct sample graph
         * 0 ---> 1
         * ^ \    |
         * |  v   |
         * |   2  |  
         * |  ^   |
         * | /    v
         * 3 <--- 4
         */ 
        Node[] graph = new Node[5];
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new Node(i);
        }
        graph[0].add(graph[1]);
        graph[0].add(graph[2]);
        graph[1].add(graph[4]);
        graph[3].add(graph[0]);
        graph[3].add(graph[2]);
        System.out.println( find(graph,3,2));
     }

} 