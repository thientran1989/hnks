package hnks.kitsoft.vn.object;

import hnks.kitsoft.utils.Utils;

import java.io.DataInputStream;

import android.database.Cursor;

public class Obj_DANHMUC_TRAM {
	public String MA_DVIQLY;
	public String MA_TRAM;
	public String TEN_TRAM;
	public String DINH_DANH;
	public String CSUAT_TRAM;
	public String MA_CAPDA_RA;
	public String DONGDIEN;
	public double X;
	public double Y;
	public String GHICHU;

	public Obj_DANHMUC_TRAM(String MA_DVIQLY,String MA_TRAM, String TEN_TRAM, String DINH_DANH,
			String CSUAT_TRAM, String MA_CAPDA_RA, String DONGDIEN, double X,
			double Y, String GHICHU) {
		this.MA_DVIQLY =MA_DVIQLY;
		this.MA_TRAM = MA_TRAM;
		this.TEN_TRAM=TEN_TRAM;
		this.DINH_DANH=DINH_DANH;
		this.CSUAT_TRAM=CSUAT_TRAM;
		this.MA_CAPDA_RA=MA_CAPDA_RA;
		this.DONGDIEN=DONGDIEN;
		this.X=X;
		this.Y=Y;
		this.GHICHU=GHICHU;

	}

	public void SET_OBJECT(Cursor c) {
		MA_DVIQLY = c.getString(c.getColumnIndex(Utils.MA_DVIQLY));
		MA_TRAM = c.getString(c.getColumnIndex(Utils.MA_TRAM));
		TEN_TRAM = c.getString(c.getColumnIndex(Utils.TEN_TRAM));
		DINH_DANH = c.getString(c.getColumnIndex(Utils.DINH_DANH));
		CSUAT_TRAM = c.getString(c.getColumnIndex(Utils.CSUAT_TRAM));
		MA_CAPDA_RA = c.getString(c.getColumnIndex(Utils.MA_CAPDA_RA));
		DONGDIEN = c.getString(c.getColumnIndex(Utils.DONGDIEN));
		X = c.getDouble(c.getColumnIndex(Utils.X));
		Y = c.getDouble(c.getColumnIndex(Utils.Y));
		GHICHU = c.getString(c.getColumnIndex(Utils.GHICHU));
	}
	public Obj_DANHMUC_TRAM (){
		
	}

	public String getMA_DVIQLY() {
		return MA_DVIQLY;
	}

	public void setMA_DVIQLY(String mA_DVIQLY) {
		MA_DVIQLY = mA_DVIQLY;
	}

	public String getMA_TRAM() {
		return MA_TRAM;
	}

	public void setMA_TRAM(String mA_TRAM) {
		MA_TRAM = mA_TRAM;
	}

	public String getTEN_TRAM() {
		return TEN_TRAM;
	}

	public void setTEN_TRAM(String tEN_TRAM) {
		TEN_TRAM = tEN_TRAM;
	}

	public String getDINH_DANH() {
		return DINH_DANH;
	}

	public void setDINH_DANH(String dINH_DANH) {
		DINH_DANH = dINH_DANH;
	}

	public String getCSUAT_TRAM() {
		return CSUAT_TRAM;
	}

	public void setCSUAT_TRAM(String cSUAT_TRAM) {
		CSUAT_TRAM = cSUAT_TRAM;
	}

	public String getMA_CAPDA_RA() {
		return MA_CAPDA_RA;
	}

	public void setMA_CAPDA_RA(String mA_CAPDA_RA) {
		MA_CAPDA_RA = mA_CAPDA_RA;
	}

	public String getDONGDIEN() {
		return DONGDIEN;
	}

	public void setDONGDIEN(String dONGDIEN) {
		DONGDIEN = dONGDIEN;
	}

	public double getX() {
		return X;
	}

	public void setX(double x) {
		X = x;
	}

	public double getY() {
		return Y;
	}

	public void setY(double y) {
		Y = y;
	}

	public String getGHICHU() {
		return GHICHU;
	}

	public void setGHICHU(String gHICHU) {
		GHICHU = gHICHU;
	}

	public void readObject(DataInputStream din) {
		try {
			MA_DVIQLY=din.readUTF();
			MA_TRAM = din.readUTF();
			TEN_TRAM = din.readUTF();
			DINH_DANH = din.readUTF();
			CSUAT_TRAM = din.readUTF();
			MA_CAPDA_RA = din.readUTF();
			DONGDIEN = din.readUTF();
			X = din.readDouble();
			Y = din.readDouble();
			GHICHU = din.readUTF();
		} catch (Exception e) {}
	}



}
