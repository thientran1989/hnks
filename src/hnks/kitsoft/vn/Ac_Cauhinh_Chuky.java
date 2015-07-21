package hnks.kitsoft.vn;

import hnks.kitsoft.utils.Custom_Toast;
import hnks.kitsoft.utils.LabelFull;
import hnks.kitsoft.utils.Link;
import hnks.kitsoft.utils.Variables;
import hnks.kitsoft.vn.custom_dialog.AndroidLDialog;
import hnks.kitsoft.vn.object.CallbackResult;
import hnks.kitsoft.vn.object.D_CHUKY;
import hnks.kitsoft.vn.object.ObjectClient;
import hnks.kitsoft.vn.server.Function;
import hnks.kitsoft.vn.server.M_READ_JSON;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.thtsoft.adddata.Tht_add_data;
import com.thtsoftlib.function.ThtShow;
import com.thtsoftlib.function.Tht_Screen;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.EditText;
import android.widget.Spinner;

public class Ac_Cauhinh_Chuky extends Activity {

	private ProgressDialog pDialog;
	CountDownTimer mcountdown;
	final int time_connnect = 180000;
	AndroidLDialog androidLDialog;

	ObjectClient oCL = null;
	CallbackResult mCB = null;

	List<D_CHUKY> list_CK = null;
	List<String> labels_nhom =null;

	String url = "";
	String MAU = "";

	private Spinner spnMau;
	private EditText edtTieude;
	private EditText edtCancu;
	private EditText edtChucvu1;
	private EditText edtNguoiky1;
	private EditText edtChucvu2;
	private EditText edtNguoiky2;
	private EditText edtChucvu3;
	private EditText edtNguoiky3;
	private EditText edtChucvu4;
	private EditText edtNguoiky4;
	private EditText edtChucvu5;
	private EditText edtNguoiky5;

	/**
	 * Find the Views in the layout<br />
	 * <br />
	 * Auto-created on 2015-07-10 13:41:43 by Android Layout Finder
	 * (http://www.buzzingandroid.com/tools/android-layout-finder)
	 */
	private void findViews() {
		spnMau = (Spinner) findViewById(R.id.spn_mau);
		edtTieude = (EditText) findViewById(R.id.edt_tieude);
		edtCancu = (EditText) findViewById(R.id.edt_cancu);
		edtChucvu1 = (EditText) findViewById(R.id.edt_chucvu1);
		edtNguoiky1 = (EditText) findViewById(R.id.edt_nguoiky1);
		edtChucvu2 = (EditText) findViewById(R.id.edt_chucvu2);
		edtNguoiky2 = (EditText) findViewById(R.id.edt_nguoiky2);
		edtChucvu3 = (EditText) findViewById(R.id.edt_chucvu3);
		edtNguoiky3 = (EditText) findViewById(R.id.edt_nguoiky3);
		edtChucvu4 = (EditText) findViewById(R.id.edt_chucvu4);
		edtNguoiky4 = (EditText) findViewById(R.id.edt_nguoiky4);
		edtChucvu5 = (EditText) findViewById(R.id.edt_chucvu5);
		edtNguoiky5 = (EditText) findViewById(R.id.edt_nguoiky5);

		url = Link.get_link_connect(Variables.DNV.getIPServer());
	}

	public void get_mau(D_CHUKY oDCK) {
		url = Link.get_link_connect(Variables.DNV.getIPServer());
		oCL = new ObjectClient();
		oCL.setParam1(Variables.DNV.MaDV);
		oCL.setParam2(Variables.DNV.UserName);
		oCL.setParam3(Variables.DNV.MatKhau);
		oCL.setParam5(Main_Activity.IMEI);
		oCL.setCommand(LabelFull.get_chuky);
		oCL.setoCK(oDCK);
		new Conect_server_sign_async().execute();
	}
	public void insert_mau(D_CHUKY oDCK) {
		url = Link.get_link_connect(Variables.DNV.getIPServer());
		oCL = new ObjectClient();
		oCL.setParam1(Variables.DNV.MaDV);
		oCL.setParam2(Variables.DNV.UserName);
		oCL.setParam3(Variables.DNV.MatKhau);
		oCL.setParam5(Main_Activity.IMEI);
		oCL.setCommand(LabelFull.ins_chuky);
		oCL.setoCK(oDCK);
		new Conect_server_sign_async().execute();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Tht_Screen.hide_title(this);
		setContentView(R.layout.ac_cauhinh_chuky);
		findViews();
		
		set_spinner_mau();
		
		spnMau.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				D_CHUKY oDCK = new D_CHUKY();
				oDCK.setMA_DVIQLY(Variables.DNV.MaDV);
				oDCK.setMAU(labels_nhom.get(arg2));
				MAU = (labels_nhom.get(arg2));
				get_mau(oDCK);
			}
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				
				
			}
		});
	}

	// gui du lieu
	class Conect_server_sign_async extends AsyncTask<String, String, String> {
		public String TAG_KQ = "Không kết nối được server";

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(Ac_Cauhinh_Chuky.this);
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
					Custom_Toast.show_red_toast(Ac_Cauhinh_Chuky.this,
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
							if (mCB.getCommand().equals(LabelFull.get_chuky)) {
//								show_alert("KẾT QUẢ", mCB.getCommand() + "\n"
//										+ mCB.getResultString());
								set_value_chuky(mCB.getChuky());
//								set_spinner_mau();
							}if (mCB.getCommand().equals(LabelFull.ins_chuky)) {
								show_alert("KẾT QUẢ","Thêm mẫu văn bản thành công");
							} else {
								show_alert("KẾT QUẢ", mCB.getCommand());
							}
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
		androidLDialog = new AndroidLDialog.Builder(Ac_Cauhinh_Chuky.this)
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

	public void set_spinner_mau() {
		try {
			labels_nhom = new ArrayList<String>();
			for (int i = 1; i < 11; i++) {
				labels_nhom.add("MAU" + i);
			}
			Tht_add_data
					.set_spinner(Ac_Cauhinh_Chuky.this, labels_nhom, spnMau);
		} catch (Exception e) {

		}
	}

	public void set_value_chuky(D_CHUKY oCK) {
		try {
			if (oCK != null) {
				edtTieude.setText(oCK.getTIEU_DE());
				edtCancu.setText(oCK.getCAN_CU());
				edtChucvu1.setText(oCK.getCV1());
				edtNguoiky1.setText(oCK.getNK1());
				edtChucvu2.setText(oCK.getCV2());
				edtNguoiky2.setText(oCK.getNK2());
				edtChucvu3.setText(oCK.getCV3());
				edtNguoiky3.setText(oCK.getNK3());
				edtChucvu4.setText(oCK.getCV4());
				edtNguoiky4.setText(oCK.getNK4());
				edtChucvu5.setText(oCK.getCV5());
				edtNguoiky5.setText(oCK.getNK5());
			}
		} catch (Exception e) {
			ThtShow.show_toast(getApplicationContext(), "lỗi " + e.toString());
		}
	}

	public D_CHUKY get_value_chuky() {
		D_CHUKY oDCK = new D_CHUKY();
		try {
			oDCK.setMA_DVIQLY(Variables.DNV.MaDV);
			oDCK.setMAU(MAU);
			oDCK.setTIEU_DE(edtTieude.getText().toString());
			oDCK.setCAN_CU(edtCancu.getText().toString());
			oDCK.setCV1(edtChucvu1.getText().toString());
			oDCK.setNK1(edtNguoiky1.getText().toString());
			oDCK.setCV2(edtChucvu2.getText().toString());
			oDCK.setNK2(edtNguoiky2.getText().toString());
			oDCK.setCV3(edtChucvu3.getText().toString());
			oDCK.setNK3(edtNguoiky3.getText().toString());
			oDCK.setCV4(edtChucvu4.getText().toString());
			oDCK.setNK4(edtNguoiky4.getText().toString());
			oDCK.setCV5(edtChucvu5.getText().toString());
			oDCK.setNK5(edtNguoiky5.getText().toString());
		} catch (Exception e) {
			ThtShow.show_toast(getApplicationContext(), "lỗi " + e.toString());
		}
		return oDCK;
	}
	public void hoan_tat(View v){
		insert_mau(get_value_chuky());
	}

}
