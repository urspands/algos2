package com.raj.algorithms.sorting

import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashSet


private const val TAG = "Trees"

fun main(vararg arg: String) {
//    runTreeProblemFunctions()
    runPractice2()
}

data class Node(var left: Node?, var right: Node?, var value: Int)
class DoubleLinkedListNode() {
    var last: Node? = null
    var first: Node? = null

}

fun runPractice2() {
    val node = generateBST()
    btLevelOrderTraversalPractice2(node)
    zigZagLevelOrderTravelPractice2(node)
    val bstNode = generateBSTPractice2(arrayListOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11), 0, 10)
    btLevelOrderTraversalPractice2(bstNode)
    val result = mutableListOf<Int>()
    preOrderTraversalPractice2(node, result)
    println("preOrderTraversalv2::$result")
    rightMostNodeInTheLevelPractice2(node)
    pathSumExistsPractice2(node, 29)
    pathSumExistsAndPathPractice2(node, 29)
    isValuesInTreeUniquePractice2(node)
//    convertBSTToCircularDoubleLinkedListPractice2(node)
    isBSTPractice2(node)
    moveZerosToEnd(mutableListOf(0,0,5,0,2,10))

}

fun runTreeProblemFunctions() {
    val node = generateBST()
    levelOrderTraversal(node)
    btLevelOrderTraversalPractice2(node)
    zigZagLevelOrderTravelPractice2(node)
    val bstNode = generateBSTPractice2(arrayListOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11), 0, 10)
    btLevelOrderTraversalPractice2(bstNode)

//    val array = ArrayList<Int>()
//    preOrderTraversal(node, array)
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
//    testheightofKaryTree()
}

fun btLevelOrderTraversalPractice2(node: Node?): ArrayList<ArrayList<Int>> {
    val retVal = ArrayList<ArrayList<Int>>()

    node?.let {
        val queue: Queue<Node> = LinkedList<Node>()
        queue.add(it)
        var levelCount = 0
        while (!queue.isEmpty()) {
            levelCount++
            val levelValues = ArrayList<Int>()
            val qSize = queue.size
            for (i in 0 until qSize) {
                val currNode = queue.remove()
                currNode.left?.let { leftNode ->
                    queue.add(leftNode)
                }
                currNode.right?.let { rightNode ->
                    queue.add(rightNode)
                }
                levelValues.add(currNode.value)
            }
            retVal.add(levelValues)
        }
        println("btLevelOrderTraversalPractice2:: levelCount-> $levelCount")
        println("btLevelOrderTraversalPractice2:: travelsal-> ${retVal.toString()}")
        return retVal
    } ?: return retVal
}

fun zigZagLevelOrderTravelPractice2(node: Node?): ArrayList<ArrayList<Int>> {
    val retVal = ArrayList<ArrayList<Int>>()
    node?.let { node ->
        val queue: Queue<Node> = LinkedList<Node>()
        queue.add(node)
        var leftToRight = true
        while (queue.isNotEmpty()) {
            val levelValues = ArrayList<Int>()
            var size = queue.size
            while (size > 0) {
                val currNode = queue.remove()
                currNode.left?.let { queue.add(it) }
                currNode.right?.let { queue.add(it) }
                levelValues.add(currNode.value)
                size--
            }
            if (!leftToRight) {
                levelValues.reverse()
            }
            leftToRight = !leftToRight
            retVal.add(levelValues)

        }
        println("zigZagLevelOrderTravelPractice2:: traversal-> ${retVal.toString()}")
        return retVal
    } ?: return retVal
}

fun generateBSTPractice2(list: ArrayList<Int>, start: Int, end: Int): Node? {
    if (start > end) {
        return null
    }
    val mid = (start + end) / 2
    val node = Node(value = list[mid], left = null, right = null)
    node.left = generateBSTPractice2(list, start, mid - 1)
    node.right = generateBSTPractice2(list, mid + 1, end)
    return node
}

fun preOrderTraversalPractice2(node: Node?, result: MutableList<Int>) {
    if (node == null) {
        return
    }
    result.add(node.value)
    preOrderTraversalPractice2(node.left, result)
    preOrderTraversalPractice2(node.right, result)
}

fun rightMostNodeInTheLevelPractice2(node: Node?): MutableList<Int> {
    val result = mutableListOf<Int>()
    node?.let { root ->
        val queue: Queue<Node> = LinkedList()
        queue.add(root)
        while (queue.isNotEmpty()) {
            var size = queue.size
            result.add(queue.last().value)
            while (size > 0) {
                val currNode = queue.remove()
                currNode.left?.let { queue.add(it) }
                currNode.right?.let { queue.add(it) }
                size--
            }
        }
        println("rightMostNodeInTheLevelPractice2::$result")
        return result
    } ?: return result

}

fun pathSumExistsPractice2(node: Node?, sum: Int) {
    val result = arrayOf(false)
    pathSumExistsHelperPractice2(node, sum, 0, result)
    println("pathSumExistsPractice2::${result[0]}")
}

fun pathSumExistsHelperPractice2(
    node: Node?,
    targetSum: Int,
    currSum: Int,
    result: Array<Boolean>
) {
    if (node == null) {
        return
    }
    val newSum = currSum + node.value
    if (node.left == null && node.right == null) {
        result[0] = newSum == targetSum
    }
    if (newSum > targetSum) {
        return
    }
    pathSumExistsHelperPractice2(node.left, targetSum, newSum, result)
    if (!result[0])
        pathSumExistsHelperPractice2(node.right, targetSum, newSum, result)
}


fun pathSumExistsAndPathPractice2(node: Node?, sum: Int) {
    val found = arrayOf(false)
    val result = mutableListOf<MutableList<Int>>()
    pathSumExistsAndPathHelperPractice2(
        node,
        targetSum = sum,
        currSum = 0,
        found,
        mutableListOf<Int>(),
        result
    )
    println("pathSumExistsAndPathPractice2:: ${result.toTypedArray().contentDeepToString()}")
}

fun pathSumExistsAndPathHelperPractice2(
    node: Node?,
    targetSum: Int,
    currSum: Int,
    found: Array<Boolean>, currPath: MutableList<Int>,
    result: MutableList<MutableList<Int>>
) {
    if (node == null) {
        return
    }
    val newSum = currSum + node.value
    currPath.add(node.value)
    if (node.right == null && node.left == null) {
        found[0] = newSum == targetSum
        if (found[0]) {
            result.add(currPath.toMutableList())
        }
    }
    if (newSum > targetSum) {
        return
    }
    pathSumExistsAndPathHelperPractice2(node.left, targetSum, newSum, found, currPath, result)
    if (!found[0]) {
        pathSumExistsAndPathHelperPractice2(node.right, targetSum, newSum, found, currPath, result)
        currPath.removeLast()
    }

}


// Given an array of integers, move all 0's to the end
// Input: [0,0,5,0,2,10]
// Output: [5,2,10,0,0,0]


fun moveZerosToEnd(input:MutableList<Int>){
    var insertPointer=0
    var idx =0
    while(idx<input.size){
        //   while(idx<input.size && input[idx]==0 ){
        //       idx++
        //   }
        if(input[idx]!=0){
            if(idx<input.size){
                swap(input, insertPointer, idx)
                insertPointer++
            }

        }
        idx++
    }
    println(input)
}
fun swap(input:MutableList<Int>, pos1:Int, pos2:Int){
    println("swap ::$pos1:: $pos2")
    var temp = input[pos1]
    input[pos1] =input[pos2]
    input[pos2]=temp
}

fun isValuesInTreeUniquePractice2(node: Node?) {
    val hashSet = HashSet<Int>()
    println("isValuesInTreeUniquePractice2::${isValuesInTreeUniqueHelperPractice2(node, hashSet)}")
}

fun isValuesInTreeUniqueHelperPractice2(node: Node?, hashSet: HashSet<Int>): Boolean {
    if (node == null) {
        return true
    }
    if (hashSet.contains(node.value)) {
        return false
    } else {
        hashSet.add(node.value)
    }
    return isValuesInTreeUniqueHelperPractice2(
        node.left,
        hashSet
    ) && isValuesInTreeUniqueHelperPractice2(node.right, hashSet)
}

//stackovererror
fun convertBSTToCircularDoubleLinkedListPractice2(node: Node?) {
    var head: Array<Node?> = Array<Node?>(1) { null }
    var previousNode: Array<Node?> = Array<Node?>(1) { null }

    convertBSTToCircularDoubleLinkedListHelperPractice2(node, previousNode, head)
    var currNode: Node? = head[0]
    while (currNode != null) {
        println("DLL::${currNode.value}")
        currNode = currNode.right
    }
    previousNode[0]?.right = head[0]
    head[0]?.left = previousNode[0]

    currNode = head[0]
    do {
        println("circular DLL::${currNode?.value}")
        currNode = currNode?.right
    } while (currNode != head[0])
}

fun convertBSTToCircularDoubleLinkedListHelperPractice2(
    root: Node?, previousNode: Array<Node?>, head: Array<Node?>
) {
    if (root == null) {
        return
    }
    convertBSTToCircularDoubleLinkedListHelperPractice2(root.left, previousNode, head)
    if (previousNode[0] == null) {
        head[0] = root
    } else {
        root.left = previousNode[0]
        previousNode[0]!!.right = root
    }
    previousNode[0] = root

    convertBSTToCircularDoubleLinkedListHelperPractice2(root.right, previousNode, head)
}


fun isBSTPractice2(node: Node?) {
    var previousNode = Array<Node?>(1) { null }
    var isBST = arrayOf(true)
    fun isBSTHelper(node: Node?) {
        if (node == null) {
            return
        }
        isBSTHelper(node?.left)
        if (previousNode[0] != null) {
            if (previousNode[0]?.value!! > node?.value!!) {
                isBST[0]= false
            }
        }
        previousNode[0] = node
        if (isBST[0])
            isBSTHelper(node?.right)
    }
    isBSTHelper(node)
    println("isBSTPractice2::${isBST[0]}")
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
    System.out.println("levelOrderTraversal: ${result.toArray().contentToString()}")
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
    System.out.println("levelOrderTraversal: ${result.toArray().contentDeepToString()}")
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
    System.out.println("levelOrderZigZagTraversal: ${result.toArray().contentDeepToString()}")
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
    System.out.println("rightMostValueAtEachLevel: ${result.toArray().contentDeepToString()}")
}

fun rightMostValueAtEachLevelRecursive(root: Node?) {
    var array = ArrayList<Int>()
    rightMostValueAtEachLevelRecur(root, arrayOf(0), 0, array)
    System.out.println(
        "rightMostValueAtEachLevelRecursive: ${
            array.toArray().contentDeepToString()
        }"
    )
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
    System.out.println("leftMostValueAtEachLevel: ${result.toArray().contentDeepToString()}")
}

fun leftMostValueAtEachLevelRecursive(root: Node?) {
    var array = ArrayList<Int>()
    leftMostValueAtEachLevelRecur(root, arrayOf(0), 0, array)
    System.out.println(
        "leftMostValueAtEachLevelRecursive: ${
            array.toArray().contentDeepToString()
        }"
    )
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
    System.out.println("pathSum: Exists::${array[0]}")
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
    System.out.println(
        "pathSum: Exists::${array[0]} || Path::${
            result.toArray().contentToString()
        }"
    )
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
    println("isUniValue::${isUniValueHelper(node, array)}")
    System.out.println("isUniValue: count ${array[0]}")
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
        System.out.println("printCircularDoublyLinkedList: ${result.toArray().contentToString()}")
    }
}

fun isItBST(node: Node?) {
    val result = arrayOf(true)
    val previousNode: Array<Node> = arrayOf()
    isItBSTHelper(node, result, previousNode)
    System.out.println("isItBST: ${result[0]}")
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
    System.out.println("testheightofKaryTree: ${maxSize[0]}")
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
