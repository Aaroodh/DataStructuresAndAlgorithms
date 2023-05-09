import java.util.*;

public class Graph {

    static class Edge{
        int src;
        int nbr;
        int wt;

        Edge(int src,int nbr,int wt){
            this.src = src;
            this.nbr=nbr;
            this.wt=wt;
        }
    }


    public static void  fillGraph(ArrayList[] graph){

        graph[0].add(new Edge(0,1,10));
        graph[0].add(new Edge(0,3,40));

        graph[1].add(new Edge(1,0,10));
        graph[1].add(new Edge(1,2,10));

        graph[2].add(new Edge(2,3,10));
        graph[2].add(new Edge(2,1,10));

        graph[3].add(new Edge(3,0,40));
        graph[3].add(new Edge(3,2,10));
        graph[3].add(new Edge(3,4,2));

        graph[4].add(new Edge(4,3,2));
        graph[4].add(new Edge(4,5,3));
        graph[4].add(new Edge(4,6,3));

        graph[5].add(new Edge(5,4,3));
        graph[5].add(new Edge(5,6,3));

        graph[6].add(new Edge(6,5,3));
        graph[6].add(new Edge(6,4,8));


    }

    public static void display(ArrayList<Edge>[] graph,int vertices){
        for(int i=0;i<vertices;i++){
            System.out.print("Source="+graph[i].get(0).src+" "+"Neighbour->");
            System.out.print("(");
            for(int j=0;j<graph[i].size();j++){
                System.out.print(graph[i].get(j).nbr+" wt="+graph[i].get(j).wt+",");
            }
            System.out.print(")");
            System.out.println();
        }
    }
    public static void display(ArrayList<ArrayList<Integer>> adj){
        for(int i =0 ;i<adj.size();i++){
            System.out.println("\nAdjacency list of vertex "+ i);
            System.out.print("head");
            for(int j=0;j<adj.get(i).size();j++){
                System.out.print("->"+adj.get(i).get(j));
            }
            System.out.println();
        }
    }

    static void addEdge(ArrayList<ArrayList<Integer>> adj,int u,int v){
            adj.get(u).add(v);
            adj.get(v).add(u);
    }

    /*BUILD A GRAPH FROM A EDGES ARRAY*/
    public ArrayList<ArrayList<Integer>> addNode( int n,int[][] edges){
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        for(int i=0;i<n;i++){
            ans.add(new ArrayList<Integer>());
        }
        for(int[]edge:edges){
            ans.get(edge[0]).add(edge[1]);
            ans.get(edge[1]).add(edge[0]);

        }
        return ans;
    }

    /*BUILD ADJACENCY LIST   FROM THE ADJACENCY MATRIX*/
    public ArrayList<ArrayList<Integer>> buildGraph(int[][] adjM){
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for(int i=0;i<adjM.length;i++){
            graph.add(new ArrayList<Integer>());
        }
        for(int i=0;i<adjM.length;i++){
            for(int j=0;j<adjM.length;j++){
                if(adjM[i][j]==1){
                    graph.get(i).add(j);
                    graph.get(j).add(i);
                }
            }
        }
        return graph;
    }
    /*BUILD A DIRECTED GRAPH ADJACENCY LIST FROM GIVEN ADJACENCY MATRIX*/
    public List<List<Integer>> buildDirectedGraph(int[][] graph) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < graph.length; i++) {
            adj.add(new ArrayList<Integer>());
        }
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph[i].length; j++) {
                adj.get(i).add(graph[i][j]);
            }
        }
        return adj;
    }



    public static void main(String[] args) {

        /*Representation using Edge Class*/
       int vertices = 7;
       ArrayList[] graph = new ArrayList[vertices];
       for (int i=0;i<vertices;i++){
           graph[i] = new ArrayList<Edge>();
       }
       fillGraph(graph);
       display(graph,vertices);
       for(int i=0;i<vertices;i++){
           System.out.println(graph[i]);
       }

        /*representation using ArrayList of ArrayList*/
        //     int vertices = 5;
        // ArrayList<ArrayList<Integer>> adj = new ArrayList<>(vertices);
        // for(int i=0;i<vertices;i++){
        //     adj.add(new ArrayList<Integer>());
        // }
        // addEdge(adj, 0, 1);
        // addEdge(adj, 0, 4);
        // addEdge(adj, 1, 2);
        // addEdge(adj, 1, 3);
        // addEdge(adj, 1, 4);
        // addEdge(adj, 2, 3);
        // addEdge(adj, 3, 4);
        // display(adj);

    }
}
