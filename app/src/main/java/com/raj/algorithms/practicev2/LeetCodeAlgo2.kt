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
    solution(3)
}
fun solution3(n:Long) : Long {
    // Type your solution here
    val result = ArrayList<String>()
    fun helper(slate:String,open:Int,close:Int){
        if(open.toLong() ==n && close.toLong() ==n){
            result.add(slate)
        }else{
            if(open<n){
                helper(slate.plus("("),open+1,close)
            }
            if(close<open)
                helper(slate.plus(")"),open,close+1)
        }
    }
    helper("",0,0)
    return result.size.toLong()
}
fun solution1(matrix:List<List<Long>>) : List<List<Long>> {
    // Type your solution here
    var result = ArrayList<ArrayList<Long>>()
    val size = matrix.size -1
    for(col in 0..size ){
        val newRow = ArrayList<Long>()
        for (row in size downTo 0){
            newRow.add(matrix[row][col])
        }
        result.add(newRow)
    }
    return result
}
fun solution(x:Long) : Long {
    var reversed = 0L
    var num =x

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
            if(arr[i]!=-1L) {
                val leftNode = BtNode(arr[i], null, null)
                node.left = leftNode
                queue.add(leftNode)
            }
            i++
        }
        if (i < arr.size) {
            if(arr[i]!=-1L) {
                val rightNode = BtNode(arr[i], null, null)
                node.right = rightNode
                queue.add(rightNode)
            }
            i++
        }
    }
    val leftSum = findSum(head.left)
    val rightSum = findSum(head.right)
    return if (leftSum > rightSum) "left" else if(leftSum==rightSum) "equal " else "right"

}

fun findSum(node: BtNode?): Long {
    if (node == null) {
        return 0
    }
    val sumLeft =findSum(node.left)
    val sumright =findSum(node.right)
    return node.value +sumLeft + sumright
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

/*34. Find First and Last Position of Element in Sorted Array
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
fun longestSubstringWith2UniqueCharacters(input:String):String{
    val hashSet= HashSet<Char>()
    var previousSubstring=""
    var currSubstring=""
    var idx=0
    while(idx<input.length){
        if(!hashSet.contains(input[idx])){
            if(hashSet.size < 2){
                hashSet.add(input[idx])
                currSubstring = currSubstring.plus(input[idx])
            }else{
                previousSubstring = if(previousSubstring.length < currSubstring.length) currSubstring else previousSubstring
                //todo: instead of removing the first character,get the lastIndexOf other character
                // in the hashset ex:: baabbbccbb --in this case get last index of a and create the subsequence
                val idx1 = currSubstring.lastIndexOf(currSubstring[0])
                hashSet.remove(currSubstring[0])
                currSubstring = currSubstring.subSequence(if(idx1==0) idx1+1 else idx1,currSubstring.length).toString()
                currSubstring.forEach{
                    hashSet.add(it)
                }
                hashSet.add(input[idx])
                currSubstring = currSubstring.plus(input[idx])
            }
        }else{
            currSubstring = currSubstring.plus(input[idx])
        }
        idx++
    }

    return if(previousSubstring.length < currSubstring.length) currSubstring else previousSubstring
}

