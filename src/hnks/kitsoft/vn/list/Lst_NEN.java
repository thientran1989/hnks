package hnks.kitsoft.vn.list;

import java.io.ByteArrayInputStream;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import hnks.kitsoft.vn.R;
import hnks.kitsoft.vn.object.Obj_HSO_HINH;
import hnks.kitsoft.vn.vesodo.Ve_So_Do_Activity;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Lst_NEN extends BaseAdapter{
	

	private Ve_So_Do_Activity activity;
	public static int STT;
	public static Collection<Obj_HSO_HINH> HDchecks = new HashSet<Obj_HSO_HINH>();
	final int CONTEXT_MENU_DELETE_ITEM = 1;
	final int CONTEXT_MENU_UPDATE = 2;
	public static String LENH;
	public static int POS_HINH;
	List<Obj_HSO_HINH> ARR;

	public Lst_NEN(List<Obj_HSO_HINH> ARR, Ve_So_Do_Activity ctx) {
		super();
		
		this.activity = ctx;
		this.ARR=ARR;
		HDchecks.clear();
	}

	public int getCount() {
		return ARR.size();
	}

	public Obj_HSO_HINH getItem(int pos) {
		return ARR.get(pos);
	}

	public long getItemId(int pos) {
		return ARR.get(pos).STT;
	}

	public static class ViewHolder {
		//public Button btn_SUA;
		public ImageView iv_HINH;
		public TextView tv_TEN;
		public Button btn_chon;
	}

	@SuppressLint("InflateParams") public View getView(final int pos, View ConvertView, ViewGroup parent) {
		ViewHolder View;
		LayoutInflater inflater = ((Activity) activity).getLayoutInflater();
		if (ConvertView == null) {
			View = new ViewHolder();
			ConvertView = inflater.inflate(R.layout.row_nen, null);
			View.iv_HINH = (ImageView) ConvertView
					.findViewById(R.id.iv_HINH_row_nen);
			View.tv_TEN = (TextView) ConvertView
					.findViewById(R.id.tv_TENHNH_row_nen);
			View.btn_chon = (Button) ConvertView
					.findViewById(R.id.btn_chon_row_nen);
			
			
			ConvertView.setTag(View);
		} else {
			View = (ViewHolder) ConvertView.getTag();
		}
		// khai bao hoa don
		final Obj_HSO_HINH HINH = ARR.get(pos);
			if (HINH!=null){
				try {
					byte[] outImage=HINH.HINH;
			        ByteArrayInputStream imageStream = new ByteArrayInputStream(outImage);
			        Bitmap theImage = BitmapFactory.decodeStream(imageStream);
			        View.iv_HINH.setImageBitmap(theImage);
			        View.tv_TEN.setText(HINH.TEN_HINH);
			        View.tv_TEN.setTextColor(Color.WHITE);
				} catch (Exception e) {
					
				}
			}
			View.btn_chon.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					activity.ve_lai_nen();
					
				}
			});
	       
		return ConvertView;
	}
	

}
