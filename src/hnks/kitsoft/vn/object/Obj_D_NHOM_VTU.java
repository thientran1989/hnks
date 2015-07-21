package hnks.kitsoft.vn.object;

import hnks.kitsoft.utils.Utils;

import java.io.DataInputStream;
import java.io.Serializable;

import android.database.Cursor;

@SuppressWarnings("serial")
public class Obj_D_NHOM_VTU implements Serializable {
		public int    NHOM;
		public String TEN_NHOM;
		public String MA_DVIQLY;
	public Obj_D_NHOM_VTU(int NHOM,String TEN_NHOM,String MA_DVIQLY) {
		this.NHOM = NHOM;
		this.TEN_NHOM = TEN_NHOM;
		this.MA_DVIQLY = MA_DVIQLY;
	
	}
	public Obj_D_NHOM_VTU() {
		super();
	}

	public void readObj(DataInputStream din) {
		try {
			NHOM=din.readInt();
			TEN_NHOM=din.readUTF();
			MA_DVIQLY=din.readUTF();
		} catch (Exception e) {

		}
	}
	public int getNHOM() {
		return NHOM;
	}
	public void setNHOM(int nHOM) {
		NHOM = nHOM;
	}
	public String getTEN_NHOM() {
		return TEN_NHOM;
	}
	public void setTEN_NHOM(String tEN_NHOM) {
		TEN_NHOM = tEN_NHOM;
	}
	public String getMA_DVIQLY() {
		return MA_DVIQLY;
	}
	public void setMA_DVIQLY(String mA_DVIQLY) {
		MA_DVIQLY = mA_DVIQLY;
	}
	public void SET_OBJECT(Cursor c) {
		NHOM = c.getInt(c.getColumnIndex(Utils.NHOM));
		TEN_NHOM = c.getString(c.getColumnIndex(Utils.TEN_NHOM));
		MA_DVIQLY = c.getString(c.getColumnIndex(Utils.MA_DVIQLY));
		
	}
	
	
}
