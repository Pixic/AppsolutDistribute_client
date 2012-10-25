import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
//import java.io.UnsupportedEncodingException;
//import org.json.JSONArray;
//import org.json.JSONException;

/**
 * @author Mattias Isene
 * 
 * Copyright [2012] [Mattias Isene]
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
 * ---------------------------------------------------------------------------
 * A source has been used to get to know how this works and then modified to 
 * work in this specific matter.
 * 
 * Source:
 * http://stackoverflow.com/questions/12472449/
 * get-a-http-request-to-a-java-application-whenever-the-mysql-database-changes
 */
public class Get_AllUsers {
	
	
	
	
	
	/**
	 * INSTANCE VARIABLES
	 */
	public ArrayList<User> userList = new ArrayList<User>();
	private boolean valueOnGoing;
	
	/**
	 * CONSTRUCTOR
	 */
	public Get_AllUsers(){
		String result = queryUsers();
		result = trimQueryResult(result);
//		System.out.println(result);
		parseResult(result);
	}
	

	/**
	 * Get ALL USERS
	 * @return ArrayList
	 */
	public String queryUsers(){
		String temp;
		String queryResult = "";
		
		try{
			URL url = new URL("http://95.80.48.213/appsolutdistribute/serverphpsql/get_users_json.php");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setDoOutput(true);
	        conn.setReadTimeout(10000);
			conn.setRequestProperty("Accept", "application/json");
			
			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}
			BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream() )));
			while ((temp = br.readLine()) != null) {
				queryResult += temp;
			}
			conn.disconnect();
		} catch (MalformedURLException e) {
			System.out.println("MalformedURLException: ");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("IOException: ");
			e.printStackTrace();
		}
		return queryResult;
	}
	
	
	
	/**
	 * This method was used to try using JSON decoder to parse the query result.
	 * @param queryResult
	 * @return
	 */
//	private void parseResult(String queuryResult){
//		try {
//			String decodedString = java.net.URLDecoder.decode(queryResult, "UTF-8");
//			JSONArray json = new JSONArray(decodedString);
//		} catch (UnsupportedEncodingException | JSONException e) {
//			e.printStackTrace();
//		}
//	}
	
	/**
	 * Method to remove the square brackets and all double quote chars.
	 * @param queryResult - raw query result string.
	 * @return result - Cleaned string.
	 */
	private String trimQueryResult(String queryResult){
		char[] charArray = queryResult.toCharArray();
		String result = "";
		for(char n : charArray){
			if( (n == '"') || (n == '[') || (n == ']')){
				continue;
			}
			else{
				result += n;
			}
		}
		return result;
	}
	
	// ********************************************************************
	// ParseRESULT method and help methods below...	
	// ********************************************************************
	/**
	 * parseResult method is used in the constructor after querying the database
	 * to parse the information.
	 * parseResult are using help methods below to step by step do things.
	 * @param queryResult - query result string as input parameter.
	 * @return userList - returning ArrayList<User> userList
	 */
	public ArrayList<User> parseResult(String queryResult){
		ArrayList<String> userElements = findUsers(queryResult);
		System.out.println("ArrayList<String> userElements = findUsers(queryResult):\n" + userElements.get(0) + "\n\n" );
		
		ArrayList<DatabaseUser> dbUsers = cleanUser(userElements);
		
		System.out.println(dbUsers.get(1).getUserId());
//		System.out.println(dbUsers.get(1).getUserName());
//		System.out.println(dbUsers.get(1).getUserPassword());
//		System.out.println(dbUsers.get(1).getUserFirstName());
//		System.out.println(dbUsers.get(1).getUserLastName());
//		System.out.println(dbUsers.get(1).getEmail());
		return userList;
	}
	
	
	/**
	 *  Finding each user and adding each user as string element in 
	 *  arraylist. Meaning, userid, username, userpw, user_first_name, 
	 *  user_last_name and user_email will all be stored in a long string 
	 *  with both column names and user values for each, user by user in the
	 *  arraylist userElements.
	 *  Example (all 3 rows below as one string):
	 *  "userid":"4","username":"lillen","userpw":"7896",
	 *  "user_first_name":"Lillen","user_last_name":"Eklund",
	 *  "user_email":"lillen@gmail.com"
	 *  ____________________________________________________________________
	 *  Removing [ ] and { } from string.
	 */
	private ArrayList<String> findUsers(String queryResult){
		String tempUser = "";
		char[] resultCharArray;
		char previous = ' ';
		ArrayList<String> userElements = new ArrayList<String>();
		resultCharArray = queryResult.toCharArray();
		for(char c : resultCharArray){
			
			if(c == ',' && previous == '}'){
				continue;
			}
			else if(c == '{'){
//				if(userElements.size() != 0){
//					userElements.add(tempUser);
//				}
				tempUser = "";
				previous = c;
				continue;
			}
			else if(c == '}'){
				userElements.add(tempUser);
				previous = c;
				tempUser = "";
				continue;
			}
			else if( (c == '{') && (previous == ',') ){
				previous = c;
				continue;
			}
			else{
				tempUser += c;
				previous = c;
			}
//			System.out.println(tempUser);
		}
		return userElements;
	}
		
		
	/*
	 * So far we have removed [ ] from the queryResult string and then 
	 * removed { } from every user and each user should now look like the 
	 * next two rows concatenated:
	 * 
	 * "userid":"4","username":"lillen","userpw":"7896","user_first_name":
	 * "Lillen","user_last_name":"Eklund","user_email":"lillen@gmail.com"
	 */
	
	/**
	 * This method separates each database column of the user and store the 
	 * string in format:
	 * 
	 * DatabaseUser.userid 	= "userid:4"
	 * DatabaseUser.uname 	= "username:lillen"
	 * DatabaseUser.upw 	= "userpw:7896"
	 * DatabaseUser.ufname	= "user_first_name:Lillen"
	 * DatabaseUser.ulname	= "user_last_name:Eklund"
	 * DatabaseUser.uemail	= "user_email:lillen@gmail.com"
	 * 
	 * @param elements
	 * @return DatabaseUser
	 */
	private ArrayList<DatabaseUser> cleanUser(ArrayList<String> userElements){
		ArrayList<DatabaseUser> listDatabaseUsers = new ArrayList<DatabaseUser>();
		char previous = ' ';
		char[] userCharArray;
		int columnCounter = 0;
		String userValue = "";
		
		// Reading each user string element and finding all user columns.
		for(String userString : userElements){
			DatabaseUser currentUser = new DatabaseUser();
			listDatabaseUsers.add(currentUser);
			userCharArray = userString.toCharArray();
			valueOnGoing = false;
			
			for(char c : userCharArray){
				if(c == ':'){
					valueOnGoing = true;
//					continue;
				}
				else if(valueOnGoing == false){
					continue;
				}
				else if(c == ','){ // We have reached ',' and ended string for user column.
					valueOnGoing = false;
					
					if(columnCounter == 1){
						currentUser.setUserId(userValue);
					}
					else if(columnCounter == 2){
						currentUser.setUserName(userValue);
					}
					else if(columnCounter == 3){
						currentUser.setUserPassword(userValue);
					}
					else if(columnCounter == 4){
						currentUser.setUserFirstName(userValue);
					}
					else if(columnCounter == 5){
						currentUser.setUserLastName(userValue);
					}
					else if(columnCounter == 6){
						currentUser.setUserEmail(userValue);
					}
//					continue;
				}
				else{
					userValue += c;
					if(previous == ','){
						userValue = "";
						columnCounter++;
					}
					previous = c;
				}
				System.out.println("previous: " + previous);
			}
			System.out.println(columnCounter);
			System.out.println("currentUser.getUserID: " + currentUser.getUserId());
		}
		return listDatabaseUsers;
	}
}

/*
 * [
 * 	{	
 * 		"userid":"4",
 * 		"username":"lillen",
 * 		"userpw":"7896",
 * 		"user_first_name":"Lillen",
 * 		"user_last_name":"Eklund",
 * 		"user_email":"lillen@gmail.com"
 * },{
 * 		"userid":"5",
 * 		"username":"musse",
 * 		"userpw":"7865",
 * 		"user_first_name":"Musse",
 * 		"user_last_name":"Pigg",
 * 		"user_email":"musse@gmail.com"
 * },{
 * 		"userid":"6",
 * 		"username":null,
 * 		"userpw":"gillar_majskolv",
 * 		"user_first_name":"Jan",
 * 		"user_last_name":null,
 * 		"user_email":null
 * },{
 * 		"userid":"7",
 * 		"username":"nisse",
 * 		"userpw":null,
 * 		"user_first_name":"Nisse",
 * 		"user_last_name":"Manpower",
 * 		"user_email":"nisse@manpower.se"
 * },{
 * 		"userid":"8",
 * 		"username":"test2",
 * 		"userpw":"bla bla",
 * 		"user_first_name":"Test",
 * 		"user_last_name":"Testor",
 * 		"user_email":"test@not4u.se"
 * },{
 * 		"userid":"9",
 * 		"username":null,
 * 		"userpw":"eller_hur?!",
 * 		"user_first_name":null,
 * 		"user_last_name":"Jamen visst!",
 * 		"user_email":null
 * },{
 * 		"userid":"10",
 * 		"username":null,
 * 		"userpw":null,
 * 		"user_first_name":null,
 * 		"user_last_name":null,
 * 		"user_email":null
 * },{
 * 		"userid":"11",
 * 		"username":"testigen",
 * 		"userpw":"1234172487",
 * 		"user_first_name":"testigen",
 * 		"user_last_name":"testigen_lastname",
 * 		"user_email":null
 * },{
 * 		"userid":"15",
 * 		"username":"malin",
 * 		"userpw":"malle",
 * 		"user_first_name":"malin",
 * 		"user_last_name":"malinder",
 * 		"user_email":"malin@malinder.se"
 * },{
 * 		"userid":"16",
 * 		"username":"anton",
 * 		"userpw":"hemligt",
 * 		"user_first_name":"anton",
 * 		"user_last_name":"anton",
 * 		"user_email":"anton.anton"
 * },{
 * 		"userid":"17",
 * 		"username":"Ser till och med",
 * 		"userpw":null,
 * 		"user_first_name":null,
 * 		"user_last_name":null,
 * 		"user_email":"Dags@sova.nu"
 * },{
 * 		"userid":"18",
 * 		"username":"Suspekt",
 * 		"userpw":"bajsa1337",
 * 		"user_first_name":"Harry",
 * 		"user_last_name":"Skaladuskitai",
 * 		"user_email":"SuspektZ@gmail.com"
 * },{
 * 		"userid":"19",
 * 		"username":"databaseExpert",
 * 		"userpw":"isene",
 * 		"user_first_name":"mattias",
 * 		"user_last_name":"isene",
 * 		"user_email":""
 * },{
 * 		"userid":"20",
 * 		"username":"sdfg",
 * 		"userpw":"sdfg",
 * 		"user_first_name":"sdfg",
 * 		"user_last_name":"sdfg",
 * 		"user_email":"sdfg"
 * },{
 * 		"userid":"22",
 * 		"username":"kkk",
 * 		"userpw":"kkk",
 * 		"user_first_name":"kkk",
 * 		"user_last_name":"kkk",
 * 		"user_email":"kkk"
 * }
 * ]
*/