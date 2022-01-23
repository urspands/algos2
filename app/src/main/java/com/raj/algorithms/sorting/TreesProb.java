package com.raj.algorithms.sorting;

import android.util.Log;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

public class TreesProb {
    public static final String TAG = "TreesProb";

    public static void testProb() {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    Solution();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "1", 1 << 26).start();
    }

    static class TreeNode {
        //int val;														// To find height of tree, value stored in nodes does not matter. So in input also we are not given this field.
        Vector<TreeNode> children = new Vector<TreeNode>(0);

        TreeNode() {
            children.clear();
        }
    }

    ;

    static HashMap<Integer, TreeNode> address = new HashMap<Integer, TreeNode>();

    static TreeNode build_tree(int[] from, int[] to) {
        int N = from.length + 1;
        address.clear();                                                // Clear the variable.
        for (int i = 1; i <= N; i++) {
            address.put(i, new TreeNode());                                // Create N nodes.
        }
        for (int i = 0; i < N - 1; i++) {
            address.get(from[i]).children.add(address.get(to[i]));        // Link the nodes. (Build the k-ary tree.)
        }
        return address.get(1);
    }

    static void Solution() throws IOException {
//        Scanner in = new Scanner(System.in);
//        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

//        int res;
//        int k;
//        k = Integer.parseInt(in.nextLine().trim());
//
//        int from_size = 0;
//        from_size = Integer.parseInt(in.nextLine().trim());

        int[] from = new int[]{1, 2, 3};
//        for (int i = 0; i < from_size; i++) {
//            int from_item;
//            from_item = Integer.parseInt(in.nextLine().trim());
//            from[i] = from_item;
//        }

//        int to_size = 0;
//        to_size = Integer.parseInt(in.nextLine().trim());

        int[] to = new int[]{4, 5, 6, 7, 8, 9};
//        for (int i = 0; i < to_size; i++) {
//            int to_item;
//            to_item = Integer.parseInt(in.nextLine().trim());
//            to[i] = to_item;
//        }

        TreeNode root = build_tree(from, to);

        int res = find_height(root);
        Log.d(TAG, "Solution: " + res);
//        bw.write(String.valueOf(res));
//        bw.newLine();
//
//        bw.close();
    }

    static int find_height(TreeNode root) {

        int[] maxSize = new int[1];
        maxSize[0] = 0;
        find_heightHelper(root, 0, maxSize);
        return maxSize[0];
    }

    static void find_heightHelper(TreeNode root, int count, int[] maxSize) {

        if (root == null) {
            return;
        }
        if (root.children == null || root.children.size() == 0) {
            if (count > maxSize[0]) {
                maxSize[0] = count;
            }
            return;
        }
        count += 1;
        Iterator children = root.children.iterator();
        if (children != null) {
            while (children.hasNext()) {
                find_heightHelper(((TreeNode) children.next()), count, maxSize);
            }
        }
    }
}
