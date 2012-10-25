

import java.io.Serializable;
import java.util.ArrayList;

/**
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

public class User implements Serializable{

	
	private int userId;
	private String userName, password, firstName, surname, emailAddress;
	//private NåntingAnnat password;
//	private ArrayList<Group> groups;
	private boolean isConfirmed, isLoggedIn;
	private String accountAction;

	
	
	public User()
	{
		isConfirmed = false;
		isLoggedIn = false;
	}
	
	/*
	 * Get methods
	 */
	public int getUserId()
	{
		return userId;
	}
	
//	public ArrayList<Group> getGroups()
//	{
//		return groups;
//	}
	
	public boolean getIsConfirmed()
	{
		return isConfirmed;
	}
	
	public boolean getIsLoggedIn()
	{
		return isLoggedIn;
	}
	
	public String getUserName()
	{
		return userName;
	}
	
	public String getEmailAddress()
	{
		return emailAddress;
	}
	public String getAccountAction()
	{
		return accountAction;
	}
	/*
	 * Set methods
	 */
	public void setUserId(int id)
	{
		this.userId = id;
	}
	
	public void setIsConfirmed(boolean b)
	{
		isConfirmed = b;
	}
	
	public void setIsLoggedIn(boolean b)
	{
		isLoggedIn = b;
	}
	
	public void setUserName(String userName)
	{
		this.userName = userName;
	}
	
	public void setEmailAddress(String email)
	{
		this.emailAddress = email;
	}
	/*
	 * Other methods
	 */
//	public void addGroup(Group group)
//	{
//		groups.add(group);
//	}
	
//	public void removeGroup(Group group)
//	{
//		groups.remove(group);
//	}
	
	public void setAccountAction(String s)
	{
		accountAction = s;
	}
}
