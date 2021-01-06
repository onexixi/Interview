package letcode;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: yangxixi
 * @Date: 2020/5/28 21:08
 */
public class Subsets {

    //给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
    //说明：解集不能包含重复的子集。
    //nums = [1,2,3]

    public static void main(String[] args) {

        int[] sbc=Utils.getRandomList(23);
        System.out.println (Arrays.toString (sbc));

        long startTime = System.currentTimeMillis ();
//        System.out.println (subsets(sbc));
        subsets(sbc);
        System.out.println((System.currentTimeMillis() - startTime)   + "    ms");


        Solution solution =new Solution ();
        long startTime1 = System.currentTimeMillis ();

//        System.out.println (solution.subsets (sbc));
        solution.subsets (sbc);
        System.out.println((System.currentTimeMillis() - startTime1)   + "    ms");



    }


    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> arrayList = new ArrayList<>();
        arrayList.add (new ArrayList<>());

        for (int i=0;i<nums.length;i++){
            int all = arrayList.size();
            for (int j=0;j<all;j++){
                List<Integer> tmp = new ArrayList<>(arrayList.get(j));
                tmp.add(nums[i]);
                arrayList.add(tmp);
            }
        }
        return arrayList;
    }

    public static List<List<Integer>> subsetss(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<>());
        for (int i = 0; i < nums.length; i++) {
            int all = res.size();
            for (int j = 0; j < all; j++) {
                List<Integer> tmp = new ArrayList<>(res.get(j));
                tmp.add(nums[i]);
                res.add(tmp);
            }
        }
        return res;
    }



    static class Solution {
        public List<List<Integer>> subsets(int[] nums) {
            List<List<Integer>> res = new ArrayList<>();
            res.add(new ArrayList<>());
            recursion(nums, 0, res);
            return res;
        }

        private void recursion(int nums[], int index, List<List<Integer>> res) {
            if (index >= nums.length) {
                return;
            }
            int size = res.size();
            for (int i = 0; i < size; i++) {
                List<Integer> temp = new ArrayList<>(res.get(i));
                temp.add(nums[index]);
                res.add(temp);
            }
            recursion(nums, index + 1, res);
        }
    }
}
