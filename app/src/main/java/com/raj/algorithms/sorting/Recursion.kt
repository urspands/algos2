package com.raj.algorithms.sorting


fun main() {
//    binaryNumberOfSizeN(3)
//    decimalNumberOfSizeN(3)
//    subsetOf(intArrayOf(1,2,3,4))
//    println("${letterCasePermutation("a1b2")}")
//    permutation(arrayListOf(1,1,2))
//    permutationWithDup(arrayListOf(1, 1, 2))
//    generateParentheses(3)
//    combination(4,2)
    subSetCalWithPosition(4)
//    permute(intArrayOf(1, 3, 2))
//    letterCasePermutation2("c")
//    climbStairs(44)
    //TODO: 17.letter combination of phone
}
/*
70. Climbing Stairs
https://leetcode.com/problems/climbing-stairs/?envType=study-plan&id=algorithm-i
 */
fun climbStairs(n: Int): Int {
    var climbCount=0
    var climb=arrayListOf(1,2)
    fun helper(totalStepsJumped:Int){
        if(totalStepsJumped==n){
            climbCount++
        }else if(totalStepsJumped>n){
            return
        }else{
            climb.forEach{
                helper(totalStepsJumped+it)
            }
        }
    }
    helper(0)
    println(climbCount)
    return climbCount
}
/*
a1b2->a1b2,A1b2,a1B2,A1B2
 */
fun letterCasePermutation2(s: String): List<String> {
    val result = ArrayList<String>()
    fun helper(pos:Int,slate:String){
        if(pos>=s.length){
            result.add(slate)
        }else{
            if(s[pos].isDigit()){
                helper(pos+1,"$slate${s[pos]}")
            }else{
                helper(pos+1,"$slate${s[pos]}")
                helper(pos+1,"$slate${s[pos].uppercase()}")
            }
        }
    }
    helper(0,"")
    println(result)
    return result
}
/*
46. Permutations
https://leetcode.com/problems/permutations/description/?envType=study-plan&id=algorithm-i
 */
fun permute(nums: IntArray): List<List<Int>> {
    val result = ArrayList<ArrayList<Int>>()
    fun helper( temp: ArrayList<Int>, pos: Int) {
        if (pos==temp.size) {
            result.add(ArrayList(temp))
        } else {
            val hashSet = HashSet<Int>()
            for (i in pos until temp.size) {
                if(!hashSet.contains(temp[i])){
                    hashSet.add(temp[i])
                    swap(temp, i, pos)
                    helper( temp, pos + 1)
                    swap(temp, i, pos)
                }
            }
        }
    }
    helper(ArrayList(nums.toMutableList()), 0)
    println(result)
    return result
}

fun subSetCalWithPosition(n: Int): List<List<Int>> {
    var result = ArrayList<ArrayList<Int>>()
    val inputArr = ArrayList<Int>()
    for (i in 1..n) {
        inputArr.add(i)
    }
    println(inputArr)
    fun helper(slate: ArrayList<Int>, pos: Int) {
        if (pos >= inputArr.size) {
            result.add(slate)
        } else {
            slate.add(inputArr[pos])
            helper(ArrayList(slate), pos + 1)
            slate.removeLast()
            helper(slate, pos + 1)
        }
    }
    helper(ArrayList(), 0)
    println(result)
    return result
}

fun combination(n: Int, k: Int): List<List<Int>> {
    var result = ArrayList<ArrayList<Int>>()
    val inputArr = ArrayList<Int>()
    for (i in 1..n) {
        inputArr.add(i)
    }
    println(inputArr)
    fun helper(slate: ArrayList<Int>, pos: Int, input: ArrayList<Int>) {
        if (slate.size == k) {
            result.add(slate)
        } else if (input.size == 0 || pos >= input.size) {
            return
        } else {
            slate.add(input[pos])
            helper(ArrayList(slate), pos + 1, input)
            slate.removeAt(slate.lastIndex)

            helper(ArrayList(slate), pos + 1, input)
        }
    }


    helper(arrayListOf(), 0, inputArr)
    println(result)
    return result
}

/*
47.permutation with duplicate array
 */
fun permutationWithDup(input: ArrayList<Int>) {
    val result = ArrayList<String>()
    fun helper(slate: String, temp: ArrayList<Int>) {
        if (temp.isEmpty()) {
            result.add(slate)
        } else {
            val hashSet = HashSet<Int>()
            temp.forEachIndexed { i, value ->
                if (!hashSet.contains(value)) {
                    hashSet.add(value)
                    val temp1 = temp.slice(0 until i).toMutableList()
                    temp1.addAll(temp.slice(i + 1 until temp.size))
                    helper(slate + value, ArrayList(temp1))
                }
            }
        }
    }
    helper("", input)
    result.forEach { println(it) }
}

/*
   22. generate parentheses ()
 */
fun generateParentheses(n: Int) {
    val results = ArrayList<String>()
    fun helper(open: Int, close: Int, slate: String) {
        if (slate.length == n * 2) {
            results.add(slate)
        }
        if (open < n) {
            helper(open + 1, close, "${slate}(")
        }
        if (close < open) {
            helper(open, close + 1, "${slate})")
        }
    }
    helper(0, 0, "")
    results.forEach { println(it) }

}

/*
 46. permutation
 */
fun permutation(input: ArrayList<Int>) {
    val result = ArrayList<String>()
    fun helper(slate: String, temp: ArrayList<Int>) {
        if (temp.isEmpty()) {
            result.add(slate)
        } else {
            temp.forEachIndexed { i, value ->
                val temp1 = temp.slice(0 until i).toMutableList()
                temp1.addAll(temp.slice(i + 1 until temp.size))
                helper(slate + value, ArrayList(temp1))
            }
        }
    }
    helper("", input)
    result.forEach { println(it) }
}

/*
784.letterCasePermutation
 */
fun letterCasePermutation(input: String) {
    val result = ArrayList<String>()
    fun helper(arrayBag: String, slate: String) {
        if (arrayBag.isEmpty()) {
            result.add(slate)
        } else {
            val currentChar = arrayBag[0]
            helper(arrayBag.subSequence(1, arrayBag.length).toString(), "$slate${currentChar}")
            if (currentChar.isLetter()) {
                helper(
                    arrayBag.subSequence(1, arrayBag.length).toString(),
                    "$slate${currentChar.uppercaseChar()}"
                )
            }

        }
    }
    helper(input, "")
    result.forEach { println(it) }
}

//Combination
fun subsetOf(array: IntArray) {
    val result = ArrayList<String>()
    fun helper(slate: String, arrayBag: IntArray) {
        if (arrayBag.isEmpty()) {
            result.add(slate)
        } else {
            //inclusion
            helper("${slate}${arrayBag[0]}", arrayBag.sliceArray(1 until arrayBag.size))
            //exclusion
            helper("$slate", arrayBag.sliceArray(1 until arrayBag.size))
        }
    }

    helper("", array)
    result.forEach { println(it) }
}

//Permutation
fun binaryNumberOfSizeN(n: Int) {
    val result = ArrayList<String>()
    fun helper(size: Int, slate: String) {
        if (slate.length == size) {
            result.add(slate)

        } else {
            helper(size, "${slate}0")
            helper(size, "${slate}1")
        }
    }
    helper(n, "")
    result.forEach { println(it) }
}

//Permutation
fun decimalNumberOfSizeN(n: Int) {
    val result = ArrayList<String>()
    fun helper(size: Int, slate: String) {
        if (slate.length == size) {
            result.add(slate)

        } else {
            for (i in 0..9)
                helper(size, "${slate}${i.toString()}")
        }
    }
    helper(n, "")
    result.forEach { println(it) }
}

fun generate_palindromic_decompositions(s: String): ArrayList<String> {
    // Write your code here.
    val result = ArrayList<String>()

    fun helper(s: String, pos: Int, slate: String, lastString: String) {
        if (pos == s.length) {
            val isPalindrome = isPalindrome(lastString)
//                System.out.println("helper:isPalindrome->${isPalindrome}:: lastString->$lastString ")
            if (isPalindrome) {
                println(
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
    if (s.isEmpty()) {
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
    val boardSize = board.size
    fun solveBoard(): Boolean {
        for (row in 0 until boardSize) {
            for (col in 0 until boardSize) {
                if (board[row][col] == 0) {
                    for (num in 1..boardSize) {
                        if (isValidPlacement(board, row, col, num)) {
                            board[row][col] = num
                            if (solveBoard()) {
                                return true
                            } else {
                                board[row][col] = 0
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

fun isNumberInRow(board: ArrayList<ArrayList<Int>>, row: Int, number: Int): Boolean {
    for (i in 0..8) {
        if (board[row][i] == number) {
            return true
        }
    }
    return false

}

fun isNumberInColumn(board: ArrayList<ArrayList<Int>>, col: Int, number: Int): Boolean {
    for (i in 0..8) {
        if (board[i][col] == number) {
            return true
        }
    }
    return false

}

fun isNumberInBox(board: ArrayList<ArrayList<Int>>, row: Int, col: Int, number: Int): Boolean {
    val boxRowStartPos = row - (row % 3)
    val boxColStartPos = col - (col % 3)
    for (i in boxRowStartPos..boxRowStartPos + 2) {
        for (j in boxColStartPos..boxColStartPos + 2) {
            if (board[i][j] == number) {
                return true
            }
        }
    }
    return false
}

fun isValidPlacement(board: ArrayList<ArrayList<Int>>, row: Int, col: Int, number: Int): Boolean {
    return !isNumberInRow(board, row, number) && !isNumberInColumn(
        board,
        col,
        number
    ) && !isNumberInBox(board, row, col, number)
}

fun generate_all_expressions(s: String, target: Long): ArrayList<String> {

    val retVal = ArrayList<String>()
    fun helper(pos: Int, slate: String, sum: Int, product: Int, currInt: Int) {
        //base case
        if (pos == s.length) {
            if ((sum + (product * currInt)).toLong() == target) {
                retVal.add(slate)
            }
            return
        }

        //concatenate
        helper(pos + 1, slate + s[pos], sum, product, (product * 10) + (s[pos] - '0'))

        //add +
        helper(pos + 1, slate + "+" + s[pos], sum + (s[pos] - '0'), product, s[pos] - '0')

        //
        helper(pos + 1, slate + "*" + s[pos], sum, (sum + (product * (s[pos] - '0'))), s[pos] - '0')

    }
    helper(1, s[0].toString(), 0, 1, s[0] - '0')

    return retVal
}

fun find_all_arrangements(n: Int): ArrayList<ArrayList<String>> {
    // Write your code here.

    val retVal = ArrayList<ArrayList<String>>()
    val temp = ArrayList<ArrayList<Int>>()
    fun helper(row: Int, slate: ArrayList<Int>) {
        if (!isPlacementValid(slate, n)) {
            return
        }
        if (row == n) {
            temp.add(ArrayList(slate))
            return
        }
        for (col in 0..n - 1) {
            slate.add(col)
            helper(row + 1, slate)
            slate.removeLast()
        }
    }

    helper(0, arrayListOf())
    System.out.println("find_all_arrangements: $temp")
    temp.forEach {
        val arr = ArrayList<String>()
        for (i in 0..n - 1) {
            var rowVal = ""
            for (j in 0..n - 1) {
                if (it[i] == j) {
                    rowVal = rowVal.plus("q")
                } else {
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

fun isPlacementValid(slate: ArrayList<Int>, n: Int): Boolean {
    if (slate.size <= 1) {
        return true
    }
    val lastQ = slate.size - 1
    for (q in 0..lastQ - 1) {
        if (slate[lastQ] == slate[q]) {
            return false
        }

        if (Math.abs(lastQ - q) == Math.abs(slate[lastQ] - slate[q])) {
            return false
        }

    }
    return true

}

