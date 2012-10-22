import java.lang.reflect.Method;

public class DumpMethods {
	public static void main(String[] args) {
		try {
			Class c = Class.forName(args[0]);
			Method m[] = c.getDeclaredMethods();
			for ( int i = 0; i < m.length; i++ )
				System.out.println(m[i].toString());
			System.out.println("----------------------------------------------------------");
			m = c.getMethods();
			for ( int i = 0; i < m.length; i++ )
				System.out.println(m[i].toString());
		}
		catch (Throwable e) {
			e.printStackTrace();
		}
	}

}
