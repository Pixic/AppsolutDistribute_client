import java.util.*;
/**
 * Skansholm 12.4
 * 
 * @author (your name) 
 * @version 2007-02-02
 */
public class SimpleQueue<T> {
    private LinkedList<T> queue = new LinkedList<T>();
	
    public int size() {
        return queue.size();
    }
    
    public synchronized void put(T obj) {
        queue.add(obj);
        notify();
    }
        
    public synchronized T take() {
        while (queue.isEmpty()) {
            try {
                wait();
            }
            catch(InterruptedException e) {
                return null;
            }
        }
        T obj = queue.getFirst();
        queue.removeFirst();
        return obj;
    }
}
