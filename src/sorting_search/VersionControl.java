package sorting_search;

public class VersionControl {

    private  boolean isBadVersion(int version){
        return true;
    }
    public int firstBadVersion(int n) {
        int low = 1;
        int high = n;
        while(low < high) {
            int mid = low + (high - low) /2;
            if(isBadVersion(mid)) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }
}
