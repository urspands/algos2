package com.raj.algorithms

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.android.material.snackbar.Snackbar
import com.raj.algorithms.databinding.ActivityMainBinding
import com.raj.algorithms.sorting.OfferApi
import com.raj.algorithms.sorting.Recursion

class MainActivity : AppCompatActivity() {
    companion object {
        const val TAG = "MainActivity"
    }

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        Recursion.generate_palindromic_decompositions("abracadabra")
        val board = arrayListOf(
            arrayListOf(8, 4, 9, 0, 0, 3, 5, 7, 0),
            arrayListOf(0, 1, 0, 0, 0, 0, 0, 0, 0),
            arrayListOf(7, 0, 0, 0, 9, 0, 0, 8, 3),
            arrayListOf(0, 0, 0, 9, 4, 6, 7, 0, 0),
            arrayListOf(0, 8, 0, 0, 5, 0, 0, 4, 0),
            arrayListOf(0, 0, 6, 8, 7, 2, 0, 0, 0),
            arrayListOf(5, 7, 0, 0, 1, 0, 0, 0, 4),
            arrayListOf(0, 0, 0, 0, 0, 0, 0, 1, 0),
            arrayListOf(0, 2, 1, 7, 0, 0, 8, 6, 5),
        )
        val api = OfferApi()
        Log.d(TAG, "onCreate:makeBuyOffer: ${api.makeBuyOffer(95)}")
        Log.d(TAG, "onCreate:makeSellOffer: ${api.makeSellOffer(105)}")
        Log.d(TAG, "onCreate:makeBuyOffer: ${api.makeBuyOffer(101)}")
        Log.d(TAG, "onCreate:makeSellOffer: ${api.makeSellOffer(90)}")
        Log.d(TAG, "onCreate:makeSellOffer: ${api.makeSellOffer(97)}")
        Log.d(TAG, "onCreate:buyList: ${api.getBuyList()}")
        Log.d(TAG, "onCreate:sellList: ${api.getSellList()}")
//        Recursion.solve_sudoku_puzzle(board)
//        Recursion.find_all_arrangements(4)
//        Sortings.rotate(intArrayOf(1,2,3,4,5,6,7),3)
//        Sortings.rotate(intArrayOf(-1,-100,3,99),2)
//        Sortings.rotateWithReverse(intArrayOf(-1),2)
//        Sortings.twoSum(intArrayOf(2,3,4),6)
//        Sortings.intersect(intArrayOf(1,2,2,1),intArrayOf(2,2))
//        Sortings.dutchProb()
//        Sortings.selectionSort(arrayOf(10,5,4,6,2,7,1))
//        Sortings.test()
//        Sortings.bubbleSort(arrayOf(10,5,4,6,2,7,1))
//        Sortings.insertionSort(arrayOf(10,5,4,6,2,7,1))
//        val array = arrayOf(7, 5, 8, 3, 9, 4, 1, 7)
//        Log.d(
//            "MergeSort",
//            "onCreate: ${Sortings.mergeSortPr(array, 0, array.size - 1).contentToString()}"
//        )
//        Sortings.findIf2NumSumExists(arrayOf(10,5,4,6,2,7,1),21)
//        Sortings.findIf2NumSumExistsUsingHashSet(arrayOf(10,5,4,6,2,7,1),8)
//        Sortings.findIf2NumSumExistsReturnIndices(arrayOf(10,5,4,6,2,7,1),10)
//        Sortings.canAttendMeeting(arrayOf(arrayOf(3, 6), arrayOf(1, 2), arrayOf(3, 4)))
//        Sortings.find3numbsSumsTo0(arrayOf(-1,1,0,2,-1,4))
//        Sortings.intersectionOf3SortedArrays(arrayOf(-1,2,3,4,5),arrayOf(-1,2,5,7,9),arrayOf(-1,3,4,5,8))
//        Sortings.groupTheNumbers(arrayOf(8,4,9,5,2,9,5,7,10))
//        Sortings.mergerFirstIntoSecond(arrayOf(1, 3, 5), arrayOf(2, 4, 6, 0, 0, 0))
//        Sortings.orderRGB(arrayOf('R','B','R','G','B','R','R','G'))
//        Sortings.orderRGB(arrayOf('G', 'B', 'G', 'G', 'R', 'B', 'R', 'G'))
//        Trees.runTreeProblemFunctions()
//        TreesProb.testProb()
//        DynamicProgramming.fibonacci(10)
//        DynamicProgramming.countUniquePath(3, 3)

//        DyProgJava.equalSumSubsetPartition(intArrayOf(2,3,4,5,6))
//        DyProgJava.editDistance("sunday", "saturday")

//        DyProgJava.count_ways_to_climb(arrayListOf(1,3,5), 9);
//        DyProgJava.minimum_coins(arrayListOf(3, 2, 1, 7, 5, 6, 4), 999);
//        DyProgJava.can_reach_last_house(arrayListOf(3, 1, 1, 0, 2, 4));
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}