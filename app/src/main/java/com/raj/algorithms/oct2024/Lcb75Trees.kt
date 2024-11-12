package com.raj.algorithms.oct2024

import java.util.Stack
import kotlin.math.max

fun main() {

}

class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}

//https://leetcode.com/problems/maximum-depth-of-binary-tree/submissions/
//104. Maximum Depth of Binary Tree
fun maxDepth(root: TreeNode?): Int {
    if (root == null) return 0
    else return max(maxDepth(root.left), maxDepth(root.right)) + 1
}
//https://leetcode.com/problems/same-tree/
//100. Same Tree
fun isSameTree(p: TreeNode?, q: TreeNode?): Boolean {
    if(p==null && q==null) return true
    if(p?.`val`!=q?.`val`) return false
    val stack = Stack<TreeNode>()
    stack.push(p)
    stack.push(q)
    while(stack.isNotEmpty()){
        val node1 = stack.pop()
        val node2 = stack.pop()
        if(node1.`val` != node2.`val`){
            return false
        }
        if(node1.left?.`val`!= node2.left?.`val`){
            return false
        }else{
            node1.left?.let{stack.push(it)}
            node2.left?.let{stack.push(it)}
        }
        if(node1.right?.`val`!= node2.right?.`val`){
            return false
        }else{
            node1.right?.let{stack.push(it)}
            node2.right?.let{stack.push(it)}
        }

    }
    return true
}
//https://leetcode.com/problems/invert-binary-tree/submissions/
//226. Invert Binary Tree
fun invertTree(root: TreeNode?): TreeNode? {
    if(root == null) return root
    else{
        val temp = root?.left
        root?.left = root?.right
        root?.right = temp
        invertTree(root?.left)
        invertTree(root?.right)
        return root
    }
}

//124. Binary Tree Maximum Path Sum
//https://leetcode.com/problems/binary-tree-maximum-path-sum/
