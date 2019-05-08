package design;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Logger {

    private final AtomicInteger mostRecentTS = new AtomicInteger(0);

    private final Map<String, Integer> messages = new LinkedHashMap<String, Integer>() {
        @Override
        protected boolean removeEldestEntry(Map.Entry<String, Integer> eldest) {
            return eldest.getValue() - mostRecentTS.get() > 10;
        }
    };

    /** Initialize your data structure here. */
    public Logger() {
    }

    /** Returns true if the message should be printed in the given timestamp, otherwise returns false.
     If this method returns false, the message will not be printed.
     The timestamp is in seconds granularity. */
    public boolean shouldPrintMessage(int timestamp, String message) {
        boolean shouldPrint;
        synchronized (messages) {
            mostRecentTS.set(timestamp);
            int prev = messages.getOrDefault(message, Integer.MIN_VALUE);
            shouldPrint = timestamp >= prev + 10;
            if(shouldPrint) messages.put(message, timestamp);
        }
        return shouldPrint;
    }

    public static void test() {

    }
}
