import java.io.*;

/**
 * A labyrinth of rooms which can be explored.
 * File save/load operations are provided to demonstrate object streams.
 * 
 * @author Uno Holmer
 * @version 2012-01-30
 */
public class Labyrinth {   
    private State state;
    
    public Labyrinth() {
        state = new State();
    }
    
    /**
     * An object of this internal class contains an object tree graph  
     * rooted at start, together with the player's current position.
     * A state object can be saved in a file and later reloaded.
     */
    private static class State implements Serializable {
        Room start = new Room("START");
        Room current = start;
    }   

    /**
     * Moves the current position to the adjacent room in the given direction.
     * If such a room does not exist, a new one is created and doubly
     * connected with the current room. Then the current position 
     * is set to the new room.
     * 
     * @param  direction   the walking direction
     * Errors: An invalid direction is ignored.
     */
    public void walk(String direction) {
        Directions directions = Directions.getInstance();
        if ( directions.isDirection(direction) ) {
            if ( state.current.getExit(direction) != null ) 
                state.current = state.current.getExit(direction);
            else {
                // Create a new room and connect it to   the current room
                Room r = new Room("");
                state.current.connect(direction,r);
                state.current = r;
            }
        }
    }
    
    /**
     * Adds a comment to the existing information in the current room
     *
     * @param  comment   the information to add
     */
    public void addInfo(String comment) {
        state.current.addInfo(comment);
    }
    
    /**
     * Print the information about the current room 
     */
    public void printInfo() {
        System.out.println(state.current.getInfo());
    }
    
    /**
     * Print the names of all exits from the current room
     */
    public void printExits() {
        System.out.print("Exits: ");
        for ( String e : state.current.getAllExits() )
            System.out.print(e + " ");
        System.out.println();
    }
    
    /**
     * Saves the labyrinth state in a file
     *
     * @param  fileName   the name of the output file
     */
    public void save(String fileName) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName));
            out.writeObject(state);
            out.close();
        }
        catch(Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }
    
    /**
     * Loads a complete labyrinth state from a file.
     *
     * @param  fileName   the name of the input file
     */
    public void load(String fileName) {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName));
            state = (State)in.readObject();
            in.close();
        }
        catch(Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }
}

