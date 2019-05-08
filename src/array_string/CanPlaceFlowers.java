package array_string;

public class CanPlaceFlowers {
    public boolean canPlaceFlowers2(int[] flowerbed, int n) {
        int start = -1;
        int available = 0;
        for (int i = 0; i < flowerbed.length; ++i) {
            if(flowerbed[i] == 1) {
                int zeros = i - 1 - start;
                if(start == -1) available += zeros/2;
                else available += (zeros-1)/2;
                start = i;
            }
            if(available >= n) return true;
        }
        if(start == -1) available += (flowerbed.length + 1)/2;
        else available += (flowerbed.length - 1 - start)/2;
        return available >= n;
    }

    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        final int LEN = flowerbed.length;
        for(int i = 0; i < LEN; ++i) {
            if(flowerbed[i] == 0) {
                boolean prev = i == 0 || flowerbed[i - 1] == 0;
                boolean next = i < LEN - 1 && flowerbed[i + 1] == 0 || i == LEN - 1;
                if(prev && next) {
                    n--;
                    i++;//we have planted an imaginable flower here.
                }
            }
            if(n <= 0) return true;
        }
        return false;
    }

}
