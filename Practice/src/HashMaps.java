import java.util.*;

public class HashMaps {
    static  void getCommonElement(int[] arr1,int [] arr2){
        HashMap<Integer,Integer> hm = new HashMap<>();
        for(Integer i : arr1){
            if(hm.containsKey(i)){
                hm.put(i,hm.get(i)+1);
            }
            else{
                hm.put(i,1);
            }
        }
        for(Integer key : arr2){
            if(hm.containsKey(key) &&hm.get(key)>0){
                System.out.print(key);
                hm.put(key,hm.get(key)-1);
            }
        }
        System.out.println(hm);
    }
    static ArrayList<ArrayList<Integer>> longestConsecutiveSequence(int[] arr){
        HashMap<Integer,Boolean> hm = new HashMap<>();
        for(int n:arr){
            hm.put(n,true);
        }
        for(int key: hm.keySet()){
                if(hm.containsKey(key-1)){
                    hm.put(key,false);
                }
        }
        System.out.println(hm);
        int maxL=0;
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        for(int val:arr){
            if(hm.get(val)==true){
                ArrayList<Integer> temp = new ArrayList<>();
                int len=1;
                temp.add(val);
                while(hm.containsKey(val+1)){
                    val=val+1;
                    len++;
                    temp.add(val);
                }
                if(maxL<len)maxL=len;
                ans.add(temp);
            }
        }
        System.out.println("longestConsecutiveSequence is "+maxL);
        return ans;
    }
    static int prime(){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for(int i =2;i<n/2;i++){
            if(n%i==0) {
                return 0;
            }
        }
        return 1;
    }
    public static class DetailsMarket implements Comparable<DetailsMarket> {
        int key;
        int val;

        DetailsMarket() {
            super();
        }

        DetailsMarket(int key, int val) {
            this.key = key;
            this.val = val;
        }

        @Override
        public int compareTo(DetailsMarket other) {

            if (this.val == other.val) {
                if(this.key>other.key){
                    return this.key-other.key;
                }
                else{
                    return other.key-this.key;
                }
            }

            else{
                return other.key-this.key;
            }
        }
    }

    public static void superMarket(int[] arr,int n){
        PriorityQueue<DetailsMarket> pq = new PriorityQueue<>(Collections.reverseOrder());
        HashMap<Integer,Integer> map = new HashMap<>();
        for (int num :
                arr) {
            if (!map.containsKey(num)) {
                map.put(num, 1);
            }
            else{
                map.put(num,map.get(num)+1);
            }
        }
        for (int key :map.keySet()) {
            if(pq.size()<n){
                pq.add(new DetailsMarket(key, map.get(key)));
            }
            else{
//                if(pq.peek().val<map.get(key)&&pq.peek().key<key){
//                    pq.remove();
//                    pq.add(new DetailsMarket(key, map.get(key)));
//                }

                if(pq.peek().val<map.get(key)){
                    pq.remove();
                    pq.add(new DetailsMarket(key, map.get(key)));
                }
            }
        }
        System.out.println(map);
        while(!pq.isEmpty()){
            System.out.println(pq.remove().key);
        }
    }


    public static void main(String[] args) {
        String str= "abracadabra";
        HashMap<Character,Integer> hm = new HashMap<>();
        for(int i=0;i<str.length();i++) {
            Character c = str.charAt(i);
            if (hm.containsKey(c)) {
                hm.put(c, hm.get(c) + 1);
            } else {
                hm.put(c, 1);
            }
        }
            int [] arr1 = {1,1,2,2,2,3,3,3,3,4,4,4,4,4,4,4,4,5,5,5,5,5,5,5,5,5,5};
            int[] arr2 = {1,1,1,2,2,3,3,3,4,4,5};
        getCommonElement(arr1,arr2);
//        int[] arr={1,2,3,6,7,14,11,12,15,13,17,10,8};
        prime();
        int[] arr={1,2,3,3,4,5,6,7,7,5,4,5,4,3};
        superMarket(arr,5);

    }
}
