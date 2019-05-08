import java.util.*;
import java.util.function.Consumer;

public class JavaNewFeatures {
    public static void test() {
        var map = Map.of("key1", "val1", "key2", "val2");
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String v = entry.getValue();
            System.out.println(v);
        }

        Consumer<String> consumer = (string) -> System.out.println(string);

    }
}
