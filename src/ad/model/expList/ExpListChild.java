package ad.model.expList;

/**
 * ExpListChild class
 * This class form the children in the group, which forms the content in the expandable list.
 * Class content:
 * Instances: String label, String tag	
 * Methods: getLabel, setLabel, getTag, setTag
 *
 *Developers Note: There are no sections in this model, because it only consists of getters and setters methods.
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
public class ExpListChild {
	// Initiates the child's instances
    private String label;  	//name 
    private String tag;  //tag 

    /**
     * getLabel - Returns the label of the child.
     * @return String - the child's label.
     */
    public String getLabel() {  
        return label;  
    }  
    /**
     * setLabelContent - Sets the incoming String as the child's new label.
     * @param content - A String with the child's new label
     */
    public void setLabel(String label) {  
        this.label = label;  
    }  
    /**
     * getTag - Returns the child's tag.
     * @return String - the child's tag
     */
   public String getTag() {  
       return tag;  
       
   }  
   /**
    * setTag - Sets the incoming String as the child's new tag.
    * @param tag - A String with the child's new tag.
    */
    public void setTag(String tag) {  
        this.tag = tag;  
   }  
      
	
}
