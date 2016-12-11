package com.aaronps.smsreader;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class ViewMessage extends ListActivity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
	    super.onCreate(savedInstanceState);
	    
	    Bundle b = getIntent().getExtras();
	    
	    String msgId = b.getString("msgId");
	    
	    String[] colums = SMSConfig.getActiveFields();
	    
//	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	    
	    Cursor cur = getContentResolver().query( Uri.parse("content://sms"), colums, "_id = ?", new String[]{msgId}, null);
	    
	    List<String> sl = new ArrayList<String>();
	    
	    if ( cur.moveToNext() )
	    {

	    	for ( String c: cur.getColumnNames() )
	    	{
	    		sl.add(c + ":" + cur.getString(cur.getColumnIndex(c)));
	    	}
	    	
	    	setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, sl));
	    }
	    
	    
	}

}
