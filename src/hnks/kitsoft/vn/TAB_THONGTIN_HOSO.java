package hnks.kitsoft.vn;

import java.util.List;

import tht.library.crouton.Style;
import hnks.kitsoft.utils.Custom_Toast;
import hnks.kitsoft.utils.Variables;
import hnks.kitsoft.vn.R;
import hnks.kitsoft.vn.custom_dialog.CAPNHAT_HSO;
import hnks.kitsoft.vn.custom_dialog.Dialog_HESO;
import hnks.kitsoft.vn.custom_dialog.Dialog_listview_thuyettrinh;
import hnks.kitsoft.vn.database.DBAdapter;
import hnks.kitsoft.vn.list.Adapter_nhap_lieu;
import hnks.kitsoft.vn.list.Lst_HESO;
import hnks.kitsoft.vn.object.Obj_HSO_CHIETTINH;
import hnks.kitsoft.vn.object.Obj_MAU_HESO;
import hnks.kitsoft.vn.object.Obj_nhap_lieu;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.thtsoftlib.function.ThtShow;
import com.thtsoftlib.function.Tht_Screen;

public class TAB_THONGTIN_HOSO extends Activity implements OnClickListener{
	EditText edt_TEN_KH_KS, edt_SO_NHA_KS, edt_DUONG_PHO_KS, edt_SODT_KS,
			edtGhiChu, edt_SO_HO,edt_thuyettrinh,edt_DIACHI_GANDIEN;
	TextView tvLoaiKH, tv_NGAY_YEU_CAU;
	TextView tvLoaiPha;
	DBAdapter mdb;
	int w, h,w_tt,h_tt;
	Dialog_HESO dialog_heso;
	List<Obj_MAU_HESO> ARR_MAU_HESO;
	Lst_HESO mAdapter_HESO;
	int LENH =0;
	int SUA_GHICHU=1,SUA_SO_NHA_KS=2,SUA_DUONG_PHO_KS=3,SUA_TEN_KH_KS=4,
			SUA_SODT_KS=5,SUA_SO_HO=6,SUA_THUYET_TRINH =7,SUA_DC_GANDIEN =8;
	public static ProgressDialog progDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Tht_Screen.hide_title(this);
		setContentView(R.layout.tab_thongtin_hoso);
		Tht_Screen.hide_keyboard(this);
		w = (Tht_Screen.get_screen_width(this)/10) * 7;
		h = (Tht_Screen.get_screen_heigth(this)/10) * 6;
		w_tt = (Tht_Screen.get_screen_width(this)/10) * 9;
		h_tt = (Tht_Screen.get_screen_heigth(this)/10) * 9;
		mdb = new DBAdapter(this);
		try {
			edt_TEN_KH_KS = (EditText) findViewById(R.id.edt_TEN_KH_KS_tab_thongtin_hoso);
			edt_SO_NHA_KS = (EditText) findViewById(R.id.edt_SO_NHA_KS_tab_thongtin_hoso);
			edt_DUONG_PHO_KS = (EditText) findViewById(R.id.edt_DUONG_PHO_KS_tab_thongtin_hoso);
			edt_SODT_KS = (EditText) findViewById(R.id.edt_SO_DT_KS_tab_thongtin_hoso);
			tv_NGAY_YEU_CAU = (TextView) findViewById(R.id.tv_NGAY_YEU_CAU_tab_thong_tin_hso);		
			edt_SO_HO = (EditText) findViewById(R.id.edt_SO_HO_tab_thong_tin_hso);
			edtGhiChu = (EditText) findViewById(R.id.edtGhiChu_tab_thong_tin_hso);
			edt_DIACHI_GANDIEN = (EditText) findViewById(R.id.edt_DIACHI_GANDIEN_tab_thongtin_hoso);
			edt_thuyettrinh = (EditText) findViewById(R.id.edt_thuyettrinh_tab_thongtin);
		} catch (Exception e) {
			ThtShow.show_crouton_toast(this, "Lỗi khởi tạo giao diện", Style.ALERT);
		}
		
		try {
			KT_KHAOSAT();
			edt_TEN_KH_KS.setOnClickListener(this);
			edt_SO_NHA_KS.setOnClickListener(this);
			edt_DUONG_PHO_KS.setOnClickListener(this);
			edt_SODT_KS.setOnClickListener(this);
			edt_SO_HO.setOnClickListener(this);
			edtGhiChu.setOnClickListener(this);
			edt_DIACHI_GANDIEN.setOnClickListener(this);
			edt_thuyettrinh.setOnClickListener(this);
		} catch (Exception e) {
			
		}
		
	}

	public void KT_KHAOSAT() {
		String TEN = Variables.HSCT_CHON.TEN_KHANG;
		String TEN_KS = Variables.HSCT_CHON.TEN_KHANG_KS;
		String SO_NHA = Variables.HSCT_CHON.SO_NHA;
		String SO_NHA_KS = Variables.HSCT_CHON.SO_NHA_KS;
		String DUONG_PHO = Variables.HSCT_CHON.DUONG_PHO;
		String DUONG_PHO_KS = Variables.HSCT_CHON.DUONG_PHO_KS;
		String DIA_CHI_GAN_DIEN = Variables.HSCT_CHON.DC_GANDIEN;
		String SO_DT = Variables.HSCT_CHON.DIEN_THOAI;
		String SO_DT_KS = Variables.HSCT_CHON.DIEN_THOAI_KS;
		String SO_HO = Variables.HSCT_CHON.SO_HO;

		if (TEN_KS.length() == 0) {
			Variables.HSCT_CHON.TEN_KHANG_KS = TEN;
			mdb.update_HSO_CHIETTINH(Variables.HSCT_CHON);
		}
		if (SO_NHA_KS.length() == 0) {
			Variables.HSCT_CHON.SO_NHA_KS = SO_NHA;
			mdb.update_HSO_CHIETTINH(Variables.HSCT_CHON);
		}
		if (DUONG_PHO_KS.length() == 0) {
			Variables.HSCT_CHON.DUONG_PHO_KS = DUONG_PHO;
			mdb.update_HSO_CHIETTINH(Variables.HSCT_CHON);
		}
		if (DIA_CHI_GAN_DIEN!=null){
			if (DIA_CHI_GAN_DIEN.length() == 0) {
				try {
					Variables.HSCT_CHON.DC_GANDIEN = SO_NHA +", "+DUONG_PHO;
					mdb.update_HSO_CHIETTINH(Variables.HSCT_CHON);
				} catch (Exception e) {
					ThtShow.show_crouton_toast(this,e.toString(), Style.ALERT);
				}
				
			}
		}else{
			ThtShow.show_crouton_toast(this,"null", Style.ALERT);
		}
		
		if (SO_DT_KS.length() == 0) {
			Variables.HSCT_CHON.DIEN_THOAI_KS = SO_DT;
			mdb.update_HSO_CHIETTINH(Variables.HSCT_CHON);
		}
		tv_NGAY_YEU_CAU.setText(Variables.HSCT_CHON.NGAY_YCAU);
		edt_SO_NHA_KS.setText(Variables.HSCT_CHON.SO_NHA_KS);
		edt_DUONG_PHO_KS.setText(Variables.HSCT_CHON.DUONG_PHO_KS);
		edt_SODT_KS.setText(Variables.HSCT_CHON.DIEN_THOAI_KS);
		edtGhiChu.setText(Variables.HSCT_CHON.GHI_CHU);
		edt_TEN_KH_KS.setText(Variables.HSCT_CHON.TEN_KHANG_KS);
		edt_SO_HO.setText(SO_HO);
		edt_DIACHI_GANDIEN.setText(Variables.HSCT_CHON.getDC_GANDIEN());
		edt_thuyettrinh.setText(Variables.HSCT_CHON.getTHUYET_TRINH());
	}

	// Mo dialog nhap so luong
	public void SHOW_CUSTOM_DIALOG_HESO(View v) {
		dialog_heso = new Dialog_HESO(TAB_THONGTIN_HOSO.this);
		dialog_heso.show();
		WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
		lp.copyFrom(dialog_heso.getWindow().getAttributes());
		lp.width = Tht_Screen.get_screen_width_percent(TAB_THONGTIN_HOSO.this, 90);
		lp.height = Tht_Screen.get_screen_heigth_percent(TAB_THONGTIN_HOSO.this, 90);
		dialog_heso.getWindow().setAttributes(lp);
		ARR_MAU_HESO = mdb.get_ARR_HESO(Variables.DNV.MaDV);
		mAdapter_HESO = new Lst_HESO(ARR_MAU_HESO, TAB_THONGTIN_HOSO.this);
		Dialog_HESO.lv_HESO.setAdapter(mAdapter_HESO);
		Dialog_HESO.tv_PT_TT.setText(getString(R.string.PT_TT)+String.valueOf(Variables.HSCT_CHON.PT_TT));
		Dialog_HESO.tv_PT_C.setText(getString(R.string.PT_C)+String.valueOf(Variables.HSCT_CHON.PT_C));
		Dialog_HESO.tv_PT_TL.setText(getString(R.string.PT_TL)+String.valueOf(Variables.HSCT_CHON.PT_TL));
		Dialog_HESO.tv_PT_K.setText(getString(R.string.PT_K)+String.valueOf(Variables.HSCT_CHON.PT_K));
		Dialog_HESO.tv_PT_VAT.setText(getString(R.string.PT_VAT)+String.valueOf(Variables.HSCT_CHON.PT_VAT));
		Dialog_HESO.tv_PT_NC.setText(getString(R.string.PT_NC)+String.valueOf(Variables.HSCT_CHON.PT_NC)+" %");
		Dialog_HESO.tv_PT_C1.setText(getString(R.string.PT_C1)+String.valueOf(Variables.HSCT_CHON.PT_C1));
		Dialog_HESO.tv_PT_NC1.setText(getString(R.string.PT_NC1)+String.valueOf(Variables.HSCT_CHON.PT_NC1)+" %");
		

	}
	public void CAP_NHAT_HESO(Obj_MAU_HESO MHS){
		try {
			Variables.HSCT_CHON.PT_TT=MHS.PT_TT;
			Variables.HSCT_CHON.PT_C=MHS.PT_C;
			Variables.HSCT_CHON.PT_TL=MHS.PT_TL;
			Variables.HSCT_CHON.PT_K=MHS.PT_K;
			Variables.HSCT_CHON.PT_VAT=MHS.PT_VAT;
			Variables.HSCT_CHON.PT_NC=MHS.PT_NC;
			Variables.HSCT_CHON.PT_C1=MHS.PT_C1;
			Variables.HSCT_CHON.PT_NC1=MHS.PT_NC1;
			mdb.update_HSO_CHIETTINH(Variables.HSCT_CHON);
			dialog_heso.dismiss();
			Custom_Toast.show_blue_toast(TAB_THONGTIN_HOSO.this, "Cập nhật hệ số thành công !");
		} catch (Exception e) {
			
		}
		
	}
	public void thoat (View v){
		finish();
	}
	 public void show_dialog_sua_ten(final Obj_HSO_CHIETTINH HSCT) {
			final Dialog dialog = new Dialog(this, R.style.FullHeightDialog);
			dialog.setContentView(R.layout.dialog_dat_ten);
			dialog.setCancelable(true);
			final EditText message = (EditText) dialog
					.findViewById(R.id.edt_ten_dialog_datten);
			final TextView title = (TextView) dialog
					.findViewById(R.id.tvmessagedialogtitle);
			if (LENH==SUA_DUONG_PHO_KS){
				title.setText(getString(R.string.sua_duong_pho));
				message.setText(HSCT.getDUONG_PHO_KS());
			}else if (LENH==SUA_GHICHU){
				title.setText(getString(R.string.sua_ghi_chu));
				message.setText(HSCT.getGHI_CHU());
			}else if (LENH==SUA_SO_HO){
				title.setText(getString(R.string.sua_so_ho));
				message.setText(HSCT.getSO_HO());
			}else if (LENH==SUA_SO_NHA_KS){
				title.setText(getString(R.string.sua_so_nha));
				message.setText(HSCT.getSO_NHA_KS());
			}else if (LENH==SUA_SODT_KS){
				title.setText(getString(R.string.sua_so_dt));
				message.setText(HSCT.getDIEN_THOAI_KS());
			}else if (LENH==SUA_TEN_KH_KS){
				title.setText(getString(R.string.sua_ten_kh));
				message.setText(HSCT.getTEN_KHANG_KS());
			}else if (LENH==SUA_DC_GANDIEN){
				title.setText(getString(R.string.sua_dc_gandien));
				message.setText(HSCT.getDC_GANDIEN());
			}
			message.setSelection(message.getText().toString().length());
			Button yes = (Button) dialog.findViewById(R.id.btn_yes_dialog_datten);
			yes.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					try {
						if (LENH==SUA_DUONG_PHO_KS){
							HSCT.setDUONG_PHO_KS(message.getText().toString());
							mdb.update_HSO_CHIETTINH(HSCT);
							edt_DUONG_PHO_KS.setText(HSCT.getDUONG_PHO_KS());
						}else if (LENH==SUA_GHICHU){
							HSCT.setGHI_CHU(message.getText().toString());
							mdb.update_HSO_CHIETTINH(HSCT);
							edtGhiChu.setText(HSCT.getGHI_CHU());
						}else if (LENH==SUA_SO_HO){
							HSCT.setSO_HO(message.getText().toString());
							mdb.update_HSO_CHIETTINH(HSCT);
							edt_SO_HO.setText(HSCT.getSO_HO());
						}else if (LENH==SUA_SO_NHA_KS){
							HSCT.setSO_NHA_KS(message.getText().toString());
							mdb.update_HSO_CHIETTINH(HSCT);
							edt_SO_NHA_KS.setText(HSCT.getSO_NHA_KS());
						}else if (LENH==SUA_SODT_KS){
							HSCT.setDIEN_THOAI_KS(message.getText().toString());
							mdb.update_HSO_CHIETTINH(HSCT);
							edt_SODT_KS.setText(HSCT.getDIEN_THOAI_KS());
						}else if (LENH==SUA_TEN_KH_KS){
							HSCT.setTEN_KHANG_KS(message.getText().toString());
							mdb.update_HSO_CHIETTINH(HSCT);
							edt_TEN_KH_KS.setText(HSCT.getTEN_KHANG_KS());
						}else if (LENH==SUA_DC_GANDIEN){
							HSCT.setDC_GANDIEN(message.getText().toString());
							mdb.update_HSO_CHIETTINH(HSCT);
							edt_DIACHI_GANDIEN.setText(HSCT.getDC_GANDIEN());
						}
					} catch (Exception e) {
						
					}
					dialog.dismiss();
				}
			});
			dialog.show();
			WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
			lp.copyFrom(dialog.getWindow().getAttributes());
			lp.width = w;
			lp.height = h;
			
			dialog.getWindow().setAttributes(lp);
		}
	 public void SHOW_DIALOG_THUYET_TRINH(final Obj_HSO_CHIETTINH HSCT) {
		 	final List<Obj_nhap_lieu> list_nhap_lieu = mdb.get_list_NL(Variables.NL_NHAP_THUYET_TRINH);
		 	Adapter_nhap_lieu mAdapter_TT = new Adapter_nhap_lieu(list_nhap_lieu, TAB_THONGTIN_HOSO.this, mdb);
			final Dialog_listview_thuyettrinh dialog_GC = new Dialog_listview_thuyettrinh(TAB_THONGTIN_HOSO.this);
			dialog_GC.show();
			Dialog_listview_thuyettrinh.edt_ghichu.setText(HSCT.getTHUYET_TRINH());
			Dialog_listview_thuyettrinh.edt_ghichu.setSelection(Dialog_listview_thuyettrinh.edt_ghichu.getText().length());
			Dialog_listview_thuyettrinh.lv_ghichu.setAdapter(mAdapter_TT);
			Dialog_listview_thuyettrinh.lv_ghichu.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
						long arg3) {
					Dialog_listview_thuyettrinh.edt_ghichu.setText(list_nhap_lieu.get(arg2).nhap_lieu);
					Dialog_listview_thuyettrinh.edt_ghichu.setSelection(Dialog_listview_thuyettrinh.edt_ghichu.getText().length());
				}
			});
			Dialog_listview_thuyettrinh.btn_ok.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					if (LENH==SUA_THUYET_TRINH){
						HSCT.setTHUYET_TRINH(Dialog_listview_thuyettrinh.edt_ghichu.getText().toString());
						mdb.update_HSO_CHIETTINH(HSCT);
						edt_thuyettrinh.setText(HSCT.getTHUYET_TRINH());
					}
					Obj_nhap_lieu NL = new Obj_nhap_lieu(Dialog_listview_thuyettrinh.edt_ghichu.getText().toString(), Variables.NL_NHAP_THUYET_TRINH);
					if (mdb.chua_ton_tai_nhap_lieu(NL)){
						DBAdapter.Insert_NHAP_LIEU(NL);
					}
					dialog_GC.dismiss();
				}
			});
			WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
			lp.copyFrom(dialog_GC.getWindow().getAttributes());
			lp.width = w_tt;
			lp.height = h_tt;
			dialog_GC.getWindow().setAttributes(lp);
		}
	@Override
	public void onBackPressed() {
		Intent i = new Intent();
	    setResult(RESULT_OK, i);
		finish();
	}
	public void to_intent_cap_nhat(View v){
		try {
			Intent i = new Intent(TAB_THONGTIN_HOSO.this, CAPNHAT_HSO.class);
			startActivity(i);
		} catch (Exception e) {
		 
		}
	}

	@Override
	public void onClick(View v) {
		if (v.getId()==R.id.edtGhiChu_tab_thong_tin_hso){
			try {
				LENH=SUA_GHICHU;
				show_dialog_sua_ten(Variables.HSCT_CHON);
			} catch (Exception e) {
			 
			}
		} else if (v.getId()==R.id.edt_SO_NHA_KS_tab_thongtin_hoso){
			try {
				LENH=SUA_SO_NHA_KS;
				show_dialog_sua_ten(Variables.HSCT_CHON);
			} catch (Exception e) {
			 
			}
		} else if (v.getId()==R.id.edt_DUONG_PHO_KS_tab_thongtin_hoso){
			try {
				LENH=SUA_DUONG_PHO_KS;
				show_dialog_sua_ten(Variables.HSCT_CHON);
			} catch (Exception e) {
			 
			}
		} else if (v.getId()==R.id.edt_TEN_KH_KS_tab_thongtin_hoso){
			try {
				LENH=SUA_TEN_KH_KS;
				show_dialog_sua_ten(Variables.HSCT_CHON);
			} catch (Exception e) {
			 
			}
		} else if (v.getId()==R.id.edt_SO_DT_KS_tab_thongtin_hoso){
			try {
				LENH=SUA_SODT_KS;
				show_dialog_sua_ten(Variables.HSCT_CHON);
			} catch (Exception e) {
			 
			}
		} else if (v.getId()==R.id.edt_SO_HO_tab_thong_tin_hso){
			try {
				LENH=SUA_SO_HO;
				show_dialog_sua_ten(Variables.HSCT_CHON);
			} catch (Exception e) {
			 
			}
		} else if (v.getId()==R.id.edt_thuyettrinh_tab_thongtin){
			try {
				LENH=SUA_THUYET_TRINH;
				SHOW_DIALOG_THUYET_TRINH(Variables.HSCT_CHON);
			} catch (Exception e) {
			 
			}
		}else if (v.getId()==R.id.edt_DIACHI_GANDIEN_tab_thongtin_hoso){
			try {
				LENH=SUA_DC_GANDIEN;
				show_dialog_sua_ten(Variables.HSCT_CHON);
			} catch (Exception e) {
			 
			}
		}
		
	}
	public void chi_duong (View v){
		String Xp = String.valueOf(Variables.HSCT_CHON.Xp);
		String Yp = String.valueOf(Variables.HSCT_CHON.Yp);
		String Xt = String.valueOf(Variables.HSCT_CHON.Xt);
		String Yt = String.valueOf(Variables.HSCT_CHON.Yt);
		try {
			if (Xt.length() > 0 & Yt.length() > 0) {
				if (Double.parseDouble(Xt) == 0
						|| Double.parseDouble(Xt) == 0) {
					if (Xp.length() > 0 & Yp.length() > 0) {
						if (Double.parseDouble(Xp) == 0
								|| Double.parseDouble(Xp) == 0) {
							Toast.makeText(getApplicationContext(),
									getString(R.string.chua_co_thong_tin),
									Toast.LENGTH_LONG).show();
						} else {
							TAB_THONGTIN_HOSO.this
									.startActivity(new Intent(
											android.content.Intent.ACTION_VIEW,
											Uri.parse("geo:" + Xp + ","
													+ Yp + "?q=" + Xp + ","
													+ Yp)));
						}

					} else {
						Toast.makeText(getApplicationContext(),
								getString(R.string.chua_co_thong_tin),
								Toast.LENGTH_LONG).show();
					}
				} else {
					TAB_THONGTIN_HOSO.this.startActivity(new Intent(
							android.content.Intent.ACTION_VIEW, Uri
									.parse("geo:" + Xt + "," + Yt + "?q="
											+ Xt + "," + Yt)));
				}

			} else {
				if (Xp.length() > 0 & Yp.length() > 0) {
					if (Double.parseDouble(Xp) == 0
							|| Double.parseDouble(Xp) == 0) {
						Toast.makeText(getApplicationContext(),
								getString(R.string.chua_co_thong_tin),
								Toast.LENGTH_LONG).show();
					} else {
						TAB_THONGTIN_HOSO.this.startActivity(new Intent(
								android.content.Intent.ACTION_VIEW, Uri
										.parse("geo:" + Xp + "," + Yp
												+ "?q=" + Xp + "," + Yp)));
					}

				} else {
					Custom_Toast.show_yellow_toast(TAB_THONGTIN_HOSO.this, getString(R.string.chua_co_thong_tin));
				}

			}
		} catch (Exception e) {
			Custom_Toast.show_red_toast(TAB_THONGTIN_HOSO.this, "Lỗi truy xuất bản đồ");
		}
		
	}
	
	 
}
