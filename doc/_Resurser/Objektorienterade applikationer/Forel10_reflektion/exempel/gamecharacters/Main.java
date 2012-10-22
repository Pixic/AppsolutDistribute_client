import java.util.ArrayList;
import java.util.Iterator;

public class Main {
	public static void main(String[] args) {
		GameCharacterFactory gfactory = new GameCharacterFactory();
		System.out.println("--------- Default game characters ---------------");
		for ( String name : gfactory.getCharacterNames() )
			gfactory.getCharacter( name ).act();
		
		// Load extra character classes. The names of the classes are stored
		// in the text file specified by the program argument.
		gfactory.loadExtraCharacters( args[ 0 ] );
		
		System.out.println("--------- Default game characters + loaded extra characters ---------------");
		for ( String name : gfactory.getCharacterNames() )
			gfactory.getCharacter( name ).act();
	}
}
