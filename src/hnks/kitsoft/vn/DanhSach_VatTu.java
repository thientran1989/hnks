package hnks.kitsoft.vn;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import hnks.kitsoft.utils.Custom_Toast;
import hnks.kitsoft.utils.LabelFull;
import hnks.kitsoft.utils.Link;
import hnks.kitsoft.utils.Variables;
import hnks.kitsoft.vn.R;
import hnks.kitsoft.vn.custom_dialog.AndroidLDialog;
import hnks.kitsoft.vn.list.Adapter_VATTU;
import hnks.kitsoft.vn.object.CallbackResult;
import hnks.kitsoft.vn.object.Obj_D_NHOM_VTU;
import hnks.kitsoft.vn.object.Obj_D_VATTU;
import hnks.kitsoft.vn.object.ObjectClient;
import hnks.kitsoft.vn.server.Function;
import hnks.kitsoft.vn.server.M_READ_JSON;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.thtsoft.adddata.Tht_add_data;
import com.thtsoftlib.function.ThtShow;
import com.thtsoftlib.function.Tht_Screen;

public class DanhSach_VatTu extends Activity {

	private EditText edtTimkiemDsvt;
	private Spinner spnNHOMVTUDsvt;
	private ListView lvVattuDsvt;

	Adapter_VATTU mAdapter_vattu = null;
	List<Obj_D_VATTU> list_vattu=null;
	public static List<Obj_D_NHOM_VTU> list_nhom_vattu = null;

	final int LENH_GET_VATTU = 1, LENH_GET_VTU_NHOM = 4;
	int NHOM_CHON;

	ObjectClient oCL = null;
	CallbackResult mCB = null;
	public static Obj_D_VATTU my_oDVT =null;
	public static String my_LENH="",LENH_SUA="SUA",LENH_THEM="THEM";

	private ProgressDialog pDialog;
	CountDownTimer mcountdown;
	final int time_connnect = 180000;
	AndroidLDialog androidLDialog;

	String url = "";
	int my_pos=-1;

	private void findViews() {
		try {
			edtTimkiemDsvt = (EditText) findViewById(R.id.edt_timkiem_dsvt);
			spnNHOMVTUDsvt = (Spinner) findViewById(R.id.spn_NHOM_VTU_dsvt);
			lvVattuDsvt = (ListView) findViewById(R.id.lv_vattu_dsvt);
		} catch (Exception e) {
			ThtShow.show_toast(getApplicationContext(), "LOI VIEW "+e.toString());
		}
		
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Tht_Screen.hide_title(this);
		setContentView(R.layout.danhsach_vattu);
		findViews();
		get_vattu();

		spnNHOMVTUDsvt.setOnItemSelectedListener(new OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				if (arg2 == 0) {
					if(list_vattu!=null){
						mAdapter_vattu = new Adapter_VATTU(
								DanhSach_VatTu.this, list_vattu);
					}
					if (mAdapter_vattu != null) {
						lvVattuDsvt.setAdapter(mAdapter_vattu);
					}
				} else {
					NHOM_CHON = list_nhom_vattu.get(arg2).getNHOM();
					if (mAdapter_vattu != null) {
						mAdapter_vattu.get_vattu_nhom(NHOM_CHON);
					}
				}

			}

			public void onNothingSelected(AdapterView<?> arg0) {
			}

		});

		edtTimkiemDsvt.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			@Override
			public void afterTextChanged(Editable s) {
			}
		});

	}
	
	public void set_spinner_nhom_vattu() {
		try {
			List<String> labels_nhom = new ArrayList<String>();
			if (list_nhom_vattu != null) {
				for (Obj_D_NHOM_VTU NVT : list_nhom_vattu) {
					labels_nhom.add(NVT.getTEN_NHOM());
				}
			}
			Tht_add_data.set_spinner(DanhSach_VatTu.this, labels_nhom,
					spnNHOMVTUDsvt);
		} catch (Exception e) {
			
		}
		
	}

	public void to_them_vattu(View v) {
		my_LENH = LENH_THEM;
		Intent i = new Intent(DanhSach_VatTu.this, Ac_ThemVatTu.class);
		startActivityForResult(i, 123);
	}
	public void get_vattu(){
		url = Link.get_link_connect(Variables.DNV.getIPServer());
		oCL = new ObjectClient();
		oCL.setParam1(Variables.DNV.MaDV);
		oCL.setParam2(Variables.DNV.UserName);
		oCL.setParam3(Variables.DNV.MatKhau);
		oCL.setParam5(Main_Activity.IMEI);
		oCL.setCommand(LabelFull.get_vattu);
		new Conect_server_sign_async().execute();
	}

	// gui du lieu
	class Conect_server_sign_async extends AsyncTask<String, String, String> {
		public String TAG_KQ = "Không kết nối được server";

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(DanhSach_VatTu.this);
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
			mcountdown = new CountDownTimer(time_connnect, 1000) {
				public void onTick(long millisUntilFinished) {
					pDialog.setMessage(getString(R.string.alert_dang_lay_du_lieu)
							+ " ..."
							+ String.valueOf(millisUntilFinished / 1000)
							+ " giây !");
				}

				public void onFinish() {
					pDialog.dismiss();
					Custom_Toast.show_red_toast(DanhSach_VatTu.this,
							"Quá thời gian lấy dữ liệu !");
				}
			}.start();
		}

		protected String doInBackground(String... kq) {
			try {
				upload(url);
			} catch (Exception e) {
			}

			return null;

		}

		protected void onPostExecute(String result) {
			pDialog.dismiss();
			mcountdown.cancel();
			runOnUiThread(new Runnable() {
				public void run() {
					if (mCB != null) {
						if (mCB.getResultString().equals("OK")) {
							if(mCB.getCommand().equals(LabelFull.get_vattu)){
//								show_alert("KẾT QUẢ", "Lấy vật tư thành công !");
								set_spinner_nhom_vattu();
								if(mAdapter_vattu!=null){
									lvVattuDsvt.setAdapter(mAdapter_vattu);
								}
							}else if(mCB.getCommand().equals(LabelFull.del_vattu)){
								oCL.setCommand(LabelFull.get_vattu);
								new Conect_server_sign_async().execute();
							}else{
								show_alert("KẾT QUẢ",mCB.getCommand());
							}
//							
						} else {
							show_alert("KẾT QUẢ", mCB.getResultString());
						}
					} else {
						show_alert("KẾT QUẢ",
								getString(R.string.alert_not_connect_server));
					}
				}
			});

		}

		public void upload(String urlServer) {
			TAG_KQ = getString(R.string.alert_not_connect_server);
			HttpURLConnection connection = null;
			DataOutputStream outputStream = null;
			DataInputStream dis = null;
			try {
				if (urlServer.contains("8888"))
					urlServer = urlServer.replace(Link.NAME_DIRECT, "/");
				URL url = new URL(urlServer);
				URLConnection urlConn = url.openConnection();
				urlConn.setConnectTimeout(3000);
				urlConn.setReadTimeout(180000);
				connection = (HttpURLConnection) urlConn;
				connection.setChunkedStreamingMode(0);
				connection.setRequestMethod("POST");
				connection.setRequestProperty("Content-Type",
						"application/octet-stream");
				connection.setRequestProperty("Connection", "Keep-Alive");
				connection.setAllowUserInteraction(true);
				connection.setUseCaches(false);
				connection.setDoInput(true);
				connection.setDoOutput(true);
				outputStream = new DataOutputStream(
						connection.getOutputStream());
				TAG_KQ = Function.alldata2server(oCL, null, null, null, null,
						null, null, null);

				Function.write_String_to_byte(outputStream, TAG_KQ);
				outputStream.flush();
				dis = new DataInputStream(connection.getInputStream());
				TAG_KQ = "";
				TAG_KQ = Function.byte_to_String(dis);
				Log.i("KQSV", TAG_KQ);
				outputStream.close();
				dis.close();
				JsonParser jp = new JsonParser();
				JsonObject mJO = jp.parse(TAG_KQ).getAsJsonObject();
				TAG_KQ = "mJO :" + mJO.toString();
				try {
					mCB = M_READ_JSON.read_callback(mJO);
				} catch (Exception e) {

				}
				if(mCB!=null){
					try {
						list_nhom_vattu = mCB.getList_nhom_vattu();
					} catch (Exception e) {
						
					}
					try {
						list_vattu = mCB.getList_vattu();
					} catch (Exception e) {
						
					}
					if(list_vattu!=null){
						mAdapter_vattu = new Adapter_VATTU(
								DanhSach_VatTu.this, list_vattu);
					}
				}
			} catch (Exception ex) {
				if (urlServer.equals(Link.get_link_connect(Variables.DNV
						.getIPServer()))) {
					String URL_new = Link.get_link_connect(Variables.DNV
							.getIPLocal());
					url = URL_new;
					upload(URL_new);
				} else {
					TAG_KQ = getString(R.string.alert_not_connect_server)
							+ "\n" + Link.get_IP(urlServer);
				}

			}

		}

	}

	public void show_alert(String title, String contain) {
		androidLDialog = new AndroidLDialog.Builder(DanhSach_VatTu.this)
				.Title(title).Message(contain)
				.setPositiveButton("OK", new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						androidLDialog.hide();
					}
				}).show();
		androidLDialog.setCancelable(false);
		androidLDialog.setTitleColor(getResources().getColor(R.color.cyan));
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(requestCode==123 & resultCode==RESULT_OK){
			ThtShow.show_toast(getApplicationContext(), "OK");
			if(list_vattu!=null){
				list_vattu.add(0,Ac_ThemVatTu.oDVT);
				mAdapter_vattu = new Adapter_VATTU(
						DanhSach_VatTu.this, list_vattu);
				lvVattuDsvt.setAdapter(mAdapter_vattu);
			}
		}else{
			ThtShow.show_toast(getApplicationContext(), "KQ "+RESULT_OK);
		}
		
	}
	public void xoa_vattu(Obj_D_VATTU oDVT,int pos){
		my_pos =pos;
		oCL.setCommand(LabelFull.del_vattu);
		oCL.setoDVT(oDVT);
		new Conect_server_sign_async().execute();
	}
	public void sua_vattu(Obj_D_VATTU oDVT){
		my_oDVT = oDVT;
		my_LENH = LENH_SUA;
		Intent i = new Intent(DanhSach_VatTu.this, Ac_ThemVatTu.class);
		startActivityForResult(i, 124);
	}
	
	
	
}
