package com.aaronps.smsreader;

import android.app.ListActivity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class ViewColumnsActivity extends ListActivity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    
	    Cursor cur = getContentResolver().query( Uri.parse("content://sms"), null, null, null, null);
	    String[] cols = cur.getColumnNames();
	    cur.close();
	    
	    setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, cols));
	    setContentView(R.layout.viewcolumns);
	}

}
