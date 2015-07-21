package hnks.kitsoft.utils;

public class Link {
	
	public static String HTTP ="http://";
	public static String NAME_DIRECT ="/kshn/";
	static String NAME_APK="hnks.apk";
	static String NAME_SERVLET ="data2mobile";
	
	public static String get_link_download (String IP_CONECT){
		String mLink ="httP://google.com.vn";
		try {
			mLink=IP_CONECT.replace(NAME_SERVLET, NAME_APK);
		} catch (Exception e) {
		}
		return mLink;
		
	}
	public static String get_IP (String IP_CONECT){
		String mLink ="httP://google.com.vn";
		try {
			mLink=IP_CONECT.replace(HTTP, "").replace(NAME_DIRECT+NAME_SERVLET, "");
		} catch (Exception e) {
		}
		return mLink;
		
	}
	public static String get_link_connect(String IP){
		String mLink ="httP://google.com.vn";
		try {
			mLink=HTTP
					+ IP
					+NAME_DIRECT+NAME_SERVLET;
		} catch (Exception e) {
		}
		return mLink;
		
	}
	
}
