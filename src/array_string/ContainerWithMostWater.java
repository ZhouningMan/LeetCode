package array_string;

public class ContainerWithMostWater {
    public int maxArea(int[] height) {
        int i = 0;
        int j = height.length - 1;
        int area = 0;
        while(i < j) {
            int temp = Math.abs(Math.min(height[i], height[j]) * (i -j));
            area = Math.max(area, temp);
            if(height[i] < height[j]) {
                i++;
            } else {
                j--;
            }
        }
        return area;
    }
}
