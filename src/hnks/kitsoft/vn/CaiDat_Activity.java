package hnks.kitsoft.vn;

//import kitsoft.chiettinhnew.R;
import hnks.kitsoft.utils.Custom_Toast;
import hnks.kitsoft.utils.Utils;
import hnks.kitsoft.utils.Variables;
import hnks.kitsoft.vn.custom_dialog.AndroidLDialog;
import hnks.kitsoft.vn.database.DBAdapter;
import hnks.kitsoft.vn.object.CallbackResult;
import hnks.kitsoft.vn.object.DM_Company;
import hnks.kitsoft.vn.object.Obj_D_NHAN_VIEN;
import hnks.kitsoft.vn.object.ObjectClient;
import hnks.kitsoft.vn.server.Function;
import hnks.kitsoft.vn.server.M_READ_JSON;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import tht.library.crouton.Style;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.thtsoftlib.function.ThtFunction;
import com.thtsoftlib.function.ThtShow;
import com.thtsoftlib.function.Tht_Screen;

public class CaiDat_Activity extends Activity {

	EditText edtMaDV, edtIPServer, edtIPLocal;
	DBAdapter mdb;

	String User;
	String Pass;
	String MaDV;
	String IPLocal;
	String IPServer;
	private ProgressDialog pDialog;
	CountDownTimer mcountdown;
	final int time_connnect = 20000;
	AndroidLDialog androidLDialog;
	String TAG_KQ = "";
	ObjectClient mOC; 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		mdb = new DBAdapter(this);
		Tht_Screen.hide_title(this);
		setContentView(R.layout.caidat_main);
		TAG_KQ = "Fail";
		Tht_Screen.hide_keyboard(this);

		edtMaDV = (EditText) findViewById(R.id.edtMaDV_caidat);
		edtIPServer = (EditText) findViewById(R.id.edtIPServer_caidat);
		edtIPLocal = (EditText) findViewById(R.id.edtIPLocal_caidat);

		if (Variables.co_nhanvien == Utils.TT_KHONGCO) {
			Variables.DNV = new Obj_D_NHAN_VIEN();
			Variables.DNV.setIPLocal("");
			Variables.DNV.setIPServer("");
			Variables.DNV.setMaDV("PB1101");
			Variables.DNV.setMaNhanVien("KSAT");
			Variables.DNV.setMatKhau("");
			Variables.DNV.setTenNhanVien("KSAT");
			Variables.DNV.setUserName("KSAT");
			DBAdapter.Insert_nhanVien(Variables.DNV);
			Variables.co_nhanvien = Utils.TT_CO;
		}
		try {
			if (Variables.DNV != null) {
				edtMaDV.setText(Variables.DNV.getMaDV());
				edtIPServer.setText(Variables.DNV.getIPServer());
				edtIPLocal.setText(Variables.DNV.getIPLocal());
			} else {
				ThtShow.show_crouton_toast(this, "null nhan vien", Style.INFO);
			}
		} catch (Exception e) {
			ThtShow.show_crouton_toast(this, e.toString(), Style.ALERT);
		}

	}

	public void thoat(View v) {
		finish();
	}

	@SuppressLint("DefaultLocale")
	public void update_nhanvien(View v) {
		mdb.open();
		if (Variables.DNV != null) {
			if (ThtFunction.validIP(edtIPServer.getText().toString())
					& ThtFunction.validIP(edtIPLocal.getText().toString())) {
				try {
					Variables.DNV.setMaDV(edtMaDV.getText().toString()
							.toUpperCase());
					Variables.DNV.setIPServer(edtIPServer.getText().toString());
					Variables.DNV.setIPLocal(edtIPLocal.getText().toString());
					mdb.update_nhanVien(Variables.DNV);
					Intent i = new Intent();
					setResult(RESULT_OK, i);
					finish();
					Custom_Toast.show_blue_toast(CaiDat_Activity.this,
							"Cập nhật thành công !");
				} catch (Exception e) {
					ThtShow.show_crouton_toast(CaiDat_Activity.this,
							e.toString(), Style.ALERT);
				}
			} else {
				if (edtMaDV.getText().length() == 6) {
					TAG_KQ = "Không kết nối được server";
					mOC = new ObjectClient();
					mOC.setCommand("getIpCompany");
					mOC.setParam2("KhaoSat");
					mOC.setParam1(edtMaDV.getText().toString()
							.toUpperCase().substring(0,4));
					new Conect_server_sign_async(
							"http://licsofts.appspot.com/getdata").execute();
				} else {
					ThtShow.show_crouton_toast(CaiDat_Activity.this,
							getString(R.string.kiem_tra_ip), Style.ALERT);
					edtIPServer.requestFocus();
				}
			}

		}

	}

	// gui du lieu
	class Conect_server_sign_async extends AsyncTask<String, String, String> {
		String url = "";
		String KQSV = "not sent";
		String MADV;

		public Conect_server_sign_async(String url) {
			this.url = url;
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(CaiDat_Activity.this);
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
					Custom_Toast.show_red_toast(CaiDat_Activity.this,
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
					if (TAG_KQ.equals("OK")) {
						set_ip();
						
						show_alert(getString(R.string.alert_thong_bao),
								getString(R.string.alert_thietlap_thanhcong));
					} else {
						show_alert(getString(R.string.alert_thong_bao), TAG_KQ);
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

				KQSV = Function.alldata2server(mOC, null, null, null, null,
							null, null,null);
				TAG_KQ = KQSV;
				Function.write_String_to_byte(outputStream, KQSV);
				outputStream.flush();
				dis = new DataInputStream(connection.getInputStream());
				KQSV = "";
				KQSV = Function.byte_to_String(dis);
				Log.i("KQSV", KQSV);
				outputStream.close();
				dis.close();
				JsonParser jp = new JsonParser();
				JsonObject mJO = jp.parse(KQSV).getAsJsonObject();
				TAG_KQ="mJO :"+mJO.toString();
				CallbackResult mCB = M_READ_JSON.read_callback(mJO);
				if (mCB != null) {
					try {
						TAG_KQ = mCB.getResultString();
						if (mCB.getResultString().equals("OK")) {
							try {
								DM_Company mDV = new DM_Company();
								mDV = M_READ_JSON.read_company(mJO);
								Variables.DNV.MaDV = edtMaDV.getText().toString();
								Variables.DNV.IPLocal = mDV.getIpTrong();
								Variables.DNV.IPServer = mDV.getIpNgoai();
								mdb.update_nhanVien(Variables.DNV);
								Intent i = new Intent();
								setResult(RESULT_OK, i);
							} catch (Exception e) {
								TAG_KQ = "loi doc don vi "+e.toString();
							}
							
						}
							
					} catch (Exception e) {
						TAG_KQ = "loi doc callback :"+e.toString();
					}
					// het
				} else {
					TAG_KQ = ("ko doc dc JSON");
				}
			} catch (Exception ex) {
				TAG_KQ = ("xxx"+ex.toString());
				
			}

		}

	}

	public void show_alert(String title, String contain) {
		androidLDialog = new AndroidLDialog.Builder(CaiDat_Activity.this)
		// settings title
				.Title(title)
				// settings message
				.Message(contain)
				// adding positive (right) button
				.setPositiveButton("OK", new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						androidLDialog.hide();
						if (TAG_KQ.equals("OK")) {
							finish();
						}
					}
				})
				// adding negative (center) button
				// .setNegativeButton("CANCEL", new View.OnClickListener() {
				// @Override
				// public void onClick(View v) {
				// androidLDialog.hide();
				// }
				// })
				// showing the dialog!
				.show();
		androidLDialog.setCancelable(false);
		androidLDialog.setTitleColor(getResources().getColor(R.color.cyan));
	}

	public void set_ip() {
		edtMaDV.setText(Variables.DNV.getMaDV());
		edtIPServer.setText(Variables.DNV.getIPServer());
		edtIPLocal.setText(Variables.DNV.getIPLocal());
	}

}
