package hnks.kitsoft.vn.list;

import hnks.kitsoft.vn.R;
import hnks.kitsoft.vn.database.DBAdapter;
import hnks.kitsoft.vn.object.Obj_nhap_lieu;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class Adapter_nhap_lieu extends BaseAdapter{

	private Activity activity;
	List<Obj_nhap_lieu> list_vattu = new ArrayList<Obj_nhap_lieu>();
	DBAdapter mdb;

	public Adapter_nhap_lieu(List<Obj_nhap_lieu> list_vattu, Activity ctx,DBAdapter mdb) {
		super();
		this.activity = ctx;
		this.list_vattu = list_vattu;
		this.mdb = mdb;
	}

	public int getCount() {
		return  list_vattu.size();
	}

	public Obj_nhap_lieu getItem(int pos) {
		return list_vattu.get(pos);
	}

	public long getItemId(int pos) {
		return 0;
	}

	public static class ViewHolder {
		public TextView tv_nhaplieu;
		public TextView tv_xoa;

	}

	@SuppressLint("InflateParams") public View getView(int pos, View ConvertView, ViewGroup parent) {
		ViewHolder View;
		LayoutInflater inflater = activity.getLayoutInflater();
		if (ConvertView == null) {
			View = new ViewHolder();
			ConvertView = inflater.inflate(R.layout.row_nhap_lieu, null);
			View.tv_nhaplieu = (TextView) ConvertView
					.findViewById(R.id.tv_nhaplieu_row_nhaplieu);
			View.tv_xoa = (TextView) ConvertView
					.findViewById(R.id.tv_xoa_nhaplieu_row_nhaplieu);
			ConvertView.setTag(View);
		} else {
			View = (ViewHolder) ConvertView.getTag();
		}
		// khai bao hoa don
		final Obj_nhap_lieu NL = getItem(pos);
		View.tv_nhaplieu.setText(NL.nhap_lieu);
		View.tv_xoa.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (NL.loai_nhap_lieu>0){
					mdb.delete_NHAP_LIEU(NL);
					list_vattu.remove(NL);
					notifyDataSetChanged();
				}
				
			}
		});

		return ConvertView;

	}

}
