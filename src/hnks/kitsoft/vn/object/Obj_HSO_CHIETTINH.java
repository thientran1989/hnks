package hnks.kitsoft.vn.object;
import hnks.kitsoft.utils.Utils;
import hnks.kitsoft.vn.Memory;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.Serializable;

import android.database.Cursor;

@SuppressWarnings("serial")
public class Obj_HSO_CHIETTINH implements Serializable {

	public String MA_DVIQLY="";
	public String MA_YCAU_KNAI="";
	public String TEN_KHANG="";
	public String TEN_KHANG_KS="";
	public String SO_NHA="";
	public String SO_NHA_KS="";
	public String DUONG_PHO="";
	public String DUONG_PHO_KS="";
	public String DIEN_THOAI="";
	public String DIEN_THOAI_KS="";
	public String NGAY_YCAU="";
	public String BB_KSAT="";
	public String MA_GCST="";
	public String MA_GCSP="";
	public String MA_LOAI_HSO="";
	public String THOI_GIAN="";
	public int    DA_CHUYEN=0;
	public String GHI_CHU="";
	public double PT_TT=0;
	public double PT_C=0;
	public double PT_TL=0;
	public double PT_K=0;
	public double PT_VAT=0;
	public double PT_C1=0;
	public double PT_NC=0;
	public double PT_NC1=0;
	public String TENTRAM_BIENAP="",MA_TRAM="",CONG_SUAT_TRAM="",DONGDIEN_DINHMUC="",
	TAI_MAX="",CONGTO_TRAI="",CONGTO_PHAI="",KC_CONGTO_DEN_TRAM="",
	KC_CONGTO_DEN_LUOI="",TRU="",CONGSUAT_THUC_TE_KW="",CONGSUAT_THUC_TE_A="",
	MUC_DICH_SU_DUNG="",MA_KHACHHANG="",CONGSUAT_DENGHI_A="",CONGSUAT_DENGHI_V="",
	CONGSUAT_DENGHI_PHA="",LY_DO="",TINHTRANG_CONG_TO="",TINHTRANG_CONGTO_CO="",
	TINHTRANG_TRONGAI="",VITRITREO="",LOAI_TAI="",MASO_STT="";
	public int DA_XONG=0;
	public String SO_HO="";
	public String NGAY_KS="";
	public double    Xt=0;
	public double    Yt=0;
	public double    Xp=0;
	public double    Yp=0;
	public String    THUYET_TRINH="";
	public String    LOAI_XAY_DUNG="";
	public String    LOAI_NHA="";
	public String    DC_GANDIEN="";
	public String    PHUTAI_A="";
	public String    PHUTAI_B="";
	public String    PHUTAI_C="";
	public String    DV_THIETKE="";
	public String    DV_THICONG="";
	public String    DV_CAP_VTU="";
	public String    TTRANG_KO_CONGTO="";
	
	public Obj_HSO_CHIETTINH() {
		super();
	}
	
	

	public Obj_HSO_CHIETTINH(String mA_DVIQLY, String mA_YCAU_KNAI,
			String tEN_KHANG, String tEN_KHANG_KS, String sO_NHA,
			String sO_NHA_KS, String dUONG_PHO, String dUONG_PHO_KS,
			String dIEN_THOAI, String dIEN_THOAI_KS, String nGAY_YCAU,
			String bB_KSAT, String mA_GCST, String mA_GCSP, String mA_LOAI_HSO,
			String tHOI_GIAN, int dA_CHUYEN, String gHI_CHU, double pT_TT,
			double pT_C, double pT_TL, double pT_K, double pT_VAT,
			double pT_C1, double pT_NC, double pT_NC1, String tENTRAM_BIENAP,
			String mA_TRAM, String cONG_SUAT_TRAM, String dONGDIEN_DINHMUC,
			String tAI_MAX, String cONGTO_TRAI, String cONGTO_PHAI,
			String kC_CONGTO_DEN_TRAM, String kC_CONGTO_DEN_LUOI, String tRU,
			String cONGSUAT_THUC_TE_KW, String cONGSUAT_THUC_TE_A,
			String mUC_DICH_SU_DUNG, String mA_KHACHHANG,
			String cONGSUAT_DENGHI_A, String cONGSUAT_DENGHI_V,
			String cONGSUAT_DENGHI_PHA, String lY_DO, String tINHTRANG_CONG_TO,
			String tINHTRANG_CONGTO_CO, String tINHTRANG_TRONGAI,
			String vITRITREO, String lOAI_TAI, String mASO_STT, int dA_XONG,
			String sO_HO, String nGAY_KS, double xt, double yt, double xp,
			double yp, String tHUYET_TRINH, String lOAI_XAY_DUNG,
			String lOAI_NHA, String dC_GANDIEN, String pHUTAI_A,
			String pHUTAI_B, String pHUTAI_C, String dV_THIETKE,
			String dV_THICONG, String dV_CAP_VTU,String tTRANG_KO_CONGTO) {
		super();
		MA_DVIQLY = mA_DVIQLY;
		MA_YCAU_KNAI = mA_YCAU_KNAI;
		TEN_KHANG = tEN_KHANG;
		TEN_KHANG_KS = tEN_KHANG_KS;
		SO_NHA = sO_NHA;
		SO_NHA_KS = sO_NHA_KS;
		DUONG_PHO = dUONG_PHO;
		DUONG_PHO_KS = dUONG_PHO_KS;
		DIEN_THOAI = dIEN_THOAI;
		DIEN_THOAI_KS = dIEN_THOAI_KS;
		NGAY_YCAU = nGAY_YCAU;
		BB_KSAT = bB_KSAT;
		MA_GCST = mA_GCST;
		MA_GCSP = mA_GCSP;
		MA_LOAI_HSO = mA_LOAI_HSO;
		THOI_GIAN = tHOI_GIAN;
		DA_CHUYEN = dA_CHUYEN;
		GHI_CHU = gHI_CHU;
		PT_TT = pT_TT;
		PT_C = pT_C;
		PT_TL = pT_TL;
		PT_K = pT_K;
		PT_VAT = pT_VAT;
		PT_C1 = pT_C1;
		PT_NC = pT_NC;
		PT_NC1 = pT_NC1;
		TENTRAM_BIENAP = tENTRAM_BIENAP;
		MA_TRAM = mA_TRAM;
		CONG_SUAT_TRAM = cONG_SUAT_TRAM;
		DONGDIEN_DINHMUC = dONGDIEN_DINHMUC;
		TAI_MAX = tAI_MAX;
		CONGTO_TRAI = cONGTO_TRAI;
		CONGTO_PHAI = cONGTO_PHAI;
		KC_CONGTO_DEN_TRAM = kC_CONGTO_DEN_TRAM;
		KC_CONGTO_DEN_LUOI = kC_CONGTO_DEN_LUOI;
		TRU = tRU;
		CONGSUAT_THUC_TE_KW = cONGSUAT_THUC_TE_KW;
		CONGSUAT_THUC_TE_A = cONGSUAT_THUC_TE_A;
		MUC_DICH_SU_DUNG = mUC_DICH_SU_DUNG;
		MA_KHACHHANG = mA_KHACHHANG;
		CONGSUAT_DENGHI_A = cONGSUAT_DENGHI_A;
		CONGSUAT_DENGHI_V = cONGSUAT_DENGHI_V;
		CONGSUAT_DENGHI_PHA = cONGSUAT_DENGHI_PHA;
		LY_DO = lY_DO;
		TINHTRANG_CONG_TO = tINHTRANG_CONG_TO;
		TINHTRANG_CONGTO_CO = tINHTRANG_CONGTO_CO;
		TINHTRANG_TRONGAI = tINHTRANG_TRONGAI;
		VITRITREO = vITRITREO;
		LOAI_TAI = lOAI_TAI;
		MASO_STT = mASO_STT;
		DA_XONG = dA_XONG;
		SO_HO = sO_HO;
		NGAY_KS = nGAY_KS;
		Xt = xt;
		Yt = yt;
		Xp = xp;
		Yp = yp;
		THUYET_TRINH = tHUYET_TRINH;
		LOAI_XAY_DUNG = lOAI_XAY_DUNG;
		LOAI_NHA = lOAI_NHA;
		DC_GANDIEN = dC_GANDIEN;
		PHUTAI_A = pHUTAI_A;
		PHUTAI_B = pHUTAI_B;
		PHUTAI_C = pHUTAI_C;
		DV_THIETKE = dV_THIETKE;
		DV_THICONG = dV_THICONG;
		DV_CAP_VTU = dV_CAP_VTU;
		TTRANG_KO_CONGTO = tTRANG_KO_CONGTO;
	}



	public void readObj(DataInputStream din) throws IOException {
		 MA_DVIQLY=din.readUTF();
		 MA_YCAU_KNAI=din.readUTF();
		 TEN_KHANG=din.readUTF();
		 TEN_KHANG_KS=din.readUTF();
		 SO_NHA=din.readUTF();
		 SO_NHA_KS=din.readUTF();
		 DUONG_PHO=din.readUTF();
		 DUONG_PHO_KS=din.readUTF();
		 DIEN_THOAI=din.readUTF();
		 DIEN_THOAI_KS=din.readUTF();
		 NGAY_YCAU=din.readUTF();
		 BB_KSAT=din.readUTF();
		 MA_GCST=din.readUTF();
		 MA_GCSP=din.readUTF();
		 MA_LOAI_HSO=din.readUTF();
		 THOI_GIAN=din.readUTF();
		DA_CHUYEN=din.readInt();
		 GHI_CHU=din.readUTF();
		PT_TT=din.readDouble();
		PT_C=din.readDouble();
		PT_TL=din.readDouble();
		PT_K=din.readDouble();
		PT_VAT=din.readDouble();
		PT_NC=din.readDouble();
		PT_C1=din.readDouble();
		PT_NC1=din.readDouble();
		 TENTRAM_BIENAP=din.readUTF(); 
		 MA_TRAM=din.readUTF(); 
		 CONG_SUAT_TRAM=din.readUTF(); 
		 DONGDIEN_DINHMUC=din.readUTF();
		 TAI_MAX=din.readUTF(); 
		 CONGTO_TRAI=din.readUTF(); 
		 CONGTO_PHAI=din.readUTF(); 
		 KC_CONGTO_DEN_TRAM=din.readUTF();
		 KC_CONGTO_DEN_LUOI=din.readUTF(); 
		 TRU=din.readUTF(); 
		 CONGSUAT_THUC_TE_KW=din.readUTF(); 
		 CONGSUAT_THUC_TE_A=din.readUTF();
		 MUC_DICH_SU_DUNG=din.readUTF(); 
		 MA_KHACHHANG=din.readUTF(); 
		 CONGSUAT_DENGHI_A=din.readUTF(); 
		 CONGSUAT_DENGHI_V=din.readUTF();
		 CONGSUAT_DENGHI_PHA=din.readUTF(); 
		 LY_DO=din.readUTF(); 
		 TINHTRANG_CONG_TO=din.readUTF(); 
		 TINHTRANG_CONGTO_CO=din.readUTF();
		 TINHTRANG_TRONGAI=din.readUTF(); 
		 VITRITREO=din.readUTF(); 
		 LOAI_TAI=din.readUTF(); 
		 MASO_STT=din.readUTF();
		 DA_XONG=din.readInt();
		 SO_HO=din.readUTF();;
		 NGAY_KS=din.readUTF();;
		 Xt=din.readDouble();
		 Yt=din.readDouble();
		 Xp=din.readDouble();
		 Yp=din.readDouble();

		 
		 THUYET_TRINH=din.readUTF();
		 LOAI_XAY_DUNG=din.readUTF();
		 LOAI_NHA=din.readUTF();
		 DC_GANDIEN=din.readUTF();
		 PHUTAI_A=din.readUTF();
		 PHUTAI_B=din.readUTF();
		 PHUTAI_C=din.readUTF();
		 DV_THIETKE=din.readUTF();
		 DV_THICONG=din.readUTF();
		 DV_CAP_VTU=din.readUTF();
		 TTRANG_KO_CONGTO=din.readUTF();
	 
	}
	public void sendObject(DataOutputStream dos) {
		try {
			dos.writeUTF(Memory.N2S(MA_DVIQLY));
			dos.writeUTF(Memory.N2S(MA_YCAU_KNAI));
			dos.writeUTF(Memory.N2S(TEN_KHANG));
			dos.writeUTF(Memory.N2S(TEN_KHANG_KS));
			dos.writeUTF(Memory.N2S(SO_NHA));
			dos.writeUTF(Memory.N2S(SO_NHA_KS));
			dos.writeUTF(Memory.N2S(DUONG_PHO));
			dos.writeUTF(Memory.N2S(DUONG_PHO_KS));
			dos.writeUTF(Memory.N2S(DIEN_THOAI));
			dos.writeUTF(Memory.N2S(DIEN_THOAI_KS));
			dos.writeUTF(Memory.N2S(NGAY_YCAU));
			dos.writeUTF(Memory.N2S(BB_KSAT));
			dos.writeUTF(Memory.N2S(MA_GCST));
			dos.writeUTF(Memory.N2S(MA_GCSP));
			dos.writeUTF(Memory.N2S(MA_LOAI_HSO));
			dos.writeUTF(Memory.N2S(THOI_GIAN));
			dos.writeInt(DA_CHUYEN);
			dos.writeUTF(Memory.N2S(GHI_CHU));
			dos.writeDouble(PT_TT);
			dos.writeDouble(PT_C);
			dos.writeDouble(PT_TL);
			dos.writeDouble(PT_K);
			dos.writeDouble(PT_VAT);
			dos.writeDouble(PT_NC);
			dos.writeDouble(PT_C1);
			dos.writeDouble(PT_NC1);
			dos.writeUTF(Memory.N2S(TENTRAM_BIENAP));
			dos.writeUTF(Memory.N2S(MA_TRAM));
			dos.writeUTF(Memory.N2S(CONG_SUAT_TRAM));
			dos.writeUTF(Memory.N2S(DONGDIEN_DINHMUC));
			dos.writeUTF(Memory.N2S(TAI_MAX));
			dos.writeUTF(Memory.N2S(CONGTO_TRAI));
			dos.writeUTF(Memory.N2S(CONGTO_PHAI));
			dos.writeUTF(Memory.N2S(KC_CONGTO_DEN_TRAM));
			dos.writeUTF(Memory.N2S(KC_CONGTO_DEN_LUOI));
			dos.writeUTF(Memory.N2S(TRU));			
			dos.writeUTF(Memory.N2S(CONGSUAT_THUC_TE_KW));
			dos.writeUTF(Memory.N2S(CONGSUAT_THUC_TE_A));
			dos.writeUTF(Memory.N2S(MUC_DICH_SU_DUNG));		
			dos.writeUTF(Memory.N2S(MA_KHACHHANG));
			dos.writeUTF(Memory.N2S(CONGSUAT_DENGHI_A));
			dos.writeUTF(Memory.N2S(CONGSUAT_DENGHI_V));
			dos.writeUTF(Memory.N2S(CONGSUAT_DENGHI_PHA));
			dos.writeUTF(Memory.N2S(LY_DO));
			dos.writeUTF(Memory.N2S(TINHTRANG_CONG_TO));
			dos.writeUTF(Memory.N2S(TINHTRANG_CONGTO_CO));
			dos.writeUTF(Memory.N2S(TINHTRANG_TRONGAI));
			dos.writeUTF(Memory.N2S(VITRITREO));
			dos.writeUTF(Memory.N2S(LOAI_TAI));
			dos.writeUTF(Memory.N2S(MASO_STT));
			dos.writeInt(DA_XONG);
			dos.writeUTF(Memory.N2S(SO_HO));
			dos.writeUTF(Memory.N2S(NGAY_KS));
			dos.writeDouble(Xt);
			dos.writeDouble(Yt);
			dos.writeDouble(Xp);
			dos.writeDouble(Yp);

			dos.writeUTF(Memory.N2S(THUYET_TRINH));
			dos.writeUTF(Memory.N2S(LOAI_XAY_DUNG));
			dos.writeUTF(Memory.N2S(LOAI_NHA));
			dos.writeUTF(Memory.N2S(DC_GANDIEN));
			dos.writeUTF(Memory.N2S(PHUTAI_A));
			dos.writeUTF(Memory.N2S(PHUTAI_B));
			dos.writeUTF(Memory.N2S(PHUTAI_C));
			dos.writeUTF(Memory.N2S(DV_THIETKE));
			dos.writeUTF(Memory.N2S(DV_THICONG));
			dos.writeUTF(Memory.N2S(DV_CAP_VTU));
			dos.writeUTF(Memory.N2S(TTRANG_KO_CONGTO));
		} catch (Exception e) {}
	}

	public void set_obj(Cursor cur){
		MA_DVIQLY=cur.getString(cur.getColumnIndex(Utils.MA_DVIQLY));
		MA_YCAU_KNAI=cur.getString(cur.getColumnIndex(Utils.MA_YCAU_KNAI));
		TEN_KHANG=cur.getString(cur.getColumnIndex(Utils.TEN_KHANG));
		TEN_KHANG_KS=cur.getString(cur.getColumnIndex(Utils.TEN_KHANG_KS));
		SO_NHA=cur.getString(cur.getColumnIndex(Utils.SO_NHA));
		SO_NHA_KS=cur.getString(cur.getColumnIndex(Utils.SO_NHA_KS));
		DUONG_PHO=cur.getString(cur.getColumnIndex(Utils.DUONG_PHO));
		DUONG_PHO_KS=cur.getString(cur.getColumnIndex(Utils.DUONG_PHO_KS));
		DIEN_THOAI=cur.getString(cur.getColumnIndex(Utils.DIEN_THOAI));
		DIEN_THOAI_KS=cur.getString(cur.getColumnIndex(Utils.DIEN_THOAI_KS));
		NGAY_YCAU=cur.getString(cur.getColumnIndex(Utils.NGAY_YCAU));
		BB_KSAT=cur.getString(cur.getColumnIndex(Utils.BB_KSAT));
		MA_GCST=cur.getString(cur.getColumnIndex(Utils.MA_GCST));
		MA_GCSP=cur.getString(cur.getColumnIndex(Utils.MA_GCSP));
		MA_LOAI_HSO=cur.getString(cur.getColumnIndex(Utils.MA_LOAI_HSO));
		THOI_GIAN=cur.getString(cur.getColumnIndex(Utils.THOI_GIAN));
		DA_CHUYEN=cur.getInt(cur.getColumnIndex(Utils.DA_CHUYEN));
		GHI_CHU=cur.getString(cur.getColumnIndex(Utils.GHI_CHU));
		PT_TT=cur.getDouble(cur.getColumnIndex(Utils.PT_TT));
		PT_C=cur.getDouble(cur.getColumnIndex(Utils.PT_C));
		PT_TL=cur.getDouble(cur.getColumnIndex(Utils.PT_TL));
		PT_K=cur.getDouble(cur.getColumnIndex(Utils.PT_K));
		PT_VAT=cur.getDouble(cur.getColumnIndex(Utils.PT_VAT));
		PT_NC=cur.getDouble(cur.getColumnIndex(Utils.PT_NC));
		PT_C1=cur.getDouble(cur.getColumnIndex(Utils.PT_C1));
		PT_NC1=cur.getDouble(cur.getColumnIndex(Utils.PT_NC1));
		TENTRAM_BIENAP=cur.getString(cur.getColumnIndex(Utils.TENTRAM_BIENAP));
		MA_TRAM=cur.getString(cur.getColumnIndex(Utils.MA_TRAM));
		CONG_SUAT_TRAM=cur.getString(cur.getColumnIndex(Utils.CONG_SUAT_TRAM));
		DONGDIEN_DINHMUC=cur.getString(cur.getColumnIndex(Utils.DONGDIEN_DINHMUC));
		TAI_MAX=cur.getString(cur.getColumnIndex(Utils.TAI_MAX));
		CONGTO_TRAI=cur.getString(cur.getColumnIndex(Utils.CONGTO_TRAI));
		CONGTO_PHAI=cur.getString(cur.getColumnIndex(Utils.CONGTO_PHAI));
		KC_CONGTO_DEN_TRAM=cur.getString(cur.getColumnIndex(Utils.KC_CONGTO_DEN_TRAM));
		KC_CONGTO_DEN_LUOI=cur.getString(cur.getColumnIndex(Utils.KC_CONGTO_DEN_LUOI));
		TRU=cur.getString(cur.getColumnIndex(Utils.TRU));
		CONGSUAT_THUC_TE_KW=cur.getString(cur.getColumnIndex(Utils.CONGSUAT_THUC_TE_KW));
		CONGSUAT_THUC_TE_A=cur.getString(cur.getColumnIndex(Utils.CONGSUAT_THUC_TE_A));
		MUC_DICH_SU_DUNG=cur.getString(cur.getColumnIndex(Utils.MUC_DICH_SU_DUNG));
		MA_KHACHHANG=cur.getString(cur.getColumnIndex(Utils.MA_KHACHHANG));
		CONGSUAT_DENGHI_A=cur.getString(cur.getColumnIndex(Utils.CONGSUAT_DENGHI_A));
		CONGSUAT_DENGHI_V=cur.getString(cur.getColumnIndex(Utils.CONGSUAT_DENGHI_V));
		CONGSUAT_DENGHI_PHA=cur.getString(cur.getColumnIndex(Utils.CONGSUAT_DENGHI_PHA));
		LY_DO=cur.getString(cur.getColumnIndex(Utils.LY_DO));
		TINHTRANG_CONG_TO=cur.getString(cur.getColumnIndex(Utils.TINHTRANG_CONG_TO));
		TINHTRANG_CONGTO_CO=cur.getString(cur.getColumnIndex(Utils.TINHTRANG_CONGTO_CO));
		TINHTRANG_TRONGAI=cur.getString(cur.getColumnIndex(Utils.TINHTRANG_TRONGAI));
		VITRITREO=cur.getString(cur.getColumnIndex(Utils.VITRITREO));
		LOAI_TAI=cur.getString(cur.getColumnIndex(Utils.LOAI_TAI));
		MASO_STT=cur.getString(cur.getColumnIndex(Utils.MASO_STT));
		DA_XONG=cur.getInt(cur.getColumnIndex(Utils.DA_XONG));
		SO_HO=cur.getString(cur.getColumnIndex(Utils.SO_HO));
		NGAY_KS=cur.getString(cur.getColumnIndex(Utils.NGAY_KS));
		Xt=cur.getDouble(cur.getColumnIndex(Utils.Xt));
		Yt=cur.getDouble(cur.getColumnIndex(Utils.Yt));
		Xp=cur.getDouble(cur.getColumnIndex(Utils.Xp));
		Yp=cur.getDouble(cur.getColumnIndex(Utils.Yp));
		THUYET_TRINH=cur.getString(cur.getColumnIndex(Utils.THUYET_TRINH));
		LOAI_XAY_DUNG=cur.getString(cur.getColumnIndex(Utils.LOAI_XAY_DUNG));
		LOAI_NHA=cur.getString(cur.getColumnIndex(Utils.LOAI_NHA));
		DC_GANDIEN=cur.getString(cur.getColumnIndex(Utils.DC_GANDIEN));
		PHUTAI_A=cur.getString(cur.getColumnIndex(Utils.PHUTAI_A));
		PHUTAI_B=cur.getString(cur.getColumnIndex(Utils.PHUTAI_B));
		PHUTAI_C=cur.getString(cur.getColumnIndex(Utils.PHUTAI_C));
		DV_CAP_VTU=cur.getString(cur.getColumnIndex(Utils.DV_CAP_VTU));
		DV_THICONG=cur.getString(cur.getColumnIndex(Utils.DV_THICONG));
		DV_THIETKE=cur.getString(cur.getColumnIndex(Utils.DV_THIETKE));
		TTRANG_KO_CONGTO=cur.getString(cur.getColumnIndex(Utils.TTRANG_KO_CONGTO));
	}
	
	public String getTTRANG_KO_CONGTO() {
		return TTRANG_KO_CONGTO;
	}



	public void setTTRANG_KO_CONGTO(String tTRANG_KO_CONGTO) {
		TTRANG_KO_CONGTO = tTRANG_KO_CONGTO;
	}



	public String getMA_DVIQLY() {
		return MA_DVIQLY;
	}

	public void setMA_DVIQLY(String mA_DVIQLY) {
		MA_DVIQLY = mA_DVIQLY;
	}

	public String getTEN_KHANG() {
		return TEN_KHANG;
	}

	public void setTEN_KHANG(String tEN_KHANG) {
		TEN_KHANG = tEN_KHANG;
	}

	public String getTEN_KHANG_KS() {
		return TEN_KHANG_KS;
	}

	public void setTEN_KHANG_KS(String tEN_KHANG_KS) {
		TEN_KHANG_KS = tEN_KHANG_KS;
	}

	public String getSO_NHA() {
		return SO_NHA;
	}

	public void setSO_NHA(String sO_NHA) {
		SO_NHA = sO_NHA;
	}

	public String getSO_NHA_KS() {
		return SO_NHA_KS;
	}

	public void setSO_NHA_KS(String sO_NHA_KS) {
		SO_NHA_KS = sO_NHA_KS;
	}

	public String getDUONG_PHO() {
		return DUONG_PHO;
	}

	public void setDUONG_PHO(String dUONG_PHO) {
		DUONG_PHO = dUONG_PHO;
	}

	public String getDUONG_PHO_KS() {
		return DUONG_PHO_KS;
	}

	public void setDUONG_PHO_KS(String dUONG_PHO_KS) {
		DUONG_PHO_KS = dUONG_PHO_KS;
	}

	public String getDIEN_THOAI() {
		return DIEN_THOAI;
	}

	public void setDIEN_THOAI(String dIEN_THOAI) {
		DIEN_THOAI = dIEN_THOAI;
	}

	public String getDIEN_THOAI_KS() {
		return DIEN_THOAI_KS;
	}

	public void setDIEN_THOAI_KS(String dIEN_THOAI_KS) {
		DIEN_THOAI_KS = dIEN_THOAI_KS;
	}

	public String getNGAY_YCAU() {
		return NGAY_YCAU;
	}

	public void setNGAY_YCAU(String nGAY_YCAU) {
		NGAY_YCAU = nGAY_YCAU;
	}

	public String getBB_KSAT() {
		return BB_KSAT;
	}

	public void setBB_KSAT(String bB_KSAT) {
		BB_KSAT = bB_KSAT;
	}

	public String getMA_GCST() {
		return MA_GCST;
	}

	public void setMA_GCST(String mA_GCST) {
		MA_GCST = mA_GCST;
	}

	public String getMA_GCSP() {
		return MA_GCSP;
	}

	public void setMA_GCSP(String mA_GCSP) {
		MA_GCSP = mA_GCSP;
	}

	public String getMA_LOAI_HSO() {
		return MA_LOAI_HSO;
	}

	public void setMA_LOAI_HSO(String mA_LOAI_HSO) {
		MA_LOAI_HSO = mA_LOAI_HSO;
	}

	public String getTHOI_GIAN() {
		return THOI_GIAN;
	}

	public void setTHOI_GIAN(String tHOI_GIAN) {
		THOI_GIAN = tHOI_GIAN;
	}

	public int getDA_CHUYEN() {
		return DA_CHUYEN;
	}

	public void setDA_CHUYEN(int dA_CHUYEN) {
		DA_CHUYEN = dA_CHUYEN;
	}

	public String getGHI_CHU() {
		return GHI_CHU;
	}

	public void setGHI_CHU(String gHI_CHU) {
		GHI_CHU = gHI_CHU;
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

	public double getPT_C1() {
		return PT_C1;
	}

	public void setPT_C1(double pT_C1) {
		PT_C1 = pT_C1;
	}

	public double getPT_NC() {
		return PT_NC;
	}

	public void setPT_NC(double pT_NC) {
		PT_NC = pT_NC;
	}

	public double getPT_NC1() {
		return PT_NC1;
	}

	public void setPT_NC1(double pT_NC1) {
		PT_NC1 = pT_NC1;
	}

	public String getTENTRAM_BIENAP() {
		return TENTRAM_BIENAP;
	}

	public void setTENTRAM_BIENAP(String tENTRAM_BIENAP) {
		TENTRAM_BIENAP = tENTRAM_BIENAP;
	}

	public String getMA_TRAM() {
		return MA_TRAM;
	}

	public void setMA_TRAM(String mA_TRAM) {
		MA_TRAM = mA_TRAM;
	}

	public String getCONG_SUAT_TRAM() {
		return CONG_SUAT_TRAM;
	}

	public void setCONG_SUAT_TRAM(String cONG_SUAT_TRAM) {
		CONG_SUAT_TRAM = cONG_SUAT_TRAM;
	}

	public String getDONGDIEN_DINHMUC() {
		return DONGDIEN_DINHMUC;
	}

	public void setDONGDIEN_DINHMUC(String dONGDIEN_DINHMUC) {
		DONGDIEN_DINHMUC = dONGDIEN_DINHMUC;
	}

	public String getTAI_MAX() {
		return TAI_MAX;
	}

	public void setTAI_MAX(String tAI_MAX) {
		TAI_MAX = tAI_MAX;
	}

	public String getCONGTO_TRAI() {
		return CONGTO_TRAI;
	}

	public void setCONGTO_TRAI(String cONGTO_TRAI) {
		CONGTO_TRAI = cONGTO_TRAI;
	}

	public String getCONGTO_PHAI() {
		return CONGTO_PHAI;
	}

	public void setCONGTO_PHAI(String cONGTO_PHAI) {
		CONGTO_PHAI = cONGTO_PHAI;
	}

	public String getKC_CONGTO_DEN_TRAM() {
		return KC_CONGTO_DEN_TRAM;
	}

	public void setKC_CONGTO_DEN_TRAM(String kC_CONGTO_DEN_TRAM) {
		KC_CONGTO_DEN_TRAM = kC_CONGTO_DEN_TRAM;
	}

	public String getKC_CONGTO_DEN_LUOI() {
		return KC_CONGTO_DEN_LUOI;
	}

	public void setKC_CONGTO_DEN_LUOI(String kC_CONGTO_DEN_LUOI) {
		KC_CONGTO_DEN_LUOI = kC_CONGTO_DEN_LUOI;
	}

	public String getTRU() {
		return TRU;
	}

	public void setTRU(String tRU) {
		TRU = tRU;
	}

	public String getCONGSUAT_THUC_TE_KW() {
		return CONGSUAT_THUC_TE_KW;
	}

	public void setCONGSUAT_THUC_TE_KW(String cONGSUAT_THUC_TE_KW) {
		CONGSUAT_THUC_TE_KW = cONGSUAT_THUC_TE_KW;
	}

	public String getCONGSUAT_THUC_TE_A() {
		return CONGSUAT_THUC_TE_A;
	}

	public void setCONGSUAT_THUC_TE_A(String cONGSUAT_THUC_TE_A) {
		CONGSUAT_THUC_TE_A = cONGSUAT_THUC_TE_A;
	}

	public String getMUC_DICH_SU_DUNG() {
		return MUC_DICH_SU_DUNG;
	}

	public void setMUC_DICH_SU_DUNG(String mUC_DICH_SU_DUNG) {
		MUC_DICH_SU_DUNG = mUC_DICH_SU_DUNG;
	}

	public String getMA_KHACHHANG() {
		return MA_KHACHHANG;
	}

	public void setMA_KHACHHANG(String mA_KHACHHANG) {
		MA_KHACHHANG = mA_KHACHHANG;
	}

	public String getCONGSUAT_DENGHI_A() {
		return CONGSUAT_DENGHI_A;
	}

	public void setCONGSUAT_DENGHI_A(String cONGSUAT_DENGHI_A) {
		CONGSUAT_DENGHI_A = cONGSUAT_DENGHI_A;
	}

	public String getCONGSUAT_DENGHI_V() {
		return CONGSUAT_DENGHI_V;
	}

	public void setCONGSUAT_DENGHI_V(String cONGSUAT_DENGHI_V) {
		CONGSUAT_DENGHI_V = cONGSUAT_DENGHI_V;
	}

	public String getCONGSUAT_DENGHI_PHA() {
		return CONGSUAT_DENGHI_PHA;
	}

	public void setCONGSUAT_DENGHI_PHA(String cONGSUAT_DENGHI_PHA) {
		CONGSUAT_DENGHI_PHA = cONGSUAT_DENGHI_PHA;
	}

	public String getLY_DO() {
		return LY_DO;
	}

	public void setLY_DO(String lY_DO) {
		LY_DO = lY_DO;
	}

	public String getTINHTRANG_CONG_TO() {
		return TINHTRANG_CONG_TO;
	}

	public void setTINHTRANG_CONG_TO(String tINHTRANG_CONG_TO) {
		TINHTRANG_CONG_TO = tINHTRANG_CONG_TO;
	}

	public String getTINHTRANG_CONGTO_CO() {
		return TINHTRANG_CONGTO_CO;
	}

	public void setTINHTRANG_CONGTO_CO(String tINHTRANG_CONGTO_CO) {
		TINHTRANG_CONGTO_CO = tINHTRANG_CONGTO_CO;
	}

	public String getTINHTRANG_TRONGAI() {
		return TINHTRANG_TRONGAI;
	}

	public void setTINHTRANG_TRONGAI(String tINHTRANG_TRONGAI) {
		TINHTRANG_TRONGAI = tINHTRANG_TRONGAI;
	}

	public String getVITRITREO() {
		return VITRITREO;
	}

	public void setVITRITREO(String vITRITREO) {
		VITRITREO = vITRITREO;
	}

	public String getLOAI_TAI() {
		return LOAI_TAI;
	}

	public void setLOAI_TAI(String lOAI_TAI) {
		LOAI_TAI = lOAI_TAI;
	}

	public String getMASO_STT() {
		return MASO_STT;
	}

	public void setMASO_STT(String mASO_STT) {
		MASO_STT = mASO_STT;
	}

	public int getDA_XONG() {
		return DA_XONG;
	}

	public void setDA_XONG(int dA_XONG) {
		DA_XONG = dA_XONG;
	}

	public String getSO_HO() {
		return SO_HO;
	}

	public void setSO_HO(String sO_HO) {
		SO_HO = sO_HO;
	}

	public String getNGAY_KS() {
		return NGAY_KS;
	}

	public void setNGAY_KS(String nGAY_KS) {
		NGAY_KS = nGAY_KS;
	}

	public double getXt() {
		return Xt;
	}

	public void setXt(double xt) {
		Xt = xt;
	}

	public double getYt() {
		return Yt;
	}

	public void setYt(double yt) {
		Yt = yt;
	}

	public double getXp() {
		return Xp;
	}

	public void setXp(double xp) {
		Xp = xp;
	}

	public double getYp() {
		return Yp;
	}

	public void setYp(double yp) {
		Yp = yp;
	}

	public String getMA_YCAU_KNAI() {
		return MA_YCAU_KNAI;
	}

	public void setMA_YCAU_KNAI(String mA_YCAU_KNAI) {
		MA_YCAU_KNAI = mA_YCAU_KNAI;
	}

	public String getTHUYET_TRINH() {
		return THUYET_TRINH;
	}

	public void setTHUYET_TRINH(String tHUYET_TRINH) {
		THUYET_TRINH = tHUYET_TRINH;
	}

	public String getLOAI_XAY_DUNG() {
		return LOAI_XAY_DUNG;
	}

	public void setLOAI_XAY_DUNG(String lOAI_XAY_DUNG) {
		LOAI_XAY_DUNG = lOAI_XAY_DUNG;
	}

	public String getLOAI_NHA() {
		return LOAI_NHA;
	}

	public void setLOAI_NHA(String lOAI_NHA) {
		LOAI_NHA = lOAI_NHA;
	}



	public String getDC_GANDIEN() {
		return DC_GANDIEN;
	}



	public void setDC_GANDIEN(String dC_GANDIEN) {
		DC_GANDIEN = dC_GANDIEN;
	}



	public String getPHUTAI_A() {
		return PHUTAI_A;
	}



	public void setPHUTAI_A(String pHUTAI_A) {
		PHUTAI_A = pHUTAI_A;
	}



	public String getPHUTAI_B() {
		return PHUTAI_B;
	}



	public void setPHUTAI_B(String pHUTAI_B) {
		PHUTAI_B = pHUTAI_B;
	}



	public String getPHUTAI_C() {
		return PHUTAI_C;
	}

	public void setPHUTAI_C(String pHUTAI_C) {
		PHUTAI_C = pHUTAI_C;
	}

	public String getDV_THIETKE() {
		return DV_THIETKE;
	}

	public void setDV_THIETKE(String dV_THIETKE) {
		DV_THIETKE = dV_THIETKE;
	}

	public String getDV_THICONG() {
		return DV_THICONG;
	}

	public void setDV_THICONG(String dV_THICONG) {
		DV_THICONG = dV_THICONG;
	}
	public String getDV_CAP_VTU() {
		return DV_CAP_VTU;
	}
	public void setDV_CAP_VTU(String dV_CAP_VTU) {
		DV_CAP_VTU = dV_CAP_VTU;
	}
	
	
	
}