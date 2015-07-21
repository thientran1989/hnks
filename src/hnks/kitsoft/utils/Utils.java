package hnks.kitsoft.utils;

import tht.library.crouton.Style;
import android.app.Activity;
import android.app.Notification.Action;
import android.content.Context;

import com.thtsoftlib.function.ThtShow;

public class Utils {
	// bien chung
	public static final int TT_CHUA_CHUYEN = 0;
	public static final int TT_DA_CHUYEN = 1;

	public static final int TT_CO = 1;
	public static final int TT_KHONGCO = 0;

	public static final int TT_CHUA_XONG = 0;
	public static final int TT_DA_XONG = 2;
//	public static final int TT_DANG_KS = -1;

	public static final int THEM_ANH = 100;
	public static final int SUA_ANH = 101;

	public static final String VAT_LIEU = "VL";
	public static final String NHAN_CONG = "NC";
	public static final String VAN_CHUYEN = "VC";
	public static final String MAY_THI_CONG = "M";

	public static final int LOAI_CP_KHACHHANG = 1;
	public static final int LOAI_CP_DIENLUC = 0;
	public static final int LOAI_CP_KHACHHANG_TULO = 2;

	// vi tri treo
	public static final String VTT_TRONGNHA = "TN";
	public static final String VTT_NGOAITRU = "NT";
	// tro ngai
	public static final String TRONGAI_CO = "TN";
	public static final String TRONGAI_KHONG = "KTN";
	// loai tai
	public static final String LOAITAI_A = "A";
	public static final String LOAITAI_B = "B";
	public static final String LOAITAI_C = "C";

	// cong to
	public static final String CT_CO = "CCT";
	public static final String CT_RIENG = "R";
	public static final String CT_CHUNG = "C";
	public static final String CT_KHONG = "KCT";

	// nhan vien
	public static final String idNhanVien = "idNhanVien";
	public static final String MaNhanVien = "MaNhanVien";
	public static final String TenNhanVien = "TenNhanVien";
	public static final String UserName = "UserName";
	public static final String MatKhau = "MatKhau";
	public static final String MaDV = "MaDV";
	public static final String IPLocal = "IPLocal";
	public static final String IPServer = "IPServer";
	// mau he so
	// public static final String MA_DVIQLY;
	public static final String id_HESO = "id_HESO";
	public static final String TEN_HESO = "TEN_HESO";
	// public static final String PT_TT;
	// public static final String PT_C;
	// public static final String PT_TL;
	// public static final String PT_K;
	// public static final String PT_VAT ;
	// public static final String PT_NC;
	// public static final String PT_C1;
	// public static final String PT_NC1;

	// ho so chiet tinh
	public static final String MA_DVIQLY = "MA_DVIQLY";
	public static final String MA_YCAU_KNAI = "MA_YCAU_KNAI";
	public static final String TEN_KHANG = "TEN_KHANG";
	public static final String TEN_KHANG_KS = "TEN_KHANG_KS";
	public static final String SO_NHA = "SO_NHA";
	public static final String SO_NHA_KS = "SO_NHA_KS";
	public static final String DUONG_PHO = "DUONG_PHO";
	public static final String DUONG_PHO_KS = "DUONG_PHO_KS";
	public static final String DIEN_THOAI = "DIEN_THOAI";
	public static final String DIEN_THOAI_KS = "DIEN_THOAI_KS";
	public static final String NGAY_YCAU = "NGAY_YCAU";
	public static final String BB_KSAT = "BB_KSAT";
	public static final String MA_GCST = "MA_GCST";
	public static final String MA_GCSP = "MA_GCSP";
	public static final String MA_LOAI_HSO = "MA_LOAI_HSO";
	public static final String THOI_GIAN = "THOI_GIAN";
	public static final String DA_CHUYEN = "DA_CHUYEN";
	public static final String GHI_CHU = "GHI_CHU";
	public static final String PT_TT = "PT_TT";
	public static final String PT_C = "PT_C";
	public static final String PT_TL = "PT_TL";
	public static final String PT_K = "PT_K";
	public static final String PT_VAT = "PT_VAT";
	public static final String PT_C1 = "PT_C1";
	public static final String PT_NC = "PT_NC";
	public static final String PT_NC1 = "PT_NC1";
	public static final String TENTRAM_BIENAP = "TENTRAM_BIENAP";
	public static final String MA_TRAM = "MA_TRAM";
	public static final String CONG_SUAT_TRAM = "CONG_SUAT_TRAM";
	public static final String DONGDIEN_DINHMUC = "DONGDIEN_DINHMUC";
	public static final String TAI_MAX = "TAI_MAX";
	public static final String CONGTO_TRAI = "CONGTO_TRAI";
	public static final String CONGTO_PHAI = "CONGTO_PHAI";
	public static final String KC_CONGTO_DEN_TRAM = "KC_CONGTO_DEN_TRAM";
	public static final String KC_CONGTO_DEN_LUOI = "KC_CONGTO_DEN_LUOI";
	public static final String TRU = "TRU";
	public static final String CONGSUAT_THUC_TE_KW = "CONGSUAT_THUC_TE_KW";
	public static final String CONGSUAT_THUC_TE_A = "CONGSUAT_THUC_TE_A";
	public static final String MUC_DICH_SU_DUNG = "MUC_DICH_SU_DUNG";
	public static final String MA_KHACHHANG = "MA_KHACHHANG";
	public static final String CONGSUAT_DENGHI_A = "CONGSUAT_DENGHI_A";
	public static final String CONGSUAT_DENGHI_V = "CONGSUAT_DENGHI_V";
	public static final String CONGSUAT_DENGHI_PHA = "CONGSUAT_DENGHI_PHA";
	public static final String LY_DO = "LY_DO";
	public static final String TINHTRANG_CONG_TO = "TINHTRANG_CONG_TO";
	public static final String TINHTRANG_CONGTO_CO = "TINHTRANG_CONGTO_CO";
	public static final String TINHTRANG_TRONGAI = "TINHTRANG_TRONGAI";
	public static final String VITRITREO = "VITRITREO";
	public static final String LOAI_TAI = "LOAI_TAI";
	public static final String MASO_STT = "MASO_STT";
	public static final String DA_XONG = "DA_XONG";
	public static final String SO_HO = "SO_HO";
	public static final String NGAY_KS = "NGAY_KS";
	public static final String Xt = "Xt";
	public static final String Yt = "Yt";
	public static final String Xp = "Xp";
	public static final String Yp = "Yp";
	public static final String THUYET_TRINH = "THUYET_TRINH";
	public static final String LOAI_XAY_DUNG = "LOAI_XAY_DUNG";
	public static final String LOAI_NHA= "LOAI_NHA";
	public static final String DC_GANDIEN="DC_GANDIEN";
	public static final String PHUTAI_A="PHUTAI_A";
	public static final String PHUTAI_B="PHUTAI_B";
	public static final String PHUTAI_C="PHUTAI_C";
	public static final String DV_THIETKE="DV_THIETKE";
	public static final String DV_THICONG="DV_THICONG";
	public static final String DV_CAP_VTU="DV_CAP_VTU";
	public static final String TTRANG_KO_CONGTO="TTRANG_KO_CONGTO";
	

	/*
	 * ho so vat tu chi tiet
	 */
	// public static final String MA_DVIQLY="MA_DVIQLY";
	public static final String MA_LOAI_TTOAN = "MA_LOAI_TTOAN";
	// public static final String MA_YCAU_KNAI="MA_YCAU_KNAI";
	// public static final String MA_VTU="MA_VTU";
	// public static final String TEN_VTU="TEN_VTU";
	// public static final String DON_GIA="DON_GIA";
	public static final String SO_LUONG = "SO_LUONG";
	// public static final String THOI_GIAN="THOI_GIAN";
	// public static final String MA_LOAI_CPHI="MA_LOAI_CPHI";
	/*
	 * nhom vat tu
	 */
	// public static final String NHOM="NHOM";
	public static final String TEN_NHOM = "TEN_NHOM";
	// public static final String MA_DVIQLY="MA_DVIQLY";

	/*
	 * danh muc vat tu
	 */
	// public static final String MA_DVIQLY;
	public static final String NHOM = "NHOM";
	public static final String MA_LOAI_CPHI = "MA_LOAI_CPHI";
	public static final String MA_VTU = "MA_VTU";
	public static final String TEN_VTU = "TEN_VTU";
	public static final String DINH_MUC_VT = "DINH_MUC_VT";
	public static final String DON_GIA = "DON_GIA";
	public static final String DVI_TINH = "DVI_TINH";
	public static final String KHU_VUC = "KHU_VUC";
	public static final String MA_DINHMUC = "MA_DINHMUC";
	/*
	 * lien ket vat tu
	 */
	// public static final String MA_VTU;
	public static final String MA_NC_LAP = "MA_NC_LAP";
	public static final String MA_NC_THAO = "MA_NC_THAO";

	/*
	 * mau vat tu
	 */
	public static final String MAU = "MAU";
	// public static final String MA_DVIQLY;
	// public static final String MA_VTU;
	// public static final String SO_LUONG;
	/*
	 * danh muc tram
	 */
	// public static final String MA_DVIQLY="";
	// public static final String MA_TRAM="";
	public static final String TEN_TRAM = "TEN_TRAM";
	public static final String DINH_DANH = "DINH_DANH";
	public static final String CSUAT_TRAM = "CSUAT_TRAM";
	public static final String MA_CAPDA_RA = "MA_CAPDA_RA";
	public static final String DONGDIEN = "DONGDIEN";
	public static final String X = "X";
	public static final String Y = "Y";
	public static final String GHICHU = "GHICHU";

	// hinh ve
	/*
	 * ho so canvas
	 */
	// public static final String MA_DVIQLY="MA_DVIQLY";
	// public static final String MA_YCAU_KNAI="MA_YCAU_KNAI";
	public static final String STT = "STT";
	public static final String TEN = "TEN";
	public static final String LENH = "LENH";
	public static final String X1 = "X1";
	public static final String Y1 = "Y1";
	public static final String X2 = "X2";
	public static final String Y2 = "Y2";
	public static final String X3 = "X3";
	public static final String Y3 = "Y3";
	public static final String HINH = "HINH";
	public static final String THUOC_TINH = "THUOC_TINH";
	public static final String CHON = "CHON";

	/*
	 * mau canvas
	 */
	// public static final String MA_DVIQLY="";
	public static final String id_MAU = "id_MAU";
	public static final String TEN_MAU = "TEN_MAU";
	// public static final String LENH="";
	// public static final String X1="";
	// public static final String Y1="";
	// public static final String X2="";
	// public static final String Y2="";
	// public static final String X3="";
	// public static final String Y3="";
	public static final String CHU = "CHU";
	// public static final String HINH="";
	// public static final String THUOC_TINH="";
	// toa do
	/*
	 * ho so toa do
	 */
	// public static final String MA_DVIQLY;
	// public static final String MA_YCAU_KNAI;
	// public static final String STT;
	public static final String TEN_DIEM = "TEN_DIEM";
	// public static final String X;
	// public static final String Y;
	// public static final String THOI_GIAN;

	// hinh anh
	/*
	 * loai hinh
	 */
	public static final String MA_LOAI_HINH = "MA_LOAI_HINH";
	public static final String TEN_LOAI_HINH = "TEN_LOAI_HINH";
	
	// nhap lieu
	public static final String  nhap_lieu="nhap_lieu";
	public static final String loai_nhap_lieu="loai_nhap_lieu";

	/*
	 * ho so hinh
	 */
	// public static final String MA_DVIQLY;
	// public static final String MA_YCAU_KNAI;
	// public static final int STT;
	public static final String TEN_HINH = "TEN_HINH";
	// public static final String HINH;
	// public static final String THOI_GIAN;
	// public static final int DA_CHUYEN;
	// public static final String MA_LOAI_HINH;

	// 445851 445361 446387 446389 446388 445754 445845 445847 445762 446908
	// 446393
	public static final int REQ_CODE = 123;
	public static void show_loi(Exception e,Activity c){
		ThtShow.show_crouton_toast(c, e.toString(), Style.ALERT);
	}
	public static String replace_special_char(String input){
		String out ="";
		try {
			out = input.replace("'", "");
		} catch (Exception e) {
			// TODO: handle exception
		}
		return out;
	}
}
