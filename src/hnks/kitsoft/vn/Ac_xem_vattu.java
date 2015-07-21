package hnks.kitsoft.vn;

import java.util.ArrayList;
import java.util.List;

import hnks.kitsoft.utils.Custom_Toast;
import hnks.kitsoft.utils.Variables;
import hnks.kitsoft.vn.R;
import hnks.kitsoft.vn.database.DBAdapter;
import hnks.kitsoft.vn.list.Adapter_D_VATTU_BY_MAU;
import hnks.kitsoft.vn.object.Obj_D_MAU;
import hnks.kitsoft.vn.object.Obj_D_VATTU;
import tht.library.crouton.Style;
import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.thtsoft.adddata.Tht_add_data;
import com.thtsoftlib.function.ThtShow;
import com.thtsoftlib.function.Tht_Screen;

public class Ac_xem_vattu extends Activity {
	
	DBAdapter mdb;
	Adapter_D_VATTU_BY_MAU mAdapter;
	List<Obj_D_VATTU> list_vattu;
	List<Obj_D_VATTU> list_vattu_mau =null;
	List<Obj_D_MAU> list_mau;
	ListView lv_vattu;
	EditText edt_search;
	Spinner spn_MAU_VTU;
	String MAU_CHON;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Tht_Screen.hide_title(this);
		setContentView(R.layout.ac_xem_vattu);
		Tht_Screen.hide_keyboard(this);
		lv_vattu = (ListView)findViewById(R.id.lv_vattu_xem_vattu);
		spn_MAU_VTU = (Spinner)findViewById(R.id.spn_mau_xem_vattu);
		edt_search = (EditText)findViewById(R.id.edt_search_ac_xem_vat_tu);
		edt_search.setHint(getString(R.string.tim_kiem_vat_tu));
		mdb = new DBAdapter(this);
		mdb.open();
		list_vattu = mdb.get_list_all_vattu(Variables.DNV.getMaDV());
		mAdapter = new Adapter_D_VATTU_BY_MAU(list_vattu,Ac_xem_vattu.this);
		lv_vattu.setAdapter(mAdapter);
		set_spinner_mau_vattu();
		spn_MAU_VTU.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				if (arg2!=0){
					mdb.open();
					MAU_CHON = Variables.list_mau_vattu.get(arg2);
					list_mau = mdb.get_ARR_VATTU_BY_MAU(MAU_CHON);
					if (list_vattu_mau!=null){
						list_vattu_mau.clear();
					}
					list_vattu_mau=new ArrayList<Obj_D_VATTU>();
					try {
						for (Obj_D_MAU DM : list_mau) {
							if (DM.getMA_VTU()!=null){
									for (Obj_D_VATTU DVT : list_vattu) {
										if (DVT.getMA_VTU().equals(DM.getMA_VTU())){
											list_vattu_mau.add(DVT);
										}
									}
							}
						}
					} catch (Exception e) {
						ThtShow.show_crouton_toast(Ac_xem_vattu.this, e.toString(), Style.ALERT);
					}
					
					mAdapter = new Adapter_D_VATTU_BY_MAU(list_vattu_mau,Ac_xem_vattu.this);
				}else{
					MAU_CHON="null";
					mAdapter = new Adapter_D_VATTU_BY_MAU(list_vattu,Ac_xem_vattu.this);
				}
				lv_vattu.setAdapter(mAdapter);
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		edt_search.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				mAdapter.get_search_vattu(s.toString());
				mAdapter.notifyDataSetChanged();
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	public void thoat(View v){
		finish();
	}
	// Spinner MAU_VTU
		public void set_spinner_mau_vattu() {
				Variables.list_mau_vattu = mdb.set_spn_mau_vattu();
				Variables.list_mau_vattu.add(0,"Tất cả mẫu");
			if (Variables.list_mau_vattu != null) {
				Tht_add_data.set_spinner(Ac_xem_vattu.this,
						Variables.list_mau_vattu, spn_MAU_VTU);
			}
		}
		public void xoa_1_mau(View v) {
			if (MAU_CHON.equals("null")){
				Custom_Toast.show_yellow_toast(Ac_xem_vattu.this, "Không thể xoá tất cả");
			}else{
				final Dialog dialog = new Dialog(this, R.style.FullHeightDialog);
				dialog.setContentView(R.layout.dialog_yes_no);
				dialog.setCancelable(true);
				TextView message = (TextView) dialog
						.findViewById(R.id.tvmessagedialogtext);
				message.setText(getString(R.string.xoa_mau_dang_chon));
				Button yes = (Button) dialog.findViewById(R.id.bmessageDialogYes);
				yes.setOnClickListener(new OnClickListener() {
					public void onClick(View v) {
						try {
							 mdb.delete_MAU_VATTU(MAU_CHON);
							 set_spinner_mau_vattu();
							 Custom_Toast.show_blue_toast(Ac_xem_vattu.this, "Xoá mẫu thành công !");
						} catch (Exception e) {
							ThtShow.show_crouton_toast(Ac_xem_vattu.this,
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
			
		}
		
}
