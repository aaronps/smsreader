package com.aaronps.smsreader;

import android.app.Application;

public class SMSApplication extends Application
{
	@Override
	public void onCreate() {
		super.onCreate();
		SMSConfig.initialize(getApplicationContext());
	}

	@Override
	public void onTerminate() {
		SMSConfig.deinitialize(getApplicationContext());
		super.onTerminate();
	}

}
