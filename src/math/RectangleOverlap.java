package math;

public class RectangleOverlap {
    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        return check(rec1, rec2, false);
    }

    private boolean check(int[] rec1, int[] rec2, boolean done) {
        boolean overlap = false;
        if(rec1[0]<= rec2[0] && rec1[2] > rec2[0] && rec1[1] <= rec2[1] && rec1[3] > rec2[1]) {
            overlap = true;
        } else if(rec1[0] >= rec2[0] && rec1[0] < rec2[2] && rec1[1] <= rec2[1] && rec1[3] > rec2[1]) {
            overlap = true;
        } else if(rec1[0] <= rec2[0] && rec1[1]>= rec2[1] && rec1[1] < rec2[3] && rec1[2] > rec2[0]) {
            overlap = true;
        } else if(rec1[0] >= rec2[0] && rec1[0] < rec2[2] && rec1[1] >= rec2[1] && rec1[1] < rec2[3]) {
            overlap = true;
        }
        if(overlap) return true;
        else if(done) return false;
        else return check(rec2, rec1, true);
    }
}
