package com.aaronps.smsreader;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.aaronps.smsreader.config.SMSContextConfig;

public class ListSMSBoxes extends ListActivity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	
	    setListAdapter(new SMSContextListAdapter(this));
	    getListView().setOnItemClickListener(new OnItemClickListener(){

			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent = new Intent(getApplicationContext(), ListMessages.class);
				Bundle b = new Bundle();
    			b.putInt("contextIndex", position);
    			intent.putExtras(b);
    			startActivity(intent);
			}
	    	
	    });
	}
	
	private static class SMSContextListAdapter extends ArrayAdapter<SMSContextConfig>
	{
		private LayoutInflater inflater;
		public SMSContextListAdapter(Context c)
		{
			super(c, android.R.layout.simple_list_item_1, SMSConfig.sms_contexts);
			inflater = LayoutInflater.from(c);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if ( convertView == null )
			{
				convertView = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
			}

			TextView tv = (TextView) convertView.findViewById(android.R.id.text1);
			
			SMSContextConfig c = SMSConfig.sms_contexts.get(position);
			tv.setText(c.name);
			
			return convertView;
		}
	}

}
