package com.raj.algorithms.practicev2

import java.util.*
import kotlin.collections.HashMap

fun main() {
//    println(
//        backspaceCompare2(
//            "y#fo##f",
//            "y#f#o##f"
//        )
//    )
    println("${decodeString("3[a]2[bc]")}")
}

data class GpsData(val gps:String,val timeStamp:Long)
private val _cache = HashMap<Int,ArrayList<GpsData>>()
fun setLocation(officerId:Int,location:String, timeStamp:Long){
    val gpsData =  _cache.getOrDefault(officerId,ArrayList<GpsData>())
    gpsData.add(GpsData(location,timeStamp))
    _cache[officerId] = gpsData
}

fun getLocation(officerId:Int,timeStamp:Long):String{
    val gpsData = _cache[officerId]
    gpsData?.let{ it ->
        it.sortBy{it.timeStamp}
        for(i in it.indices){
            if(it[i].timeStamp == timeStamp){
                return it[i].gps
            }else{
                val previousIdx = i-1
//       val nextIdx=i+1
                if(previousIdx in it.indices){
                    if(it[previousIdx].timeStamp < timeStamp && timeStamp< it[i].timeStamp){
                        val diff = it[previousIdx].timeStamp+ (it[i].timeStamp - it[previousIdx].timeStamp)/2
                        return if(it[i].timeStamp < diff) it[previousIdx].gps else it[i].gps
                    }
                }
            }
        }
        return it[it.size-1].gps
    }?:return ""

}
fun isValidBST(root: TreeNode?): Boolean {
    var previousNode:TreeNode?=null
    fun helper(root:TreeNode?):Boolean{
        if(root==null){
            return true
        }
        val isBst = helper(root.left)
        if(previousNode!=null){
            if(previousNode!!.`val`> root.`val`){
                return false
            }
        }
        previousNode= root
        if(isBst){
            return  helper(root.right)
        }else{
            return false
        }

    }
    return helper(root)
}
fun decodeString(s: String): String {
    val numStack = Stack<Int>()
    val strStack = Stack<String>()
    var result = ""
    var idx = 0
    while (idx < s.length) {
        when {
            s[idx].isDigit() -> {
                var count = 0
                while (s[idx].isDigit()) {
                    count = 10 * count + s[idx].digitToInt()
                    idx++
                }
                numStack.push(count)
            }
            s[idx] == '[' -> {
                strStack.push(result)
                result = ""
                idx++
            }
            s[idx] == ']' -> {
                val strBuilder = StringBuilder(strStack.pop())
                val count = numStack.pop()
                repeat(count) {
                    strBuilder.append(result)
                }
                result = strBuilder.toString()
                idx++
            }
            else -> {
                result = result.plus(s[idx])
                idx++
            }

        }
    }
    return result
}

fun isIsomorphic(s: String, t: String): Boolean {
    val hashMap: HashMap<Char, Char> = HashMap()
    s.forEachIndexed { i, value ->
        if (!hashMap.containsKey(value)) {
            hashMap[value] = t[i]

        } else {
            if (hashMap[value] != t[i]) {
                return false
            }
        }
    }
    return true
}

// class TreeNode(var `val`: Int = 0) {
//         var left: TreeNode? = null
//         var right: TreeNode? = null
//     }
fun lowestCommonAncestor(root: TreeNode?, p: TreeNode?, q: TreeNode?): TreeNode? {
    if (p?.`val`!! > root?.`val`!! && q?.`val`!! > root?.`val`!!) {
        return lowestCommonAncestor(root?.right, p, q)
    }
    if ((p?.`val`!! < root?.`val`!!) && (q?.`val`!! < root?.`val`!!)) {
        return lowestCommonAncestor(root?.left, p, q)
    }
    return root
}

fun backspaceCompare2(s: String, t: String): Boolean {
    val hm1 = Stack<Char>()
    val hm2 = Stack<Char>()
    s.forEach {
        if (it == '#') {
            if (hm1.isNotEmpty())
                hm1.pop()
        } else {
            hm1.push(it)
        }
    }
    t.forEach {
        if (it == '#') {
            if (hm2.isNotEmpty())
                hm2.pop()
        } else {
            hm2.push(it)
        }
    }
//    while (hm1.isNotEmpty() && hm2.isNotEmpty()) {
//        if (hm1.pop() != hm2.pop()) {
//            return false
//        }
//    }
    println(hm1.toString())
    println(hm2.toString())
    return hm1 == hm2
}