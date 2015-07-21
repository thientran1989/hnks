package hnks.kitsoft.vn.object;

import hnks.kitsoft.utils.Utils;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.Serializable;

import android.database.Cursor;

@SuppressWarnings("serial")
public class Obj_D_MAU implements Serializable {

	public String MAU;
	public String MA_DVIQLY;
	public String MA_VTU;
	public double SO_LUONG;

	public Obj_D_MAU(String MAU,String MA_DVIQLY ,String MA_VTU, double SO_LUONG) {
		this.MAU = MAU;
		this.MA_DVIQLY = MA_DVIQLY;
		this.MA_VTU = MA_VTU;
		this.SO_LUONG = SO_LUONG;
	}
	public Obj_D_MAU() {
		super();
	}
	public void sendObject(DataOutputStream dos) {
		try {
			dos.writeUTF(MAU);
			dos.writeUTF(MA_DVIQLY);
			dos.writeUTF(MA_VTU);
			dos.writeDouble(SO_LUONG);
		} catch (Exception e) {}
	}
	public void readObject(DataInputStream din) {
		try {
			MAU=din.readUTF();
			MA_DVIQLY=din.readUTF();
			MA_VTU=din.readUTF();
			SO_LUONG=din.readDouble();
		} catch (Exception e) {}
	}
	public void SET_OBJ(Cursor cur){
		MAU=cur.getString(cur.getColumnIndex(Utils.MAU));
		MA_DVIQLY=cur.getString(cur.getColumnIndex(Utils.MA_DVIQLY));
		MA_VTU=cur.getString(cur.getColumnIndex(Utils.MA_VTU));
		SO_LUONG=cur.getDouble(cur.getColumnIndex(Utils.SO_LUONG));
	}
	public String getMA_DVIQLY() {
		return MA_DVIQLY;
	}
	public void setMA_DVIQLY(String mA_DVIQLY) {
		MA_DVIQLY = mA_DVIQLY;
	}
	public String getMA_VTU() {
		return MA_VTU;
	}
	public void setMA_VTU(String mA_VTU) {
		MA_VTU = mA_VTU;
	}
	public double getSO_LUONG() {
		return SO_LUONG;
	}
	public void setSO_LUONG(double sO_LUONG) {
		SO_LUONG = sO_LUONG;
	}
	public String getMAU() {
		return MAU;
	}
	public void setMAU(String mAU) {
		MAU = mAU;
	}
	
	

}
