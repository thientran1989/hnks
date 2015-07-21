package hnks.kitsoft.vn.object;

import hnks.kitsoft.utils.Utils;

import java.util.List;
public class Obj_Chiettinh {
	public double TIEN_VL_KH ;
	double TIEN_NC_KH;
	double TIEN_VC_KH;
	double TIEN_M_KH;
	double T;
	double TT;
	double C;
	double TL;
	double K;
	double Gtt;
	double VAT;
	double G;
	public Obj_Chiettinh(double tIEN_VL_KH, double tIEN_NC_KH, double tIEN_VC_KH,
			double tIEN_M_KH, double t, double tT, double c, double tL, double k, double gtt,
			double vAT, double g) {
		super();
		TIEN_VL_KH = tIEN_VL_KH;
		TIEN_NC_KH = tIEN_NC_KH;
		TIEN_VC_KH = tIEN_VC_KH;
		TIEN_M_KH = tIEN_M_KH;
		T = t;
		TT = tT;
		C = c;
		TL = tL;
		K = k;
		Gtt = gtt;
		VAT = vAT;
		G = g;
	}
	public Obj_Chiettinh() {
		// TODO Auto-generated constructor stub
	}
	public double getTIEN_VL_KH() {
		return TIEN_VL_KH;
	}
	public void setTIEN_VL_KH(double tIEN_VL_KH) {
		TIEN_VL_KH = tIEN_VL_KH;
	}
	public double getTIEN_NC_KH() {
		return TIEN_NC_KH;
	}
	public void setTIEN_NC_KH(double tIEN_NC_KH) {
		TIEN_NC_KH = tIEN_NC_KH;
	}
	public double getTIEN_VC_KH() {
		return TIEN_VC_KH;
	}
	public void setTIEN_VC_KH(double tIEN_VC_KH) {
		TIEN_VC_KH = tIEN_VC_KH;
	}
	public double getTIEN_M_KH() {
		return TIEN_M_KH;
	}
	public void setTIEN_M_KH(double tIEN_M_KH) {
		TIEN_M_KH = tIEN_M_KH;
	}
	public double getT() {
		return T;
	}
	public void setT(double t) {
		T = t;
	}
	public double getTT() {
		return TT;
	}
	public void setTT(double tT) {
		TT = tT;
	}
	public double getC() {
		return C;
	}
	public void setC(double c) {
		C = c;
	}
	public double getTL() {
		return TL;
	}
	public void setTL(double tL) {
		TL = tL;
	}
	public double getK() {
		return K;
	}
	public void setK(double k) {
		K = k;
	}
	public double getGtt() {
		return Gtt;
	}
	public void setGtt(double gtt) {
		Gtt = gtt;
	}
	public double getVAT() {
		return VAT;
	}
	public void setVAT(double vAT) {
		VAT = vAT;
	}
	public double getG() {
		return G;
	}
	public void setG(double g) {
		G = g;
	}

	public static Obj_Chiettinh TINH_CHI_PHI_DL(List<Obj_HSO_VATTU_CTIET> listVTCTiet,Obj_HSO_CHIETTINH HS) {
		double CP_VLieu_DL=0; double CP_NCong_DL=0; double CP_May_DL=0; double CP_VC_DL=0;
		double PT_TT = HS.PT_TT/100;
		double PT_C = HS.PT_C/100;
		double PT_TL = HS.PT_TL/100;
		double PT_K = HS.PT_K/100;
		double PT_VAT = HS.PT_VAT/100;
		double PT_C1 = HS.PT_C1/100;
		double PT_NC = HS.PT_NC;
		double PT_NC1 = HS.PT_NC1;
		for (Obj_HSO_VATTU_CTIET VT : listVTCTiet) {
			if (VT.MA_LOAI_TTOAN==Utils.LOAI_CP_DIENLUC) {
				if (VT.MA_LOAI_CPHI.equals(Utils.VAT_LIEU)) {
					CP_VLieu_DL=CP_VLieu_DL+VT.SO_LUONG*VT.DON_GIA;
				} else if (VT.MA_LOAI_CPHI.equals(Utils.NHAN_CONG)) {
					CP_NCong_DL=CP_NCong_DL+VT.SO_LUONG*VT.DON_GIA;
				} else if (VT.MA_LOAI_CPHI.equals(Utils.MAY_THI_CONG)) {
					CP_May_DL=CP_May_DL+VT.SO_LUONG*VT.DON_GIA;
				} else if (VT.MA_LOAI_CPHI.equals(Utils.VAN_CHUYEN)) {  
					CP_VC_DL=CP_VC_DL+VT.SO_LUONG*VT.DON_GIA;
				}
			}
		}
		CP_VLieu_DL=Math.round(CP_VLieu_DL); 
		CP_NCong_DL=Math.round(CP_NCong_DL);
		CP_May_DL=Math.round(CP_May_DL);
		CP_VC_DL=Math.round(CP_VC_DL);
		
		CP_VLieu_DL=CP_VLieu_DL+CP_VC_DL;
		CP_NCong_DL=CP_NCong_DL*PT_NC*PT_NC1;
		//double CP_TTiep_Khac_DL=PT_TT*(CP_VLieu_DL+CP_NCong_DL+CP_May_DL);
		double CP_TTiep_T_DL=CP_VLieu_DL+CP_NCong_DL+CP_May_DL; //+CP_TTiep_Khac_DL;
		
		double CP_C_DL=PT_C1*PT_C*CP_NCong_DL;
		//double TL_DL=PT_TL*(CP_TTiep_T_DL+CP_C_DL);
		//double K_DL=PT_K*(CP_TTiep_T_DL+CP_C_DL+TL_DL);
		//double Gtt_DL=CP_TTiep_T_DL+CP_C_DL+TL_DL+K_DL;
		//double VAT_DL=PT_VAT*Gtt_DL;
		double G_DL=CP_TTiep_T_DL+CP_C_DL; //Gtt_DL+VAT_DL;
		
		Obj_Chiettinh CT = new Obj_Chiettinh();
		CT.setC(CP_C_DL);
		CT.setG(G_DL);
		CT.setT(CP_TTiep_T_DL);

		CT.setTIEN_M_KH(CP_May_DL);
		CT.setTIEN_NC_KH(CP_NCong_DL);
		CT.setTIEN_VC_KH(CP_VC_DL);
		CT.setTIEN_VL_KH(CP_VLieu_DL);
		return CT;
		
	}

	public static Obj_Chiettinh TINH_CHI_PHI_KH(List<Obj_HSO_VATTU_CTIET> listVTCTiet,Obj_HSO_CHIETTINH HS) {
		double PT_TT = HS.PT_TT/100;
		double PT_C = HS.PT_C/100;
		double PT_TL = HS.PT_TL/100;
		double PT_K = HS.PT_K/100;
		double PT_VAT = HS.PT_VAT/100;
		double PT_C1 = HS.PT_C1/100;
		double PT_NC = HS.PT_NC;
		double PT_NC1 = HS.PT_NC1;
	
		double CP_VLieu_KH=0; double CP_NCong_KH=0; double CP_May_KH=0; double CP_VC_KH=0;
		double CP_VLieu_KH_TuLo=0;
		for (Obj_HSO_VATTU_CTIET VT : listVTCTiet) {
			if (VT.MA_LOAI_TTOAN==Utils.LOAI_CP_KHACHHANG || VT.MA_LOAI_TTOAN==Utils.LOAI_CP_KHACHHANG_TULO) {
				if (VT.MA_LOAI_CPHI.equals("VL") & VT.MA_LOAI_TTOAN==1) {
					CP_VLieu_KH=CP_VLieu_KH+VT.SO_LUONG*VT.DON_GIA;
				}if (VT.MA_LOAI_CPHI.equals("VL") & VT.MA_LOAI_TTOAN==2) {
					CP_VLieu_KH_TuLo=CP_VLieu_KH_TuLo+VT.SO_LUONG*VT.DON_GIA;
				} else if (VT.MA_LOAI_CPHI.equals("NC")) {
					CP_NCong_KH=CP_NCong_KH+VT.SO_LUONG*VT.DON_GIA;
				} else if (VT.MA_LOAI_CPHI.equals("M")) {  // Chi Phi May
					CP_May_KH=CP_May_KH+VT.SO_LUONG*VT.DON_GIA;
				} else if (VT.MA_LOAI_CPHI.equals("VC")) {
					CP_VC_KH=CP_VC_KH+VT.SO_LUONG*VT.DON_GIA;
				}
			}
		}
	
		CP_VLieu_KH=Math.round(CP_VLieu_KH); 
		CP_NCong_KH=Math.round(CP_NCong_KH);
		CP_May_KH=Math.round(CP_May_KH);
		CP_VC_KH=Math.round(CP_VC_KH);
		CP_VLieu_KH_TuLo=Math.round(CP_VLieu_KH_TuLo);
	
		CP_VLieu_KH=CP_VLieu_KH+CP_VC_KH;
		CP_NCong_KH=Math.round(CP_NCong_KH*PT_NC*PT_NC1);
		double CP_TTiep_Khac_KH=Math.round(PT_TT*(CP_VLieu_KH+CP_NCong_KH+CP_May_KH));
		double CP_TTiep_T_KH=CP_VLieu_KH+CP_NCong_KH+CP_May_KH+CP_TTiep_Khac_KH;
	
		double CP_C_KH=Math.round(PT_C*CP_NCong_KH);
		double TL_KH=Math.round(PT_TL*(CP_TTiep_T_KH+CP_C_KH));
		double K_KH=Math.round(PT_K*(CP_TTiep_T_KH+CP_C_KH+TL_KH+CP_VLieu_KH_TuLo));
		double Gtt_KH=CP_TTiep_T_KH+CP_C_KH+TL_KH+K_KH;
		double VAT_KH=Math.round(PT_VAT*Gtt_KH);
		double G_KH=Gtt_KH+VAT_KH;

		Obj_Chiettinh CT = new Obj_Chiettinh();
		CT.setC(CP_C_KH);
		CT.setG(G_KH);
		CT.setGtt(Gtt_KH);
		CT.setK(K_KH);
		CT.setT(CP_TTiep_T_KH);
		CT.setTIEN_M_KH(CP_May_KH);
		CT.setTIEN_NC_KH(CP_NCong_KH);
		CT.setTIEN_VC_KH(CP_VC_KH);
		CT.setTIEN_VL_KH(CP_VLieu_KH);
		CT.setTL(TL_KH);
		CT.setTT(CP_TTiep_Khac_KH);
		CT.setVAT(VAT_KH);
		return CT;
	}
	
	public static Obj_Chiettinh TINH_CHI_PHI_KH_TV(List<Obj_HSO_VATTU_CTIET> listVTCTiet,Obj_HSO_CHIETTINH HS) {
		double PT_TT = HS.PT_TT/100;
		double PT_C = HS.PT_C/100;
		double PT_TL = HS.PT_TL/100;
		double PT_K = HS.PT_K/100;
		double PT_VAT = HS.PT_VAT/100;
		double PT_C1 = HS.PT_C1/100;
		double PT_NC = HS.PT_NC;
		double PT_NC1 = HS.PT_NC1;
	
		double CP_VLieu_KH=0; double CP_NCong_KH=0; double CP_May_KH=0; double CP_VC_KH=0;
		double CP_VLieu_KH_TuLo=0;
		for (Obj_HSO_VATTU_CTIET VT : listVTCTiet) {
			if (VT.MA_LOAI_TTOAN==Utils.LOAI_CP_KHACHHANG || VT.MA_LOAI_TTOAN==Utils.LOAI_CP_KHACHHANG_TULO ) {
				if (VT.MA_LOAI_CPHI.equals("VL") & VT.MA_LOAI_TTOAN==Utils.LOAI_CP_KHACHHANG) {
					CP_VLieu_KH=CP_VLieu_KH+VT.SO_LUONG*VT.DON_GIA;
				}if (VT.MA_LOAI_CPHI.equals("VL") & VT.MA_LOAI_TTOAN==Utils.LOAI_CP_KHACHHANG_TULO) {
					CP_VLieu_KH_TuLo=CP_VLieu_KH_TuLo+VT.SO_LUONG*VT.DON_GIA;
				} else if (VT.MA_LOAI_CPHI.equals("NC")) {
					CP_NCong_KH=CP_NCong_KH+VT.SO_LUONG*VT.DON_GIA;
				} else if (VT.MA_LOAI_CPHI.equals("M")) {  // Chi Phi May
					CP_May_KH=CP_May_KH+VT.SO_LUONG*VT.DON_GIA;
				} else if (VT.MA_LOAI_CPHI.equals("VC")) {
					CP_VC_KH=CP_VC_KH+VT.SO_LUONG*VT.DON_GIA;
				}
			}
		}
	
		CP_VLieu_KH=Math.round(CP_VLieu_KH); 
		CP_NCong_KH=Math.round(CP_NCong_KH);
		CP_May_KH=Math.round(CP_May_KH);
		CP_VC_KH=Math.round(CP_VC_KH);
		CP_VLieu_KH_TuLo=Math.round(CP_VLieu_KH_TuLo);
	
		CP_VLieu_KH=CP_VLieu_KH+CP_VC_KH;
		CP_NCong_KH=Math.round(CP_NCong_KH*PT_NC*PT_NC1);
		double CP_TTiep_Khac_KH=Math.round(PT_TT*(CP_VLieu_KH+CP_NCong_KH+CP_May_KH));
		double CP_TTiep_T_KH=CP_VLieu_KH+CP_NCong_KH+CP_May_KH+CP_TTiep_Khac_KH;
	
		double CP_C_KH=Math.round(PT_C*CP_NCong_KH);
		double TL_KH=Math.round(PT_TL*(CP_TTiep_T_KH+CP_C_KH));
		double K_KH=Math.round(PT_K*(CP_TTiep_T_KH+CP_C_KH+TL_KH));// sua o day
		double Gtt_KH=CP_TTiep_T_KH+CP_C_KH+TL_KH+K_KH;
		double VAT_KH=Math.round(PT_VAT*Gtt_KH);
		double G_KH=Gtt_KH+VAT_KH;

		Obj_Chiettinh CT = new Obj_Chiettinh();
		CT.setC(CP_C_KH);
		CT.setG(G_KH);
		CT.setGtt(Gtt_KH);
		CT.setK(K_KH);
		CT.setT(CP_TTiep_T_KH);
		CT.setTIEN_M_KH(CP_May_KH);
		CT.setTIEN_NC_KH(CP_NCong_KH);
		CT.setTIEN_VC_KH(CP_VC_KH);
		CT.setTIEN_VL_KH(CP_VLieu_KH);
		CT.setTL(TL_KH);
		CT.setTT(CP_TTiep_Khac_KH);
		CT.setVAT(VAT_KH);
		return CT;
	}
	
	

}
