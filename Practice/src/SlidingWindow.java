import java.util.*;

public class SlidingWindow {

    public static int maxSumSubarray(int[] arr,int k){
        int sum=0;
        int start=0,end=0;
        int tempSum=0;
//        for(int i=0;i<arr.length;i++){
//            if(i<k){
//                sum+=arr[i];
//                tempSum=sum;
//            }
//            else{
//                tempSum-=arr[ind];
//                tempSum+=arr[i];
//                ind++;
//                sum=Math.max(sum,tempSum);
//            }
//        }
        while(end<arr.length){
            if(end-start+1<=k){
                tempSum+=arr[end];
                end++;
            }
            else{
                tempSum-=arr[start];
                tempSum+=arr[end];
                start++;
                end++;
            }
            sum=Math.max(sum,tempSum);
            System.out.println(sum);
        }
        return sum;
    }

    public static void firstNegative(int[]arr,int k ){
        Queue<Integer> q = new LinkedList<>();
        int start=0;
        for(int i=0;i<arr.length;i++){
            if(i-start+1<=k){
                if(arr[i]<0){
                    q.add(arr[i]);
                }
            }

            else{
                if(!q.isEmpty()){
                    System.out.println(q.peek());
                }
                else {
                    System.out.println(0);
                }
                if(arr[i]<0){
                    q.add(arr[i]);
                }
                if(arr[start]==q.peek()){
                    q.remove();
                }
                start++;
            }
        }
    }

    public static int countAnagrams(String str,String ptrn){
        int i=0,j=0;
        int count=0;
        HashMap<Character,Integer> map = new HashMap<>();
        for(int c=0;c<ptrn.length();c++){
            Character ch = ptrn.charAt(c);
            if(!map.containsKey(ch))map.put(ch,1);
            else{
                map.put(ch,map.get(ch)+1);
            }
        }
        StringBuilder sb = new StringBuilder();
        while(j<=str.length()){
            if(j<ptrn.length()){
                sb.append(str.charAt(j));
            }
            else{
                HashMap<Character,Integer> temp = (HashMap<Character, Integer>) map.clone();
                int ctr=temp.size();
                for (int k = 0; k < sb.length(); k++) {
                    Character ch=sb.charAt(k);
                    if(temp.containsKey(ch)){
                        temp.put(ch,temp.get(ch)-1);
                    }
                    if(temp.get(ch)==0){
                        ctr--;
                    }
                    System.out.println(temp);
                    System.out.println(ctr);

                }
                if(ctr==0){
                    count++;
                }            System.out.println(sb);
//                System.out.println(map);
                sb.deleteCharAt(0);
                if(j<str.length())
                    sb.append(str.charAt(j));
//                System.out.println(j);
            }
            j++;

        }
        return count;
    }


    public static void maxAllSubarray(int[] arr,int k){
        int j=0,i=0;
        int max=Integer.MIN_VALUE,temp=Integer.MIN_VALUE;
        ArrayList<Integer> list = new ArrayList<>();
        Queue<Integer> q= new LinkedList<>();
        while(j<arr.length){
            max=Math.max(max,arr[j]);
            if(j-i+1<k){
              j++;

            }
            else if(j-i+1==k){
                list.add(max);
                if(max==arr[i]){
                    i++;
                    max=-1;
                    j=i;

                }
                else {
                    i++;
                    j++;
                }
            }
        }
        System.out.println((list));
    }

    public static int largestSubarrraySum(int[]arr,int sum){
        HashMap<Integer, Integer> map = new HashMap<>();
        int ans=0, K=0;
        map.put(0,-1);
        int N = arr.length;
        for(int i=0;i<N;i++){
            K+=arr[i];
            if(map.containsKey(K-sum)) {
                ans = Math.max(ans, i - map.get(K - sum));
            }
            if(!map.containsKey(K)) {
                map.put(K, i);
            }
        }
        return ans;

//        int len=0;
//        int j=0,i=0;
//        int s=0;
//        int temp=0;
//        while(j<arr.length){
//            s+=arr[j];
//            temp++;
//            if(s<sum){
//                j++;
//            }
//            if(s==sum){
//                len=Math.max(len,temp);
//                temp-=1;
//                s-=arr[i];
//                i++;
//                j++;
//            }
//            if(s>sum){
//                s=0;
//                temp=0;
//                i++;
//                j=i;
//
//            }
//
//        }
//        return len;
    }

    public static int longestKUnique(String str,int k){
        int i=0,j=0;
        int max=0;
        int len=0;
        HashMap<Character,Integer> map = new HashMap<>();
        while(j<str.length()){
            Character c =str.charAt(j);
            len++;
            map.put(c,map.getOrDefault(c,0)+1);
            System.out.println(map);
            if(map.size()==k){
                max=Math.max(max,len);
            }
            else if(map.size()>k){
                while(map.size()>k){
                    Character ch = str.charAt(i);
                    map.put(ch,map.get(ch)-1);
                    if(map.get(ch)==0){
                        map.remove(ch);
                    }
                    i++;
                    len--;

                }
            }
            j++;
        }
        return max;
    }

    public static int lengthOfLongestSubstring(String s){
        int i=0,j=0,len=0,max=-1;
        HashMap<Character,Integer> map  = new HashMap<>();

        while(j<s.length()){
            Character c = s.charAt(j);
            map.put(c,map.getOrDefault(c,0)+1);
            len++;
            if(map.size()==j-i+1){
                max=Math.max(max,len/*or map.size()*/);
            }
            else if(map.size()<j-i+1){
                while(!(map.size()==j-i+1)){
                    Character ch = s.charAt(i);
                    map.put(ch,map.get(ch)-1);
                    if(map.get(ch)==0){
                        map.remove(ch);
                    }
                    i++;
                    len--;
                }
            }
            j++;
        }
        return max;
    }

    public static int minimumWindowSubstring(String s,String t){
            int ans=Integer.MAX_VALUE,count=0,start=0,j=0;
            String substring = "";
            HashMap<Character,Integer> map = new HashMap<>();
            for(int i=0;i<t.length();i++){
                map.put(t.charAt(i),map.getOrDefault(t.charAt(i),0)+1);
            }
            count = map.size();
            while (j<s.length()){
                Character c = s.charAt(j);
                if(map.containsKey(c)){
                    map.put(c,map.get(c)-1);
                    if(map.get(c)==0){
                        count--;
                    }
                }
                while(count==0){
                    if(j-start+1<ans){
                        ans=j-start+1;
                        substring=s.substring(start,j+1);
                    }
                        ans=Math.min(j-start+1,ans);
                        Character ch = s.charAt(start);
                        if(map.containsKey(ch)){
                            map.put(ch,map.get(ch)+1);
                            if(map.get(ch)>0){
                                count++;
                            }
                        }
                        start++;

                }
                j++;
            }

        System.out.println(substring);
            return ans;
    }



    public static void main(String[] args) {
        int[] arr = {-2,1,-3,4,-1,2,1,-5,4};
//        System.out.println(maxSumSubarray(arr,4));
//        firstNegative(arr,3);
//        System.out.println(countAnagrams("aabaabaa","aaba"));
//        maxAllSubarray(arr,3);
//        System.out.println( largestSubarrraySum(new int[] {2,3,4,6,9,4 },13));
//        System.out.println(longestKUnique("aaaa",1));
//        System.out.println(lengthOfLongestSubstring("pwwkew"));
        System.out.println(minimumWindowSubstring("adobecodebanc","abc"));
    }


}
