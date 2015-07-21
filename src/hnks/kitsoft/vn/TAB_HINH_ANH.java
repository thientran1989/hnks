package hnks.kitsoft.vn;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.List;

import hnks.kitsoft.utils.Length_text;
import hnks.kitsoft.utils.Utils;
import hnks.kitsoft.utils.Variables;
import hnks.kitsoft.vn.R;
import hnks.kitsoft.vn.camera.DisplayImageActivity;
import hnks.kitsoft.vn.custom_dialog.Dialog_listview_ghichu;
import hnks.kitsoft.vn.database.DBAdapter;
import hnks.kitsoft.vn.dokhoangcach.BitmapHelper;
import hnks.kitsoft.vn.dokhoangcach.CameraActivity;
import hnks.kitsoft.vn.list.Adapter_nhap_lieu;
import hnks.kitsoft.vn.list.Lst_HSO_HINH;
import hnks.kitsoft.vn.object.Obj_HSO_HINH;
import hnks.kitsoft.vn.object.Obj_nhap_lieu;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

import com.thtsoftlib.function.ThtCanvas;
import com.thtsoftlib.function.ThtShow;
import com.thtsoftlib.function.ThtTime;
import com.thtsoftlib.function.Tht_Screen;

public class TAB_HINH_ANH extends Activity {

	ImageView imb_DODAC;
	Button  btn_XOA_ALL,btn_camera;
	byte[] imageName;
	Bitmap theImage;
	public static int w, h;
	DBAdapter mdb;
	GridView grid_HINH_ANH;
	ListView lv_hinhanh;
	Lst_HSO_HINH mAdapter;
    Bitmap bitmap;
    List<Obj_HSO_HINH> list_hinh;
    int LENH=0;
    Dialog_listview_ghichu dialog_GC ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Tht_Screen.hide_title(this);
		setContentView(R.layout.tab_hinhanh);
		
		grid_HINH_ANH = (GridView) findViewById(R.id.grid_HINHANH_tab_hinhanh);
		lv_hinhanh = (ListView)findViewById(R.id.lv_HINHANH_tab_hinhanh);
		mdb = new DBAdapter(this);
		list_hinh = mdb.get_list_hinh(Variables.HSCT_CHON.MA_YCAU_KNAI);
		mAdapter = new Lst_HSO_HINH(list_hinh, TAB_HINH_ANH.this);
//		grid_HINH_ANH.setAdapter(mAdapter);
		lv_hinhanh.setAdapter(mAdapter);
		w = (Tht_Screen.get_screen_width(this)/10)*7;
		h = (Tht_Screen.get_screen_heigth(this)/10)*5;
		imb_DODAC = (ImageView) findViewById(R.id.imb_DODAC_tab_hinhanh);
		imb_DODAC.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if(cameraNotDetected()){
		        	String message = "No camera";
		        	ThtShow.show_crouton_toast(TAB_HINH_ANH.this, message, tht.library.crouton.Style.ALERT);
		        }else{
		        	Intent intent = new Intent(TAB_HINH_ANH.this, CameraActivity.class);
			    	startActivityForResult(intent, Variables.REQ_CAMERA_IMAGE);
		        }
				

			}
		});
		btn_XOA_ALL=(Button)findViewById(R.id.btn_xoa_het_tab_hinhanh);
		btn_XOA_ALL.setText("Xoá\nhết");
		btn_camera = (Button) findViewById(R.id.btn_camera_tab_hinhanh);
		btn_camera.setText("Chụp\nảnh");
		btn_camera.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				try {
					callCamera();
				} catch (Exception e) {
					
				}
			}
		});
	}

	/**
	 * open camera method
	 */
	public void callCamera() {
		Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
		File file = new File(Environment.getExternalStorageDirectory()+File.separator + "image.jpg");
		intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
		startActivityForResult(intent, Variables.CAPTURE_IMAGE_FULLSIZE_ACTIVITY_REQUEST_CODE);

	}
	public static Bitmap decodeSampledBitmapFromFile(String path, int reqWidth, int reqHeight) 
	{
	     
	    //First decode with inJustDecodeBounds=true to check dimensions
	    final BitmapFactory.Options options = new BitmapFactory.Options();
	    options.inJustDecodeBounds = true;
	    BitmapFactory.decodeFile(path, options);
	 
	    // Calculate inSampleSize, Raw height and width of image
	    final int height = options.outHeight;
	    final int width = options.outWidth;
	    options.inPreferredConfig = Bitmap.Config.RGB_565;
	    int inSampleSize = 1;
	 
	    if (height > reqHeight) 
	    {
	        inSampleSize = Math.round((float)height / (float)reqHeight);
	    }
	    int expectedWidth = width / inSampleSize;
	 
	    if (expectedWidth > reqWidth) 
	    {
	        //if(Math.round((float)width / (float)reqWidth) > inSampleSize) // If bigger SampSize..
	        inSampleSize = Math.round((float)width / (float)reqWidth);
	    }
	 
	    options.inSampleSize = inSampleSize;
	 
	    // Decode bitmap with inSampleSize set
	    options.inJustDecodeBounds = false;
	 
	    return BitmapFactory.decodeFile(path, options);
	}
	protected void onActivityResult(int requestCode, int resultCode, Intent data) 
	{  
	    if (requestCode == Variables.CAPTURE_IMAGE_FULLSIZE_ACTIVITY_REQUEST_CODE
	    		 && resultCode == RESULT_OK) 
	    {
	       try {
	    	   File file = new File(Environment.getExternalStorageDirectory()+File.separator + "image.jpg");
		       Bitmap bitmap = decodeSampledBitmapFromFile(file.getAbsolutePath(), 1000, 700);
		       // them text tren hinh
		       Bitmap newBitmap = null;
		       Config config = bitmap.getConfig();
		       if(config == null){
		        config = Bitmap.Config.ARGB_8888;
		       }
		       
		       newBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), config);
		       Canvas newCanvas = new Canvas(newBitmap);
		       Paint paint = new Paint();
	           paint.setColor(Color.RED);
	           paint.setStrokeWidth(get_do_lon_chu_thap(newBitmap)); 
	           paint.setStyle(Style.FILL);
	           paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER));
	           
	           newCanvas.drawBitmap(bitmap,0,0, paint);
	           
	           paint.setColor(Color.RED);
	           paint.setStrokeWidth(get_do_lon_chu_thap(newBitmap)); 
	           paint.setTextSize(get_do_lon_chu_thap(newBitmap)*5);
	           paint.setStyle(Style.FILL);
	           paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER)); 
	          
	           Paint paint2 = new Paint();
	           paint2.setColor(Color.RED); 
	           paint2.setStrokeWidth(get_do_lon_chu_thap(newBitmap)); 
	           paint2.setTextSize(get_do_lon_chu_thap(newBitmap)*3);
	           paint2.setStyle(Style.FILL);
	           paint2.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER));
	           // some more settings...
	           String TEN = Variables.HSCT_CHON.getMA_YCAU_KNAI()+" : "+Variables.HSCT_CHON.getTEN_KHANG();
	           String DC  =Variables.HSCT_CHON.getSO_NHA()+", "+Variables.HSCT_CHON.getDUONG_PHO();
	           // ve them text ten dia chi kh
	           ThtCanvas.ve_text_on_canvas(newCanvas, newBitmap, paint, TEN, 1,1,0);
	           ThtCanvas.ve_text_on_canvas(newCanvas, newBitmap, paint2, DC, 2,0,0);
	           ThtCanvas.ve_text_on_canvas(newCanvas, newBitmap, paint, "Tên NV : "+Variables.DNV.getTenNhanVien(), 3,0,(float)0.25);
	           ThtCanvas.ve_text_on_canvas(newCanvas, newBitmap, paint2,"Ngày giờ : "+ThtTime.get_curent_full_date_time(), 4,0,(float)0.25);
	          // het test
	           ByteArrayOutputStream stream = new ByteArrayOutputStream();
	           newBitmap.compress(Bitmap.CompressFormat.JPEG, 50, stream);
				byte imageInByte[] = stream.toByteArray();
		       
		       Obj_HSO_HINH H = new Obj_HSO_HINH();
		       H.setDA_CHUYEN(Utils.TT_CHUA_CHUYEN);
		       H.setHINH(imageInByte);
		       H.setMA_DVIQLY(Variables.HSCT_CHON.getMA_DVIQLY());
		       H.setMA_LOAI_HINH("TT");
		       H.setMA_YCAU_KNAI(Variables.HSCT_CHON.getMA_YCAU_KNAI());
		       H.setTEN_HINH(Variables.HSCT_CHON.getMA_YCAU_KNAI()+"-"+Variables.HSCT_CHON.getTEN_KHANG());
		       H.setTHOI_GIAN(Memory.getNow());
		       mdb.Insert_HSO_HINH(H);
		       H = mdb.get_MAX_STT_HINHANH();
			   list_hinh.add(0,H);
		       mAdapter.notifyDataSetChanged();
		       grid_HINH_ANH.setAdapter(mAdapter);
		       Intent i = new Intent();
		       setResult(RESULT_OK, i);
		} catch (Exception e) {
			ThtShow.show_crouton_toast(TAB_HINH_ANH.this,  "Khong luu duoc hinh camera", tht.library.crouton.Style.ALERT);
		}
	    }else if(requestCode == Variables.REQ_CAMERA_IMAGE && resultCode == RESULT_OK){
			String imgPath = data.getStringExtra(CameraActivity.EXTRA_IMAGE_PATH);
			displayImage(imgPath);
		} else{
			ThtShow.show_crouton_toast(TAB_HINH_ANH.this,  "Không chụp được ảnh", tht.library.crouton.Style.ALERT);
		}
	    
	}
	private void displayImage(String path) {
	  //Get our saved file into a bitmap object:
		try {
			   Bitmap newBitmap = null;
		       Bitmap bitmap = BitmapHelper.decodeSampledBitmap(path, 1000, 700);
		       bitmap = ResizedBitmap(bitmap, Tht_Screen.get_screen_heigth(TAB_HINH_ANH.this));
		                  
		       Config config = bitmap.getConfig();
		       if(config == null){
		        config = Bitmap.Config.ARGB_8888;
		       }
		       
//		       newBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), config);
		       newBitmap = Bitmap.createBitmap(Tht_Screen.get_screen_width(TAB_HINH_ANH.this),Tht_Screen.get_screen_heigth(TAB_HINH_ANH.this), config);
		       Canvas newCanvas = new Canvas(newBitmap);
		       int DON_LO=get_canh_chu_thap(newBitmap);
		       Paint paint = new Paint();
	           paint.setColor(Color.RED);
	           paint.setStrokeWidth(get_do_lon_chu_thap(newBitmap)); 
	           paint.setStyle(Style.FILL);
	           paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER));
	           
	           newCanvas.drawBitmap(bitmap,( (Tht_Screen.get_screen_width(TAB_HINH_ANH.this)-bitmap.getWidth())/2),0, paint);
	           ThtCanvas.ve_chu_thap(newCanvas, newBitmap, paint, DON_LO);
	           
	           paint.setColor(Color.RED);
	           paint.setStrokeWidth(get_do_lon_chu_thap(newBitmap)); 
	           paint.setTextSize(get_do_lon_chu_thap(newBitmap)*5);
	           paint.setStyle(Style.FILL);
	           paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER)); 
	          
	           Paint paint2 = new Paint();
	           paint2.setColor(Color.RED); 
	           paint2.setStrokeWidth(get_do_lon_chu_thap(newBitmap)); 
	           paint2.setTextSize(get_do_lon_chu_thap(newBitmap)*3);
	           paint2.setStyle(Style.FILL);
	           paint2.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER));
	           // some more settings...
	           String TEN = Variables.HSCT_CHON.getMA_YCAU_KNAI()+" : "+Variables.HSCT_CHON.getTEN_KHANG();
	           String DC  =Variables.HSCT_CHON.getSO_NHA()+", "+Variables.HSCT_CHON.getDUONG_PHO();
	           // ve them text ten dia chi kh
	           ThtCanvas.ve_text_on_canvas(newCanvas, newBitmap, paint, TEN, 1,1,0);
	           ThtCanvas.ve_text_on_canvas(newCanvas, newBitmap, paint2, DC, 2,0,0);
	           ThtCanvas.ve_text_on_canvas(newCanvas, newBitmap, paint, "Tên NV : "+Variables.DNV.getTenNhanVien(), 3,0,(float)0.25);
	           ThtCanvas.ve_text_on_canvas(newCanvas, newBitmap, paint2,"Ngày giờ : "+ThtTime.get_curent_full_date_time(), 4,0,(float)0.25);
	           ByteArrayOutputStream stream = new ByteArrayOutputStream();
	           newBitmap.compress(Bitmap.CompressFormat.JPEG, 70, stream);	       
				byte imageInByte[] = stream.toByteArray();
				Obj_HSO_HINH H = new Obj_HSO_HINH(Variables.DNV.MaDV, Variables.HSCT_CHON.getMA_YCAU_KNAI(), 0, 
						Variables.HSCT_CHON.getTEN_KHANG(), imageInByte, Memory.getNow(), 0,"TT");
				mdb.Insert_HSO_HINH(H);	
				H = mdb.get_MAX_STT_HINHANH();
				list_hinh.add(0,H);
				mAdapter.notifyDataSetChanged();
//				ThtShow.show_crouton_toast(TAB_HINH_ANH.this,  "da luu anh !", tht.library.crouton.Style.INFO);
		} catch (Exception e) {
			ThtShow.show_crouton_toast(TAB_HINH_ANH.this,  "Khong luu duoc hinh +", tht.library.crouton.Style.ALERT);
		}
		   
	}
	public Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();
        float bitmapRatio = width / height;
        if (bitmapRatio > 0) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
	}
	public Bitmap ResizedBitmap(Bitmap image, int max_h) {
		float width = image.getWidth();
		float height = image.getHeight();
        float bitmapRatio = max_h / height;
        if (bitmapRatio > 0) {
            width =(width*bitmapRatio);
            height = max_h;
        } else {
//            height = maxSize;
//            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, (int)width, (int)height, true);
	}

	public void XEM_HINH(){ 
			Intent intent = new Intent(TAB_HINH_ANH.this,
					DisplayImageActivity.class);
			startActivity(intent);
	}

	public void XOA_1_HINH(final Obj_HSO_HINH HINH_XOA){
		
		final Dialog dialog = new Dialog(this, R.style.FullHeightDialog);
		dialog.setContentView(R.layout.dialog_yes_no);
		dialog.setCancelable(true);
		TextView message = (TextView) dialog
				.findViewById(R.id.tvmessagedialogtext);
		message.setText(getString(R.string.xoa_hinh_dang_chon));
		Button yes = (Button) dialog.findViewById(R.id.bmessageDialogYes);
		yes.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				try {
					mdb.delete_HSO_HINH(HINH_XOA);
					 list_hinh.remove(HINH_XOA);
					 mAdapter.notifyDataSetChanged();
					 Intent i = new Intent();
				     setResult(RESULT_OK, i);
				} catch (Exception e) {
					ThtShow.show_crouton_toast(TAB_HINH_ANH.this,
							e.toString(), tht.library.crouton.Style.ALERT);
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
	public void xoa_tat_ca_hinh(View v){
		final Dialog dialog = new Dialog(this, R.style.FullHeightDialog);
		dialog.setContentView(R.layout.dialog_yes_no);
		dialog.setCancelable(true);
		TextView message = (TextView) dialog
				.findViewById(R.id.tvmessagedialogtext);
		message.setText(getString(R.string.xoa_tat_ca_hinh));
		Button yes = (Button) dialog.findViewById(R.id.bmessageDialogYes);
		yes.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				try {
					mdb.delete_HSO_HINH_BY_MA_YCAU_KNAI(Variables.HSCT_CHON.getMA_YCAU_KNAI());
					list_hinh.clear();
					mAdapter.notifyDataSetChanged();
					Intent i = new Intent();
				       setResult(RESULT_OK, i);
				} catch (Exception e) {
					ThtShow.show_crouton_toast(TAB_HINH_ANH.this,
							e.toString(), tht.library.crouton.Style.ALERT);
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
	public void thoat(View v){
		finish();
	}
	 private boolean cameraNotDetected() {
			return !getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA);
		}
	 public void SHOW_DIALOG_SUA_TEN_HINH(final Obj_HSO_HINH HSH) {
		 final List<Obj_nhap_lieu> list_nhap_lieu = mdb.get_list_NL(Variables.NL_LOAI_HO_SO);
		 	Adapter_nhap_lieu mAdapter_TT = new Adapter_nhap_lieu(list_nhap_lieu, TAB_HINH_ANH.this, mdb);		 	
			dialog_GC = new Dialog_listview_ghichu(TAB_HINH_ANH.this,Length_text.ML_TEN_HINH);
			dialog_GC.show();
			Dialog_listview_ghichu.lv_ghichu.setAdapter(mAdapter_TT);
			Dialog_listview_ghichu.edt_ghichu.setText(HSH.getTEN_HINH());
			Dialog_listview_ghichu.edt_ghichu.setSelection(Dialog_listview_ghichu.edt_ghichu.getText().length());
			Dialog_listview_ghichu.lv_ghichu.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
						long arg3) {
					Dialog_listview_ghichu.edt_ghichu.setText(list_nhap_lieu.get(arg2).nhap_lieu);
					Dialog_listview_ghichu.edt_ghichu.setSelection(Dialog_listview_ghichu.edt_ghichu.getText().length());
				}
			});
			Dialog_listview_ghichu.btn_ok.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					if (Dialog_listview_ghichu.edt_ghichu.getText().length()<=dialog_GC.max_length){
						HSH.setTEN_HINH(Dialog_listview_ghichu.edt_ghichu.getText().toString());
						mdb.update_HSO_HINH(HSH);
						Obj_nhap_lieu NL = new Obj_nhap_lieu(Dialog_listview_ghichu.edt_ghichu.getText().toString(), Variables.NL_LOAI_HO_SO);
						if (mdb.chua_ton_tai_nhap_lieu(NL)){
							DBAdapter.Insert_NHAP_LIEU(NL);
						}
						mAdapter.notifyDataSetChanged();
						dialog_GC.dismiss();
						LENH =0;
					}else{
						Length_text.alert_length(TAB_HINH_ANH.this, dialog_GC.max_length);
					}
						
				}
			});
			WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
			lp.copyFrom(dialog_GC.getWindow().getAttributes());
			lp.width = Tht_Screen.get_screen_width_percent(TAB_HINH_ANH.this, 90);
			lp.height = Tht_Screen.get_screen_heigth_percent(TAB_HINH_ANH.this, 90);
			dialog_GC.getWindow().setAttributes(lp);
		}
	 public int get_canh_chu_thap(Bitmap bit){
		 int dolon =0;
		 if (bit!=null){
			 try {
				dolon = bit.getHeight()/13;	
				} catch (Exception e) {
					// TODO: handle exception
				}
		 }
		 return dolon;
		
	 }
	 public int get_do_lon_chu_thap(Bitmap bit){
		 int dolon =0;
		 if (bit!=null){
			 try {
				dolon = bit.getHeight()/100;	
				} catch (Exception e) {
					// TODO: handle exception
				}
		 }
		 return dolon;
		
	 }
		
}
