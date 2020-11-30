package littlejava.util.concurrent;

public class ArrayBlockingQueue<E> implements BlockingQueue<E> {

    private final Object[] items;
    private int takeIndex;
    private int putIndex;
    private int count;

    public ArrayBlockingQueue(int capacity) {
        if (capacity <= 0)
            throw new IllegalArgumentException();
        items = new Object[capacity];
    }

    @Override
    public synchronized void put(E e) throws InterruptedException {
        while (count == items.length)
            wait();
        enqueue(e);
        notifyAll();
    }

    @Override
    public synchronized E take() throws InterruptedException {
        while (count == 0)
            wait();
        E v = dequeue();
        notifyAll();
        return v;
    }

    private void enqueue(E e) {
        items[putIndex] = e;
        if (++putIndex == items.length) putIndex = 0;
        count++;
    }

    private E dequeue() {
        @SuppressWarnings("unchecked")
        E e = (E) items[takeIndex];
        items[takeIndex] = null;
        if (++takeIndex == items.length) takeIndex = 0;
        count--;
        return e;
    }
}
