package com.raj.algorithms.sorting

import android.util.Log

object DynamicProgramming {
    private const val TAG = "DynamicProgramming"
    fun fibonacci(n: Int) {
        val memo = ArrayList<Int>()
        memo.add(0, 0)
        memo.add(1, 1)
        System.out.println("fibonacci: ${fib(n, memo)}")
    }

    private fun fib(n: Int, memo: ArrayList<Int>): Int {

        if (n < memo.size && memo[n] != null) {
            return memo[n];
        }

        memo.add(n, fib(n - 2, memo) + fib(n - 1, memo))
        return memo.get(n)
    }

    fun countUniquePath(mRow: Int, nCol: Int) {
        val table = Array(mRow) { IntArray(nCol) }
        for (i in 0 until mRow) {
            table[i][0] = 1
        }
        for (j in 0 until nCol) {
            table[0][j] = 1
        }

        for (row in 1 until mRow) {
            for (col in 1 until nCol) {
                table[row][col] = table[row - 1][col] + table[row][col - 1]
            }
        }
        System.out.println("countUniquePath: ${table[mRow - 1][nCol - 1]}")
    }

    //priceArray={0,1,2,3,5,10}
    fun calculateMaxValueToCutRod(rodLength: Int, pricesArray: Array<Int>) {
        val dpArray= Array<Int>(rodLength) { 0 }
        dpArray[0]=0

    }

    fun equalSumSubsetPartition(array: Array<Int>){
        var sum= array.sum()
    }
//    fun equalSumSubsetPartitionHelper(array: Array<Int>):Boolean{
//
//
//    }
}