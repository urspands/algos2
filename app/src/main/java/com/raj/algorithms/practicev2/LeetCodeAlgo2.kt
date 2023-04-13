package com.raj.algorithms.practicev2

import java.util.LinkedList
import java.util.Queue

fun main() {

//    searchRange(intArrayOf(5, 7, 7, 8, 8, 10), 8)
//    search(intArrayOf(4,5,6,7,0,1,2),0)
//    search(intArrayOf(1), 0)
//    println(findWeightOfTree(longArrayOf(1,4,100,5)))
//    println("${solution(-123)}")
//    println("${solution1(arrayListOf(arrayListOf(1,2), arrayListOf(3,4)))}")
//    solution(3)
//    longestSubstringWithKUniqueCharacters("aabcbcadeds", 3)
//    deleteDuplicates(getDummyListNode())
//    val matrix = arrayListOf(arrayListOf(1,1,0,1),arrayListOf(1,1,0,0),arrayListOf(0,0,1,0),arrayListOf(1,0,0,1))
//    val matrix = arrayListOf(arrayListOf(1,1,0),arrayListOf(1,1,0),arrayListOf(0,0,1))
//    val matrix = arrayListOf(arrayListOf(1, 1, 1), arrayListOf(1, 1, 1), arrayListOf(1, 1, 1))
//    println("${getProvinceCount(matrix)}")
//    val arr = arrayListOf(4, 2, 5, 88, 55, 77)
//    arr.sortWith { a, b -> b - a }
//    println("$arr")
//    intervalIntersection(
//        arrayOf(
//            intArrayOf(0, 2),
//            intArrayOf(5, 10),
//            intArrayOf(13, 23),
//            intArrayOf(24, 25)
//        ), arrayOf(intArrayOf(1, 5), intArrayOf(8, 12), intArrayOf(15, 24), intArrayOf(25, 26))
//    )
//    numSubarrayProductLessThanK(intArrayOf(10, 9, 10, 4, 3, 8, 3, 3, 6, 2, 10, 10, 9, 3), 19)
    println("${minSubArrayLen(7, intArrayOf(2, 3, 1, 2, 4, 3))}")
    println(
        "${
            numIslands(
                arrayOf(
                    charArrayOf('1', '1', '0', '0', '0'),
                    charArrayOf('1', '1', '0', '0', '0'),
                    charArrayOf('0', '0', '1', '0', '0'),
                    charArrayOf('0', '0', '0', '1', '1'),
                )
            )
        }"
    )
}

/*
547. Number of Provinces
https://leetcode.com/problems/number-of-provinces/description/?envType=study-plan&id=algorithm-ii
 */
fun findCircleNum(matrix: Array<IntArray>): Int {

    val visitedArr = Array(matrix.size) { IntArray(matrix[0].size) { 0 } }
    var provinceCount = 0
    fun helper(lRow: Int, lCol: Int) {
        visitedArr[lRow][lCol] = -1
        for (i in 0..matrix[0].size - 1) {
            if (matrix[lRow][i] == 1 && visitedArr[lRow][i] != -1) {
                helper(lRow, i)
            }
            if (matrix[lCol][i] == 1 && visitedArr[lCol][i] != -1) {
                helper(lCol, i)
            }
        }
    }


    for (row in 0..matrix.size - 1) {
        for (col in 0..matrix[0].size - 1) {
            if (matrix[row][col] == 1 && visitedArr[row][col] != -1) {
                provinceCount++
                helper(row, col)
            }
        }
    }

    return provinceCount
}

/*
200. Number of Islands
https://leetcode.com/problems/number-of-islands/?envType=study-plan&id=algorithm-ii
 */
fun numIslands(grid: Array<CharArray>): Int {
    val visitedArray = Array(grid.size) { IntArray(grid[0].size) { 0 } }
    val neighbors = arrayOf(arrayOf(0, 1), arrayOf(0, -1), arrayOf(-1, 0), arrayOf(1, 0))
    val queue: Queue<Point> = LinkedList()
    var islandCount = 0
    for (row in 0..grid.size - 1) {
        for (col in 0..grid[0].size - 1) {
            if (grid[row][col] == '1' && visitedArray[row][col] != -1) {
                queue.add(Point(row, col))
                visitedArray[row][col] = -1
                islandCount++
                while (queue.isNotEmpty()) {
                    val point = queue.poll()
                    neighbors.forEach { neighbor ->
                        val localx = point.x + neighbor[0]
                        val localy = point.y + neighbor[1]
                        if (localx in grid.indices && localy in 0 until grid[0].size && grid[localx][localy] == '1' && visitedArray[localx][localy] != -1) {
                            queue.add(Point(localx, localy))
                            visitedArray[localx][localy] = -1
                        }
                    }
                }
            }
        }
    }
    return islandCount
}

fun minSubArrayLen(target: Int, nums: IntArray): Int {

    if (nums.isEmpty()) {
        return 0
    }
    var curSum = 0
    var curSubArraySize = 0
    var minLength = Int.MAX_VALUE
    var (right, left) = 0 to 0

    while (right < nums.size && left <= right) {
        curSum += nums[right]
        while (curSum >= target) {
            minLength = minOf(minLength, right - left + 1)
            curSum -= nums[left++]
        }
        right++
    }
    if (minLength == Int.MAX_VALUE) {
        return 0
    }
    return minLength
}

fun numSubarrayProductLessThanK(nums: IntArray, k: Int): Int {
    if (k <= 1) return 0;
    var left = 0
    var right = 0
    var count = 0
    var product = 1;

    while (right < nums.size) {
        product *= nums[right];
        while (product >= k) product /= nums[left++];
        count += 1 + (right - left);
        right++;
    }

    println("numSubarrayProductLessThanK::$count")
    return count
}

/*
11. Container With Most Water
https://leetcode.com/problems/container-with-most-water/?envType=study-plan&id=algorithm-ii
 */
fun maxArea(height: IntArray): Int {
    var maxValue = -1
    var (i, j) = 0 to height.size - 1

    while (i < j) {
        val capacity = minOf(height[i], height[j]) * (j - i)
        if (maxValue < capacity) {
            maxValue = capacity
        }
        if (height[i] > height[j]) {
            j--
        } else {
            i++
        }
    }


    return maxValue
}

/*
986. Interval List Intersections
https://leetcode.com/problems/interval-list-intersections/description/?envType=study-plan&id=algorithm-ii
 */
fun intervalIntersection(firstList: Array<IntArray>, secondList: Array<IntArray>): Array<IntArray> {
    val list1Size = firstList.size
    val list2Size = secondList.size

    val tempArray = ArrayList<IntArray>()
    if (firstList.isEmpty() || secondList.isEmpty()) {
        return Array(0) { IntArray(0) { -1 } }
    }
    var list1Ptr = 0
    var list2Ptr = 0
    // var insertPtr=0
    while (list1Ptr < list1Size && list2Ptr < list2Size) {

        var i = 0
        val intersection = IntArray(2) { -1 }
        if (secondList[list2Ptr][0] in firstList[list1Ptr][0]..firstList[list1Ptr][1]) {
            intersection[i++] = secondList[list2Ptr][0]
        } else if (firstList[list1Ptr][0] in secondList[list2Ptr][0]..secondList[list2Ptr][1]) {
            intersection[i++] = firstList[list1Ptr][0]
        }
        if (secondList[list2Ptr][1] in firstList[list1Ptr][0]..firstList[list1Ptr][1]) {
            intersection[i++] = secondList[list2Ptr][1]
        } else if (firstList[list1Ptr][1] in secondList[list2Ptr][0]..secondList[list2Ptr][1]) {
            intersection[i++] = firstList[list1Ptr][1]
        }
        if (i == 1) {
            intersection[i++] = minOf(firstList[list1Ptr][1], secondList[list2Ptr][1])
        }
        if (firstList[list1Ptr][1] < secondList[list2Ptr][1]) {
            list1Ptr++
        } else {
            list2Ptr++
        }
        if (intersection[0] != -1 && intersection[1] != -1) {
            tempArray.add(intersection)
        }

    }
//    val result = Array(tempArray.size) { IntArray(2) }
//    tempArray.forEachIndexed { i, value ->
//        result[i] = value
//    }
    return tempArray.toTypedArray()
    /*
    val res = mutableListOf<IntArray>()
        var (l, r) = 0 to 0

        while (l in firstList.indices && r in secondList.indices) {
            val (a1, a2) = firstList[l][0] to firstList[l][1]
            val (b1, b2) = secondList[r][0] to secondList[r][1]
            when {
                a2 < b1 -> l++
                b2 < a1 -> r++
                else -> {
                    res += intArrayOf(maxOf(a1, b1), minOf(a2, b2))
                    if (a2 < b2) l++
                    else r++
                }
            }
        }

        return res.toTypedArray()
     */
}

/*
  1,1,0,1
  1,1,0,0
  0,0,1,0
  1,0,0,1

  1,1,1
  1,1,1
  1,1,1
 */
fun getProvinceCount(matrix: ArrayList<ArrayList<Int>>): Int {
    val matSize = matrix.size
    val visitedArr = Array(matSize) { IntArray(matSize) { 0 } }
    fun helper(lRow: Int, lCol: Int) {
        visitedArr[lRow][lCol] = -1
        for (i in 0..matSize - 1) {
            if (matrix[lRow][i] == 1 && visitedArr[lRow][i] == 0) {
                helper(lRow, i)
            }
            if (matrix[lCol][i] == 1 && visitedArr[lCol][i] == 0) {
                helper(lCol, i)
            }
        }
    }

    var provinceCount = 0

    for (row in 0..matSize - 1) {
        for (col in 0..matSize - 1) {
            if (matrix[row][col] == 1 && visitedArr[row][col] == 0) {
                provinceCount++
                helper(row, col)
            }
        }

    }

    return provinceCount

}

//data class PointCO(val row:Int,val col:Int)
/*
https://leetcode.com/problems/backspace-string-compare/description/?envType=study-plan&id=algorithm-ii
844. Backspace String Compare
Given two strings s and t, return true if they are equal when both are typed into empty text editors. '#' means a backspace character.

Note that after backspacing an empty text, the text will continue empty.
Input: s = "ab#c", t = "ad#c"
Output: true
Explanation: Both s and t become "ac".
 */
fun backspaceCompare(s: String, t: String): Boolean {
    fun getModifiedString(input: String): String {
        var result = input
        var start = 0
        while (start < result.length) {
            if (result[start] == '#') {
                result = result.substring(0, if (start - 1 < 0) 0 else start - 1)
                    .plus(result.substring(start + 1, result.length))
                start -= 2
                if (start < 0) {
                    start = 0
                }
            } else {
                start++
            }
        }
        return result
    }
    return getModifiedString(s) == getModifiedString(t)
}

fun getDummyListNode(): ListNode {
    val head = ListNode(1)
    val node1 = ListNode(2)
    head.next = node1
    val node2 = ListNode(3)
    node1.next = node2
    val node3 = ListNode(3)
    node2.next = node3
    val node4 = ListNode(4)
    node3.next = node4
    val node5 = ListNode(4)
    node4.next = node5
    val node6 = ListNode(5)
    node5.next = node6
    return head
}

/*
https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii/?envType=study-plan&id=algorithm-ii
82. Remove Duplicates from Sorted List II
Medium
7.5K
197
Companies
Given the head of a sorted linked list, delete all nodes that have duplicate numbers, leaving only
distinct numbers from the original list. Return the linked list sorted as well.
Input: head = [1,2,3,3,4,4,5]
Output: [1,2,5]
 */
fun deleteDuplicates(head: ListNode?): ListNode? {

    var currentNode = head
    var newHead: ListNode? = null
    var previousUniqueNode: ListNode? = null

    while (currentNode != null) {
        if (currentNode?.next != null) {
            if (currentNode.value == currentNode?.next?.value) {

                val currentDupValue = currentNode.value
                while (currentNode != null && currentNode.value == currentDupValue) {
                    currentNode = currentNode?.next
                }
                if (currentNode == null && previousUniqueNode != null) {
                    previousUniqueNode.next = currentNode
                }
            } else {
                if (newHead == null) {
                    newHead = currentNode
                }
                if (previousUniqueNode != null) {
                    previousUniqueNode?.next = currentNode
                }
                previousUniqueNode = currentNode
                currentNode = currentNode?.next
            }
        } else {
            if (newHead == null) {
                newHead = currentNode
            }
            if (previousUniqueNode != null) {
                previousUniqueNode?.next = currentNode
            }
            previousUniqueNode = currentNode
            currentNode = currentNode?.next
        }

    }
    return newHead

}

fun solution3(n: Long): Long {
    // Type your solution here
    val result = ArrayList<String>()
    fun helper(slate: String, open: Int, close: Int) {
        if (open.toLong() == n && close.toLong() == n) {
            result.add(slate)
        } else {
            if (open < n) {
                helper(slate.plus("("), open + 1, close)
            }
            if (close < open)
                helper(slate.plus(")"), open, close + 1)
        }
    }
    helper("", 0, 0)
    return result.size.toLong()
}

fun solution1(matrix: List<List<Long>>): List<List<Long>> {
    // Type your solution here
    var result = ArrayList<ArrayList<Long>>()
    val size = matrix.size - 1
    for (col in 0..size) {
        val newRow = ArrayList<Long>()
        for (row in size downTo 0) {
            newRow.add(matrix[row][col])
        }
        result.add(newRow)
    }
    return result
}

fun solution(x: Long): Long {
    var reversed = 0L
    var num = x

    while (num != 0L) {
        val digit = num % 10L
        reversed = reversed * 10L + digit
        num /= 10L
    }
    return reversed

}

fun findWeightOfTree(arr: LongArray): String {
    val queue: Queue<BtNode> = LinkedList()
    val head = BtNode(arr[0], null, null)
    queue.add(head)
    var i = 1
    while (queue.isNotEmpty()) {
        val node = queue.poll()
        if (i < arr.size) {
            if (arr[i] != -1L) {
                val leftNode = BtNode(arr[i], null, null)
                node.left = leftNode
                queue.add(leftNode)
            }
            i++
        }
        if (i < arr.size) {
            if (arr[i] != -1L) {
                val rightNode = BtNode(arr[i], null, null)
                node.right = rightNode
                queue.add(rightNode)
            }
            i++
        }
    }
    val leftSum = findSum(head.left)
    val rightSum = findSum(head.right)
    return if (leftSum > rightSum) "left" else if (leftSum == rightSum) "equal " else "right"

}

fun findSum(node: BtNode?): Long {
    if (node == null) {
        return 0
    }
    val sumLeft = findSum(node.left)
    val sumright = findSum(node.right)
    return node.value + sumLeft + sumright
}

class BtNode(val value: Long, var left: BtNode?, var right: BtNode?)

/*
162. Find Peak Element
https://leetcode.com/problems/find-peak-element/description/?envType=study-plan&id=algorithm-ii
 */
fun findPeakElement(nums: IntArray): Int {
    var start = 0
    var end = nums.size - 1
    while (start < end) {
        val mid = start + (end - start) / 2
        if (nums[mid] < nums[mid + 1]) {
            start = mid + 1
        } else {
            end = mid
        }
    }
    return start
}

/*
153. Find Minimum in Rotated Sorted Array
https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/description/?envType=study-plan&id=algorithm-ii
 */
fun findMin(nums: IntArray): Int {

    fun binarySearch(): Int {
        if (nums[0] <= nums[nums.size - 1]) {
            return nums[0]
        }
        var start = 0
        var end = nums.size - 1
        while (start <= end) {
            val mid = start + ((end - start) / 2)
            if (nums[mid] > nums[mid + 1]) {
                return nums[mid + 1]
            } else if (nums[mid] > nums[0]) {
                start = mid
            } else {
                end = mid
            }
        }
        return -1
    }
    return binarySearch()
}

/*
74. Search a 2D Matrix
https://leetcode.com/problems/search-a-2d-matrix/?envType=study-plan&id=algorithm-ii
 */
fun searchMatrix(matrix: Array<IntArray>, target: Int): Boolean {
    val m = matrix.size
    val n = matrix[0].size
    var start = 0
    var end = (m * n) - 1
    while (start <= end) {
        var mid = start + (end - start) / 2
        val row = mid / n
        val col = mid % n
        if (matrix[row][col] == target) {
            return true
        } else {
            if (matrix[row][col] > target) {
                end = mid - 1
            } else {
                start = mid + 1
            }

        }

    }
    return false
}

/*
33. Search in Rotated Sorted Array
https://leetcode.com/problems/search-in-rotated-sorted-array/?envType=study-plan&id=algorithm-ii
 */
fun search(nums: IntArray, target: Int): Int {
    fun binarySearch(sIdx: Int, endIdx: Int, findRotationPoint: Boolean = false): Int {
        var start = sIdx
        var end = endIdx
        var idx = -1

        while (start <= end) {
            val mid = start + ((end - start) / 2)
            if (findRotationPoint) {
                if (nums[0] <= nums[nums.size - 1]) {
                    return 0
                }
                if (nums[mid] > nums[mid + 1]) {
                    idx = mid
                    break
                } else if (nums[0] > nums[mid] && nums[mid] < nums[nums.size - 1]) {
                    end = mid
                } else if (nums[0] < nums[mid] && nums[mid] > nums[nums.size - 1]) {
                    start = mid
                } else {
                    break
                }
            } else {
                if (nums[mid] == target) {
                    idx = mid
                    break
                } else if (nums[mid] > target) {
                    end = mid - 1
                } else {
                    start = mid + 1
                }
            }

        }

        return idx
    }

    val pivot = binarySearch(0, nums.size - 1, true)
    return if (pivot != -1) {
        val result = if (target <= nums[pivot] && target >= nums[0]) {
            binarySearch(0, pivot)
        } else {
            binarySearch(pivot + 1, nums.size - 1)
        }
        println(result)
        result
    } else {
        binarySearch(0, nums.size - 1)
    }
}

/*34. Find First and Last Position of an Element in Sorted Array
https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/description/?envType=study-plan&id=algorithm-ii
 */
fun searchRange(nums: IntArray, target: Int): IntArray {
    var result = IntArray(2) { -1 }
    fun binarySearch(leftBias: Boolean = false): Int {
        var start = 0
        var end = nums.size - 1
        var idx = -1

        while (start <= end) {
            val mid = start + ((end - start) / 2)
            if (nums[mid] == target) {
                idx = mid
                if (leftBias) {
                    end = idx - 1
                } else {
                    start = idx + 1
                }
            } else if (nums[mid] > target) {
                end = mid - 1
            } else {
                start = mid + 1
            }
        }
        return idx
    }

    result[0] = binarySearch(true)
    result[1] = binarySearch()

    println("searchRange::${result.contentToString()}")
    return result
}


/*
Given a string, return the longest substring composed of at most two unique characters.
aabbc -> aabb
bcaabcbcbc -> bcbcbc
baabbbccbb
 */
//aabcbc
fun longestSubstringWithKUniqueCharacters(input: String, k: Int): String {

    var result: String = ""
    var countArr = IntArray(26) { 0 }
    var uniqueCount = 0
    input.forEach {
        if (countArr[it - 'a'] == 0) {
            uniqueCount++
        }
        countArr[it - 'a']++
    }
    if (k > uniqueCount) {
        return result
    }
    fun isValid(countArr: IntArray, k: Int): Boolean {
        var uniCount = 0
        countArr.forEach {
            if (it > 0) {
                uniCount++
            }
        }
        return uniCount <= k
    }

    var currStart = 0
    var currEnd = 0
    countArr = IntArray(26) { 0 }
    var maxStartPos = 0
    var maxWindow = 1
    while (currEnd < input.length) {
        countArr[input[currEnd] - 'a']++
        currEnd++
        if (!isValid(countArr, k)) {
            countArr[input[currStart] - 'a']--
            currStart++
        }
        if (currEnd - currStart > maxWindow) {
            maxStartPos = currStart
            maxWindow = currEnd - currStart
        }
    }
    result = input.substring(maxStartPos, maxStartPos + maxWindow)
    println("longestSubstringWithKUniqueCharacters:: $result")
    return result
}

