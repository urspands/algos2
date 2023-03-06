package com.raj.algorithms.sorting;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;

public class DyProgJava {
    public static final String TAG = "DyProgJava";

    public static void equalSumSubsetPartition(int[] array) {
        int sum = Arrays.stream(array).sum();
        if (sum % 2 != 0) {
            Log.d(TAG, "equalSumSubsetPartition: false");
        } else {
            int subsetSum = sum / 2;
            int length = array.length;
            Log.d(TAG, "equalSumSubsetPartition: " + equalSumSubsetPartitionHelper(array, length, subsetSum));
        }
    }

    private static boolean equalSumSubsetPartitionHelper(int[] array, int length, int subsetSum) {
        if (subsetSum == 0) {
            return true;
        }
        if (subsetSum < 0 || length == 0) {
            return false;
        }
        return equalSumSubsetPartitionHelper(array, length - 1, subsetSum - array[length - 1]) || equalSumSubsetPartitionHelper(array, length - 1, subsetSum);
    }

    /**
     * * Given two strings how many minimum edits(update, delete or add) is needed to convert one string to another
     */
    public static void editDistance(String str1, String str2) {
//        int count = recEditDistance(str1.toCharArray(), str2.toCharArray(), 0, 0);
        int[][] memo = new int[str1.length()][str2.length()];
        for (int i = 0; i < str1.length(); i++) {
            Arrays.fill(memo[i], -1);
        }
        int count = editDistanceHelper(str1.toCharArray(), str2.toCharArray(), 0, 0, memo);
        Log.d(TAG, "editDistance: " + count);
        dynamicEditDistance(str1, str2);
    }

    private static int editDistanceHelper(char[] str1, char[] str2, int str1Idx, int str2Idx, int[][] memo) {
        if (str1Idx == str1.length) {
            return str2.length - str2Idx;
        }
        if (str2Idx == str2.length) {
            return str1.length - str1Idx;
        }
        if (memo[str1Idx][str2Idx] != -1) {
            return memo[str1Idx][str2Idx];
        }
        if (str1[str1Idx] == str2[str2Idx]) {
            return memo[str1Idx][str2Idx] = editDistanceHelper(str1, str2, str1Idx + 1, str2Idx + 1, memo);
        } else {
            int insert = editDistanceHelper(str1, str2, str1Idx, str2Idx + 1, memo);
            int delete = editDistanceHelper(str1, str2, str1Idx + 1, str2Idx, memo);
            int replace = editDistanceHelper(str1, str2, str1Idx + 1, str2Idx + 1, memo);
            return memo[str1Idx][str2Idx] = 1 + (min(insert, delete, replace));
        }
    }

    private static int dynamicEditDistance(String str1, String str2) {
        int[][] dp = new int[str1.length() + 1][str2.length() + 1];
        for (int i = 0; i < dp[0].length; i++) {
            dp[0][i] = i;
        }
        for (int j = 0; j < dp.length; j++) {
            dp[j][0] = j;
        }
        for (int i = 1; i <= str1.length(); i++) {
            for (int j = 1; j <= str2.length(); j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + min(dp[i][j - 1], dp[i - 1][j], dp[i - 1][j - 1]);
                }
            }
        }
        Log.d(TAG, "dynamicEditDistance: " + dp[str1.length()][str2.length()]);
        return dp[str1.length()][str2.length()];
    }

//    Count Ways To Reach The Nth Step
//    There is a staircase with n steps. A person standing at the 0-th step wants to reach the n-th one. They are capable of jumping up by certain numbers of steps at a time.
//
//    Given how the person can jump, count the number of ways they can reach the top.


    public static int count_ways_to_climb(ArrayList<Integer> steps, Integer n) {
        ArrayList<Integer> count = new ArrayList<Integer>() {{
            add(0);
        }};
        count_ways_to_climbHelper(steps, n, count);
        Log.d(TAG, "count_ways_to_climb: " + count.get(0));
        return 0;
    }

    static void count_ways_to_climbHelper(ArrayList<Integer> steps, Integer n, ArrayList<Integer> count) {
        if (n == 0) {
            count.set(0, count.get(0) + 1);
            return;
        }
        if (n < 0) {
            return;
        }
        for (int step : steps) {
            count_ways_to_climbHelper(steps, n - step, count);
        }
    }

    //    Given a variety of coin types defining a currency system, find the minimum number of coins required to express a given amount of money. Assume infinite supply of coins of every type.
//
//    Example
//    {
//        "coins": [1, 3, 5],
//        "value": 9
//    }
    public static void minimum_coins(ArrayList<Integer> coins, Integer value) {
        int[][] memo = new int[coins.size() + 1][value + 1];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        int count = minimum_coinsHelper(coins, coins.size(), value, memo);

        Log.d(TAG, "minimum_coins: " + count);

    }

    static Integer minimum_coinsHelper(ArrayList<Integer> coins, Integer coinsIndex, Integer value, int[][] memo) {
        Log.d(TAG, "minimum_coinsHelper: value ::" + value);
        if (value == 0) {
            Log.d(TAG, "minimum_coinsHelper: returning 0 for value 0 and coinsIndex :" + coinsIndex);
            return 0;
        }
        if (value < 0) {
            Log.d(TAG, "minimum_coinsHelper: returning Integer.MAX_VALUE for value ::" + value
                    + " and coinsIndex :" + coinsIndex);
            return 10000;
        }
        Log.d(TAG, "minimum_coinsHelper: coinsIndex::" + coinsIndex);
        if (coinsIndex == 0) {
            Log.d(TAG, "minimum_coinsHelper: returning 0 for coinsIndex 0 and value :" + value);
            return 100000;
        }
        int count = -1;
        if (memo[coinsIndex][value] == -1) {
            memo[coinsIndex][value] = Math.min(minimum_coinsHelper(coins, coinsIndex - 1, value, memo),
                    1 + minimum_coinsHelper(coins, coinsIndex, (value - coins.get(coinsIndex - 1)), memo));
        }
        count = memo[coinsIndex][value];

        Log.d(TAG, "minimum_coinsHelper: count::" + count);
        return count;
    }


//    Jump Game
//    Given a list of maximum jump lengths from different houses, determine if you can reach the last house in one or more jumps starting from the first one.
//
//    Maximum jump length of 2 from a house, for example, means that you can either jump to the next house or to the one after next.
//
//    Example One
//    {
//        "maximum_jump_lengths": [2, 3, 1, 0, 4, 7]
//    }

   public static Boolean can_reach_last_house(ArrayList<Integer> maximum_jump_lengths) {
        boolean[] retValue = new boolean[1];
       int[][] memo = new int[0][];
       try {
           memo = new int[maximum_jump_lengths.size()][maximum_jump_lengths.size()];
       } catch (Exception e) {
           e.printStackTrace();
       }
       for (int[] row : memo) {
           Arrays.fill(row, -1);
       }
        boolean returnValue = can_reach_last_houseHelper(maximum_jump_lengths, maximum_jump_lengths.size() - 1, 0, retValue);
        Log.d(TAG, "can_reach_last_house: " + returnValue);
        return returnValue;
    }

    static Boolean can_reach_last_houseHelper(ArrayList<Integer> maximum_jump_lengths, int destination, int currentIndex, boolean[] retValue) {
        if (currentIndex == destination) {
            return true;
        }
        if (currentIndex > destination) {
            return false;
        }
        if (maximum_jump_lengths.get(currentIndex) == 0) {
            return false;
        }

        for (int i = maximum_jump_lengths.get(currentIndex); i >= 1; i--) {
//            if (retValue)
            retValue[0] = retValue[0] || can_reach_last_houseHelper(maximum_jump_lengths, destination, currentIndex + i, retValue);

        }

        return retValue[0];
    }

    private static int min(int a, int b, int c) {
        int l = Math.min(a, b);
        return Math.min(l, c);
    }
}
