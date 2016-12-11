package com.aaronps.smsreader;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import com.aaronps.smsreader.config.SMSContextConfig;
import com.aaronps.smsreader.config.SMSFieldConfig;

public final class SMSConfig
{
	static List<SMSContextConfig> sms_contexts = null;
	static List<SMSFieldConfig> sms_fields = null ;
	

	public static void initialize(Context c)
	{
		sms_contexts = new ArrayList<SMSContextConfig>();
		
		sms_contexts.add(new SMSContextConfig("inbox", "/sms/inbox", true));
		sms_contexts.add(new SMSContextConfig("sent", "/sms/sent", true));
		
		sms_fields = new ArrayList<SMSFieldConfig>();
		
		sms_fields.add(new SMSFieldConfig("id", "_id", false));
		sms_fields.add(new SMSFieldConfig("date", "date", true));
		sms_fields.add(new SMSFieldConfig("address", "address", true));
		sms_fields.add(new SMSFieldConfig("body", "body", true));

		Cursor cur = c.getContentResolver().query( Uri.parse("content://sms"), null, null, null, null);
	    
	    for ( String name: cur.getColumnNames())
	    {
	    	if ( ! hasInternalField(name) )
	    	{
	    		sms_fields.add(new SMSFieldConfig(name, name, false));
	    	}
	    }
	    cur.close();
	}
	
	public static void deinitialize(Context c)
	{
		
	}
	
	private static boolean hasInternalField(String internal_name)
	{
		for ( SMSFieldConfig c: sms_fields)
		{
			if ( c.internal_name.equals(internal_name) )
			{
				return true;
			}
		}
		return false;
	}
	
	public static String[] getActiveFields()
	{
		ArrayList<String> r = new ArrayList<String>();
		for ( SMSFieldConfig c: sms_fields )
		{
			if ( c.enabled == true )
			{
				r.add(c.internal_name);
			}
		}

		return (String[]) r.toArray(new String[0]);
	}
	
}
