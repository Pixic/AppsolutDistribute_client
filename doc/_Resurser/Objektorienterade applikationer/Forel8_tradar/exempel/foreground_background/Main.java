/**
 * Example: Using the join method in a thread.
 * 
 * @author Uno Holmer
 * @version 2006-02-06
 */
public class Main {
    private BackgroundJob backgroundJob = new BackgroundJob(1000,10);

    public Main()  {
        backgroundJob.start();
        foregroundJob();
    }
    
    public void foregroundJob() {
        int i = 0;
        while ( i <= 20 && ! Thread.interrupted() ) {
            try {
                Thread.sleep(200);
            }
            catch (InterruptedException e) {
                break;
            }
            System.out.println("Foreground: " + i++); 
        }
        
        // Now wait for background job to finish before continuing foreground job 
        try {
            backgroundJob.join();
        }
        catch(InterruptedException e) { } 
        
        System.out.println("Background result: " + backgroundJob.getResult()); 
        System.out.println("Foreground work continues...");
        // ... 
        System.exit(0);
    } 
}
