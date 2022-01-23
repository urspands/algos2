package com.raj.algorithms.sorting

import android.util.Log
import java.util.*
import kotlin.collections.ArrayList

object Trees {
    private const val TAG = "Trees"

    data class Node(var left: Node?, var right: Node?, var value: Int)
    class DoubleLinkedListNode() {
        var last: Node? = null
        var first: Node? = null

    }

    fun runTreeProblemFunctions() {
        val node = generateBST()
        levelOrderTraversal(node)
        val array = ArrayList<Int>()
//        preOrderTraversal(node, array)
//        Log.d(
//            TAG,
//            "runTreeProblemFunctions: preOrderTraversal::${array.toArray().contentToString()}"
//        )
//        inOrderTraversal(node, array)
//        Log.d(
//            TAG,
//            "runTreeProblemFunctions: inOrderTraversal::${array.toArray().contentToString()}"
//        )
//        postOrderTraversal(node, array)
//        Log.d(
//            TAG,
//            "runTreeProblemFunctions: postOrderTraversal::${array.toArray().contentToString()}"
//        )
//        levelOrderZigZagTraversal(node)
//        rightMostValueAtEachLevel(node)
//        rightMostValueAtEachLevelRecursive(node)
//        leftMostValueAtEachLevel(node)
//        leftMostValueAtEachLevelRecursive(node)
//        pathSumExists(node, 29)
//        pathSum(node, 10)
//        isUniValue(generateBST(arrayOf(1, 5, 5, 5, 5, 5)))
//        convertBSTToCircularDoublyLinkedList(generateBST())
//        isItBST(generateBST())
        testheightofKaryTree()
    }

    fun generateBST(): Node? {
        val array = arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
        val node = _generateBST(array, 0, array.size - 1)
        return node
    }

    fun generateBST(array: Array<Int>): Node? {
//        val array = arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
        val node = _generateBST(array, 0, array.size - 1)
        return node
    }

    private fun _generateBST(array: Array<Int>, start: Int, end: Int): Node? {
        if (start > end) {
            return null
        }
        val mid = (start + end) / 2
        val node = Node(value = array[mid], left = null, right = null)
        node.left = _generateBST(array, start, mid - 1)
        node.right = _generateBST(array, mid + 1, end)
        return node
    }

    fun levelTraversal(root: Node?) {
        if (root == null) {
            return
        }
        val queue: Queue<Node> = LinkedList<Node>()
        queue.add(root)
        val result = ArrayList<Int>()
        while (queue.isNotEmpty()) {
            val node = queue.remove();
            result.add(node.value)
            node.left?.let { queue.add(it) }
            node.right?.let { queue.add(it) }
        }
        Log.d(TAG, "levelOrderTraversal: ${result.toArray().contentToString()}")
    }

    fun levelOrderTraversal(root: Node?) {
        if (root == null) {
            return
        }
        val queue: Queue<Node> = LinkedList<Node>()
        queue.add(root)
        val result = ArrayList<ArrayList<Int>>()
        while (queue.isNotEmpty()) {
            val level = ArrayList<Int>()
            var size = queue.size
            while (size > 0) {
                val node = queue.remove();
                level.add(node.value)
                node.left?.let { queue.add(it) }
                node.right?.let { queue.add(it) }
                size--
            }
            result.add(level)
        }
        Log.d(TAG, "levelOrderTraversal: ${result.toArray().contentDeepToString()}")
    }

    fun levelOrderZigZagTraversal(root: Node?) {
        if (root == null) {
            return
        }
        val queue: Queue<Node> = LinkedList<Node>()
        queue.add(root)
        val result = ArrayList<ArrayList<Int>>()
        var ltr = true
        while (queue.isNotEmpty()) {
            val level = ArrayList<Int>()
            var size = queue.size
            while (size > 0) {
                val node = queue.remove();
                level.add(node.value)
                node.left?.let { queue.add(it) }
                node.right?.let { queue.add(it) }
                size--
            }
            if (!ltr) {
                level.reverse()
            }
            ltr = !ltr
            result.add(level)
        }
        Log.d(TAG, "levelOrderZigZagTraversal: ${result.toArray().contentDeepToString()}")
    }

    fun preOrderTraversal(root: Node?, result: ArrayList<Int>) {
        if (root == null) {
            return
        }
        result.add(root.value)
        preOrderTraversal(root.left, result)
        preOrderTraversal(root.right, result)
    }

    fun inOrderTraversal(root: Node?, result: ArrayList<Int>) {
        if (root == null) {
            return
        }

        inOrderTraversal(root.left, result)
        result.add(root.value)
        inOrderTraversal(root.right, result)
    }

    fun postOrderTraversal(root: Node?, result: ArrayList<Int>) {
        if (root == null) {
            return
        }

        postOrderTraversal(root.left, result)
        postOrderTraversal(root.right, result)
        result.add(root.value)
    }

    fun rightMostValueAtEachLevel(root: Node?) {
        if (root == null) {
            return
        }
        val queue: Queue<Node> = LinkedList<Node>()
        queue.add(root)
        val result = ArrayList<ArrayList<Int>>()
        while (queue.isNotEmpty()) {
            val level = ArrayList<Int>()
            var size = queue.size
            level.add(queue.last().value)
            while (size > 0) {
                val node = queue.remove();
                node.left?.let { queue.add(it) }
                node.right?.let { queue.add(it) }
                size--
            }
            result.add(level)
        }
        Log.d(TAG, "rightMostValueAtEachLevel: ${result.toArray().contentDeepToString()}")
    }

    fun rightMostValueAtEachLevelRecursive(root: Node?) {
        var array = ArrayList<Int>()
        rightMostValueAtEachLevelRecur(root, arrayOf(0), 0, array)
        Log.d(TAG, "rightMostValueAtEachLevelRecursive: ${array.toArray().contentDeepToString()}")
    }

    private fun rightMostValueAtEachLevelRecur(
        root: Node?,
        maxLevel: Array<Int>,
        currentLevel: Int,
        result: ArrayList<Int>
    ) {
        if (root == null) {
            return
        }
        var newCurrentLevel = currentLevel + 1

        if (newCurrentLevel > maxLevel[0]) {
            result.add(root.value)
            maxLevel[0] = newCurrentLevel
        }
        rightMostValueAtEachLevelRecur(root.right, maxLevel, newCurrentLevel, result)
        rightMostValueAtEachLevelRecur(root.left, maxLevel, newCurrentLevel, result)

    }

    fun leftMostValueAtEachLevel(root: Node?) {
        if (root == null) {
            return
        }
        val queue: Queue<Node> = LinkedList<Node>()
        queue.add(root)
        val result = ArrayList<ArrayList<Int>>()
        while (queue.isNotEmpty()) {
            val level = ArrayList<Int>()
            var size = queue.size
            level.add(queue.peek().value)
            while (size > 0) {
                val node = queue.remove();
                node.left?.let { queue.add(it) }
                node.right?.let { queue.add(it) }
                size--
            }
            result.add(level)
        }
        Log.d(TAG, "leftMostValueAtEachLevel: ${result.toArray().contentDeepToString()}")
    }

    fun leftMostValueAtEachLevelRecursive(root: Node?) {
        var array = ArrayList<Int>()
        leftMostValueAtEachLevelRecur(root, arrayOf(0), 0, array)
        Log.d(TAG, "leftMostValueAtEachLevelRecursive: ${array.toArray().contentDeepToString()}")
    }

    private fun leftMostValueAtEachLevelRecur(
        root: Node?,
        maxLevel: Array<Int>,
        currentLevel: Int,
        result: ArrayList<Int>
    ) {
        if (root == null) {
            return
        }
        var newCurrentLevel = currentLevel + 1

        if (newCurrentLevel > maxLevel[0]) {
            result.add(root.value)
            maxLevel[0] = newCurrentLevel
        }
        leftMostValueAtEachLevelRecur(root.left, maxLevel, newCurrentLevel, result)
        leftMostValueAtEachLevelRecur(root.right, maxLevel, newCurrentLevel, result)

    }

    fun pathSumExists(root: Node?, sum: Int) {
        val array = arrayOf(false)
        pathSumExistsHelper(root, sum, 0, array)
        Log.d(TAG, "pathSum: Exists::${array[0]}")
    }

    private fun pathSumExistsHelper(
        root: Node?,
        targetSum: Int,
        currentSum: Int,
        found: Array<Boolean>
    ) {
        if (root == null) {
            return
        }
        val newSum = currentSum + root.value
        if (root.left == null && root.right == null) {
            found[0] = newSum == targetSum
        }
        if (newSum > targetSum) {
            return
        }
        pathSumExistsHelper(root.left, targetSum, newSum, found)
        if (!found[0])
            pathSumExistsHelper(root.right, targetSum, newSum, found)
    }

    fun pathSum(root: Node?, sum: Int) {
        val array = arrayOf(false)
        val result = ArrayList<ArrayList<Int>>()
        pathSumHelper(root, sum, 0, array, ArrayList<Int>(), result)
        Log.d(TAG, "pathSum: Exists::${array[0]} || Path::${result.toArray().contentToString()}")
    }

    private fun pathSumHelper(
        root: Node?,
        targetSum: Int,
        currentSum: Int,
        found: Array<Boolean>,
        currentPath: ArrayList<Int>,
        result: ArrayList<ArrayList<Int>>
    ) {
        if (root == null) {
            return
        }
        val newSum = currentSum + root.value
        currentPath.add(root.value)
        if (root.left == null && root.right == null) {
            found[0] = newSum == targetSum
            if (found[0]) {
                result.add(currentPath.clone() as java.util.ArrayList<Int>)
            }
        }
//        if (newSum > targetSum) {
//            return
//        }
        pathSumHelper(root.left, targetSum, newSum, found, currentPath, result)
        if (!found[0]) {
            pathSumHelper(root.right, targetSum, newSum, found, currentPath, result)
            currentPath.removeLast()
        }
    }

    fun isUniValue(node: Node?) {
        val array = arrayOf(0)
        isUniValueHelper(node, array)
        Log.d(TAG, "isUniValue: ${array[0]}")
    }

    fun isUniValueHelper(root: Node?, count: Array<Int>): Boolean {
        if (root == null) {
            return true
        }

        val isLeftUniValue = isUniValueHelper(root.left, count)
        val isRightUniValue = isUniValueHelper(root.right, count)

        if (!isLeftUniValue || !isRightUniValue) {
            return false
        }
        if (root.left != null && root.left?.value != root.value) {
            return false
        }
        if (root.right != null && root.right?.value != root.value) {
            return false
        }

        count[0]++
        return true
    }

    // gets stackoverflow error.. need to check
    fun convertBSTToCircularDoublyLinkedList(root: Node?) {
        if (root == null) {
            return
        }
        val firstLast = DoubleLinkedListNode()
        convertBSTToCircularDoublyLinkedListHelper(root, firstLast)
        firstLast.first?.left = firstLast.last
        firstLast.last?.right = firstLast.first
        printCircularDoublyLinkedList(firstLast.first)
    }

    fun convertBSTToCircularDoublyLinkedListHelper(root: Node?, dllNode: DoubleLinkedListNode) {
        if (root == null) {
            return
        }
        convertBSTToCircularDoublyLinkedListHelper(root.left, dllNode)
        if (dllNode.first == null) {
            dllNode.first = root
        }
        val previousNode = dllNode.last
        if (previousNode != null) {
            previousNode.right = root
            root.left = previousNode
        }
        dllNode.last = root
        convertBSTToCircularDoublyLinkedListHelper(root.right, dllNode)
    }

    fun printCircularDoublyLinkedList(node: Node?) {
        if (node != null) {
            val lastNode = node.left
            val result = ArrayList<Int>()
            var currentNode = node
            while (true) {
                currentNode?.let { result.add(it.value) }
                if (currentNode?.equals(lastNode) == true) {
                    break
                }
                currentNode = currentNode?.right
            }
            Log.d(TAG, "printCircularDoublyLinkedList: ${result.toArray().contentToString()}")
        }
    }

    fun isItBST(node: Node?) {
        val result = arrayOf(true)
        val previousNode: Array<Node> = arrayOf()
        isItBSTHelper(node, result, previousNode)
        Log.d(TAG, "isItBST: ${result[0]}")
    }

    fun isItBSTHelper(node: Node?, isItBST: Array<Boolean>, previousNode: Array<Node>) {
        if (node == null) {
            return
        }
        isItBSTHelper(node.left, isItBST, previousNode)
        if (previousNode.isNotEmpty()) {
            if (previousNode[0].value > node.value) {
                isItBST[0] = false
            }
        }
        previousNode[0] = node
        if (isItBST[0]) {
            isItBSTHelper(node.right, isItBST, previousNode)
        }
    }

    class NaryNode(value: Int) {
        var childrenNodes: ArrayList<NaryNode>? = null
    }

    fun testheightofKaryTree() {
        val maxSize = arrayListOf(0)
        heightofKaryTree(getNaryNode(), 0, maxSize)
        Log.d(TAG, "testheightofKaryTree: ${maxSize[0]}")
    }

    fun getNaryNode(): NaryNode {
        val node1 = NaryNode(1)
        val node2 = NaryNode(2)
        val node3 = NaryNode(3)
        node1.childrenNodes = arrayListOf(node2, node3)


        val node4 = NaryNode(4)
        val node5 = NaryNode(5)
        val node6 = NaryNode(6)
        node2.childrenNodes = arrayListOf(node4)
//        node3.childrenNodes = arrayListOf(node5, node6)
//        val node7 = NaryNode(7)
//        val node8 = NaryNode(8)
//        val node9 = NaryNode(9)
//        node6.childrenNodes = arrayListOf(node7)
//        node7.childrenNodes = arrayListOf(node8)
//        node8.childrenNodes = arrayListOf(node9)
        return node1
    }

    fun heightofKaryTree(node: NaryNode?, count: Int, maxSize: ArrayList<Int>) {
        if (node == null) {
            return
        }
        if (node.childrenNodes == null) {
//            val newCount = count + 1
            if (count > maxSize[0]) {
                maxSize[0] = count
            }
            return
        }

        val newCount = count + 1
        if (node.childrenNodes != null) {
            for (childNode in node.childrenNodes!!) {
                heightofKaryTree(childNode, newCount, maxSize)
            }
        }
    }
}