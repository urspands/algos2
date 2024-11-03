package com.raj.algorithms.oct2024

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.Stack
import kotlin.math.sqrt

//https://leetcode.com/discuss/general-discussion/460599/blind-75-leetcode-questions
fun main() {

//    val twoSum = twoSum(intArrayOf(3, 2, 4), 6)
//    println("twoSum:: ${twoSum.contentToString()}")
//    println("maxProfit :: ${maxProfit(intArrayOf(7, 1, 5, 3, 6, 4))}")
//    println("increasingDecreasingOrder :: ${isValidMountainArray(intArrayOf(7, 1, 5, 3, 6, 4))}")
//    println("increasingDecreasingOrder :: ${isValidMountainArray(intArrayOf(1, 2, 3, 4))}")
//    println("increasingDecreasingOrder :: ${isValidMountainArray(intArrayOf(1, 2, 3, 4, 3))}")
//    println("increasingDecreasingOrder :: ${isValidMountainArray(intArrayOf(1, 2, 3, 4, 3, 4))}")
//    println("increasingDecreasingOrder :: ${isValidMountainArray(intArrayOf(8, 7, 6, 5, 4))}")
//    println("isPrime :: ${isPrime(3)}")
//    println("isPalindrome :: ${isPalindrome("A man, a plan, a canal: Panama")}")
//    println("isPalindrome :: ${isPalindrome("Not a palindrome")}")
//test()
    isAnagram("panp", "Tamil")
    val ed = EncodeAndDecodeString()
    val en = ed.encode(listOf("He#llo", "#World", "^Raj"))
    println("${ed.decode(en)}")
}

//https://leetcode.com/problems/encode-and-decode-strings/
//Leetcode 271. Encode and Decode Strings
//https://www.youtube.com/watch?v=pdRZrjlqSbs
class EncodeAndDecodeString {
    private val DELIMITER = '#'
    fun encode(list: List<String>): String {
        val stringBuilder = StringBuilder()
        list.forEach {
            stringBuilder.append(it.length).append(DELIMITER).append(it)
        }
        return stringBuilder.toString()
    }

    fun decode(input: String): List<String> {
        val result = ArrayList<String>()
        var left = 0
        while (left < input.length) {
            val countStr = StringBuilder()
            while (input[left] != DELIMITER) {
                countStr.append(input[left])
                left++
            }
            left++
            val count = countStr.toString().toInt()
            val strEnd = left + count
            val string = StringBuilder()
            while (left < strEnd) {
                string.append(input[left])
                left++
            }
            result.add(string.toString())
        }
        return result
    }
}

//647. Palindromic Substrings
//https://leetcode.com/problems/palindromic-substrings/
fun countPalindromicSubstrings(s: String): Int {
    var result = 0
    for (i in 0..s.length) {
        result += countPSubstrings(i, i, s)
        result += countPSubstrings(i, i + 1, s)
    }
    return result
}

fun countPSubstrings(left: Int, right: Int, input: String): Int {
    var tempResult = 0
    while (left >= 0 && right < input.length && input[left] == input[right]) {
        tempResult++
    }
    return tempResult
}

//https://leetcode.com/problems/valid-palindrome/
//125. Valid Palindrome
fun isPalindrome(input: String): Boolean {
    if (input.isEmpty()) return false

    var left = 0
    var right = input.length - 1
    while (left < right) {
        while (left < right && !input[left].isLetterOrDigit()) {
            left++
        }
        while (left < right && !input[right].isLetterOrDigit()) {
            right--
        }

        if (left > right) {
            return false
        }
        if (input[left++].lowercaseChar() != input[right--].lowercaseChar()) {
            return false
        }
    }
    return true
}

//https://leetcode.com/problems/valid-parentheses/
//20. Valid Parentheses
fun isValid(s: String): Boolean {
    val stack = Stack<Char>()
    s.toCharArray().forEach { char ->
        when (char) {
            '(', '[', '{' -> stack.push(char)
            ')' -> {
                if (stack.isEmpty) return false
                val topChar = stack.pop()
                if (topChar != '(') {
                    return false
                }
            }

            ']' -> {
                if (stack.isEmpty) return false
                val topChar = stack.peek()
                if (topChar != '[') {
                    return false
                }
            }

            '}' -> {
                if (stack.isEmpty) return false
                val topChar = stack.pop()
                if (topChar != '{') {
                    return false
                }
            }
        }

    }

    return stack.isEmpty
}

//https://leetcode.com/problems/group-anagrams/
//49. Group Anagrams
fun groupAnagrams(strs: Array<String>): List<List<String>> {
    val map = HashMap<String, MutableList<String>>()
    strs.forEach { str ->
        val key = str.toCharArray().sorted().toString()
        val list = map.getOrDefault(key, mutableListOf()).apply {
            add(str)
        }
        map.put(key, list)
    }
    return map.values.toList()
}

fun isAnagram(input1: String, input2: String): Boolean {
    val groupBy = input1.groupBy { it }
    val groupBy2 = input2.groupBy { it }
    return groupBy == groupBy2
}

fun test() = runBlocking {
    test2()
    println("hello")

}

suspend fun test2() = coroutineScope {
    launch {
        delay(2000)
        println("2")
    }
    launch {
        delay(1000)
        println("1")
    }
    println("start")
}

fun isValidMountainArray(input: IntArray): Boolean {
    if (input.size < 3) return false
    var peakFound = false
    for (i in 1 until input.size) {
        if (!peakFound) {
            if (input[i - 1] > input[i]) {
                if (i - 1 == 0) {
                    return false
                } else {
                    peakFound = true
                }
            } else if (input[i - 1] == input[i]) {
                return false
            }
        } else {
            if (input[i - 1] <= input[i]) {
                return false
            }
        }
    }
    return peakFound
}


fun isPrime(n: Int): Boolean {
    if (n <= 1) return false
    if (n <= 3) return true
    val sqrt = sqrt(n.toDouble()).toInt()
    println("sqrt :: $sqrt")
    for (i in 2..sqrt) {
        if (n % i == 0) return false
    }

    return true
}

fun isValidIncrementThenDecrement(arr: IntArray): Boolean {
    if (arr.size < 3) return false  // A valid sequence needs at least 3 elements

    var peakFound = false  // Tracks when we hit the peak

    for (i in 1 until arr.size) {
        if (arr[i] == arr[i - 1]) {
            return false  // No duplicate values allowed in a strictly increasing/decreasing pattern
        } else if (!peakFound) {
            if (arr[i] < arr[i - 1]) {
                peakFound = true  // Peak detected, switch to decreasing mode
            }
        } else {
            if (arr[i] >= arr[i - 1]) {
                return false  // Should be strictly decreasing after the peak
            }
        }
    }

    // The sequence is valid if we found a peak and there was both an increasing and decreasing part
    return peakFound
}

fun increasingDecreasingOrder(input: List<Int>): Boolean {
    if (input.size < 3) {
        return false
    }
    var peakFound = false
    for (i in 1 until input.size) {
        if (input[i - 1] == input[i]) {
            return false
        }
        if (!peakFound) {
            if (input[i - 1] > input[i]) {
                if (i - 1 == 0) {
                    return false
                } else {
                    peakFound = true
                }
            }
        } else {
            if (input[i] > input[i - 1]) {
                return false
            }
        }
    }

    return peakFound
}

//3. Longest Substring Without Repeating Characters
//https://leetcode.com/problems/longest-substring-without-repeating-characters/
fun lengthOfLongestSubstring(s: String): Int {
    if (s.isEmpty()) return 0
    var (start, end) = 0 to 0
    var (subStringStart, subStringEnd) = 0 to 0
    val hashSet = HashSet<Char>()
    while (start < s.length && end < s.length) {

        if (!hashSet.contains(s[end])) {
            hashSet.add(s[end])
            if (subStringEnd - subStringStart < end - start) {
                subStringStart = start
                subStringEnd = end
            }
            end++
        } else {
            hashSet.remove(s[start])
            start++
        }
    }
    return subStringEnd - subStringStart + 1
}

//11. Container With Most Water
//https://leetcode.com/problems/container-with-most-water/
fun maxArea(height: IntArray): Int {
    var maxArea = 0
    var start = 0
    var end = height.size - 1

    while (start < end) {
        val area = (end - start) * Math.min(height[end], height[start])
        maxArea = Math.max(maxArea, area)
        if (height[end] > height[start]) {
            start++
        } else {
            end--
        }
    }
    return maxArea
}
//15. 3Sum
// https://leetcode.com/problems/3sum/

fun threeSum(nums: IntArray): List<List<Int>> {
    val retVal = HashSet<ArrayList<Int>>()
    nums.sort()
    nums.forEachIndexed { idx, value ->
        val target = -value
        var start = idx + 1
        var end = nums.size - 1
        while (start < end) {
            val sum = nums[start] + nums[end]
            if (sum == target) {
                retVal.add(arrayListOf(value, nums[start], nums[end]))
                end--
                start++
            } else if (sum > target) {
                end--
            } else {
                start++
            }
        }
    }
    return ArrayList<ArrayList<Int>>(retVal)
}

//153. Find Minimum in Rotated Sorted Array
//https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/
fun findMin(nums: IntArray): Int {
    if (nums[0] <= nums[nums.size - 1]) return nums[0]
    var start = 0
    var end = nums.size - 1
    while (start <= end) {
        var mid = start + ((end - start) / 2)
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

//33. Search in Rotated Sorted Array
//https://leetcode.com/problems/search-in-rotated-sorted-array/
fun search(nums: IntArray, target: Int): Int {
    val nNums = nums.size

    var lo = 0
    var hi = nNums - 1

    while (lo <= hi) {
        val mid = lo + (hi - lo) / 2

        if (nums[mid] == target) return mid

        if (nums[lo] <= nums[mid]) {
            if (target in nums[lo]..nums[mid]) {
                hi = mid - 1
            } else lo = mid + 1
        } else {
            if (target in nums[mid]..nums[hi]) {
                lo = mid + 1
            } else hi = mid - 1
        }
    }

    return -1
}

//https://leetcode.com/problems/two-sum/
fun twoSum(nums: IntArray, target: Int): IntArray {
    val map = HashMap<Int, Int>()
    nums.forEachIndexed { idx, value ->
        val compliment = target - value
        if (map.containsKey(compliment)) {
            return intArrayOf(map.getValue(compliment), idx)
        }
        map.put(value, idx)
    }
    return intArrayOf()
}

//Best Time to Buy and Sell Stock
//https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
fun maxProfit(prices: IntArray): Int {
//    var maxProfit = Int.MIN_VALUE
//    prices.forEachIndexed { idx1, value ->
//        var idx2 = idx1 + 1
//        while (idx2 < prices.size) {
//            maxProfit = maxOf(maxProfit, prices[idx2] - value)
//            idx2++
//        }
//    }
//    return maxOf(maxProfit,0)
//
    var minSoFar = Int.MAX_VALUE
    var result = 0
    prices.forEachIndexed { idx, value ->
        minSoFar = minOf(minSoFar, value)
        result = maxOf(result, value - minSoFar)
    }
    return result
}

//Contains Duplicate
//https://leetcode.com/problems/contains-duplicate/
fun containsDuplicate(nums: IntArray): Boolean {
    val map = HashMap<Int, Int>()
    nums.forEach {
        if (map.containsKey(it)) {
            return true
        } else {
            map.put(it, it)
        }
    }
    return false
}

//238. Product of Array Except Self
//https://leetcode.com/problems/product-of-array-except-self/
//https://www.youtube.com/watch?v=tSRFtR3pv74
fun productExceptSelf(nums: IntArray): IntArray {
//    var result = IntArray(nums.size){1}
//    val leftProducts = IntArray(nums.size){1}
//    val rightProducts = IntArray(nums.size){1}
//    for(i in 1..nums.size-1){
//        leftProducts[i] = leftProducts[i-1]*nums[i-1]
//    }
//    for(i in nums.size-2 downTo 0){
//        rightProducts[i] = rightProducts[i+1]*nums[i+1]
//    }
//    for (i in 0..nums.size-1){
//        result[i]=leftProducts[i]*rightProducts[i]
//    }
//    return result
    var result = IntArray(nums.size) { 1 }
//    val leftProducts = IntArray(nums.size){1}
//    val rightProducts = IntArray(nums.size){1}
    for (i in 1..nums.size - 1) {
        result[i] = result[i - 1] * nums[i - 1]
    }
    var right = 1
    for (i in nums.size - 1 downTo 0) {
        result[i] = result[i + 1] * right
        right = right * nums[i]
    }

    return result
}

//53. Maximum Subarray
//https://leetcode.com/problems/maximum-subarray/
//nums = [-2,1,-3,4,-1,2,1,-5,4]
//fun maxSubArray(nums: IntArray): Int {
//    var result = Int.MIN_VALUE
//    for (i in 0..nums.size - 1) {
//        for (j in )
//    }
//
//}