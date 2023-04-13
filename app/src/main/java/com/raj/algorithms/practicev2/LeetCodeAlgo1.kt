package com.raj.algorithms.practicev2

import java.util.*
import kotlin.math.ceil
import kotlin.math.floor
import kotlin.math.log2


fun main() {
//    val grid = arrayOf(
//        intArrayOf(0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0),
//        intArrayOf(0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0),
//        intArrayOf(0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0),
//        intArrayOf(0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0),
//        intArrayOf(0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0),
//        intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0),
//        intArrayOf(0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0),
//        intArrayOf(0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0)
//    )
//    println("${maxAreaOfIsland(grid)}")
//    val mat = arrayOf(intArrayOf(0, 0, 0), intArrayOf(0, 1, 0), intArrayOf(1, 1, 1))
//    println("${updateMatrix(mat)}")
//    println("HowAreYouDoing?".toSnakeCase())
//    println("How are You Doing?".toSnakeCase())
//    println("How are5You-10Doing?".toSnakeCase())
    println(findAnagrams("abab", "ab"))

    minimumTotal(
        arrayListOf(
            arrayListOf(2),
            arrayListOf(3, 4),
            arrayListOf(6, 5, 7),
            arrayListOf(4, 1, 8, 3)
        )
    )
    hammingWeight(2097152)
}


fun hammingWeight(m: Int): Int {
    var count = 0
    var n = m
    // if(n<=0){
    //     return count
    // }
    for (i in 0..31) {
        if (n and 1 == 1) {
            count++
        }
        n = n shr 1
    }
    return count
}


fun isPowerOfTwo(m: Int): Boolean {
    var n = m
    if (n <= 0) {
        return false
    }
//    while(n >1 && ((n and 1) == 0)){
//        n = n shr 1
//    }
//    return n==1
    return ceil(log2(m.toDouble())) == floor(log2(m.toDouble()))

}

fun minimumTotal(triangle: List<List<Int>>): Int {

    if (triangle.isEmpty()) {
        return -1
    }
    val dyTable = Array(triangle.size) { i -> IntArray(i + 1) }
    dyTable[0][0] = triangle[0][0]
    for (row in 1 until triangle.size) {
        dyTable[row][0] = dyTable[row - 1][0] + triangle[row][0]
        dyTable[row][row] = dyTable[row - 1][row - 1] + triangle[row][row]
    }

    for (row in 2 until triangle.size) {
        for (col in 1 until row) {
            dyTable[row][col] =
                minOf(dyTable[row - 1][col - 1], dyTable[row - 1][col]) + triangle[row][col]
        }
    }
    var min = Int.MAX_VALUE
    for (col in 0 until triangle[triangle.size - 1].size) {
        min = minOf(min, dyTable[triangle.size - 1][col])
    }
    println("minimumTotal:: $min")
    return min
}

/*
438. Find All Anagrams in a String
https://leetcode.com/problems/find-all-anagrams-in-a-string/
*/
fun findAnagrams(s: String, p: String): List<Int> {
    val retVal = ArrayList<Int>()
    if (p.length > s.length) {
        return retVal
    }
    val pIntArray = IntArray(26) { 0 }
    val sIntArray = IntArray(26) { 0 }

    p.forEachIndexed { i, value ->
        pIntArray[p[i] - 'a']++
        sIntArray[s[i] - 'a']++
    }
    var rightPtr = p.length - 1
    var leftPtr = 0
    while (rightPtr < s.length) {
        if (pIntArray.contentEquals(sIntArray)) {
            retVal.add(leftPtr)
        }
        rightPtr++
        if (rightPtr < s.length) {
            sIntArray[s[rightPtr] - 'a']++
            sIntArray[s[leftPtr] - 'a']--
            leftPtr++
        }

    }
    return retVal
}

fun String.toSnakeCase(): String {
    val retVal = this.replace("([a-z])([A-Z])".toRegex(), "$1_$2").lowercase()
        .replace("\\s".toRegex(), "_").replace("-".toRegex(), "_")
        .replace("([a-z])(\\d)".toRegex(), "$1_$2")
    return retVal
}


/*
206. Reverse Linked List
https://leetcode.com/problems/reverse-linked-list/?envType=study-plan&id=algorithm-i
*/
fun reverseList(head: ListNode?): ListNode? {
    if (head == null) {
        return head
    }
    var prevNode = head
    var currNode = prevNode.next
    head.next = null

    while (currNode != null) {
        val temp = currNode.next
        currNode.next = prevNode
        prevNode = currNode
        currNode = temp
    }
    return prevNode
}

/*
21. Merge Two Sorted Lists
https://leetcode.com/problems/merge-two-sorted-lists/?envType=study-plan&id=algorithm-i
*/
fun mergeTwoLists(list1: ListNode?, list2: ListNode?): ListNode? {
    if (list1 == null && list2 == null) {
        return null
    }
    if (list1 == null) {
        return list2
    }
    if (list2 == null) {
        return list1
    }
    var head: ListNode? = null
    var currNode1 = list1
    var currNode2 = list2
    var currNode: ListNode? = null

    while (currNode1 != null && currNode2 != null) {
        val minNode = if (currNode1.value < currNode2.value) {
            currNode1
        } else {
            currNode2
        }
        if (head == null) {
            head = minNode
        } else {
            if (minNode == currNode1) {
                currNode1 = currNode1.next
            } else {
                currNode2 = currNode2.next
            }
            if (currNode == null) {
                currNode = minNode
            } else {
                currNode.next = minNode
                currNode = currNode.next
            }
        }
    }
    while (currNode1 != null) {
        currNode?.next = currNode1
        currNode = currNode?.next
        currNode1 = currNode1.next
    }
    while (currNode2 != null) {
        currNode?.next = currNode2
        currNode = currNode?.next
        currNode2 = currNode2.next
    }
    return head


}

/*
994. Rotting Oranges
https://leetcode.com/problems/rotting-oranges/?envType=study-plan&id=algorithm-i

*/
fun orangesRotting(grid: Array<IntArray>): Int {
    val queue: Queue<Point> = LinkedList()
    var minuteCount = 0
    val neighbors = arrayOf(arrayOf(0, -1), arrayOf(-1, 0), arrayOf(0, 1), arrayOf(1, 0))
    val rowSize = grid.size
    val colSize = grid[0].size

    for (i in 0 until rowSize) {
        for (j in 0 until colSize) {
            if (grid[i][j] == 2) {
                queue.add(Point(i, j))
            }
        }
    }
    while (queue.isNotEmpty()) {
        var neighborAdded = false
        var queueSize = queue.size
        while (queueSize > 0) {
            val point = queue.poll()

            neighbors.forEach { neighbor ->
                val row = point.x + neighbor[0]
                val col = point.y + neighbor[1]
                if (row in 0 until rowSize && col in 0 until colSize && grid[row][col] == 1) {
                    grid[row][col] = 2
                    queue.add(Point(row, col))
                    neighborAdded = true
                }
            }
            queueSize--
        }
        if (neighborAdded) {
            minuteCount++
        }
    }
    for (i in 0 until rowSize) {
        for (j in 0 until colSize) {
            if (grid[i][j] == 1) {
                return -1
            }
        }
    }
    return minuteCount

}

/* 542. 01 Matrix
https://leetcode.com/problems/01-matrix/?envType=study-plan&id=algorithm-i
Medium
6.7K
321
Companies
Given an m x n binary matrix mat, return the distance of the nearest 0 for each cell.

The distance between two adjacent cells is 1.


Example 1:


Input: mat = [[0,0,0],[0,1,0],[0,0,0]]
Output: [[0,0,0],[0,1,0],[0,0,0]]

*/
fun updateMatrix(mat: Array<IntArray>): Array<IntArray> {
    val rowSize = mat.size
    val colSize = mat[0].size
    val queue: Queue<Point> = LinkedList()
    for (i in 0 until rowSize) {
        for (j in 0 until colSize) {
            if (mat[i][j] == 0) {
                queue.add(Point(i, j))
            } else {
                mat[i][j] = -1
            }
        }
    }
    val neighbors = arrayOf(arrayOf(0, -1), arrayOf(-1, 0), arrayOf(1, 0), arrayOf(0, 1))
    while (queue.isNotEmpty()) {
        val point = queue.poll()
        neighbors.forEach { neighbor ->
            val row = point.x + neighbor[0]
            val col = point.y + neighbor[1]
            if (row in 0 until rowSize && col in 0 until colSize && mat[row][col] == -1) {
                mat[row][col] = mat[point.x][point.y] + 1
                queue.add(Point(row, col))
            }
        }
    }
    return mat
}

/*
116. Populating Next Right Pointers in Each Node
https://leetcode.com/problems/populating-next-right-pointers-in-each-node/?envType=study-plan&id=algorithm-i
*/
class Node(var `val`: Int) {
    var left: Node? = null
    var right: Node? = null
    var next: Node? = null
}

fun connect(root: Node?): Node? {
    if (root == null) {
        return root
    }
    val queue: Queue<Node> = LinkedList<Node>()
    queue.add(root)

    while (queue.isNotEmpty()) {
        val size = queue.size
        var count = 0
        var previousNode: Node? = null
        while (count < size) {
            val currNode = queue.poll()
            currNode.left?.let { queue.add(it) }
            currNode.right?.let { queue.add(it) }
            if (previousNode != null) {
                previousNode.next = currNode
            }
            previousNode = currNode
            count++
        }
    }
    return root
}

class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}

fun mergeTrees(root1: TreeNode?, root2: TreeNode?): TreeNode? {
    if (root1 == null && root2 == null) {
        return null
    }
    val value = root1?.`val` ?: 0
    val value2 = root2?.`val` ?: 0
    val node = TreeNode(value + value2)
    node.left = mergeTrees(root1?.left, root2?.left)
    node.right = mergeTrees(root1?.right, root2?.right)
    return node
}

/*
695. Max Area of Island
https://leetcode.com/problems/max-area-of-island/?envType=study-plan&id=algorithm-i
Medium
8.7K
195
Companies
You are given an m x n binary matrix grid. An island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.

The area of an island is the number of cells with a value 1 in the island.

Return the maximum area of an island in grid. If there is no island, return 0.



Example 1:


Input: grid = [[0,0,1,0,0,0,0,1,0,0,0,0,0],[0,0,0,0,0,0,0,1,1,1,0,0,0],[0,1,1,0,1,0,0,0,0,0,0,0,0],[0,1,0,0,1,1,0,0,1,0,1,0,0],[0,1,0,0,1,1,0,0,1,1,1,0,0],[0,0,0,0,0,0,0,0,0,0,1,0,0],[0,0,0,0,0,0,0,1,1,1,0,0,0],[0,0,0,0,0,0,0,1,1,0,0,0,0]]
Output: 6
Explanation: The answer is not 11, because the island must be connected 4-directionally.
Example 2:

Input: grid = [[0,0,0,0,0,0,0,0]]
Output: 0

*/
data class Point(val x: Int, val y: Int)

fun maxAreaOfIsland(grid: Array<IntArray>): Int {
    val rowSize = grid.size
    val colSize = grid[0].size
    val visited = Array<IntArray>(rowSize) { IntArray(colSize) }
    var retVal = 0
    val queue: Queue<Point> = LinkedList<Point>()
    for (row in 0 until rowSize) {
        for (col in 0 until colSize) {
            if (grid[row][col] == 1 && visited[row][col] == 0) {
                queue.add(Point(row, col))
                visited[row][col] = 1
                var currentArea = 0
                while (queue.isNotEmpty()) {
                    val point = queue.poll()
                    currentArea++
                    if (point.x > 0) {
                        if (grid[point.x - 1][point.y] == 1 && visited[point.x - 1][point.y] == 0) {
                            queue.add(Point(point.x - 1, point.y))
                            visited[point.x - 1][point.y] = 1
                        }
                    }
                    if (point.y > 0) {
                        if (grid[point.x][point.y - 1] == 1 && visited[point.x][point.y - 1] == 0) {
                            queue.add(Point(point.x, point.y - 1))
                            visited[point.x][point.y - 1] = 1
                        }
                    }
                    if (point.x + 1 < rowSize) {
                        if (grid[point.x + 1][point.y] == 1 && visited[point.x + 1][point.y] == 0) {
                            queue.add(Point(point.x + 1, point.y))
                            visited[point.x + 1][point.y] = 1
                        }
                    }
                    if (point.y + 1 < colSize) {
                        if (grid[point.x][point.y + 1] == 1 && visited[point.x][point.y + 1] == 0) {
                            queue.add(Point(point.x, point.y + 1))
                            visited[point.x][point.y + 1] = 1
                        }
                    }

                }
                if (currentArea > retVal) {
                    retVal = currentArea
                }
            }
        }
    }
    return retVal
}
/*
733. Flood Fill
Easy
6.8K
678
Companies
An image is represented by an m x n integer grid image where image[i][j] represents the pixel value of the image.

You are also given three integers sr, sc, and color. You should perform a flood fill on the image starting from the pixel image[sr][sc].

To perform a flood fill, consider the starting pixel, plus any pixels connected 4-directionally to the starting pixel of the same color as the starting pixel, plus any pixels connected 4-directionally to those pixels (also with the same color), and so on. Replace the color of all of the aforementioned pixels with color.

Return the modified image after performing the flood fill.



Example 1:


Input: image = [[1,1,1],[1,1,0],[1,0,1]], sr = 1, sc = 1, color = 2
Output: [[2,2,2],[2,2,0],[2,0,1]]
Explanation: From the center of the image with position (sr, sc) = (1, 1) (i.e., the red pixel), all pixels connected by a path of the same color as the starting pixel (i.e., the blue pixels) are colored with the new color.
Note the bottom corner is not colored 2, because it is not 4-directionally connected to the starting pixel.
Example 2:

Input: image = [[0,0,0],[0,0,0]], sr = 0, sc = 0, color = 0
Output: [[0,0,0],[0,0,0]]
Explanation: The starting pixel is already colored 0, so no changes are made to the image.

 */

fun floodFill(image: Array<IntArray>, sr: Int, sc: Int, color: Int): Array<IntArray> {
    val queue: Queue<Point> = LinkedList()
    val rowSize = image.size
    val colSize = image[0].size
    val retVal = Array<IntArray>(rowSize) { IntArray(colSize) }
    val matchColor = image[sr][sc]
    if (matchColor != color) {
        queue.add(Point(sr, sc))
    }
    while (queue.isNotEmpty()) {
        val point = queue.poll()
        image[point.x][point.y] = color
        if (point.x > 0) {
            if (image[point.x - 1][point.y] == matchColor) {
                queue.add(Point(point.x - 1, point.y))
            }
        }
        if (point.y > 0) {
            if (image[point.x][point.y - 1] == matchColor) {
                queue.add(Point(point.x, point.y - 1))
            }
        }
        if (point.x + 1 < rowSize) {
            if (image[point.x + 1][point.y] == matchColor) {
                queue.add(Point(point.x + 1, point.y))
            }
        }
        if (point.y + 1 < colSize) {
            if (image[point.x][point.y + 1] == matchColor) {
                queue.add(Point(point.x, point.y + 1))
            }
        }

    }
    return image
}

/*
https://leetcode.com/problems/permutation-in-string/
567. Permutation in String
Medium
9.6K
308
Companies
Given two strings s1 and s2, return true if s2 contains a permutation of s1, or false otherwise.

In other words, return true if one of s1's permutations is the substring of s2.
 */
fun checkInclusion(s1: String, s2: String): Boolean {

    if (s1.length > s2.length) {
        return false
    }
    if (s1.length == 0) {
        return true
    }
    var arr1 = IntArray(26) { 0 }
    var arr2 = IntArray(26) { 0 }
    var i = 0
    while (i < s1.length) {
        arr1[s1[i] - 'a']++
        arr2[s2[i] - 'a']++
        i++
    }
    for (j in i..s2.length - 1) {
        if (arr1.contentEquals(arr2)) {
            return true
        }
        arr2[s2[j - i] - 'a']--
        arr2[s2[j] - 'a']++
    }
    return arr1.contentEquals(arr2)

}

//find the longest substring without repeated characters
//example: abcdcef
fun longestSubstringWithoutRepeat(input: String): String {
    val list = ArrayList<Char>()
    var currentPtr = 0
    var runningPtr = 0
    var currentSubstring = ""
    var previousSubString = ""
    while (runningPtr < input.length) {
        if (currentSubstring.indexOf(input[runningPtr]) == -1) {
            currentSubstring = currentSubstring.plus(input[runningPtr])
        } else {
            previousSubString =
                if (currentSubstring.length > previousSubString.length) currentSubstring else previousSubString
            currentPtr = currentSubstring.indexOf(input[runningPtr]) + 1
            currentSubstring =
                currentSubstring.subSequence(currentPtr, currentSubstring.length).toString()
                    .plus(input[runningPtr])
        }

        runningPtr++
    }
    return if (currentSubstring.length > previousSubString.length) currentSubstring else previousSubString
}
