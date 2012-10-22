import java.util.*;
/**
 * Skansholm 12.4
 * 
 * @author JSk (UH)
 * @version 2007-02-02
 */
public class JobShop
{
    private SimpleQueue<String> helpDeskQueue = new SimpleQueue<String>();
    private JobGenerator jobGenerator = new JobGenerator();
    private ArrayList<Thread> threads = new ArrayList<Thread>();
    
	public JobShop() throws InterruptedException {
        createAndStartThreads();
        // sleep while the simulation is running
        Thread.sleep(20000);
        // stop the threads
        stopThreads();      
        System.out.println("\nJobs left in queue: " + helpDeskQueue.size());
        
        System.exit(0);
	}
	
	public void createAndStartThreads() {
	    threads.add(new Producer("Lundin",6237,helpDeskQueue,jobGenerator));
        threads.add(new Producer("von Hacht",2846,helpDeskQueue,jobGenerator));
        threads.add(new Producer("Holmer",1239,helpDeskQueue,jobGenerator));
        
        threads.add(new Consumer("Erik",2000,helpDeskQueue));
        threads.add(new Consumer("Mikael",1500,helpDeskQueue));
        
        for ( Thread t : threads ) 
            t.start();
    }
    
    public void stopThreads() {
        for ( Thread t : threads ) 
            t.interrupt();
    }
}

























