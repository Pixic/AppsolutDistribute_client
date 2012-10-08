package ad.view.activity;

import java.util.Stack;

import ad.model.expList.ExpListChild;
import ad.view.activity.R;
import ad.controller.expList.ExpListAdapter;

import android.app.Activity;  
import android.app.AlertDialog; 
import android.os.Bundle;  
import android.view.KeyEvent;   
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;  
import android.widget.ExpandableListView.OnChildClickListener;

import android.content.DialogInterface;

/**
 * MainActivity - extends Activity and implements OnChildClickListener.
 * This class creates the window(activity), sets its content(views) and is the listener of the content.
 * Since this class implements OnChildClickListener it must have the method onChildClick.
 * 
 * Class content:
 * Instances: ExpAdapter expAdapter, ExpandList expandList, Stack<ExpListAdapter> backExpMenu, Stack<CharSequence> backAppTitle
 * Sections:
 * 			Activity, methods: onCreate, onCreateOptionsMenu
 * 			On Click/Select, methods: onChildClick, onOptionsItemSelected, onKeyDown
 * 			Menu Methods, methods: startMenu, helpMenu, menuMenu, androidMenu
 * 			Special Operation, methods: goToHelpMenu, settingsDialog
 * 			Standard Operation, methods: onBack, logout, onExit
 *
 * @author Stefan Arvidsson
 * © 2012 Stefan Arvidsson
 */
public class MainActivity extends Activity implements OnChildClickListener { 
     //Initiates the activity's instances 
	 private ExpListAdapter expAdapter;  
     private ExpandableListView expandList;  
     // Note: If an operation is performed on one stack it should also be used on the other.
     private Stack<ExpListAdapter> backExpMenu = new Stack<ExpListAdapter>();	
     private Stack<CharSequence> backAppTitle = new Stack<CharSequence>(); 
//---------------------------------------------------------------------------------------------------------------------------------------------------
// Activity Section - methods that are overridden from the Activity superclass, examples: What happens when the activity is created, paused, e.g.. --
//---------------------------------------------------------------------------------------------------------------------------------------------------
     /**
      * onCreate - Called when the activity is first created. Defines what happens on application start up.
      * 		   Sets the content of the view and build the ExpandableListView menu.
      * @param savedInstanceState - Bundle with the state of the Activity.
      */
     @Override  
     public void onCreate(Bundle savedInstanceState) {  
    	 super.onCreate(savedInstanceState);
         setContentView(R.layout.main); 
         expandList = (ExpandableListView) findViewById(R.id.ExpList);     // Build the initial expandable list menu              
         expAdapter = new ExpListAdapter(MainActivity.this, "start"); 
     	 expandList.setAdapter(expAdapter); 
     	 expandList.setOnChildClickListener(this);
     }  
     
 	/**
 	 * onCreateOptionsMenu - Creates the menu layout.
 	 * @return Returns a boolean to indicate if the operation went well or not.
 	 */
     @Override
     public boolean onCreateOptionsMenu(Menu menu) {
     	getMenuInflater().inflate(R.menu.main, menu);
         return true;
     }     
     
     
//---------------------------------------------------------------------------------------------------------------------------------------------------    
//On Click/Select Section - identifies in which menu the click occurred and sends an identifier of the instance to the identified menu's method -----
//---------------------------------------------------------------------------------------------------------------------------------------------------     
  	/**
  	 * onChildClick - Defines action On child click in the ExpandableListView, it checks which tag the child has and sends the label to the menu's method.					  		  
  	 * @return Returns a boolean to indicate if the operation went well or not.
  	 */
  	public boolean onChildClick(ExpandableListView parent, View v,
  			int groupPosition, int childPosition, long id) {
  		// TODO Auto-generated method stub
  		ExpListChild c = expAdapter.getActiveMenu().get(groupPosition).getContent().get(childPosition);
  		if(c.getTag().equals("start")){	
  			startMenu(c.getLabel());
  			return true;
  		}else if(c.getTag().equals("help")){
  			helpMenu(c.getLabel());
  			return true;
  		}
  		return false;
  	}     
  	
    /**
     * onOptionsItemSelected - Defines the action on pressed menu button
     * @param item - The menu item
     * @return Returns a boolean to indicate if the operation went well or not.
     */
    @Override 
    public boolean onOptionsItemSelected(MenuItem item) { 
    	return menuMenu(item);
    } 
  	
	/**
	 * onKeyDown - Defines action on key click in the "android key menu".(Keys: Camera, volume, on/off, e.g.)
	 * @param keyCode - an int, describes which key was pressed.
	 * @param event - a KeyEvent, describes what event the key was exposed to.
	 * @return		Returns a boolean to indicate if the operation went well or not.
	 */
	@Override 
	public boolean onKeyDown(int keyCode, KeyEvent event)  { 
		return androidMenu(keyCode,event);
	} 
  	
//---------------------------------------------------------------------------------------------------------------------------------------------------
//Menu Methods Section - Identifies which item in the menu was clicked and calls a special operation method or standard operation method  -----------
//---------------------------------------------------------------------------------------------------------------------------------------------------        
  
 	/**
 	 * startMenu - is a menu method which use the label to identify what operation method to perform. 				   	   
 	 * @param label - String with the pressed child's label.
 	 */
 	public void startMenu(String label){
		if(label.equals("Login")){
			
			
		}else if(label.equals("Create Account")){
			
		}else if(label.equals("Select Server")){
			
		}else if(label.equals("About")){
			
			
		}else if(label.equals("Help")){
			goToMenu("help", getResources().getString( R.string.add_help_tile));
		}else if(label.equals("Exit")){			 
			onExit();
		}
	}
 	
 	/**
 	 * helpMenu - Is a ExpandableListViwe menu method which use the label to identify what operation method to perform. 				  	   
 	 * @param label - String with the pressed child's label.
 	 */
	public void helpMenu(String label){
		if(label.equals("Back")){		
			onBack();
		}
	}
	/**
	 * menuMenu - Is the menus menu method which uses the MenuItem to identify what operation method to perform. 
	 * @param item - the selected menu item.
	 * @return Returns a boolean to indicate if the method had a defined operation for the selected menu item.
	 */
	public boolean menuMenu(MenuItem item){
        if (item.getItemId() == R.id.menu_settings) { 	        // Settings where selected.
        	settingsDialog();
            return true; 
        } 
        return false;
	}
	/**
	 * androidMenu - Is the android menu which uses the keyCode and keyEvent to identify what operation method to perform.
	 * @param keyCodean - an int, describes which key was pressed.
	 * @param event - a KeyEvent, describes what event the key was exposed to.
	 * @return Returns a boolean to indicate if the method had a defined operation for the selected menu item.
	 */
	public boolean androidMenu(int keyCode, KeyEvent event){
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) { // BACK key was pressed, once?.
			onBack();   
	        return true; 
	    } 	 
	    return super.onKeyDown(keyCode, event); 
	}
	
//---------------------------------------------------------------------------------------------------------------------------------------------------     
//Special Operation Methods - Operations that is only used by one menu item -------------------------------------------------------------------------
//--------------------------------------------------------------------------------------------------------------------------------------------------- 	
	/**
	 * settingsDialog - Activates the settings dialog.
	 * 					Settings dialog is where the user can decide on the look of the application(set background image).
	 */
	public void settingsDialog(){
    	AlertDialog.Builder settings = new AlertDialog.Builder(MainActivity.this);
		 	settings.setTitle(this.getResources().getString(R.string.settings_title));
		 	settings.setIcon(CONTEXT_IGNORE_SECURITY);
		 	settings.setNegativeButton(this.getResources().getString(R.string.done), null);
		 	settings.create().show();
	}
	
	
//---------------------------------------------------------------------------------------------------------------------------------------------------
//Standard Operation Methods - Operations that can be used by several menu items from different menus. ----------------------------------------------
//--------------------------------------------------------------------------------------------------------------------------------------------------- 
	/**
	 * goToMenu - A general method that goes to (creates) the indicated expandable list menu.
	 * 			  
	 * @param menuName - the name of the menu
	 * @param menuTitle - the title of the menu
	 */
	public void goToMenu(String menuName, String menuTitle){
		backExpMenu.push(expAdapter);
		expAdapter = new ExpListAdapter(MainActivity.this, menuName);
		expandList.setAdapter(expAdapter);
		backAppTitle.push(getResources().getString(R.string.title_activity_main));
		setTitle(backAppTitle.peek() + menuTitle);
		
	}
	
	
	/**
	 * onBack - A standard operation for the user to return to a previous menu. 
	 * 			If that menu is in the start menu, the user probably want to exit the application which makes this method to launch
	 * 			the standard operation for exit.
	 * 			If the user is in the online menu, the user probably want to logout from the account which makes this method to launch
	 * 			the standard operation for logout. NOTE: logout() not written yet.
	 */
	public void onBack(){
		try{
			expAdapter = backExpMenu.pop();
			expandList.setAdapter(expAdapter);
			setTitle(backAppTitle.pop());
		}catch(Exception e){
			if( this.getTitle().toString().equals(getResources().getString(R.string.title_activity_main))){
				onExit();			
			}else{	// NOT YET IMPLEMENTED
				logout();
			}
			 
		}
	}
	/**
	 * logout - NOT YET IMPLEMENTED
	 */
	public void logout(){
		
	}
	
	
	/**
	 * onExit - A standard operation for the user to exit the application, try to stop the user with a Dialog!!
	 */
	public void onExit(){
		 AlertDialog.Builder exit = new AlertDialog.Builder(MainActivity.this);
		 exit.setTitle(this.getResources().getString(R.string.exit_title));
		 exit.setIcon(CONTEXT_IGNORE_SECURITY);
		 exit.setNeutralButton(this.getResources().getString(R.string.yes),new DialogInterface.OnClickListener() {
			// Add actions on click here 
			 public void onClick(DialogInterface dialog, int which) { 			    
				 	finish();
					System.exit(0);
			 }
		 } );
		 exit.setNegativeButton(this.getResources().getString(R.string.no), null);
		 exit.create().show();
	}
	

    
}




