package letcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @Author: yangxixi
 * @Date: 2020/5/25 21:56
 */
public class FindMedianSortedArrays {
    //给定两个大小为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。
    //
    //请你找出这两个正序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
    //
    //你可以假设 nums1 和 nums2 不会同时为空。

    public static void main(String[] args) {
        int[] nums1 = Utils.getRandomList(100000);
        int[] nums2 = Utils.getRandomList(100000);

        Utils.printTime(() -> findMedianSortedArrays(nums1,nums2));
        Utils.printTime(() -> findMedianSortedArrays(nums2,nums1));

        Utils.printTime(() -> findMedianSortedArrays2(nums1,nums2));
        Utils.printTime(() -> findMedianSortedArrays2(nums2,nums1));


    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {

        int length1=nums1.length;
        int length2=nums2.length;
            //数组相交
            List<Integer> integers= new ArrayList<> (length1+length2);
            for (int i=0;i<length1;i++){
                integers.add (nums1[i]);
            }
            for (int i=0;i<length2;i++){
                integers.add (nums2[i]);
            }
            Collections.sort(integers);
            double j = 0;
            int size = length1+length2;
            if(size % 2 == 1){
                j = integers.get((size-1)/2);
            }else {
                //把int转成double类型，否则除以2会算错
                j = (integers.get(size/2-1) + integers.get(size/2) + 0.0)/2;
            }
            return j;
    }


    public static double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        int n = (nums1.length+nums2.length)/2;
        int[] arr = concat(nums1,nums2);
        Arrays.sort(arr);
        if (arr.length%2==0){
            return (double)(arr[n]+arr[n-1])/2;
        }else {
            return arr[n];
        }
    }
    static int[] concat(int[] a, int[] b) {
        int[] c= new int[a.length+b.length];
        System.arraycopy(a, 0, c, 0, a.length);
        System.arraycopy(b, 0, c, a.length, b.length);
        return c;
    }


}
