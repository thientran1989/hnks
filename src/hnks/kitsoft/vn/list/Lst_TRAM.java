package hnks.kitsoft.vn.list;

import java.util.ArrayList;
import java.util.List;
import hnks.kitsoft.vn.R;
import hnks.kitsoft.vn.custom_dialog.CAPNHAT_HSO;
import hnks.kitsoft.vn.object.Obj_DANHMUC_TRAM;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

@SuppressLint("DefaultLocale") public class Lst_TRAM extends BaseAdapter{
	

	private CAPNHAT_HSO activity;
	List<Obj_DANHMUC_TRAM> ARR_all;
	List<Obj_DANHMUC_TRAM> ARR_list;
	String KEY="";

	public Lst_TRAM(List<Obj_DANHMUC_TRAM> ARR_all, CAPNHAT_HSO ctx) {
		super();
		
		this.activity = ctx;
		this.ARR_all=ARR_all;
		this.ARR_list=new ArrayList<Obj_DANHMUC_TRAM>(this.ARR_all);
	}
	@SuppressLint("DefaultLocale") public void get_SEARCH_TRAM(String KEY) {
		ARR_list.clear();
		if (KEY.length() > 0 ) {
			for (Obj_DANHMUC_TRAM DMT : ARR_all) {
				KEY = AccentRemover.LoaiBoDauTV(KEY).toLowerCase();
				if ((AccentRemover.LoaiBoDauTV(DMT.TEN_TRAM).toLowerCase()).contains(KEY)
						|| (AccentRemover.LoaiBoDauTV(DMT.MA_TRAM).toLowerCase()).contains(KEY) || (AccentRemover.LoaiBoDauTV(DMT.DINH_DANH).toLowerCase()).contains(KEY))
					ARR_list.add(DMT);
			}
		}else{
			this.ARR_list=new ArrayList<Obj_DANHMUC_TRAM>(this.ARR_all);
		}
	}

	public int getCount() {
		return ARR_list.size();
	}

	public Obj_DANHMUC_TRAM getItem(int pos) {
		return ARR_list.get(pos);
	}

	public long getItemId(int pos) {
		return 0;
	}

	public static class ViewHolder {
		public TextView TEN_TRAM;
		public TextView MA_TRAM;
		public TextView CS_TRAM;
		public TextView DINH_DANH;
		public TextView DONG_DIEN;
		public Button btn_CHON_TRAM;
	}

	@SuppressLint("InflateParams") public View getView(final int pos, View ConvertView, ViewGroup parent) {
		ViewHolder View;
		LayoutInflater inflater = ((Activity) activity).getLayoutInflater();
		if (ConvertView == null) {
			View = new ViewHolder();
			ConvertView = inflater.inflate(R.layout.row_tram, null);
			View.TEN_TRAM = (TextView) ConvertView
					.findViewById(R.id.tv_TEN_TRAM_row_tram);
			View.MA_TRAM = (TextView) ConvertView
					.findViewById(R.id.tv_MA_TRAM_row_tram);
			View.CS_TRAM = (TextView) ConvertView
					.findViewById(R.id.tv_CS_TRAM_row_tram);
			View.DINH_DANH = (TextView) ConvertView
					.findViewById(R.id.tv_DINH_DANH_row_tram);
			View.DONG_DIEN = (TextView) ConvertView
					.findViewById(R.id.tv_DONG_DIEN_row_tram);
			View.btn_CHON_TRAM = (Button) ConvertView
					.findViewById(R.id.btn_CHON_TRAM_row_tram);
			
			
			ConvertView.setTag(View);
		} else {
			View = (ViewHolder) ConvertView.getTag();
		}
		// khai bao hoa don
		final Obj_DANHMUC_TRAM HD = ARR_list.get(pos);

	        View.TEN_TRAM.setText(" "+HD.TEN_TRAM);
	        View.MA_TRAM.setText(" "+HD.MA_TRAM);
	        View.CS_TRAM.setText(" "+HD.CSUAT_TRAM);
	        View.DINH_DANH.setText(" "+HD.DINH_DANH);
	        View.DONG_DIEN.setText(" "+HD.DONGDIEN);
	        View.btn_CHON_TRAM.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					activity.SET_TRAM(HD);
					
				}
			});
		return ConvertView;
	}
	


}
