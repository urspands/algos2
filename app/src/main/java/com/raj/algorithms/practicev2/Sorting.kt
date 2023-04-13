package com.raj.algorithms.practicev2

import java.math.BigInteger
import java.util.*
import kotlin.Comparator
import kotlin.collections.ArrayDeque
import kotlin.math.pow
import kotlin.system.measureTimeMillis

fun main() {
//    println("hello")
    var timeMillis = 0L
    val inputList = mutableListOf(5, 3, 10, 45, 2, 0, 400, 32, 43, 22, 99, 55, 88, 100)
//    timeMillis =
//        measureTimeMillis { println("Selection Sort${selectionSort(inputList)}") }
//    println("Time to execute Selection Sort:: $timeMillis")
//    timeMillis =
//        measureTimeMillis { println("Bubble Sort${bubbleSort(inputList)}") }
//    println("Time to execute Bubble Sort:: $timeMillis")
//
//    timeMillis =
//        measureTimeMillis { println("Merge Sort${mergeSort(inputList, 0, inputList.size - 1)}") }
//    println("Time to execute Merge Sort:: $timeMillis")
//    timeMillis =
//        measureTimeMillis {
//            println(
//                "twoSumWithSortedArray :: ${
//                    twoSumWithSortedArray(
//                        inputList.toIntArray().sortedArray(),
//                        255
//                    )
//                }"
//            )
//        }
//    println("Time to execute twoSumWithSortedArray:: $timeMillis")
//    val arr1 = arrayOf(11, 2, 4)
//    val arr2 = arrayOf(4, 5, 6)
//    val arr3 = arrayOf(10, 8, -12)
//    println("diagonalDifference::${diagonalDifference(arrayOf(arr1, arr2, arr3))}")

//    val strInput =
//        "89 37 29 73 68 82 58 45 84 17 88 46 69 60 20 24 34 49 52 80 43 72 92 55 10 49 14 88 77 47 64 43 23 66 64 86 27 63 38 62 75 25 58 13 79 30 26 12 94 96 29 92 59 15 98 39 32 81 78 13 15 86 72 45 82 46 42 66 97 85 42 46 56 57 40 54 63 27 32 68 2 64 29 22 38 18 36 60 82 20 75 18 79 70 17 56 6 15 11 15 40 17 5 96 84 7 78 28 74 51 67 3 48 53 30 46 9 89 40 65 16 61 98 33 3 19 57 84 32 27 42 83 60 80 27 17 98 61 7 14 76 74 65 37 2 97 52 12 77 40 41 99 90 29 55 76 71 62 63 46 27 46 51 3 11 89 75 52 78 23 96 17 65 85 63 19 16 50 42 4 92 67 51 96 53 7 37 97 45 5 70 73 80 73 39 19 15 43 39 34 4 31 72 26 96 98 17 84 8 76 21 5 86 23 30 95 88 92 44 33 7 10 32 1 69 88 10 19 29 8 82 86 25 91 60 47 18 7 36 53 88 40 25 29 69 99 82 93 47 92 25 33 26 8 88 83 16 87 31 95 9 0 28 52 20 39 20 81 71 39 10 85 63 22 8 98 67 26 99 96 71 19 64 75 54 35 58 26 40 89 46 67 23 53 61 17 69 41 62 64 19 74 48 61 81 38 49 38 69 74 61 20 2 84 48 35 63 91 82 75 98 87 16 35 26 53 9 0 84 75 41 28 25 48 11 0 99 11 10 71 79 58 96 88 94 0 74 75 84 52 92 53 93 13 21 91 64 46 16 6 82 57 19 53 95 29 1 46 52 58 99 78 37 23 60 24 47 10 97 34 38 6 35 49 53 98 84 62 51 66 78 30 58 48 60 34 51 30 95 16 36 55 53 99 54 3 53 57 43 55 98 92 53 98 73 70 86 7 50 58 75 32 67 36 84 56 37 81 6 73 90 5 8 69 92 18 3 76 59 42 88 87 64 48 45 95 33 40 5 51 58 5 30 20 87 33 37 61 14 36 95 32 44 33 74 93 23 23 28 4 90 75 58 93 20 60 57 64 79 56 69 35 45 37 14 6 47 16 36 90 18 29 4 82 19 77 8 22 54 2 65 37 86 5 67 58 33 86 43 15 4 97 62 39 7 12 60 75 17 66 97 76 57 86 75 7 78 49 74 81 50 26 69 82 75 40 17 34 24 15 37 8 18 35 24 19 86 47 0 14 30 22 20 3 97 56 93 55 28 11 98 78 19 5 70 71 42 62 91 0 84 36 24 21 26 59 20 46 65 26 82 50 34 40 98 30 63 60 2 91 83 94 18 69 12 37 22 97 53 78 17 85 66 27 55 0 96 89 73 85 42 21 81 5 38 37 62 14 77 60 57 17 45 43 25 53 82 72 30 2 85 25 8 29 18 21 96 51 4 55 23 79 9 40 35 10 96 24 96 18 11 81 55 27 72 8 28 67 40 32 77 76 26 17 63 47 99 22 37 64 42 32 3 41 15 83 86 58 5 74 93 47 44 18 1 32 27 41 10 74 67 42 81 70 17 57 26 92 33 29 23 42 90 9 40 11 20 85 79 67 26 62 19 81 45 17 92 20 34 52 79 39 90 58 92 56 12 66 69 20 53 0 14 5 1 44 34 49 47 68 53 62 13 21 37 36 66 25 55 87 88 4 31 2 54 64 71 62 97 95 45 46 23 94 37 45 45 49 50 54 24 10 29 44 91 93 12 71 9 73 45 46 63 94 10 50 94 64 22 96 54 53 89 67 98 2 42 15 31 82 82 18 72 41 20 8 21 57 54 78 37 47 37 75 26 32 17 64 79 46 88 46 57 63 62 63 30 86 99 57 32 58 48 67 27 7 0 6 9 50 82 55 72 5 48 20 45 62 67 32 45 94 18 81 92 33 78 9 97 76 25 29 74 86 0 59 60 45 25 6 25 14 4 20 10 45 34 4 64 6 21 42 87 0 81 16 77 16 79 46 24 33 83 62 95 52 52 51 92 47 57 97 47 53 12 62 24 41 74 35 3 95 70 13 13 99 50 19 2 63 4 70 42 78 95 8 69 56 98 54 42 73 22 45 73 22 7 32 58 6 90 13 50 74 26 37 44 9 60 54 45 87 30 8 24 81 23 91 90 97 29 49 31 47 93 88 6 29 48 11 43 28 28 2 9 73 37 48 72 56 62 13 48 19 83 21 29 19 24 76 60 35 89 76 18 65 2 32 45 91"
//    val arr = strInput.trimEnd().split(" ").map { it.toInt() }.toTypedArray()
//    val result = countingSort(arr)
//
//    println(result.joinToString(" "))
//    println(caesarCipher("www.abc.xy", 87))
//    println(
//        gridChallenge(
//            arrayOf(
//                "kc","iu"
//            )
//        )
//    )
//    println("superDigit::${superDigit("9875", 4)}")
//    println("isBalanced::${isBalanced("{(([])[])[]}{}")}")
    println(
        "find_top_k_frequent_elements::${
            find_top_k_frequent_elements(
                arrayListOf(
                    1,
                    2,
                    3,
                    2,
                    4,
                    3,
                    1
                ), 2
            )
        }"
    )
    kth_largest_in_an_array(arrayListOf(2,4,1,9,44,23,66,28,54),3)
    var head = ListNode(1)
    head.next= ListNode(2)
    head.next!!.next=ListNode(3)
    head.next!!.next!!.next=ListNode(4)
    head.next!!.next!!.next!!.next=ListNode(5)

//    removeNthFromEnd(head,2)
//    checkInclusion()
//    println(arrayOf(1,2).contentEquals( arrayOf(1,2)))
}


fun isBalanced(s: String): String {
    // Write your code here
    var i = 0
    var j = s.length - 1
    val stack = Stack<Char>()
    s.forEach {
        when (it) {
            '(', '[', '{' -> stack.add(it)
            else -> {
                if (stack.isEmpty()) {
                    return "NO"
                } else {
                    val topItem = stack.pop()
                    if (it == ')') {
                        if (topItem != '(') {
                            return "NO"
                        }
                    } else if (it == ']') {
                        if (topItem != '[') {
                            return "NO"
                        }
                    } else if (it == '}') {
                        if (topItem != '{') {
                            return "NO"
                        }
                    }
                }
            }
        }
    }
    return "YES"
}

class QWithStack() {
    val stack1 = ArrayDeque<Int>()
    val stack2 = ArrayDeque<Int>()

    fun enqueue(value: Int) {
        while (!stack2.isEmpty()) {
            stack1.addLast(stack2.removeLast())
        }
        stack1.addLast(value)
    }

    fun dequeue(): Int {
        while (!stack1.isEmpty()) {
            stack2.addLast(stack1.removeLast())
        }
        return stack2.removeLast()
    }
}

fun superDigit(n: String, k: Int): Int {
    var m = ""
    for (i in 0 until k) {
        m = m.plus(n)
    }
    return calculateSD(m)
}

fun calculateSD(input: String): Int {
    if (input.toBigInteger() < BigInteger("10")) {
        return input.toInt()
    }
    var sum = 0
    input.forEach { sum += it.toString().toInt() }

    return calculateSD(sum.toString())
}

fun gridChallenge(grid1: Array<String>): String {

    val grid = Array<String>(grid1.size) { "" }
    grid1.forEachIndexed { i, value ->
        value.toCharArray().sort()
        grid[i] = value
    }
    for (col in 0 until grid[0].length) {
        for (row in 0 until grid.size - 1) {
            val right = grid[row][col]
            val left = grid[row + 1][col]
            if (right > left) {
                return "NO"
            }
        }
    }
    return "YES"

}

fun caesarCipher(s: String, v: Int): String {
    val charArray = CharArray(s.length)
    val k = v % 26
    s.forEachIndexed { i, value ->
        if ((value in 'a'..'z')) {
            if ('z' - value < k) {
                charArray[i] = 'a' + (k - 1) - ('z' - value)
            } else {
                charArray[i] = s[i] + k
            }
        } else if ((value >= 'A' && value <= 'Z')) {
            if ('Z' - value < k) {
                charArray[i] = 'A' + (k - 1) - ('Z' - value)
            } else {
                charArray[i] = s[i] + k
            }
        } else {
            charArray[i] = s[i]
        }
    }
    return String(charArray)
}

fun test() {
    println("5 op 3: positive dividend, positive divisor")
    println("mod: ${5.mod(3)}")
    println(" % : ${5 % 3}")
    println("rem: ${5.rem(3)}")
    println()
    println("5 op (-3): positive dividend, negative divisor")
    println("mod: ${5.mod(-3)}")
    println(" % : ${5 % (-3)}")
    println("rem: ${5.rem(-3)}")
    println()
    println("(-5) op 3: negative dividend, positive divisor")
    println("mod: ${(-5).mod(3)}")
    println(" % : ${(-5) % 3}")
    println("rem: ${(-5).rem(3)}")
    println()
    println("(-5) op (-3): negative dividend, negative divisor")
    println("mod: ${(-5).mod(-3)}")
    println(" % : ${(-5) % (-3)}")
    println("rem: ${(-5).rem(-3)}")
}

fun countingSort(arr: Array<Int>): Array<Int> {
    val retArr = Array<Int>(arr.size) { 0 }
    arr.forEach { value -> retArr[value] += 1 }
    return retArr
}

fun diagonalDifference(arr: Array<Array<Int>>): Int {
    var sumRightDiagonals = 0
    var sumLightDiagonals = 0
    val arrSize = arr.size - 1
    for (i in arr.indices) {
        sumRightDiagonals += arr[i][i]
        sumLightDiagonals += arr[i][arrSize - i]
    }
    return if (sumRightDiagonals > sumLightDiagonals) sumRightDiagonals - sumLightDiagonals else sumLightDiagonals - sumRightDiagonals
}

fun miniMaxSum(arr: Array<Int>): Unit {
    arr.sort()
    val minSum: BigInteger = arr.copyOfRange(0, 4)
        .fold(BigInteger.ZERO) { acc: BigInteger, i: Int -> acc + i.toBigInteger() }
    val maxSum = arr.copyOfRange(1, 5)
        .fold(BigInteger.ZERO) { acc: BigInteger, i: Int -> acc + i.toBigInteger() }
    System.out.println("$minSum $maxSum")
}

fun selectionSort(list: MutableList<Int>): MutableList<Int> {

    for (i in 0 until list.size) {
//        curPos = i + 1
//        minPos = i
//        while (curPos < list.size) {
//            if (list[curPos] < list[minPos]) {
//                minPos = curPos
//            }
//            curPos++
//        }
        val minVal = list.subList(i, list.size).minOrNull()
        if (minVal != null) {
            val minPos1 = list.indexOf(minVal)
//        list.swap(minPos, i)
            Collections.swap(list, minPos1, i)
        }
    }
    return list
}

fun bubbleSort(list: MutableList<Int>): MutableList<Int> {
    for (i in list.size - 1 downTo 0) {
        val maxVal = list.subList(0, i + 1).maxOrNull()
        if (maxVal != null) {
            val maxPos = list.indexOf(maxVal)
            Collections.swap(list, i, maxPos)
        }
    }
    return list
}

fun mergeSort(list: MutableList<Int>, start: Int, end: Int): MutableList<Int> {
    if (start >= end) {
        return list
    }
    val mid = (start + end).div(2)
    mergeSort(list, start, mid)
    mergeSort(list, mid + 1, end)
    var i = start
    var j = mid + 1
    val tempList = mutableListOf<Int>()
    while (i <= mid && j <= end) {
        if (list[i] <= list[j]) {
            tempList.add(list[i])
            i++
        } else {
            tempList.add(list[j])
            j++
        }
    }
    while (i <= mid) {
        tempList.add(list[i])
        i++
    }
    while (j <= end) {
        tempList.add(list[j])
        j++
    }
    var idx = start
    tempList.forEach { list[idx++] = it }
    return list
}

fun twoSumWithSortedArray(array: IntArray, target: Int): Boolean {
    var i = 0
    var j = array.size - 1
    while (i < j) {
        val compItem = target - array[i]
        if (compItem == array[j]) {
            return true
        } else if (compItem < array[j]) {
            j--
        } else {
            i++
        }
    }
    return false
}

//fun twoSumWithUnSortedArrayUsingAuxSpace(array: IntArray, target: Int): Boolean {
////    val table = Hashtable<>
//}

fun <T> MutableList<T>.swap(pos1: Int, pos2: Int) {
    val temp = this[pos1]
    this[pos1] = this[pos2]
    this[pos2] = temp
}

/*
 * Complete the 'palindromeIndex' function below.
 *
 * The function is expected to return an INTEGER.
 * The function accepts STRING s as parameter.
 */

fun palindromeIndex(s: String): Int {
    // Write your code here
    var returnVal = -1
    if (isPalindrome(s)) {
        return returnVal
    } else {
        var start = 0
        var end = s.length - 1
        while (start <= end) {
            if (s[start] != s[end]) {
                var newInput =
                    s.subSequence(0, start).toString() + s.subSequence(start + 1, s.length)
                if (isPalindrome(newInput)) {
                    return start
                } else {
                    newInput = s.subSequence(0, end).toString() + s.subSequence(end + 1, s.length)
                    if (isPalindrome(newInput)) {
                        return end
                    }
                }
            }
            start++
            end--
        }
        return -1
    }

}

fun isPalindrome(input: String): Boolean {
    var start = 0
    var end = input.length - 1
    while (start <= end) {
        if (input[start] != input[end]) {
            return false
        }
        start++
        end--
    }
    return true
}


class LinkedListNode(value: Int) {
    var value: Int?
    var next: LinkedListNode?

    init {
        this.value = value
        next = null
    }
}

fun merge_k_lists(lists: ArrayList<LinkedListNode?>): LinkedListNode? {
    var head: LinkedListNode? = null
//    val comparator: Comparator<Input> = compareBy{it.data}
    val comparator: Comparator<Input> =
        Comparator<Input> { p0, p1 ->
            when {
                p0?.data != p1?.data -> p0?.data!!.minus(p1?.data!!)
                else -> p0!!.data!!.compareTo(p1!!.data!!)
            }
        }

    val minHeap = PriorityQueue<Input>(comparator)
    for (i in lists.indices) {
        minHeap.add(Input(lists[i]?.value!!, lists[i]))
    }
    var previousNode = head
    while (minHeap.isNotEmpty()) {
        val min = minHeap.remove()
        val currentNode = min

        if (head == null) {
            head = min.node
        }
        if (min.node?.next != null) {
            minHeap.add(Input(min?.node?.value, min?.node?.next))
        }
        currentNode.node?.next = null
        if (previousNode == null) {
            previousNode = currentNode.node
        } else {
            previousNode.next = currentNode.node
            previousNode = currentNode.node
        }
    }
    // Write your code here.
    return head
}

data class Input(val data: Int?, val node: LinkedListNode?) {

}

fun can_attend_all_meetings(intervals: ArrayList<ArrayList<Int>>): Int {
    intervals.sortWith(compareBy { it[0] })
    for (i in 0..intervals.size - 2) {
        if (intervals[i][1] > intervals[i + 1][0]) {
            return 0
        }
    }
    return 1

}

fun find_top_k_frequent_elements(arr: ArrayList<Int>, k: Int): ArrayList<Int> {

    val hashMap = HashMap<Int, Int>()
    arr.forEachIndexed { i, value ->
        hashMap.put(value, hashMap.getOrDefault(value, 0).plus(1))
    }
    val comparator: Comparator<Map.Entry<Int, Int>> =
        Comparator<Map.Entry<Int, Int>> { p0, p1 -> p1!!.value - p0!!.value }

    val priorityQueue: PriorityQueue<Map.Entry<Int, Int>> =
        PriorityQueue<Map.Entry<Int, Int>>(comparator)
    hashMap.forEach { entry: Map.Entry<Int, Int> ->
        priorityQueue.add(entry)
    }
    val retVal = ArrayList<Int>()
    for (i in 0..k) {
        retVal.add(priorityQueue.poll().key)
    }

// Write your code here.
    return retVal
}

fun kth_largest_in_an_array(numbers: ArrayList<Int>, k: Int): Int {
    // Write your code here.

    val priorityQueue = PriorityQueue<Int>()
    numbers.forEach {
        if (priorityQueue.count() < k) {
            priorityQueue.add(it)
        } else {
            val topValue = priorityQueue.peek()
            if (it > topValue!!) {
                priorityQueue.poll()
                priorityQueue.add(it)
            }
        }

    }
    return priorityQueue.peek()!!
}

fun online_median(stream: ArrayList<Int>): ArrayList<Int> {
    val retVal = ArrayList<Int>()
    val minHeap = PriorityQueue<Int>()
    val maxHeap = PriorityQueue<Int> { a, b -> b - a }
    var even = true
    stream.forEach {
        if (even) {
            maxHeap.add(it)
            minHeap.add(maxHeap.poll())
        } else {
            minHeap.add(it)
            maxHeap.add(minHeap.poll())
        }
        even = !even

        if (even) {
            val median = (minHeap.peek() + maxHeap.peek()) / 2
            retVal.add(median)
        } else {
            val median = minHeap.peek()
            retVal.add(median)
        }

    }

    return retVal
}

fun canTransform(start: String, end: String): Boolean {
    if (start.replace("X", "", true) != end.replace("X", "", true)) {
        return false
    }
    var p1 = 0
    var p2 = 0
    while (p1 < start.length && p2 < end.length) {
        if(start[p1]=='X'){
            p1++
        }else if(end[p2]=='X'){
            p2++
        }else{
            if((start[p1]=='R' && p1>p2) ||start[p1]=='L' && p1<p2){
                return false
            }
            p1++
            p2++
        }

    }
    return true
}
 fun firstBadVersion(n: Int) : Int {
    var retVal =1
//    fun helper(strIdx:Int, endIdx:Int):Int{
//        if(strIdx>endIdx){
//            return 1
//        }
//        val midIdx = strIdx+((endIdx-strIdx)/2)
//        val isCurrentBad=isBadVersion(midIdx)
//        if( isCurrentBad && !isBadVersion(midIdx-1)){
//            return midIdx
//        }else{
//            if(isCurrentBad){
//                return helper(strIdx,midIdx)
//            }else{
//                return helper(midIdx+1,endIdx)
//            }
//        }
//    }
//    retVal= helper(1,n)
    return retVal
}
fun searchInsert(nums: IntArray, target: Int): Int {
    var retVal=0
    fun helper(strtIdx:Int,endIdx:Int):Int{
        if(strtIdx>endIdx){
            return strtIdx
        }
        val mid = strtIdx + ((endIdx-strtIdx)/2)
        return when{
            nums[mid]==target -> return mid
            nums[mid]<target -> helper(mid+1,endIdx)
            else -> helper(strtIdx,mid-1)
        }

    }
    retVal = helper(0,nums.size-1)
    return retVal
}
//977. Squares of a Sorted Array
/*Given an integer array nums sorted in non-decreasing order, return an array of the squares of each number sorted in non-decreasing order.



Example 1:

Input: nums = [-4,-1,0,3,10]
Output: [0,1,9,16,100]
Explanation: After squaring, the array becomes [16,1,0,9,100].
After sorting, it becomes [0,1,9,16,100].
Example 2:

Input: nums = [-7,-3,2,3,11]
Output: [4,9,9,49,121]
*/
fun sortedSquares(nums: IntArray): IntArray {
    val retVal = IntArray(nums.size)
    var fwdPtr=0
    var bckPtr= nums.size-1
    var newArrPtr= nums.size-1
    while(fwdPtr<=bckPtr){

        val fwdPtrSqVal = nums[fwdPtr].toDouble().pow(2.toDouble()).toInt()
        val bckPtrSqVal = Math.pow(nums[bckPtr].toDouble(),2.toDouble()).toInt()
        retVal[newArrPtr--] = if(fwdPtrSqVal>bckPtrSqVal){
            fwdPtr++
            fwdPtrSqVal
        }else{
            bckPtr--
            bckPtrSqVal
        }
    }
    return retVal
}

/*
189. Rotate Array
Medium
13.4K
1.6K
Companies
Given an integer array nums, rotate the array to the right by k steps, where k is non-negative.



Example 1:

Input: nums = [1,2,3,4,5,6,7], k = 3
Output: [5,6,7,1,2,3,4]
Explanation:
rotate 1 steps to the right: [7,1,2,3,4,5,6]
rotate 2 steps to the right: [6,7,1,2,3,4,5]
rotate 3 steps to the right: [5,6,7,1,2,3,4]
Example 2:

Input: nums = [-1,-100,3,99], k = 2
Output: [3,99,-1,-100]
Explanation:
rotate 1 steps to the right: [99,-1,-100,3]
rotate 2 steps to the right: [3,99,-1,-100]

 */
fun rotate(nums: IntArray, k: Int): Unit {
    val size = nums.size
    val tempArr = IntArray(size)
    for(i in 0..size-1){
        tempArr[(i+k)%size] = nums[i]
    }
    for(i in 0..size-1){
        nums[i]=tempArr[i]
    }

}
//     fun rotate(nums: IntArray, k: Int): Unit {
//         fun reverse(i:Int,j:Int){
//             var m=i
//             var n=j
//             while(m<n){
//                 val temp = nums[m]
//                 nums[m]=nums[n]
//                 nums[n]=temp
//               m++
//                 n--
//             }
//         }

//         val j =k%nums.size

//         reverse(0,nums.size-1)
//         reverse(0,j-1)
//         reverse(j,nums.size-1)

//     }

//     fun rotate(nums: IntArray, k: Int): Unit {
//         var strtIdx=0
//         var rearIdx= nums.size-1
//         var swapIdx= nums.size-1-k
//         var count=0
//         fun swap(i:Int,j:Int){
//             val temp = nums[i]
//             nums[i]=nums[j]
//             nums[j]=temp
//     }
//         while(count<k){
//             val temp = nums[0]
//             swap(rearIdx,0)
//             var i = nums.size-1
//             while(i>1){
//                 nums[i]=nums[i-1]
//                 i--
//             }
//             nums[i]=temp
//             count++
//         }

//     }
/*
167. Two Sum II - Input Array Is Sorted
Medium
9.1K
1.2K
Companies
Given a 1-indexed array of integers numbers that is already sorted in non-decreasing order, find two numbers such that they add up to a specific target number. Let these two numbers be numbers[index1] and numbers[index2] where 1 <= index1 < index2 <= numbers.length.

Return the indices of the two numbers, index1 and index2, added by one as an integer array [index1, index2] of length 2.

The tests are generated such that there is exactly one solution. You may not use the same element twice.

Your solution must use only constant extra space.



Example 1:

Input: numbers = [2,7,11,15], target = 9
Output: [1,2]
Explanation: The sum of 2 and 7 is 9. Therefore, index1 = 1, index2 = 2. We return [1, 2].
Example 2:

Input: numbers = [2,3,4], target = 6
Output: [1,3]
Explanation: The sum of 2 and 4 is 6. Therefore index1 = 1, index2 = 3. We return [1, 3].
Example 3:

Input: numbers = [-1,0], target = -1
Output: [1,2]
Explanation: The sum of -1 and 0 is -1. Therefore index1 = 1, index2 = 2. We return [1, 2].

 */
fun twoSum(numbers: IntArray, target: Int): IntArray {
    val retVal = IntArray(2)

    var i =0
    var j=numbers.size-1
    while(i <j){
        val sum= numbers[i]+numbers[j]

        if(sum == target){
            retVal[0]=i+1
            retVal[1]=j+1
            break
        }
        else if(sum < target ){
            i++
        }
        else{
            j--
        }


    }
    return retVal

}
/*
557. Reverse Words in a String III
Easy
4.7K
222
Companies
Given a string s, reverse the order of characters in each word within a sentence while still preserving whitespace and initial word order.



Example 1:

Input: s = "Let's take LeetCode contest"
Output: "s'teL ekat edoCteeL tsetnoc"
Example 2:

Input: s = "God Ding"
Output: "doG gniD"


Constraints:

1 <= s.length <= 5 * 104
s contains printable ASCII characters.
s does not contain any leading or trailing spaces.
There is at least one word in s.
All the words in s are separated by a single space.
 */
//fun reverseWords(s: String): String {
//    val charArr = s.toCharArray()
//    fun swap(i:Int,j:Int){
//        val temp = charArr[i]
//        charArr[i]=charArr[j]
//        charArr[j]=temp
//    }
//    var i =0
//    var j =0
//    val size =charArr.size
//    while(j<size){
//        while(j<size && charArr[j]!=' '){
//            j++
//        }
//        j++
//        var m = j-2
//        while(i<m){
//            swap(i,m)
//            i++
//            m--
//        }
//        i=j
//    }
//    return String(charArr)
//}

fun reverseWords(s: String): String {
    var words = s.split(" ")
    fun reverse(str: CharArray): String {
        var i = 0
        var j = str.size - 1
        while (i < j) {
            val temp = str[i]
            str[i] = str[j]
            str[j] = temp
            i++
            j--
        }
        return String(str)
    }

    val retVal = StringBuilder()
    words.forEach {
        retVal.append(reverse(it.toCharArray()))

    }
    return retVal.toString()
}
 class ListNode(var value: Int) {
         var next: ListNode? = null
 }
fun removeNthFromEnd(head: ListNode?, n: Int): ListNode? {
    if(head==null){
        return null
    }
    var currentNode=head
    var size =0
    while(currentNode!=null){
        currentNode=currentNode?.next
        size++
    }
    currentNode=head
    var nthNode = size-n
    var i =0
    while(i<nthNode){
        currentNode= currentNode?.next
        i++
    }
    currentNode?.next= currentNode?.next?.next
    return head
}
fun lengthOfLongestSubstring(s: String): Int {
    var prevLongest = ""
    var currentSubString=""
    s.forEachIndexed{i,value ->
        if(currentSubString.indexOf(value)==-1){
            currentSubString = currentSubString.plus(value)
        }else{
            if(prevLongest.length<currentSubString.length){
                prevLongest = currentSubString
            }
            val repeatCharIdx = currentSubString.indexOf(value)
            currentSubString = currentSubString.subSequence(repeatCharIdx+1,currentSubString.length).toString()
            currentSubString = currentSubString.plus(value)
        }
    }
    if(prevLongest.length<currentSubString.length){
        prevLongest = currentSubString
    }
    return prevLongest.length
}

