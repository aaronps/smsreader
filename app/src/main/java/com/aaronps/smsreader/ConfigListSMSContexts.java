package com.aaronps.smsreader;

import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.aaronps.smsreader.config.SMSContextConfig;

public class ConfigListSMSContexts extends ListActivity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    
	    setListAdapter(new SMSContextListAdapter(this));
	}
	
	private static class SMSContextListAdapter extends ArrayAdapter<SMSContextConfig>
	{
		public SMSContextListAdapter(Context c)
		{
			super(c, android.R.layout.simple_list_item_1, SMSConfig.sms_contexts);
		}

		@Override
		public int getCount() {
			return SMSConfig.sms_contexts.size() + 1;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			TextView tv = (TextView)convertView;
			if ( tv == null )
			{
				tv = new TextView(getContext());
			}
			
			if ( position < SMSConfig.sms_contexts.size() )
			{
				SMSContextConfig c = SMSConfig.sms_contexts.get(position);
				tv.setText(c.name + ": " + c.path );
			}
			else
			{
				tv.setText("Add new...");
			}
			
			return tv;
		}
	}

}
