import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.util.*;
import java.util.stream.Stream;

public class App {
    public static void main(String[] args) {
//        romanToInt("MCMXCIV");
//        MyStack st = new MyStack();
//        st.push(23);
//        st.push(432);
//        st.push(213);
//        System.out.println(st.pop());
//        System.out.println(st.top());
//        System.out.println(st.empty());
//        System.out.println(checkPalindrome(2));
//        int[] arr = {3,4,5,6,1,7};
//        minimumNumber(arr);
//        prime(80);
//        findNth(6621);
//        solve(3,"AbC");
        int[] arr ={7,5,2,11,2,4,3,1,1};
        rotate(arr, 9, 2);
    }
    public static void rotate(int[] arr,int N,int K){
		K=K%N;
		reverse(arr,0,K-1);
        System.out.println(Arrays.toString(arr));
		reverse(arr, K, N-1);
        System.out.println(Arrays.toString(arr));

		reverse(arr, 0, N-1);
        System.out.println(Arrays.toString(arr));

		for(int i:arr){
		System.out.print(i+" ");

		}
	}
	public static void reverse(int[] arr,int i,int j){
		while(i<j){
			int temp=arr[i];
			arr[i]=arr[j];
			arr[j]=temp;
			i++;j--;
		}
	}
    public static String solve(int N,String s){
        StringBuilder ans = new StringBuilder("");
        for (int i=0;i<N;i++){
            int as=s.charAt(i);
            if(as>=65 && as<=90){
               ans.append('@');
            }
            else if(as>=97 && as<=122) {
                ans.append('$');
            }
        }
        return ans.toString();
    }


    public static int findNth(int n){
        int[] dp =new int[n+1];
        dp[0]=0;
        dp[1]=1;
        for(int i=2;i<n+1;i++){
            if(i%5==0){
                dp[i]=11;
            }
            else{
                dp[i]=dp[i-1]+dp[i-2];
            }
        }
        System.out.println(dp[n]);
        return dp[n];
    }
    public static int minimumNumber(int[] num){
        int min=num[0];
        for(int i=num.length-1;i>0;i--){
            for(int j=num.length-1;j>0;j--){
                if(num[j]<min){
                    min=num[j];
                    num[j]=99999;
                }
            }
            System.out.println(min);
        }
        return min;
    }

    public static int checkPalindrome(int n){
        while(true){
            int revr=reverse(n);
            if(revr==n){
                return n;
            }
            n+=revr;
        }
    }

    public static int reverse(int n){
        int rev=0;
        while (n>0){
            int r=n%10;
            rev=rev*10+r;
            n=n/10;
        }
        return rev;
    }

    public static void prime(int n){
        for(int i=1;i<=n;i++){
            int c=0;
            for(int j=2;j*j<=i;j++){
                if(i%j==0){
                    c++;
                    break;
                }
            }
            if(c!=1){
                System.out.println(i);
            }
        }
    }
    public static int romanToInt(String s) {
        Stack<Character> st = new Stack<>();
        for (int i = s.length() - 1; i >= 0; i--) {
            st.push(s.charAt(i));
        }
        int ans = 0;
        char prev = '0';
        System.out.println(st);
        while (!st.empty()) {
            char e = st.pop();
            if (e == 'I') {
                ans += 1;
            }
            if (e == 'V') {
                if (prev == 'I') {
                    ans += 3;
                } else {
                    ans += 5;
                }
            }
            if (e == 'X') {
                if (prev == 'I') {
                    ans += 8;
                } else {
                    ans += 10;
                }
            }
            if (e == 'L') {
                if (prev == 'X') {
                    ans += 30;
                } else {
                    ans += 50;
                }
            }
            if (e == 'C') {
                if (prev == 'X') {
                    ans += 80;
                } else {
                    ans += 100;
                }
            }
            if (e == 'D') {
                if (prev == 'C') {
                    ans += 300;
                } else {
                    ans += 500;
                }
            }
            if (e == 'M') {
                if (prev == 'C') {
                    ans += 800;
                } else {
                    ans += 1000;
                }
            }
            prev = e;
            System.out.println(ans);
        }
        return ans;
    }
    //Stack using two queues
    static class MyStack {
        Queue<Integer> q1, q2;

        public MyStack() {
            q1 = new LinkedList<Integer>();
            q2 = new LinkedList<Integer>();

        }

        public void push(int x) {
            q1.add(x);

        }

        public int pop() {
            int num = 0;
            while (!q1.isEmpty()) {
                if (q1.size() == 1) {
                    num = q1.remove();
                } else {
                    q2.add(q1.remove());
                }
            }
            while (!q2.isEmpty()) {
                q1.add(q2.remove());
            }

            return num;
        }

        public int top() {
            int num = 0;
            while (!q1.isEmpty()) {
                num = q1.remove();
                q2.add(num);
            }
            while (!q2.isEmpty()) {
                q1.add(q2.remove());
            }
            return num;
        }

        public boolean empty() {

            return q1.isEmpty();

        }
    }
}
