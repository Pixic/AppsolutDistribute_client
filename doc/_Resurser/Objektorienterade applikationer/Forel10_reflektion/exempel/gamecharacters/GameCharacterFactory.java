import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class GameCharacterFactory {
	private HashMap<String,GameCharacter> gameCharacters = 
		new HashMap<String,GameCharacter>();
	
	public GameCharacterFactory() {
		// Default "hard wired" game characters
		gameCharacters.put("Gnome", new Gnome());	
		gameCharacters.put("Druid", new Druid());
	}
	
	public GameCharacter getCharacter( String name ) {
		return gameCharacters.get( name );
	}
	
	public void addCharacter( String name, GameCharacter gc ) {
		gameCharacters.put( name, gc );
	}
	
	public void loadExtraCharacters( String fileName ) {
		// Get the *names* (strings!) of new classes from fileName
		ArrayList<String> newClassNames = loadExtraClassNames( fileName );

		// Load the classes
		for ( String className : newClassNames ) {
			try {
				// Load a class
				Class cls = Class.forName( className );
				// Create an object of the loaded class
				GameCharacter gc = (GameCharacter)cls.newInstance();
				// Put the object in the table
				gameCharacters.put( className, gc );
				return;
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			System.exit(0); // Class load failed
		}
	}
	
	// load class names from the given text file
	private ArrayList<String> loadExtraClassNames( String fileName ) {
		ArrayList<String> newClassNames = new ArrayList<String>();
		BufferedReader in = null;
		try {
			in = new BufferedReader( new FileReader( fileName ) );
		} catch ( FileNotFoundException e1 ) {
			System.out.println( e1.getMessage() );
		}
		while ( true ) {
			String className;
			try {
				className = in.readLine();
				if ( className == null )
					break;
				newClassNames.add( className );
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return newClassNames;
	}
	
	// For demonstration purposes
	public Set<String> getCharacterNames() {
		return gameCharacters.keySet();
	}
}
