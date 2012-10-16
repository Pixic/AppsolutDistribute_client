package ad.view.test;

import junit.framework.TestCase;
import ad.controller.list.ExpListAdapter;
import ad.view.activity.MainActivity;
/**
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
 * 
 */
public class MainActivityTestCase extends TestCase {
	private ExpListAdapter expAdapter; 
	private MainActivity main = new MainActivity();
	public MainActivityTestCase(){
		expAdapter = new ExpListAdapter(main, "tjo");

		assertEquals(expAdapter.getActiveMenu().size(), 0);
	}
	
	
}
