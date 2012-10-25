/**
 * 
 * 
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
 */

public class DatabaseUser{

	
	private String 	userid,
					uname, 
					upw, 
					ufname, 
					ulname, 
					uemail;
	
	/**
	 * CONSTRUCTOR
	 */
	public DatabaseUser()
	{

	}
	
	/**
	 * Get methods
	 */
	public String getUserId()
	{
		return userid;
	}
	
	public String getUserName()
	{
		return uname;
	}
	
	public String getUserPassword()
	{
		return upw;
	}
	
	public String getUserFirstName(){
		return ufname;
	}
	
	public String getUserLastName(){
		return ulname;
	}
	
	public String getEmail()
	{
		return uemail;
	}

	/**
	 * Set methods
	 */
	public void setUserId(String userid)
	{
		this.userid = userid;
	}
	
	public void setUserName(String uname)
	{
		this.uname = uname;
	}
	
	public void setUserPassword(String upw)
	{
		this.upw = upw;
	}
	
	public void setUserFirstName(String ufname)
	{
		this.ufname = ufname;
	}
	
	public void setUserLastName(String ulname)
	{
		this.ulname = ulname;
	}
	
	public void setUserEmail(String email)
	{
		this.uemail = email;
	}
}
