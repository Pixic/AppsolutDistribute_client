/**
 * Skansholm s.412 (med arv från Thread)
 * 
 * @author UH
 * @version 2006-02-06
 */
public class Writer extends Thread {
    String text;
    private long interval; // ms

    public Writer(String text,long interval)
    {
        this.text = text;
        this.interval = interval;
    }
    
    public void run() {
        while ( ! interrupted() ) {
            try {
                sleep(interval);
            }
            catch (InterruptedException e) {
                break;
            }
            System.out.println(text); 
        }
    }
}
