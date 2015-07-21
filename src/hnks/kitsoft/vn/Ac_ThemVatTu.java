package hnks.kitsoft.vn;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import tht.library.crouton.Style;
import hnks.kitsoft.utils.Custom_Toast;
import hnks.kitsoft.utils.LabelFull;
import hnks.kitsoft.utils.Link;
import hnks.kitsoft.utils.Variables;
import hnks.kitsoft.vn.custom_dialog.AndroidLDialog;
import hnks.kitsoft.vn.object.CallbackResult;
import hnks.kitsoft.vn.object.Obj_D_NHOM_VTU;
import hnks.kitsoft.vn.object.Obj_D_VATTU;
import hnks.kitsoft.vn.object.Obj_Text;
import hnks.kitsoft.vn.object.ObjectClient;
import hnks.kitsoft.vn.server.Function;
import hnks.kitsoft.vn.server.M_READ_JSON;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.thtsoftlib.function.ThtShow;
import com.thtsoftlib.function.Tht_Screen;
import com.thtsoftlib.function.Thtcovert;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

public class Ac_ThemVatTu extends Activity {
	
	private EditText edtMavattuThemvt;
	private EditText edtLoaicp;
	private EditText edtTenvattu;
	private EditText edtDongia;
	private EditText edtDongiaTg;
	private EditText edtDvtinh;
	private EditText edtK1NC;
	private EditText edtK2NC;
	private EditText edtKMTC;
	private EditText edtNC;
	private EditText edtDGMTC;
	private EditText edtNhom;

	public static Obj_D_VATTU oDVT = null;
	ObjectClient oCL=null;
	CallbackResult mCB=null;
	
	private ProgressDialog pDialog;
	CountDownTimer mcountdown;
	final int time_connnect = 180000;
	AndroidLDialog androidLDialog;
	
	String url = "";
	List<Obj_Text> list_CP=null;
	List<Obj_D_NHOM_VTU> list_nhom=null;
	

	private void findViews() {
		oDVT = new Obj_D_VATTU();
		get_list_CP();
		edtMavattuThemvt = (EditText)findViewById( R.id.edt_mavattu_themvt );
		edtLoaicp = (EditText)findViewById( R.id.edt_loaicp );
		edtTenvattu = (EditText)findViewById( R.id.edt_tenvattu );
		edtDongia = (EditText)findViewById( R.id.edt_dongia );
		edtDongiaTg = (EditText)findViewById( R.id.edt_dongia_tg );
		edtDvtinh = (EditText)findViewById( R.id.edt_dvtinh );
		edtK1NC = (EditText)findViewById( R.id.edt_K1NC );
		edtK2NC = (EditText)findViewById( R.id.edt_K2NC );
		edtKMTC = (EditText)findViewById( R.id.edt_KMTC );
		edtNC = (EditText)findViewById( R.id.edt_NC );
		edtDGMTC = (EditText)findViewById( R.id.edt_DG_MTC );
		edtNhom = (EditText)findViewById( R.id.edt_nhom_themvt );
		
		if(DanhSach_VatTu.list_nhom_vattu!=null){
			list_nhom = new ArrayList<Obj_D_NHOM_VTU>(DanhSach_VatTu.list_nhom_vattu);
		}
		
		oCL = new ObjectClient();
		oDVT = new Obj_D_VATTU();
		oDVT.setMA_LOAI_CPHI(list_CP.get(0).KEY);
		edtLoaicp.setText(list_CP.get(0).NAME);
		edtLoaicp.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				get_list_CP();show_alert_list("Chọn loại chi phí");
			}
		});
		edtNhom.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				if(list_nhom!=null){
					show_alert_list_nhom("Chọn nhóm");
				}else{
					ThtShow.show_toast(getApplicationContext(), "không có nhóm");
				}
			}
		});
		
		if(DanhSach_VatTu.my_LENH.equals(DanhSach_VatTu.LENH_SUA)){
			if(DanhSach_VatTu.my_oDVT!=null){
				edtMavattuThemvt.setEnabled(false);
				edtMavattuThemvt.setText(DanhSach_VatTu.my_oDVT.getMA_VTU());
				edtLoaicp.setText(DanhSach_VatTu.my_oDVT.get_LOAICP_label(list_CP));
				edtTenvattu.setText(DanhSach_VatTu.my_oDVT.getTEN_VTU());
				edtDongia.setText(""+DanhSach_VatTu.my_oDVT.getDON_GIA());
				edtDongiaTg.setText(""+DanhSach_VatTu.my_oDVT.getDON_GIA_TG());
				edtDvtinh.setText(DanhSach_VatTu.my_oDVT.getDVI_TINH());
				edtK1NC.setText(""+DanhSach_VatTu.my_oDVT.getK1NC());
				edtK2NC.setText(""+DanhSach_VatTu.my_oDVT.getK2NC());
				edtKMTC.setText(""+DanhSach_VatTu.my_oDVT.getKMTC());
				edtNC.setText(""+DanhSach_VatTu.my_oDVT.getDG_NC());
				edtDGMTC.setText(""+DanhSach_VatTu.my_oDVT.getDG_MTC());
				edtNhom.setText(""+DanhSach_VatTu.my_oDVT.get_NHOM_label(list_nhom));
			}
		}
	}

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Tht_Screen.hide_title(this);
		setContentView(R.layout.ac_themvattu);
		findViews();
	}
	public void get_VATTU(){
		if(oDVT!=null){
			if(edtMavattuThemvt.length()>0
			   & edtTenvattu.length()>0
			   & edtDongia.length()>0
			   & edtDvtinh.length()>0){
				oDVT.setMA_DVIQLY(Variables.DNV.MaDV);
				oDVT.setMA_VTU(edtMavattuThemvt.getText().toString());
				oDVT.setTEN_VTU(edtTenvattu.getText().toString());
				oDVT.setDON_GIA(Thtcovert.string_to_int(edtDongia.getText().toString()));
				oDVT.setDVI_TINH(edtDvtinh.getText().toString());
				oDVT.setDON_GIA_TG(Thtcovert.string_to_int(edtDongiaTg.getText().toString()));
				oDVT.setK1NC(Thtcovert.String_to_double(edtK1NC.getText().toString()));
				oDVT.setK2NC(Thtcovert.String_to_double(edtK2NC.getText().toString()));
				oDVT.setKMTC(Thtcovert.String_to_double(edtKMTC.getText().toString()));
				oDVT.setDG_NC(Thtcovert.string_to_int(edtNC.getText().toString()));
				oDVT.setDG_MTC(Thtcovert.string_to_int(edtDGMTC.getText().toString()));
			}else{
				ThtShow.show_crouton_toast(this, "Chưa đủ thông tin", Style.ALERT);
			}
		}else{
			ThtShow.show_crouton_toast(this, "Vật tư NULL", Style.ALERT);
		}
	}
	public void hoantat(View v){
		url = Link.get_link_connect(Variables.DNV.getIPServer());
		get_VATTU();
		if(DanhSach_VatTu.my_LENH.equals(DanhSach_VatTu.LENH_SUA)){
			oCL.setCommand(LabelFull.upd_vattu);
			oCL.setoDVT(oDVT);
		}else{
			oCL.setCommand(LabelFull.ins_vattu);
			oCL.setoDVT(oDVT);
		}
		new Conect_server_sign_async(oCL).execute();
	}
	public void back(View v){
		finish();
	}
	// gui du lieu
			class Conect_server_sign_async extends AsyncTask<String, String, String> {
				public String TAG_KQ = "Không kết nối được server";
				ObjectClient mOC;

				public Conect_server_sign_async(ObjectClient mOC) {
					this.mOC = mOC;
				}
				@Override
				protected void onPreExecute() {
					super.onPreExecute();
					pDialog = new ProgressDialog(Ac_ThemVatTu.this);
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
							Custom_Toast.show_red_toast(Ac_ThemVatTu.this,
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
									if(mCB.getCommand().equals(LabelFull.ins_vattu)){
										show_alert("KẾT QUẢ", "Thêm vật tư thành công !");
										Intent i = new Intent();
										setResult(RESULT_OK, i);
										finish();
									}else if(mCB.getCommand().equals(LabelFull.upd_vattu)){
										show_alert("KẾT QUẢ", "Sửa vật tư thành công !");
										finish();
									}else {
										show_alert("KẾT QUẢ", mCB.getCommand());
									}
									
								}else{
									show_alert("KẾT QUẢ", mCB.getResultString());
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
						TAG_KQ = Function.alldata2server(mOC, null, null, null, null,
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
				 androidLDialog = new AndroidLDialog.Builder(Ac_ThemVatTu.this)
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
			public String show_alert_list(String title){
				final List<String> list = new ArrayList<String>();
				for (Obj_Text oT : list_CP) {
					list.add(oT.NAME);
				}
				 	CharSequence [] items = list.toArray(new CharSequence[list.size()]);
			        AlertDialog.Builder builder = new AlertDialog.Builder(Ac_ThemVatTu.this);
			        builder.setTitle(title);
			        builder.setItems(items, new DialogInterface.OnClickListener() {
			            public void onClick(DialogInterface dialog, int item) {
			            	edtLoaicp.setText(list.get(item));
			            	oDVT.setMA_LOAI_CPHI(list_CP.get(item).KEY);
			            }
			        });
			        AlertDialog alert = builder.create();
			        alert.show();
			        return "";
			}
			public String show_alert_list_nhom(String title){
				final List<String> list = new ArrayList<String>();
				for (Obj_D_NHOM_VTU oT : list_nhom) {
					list.add(oT.TEN_NHOM);
				}
				 	CharSequence [] items = list.toArray(new CharSequence[list.size()]);
			        AlertDialog.Builder builder = new AlertDialog.Builder(Ac_ThemVatTu.this);
			        builder.setTitle(title);
			        builder.setItems(items, new DialogInterface.OnClickListener() {
			            public void onClick(DialogInterface dialog, int item) {
			            	edtNhom.setText(list.get(item));
			            	oDVT.setNHOM(list_nhom.get(item).getNHOM());
			            }
			        });
			        AlertDialog alert = builder.create();
			        alert.show();
			        return "";
			}
			public void get_list_CP(){
				list_CP= new ArrayList<Obj_Text>();
				list_CP.add(new Obj_Text("VL","Vật tư"));
				list_CP.add(new Obj_Text("NC","Nhân công"));
				list_CP.add(new Obj_Text("M","Máy thi công"));
			}
			public void sua_vattu(){
				
			}
}
