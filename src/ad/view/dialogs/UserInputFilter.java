package ad.view.dialogs;

import android.text.InputFilter;
import android.text.Spanned;
/**
 * UserInputFilter - An interface, used by MainActivity to stop the user from 
 * 					 being able to insert special characters(one exception is @)
 * 					 and make sure that the input from the user is shorter than
 * 					 a specified length.
 * 
 * Interface content:
 * 
 * 
 * 
 * @author Stefan Arvidsson
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
public interface UserInputFilter {
	/**
	 * CharacterFilter - Is a static class, implements InputFilter and has one
	 * 					 implemented method, filter(). This method is specified
	 * 					 to make the user unable to input characters other than
	 * 					 letters, numbers and at(@).
	 * 
	 * @author Stefan Arvidsson
	 *
	 */
	public static class CharacterFilter implements InputFilter{
		/**
		 * filter - An implemented method from InputFilter, filters which characters
		 * 			is allowed in the EditText and therefore stops the user from 
		 * 			inserting certain characters.
		 * 
		 * @param source - The EditText's CharSequence to be filtered
		 * @param start - An int, the start of the CharSequence.
		 * @param end - An int, the end of the CharSequence.
		 * @param dest - An instance of Spanned is unused, maybe used by the class itself.
		 * @param dstart - An unused int, maybe used by the class itself.
		 * @param dend - An unused int, maybe used by the class itself.
		 * 
		 * @return CharSequence - The filtered input
		 */
		public CharSequence filter(CharSequence source, int start, int end, 
        		Spanned dest, int dstart, int dend) { 
                for (int i = start; i < end; i++) { 
                        if (!(Character.isLetterOrDigit(source.charAt(i))|| (source.charAt(i) == 64))) { 
                        	return ""; 
                        } 
                } 
                return null; 
        } 
	}
	
	/**
	 * LengthFilter - An implemented method from InputFilter, filters the length
	 * 				  of the user input. This makes it impossible for the user 
	 * 				  to insert more characters than the specified number. 
	 * 
	 * @author Stefan Arvidsson
	 *
	 */
	public static class LengthFilter implements InputFilter {
        private int maxLength;	
        /**
         * The Constructor 
         * @param specifiedMaxLength - The max length that the user should be
         * 							   able to insert in the filtered EditText.
         */
		public LengthFilter(int specifiedMaxLength) {
            maxLength = specifiedMaxLength;
        }
		/**
		 * filter - An implemented method from InputFilter, filters the length of
		 * 			the users input in an EditText. It uses the specified max length
		 * 			from the constructor.
		 * 
		 * @param source - The EditText's CharSequence to be filtered
		 * @param start - An int, the start of the CharSequence.
		 * @param end - An int, the end of the CharSequence.
		 * @param dest - An instance of Spanned, used to determine how many characters the
		 * 				 user have written.
		 * @param dstart - An int, used to determine how many character the user have written.				   
		 * @param dend - An int,used to determine how many character the user have written.				   
		 * 
		 * @return CharSequence - The filtered input
		 */
        public CharSequence filter(CharSequence source, int start, int end,
                                   Spanned dest, int dstart, int dend) {
            int keep = maxLength - (dest.length() - (dend - dstart));

            if (keep <= 0) {
                return ""; // The CharSequence is to long do not add another character.
            } else if (keep >= end - start) {
                return null; // keep the original CharSequence
            } else {
                return source.subSequence(start, start + keep); // Its OK the user can insert more characters.
            }
        }
    }	
	
	
}
