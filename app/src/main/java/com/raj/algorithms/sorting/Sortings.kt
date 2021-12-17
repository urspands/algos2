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
}