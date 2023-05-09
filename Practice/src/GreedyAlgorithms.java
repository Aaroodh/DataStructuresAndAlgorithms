import java.util.*;

class Node implements Comparable<Node>{
    int data;
    Node right;
    Node left;
    Node(int d){
        data=d;
    }
    public int compareTo(Node other){
        if(this.data==other.data)return 1;
        return this.data-other.data;
    }
}
public class GreedyAlgorithms {
    //FRACTIONAL KNAPSACK

//    class Pair implements Comparable<Pair>{
//        double d;
//        Item it;
//        Pair(double d,Item i){
//            this.d=d;
//            this.it=i;
//        }
//        public int compareTo(Pair other){
//            if(this.d==other.d)return 0;
//            return (int)(this.d-other.d);
//        }
//    }
//    double fractionalKnapsack(int W, Item arr[], int n) {
//         ArrayList<Pair> a=new ArrayList<>();
//         double ans=0;
//         for(int i=0;i<n;i++){
//             double itemPerValue=(double)arr[i].value/(double)arr[i].weight;
//             a.add(new Pair(itemPerValue,arr[i]));
//         }
//         Collections.sort(a,Collections.reverseOrder());
//         for(int i=0;i<n;i++){
//             if(a.get(i).it.weight>W){
//                 //you cam take ony fractional value
//                 ans+=W*a.get(i).d;
//                 break;
//             }
//             else{
//                 //take the full value
//                 ans+=a.get(i).it.value;
//                 W-=a.get(i).it.weight;
//             }
//         }
//         return ans;
//    }
//

    //HUFFMAN ENCODING
    public ArrayList<String> huffmanCodes(String S, int f[], int N)
    {
        // Code here
        PriorityQueue<Node> pq = new PriorityQueue<>();
        for(int i:f){
            pq.add(new Node(i));
        }
        while(pq.size()>1){
            Node left = pq.remove();
            Node right = pq.remove();
            Node newNode=new Node(left.data+right.data);
            newNode.left=left;
            newNode.right=right;
            pq.add(newNode);
        }

        Node root=pq.remove();
        ArrayList<String> ans = new ArrayList<>();
        String temp=new String("");
        traverse(root,ans,temp);
        return ans;
    }
    public void traverse(Node root,ArrayList<String> ans,String temp){
        if(root.left==null&&root.right==null){
            ans.add(temp);
            return;
        }
        traverse(root.left,ans,temp+"0");
        traverse(root.right,ans,temp+"1");
    }


    //JOB SCHEDULING
    static class Job {
        int id, profit, deadline;
        Job(int x, int y, int z){
            this.id = x;
            this.deadline = y;
            this.profit = z;
        }
    }
    int[] JobScheduling(Job arr[], int n)
    {
        // Your code here
        Arrays.sort(arr,(x,y)->x.profit-y.profit);
        int maxDead=Integer.MIN_VALUE;
        for(int i=0;i<n;i++){
            maxDead=Math.max(maxDead,arr[i].deadline);
        }
        int[] sch=new int[maxDead+1];
        int profit=0;
        int jobs=0;
        for(int i=n-1;i>=0;i--){
            int jobID=arr[i].id;
            int currDead=arr[i].deadline;
            int curProfit=arr[i].profit;
            for(int k=currDead;k>0;k--){
                if(sch[k]==0){
                    jobs++;
                    profit+=curProfit;
                    sch[k]=jobID;
                    break;
                }
            }
        }
        int ans[]=new int[3];
        ans[0]=jobs;
        ans[1]=profit;
        return ans;
    }
}
