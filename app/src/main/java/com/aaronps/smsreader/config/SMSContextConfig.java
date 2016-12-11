package com.aaronps.smsreader.config;

public class SMSContextConfig
{
	public String name;
	public String path;
	public boolean enabled;
	
	public SMSContextConfig()
	{
		name = "";
		path = "";
		enabled = false;
	}
	
	public SMSContextConfig(String name, String path, boolean enabled)
	{
		this.name = name;
		this.path = path;
		this.enabled = enabled;
	}
}
