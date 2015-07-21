package hnks.kitsoft.vn.custom_dialog;

import java.util.ArrayList;
import java.util.List;
import com.thtsoftlib.function.ThtFunction;
import com.thtsoftlib.function.Tht_Screen;
import com.thtsoftlib.function.Thtcovert;
import hnks.kitsoft.utils.Custom_Toast;
import hnks.kitsoft.utils.Length_text;
import hnks.kitsoft.utils.Utils;
import hnks.kitsoft.utils.Variables;
import hnks.kitsoft.vn.R;
import hnks.kitsoft.vn.database.DBAdapter;
import hnks.kitsoft.vn.list.Adapter_nhap_lieu;
import hnks.kitsoft.vn.list.Lst_TRAM;
import hnks.kitsoft.vn.object.Obj_DANHMUC_TRAM;
import hnks.kitsoft.vn.object.Obj_HSO_CHIETTINH;
import hnks.kitsoft.vn.object.Obj_nhap_lieu;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class CAPNHAT_HSO extends Activity implements OnClickListener{

	int LENH = 0;
	DBAdapter mdb;
	EditText edt_TENTRAM_BIENAP, edt_MA_TRAM, edt_CONG_SUAT_TRAM,
			edt_DONGDIEN_DINHMUC, edt_TAI_MAX, edt_CONGTO_TRAI,
			edt_CONGTO_PHAI, edt_KC_CONGTO_DEN_TRAM, edt_KC_CONGTO_DEN_LUOI,
			edt_TRU, edt_CONGSUAT_THUC_TE_KW, edt_CONGSUAT_THUC_TE_A,
			edt_MA_KHACHHANG, edt_CONGSUAT_DENGHI_A, edt_CONGSUAT_DENGHI_V,
			edt_CONGSUAT_DENGHI_PHA, edt_MASO_STT,edt_VITRI_TREO,edt_LOAI_XAY_DUNG
			,edt_LOAI_NHA,edt_DVI_THICONG,edt_DVI_CAPVATTU,edt_DVI_THIETKE,
			edt_PHUTAI_A,edt_PHUTAI_B,edt_PHUTAI_C;
	AutoCompleteTextView aut_MUC_DICH_SU_DUNG, aut_LY_DO;
	
	Dialog_listview_ghichu dialog_GC ;
	Dialog_Kw2A dialog_KW2A ;

	CheckBox chk_KHONG_CONG_TO, chk_CO_CONG_TO,
			chk_KHONG_TRONGAI, chk_TRONGAI;
	int w, h;
	RadioGroup  radioGroup_LOAITAI,
			RadioGroup_TINHTRANG_CONGTO;
	Button btn_OK;
	RadioButton rdo_LOAITAI_A, rdo_LOAITAI_B, rdo_LOAITAI_C;
	RadioButton rdo_DUNGCHUNG, rdo_DUNGRIENG;
	Dialog_TRAM dialog;
	Lst_TRAM mAdapter_TRAM;
	final int SUA_LYDO =1,SUA_MUCDICH=2,SUA_KCCT2TBA=3,SUA_KCCT2TLHT=4,SUA_TRU=5,SUA_TAIMAX=6,
			SUA_CONGSUAT_TT_A =7,SUA_CONGSUAT_TT_KW =8,SUA_CONGSUAT_DN_A =9,
			SUA_CONGSUAT_DN_V =10,SUA_CONGSUAT_DN_PHA =11,SUA_CONGTO_TRAI =12,SUA_CONGTO_PHAI =13
			,SUA_MA_KH =14,SUA_MASO_STT =15,SUA_DD_DINHMUC =16,SUA_VITRI_TREO=17,SUA_LOAI_XAY_DUNG=18,
			SUA_LOAI_NHA=19,SUA_DV_CAPVATTU=20,SUA_DV_THIETKE=21,SUA_DV_THICONG=22,
			SUA_PHUTAI_A=23,SUA_PHUTAI_B=24,SUA_PHUTAI_C=25;
	int lenh_load_data=1,lenh_save_data=2;
	String text_dialog ="";
	String donvi_thuchien="";
	int LENH_NL=0;
	List<Obj_nhap_lieu> list_nhap_lieu;
	Adapter_nhap_lieu mAdapter_TT;
	private ProgressDialog pDialog;
	CountDownTimer mcountdown;
	final int time_connnect = 40000;
	Obj_HSO_CHIETTINH HS;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Tht_Screen.hide_title(this);
		setContentView(R.layout.cap_nhat_ho_so_khao_sat);
		new load_du_lieu(lenh_load_data).execute();

	}

	public void set_key() {

		btn_OK = (Button) findViewById(R.id.btn_OK_ho_so_khao_sat);
		edt_TENTRAM_BIENAP = (EditText) findViewById(R.id.edt_TENTRAM_BIENAP_ho_so_khao_sat);
		edt_MA_TRAM = (EditText) findViewById(R.id.edt_MA_TRAM_ho_so_khao_sat);
		edt_CONG_SUAT_TRAM = (EditText) findViewById(R.id.edt_CONG_SUAT_TRAM_ho_so_khao_sat);
		edt_DONGDIEN_DINHMUC = (EditText) findViewById(R.id.edt_DONGDIEN_DINHMUC_ho_so_khao_sat);
		edt_TAI_MAX = (EditText) findViewById(R.id.edt_TAI_MAX_ho_so_khao_sat);
		edt_CONGTO_TRAI = (EditText) findViewById(R.id.edt_CONGTO_TRAI_ho_so_khao_sat);
		edt_CONGTO_PHAI = (EditText) findViewById(R.id.edt_CONGTO_PHAI_ho_so_khao_sat);
		edt_KC_CONGTO_DEN_TRAM = (EditText) findViewById(R.id.edt_KC_CONGTO_DEN_TRAM_ho_so_khao_sat);
		edt_KC_CONGTO_DEN_LUOI = (EditText) findViewById(R.id.edt_KC_CONGTO_DEN_LUOI_ho_so_khao_sat);

		edt_TRU = (EditText) findViewById(R.id.edt_TRU_ho_so_khao_sat);
		edt_CONGSUAT_THUC_TE_KW = (EditText) findViewById(R.id.edt_CONGSUAT_THUC_TE_KW_ho_so_khao_sat);
		edt_CONGSUAT_THUC_TE_A = (EditText) findViewById(R.id.edt_CONGSUAT_THUC_TE_A_ho_so_khao_sat);
		aut_MUC_DICH_SU_DUNG = (AutoCompleteTextView) findViewById(R.id.aut_MUC_DICH_SU_DUNG_ho_so_khao_sat);
		edt_MA_KHACHHANG = (EditText) findViewById(R.id.edt_MA_KHACHHANG);
		edt_VITRI_TREO = (EditText)findViewById(R.id.edt_VI_TRI_TREO_ho_so_khao_sat);

		edt_CONGSUAT_DENGHI_A = (EditText) findViewById(R.id.edt_CONGSUAT_DENGHI_A_ho_so_khao_sat);
		edt_CONGSUAT_DENGHI_V = (EditText) findViewById(R.id.edt_CONGSUAT_DENGHI_V_ho_so_khao_sat);
		edt_CONGSUAT_DENGHI_PHA = (EditText) findViewById(R.id.edt_CONGSUAT_DENGHI_PHA_ho_so_khao_sat);
		aut_LY_DO = (AutoCompleteTextView) findViewById(R.id.aut_LY_DO_ho_so_khao_sat);
		edt_MASO_STT = (EditText) findViewById(R.id.edt_MASO_STT_ho_so_khao_sat);
		edt_LOAI_XAY_DUNG = (EditText) findViewById(R.id.edt_LOAI_XAY_DUNG_ho_so_khao_sat);
		edt_LOAI_NHA = (EditText) findViewById(R.id.edt_LOAI_NHA_ho_so_khao_sat);
		edt_DVI_CAPVATTU = (EditText) findViewById(R.id.edt_DV_CAPVATTU_capnhathoso);
		edt_DVI_THICONG = (EditText) findViewById(R.id.edt_DV_THICONG_capnhathoso);
		edt_DVI_THIETKE = (EditText) findViewById(R.id.edt_DV_THIETKE_capnhathoso);
		edt_PHUTAI_A = (EditText) findViewById(R.id.edt_PHUTAI_A_ho_so_khao_sat);
		edt_PHUTAI_B = (EditText) findViewById(R.id.edt_PHUTAI_B_ho_so_khao_sat);
		edt_PHUTAI_C = (EditText) findViewById(R.id.edt_PHUTAI_C_ho_so_khao_sat);

		chk_KHONG_CONG_TO = (CheckBox) findViewById(R.id.chk_KHONG_CONG_TO_ho_so_khao_sat);
		chk_CO_CONG_TO = (CheckBox) findViewById(R.id.chk_CO_CONG_TO_ho_so_khao_sat);
		chk_KHONG_TRONGAI = (CheckBox) findViewById(R.id.chk_KHONG_TRONGAI_ho_so_khao_sat);
		chk_TRONGAI = (CheckBox) findViewById(R.id.chk_TRONGAI_ho_so_khao_sat);
		radioGroup_LOAITAI = (RadioGroup) findViewById(R.id.radioGroup_LOAITAI_ho_so_khao_sat);
		RadioGroup_TINHTRANG_CONGTO = (RadioGroup) findViewById(R.id.RadioGroup_TINHTRANG_CONGTO_ho_so_khao_sat);
		rdo_LOAITAI_A = (RadioButton) findViewById(R.id.rdo_LOAITAI_A_ho_so_khao_sat);
		rdo_LOAITAI_B = (RadioButton) findViewById(R.id.rdo_LOAITAI_B_ho_so_khao_sat);
		rdo_LOAITAI_C = (RadioButton) findViewById(R.id.rdo_LOAITAI_C_ho_so_khao_sat);
		rdo_DUNGCHUNG = (RadioButton) findViewById(R.id.rdo_DUNGCHUNG_ho_so_khao_sat);
		rdo_DUNGRIENG = (RadioButton) findViewById(R.id.rdo_DUNGRIENG_ho_so_khao_sat);

	}

//	public void addListenerOnButton() {
//
//		btn_OK.setOnClickListener(new View.OnClickListener() {
//
//			public void onClick(View v) {
//
//				// get selected radio button from radioGroup
//				int selectedId = radioGroup_VITRITREO.getCheckedRadioButtonId();
//
//				// find the radiobutton by returned id
//				rdo_VITRITREO = (RadioButton) findViewById(selectedId);
////				int radioId = radioGroup_VITRITREO.indexOfChild(rdo_VITRITREO);
//				// RadioButton btn = (RadioButton)
//				// radioGroup_VITRITREO.getChildAt(radioId);
//				// String selection = (String) btn.getText();
//				// btn_HUY.setText(selection);
//
//			}
//
//		});
//
//	}
	public void LOAD_DATA (){
		w = (Tht_Screen.get_screen_width(this)/10) * 9;
		h = (Tht_Screen.get_screen_heigth(this)/10) * 9;
		LENH =0;
		mdb = new DBAdapter(this);
		Tht_Screen.hide_keyboard(this);
		set_key();
		edt_TENTRAM_BIENAP.setOnClickListener(this);
		edt_MA_TRAM.setOnClickListener(this);
		aut_LY_DO.setOnClickListener(this);
		aut_MUC_DICH_SU_DUNG.setOnClickListener(this);
		edt_KC_CONGTO_DEN_TRAM.setOnClickListener(this);
		edt_KC_CONGTO_DEN_LUOI.setOnClickListener(this);
		edt_TRU.setOnClickListener(this);
		edt_TAI_MAX.setOnClickListener(this);
		edt_CONGSUAT_DENGHI_A.setOnClickListener(this);
		edt_CONGSUAT_DENGHI_PHA.setOnClickListener(this);
		edt_CONGSUAT_DENGHI_V.setOnClickListener(this);
		edt_CONGSUAT_THUC_TE_A.setOnClickListener(this);
		edt_CONGSUAT_THUC_TE_KW.setOnClickListener(this);
		edt_CONGTO_PHAI.setOnClickListener(this);
		edt_CONGTO_TRAI.setOnClickListener(this);
		edt_MA_KHACHHANG.setOnClickListener(this);
		edt_MASO_STT.setOnClickListener(this);
		edt_DONGDIEN_DINHMUC.setOnClickListener(this);
		edt_VITRI_TREO.setOnClickListener(this);
		edt_LOAI_XAY_DUNG.setOnClickListener(this);
		edt_LOAI_NHA.setOnClickListener(this);
		edt_DVI_CAPVATTU.setOnClickListener(this);
		edt_DVI_THICONG.setOnClickListener(this);
		edt_DVI_THIETKE.setOnClickListener(this);
		edt_PHUTAI_A.setOnClickListener(this);
		edt_PHUTAI_B.setOnClickListener(this);
		edt_PHUTAI_C.setOnClickListener(this);
		HS = new Obj_HSO_CHIETTINH();
		if (Variables.HSCT_CHON!=null){
			HS = Variables.HSCT_CHON;
		}
		
		
	}
	public void KT_CHECKBOX() {
		if (Variables.HSCT_CHON.TINHTRANG_CONG_TO.equals(Utils.CT_KHONG)) {
			chk_KHONG_CONG_TO.setChecked(true);
			chk_CO_CONG_TO.setChecked(false);
			rdo_DUNGCHUNG.setChecked(false);
			rdo_DUNGRIENG.setChecked(false);
			rdo_DUNGCHUNG.setEnabled(false);
			rdo_DUNGRIENG.setEnabled(false);
			edt_MA_KHACHHANG.setEnabled(false);
		} else {
			chk_KHONG_CONG_TO.setChecked(false);
			chk_CO_CONG_TO.setChecked(true);
			rdo_DUNGCHUNG.setEnabled(true);
			rdo_DUNGRIENG.setEnabled(true);
			edt_MA_KHACHHANG.setEnabled(true);
			if (Variables.HSCT_CHON.TINHTRANG_CONGTO_CO.equals(Utils.CT_RIENG)) {
				rdo_DUNGRIENG.setChecked(true);
				rdo_DUNGCHUNG.setChecked(false);
			} else {
				rdo_DUNGRIENG.setChecked(false);
				rdo_DUNGCHUNG.setChecked(true);
			}
		}
		if (Variables.HSCT_CHON.TINHTRANG_TRONGAI.equals(Utils.TRONGAI_CO)) {
			chk_TRONGAI.setChecked(true);
			chk_KHONG_TRONGAI.setChecked(false);
			aut_LY_DO.setEnabled(true);
			edt_CONGSUAT_DENGHI_A.setEnabled(false);
			edt_CONGSUAT_DENGHI_V.setEnabled(false);
			edt_CONGSUAT_DENGHI_PHA.setEnabled(false);
			edt_MASO_STT.setEnabled(false);

		} else {
			chk_TRONGAI.setChecked(false);
			chk_KHONG_TRONGAI.setChecked(true);
			aut_LY_DO.setEnabled(false);
			edt_CONGSUAT_DENGHI_A.setEnabled(true);
			edt_CONGSUAT_DENGHI_V.setEnabled(true);
			edt_CONGSUAT_DENGHI_PHA.setEnabled(true);
			edt_MASO_STT.setEnabled(true);

		}
		if (Variables.HSCT_CHON.LOAI_TAI.equals(Utils.LOAITAI_A)) {
			rdo_LOAITAI_A.setChecked(true);
		} else if (Variables.HSCT_CHON.LOAI_TAI.equals(Utils.LOAITAI_B)) {
			rdo_LOAITAI_B.setChecked(true);
		} else {
			rdo_LOAITAI_C.setChecked(true);
		}
		//
		chk_CO_CONG_TO
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton arg0,
							boolean isChecked) {
						if (isChecked) {
							chk_KHONG_CONG_TO.setChecked(false);
							rdo_DUNGCHUNG.setEnabled(true);
							rdo_DUNGRIENG.setEnabled(true);
							edt_MA_KHACHHANG.setEnabled(true);
							if (Variables.HSCT_CHON.TINHTRANG_CONGTO_CO.equals(Utils.CT_RIENG)) {
								rdo_DUNGRIENG.setChecked(true);
								rdo_DUNGCHUNG.setChecked(false);
							} else {
								rdo_DUNGRIENG.setChecked(false);
								rdo_DUNGCHUNG.setChecked(true);
							}

						} else {
							chk_KHONG_CONG_TO.setChecked(true);
							rdo_DUNGCHUNG.setEnabled(false);
							rdo_DUNGRIENG.setEnabled(false);
							edt_MA_KHACHHANG.setEnabled(false);

						}
					}
				});
		chk_KHONG_CONG_TO
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton arg0,
							boolean isChecked) {
						if (isChecked) {

							chk_CO_CONG_TO.setChecked(false);
							rdo_DUNGCHUNG.setChecked(false);
							rdo_DUNGRIENG.setChecked(false);
							rdo_DUNGCHUNG.setEnabled(false);
							rdo_DUNGRIENG.setEnabled(false);// disable checkbox
							edt_MA_KHACHHANG.setEnabled(false);
						} else {
							chk_CO_CONG_TO.setChecked(true);
						}
					}
				});
		// tro ngai
		chk_KHONG_TRONGAI
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton arg0,
							boolean isChecked) {
						if (isChecked) {
							chk_TRONGAI.setChecked(false);
							edt_CONGSUAT_DENGHI_A.setEnabled(true);
							edt_CONGSUAT_DENGHI_V.setEnabled(true);
							edt_CONGSUAT_DENGHI_PHA.setEnabled(true);
							edt_MASO_STT.setEnabled(true);
							aut_LY_DO.setEnabled(false);
						} else {
							chk_TRONGAI.setChecked(true);
						}
					}
				});
		chk_TRONGAI.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean isChecked) {
				if (isChecked) {
					chk_KHONG_TRONGAI.setChecked(false); // disable checkbox
					edt_CONGSUAT_DENGHI_A.setEnabled(false);
					edt_CONGSUAT_DENGHI_V.setEnabled(false);
					edt_CONGSUAT_DENGHI_PHA.setEnabled(false);
					edt_MASO_STT.setEnabled(false);
					aut_LY_DO.setEnabled(true);
				} else {
					chk_KHONG_TRONGAI.setChecked(true);
				}
			}
		});

	}

	public void SET_TO_TEXTVIEW() {
		edt_TENTRAM_BIENAP.setText(HS.TENTRAM_BIENAP);
		edt_MA_TRAM.setText(HS.MA_TRAM);
		edt_CONG_SUAT_TRAM.setText(HS.CONG_SUAT_TRAM);
		edt_DONGDIEN_DINHMUC.setText(HS.DONGDIEN_DINHMUC);
		edt_TAI_MAX.setText(HS.TAI_MAX);
		edt_CONGTO_TRAI.setText(HS.CONGTO_TRAI);
		edt_CONGTO_PHAI.setText(HS.CONGTO_PHAI);
		edt_KC_CONGTO_DEN_TRAM.setText(HS.KC_CONGTO_DEN_TRAM);
		edt_KC_CONGTO_DEN_LUOI.setText(HS.KC_CONGTO_DEN_LUOI);
		edt_TRU.setText(HS.TRU);
		edt_CONGSUAT_THUC_TE_KW.setText(HS.CONGSUAT_THUC_TE_KW);
		edt_CONGSUAT_THUC_TE_A.setText(HS.CONGSUAT_THUC_TE_A);
		aut_MUC_DICH_SU_DUNG.setText(HS.MUC_DICH_SU_DUNG);
		edt_MA_KHACHHANG.setText(HS.MA_KHACHHANG);
		edt_CONGSUAT_DENGHI_A.setText(HS.CONGSUAT_DENGHI_A);
		edt_CONGSUAT_DENGHI_V.setText(HS.CONGSUAT_DENGHI_V);
		edt_CONGSUAT_DENGHI_PHA.setText(HS.CONGSUAT_DENGHI_PHA);
		aut_LY_DO.setText(HS.LY_DO);
		edt_MASO_STT.setText(HS.MASO_STT);
		edt_VITRI_TREO.setText(HS.getVITRITREO());
		edt_LOAI_XAY_DUNG.setText(HS.getLOAI_XAY_DUNG());
		edt_LOAI_NHA.setText(HS.getLOAI_NHA());
		edt_DVI_CAPVATTU.setText(HS.getDV_CAP_VTU());
		edt_DVI_THICONG.setText(HS.getDV_THICONG());
		edt_DVI_THIETKE.setText(HS.getDV_THIETKE());
		edt_PHUTAI_A.setText(HS.getPHUTAI_A());
		edt_PHUTAI_B.setText(HS.getPHUTAI_B());
		edt_PHUTAI_C.setText(HS.getPHUTAI_C());
	}

	public void LAY_DU_LIEU_TU_EDITTEXT() {

		Variables.HSCT_CHON.TENTRAM_BIENAP = edt_TENTRAM_BIENAP.getText().toString();
		Variables.HSCT_CHON.MA_TRAM = edt_MA_TRAM.getText().toString();
		Variables.HSCT_CHON.CONG_SUAT_TRAM = edt_CONG_SUAT_TRAM.getText().toString();
		Variables.HSCT_CHON.DONGDIEN_DINHMUC = edt_DONGDIEN_DINHMUC.getText().toString();
		Variables.HSCT_CHON.TAI_MAX = edt_TAI_MAX.getText().toString();

		Variables.HSCT_CHON.CONGTO_TRAI = edt_CONGTO_TRAI.getText().toString();
		Variables.HSCT_CHON.CONGTO_PHAI = edt_CONGTO_PHAI.getText().toString();
		Variables.HSCT_CHON.DONGDIEN_DINHMUC = edt_DONGDIEN_DINHMUC.getText().toString();
		Variables.HSCT_CHON.KC_CONGTO_DEN_TRAM = edt_KC_CONGTO_DEN_TRAM.getText().toString();

		Variables.HSCT_CHON.KC_CONGTO_DEN_LUOI = edt_KC_CONGTO_DEN_LUOI.getText().toString();
		Variables.HSCT_CHON.TRU = edt_TRU.getText().toString();
		Variables.HSCT_CHON.CONGSUAT_THUC_TE_KW = edt_CONGSUAT_THUC_TE_KW.getText().toString();
		Variables.HSCT_CHON.CONGSUAT_THUC_TE_A = edt_CONGSUAT_THUC_TE_A.getText().toString();
		Variables.HSCT_CHON.MUC_DICH_SU_DUNG = aut_MUC_DICH_SU_DUNG.getText().toString();
		Variables.HSCT_CHON.MA_KHACHHANG = edt_MA_KHACHHANG.getText().toString();
		Variables.HSCT_CHON.LY_DO = aut_LY_DO.getText().toString();
		Variables.HSCT_CHON.MASO_STT = edt_MASO_STT.getText().toString();
		Variables.HSCT_CHON.VITRITREO = edt_VITRI_TREO.getText().toString();
		Variables.HSCT_CHON.LOAI_XAY_DUNG = edt_LOAI_XAY_DUNG.getText().toString();
		Variables.HSCT_CHON.LOAI_NHA = edt_LOAI_NHA.getText().toString();
		// tinh trang co hay ko co cong to
		if (chk_KHONG_CONG_TO.isChecked() == true) {
			Variables.HSCT_CHON.TINHTRANG_CONG_TO = Utils.CT_KHONG;
			Variables.HSCT_CHON.TINHTRANG_CONGTO_CO = "";
		} else if (chk_CO_CONG_TO.isChecked() == true) {
			Variables.HSCT_CHON.TINHTRANG_CONG_TO = Utils.CT_CO;
			if (rdo_DUNGRIENG.isChecked() == true) {
				Variables.HSCT_CHON.TINHTRANG_CONGTO_CO = Utils.CT_RIENG;
			} else if (rdo_DUNGCHUNG.isChecked() == true) {
				Variables.HSCT_CHON.TINHTRANG_CONGTO_CO = Utils.CT_CHUNG;
			}
		}
		if (rdo_LOAITAI_A.isChecked() == true) {
			Variables.HSCT_CHON.LOAI_TAI = Utils.LOAITAI_A;
		} else if (rdo_LOAITAI_B.isChecked() == true) {
			Variables.HSCT_CHON.LOAI_TAI = Utils.LOAITAI_B;
		} else if (rdo_LOAITAI_C.isChecked() == true) {
			Variables.HSCT_CHON.LOAI_TAI = Utils.LOAITAI_C;
		}
		if (chk_TRONGAI.isChecked() == true) {
			Variables.HSCT_CHON.TINHTRANG_TRONGAI = Utils.TRONGAI_CO;
		} else if (chk_KHONG_TRONGAI.isChecked() == true) {
			Variables.HSCT_CHON.TINHTRANG_TRONGAI = Utils.TRONGAI_KHONG;
			Variables.HSCT_CHON.CONGSUAT_DENGHI_A = edt_CONGSUAT_DENGHI_A.getText().toString();
			Variables.HSCT_CHON.CONGSUAT_DENGHI_V = edt_CONGSUAT_DENGHI_V.getText().toString();
			Variables.HSCT_CHON.CONGSUAT_DENGHI_PHA = edt_CONGSUAT_DENGHI_PHA.getText().toString();
		}

	}
	public void SHOW_DIALOG_TRAM() {
		Tht_Screen.hide_keyboard(this);
		dialog = new Dialog_TRAM(CAPNHAT_HSO.this);
		dialog.show();
		WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
		lp.copyFrom(dialog.getWindow().getAttributes());
		lp.width = w;
		lp.height = h;
		dialog.getWindow().setAttributes(lp);
		if (Variables.list_tram==null){
		Variables.list_tram = mdb.get_ARR_TRAM(Variables.HSCT_CHON.MA_DVIQLY);
		}
		mAdapter_TRAM = new Lst_TRAM(Variables.list_tram, CAPNHAT_HSO.this);
		Dialog_TRAM.lv_TRAM.setAdapter(mAdapter_TRAM);

		Dialog_TRAM.edt_TRAM.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
					mAdapter_TRAM.get_SEARCH_TRAM(s.toString());
					mAdapter_TRAM.notifyDataSetChanged();

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
	public void SET_TRAM(Obj_DANHMUC_TRAM DMT){
		Variables.HSCT_CHON.setTENTRAM_BIENAP(DMT.TEN_TRAM);
		Variables.HSCT_CHON.setCONG_SUAT_TRAM(DMT.CSUAT_TRAM);
		Variables.HSCT_CHON.setMA_TRAM(DMT.MA_TRAM);
		mdb.update_HSO_CHIETTINH(Variables.HSCT_CHON);
		edt_TENTRAM_BIENAP.setText(DMT.TEN_TRAM);
		edt_CONG_SUAT_TRAM.setText(DMT.CSUAT_TRAM);
		edt_MA_TRAM.setText(DMT.MA_TRAM);
//		edt_DONGDIEN_DINHMUC.setText(DMT.DONGDIEN);

		dialog.dismiss();
	}
	public void thoat(View v){
		finish();
	}
	 public void SHOW_DIALOG_LY_DO(final Obj_HSO_CHIETTINH HSCT,final int length) {
			if (LENH==SUA_KCCT2TBA || LENH==SUA_KCCT2TLHT){
				list_nhap_lieu = new ArrayList<Obj_nhap_lieu>();
				for (int i = 1; i < 100; i++) {
					list_nhap_lieu.add(new Obj_nhap_lieu(Thtcovert.int_to_String(i), -1));
				}
			}else {
				list_nhap_lieu = mdb.get_list_NL(LENH_NL);
			}
			mAdapter_TT = new Adapter_nhap_lieu(list_nhap_lieu, CAPNHAT_HSO.this, mdb);
			dialog_GC = new Dialog_listview_ghichu(CAPNHAT_HSO.this,length);
			dialog_GC.show();
			Dialog_listview_ghichu.lv_ghichu.setAdapter(mAdapter_TT);
			Dialog_listview_ghichu.edt_ghichu.setText(text_dialog);
			Dialog_listview_ghichu.edt_ghichu.setSelection(Dialog_listview_ghichu.edt_ghichu.getText().length());
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
					if (Dialog_listview_ghichu.edt_ghichu.getText().length()<=length){
						if (LENH==SUA_LYDO){
							HSCT.setLY_DO(Dialog_listview_ghichu.edt_ghichu.getText().toString());
							mdb.update_HSO_CHIETTINH(HSCT);
							aut_LY_DO.setText(HSCT.getLY_DO());
						}else if (LENH==SUA_MUCDICH){
							HSCT.setMUC_DICH_SU_DUNG(Dialog_listview_ghichu.edt_ghichu.getText().toString());
							mdb.update_HSO_CHIETTINH(HSCT);
							aut_MUC_DICH_SU_DUNG.setText(HSCT.getMUC_DICH_SU_DUNG());
						}else if (LENH==SUA_KCCT2TBA){
							HSCT.setKC_CONGTO_DEN_TRAM(Dialog_listview_ghichu.edt_ghichu.getText().toString());
							mdb.update_HSO_CHIETTINH(HSCT);
							edt_KC_CONGTO_DEN_TRAM.setText(HSCT.getKC_CONGTO_DEN_TRAM());
						}else if (LENH==SUA_KCCT2TLHT){
							HSCT.setKC_CONGTO_DEN_LUOI(Dialog_listview_ghichu.edt_ghichu.getText().toString());
							mdb.update_HSO_CHIETTINH(HSCT);
							edt_KC_CONGTO_DEN_LUOI.setText(HSCT.getKC_CONGTO_DEN_LUOI());
						}else if (LENH==SUA_TRU){
							HSCT.setTRU(Dialog_listview_ghichu.edt_ghichu.getText().toString());
							mdb.update_HSO_CHIETTINH(HSCT);
							edt_TRU.setText(HSCT.getTRU());
						}else if (LENH==SUA_TAIMAX){
							HSCT.setTAI_MAX(Dialog_listview_ghichu.edt_ghichu.getText().toString());
							mdb.update_HSO_CHIETTINH(HSCT);
							edt_TAI_MAX.setText(HSCT.getTAI_MAX());
						}else if (LENH==SUA_CONGSUAT_DN_A){
							HSCT.setCONGSUAT_DENGHI_A(Dialog_listview_ghichu.edt_ghichu.getText().toString());
							mdb.update_HSO_CHIETTINH(HSCT);
							edt_CONGSUAT_DENGHI_A.setText(HSCT.getCONGSUAT_DENGHI_A());
						}else if (LENH==SUA_CONGSUAT_DN_V){
							HSCT.setCONGSUAT_DENGHI_V(Dialog_listview_ghichu.edt_ghichu.getText().toString());
							mdb.update_HSO_CHIETTINH(HSCT);
							edt_CONGSUAT_DENGHI_V.setText(HSCT.getCONGSUAT_DENGHI_V());
						}else if (LENH==SUA_CONGSUAT_DN_PHA){
							HSCT.setCONGSUAT_DENGHI_PHA(Dialog_listview_ghichu.edt_ghichu.getText().toString());
							mdb.update_HSO_CHIETTINH(HSCT);
							edt_CONGSUAT_DENGHI_PHA.setText(HSCT.getCONGSUAT_DENGHI_PHA());
						}else if (LENH==SUA_CONGSUAT_TT_A){
							HSCT.setCONGSUAT_THUC_TE_A(Dialog_listview_ghichu.edt_ghichu.getText().toString());
							mdb.update_HSO_CHIETTINH(HSCT);
							edt_CONGSUAT_THUC_TE_A.setText(HSCT.getCONGSUAT_THUC_TE_A());
						}else if (LENH==SUA_CONGSUAT_TT_KW){
							HSCT.setCONGSUAT_THUC_TE_KW(Dialog_listview_ghichu.edt_ghichu.getText().toString());
							mdb.update_HSO_CHIETTINH(HSCT);
							edt_CONGSUAT_THUC_TE_KW.setText(HSCT.getCONGSUAT_THUC_TE_KW());
						}else if (LENH==SUA_CONGTO_PHAI){
							HSCT.setCONGTO_PHAI(Dialog_listview_ghichu.edt_ghichu.getText().toString());
							mdb.update_HSO_CHIETTINH(HSCT);
							edt_CONGTO_PHAI.setText(HSCT.getCONGTO_PHAI());
						}else if (LENH==SUA_CONGTO_TRAI){
							HSCT.setCONGTO_TRAI(Dialog_listview_ghichu.edt_ghichu.getText().toString());
							mdb.update_HSO_CHIETTINH(HSCT);
							edt_CONGTO_TRAI.setText(HSCT.getCONGTO_TRAI());
						}else if (LENH==SUA_MA_KH){
							HSCT.setMA_KHACHHANG(Dialog_listview_ghichu.edt_ghichu.getText().toString());
							mdb.update_HSO_CHIETTINH(HSCT);
							edt_MA_KHACHHANG.setText(HSCT.getMA_KHACHHANG());
						}else if (LENH==SUA_MASO_STT){
							HSCT.setMASO_STT(Dialog_listview_ghichu.edt_ghichu.getText().toString());
							mdb.update_HSO_CHIETTINH(HSCT);
							edt_MASO_STT.setText(HSCT.getMASO_STT());
						}else if (LENH==SUA_DD_DINHMUC){
							HSCT.setDONGDIEN_DINHMUC(Dialog_listview_ghichu.edt_ghichu.getText().toString());
							mdb.update_HSO_CHIETTINH(HSCT);
							edt_DONGDIEN_DINHMUC.setText(HSCT.getDONGDIEN_DINHMUC());
						}else if (LENH==SUA_VITRI_TREO){
							HSCT.setVITRITREO(Dialog_listview_ghichu.edt_ghichu.getText().toString());
							mdb.update_HSO_CHIETTINH(HSCT);
							edt_VITRI_TREO.setText(HSCT.getVITRITREO());
						}else if (LENH==SUA_LOAI_XAY_DUNG){
							HSCT.setLOAI_XAY_DUNG(Dialog_listview_ghichu.edt_ghichu.getText().toString());
							mdb.update_HSO_CHIETTINH(HSCT);
							edt_LOAI_XAY_DUNG.setText(HSCT.getLOAI_XAY_DUNG());
						}else if (LENH==SUA_LOAI_NHA){
							HSCT.setLOAI_NHA(Dialog_listview_ghichu.edt_ghichu.getText().toString());
							mdb.update_HSO_CHIETTINH(HSCT);
							edt_LOAI_NHA.setText(HSCT.getLOAI_NHA());
						}
						// phu tai
						else if (LENH==SUA_PHUTAI_A){
							HSCT.setPHUTAI_A(Dialog_listview_ghichu.edt_ghichu.getText().toString());
							mdb.update_HSO_CHIETTINH(HSCT);
							edt_PHUTAI_A.setText(HSCT.getPHUTAI_A());
						}
						else if (LENH==SUA_PHUTAI_B){
							HSCT.setPHUTAI_B(Dialog_listview_ghichu.edt_ghichu.getText().toString());
							mdb.update_HSO_CHIETTINH(HSCT);
							edt_PHUTAI_B.setText(HSCT.getPHUTAI_B());
						}
						else if (LENH==SUA_PHUTAI_C){
							HSCT.setPHUTAI_C(Dialog_listview_ghichu.edt_ghichu.getText().toString());
							mdb.update_HSO_CHIETTINH(HSCT);
							edt_PHUTAI_C.setText(HSCT.getPHUTAI_C());
						}
						Obj_nhap_lieu NL = new Obj_nhap_lieu(Dialog_listview_ghichu.edt_ghichu.getText().toString(), LENH_NL);
						if (mdb.chua_ton_tai_nhap_lieu(NL)){
							DBAdapter.Insert_NHAP_LIEU(NL);
						}
						dialog_GC.dismiss();
						LENH =0;
					}else{
						Length_text.alert_length(CAPNHAT_HSO.this, dialog_GC.max_length);
					}
					
				}
			});
			WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
			lp.copyFrom(dialog_GC.getWindow().getAttributes());
			lp.width = w;
			lp.height = h;
			dialog_GC.getWindow().setAttributes(lp);
		}
	 public void SHOW_DIALOG_KW2A() {
		 try {
			 dialog_KW2A = new Dialog_Kw2A(CAPNHAT_HSO.this);
			 dialog_KW2A.show();
			 if (LENH==SUA_DD_DINHMUC){
				 Dialog_Kw2A.edt_congsuat.setText(Variables.HSCT_CHON.CONG_SUAT_TRAM);
				 Dialog_Kw2A.edt_congsuat.setFocusable(false);
			 } else if (LENH==SUA_CONGSUAT_TT_A){
				 Dialog_Kw2A.edt_congsuat.setText(Variables.HSCT_CHON.getCONGSUAT_THUC_TE_KW()); 
				 Dialog_Kw2A.edt_congsuat.addTextChangedListener(new TextWatcher() {
					
					@Override
					public void onTextChanged(CharSequence s, int start, int before, int count) {
						tinh_toan();
						
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
			 tinh_toan();
			 Dialog_Kw2A.rdo_1pha.setOnCheckedChangeListener(new OnCheckedChangeListener() {
					
					@Override
					public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
						if (isChecked){
							Dialog_Kw2A.rdo_3pha.setChecked(false);
						}else{
							Dialog_Kw2A.rdo_3pha.setChecked(true);
							tinh_toan();
						}
						
						
					}
				});
			 Dialog_Kw2A.rdo_3pha.setOnCheckedChangeListener(new OnCheckedChangeListener() {
						
						@Override
						public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
							if (isChecked){
								Dialog_Kw2A.rdo_1pha.setChecked(false);
							}else{
								Dialog_Kw2A.rdo_1pha.setChecked(true);
								tinh_toan();
							}
							
						}
					});
			 Dialog_Kw2A.edt_PF.addTextChangedListener(new TextWatcher() {
				
				@Override
				public void onTextChanged(CharSequence s, int start, int before, int count) {
					tinh_toan();
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
				Dialog_Kw2A.btn_ok.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						double PF = Thtcovert.String_to_double(Dialog_Kw2A.edt_PF.getText().toString());
						if (PF >0 & PF <=100){
							try {
								if (LENH==SUA_DD_DINHMUC){
									Variables.HSCT_CHON.setDONGDIEN_DINHMUC(Dialog_Kw2A.edt_KQ.getText().toString());
									mdb.update_HSO_CHIETTINH(Variables.HSCT_CHON);
									edt_DONGDIEN_DINHMUC.setText(Dialog_Kw2A.edt_KQ.getText().toString());
								}else if (LENH==SUA_CONGSUAT_TT_A){
									Variables.HSCT_CHON.setCONGSUAT_THUC_TE_KW(Dialog_Kw2A.edt_congsuat.getText().toString());
									Variables.HSCT_CHON.setCONGSUAT_THUC_TE_A(Dialog_Kw2A.edt_KQ.getText().toString());
									mdb.update_HSO_CHIETTINH(Variables.HSCT_CHON);
									edt_CONGSUAT_THUC_TE_KW.setText(Dialog_Kw2A.edt_congsuat.getText().toString());
									edt_CONGSUAT_THUC_TE_A.setText(Dialog_Kw2A.edt_KQ.getText().toString());
								}
								
							} catch (Exception e) {
								Custom_Toast.show_red_toast(CAPNHAT_HSO.this,"Lỗi cập nhật thông tin !");
							}
							
							dialog_KW2A.dismiss();
						}else{
							Custom_Toast.show_yellow_toast(CAPNHAT_HSO.this,getString(R.string.pf_lon_hon_khong_nho_hon_tram));
						}
						
						
					}
				});
				Dialog_Kw2A.btn_dong.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
							dialog_KW2A.dismiss();
					}
				});
				WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
				lp.copyFrom(dialog_KW2A.getWindow().getAttributes());
				lp.width = Tht_Screen.get_screen_width_percent(CAPNHAT_HSO.this, 60);
				lp.height = Tht_Screen.get_screen_heigth_percent(CAPNHAT_HSO.this, 90);
				dialog_KW2A.getWindow().setAttributes(lp);
		} catch (Exception e) {
			Custom_Toast.show_red_toast(CAPNHAT_HSO.this, "Lỗi mở dialog !");
		}
		}

	protected void tinh_toan() {
		double PF = Thtcovert.String_to_double(Dialog_Kw2A.edt_PF.getText().toString());
		if (PF >0 & PF <=100){
			if (Dialog_Kw2A.rdo_1pha.isChecked()==true){
				double CS = Thtcovert.String_to_double(Dialog_Kw2A.edt_congsuat.getText().toString());
				 Dialog_Kw2A.edt_KQ.setText(ThtFunction.Kw2A_1pha(1, CS, PF));
			}else if (Dialog_Kw2A.rdo_3pha.isChecked()==true){
				double CS = Thtcovert.String_to_double(Dialog_Kw2A.edt_congsuat.getText().toString());
				 Dialog_Kw2A.edt_KQ.setText(ThtFunction.Kw2A_1pha(3, CS, PF));
			}
		}else{
			Dialog_Kw2A.edt_KQ.setText("Nhập liệu không chính xác !");
		}
		
	}

	@Override
	public void onClick(View v) {
		if (v.getId()==R.id.edt_TENTRAM_BIENAP_ho_so_khao_sat 
				|| v.getId()==R.id.edt_MA_TRAM_ho_so_khao_sat
				|| v.getId()==R.id.edt_CONG_SUAT_TRAM_ho_so_khao_sat){
			SHOW_DIALOG_TRAM();
		} else if (v.getId()==R.id.aut_LY_DO_ho_so_khao_sat){
			LENH=SUA_LYDO;
			LENH_NL = Variables.NL_NHAP_LY_DO;
			text_dialog = ((EditText)v).getText().toString();
			SHOW_DIALOG_LY_DO(Variables.HSCT_CHON,Length_text.ML_LY_DO);
		} else if (v.getId()==R.id.aut_MUC_DICH_SU_DUNG_ho_so_khao_sat){
			LENH=SUA_MUCDICH;
			LENH_NL = Variables.NL_NHAP_MUC_DICH_SD;
			text_dialog = ((EditText)v).getText().toString();
			SHOW_DIALOG_LY_DO(Variables.HSCT_CHON,Length_text.ML_MUC_DICH_SU_DUNG);
		} else if (v.getId()==R.id.edt_KC_CONGTO_DEN_TRAM_ho_so_khao_sat){
			LENH=SUA_KCCT2TBA;
			LENH_NL = 0;
			text_dialog = ((EditText)v).getText().toString();
			SHOW_DIALOG_LY_DO(Variables.HSCT_CHON,Length_text.ML_KC_CONGTO_DEN_TRAM);
		} else if (v.getId()==R.id.edt_KC_CONGTO_DEN_LUOI_ho_so_khao_sat){
			LENH=SUA_KCCT2TLHT;
			LENH_NL = 0;
			text_dialog = ((EditText)v).getText().toString();
			SHOW_DIALOG_LY_DO(Variables.HSCT_CHON,Length_text.ML_KC_CONGTO_DEN_LUOI);
		} else if (v.getId()==R.id.edt_TRU_ho_so_khao_sat){
			LENH=SUA_TRU;
			LENH_NL = Variables.NL_TRU;
			text_dialog = ((EditText)v).getText().toString();
			SHOW_DIALOG_LY_DO(Variables.HSCT_CHON,Length_text.ML_TRU);
		} else if (v.getId()==R.id.edt_TAI_MAX_ho_so_khao_sat){
			LENH=SUA_TAIMAX;
			LENH_NL = Variables.NL_TAI_MAX;
			text_dialog = ((EditText)v).getText().toString();
			SHOW_DIALOG_LY_DO(Variables.HSCT_CHON,Length_text.ML_TAI_MAX);
		} else if (v.getId()==R.id.edt_CONGSUAT_DENGHI_A_ho_so_khao_sat){
			LENH=SUA_CONGSUAT_DN_A;
			LENH_NL = Variables.NL_CONGSUAT_DN_A;
			text_dialog = ((EditText)v).getText().toString();
			SHOW_DIALOG_LY_DO(Variables.HSCT_CHON,Length_text.ML_CONGSUAT_DENGHI_A);
		} else if (v.getId()==R.id.edt_CONGSUAT_DENGHI_PHA_ho_so_khao_sat){
			LENH=SUA_CONGSUAT_DN_PHA;
			LENH_NL = Variables.NL_CONGSUAT_DN_PHA;
			text_dialog = ((EditText)v).getText().toString();
			SHOW_DIALOG_LY_DO(Variables.HSCT_CHON,Length_text.ML_CONGSUAT_DENGHI_PHA);
		} else if (v.getId()==R.id.edt_CONGSUAT_DENGHI_V_ho_so_khao_sat){
			LENH=SUA_CONGSUAT_DN_V;
			LENH_NL = Variables.NL_CONGSUAT_DN_V;
			text_dialog = ((EditText)v).getText().toString();
			SHOW_DIALOG_LY_DO(Variables.HSCT_CHON,Length_text.ML_CONGSUAT_DENGHI_V);
		} else if (v.getId()==R.id.edt_CONGSUAT_THUC_TE_A_ho_so_khao_sat){
			LENH=SUA_CONGSUAT_TT_A;
			LENH_NL = Variables.NL_CONGSUAT_TT_A;
			text_dialog = ((EditText)v).getText().toString();
			SHOW_DIALOG_KW2A();
		} else if (v.getId()==R.id.edt_CONGSUAT_THUC_TE_KW_ho_so_khao_sat){
			LENH=SUA_CONGSUAT_TT_KW;
			LENH_NL = Variables.NL_CONGSUAT_TT_KW;
			text_dialog = ((EditText)v).getText().toString();
			SHOW_DIALOG_LY_DO(Variables.HSCT_CHON,Length_text.ML_CONGSUAT_THUC_TE_KW);
		}else if (v.getId()==R.id.edt_CONGTO_TRAI_ho_so_khao_sat){
			LENH=SUA_CONGTO_TRAI;
			LENH_NL = Variables.NL_CONG_TO;
			text_dialog = ((EditText)v).getText().toString();
			SHOW_DIALOG_LY_DO(Variables.HSCT_CHON,Length_text.ML_CONGTO_TRAI);
		}else if (v.getId()==R.id.edt_CONGTO_PHAI_ho_so_khao_sat){
			LENH=SUA_CONGTO_PHAI;
			LENH_NL = Variables.NL_CONG_TO;
			text_dialog = ((EditText)v).getText().toString();
			SHOW_DIALOG_LY_DO(Variables.HSCT_CHON,Length_text.ML_CONGTO_PHAI);
		}else if (v.getId()==R.id.edt_MA_KHACHHANG){
			LENH=SUA_MA_KH;
			LENH_NL = Variables.NL_MA_KH;
			text_dialog = ((EditText)v).getText().toString();
			SHOW_DIALOG_LY_DO(Variables.HSCT_CHON,Length_text.ML_MA_KHACHHANG);
		}else if (v.getId()==R.id.edt_MASO_STT_ho_so_khao_sat){
			LENH=SUA_MASO_STT;
			LENH_NL = Variables.NL_MASO_STT;
			text_dialog = ((EditText)v).getText().toString();
			SHOW_DIALOG_LY_DO(Variables.HSCT_CHON,Length_text.ML_MASO_STT);
		}else if (v.getId()==R.id.edt_DONGDIEN_DINHMUC_ho_so_khao_sat){
			LENH=SUA_DD_DINHMUC;
			LENH_NL = Variables.NL_DD_DINHMUC;
			text_dialog = ((EditText)v).getText().toString();
			SHOW_DIALOG_KW2A();
		}else if (v.getId()==R.id.edt_VI_TRI_TREO_ho_so_khao_sat){
			LENH=SUA_VITRI_TREO;
			LENH_NL = Variables.NL_VITRI_TREO;
			text_dialog = ((EditText)v).getText().toString();
			SHOW_DIALOG_LY_DO(Variables.HSCT_CHON,Length_text.ML_VITRITREO);
		}else if (v.getId()==R.id.edt_LOAI_XAY_DUNG_ho_so_khao_sat){
			LENH=SUA_LOAI_XAY_DUNG;
			LENH_NL = Variables.NL_LOAI_XD;
			text_dialog = ((EditText)v).getText().toString();
			SHOW_DIALOG_LY_DO(Variables.HSCT_CHON,Length_text.ML_LOAI_XAY_DUNG);
		}else if (v.getId()==R.id.edt_LOAI_NHA_ho_so_khao_sat){
			LENH=SUA_LOAI_NHA;
			LENH_NL = Variables.NL_LOAI_NHA;
			text_dialog = ((EditText)v).getText().toString();
			SHOW_DIALOG_LY_DO(Variables.HSCT_CHON,Length_text.ML_LOAI_NHA);
		}
		// phu tai
		else if (v.getId()==R.id.edt_PHUTAI_A_ho_so_khao_sat){
			LENH=SUA_PHUTAI_A;
			LENH_NL = Variables.NL_PHUTAI_A;
			text_dialog = ((EditText)v).getText().toString();
			SHOW_DIALOG_LY_DO(Variables.HSCT_CHON,Length_text.ML_PHUTAI_A);
		}
		else if (v.getId()==R.id.edt_PHUTAI_B_ho_so_khao_sat){
			LENH=SUA_PHUTAI_B;
			LENH_NL = Variables.NL_PHUTAI_B;
			text_dialog = ((EditText)v).getText().toString();
			SHOW_DIALOG_LY_DO(Variables.HSCT_CHON,Length_text.ML_PHUTAI_B);
		}
		else if (v.getId()==R.id.edt_PHUTAI_C_ho_so_khao_sat){
			LENH=SUA_PHUTAI_C;
			LENH_NL = Variables.NL_PHUTAI_C;
			text_dialog = ((EditText)v).getText().toString();
			SHOW_DIALOG_LY_DO(Variables.HSCT_CHON,Length_text.ML_PHUTAI_C);
		}
		// don vi
		else if (v.getId()==R.id.edt_DV_CAPVATTU_capnhathoso){
			LENH=SUA_DV_CAPVATTU;
			donvi_thuchien = ((EditText)v).getText().toString();
			show_alert_list("Chọn đơn vị thực hiện");
		}
		else if (v.getId()==R.id.edt_DV_THICONG_capnhathoso){
			LENH=SUA_DV_THICONG;
			donvi_thuchien = ((EditText)v).getText().toString();
			show_alert_list("Chọn đơn vị thực hiện");
		}
		else if (v.getId()==R.id.edt_DV_THIETKE_capnhathoso){
			LENH=SUA_DV_THIETKE;
			donvi_thuchien = ((EditText)v).getText().toString();
			show_alert_list("Chọn đơn vị thực hiện");
		}
	}
	public void cap_nhat_du_lieu(View v){
		new load_du_lieu(lenh_save_data).execute();
	}
	// load du lieu
			class load_du_lieu extends AsyncTask<String, String, String> {
				int lenh_async=0;
				public load_du_lieu (int lenh){
					this.lenh_async = lenh;
				}
				@Override
				protected void onPreExecute() {
					super.onPreExecute();
					pDialog = new ProgressDialog(CAPNHAT_HSO.this);
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
						if (lenh_async==lenh_save_data){
							LAY_DU_LIEU_TU_EDITTEXT();
							mdb.update_HSO_CHIETTINH(Variables.HSCT_CHON);
						}else if (lenh_async == lenh_load_data){
							LOAD_DATA();
						}
						
					} catch (Exception e) {
						
					}

					return null;

				}

				protected void onPostExecute(String result) {
					pDialog.dismiss();
					mcountdown.cancel();
					runOnUiThread(new Runnable() {
						public void run() {
							if (lenh_async==lenh_save_data){
								finish();
							}else if (lenh_async == lenh_load_data){
								SET_TO_TEXTVIEW();
								KT_CHECKBOX();
							}
						}
					});

				}

			}
			public String show_alert_list(String title){
				final List<String> list = new ArrayList<String>();
				list.add("Điện Lực");
				list.add("Khách hàng");
				 	CharSequence [] items = list.toArray(new CharSequence[list.size()]);
			        AlertDialog.Builder builder = new AlertDialog.Builder(CAPNHAT_HSO.this);
			        builder.setTitle(title);
			        builder.setItems(items, new DialogInterface.OnClickListener() {
			            public void onClick(DialogInterface dialog, int item) {
			            	donvi_thuchien = list.get(item);
			            	if (donvi_thuchien.length()>0){
			    				if (LENH==SUA_DV_CAPVATTU){
			    					Variables.HSCT_CHON.setDV_CAP_VTU(donvi_thuchien);
									mdb.update_HSO_CHIETTINH(Variables.HSCT_CHON);
									edt_DVI_CAPVATTU.setText(donvi_thuchien);
			    				} else if (LENH==SUA_DV_THICONG){
			    					Variables.HSCT_CHON.setDV_THICONG(donvi_thuchien);
									mdb.update_HSO_CHIETTINH(Variables.HSCT_CHON);
									edt_DVI_THICONG.setText(donvi_thuchien);
			    				} if (LENH==SUA_DV_THIETKE){
			    					Variables.HSCT_CHON.setDV_THIETKE(donvi_thuchien);
									mdb.update_HSO_CHIETTINH(Variables.HSCT_CHON);
									edt_DVI_THIETKE.setText(donvi_thuchien);
			    				}
			    			}	
			            }
			        });
			        AlertDialog alert = builder.create();
			        alert.show();
			        return "";
			}

}
