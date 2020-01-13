package system_design;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.*;

public class WebCrawlerService {
    public interface HtmlHeler {
        List<String> parseUrls(String url);
    }

    private class WebCrawler implements Runnable{

        @Override
        public void run() {

        }
    }

    private static final int WORKERS = 3;
    private final Queue<String> pendingUrl = new LinkedBlockingQueue<>();
    private final Set<String> result = ConcurrentHashMap.newKeySet();
    private final Set<String> visited = ConcurrentHashMap.newKeySet();
    private final ExecutorService executors = Executors.newFixedThreadPool(WORKERS);


    /**
     * @param url: a url of root page
     * @return: all urls
     */
    public List<String> crawler(String url) {
        for(int i = 0; i < WORKERS; ++i) {
            executors.submit(new WebCrawler());
        }
        return new ArrayList<>(visited);
    }
}
