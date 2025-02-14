import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test {
    private static final Logger logger = LogManager.getLogger(Test.class);

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);

        for (int i = 0; i < 5; i++) {
            int taskId = i;
            executor.submit(() -> {
                logger.info("Task {} is running inside ThreadPool", taskId);
                LogManager.shutdown(); // Force flush (for debugging only)
            });
        }

        executor.shutdown();
    }
}