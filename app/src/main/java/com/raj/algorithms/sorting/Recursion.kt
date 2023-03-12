package com.raj.algorithms.sorting

import android.util.Log

object Recursion {
    const val TAG = "Recursion"
    fun generate_palindromic_decompositions(s: String): ArrayList<String> {
        // Write your code here.
        val result = ArrayList<String>()

        fun helper(s: String, pos: Int, slate: String, lastString: String) {
            if (pos == s.length) {
                val isPalindrome = isPalindrome(lastString)
//                System.out.println("helper:isPalindrome->${isPalindrome}:: lastString->$lastString ")
                if (isPalindrome) {
                    Log.d(
                        TAG,
                        "helper:isPalindrome->${isPalindrome}:: lastString->$lastString :: slate ->$slate"
                    )
                    result.add(slate)
                }
                return
            }
            helper(s, pos + 1, slate + s[pos], lastString + s[pos])
            if (!isPalindrome(lastString)) {
                return
            }
            helper(s, pos + 1, slate + "|" + s[pos], s[pos].toString())
        }
        helper(s, 0, "", "")
        return result
    }

    fun isPalindrome(s: String): Boolean {
        if(s.isEmpty()){
            return false
        }
        var i = 0
        var j = s.length - 1
        while (i <= j) {
            if (s[i] != s[j]) {
                return false
            }
            i++
            j--
        }

        return true
    }

    fun solve_sudoku_puzzle(board: ArrayList<ArrayList<Int>>): ArrayList<ArrayList<Int>> {
        val  boardSize = board.size
        fun solveBoard():Boolean{
            for(row in 0 until boardSize){
                for(col in 0 until boardSize){
                    if(board[row][col]==0){
                        for(num in 1..boardSize){
                            if(isValidPlacement(board,row,col,num)){
                                board[row][col]=num
                                if(solveBoard()){
                                    return true
                                }else{
                                    board[row][col]=0
                                }
                            }


                        }
                        return false
                    }
                }
            }
            return true
        }
        solveBoard()

        return board
    }

    fun isNumberInRow(board: ArrayList<ArrayList<Int>>, row:Int,number:Int):Boolean{
        for( i in 0..8){
            if(board[row][i]==number){
                return true
            }
        }
        return false

    }
    fun isNumberInColumn(board: ArrayList<ArrayList<Int>>, col:Int,number:Int):Boolean{
        for( i in 0..8){
            if(board[i][col]==number){
                return true
            }
        }
        return false

    }

    fun isNumberInBox(board: ArrayList<ArrayList<Int>>,row:Int, col:Int,number:Int):Boolean{
        val boxRowStartPos= row - (row%3)
        val boxColStartPos = col -(col%3)
        for(i in boxRowStartPos..boxRowStartPos+2){
            for(j in boxColStartPos..boxColStartPos+2){
                if(board[i][j]==number){
                    return true
                }
            }
        }
        return false
    }

    fun isValidPlacement(board: ArrayList<ArrayList<Int>>,row:Int, col:Int,number:Int):Boolean{
        return !isNumberInRow(board,row,number) && !isNumberInColumn(board,col,number) && !isNumberInBox(board,row,col,number)
    }

    fun generate_all_expressions(s: String, target: Long): ArrayList<String> {

        val retVal = ArrayList<String>()
        fun helper(pos: Int,slate: String, sum: Int, product: Int, currInt: Int){
            //base case
            if(pos == s.length){
                if((sum + (product * currInt)).toLong() == target){
                    retVal.add(slate)
                }
                return
            }

            //concatenate
            helper(pos+1,slate+s[pos],sum,product, (product *10)+(s[pos] -'0'))

            //add +
            helper(pos+1,slate+"+"+s[pos],sum+(s[pos]-'0'),product,s[pos]-'0')

            //
            helper(pos+1,slate+"*"+s[pos],sum, (sum+(product*(s[pos]-'0'))),s[pos]-'0')

        }
        helper(1,s[0].toString(),0,1,s[0] -'0')

        return retVal
    }

    fun find_all_arrangements(n: Int): ArrayList<ArrayList<String>> {
        // Write your code here.

        val retVal = ArrayList<ArrayList<String>>()
        val temp = ArrayList<ArrayList<Int>>()
        fun helper(row: Int, slate: ArrayList<Int>){
            if(!isPlacementValid(slate,n)){
                return
            }
            if(row==n){
                temp.add(ArrayList(slate))
                return
            }
            for(col in 0..n-1){
                slate.add(col)
                helper(row+1,slate)
                slate.removeLast()
            }
        }

        helper(0,arrayListOf())
        System.out.println("find_all_arrangements: $temp")
        temp.forEach{
            val arr = ArrayList<String>()
            for( i in 0..n-1){
                var rowVal=""
                for( j in 0..n-1){
                    if(it[i]==j){
                        rowVal = rowVal.plus("q")
                    }else{
                        rowVal = rowVal.plus("-")
                    }
                }
                arr.add(rowVal)
            }
            retVal.add(arr)
        }
        System.out.println("find_all_arrangements: $retVal")
        return retVal
    }

    fun isPlacementValid(slate: ArrayList<Int>, n: Int):Boolean{
        if(slate.size<=1){
            return true
        }
        val lastQ= slate.size-1
        for( q in 0..lastQ-1){
            if(slate[lastQ]==slate[q]){
                return false
            }

            if(Math.abs(lastQ - q) == Math.abs(slate[lastQ] - slate[q])){
                return false
            }

        }
        return true

    }

}