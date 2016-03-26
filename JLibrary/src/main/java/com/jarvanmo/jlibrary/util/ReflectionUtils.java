package com.jarvanmo.jlibrary.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class ReflectionUtils {
	// PUBLIC METHODS
	// ////////////////////////////////////////////////////////////////////////////////////

	// Meta-data

	@SuppressWarnings("unchecked")
	public static <T> T getMetaData(Context context, String name) {
		try {
			final ApplicationInfo ai = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);

			if (ai.metaData != null) {
				return (T) ai.metaData.get(name);
			}
		} catch (Exception e) {
			Log.w("Couldn't find:" + name, name);
		}

		return null;
	}

	// ////////////////////////////////////////////////////////////////////////////////////
	// PRIVATE METHODS
	// ////////////////////////////////////////////////////////////////////////////////////

	public static boolean isSubclassOf(Class<?> type, Class<?> superClass) {
		if (type.getSuperclass() != null) {
			if (type.getSuperclass().equals(superClass)) {
				return true;
			}

			return isSubclassOf(type.getSuperclass(), superClass);
		}

		return false;
	}

	// 验证邮箱的正则表达式
	public static boolean isEmail(String email) {
		// String
		// str="^([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)*@([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)+[\\.][A-Za-z]{2,3}([\\.][A-Za-z]{2})?$";
		// String
		// str="^([a-z0-9A-Z]+[-|//.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?//.)+[a-zA-Z]{2,}$";
		String str = "[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}";
		Pattern p = Pattern.compile(str);
		Matcher m = p.matcher(email);
		return m.matches();
	}

	// 验证电话的正则表达式
	// public static boolean isPhoneNumberValid(String phoneNumber) {
	// // TODO Auto-generated method stub
	// boolean isValid = false;
	// String expression = "^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{5})$";
	// String expression2 = "^\\(?(\\d{3})\\)?[- ]?(\\d{4})[- ]?(\\d{4})$";
	// CharSequence inputstr = phoneNumber;
	// Pattern pattern = Pattern.compile(expression);
	// Matcher matcher = pattern.matcher(inputstr);
	//
	// Pattern pattern2 = Pattern.compile(expression2);
	// Matcher matcher2 = pattern.matcher(inputstr);
	// if(matcher.matches() || matcher2.matches()){
	// isValid = true;
	// }
	// return isValid;
	// }

	public static boolean isPhoneNumberValid(String phoneNumer) {
		Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9])|(17[0])|(14[0,7]))\\d{8}$");
		Matcher m = p.matcher(phoneNumer);
		return m.matches();
	}

	public static boolean isPassword(String password) {
	
			Pattern p = Pattern.compile("^[\\dA-Za-z(!@#$%&)]{6,16}$");
			Matcher m = p.matcher(password);
			return m.matches();
		
	}
}
