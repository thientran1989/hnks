package hnks.kitsoft.vn.list;

import java.util.List;

import hnks.kitsoft.vn.R;
import hnks.kitsoft.vn.TAB_BAN_DO;
import hnks.kitsoft.vn.object.Obj_HSO_TOADO;
import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class Lst_TOA_DO extends BaseAdapter {
	TAB_BAN_DO activity;
	List<Obj_HSO_TOADO> list_toado;

	public Lst_TOA_DO(TAB_BAN_DO ctx,List<Obj_HSO_TOADO> list_toado) {
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
		public Button btn_SUA;
		public TextView tv_TEN_DIEM;
		public TextView tv_X;
		public CheckBox checkrow;
		public Button imb_XOA_HINH;
	}

	@SuppressLint("InflateParams") public View getView(int pos, View ConvertView, ViewGroup parent) {
		ViewHolder View;
		LayoutInflater inflater = activity.getLayoutInflater();
		if (ConvertView == null) {
			View = new ViewHolder();
			ConvertView = inflater.inflate(R.layout.row_ban_do, null);
			View.btn_SUA = (Button) ConvertView
					.findViewById(R.id.btn_SUA_TEN_row_bando);
			View.tv_TEN_DIEM = (TextView) ConvertView
					.findViewById(R.id.tv_TEN_DIEM_row_ban_do);
			View.tv_X = (TextView) ConvertView
					.findViewById(R.id.tv_toado_row_ban_do);
			View.imb_XOA_HINH = (Button) ConvertView
					.findViewById(R.id.imb_XOA_DIEM_row_bando);
			ConvertView.setTag(View);
		} else {
			View = (ViewHolder) ConvertView.getTag();
		}
		final Obj_HSO_TOADO TD = getItem(pos);
		View.btn_SUA.setOnClickListener(new OnClickListener() {	
			@Override
			public void onClick(View v) {
				activity.SHOW_DIALOG_SUA_TEN_DIEM(TD);
			}
		});
		View.tv_TEN_DIEM.setText(TD.TEN_DIEM);
		View.tv_X.setText("( " + String.valueOf(TD.X)+","+String.valueOf(TD.Y)+" )");
		View.imb_XOA_HINH.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				activity.xoa_1_diem(TD);
				
			}
		});
		
		return ConvertView;
	}

}
