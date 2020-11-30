package littlejava.util.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class BlockingQueueMain {
    public static void main(String[] args) {
        final int N = 200;
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(10);
        Runnable takeTask = () -> IntStream.range(0, N).forEach(i -> {
            try {
                int v = blockingQueue.take();
                System.out.println("Take " + v);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Runnable putTask = () -> IntStream.range(0, N).forEach(i -> {
            try {
                blockingQueue.put(i);
                System.out.println("Put " + i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        executorService.submit(putTask);
        executorService.submit(takeTask);
        Utils.stop(executorService);
    }
}
