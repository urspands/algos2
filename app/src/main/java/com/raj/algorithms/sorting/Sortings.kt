package com.raj.algorithms.sorting

import android.util.Log
import kotlin.experimental.and
import kotlin.experimental.or
import kotlin.math.pow

object Sortings {
    private const val TAG = "Sortings"
    fun selectionSort(array: Array<Int>): Array<Int> {
        for (i in array.indices) {
            var minNumIndex = i;
            var minNum = array[i]
            for (j in i + 1 until array.size) {
                if (array[j] < minNum) {
                    minNumIndex = j
                    minNum = array[j]
                }
            }
            swap(array, i, minNumIndex)
        }
        Log.d(TAG, "selectionSort: ${array.contentToString()}")
        return array
    }

    private fun swap(array: Array<Int>, index1: Int, index2: Int) {
        val temp = array[index1]
        array[index1] = array[index2]
        array[index2] = temp
    }

    fun bubbleSort(array: Array<Int>): Array<Int> {
        //10,5,4,6,2,7,1
        for (i in array.indices) {
            var j = array.size - 1
            while (j >= i + 1) {
                if (array[j] < array[j - 1]) {
                    swap(array, j, j - 1)
                }
                j--
            }
        }
        Log.d(TAG, "bubbleSort: ${array.contentToString()}")
        return array
    }

    fun insertionSort(array: Array<Int>): Array<Int> {
        //10,5,4,6,2,7,1
        for (i in 1 until array.size) {
            val curElementToInsertInItsPos = array[i]
            var j = i - 1
            while (j >= 0 && curElementToInsertInItsPos < array[j]) {
                array[j + 1] = array[j]
                j--
            }
            array[j + 1] = curElementToInsertInItsPos
        }
        Log.d(TAG, "insertionSort: ${array.contentToString()}")
        return array
    }

    fun mergeSort(array: Array<Int>, start: Int, end: Int): Array<Int> {
        val sortedArray = mergeSorting(array, start, end)
        Log.d(TAG, "mergeSort: ${sortedArray.contentToString()}")
        return sortedArray
    }

    private fun mergeSorting(array: Array<Int>, start: Int, end: Int): Array<Int> {
        if (start == end) return array
        val mid = (start + end) / 2
        mergeSorting(array, start, mid)
        mergeSorting(array, mid + 1, end)
        var tempArray = arrayOf<Int>()
        var i = start
        var j = mid + 1
        while (i <= mid && j <= end) {
            if (array[i] <= array[j]) {
                tempArray = tempArray.plus(array[i])
                i++
            } else {
                tempArray = tempArray.plus(array[j])
                j++
            }
        }
        while (i <= mid) {
            tempArray = tempArray.plus(array[i])
            i++
        }
        while (j <= end) {
            tempArray = tempArray.plus(array[j])
            j++
        }
        var m = start
        for (item in tempArray) {
            array[m] = item
            m++
        }
        return tempArray
    }


    // Sorting Problems

//    Find if sum of 2 numbers matches the target

    fun findIf2NumSumExists(array: Array<Int>, target: Int): Boolean {
        array.sort()
        var start = 0
        var end = array.size - 1
        while (start < end) {
            val total = array[start] + array[end]
            when {
                total == target -> {
                    Log.d(TAG, "findIf2NumSumExists: Exists: true")
                    return true
                }
                total > target -> {
                    end--
                }
                else -> start++
            }
        }
        Log.d(TAG, "findIf2NumSumExists: Exists: false")
        return false
    }

    fun findIf2NumSumExistsUsingHashSet(array: Array<Int>, target: Int): Boolean {
        val hashSet = HashSet<Int>()
        hashSet.addAll(array)
        for (num in array) {
            if (hashSet.contains(target - num)) {
                Log.d(TAG, "findIf2NumSumExistsUsingHashSet: Exists: true")
                return true
            }
        }

        Log.d(TAG, "findIf2NumSumExistsUsingHash: Exists: false")
        return false
    }

    fun findIf2NumSumExistsReturnIndices(array: Array<Int>, target: Int): IntArray {
        val hashMap = HashMap<Int, Int>()
        val retVal = IntArray(2)
        for (i in array.indices) {
            hashMap[i] = array[i]
        }
        for (i in array.indices) {
            if (hashMap.containsValue(target - array[i])) {
                retVal[1] = i
                retVal[0] = hashMap.filter { it.value == target - array[i] }.keys.first()
            }
        }
        Log.d(TAG, "findIf2NumSumExistsReturnIndices: ${retVal.contentToString()}")
        return retVal
    }

    fun canAttendMeeting(array: Array<Array<Int>>): Boolean {
        array.sortBy { it[0] }
        Log.d(TAG, "canAttendMeeting: ${array.contentDeepToString()}")
        for (i in 0..array.size - 2) {
            if (array[i][1] > array[i + 1][0]) {
                Log.d(TAG, "canAttendMeeting: false")
                return false
            }
        }
        Log.d(TAG, "canAttendMeeting: true")
        return true
    }

    //a+b+c=0
    fun find3numbsSumsTo0(array: Array<Int>) {
        array.sort()
        val retVal = HashSet<ArrayList<Int>>()
        for (i in array.indices) {
            val target = -array[i]
            var start = i + 1
            var end = array.size - 1
            while (start < end) {
                when {
                    array[start] + array[end] == target -> {
                        retVal.add(arrayListOf(array[i], array[start], array[end]))
                        start++
                        end--
                    }
                    array[start] + array[end] > target -> {
                        end--
                    }
                    else -> {
                        start++
                    }
                }
            }
        }
        Log.d(TAG, "find3numbsSumsTo0:${retVal.toArray().contentDeepToString()}")
    }

    fun intersectionOf3SortedArrays(
        array1: Array<Int>,
        array2: Array<Int>,
        array3: Array<Int>
    ): Array<Int> {
        var retval: Array<Int> = arrayOf()
        var i = 0
        var j = 0
        var k = 0
        while (i < array1.size && j < array2.size && k < array3.size) {
            if (array1[i] == array2[j] && array1[i] == array3[k]) {
                retval = retval.plus(array1[i])
                i++
                j++
                k++
            } else {
                val min1 = array1[i].coerceAtMost(array2[j])
                val min2 = min1.coerceAtMost(array3[k])
                if (min2 == array1[i]) {
                    i++
                }
                if (min2 == array2[j]) {
                    j++
                } else {
                    k++
                }
            }
        }
        Log.d(TAG, "intersectionOf3SortedArrays: ${retval.contentToString()}")
        return retval
    }

    /**   You are given an integer array arr of size n. Group and rearrange them (in-place) into
     * evens and odds in such a way that group of all even integers appears on the left side and
     * group of all odd integers appears on the right side.
    Example
    Input: [1, 2, 3, 4]
    Output: [4, 2, 1, 3]
    Order does not matter. Other valid solutions are:
    [2, 4, 1, 3]
    [2, 4, 3, 1]
    [4, 2, 3, 1]*/
    fun groupTheNumbers(array: Array<Int>) {
        var i = 0
        var j = array.size - 1
        while (i < j) {
            while (array[i] % 2 == 0 && i < array.size - 1) {
                i++
            }
            while (array[j] % 2 != 0 && j > 0) {
                j--
            }
            if (i < j) {
                val temp = array[i]
                array[i] = array[j]
                array[j] = temp
                i++
                j--
            }
        }
        Log.d(TAG, "groupTheNumbers: ${array.contentToString()}")
    }

    fun mergeSortPr(array: Array<Int>, start: Int, end: Int): Array<Int> {
        if (start == end) {
            return array
        }
        val mid = (start + end) / 2
        mergeSortPr(array, start, mid)
        mergeSortPr(array, mid + 1, end)
        var i = start
        var j = mid + 1
        var tempArray = intArrayOf()
        while (i <= mid && j <= end) {
            if (array[i] < array[j]) {
                tempArray = tempArray.plus(array[i])
                i++
            } else {
                tempArray = tempArray.plus(array[j])
                j++
            }
        }
        while (i <= mid) {
            tempArray = tempArray.plus(array[i])
            i++
        }
        while (j <= end) {
            tempArray = tempArray.plus(array[j])
            j++
        }
        var m = start
        for (arr in tempArray) {
            array[m] = arr
            m++
        }
        return array
    }

    fun mergerFirstIntoSecond(array1: Array<Int>, array2: Array<Int>): Array<Int> {
        var arr2EndPtr = array2.size - 1
//        var arr2StartPtr = 0
        var arr1EndPtr = array1.size - 1
        var arr2ValEndPtr = (array2.size / 2) - 1

        while (arr1EndPtr >= 0 && arr2ValEndPtr >= 0) {
            if (array1[arr1EndPtr] > array2[arr2ValEndPtr]) {
                array2[arr2EndPtr] = array1[arr1EndPtr]
                arr2EndPtr--
                arr1EndPtr--
            } else {
                array2[arr2EndPtr] = array2[arr2ValEndPtr]
                arr2EndPtr--
                arr2ValEndPtr--
            }
        }

        while (arr1EndPtr >= 0) {
            array2[arr2EndPtr] = array1[arr1EndPtr]
            arr2EndPtr--
            arr1EndPtr--
        }
        Log.d(TAG, "mergerFirstIntoSecond: ${array2.contentToString()}")
        return array2
    }

    fun orderRGB(arr: Array<Char>) {
        var ptr = 0
        var endPtr = arr.size - 1
//        while(arr[ptr]=='R'){
//            ptr++
//        }

        Log.d(TAG, "ptr ::$ptr");
        while (ptr <= endPtr) {
            Log.d(TAG, "R loop::" + arr.contentToString());
            Log.d(TAG, "R loop::ptr ::$ptr");
            Log.d(TAG, "R loop::endPtr ::$endPtr");
            while (arr[ptr] == 'R') {
                ptr++;
            }
            if (arr[endPtr] == 'R') {

                Log.d(TAG, "Before swapping::ptr ::$ptr || endPtr :: $endPtr");
                Log.d(TAG, "SWAPPING::" + arr[ptr] + " && " + arr[endPtr])
                val tmp = arr[ptr]
                arr[ptr] = arr[endPtr]
                arr[endPtr] = tmp
                ptr++
            }
            endPtr--
        }
        endPtr = arr.size - 1;
//        while(arr[ptr]=='G'){
//            ptr++;
//        }
        Log.d(TAG, "ptr ::$ptr");
        while (ptr <= endPtr) {
            Log.d(TAG, "G loop::" + arr.contentToString())
            Log.d(TAG, "G loop::ptr ::$ptr")
            Log.d(TAG, "G loop::endPtr ::$endPtr")
            while (arr[ptr] == 'G') {
                ptr++
            }
            if (arr[endPtr] == 'G') {

                Log.d(TAG, "Before swapping::ptr ::$ptr || endPtr :: $endPtr");
                Log.d(TAG, "SWAPPING::" + arr[ptr] + " && " + arr[endPtr])
                val tmp = arr[ptr]
                arr[ptr] = arr[endPtr]
                arr[endPtr] = tmp
                ptr++
            }
            endPtr--
        }

        Log.d(TAG, arr.contentDeepToString());
    }

    fun test() {
        val words = arrayOf("cat", "baby", "dog", "bird", "car", "ax");
        val string1 = "tcabnihjs";
        val string2 = "tbcanihjs";
        val string3 = "baykkjl";
        val string4 = "bbabylkkj";
        val string5 = "ccc";
        val string6 = "breadmaking";
        Log.d(TAG, find_embedded_word(words, string1));
        Log.d(TAG, find_embedded_word(words, string2));
        Log.d(TAG, find_embedded_word(words, string3));
        Log.d(TAG, find_embedded_word(words, string4));
        Log.d(TAG, find_embedded_word(words, string5));
        Log.d(TAG, find_embedded_word(words, string6));
    }

    fun find_embedded_word(words: Array<String>, matchWord: String): String {
//   val hashMap = getHashMap(matchWord)
//   for(c in matchWord){
//     if(hashMap.containsKey(c)){
//      val value= hashMap.get(c)?:0
//       hashMap.put(c,value.plus(1))
//     }else{
//        hashMap.put(c,1)
//     }
//   }

        words.forEach { word ->
            var count = 0;
            val hashMap = getHashMap(matchWord)
            for (character in word) {

                val value = hashMap[character] ?: 0
                if (!hashMap.containsKey(character) || (value <= 0)) {
                    break;
                } else {
                    count++;
                    hashMap[character] = value.minus(1)
                }
            }
            if (count == word.length) {
                return word;
            }
        }
        return "null";
    }

    fun getHashMap(matchWord: String): HashMap<Char, Int> {
        val hashMap = HashMap<Char, Int>();
        for (c in matchWord) {
            if (hashMap.containsKey(c)) {
                val value = hashMap[c] ?: 0
                hashMap[c] = value.plus(1)
            } else {
                hashMap[c] = 1
            }
        }
        return hashMap;
    }

    fun dutchProb() {
        val result = dutch_flag_sort(arrayListOf( 'B',  'R', 'G'))
//        val result = dutch_flag_sort(arrayListOf('G', 'B', 'G', 'G', 'R', 'B', 'R', 'G'))
//        Log.d(TAG, "dutchProb: ${result.toString()}")
//        Log.d(TAG, " find_integer:: ${find_integer(arrayListOf(4,6,7,1,3,4))}")
        Log.d(TAG, " nearest_neighbours:: ${nearest_neighbours(1,1,1, arrayListOf(arrayListOf(1,0),
            arrayListOf(0,0)))}")
    }


    fun dutch_flag_sort(balls: ArrayList<Char>): ArrayList<Char> {
        // Write your code here.
        var bInsertPtr= balls.size-1
        var rInsertPtr= 0
        var currentPtr=0
        while(currentPtr<bInsertPtr){


            when {
                balls[currentPtr] =='R' -> {
                    val temp = balls[currentPtr]
                    balls[currentPtr]=balls[rInsertPtr]
                    balls[rInsertPtr]=temp
                    rInsertPtr++
                    currentPtr++
                }
                balls[currentPtr] =='G' -> {
                    currentPtr++
                }
                else -> {
                    val temp = balls[currentPtr]
                    balls[currentPtr]=balls[bInsertPtr]
                    balls[bInsertPtr]=temp
                    bInsertPtr--
                }
            }


        }
        return balls
    }


open fun find_integer(arr: ArrayList<Long>): Long? {
    val size = (2.0.pow(32.0) / 8).toInt()
    val bytes = ByteArray(size) // Initialized with zeros by the JVM.
    for (inputValue in arr) {
        val byteIndex = (inputValue / 8).toInt()
        val bitIndex = (inputValue % 8).toInt()
        // Set the bit corresponding to inputValue:
        bytes[byteIndex] = bytes[byteIndex] or (1 shl bitIndex).toByte()
    }
    for (byteIndex in 0 until size) {
        for (bitIndex in 0..7) {
            if (bytes[byteIndex] and (1 shl bitIndex).toByte() == 0.toByte()) {
                // As soon as we have found the first unset bit,
                // return the number corresponding to that bit.
                return byteIndex * 8L + bitIndex
            }
        }
    }
    throw IllegalStateException(
        "Must have found an unset bit and returned from the loop"
    )
}


    fun nearest_neighbours(p_x: Int, p_y: Int, k: Int, n_points: ArrayList<ArrayList<Int>>): ArrayList<ArrayList<Int>> {
        var retVal = ArrayList<ArrayList<Int>>()
        val tempPairArray = ArrayList<Pair<Double,ArrayList<Int>>>()
        n_points.forEach{
            val distance = getDistance(p_x,p_y,it[0],it[1])
            Log.d(TAG, "nearest_neighbours: distance->$distance :: pair ->$it")
            val pairObj = Pair(distance,it)
            tempPairArray.add(pairObj)
        }
        tempPairArray.sortWith(compareBy({it.first}))
        for(i in 0 until k){
            retVal.add(tempPairArray[i].second)
        }

        return retVal
    }

    fun getDistance(p1:Int,p2:Int,q1:Int,q2:Int):Double{
        return Math.sqrt(Math.pow(q1.toDouble()-p1.toDouble(),2.0)+Math.pow(q2.toDouble()-p2.toDouble(),2.0))
    }

    fun rotate(nums: IntArray, k: Int): Unit {
        var strtIdx=0
        var rearIdx= nums.size-1
        var swapIdx= nums.size-1-k
        var count=0
        fun swap(i:Int,j:Int){
            val temp = nums[i]
            nums[i]=nums[j]
            nums[j]=temp
        }
        while(count<k){
            val temp = nums[0]
            swap(rearIdx,0)
            var i = nums.size-1
            while(i>1){
                nums[i]=nums[i-1]
                i--
            }
            nums[i]=temp
            count++
        }
    }
    fun rotateWithReverse(nums: IntArray, k: Int): Unit {
        fun reverse(i:Int,j:Int){
            var m=i
            var n=j
            while(m<n){
                val temp = nums[m]
                Log.d(TAG, "reverse: m:$m")
                Log.d(TAG, "reverse: n:$n")
                nums[m]=nums[n]
                nums[n]=temp
                m++
                n--
            }
        }
        val j =k%nums.size
        reverse(0,nums.size-1)
        reverse(0,j-1)
        reverse(j,nums.size-1)

    }

    fun twoSum(numbers: IntArray, target: Int): IntArray {
        val retVal = IntArray(2)

        var i =0
        var j=numbers.size-1
        while(i <j){
            val sum= numbers[i]+numbers[j]
            when{
                sum == target -> {
                    retVal[0]=i+1
                    retVal[1]=j+1
                    break
                }
                sum < target ->{ i++}
                sum > target ->{j--}
            }

        }
        Log.d(TAG, "twoSum: ${retVal.contentToString()}")
        return retVal

    }

    fun intersect(nums1: IntArray, nums2: IntArray): IntArray {
        val retVal = IntArray(Math.max(nums1.size,nums2.size))
        val hashMap = HashMap<Int,Int>()

        nums1.forEach{
            if(hashMap.contains(it)){
                hashMap.put(it,hashMap.get(it)!!+1)
            }else{
                hashMap.put(it,1)
            }
        }
        var idx=0
        nums2.forEach{
            if(hashMap.contains(it)){
                val count = hashMap.get(it)?:0
                if(count>0){
                    retVal[idx++]=it
                    hashMap.put(it,count-1)
                }
            }
        }

        return retVal

    }

    fun matrixReshape(mat: Array<IntArray>, r: Int, c: Int): Array<IntArray> {
        val m = mat.size
        val n = mat[0].size
        var rItr=0
        var cItr=0
        if((m*n)==(r*c)){
            val retVal = Array<IntArray>(r) { i -> IntArray(c){i * 0 }}
            for(mItr in 0..m-1){
                for(nItr in 0..n-1){
                    retVal[rItr][cItr++]=mat[mItr][nItr]
                    if(cItr==c){
                        cItr=0
                        rItr++
                    }
                }
            }
            return retVal
        }else{
            return mat
        }

    }
}