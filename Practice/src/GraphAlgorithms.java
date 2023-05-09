import java.util.*;
public class GraphAlgorithms {

    public ArrayList<Integer> bfsOfGraph(int V, ArrayList<ArrayList<Integer>> adj) {

        Queue<Integer> q = new LinkedList<>();
        ArrayList<Integer> bfs = new ArrayList<>();
        boolean[] visited = new boolean[V];
        q.add(0);
        visited[0]=true;
        while(!q.isEmpty()){
            int node =q.remove();
            bfs.add(node);
            for(int i: adj.get(node)){
                if(!visited[i]){
                    visited[i]=true;
                    q.add(i);
                }
            }
//            for(int i=0;i<adj.get(node).size();i++){
//                if(!visited[adj.get(node).get(i)]){
//                    visited[adj.get(node).get(i)] = true;
//                    q.add(adj.get(node).get(i));
//
//                }
//            }
        }
        return bfs;
    }



    public static ArrayList<Integer> dfsOfGraph(int V,ArrayList<ArrayList<Integer>>adj){
        boolean[] visited = new boolean[V];
        ArrayList<Integer> dfsAns = new ArrayList<>();
        return dfs(0,adj,visited,dfsAns);
    }
    private static ArrayList<Integer> dfs(int node,ArrayList<ArrayList<Integer>>adj,boolean[] visited,
                                                ArrayList<Integer>dfsAns){
        visited[node] = true;
        dfsAns.add(node);
        for(int it:adj.get(node)){
            if(!visited[it]){
                dfs(it,adj,visited,dfsAns);
            }
        }
        return dfsAns;
    }

/*DETECT CYCLE IN THE GRAPH*/
    public boolean isCycle(int V, ArrayList<ArrayList<Integer>> adj) {
        // Code here
        boolean[] visited=new boolean[V];
        for(int i=0;i<V;i++){
            if(!visited[i]){
                boolean ans = dfs(i,adj,visited,-1);
                if(ans)return true;
            }
        }
        return false;

    }
    //USING DFS
    private boolean dfs(int node, ArrayList<ArrayList<Integer>> adj,boolean[]visited,int parent){
        visited[node]=true;
        for(int it:adj.get(node)){
            if(!visited[it]){
                boolean hasCycle=dfs(it,adj,visited,node);
                if(hasCycle)return true;
            }
            else if(it!=parent){
                return true;
            }
        }
        return false;
    }
    //USING BFS
    static class PairBFS{
        int node,parent;
        PairBFS(int n,int p){
            this.node = n;
            this.parent=p;
        }
    }
    public boolean bfs(int node,int V,ArrayList<ArrayList<Integer>> graph,boolean[] visited){
        Queue<PairBFS> q = new LinkedList<>();

        visited[node]=true;
        q.add(new PairBFS(node, -1));
        while(!q.isEmpty()){
            int n=q.peek().node;
            int parent = q.peek().parent;
            q.remove();
            for(int nbrNode:graph.get(n)){
                if(visited[nbrNode]==false){
                    visited[nbrNode]=true;
                    q.add(new PairBFS(nbrNode, n));
                }
                else if(parent !=nbrNode){
                    return true;
                }
            }
        }
        return false;
    }


    /*FLOOD FILL ALGORITHM*/
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int iniColor = image[sr][sc];
        dfs(sr,sc,image,color,iniColor);
        return image;

    }
    public void dfs(int r,int c,int[][] image,int newColor,int iniColor){
        int n=image.length,m=image[0].length;
        if(!(r>=0&&c>=0&&r<n&&c<m&&image[r][c]!=newColor&&image[r][c]==iniColor))return;
        image[r][c]=newColor;
        dfs(r-1,c,image,newColor,iniColor);
        dfs(r+1,c,image,newColor,iniColor);
        dfs(r,c-1,image,newColor,iniColor);
        dfs(r,c+1,image,newColor,iniColor);
    }



    static class Pair{
        int row;
        int col;
        int time;
        Pair(int f,int s,int t){
            row=f;
            col=s;
            time=t;
        }
    }
    public int orangesRotting(int[][] grid)
    {

        int n =grid.length;
        int m= grid[0].length;
        Queue<Pair>  q= new LinkedList<>();
        boolean visited[][] = new boolean[n][m];
        int fresh=0;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(grid[i][j]==2){
                    q.add(new Pair(i,j,0));
                    visited[i][j]=true;
                }
                else if(grid[i][j]==1)fresh++;
            }
        }
        int[] delrow ={-1,0,1,0};
        int[] delcol = {0,-1,0,1};
        int time=0;
        while(!q.isEmpty()){
            int r=q.peek().row;
            int c=q.peek().col;
            int t=q.peek().time;
            q.remove();
            time=Math.max(time,t);
            for(int i =0;i<4;i++){
                int nrow=r+delrow[i];
                int ncol=c+delcol[i];
                if(nrow>=0&&nrow<n&&ncol>=0&&ncol<m&&grid[nrow][ncol]==1 &&visited[nrow][ncol]!=true){
                    q.add(new Pair(nrow,ncol,time+1));
                    visited[nrow][ncol]=true;
                }
            }

        }

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(visited[i][j]==false && grid[i][j]==1)return -1;
            }
        }
        return time;
    }

    //Topological Sorting
    static int[] topoSort(int V, ArrayList<ArrayList<Integer>> adj)
    {
      //   Using dfs
//         Stack<Integer> st = new Stack<>();
//         int[] vis = new int[V];
//         for(int i=0;i<V;i++){
//             if(vis[i]!=1){
//                 dfs(i,adj,vis,st);
//             }
//         }
//         int  i=0;
//         int ans[] = new int[V];
//         while(!st.isEmpty()){
//             ans[i]=st.pop();
//             i++;
//         }
//         return ans;


        //using BFS

        int[] id=new int[V];
        for(int i=0;i<V;i++){
            for(int it:adj.get(i)){
                id[it]++;
            }
        }
        Queue<Integer> q = new LinkedList<>();
        int[] ans = new int[V];
        for(int i=0;i<V;i++){
            if(id[i]==0){
                q.add(i);
            }
        }
        int index=0;

        while(!q.isEmpty()){
            int node = q.remove();
            ans[index++]=node;
            for(int nbrNode:adj.get(node)){
                id[nbrNode]--;
                if(id[nbrNode]==0){
                    q.add(nbrNode);
                }
            }
        }
        return ans;


    }
    // static void dfs(int node,ArrayList<ArrayList<Integer>> graph,int[]vis,Stack<Integer> st){
    //     vis[node]=1;
    //     for(int it:graph.get(node)){
    //         if(vis[it]!=1){
    //             dfs(it,graph,vis,st);
    //         }
    //     }
    //     st.push(node);
    // }


//SHORTEST PATH IN DIRECTED ACYCLIC GRAPH USING TOPOLOGICAL SORT//
    class Pair2{
    int dest;
    int wt;
    public Pair2(int dest,int wt){
        this.dest=dest;
        this.wt=wt;
    }
}
public int[] shortestPath(int N,int M, int[][] edge) {
    //Code here
    int[] vis=new int[N];
    Stack<Integer> st = new Stack<>();
    ArrayList<ArrayList<Pair2>> adj = new ArrayList<>();

    for(int i=0;i<N;i++){
        adj.add(new ArrayList<Pair2>());
    }

    for(int i=0;i<M;i++){
        int u=edge[i][0];
        int v=edge[i][1];
        int wt=edge[i][2];
        adj.get(u).add(new Pair2(v,wt));
    }

    for(int i=0;i<N;i++){
        if(vis[i]!=1){
            topoSort(i,adj,vis,st);
        }
    }
    int[] distance=new int[N];
    Arrays.fill(distance,9999999);
    distance[0]=0;

    while(!st.isEmpty()){
        int node=st.pop();
        for(int i=0;i<adj.get(node).size();i++){
            int dest=adj.get(node).get(i).dest;
            int wt= adj.get(node).get(i).wt;
            if(distance[node]+wt<distance[dest]){
                distance[dest]=distance[node]+wt;
            }
        }

    }
    for(int i=0;i<N;i++){
        if(distance[i]==9999999){
            distance[i]=-1;
        }
    }
    return distance;
}


    private void topoSort(int node,	ArrayList<ArrayList<Pair2>> adj,int[] vis,Stack<Integer>st){
        vis[node]=1;
        for(int i=0;i<adj.get(node).size();i++){
            int v=adj.get(node).get(i).dest;
            if(vis[v]!=1){
                topoSort(v,adj,vis,st);
            }
        }
        st.push(node);
    }
//    DIJKSTRA ALGORTIHM
    static class Dijk implements Comparable<Dijk>{
        int node;
        int weight;
        Dijk(int n,int d){
            node=n;
            weight=d;
        }
        public int compareTo(Dijk that){
            return this.weight-that.weight;
        }
    }
    static int[] dijkstra(int V, ArrayList<ArrayList<ArrayList<Integer>>> adj, int S)
    {
        //Using PRIORITY QUEUE

        PriorityQueue<Dijk> pq = new PriorityQueue<>();
        pq.add(new Dijk(S,0));
        int[] distance = new int[V];
        Arrays.fill(distance,(int)1e9);
        distance[S]=0;
        while(!pq.isEmpty()){
            int node=pq.peek().node;
            int weight=pq.peek().weight;
            pq.remove();

            for(int i=0;i<adj.get(node).size();i++){
                int adjNode=adj.get(node).get(i).get(0);
                int nodeWeight=adj.get(node).get(i).get(1);
                if(distance[adjNode]>weight+nodeWeight){
                    distance[adjNode]=distance[node]+nodeWeight;
                    pq.add(new Dijk(adjNode,distance[adjNode]));
                }
            }
        }

        return distance;
    }
    public static void main(String[] args) {
    }
}
