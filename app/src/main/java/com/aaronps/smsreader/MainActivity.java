package com.aaronps.smsreader;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String[] options = getResources().getStringArray(R.array.main_options);
        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, options));

        ListView lv = getListView();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = null;
                switch ( position )
                {
                    case 0:
                        intent = new Intent(getApplicationContext(), MainConfigureActivity.class);
                        break;
                    case 1:
                    case 2:
                        break;
                    case 3:
                        intent = new Intent(getApplicationContext(), ListSMSBoxes.class);
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
