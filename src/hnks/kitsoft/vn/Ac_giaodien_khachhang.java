package hnks.kitsoft.vn;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import hnks.kitsoft.utils.Custom_Toast;
import hnks.kitsoft.utils.LabelFull;
import hnks.kitsoft.utils.Link;
import hnks.kitsoft.utils.Utils;
import hnks.kitsoft.utils.Variables;
import hnks.kitsoft.vn.R;
import hnks.kitsoft.vn.custom_dialog.AndroidLDialog;
import hnks.kitsoft.vn.database.DBAdapter;
import hnks.kitsoft.vn.object.CallbackResult;
import hnks.kitsoft.vn.object.Obj_CANVAS;
import hnks.kitsoft.vn.object.Obj_Chiettinh;
import hnks.kitsoft.vn.object.Obj_GHICHU;
import hnks.kitsoft.vn.object.Obj_HSO_CHIETTINH;
import hnks.kitsoft.vn.object.Obj_HSO_HINH;
import hnks.kitsoft.vn.object.Obj_HSO_TOADO;
import hnks.kitsoft.vn.object.Obj_HSO_VATTU_CTIET;
import hnks.kitsoft.vn.object.Obj_MAU_CANVAS;
import hnks.kitsoft.vn.object.Obj_Text;
import hnks.kitsoft.vn.object.ObjectClient;
import hnks.kitsoft.vn.server.Function;
import hnks.kitsoft.vn.server.M_READ_JSON;
import hnks.kitsoft.vn.vesodo.Ve_So_Do_Activity;
import tht.library.crouton.Style;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.thtsoftlib.function.ThtShow;
import com.thtsoftlib.function.Tht_Network;
import com.thtsoftlib.function.Tht_Screen;
import com.thtsoftlib.function.Thtcovert;


public class Ac_giaodien_khachhang extends Activity{
	
	DBAdapter mdb;
	TextView tv_ten_kh,tv_diachi_kh;
	TextView tv_sl_cpdl,tv_sl_cpkh,tv_sl_cpkhtd,tv_sl_toado,tv_sl_hinhanh,tv_tinhtrang_gui;
	TextView tv_tt_xong;
	TextView tv_tien_kh,tv_tien_dl;
	ImageView iv_tt_xong;
	EditText edt_LOAIHS;
	String MA_YC="";
	String MA_DVI="";
	int w,h;
	private ProgressDialog pDialog;
	CountDownTimer mcountdown;
	final int time_connnect = 120000;
	int LENH=0;
	final int TO_CPDL=1,TO_CPKH=2,TO_CPKHTL=3;
	String TEST_URL = "";
	AndroidLDialog androidLDialog;
	ObjectClient oC;
	List<Obj_Text> list_CP=null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Tht_Screen.hide_title(this);
		setContentView(R.layout.giaodien_khachhang);
		w = (Tht_Screen.get_screen_width(this)/10)*5;
		h = (Tht_Screen.get_screen_heigth(this)/10)*6;
		mdb = new DBAdapter(this);
		mdb.open();
		MA_YC = Variables.HSCT_CHON.getMA_YCAU_KNAI();
		MA_DVI = Variables.HSCT_CHON.getMA_DVIQLY();
		try {
			Variables.size_text = Tht_Screen.get_screen_width(this)/70;
			Variables.size_character = Tht_Screen.get_screen_width(this)/180;
			Variables.size_padding_text = Tht_Screen.get_screen_width(this)/100;
			Variables.size_troke_text = Tht_Screen.get_screen_width(this)/40;
			Variables.size_stroke_paint = Tht_Screen.get_screen_width(this)/300;
			Variables.size_stroke_point = Tht_Screen.get_screen_width(this)/100;
			Variables.size_finger_range = Tht_Screen.get_screen_width(this)/10;
		} catch (Exception e) {
			
		}
		
		// khao bao view
		tv_ten_kh = (TextView)findViewById(R.id.tv_ten_kh_giaodienkh);
		tv_diachi_kh = (TextView)findViewById(R.id.tv_diachi_kh_giaodienkh);
		tv_tinhtrang_gui = (TextView)findViewById(R.id.tv_tinhtrang_gui_giaodien_khachhang);
		tv_tt_xong = (TextView)findViewById(R.id.tv_tt_xong_giaodien_khachhang);
		tv_tien_dl = (TextView)findViewById(R.id.tv_tien_dienluc_giaodien_khachhang);
		tv_tien_kh = (TextView)findViewById(R.id.tv_tien_khachhang_giaodien_khachhang);
		iv_tt_xong = (ImageView)findViewById(R.id.iv_tt_xong_giaodien_khachhang);
		// so luong
		tv_sl_cpdl = (TextView)findViewById(R.id.tv_sl_cpdl_giaodien_kh);
		tv_sl_cpkh = (TextView)findViewById(R.id.tv_sl_cpkh_giaodien_kh);
		tv_sl_cpkhtd =(TextView)findViewById(R.id.tv_sl_cpkhtd_giaodien_kh);
		tv_sl_hinhanh =(TextView)findViewById(R.id.tv_sl_hinhanh_giaodien_kh);
		tv_sl_toado =(TextView)findViewById(R.id.tv_sl_toado_giaodien_kh);
		edt_LOAIHS =(EditText)findViewById(R.id.edt_LOAIHS);
		refesh_so_luong();
		
		if (Variables.HSCT_CHON!=null){
			try {
				tv_ten_kh.setText(Variables.HSCT_CHON.getMA_YCAU_KNAI()+" - "+Variables.HSCT_CHON.getTEN_KHANG());
				tv_diachi_kh.setText(Variables.HSCT_CHON.getSO_NHA()+","+Variables.HSCT_CHON.getDUONG_PHO());
			} catch (Exception e) {
				
			}
		}
		edt_LOAIHS.setText(get_loaiHS_label(Variables.HSCT_CHON.MA_LOAI_HSO));
		edt_LOAIHS.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				show_alert_list("Chọn loại hồ sơ");
			}
		});
	}
	
	// chuyen intent
	public void to_intent_thongtin(View v){
		Intent i = new Intent(Ac_giaodien_khachhang.this,TAB_THONGTIN_HOSO.class);
		startActivityForResult(i, Utils.REQ_CODE);
	}
	public void to_intent_chiphidl(View v){
		LENH = TO_CPDL;
		Variables.MA_LOAI_TTOAN_CHON = Utils.LOAI_CP_DIENLUC;
		Variables.MA_LOAI_TTOAN_CHON_TITLE = (getString(R.string.chiphi_dl));
		new load_du_lieu(LENH).execute();
	}
	public void to_intent_chiphikh(View v){
		LENH = TO_CPKH;
		Variables.MA_LOAI_TTOAN_CHON = Utils.LOAI_CP_KHACHHANG;
		Variables.MA_LOAI_TTOAN_CHON_TITLE = (getString(R.string.chiphi_kh));
		new load_du_lieu(LENH).execute();
	}
	public void to_intent_chiphikhtl(View v){
		LENH = TO_CPKHTL;
		Variables.MA_LOAI_TTOAN_CHON = Utils.LOAI_CP_KHACHHANG_TULO;
		Variables.MA_LOAI_TTOAN_CHON_TITLE = (getString(R.string.chi_kh_tulo));
		new load_du_lieu(LENH).execute();
	}
	public void to_intent_hinhanh(View v){
		Intent i = new Intent(Ac_giaodien_khachhang.this,TAB_HINH_ANH.class);
		startActivityForResult(i, Utils.REQ_CODE);
	}
	public void to_intent_chiettinh(View v){
		Intent i = new Intent(Ac_giaodien_khachhang.this,TAB_CHIETTINH.class);
		startActivity(i);
	}
	public void to_intent_ve_sodo(View v){
		Intent i = new Intent(Ac_giaodien_khachhang.this,Ve_So_Do_Activity.class);
		startActivityForResult(i, Utils.REQ_CODE);
	}
	public void to_intent_toado(View v){
		Intent i = new Intent(Ac_giaodien_khachhang.this,TAB_BAN_DO.class);
		startActivityForResult(i, Utils.REQ_CODE);
	}
	public void to_gui_ho_so(View v){
		if (Tht_Network.isNetworkAvailable(Ac_giaodien_khachhang.this)){
			if (Variables.DNV.getMatKhau().equals("")){
				show_dialog_nhap_mat_khau();
			}else{
				TEST_URL = Link.get_link_connect(Variables.DNV.getIPServer());
				oC = new ObjectClient();
				oC.Command = LabelFull.sendData2Server;
				oC.Param1 = Variables.DNV.MaDV;
				oC.Param2 = Memory.Version;
				oC.Param3 = "";
				// oC.Param4=LayBuocKeTiep;
				new Conect_server_sign_async(TEST_URL, oC,Variables.HSCT_CHON.getMA_YCAU_KNAI(),
						Variables.HSCT_CHON.getMA_DVIQLY()).execute();
			}
		}else{
			ThtShow.show_crouton_toast(this,getString(R.string.khong_co_ket_noi), Style.ALERT);
		}
		
		
	}
	public void thay_doi_tt_xong (View v){
		try {
			if (Variables.HSCT_CHON.DA_XONG == Utils.TT_CHUA_XONG){
				Variables.HSCT_CHON.DA_XONG = Utils.TT_DA_XONG;
			}else{
				Variables.HSCT_CHON.DA_XONG = Utils.TT_CHUA_XONG;
			}
			mdb.update_HSO_CHIETTINH(Variables.HSCT_CHON);
			if (Variables.HSCT_CHON.getDA_XONG()==Utils.TT_CHUA_XONG){
				tv_tt_xong.setText("chưa xong");
				iv_tt_xong.setImageResource(R.drawable.ic_check_white);
			}else{
				tv_tt_xong.setText("đã xong");
				iv_tt_xong.setImageResource(R.drawable.ic_check);
			}
			Intent i = new Intent();
			setResult(RESULT_OK,i);
		} catch (Exception e) {
			
		}
		
		
	}
	// thoat
	public void thoat (View v){
		finish();
	}
	public void show_dialog_nhap_mat_khau() {
		final Dialog dialog = new Dialog(this, R.style.FullHeightDialog);
		dialog.setContentView(R.layout.dialog_nhap_pass);
		dialog.setCancelable(true);
		final EditText username = (EditText) dialog
				.findViewById(R.id.edt_username_dialog_nhap_pass);
		final EditText pass = (EditText) dialog
				.findViewById(R.id.edt_matkhau_dialog_nhap_pass);
		pass.requestFocus();
		username.setText(Variables.DNV.getUserName());
		final Button yes = (Button) dialog.findViewById(R.id.btn_yes_dialog_nhap_pass);
		yes.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				try {
					Variables.DNV.setMatKhau(pass.getText().toString());
					to_gui_ho_so(yes);
				} catch (Exception e) {
					ThtShow.show_crouton_toast(Ac_giaodien_khachhang.this,
							e.toString(), tht.library.crouton.Style.ALERT);
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
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == Utils.REQ_CODE) {
			if (resultCode == RESULT_OK) {
				mdb.open();
				Variables.HSCT_CHON.DA_CHUYEN = Utils.TT_CHUA_CHUYEN;
				mdb.update_HSO_CHIETTINH(Variables.HSCT_CHON);
				refesh_so_luong();
			}

		}

		super.onActivityResult(requestCode, resultCode, data);
	}
	public void refesh_so_luong(){
		try {
			Variables.sl_cpdl = mdb.get_sl_vattu(Utils.LOAI_CP_DIENLUC, MA_YC);
			Variables.sl_cpkh = mdb.get_sl_vattu(Utils.LOAI_CP_KHACHHANG, MA_YC);
			Variables.sl_cpkhtd = mdb.get_sl_vattu(Utils.LOAI_CP_KHACHHANG_TULO, MA_YC);
			Variables.sl_hinhanh = mdb.get_sl_hinhanh(MA_YC);
			Variables.sl_toado = mdb.get_sl_toado(MA_YC);
			
			tv_sl_cpdl.setText(Thtcovert.int_to_String(Variables.sl_cpdl));
			tv_sl_cpkh.setText(Thtcovert.int_to_String(Variables.sl_cpkh));
			tv_sl_cpkhtd.setText(Thtcovert.int_to_String(Variables.sl_cpkhtd));
			tv_sl_hinhanh.setText(Thtcovert.int_to_String(Variables.sl_hinhanh));
			tv_sl_toado.setText(Thtcovert.int_to_String(Variables.sl_toado));
			if (Variables.HSCT_CHON.getDA_CHUYEN()==Utils.TT_CHUA_CHUYEN){
				tv_tinhtrang_gui.setText("chưa gửi");
			}else{
				tv_tinhtrang_gui.setText("đã gửi");
			}
			if (Variables.HSCT_CHON.getDA_XONG()==Utils.TT_DA_XONG){
				tv_tt_xong.setText("đã xong");
				iv_tt_xong.setImageResource(R.drawable.ic_check);
			}else{
					Variables.HSCT_CHON.setDA_XONG(Utils.TT_CHUA_XONG);
					mdb.update_HSO_CHIETTINH(Variables.HSCT_CHON);
					tv_tt_xong.setText("chưa xong");
					iv_tt_xong.setImageResource(R.drawable.ic_check_white);
				
			}
			tv_tien_dl.setText(Thtcovert.int_format_tien((int)
					Obj_Chiettinh.TINH_CHI_PHI_DL(
					DBAdapter.get_list_vattu_chitiet(Variables.HSCT_CHON.MA_YCAU_KNAI, Utils.LOAI_CP_DIENLUC)
					, Variables.HSCT_CHON).getG())+" VND");
			tv_tien_kh.setText(Thtcovert.int_format_tien((int)
					Obj_Chiettinh.TINH_CHI_PHI_KH(
					DBAdapter.get_list_vattu_chitiet(Variables.HSCT_CHON.MA_YCAU_KNAI, Utils.LOAI_CP_KHACHHANG)
					, Variables.HSCT_CHON).getG())+" VND");
			Intent i = new Intent();
			setResult(RESULT_OK, i);
		} catch (Exception e) {
			Custom_Toast.show_red_toast(Ac_giaodien_khachhang.this,e.toString());
		}
	}
	
	// load du lieu
	class load_du_lieu extends AsyncTask<String, String, String> {
		int lenh;
		public load_du_lieu(int lenh){
			this.lenh=lenh;
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(Ac_giaodien_khachhang.this);
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
//						Variables.list_all_vattu = mdb.get_list_all_vattu();
						Variables.list_vattu_kienket = mdb.get_list_vattu_lienket();
//						Variables.list_vattu_da_chon = mdb.get_list_vattu_chitiet(
//						Variables.HSCT_CHON.MA_YCAU_KNAI, Variables.MA_LOAI_TTOAN_CHON);
//						Variables.list_vattu_sd = new ArrayList<Obj_D_VATTU>(Variables.list_all_vattu);
//					if (Variables.list_vattu_da_chon!=null){
//						for (Obj_HSO_VATTU_CTIET HSCT : Variables.list_vattu_da_chon) {
//							for (Obj_D_VATTU DVT : Variables.list_all_vattu) {
//								if (DVT.getMA_VTU().equals(HSCT.getMA_VTU())){
//									Variables.list_vattu_sd.remove(DVT);
//								}
//							}
//						}
//					}
			} catch (Exception e) {
				Custom_Toast.show_red_toast(Ac_giaodien_khachhang.this, e.toString());
			}

			return null;

		}

		protected void onPostExecute(String result) {
			pDialog.dismiss();
			mcountdown.cancel();
			runOnUiThread(new Runnable() {
				public void run() {
						Intent i = new Intent(Ac_giaodien_khachhang.this,TAB_VATTU_CHIPHI.class);
						startActivityForResult(i, Utils.REQ_CODE);
					
				}
				
			});

		}
	}
	// gui du lieu
		class Conect_server_sign_async extends AsyncTask<String, String, String> {
			String url = "";
			String KQSV = "not sent";
			String MAYC = "";
			String MADV = "";
			public String TAG_KQ = "Không kết nối được server";
			ObjectClient mOC;

			public Conect_server_sign_async( String url,
					ObjectClient mOC, String MAYC, String MADV) {
				this.url = url;
				this.mOC = mOC;
				this.MAYC = MAYC;
				this.MADV = MADV;
			}

			@Override
			protected void onPreExecute() {
				super.onPreExecute();
				pDialog = new ProgressDialog(Ac_giaodien_khachhang.this);
				pDialog.setIndeterminate(false);
				pDialog.setCancelable(false);
				pDialog.show();

				mcountdown = new CountDownTimer(time_connnect, 1000) {
					public void onTick(long millisUntilFinished) {
						pDialog.setMessage("Xin chờ ..."
								+ String.valueOf(millisUntilFinished / 1000)
								+ " giây !");
					}

					public void onFinish() {
						pDialog.dismiss();
						Custom_Toast.show_red_toast(Ac_giaodien_khachhang.this,
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
							show_alert(getString(R.string.alert_thong_bao), TAG_KQ);
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

					if (mOC.getCommand().equals(LabelFull.sendData2Server)) {
						Obj_HSO_CHIETTINH mHSCT = null;
						List<Obj_CANVAS> mL_canvas = null;
						List<Obj_HSO_VATTU_CTIET> mL_vtuchitiet = null;
						List<Obj_HSO_HINH> lstHSO_HINH = null;
						List<Obj_HSO_TOADO> mL_toado = null;
						List<Obj_GHICHU> mL_GhiChu = null;
						List<Obj_MAU_CANVAS> mL_maucanvas = null;
						try {
							mHSCT = Variables.HSCT_CHON;
							mL_canvas = DBAdapter.get_list_HSO_CANVAS(MAYC);
							mL_vtuchitiet = DBAdapter
									.get_list_vattu_chitiet_khachhang(MAYC);
							lstHSO_HINH = DBAdapter.get_list_hinh(MAYC);
							mL_toado = DBAdapter.get_list_toado(MAYC);
							mL_GhiChu = DBAdapter.get_list_ghichu(MAYC);
							mL_maucanvas = DBAdapter.get_list_MAU_CANVAS(MADV);
						} catch (Exception e) {
							TAG_KQ = e.toString();
						}
						KQSV = Function.alldata2server(mOC, mL_canvas, mHSCT,
								mL_toado, mL_vtuchitiet, mL_maucanvas, mL_GhiChu,lstHSO_HINH);
					} 
					Function.write_String_to_byte(outputStream, KQSV);
					outputStream.flush();

					InputStream is = new BufferedInputStream(
							connection.getInputStream());
					byte[] buffer = new byte[512];
					byte[] data = new byte[0];
					int bytesRead;
					bytesRead = is.read(buffer);
					while (bytesRead > 0) {
						byte[] newData = new byte[data.length + bytesRead];
						System.arraycopy(data, 0, newData, 0, data.length);
						System.arraycopy(buffer, 0, newData, data.length, bytesRead);
						data = newData;
						bytesRead = is.read(buffer);
					}
					ByteArrayInputStream inputStream = new ByteArrayInputStream(
							data);
					dis = new DataInputStream(inputStream);
					KQSV = "";
					KQSV = Function.byte_to_String(dis);
					outputStream.close();
					dis.close();
					JsonParser jp = new JsonParser();
					JsonObject mJO = jp.parse(KQSV).getAsJsonObject();
					CallbackResult mCB = M_READ_JSON.read_callback(mJO);
					if (mCB != null) {
						try {
							if (mCB.getCommand().equals(LabelFull.sendData2Server)) {
								TAG_KQ = mCB.getResultString();
								if (TAG_KQ.equals("OK")){
									TAG_KQ = getString(R.string.alert_gui_thanh_cong);
								}
							}
						} catch (Exception e) {
							TAG_KQ = e.toString();
						}
						// het
					} else {
						TAG_KQ = ("ko doc dc JSON");
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
			 androidLDialog = new AndroidLDialog.Builder(Ac_giaodien_khachhang.this)
	         //settings title
	         .Title(title)
	         //settings message
	         .Message(contain)
	         //adding positive (right) button
	         .setPositiveButton("OK", new View.OnClickListener() {
	             @Override
	             public void onClick(View v) {
	            	 androidLDialog.hide();
	             }
	         })
	         //adding negative (center) button
//	         .setNegativeButton("CANCEL", new View.OnClickListener() {
//	             @Override
//	             public void onClick(View v) {
//	                 androidLDialog.hide();
//	             }
//	         })
	         //showing the dialog!
	         .show();
			 androidLDialog.setCancelable(false);
			 androidLDialog.setTitleColor(getResources().getColor(R.color.cyan));
		}
		public String show_alert_list(String title){
			get_list_loaiHS();
			final List<String> list = new ArrayList<String>();
			for (Obj_Text oT : list_CP) {
				list.add(oT.NAME);
			}
			 	CharSequence [] items = list.toArray(new CharSequence[list.size()]);
		        AlertDialog.Builder builder = new AlertDialog.Builder(Ac_giaodien_khachhang.this);
		        builder.setTitle(title);
		        builder.setItems(items, new DialogInterface.OnClickListener() {
		            public void onClick(DialogInterface dialog, int item) {
		            	edt_LOAIHS.setText(list.get(item));
		            	Variables.HSCT_CHON.setMA_LOAI_HSO(list_CP.get(item).KEY);
		            	mdb.update_HSO_CHIETTINH(Variables.HSCT_CHON);
		            }
		        });
		        AlertDialog alert = builder.create();
		        alert.show();
		        return "";
		}
		public void get_list_loaiHS(){
			list_CP= new ArrayList<Obj_Text>();
			list_CP.add(new Obj_Text("LOAI1","Bình thường"));
			list_CP.add(new Obj_Text("LOAI2","Trên không"));
			list_CP.add(new Obj_Text("LOAI3","Cáp ngầm"));
			list_CP.add(new Obj_Text("LOAI4","Loại 4"));
			list_CP.add(new Obj_Text("LOAI5","Loại 5"));
		}
		public String get_loaiHS_label(String LOAIHS){
			get_list_loaiHS();
			String KQ = "";
			for (Obj_Text oT : list_CP) {
				if(oT.KEY.equals(LOAIHS)){
					KQ = oT.NAME;
				}
			}
			return KQ;
		}
}
