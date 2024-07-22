package com.yin.algorithm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 给你二叉树的根节点 root ，返回其节点值的 ·层序遍历· 。 （即逐层地，从左到右访问所有节点）。
 */
public class Lc102 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        Solution102 solution102 = new Solution102();
        List<List<Integer>> lists = solution102.levelOrder(root);
        System.out.println(lists);
    }
}

class Solution102 {
    public List<List<Integer>> levelOrderByMyself(TreeNode root) {
        List<List<Integer>> ret = new LinkedList<>();
        if (root == null) {
            return ret;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        List<Integer> rootNode = new LinkedList<>();
        rootNode.add(root.val);
        ret.add(rootNode);
        queue.offer(root);
        while (!queue.isEmpty()) {
            List<Integer> item = new LinkedList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                if (poll.left != null) {
                    item.add(poll.left.val);
                    queue.offer(poll.left);
                }
                if (poll.right != null) {
                    item.add(poll.right.val);
                    queue.offer(poll.right);
                }
            }
            if (!item.isEmpty()) {
                ret.add(item);
            }
        }
        return ret;
    }

    List<List<Integer>> res=new ArrayList<>();
    public List<List<Integer>> levelOrder(TreeNode root) {
        if(root==null){
            return res;
        }
        func(root,0);
        return res;
    }
    public void func(TreeNode node,int level){
        if(res.size()==level){
            res.add(new ArrayList<Integer>());
        }

        res.get(level).add(node.val);
        if(node.left!=null){
            func(node.left,level+1);
        }
        if(node.right!=null){
            func(node.right,level+1);
        }

    }
}
