package hnks.kitsoft.utils;

import com.thtsoftlib.function.Thtcovert;
import hnks.kitsoft.vn.R;
import android.app.Activity;

public class Length_text {
	public static final int ML_TEN_KHANG_KS = 100;
	public static final int ML_DIEN_THOAI_KS = 50;
	public static final int ML_MA_TRAM = 15;
	public static final int ML_MA_GCST = 30;
	public static final int ML_MA_GCSP = 30;
	public static final int ML_GHI_CHU = 100;
	public static final int ML_TENTRAM_BIENAP = 150;
	public static final int ML_CONG_SUAT_TRAM = 10;
	public static final int ML_DONGDIEN_DINHMUC = 10;
	public static final int ML_TAI_MAX = 100;
	public static final int ML_CONGTO_TRAI = 15;
	public static final int ML_CONGTO_PHAI = 15;
	public static final int ML_KC_CONGTO_DEN_TRAM = 10;
	public static final int ML_KC_CONGTO_DEN_LUOI = 10;
	public static final int ML_TRU = 100;
	public static final int ML_CONGSUAT_THUC_TE_KW = 10;
	public static final int ML_CONGSUAT_THUC_TE_A = 10;
	public static final int ML_MUC_DICH_SU_DUNG = 50;
	public static final int ML_MA_KHACHHANG = 15;
	public static final int ML_CONGSUAT_DENGHI_A = 10;
	public static final int ML_CONGSUAT_DENGHI_V = 10;
	public static final int ML_CONGSUAT_DENGHI_PHA = 10;
	public static final int ML_LY_DO = 50;
	public static final int ML_TINHTRANG_CONG_TO = 30;
	public static final int ML_TINHTRANG_CONGTO_CO = 30;
	public static final int ML_TINHTRANG_TRONGAI = 30;
	public static final int ML_VITRITREO = 30;
	public static final int ML_LOAI_TAI = 3;
	public static final int ML_SO_NHA_KS = 100;
	public static final int ML_DUONG_PHO_KS = 100;
	public static final int ML_MASO_STT = 30;
	public static final int ML_SO_HO = 5;
	public static final int ML_THUYET_TRINH = 3000;
	public static final int ML_LOAI_XAY_DUNG = 50;
	public static final int ML_LOAI_NHA = 50;
	public static final int ML_DC_GANDIEN = 100;
	public static final int ML_PHUTAI_A = 10;
	public static final int ML_PHUTAI_B = 10;
	public static final int ML_PHUTAI_C = 10;
	public static final int ML_DV_THIETKE = 50;
	public static final int ML_DV_THICONG = 50;
	public static final int ML_DV_CAP_VTU = 50;
	public static final int ML_TTRANG_KO_CONGTO = 50;
	
	public static final int ML_TEN_DIEM = 50;
	public static final int ML_TEN_HINH = 50;
	public static final int ML_MAU = 100;
	public static final int ML_TEN = 100;
	
	public static void alert_length(Activity c,int max){
		Custom_Toast.show_yellow_toast(c, c.getString(R.string.khong_nhap_qua)+" "+Thtcovert.int_to_String(max)+" "+c.getString(R.string.ky_tu));
	}

}
