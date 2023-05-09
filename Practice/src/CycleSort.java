import java.util.*;
public class CycleSort {
    public static List<Integer> findDuplicates(int[] nums) {
        int index =0;
        while(index<nums.length){
            int correct = nums[index]-1;
            if( nums[index]!=nums[correct]){
                swap(nums,index,correct);
            }
            else{
                index++;
            }
        }
        System.out.println(Arrays.toString(nums));
        ArrayList <Integer> ans = new ArrayList<>();
        for(int i=0;i<nums.length;i++){
            if(nums[i]-1!=i) ans.add(nums[i]);
        }
        System.out.println(ans);
        return ans;
    }
    public static int firstMissingPositive(int[] nums) {
        int index =0;
        while(index<nums.length){
            int correct = nums[index]-1;
            if( nums[index]>0 && nums[index]<=nums.length&& nums[index]!=nums[correct]){
                swap(nums,index,correct);
            }
            else{
                index++;
            }
        }
        for(int i=0;i<nums.length;i++){
            if(nums[i]-1!=i){
                return i+1;
            }
        }
        return nums.length+1;
    }

    public static void swap(int[] arr, int first, int last){
        int temp = arr[first];
        arr[first]=arr[last];
        arr[last]=temp;
    }

    public static void main(String[] args) {
        int[] arr={4,3,1,1};
        int[] nums= {3,4,-1,1,};
        findDuplicates(arr);
//            firstMissingPositive(nums);
    }

}