package ad.view.activity;

import java.util.ArrayList;
import java.util.Stack;

import ad.model.expList.ExpListChild;
import ad.view.activity.R;
import ad.view.dialogs.CustomDialog;
import ad.controller.list.ExpListAdapter;
import ad.controller.protocol.Protocol;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
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

/**
 * MainActivity - extends Activity and implements OnChildClickListener. This
 * class creates the window(activity), sets its content(views) and is the
 * listener of the content. Since this class implements OnChildClickListener it
 * must have the method onChildClick.
 * 
 * Class content: Instances: ExpAdapter expAdapter, ExpandList expandList,
 * Stack<ExpListAdapter> backExpMenu, Stack<CharSequence> backAppTitle Sections:
 * Activity, methods: onCreate, onCreateOptionsMenu On Click/Select, methods:
 * onChildClick, onOptionsItemSelected, onKeyDown Menu Methods, methods:
 * startMenu, helpMenu, menuMenu, androidMenu Special Operation, methods:
 * goToHelpMenu, settingsDialog Standard Operation, methods: onBack, logout,
 * onExit Dialog Section, methods: createNewDialog, createDialogButton, listener
 * (instance), onClick, closeDialog, createAccount
 * 
 * @author Stefan Arvidsson Copyright [2012] [Stefan Arvidsson]
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
public class MainActivity extends Activity implements OnChildClickListener {
	// Initiates the activity's instances
	private ExpListAdapter expAdapter;
	private ExpandableListView expandList;
	// Note: If an operation is performed on one stack it should also be used on
	// the other.
	private Stack<ExpListAdapter> backExpMenu = new Stack<ExpListAdapter>();
	private Stack<CharSequence> backAppTitle = new Stack<CharSequence>();

	private CustomDialog custom;

	private Protocol protocol = new Protocol();

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
	 * 			   identify what operation method to perform in the start menu.
	 * 
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
			} else if (label.equals(getResources().getStringArray(R.array.start_children)[5])) { 	
				goToMenu("help1", getResources().getString(R.string.add_help_tile));
			} else if (label.equals(getResources().getStringArray(R.array.start_children)[6])) { // Exit
				onExit();
			}
	}

	/**
	 * helpMenuOne - Is an ExpandableListViwe menu method which use the label to
	 * 			  	 identify what operation method to perform in help menu one.
	 * 
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
				custom.cancel(); // Ugly solution I know
				onBack();
			}	
	
	}

	/**
	 * onlineMenu - Is an ExpandableListView menu method which use the label to
	 * 				identify what operation method to perform in the online menu.
	 * @param lable - String with the pressed child's label.
	 *           
	 */
	public void onlineMenu(String label) {

			if (label.equals(getResources().getStringArray(R.array.online_children)[0])) { // My Groups
				// dialog
			} else if (label.equals(getResources().getStringArray(R.array.online_children)[1])) { // Create Group
				// dialog
			} else if (label.equals(getResources().getStringArray(R.array.online_children)[2])) { // Join Group
				// dialog
			} else if (label.equals(getResources().getStringArray(R.array.online_children)[3])) { // Answer Group Invites
				// dialog
/*group 2*/	} else if (label.equals(getResources().getStringArray(R.array.online_children)[5])) { // Change Username
				// dialog
			} else if (label.equals(getResources().getStringArray(R.array.online_children)[6])) { // Change Password
				// dialog
			} else if (label.equals(getResources().getStringArray(R.array.online_children)[7])) { // End Account
				// dialog -> start menu
/*group 3*/	} else if (label.equals(getResources().getStringArray(R.array.online_children)[9])) { // Logout
				// dialog -> start menu
				onBack();
			} else if (label.equals(getResources().getStringArray(R.array.online_children)[10])) { // About
				createNewDialog(label, R.layout.info);
			} else if (label.equals(getResources().getStringArray(R.array.online_children)[11])) { // Help
				goToMenu("help2", getResources().getString(R.string.add_help_tile)); // Help
			} 
	}
	/**
	 * helpMenuTwo - Is an ExpandableListViwe menu method which use the label to
	 * 			  	 identify what operation method to perform in help menu two.
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
/*group 2*/	}else if(label.equals(getResources().getStringArray(R.array.help_children2)[5])){ // 4.1. Change Username
				text.setText(getResources().getText(R.string.help4_1));
			}else if(label.equals(getResources().getStringArray(R.array.help_children2)[6])){ // 4.2. Change Password
				text.setText(getResources().getText(R.string.help4_2));
			}else if(label.equals(getResources().getStringArray(R.array.help_children2)[7])){ // 4.3. End Account
				text.setText(getResources().getText(R.string.help4_3));
/*group 3*/	}else if(label.equals(getResources().getStringArray(R.array.help_children2)[9])){ // About
				text.setText(getResources().getText(R.string.about_text));		
			}else if(label.equals(getResources().getStringArray(R.array.help_children2)[10])){ // Back
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
		// BACK key was pressed, once?.
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
		settings.setTitle(this.getResources().getString(R.string.settings_title));
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
	 * @param menuTitle - the title of the menu       
	 */
	public void goToMenu(String menuName, String menuTitle) {
		backExpMenu.push(expAdapter);
		expAdapter = new ExpListAdapter(MainActivity.this, menuName);
		expandList.setAdapter(expAdapter);
		backAppTitle.push(getResources().getString(R.string.title_activity_main));
		setTitle(backAppTitle.peek() + menuTitle);

	}

	/**
	 * onBack - A standard operation for the user to return to a previous menu.
	 * 			If that menu is in the start menu, the user probably want to exit the
	 * 			application which makes this method to launch the standard operation for
	 * 			exit. If the user is in the online menu, the user probably want to logout
	 * 			from the account which makes this method to launch the standard operation
	 * 			for logout. NOTE: logout() not written yet.
	 */
	public void onBack() {
		try {
			if(getTitle().equals(getResources().getString(R.string.title_activity_main) + this.getResources().getString(R.string.add_online_tile))){
				logout();
			}
			expAdapter = backExpMenu.pop();
			expandList.setAdapter(expAdapter);
			setTitle(backAppTitle.pop());
		} catch (Exception e) {
			if (this.getTitle().toString().equals(getResources().getString(R.string.title_activity_main))) {
				onExit();
			} 
		}
	}

	/**
	 * logout - NOT YET IMPLEMENTED
	 */
	public void logout() {
		// Send logout to server
		Toast.makeText(getBaseContext(), "logout", Toast.LENGTH_LONG).show();
	}

	/**
	 * onExit - A standard operation for the user to exit the application, try
	 * 			to stop the user with a Dialog!!
	 */
	public void onExit() {
		AlertDialog.Builder exit = new AlertDialog.Builder(MainActivity.this);
		exit.setTitle(this.getResources().getString(R.string.exit_title))
				.setIcon(CONTEXT_IGNORE_SECURITY)
				.setNeutralButton(this.getResources().getString(R.string.yes),
						new DialogInterface.OnClickListener() {
							// Add actions on click here
							public void onClick(DialogInterface dialog,
									int which) {
								finish();
								System.exit(0);
							}
						})
				.setNegativeButton(this.getResources().getString(R.string.no),
						null).create().show();
	}

	// ---------------------------------------------------------------------------------------------------------------------------------------------------
	// Dialog Section - Builds the dialog, when an expandable list item is selected, sets its content, identifies selected dialog button and its action -
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
		if (layout == R.layout.info) {
		} else if (layout == R.layout.create_user_account) {
			createDialogButton(R.id.create_account_button);
		} else if (layout == R.layout.login) {
			createDialogButton(R.id.login_button);
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
				if (createAccount()) {
					goToMenu("online",getResources().getString(R.string.add_online_tile));
					closeDialog();
				}
			}else if(v.equals(custom.findViewById(R.id.login_button))){
				if(login()){
					goToMenu("online",getResources().getString(R.string.add_online_tile));
					closeDialog();
				}
			}
		}
	};

	// ---------------------------------------------------------------------------------------------------------------------------------------------------
	// Dialog Standard Methods Section - methods used by several dialogs and other dialog methods. -------------------------
	// ---------------------------------------------------------------------------------------------------------------------------------------------------

	/**
	 * closeDialog - A standard operation to close the dialog
	 */
	public void closeDialog() {
		custom.cancel();
	}

	// ---------------------------------------------------------------------------------------------------------------------------------------------------
	// Dialog Special Methods Section - methods used by several dialogs and other dialog methods. -------------------------
	// ---------------------------------------------------------------------------------------------------------------------------------------------------

	/**
	 * createAccount - Used by create_account_button. Reads in the user input
	 * from the dialog and tries to create an account on the server side.
	 * Returns false and an AlertDialog if it failed to create the account.
	 * 
	 * @return boolean - returns true if the account was created.
	 */
	public boolean createAccount() {
		EditText username = (EditText) custom.findViewById(R.id.user_text_input);
		EditText password1 = (EditText) custom.findViewById(R.id.password1);
		EditText password2 = (EditText) custom.findViewById(R.id.password2);

		EditText firstName = (EditText) custom.findViewById(R.id.firstname_text_input);
		EditText surname = (EditText) custom.findViewById(R.id.surname_text_input);
		EditText email = (EditText) custom.findViewById(R.id.email_text_input);
		
		try {
			if (username.getText().toString().length() == 0){
				throw new Exception(getResources().getString(R.string.account_error_no_user_name));
			}else if (username.getText().toString().length() < 4){
				throw new Exception(getResources().getString(R.string.account_error_short_user_name));
			} else if(username.getText().toString().length() > 20){
				throw new Exception(getResources().getString(R.string.account_error_long_user_name));
			}		
			if (password1.getText().toString().length() < 6){
				throw new Exception(getResources().getString(R.string.account_error_short_password));
			}else if(password1.getText().toString().length() > 20){
				throw new Exception(getResources().getString(R.string.account_error_long_password));
			}			
			if (!((password1.getText().toString()).equals(password2.getText().toString())))
				throw new Exception(getResources().getString(R.string.account_error_password_match));
		
			if(firstName.getText().toString().length() > 20 )
				throw new Exception(getResources().getString(R.string.account_error_first_name));
			if(surname.getText().toString().length() > 20 )
				throw new Exception(getResources().getString(R.string.account_error_surname));
			if(email.getText().toString().length() > 40 )
				throw new Exception(getResources().getString(R.string.account_error_email));
			
			 if(!(protocol.attemptCreationOfAccount(username.getText().toString(),password1.getText().toString(), 
					 firstName.getText().toString(),surname.getText().toString()))){
				 throw new Exception("Failed to create account");
			 }

		} catch (Exception e) {
			Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_LONG).show();
			return false;
		}
		return true;
	}

	public boolean login() {
		EditText username = (EditText) custom.findViewById(R.id.user_text_input);
		EditText password = (EditText) custom.findViewById(R.id.password);
		
		try{
			
		}catch(Exception e){
			return false;
		}
		
		
		return true;
	}

}
