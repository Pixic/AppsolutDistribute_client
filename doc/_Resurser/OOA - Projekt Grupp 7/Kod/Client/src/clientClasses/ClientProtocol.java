package clientClasses;

/**
 * 
 * @author Robin Braaf, Fredrik Hansson
 * @version 2010-02-15
 */
public class ClientProtocol {

	public static final String CMD = "CMD"; // For commands
	public static final String USERS = "USERS"; //Prefix before a user list
	public static final String JOIN = "JOIN"; // When a user joins the channel
	public static final String LEAVE = "LEAVE"; //When a user leaves the channel
	public static final String BC_MSG = "BC"; // ACHTUNG BROADCAST
	public static final String PRIV_MSG = "MSG"; // Private message received
	//CMD MESSAGES
	public static final String AUTH_OK = "AUTH_OK"; //If authentication was successful 
	public static final String AUTH_FAILED = "AUTH_FAILED"; //If authentication failed
	public static final String NICK_IN_USE = "NICK_IN_USE"; //If the user name is taken
	public static final String NICK_INVALID = "NICK_INVALID"; // If nickname is invalid
	public static final String REQUEST_OK = "REQUEST_OK"; //If the request was successful
	public static final String REQUEST_INVALID = "REQUEST_INVALID"; //If the request was rejected
	
	/**
	 * Returns a more user friendly String to display.
	 * @param msg CMD message to process.
	 * @return Processed String.
	 */
	public static String processCMDMSG(String msg){
		if(msg == ClientProtocol.AUTH_OK){
			return "Login was successful.";
		}else if(msg == ClientProtocol.AUTH_FAILED){
			return "Login failed.";
		}else if(msg == ClientProtocol.NICK_IN_USE){
			return "This nickname is already in use.";
		}else if(msg == ClientProtocol.NICK_INVALID){
			return "Nickname is invalid, please us another.";
		}else if(msg == ClientProtocol.REQUEST_OK){
			return "Request was successful.";
		}else if(msg == ClientProtocol.REQUEST_INVALID){
			return "Request was denied.";
		}
		return "No CMD message";
	}
}
