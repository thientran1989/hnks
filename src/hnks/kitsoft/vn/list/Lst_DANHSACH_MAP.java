package hnks.kitsoft.vn.list;

import hnks.kitsoft.vn.Ban_Do_Activity;
import hnks.kitsoft.vn.R;
import hnks.kitsoft.vn.object.Obj_HSO_TOADO;

import java.util.List;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class Lst_DANHSACH_MAP extends BaseAdapter{
	
	private Ban_Do_Activity activity;
	List<Obj_HSO_TOADO> list_toado;

	public Lst_DANHSACH_MAP(Ban_Do_Activity ctx,List<Obj_HSO_TOADO> list_toado) {
		super();
		this.activity = ctx;
		this.list_toado = list_toado;
	}

	public int getCount() {
		return list_toado.size();
	}

	public Obj_HSO_TOADO getItem(int pos) {
		return list_toado.get(pos);
	}

	public long getItemId(int pos) {
		return getItem(pos).getSTT();
	}

	public static class ViewHolder {
		public TextView tv_TEN_DIEM;
	}

	public View getView(int pos, View ConvertView, ViewGroup parent) {
		ViewHolder View;
		LayoutInflater inflater = activity.getLayoutInflater();
		if (ConvertView == null) {
			View = new ViewHolder();
			ConvertView = inflater.inflate(R.layout.row_map, null);
			View.tv_TEN_DIEM = (TextView) ConvertView
					.findViewById(R.id.tv_TEN_DIEM_row_map);
			ConvertView.setTag(View);
		} else {
			View = (ViewHolder) ConvertView.getTag();
		}
		// khai bao hoa don
		final Obj_HSO_TOADO HD = getItem(pos);
		View.tv_TEN_DIEM.setText(HD.TEN_DIEM);
		return ConvertView;
	}


}
