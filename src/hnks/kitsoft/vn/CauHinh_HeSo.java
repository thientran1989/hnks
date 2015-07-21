package hnks.kitsoft.vn;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import tht.library.crouton.Style;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.thtsoftlib.function.ThtShow;
import com.thtsoftlib.function.Thtcovert;

import hnks.kitsoft.utils.Custom_Toast;
import hnks.kitsoft.utils.LabelFull;
import hnks.kitsoft.utils.Link;
import hnks.kitsoft.utils.Variables;
import hnks.kitsoft.vn.R;
import hnks.kitsoft.vn.custom_dialog.AndroidLDialog;
import hnks.kitsoft.vn.object.CallbackResult;
import hnks.kitsoft.vn.object.ObjConfig;
import hnks.kitsoft.vn.object.ObjectClient;
import hnks.kitsoft.vn.server.Function;
import hnks.kitsoft.vn.server.M_READ_JSON;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class CauHinh_HeSo extends Activity {
	
	private EditText edtPtncCauhinh;
	private EditText edtPtncbsCauhinh;
	private EditText edtPtChungCauhinh;
	private EditText edtPtChungbsCauhinh;
	private EditText edtPttlCauhinh;
	private EditText edtPtkCauhinh;
	private EditText edtPtVatCauhinh;
	private EditText edtPtTtCauhinh;
	
	ObjConfig oCF =null;
	ObjectClient oCL=null;
	CallbackResult mCB=null;
	
	private ProgressDialog pDialog;
	CountDownTimer mcountdown;
	final int time_connnect = 180000;
	AndroidLDialog androidLDialog;
	
	String url = "";

	private void findViews() {
		edtPtncCauhinh = (EditText)findViewById( R.id.edt_tieude );
		edtPtncbsCauhinh = (EditText)findViewById( R.id.edt_ptncbs_cauhinh );
		edtPtChungCauhinh = (EditText)findViewById( R.id.edt_pt_chung_cauhinh );
		edtPtChungbsCauhinh = (EditText)findViewById( R.id.edt_pt_chungbs_cauhinh );
		edtPttlCauhinh = (EditText)findViewById( R.id.edt_pttl_cauhinh );
		edtPtkCauhinh = (EditText)findViewById( R.id.edt_ptk_cauhinh );
		edtPtVatCauhinh = (EditText)findViewById( R.id.edt_pt_vat_cauhinh );
		edtPtTtCauhinh = (EditText)findViewById( R.id.edt_pt_tt_cauhinh );
		
		oCL = new ObjectClient();
		oCF = new ObjConfig();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ac_cauhinh_heso);
		findViews();
		
		get_config_server();
		
	}
	public void get_config(){
		if(edtPtChungbsCauhinh.length()>0
			& edtPtChungCauhinh.length()>0
			& edtPtkCauhinh.length()>0
			& edtPtncbsCauhinh.length()>0
			& edtPtncCauhinh.length()>0
			& edtPttlCauhinh.length()>0
			& edtPtTtCauhinh.length()>0
			& edtPtVatCauhinh.length()>0){
			if (oCF!=null){
				try {
					oCF.setPT_C(Thtcovert.String_to_double(edtPtChungCauhinh.getText().toString()));
					oCF.setPT_C1(Thtcovert.String_to_double(edtPtChungbsCauhinh.getText().toString()));
					oCF.setPT_K(Thtcovert.String_to_double(edtPtkCauhinh.getText().toString()));
					oCF.setPT_NC(Thtcovert.String_to_double(edtPtncCauhinh.getText().toString()));
					oCF.setPT_TL(Thtcovert.String_to_double(edtPttlCauhinh.getText().toString()));
					oCF.setPT_TT(Thtcovert.String_to_double(edtPtTtCauhinh.getText().toString()));
					oCF.setPT_VAT(Thtcovert.String_to_double(edtPtVatCauhinh.getText().toString()));
					oCF.setPT_NC1(Thtcovert.String_to_double(edtPtncbsCauhinh.getText().toString()));
				} catch (Exception e) {
					ThtShow.show_crouton_toast(this, "LỖI "+e.toString(), Style.ALERT);
				}
			}else{
				ThtShow.show_crouton_toast(this, "CONFIG NULL", Style.ALERT);
			}
			
		}else{
			ThtShow.show_crouton_toast(this, "Chưa đủ thông tin", Style.ALERT);
		}
		
	}
	public void get_config_server(){
		url = Link.get_link_connect(Variables.DNV.getIPServer());
		oCL.setParam1(Variables.DNV.MaDV);
		oCL.setParam2(Variables.DNV.UserName);
		oCL.setParam3(Variables.DNV.MatKhau);
		oCL.setParam5(Main_Activity.IMEI);
		oCL.setCommand(LabelFull.get_cauhinh);
		new Conect_server_sign_async().execute();
	}
	public void hoantat(View v){
		url = Link.get_link_connect(Variables.DNV.getIPServer());
		get_config();
		oCL.setCommand(LabelFull.upd_cauhinh);
		oCL.setObjConfig(oCF);
		new Conect_server_sign_async().execute();
	}
	public void back(View v){
		finish();
	}
	
	// gui du lieu
		class Conect_server_sign_async extends AsyncTask<String, String, String> {
			public String TAG_KQ = "Không kết nối được server";
			@Override
			protected void onPreExecute() {
				super.onPreExecute();
				pDialog = new ProgressDialog(CauHinh_HeSo.this);
				pDialog.setIndeterminate(false);
				pDialog.setCancelable(false);
				pDialog.show();
				mcountdown = new CountDownTimer(time_connnect, 1000) {
					public void onTick(long millisUntilFinished) {
						pDialog.setMessage(getString(R.string.alert_dang_lay_du_lieu)+" ..."
								+ String.valueOf(millisUntilFinished / 1000)
								+ " giây !");
					}

					public void onFinish() {
						pDialog.dismiss();
						Custom_Toast.show_red_toast(CauHinh_HeSo.this,
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
						if(mCB!=null){
							if(mCB.getResultString().equals("OK")){
								if(mCB.getCommand().equals(LabelFull.get_cauhinh)){
									if(oCF!=null){
										set_config();
									}
								}else{
									show_alert("KẾT QUẢ", "Cấu hình thành công !");
									finish();
								}
								
							}else{
								show_alert("KẾT QUẢ", mCB.getResultString()+"\nIMEI "+oCL.Param5);
							}
						}else{
							show_alert("KẾT QUẢ",getString(R.string.alert_not_connect_server));
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
					if (urlServer.contains("8888")) urlServer=urlServer.replace(Link.NAME_DIRECT, "/");
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
								null, null,null);

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
					TAG_KQ="mJO :"+mJO.toString();
					try {
						mCB = M_READ_JSON.read_callback(mJO);
					} catch (Exception e) {
						
					}
					if (mCB != null) {
						if(mCB.getCommand().equals(LabelFull.get_cauhinh)){
							oCF = mCB.getoCF();
						}
					}
				} catch (Exception ex) {
					if (urlServer.equals(Link.get_link_connect(Variables.DNV.getIPServer()))){
						String URL_new =Link.get_link_connect(Variables.DNV.getIPLocal());
						url = URL_new;
						upload(URL_new);
					}else{
						TAG_KQ = getString(R.string.alert_not_connect_server)+"\n"+
								Link.get_IP(urlServer);
					}
					
				}

			}

		}
		public void show_alert(String title,String contain){
			 androidLDialog = new AndroidLDialog.Builder(CauHinh_HeSo.this)
	         .Title(title)
	         .Message(contain)
	         .setPositiveButton("OK", new View.OnClickListener() {
	             @Override
	             public void onClick(View v) {
	            	 androidLDialog.hide();
	             }
	         })
	         .show();
			 androidLDialog.setCancelable(false);
			 androidLDialog.setTitleColor(getResources().getColor(R.color.cyan));
		}
		public void set_config(){
			try {
				edtPtChungCauhinh.setText(Thtcovert.double_to_String(oCF.getPT_C()));
				edtPtChungbsCauhinh.setText(Thtcovert.double_to_String(oCF.getPT_C1()));
				edtPtkCauhinh.setText(Thtcovert.double_to_String(oCF.getPT_K()));
				edtPtncCauhinh.setText(Thtcovert.double_to_String(oCF.getPT_NC()));
				edtPttlCauhinh.setText(Thtcovert.double_to_String(oCF.getPT_TL()));
				edtPtTtCauhinh.setText(Thtcovert.double_to_String(oCF.getPT_TT()));
				edtPtVatCauhinh.setText(Thtcovert.double_to_String(oCF.getPT_VAT()));
				edtPtncbsCauhinh.setText(Thtcovert.double_to_String(oCF.getPT_NC1()));
			} catch (Exception e) {
				
			}
		}
}
	
