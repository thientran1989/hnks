package hnks.kitsoft.vn;

import java.util.ArrayList;
import java.util.List;

import hnks.kitsoft.utils.Custom_Toast;
import hnks.kitsoft.utils.Length_text;
import hnks.kitsoft.utils.Utils;
import hnks.kitsoft.utils.Variables;
import hnks.kitsoft.vn.R;
import hnks.kitsoft.vn.custom_dialog.Dialog_listview_ghichu;
import hnks.kitsoft.vn.database.DBAdapter;
import hnks.kitsoft.vn.list.Adapter_nhap_lieu;
import hnks.kitsoft.vn.list.LST_HSO_VATTU_SD;
import hnks.kitsoft.vn.list.Lst_HSO_VATTU_DACHON;
import hnks.kitsoft.vn.object.Obj_D_MAU;
import hnks.kitsoft.vn.object.Obj_D_NHOM_VTU;
import hnks.kitsoft.vn.object.Obj_D_VATTU;
import hnks.kitsoft.vn.object.Obj_HSO_VATTU_CTIET;
import hnks.kitsoft.vn.object.Obj_LIENKET_VATTU;
import hnks.kitsoft.vn.object.Obj_nhap_lieu;
import tht.library.crouton.Style;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.inputmethodservice.Keyboard;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.thtsoft.adddata.Tht_add_data;
import com.thtsoft.keyboard.CustomKeyboard;
import com.thtsoftlib.function.ThtShow;
import com.thtsoftlib.function.ThtTime;
import com.thtsoftlib.function.Tht_Screen;
import com.thtsoftlib.function.Thtcovert;

public class TAB_VATTU_CHIPHI extends Activity implements OnTouchListener {

	AlertDialog.Builder alertDialog;
	ListView lvVatTu, lv_bo_vattu;
	DBAdapter mdb;
	LST_HSO_VATTU_SD mAdapter_bo_vattu;
	Lst_HSO_VATTU_DACHON mAdapter_vattu_dachon;

	Spinner spn_nhom_vattu, spn_MAU_VTU;
	// spnVatTu;
	Button btnChonDanhMuc, btn_XOA_ALL;
//	View ln_nhom;
	View ln_chon_vtu;
	double so_luong_vtu = 0;
	double so_luong_vtu_lap = 0;
	// view chon vat tu
	EditText edt_sl_vattu, edt_sl_vattu_lap;
	TextView tv_ten_vattu, tv_ten_vattu_lap;
	TextView tv_title;
	EditText edt_timkiem;

	int LENH;
	int NHOM_CHON;
	int POS_VTU_CHON=-1;
	int POS_VTU_LK =-1;
	double SL, SL_THAO, SL_LAP;
	String MA_VT, MA_VT_CHON;
	String MAU_CHON;
	Obj_D_VATTU VTU_CHON = null;
	Obj_D_VATTU VTU_LK = null;
	List<Obj_D_MAU> list_mau;
	CustomKeyboard mKeyboardView;
	Keyboard mKeyboard;
	private ProgressDialog pDialog;
	CountDownTimer mcountdown;
	final int time_connnect = 40000;
	final int SUA_MAU =1;
	final int LENH_GET_VATTU =1,LENH_LUU_MAU=2,LENH_CHON_MAU=3,LENH_GET_VTU_NHOM=4;
	String TEN_MAU_LUU="";
	int SL_VTU_MAU=0;
	int SL_TONGVT=0;
	String S_KH_TULAM = " (KH tự làm)";
	String MA_DVI="";
	
	List<Obj_D_VATTU> list_all_vattu;
	List<Obj_HSO_VATTU_CTIET> list_vattu_da_chon;
	List<Obj_D_VATTU> list_vattu_sudung ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mdb = new DBAdapter(this);
		mdb.open();
		// khai bao bien
		
		Tht_Screen.hide_title(this);
		setContentView(R.layout.tab_vattu_chiphi);
		// dang ky ban phim
		mKeyboardView = new CustomKeyboard(this, R.id.keyboardview,
				R.xml.keyboard);
		mKeyboardView.registerEditText(R.id.edt_sl_vtu_tab_chiphi);
		mKeyboardView.registerEditText(R.id.edt_sl_vtu_lap_tab_chiphi);
		lvVatTu = (ListView) findViewById(R.id.lvVatTu_tab_vattu_thanhtoan_khachhang);
		lv_bo_vattu = (ListView) findViewById(R.id.lv_bovt_tab_chiphi);
		spn_nhom_vattu = (Spinner) findViewById(R.id.spn_NHOM_VTU_tab_vattu_thanhtoan_khachhang);
		spn_MAU_VTU = (Spinner) findViewById(R.id.spn_MAU_VTU_tab_vattu_thanhtoan_khachhang);
		btnChonDanhMuc = (Button) findViewById(R.id.btn_CHON_MAU_VTU_tab_vattu_thanhtoan_khachhang);
		btn_XOA_ALL = (Button) findViewById(R.id.btn_xoa_het_tab_chiphi);
		btn_XOA_ALL.setText("Xóa\nhết");
//		ln_nhom = (LinearLayout) findViewById(R.id.ln_nhom_vtu_tab_chiphi);
		ln_chon_vtu = (LinearLayout) findViewById(R.id.ln_chon_vtu_tab_chiphi);
		tv_ten_vattu = (TextView) findViewById(R.id.tv_ten_vtu_tab_chiphi);
		edt_sl_vattu = (EditText) findViewById(R.id.edt_sl_vtu_tab_chiphi);
		tv_ten_vattu_lap = (TextView) findViewById(R.id.tv_ten_vtu_lap_tab_chiphi);
		edt_sl_vattu_lap = (EditText) findViewById(R.id.edt_sl_vtu_lap_tab_chiphi);
		edt_timkiem = (EditText) findViewById(R.id.edt_timkiem_tab_chiphi);
		tv_title = (TextView)findViewById(R.id.tv_title_tab_chiphi);
		
		edt_sl_vattu.setOnTouchListener(this);
		edt_sl_vattu_lap.setOnTouchListener(this);
		
		MA_DVI = Variables.HSCT_CHON.getMA_DVIQLY();

		// lay du lieu
		new load_du_lieu(LENH_GET_VATTU).execute();
		
		spn_nhom_vattu.setOnItemSelectedListener(new OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				if (arg2==0){
					NHOM_CHON = -1;
				}else{
					NHOM_CHON = Variables.list_nhom_vattu.get(arg2).getNHOM();
				}
				new load_du_lieu(LENH_GET_VTU_NHOM).execute();				
			}

			public void onNothingSelected(AdapterView<?> arg0) {
			}

		});
		spn_MAU_VTU.setOnItemSelectedListener(new OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				if (arg2!=0){
					MAU_CHON = Variables.list_mau_vattu.get(arg2);
				}else{
					MAU_CHON ="";
				}
				
			}

			public void onNothingSelected(AdapterView<?> arg0) {
			}

		});
		edt_timkiem.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				mAdapter_bo_vattu.get_search_vattu(s.toString());
				mAdapter_bo_vattu.notifyDataSetChanged();
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			@Override
			public void afterTextChanged(Editable s) {
			}
		});
		edt_sl_vattu.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				if (VTU_CHON != null) {
					if (VTU_CHON.getMA_LOAI_CPHI().equals(Utils.VAT_LIEU)) {
						if (VTU_LK != null) {
							edt_sl_vattu_lap.setText(s.toString());
						}
					}
				}

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub

			}
		});
	}

	/*
	 * chon ca mau
	 */
	public void chon_het_mau(View v) {
		try {
			if (MAU_CHON.length()>0){
				if (mdb.get_soluong_vat_tu_cua_mau(MAU_CHON)>0){
					if (list_vattu_da_chon.size() == 0) {
						list_mau = mdb.get_ARR_VATTU_BY_MAU(MAU_CHON);
						if (list_mau!=null){
							new load_du_lieu(LENH_CHON_MAU).execute();
						}else{
							Custom_Toast.show_yellow_toast(TAB_VATTU_CHIPHI.this, getString(R.string.chon_mau_ko_chinh_xac));
						}
						
					}else{
						Custom_Toast.show_yellow_toast(TAB_VATTU_CHIPHI.this, getString(R.string.xoa_truoc_khi_chon_mau));
					}
				}else{
					Custom_Toast.show_yellow_toast(TAB_VATTU_CHIPHI.this,"Chưa có vật tư trong mẫu đang chọn");
				}
				
			}else{
				Custom_Toast.show_yellow_toast(TAB_VATTU_CHIPHI.this, getString(R.string.chon_mau_ko_chinh_xac));
			}
			

		} catch (Exception e) {
			ThtShow.show_crouton_toast(TAB_VATTU_CHIPHI.this, e.toString(),
					Style.ALERT);
		}

	}
	public void chon_mau(List<Obj_D_MAU> list_mau){
		try {
			if (list_mau!=null){
				mdb.open();
				for (Obj_D_MAU obj_D_MAU : list_mau) {
					if (mdb.ton_tai_vattu(MA_DVI, obj_D_MAU.getMA_VTU())){
						Obj_D_VATTU DVT = mdb.get_vattu(obj_D_MAU.getMA_VTU());
						Obj_HSO_VATTU_CTIET HSVTCT = new Obj_HSO_VATTU_CTIET();
						HSVTCT.setDON_GIA(DVT.getDON_GIA());
						HSVTCT.setMA_DVIQLY(DVT.getMA_DVIQLY());
						HSVTCT.setMA_LOAI_CPHI(DVT.getMA_LOAI_CPHI());
						HSVTCT.setMA_LOAI_TTOAN(Variables.MA_LOAI_TTOAN_CHON);
						HSVTCT.setMA_VTU(DVT.getMA_VTU());
						HSVTCT.setMA_YCAU_KNAI(Variables.HSCT_CHON.MA_YCAU_KNAI);
						HSVTCT.setSO_LUONG(obj_D_MAU.getSO_LUONG());
						HSVTCT.setTEN_VTU(DVT.getTEN_VTU());
						HSVTCT.setTHOI_GIAN(ThtTime.get_curent_full_date_time());
						list_vattu_da_chon.add(0, HSVTCT);
						DBAdapter.Insert_HSO_VATTU_CTIET(HSVTCT);
						SL_VTU_MAU = SL_VTU_MAU+1;
					}
				}
			}
			
		} catch (Exception e) {
			ThtShow.show_crouton_toast(TAB_VATTU_CHIPHI.this, e.toString(), Style.ALERT);
		}						
	}

	/*
	 * chon vat tu
	 */
	public void chon_vat_tu(Obj_D_VATTU DVT,int POS) {

		// kiem tra lien ket vat tu
		VTU_CHON = null;
		VTU_LK = null;
		POS_VTU_CHON =POS;
		try {
			if (!mdb.ton_tai_vattu_dachon(Variables.HSCT_CHON.MA_YCAU_KNAI,
					DVT.getMA_VTU(), Variables.MA_LOAI_TTOAN_CHON)) {
				String ma_vtu_lk = "";
				LENH = Variables.THEM;
				ln_chon_vtu.setVisibility(View.VISIBLE);
				edt_sl_vattu.setText("");
				edt_sl_vattu_lap.setText("");
				edt_sl_vattu.setEnabled(false);
				edt_sl_vattu_lap.setEnabled(false);
				VTU_CHON = DVT;
				if (VTU_CHON != null) {
					if (VTU_CHON.getMA_LOAI_CPHI().equals(Utils.VAT_LIEU)
							|| VTU_CHON.getMA_LOAI_CPHI().equals(Utils.VAN_CHUYEN)
							|| VTU_CHON.getMA_LOAI_CPHI().equals(Utils.MAY_THI_CONG)) {
						edt_sl_vattu.requestFocus();
						for (Obj_LIENKET_VATTU mLKVT : Variables.list_vattu_kienket) {
							if (mLKVT.getMA_VTU() != null) {
								if ((mLKVT.getMA_VTU())
										.equals(VTU_CHON.getMA_VTU())) {
									ma_vtu_lk = mLKVT.getMA_NC_LAP();
									break;
								}
							}
						}
						// lay vat tu lien ket
						if (!ma_vtu_lk.equals("")) {
							if (mdb.ton_tai_vattu(MA_DVI, ma_vtu_lk)) {
								try {
									VTU_LK = mdb.get_vattu(ma_vtu_lk);
								} catch (Exception e) {

								}
							}
						}

						// set gia tri edittext
						if (VTU_CHON != null) {
							tv_ten_vattu.setText(VTU_CHON.getTEN_VTU());
						}
						if (VTU_LK != null) {
							tv_ten_vattu_lap.setText(VTU_LK.getTEN_VTU());
						} else {
							tv_ten_vattu_lap
									.setText(getString(R.string.khong_co_ncong_lk));
						}
					} else if (VTU_CHON.getMA_LOAI_CPHI().equals(Utils.NHAN_CONG)) {
						edt_sl_vattu_lap.requestFocus();
						for (Obj_LIENKET_VATTU mLKVT : Variables.list_vattu_kienket) {
							if (mLKVT.getMA_NC_LAP() != null) {
								if ((mLKVT.getMA_NC_LAP()).equals(VTU_CHON
										.getMA_VTU())) {
									ma_vtu_lk = mLKVT.getMA_VTU();
									break;
								}
							}
						}
						// lay vat tu lien ket
						if (!ma_vtu_lk.equals("")) {
							if (mdb.ton_tai_vattu(MA_DVI, ma_vtu_lk)) {
								try {
									VTU_LK = mdb.get_vattu(ma_vtu_lk);
								} catch (Exception e) {

								}
							}
						}

						// set gia tri edittext
						if (VTU_CHON != null) {
							tv_ten_vattu_lap.setText(VTU_CHON.getTEN_VTU());
						}

						if (VTU_LK != null) {
							tv_ten_vattu.setText(VTU_LK.getTEN_VTU());
						} else {
							tv_ten_vattu
									.setText(getString(R.string.khong_co_vtu_lk));
						}
					}
					// kiem tra edittext
					if (VTU_CHON != null) {
						if (VTU_CHON.getMA_LOAI_CPHI().equals(Utils.VAT_LIEU)
								|| VTU_CHON.getMA_LOAI_CPHI().equals(Utils.VAN_CHUYEN)
								|| VTU_CHON.getMA_LOAI_CPHI().equals(Utils.MAY_THI_CONG)) {
							edt_sl_vattu.setEnabled(true);
						} else if (VTU_CHON.getMA_LOAI_CPHI().equals(
								Utils.NHAN_CONG)) {
							edt_sl_vattu_lap.setEnabled(true);
						}

					}
					if (VTU_LK != null) {
						if (mdb.ton_tai_vattu_dachon(Variables.HSCT_CHON.getMA_YCAU_KNAI(), VTU_LK.getMA_VTU(), Variables.MA_LOAI_TTOAN_CHON)){
							double so_luong_lk =0;
							LENH = Variables.SUA;
							for (Obj_HSO_VATTU_CTIET VTCT : list_vattu_da_chon) {
								if (VTCT.getMA_VTU()!=null){
									if (VTCT.getMA_VTU().equals(VTU_LK.getMA_VTU())){
										so_luong_lk = VTCT.getSO_LUONG();
										break;
									}
								}
							}
							if (so_luong_lk>0){
								if (VTU_LK.getMA_LOAI_CPHI().equals(Utils.VAT_LIEU)
										||VTU_LK.getMA_LOAI_CPHI().equals(Utils.VAN_CHUYEN)
										||VTU_LK.getMA_LOAI_CPHI().equals(Utils.MAY_THI_CONG)) {
									edt_sl_vattu.setEnabled(true);
									edt_sl_vattu.setText(Thtcovert.double_to_String(so_luong_lk));
								} else if (VTU_LK.getMA_LOAI_CPHI().equals(Utils.NHAN_CONG)) {
									edt_sl_vattu_lap.setEnabled(true);
									edt_sl_vattu_lap.setText(Thtcovert.double_to_String(so_luong_lk));
								}
							}
							
						}else{
							if (VTU_LK.getMA_LOAI_CPHI().equals(Utils.VAT_LIEU)
									||VTU_LK.getMA_LOAI_CPHI().equals(Utils.VAN_CHUYEN)
									||VTU_LK.getMA_LOAI_CPHI().equals(Utils.MAY_THI_CONG)) {
								edt_sl_vattu.setEnabled(true);
							} else if (VTU_LK.getMA_LOAI_CPHI().equals(Utils.NHAN_CONG)) {
								edt_sl_vattu_lap.setEnabled(true);
							}
						}
						

					}
				}
			} else {
				ln_chon_vtu.setVisibility(View.GONE);
//				ln_nhom.setVisibility(View.VISIBLE);
				if (mKeyboardView != null
						&& mKeyboardView.isCustomKeyboardVisible())
					mKeyboardView.hideCustomKeyboard();
				ThtShow.show_crouton_toast(TAB_VATTU_CHIPHI.this,
						getString(R.string.da_chon_roi), Style.ALERT);
			}
		} catch (Exception e) {
			ThtShow.show_crouton_toast(TAB_VATTU_CHIPHI.this,
					getString(R.string.loi_chon_vat_tu), Style.ALERT);
		}
		

	}

	public void sua_vat_tu(String m_MAVT_SUA) {
		Intent ireturn = new Intent();
		setResult(RESULT_OK, ireturn);
		try {
			VTU_CHON = null;
			VTU_LK = null;
			String ma_vtu_lk = "";
			LENH = Variables.SUA;
			// kiem tra vat tu sua
			if (m_MAVT_SUA != null) {
				if (mdb.ton_tai_vattu(MA_DVI, m_MAVT_SUA)) {
					VTU_CHON = mdb.get_vattu(m_MAVT_SUA);
					// het kiem tra
					ln_chon_vtu.setVisibility(View.VISIBLE);
//					ln_nhom.setVisibility(View.GONE);
					edt_sl_vattu.setText("");
					edt_sl_vattu_lap.setText("");

					// kiem tra lien ket vat tu
					if (VTU_CHON != null) {
						if (VTU_CHON.getMA_LOAI_CPHI().equals(Utils.VAT_LIEU)
								||VTU_CHON.getMA_LOAI_CPHI().equals(Utils.VAN_CHUYEN)
								||VTU_CHON.getMA_LOAI_CPHI().equals(Utils.MAY_THI_CONG)) {
							edt_sl_vattu.requestFocus();
							edt_sl_vattu.setSelection(edt_sl_vattu.getText().toString().length());
							if (VTU_CHON.getMA_LOAI_CPHI().equals(Utils.VAN_CHUYEN)
								||VTU_CHON.getMA_LOAI_CPHI().equals(Utils.MAY_THI_CONG)){
								ma_vtu_lk="";
							}else{
								for (Obj_LIENKET_VATTU mLKVT : Variables.list_vattu_kienket) {
									if (mLKVT.getMA_VTU() != null) {
										if ((mLKVT.getMA_VTU()).equals(VTU_CHON
												.getMA_VTU())) {
											ma_vtu_lk = mLKVT.getMA_NC_LAP();
											break;
										}
									}
								}
							}
							
							// lay vat tu lien ket
							if (!ma_vtu_lk.equals("")) {
								VTU_LK =null;
								if (mdb.ton_tai_vattu(MA_DVI, ma_vtu_lk)) {
									try {
										VTU_LK = mdb.get_vattu(ma_vtu_lk);
									} catch (Exception e) {

									}
								}
							}else{
								
							}

							// set gia tri edittext
							if (VTU_CHON != null) {
								tv_ten_vattu.setText(VTU_CHON.getTEN_VTU());
							}

							if (VTU_LK != null) {
								tv_ten_vattu_lap.setText(VTU_LK.getTEN_VTU());
								if (VTU_LK.getMA_LOAI_CPHI().equals(Utils.VAT_LIEU)){
									edt_sl_vattu.setEnabled(false);
								}else{
									edt_sl_vattu_lap.setEnabled(false);
								}
							} else {
								tv_ten_vattu_lap
										.setText(getString(R.string.khong_co_ncong_lk));
								edt_sl_vattu_lap.setEnabled(false);
								if (VTU_CHON.getMA_LOAI_CPHI().equals(Utils.VAT_LIEU)){
									edt_sl_vattu_lap.setEnabled(false);
								}else{
									edt_sl_vattu.setEnabled(false);
								}
							}
						} else if (VTU_CHON.getMA_LOAI_CPHI().equals(
								Utils.NHAN_CONG)) {
							edt_sl_vattu_lap.requestFocus();

							for (Obj_LIENKET_VATTU mLKVT : Variables.list_vattu_kienket) {
								if (mLKVT.getMA_NC_LAP() != null) {
									if ((mLKVT.getMA_NC_LAP()).equals(VTU_CHON
											.getMA_VTU())) {
										ma_vtu_lk = mLKVT.getMA_VTU();
										break;
									}
								}
							}
							// lay vat tu lien ket
							if (ma_vtu_lk.length()>0) {
								if (mdb.ton_tai_vattu(MA_DVI, ma_vtu_lk)) {
									try {
										VTU_LK = mdb.get_vattu(ma_vtu_lk);
									} catch (Exception e) {

									}
								}
							}

							// set gia tri edittext
							if (VTU_CHON != null) {
								tv_ten_vattu_lap.setText(VTU_CHON.getTEN_VTU());
							}

							if (VTU_LK != null) {
								tv_ten_vattu.setText(VTU_LK.getTEN_VTU());
							} else {
								tv_ten_vattu
										.setText(getString(R.string.khong_co_vtu_lk));
							}
						}
						// so luong vat tu chon
						if (VTU_CHON != null) {
							for (Obj_HSO_VATTU_CTIET VTCT : list_vattu_da_chon) {
								if (VTCT.getMA_VTU() != null) {
									if (VTCT.getMA_VTU().equals(
											VTU_CHON.getMA_VTU())) {
										if (VTU_CHON.getMA_LOAI_CPHI().equals(Utils.VAT_LIEU)
											||VTU_CHON.getMA_LOAI_CPHI().equals(Utils.VAN_CHUYEN)
											||VTU_CHON.getMA_LOAI_CPHI().equals(Utils.MAY_THI_CONG)) {
											edt_sl_vattu.setText(Thtcovert
													.double_to_String(VTCT
															.getSO_LUONG()));
										} else if (VTU_CHON.getMA_LOAI_CPHI()
												.equals(Utils.NHAN_CONG)) {
											edt_sl_vattu_lap.setText(Thtcovert
													.double_to_String(VTCT
															.getSO_LUONG()));
										}
										break;
									}
								}
							}
						}

						// so luong vat tu lien ket
						if (VTU_LK != null) {
							for (Obj_HSO_VATTU_CTIET VTCT : list_vattu_da_chon) {
								if (VTCT.getMA_VTU() != null) {
									if (VTCT.getMA_VTU().equals(VTU_LK.getMA_VTU())) {
										if (VTU_LK.getMA_LOAI_CPHI().equals(
												Utils.VAT_LIEU)) {
											edt_sl_vattu.setText(Thtcovert
													.double_to_String(VTCT
															.getSO_LUONG()));
										} else if (VTU_LK.getMA_LOAI_CPHI().equals(
												Utils.NHAN_CONG)) {
											edt_sl_vattu_lap.setText(Thtcovert
													.double_to_String(VTCT
															.getSO_LUONG()));
										}
										break;
									}
								}
							}
						}
						// kiem tra edittext
						if (VTU_CHON != null) {
							if (VTU_CHON.getMA_LOAI_CPHI().equals(Utils.VAT_LIEU)
									||VTU_CHON.getMA_LOAI_CPHI().equals(Utils.VAN_CHUYEN)
									||VTU_CHON.getMA_LOAI_CPHI().equals(Utils.MAY_THI_CONG)) {
								edt_sl_vattu.setEnabled(true);
								edt_sl_vattu.setSelection(edt_sl_vattu.length());
							} else if (VTU_CHON.getMA_LOAI_CPHI().equals(
									Utils.NHAN_CONG)) {
								edt_sl_vattu_lap.setEnabled(true);
								edt_sl_vattu_lap
										.setSelection(edt_sl_vattu_lap.length());
							}

						}
						if (VTU_LK != null) {
							if (VTU_LK.getMA_LOAI_CPHI().equals(Utils.VAT_LIEU)) {
								edt_sl_vattu.setEnabled(true);
								edt_sl_vattu.setSelection(edt_sl_vattu.length());
							} else if (VTU_LK.getMA_LOAI_CPHI().equals(
									Utils.NHAN_CONG)) {
								edt_sl_vattu_lap.setEnabled(true);
								edt_sl_vattu_lap
										.setSelection(edt_sl_vattu.length());
							}

						}

					}
				}

			}
		} catch (Exception e) {
			ThtShow.show_crouton_toast(TAB_VATTU_CHIPHI.this,
					getString(R.string.loi_sua_vat_tu), Style.ALERT);
		}
		
	}

	public void them_vattu(View v) {
		Intent ireturn = new Intent();
		setResult(RESULT_OK, ireturn);
		so_luong_vtu = 0;
		so_luong_vtu_lap = 0;
		try {
			if (edt_sl_vattu.length() > 0) {
				so_luong_vtu = Thtcovert.String_to_double(edt_sl_vattu
						.getText().toString());
			} else {
				so_luong_vtu = 0;
			}

		} catch (Exception e) {

		}
		try {
			if (edt_sl_vattu_lap.length() > 0) {
				so_luong_vtu_lap = Thtcovert.String_to_double(edt_sl_vattu_lap
						.getText().toString());
			} else {
				so_luong_vtu_lap = 0;
			}
		} catch (Exception e) {

		}
		// het
		// neu them vat tu
		if (LENH == Variables.THEM) {
			try {
				if (mdb.ton_tai_vattu_dachon(Variables.HSCT_CHON.MA_YCAU_KNAI,
						VTU_CHON.getMA_VTU(), Variables.MA_LOAI_TTOAN_CHON)) {
					ThtShow.show_crouton_toast(TAB_VATTU_CHIPHI.this,
							getString(R.string.da_chon_roi), Style.ALERT);
				} else {
					// chon vat tu
					if (VTU_CHON != null) {
						Obj_HSO_VATTU_CTIET HSVTCT = new Obj_HSO_VATTU_CTIET();
						HSVTCT.setDON_GIA(VTU_CHON.getDON_GIA());
						HSVTCT.setMA_DVIQLY(MA_DVI);
						HSVTCT.setMA_LOAI_CPHI(VTU_CHON.getMA_LOAI_CPHI());
						HSVTCT.setMA_LOAI_TTOAN(Variables.MA_LOAI_TTOAN_CHON);
						HSVTCT.setMA_VTU(VTU_CHON.getMA_VTU());
						HSVTCT.setMA_YCAU_KNAI(Variables.HSCT_CHON.MA_YCAU_KNAI);
						if (VTU_CHON.getMA_LOAI_CPHI().equals(Utils.VAT_LIEU)
								||VTU_CHON.getMA_LOAI_CPHI().equals(Utils.VAN_CHUYEN)
								||VTU_CHON.getMA_LOAI_CPHI().equals(Utils.MAY_THI_CONG)) {
							HSVTCT.setSO_LUONG(so_luong_vtu);
						} else if (VTU_CHON.getMA_LOAI_CPHI().equals(
								Utils.NHAN_CONG)) {
							HSVTCT.setSO_LUONG(so_luong_vtu_lap);
						}
						HSVTCT.setTEN_VTU(VTU_CHON.getTEN_VTU());
						HSVTCT.setTHOI_GIAN(ThtTime.get_curent_full_date_time());
						if (VTU_CHON.getMA_LOAI_CPHI().equals(Utils.VAT_LIEU)
								||VTU_CHON.getMA_LOAI_CPHI().equals(Utils.VAN_CHUYEN)
								||VTU_CHON.getMA_LOAI_CPHI().equals(Utils.MAY_THI_CONG)) {
							if (so_luong_vtu > 0) {
								DBAdapter.Insert_HSO_VATTU_CTIET(HSVTCT);
								list_vattu_da_chon.add(0, HSVTCT);
								mAdapter_vattu_dachon.notifyDataSetChanged();
								refesh_khi_them_vattu();
							}
						} else if (VTU_CHON.getMA_LOAI_CPHI().equals(
								Utils.NHAN_CONG)) {
							if (so_luong_vtu_lap > 0) {
								DBAdapter.Insert_HSO_VATTU_CTIET(HSVTCT);
								list_vattu_da_chon.add(0, HSVTCT);
								mAdapter_vattu_dachon.notifyDataSetChanged();
								refesh_khi_them_vattu();
							}else{
								// neu so luong =0
								if (Variables.MA_LOAI_TTOAN_CHON==Utils.LOAI_CP_KHACHHANG_TULO){
									HSVTCT.setTEN_VTU(HSVTCT.getTEN_VTU()+S_KH_TULAM);
									DBAdapter.Insert_HSO_VATTU_CTIET(HSVTCT);
									list_vattu_da_chon.add(0, HSVTCT);
									mAdapter_vattu_dachon.notifyDataSetChanged();
									refesh_khi_them_vattu();
								}
							}
						}

					}
					// chon lien ket
					if (VTU_LK != null) {
						Obj_HSO_VATTU_CTIET HSVTCT = new Obj_HSO_VATTU_CTIET();
						HSVTCT.setDON_GIA(VTU_LK.getDON_GIA());
						HSVTCT.setMA_DVIQLY(VTU_LK.getMA_DVIQLY());
						HSVTCT.setMA_LOAI_CPHI(VTU_LK.getMA_LOAI_CPHI());
						HSVTCT.setMA_LOAI_TTOAN(Variables.MA_LOAI_TTOAN_CHON);
						HSVTCT.setMA_VTU(VTU_LK.getMA_VTU());
						HSVTCT.setMA_YCAU_KNAI(Variables.HSCT_CHON.MA_YCAU_KNAI);
						if (VTU_LK.getMA_LOAI_CPHI().equals(Utils.VAT_LIEU)) {
							HSVTCT.setSO_LUONG(so_luong_vtu);
						} else if (VTU_LK.getMA_LOAI_CPHI().equals(
								Utils.NHAN_CONG)) {
							HSVTCT.setSO_LUONG(so_luong_vtu_lap);
						}
						HSVTCT.setTEN_VTU(VTU_LK.getTEN_VTU());
						HSVTCT.setTHOI_GIAN(ThtTime.get_curent_full_date_time());
						if (VTU_LK.getMA_LOAI_CPHI().equals(Utils.VAT_LIEU)) {
							if (so_luong_vtu > 0) {
								DBAdapter.Insert_HSO_VATTU_CTIET(HSVTCT);
								list_vattu_da_chon.add(0, HSVTCT);
								mAdapter_vattu_dachon.notifyDataSetChanged();
							}
						} else if (VTU_LK.getMA_LOAI_CPHI().equals(
								Utils.NHAN_CONG)) {
							if (so_luong_vtu_lap > 0) {
								DBAdapter.Insert_HSO_VATTU_CTIET(HSVTCT);
								list_vattu_da_chon.add(0, HSVTCT);
								mAdapter_vattu_dachon.notifyDataSetChanged();
								refesh_khi_them_vattu();
							}else{
								// neu so luong =0
								if (Variables.MA_LOAI_TTOAN_CHON==Utils.LOAI_CP_KHACHHANG_TULO){
									HSVTCT.setTEN_VTU(HSVTCT.getTEN_VTU()+S_KH_TULAM);
									DBAdapter.Insert_HSO_VATTU_CTIET(HSVTCT);
									list_vattu_da_chon.add(0, HSVTCT);
									mAdapter_vattu_dachon.notifyDataSetChanged();
									refesh_khi_them_vattu();
								}
							}
						}
					}
					ln_chon_vtu.setVisibility(View.GONE);
					if (mKeyboardView != null
							&& mKeyboardView.isCustomKeyboardVisible())
						mKeyboardView.hideCustomKeyboard();
//					them_thanh_cong
					refesh_tieude();
				}
			} catch (Exception e) {
				ThtShow.show_crouton_toast(TAB_VATTU_CHIPHI.this, getString(R.string.them_ko_thanh_cong),
						Style.ALERT);
			}
			// neu sua vat tu
		} else if (LENH == Variables.SUA) {
			try {
				
				if (VTU_CHON != null) {
					if (mdb.ton_tai_vattu_dachon(Variables.HSCT_CHON.getMA_YCAU_KNAI(), VTU_CHON.getMA_VTU(), Variables.MA_LOAI_TTOAN_CHON)){
						for (Obj_HSO_VATTU_CTIET VTCT : list_vattu_da_chon) {
							if (VTCT.getMA_VTU() != null) {
								if (VTCT.getMA_VTU().equals(VTU_CHON.getMA_VTU())) {
									if (VTU_CHON.getMA_LOAI_CPHI().equals(Utils.VAT_LIEU)
											||VTU_CHON.getMA_LOAI_CPHI().equals(Utils.VAN_CHUYEN)
											||VTU_CHON.getMA_LOAI_CPHI().equals(Utils.MAY_THI_CONG)) {
										if (so_luong_vtu > 0) {
											VTCT.setSO_LUONG(so_luong_vtu);
											mdb.update_HSO_VATTU_CTIET(VTCT);
										}else{
												mdb.delete_VTU_CHON(VTCT);
												list_vattu_da_chon.remove(VTCT);
												mAdapter_vattu_dachon.notifyDataSetChanged();
												refesh_khi_xoa_vattu();
											
										}
									} else if (VTU_CHON.getMA_LOAI_CPHI().equals(
											Utils.NHAN_CONG)) {
										if (so_luong_vtu_lap > 0) {
											VTCT.setSO_LUONG(so_luong_vtu_lap);
											VTCT.setTEN_VTU(VTCT.getTEN_VTU().replace(S_KH_TULAM, ""));
											mdb.update_HSO_VATTU_CTIET(VTCT);
										} else{
											if (Variables.MA_LOAI_TTOAN_CHON==Utils.LOAI_CP_KHACHHANG_TULO){
												VTCT.setTEN_VTU(VTCT.getTEN_VTU().replace(S_KH_TULAM, "")+S_KH_TULAM);
												VTCT.setSO_LUONG(so_luong_vtu_lap);
												mdb.update_HSO_VATTU_CTIET(VTCT);
											}else{
												mdb.delete_VTU_CHON(VTCT);
												list_vattu_da_chon.remove(VTCT);
												mAdapter_vattu_dachon.notifyDataSetChanged();
												refesh_khi_xoa_vattu();
											}
											
										}

									}
									break;
								}
							}
						}
						// neu chua chon
					}else{
						Obj_HSO_VATTU_CTIET HSVTCT = new Obj_HSO_VATTU_CTIET();
						HSVTCT.setDON_GIA(VTU_CHON.getDON_GIA());
						HSVTCT.setMA_DVIQLY(MA_DVI);
						HSVTCT.setMA_LOAI_CPHI(VTU_CHON.getMA_LOAI_CPHI());
						HSVTCT.setMA_LOAI_TTOAN(Variables.MA_LOAI_TTOAN_CHON);
						HSVTCT.setMA_VTU(VTU_CHON.getMA_VTU());
						HSVTCT.setMA_YCAU_KNAI(Variables.HSCT_CHON.MA_YCAU_KNAI);
						if (VTU_CHON.getMA_LOAI_CPHI().equals(Utils.VAT_LIEU)
								||VTU_CHON.getMA_LOAI_CPHI().equals(Utils.VAN_CHUYEN)
								||VTU_CHON.getMA_LOAI_CPHI().equals(Utils.MAY_THI_CONG)) {
							HSVTCT.setSO_LUONG(so_luong_vtu);
						} else if (VTU_CHON.getMA_LOAI_CPHI().equals(
								Utils.NHAN_CONG)) {
							HSVTCT.setSO_LUONG(so_luong_vtu_lap);
						}
						HSVTCT.setTEN_VTU(VTU_CHON.getTEN_VTU());
						HSVTCT.setTHOI_GIAN(ThtTime.get_curent_full_date_time());
						if (VTU_CHON.getMA_LOAI_CPHI().equals(Utils.VAT_LIEU)
								||VTU_CHON.getMA_LOAI_CPHI().equals(Utils.VAN_CHUYEN)
								||VTU_CHON.getMA_LOAI_CPHI().equals(Utils.MAY_THI_CONG)) {
							if (so_luong_vtu > 0) {
								DBAdapter.Insert_HSO_VATTU_CTIET(HSVTCT);
								list_vattu_da_chon.add(0, HSVTCT);
								mAdapter_vattu_dachon.notifyDataSetChanged();
								refesh_khi_them_vattu();
							}
						} else if (VTU_CHON.getMA_LOAI_CPHI().equals(
								Utils.NHAN_CONG)) {
							if (so_luong_vtu_lap > 0) {
								DBAdapter.Insert_HSO_VATTU_CTIET(HSVTCT);
								list_vattu_da_chon.add(0, HSVTCT);
								mAdapter_vattu_dachon.notifyDataSetChanged();
								refesh_khi_them_vattu();
							}else{
								// so luong nhan cong =0
								if (Variables.MA_LOAI_TTOAN_CHON==Utils.LOAI_CP_KHACHHANG_TULO){
									HSVTCT.setTEN_VTU(HSVTCT.getTEN_VTU()+S_KH_TULAM);
									DBAdapter.Insert_HSO_VATTU_CTIET(HSVTCT);
									list_vattu_da_chon.add(0, HSVTCT);
									mAdapter_vattu_dachon.notifyDataSetChanged();
									refesh_khi_them_vattu();
								}
							}
						}
					}
					
				}
				// vat tu lien ket
				if (VTU_LK != null) {
					if (mdb.ton_tai_vattu_dachon(Variables.HSCT_CHON.getMA_YCAU_KNAI(), VTU_LK.getMA_VTU(), Variables.MA_LOAI_TTOAN_CHON)){
						for (Obj_HSO_VATTU_CTIET VTCT : list_vattu_da_chon) {
							if (VTCT.getMA_VTU() != null) {
								if (VTCT.getMA_VTU().equals(VTU_LK.getMA_VTU())) {
									if (VTU_LK.getMA_LOAI_CPHI().equals(
											Utils.VAT_LIEU)) {
										if (so_luong_vtu > 0) {
											VTCT.setSO_LUONG(so_luong_vtu);
											mdb.update_HSO_VATTU_CTIET(VTCT);
										}else{
											mdb.delete_VTU_CHON(VTCT);
											list_vattu_da_chon.remove(VTCT);
											mAdapter_vattu_dachon.notifyDataSetChanged();
											refesh_khi_xoa_vattu();
										}
									} else if (VTU_LK.getMA_LOAI_CPHI().equals(
											Utils.NHAN_CONG)) {
										if (so_luong_vtu_lap > 0) {
											VTCT.setSO_LUONG(so_luong_vtu_lap);
											VTCT.setTEN_VTU(VTCT.getTEN_VTU().replace(S_KH_TULAM, ""));
											mdb.update_HSO_VATTU_CTIET(VTCT);
										}else{
											if (Variables.MA_LOAI_TTOAN_CHON==Utils.LOAI_CP_KHACHHANG_TULO){
												VTCT.setSO_LUONG(so_luong_vtu_lap);
												mdb.update_HSO_VATTU_CTIET(VTCT);
												mAdapter_vattu_dachon.notifyDataSetChanged();
											}else{
												mdb.delete_VTU_CHON(VTCT);
												list_vattu_da_chon.remove(VTCT);
												mAdapter_vattu_dachon.notifyDataSetChanged();
												refesh_khi_xoa_vattu();
											}
											
										}
									}
									break;
								}
							}
						}
						// neu chua chon
					}else{
						Obj_HSO_VATTU_CTIET HSVTCT = new Obj_HSO_VATTU_CTIET();
						HSVTCT.setDON_GIA(VTU_LK.getDON_GIA());
						HSVTCT.setMA_DVIQLY(VTU_LK.getMA_DVIQLY());
						HSVTCT.setMA_LOAI_CPHI(VTU_LK.getMA_LOAI_CPHI());
						HSVTCT.setMA_LOAI_TTOAN(Variables.MA_LOAI_TTOAN_CHON);
						HSVTCT.setMA_VTU(VTU_LK.getMA_VTU());
						HSVTCT.setMA_YCAU_KNAI(Variables.HSCT_CHON.MA_YCAU_KNAI);
						if (VTU_LK.getMA_LOAI_CPHI().equals(Utils.VAT_LIEU)) {
							HSVTCT.setSO_LUONG(so_luong_vtu);
						} else if (VTU_LK.getMA_LOAI_CPHI().equals(
								Utils.NHAN_CONG)) {
							HSVTCT.setSO_LUONG(so_luong_vtu_lap);
						}
						HSVTCT.setTEN_VTU(VTU_LK.getTEN_VTU());
						HSVTCT.setTHOI_GIAN(ThtTime.get_curent_full_date_time());
						if (VTU_LK.getMA_LOAI_CPHI().equals(Utils.VAT_LIEU)) {
							if (so_luong_vtu > 0) {
								DBAdapter.Insert_HSO_VATTU_CTIET(HSVTCT);
								list_vattu_da_chon.add(0, HSVTCT);
								mAdapter_vattu_dachon.notifyDataSetChanged();
								refesh_khi_them_vattu();
							}
						} else if (VTU_LK.getMA_LOAI_CPHI().equals(
								Utils.NHAN_CONG)) {
							if (so_luong_vtu_lap > 0) {
								DBAdapter.Insert_HSO_VATTU_CTIET(HSVTCT);
								list_vattu_da_chon.add(0, HSVTCT);
								mAdapter_vattu_dachon.notifyDataSetChanged();
								refesh_khi_them_vattu();
							}else{
								if (Variables.MA_LOAI_TTOAN_CHON==Utils.LOAI_CP_KHACHHANG_TULO){
									HSVTCT.setTEN_VTU(HSVTCT.getTEN_VTU()+S_KH_TULAM);
									DBAdapter.Insert_HSO_VATTU_CTIET(HSVTCT);
									list_vattu_da_chon.add(0, HSVTCT);
									mAdapter_vattu_dachon.notifyDataSetChanged();
									refesh_khi_them_vattu();
								}
							}
						}
					}
					
				}
				ln_chon_vtu.setVisibility(View.GONE);
				if (mKeyboardView != null
						&& mKeyboardView.isCustomKeyboardVisible())
					mKeyboardView.hideCustomKeyboard();
				mAdapter_vattu_dachon.notifyDataSetChanged();
//				sua_thanh_cong
			} catch (Exception e) {
				ThtShow.show_crouton_toast(TAB_VATTU_CHIPHI.this,
						getString(R.string.sua_ko_thanh_cong), Style.ALERT);
			}

		}

	}

	/*
	 * xoa vat tu
	 */
	public void xoa_vat_tu(Obj_HSO_VATTU_CTIET HSVTCT) {
		Intent ireturn = new Intent();
		setResult(RESULT_OK, ireturn);
		VTU_CHON = null;
		VTU_LK = null;
		String ma_vtu_lk = "";
		String m_MAVT_XOA = null;
		try {
			m_MAVT_XOA = HSVTCT.getMA_VTU();
			if (m_MAVT_XOA != null) {
				if (mdb.ton_tai_vattu(MA_DVI, m_MAVT_XOA)) {
					VTU_CHON = mdb.get_vattu(m_MAVT_XOA);
					// kiem tra lien ket vat tu
					if (VTU_CHON != null) {
						if (VTU_CHON.getMA_LOAI_CPHI().equals(Utils.VAT_LIEU)) {
							for (Obj_LIENKET_VATTU mLKVT : Variables.list_vattu_kienket) {
								if (mLKVT.getMA_VTU() != null) {
									if ((mLKVT.getMA_VTU()).equals(VTU_CHON
											.getMA_VTU())) {
										ma_vtu_lk = mLKVT.getMA_NC_LAP();
										break;
									}
								}
							}
							// lay vat tu lien ket
							if (!ma_vtu_lk.equals("")) {
								if (mdb.ton_tai_vattu(MA_DVI, ma_vtu_lk)) {
									try {
										VTU_LK = mdb.get_vattu(ma_vtu_lk);
									} catch (Exception e) {

									}
								}
							}
						} else if (VTU_CHON.getMA_LOAI_CPHI().equals(
								Utils.NHAN_CONG)) {
							for (Obj_LIENKET_VATTU mLKVT : Variables.list_vattu_kienket) {
								if (mLKVT.getMA_NC_LAP() != null) {
									if ((mLKVT.getMA_NC_LAP()).equals(VTU_CHON
											.getMA_VTU())) {
										ma_vtu_lk = mLKVT.getMA_VTU();
										break;
									}
								}
							}
							// lay vat tu lien ket
							if (ma_vtu_lk.length()>0) {
								if (mdb.ton_tai_vattu(MA_DVI, ma_vtu_lk)) {
									try {
										VTU_LK = mdb.get_vattu(ma_vtu_lk);
									} catch (Exception e) {

									}
								}
							}
						}
						if (VTU_CHON != null) {
							try {
								mdb.delete_VTU_CHON(HSVTCT);
								list_vattu_da_chon.remove(HSVTCT);
								mAdapter_vattu_dachon.notifyDataSetChanged();
								refesh_khi_xoa_vattu();
							} catch (Exception e) {
								ThtShow.show_crouton_toast(TAB_VATTU_CHIPHI.this,
										e.toString(), Style.ALERT);
							}
						}
						if (VTU_LK != null) {
							for (Obj_HSO_VATTU_CTIET HSCT : list_vattu_da_chon) {
								if (HSCT.getMA_VTU()!=null & VTU_LK.getMA_VTU() !=null){
									if (HSCT.getMA_VTU().equals(VTU_LK.getMA_VTU())) {
										try {
											mdb.delete_VTU_CHON(HSCT);
											list_vattu_da_chon.remove(HSCT);
											mAdapter_vattu_dachon.notifyDataSetChanged();
											refesh_khi_xoa_vattu();
											break;
										} catch (Exception e) {
											ThtShow.show_crouton_toast(
													TAB_VATTU_CHIPHI.this,
													e.toString(), Style.ALERT);
										}
									}
								}
								
							}
						}
//						xoa_thanh_cong)
						refesh_tieude();
						
					}
				}

			}
			
		} catch (Exception e) {
			ThtShow.show_crouton_toast(TAB_VATTU_CHIPHI.this, getString(R.string.xoa_ko_thanh_cong), Style.ALERT);
		}
		
		
	}

	/*
	 * xoa het vat tu
	 */
	public void xoa_het_vat_tu(View v) {
		final Dialog dialog = new Dialog(this, R.style.FullHeightDialog);
		dialog.setContentView(R.layout.dialog_yes_no);
		dialog.setCancelable(true);
		TextView message = (TextView) dialog
				.findViewById(R.id.tvmessagedialogtext);
		message.setText(getString(R.string.xoa_tat_ca_vattu));
		Button yes = (Button) dialog.findViewById(R.id.bmessageDialogYes);
		yes.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				try {
					mdb.open();
					mdb.delete_VTU_BY_MA_YCAU_KNAI(
							Variables.HSCT_CHON.getMA_YCAU_KNAI(),
							Variables.MA_LOAI_TTOAN_CHON);
					list_vattu_da_chon.clear();
					mAdapter_vattu_dachon.notifyDataSetChanged();
					list_vattu_sudung = new ArrayList<Obj_D_VATTU>(list_all_vattu);
					mAdapter_bo_vattu = new LST_HSO_VATTU_SD(TAB_VATTU_CHIPHI.this,
							list_vattu_sudung);
					mAdapter_bo_vattu.get_vattu_nhom(NHOM_CHON);
					lv_bo_vattu.setAdapter(mAdapter_bo_vattu);
					Custom_Toast.show_blue_toast(TAB_VATTU_CHIPHI.this,getString(R.string.xoa_het_thanh_cong));
					Intent i = new Intent();
					setResult(RESULT_OK, i);
					refesh_tieude();
				} catch (Exception e) {
					ThtShow.show_crouton_toast(TAB_VATTU_CHIPHI.this,
							e.toString(), Style.ALERT);
				}
				dialog.dismiss();
			}
		});
		Button no = (Button) dialog.findViewById(R.id.bmessageDialogNo);
		no.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				dialog.dismiss();
			}
		});
		dialog.show();
	}

	public void thoat(View v) {
		if (mKeyboardView != null && mKeyboardView.isCustomKeyboardVisible()) {
			mKeyboardView.hideCustomKeyboard();
			ln_chon_vtu.setVisibility(View.GONE);
//			ln_nhom.setVisibility(View.VISIBLE);
		} else {
			super.onBackPressed();
		}
	}

	// Spinner MAU_VTU
	public void set_spinner_mau_vattu() {
		try {
			Variables.list_mau_vattu = mdb.set_spn_mau_vattu();
			Variables.list_mau_vattu.add(0,getString(R.string.chon_mau));
		if (Variables.list_mau_vattu != null) {
			Tht_add_data.set_spinner(TAB_VATTU_CHIPHI.this,
					Variables.list_mau_vattu, spn_MAU_VTU);
		}
		} catch (Exception e) {
			Custom_Toast.show_red_toast(TAB_VATTU_CHIPHI.this, e.toString());
		}
			

	}

	// spinner nhom vat tu
	public void set_spinner_nhom_vattu() {
		mdb.open();
		List<String> labels_nhom = new ArrayList<String>();
			Variables.list_nhom_vattu = mdb
					.get_list_nhom_vattu(Variables.HSCT_CHON.MA_DVIQLY);
			//Variables.list_nhom_vattu.add(0,new Obj_D_NHOM_VTU(-1,getString(R.string.tat_ca_nhom),""));
		if (Variables.list_nhom_vattu != null) {
			for (Obj_D_NHOM_VTU NVT : Variables.list_nhom_vattu) {
				labels_nhom.add(NVT.getTEN_NHOM());
			}
		}
		Tht_add_data.set_spinner(TAB_VATTU_CHIPHI.this, labels_nhom,
				spn_nhom_vattu);
	}

	@Override
	public void onBackPressed() {
		if (mKeyboardView != null && mKeyboardView.isCustomKeyboardVisible()){
			mKeyboardView.hideCustomKeyboard();
			ln_chon_vtu.setVisibility(View.GONE);
		} else{
			super.onBackPressed();
		}
			
	}
	@SuppressLint("ClickableViewAccessibility")
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		if (v.getId() == R.id.edt_sl_vtu_tab_chiphi) {
			edt_sl_vattu.requestFocus();
		} else if (v.getId() == R.id.edt_sl_vtu_lap_tab_chiphi) {
			edt_sl_vattu_lap.requestFocus();
		}
		return false;
	}

	// load du lieu
	class load_du_lieu extends AsyncTask<String, String, String> {
		int lenh ;
		public load_du_lieu(int lenh){
			this.lenh = lenh;
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(TAB_VATTU_CHIPHI.this);
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
			pDialog.setMessage("Xin chờ ...");

			mcountdown = new CountDownTimer(time_connnect, 1000) {

				public void onTick(long millisUntilFinished) {

				}

				public void onFinish() {
					pDialog.dismiss();
				}
			}.start();
		}

		protected String doInBackground(String... kq) {
			try {
				if (lenh==LENH_GET_VATTU){
					list_all_vattu = mdb.get_list_all_vattu(MA_DVI);
					khoitao_vattu();
					LENH = 0;
				} else if (lenh==LENH_LUU_MAU){
					LUU_MAU_VATTU(list_vattu_da_chon,TEN_MAU_LUU);
				}  else if (lenh==LENH_CHON_MAU){
					try {
						chon_mau(list_mau);
						refesh_bo_vattu();
						mAdapter_bo_vattu.get_vattu_nhom(NHOM_CHON);
					} catch (Exception e) {
						Utils.show_loi(e, TAB_VATTU_CHIPHI.this);
					}
					
				} else if (lenh==LENH_GET_VTU_NHOM){
					refesh_bo_vattu();
					mAdapter_bo_vattu.get_vattu_nhom(NHOM_CHON);
				}
				

			} catch (Exception e) {
				ThtShow.show_crouton_toast(TAB_VATTU_CHIPHI.this, e.toString(), Style.ALERT);
			}

			return null;

		}

		protected void onPostExecute(String result) {
			pDialog.dismiss();
			mcountdown.cancel();
			runOnUiThread(new Runnable() {
				public void run() {
					if (lenh==LENH_GET_VATTU){
						set_spinner_mau_vattu();
						set_spinner_nhom_vattu();
						lv_bo_vattu.setAdapter(mAdapter_bo_vattu);
						lvVatTu.setAdapter(mAdapter_vattu_dachon);
					}else if (lenh==LENH_LUU_MAU){
						LENH=0;
						set_spinner_mau_vattu();
						Custom_Toast.show_blue_toast(TAB_VATTU_CHIPHI.this, "Đã lưu mẫu thành công");
						mdb.open();
					}else if (lenh==LENH_CHON_MAU){
						try {
							if (SL_VTU_MAU>0){
								lv_bo_vattu.setAdapter(mAdapter_bo_vattu);
								lvVatTu.setAdapter(mAdapter_vattu_dachon);
								Custom_Toast.show_blue_toast(TAB_VATTU_CHIPHI.this, "Đã chọn mẫu thành công !");
								Intent i = new Intent();
								setResult(RESULT_OK, i);
							}else{
								Custom_Toast.show_yellow_toast(TAB_VATTU_CHIPHI.this,"Chưa có vật tư trong mẫu đang chọn");
							}
							SL_VTU_MAU=0;
						} catch (Exception e) {
							Utils.show_loi(e, TAB_VATTU_CHIPHI.this);
						}
						
					} else if (lenh==LENH_GET_VTU_NHOM){
						lv_bo_vattu.setAdapter(mAdapter_bo_vattu);
					}
					refesh_tieude();
				}

				
			});

		}

	}
	public void LUU_MAU_VATTU(List<Obj_HSO_VATTU_CTIET> list_VTCT,String TEN_MAU){
		if (TEN_MAU.length()>0){
			if (list_VTCT!=null){
				try {
					for (Obj_HSO_VATTU_CTIET VTCT : list_VTCT) {
						if (VTCT!=null){
							if (mdb.ton_tai_vattu(MA_DVI, VTCT.getMA_VTU())){
								Obj_D_MAU DM = new Obj_D_MAU();
								DM.setMA_DVIQLY(MA_DVI);
								DM.setMA_VTU( VTCT.getMA_VTU());
								DM.setMAU("MTB_"+TEN_MAU);
								DM.setSO_LUONG(VTCT.getSO_LUONG());
								DBAdapter.Insert_D_MAU(DM);
							}				
						}				
					}
					Obj_nhap_lieu NL = new Obj_nhap_lieu(TEN_MAU, Variables.NL_TEN_MAU_VATTU);
					if (mdb.chua_ton_tai_nhap_lieu(NL)){
						DBAdapter.Insert_NHAP_LIEU(NL);
					}
					
				} catch (Exception e) {
					Custom_Toast.show_red_toast(TAB_VATTU_CHIPHI.this, "Lỗi lưu mẫu !");
				}
				
			}
		}
		
		
	}
	 public void SHOW_DIALOG_LUU_MAU(View v) {
		 	LENH=SUA_MAU;
		 	final List<Obj_nhap_lieu> list_nhap_lieu = mdb.get_list_NL(Variables.NL_TEN_MAU_VATTU);
		 	Adapter_nhap_lieu mAdapter_TT = new Adapter_nhap_lieu(list_nhap_lieu, TAB_VATTU_CHIPHI.this, mdb);
			final Dialog_listview_ghichu dialog_GC = new Dialog_listview_ghichu(TAB_VATTU_CHIPHI.this,Length_text.ML_MAU);
			dialog_GC.show();
			Dialog_listview_ghichu.edt_ghichu.setText("");
			Dialog_listview_ghichu.edt_ghichu.setSelection(Dialog_listview_ghichu.edt_ghichu.getText().length());
			Dialog_listview_ghichu.lv_ghichu.setAdapter(mAdapter_TT);
			Dialog_listview_ghichu.lv_ghichu.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
						long arg3) {
					Dialog_listview_ghichu.edt_ghichu.setText(list_nhap_lieu.get(arg2).nhap_lieu);
					Dialog_listview_ghichu.edt_ghichu.setSelection(Dialog_listview_ghichu.edt_ghichu.getText().length());
				}
			});
			Dialog_listview_ghichu.btn_ok.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					if (LENH==SUA_MAU){
						// luu mau
						if (Dialog_listview_ghichu.edt_ghichu.getText().length()<=dialog_GC.max_length){
							TEN_MAU_LUU = Dialog_listview_ghichu.edt_ghichu.getText().toString();
							if (mdb.chua_ton_tai_mau_vat_tu(TEN_MAU_LUU)
									& mdb.chua_ton_tai_mau_vat_tu("MTB_"+TEN_MAU_LUU)){
								dialog_GC.dismiss();
								new load_du_lieu(LENH_LUU_MAU).execute();
							}else{
								Custom_Toast.show_yellow_toast(TAB_VATTU_CHIPHI.this, "Mẫu này đã tồn tại, đặt tên khác !");
							}
						}else{
							Length_text.alert_length(TAB_VATTU_CHIPHI.this, dialog_GC.max_length);
						}
						
					}
				}
			});
			WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
			lp.copyFrom(dialog_GC.getWindow().getAttributes());
			lp.width = Tht_Screen.get_screen_width_percent(TAB_VATTU_CHIPHI.this, 90);
			lp.height = Tht_Screen.get_screen_heigth_percent(TAB_VATTU_CHIPHI.this, 90);;
			dialog_GC.getWindow().setAttributes(lp);
		}
	 public void refesh_khi_them_vattu (){
		 try {
			 mAdapter_bo_vattu.refesh_list(POS_VTU_CHON);
			 refesh_tieude();
		 } catch (Exception e) {
//			Custom_Toast.show_red_toast(TAB_VATTU_CHIPHI.this,"refesh_xoa");
		}
	}
	 public void refesh_khi_xoa_vattu (){
		 try {
			 refesh_bo_vattu();
			 mAdapter_bo_vattu.get_vattu_nhom(NHOM_CHON);
			 lv_bo_vattu.setAdapter(mAdapter_bo_vattu);
			 lvVatTu.setAdapter(mAdapter_vattu_dachon);
			 refesh_tieude();
		} catch (Exception e) {
//			Custom_Toast.show_red_toast(TAB_VATTU_CHIPHI.this,e.toString());
		}
	}
	 public void refesh_tieude (){
		 try {
			 tv_title.setText(Variables.MA_LOAI_TTOAN_CHON_TITLE+" ( "
						+Thtcovert.int_to_String(mAdapter_vattu_dachon.get_soluong_loai(Utils.VAT_LIEU))+" VL - "
						+Thtcovert.int_to_String(mAdapter_vattu_dachon.get_soluong_loai(Utils.NHAN_CONG))+" NC )"
						);
			 if (NHOM_CHON >0){
				 edt_timkiem.setHint(getString(R.string.tim_kiem_vat_tu)+" trong "
							+Thtcovert.int_to_String(mAdapter_bo_vattu.getCount()));
			 }else{
				 edt_timkiem.setHint(getString(R.string.tim_kiem_vat_tu)+" trong "
						+Thtcovert.int_to_String(SL_TONGVT - mAdapter_vattu_dachon.getCount()));
			 }
						
		} catch (Exception e) {
			
		}
	}
	 
	 public void get_vattu_nhom (int nhom){
		 try {
				if (nhom>0){
					mAdapter_bo_vattu.get_vattu_nhom(NHOM_CHON);
				}else{
					mAdapter_bo_vattu.get_vattu_nhom(-1);
				}
				
			} catch (Exception e) {
				
			}
	 }
	 public void khoitao_vattu(){
		 	if (list_all_vattu!=null){
		 		SL_TONGVT = list_all_vattu.size();
		 	}
			list_vattu_da_chon = mdb.get_list_vattu_chitiet
					(Variables.HSCT_CHON.MA_YCAU_KNAI, Variables.MA_LOAI_TTOAN_CHON);
			list_vattu_sudung = new ArrayList<Obj_D_VATTU>(list_all_vattu);
			for (Obj_HSO_VATTU_CTIET VTCT : list_vattu_da_chon) {
				for (Obj_D_VATTU DVT : list_all_vattu) {
					if (DVT.getMA_VTU().equals(VTCT.getMA_VTU())){
						list_vattu_sudung.remove(DVT);
					}
				}
			}
			mAdapter_bo_vattu = new LST_HSO_VATTU_SD(TAB_VATTU_CHIPHI.this,
					list_vattu_sudung);
			mAdapter_vattu_dachon = new Lst_HSO_VATTU_DACHON(list_vattu_da_chon, TAB_VATTU_CHIPHI.this);
	 }
	 public void refesh_bo_vattu(){
		 	list_all_vattu = mdb.get_list_all_vattu(MA_DVI);
		 	if (list_all_vattu!=null){
		 		SL_TONGVT = list_all_vattu.size();
		 	}
			list_vattu_sudung = new ArrayList<Obj_D_VATTU>(list_all_vattu);
			for (Obj_HSO_VATTU_CTIET VTCT : list_vattu_da_chon) {
				for (Obj_D_VATTU DVT : list_all_vattu) {
					if (DVT.getMA_VTU().equals(VTCT.getMA_VTU())){
						list_vattu_sudung.remove(DVT);
					}
				}
			}
			mAdapter_bo_vattu = new LST_HSO_VATTU_SD(TAB_VATTU_CHIPHI.this,
					list_vattu_sudung);
	 }
}
	
