public class Recursion {
    public static  int numberOfSteps(int num) {
        int steps = 0;
        if (num == 0) {
            return steps;
        }
        if (num % 2 != 0) {
            num = num - 1;
            steps++;
        } else {

            num = num / 2;
            steps++;
        }
        steps = steps + numberOfSteps(num);
        return steps;
    }

    public static int steps(int meters){
        int way=0;
        if(meters==0){
            return way+=1;
        }
        if(meters<0){
            return 0;
        }
        way+=steps(meters-1)+steps(meters-2);
        return way;
    }

    public static int stepsTabulatio(int meters){
        int[] dp = new int[meters+1];
        dp[0]=1;
        for(int i=1;i<meters+1;i++) {
            if (i > 1) {
                dp[i] = dp[i - 1] + dp[i - 2];
            } else {
                dp[i] = dp[i - 1];
            }
        }
        return dp[meters];
    }
    public static int maxContigious(int[] arr){
        int maxSum=0;
        int tempSum=0;
        for(int i : arr){
            tempSum+=i;
            if(maxSum<tempSum){
                maxSum=tempSum;
            }
            if(i<0){
                tempSum=0;
            }
        }
        return maxSum;
    }
    public static void main(String[] args) {

     //   System.out.println(numberOfSteps(14));
        System.out.println(steps(4));
        System.out.println(stepsTabulatio(4));

        int[] arr={1,2,4,-5,3};
//        System.out.println(maxContigious(arr));
    }
}
