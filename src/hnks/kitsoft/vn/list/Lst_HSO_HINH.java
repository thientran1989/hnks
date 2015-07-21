package hnks.kitsoft.vn.list;

import java.util.List;

import com.thtsoftlib.function.Thtcovert;

import hnks.kitsoft.utils.Variables;
import hnks.kitsoft.vn.R;
import hnks.kitsoft.vn.TAB_HINH_ANH;
import hnks.kitsoft.vn.object.Obj_HSO_HINH;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Lst_HSO_HINH extends BaseAdapter{

	private TAB_HINH_ANH activity;
	List<Obj_HSO_HINH> list_hinh;

	public Lst_HSO_HINH(List<Obj_HSO_HINH> list_hinh, TAB_HINH_ANH ctx) {
		super();
		
		this.activity = ctx;
		this.list_hinh=list_hinh;
	}

	public int getCount() {
		return list_hinh.size();
	}

	public Obj_HSO_HINH getItem(int pos) {
		return list_hinh.get(pos);
	}

	public long getItemId(int pos) {
		return list_hinh.get(pos).STT;
	}

	public static class ViewHolder {
		//public Button btn_SUA;
		public ImageView iv_HINH;
		public TextView tv_TEN;
		public Button btn_SUA;
		public Button imb_XOA_HINH;

	}

	@SuppressLint("InflateParams") public View getView(final int pos, View ConvertView, ViewGroup parent) {
		ViewHolder View;
		LayoutInflater inflater = ((Activity) activity).getLayoutInflater();
		if (ConvertView == null) {
			View = new ViewHolder();
			ConvertView = inflater.inflate(R.layout.row_hinhanh, null);
			View.iv_HINH = (ImageView) ConvertView
					.findViewById(R.id.iv_HINH_row_hinh);
			View.tv_TEN = (TextView) ConvertView
					.findViewById(R.id.tv_TENHNH_row_hinh);
			View.imb_XOA_HINH = (Button) ConvertView
					.findViewById(R.id.imb_XOA_HINH_row_hinh);
			
			
			ConvertView.setTag(View);
		} else {
			View = (ViewHolder) ConvertView.getTag();
		}
		// khai bao hoa don
		final Obj_HSO_HINH HINH = list_hinh.get(pos);
		if (HINH!=null){
			try {
				View.iv_HINH = (ImageView) ConvertView
						.findViewById(R.id.iv_HINH_row_hinh);
				View.tv_TEN = (TextView) ConvertView
						.findViewById(R.id.tv_TENHNH_row_hinh);
				View.btn_SUA = (Button) ConvertView
						.findViewById(R.id.btn_STT_row_hinh);
				View.tv_TEN.setText(HINH.TEN_HINH);  
				Bitmap theImage = Thtcovert.byte_to_image(HINH.HINH);
				View.iv_HINH.setImageBitmap(theImage);
			} catch (Exception e) {
				View.iv_HINH.setImageResource(R.drawable.yellow);
			}
		}
			View.btn_SUA.setOnClickListener(new OnClickListener() {	
				@Override
				public void onClick(View v) {
					activity.SHOW_DIALOG_SUA_TEN_HINH(HINH);
				}
			});
			View.imb_XOA_HINH.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					activity.XOA_1_HINH(HINH);
					
				}
			});
		        //convert byte to bitmap take from contact class
		        View.iv_HINH.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						Variables.HINH_CHON = HINH;
						activity.XEM_HINH();
					}
				});


			return ConvertView;
	}
	
	

}
