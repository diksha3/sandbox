package com.diksha;

public class CoinChange2 {

    public static void main(String[] args) {
        CoinChange2 coinChange2 = new CoinChange2() ;
        int[] coins = {1,2,5} ;
        coinChange2.change(5,coins) ;
    }
    public int change(int amount, int[] coins) {
        if (amount == 0) {
            return 0;
        }
        int count = 0 ;
// Min coins amount for every particular sum
        int[] dp = new int[amount + 1];
// To get 0 we need 0 coins
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            dp[i] = Integer.MAX_VALUE;
        }

        for (int sum = 1; sum <= amount; sum++) {
// Try to get the sum using every coin as (sum - coin denomination) + this coin
            for (int j = 0; j < coins.length; j++) {
                if (sum >= coins[j] && dp[sum-coins[j]] != Integer.MAX_VALUE) {
// Check if we add this coin to (sum - coinDenomination), will it be better?
                    dp[sum] = Math.min(dp[sum], 1 + dp[sum - coins[j]]);
                    if(dp[sum]!=Integer.MAX_VALUE) {
                        count++ ;
                    }
                }
            }

        }
        return count ;
    }
}