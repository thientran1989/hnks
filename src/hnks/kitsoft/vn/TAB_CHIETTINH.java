package hnks.kitsoft.vn;

import java.util.List;

import hnks.kitsoft.utils.Variables;
import hnks.kitsoft.vn.R;
import hnks.kitsoft.vn.database.DBAdapter;
import hnks.kitsoft.vn.object.Obj_Chiettinh;
import hnks.kitsoft.vn.object.Obj_HSO_CHIETTINH;
import hnks.kitsoft.vn.object.Obj_HSO_VATTU_CTIET;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.thtsoftlib.function.Tht_Screen;
import com.thtsoftlib.function.Thtcovert;

public class TAB_CHIETTINH extends Activity {
	DBAdapter mdb;
	List<Obj_HSO_VATTU_CTIET> list_vattu_chitiet;
	Obj_HSO_CHIETTINH HS=null;

	TextView tv_TIEN_VAT_LIEU, tv_TIEN_NHAN_CONG,
			tv_TIEN_VAN_CHUYEN, tv_TIEN_THI_CONG;
	TextView tv_TIEN_VAT_LIEU_KH, tv_TIEN_NHAN_CONG_KH,
			tv_TIEN_VAN_CHUYEN_KH, tv_TIEN_THI_CONG_KH;
	TextView tv_T_KH, tv_TT_KH, tv_C_KH, tv_TL_KH, tv_K_KH,
			tv_Gtt_KH, tv_VAT_KH, tv_G_KH;
	TextView tv_T, tv_TT, tv_C, tv_TL, tv_K, tv_Gtt, tv_VAT,
			tv_G;
	String null_value ="-";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Tht_Screen.hide_title(this);
		setContentView(R.layout.tab_chiettinh);
		mdb = new DBAdapter(this);
		if (HS==null){
			HS =Variables.HSCT_CHON;
		}
		list_vattu_chitiet = mdb.get_list_vattu_chitiet_khachhang(HS.getMA_YCAU_KNAI());

		tv_TIEN_VAT_LIEU = (TextView) findViewById(R.id.tv_TIEN_VAT_LIEU_tab_thongtin);
		tv_TIEN_NHAN_CONG = (TextView) findViewById(R.id.tv_TIEN_NHAN_CONG_tab_thongtin);
		tv_TIEN_VAN_CHUYEN = (TextView) findViewById(R.id.tv_TIEN_VAN_CHUYEN_tab_thongtin);
		tv_TIEN_THI_CONG = (TextView) findViewById(R.id.tv_TIEN_THI_CONG_tab_thongtin);

		tv_T = (TextView) findViewById(R.id.tv_T_tab_thongtin);
		tv_TT = (TextView) findViewById(R.id.tv_TT_tab_thongtin);
		tv_C = (TextView) findViewById(R.id.tv_C_tab_thongtin);
		tv_TL = (TextView) findViewById(R.id.tv_TL_tab_thongtin);
		tv_K = (TextView) findViewById(R.id.tv_K_tab_thongtin);
		tv_Gtt = (TextView) findViewById(R.id.tv_Gtt_tab_thongtin);
		tv_VAT = (TextView) findViewById(R.id.tv_VAT_tab_thongtin);
		tv_G = (TextView) findViewById(R.id.tv_G_tab_thongtin);

		tv_TIEN_VAT_LIEU_KH = (TextView) findViewById(R.id.tv_TIEN_VAT_LIEU_KH_tab_thongtin);
		tv_TIEN_NHAN_CONG_KH = (TextView) findViewById(R.id.tv_TIEN_NHAN_CONG_KH_tab_thongtin);
		tv_TIEN_VAN_CHUYEN_KH = (TextView) findViewById(R.id.tv_TIEN_VAN_CHUYEN_KH_tab_thongtin);
		tv_TIEN_THI_CONG_KH = (TextView) findViewById(R.id.tv_TIEN_THI_CONG_KH_tab_thongtin);

		tv_T_KH = (TextView) findViewById(R.id.tv_T_KH_tab_thongtin);
		tv_TT_KH = (TextView) findViewById(R.id.tv_TT_KH_tab_thongtin);
		tv_C_KH = (TextView) findViewById(R.id.tv_C_KH_tab_thongtin);
		tv_TL_KH = (TextView) findViewById(R.id.tv_TL_KH_tab_thongtin);
		tv_K_KH = (TextView) findViewById(R.id.tv_K_KH_tab_thongtin);
		tv_Gtt_KH = (TextView) findViewById(R.id.tv_Gtt_KH_tab_thongtin);
		tv_VAT_KH = (TextView) findViewById(R.id.tv_VAT_KH_tab_thongtin);
		tv_G_KH = (TextView) findViewById(R.id.tv_G_KH_tab_thongtin);
		try {
			tv_TIEN_VAT_LIEU.setText(null_value);
			tv_TIEN_NHAN_CONG.setText(null_value);
			tv_TIEN_VAN_CHUYEN.setText(null_value);
			tv_TIEN_THI_CONG.setText(null_value);
			tv_T.setText(null_value);
			tv_TT.setText(null_value);
			tv_C.setText(null_value);
			tv_TL.setText(null_value);
			tv_K.setText(null_value);
			tv_Gtt.setText(null_value);
			tv_VAT.setText(null_value);
			tv_G.setText(null_value);
			tv_TIEN_VAT_LIEU_KH.setText(null_value);
			tv_TIEN_NHAN_CONG_KH.setText(null_value);
			tv_TIEN_VAN_CHUYEN_KH.setText(null_value);
			tv_TIEN_THI_CONG_KH.setText(null_value);
			tv_T_KH.setText(null_value);
			tv_TT_KH.setText(null_value);
			tv_C_KH.setText(null_value);
			tv_TL_KH.setText(null_value);
			tv_K_KH.setText(null_value);
			tv_Gtt_KH.setText(null_value);
			tv_VAT_KH.setText(null_value);
			tv_G_KH.setText(null_value);
		} catch (Exception e) {
			
		}
		try {
			TINH_CHI_PHI_DL();;
			TINH_CHI_PHI_KH();
		} catch (Exception e) {
			
		}
		
	}

	private void TINH_CHI_PHI_DL() {
		Obj_Chiettinh CT = Obj_Chiettinh.TINH_CHI_PHI_DL(list_vattu_chitiet, Variables.HSCT_CHON);
		if (CT!=null){
			try {
				tv_TIEN_VAT_LIEU.setText(Thtcovert.int_format_tien((int)CT.getTIEN_VL_KH()) + " VND");
				tv_TIEN_NHAN_CONG.setText(Thtcovert.int_format_tien((int)CT.getTIEN_NC_KH()) + " VND");
				tv_TIEN_VAN_CHUYEN.setText(Thtcovert.int_format_tien((int)CT.getTIEN_VC_KH()) + " VND");
				tv_TIEN_THI_CONG.setText(Thtcovert.int_format_tien((int)CT.getTIEN_M_KH()) + " VND");

				tv_T.setText(Thtcovert.int_format_tien((int)CT.getT()) + " VND");
				//tv_TT.setText(String.valueOf(" " + String.format("%,d", TT) + " VND"));
				tv_TT.setText("-");
				tv_C.setText(Thtcovert.int_format_tien((int)CT.getC()) + " VND");
				//tv_TL.setText(String.valueOf(" " + String.format("%,d", TL) + " VND"));
				tv_TL.setText("-");
				//tv_K.setText(String.valueOf(" " + String.format("%,d", K) + " VND"));
				tv_K.setText("-");
				tv_Gtt.setText(Thtcovert.int_format_tien((int)CT.getG()) + " VND");
				//tv_VAT.setText(String.valueOf(" " + String.format("%,d", VAT) + " VND"));
				tv_VAT.setText("-");
				//tv_G.setText(String.valueOf(" " + String.format("%,d", G) + " VND"));
				tv_G.setText("-");
			} catch (Exception e) {
				
			}
		}
		
	}

	private void TINH_CHI_PHI_KH() {
		Obj_Chiettinh CT = null;
		try {
			if (Variables.HSCT_CHON.MA_DVIQLY.startsWith("PB16")){
				 CT = Obj_Chiettinh.TINH_CHI_PHI_KH_TV(list_vattu_chitiet, Variables.HSCT_CHON);
			}else {
				 CT = Obj_Chiettinh.TINH_CHI_PHI_KH(list_vattu_chitiet, Variables.HSCT_CHON);
			}
		} catch (Exception e) {
			
		}
		
		if (CT!=null){
			try {
				tv_TIEN_VAT_LIEU_KH.setText(Thtcovert.int_format_tien((int)CT.getTIEN_VL_KH()) + " VND");
				tv_TIEN_NHAN_CONG_KH.setText(Thtcovert.int_format_tien((int)CT.getTIEN_NC_KH()) + " VND");
				tv_TIEN_VAN_CHUYEN_KH.setText(Thtcovert.int_format_tien((int)CT.getTIEN_VC_KH()) + " VND");
				tv_TIEN_THI_CONG_KH.setText(Thtcovert.int_format_tien((int)CT.getTIEN_M_KH()) + " VND");

				tv_T_KH.setText(Thtcovert.int_format_tien((int)CT.getT()) + " VND");
				tv_TT_KH.setText(Thtcovert.int_format_tien((int)CT.getTT()) + " VND");
				tv_C_KH.setText(Thtcovert.int_format_tien((int)CT.getC()) + " VND");
				tv_TL_KH.setText(Thtcovert.int_format_tien((int)CT.getTL()) + " VND");
				tv_K_KH.setText(Thtcovert.int_format_tien((int)CT.getK()) + " VND");
				tv_Gtt_KH.setText(Thtcovert.int_format_tien((int)CT.getGtt())+ " VND");
				tv_VAT_KH.setText(Thtcovert.int_format_tien((int)CT.getVAT())+ " VND");
				tv_G_KH.setText(Thtcovert.int_format_tien((int)CT.getG()) + " VND");
			} catch (Exception e) {
				
			}
		}
		
	}
	public void thoat(View v){
		finish();
	}

}
