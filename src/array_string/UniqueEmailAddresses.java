package array_string;

import java.util.HashSet;
import java.util.Set;

public class UniqueEmailAddresses {
    public int numUniqueEmails(String[] emails) {
        Set<String> uniuqe = new HashSet<>();
        for(String email : emails) {
            String[] segments = email.split("@");
            int idxPlus = segments[0].indexOf('+');
            //when deal with index, be careful!
            String local = segments[0].substring(0, idxPlus < 0 ? segments[0].length() : idxPlus);
            //replaceAll accepts regex, replace accepts string literal
            uniuqe.add(local.replace(".", "") + "@" + segments[1]);
        }
        return uniuqe.size();
    }
}
