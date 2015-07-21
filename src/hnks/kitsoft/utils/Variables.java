package hnks.kitsoft.utils;

import hnks.kitsoft.vn.object.Obj_DANHMUC_TRAM;
import hnks.kitsoft.vn.object.Obj_D_NHAN_VIEN;
import hnks.kitsoft.vn.object.Obj_D_NHOM_VTU;
import hnks.kitsoft.vn.object.Obj_HSO_CHIETTINH;
import hnks.kitsoft.vn.object.Obj_HSO_HINH;
import hnks.kitsoft.vn.object.Obj_LIENKET_VATTU;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.text.NumberFormat;
import java.util.Enumeration;
import java.util.List;


public class Variables {
	
	public static Obj_HSO_CHIETTINH HSCT_CHON = null;
	public static Obj_HSO_HINH HINH_CHON = null;
	
	public static int MA_LOAI_TTOAN_CHON =0;
	public static String MA_LOAI_TTOAN_CHON_TITLE ="";
	//danh sach vat tu
//	public static List<Obj_D_VATTU> list_all_vattu=null;
//	public static List<Obj_D_VATTU> list_vattu_sd;
	public static List<Obj_LIENKET_VATTU> list_vattu_kienket;
//	public static List<Obj_HSO_VATTU_CTIET> list_vattu_da_chon;
	
	
	
	public static List<Obj_D_NHOM_VTU> list_nhom_vattu=null;
	public static List<String> list_mau_vattu=null;
	public static List<Obj_DANHMUC_TRAM> list_tram=null;
	
	// nhan vien
	public static int co_nhanvien = 0;
	public static Obj_D_NHAN_VIEN DNV = null;
	
	// lenh hinh anh
	public static int LENH_CHUP_ANH =0;
	
	// ve so do
	public static int VE_TU_DO = 0;
	public static int VE_DUONG_THANG = 1;
	public static int VE_2LINE = 2;
	public static int MOVE = 3;
	public static int VE_TEXT = 5;
	public static int VE_HINH_CHU_NHAT = 6;
	
	public static int VE_NHA = 10;
	public static int VE_LINE_DUT_NET = 11;
	public static int VE_SONG = 12;
	public static int VE_ARROW = 13;
	public static int VE_CAU = 14;
//	public static int VE_DIEM_FILL = 15;
	public static int VE_HINH = 16;
	public static int VE_HINH_Z_NGANG = 17;
	public static int VE_HINH_Z_NGANG_DUT = 18;
	public static int VE_HINH_CONGTO = 24;
	public static int VE_KDT = 25;
	public static int VE_MUI_TEN = 26;
	public static int KO_VE = 1000;
	public static int DI_CHUYEN = 100;
	public static int VE_NEN = 101;
	public static int VE_DUONG = 102;
	
	public static int VE_COT = 103;
	public static int VE_COT_NGANG = 104;
	public static int VE_HOP = 105;
	public static int VE_HOP_NGANG = 106;
	// ve tru
	public static int VE_TRU_BTLT_12_HHUU = 401;
	public static int VE_TRU_BTLT_12_SLAP = 9;
	public static int VE_TRU_BTLT_14_HHUU = 403;
	public static int VE_TRU_BTLT_14_SLAP = 404;
	public static int VE_TRU_BTLT_20_HHUU = 405;
	public static int VE_TRU_BTLT_20_SLAP = 406;
	public static int VE_TRU_BTLT_75_HHUU = 407;
	public static int VE_TRU_BTLT_75_SLAP = 408;
	public static int VE_TRU_BTLT_85_HHUU = 409;
	public static int VE_TRU_BTLT_85_SLAP = 410;
	public static int VE_TRU_BTLT_105_HHUU = 4;
	public static int VE_TRU_BTLT_105_SLAP = 7;
	public static final int VE_TRU_BTV_11_HHUU = 412;
	public static final int VE_TRU_BTV_11_SLAP = 413;
	public static final int VE_TRU_BTV_65_HHUU = 19;
	public static final int VE_TRU_BTV_65_SLAP = 27;
	public static final int VE_TRU_BTV_75_HHUU = 414;
	public static final int VE_TRU_BTV_75_SLAP = 415;
	public static final int VE_TRU_BTV_85_HHUU = 416;
	public static final int VE_TRU_BTV_85_SLAP = 417;
	public static final int VE_TRU_GO_HHUU = 418;
	public static final int VE_TRU_GO_SLAP = 419;
	public static final int VE_TRU_NHOM_HHUU = 420;
	public static final int VE_TRU_NHOM_SLAP = 421;
	// ve cong to treo tru
	public static int VE_CONGTO_TREO_NHA = 701;
	public static int VE_CONGTO_TREO_TRU_BTLT_105_HHUU = 702;
	public static int VE_CONGTO_TREO_TRU_BTLT_105_SLAP = 703;
	public static int VE_CONGTO_TREO_TRU_BTLT_12_HHUU = 704;
	public static int VE_CONGTO_TREO_TRU_BTV_65_HHUU = 705;
	public static int VE_CONGTO_TREO_TRU_BTV_65_SLAP = 706;
	//ve tram
	public static int VE_TRAM_1PHA_HHUU = 801;
	public static int VE_TRAM_1PHA_SLAP = 802;
	public static int VE_TRAM_3PHA_HHUU = 8;
	public static int VE_TRAM_3PHA_SLAP = 803;
	public static int VE_TRAM_3PHA_NEN_HHUU = 804;
	public static int VE_TRAM_3PHA_NEN_SLAP = 805;
	public static int VE_TRAM_3PHA_NHA_HHUU = 806;
	public static int VE_TRAM_3PHA_NHA_SLAP = 807;
	//thap sat
	public static int VE_THAPSAT_10_HHUU = 901;
	public static int VE_THAPSAT_10_SLAP = 902;
	public static int VE_THAPSAT_12_HHUU = 903;
	public static int VE_THAPSAT_12_SLAP = 904;
	
	// nhap lieu
	public static int NL_NHAP_LY_DO = 1;
	public static int NL_NHAP_THUYET_TRINH = 2;
	public static int NL_NHAP_MUC_DICH_SD= 3;
	public static int NL_LOAI_HO_SO= 4;
	public static int NL_TRU= 5;
	public static int NL_CANVAS= 6;
	public static int NL_TAI_MAX= 7;
	public static int NL_CONGSUAT_TT_A= 8;
	public static int NL_CONGSUAT_TT_KW= 9;
	public static int NL_CONGSUAT_DN_A= 10;
	public static int NL_CONGSUAT_DN_V= 11;
	public static int NL_CONGSUAT_DN_PHA= 12;
	public static int NL_CONG_TO= 13;
	public static int NL_MA_KH= 14;
	public static int NL_MASO_STT= 15;
	public static int NL_DD_DINHMUC= 16;
	public static int NL_VITRI_TREO= 17;
	public static int NL_VITRI= 18;
	public static int NL_LOAI_XD= 19;
	public static int NL_LOAI_NHA= 20;
	public static int NL_TEN_MAU_VATTU= 21;
	public static int NL_PHUTAI_A= 22;
	public static int NL_PHUTAI_B= 23;
	public static int NL_PHUTAI_C= 24;
	
	// tab chi phi
	public static int SUA = 1;
	public static int THEM = 2;
	// camera
	public static final int REQ_CAMERA_IMAGE = 123;
	public static final int CAPTURE_IMAGE_FULLSIZE_ACTIVITY_REQUEST_CODE = 1777; 
	// ve so do
	public static int size_text = 0;
	public static int size_character = 0;
	public static int size_troke_text = 0;
	public static int size_padding_text = 0;
	public static int size_stroke_paint = 0;
	public static int size_stroke_point = 0;
	public static int size_finger_range = 0;
	// giao dien khach hang
	public static int sl_cpdl = 0;
	public static int sl_cpkh = 0;
	public static int sl_cpkhtd = 0;
	public static int sl_hinhanh = 0;
	public static int sl_toado = 0;
	
	 public static String IPClient = "";

    public static String getLocalIpAddress() {
	    try {
	        for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
	            NetworkInterface intf = en.nextElement();
	            for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
	                InetAddress inetAddress = enumIpAddr.nextElement();
	                if (!inetAddress.isLoopbackAddress() & inetAddress instanceof Inet4Address) {
	                    return inetAddress.getHostAddress().toString();
	                }
	            }
	        }
	    } catch (SocketException ex) {}
	    return "";
	}
    public static String double_format(double d) {
    	NumberFormat fmt = NumberFormat.getInstance();
    	return fmt.format(d);
    }
    

}
