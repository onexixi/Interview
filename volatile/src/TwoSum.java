import ThreadTest.Thread01;

import java.util.*;

public class TwoSum {
//给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
//你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素

    public int[] twoSum(int[] nums, int target) {
        int[] data = new int[2];
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                if (nums[i] + nums[j] == target && i != j) {
                    data[0] = i;
                    data[1] = j;
                    return data;
                }
            }
        }

        return data;
    }
//给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
//如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
//您可以假设除了数字 0 之外，这两个数都不会以 0 开头。


    // Definition for singly-linked list.
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
        }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode ll=l1.next;

        return l1;
    }

    public static void main(String[] args) {
        List list=new ArrayList();
        list.forEach(c->{

        });

        Thread01 thread01 = new Thread01();
        Thread01 thread02 = new Thread01();
        Thread01 thread03 = new Thread01();
        thread01.start();
        thread02.start();

        for (int i = 1; i < 20; i++) {
            System.out.println("2222");

        }
        thread03.start();
    }
}
