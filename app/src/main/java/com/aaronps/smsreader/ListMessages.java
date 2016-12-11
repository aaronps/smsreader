package com.aaronps.smsreader;

import java.sql.Date;
import java.text.SimpleDateFormat;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class ListMessages extends ListActivity
{
	Cursor cur;
	SimpleDateFormat sdf;
	SimpleCursorAdapter adapter;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
	    super.onCreate(savedInstanceState);
	    
	    Bundle b = getIntent().getExtras();
	    String ctx = "content:/" + (SMSConfig.sms_contexts.get(b.getInt("contextIndex")).path);
	    
	    String[] cols = new String[] { "_id", "date", "address" };
	    String[] displayFields = new String[] {"date", "address" };
	    int[] displayViews = new int[] {android.R.id.text1, android.R.id.text2};
	    
	    cur = getContentResolver().query( Uri.parse(ctx), cols, null, null, null);
	    
	    sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	    
	    adapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_2, cur, displayFields, displayViews);
	    adapter.setViewBinder(new SimpleCursorAdapter.ViewBinder() {
			public boolean setViewValue(View view, Cursor cursor, int columnIndex) {
				if ( columnIndex == 1 )
				{
					TextView tv = (TextView)view;
					tv.setText(sdf.format(new Date(cursor.getLong(columnIndex))));
					return true;
				}
				return false;
			}
		});
	    
	    setListAdapter(adapter);
	    
	    ListView lv = getListView();
	    lv.setEnabled(true);
	    lv.setOnItemClickListener(new OnItemClickListener(){
			public void onItemClick(AdapterView<?> arg0, View arg1, int position, long id) {
	    		Toast.makeText(getApplicationContext(), "Selected " + position, Toast.LENGTH_SHORT).show();
    			Cursor o = (Cursor)adapter.getItem(position);
    			
    			Bundle b = new Bundle();
    			b.putString("msgId", o.getString(0));
    			
				Intent i = new Intent(getApplicationContext(), ViewMessage.class);
				i.putExtras(b);
				startActivity(i);
			}
	    });
	}
	
	
	
	@Override
	protected void onDestroy()
	{
		cur.close();
		super.onDestroy();
	}

}
