package ad.controller.protocol;

import java.io.Serializable;

import ad.model.protocol.*;

/**
 * Protocol 
 * 
 * 
 * @author Anton Kostet
 *
 * Copyright [2012] [Anton Kostet]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
public class Protocol implements Serializable {
	

	private ServerConnection sc;
	
	/**
	 * attemptCreationOfAccount - Tries to create account returns false if failed to create account, 
	 * 							  throws an exception if something else went wrong.
	 * @param username - A String the username of the user.
	 * @param password - A String the password of the user.
	 * @param firstName - A String the first name of the user.
	 * @param surname - A String the surname of the user.
	 * @return boolean - if denied account (not implemented returns true always)
	 * @throws Exception - if it failed to make a connection or if something else went wrong.
	 */
	
	
	public Protocol()
	{
		sc = new ServerConnection();
	}
	
	

	
	public Object readAndProcess()
	{
		Object o = sc.readFromServer();
		
		if(o instanceof ChatMessage)
		{
			ChatMessage cm = (ChatMessage)o;
			//Bla bla
			//Uppdatera view för chat
			return cm;
		}
		return null;
	}
	
	public boolean attemptCreationOfAccount(String username, String password, String firstName, String surname, String email)/* throws Exception*/{
		
		sc.connectToServer();
		
		Object response;
		User user = new User();
		
		user.setUserName(username);
		user.setPassword(password);
		user.setFirstName(firstName);
		user.setSurname(surname);
		user.setEmailAddress(email);
		
		user.setAccountAction(User.CREATE_ACCOUNT);
		
		sc.writeToServer(user);
		
		//Expecting a User back:
		while((response = sc.readFromServer()) == null);
		if(response instanceof User)
		{
			User result = (User)response;
			
			//User created, success!
			if(result != null)
			{
				
			}
			
			
		}
		else if(response instanceof String)
		{
			//Couldn't create account, 
		}
		else
		{
			//Something unexpected happened!
		}
		
		return true; // if denied it should mean that the user name was taken
	}

	public void attemptLogin(String username,String password) throws Exception {
		sc.connectToServer();
		
		Object response;
		User usr = new User();
		
		usr.setUserName(username);
		usr.setPassword(password);
		usr.setAccountAction(User.LOG_IN);
		
		sc.writeToServer(usr);
		
		
		while((response = sc.readFromServer()) == null);
		
		if(response instanceof User)
		{
			//Logged in successfully! 
			//return true;
		}
		else if(response instanceof String)
		{
			//Couldn't log in, username or password incorrect!
			//throw new Exception();
			//return false;
			throw new Exception("troll");
		}
		else
		{
			//Something unexpected happened!
			//throw new Exception();
			//return false;
			throw new Exception("????");
		}
		

		// login_account_dont_exist
		// database error?
		// error failed to connect to server
		// error wrong password or user name?
		
	}

	public boolean attemptCreateGroup(String string) {
		
		return true; // should return false if groupname is taken
	}
	
	public void sendMessage(String message){
		
	}


}
