package ad.controller.list;

import java.util.ArrayList; 
import ad.view.activity.R;
import ad.model.expList.*;
import android.app.AlertDialog;
import android.content.Context;  
import android.view.LayoutInflater;  
import android.view.View;  
import android.view.ViewGroup;  
import android.widget.BaseExpandableListAdapter;  
import android.widget.TextView;  

/**
 * ExpListAdapter - extends BaseExpandableListAdapter.
 * This class acts as a bridge between the View and the Model. It is therefore the controller of the ExpandableListView.
 * This class constructs expandable list menus (ArrayList<ExpListGroup>).
 * Class content:
 * Instances: Context context, ArrayList<ExpListGroup> groups
 * Sections:
 * 			Initialize, methods: ExpListAdapter (constructor), setActiveMenu (private), buildExpList (private)
 * 			Expandable List Operation, methods: getActiveMenu, addItem
 * 			Implementation Methods, methods: getChild, getChildId, getChildView, getChildrenCount, getGroup, getGroupCount, getGroupId, hasStableIds, isChildSelectable
 * 
 * @author Stefan Arvidsson 
 * Copyright [2012] [Stefan Arvidsson]
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
 */
public class ExpListAdapter extends BaseExpandableListAdapter {  
	// Initiates the adapter's instances
    private Context context;  			
    private ArrayList<ExpListGroup> groups;      

//---------------------------------------------------------------------------------------------------------------------------------------------------
// Initialize Section - The methods to build the Expandable List Adapter. ---------------------------------------------------------------------------
//---------------------------------------------------------------------------------------------------------------------------------------------------
    /**
     * ExpListAdapter - The constructor of the expandable list adapter, sets the context and uses the setActiveMenu to construct the expandable list menu.
     * @param context - a Context, used to construct the expandable list menu.
     * @param menuName - a String with the expandable list menus name.
     */
    public ExpListAdapter(Context context, String menuName) {  
        this.context = context;  
        setActiveMenu(menuName);
        // setActiveMenu("jfsoj"); test example 
        //addItem((ExpListChild) this.getChild(1, 0),(ExpListGroup)groups.get(0)); // addItem test
	}  
    
    /**
     * setActiveMenu -Sets a new menu from an ArrayList as the active menu and notify the view of the change.
     * @param newActiveMenu - a String with the new menu's name, used to construct the new menu.
     */
    private void setActiveMenu(String newActiveMenu){   	
    	this.groups = buildExpList(newActiveMenu);
    	this.notifyDataSetChanged();
    }
    
    /**
     * buildExpList - Returns an ArrayList with all ExpandableList groups which make up a single menu.
     * 				This solution is due to android's lack of support for double string arrays in the XML files.			
     * @param menu - a String, the menu's name.
     * @return ArrayList<ExpListGroup> - the menu.
     */
    private ArrayList<ExpListGroup> buildExpList(String menu){
		 ArrayList<ExpListGroup> list = new ArrayList<ExpListGroup>();  
		 ArrayList<ExpListChild> list2 = new ArrayList<ExpListChild>(); 
	 	 ExpListGroup tempGroup;
	 	 ExpListChild tempChild;	 	 
		 try{
			
			 String[] inputGroups = null, inputChildren = null;
			 String tag = "";
			 // Add menus here 
			 if(menu.equals("start")){		
   			 	 tag = "start";
   				 inputGroups = context.getResources().getStringArray(R.array.start_groups);
   				 inputChildren = context.getResources().getStringArray(R.array.start_children);
			 }else if(menu.equals("help1")){
   				 tag = "help1";
   				 inputGroups = context.getResources().getStringArray(R.array.help_groups1);
   				 inputChildren = context.getResources().getStringArray(R.array.help_children1);
			 }else if(menu.equals("online")){
   				 tag = "online";
   				 inputGroups = context.getResources().getStringArray(R.array.online_groups);
   				 inputChildren = context.getResources().getStringArray(R.array.online_children);
			 }else if(menu.equals("help2")){
   				 tag = "help2";
   				 inputGroups = context.getResources().getStringArray(R.array.help_groups2);
   				 inputChildren = context.getResources().getStringArray(R.array.help_children2);
			 }
//			 else if(menu.equals("group")){
//   				 tag = "group";
//   				 inputGroups = context.getResources().getStringArray(R.array.group_groups);
//   				 inputChildren = context.getResources().getStringArray(R.array.group_children);
//			 }else if(menu.equals("help3")){
//   				 tag = "help3";
//   				 inputGroups = context.getResources().getStringArray(R.array.help_groups3);
//   				 inputChildren = context.getResources().getStringArray(R.array.help_children3);
//			 }
			 
			 // Wrong menu name doesn't exist
			 if(inputGroups.equals(null)){
				 throw new Exception(context.getResources().getString(R.string.error_empty_menu)); 
			 }		 
			 int j = 0;   	 
			 for(String group:inputGroups){
				 tempGroup = new ExpListGroup();	
				 tempGroup.setId(group); 
				 for(int i = j;i<inputChildren.length;i++){
					 if(inputChildren[i].length()==0){		
						 j = i+1;
						 break;
					 }else{
						 tempChild = new ExpListChild();
						 tempChild.setLabel(inputChildren[i]);
						 tempChild.setTag(tag);
						 list2.add(tempChild);
					 }
				 }
				 tempGroup.setContent(list2);
				 list2 = new ArrayList<ExpListChild>(); 
				 list.add(tempGroup);  
			 }
		 }catch(Exception e){
			 AlertDialog.Builder error = new AlertDialog.Builder(context);
			 error.setTitle(context.getResources().getString(R.string.error_title))
			 	  .setMessage(context.getResources().getString(R.string.error_on_creating_expandable_list)+"\n"+ e.getMessage())
			 	  .setNeutralButton("Ok", null)
			      .create().show();
		 }
   	 	return list;
    }
 

//---------------------------------------------------------------------------------------------------------------------------------------------------
// Expandable List Operations Section - Operations to find out more about the expandable list and do operations on it--------------------------------
//---------------------------------------------------------------------------------------------------------------------------------------------------    
    /**
     * getActiveMenu - Returns the active menu, used by the MainActivity by the onChildClick method in On Click/Select Section
     * 				   to initiate the listening on the expandable list menus. 
     * @return ArrayList<ExpListGroup> - A menu.
     */
    public ArrayList<ExpListGroup> getActiveMenu(){
    	return this.groups;
    }   
    /**   
     * addItem - Adds a child to a group.  
     * 			 Developers Note: is yet implemented in application but can be used for adding child elements in a specific group. Might want to add a method removeItem, unsure how.
     * @param item - an ExpListChild, a child in a group.
     * @param group - an ExpListGroup, a group in the expandable list.
     */
    public void addItem(ExpListChild item, ExpListGroup group) {  
    	if (!groups.contains(group)) {  
            groups.add(group);  
        }  
        int index = groups.indexOf(group);  
        ArrayList<ExpListChild> ch = groups.get(index).getContent();  
        ch.add(item);  
        groups.get(index).setContent(ch);  
        // If implemented in application it might be necessary to add this.notifyDataSetChanged();
    }  
    
//----------------------------------------------------------------------------------------------------------------------------------------------------
// Extend Methods Section - These methods are used by the class itself since it extends BaseExpandableListAdapter, getters methods. and one isChildSelectable method. 
//----------------------------------------------------------------------------------------------------------------------------------------------------    
    
    
    /**
     * getChild - Finds the child in the array and return it. OBS! It returns ExpListChild.
     * 			  Developers Note: Probably be used by the class itself.
     * @param groupPosition - an int, the group's location in the expandable list, used to get the child. 
     * @param childPosition - an int, the child's location in the group, used to get the child. 
     * @return Object -  is in fact an ExpListChild.
     */
    public Object getChild(int groupPosition, int childPosition) {  
        // TODO Auto-generated method stub  
        ArrayList<ExpListChild> chList = groups.get(groupPosition).getContent();  
        return chList.get(childPosition);  
    }  
    /**
     * getChildId - Returns the child's position in a specified group. Not used yet, unclear what to use it for.
     * 				Developers Note: Probably be used by the class itself.
     * @param groupPosition - an int, the group's location in the expandable list, not used.
     * @param childPosition - n int, the child's location in the group, as the child's id. 
     * @return long - the child's id.
     */
    public long getChildId(int groupPosition, int childPosition) {  
        // TODO Auto-generated method stub  
        return childPosition;  
    }  
    
    /**
     * getChildView - Returns the view for the child.
     * 				  Developers Note: Probably be used by the class itself.
     * @param groupPosition - an int, the group's location in the expandable list, used to get the child. 
     * @param childPosition - an int, the child's location in the group, used to get the child. 
     * @param isLastChild - a boolean, true if its the last child in the group.
     * @param view - a view, if there is not a view the method creates a view with an inflater layout.
     * @param parent - a ViewGroup, class itself?
     * @return View - the view of the child.
     */
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View view,  
            ViewGroup parent) {  
    	// TODO Auto-generated method stub  
    	ExpListChild child = (ExpListChild) getChild(groupPosition, childPosition);  
        if (view == null) {  
            LayoutInflater infalInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);  
            view = infalInflater.inflate(R.layout.explist_child, null);
        }  
        TextView tv = (TextView) view.findViewById(R.id.tvChild);  
        tv.setText(child.getLabel().toString());  
        tv.setTag(child.getLabel());          
        return view;  
	}  
    /**
     * getChildrenCount - Returns how many children there is in a group.
     * 					  Developers Note: Probably be used by the class itself.
     * @param groupPosition - an int, the group's location in the expandable list, used to get the groups. 
     * @return int - number of children in group.
     */
    public int getChildrenCount(int groupPosition) {  
        // TODO Auto-generated method stub  
        ArrayList<ExpListChild> chList = groups.get(groupPosition).getContent();  
        return chList.size();      
    }  
    /**
     * getGroup - Returns a group. OBS! It returns an ExpListGroup.
     * 			  Developers Note: Probably be used by the class itself.
     * @param groupPosition - an int, the group's location in the expandable list, used to get the group.
     * @return Object - is in fact an ExpListGroup.
     */
    public Object getGroup(int groupPosition) {  
        // TODO Auto-generated method stub  
        return groups.get(groupPosition);  
    }  
    /**
     * getGroupCount - Returns the number of groups used in the expandable list.
     * 				   Developers Note: Probably be used by the class itself.
     * @return int - the groups size.
     */
   public int getGroupCount() {  
        // TODO Auto-generated method stub  
        return groups.size();  
    }  
   /**
    * getGroupId - Returns the group's position.
    * 			   Developers Note: Probably be used by the class itself.
    * @param long - the groups position, used as the group's id.
    * @param long - the group's id.
    */
    public long getGroupId(int groupPosition) {  
        // TODO Auto-generated method stub  
        return groupPosition;  
    }  
    /**
     * getGroupView - Returns the view for the group.
     * 				  Developers Note: Probably be used by the class itself.
     * @param groupPosition - an int, the group's location in the expandable list, used to get the group.
     * @param isLastChild - a boolean, true if its the last child in the group.
     * @param view - a view, if there is not a view the method creates a view with an inflater layout.
     * @param parent - a ViewGroup, class itself?
     * @return View - the view of the group.
     */
    public View getGroupView(int groupPosition, boolean isLastChild, View view,  
            ViewGroup parent) {  
        // TODO Auto-generated method stub  
    	ExpListGroup group = (ExpListGroup) getGroup(groupPosition);  
        if (view == null) {  
            LayoutInflater inf = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);  
            view = inf.inflate(R.layout.explist_group, null);  
        }  
        TextView tv = (TextView) view.findViewById(R.id.tvGroup);  
        tv.setText(group.getLabel());  

        return view;  
    }  
    /**
     * hasStableIds - Returns a boolean which is true if the adapter has stable id, not sure what the meaning of this method is.
     * 				  Developers Note: Probably be used by the class itself.
     * @return boolean 
     */
    public boolean hasStableIds() {  
        // TODO Auto-generated method stub  
        return true;  
    }  
    /**
     * isChildSelectable - Returns a boolean with the value true. 
     * 					   Developers Note: Probably be used by the class itself. 
     * 					   There is no child that is a not select able child, so all children should be select able.
     * @param arg0 - an int, can be defined in the method by the developer as groupPosition.
     * @param arg1 - an int, can be defined in the method by the developer as childPosition. 
     * @return boolean - true if the child is select able, false if not.
     */
    public boolean isChildSelectable(int arg0, int arg1) {  
        // TODO Auto-generated method stub  
        return true;  
    }  
} 