package hnks.kitsoft.vn;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Memory {
	public static String Version = "v3.9";
	
    //public static Obj_D_NHAN_VIEN NV=new Obj_D_NHAN_VIEN();
    
    public static String N2S(Object o) {
		if (o==null) return "";
		else return o.toString();
	}
    public static String getNow() {
    	Calendar cal = Calendar.getInstance();
    	SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:SS");
    	String lFromDate1="";
        try {
            lFromDate1 = format.format(cal.getTime());
        } catch (Exception e) {
        }
        return  lFromDate1;
    }
    public static String LAY_TIME_TOI_PHUT() {
    	Calendar cal = Calendar.getInstance();
    	SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    	String lFromDate1="";
        try {
            lFromDate1 = format.format(cal.getTime());
        } catch (Exception e) {
        }
        return  lFromDate1;
    }
}
