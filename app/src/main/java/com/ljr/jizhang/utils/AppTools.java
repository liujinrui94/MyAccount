package com.ljr.jizhang.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.telephony.TelephonyManager;
import android.util.Log;


public class AppTools
{
	
	public static String getIMEI(Context context)
	{
		return ((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
	}

	
	public static String getIMSI(Context context)
	{
		return ((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE)).getSubscriberId();
	}

	
	public static String getAppVersionName(Context context)
	{
		String versionName = "";
		try
		{
			PackageManager pm = context.getPackageManager();
			PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
			versionName = pi.versionName;
			if (versionName == null || versionName.length() <= 0)
			{
				return "";
			}
		}
		catch (Exception e)
		{
			Log.e("VersionInfo", "Exception", e);
		}
		return versionName;
	}
}
