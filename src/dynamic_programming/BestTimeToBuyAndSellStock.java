package dynamic_programming;

public class BestTimeToBuyAndSellStock {
    public int maxProfit(int[] prices) {
        int[] bestSellPrice = new int[prices.length + 1];
        for(int i = prices.length - 1; i >= 1; i--) {
            bestSellPrice[i] = Math.max(prices[i], bestSellPrice[i + 1]);
        }

        int maxProfit = 0;
        for(int i = 0; i < prices.length - 1; i++) {
            maxProfit = Math.max(bestSellPrice[ i + 1] - prices[i], maxProfit);
        }
        return maxProfit;
    }
}
