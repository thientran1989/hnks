package hnks.kitsoft.vn.object;

import hnks.kitsoft.utils.Utils;

import java.io.Serializable;
import java.util.List;

import android.database.Cursor;

@SuppressWarnings("serial")
public class Obj_D_VATTU implements Serializable{
	public String MA_DVIQLY;
	public int    NHOM;
	public String MA_LOAI_CPHI;
	public String MA_VTU;
	public String TEN_VTU;
	public double DINH_MUC_VT;
	public int    DON_GIA;
	public String DVI_TINH;
	public String KHU_VUC;
	public String MA_DINHMUC;
	
	//Them cua HN
	public double K1NC=1; 
	public double K2NC=1;
	public double KMTC=1; // He so dieu chinh May Thi Cong
	public int    DG_NC=0;
	public int    DG_MTC=0;
	public int    DON_GIA_TG=0;

	public void SET_OBJ(Cursor cur){
		MA_DVIQLY=cur.getString(cur.getColumnIndex(Utils.MA_DVIQLY));
		NHOM=cur.getInt(cur.getColumnIndex(Utils.NHOM));
		MA_LOAI_CPHI=cur.getString(cur.getColumnIndex(Utils.MA_LOAI_CPHI));
		MA_VTU=cur.getString(cur.getColumnIndex(Utils.MA_VTU));
		TEN_VTU=cur.getString(cur.getColumnIndex(Utils.TEN_VTU));
		DINH_MUC_VT=cur.getDouble(cur.getColumnIndex(Utils.DINH_MUC_VT));
		DON_GIA=cur.getInt(cur.getColumnIndex(Utils.DON_GIA));
		DVI_TINH=cur.getString(cur.getColumnIndex(Utils.DVI_TINH));
		KHU_VUC=cur.getString(cur.getColumnIndex(Utils.KHU_VUC));
		MA_DINHMUC=cur.getString(cur.getColumnIndex(Utils.MA_DINHMUC));
		K1NC = cur.getDouble(cur.getColumnIndex(tag_K1NC));
		K2NC = cur.getDouble(cur.getColumnIndex(tag_K2NC));
		KMTC = cur.getDouble(cur.getColumnIndex(tag_KMTC));
		DG_NC = cur.getInt(cur.getColumnIndex(tag_DG_NC));
		DG_MTC = cur.getInt(cur.getColumnIndex(tag_DG_MTC));
		DON_GIA_TG = cur.getInt(cur.getColumnIndex(tag_DON_GIA_TG));
	}

	public String getMA_DVIQLY() {
		return MA_DVIQLY;
	}

	public void setMA_DVIQLY(String mA_DVIQLY) {
		MA_DVIQLY = mA_DVIQLY;
	}

	public int getNHOM() {
		return NHOM;
	}

	public void setNHOM(int nHOM) {
		NHOM = nHOM;
	}

	public String getMA_LOAI_CPHI() {
		return MA_LOAI_CPHI;
	}

	public void setMA_LOAI_CPHI(String mA_LOAI_CPHI) {
		MA_LOAI_CPHI = mA_LOAI_CPHI;
	}

	public String getTEN_VTU() {
		return TEN_VTU;
	}

	public void setTEN_VTU(String tEN_VTU) {
		TEN_VTU = tEN_VTU;
	}

	public double getDINH_MUC_VT() {
		return DINH_MUC_VT;
	}

	public void setDINH_MUC_VT(double dINH_MUC_VT) {
		DINH_MUC_VT = dINH_MUC_VT;
	}

	public int getDON_GIA() {
		return DON_GIA;
	}

	public void setDON_GIA(int dON_GIA) {
		DON_GIA = dON_GIA;
	}

	public String getDVI_TINH() {
		return DVI_TINH;
	}

	public void setDVI_TINH(String dVI_TINH) {
		DVI_TINH = dVI_TINH;
	}

	public String getKHU_VUC() {
		return KHU_VUC;
	}

	public void setKHU_VUC(String kHU_VUC) {
		KHU_VUC = kHU_VUC;
	}

	public String getMA_DINHMUC() {
		return MA_DINHMUC;
	}

	public void setMA_DINHMUC(String mA_DINHMUC) {
		MA_DINHMUC = mA_DINHMUC;
	}

	public String getMA_VTU() {
		return MA_VTU;
	}

	public void setMA_VTU(String mA_VTU) {
		MA_VTU = mA_VTU;
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
	

	public int getDON_GIA_TG() {
		return DON_GIA_TG;
	}

	public void setDON_GIA_TG(int dON_GIA_TG) {
		DON_GIA_TG = dON_GIA_TG;
	}


	public static final String tag_K1NC= "K1NC"; 
	public static final String tag_K2NC="K2NC";
	public static final String tag_KMTC="KMTC"; // He so dieu chinh May Thi Cong
	public static final String tag_DG_NC="DG_NC";
	public static final String tag_DG_MTC="DG_MTC";
	public static final String tag_DON_GIA_TG="DON_GIA_TG";
	
	public String get_NHOM_label(List<Obj_D_NHOM_VTU> list_nhom) {
		String label = "loi";
		for (Obj_D_NHOM_VTU obj_D_NHOM_VTU : list_nhom) {
			if(obj_D_NHOM_VTU.getNHOM()==getNHOM()){
				label = obj_D_NHOM_VTU.getTEN_NHOM();
			}
		}
		return label;
	}
	public String get_LOAICP_label(List<Obj_Text> listCP) {
		String label = "loi";
		for (Obj_Text mCP : listCP) {
			if(mCP.KEY.equals(getMA_LOAI_CPHI())){
				label = mCP.NAME;
			}
		}
		return label;
	}
	
}
