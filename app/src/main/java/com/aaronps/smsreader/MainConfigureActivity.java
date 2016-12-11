package com.aaronps.smsreader;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainConfigureActivity extends ListActivity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	
	    String[] options = getResources().getStringArray(R.array.main_configure_options);
        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, options));
        
        ListView lv = getListView();
        lv.setOnItemClickListener(new OnItemClickListener(){

			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent = null;
				switch ( position )
				{
					case 0:
						intent = new Intent(getApplicationContext(), ConfigListSMSContexts.class);
						break;
					case 1:
						intent = new Intent(getApplicationContext(), ConfigListSMSFields.class);
						break;
				}
				
				if ( intent != null )
				{
					startActivity(intent);
				}
			}
        	
        });
	}

}
