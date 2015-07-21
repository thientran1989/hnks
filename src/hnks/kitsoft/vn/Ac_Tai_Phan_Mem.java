package hnks.kitsoft.vn;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import tht.library.crouton.Style;

import com.thtsoftlib.function.ThtShow;
import com.thtsoftlib.function.Tht_Network;
import com.thtsoftlib.function.Tht_Screen;

import hnks.kitsoft.utils.Custom_Toast;
import hnks.kitsoft.utils.Variables;
import hnks.kitsoft.vn.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;

public class Ac_Tai_Phan_Mem  extends Activity{
	private ProgressDialog pDialog_download;
	public static final int progress_bar_type = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Tht_Screen.hide_title(this);
		setContentView(R.layout.ac_tai_phan_mem);
	}
	public void tai_ksct_noi_bo(View v){
		if (Tht_Network.isNetworkAvailable(this)){
			try {
				String url ="http://"+Variables.DNV.getIPLocal()+"/kshn/hnks.apk";
				new DownloadFileFromURL().execute(url);
			} catch (Exception e) {
				
			}
		} else{
			ThtShow.show_crouton_toast(Ac_Tai_Phan_Mem.this,getString(R.string.khong_co_ket_noi), Style.ALERT);
		}
	}
	public void tai_ksct_ngoai(View v){
		if (Tht_Network.isNetworkAvailable(this)){
			try {
				String url ="http://"+Variables.DNV.getIPServer()+"/kshn/hnks.apk";
				new DownloadFileFromURL().execute(url);
			} catch (Exception e) {
				
			}
	} else{
		ThtShow.show_crouton_toast(Ac_Tai_Phan_Mem.this,getString(R.string.khong_co_ket_noi), Style.ALERT);
		}
	}
	public void tai_ban_phim(View v){
		if (Tht_Network.isNetworkAvailable(this)){
		try {
			 startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.touchtype.swiftkey&hl=vi")));
		} catch (Exception e) {
			
		}
	} else{
		ThtShow.show_crouton_toast(Ac_Tai_Phan_Mem.this,getString(R.string.khong_co_ket_noi), Style.ALERT);
		}
	}
	public void tai_google_play(View v){
		if (Tht_Network.isNetworkAvailable(this)){
				try {
					 startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.gms&hl=vi")));
				} catch (Exception e) {
					
				}
			
		} else{
			ThtShow.show_crouton_toast(Ac_Tai_Phan_Mem.this,getString(R.string.khong_co_ket_noi), Style.ALERT);
		}
	}
	public void tai_ksct_google(View v){
		if (Tht_Network.isNetworkAvailable(this)){
				try {
					String url ="https://docs.google.com/uc?authuser=0&id=0B9GJriGnHUkJWlpNWlMzbFVlRUk&export=download";
					new DownloadFileFromURL().execute(url);
				} catch (Exception e) {
					
				}
			
		} else{
			ThtShow.show_crouton_toast(Ac_Tai_Phan_Mem.this,getString(R.string.khong_co_ket_noi), Style.ALERT);
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
						.setMessage("Đang tải file nâng cấp, xin chờ...");
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
										Ac_Tai_Phan_Mem.this,
										"Lỗi khi cập nhật phần mềm \nVui lòng bấm vào TẢI PHẦN MỀM để cập nhật thủ công.");
					}
				} else {
					Custom_Toast
							.show_red_toast(
									Ac_Tai_Phan_Mem.this,
									"Lỗi khi cập nhật phần mềm \nVui lòng bấm vào TẢI PHẦN MỀM để cập nhật thủ công.");
				}

			}

		}
}
