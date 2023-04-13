package com.raj.algorithms.practicev2

fun main(){

}
fun isIsomorphic(s: String, t: String): Boolean {
    val hashMap:HashMap<Char,Char> = HashMap()
    s.forEachIndexed{i,value->
        if(!hashMap.containsKey(value)){
            hashMap[value]=t[i]
        }else{
            if(hashMap[value]!=t[i]){
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
    if(p?.`val`!! > root?.`val`!! && q?.`val`!! > root?.`val`!!){
        return lowestCommonAncestor(root?.right,p,q)
    }
    if((p?.`val`!!< root?.`val`!!) && (q?.`val`!!< root?.`val`!!)){
        return lowestCommonAncestor(root?.left,p,q)
    }
    return root
}