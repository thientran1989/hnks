package hnks.kitsoft.vn.object;

import hnks.kitsoft.utils.Utils;

import java.io.DataInputStream;

import android.database.Cursor;

public class Obj_LIENKET_VATTU {
	public String MA_VTU;
	public String MA_NC_LAP;
	public String MA_NC_THAO;
	
	
	public Obj_LIENKET_VATTU(String MA_VTU,String MA_NC_LAP,
			String MA_NC_THAO
	){
		this.MA_VTU = MA_VTU;
		this.MA_NC_THAO =MA_NC_THAO;
		this.MA_NC_LAP=MA_NC_LAP;
		
	}
	public Obj_LIENKET_VATTU(){
		
	}
	public void SET_OBJ(Cursor c){
		MA_VTU = c.getString(c.getColumnIndex(Utils.MA_VTU));
		MA_NC_THAO = c.getString(c.getColumnIndex(Utils.MA_NC_THAO));
		MA_NC_LAP = c.getString(c.getColumnIndex(Utils.MA_NC_LAP));
	}
	public String getMA_VTU() {
		return MA_VTU;
	}
	public void setMA_VTU(String mA_VTU) {
		MA_VTU = mA_VTU;
	}
	public String getMA_NC_LAP() {
		return MA_NC_LAP;
	}
	public void setMA_NC_LAP(String mA_NC_LAP) {
		MA_NC_LAP = mA_NC_LAP;
	}
	public String getMA_NC_THAO() {
		return MA_NC_THAO;
	}
	public void setMA_NC_THAO(String mA_NC_THAO) {
		MA_NC_THAO = mA_NC_THAO;
	}
	
	public void readObject(DataInputStream din) {
		try {
			MA_VTU = din.readUTF();
			MA_NC_THAO= din.readUTF();
			MA_NC_LAP= din.readUTF();
		} catch (Exception e) {}
	}

}
