package hnks.kitsoft.vn;

import java.util.ArrayList;
import java.util.List;

import hnks.kitsoft.utils.Utils;
import hnks.kitsoft.utils.Variables;
import hnks.kitsoft.vn.R;
import hnks.kitsoft.vn.database.DBAdapter;
import hnks.kitsoft.vn.list.Lst_HSO_CHIETTINH;
import hnks.kitsoft.vn.object.Obj_HSO_CHIETTINH;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.thtsoft.adddata.Tht_add_data;
import com.thtsoftlib.function.Tht_Screen;
import com.thtsoftlib.function.Thtcovert;

public class DSHS_CHIETTINH_Activity extends Activity{
    public static ProgressDialog progDialog;
	ListView  lv_hso_chiettinh;
	Spinner spn_trangthai;
	public Lst_HSO_CHIETTINH mAdapter;
	final int CONTEXT_MENU_UPDATE = 1;
	DBAdapter mdb;
	EditText edt_SEARCH;
	TextView tv_soluong;
	List<Obj_HSO_CHIETTINH> list_all_hoso;
	private ProgressDialog pDialog;
	CountDownTimer mcountdown;
	final int time_connnect = 40000;
	int TT_CHON=0;
	int LENH=0;
	final int GET_MOI=1;
	final int RELOAD=2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mdb=new DBAdapter(this);
		mdb.open();
		Tht_Screen.hide_title(this);
		setContentView(R.layout.hso_chiettinh);
		Tht_Screen.hide_keyboard(this);
		lv_hso_chiettinh = (ListView)findViewById(R.id.lvHso_Chiettinh_hso_chiettinh);
		tv_soluong =(TextView)findViewById(R.id.tv_SO_LUONG_hso_chiettinh);
		edt_SEARCH=(EditText)findViewById(R.id.edt_SEARCH_KH_hso_chiettinh);
		spn_trangthai = (Spinner)findViewById(R.id.spn_trangthai_ds_hoso);
		set_spinner_trang_thai_hs();
		LENH=GET_MOI;
		spn_trangthai.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				TT_CHON =arg2;
				new load_du_lieu(LENH).execute();	
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			}
		});

		edt_SEARCH.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				mAdapter.get_SEARCH(s.toString());
				mAdapter.notifyDataSetChanged();
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}
			
			@Override
			public void afterTextChanged(Editable s) {
			}
		});

		//Neu chua co HOSO nao thi bat dau lay DS HoSo
		//Neu co dang co thi vao danh sach ho so
		  //Kiem Tra Rac va xoa: Nhung HoSo Khong co HoSoChietTinh thi
		  //xoa HoSo Hinh, HoSo Ghi Chu, HoSo ToaDo ....
			  //Co nut xoa HoSo ma Tren CMIS da chuyen sang buoc ke tiep!
		      //De lay lai HoSo thi phai xoa HoSo do tren Mobile va lay lai HoSo
		
	}
/*	
    @Override
	public boolean onContextItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case CONTEXT_MENU_UPDATE:
			DIALOG_YES_NO();
			return true;
		}
		return (super.onOptionsItemSelected(item));
	}

	public void DIALOG_YES_NO(){
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(
				DSHS_CHIETTINH_Activity.this);
		alertDialog.setTitle("Cảnh Báo");
		alertDialog
				.setMessage("Bạn có muốn lấy dữ liệu khách hàng từ máy chủ?")
				.setPositiveButton("Đồng ý",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								//Lst_HSO_CHIETTINH.MA_DVIQLY_ChonLoadLai
								//Lst_HSO_CHIETTINH.MA_YCAU_KNAI_ChonLoadLai
								mdb.delete_VTU_BY_MA_YCAU_KNAI(
										Lst_HSO_CHIETTINH.MA_YCAU_KNAI_ChonLoadLai, -1);
								mdb.Xoa_HSO_CHIETTINH_BY_MA_YCAU_KNAI(
										Lst_HSO_CHIETTINH.MA_YCAU_KNAI_ChonLoadLai);
								mAdapter.notifyDataSetChanged();
							}
						})
				// Lenh Cancel
				.setNegativeButton("Hủy",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								dialog.cancel();
							}
						});
		AlertDialog alert = alertDialog.create();
		alert.show();
		
	}
	*/
	public void thoat(View v){
		finish();
	}
	public void to_intent_khachhang(){
		Intent i = new Intent(DSHS_CHIETTINH_Activity.this,Ac_giaodien_khachhang.class);
		startActivityForResult(i, Utils.REQ_CODE);;
	}
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == Utils.REQ_CODE) {
			if (resultCode == RESULT_OK) {
				mAdapter.notifyDataSetChanged();
			}

		}

		super.onActivityResult(requestCode, resultCode, data);
	}
	public void set_spinner_trang_thai_hs() {
//		mdb.open();
		List<String> labels_trangthai = new ArrayList<String>();
		if (labels_trangthai!=null){
			labels_trangthai.clear();
			labels_trangthai.add(getString(R.string.tat_ca));
			labels_trangthai.add(getString(R.string.da_khao_sat));
			labels_trangthai.add(getString(R.string.chua_khao_sat));
			labels_trangthai.add(getString(R.string.dang_khao_sat));
		}
		Tht_add_data.set_spinner(DSHS_CHIETTINH_Activity.this, labels_trangthai,
				spn_trangthai);
	}
	// load du lieu
			class load_du_lieu extends AsyncTask<String, String, String> {
				int lenh =0;
				public load_du_lieu(int lenh){
					this.lenh=lenh;
				}
				@Override
				protected void onPreExecute() {
					super.onPreExecute();
					pDialog = new ProgressDialog(DSHS_CHIETTINH_Activity.this);
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
					if (lenh==GET_MOI){
						try {
							list_all_hoso = mdb.get_list_hoso(Variables.DNV);
							mAdapter=new Lst_HSO_CHIETTINH(mdb,list_all_hoso,DSHS_CHIETTINH_Activity.this);
						} catch (Exception e) {
							
						}
					} else if (lenh==RELOAD){
						mAdapter.get_hso_da_ks(TT_CHON);
					}
					

					return null;

				}

				protected void onPostExecute(String result) {
					pDialog.dismiss();
					mcountdown.cancel();
					runOnUiThread(new Runnable() {
						public void run() {
							if (lenh==GET_MOI){
								lv_hso_chiettinh.setAdapter(mAdapter);
								LENH = RELOAD;
							}else if (lenh==RELOAD){
								mAdapter.notifyDataSetChanged();
							}
							tv_soluong.setText(Thtcovert.int_to_String(mAdapter.getCount()));
							
						}

						
					});

				}

			}

}
