package com.raj.algorithms

import com.raj.algorithms.sorting.OfferApi
import org.junit.Test

class OfferApiTest {
    private val offerApi= OfferApi()

    @Test
    fun testMakeBuyOfferWithEmptySellList(){
        offerApi.getSellList().clear()
        assert(!offerApi.makeBuyOffer(10))
    }

    @Test
    fun testMakeBuyOfferWithSellListValueLessThanBuyOffer(){
        offerApi.getSellList().clear()
        offerApi.makeSellOffer(90)
        assert(offerApi.makeBuyOffer(100))
    }
}