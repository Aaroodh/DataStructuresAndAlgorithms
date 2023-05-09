import java.lang.reflect.Array;
import java.util.*;
public class PriorityQueues {

    public static void mergeKSortedArray(int[] arr,int K){
        int ind=0;
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        List<Integer> ans = new ArrayList<>();
        for(int i=0;i<arr.length;i++){
            if(heap.size()<K){
                heap.add(arr[i]);
            }
            else{
               ans.add(heap.remove());
            }
        }
        while (heap.size()>0){
            ans.add(heap.remove());
        }
        System.out.println(ans);
    }
        public static void getKMax(int[] arr,int K){
            PriorityQueue<Integer> pq = new PriorityQueue<>();
            for(int num:arr) {
                if(pq.size()<K) {
                    pq.add(num);
                }
                else{
                    if(num>pq.peek()){
                        pq.remove();
                        pq.add(num);
                    }
                }

            }
            for (int i = 0; i <K ; i++) {
                System.out.println(pq.remove());
            }
        }



        public static int KthSmallest(int[] nums,int K){
            PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
            int ksmall=0;
            for(int ele:nums) {
                if(pq.size()<K){
                    pq.add(ele);
                }
                else {
                    if(pq.peek()>ele) {
                        pq.remove();
                        pq.add(ele);
                    }
                }
            }
            ksmall= pq.peek();
            return ksmall;
        }

    public static class Pair implements Comparable<Pair>{
        int data;
        int gap;
        Pair(){super();}
        Pair(int data,int gap){
            this.data=data;
            this.gap=gap;
        }
        @Override
        public int compareTo(Pair otherPair) {
            if(this.gap==otherPair.gap){
                return this.data-otherPair.data;
            }
            else{
                return this.gap-otherPair.gap;
            }
        }
    }

    public static List<Integer> kClosestNumbers(int[] nums,int K,int number){
        PriorityQueue<Pair> pq = new PriorityQueue<>(Collections.reverseOrder());
        for(int i=0;i<nums.length;i++){
            int data=nums[i];
            int gap=Math.abs(number-nums[i]);
            if(pq.size()<K){
                pq.add(new Pair(data,gap));
            }
            else{
                if(pq.peek().gap>gap){
                    pq.remove();
                    pq.add(new Pair(data,gap));
                }
            }
        }
        List<Integer> ans = new ArrayList<>();
        while(!pq.isEmpty()){
            ans.add(pq.remove().data);
        }
            Collections.sort(ans);
        return ans;
    }

    public static List<Integer> optimisedKClosestNumbers(int[] nums,int K,int number){
        int l=0;
        int r=nums.length-1;
        int mid=0;
        while(l<=r){
            mid = (l+r)/2;
            if(nums[mid]==number){
                break;
            }
            if(nums[mid]>number)r=mid-1;
            else
                l=mid+1;
        }
        l=mid-1;
        r=mid;
        List<Integer> ans = new ArrayList<>();
        while(l>0 && r<nums.length && K>0){
            if(Math.abs(nums[l]-number)<=Math.abs(nums[r]-number)){
                ans.add(nums[l]);
                l--;
            }
            else{
                ans.add(nums[r]);
                r++;
            }
            K--;
        }
        return ans;
    }
  public  static class Details implements  Comparable<Details>{
        int dis;
        int index;
        Details(int dis,int index){
            this.dis=dis;
            this.index=index;
        }
        Details(){}

      @Override
      public int compareTo(Details o) {
            if(this.dis==o.dis){
                return this.dis;
            }
            else {
                return this.dis-o.dis;
            }
          }
  }

    public static void KclosestToOrigin(int[][] points,int k) {

        PriorityQueue<Details> pq = new PriorityQueue<Details>(Collections.reverseOrder());
        for (int i = 0; i < points.length; i++) {
            int dis = (int) (Math.pow(points[i][0], 2) + Math.pow(points[i][1], 2));
            if (pq.size() < k) {
                pq.add(new Details(dis, i));
            } else {
                if (pq.peek().dis > dis) {
                    pq.remove();
                    pq.add(new Details(dis, i));
                }
            }
        }
        int[][] ans = new int[k][2];
        for (int i = 0; i <k; i++) {
            for (int j = 0; j < 2; j++) {
                ans[i][j] = points[pq.peek().index][j];
                System.out.println(points[pq.peek().index][j]);
            }
            pq.remove();
        }
    }


    public static void main(String[] args) {
        int [] arr={2,3,41,34,5,21,431,134,4432,513,345,56,78,869,557};
        PriorityQueue<Integer> p= new PriorityQueue<>();
        PriorityQueue<Integer> p2= new PriorityQueue<>(Collections.reverseOrder());
        for(int i:arr){
            p.add(i);
            p2.add(i);
        }

        System.out.println(p+""+p.peek());
        System.out.println(p2+""+p2.peek());


//        getKMax(arr,3);
//        System.out.println(KthSmallest(arr,3));
        int[] nums={1,2,3,4,5,6,7,8,9};
//        mergeKSortedArray(nums,4);
//        System.out.println(kClosestNumbers(nums,3,7));
//        System.out.println(optimisedKClosestNumbers(nums,3,5));
/*        int [][] points={{1,3},{-2,2},{2,-2}};
        KclosestToOrigin(points,2);*/
    }
}
