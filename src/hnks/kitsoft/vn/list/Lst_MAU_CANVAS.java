package hnks.kitsoft.vn.list;

import java.util.ArrayList;
import java.util.List;

import com.thtsoftlib.function.ThtShow;

import hnks.kitsoft.vn.R;
import hnks.kitsoft.vn.database.DBAdapter;
import hnks.kitsoft.vn.object.Obj_MAU_CANVAS;
import hnks.kitsoft.vn.vesodo.Ve_So_Do_Activity;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

public class Lst_MAU_CANVAS extends BaseAdapter{
	
	private Ve_So_Do_Activity activity;
	List<Obj_MAU_CANVAS> ARR_all;
	List<Obj_MAU_CANVAS> ARR_list;
	DBAdapter mdb;

	public Lst_MAU_CANVAS(List<Obj_MAU_CANVAS> ARR_all, Ve_So_Do_Activity ctx,DBAdapter mdb) {
		super();
		
		this.activity = ctx;
		this.ARR_all=ARR_all;
		this.ARR_list = new ArrayList<Obj_MAU_CANVAS>(this.ARR_all);
		this.mdb = mdb;
	}

	public int getCount() {
		return ARR_list.size();
	}

	public Obj_MAU_CANVAS getItem(int pos) {
		return ARR_list.get(pos);
	}

	public long getItemId(int pos) {
		return ARR_list.get(pos).id_MAU;
	}
	@SuppressLint("DefaultLocale") public void get_SEARCH_MAU(String KEY) {
		ARR_list.clear();
		if (KEY.length() > 0 ) {
			for (Obj_MAU_CANVAS mcv : ARR_all) {
				KEY = AccentRemover.LoaiBoDauTV(KEY).toLowerCase();
				if ((AccentRemover.LoaiBoDauTV(mcv.TEN_MAU).toLowerCase()).contains(KEY))
					ARR_list.add(mcv);
			}
		}else{
			this.ARR_list=new ArrayList<Obj_MAU_CANVAS>(this.ARR_all);
		}
		notifyDataSetChanged();
	}

	public static class ViewHolder {
		public Button btn_VE_MAU;
		public Button btn_XOA_MAU;
		public TextView tv_TEN;
	}

	@SuppressLint("InflateParams") public View getView(final int pos, View ConvertView, ViewGroup parent) {
		ViewHolder View;
		LayoutInflater inflater = ((Activity) activity).getLayoutInflater();
		if (ConvertView == null) {
			View = new ViewHolder();
			ConvertView = inflater.inflate(R.layout.row_mau_canvas, null);
			View.tv_TEN = (TextView) ConvertView
					.findViewById(R.id.tv_TEN_MAU_row_canvas);
			View.btn_VE_MAU = (Button) ConvertView
					.findViewById(R.id.btn_ve_mau_row_mau_canvas);
			View.btn_XOA_MAU = (Button) ConvertView
					.findViewById(R.id.btn_xoa_mau_row_mau_canvas);
			
			
			ConvertView.setTag(View);
		} else {
			View = (ViewHolder) ConvertView.getTag();
		}
		// khai bao hoa don
		final Obj_MAU_CANVAS HD = ARR_list.get(pos);

	        //convert byte to bitmap take from contact class
	       
//	        View.iv_HINH.setImageBitmap(theImage);
	        View.tv_TEN.setText(HD.TEN_MAU);
	        View.btn_VE_MAU.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					activity.VE_MAU(HD.TEN_MAU);
					
				}
			});
	        View.btn_XOA_MAU.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					XOA_MAU(HD);
					
				}
			});
		return ConvertView;
	}
	public void XOA_MAU(final Obj_MAU_CANVAS MCV) {
		final Dialog dialog = new Dialog(activity, R.style.FullHeightDialog);
		dialog.setContentView(R.layout.dialog_yes_no);
		dialog.setCancelable(true);
		TextView message = (TextView) dialog
				.findViewById(R.id.tvmessagedialogtext);
		message.setText(activity.getString(R.string.xoa_mau_dang_chon)+" "+MCV.TEN_MAU+" "+
				activity.getString(R.string.khong_));
		Button yes = (Button) dialog.findViewById(R.id.bmessageDialogYes);
		yes.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				try {
					mdb.delete_MAU_CANVAS(MCV);
					ARR_list.remove(MCV);
					notifyDataSetChanged();
				} catch (Exception e) {
					ThtShow.show_crouton_toast(activity,
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

}
