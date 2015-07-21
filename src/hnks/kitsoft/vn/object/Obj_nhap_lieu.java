package hnks.kitsoft.vn.object;

import hnks.kitsoft.utils.Utils;

import com.thtsoftlib.function.ThtDatabase;

import android.database.Cursor;

public class Obj_nhap_lieu {
	
	public String nhap_lieu;
	public int loai_nhap_lieu;
	public int STT;
	
	public Obj_nhap_lieu(String nhap_lieu, int loai_nhap_lieu) {
		super();
		this.nhap_lieu = nhap_lieu;
		this.loai_nhap_lieu = loai_nhap_lieu;
	}
	public Obj_nhap_lieu(){
		
	}
	public void set_obj(Cursor c){
		STT = ThtDatabase.get_cursor_int(c, Utils.STT);
		nhap_lieu = ThtDatabase.get_cursor_string(c, Utils.nhap_lieu);
		loai_nhap_lieu = ThtDatabase.get_cursor_int(c, Utils.loai_nhap_lieu);
	}
	

}
