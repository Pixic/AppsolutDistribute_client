package ad.controller.protocol;

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
public class Protocol {
	
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
	public boolean attemptCreationOfAccount(String username, String password, String firstName, String surname, String email)/* throws Exception*/{
		
		
		return true; // if denied it should mean that the user name was taken
	}

	public void attemptLogin(String username,String password) throws Exception {
		
		// login_account_dont_exist
		// database error?
		// error failed to connect to server
		// error wrong password or user name?
		
	}

	public boolean attemptCreateGroup(String string) {
		
		return true; // should return false if groupname is taken
	}
	
}
