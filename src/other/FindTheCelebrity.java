package other;

public class FindTheCelebrity {

    private boolean knows(int a, int b) {
        return true;
    }

    public int findCelebrity(int n) {
        int candidate = 0;
        //celebrity conditon:
        //1. he/she knows nobdy
        //2. everybody knows him/her
        //3. there is only one celebrity, which means there could be only one candidate.
        //i just need to find the celebrity candidate which knows nobody
        for(int i = 1; i < n; i++) {
            //if candidate is a celebrity, then candidate, cannot knows i and
            //i must knows candidate.
            if(knows(candidate, i)) {
                candidate = i;
            }
        }

        //we got a candidate
        for(int i = 0; i < n; i++) {
            if(i != candidate && (!knows(i, candidate) || knows(candidate, i))) return -1;
        }
        return candidate;
    }
}
