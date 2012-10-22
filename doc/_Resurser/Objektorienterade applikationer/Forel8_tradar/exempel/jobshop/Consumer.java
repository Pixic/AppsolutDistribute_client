/**
 * Skansholm 12.4, Consumer process
 * 
 * @author JSk (UH) 
 * @version 2007-02-02
 */
public class Consumer extends Thread
{
    private String name;
    private long interval;
    private SimpleQueue<String> queue; 

	public Consumer(String name,long interval,SimpleQueue<String> queue)
	{
		this.name = name;
   		this.interval = interval;
		this.queue = queue;
	}
	
	public void run() {
	    while (!Thread.interrupted()) {
	        try {
	            Thread.sleep(interval);
	        }
	        catch (InterruptedException e ) {
	            break;
	        }
	        System.out.println(name + " received job from " + queue.take());
	    }
    }   
}
