package dynamic_programming;

import java.util.Arrays;

@SuppressWarnings("Duplicates")
public class CoinChange {
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        //we do not initialize the value to Integer.MAX_VALUE to avoid overflow
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        for(int i = 0; i <= amount; ++i) {
            for(int coin : coins) {
                int leftOver = i - coin;
                if(leftOver >= 0)
                    dp[i] = Math.min(dp[i], dp[leftOver] + 1);
                }
            }
        return dp[amount] > amount ? -1 : dp[amount];
    }

    public int coinChangeRecursive(int[] coins, int amount) {
        int[] cache = new int[amount + 1];
        return helper(coins, amount, cache);
    }

    private int helper(int[] coins, int amount, int[] cache) {
        if(amount < 0) return -1;
        if(amount == 0) return 0;
        //if amount is greater, cache[amount] cannot be -1
        if(cache[amount] != 0) return cache[amount];
        int prevCoins = Integer.MAX_VALUE;
        for(int coin : coins) {
            int val = helper(coins, amount - coin, cache);
            if(val >= 0) {//only check for valid prevCoins
                prevCoins = Math.min(prevCoins, val);
            }
        }
        //don't find a good solution
        if(prevCoins == Integer.MAX_VALUE) {
            cache[amount] = -1;
            return -1;
        } else {
            cache[amount] = prevCoins + 1;
            return prevCoins + 1;
        }
    }
}
