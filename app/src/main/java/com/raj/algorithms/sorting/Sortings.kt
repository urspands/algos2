package com.raj.algorithms.sorting

import android.util.Log

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

    fun orderRGB(arr:Array<Char>){
        var ptr =0
        var endPtr = arr.size-1
//        while(arr[ptr]=='R'){
//            ptr++
//        }

        Log.d(TAG,"ptr ::$ptr");
        while(ptr<=endPtr){
            Log.d(TAG,"R loop::"+ arr.contentToString());
            Log.d(TAG, "R loop::ptr ::$ptr");
            Log.d(TAG, "R loop::endPtr ::$endPtr");
            while(arr[ptr]=='R'){
                ptr++;
            }
            if(arr[endPtr] =='R'){

                Log.d(TAG, "Before swapping::ptr ::$ptr || endPtr :: $endPtr");
                Log.d(TAG,"SWAPPING::"+arr[ptr] +" && "+arr[endPtr])
                val tmp = arr[ptr]
                arr[ptr]=arr[endPtr]
                arr[endPtr]=tmp
                ptr++
            }
            endPtr--
        }
        endPtr = arr.size-1;
//        while(arr[ptr]=='G'){
//            ptr++;
//        }
        Log.d(TAG,"ptr ::$ptr");
        while(ptr<=endPtr){
            Log.d(TAG,"G loop::"+arr.contentToString())
            Log.d(TAG,"G loop::ptr ::$ptr")
            Log.d(TAG,"G loop::endPtr ::$endPtr")
            while(arr[ptr]=='G'){
                ptr++
            }
            if(arr[endPtr] =='G'){

                Log.d(TAG, "Before swapping::ptr ::$ptr || endPtr :: $endPtr");
                Log.d(TAG,"SWAPPING::"+arr[ptr] +" && "+arr[endPtr])
                val tmp = arr[ptr]
                arr[ptr]=arr[endPtr]
                arr[endPtr]=tmp
                ptr++
            }
            endPtr--
        }

        Log.d(TAG,arr.contentDeepToString());
    }

}