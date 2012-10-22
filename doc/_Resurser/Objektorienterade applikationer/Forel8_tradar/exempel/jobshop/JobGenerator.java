import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * geberate random jobs for the computer help desk
 * 
 * @version    2007-02-02
 * @author     Uno Holmer
 */
public class JobGenerator
{
    private ArrayList<String> jobs;   
    private Random randomGenerator;

    /**
     * Construct a Responder
     */
    public JobGenerator()
    {
        jobs = new ArrayList<String>();
        fillJobList();
        randomGenerator = new Random();
    }

    /**
     * Build up a list of default responses from which we can pick one
     * if we don't know what else to say.
     */
    private void fillJobList()
    {
        jobs.add("I want Linux in my wrist watch");
        jobs.add("My HC12 is on fire");
        jobs.add("Please reset the printer queue");
        jobs.add("I want a virus called Windows removed");
        jobs.add("My DVD is stuck in the player");
        jobs.add("My portable just slipped out through the window, can you fix it?");
        jobs.add("Every mouse in J321 seems to be dead");
    }

    /**
     * Randomly select and return one of the default jobs.
     * @return     A random job
     */
    public String pickRandomJob()
    {
        int index = randomGenerator.nextInt(jobs.size());
        return jobs.get(index);
    }
}









