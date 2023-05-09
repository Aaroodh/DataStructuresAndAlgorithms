import java.util.*;
import java.io.*;

public class DynamicProgramming {

    public static int fibonacciMemoized(int n,int[] qb){
        if (n == 0 || n == 1) {
            return n;
        }
        if(qb[n]!=0){
            return qb[n];
        }
        qb[n]=fibonacciMemoized(n-1,qb)+fibonacciMemoized(n-2,qb);
//        System.out.println(Arrays.toString(qb));
        return qb[n];
    }
    public static int fibonacciTabulation(int n){
        int[] dp =new int[n+1];
        if(n<1)return 0;
        dp[0]=0;
        dp[1]=1;
        for(int i=2;i<=n;i++){
            dp[i]=dp[i-1]+dp[i-2];
        }
        System.out.println(Arrays.toString(dp));
        return dp[n];
    }
    //You are climbing a staircase. It takes n steps to reach the top.
    //Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
    public static int climbStairsTabulation(int n) {
        int[] dp = new int[n+1];
        dp[0]=1;
        for(int i=1;i<=n;i++){
            if(i>1){
                dp[i]=dp[i-1]+dp[i-2];
            }
            else{
                dp[i]=dp[i-1];
            }
        }
        return dp[n];
    }
    public static int climbStairsMemoized(int n ,int[] dp){
        if(n==0){
            return 1;
        }
        if(n<0){
            return 0;
        }
        int ans=climbStairsMemoized(n-1,dp)+climbStairsMemoized(n-2,dp);
        dp[n]=ans;
        return dp[n];
    }


    /* 0/1 KNAPSACK */


    public static int knapsackMemoized(int[] wt,int[] val,int W,int n,int[][] dp){
        int maxProfit=0;
        if(W==0||n==0){
            return 0;
        }
        if(dp[n][W]!=-1){
            return dp[n][W];
        }
        if(wt[n-1]<=W){
            maxProfit+= Math.max(val[n-1]+knapsackMemoized(wt,val,W-wt[n-1],n-1,dp),knapsackMemoized(wt,val,W,n-1,dp));
        }
        else{
            maxProfit+= knapsackMemoized(wt,val,W,n-1,dp);
        }
        dp[n][W]=maxProfit;
        return maxProfit; //or return dp[n][W];
    }
    public static int knapsackTabulation(int[] wt,int[] val,int W,int n){
        int[][] dp = new int[n+1][W+1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= W; j++) {
                if(i==0||j==0){
                    dp[i][j]=0;
                }else{
                dp[i][j]=-1;
                }
            }

        }
        for (int i=1;i<n+1;i++){
            for(int j=1;j<W+1;j++){
                if(wt[i-1]<=j){
                    dp[i][j]=Math.max(val[i-1]+dp[i-1][j-wt[i-1]],dp[i-1][j]);
                }
                else{
                    dp[i][j]=dp[i-1][j];
                }
            }
        }
        return dp[n][W];
    }

    public static boolean subsetSumTalulation(int[] arr,int sum){
        int n=arr.length;
        List<Integer> ans = new ArrayList<>();
            //Initialization
        boolean[][] dp = new boolean[n+1][sum+1];
        for(int row=0;row<n+1;row++){
            dp[row][0]=true;
        }
        for(int col=1;col<sum+1;col++){
            dp[0][col]=false;
        }
        for (int i=1;i<n+1;i++){
            for(int j=1;j<sum+1;j++){
                if(arr[i-1]<=j){
                    dp[i][j]=(dp[i-1][j-arr[i-1]]||dp[i-1][j]);
                    ans.add(arr[i-1]);
                }
                else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[n][sum];
    }

    public static boolean subsetSumRecursion(int[] arr,int sum,int n,boolean[][] dp){
        if(dp[n][sum]!=false){
            return dp[n][sum];
        }
        if(sum==0){
            return true;
        }
        if(n==0){
            return false;
        }
        if(arr[n-1]<=sum){
            dp[n][sum]= subsetSumRecursion(arr,sum-arr[n-1],n-1,dp)||subsetSumRecursion(arr,sum,n-1,dp);
        }
        else{
            dp[n][sum]= subsetSumRecursion(arr,sum,n-1,dp);
        }
        return dp[n][sum];
    }

    public static boolean canPartition(int[] nums) {
        int sum=0;
        for(int ele:nums){
            sum+=ele;
        }
        if(sum%2!=0){
            return false;
        }
        else{
            sum=sum/2;
            return subsetSumTalulation(nums,sum);
        }
    }

    public static int countSubset(int[] nums,int sum){
        int n=nums.length;
        int[][] dp = new int[n+1][sum+1];
        for (int row = 0; row<n+1; row++) {
            dp[row][0]=1;

        }
        for (int col = 1; col <sum+1; col++) {
            dp[0][col]=0;
        }
        for(int i=1;i<n+1;i++){
            for (int j = 1; j <sum+1; j++) {
                if(nums[i-1]<=j){
                    dp[i][j]=dp[i-1][j-nums[i-1]]+dp[i-1][j];
                }
                else{
                    dp[i][j]=dp[i-1][j];
                }
            }
        }
        return dp[n][sum];

    }

    public static int targetSum(int[] arr,int dif){
        int sum=0;
        for(int ele:arr){
            sum+=ele;
        }
        int s1=(sum+dif)/2;

        int[][] dp = new int[arr.length+1][s1+1];
        for(int i=0;i< arr.length+1;i++){
            dp[i][0]=1;
        }
        for(int col=1;col< s1+1;col++){
            dp[0][col]=0;
        }

        for(int i=1;i< arr.length+1;i++){
            for(int j=1;j<s1+1;j++){
                if(arr[i-1]<=j){
                    dp[i][j]=dp[i-1][j-arr[i-1]]+dp[i-1][j];
                }
                else{
                    dp[i][j]=dp[i-1][j];
                }
            }
        }
        return dp[arr.length][s1];
    }

    /*Unbounded KNAPSACK*/
    
    public static int unboundedKnapSack(int[] wt,int[] val,int W){
        int n=wt.length;
        int[][] dp = new int[n+1][W+1];
        for(int i=0;i<n+1;i++){
            for(int j=0;j<W+1;j++){
                dp[i][j]=0;
            }
        }

        for(int i=1;i<n+1;i++){
            for(int j=1;j<W+1;j++){
                if(wt[i-1]<=j){
                    dp[i][j]=Math.max(val[i-1]+dp[i][j-wt[i-1]],dp[i-1][j]);
                }
                else{
                    dp[i][j]=dp[i-1][j-1];
                }
            }
        }
        return dp[n][W];
    }


    public static int rodCutting(int[] price,int m){
        int[][] dp = new int[price.length+1][m+1];
        for(int i=0;i<price.length+1;i++){
            for(int j=0;j<m+1;j++){
                dp[i][j]=0;
            }
        }
        int[] length=new int[m];
        for(int i=0;i<m;i++){
            length[i]=i+1;
        }

        for(int i=1;i<price.length+1;i++){
            for(int j=1;j<m+1;j++){
                if(length[i-1]<=j){
                    dp[i][j]=Math.max(price[i-1]+dp[i][j-length[i-1]],dp[i-1][j]);
                }
                else{
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        return dp[price.length][m];
    }

    public static int coinExchange(int[] coins,int sum){
        int[][] dp = new int[coins.length+1][sum+1];
        for (int i = 1; i < sum+1; i++) {
            dp[0][i]=0;
        }
        for (int i = 0; i < coins.length + 1; i++) {
            dp[i][0]=1;
        }

        for (int i = 1; i <coins.length+1 ; i++) {
            for (int j = 1; j <sum+1 ; j++) {
                if(coins[i-1]<=j){
                    dp[i][j]= dp[i][j-coins[i-1]]+dp[i-1][j];
                }
                else{
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        return dp[coins.length][sum];

    }
    public static int coinChangeII(int[] coins, int amount){
        int n = coins.length;
        int dp[][] = new int[n+1][amount+1];
        for(int i=0;i<amount+1;i++){
            dp[0][i]=Integer.MAX_VALUE -1;
        }
        for (int i = 1; i < n+1; i++) {
            dp[i][0] = 0;
        }
        for (int j = 1; j<amount+1; j++) {
            for (int i = 0; i <n ; i++){
                if(j%coins[i]==0){
                    dp[1][j]=j/coins[i];
                }
                else{
                    dp[1][j]=Integer.MAX_VALUE-1;
                }
            }
        }

        for (int i = 2; i <n+1 ; i++) {
            for (int j = 1; j <amount+1 ; j++) {
                if(coins[i-1]<=j){
                    dp[i][j]=Math.min(1+dp[i][j-coins[i-1]],dp[i-1][j]);
                }
                else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        System.out.println(dp[n][amount]);
        return dp[n][amount];
    }

    /*LONGEST COMMON SUBSEQUENCE*/


    public static int longestCommonSubsequenceRecursive(String a,String b,int n,int m){
        if(n<0||m<0)return 0;
        if(a.charAt(n)==b.charAt(m)){
            return 1+longestCommonSubsequenceRecursive(a,b,n-1,m-1);
        }
        else{
          return Math.max(longestCommonSubsequenceRecursive(a,b,n,m-1),longestCommonSubsequenceRecursive(a,b,n-1,m));

        }
    }

    public static int lcsMemoized(String a,String b,int n,int m,int[][]dp){

        if(n<0||m<0)return 0;
        if(dp[n][m]!=0){
            return dp[n][m];
        }
        if(a.charAt(n)==b.charAt(m)){
            dp[n][m] = 1+lcsMemoized(a,b,n-1,m-1,dp);
        }
        else{
            dp[n][m]=Math.max(lcsMemoized(a,b,n-1,m,dp),lcsMemoized(a,b,n,m-1,dp));
        }
        return dp[n][m];
    }

    public static int lcsTabulation(String a,String b){
        int n=a.length();
        int m = b.length();
        int[][] dp = new int[n+1][m+1];
//        for(int i =1;i<n+1;i++){
//            dp[i][0]=0;
//        }
//        for(int j=1;j<m+1;j++){
//            dp[0][j]=0;
//        }
        for(int[] rows:dp){
            Arrays.fill(rows,0);
        }
        for(int i=1;i<n+1;i++){
            for(int j=1;j<m+1;j++){
               if(a.charAt(i-1)==b.charAt(j-1)){
                   dp[i][j] = 1+dp[i-1][j-1];
               }
               else{
                   dp[i][j] = Math.max(dp[i][j-1],dp[i-1][j]);
               }
            }
        }
        return dp[n][m];
    }

    public static int LongestCommonSubstring(String a , String b){
        int n = a.length();
        int m = b.length();
        int maxLen=0;
        int[][] dp = new int[n+1][m+1];
        for(int[] row:dp){
            Arrays.fill(row,0);
        }
        for(int i=1;i<n+1;i++){
            for(int j=1;j<m+1;j++){
                if(a.charAt(i-1)==b.charAt(j-1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                }
                if(dp[i][j]>maxLen){
                    maxLen=dp[i][j];
                }
            }
        }
        return maxLen;
    }

    public static String printLCS(String a, String b){
        int n=a.length();
        int m=b.length();
        int[][] dp = new int[n+1][m+1];
        for (int[] row :dp) {
            Arrays.fill(row,0);
        }
        StringBuilder str=new StringBuilder("");
        for(int i=1;i<n+1;i++){
            for(int j=1;j<m+1;j++){
                if(a.charAt(i-1)==b.charAt(j-1)){
                    dp[i][j]=1+dp[i-1][j-1];
                }
                else{
                    dp[i][j]=Math.max(dp[i-1][j],dp[i][j-1]);
                }
            }
        }
        int row=n;
        int col=m;
        while(row>0 && col>0){
            if(a.charAt(row-1)==b.charAt(col-1)){
                str.append(a.charAt(row-1));
                row--;
                col--;
            }
            else{
                if(dp[row-1][col]>dp[row][col-1]){
                    row--;
                }
                else{
                    col--;
                }

            }
        }
         str.reverse();
         String str1=str.toString();
         return str1;
    }

    public static int shortestCommnSupersequence(String a,String b){
        int l1=a.length();
        int l2=b.length();
        return l1+l2-lcsTabulation(a,b);
    }


    public static void insertionsNdeletions(String original,String target){
        int lcs=lcsTabulation(original,target);
        int ins=original.length()-lcs;
        int del=target.length() - lcs;
        System.out.println("INSERTIONS:"+ ins+" DELETIONS:"+del);
    }

    public static int longestPalindromicSubsequence(String str){
        String rev="";
        for(int i=str.length()-1;i>=0;i--){
            rev+=str.charAt(i);
        }
//        System.out.println(str);
//        System.out.println(rev);
        return lcsTabulation(str,rev);
    }

    public static void printSCS(String a ,String b){
        int n=a.length();
        int m = b.length();
        int[][] dp = new int[n+1][m+1];
        for(int[] rows:dp){
            Arrays.fill(rows,0);
        }
        for(int i=1;i<n+1;i++){
            for(int j=1;j<m+1;j++){
                if(a.charAt(i-1)==b.charAt(j-1)){
                    dp[i][j] = 1+dp[i-1][j-1];
                }
                else{
                    dp[i][j] = Math.max(dp[i][j-1],dp[i-1][j]);
                }
            }
        }
        int row=n;
        int col=m;
        StringBuilder str = new StringBuilder();

        while(row>0&&col>0){
            if(a.charAt(row-1)==b.charAt(col-1)){
                str.append(a.charAt(row-1));
                row--;
                col--;
            }
            else{
                if(dp[row-1][col]>dp[row][col-1]){
                    str.append(a.charAt(row-1));
                    row--;
                }
                else{
                    str.append(b.charAt(col-1));
                    col--;
                }
            }
        }
        while(row>0){
            str.append(a.charAt(row-1));
            row--;
        }

        while(col>0){
            str.append(b.charAt(col-1));
            col--;
        }
        String ans= str.reverse().toString();
        System.out.println(ans);

    }


    public static int longestRepeatingSubsequence(String str){
        int n = str.length();
        int[][] dp =new int[n+1][n+1];
        for(int[] row :dp){
            Arrays.fill(row,0);
        }
        for(int i =1;i<n+1;i++){
            for(int j=1;j<n+1;j++){
                if(str.charAt(i-1)==str.charAt(j-1)&& i!=j){
                    dp[i][j] = 1+dp[i-1][j-1];
                }
                else{
                    dp[i][j]=Math.max(dp[i-1][j],dp[i][j-1]);
                }
            }
        }
            return dp[n][n];
    }

    //MTARIX CHAIN MULTIPLICATION//

    public static int mcm(int[] arr,int i,int j){

        if(i>=j)return 0;
        int min=Integer.MAX_VALUE;
        for(int k=i;k<j;k++){
            int tempAns = mcm(arr,i,k)+mcm(arr,k+1,j)+(arr[i-1]*arr[k]*arr[j]);
            min = Math.min(min,tempAns);
        }
        return min;
    }

    public static int mcmTablulation(int[] arr,int i ,int j,int[][]dp){
        if(i>=j)return 0;
        if(dp[i][j]!=0)return dp[i][j];
        int ans =Integer.MAX_VALUE;
        for(int k=i;k<j;k++){
            int tempAns = mcmTablulation(arr,i,k,dp)+mcmTablulation(arr,k+1,j,dp)+(arr[i-1]*arr[k]*arr[j]);
            ans=Math.min(ans,tempAns);
        }
        dp[i][j]=ans;
        return dp[i][j];
    }

    public static int palindromePartitioningReursive(String str,int i , int j){
        if(i>=j)return 0;

        if(isPalindrome(str,i,j)){
            return 0;
        }
        int ans=Integer.MAX_VALUE;
        for(int k=i;k<j;k++){
            int temp = 1 + palindromePartitioningReursive(str, i, k) + palindromePartitioningReursive(str, k + 1, j);
            ans = Math.min(ans,temp);
        }
        return ans;
    }
            private static boolean isPalindrome(String str,int i ,int j ) {
                while (i <= j) {
                    if (str.charAt(i) != str.charAt(j)) return false;
                    else {
                        i++;
                        j--;
                    }
                }
                return true;
            }


    public static int palindromePartiotioningMemo(String str,int i,int j ,int[][] dp){
        if(i>=j)return 0;

        if(dp[i][j]!=0)return dp[i][j];

        if(isPalindrome(str,i,j)){
            return 0;
        }

        int ans=Integer.MAX_VALUE;
        for(int k=i;k<j;k++){
            int temp = 1+palindromePartiotioningMemo(str,i,k,dp)+palindromePartiotioningMemo(str,k+1,j,dp);
            ans = Math.min(ans,temp);
        }
        dp[i][j]=ans;
        return dp[i][j];
    }

    //MAIN FUNCTION//


        public static void main(String[] args) {

        int[] qb= new int[100];
//        System.out.println(fibonacciMemoized(15,qb));
//        System.out.println(fibonacciTabulation(5));
//        System.out.println(climbStairsTabulation(44));
//        System.out.println(climbStairsMemoized(44,qb));


            /* 0/1 KNAPSACK */


        int[] wt={1,2,4,5};
        int[] val={5,4,8,6};
        int n= wt.length;
        int W=5;
        int[][] dp = new int[n+1][W+1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= W; j++) {
                dp[i][j]=-1;
            }
        }
//        System.out.println(knapsackTabulation(wt,val,W,wt.length));

            int[] arr = {3,2,7,8,10};
            int sum=12;
            boolean[][] t=new boolean[arr.length+1][sum+1];
//            System.out.println(subsetSum(arr,sum));
//            System.out.println(subsetSumRecursion(arr,sum, arr.length,t));

//            System.out.println(canPartition(arr));

//            System.out.println(countSubset(arr,sum));

//            int[] set= {1,1,2,3};
//            int dif = 1;
//            System.out.println(targetSum(set,dif));

            /*UNBOUNDED KNAPSACK*/

//            System.out.println(unboundedKnapSack(wt,val,W));

//            int[] price= {1,5,8,9,10,17,17,20};
//            int m =8;
//            System.out.println(rodCutting(price,m));

              int[] coins = {1,2,3};
              int s=5;
//            System.out.println(coinExchange(coins,s));
//            coinChangeII(coins,s);


            /*lcs*/


            String a = "abcde";
            String b ="ace";
//            System.out.println(longestCommonSubsequenceRecursive(a,b,a.length()-1,b.length()-1));
//            int[][] lcs_dp = new int[a.length()+1][b.length()+1];
//            System.out.println(lcsMemoized(a,b,a.length()-1,b.length()-1,lcs_dp));
//            System.out.println(lcsTabulation(a,b));

//            System.out.println(LongestCommonSubstring(a,b));

//            System.out.println(printLCS(a,b));
            String str1 ="AGGTAB";
            String str2="GXTXAYB";
//            System.out.println(shortestCommnSupersequence(str1,str2));
//            printSCS(str1,str2);

            String original = "heap";
            String target = "pea";
//            insertionsNdeletions(original,target);

            String palin="agbcba";
//            System.out.println(longestPalindromicSubsequence(palin));

            String lrs = "AABEBCDD";
//            System.out.println(longestRepeatingSubsequence(lrs));

            int[] dimensions={40,20,30,10,30};
//            System.out.println(mcm(dimensions,1,dimensions.length-1));
            int[][] mcmdp=new int[1001][1001];
//            System.out.println(mcmTablulation(dimensions,1,dimensions.length-1,mcmdp));

            String str="eegiicgaeadbcfacfhifdbiehbgejcaeggcgbahfcajfhjjdgj";
//            System.out.println(palindromePartitioningReursive(str,0,str.length()-1));
//            System.out.println(palindromePartiotioningMemo(str,0,str.length()-1,new int[2001][2001]));


    }
}
