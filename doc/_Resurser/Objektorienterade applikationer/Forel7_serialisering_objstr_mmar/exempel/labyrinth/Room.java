import java.util.HashMap;
import java.util.Set;
import java.io.*;

/**
 * A Room has a description and a set of exits leading to other Rooms
 * 
 * @author Uno Holmer
 * @version 2012-01-30
 */
public class Room implements Serializable
{
    private static final long serialVersionUID = 123456789L;
    
    private String description;
    private HashMap<String,Room> exits = new HashMap<String,Room>();

    public Room(String description)
    {
        this.description = description;
    }

    public String getInfo() {
        return description;
    }
    
    public void addInfo(String info) {
        description = description + '\n' + info;
    }
    
    public Room getExit(String direction)
    {
         return exits.get(direction);
    }
    
    public Set<String> getAllExits() {
        return exits.keySet();
    }
    
    /**
     * Connect this room with a given room in a given direction.
     *
     * @param  direction  the name of the new exit
     * @param  room       the room to connected to the new exit
     * 
     * ERRORS:
     *    An invalid direction is ignored.
     *    If the exit id is already in use this room is left unchanged.
     */
    public void connect(String direction,Room room) {
        Directions directions = Directions.getInstance();
        if ( directions.isDirection(direction) && 
             ! exits.containsKey(direction) ) 
        {
            // Connect forwards
            exits.put(direction,room);
            // Connect backwards
            room.connect(directions.opposite(direction),this);
        }
    }
}















