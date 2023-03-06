package com.raj.algorithms.sorting

import java.util.*
import kotlin.collections.ArrayList

class OfferApi {
    private val buyList = ArrayList<Int>()
    private val sellList = ArrayList<Int>()
    private val buyListHeap = PriorityQueue<Int>()

    fun makeBuyOffer(buyOffer: Int): Boolean {
        if(sellList.isNotEmpty()){
            sellList.sortedWith(compareBy { it })
        }
        val match = if (sellList.isEmpty()) {
            false
        } else {
            (buyOffer >= sellList.first())
        }
        if (!match) {
            buyList.add(buyOffer)
        }else{
            sellList.remove(sellList.first())
        }
        return match
    }

    fun makeSellOffer(sellOffer: Int): Boolean {
        if(buyList.isNotEmpty()){
            buyList.sortedWith(compareBy { it })
        }
        val match = if (buyList.isEmpty()) {
            false
        } else {
            sellOffer <= buyList.last()
        }
        if (!match) {
            sellList.add(sellOffer)
        }else{
            buyList.remove(buyList.last())
        }
        return match
    }

    fun getBuyList(): ArrayList<Int> {
        return buyList
    }

    fun getSellList(): ArrayList<Int> {
        return sellList
    }
}