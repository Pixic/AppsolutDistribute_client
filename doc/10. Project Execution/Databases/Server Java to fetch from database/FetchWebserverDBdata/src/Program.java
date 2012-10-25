

public class Program {
	
	public static void main(String[] args){
		
		long startTime = System.currentTimeMillis();
		
		/* UserParser uses GetUsers (HttpURLConnect to PHP+MySQL) which fetch
		 * data from Web Server database and return in JSON.
		 * The UserParser then decode the returned data in JSON and store it 
		 * in the Server list of users, i.e. an ArrayList<User>. 
		 */
		Get_AllUsers allUsers = new Get_AllUsers();
		
		
		System.out.println("Elapsed time: " + 
				(System.currentTimeMillis()-startTime));
	}
}