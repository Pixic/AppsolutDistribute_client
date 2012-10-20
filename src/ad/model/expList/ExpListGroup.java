package ad.model.expList;

import java.util.ArrayList;  


/**
 * ExpListGroup
 * This class make up the content of the expandable list, by forming the groups.
 * The children are placed in a group with the ExpListAdapter.
 * A group can be empty, but that would be useless unless elements are added later on with addItem() in ExpListAdapter.
 * 
 * Developers Note: There are no sections in this model, because it only consists of getters and setters methods.
 * 
 * Class content:
 * Instances: String label, ArrayList<ExpListChild> content	
 * Methods: getLabel, setLabel, getContent, setContent
 * 
 * @author Stefan Arvidsson
 * 
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
public class ExpListGroup {  
	// Initiates the group's instances
     private String label;  					//group name
     private ArrayList<ExpListChild> content;   //items
     /**
      * getLabel - Returns the label of the group.  
      * @return	String - the groups label.
      */
     public String getLabel() {  
         return label;  
     }  
     /**
      * setLabel - Sets the incoming String as the group's new label.
      * @param label - A String with the groups new label.
      */
     public void setLabel(String label) {  
         this.label = label;  
     }  
     /**
      * getContent - Returns an ArrayList with the content.
      * @return	content - ArrayList<ExpListChild> , that makes up the content of the group.
      */
     public ArrayList<ExpListChild> getContent() {  
         return content;  
     }  
     /**
      * setContent - Sets the incoming ArrayList<ExpListChild> as the groups's new content.
      * @param content - ArrayList<ExpListChild>, the groups's new content.
      */
     public void setContent(ArrayList<ExpListChild> content) {  
		this.content = content;  
     }          

} 
