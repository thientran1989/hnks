package hnks.kitsoft.vn.object;

import hnks.kitsoft.utils.Utils;
import android.database.Cursor;

public class Obj_HSO_VATTU_CTIET {
	
	public String MA_DVIQLY;
	public int MA_LOAI_TTOAN;
	public String MA_YCAU_KNAI;
	public String MA_VTU;
	public String TEN_VTU;
	public int    DON_GIA;
	public double SO_LUONG;
	public String THOI_GIAN;
	public String MA_LOAI_CPHI;
	
	//Them cua HN
	public double K1NC=1; 
	public double K2NC=1;
	public double KMTC=1; // He so dieu chinh May Thi Cong
	public int    DG_NC=0;
	public int    DG_MTC=0;

	public Obj_HSO_VATTU_CTIET(String MA_DVIQLY,
								int MA_LOAI_TTOAN,
								String MA_YCAU_KNAI,
								String MA_VTU,
								String TEN_VTU,
								int    DON_GIA,
								double SO_LUONG,
								String THOI_GIAN,
								String MA_LOAI_CPHI) {
		this.MA_DVIQLY = MA_DVIQLY;
		this.MA_LOAI_TTOAN = MA_LOAI_TTOAN;
		this.MA_YCAU_KNAI  = MA_YCAU_KNAI;
		this.MA_VTU = MA_VTU;
		this.TEN_VTU = TEN_VTU;
		this.DON_GIA = DON_GIA;
		this.SO_LUONG = SO_LUONG;
		this.THOI_GIAN = THOI_GIAN;
		this.MA_LOAI_CPHI = MA_LOAI_CPHI;
	}

	public Obj_HSO_VATTU_CTIET(){
		
	}
	public void set_obj(Cursor cur){
		MA_DVIQLY=cur.getString(cur.getColumnIndex(Utils.MA_DVIQLY));
		MA_LOAI_TTOAN=cur.getInt(cur.getColumnIndex(Utils.MA_LOAI_TTOAN));
		MA_YCAU_KNAI=cur.getString(cur.getColumnIndex(Utils.MA_YCAU_KNAI));
		MA_VTU=cur.getString(cur.getColumnIndex(Utils.MA_VTU));
		TEN_VTU=cur.getString(cur.getColumnIndex(Utils.TEN_VTU));
		DON_GIA=cur.getInt(cur.getColumnIndex(Utils.DON_GIA));
		SO_LUONG=cur.getDouble(cur.getColumnIndex(Utils.SO_LUONG));
		THOI_GIAN=cur.getString(cur.getColumnIndex(Utils.THOI_GIAN));
		MA_LOAI_CPHI=cur.getString(cur.getColumnIndex(Utils.MA_LOAI_CPHI));
		K1NC = cur.getDouble(cur.getColumnIndex(tag_K1NC));
		K2NC = cur.getDouble(cur.getColumnIndex(tag_K2NC));
		KMTC = cur.getDouble(cur.getColumnIndex(tag_KMTC));
		DG_NC = cur.getInt(cur.getColumnIndex(tag_DG_NC));
		DG_MTC = cur.getInt(cur.getColumnIndex(tag_DG_MTC));
	}
	public String getMA_DVIQLY() {
		return MA_DVIQLY;
	}
	public void setMA_DVIQLY(String mA_DVIQLY) {
		MA_DVIQLY = mA_DVIQLY;
	}
	public int getMA_LOAI_TTOAN() {
		return MA_LOAI_TTOAN;
	}
	public void setMA_LOAI_TTOAN(int mA_LOAI_TTOAN) {
		MA_LOAI_TTOAN = mA_LOAI_TTOAN;
	}
	public String getMA_YCAU_KNAI() {
		return MA_YCAU_KNAI;
	}
	public void setMA_YCAU_KNAI(String mA_YCAU_KNAI) {
		MA_YCAU_KNAI = mA_YCAU_KNAI;
	}
	public String getMA_VTU() {
		return MA_VTU;
	}
	public void setMA_VTU(String mA_VTU) {
		MA_VTU = mA_VTU;
	}
	public String getTEN_VTU() {
		return TEN_VTU;
	}
	public void setTEN_VTU(String tEN_VTU) {
		TEN_VTU = tEN_VTU;
	}
	public int getDON_GIA() {
		return DON_GIA;
	}
	public void setDON_GIA(int dON_GIA) {
		DON_GIA = dON_GIA;
	}
	public double getSO_LUONG() {
		return SO_LUONG;
	}
	public void setSO_LUONG(double sO_LUONG) {
		SO_LUONG = sO_LUONG;
	}
	public String getTHOI_GIAN() {
		return THOI_GIAN;
	}
	public void setTHOI_GIAN(String tHOI_GIAN) {
		THOI_GIAN = tHOI_GIAN;
	}
	public String getMA_LOAI_CPHI() {
		return MA_LOAI_CPHI;
	}
	public void setMA_LOAI_CPHI(String mA_LOAI_CPHI) {
		MA_LOAI_CPHI = mA_LOAI_CPHI;
	}
	public double getK1NC() {
		return K1NC;
	}

	public void setK1NC(double k1nc) {
		K1NC = k1nc;
	}

	public double getK2NC() {
		return K2NC;
	}

	public void setK2NC(double k2nc) {
		K2NC = k2nc;
	}

	public double getKMTC() {
		return KMTC;
	}

	public void setKMTC(double kMTC) {
		KMTC = kMTC;
	}

	public int getDG_NC() {
		return DG_NC;
	}

	public void setDG_NC(int dG_NC) {
		DG_NC = dG_NC;
	}

	public int getDG_MTC() {
		return DG_MTC;
	}

	public void setDG_MTC(int dG_MTC) {
		DG_MTC = dG_MTC;
	}
	public static final String tag_K1NC= "K1NC"; 
	public static final String tag_K2NC="K2NC";
	public static final String tag_KMTC="KMTC"; // He so dieu chinh May Thi Cong
	public static final String tag_DG_NC="DG_NC";
	public static final String tag_DG_MTC="DG_MTC";
	
}
