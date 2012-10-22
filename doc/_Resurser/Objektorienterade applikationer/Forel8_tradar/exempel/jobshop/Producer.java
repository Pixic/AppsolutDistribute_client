/**
 * Skansholm 12.4, Producer process
 * 
 * @author JSk (UH) 
 * @version 2007-02-02
 */
public class Producer extends Thread
{
    private String name;
    private long interval;
    private SimpleQueue<String> queue;
    private JobGenerator jobGenerator;

	public Producer(String name,long interval,SimpleQueue<String> queue,JobGenerator jobGenerator)
	{
		this.name = name;
		this.interval = interval;
		this.queue = queue;
		this.jobGenerator = jobGenerator;
	}

	public void run() {
	    while (!Thread.interrupted()) {
	        try {
	            Thread.sleep(interval);
	        }
	        catch (InterruptedException e ) {
	            break;
	        }
	        queue.put(name + ": " + jobGenerator.pickRandomJob());
	    }
    }      
}
