package hnks.kitsoft.vn;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import tht.library.crouton.Style;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.thtsoftlib.function.ThtShow;
import com.thtsoftlib.function.Tht_Network;
import com.thtsoftlib.function.Tht_Screen;

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
import hnks.kitsoft.vn.object.Obj_DANHMUC_TRAM;
import hnks.kitsoft.vn.object.Obj_D_MAU;
import hnks.kitsoft.vn.object.Obj_D_NHOM_VTU;
import hnks.kitsoft.vn.object.Obj_D_VATTU;
import hnks.kitsoft.vn.object.Obj_GHICHU;
import hnks.kitsoft.vn.object.Obj_HSO_CHIETTINH;
import hnks.kitsoft.vn.object.Obj_HSO_TOADO;
import hnks.kitsoft.vn.object.Obj_HSO_VATTU_CTIET;
import hnks.kitsoft.vn.object.Obj_LIENKET_VATTU;
import hnks.kitsoft.vn.object.Obj_MAU_CANVAS;
import hnks.kitsoft.vn.object.Obj_MAU_HESO;
import hnks.kitsoft.vn.object.Obj_nhap_lieu;
import hnks.kitsoft.vn.object.ObjectClient;
import hnks.kitsoft.vn.object.UserLogin;
import hnks.kitsoft.vn.server.Function;
import hnks.kitsoft.vn.server.M_READ_JSON;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class Main_Activity extends Activity {
	DBAdapter mdb;
	TextView tv_IMEI;
	TextView tv_madv, tv_ipserver, tv_iplocal, tv_ip_main, tv_version_main;
	CheckBox chk_chon_ip_main;
	EditText edt_user;
	EditText edt_password;
	public static String IMEI = "";
	private ProgressDialog pDialog;
	private ProgressDialog pDialog_download;
	CountDownTimer mcountdown;
	final int time_connnect = 180000;
	final int time_update = 2000;
	final int GET_VER = 1, GET_DM = 2, GET_HSO = 3, THEM_DATA = 0;
	public static final int progress_bar_type = 0;
	String url = "";
	AndroidLDialog androidLDialog;
	AndroidLDialog androidLDialog_YN;
	CallbackResult mCB = null;
	ObjectClient mOC = null;

	// String TEST_URL = "http://10.179.0.21:8888/data2mobile";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Tht_Screen.hide_title(this);
		try {
			setContentView(R.layout.main_activity);
			tv_IMEI = (TextView) findViewById(R.id.tv_imei_main);
			tv_madv = (TextView) findViewById(R.id.tv_madv_main);
			tv_ipserver = (TextView) findViewById(R.id.tv_ip_server_main);
			tv_iplocal = (TextView) findViewById(R.id.tv_ip_local_main);
			tv_ip_main = (TextView) findViewById(R.id.tv_ip_main);
			tv_version_main = (TextView) findViewById(R.id.tv_version_main);
			edt_user = (EditText) findViewById(R.id.edt_username_main);
			edt_password = (EditText) findViewById(R.id.edt_password_main);
			chk_chon_ip_main = (CheckBox) findViewById(R.id.chk_chon_ip_main);
		} catch (Exception e) {
			ThtShow.show_crouton_toast(this, "loi khoi tao giao dien",
					Style.ALERT);
		}

		mdb = new DBAdapter(this);
		mdb.open();
		// Security From Here
		try {
			IMEI = getDeviceIdBySlot(getApplicationContext(),
					"getDeviceIdGemini", 0);
		} catch (Exception e) {
			TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
			IMEI = telephonyManager.getDeviceId();
			if (IMEI == null)
				IMEI = "";
			if (IMEI.length() < 10) {
				try {
					WifiManager wifiManager = (WifiManager) getSystemService(WIFI_SERVICE);
					WifiInfo wifiInfo = wifiManager.getConnectionInfo();
					IMEI = wifiInfo.getMacAddress().toString();
					if (IMEI == null)
						IMEI = "";
				} catch (Exception ex) {
					IMEI = "54";// Settings.Secure.getString(getContentResolver(),
								// Settings.Secure.ANDROID_ID);
				}
			}
		}
		mOC = new ObjectClient();
		kiem_tra_nhan_vien();

	}

	// chuyen intent
	public void to_intent_caidat(View v) {
		if (Variables.DNV != null) {
			Intent i = new Intent(Main_Activity.this, CaiDat_Activity.class);
			startActivityForResult(i, Utils.REQ_CODE);
		} else {
			ThtShow.show_crouton_toast(Main_Activity.this,
					"loi truy xuat nhan vien", Style.ALERT);
		}
	}

	public void to_intent_xemdanhsach(View v) {
		mdb.open();
		try {

			if (edt_user.length() == 0 || edt_password.length() == 0) {
				Custom_Toast.show_red_toast(Main_Activity.this,
						getString(R.string.chua_nhap_pass));
				edt_password.requestFocus();
			} else {
				// kiem tra mat khau truoc
				if (mdb.get_soluong_user(edt_user.getText().toString().trim(),
						edt_password.getText().toString().trim()) > 0) {
					if (mdb.get_soluong_hoso_by_donvi(Variables.DNV.getMaDV()) > 0) {
						Intent i = new Intent(Main_Activity.this,
								DSHS_CHIETTINH_Activity.class);
						startActivity(i);
					} else {
						Custom_Toast.show_yellow_toast(Main_Activity.this,
								"Chưa có hồ sơ, lấy dữ liệu mới trước !");
					}
				} else {
					Custom_Toast
							.show_yellow_toast(
									Main_Activity.this,
									"Sai username hoặc mật khẩu, "
											+ "Vui lòng nhập đúng và lấy dữ liệu trước !");
				}

			}

		} catch (Exception e) {
			Custom_Toast.show_red_toast(Main_Activity.this, e.toString());
		}
		// test
		// Intent i = new Intent(Main_Activity.this, Ac_giaodien_quantri.class);
		// startActivity(i);

	}

	public void to_intent_xemvattu(View v) {
		Intent i = new Intent(Main_Activity.this, Ac_xem_vattu.class);
		startActivity(i);
	}

	public void to_intent_tai_phan_mem(View v) {
		Intent i = new Intent(Main_Activity.this, Ac_Tai_Phan_Mem.class);
		startActivity(i);
	}

	// lay du lieu
	public void lay_dulieu(View v) {
		if (Tht_Network.isNetworkAvailable(Main_Activity.this)) {
			try {
				Variables.DNV.UserName = edt_user.getText().toString();
				Variables.DNV.MatKhau = edt_password.getText().toString();
				mdb.open();
				mdb.update_nhanVien(Variables.DNV);
				if (edt_user.length() == 0 || edt_password.length() == 0) {
					Custom_Toast.show_red_toast(Main_Activity.this,
							getString(R.string.chua_nhap_pass));
					edt_password.requestFocus();
				} else {
					url = Link.get_link_connect(Variables.DNV.getIPServer());
					load_du_lieu();
				}
			} catch (Exception e) {

			}

		} else {
			ThtShow.show_crouton_toast(this,
					getString(R.string.khong_co_ket_noi), Style.ALERT);
		}
	}

	public void them_nhap_lieu() {
		if (mdb.get_soluong_nhap_lieu() == 0) {
			DBAdapter.Insert_NHAP_LIEU(new Obj_nhap_lieu(
					getString(R.string.LD_kh_vang), Variables.NL_NHAP_LY_DO));
			DBAdapter.Insert_NHAP_LIEU(new Obj_nhap_lieu(
					getString(R.string.LD_ko_thay_dia_chi),
					Variables.NL_NHAP_LY_DO));
			DBAdapter.Insert_NHAP_LIEU(new Obj_nhap_lieu(
					getString(R.string.LD_nha_chua_xay_xong),
					Variables.NL_NHAP_LY_DO));
			DBAdapter.Insert_NHAP_LIEU(new Obj_nhap_lieu(
					getString(R.string.MD_bom_nuoc_tuoi_tieu),
					Variables.NL_NHAP_MUC_DICH_SD));
			DBAdapter.Insert_NHAP_LIEU(new Obj_nhap_lieu(
					getString(R.string.MD_chieu_sang_cong_cong),
					Variables.NL_NHAP_MUC_DICH_SD));
			DBAdapter.Insert_NHAP_LIEU(new Obj_nhap_lieu(
					getString(R.string.MD_co_quan_hanh_chinh),
					Variables.NL_NHAP_MUC_DICH_SD));
			DBAdapter.Insert_NHAP_LIEU(new Obj_nhap_lieu(
					getString(R.string.MD_dich_vu),
					Variables.NL_NHAP_MUC_DICH_SD));
			DBAdapter.Insert_NHAP_LIEU(new Obj_nhap_lieu(
					getString(R.string.MD_kinh_doanh),
					Variables.NL_NHAP_MUC_DICH_SD));
			DBAdapter.Insert_NHAP_LIEU(new Obj_nhap_lieu(
					getString(R.string.MD_san_xuat),
					Variables.NL_NHAP_MUC_DICH_SD));
			DBAdapter.Insert_NHAP_LIEU(new Obj_nhap_lieu(
					getString(R.string.MD_thap_sang_sinh_hoat),
					Variables.NL_NHAP_MUC_DICH_SD));
			DBAdapter.Insert_NHAP_LIEU(new Obj_nhap_lieu(
					getString(R.string.MD_truong_hoc_benh_vien),
					Variables.NL_NHAP_MUC_DICH_SD));

			DBAdapter.Insert_NHAP_LIEU(new Obj_nhap_lieu(
					getString(R.string.LHS_bien_ban_ap_gia),
					Variables.NL_LOAI_HO_SO));
			DBAdapter.Insert_NHAP_LIEU(new Obj_nhap_lieu(
					getString(R.string.LHS_cmnd), Variables.NL_LOAI_HO_SO));
			DBAdapter.Insert_NHAP_LIEU(new Obj_nhap_lieu(
					getString(R.string.LHS_giay_to_dat),
					Variables.NL_LOAI_HO_SO));
			DBAdapter.Insert_NHAP_LIEU(new Obj_nhap_lieu(
					getString(R.string.LHS_hop_dong_thue_nha),
					Variables.NL_LOAI_HO_SO));
			DBAdapter.Insert_NHAP_LIEU(new Obj_nhap_lieu(
					getString(R.string.LHS_liet_ke_thiet_bi),
					Variables.NL_LOAI_HO_SO));
			DBAdapter.Insert_NHAP_LIEU(new Obj_nhap_lieu(
					getString(R.string.LHS_xac_nhan_so_ho),
					Variables.NL_LOAI_HO_SO));
			DBAdapter.Insert_NHAP_LIEU(new Obj_nhap_lieu(
					getString(R.string.VT_diem_dau_noi), Variables.NL_VITRI));
			DBAdapter.Insert_NHAP_LIEU(new Obj_nhap_lieu(
					getString(R.string.VT_diem_treo_cong_to),
					Variables.NL_VITRI));
			DBAdapter.Insert_NHAP_LIEU(new Obj_nhap_lieu(
					getString(R.string.VT_vi_tri_tram), Variables.NL_VITRI));
			DBAdapter.Insert_NHAP_LIEU(new Obj_nhap_lieu(
					getString(R.string.VT_vi_tri_tru), Variables.NL_VITRI));

			DBAdapter.Insert_NHAP_LIEU(new Obj_nhap_lieu(
					getString(R.string.TT_1), Variables.NL_NHAP_THUYET_TRINH));
			DBAdapter.Insert_NHAP_LIEU(new Obj_nhap_lieu(
					getString(R.string.TT_2), Variables.NL_NHAP_THUYET_TRINH));
			DBAdapter.Insert_NHAP_LIEU(new Obj_nhap_lieu(
					getString(R.string.TT_3), Variables.NL_NHAP_THUYET_TRINH));
			DBAdapter.Insert_NHAP_LIEU(new Obj_nhap_lieu(
					getString(R.string.TT_4), Variables.NL_NHAP_THUYET_TRINH));

			DBAdapter
					.Insert_NHAP_LIEU(new Obj_nhap_lieu(
							getString(R.string.VTT_ngoai_tru),
							Variables.NL_VITRI_TREO));
			DBAdapter
					.Insert_NHAP_LIEU(new Obj_nhap_lieu(
							getString(R.string.VTT_trong_nha),
							Variables.NL_VITRI_TREO));
			DBAdapter.Insert_NHAP_LIEU(new Obj_nhap_lieu(
					getString(R.string.VTT_trong_nha_hanh_lang),
					Variables.NL_VITRI_TREO));
			DBAdapter.Insert_NHAP_LIEU(new Obj_nhap_lieu(
					getString(R.string.VTT_trong_nha_mai_hien),
					Variables.NL_VITRI_TREO));

			DBAdapter.Insert_NHAP_LIEU(new Obj_nhap_lieu(
					getString(R.string.LXD_cap_1), Variables.NL_LOAI_XD));
			DBAdapter.Insert_NHAP_LIEU(new Obj_nhap_lieu(
					getString(R.string.LXD_cap_2), Variables.NL_LOAI_XD));
			DBAdapter.Insert_NHAP_LIEU(new Obj_nhap_lieu(
					getString(R.string.LXD_cap_3), Variables.NL_LOAI_XD));
			DBAdapter.Insert_NHAP_LIEU(new Obj_nhap_lieu(
					getString(R.string.LXD_cap_4), Variables.NL_LOAI_XD));
			DBAdapter.Insert_NHAP_LIEU(new Obj_nhap_lieu(
					getString(R.string.LXD_cap_5), Variables.NL_LOAI_XD));
			DBAdapter.Insert_NHAP_LIEU(new Obj_nhap_lieu(
					getString(R.string.LN_la), Variables.NL_LOAI_NHA));
			DBAdapter.Insert_NHAP_LIEU(new Obj_nhap_lieu(
					getString(R.string.LN_go), Variables.NL_LOAI_NHA));
			DBAdapter.Insert_NHAP_LIEU(new Obj_nhap_lieu(
					getString(R.string.LN_tuong), Variables.NL_LOAI_NHA));
			DBAdapter.Insert_NHAP_LIEU(new Obj_nhap_lieu(
					getString(R.string.LN_cao_tang), Variables.NL_LOAI_NHA));

			DBAdapter.Insert_NHAP_LIEU(new Obj_nhap_lieu(
					getString(R.string.MVT_dien_luc_cap),
					Variables.NL_TEN_MAU_VATTU));
			DBAdapter.Insert_NHAP_LIEU(new Obj_nhap_lieu(
					getString(R.string.MVT_khach_hang_3_pha),
					Variables.NL_TEN_MAU_VATTU));
			DBAdapter.Insert_NHAP_LIEU(new Obj_nhap_lieu(
					getString(R.string.MVT_khach_hang_sinh_hoat),
					Variables.NL_TEN_MAU_VATTU));
			DBAdapter.Insert_NHAP_LIEU(new Obj_nhap_lieu(
					getString(R.string.MVT_khach_hang_treo_tru),
					Variables.NL_TEN_MAU_VATTU));

		}
	}

	public void kiem_tra_nhan_vien() {
		try {
			if (mdb.get_SL_nhanvien() == 0) {
				Variables.co_nhanvien = Utils.TT_KHONGCO;
				Intent i = new Intent(Main_Activity.this, CaiDat_Activity.class);
				startActivityForResult(i, Utils.REQ_CODE);
			} else {
				try {
					Variables.co_nhanvien = Utils.TT_CO;
					Variables.DNV = mdb.get_nhanvien();
					Variables.DNV.MatKhau = edt_password.getText().toString();
					// IMEI = ThtDevive.get_imei(this);
					edt_user.setText(Variables.DNV.getUserName());
					tv_IMEI.setText("IMEI : " + IMEI);
					tv_madv.setText(Variables.DNV.getMaDV());
					tv_iplocal.setText("IP local : "
							+ Variables.DNV.getIPLocal());
					tv_ipserver.setText("IP Server : "
							+ Variables.DNV.getIPServer());
					tv_ip_main.setText("IP của bạn : "
							+ Variables.getLocalIpAddress());
					tv_version_main.setText("Phiên bản " + Memory.Version);
					url = Link.get_link_connect(Variables.DNV.getIPServer());
					them_nhap_lieu();
					// set mat khau
				} catch (Exception e) {

				}

			}
		} catch (Exception e) {
			ThtShow.show_crouton_toast(this, "loi kiem tra nhan vien",
					Style.ALERT);
		}

	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == Utils.REQ_CODE) {
			if (resultCode == RESULT_OK) {
				kiem_tra_nhan_vien();
			}

		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public void onBackPressed() {
		thoat();
	}

	public void thoat() {
		show_alert_YN("XÁC NHẬN !", getString(R.string.ban_co_muon_thoat));
	}

	private static String getDeviceIdBySlot(Context context,
			String predictedMethodName, int slotID)
			throws GeminiMethodNotFoundException {
		String imei = "";
		TelephonyManager telephony = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
		try {
			Class<?> telephonyClass = Class.forName(telephony.getClass()
					.getName());
			Class<?>[] parameter = new Class[1];
			parameter[0] = int.class;
			Method getSimID = telephonyClass.getMethod(predictedMethodName,
					parameter);
			Object[] obParameter = new Object[1];
			obParameter[0] = slotID;
			Object ob_phone = getSimID.invoke(telephony, obParameter);
			if (ob_phone != null) {
				imei = ob_phone.toString();
			}
		} catch (Exception e){
			e.printStackTrace();
			throw new GeminiMethodNotFoundException(predictedMethodName);
		}
		return imei;
	}

	private static class GeminiMethodNotFoundException extends Exception {
		private static final long serialVersionUID = -996812356902545308L;

		public GeminiMethodNotFoundException(String info) {
			super(info);
		}
	}

	// them nhap lieu
	class them_nhap_lieu extends AsyncTask<String, String, String> {
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(Main_Activity.this);
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
					Custom_Toast.show_red_toast(Main_Activity.this,
							"Quá thời gian lấy dữ liệu !");
				}
			}.start();
		}

		protected String doInBackground(String... kq) {
			try {
				them_nhap_lieu();
			} catch (Exception e) {
			}
			return null;
		}

		protected void onPostExecute(String result) {
			pDialog.dismiss();
			mcountdown.cancel();
			runOnUiThread(new Runnable() {
				public void run() {

				}
			});

		}

	}

	// load du lieu
	public void load_du_lieu() {
		try {
			mOC.Command = LabelFull.checkVersion;
			mOC.Param1 = Variables.DNV.MaDV;
			mOC.Param2 = Memory.Version;
			mOC.Param3 = "";
			mOC.setParam2(edt_user.getText().toString().trim());
			mOC.setParam3(edt_password.getText().toString().trim());
			mOC.setParam5(IMEI);
			// oC.Param4=LayBuocKeTiep;
			new Conect_server_sign_async().execute();
		} catch (Exception e) {
		}

	}

	// tai phan mem
	/**
	 * Showing Dialog
	 * */
	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case progress_bar_type:
			pDialog_download = new ProgressDialog(this);
			pDialog_download
					.setMessage("Đang tải file nâng cấp, xin chờ...");
			pDialog_download.setIndeterminate(false);
			pDialog_download.setMax(100);
			pDialog_download.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			pDialog_download.setCancelable(true);
			pDialog_download.show();
			return pDialog_download;
		default:
			return null;
		}
	}

	// download file
	class DownloadFileFromURL extends AsyncTask<String, String, String> {
		int KQ = 0;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			showDialog(progress_bar_type);
		}

		@SuppressLint("SdCardPath")
		@Override
		protected String doInBackground(String... f_url) {
			int count;
			try {
				URL url = new URL(f_url[0]);
				URLConnection conection = url.openConnection();
				conection.connect();
				int lenghtOfFile = conection.getContentLength();
				InputStream input = new BufferedInputStream(url.openStream(),
						8192);
				OutputStream output = new FileOutputStream("/sdcard/hnks.apk");
				byte data[] = new byte[1024];
				long total = 0;
				while ((count = input.read(data)) != -1) {
					total += count;
					publishProgress("" + (int) ((total * 100) / lenghtOfFile));
					output.write(data, 0, count);
				}
				output.flush();
				output.close();
				input.close();
				if (total == 0) {
					KQ = 0;
				} else {
					KQ = 1;
				}
			} catch (Exception e) {
				Log.e("Error: ", e.getMessage());
			}

			return null;
		}

		protected void onProgressUpdate(String... progress) {
			pDialog_download.setProgress(Integer.parseInt(progress[0]));
		}

		@Override
		protected void onPostExecute(String file_url) {
			dismissDialog(progress_bar_type);
			if (KQ == 1) {
				try {
					String imagePath = Environment
							.getExternalStorageDirectory().toString()
							+ "/hnks.apk";
					Intent intent = new Intent(Intent.ACTION_VIEW);
					intent.setDataAndType(Uri.fromFile(new File(imagePath)),
							"application/vnd.android.package-archive");
					intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					startActivity(intent);
				} catch (Exception e) {
					Custom_Toast
							.show_red_toast(
									Main_Activity.this,
									"Lỗi khi cập nhật phần mềm \nVui lòng bấm vào TẢI PHẦN MỀM để cài đặt thủ công .");
				}
			} else {
				Custom_Toast
						.show_red_toast(
								Main_Activity.this,
								"Lỗi khi cập nhật phần mềm \nVui lòng bấm vào TẢI PHẦN MỀM để cài đặt thủ công .");
			}

		}

	}

	// gui du lieu
	class Conect_server_sign_async extends AsyncTask<String, String, String> {
		public String TAG_KQ = "Không kết nối được server";

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(Main_Activity.this);
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
					Custom_Toast.show_red_toast(Main_Activity.this,
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
					if (mOC.Command.equals(LabelFull.checkVersion)) {
						if (TAG_KQ.equals(LabelFull.HasNewVersion)) {
							try {
								new DownloadFileFromURL().execute(Link
										.get_link_download(url));
							} catch (Exception e) {
								TAG_KQ = e.toString();
							}
						}
					} else if (mOC.Command.equals(LabelFull.CheckLogin)) {
						if (mCB.getResultString().equals("OK")) {
							if (mCB != null) {
								if (mCB.getoUSER() != null) {
									if (mCB.getoUSER().getMA_BPHAN()
											.equals("QL")) {
										Variables.DNV.UserName = edt_user
												.getText().toString();
										Variables.DNV.MatKhau = "";
										mdb.update_nhanVien(Variables.DNV);
										Intent i = new Intent(
												Main_Activity.this,
												Ac_giaodien_quantri.class);
										startActivity(i);
									}
//									else{
//										ThtShow.show_toast(getApplicationContext(), mCB.getoUSER().getMA_BPHAN());
//									}
								} else {
									TAG_KQ = getString(R.string.alert_not_connect_server);
								}
							}

						}
					}else{
						show_alert(getString(R.string.alert_thong_bao), TAG_KQ+"\n"+mCB.getCommand()+"\nIMEI "+mOC.getParam5());
					}
//					
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

				if (mOC.getCommand().equals(LabelFull.getDSHS)) {
					mOC.setParam1(Variables.DNV.MaDV);
					mOC.setParam2(edt_user.getText().toString().trim());
					mOC.setParam3(edt_password.getText().toString().trim());
					String dsHSExist = "' ',";
					List<Obj_HSO_CHIETTINH> lstHSCT = DBAdapter
							.get_list_hoso(Variables.DNV);
					for (Obj_HSO_CHIETTINH HS : lstHSCT) {
						dsHSExist = dsHSExist + "'" + HS.MA_YCAU_KNAI + "',";
					}
					dsHSExist = dsHSExist.substring(0, dsHSExist.length() - 1);
					mOC.Param4 = dsHSExist;
					TAG_KQ = Function.alldata2server(mOC, null, null, null,
							null, null, null, null);
				} else if (mOC.getCommand().equals(LabelFull.checkVersion)) {
					mOC.setParam2(Memory.Version);
					TAG_KQ = Function.alldata2server(mOC, null, null, null,
							null, null, null, null);
				} else if (mOC.getCommand().equals(LabelFull.CheckLogin)) {
					mOC.setParam1(Variables.DNV.MaDV);
					mOC.setParam2(edt_user.getText().toString().trim());
					mOC.setParam3(edt_password.getText().toString().trim());
					TAG_KQ = Function.alldata2server(mOC, null, null, null,
							null, null, null, null);
				}else if (mOC.getCommand().equals(LabelFull.updateMa2Mobile)) {
					TAG_KQ = Function.alldata2server(mOC, null, null, null, null,
							null, null,null);
				}

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
				mCB = M_READ_JSON.read_callback(mJO);
				UserLogin CB_USER = null;
				if (mCB != null) {
					try {
						TAG_KQ = mCB.getResultString();
						// kiem tra phien ban
						if (mOC.getCommand().equals(LabelFull.checkVersion)) {
							if (mCB.getResultString().equals("OK")) {
								mOC.setCommand(LabelFull.CheckLogin);
								upload(urlServer);
							}
							// kiem tra user dang nhap
						} else if (mOC.getCommand()
								.equals(LabelFull.CheckLogin)) {
							if (mCB.getResultString().equals("OK")) {
								try {
									CB_USER = mCB.getoUSER();
								} catch (Exception e) {

								}
								if (CB_USER != null) {
									if (!CB_USER.getMA_BPHAN().equals("QL")
										&&	!CB_USER.getMA_BPHAN().equals("QT")
										&&	!CB_USER.getMA_BPHAN().equals("CT")) {
										mOC.setCommand(LabelFull.getDSHS);
										upload(urlServer);
									}
								} else {
									TAG_KQ = getString(R.string.alert_not_connect_server);
								}
							}
						} else if (mCB.getCommand().equals(LabelFull.getDSHS)) {
							if (TAG_KQ.equals("OK")) {
								Variables.DNV.UserName = edt_user.getText()
										.toString();
								Variables.DNV.MatKhau = edt_password.getText()
										.toString();
								mdb.update_nhanVien(Variables.DNV);

								List<Obj_CANVAS> ml_canvas = M_READ_JSON
										.read_list_canvas(mJO);
								List<Obj_D_MAU> ml_mau = M_READ_JSON
										.read_list_dmau(mJO);
								List<Obj_D_NHOM_VTU> ml_nhom = M_READ_JSON
										.read_list_dnhom(mJO);
								List<Obj_D_VATTU> ml_vattu = M_READ_JSON
										.read_list_dvattu(mJO);
								List<Obj_DANHMUC_TRAM> ml_danhmuctram = M_READ_JSON
										.read_list_danhmuctram(mJO);
								List<Obj_HSO_CHIETTINH> ml_hoso = M_READ_JSON
										.read_list_hschiettinh(mJO);
								List<Obj_HSO_TOADO> ml_toado = M_READ_JSON
										.read_list_toado(mJO);
								List<Obj_HSO_VATTU_CTIET> ml_vattuchitiet = M_READ_JSON
										.read_list_vattuchitiet(mJO);
								List<Obj_LIENKET_VATTU> ml_lienket = M_READ_JSON
										.read_list_lkvattu(mJO);
								List<Obj_MAU_CANVAS> ml_maucanvas = M_READ_JSON
										.read_list_maucanvas(mJO);
								List<Obj_MAU_HESO> ml_mauheso = M_READ_JSON
										.read_list_mauheso(mJO);
								List<Obj_GHICHU> ml_ghichu = M_READ_JSON
										.read_list_ghichu(mJO);

								if (ml_ghichu != null) {
									for (Obj_GHICHU mo : ml_ghichu) {
										DBAdapter.Insert_HSO_GHICHU(mo);
									}
								}

								if (ml_mauheso != null) {
									DBAdapter.delete_ALL_MAU_HESO();
									for (Obj_MAU_HESO mo : ml_mauheso) {
										DBAdapter.Insert_MAU_HESO(mo);
									}
								}

								if (ml_maucanvas != null) {
									DBAdapter.delete_ALL_MAU_CANVAS();
									for (Obj_MAU_CANVAS mo : ml_maucanvas) {
										DBAdapter.Insert_MAU_CANVAS(mo);
									}
								}

								if (ml_lienket != null) {
									DBAdapter.delete_ALL_LIENKET_VATTU();
									for (Obj_LIENKET_VATTU mo : ml_lienket) {
										DBAdapter.Insert_LIENKET(mo);
									}
								}

								if (ml_vattuchitiet != null) {
									for (Obj_HSO_VATTU_CTIET mo : ml_vattuchitiet) {
										DBAdapter.Insert_HSO_VATTU_CTIET(mo);
									}
								}

								if (ml_toado != null) {
									for (Obj_HSO_TOADO mo : ml_toado) {
										DBAdapter.Insert_HSO_TOADO(mo);
									}
								}

								if (ml_canvas != null) {
									for (Obj_CANVAS mo : ml_canvas) {
										DBAdapter.Insert_HSO_CANVAS(mo);
									}
								}
								if (ml_mau != null) {
									DBAdapter.delete_ALL_D_MAU();
									for (Obj_D_MAU mo : ml_mau) {
										DBAdapter.Insert_D_MAU(mo);
									}
								}
								if (ml_nhom != null) {
									DBAdapter.delete_ALL_D_NHOM_VTU();
									for (Obj_D_NHOM_VTU mo : ml_nhom) {
										DBAdapter.Insert_D_NHOM_VTU(mo);
									}
								}
								if (ml_vattu != null) {
									DBAdapter.delete_ALL_D_VATTU();
									for (Obj_D_VATTU mo : ml_vattu) {
										DBAdapter.Insert_D_VATTU(mo);
									}
								}
								if (ml_danhmuctram != null) {
									DBAdapter.delete_ALL_DANHMUC_TRAM();
									for (Obj_DANHMUC_TRAM mo : ml_danhmuctram) {
										DBAdapter.Insert_DANHMUC_TRAM(mo);
									}
								}
								if (ml_hoso != null) {
									for (Obj_HSO_CHIETTINH mo : ml_hoso) {
										DBAdapter.Insert_HSO_CHIETTINH(mo);
									}
								}
								TAG_KQ = getString(R.string.alert_nhan_du_lieu_thanh_cong);
							}
						}
					} catch (Exception e) {
						TAG_KQ = "loi doc callback :" + e.toString();
					}
				} else {
					TAG_KQ = "ko doc dc JSON";
				}
			} catch (Exception ex) {
				if (urlServer.equals(Link.get_link_connect(Variables.DNV
						.getIPServer()))) {
					String URL_new = Link.get_link_connect(Variables.DNV
							.getIPLocal());
					url = URL_new;
					upload(url);
				} else {
					TAG_KQ = getString(R.string.alert_not_connect_server)
							+ "\n" + Link.get_IP(urlServer);
				}

			}

		}

	}

	public void show_alert(String title, String contain) {
		androidLDialog = new AndroidLDialog.Builder(Main_Activity.this)
		// settings title
				.Title(title)
				// settings message
				.Message(contain)
				// adding positive (right) button
				.setPositiveButton("OK", new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						androidLDialog.hide();
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

	public void show_alert_YN(String title, String contain) {
		androidLDialog = new AndroidLDialog.Builder(Main_Activity.this)
		// settings title
				.Title(title)

				// settings message
				.Message(contain)
				// adding positive (right) button
				.setPositiveButton("Đồng ý", new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						finish();
					}
				})
				// adding negative (center) button
				.setNegativeButton("Không", new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						androidLDialog.hide();
					}
				})
				// showing the dialog!
				.show();
		androidLDialog.setTitleColor(getResources().getColor(R.color.cyan));
		androidLDialog.setCancelable(false);
	}

	public void kiemtra_maychu(View v) {
		try {
			String mURL = "http://" + Variables.DNV.getIPServer() + "/kshn";
			Intent i = new Intent(Intent.ACTION_VIEW);
			i.setData(Uri.parse(mURL));
			startActivity(i);
			// ThtShow.show_toast(Main_Activity.this, mURL);
		} catch (Exception e) {

		}

	}
	public void get_license(View v) {
		if (Tht_Network.isNetworkAvailable(Main_Activity.this)) {
			try {
				mOC.Command="getMa2Moblie";
				mOC.Param1="KhaoSat";
				mOC.Param2=IMEI;
				mOC.Param3=""; //codeMD5("79610"+oC.Param1+om.IMEI+"7645");
				mOC.Param4=""; //om.CODE1BH;
				mOC.Param5=""; //om.MA_DVIQLY +" "+om.GHI_CHU;
				new Conect_server_license("http://licsofts.appspot.com/getdata", mOC).execute();
			} catch (Exception e) {}
			
		} else {
			ThtShow.show_crouton_toast(this,
					getString(R.string.khong_co_ket_noi), Style.ALERT);
		}
	}
	
	// get License 
		class Conect_server_license extends AsyncTask<String, String, String> {
			String url = "";
			String KQSV = "not sent";
			CallbackResult mCB=new CallbackResult();
			public Conect_server_license(String url, ObjectClient mOC) {
				this.url = url;
			}

			@Override
			protected void onPreExecute() {
				super.onPreExecute();
				pDialog = new ProgressDialog(Main_Activity.this);
				pDialog.setIndeterminate(false);
				pDialog.setCancelable(false);
				pDialog.show();

				mcountdown = new CountDownTimer(30000, 1000) {
					public void onTick(long millisUntilFinished) {
						pDialog.setMessage(getString(R.string.alert_dang_lay_du_lieu)
								+ " ..."
								+ String.valueOf(millisUntilFinished / 1000)
								+ " giÃ¢y !");
					}

					public void onFinish() {
						pDialog.dismiss();
						Custom_Toast.show_red_toast(Main_Activity.this,
								"QuÃ¡ thá»�i gian láº¥y dá»¯ liá»‡u !");
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
						if (mCB.getResultString().startsWith("xxx"))
							show_alert(getString(R.string.alert_thong_bao), mCB.getResultString());
						else {
							url = Link.get_link_connect(Variables.DNV.getIPServer());
							Log.i("URL", url);
							mOC.Command = LabelFull.updateMa2Mobile;
							mOC.Param1 = Variables.DNV.MaDV;
							mOC.Param2 = mCB.getResultString(); //Code 2
							new Conect_server_sign_async().execute();
						}
						
					}
				});
			}

			public void upload(String urlServer) {
				HttpURLConnection connection = null;
				DataOutputStream outputStream = null;
				DataInputStream dis = null;
				try {
					URL murl = new URL(urlServer);
					URLConnection urlConn = murl.openConnection();
					urlConn.setConnectTimeout(3000);
					urlConn.setReadTimeout(30000);
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
					mCB = M_READ_JSON.read_callback(mJO);
					if (mCB == null) {
						mCB.setResultString("ko doc duoc JSON");
					}
				} catch (Exception ex) {
					mCB.setResultString("xxx"+ex.toString());
					
				}

			}

		}

}
