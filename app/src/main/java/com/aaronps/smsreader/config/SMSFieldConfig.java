package com.aaronps.smsreader.config;

public class SMSFieldConfig
{
	public String name;
	public String internal_name;
	public boolean enabled;
	
	public SMSFieldConfig()
	{
		name = "";
		internal_name = "";
		enabled = false;
	}
	
	public SMSFieldConfig(String name, String internal_name, boolean enabled)
	{
		this.name = name;
		this.internal_name = internal_name;
		this.enabled = enabled;
	}
}
