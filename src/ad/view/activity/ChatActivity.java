package ad.view.activity;

import java.util.ArrayList;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import ad.view.activity.R;

import ad.controller.protocol.*;
/**
 * ChatActivity - Extends ListActivity and implements OnClickListener. 
 * 				  There are two nested classes in this class: 
 * 				  ListViewAdapter and ListContent (ListContent is 
 * 				  nestled in ListViewAdapter)
 * 				  Both are static.
 * 				  
 * 				  
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
public class ChatActivity extends ListActivity implements OnClickListener {
	EditText textContent;
	Button submit;
	ListView mainListview;

	private static final ArrayList<String> ListviewContent = new ArrayList<String>();
	Protocol protocol;
	/**
	 * onCreate - Called when the activity is first created. Defines what
	 * 			   happens on application start up. Sets the content of the view 
	 * @param savedInstanceState  - Bundle with the state of the Activity.       
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.chat);
		
		Intent i = getIntent();
		protocol = (Protocol) i.getSerializableExtra("Protocol");
		
		ListviewContent.add("Androidpeople.com");
		ListviewContent.add("Android FROYO");
		
		textContent = (EditText) findViewById(R.id.EditText01);
		submit = (Button) findViewById(R.id.Button01);
		submit.setOnClickListener(this);
		setListAdapter(new ListViewAdapter(this));
	}



	/**
	 * onClick - Determines action when a component is clicked, in this case
	 * 			 it is the send button.
	 * 
	 * @param v - Is the view of the component that is clicked.
	 */
	public void onClick(View v) {
		if (v == submit) {
			ListviewContent.add(textContent.getText().toString());
			protocol.sendMessage(textContent.getText().toString());
			
			setListAdapter(new ListViewAdapter(this));
		}
	}
	
	/**
	 * ListViewAdapter - Extends BaseAdapter
	 * 
	 * @author Stefan
	 *
	 */
	private static class ListViewAdapter extends BaseAdapter {
		private LayoutInflater mInflater;
		
		/**
		 * ListViewAdapter - Constructor needs the activities context.
		 * 
		 * @param context - This 
		 */
		public ListViewAdapter(Context context) {

			mInflater = LayoutInflater.from(context);

		}
		/**
		 * getCount - Returns the number of elements in the ListView.
		 * 
		 * @return int - The size of the list which contains the ListViews content.
		 */
		public int getCount() {
			return ListviewContent.size();
		}
		/**
		 * getItem - Gets the item.(position ?)
		 * 
		 * @param position - A int, the items position.
		 * 
		 * @return Object - Is an int with the item position in the ListView.
		 */
		public Object getItem(int position) {
			return position;
		}
		/**
		 * getItemId - Gets the item id.(position ?)
		 * 
		 * @param position - A int, the items position.
		 * 
		 * @return long - The items id is its position.
		 */
		public long getItemId(int position) {
			return position;
		}
		/**
		 * getView - gets the list view.
		 * 
		 * @param position - A int, the items position.
		 * @param convertView - The View of the list
		 * @param parent - The ViewGroup parent (the linear layout ?)
		 */
		public View getView(int position, View convertView, ViewGroup parent) {

			ListContent holder;

			if (convertView == null) {
				convertView = mInflater.inflate(R.layout.listviewinflate,
						null);

				holder = new ListContent();
				holder.text = (TextView) convertView.findViewById(R.id.TextView01);
				holder.text.setCompoundDrawables(convertView.getResources()
						.getDrawable(R.drawable.ic_launcher), null, null, null);

				convertView.setTag(holder);
			} else {

				holder = (ListContent) convertView.getTag();
			}

			holder.text.setText(ListviewContent.get(ListviewContent.size()-1-position)); // Change here ListviewContent.get(position)
			return convertView;
		}
		/**
		 * ListContent - Is a static class which only holds one instance, the text for the chat.			 
		 * 
		 * @author Stefan Arvidsson
		 *
		 */
		static class ListContent {
			TextView text;
		}
	}

}
