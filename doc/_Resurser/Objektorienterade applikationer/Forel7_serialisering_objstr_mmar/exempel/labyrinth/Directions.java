import java.util.HashMap;
import java.io.*;

/**
 * Defines the possible directions names and their opposite directions
 * The class uses the Singleton design pattern.
 * @author Uno Holmer
 * @version 2008-10-05
 */
public class Directions implements Serializable
{
    private static final long serialVersionUID = 6532389002L;
    
	private static HashMap<String,String> directions;
    private static final Directions instance = new Directions();
	
	private Directions()
	{
		directions = new HashMap<String,String>();
		directions.put("north","south");
		directions.put("south","north");
		directions.put("east","west");
		directions.put("west","east");
	}
	
	public static Directions getInstance() {
	    return instance;
	}

	public static boolean isDirection(String d)
	{
		return directions.containsKey(d);
	}
	
    public static String opposite(String d)
	{
		return directions.get(d);
	}
}
