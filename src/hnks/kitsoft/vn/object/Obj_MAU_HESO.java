package hnks.kitsoft.vn.object;

import hnks.kitsoft.utils.Utils;
import hnks.kitsoft.vn.Memory;

import java.io.DataInputStream;
import java.io.DataOutputStream;

import android.database.Cursor;


public class Obj_MAU_HESO {
	public String MA_DVIQLY="";
	public int id_HESO=0;
	public String TEN_HESO="";
	public double PT_TT=0;
	public double PT_C=0;
	public double PT_C1=0;
	public double PT_TL=0;
	public double PT_K=0;
	public double PT_VAT=0;
	public double PT_NC=0;
	public double PT_NC1=0;
	public Obj_MAU_HESO(){
		
	}
	public Obj_MAU_HESO(String mA_DVIQLY, int id_HESO, String tEN_HESO,
			double pT_TT, double pT_C, double pT_TL, double pT_K,
			double pT_VAT, double pT_NC, double pT_C1, double pT_NC1) {
		super();
		MA_DVIQLY = mA_DVIQLY;
		this.id_HESO = id_HESO;
		TEN_HESO = tEN_HESO;
		PT_TT = pT_TT;
		PT_C = pT_C;
		PT_TL = pT_TL;
		PT_K = pT_K;
		PT_VAT = pT_VAT;
		PT_NC = pT_NC;
		PT_C1 = pT_C1;
		PT_NC1 = pT_NC1;
	}
	
	public void sendObject(DataOutputStream dos) {
		try {
			dos.writeUTF(Memory.N2S(MA_DVIQLY));
			dos.writeInt(id_HESO);
			dos.writeUTF(Memory.N2S(TEN_HESO));
			dos.writeDouble(PT_TT);
			dos.writeDouble(PT_C);
			dos.writeDouble(PT_C1);
			dos.writeDouble(PT_TL);
			dos.writeDouble(PT_K);
			dos.writeDouble(PT_VAT);
			dos.writeDouble(PT_NC);
			dos.writeDouble(PT_NC1);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public void readObject(DataInputStream din) {
		try {
			MA_DVIQLY = din.readUTF();
			id_HESO = din.readInt();
			TEN_HESO= din.readUTF();
			PT_TT=din.readDouble();
			PT_C=din.readDouble();
			PT_C1=din.readDouble();
			PT_TL=din.readDouble();
			PT_K=din.readDouble();
			PT_VAT=din.readDouble();
			PT_NC=din.readDouble();
			PT_NC1=din.readDouble();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	
	public void SET_OBJ(Cursor cur){
		MA_DVIQLY = cur.getString(cur.getColumnIndex(Utils.MA_DVIQLY));
		id_HESO = cur.getInt(cur.getColumnIndex(Utils.id_HESO));
		TEN_HESO = cur.getString(cur.getColumnIndex(Utils.TEN_HESO));
		PT_TT = cur.getDouble(cur.getColumnIndex(Utils.PT_TT));
		PT_C = cur.getDouble(cur.getColumnIndex(Utils.PT_C));
		PT_TL = cur.getDouble(cur.getColumnIndex(Utils.PT_TL));
		PT_K = cur.getDouble(cur.getColumnIndex(Utils.PT_K));
		PT_VAT = cur.getDouble(cur.getColumnIndex(Utils.PT_VAT));
		PT_NC = cur.getDouble(cur.getColumnIndex(Utils.PT_NC));
		PT_C1 = cur.getDouble(cur.getColumnIndex(Utils.PT_C1));
		PT_NC1 = cur.getDouble(cur.getColumnIndex(Utils.PT_NC1));
	}
	public String getMA_DVIQLY() {
		return MA_DVIQLY;
	}
	public void setMA_DVIQLY(String mA_DVIQLY) {
		MA_DVIQLY = mA_DVIQLY;
	}
	public String getTEN_HESO() {
		return TEN_HESO;
	}
	public void setTEN_HESO(String tEN_HESO) {
		TEN_HESO = tEN_HESO;
	}
	public double getPT_TT() {
		return PT_TT;
	}
	public void setPT_TT(double pT_TT) {
		PT_TT = pT_TT;
	}
	public double getPT_C() {
		return PT_C;
	}
	public void setPT_C(double pT_C) {
		PT_C = pT_C;
	}
	public double getPT_TL() {
		return PT_TL;
	}
	public void setPT_TL(double pT_TL) {
		PT_TL = pT_TL;
	}
	public double getPT_K() {
		return PT_K;
	}
	public void setPT_K(double pT_K) {
		PT_K = pT_K;
	}
	public double getPT_VAT() {
		return PT_VAT;
	}
	public void setPT_VAT(double pT_VAT) {
		PT_VAT = pT_VAT;
	}
	public double getPT_NC() {
		return PT_NC;
	}
	public void setPT_NC(double pT_NC) {
		PT_NC = pT_NC;
	}
	public double getPT_C1() {
		return PT_C1;
	}
	public void setPT_C1(double pT_C1) {
		PT_C1 = pT_C1;
	}
	public double getPT_NC1() {
		return PT_NC1;
	}
	public void setPT_NC1(double pT_NC1) {
		PT_NC1 = pT_NC1;
	}
	public int getId_HESO() {
		return id_HESO;
	}
	
	

}
