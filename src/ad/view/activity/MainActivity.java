package ad.view.activity;

import java.util.Stack;

import ad.model.expList.ExpListChild;
import ad.view.activity.R;
import ad.view.dialogs.CustomDialog;
import ad.view.dialogs.UserInputFilter;
import ad.view.service.AppService;
import ad.controller.list.ExpListAdapter;
import ad.controller.protocol.Protocol;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ExpandableListView.OnChildClickListener;

import android.content.DialogInterface;
import android.content.Intent;

/**
 * MainActivity - extends Activity and implements OnChildClickListener. This
 * class creates the window(activity), sets its content(views) and is the
 * listener of the content. Since this class implements OnChildClickListener it
 * must have the method onChildClick.
 * 
 * Class content: 
 * 
 * Instances: 
 *    ExpAdapter expAdapter, ExpandList expandList,
 *    Stack<ExpListAdapter> backExpMenu, Stack<CharSequence> backAppTitle,
 *    listener (located in dialog section), 
 *    (The following instances used in Dialog Build Section)
 *    InputFilter[] filters,  
 *    
 * Sections and their methods:
 * Activity: onCreate, onCreateOptions
 * 
 * Menu On Click/Select: onChildClick, onOptionsItemSelected, onKeyDown
 *                     
 * Menu Methods: startMenu, helpMenu, helpMenuOne, onlineMenu, helpMenutwo,
 *  					  groupMenu, helpMenuThree, menuMenu,androidMenu
 * 
 * Special Operation: settingsDialog 
 * 				
 * Standard Operation: goToMenu, onBack, logout, onExit 
 * 	
 * Build Dialog: createNewDialog, createDialogButton,  
 * 		      				, closeDialog, createAccount
 * Dialog OnClick/Selection: onClick  (here will probably the ListView Onclick method be)
 * 
 * Dialog Standard Operations: closeDialog, goOnline
 * 
 * Dialog Special Operations: createAccount, login, createGroup
 * 
 * @author Stefan Arvidsson 
 * 
 * 		   Copyright [2012] [Stefan Arvidsson]
 * 
 *         Licensed under the Apache License, Version 2.0 (the "License"); you
 *         may not use this file except in compliance with the License. You may
 *         obtain a copy of the License at
 * 
 *         http://www.apache.org/licenses/LICENSE-2.0
 * 
 *         Unless required by applicable law or agreed to in writing, software
 *         distributed under the License is distributed on an "AS IS" BASIS,
 *         WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 *         implied. See the License for the specific language governing
 *         permissions and limitations under the License.
 */
public class MainActivity extends Activity implements OnChildClickListener, UserInputFilter {
	// Initiates the activity's instances
	private ExpListAdapter expAdapter;
	private ExpandableListView expandList;
	// Note: If an operation is performed on one stack it should also be used on
	// the other.
	private Stack<ExpListAdapter> backExpMenu = new Stack<ExpListAdapter>();
	private Stack<CharSequence> backAppTitle = new Stack<CharSequence>();

	private CustomDialog custom;
	
	private Protocol protocol = new Protocol();

	// Text input filters
	private InputFilter[] filters = {new CharacterFilter (), new LengthFilter(40)};

	private static final int AUTHORITY_USER=0, AUTHORITY_MODERATOR=1, AUTHORITY_ADMIN=2;
	private int minUsername=4, maxUsername=20,maxName=20, minPassword=6, 
							maxPassword=20, maxEmail=40, minGroupName=4, maxGroupName=20;

	//private int currentAuthority = AUTHORITY_USER;
			
	// ---------------------------------------------------------------------------------------------------------------------------------------------------
	// Activity Section - methods that are overridden from the Activity superclass, examples: What happens when the activity is created, paused, e.g. ----
	// ---------------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * onCreate - Called when the activity is first created. Defines what
	 * happens on application start up. Sets the content of the view and build
	 * the ExpandableListView menu.
	 * 
	 * @param savedInstanceState  - Bundle with the state of the Activity.       
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		expandList = (ExpandableListView) findViewById(R.id.ExpList); // Build the initial expandable list menu
		expAdapter = new ExpListAdapter(MainActivity.this, "start");
		expandList.setAdapter(expAdapter);
		expandList.setOnChildClickListener(this);
	}

	/**
	 * onCreateOptionsMenu - Creates the menu layout.
	 * 
	 * @return Returns a boolean to indicate if the operation went well or not.
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

// ---------------------------------------------------------------------------------------------------------------------------------------------------
// On Click/Select Section - identifies in which menu the click occurred and sends an identifier of the instance to the identified menu's method -----
// ---------------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * onChildClick - Defines action On child click in the ExpandableListView,
	 * 			      it checks which tag the child has and sends the label to the menu's method.
	 * @return Returns a boolean to indicate if the operation went well or not.
	 */
	public boolean onChildClick(ExpandableListView parent, View v,
			int groupPosition, int childPosition, long id) {
		// TODO Auto-generated method stub
		ExpListChild c = expAdapter.getActiveMenu().get(groupPosition)
				.getContent().get(childPosition);
		Toast.makeText(getBaseContext(), c.getLabel(), Toast.LENGTH_LONG).show(); // might be useful to have, but for now it is for the developer
		if (c.getTag().equals("start")) {
			startMenu(c.getLabel());
			return true;
		} else if (c.getTag().equals("help1")) {
			helpMenuOne(c.getLabel());
			return true;
		} else if (c.getTag().equals("online")) {
			onlineMenu(c.getLabel());
			return true;
		}else if (c.getTag().equals("help2")) {
			helpMenuTwo(c.getLabel());
			return true;
		}else if (c.getTag().equals("group")) {
			groupMenu(c.getLabel());
			return true;
		}else if (c.getTag().equals("help3")) {
			helpMenuThree(c.getLabel());
			return true;
		}

		return false;
	}

	/**
	 * onOptionsItemSelected - Defines the action on pressed menu button
	 * 
	 * @param item - The menu item          
	 * @return Returns a boolean to indicate if the operation went well or not.
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		return menuMenu(item);
	}

	/**
	 * onKeyDown - Defines action on key click in the "android key menu".(Keys:Camera, volume, on/off, e.g.)
	 * 
	 * @param keyCode - an int, describes which key was pressed.    
	 * @param event - a KeyEvent, describes what event the key was exposed to.          
	 * @return Returns a boolean to indicate if the operation went well or not.
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		return androidMenu(keyCode, event);
	}

// ---------------------------------------------------------------------------------------------------------------------------------------------------
// Menu Methods Section - Identifies which item in the menu was clicked and calls a special operation method or standard operation method. -----------
// ---------------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * startMenu - Is an ExpandableListView menu method which use the label to
	 * 			   identify what operation method to perform in the start menu, or
	 * 			   opens the specified dialog.
	 * @param label - String with the pressed child's label.
	 *            
	 */  
	public void startMenu(String label) {
			if (label.equals(getResources().getStringArray(R.array.start_children)[0])) {	// Login
				createNewDialog(label, R.layout.login);
			}else if (label.equals(getResources().getStringArray(R.array.start_children)[1])) { // Create Account
				createNewDialog(label, R.layout.create_user_account);
			} else if (label.equals( getResources().getStringArray(R.array.start_children)[2])) {	// Select Server
				Toast.makeText(getBaseContext(), this.getResources().getString(R.string.error_service_not_made), Toast.LENGTH_LONG).show();
/*group 2*/	} else if (label.equals(getResources().getStringArray(R.array.start_children)[4])) { // About
				createNewDialog(label, R.layout.info);	
				TextView text = (TextView) custom.findViewById(R.id.info);
				text.setText(getResources().getText(R.string.about_text));
			} else if (label.equals(getResources().getStringArray(R.array.start_children)[5])) { // Help	
				goToMenu("help1", getResources().getText(R.string.add_help_tile));
			} else if (label.equals(getResources().getStringArray(R.array.start_children)[6])) { // Exit
				onExit();
			}
	}

	/**
	 * helpMenuOne - Is an ExpandableListViwe menu method which use the label to
	 * 			  	 identify what operation method to perform in help menu one, or
	 * 				 open one specific dialog with the info layout and sets its content
	 * 				 depending on which choice the user made.
	 * @param label - String with the pressed child's label.          
	 */
	public void helpMenuOne(String label) {

			createNewDialog(label, R.layout.info);	
			TextView text = (TextView) custom.findViewById(R.id.info);

			if(label.equals(getResources().getStringArray(R.array.help_children1)[0])){ // 1.1. Help
				text.setText(getResources().getText(R.string.help1_1));				
			}else if(label.equals(getResources().getStringArray(R.array.help_children1)[1])){ // 1.2. About
				text.setText(getResources().getText(R.string.help1_2));
			}else if(label.equals(getResources().getStringArray(R.array.help_children1)[2])){ // 1.3. Settings
				text.setText(getResources().getText(R.string.help1_3));
			}else if(label.equals(getResources().getStringArray(R.array.help_children1)[3])){ // 1.4. Navigation
				text.setText(getResources().getText(R.string.help1_4));
/*group 2*/	}else if(label.equals(getResources().getStringArray(R.array.help_children1)[5])){ // 2.1. Create Account
				text.setText(getResources().getText(R.string.help2_1));
			}else if(label.equals(getResources().getStringArray(R.array.help_children1)[6])){ // 2.2. Login
				text.setText(getResources().getText(R.string.help2_2));
			}else if(label.equals(getResources().getStringArray(R.array.help_children1)[7])){ // 2.3. Select Server 
				text.setText(getResources().getText(R.string.help2_3));
/*group 3*/	}else if(label.equals(getResources().getStringArray(R.array.help_children1)[9])){ // About
				text.setText(getResources().getText(R.string.about_text));		
			}else if(label.equals(getResources().getStringArray(R.array.help_children1)[10])){ // Back
				custom.cancel(); // Ugly solution I know, but is the fastest way to fix the bug of dialog back appearing 
				onBack();
			}	
	
	}

	/**
	 * onlineMenu - Is an ExpandableListView menu method which use the label to
	 * 				identify what operation method to perform in the online menu, or
	 * 				opens a specific dialog.
	 * @param lable - String with the pressed child's label.
	 *           
	 */
	public void onlineMenu(String label) {

			if (label.equals(getResources().getStringArray(R.array.online_children)[0])) { // My Groups
				//createNewDialog(label, R.layout.my_groups);
				// test, Behöver skicka in gruppens namn från ListView (outdated)
//				CharSequence title = new String(": " + label);
//				goToMenu("group", title);
			} else if (label.equals(getResources().getStringArray(R.array.online_children)[1])) { // Create Group
				createNewDialog(label, R.layout.create_group);
			} else if (label.equals(getResources().getStringArray(R.array.online_children)[2])) { // Join Group
			//	createNewDialog(label, R.layout.join_groups);
			} else if (label.equals(getResources().getStringArray(R.array.online_children)[3])) { // Answer Group Invites
				//createNewDialog(label, R.layout.answer_group_invites);
/*group 2*/	} else if (label.equals(getResources().getStringArray(R.array.online_children)[5])) { // Change User Information
				createNewDialog(label, R.layout.change_user_information);
			} else if (label.equals(getResources().getStringArray(R.array.online_children)[6])) { // Change Password
				createNewDialog(label, R.layout.change_user_password);
			} else if (label.equals(getResources().getStringArray(R.array.online_children)[7])) { // End Account
				createNewDialog(label, R.layout.end_account);
/*group 3*/	} else if (label.equals(getResources().getStringArray(R.array.online_children)[9])) { // Logout
				onBack();
			} else if (label.equals(getResources().getStringArray(R.array.online_children)[10])) { // About
				createNewDialog(label, R.layout.info);
			} else if (label.equals(getResources().getStringArray(R.array.online_children)[11])) { // Help
				goToMenu("help2", getResources().getText(R.string.add_help_tile)); 
			} 
	}
	/**
	 * helpMenuTwo - Is an ExpandableListViwe menu method which use the label to
	 * 			  	 identify what operation method to perform in help menu two, or
	 * 				 open one specific dialog with the info layout and sets its content
	 * 				 depending on which choice the user made.
	 * 
	 * @param label - String with the pressed child's label.          
	 */	
	public void helpMenuTwo(String label){
		createNewDialog(label, R.layout.info);	
		TextView text = (TextView) custom.findViewById(R.id.info);
			if(label.equals(getResources().getStringArray(R.array.help_children2)[0])){ // 3.1. My Groups
				text.setText(getResources().getText(R.string.help3_1));
			}else if(label.equals(getResources().getStringArray(R.array.help_children2)[1])){ // 3.2. Create Group
				text.setText(getResources().getText(R.string.help3_2));
			}else if(label.equals(getResources().getStringArray(R.array.help_children2)[2])){ // 3.3. Join Group
				text.setText(getResources().getText(R.string.help3_3));
			}else if(label.equals(getResources().getStringArray(R.array.help_children2)[3])){ // 3.4. Answer Group Invites
				text.setText(getResources().getText(R.string.help3_4));
/*group 2*/	}else if(label.equals(getResources().getStringArray(R.array.help_children2)[5])){ // 4.1. Change User Information
				text.setText(getResources().getText(R.string.help4_1));
			}else if(label.equals(getResources().getStringArray(R.array.help_children2)[6])){ // 4.2. Change Password
				text.setText(getResources().getText(R.string.help4_2));
			}else if(label.equals(getResources().getStringArray(R.array.help_children2)[7])){ // 4.3. End Account
				text.setText(getResources().getText(R.string.help4_3));
/*group 3*/	}else if(label.equals(getResources().getStringArray(R.array.help_children2)[9])){ // About
				text.setText(getResources().getText(R.string.about_text));		
			}else if(label.equals(getResources().getStringArray(R.array.help_children2)[10])){ // Back
				custom.cancel();
				onBack();
			}
	}

	/**
	 * groupMenu - Is an ExpandableListViwe menu method which use the label to
	 * 			   identify what operation method to perform in the group menu, or
	 * 			   opens a specific dialog.
	 * @param label 
	 */
	public void groupMenu(String label){
			if (label.equals(getResources().getStringArray(R.array.group_children)[0])) { // Group members
			// dialog
			}else if(label.equals(getResources().getStringArray(R.array.group_children)[1])){ // Group Info
				createNewDialog(label, R.layout.info);
				//((TextView) custom.findViewById(R.id.info)).setText(getResources().getText(R.string.help5_1));// Change the getResources().getText(R.string.help5_1) to data from group.
			}else if(label.equals(getResources().getStringArray(R.array.group_children)[2])){ // Leave Group
	
/*group 2*/	}else if (label.equals(getResources().getStringArray(R.array.group_children)[4])) { // Chat
			// dialog
/*group 3*/	}else if (label.equals(getResources().getStringArray(R.array.group_children)[6])) { // About
				createNewDialog(label, R.layout.info);
			}else if (label.equals(getResources().getStringArray(R.array.group_children)[7])) { // Help
				goToMenu("help3", this.getResources().getText(R.string.add_help_tile)); 
			}else if (label.equals(getResources().getStringArray(R.array.group_children)[8])) { // Back
				onBack();
			}
			// Authorities Moderator & admin
/*m&a*/		else if(label.equals(getResources().getStringArray(R.array.authority_children)[0])){ // Send Invite
				
			}else if(label.equals(getResources().getStringArray(R.array.authority_children)[1])){ // Answer Join Request
				
			}else if(label.equals(getResources().getStringArray(R.array.authority_children)[2])){ // Add Feature ... NO time, Not implemented
				Toast.makeText(getBaseContext(), this.getResources().getString(R.string.error_service_not_made), Toast.LENGTH_LONG).show();
			}else if(label.equals(getResources().getStringArray(R.array.authority_children)[3])){ // Remove Feature ... NO time, Not implemented
				Toast.makeText(getBaseContext(), this.getResources().getString(R.string.error_service_not_made), Toast.LENGTH_LONG).show();
			}else if(label.equals(getResources().getStringArray(R.array.authority_children)[4])){ // Remove Group Member
				
				/*admin only authorities*/ 
/*a*/		}else if(label.equals(getResources().getStringArray(R.array.authority_children)[6])){ // Change Group Name
				createNewDialog(label, R.layout.change_group_name);
			}else if(label.equals(getResources().getStringArray(R.array.authority_children)[7])){ // Set Group Info
				//createNewDialog(label, R.layout.);
			}else if(label.equals(getResources().getStringArray(R.array.authority_children)[8])){ // Promote User
				//createNewDialog(label, R.layout.);
			}else if(label.equals(getResources().getStringArray(R.array.authority_children)[9])){ // Demote User
				//createNewDialog(label, R.layout.);
			}else if(label.equals(getResources().getStringArray(R.array.authority_children)[10])){ // End Group
				//endGroup();
			}
			
			
			
	}
	
	/**
	 * helpMenuThree - Is an ExpandableListViwe menu method which use the label to
	 * 			  	   identify what operation method to perform in help menu three, or
	 * 				   open one specific dialog with the info layout and sets its content
	 * 				   depending on which choice the user made.
	 * 
	 * @param label - String with the pressed child's label.          
	 */	
	public void helpMenuThree(String label){
		createNewDialog(label, R.layout.info);	
		TextView text = (TextView) custom.findViewById(R.id.info);
			if(label.equals(getResources().getStringArray(R.array.help_children3)[0])){ // 5.1. Group members
				text.setText(getResources().getText(R.string.help5_1));
			}else if(label.equals(getResources().getStringArray(R.array.help_children3)[1])){ // 5.2. Group info
				text.setText(getResources().getText(R.string.help5_2));
			}else if(label.equals(getResources().getStringArray(R.array.help_children3)[2])){ // 5.3. Leave Group
				text.setText(getResources().getText(R.string.help5_3));
/*group 2*/	}else if(label.equals(getResources().getStringArray(R.array.help_children3)[4])){ // 6.1. Chat
				text.setText(getResources().getText(R.string.help6_1));
/*group 3*/	}else if(label.equals(getResources().getStringArray(R.array.help_children3)[6])){ // 7.1. What Is A Moderator?
				text.setText(getResources().getText(R.string.help7_1));
			}else if(label.equals(getResources().getStringArray(R.array.help_children3)[7])){ // 7.2. Send Invites
				text.setText(getResources().getText(R.string.help7_2));
			}else if(label.equals(getResources().getStringArray(R.array.help_children3)[8])){ // 7.3. Answer Join Request
				text.setText(getResources().getText(R.string.help7_3));
			}else if(label.equals(getResources().getStringArray(R.array.help_children3)[9])){ // 7.4. Add Feature 
				text.setText(getResources().getText(R.string.help7_4));
			}else if(label.equals(getResources().getStringArray(R.array.help_children3)[10])){ // 7.5. Remove Feature
				text.setText(getResources().getText(R.string.help7_5));
			}else if(label.equals(getResources().getStringArray(R.array.help_children3)[11])){ //7.6. Remove Group Member
				text.setText(getResources().getText(R.string.help7_6));
/*group 4*/ }else if(label.equals(getResources().getStringArray(R.array.help_children3)[13])){ // 8.1. What Is A Group Admin?
				text.setText(getResources().getText(R.string.help8_1));
			}else if(label.equals(getResources().getStringArray(R.array.help_children3)[14])){ // 8.2. Change Group Name 
				text.setText(getResources().getText(R.string.help8_2));
			}else if(label.equals(getResources().getStringArray(R.array.help_children3)[15])){ // 8.3. Set Group Info 
				text.setText(getResources().getText(R.string.help8_3));
			}else if(label.equals(getResources().getStringArray(R.array.help_children3)[16])){ // 8.4. Promote User
				text.setText(getResources().getText(R.string.help8_4));
			}else if(label.equals(getResources().getStringArray(R.array.help_children3)[17])){ // 8.5. Demote User
				text.setText(getResources().getText(R.string.help8_5));
			}else if(label.equals(getResources().getStringArray(R.array.help_children3)[18])){ // 8.6. End Group
				text.setText(getResources().getText(R.string.help8_6));
/*group 5*/	}else if(label.equals(getResources().getStringArray(R.array.help_children3)[20])){ // About
				text.setText(getResources().getText(R.string.about_text));
			}else if(label.equals(getResources().getStringArray(R.array.help_children3)[21])){	// Back						
				custom.cancel();
				onBack();				
			}
	}
	
	
	/**
	 * menuMenu - Is the menus menu method which uses the MenuItem to identify
	 * 			  what operation method to perform.
	 * 
	 * @param item - the selected menu item.    
	 * @return Returns a boolean to indicate if the method had a defined operation for the selected menu item.
	 */
	public boolean menuMenu(MenuItem item) {
		if (item.getItemId() == R.id.menu_settings) { // Settings where selected.
			settingsDialog();
			return true;
		}
		return false;
	}

	/**
	 * androidMenu - Is the android menu which uses the keyCode and keyEvent to
	 * 				 identify what operation method to perform.
	 * 
	 * @param keyCode - an int, describes which key was pressed.
	 * @param event - a KeyEvent, describes what event the key was exposed to.
	 * @return Returns a boolean to indicate if the method had a defined operation for the selected menu item.      		   
	 */
	public boolean androidMenu(int keyCode, KeyEvent event) {
		// BACK key was pressed, once?(repeat count)
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) { 
			onBack();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

// ---------------------------------------------------------------------------------------------------------------------------------------------------
// Special Operation Methods - Operations that is only used by one menu item -------------------------------------------------------------------------
// ---------------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * settingsDialog - Activates the settings dialog. Settings dialog is where
	 * 					the user can decide on the look of the application(set background image).
	 */
	public void settingsDialog() {
		AlertDialog.Builder settings = new AlertDialog.Builder(MainActivity.this);
		settings.setTitle(R.string.settings_title);
		settings.setIcon(CONTEXT_IGNORE_SECURITY);
		settings.setNegativeButton(getResources().getString(R.string.done), null);
		settings.create().show();
	}

// ---------------------------------------------------------------------------------------------------------------------------------------------------
// Standard Operation Methods - Operations that can be used by several menu items from different menus. ----------------------------------------------
// ---------------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * goToMenu - A general method that goes to (creates) the indicated
	 * expandable list menu.
	 * 
	 * @param menuName - the name of the menu          
	 * @param charSequence - the title of the menu       
	 */
	public void goToMenu(String menuName, CharSequence titel) {
		backExpMenu.push(expAdapter);		// lägger menyn på stacken
		expAdapter = new ExpListAdapter(MainActivity.this, menuName); // skapar den nya meny adaptern
		expandList.setAdapter(expAdapter);								// sätter den nya meny adaptern till menyns adapter
		// getResources().getString(R.string.title_activity_main) the static value
		backAppTitle.push(titel); // lägger den gamla titeln på stacken
		setTitle(getResources().getString(R.string.title_activity_main)  + titel);	// lägger den gamla titeln + ny titel som titel
		
	}

	/**
	 * onBack - A standard operation for the user to return to a previous menu.
	 * 			If that menu is in the start menu, the user probably want to exit the
	 * 			application which makes this method to launch the standard operation for
	 * 			exit. If the user is in the online menu, the user probably want to logout
	 * 			from the account which makes this method to launch the standard operation
	 * 			for logout. NOTE: logout() NOT COMPLETE YET.
	 */
	public void onBack() {
		try {	
			if(backAppTitle.isEmpty()){
				onExit();		
			}
			if(backAppTitle.peek().equals( getResources().getString(R.string.add_online_tile))){
				logout();
			}else{
				expAdapter = backExpMenu.pop();
				expandList.setAdapter(expAdapter);			
				backAppTitle.pop();		
				CharSequence titleAdd = new String(getResources().getString(R.string.title_activity_main) + backAppTitle.peek().toString() );
				setTitle(  titleAdd );
			}
				
		} catch (Exception e) {
			setTitle(getResources().getString(R.string.title_activity_main));
		}


	}
	

	/**
	 * logout - NOT COMPLETE YET, needs comunication with server
	 * 			Warns the user with an AlertDialog where the user can chose to 
	 * 			go offline or stay online
	 */
	public void logout() {
			AlertDialog.Builder exit = new AlertDialog.Builder(MainActivity.this);
			exit.setTitle(R.string.go_offline_title)
				.setIcon(CONTEXT_IGNORE_SECURITY)
				.setNeutralButton(R.string.yes,new DialogInterface.OnClickListener(){
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						// Send logout to server WHEN GOING OFFLINE before these operations
						try{// prevents bug, can't logout twice if multiple dialogs shows (cause fast clicking)
							if(!((backAppTitle.peek()).equals(null))){ 
								Toast.makeText(getBaseContext(), R.string.user_offline_message, Toast.LENGTH_LONG).show();
								expAdapter = backExpMenu.pop();
							expandList.setAdapter(expAdapter);			
							backAppTitle.pop();
							setTitle(getResources().getString(R.string.title_activity_main));
							stopService(new Intent(MainActivity.this, AppService.class));
							}
						}catch(Exception e){/*Not necessary to do anything here yet*/}
					}		
				})								
				.setNegativeButton(R.string.no, null).create().show();
	}

	/**
	 * onExit - A standard operation for the user to exit the application, try
	 * 			to stop the user with a Dialog!!
	 */
	public void onExit() {
		AlertDialog.Builder exit = new AlertDialog.Builder(MainActivity.this);
		exit.setTitle(R.string.exit_title)
				.setIcon(CONTEXT_IGNORE_SECURITY)
				.setNeutralButton(R.string.yes,
						new DialogInterface.OnClickListener() {
							// Add actions on click here
							public void onClick(DialogInterface dialog,
									int which) {
								finish();
								System.exit(0);
							}
						})
				.setNegativeButton(R.string.no, null).create().show();
	}

// ---------------------------------------------------------------------------------------------------------------------------------------------------
// Build Dialog Section - Builds the dialog when an expandable list item is selected, sets its content and the contents filters. ---------------------
// ---------------------------------------------------------------------------------------------------------------------------------------------------
	
	/**
	 * createNewDialog - Creates the specified dialog when an expandable list
	 * 					 menu item is selected. All dialogs will have a title(null if no String)
	 * 					 and a back button. Make sure the back button is included in the dialogs
	 * 					 layout.
	 * @param title - A String with the selected menu items name, which becomes the title of the dialog.                      
	 * @param layout - An int with the dialogs layout id.          
	 */
	public void createNewDialog(String title, int layout) {
		custom = new CustomDialog(MainActivity.this, layout);
		custom.setTitle(title);
		// Create buttons and set EditText filters for the different layouts.
		if (layout == R.layout.info) {
		}else if (layout == R.layout.create_user_account) {		
			createDialogButton(R.id.create_account_button);
			((EditText) custom.findViewById(R.id.username_text_input)).setFilters(filters);
			((EditText) custom.findViewById(R.id.password1)).setFilters(filters);
			((EditText) custom.findViewById(R.id.password2)).setFilters(filters);
			((EditText) custom.findViewById(R.id.firstname_text_input)).setFilters(filters);
			((EditText) custom.findViewById(R.id.surname_text_input)).setFilters(filters);
			((EditText) custom.findViewById(R.id.email_text_input)).setFilters(filters);		
		}else if (layout == R.layout.login) {
			createDialogButton(R.id.login_button);
			((EditText) custom.findViewById(R.id.user_text_input)).setFilters(filters);
			((EditText) custom.findViewById(R.id.password)).setFilters(filters);
		}
//		else if(layout == R.layout.my_groups){
//			
//		}
		else if (layout == R.layout.create_group){
			createDialogButton(R.id.create_group_button);
			((EditText) custom.findViewById(R.id.user_text_input)).setFilters(filters);
		}
//		else if(layout == R.layout.join_groups){
//			
//		}
//		else if(layout == R.layout.answer_group_invites){
//			
//		}
		else if(layout == R.layout.change_user_information){
			createDialogButton(R.id.change_userinfo_button);
			((EditText) custom.findViewById(R.id.change_username_text_input)).setFilters(filters);
			((EditText) custom.findViewById(R.id.change_first_name_text_input)).setFilters(filters);
			((EditText) custom.findViewById(R.id.change_surname_text_input)).setFilters(filters);
			((EditText) custom.findViewById(R.id.change_email_text_input)).setFilters(filters);
		}else if(layout == R.layout.change_user_password){
			createDialogButton(R.id.change_password_button);
			((EditText) custom.findViewById(R.id.password1)).setFilters(filters);
			((EditText) custom.findViewById(R.id.password2)).setFilters(filters);
			((EditText) custom.findViewById(R.id.password)).setFilters(filters);
		}else if(layout == R.layout.end_account){ // move this to last later on
			createDialogButton(R.id.end_account_button);
			((EditText) custom.findViewById(R.id.password1)).setFilters(filters);
			((EditText) custom.findViewById(R.id.password2)).setFilters(filters);
		}else if(layout == R.layout.change_group_name){
			createDialogButton(R.id.change_group_name_button);
			((EditText) custom.findViewById(R.id.user_text_input)).setFilters(filters);
			((EditText) custom.findViewById(R.id.password)).setFilters(filters);
		}
		createDialogButton(R.id.dialog_back_button);
		custom.show();
	}

	/**
	 * createDialogButton - Creates a dialog button and adds the dialog
	 * listener.
	 * 
	 * @param buttonId  - An int with the buttons id.          
	 */
	public void createDialogButton(int buttonId) {
		Button button = (Button) custom.findViewById(buttonId);
		button.setOnClickListener(listener);
	}
		
// ---------------------------------------------------------------------------------------------------------------------------------------------------
// Dialog OnClick/Selection Section -  Identifies selected dialog button and its course of action. ---------------------------------------------------
// ---------------------------------------------------------------------------------------------------------------------------------------------------
	// The custom dialog's listener
	private View.OnClickListener listener = new View.OnClickListener() {
		/**
		 * onClick - The listeners event method.
		 * 
		 * @param v - View of the click able item         
		 */
		public void onClick(View v) {
			// TODO Auto-generated method stub
			// Identify selected  dialog button
			if (v.equals(custom.findViewById(R.id.dialog_back_button))) { 
				closeDialog();
			} else if (v.equals(custom.findViewById(R.id.create_account_button))) {
				createAccount();
			}else if(v.equals(custom.findViewById(R.id.login_button))){
				login();
			}else if(v.equals(custom.findViewById(R.id.create_group_button))){
				createGroup();
			}else if(v.equals(custom.findViewById(R.id.change_userinfo_button))){
				changeUserInfo();
			}else if(v.equals(custom.findViewById(R.id.change_password_button))){ // move this to last later on
				changePassword();
			}else if(v.equals(custom.findViewById(R.id.end_account_button))){ 
				endAccount();
			}else if(v.equals(custom.findViewById(R.id.change_group_name_button))){
				changeGroupName();
			}
		}
	};

// ---------------------------------------------------------------------------------------------------------------------------------------------------
// Dialog Standard Operations Section - methods used by several dialogs and other dialog methods. ----------------------------------------------------
// ---------------------------------------------------------------------------------------------------------------------------------------------------

	/**
	 * closeDialog - A standard operation to close the dialog
	 */
	public void closeDialog() {
		custom.cancel();
	}

	/**
	 *  NOT COMPLETE, might not be needed
	 * goOnline - Tries to go online, catches an Exception and explains what went wrong for the user.
	 * @param username - A String with the users user name.
	 * @param password - A String with the users password.
	 * @throws Exception - If it could not go online, it will throw a message
	 * 					   to explain what went wrong
	 */
	public void goOnline(String username, String password) throws Exception{
		try{
			protocol.attemptLogin(username, password);	// Not yet implemented
		}catch(Exception e){
			throw new Exception(getResources().getString(R.string.failed_to_go_online));
		}
		Toast.makeText(getBaseContext(), R.string.user_online_message, Toast.LENGTH_LONG).show();
	}
	
	
	
	
	
// ---------------------------------------------------------------------------------------------------------------------------------------------------
// Dialog Special Operations Section - methods used by one dialog item. ------------------------------------------------------------------------------
// ---------------------------------------------------------------------------------------------------------------------------------------------------

	/**
	 *  NOT COMPLETE
	 * createAccount - Reads in the user input and checks if its correct, if not send
	 * 		   		   a message to inform the user of what went wrong. If correct
	 * 		   		   close the dialog and go online.
	 */
	public void createAccount() {
		EditText username = (EditText) custom.findViewById(R.id.username_text_input);
		EditText password1 = (EditText) custom.findViewById(R.id.password1);
		EditText password2 = (EditText) custom.findViewById(R.id.password2);

		EditText firstName = (EditText) custom.findViewById(R.id.firstname_text_input);
		EditText surname = (EditText) custom.findViewById(R.id.surname_text_input);
		EditText email = (EditText) custom.findViewById(R.id.email_text_input);
		
		try {
			// Correct the user (user name 4-20 char, password 6-20 char, names, max 20 char  )
			// Check user name
			if (username.getText().toString().length() == 0){
				throw new Exception(getResources().getString(R.string.account_error_no_user_name));
			}else if (username.getText().toString().length() < minUsername){
				throw new Exception(getResources().getString(R.string.account_error_short_user_name));
			} else if(username.getText().toString().length() > maxUsername){
				throw new Exception(getResources().getString(R.string.account_error_long_user_name));
			}	// Check password
			if((password1.getText().toString().length()==0)|| (password2.getText().toString().length()==0)){
				throw new Exception(getResources().getString(R.string.account_error_no_password));
			}else if ((password1.getText().toString().length() < minPassword) || (password2.getText().toString().length() < minPassword) ){
				throw new Exception(getResources().getString(R.string.account_error_short_password));
			}else if((password1.getText().toString().length() > maxPassword) || (password2.getText().toString().length() >maxPassword)){
				throw new Exception(getResources().getString(R.string.account_error_long_password));
			}else if (!((password1.getText().toString()).equals(password2.getText().toString())))
				throw new Exception(getResources().getString(R.string.account_error_password_match));	
			// Check other user information
			if((firstName.getText().toString().length() > maxName)){
				throw new Exception(getResources().getString(R.string.account_error_first_name));
			}if((surname.getText().toString().length() >maxName))
				throw new Exception(getResources().getString(R.string.account_error_surname));
			if(email.getText().toString().length() > maxEmail ) // might be unnecessary, depending on maximum input size in filter.
				throw new Exception(getResources().getString(R.string.account_error_email));
			// Attempt validation check of email. maybe
			// The create account attempt, check if everything went well, if so go online.
			 if(!(protocol.attemptCreationOfAccount(username.getText().toString(),password1.getText().toString(), 
					 firstName.getText().toString(),surname.getText().toString()))){
				 throw new Exception(getResources().getString(R.string.failed_to_create_account));
			 }else{

				// Go online... one should go online (server should set the user) 
				// once the account has been created, meaning -> go to the online menu
				 //or else goOnline(username.getText().toString(), password1.getText().toString()); 
				 startService(new Intent(this, AppService.class));
				 goToMenu("online",getResources().getString(R.string.add_online_tile));				
				closeDialog();
			 }
			 Toast.makeText(getBaseContext(),R.string.create_account_success, Toast.LENGTH_LONG).show();
		} catch (Exception e) {
			Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_LONG).show();
		}
	}
	/**
	 *  NOT COMPLETE
	 * login - Reads in the user input and checks if its correct, if not send
	 * 		   a message to inform the user of what went wrong. If correct
	 * 		   close the dialog and go online.
	 */
	public void login() {
		EditText username = (EditText) custom.findViewById(R.id.user_text_input);
		EditText password = (EditText) custom.findViewById(R.id.password);
		
		try{
			// Correct the user (password 6-20 char, user name 4-20 char )
			// Check user name
			if ((username.getText().toString().length()) == 0){
				throw new Exception(getResources().getString(R.string.account_error_no_user_name));
			}else if (username.getText().toString().length() < minUsername){
				throw new Exception(getResources().getString(R.string.account_error_short_user_name));
			} else if(username.getText().toString().length() > maxUsername){
				throw new Exception(getResources().getString(R.string.account_error_long_user_name));
			} // Check Password
			if(password.getText().toString().length() == 0){
				throw new Exception(getResources().getString(R.string.account_error_no_password));
			}else if (password.getText().toString().length() < minPassword){
				throw new Exception(getResources().getString(R.string.account_error_short_password));
			}else if(password.getText().toString().length() > maxPassword){
				throw new Exception(getResources().getString(R.string.account_error_long_password));
			}		
			// Attempt go online, throws exceptions
			 goOnline(username.getText().toString(), password.getText().toString());
			 
			 startService(new Intent(this, AppService.class));
			 
			goToMenu("online",getResources().getString(R.string.add_online_tile));
			closeDialog();
			 //service.startService(new Intent(this, AppService.class));
		}catch(Exception e){
			//  R.string.failed_to_login
			Toast.makeText(getBaseContext(),e.getMessage(), Toast.LENGTH_LONG).show();
		}
	}
	/**
	 * autoLogin - NOT MADE 
	 */
	public void autoLogin(){
		// launched from login with a button, should throw an exception if the user 
		// account does not exist.
		
		// reads in user name and password from the local database
		// goOnline(username, password)
		// Close dialog...
	}
	
	/**
	 * NOT COMPLETE
	 * createGroup - Reads in the user input and checks if its correct, if not send
	 * 		  		 a message to inform the user of what went wrong. If correct
	 * 		 		 close the dialog and go to the created groups menu. 
	 */
	public void createGroup(){
		// Attempt create group
		EditText groupName = (EditText) custom.findViewById(R.id.user_text_input);

		try{
			// Check Group Name
			if (groupName.getText().toString().length() == 0){
				throw new Exception(getResources().getString(R.string.create_group_error_no_name));
			}else if(groupName.getText().toString().length() < minGroupName){
				throw new Exception(getResources().getString(R.string.create_group_error_name_to_short));
			}else if(groupName.getText().toString().length() > maxGroupName){
				throw new Exception(getResources().getString(R.string.create_group_error_name_to_long));
			}
			
			if(protocol.attemptCreateGroup(groupName.getText().toString())){
				// Goes directly to the created group
				CharSequence s= new String(": "+groupName.getText().toString());
				goToMenu("group",s);
				closeDialog();
				expAdapter.addAuthority(AUTHORITY_ADMIN);
				
				// make sure the user becomes admin in database.
			}
			
			Toast.makeText(getBaseContext(),R.string.create_group_success, Toast.LENGTH_LONG).show();
		}catch(Exception e){
			Toast.makeText(getBaseContext(),e.getMessage(), Toast.LENGTH_LONG).show();
		}
	}
	
	
	public void changeUserInfo(){
		EditText username = (EditText) custom.findViewById(R.id.change_username_text_input);
		EditText firstName = (EditText) custom.findViewById(R.id.change_first_name_text_input);
		EditText surname = (EditText) custom.findViewById(R.id.change_surname_text_input);
		EditText email = (EditText) custom.findViewById(R.id.change_email_text_input);
		try{// Check the user input
			if (username.getText().toString().length() < minUsername){
				throw new Exception(getResources().getString(R.string.change_userinfo_username_input_to_short));
			} else if(username.getText().toString().length() > maxUsername){
				throw new Exception(getResources().getString(R.string.change_userinfo_username_input_to_long));
			}else if((firstName.getText().toString().length() > maxName)){ 
				throw new Exception(getResources().getString(R.string.change_userinfo_first_name_input_to_long));
			}else if((surname.getText().toString().length() >maxName)){
				throw new Exception(getResources().getString(R.string.change_userinfo_surname_input_to_long));
			}else if(email.getText().toString().length() > maxEmail ) // might be unnecessary, depending on maximum input size in filter.
				throw new Exception(getResources().getString(R.string.change_userinfo_email_input_to_long));
			// Attempt validation check of email. maybe
			
			// Attempt change of user information 
			
			closeDialog();
			Toast.makeText(getBaseContext(), R.string.change_userinfo_success, Toast.LENGTH_LONG).show();
		}catch(Exception e){
			Toast.makeText(getBaseContext(),e.getMessage(), Toast.LENGTH_LONG).show();
		}
		
		
	}
	
	/**
	 * changePassword - Reads in the user input and checks if its correct, if not send
	 * 		   			a message to inform the user of what went wrong. If correct
	 * 		   			change the password and close the dialog.
	 */
	public void changePassword(){
		EditText oldPassword = (EditText) custom.findViewById(R.id.password);
		EditText password1 = (EditText) custom.findViewById(R.id.password1);
		EditText password2 = (EditText) custom.findViewById(R.id.password2);
		try{
			// Check if the former password is allowed to be sent and 
			// checks if the two new passwords are correct and matches 
			if (oldPassword.getText().toString().length() == 0){			
				throw new Exception(getResources().getString(R.string.change_error_no_current_password)); 
			}else if(oldPassword.getText().toString().length() < minPassword){
				throw new Exception(getResources().getString(R.string.change_error_current_to_short_password));
			}else if(oldPassword.getText().toString().length() > maxPassword){
				throw new Exception(getResources().getString(R.string.change_error_current_to_long_password));
			}else if((password1.getText().toString().length() == 0) || (password2.getText().toString().length() == 0)){
				throw new Exception(getResources().getString(R.string.change_error_no_new_password));
			}else if((password1.getText().toString().length() < minPassword) || (password2.getText().toString().length() < minPassword)){
				throw new Exception(getResources().getString(R.string.change_error_new_to_short_password));
			}else if((password1.getText().toString().length() > maxPassword) || (password2.getText().toString().length() > maxPassword)){
				throw new Exception(getResources().getString(R.string.change_error_new_to_long_password));
			}else if(!((password1.getText().toString()).equals(password2.getText().toString()))){
				throw new Exception(getResources().getString(R.string.change_error_new_passwords_mismatch));
			}
	
				
				// request change of user password
				// throw failed exception if no success
			//Success
			closeDialog();
			Toast.makeText(getBaseContext(),R.string.change_password_success, Toast.LENGTH_LONG).show();
		}catch(Exception e){
			Toast.makeText(getBaseContext(),e.getMessage(), Toast.LENGTH_LONG).show();
		}
		

	}
	
	/**
	 * endAccount - Reads in the user input and checks if its correct, if not send
	 * 		   		a message to inform the user of what went wrong. If correct
	 * 		   		end the account, close the dialog and go back to start dialog.
	 */
	//@SuppressWarnings("finally")
	public void endAccount(){
		EditText password1 = (EditText) custom.findViewById(R.id.password1);
		EditText password2 = (EditText) custom.findViewById(R.id.password2);
		
		try{
			// Check if the user password input is correct
			if((password1.getText().toString().length() == 0) || (password2.getText().toString().length() == 0)){
				throw new Exception(getResources().getString(R.string.end_account_no_passwords));
			}else if((password1.getText().toString().length() < minPassword) || (password2.getText().toString().length() < minPassword)){
				throw new Exception(getResources().getString(R.string.end_account_password_to_short));
			}else if((password1.getText().toString().length() > maxPassword) || (password2.getText().toString().length() > maxPassword)){
				throw new Exception(getResources().getString(R.string.end_account_password_to_long));
			}else if(!((password1.getText().toString()).equals(password2.getText().toString()))){
				throw new Exception(getResources().getString(R.string.end_account_password_mismatch));
			}
			// Give warning
			AlertDialog.Builder exit = new AlertDialog.Builder(MainActivity.this);
			exit.setTitle(R.string.end_account_last_chance)
					.setIcon(CONTEXT_IGNORE_SECURITY)
					.setNeutralButton(R.string.yes,
							new DialogInterface.OnClickListener() {
								// Add actions on click here
								public void onClick(DialogInterface dialog,
										int which) {
									// Attempt end account
									
									// if failed throw new Exception(getResources().getString(R.string.end_account_failed));
									
									// Success go back to start menu and close dialog
									try{// prevents bug, can't logout twice if multiple dialogs shows (cause fast clicking)
										if(!((backAppTitle.peek()).equals(null))){
											closeDialog();
											Toast.makeText(getBaseContext(),R.string.end_account_success, Toast.LENGTH_LONG).show();
											Toast.makeText(getBaseContext(), R.string.user_offline_message, Toast.LENGTH_LONG).show();
											expAdapter = backExpMenu.pop();
											expandList.setAdapter(expAdapter);			
											backAppTitle.pop();
											setTitle(getResources().getString(R.string.title_activity_main));		
										}
									}catch(Exception e){}

								}
							})
					.setNegativeButton(R.string.no, null).create().show();
		}catch(Exception e){
			Toast.makeText(getBaseContext(),e.getMessage(), Toast.LENGTH_LONG).show();
		}

	}
	
	/**
	 * changeGroupName - 
	 */
	public void changeGroupName(){
		// Attempt create group
		EditText groupName = (EditText) custom.findViewById(R.id.user_text_input);
		EditText password = (EditText) custom.findViewById(R.id.password);
		try{
			// Check Group Name
//			if (groupName.getText().toString().length() == 0){
//				throw new Exception(getResources().getString(R.string.change_group_name_error_no_name));
//			}else if(groupName.getText().toString().length() < minGroupName){
//				throw new Exception(getResources().getString(R.string.change_group_name_error_name_to_short));
//			}else if(groupName.getText().toString().length() > maxGroupName){
//				throw new Exception(getResources().getString(R.string.change_group_name_error_name_to_long));
//			}
			
//			if(protocol.attemptCreateGroup(groupName.getText().toString())){
//				// Goes directly to the created group
//				CharSequence s= new String(": "+groupName.getText().toString());
//				goToMenu("group",s);
//				closeDialog();
//				expAdapter.addAuthority(AUTHORITY_ADMIN);
//				// make sure the user becomes admin in database.
//			}
			
//			Toast.makeText(getBaseContext(),R.string.change_group_name_success, Toast.LENGTH_LONG).show();
		}catch(Exception e){
			Toast.makeText(getBaseContext(),e.getMessage(), Toast.LENGTH_LONG).show();
		}
	}
		
}
