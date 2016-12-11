package com.aaronps.smsreader;

import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.aaronps.smsreader.config.SMSFieldConfig;

public class ConfigListSMSFields extends ListActivity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	
	    setListAdapter(new SMSFieldListAdapter(this));
	}

	private static class SMSFieldListAdapter extends ArrayAdapter<SMSFieldConfig>
	{
		private LayoutInflater inflater;
		private CompoundButton.OnCheckedChangeListener checkboxChangeListener;
		
		public SMSFieldListAdapter(Context c)
		{
			super(c, android.R.layout.simple_list_item_1, SMSConfig.sms_fields);
			inflater = (LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			checkboxChangeListener = new CompoundButton.OnCheckedChangeListener()
			{
				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
				{
					SMSFieldConfig c = (SMSFieldConfig)buttonView.getTag();
					c.enabled = isChecked;
				}
			};
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent)
		{
			ViewHolder holder;
			if ( convertView == null )
			{
				convertView = inflater.inflate(R.layout.field_config_row, parent, false);
				holder = new ViewHolder();
				holder.textView = (TextView)convertView.findViewById(R.id.text1);
				holder.checkBox = (CheckBox)convertView.findViewById(R.id.check1);
				holder.checkBox.setOnCheckedChangeListener(checkboxChangeListener);
				
				convertView.setTag(holder);
			}
			else
			{
				holder = (ViewHolder)convertView.getTag();
			}
			
			SMSFieldConfig c = SMSConfig.sms_fields.get(position);
			holder.textView.setText(c.name + ": " + c.internal_name);
			holder.checkBox.setTag(c);
			holder.checkBox.setChecked(c.enabled);
			
			return convertView;
		}
		
		private class ViewHolder
		{
			public TextView textView;
			public CheckBox checkBox;
		}
	}
	
}
