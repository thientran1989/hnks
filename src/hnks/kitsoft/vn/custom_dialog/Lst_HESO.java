package hnks.kitsoft.vn.custom_dialog;

import java.util.ArrayList;
import java.util.List;

import hnks.kitsoft.vn.R;
import hnks.kitsoft.vn.TAB_THONGTIN_HOSO;
import hnks.kitsoft.vn.object.Obj_MAU_HESO;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

public class Lst_HESO extends BaseAdapter{
	

	private TAB_THONGTIN_HOSO activity;
	List<Obj_MAU_HESO> ARR_all;
	List<Obj_MAU_HESO> ARR_list;
	String KEY="";

	public Lst_HESO(List<Obj_MAU_HESO> ARR_all, TAB_THONGTIN_HOSO ctx) {
		super();
		
		this.activity = ctx;
		this.ARR_all=ARR_all;
		this.ARR_list=new ArrayList<Obj_MAU_HESO>(this.ARR_all);
	}

	public int getCount() {
		return ARR_list.size();
	}

	public Obj_MAU_HESO getItem(int pos) {
		return ARR_list.get(pos);
	}

	public long getItemId(int pos) {
		return 0;
	}

	public static class ViewHolder {
		public TextView tv_PT_TT, tv_PT_C, tv_PT_TL, tv_PT_K, tv_PT_VAT, tv_PT_NC, 
		tv_PT_C1, tv_PT_NC1,tv_TEN_HESO;
		public Button btn_CHON_HESO;
	}

	public View getView(final int pos, View ConvertView, ViewGroup parent) {
		ViewHolder View;
		LayoutInflater inflater = ((Activity) activity).getLayoutInflater();
		if (ConvertView == null) {
			View = new ViewHolder();
			ConvertView = inflater.inflate(R.layout.row_heso, null);
			View.tv_PT_TT =(TextView)ConvertView
					.findViewById(R.id.tv_PT_TT_row_heso);
			View.tv_PT_C =(TextView)ConvertView
					.findViewById(R.id.tv_PT_C_row_heso);
			View.tv_PT_TL =(TextView)ConvertView
					.findViewById(R.id.tv_PT_TL_row_heso);
			View.tv_PT_K =(TextView)ConvertView
					.findViewById(R.id.tv_PT_K_row_heso);
			View.tv_PT_VAT =(TextView)ConvertView
					.findViewById(R.id.tv_PT_VAT_row_heso);
			View.tv_PT_NC=(TextView)ConvertView
					.findViewById(R.id.tv_PT_NC_row_heso);
			View.tv_PT_C1 =(TextView)ConvertView
					.findViewById(R.id.tv_PT_C1_row_heso);
			View.tv_PT_NC1 =(TextView)ConvertView
					.findViewById(R.id.tv_PT_NC1_row_heso);
			View.tv_TEN_HESO =(TextView)ConvertView
					.findViewById(R.id.tv_TEN_HESO_row_heso);
			View.btn_CHON_HESO = (Button) ConvertView
					.findViewById(R.id.btn_CHON_row_heso);
			ConvertView.setTag(View);
		} else {
			View = (ViewHolder) ConvertView.getTag();
		}
		// khai bao hoa don
		final Obj_MAU_HESO HS = ARR_list.get(pos);
		View.tv_PT_TT.setText("TT : "+String.valueOf(HS.PT_TT));
		View.tv_PT_C.setText("C : "+String.valueOf(HS.PT_C));
		View.tv_PT_TL.setText("TL : "+String.valueOf(HS.PT_TL));
		View.tv_PT_K.setText("K : "+String.valueOf(HS.PT_K));
		View.tv_PT_VAT.setText("VAT : "+String.valueOf(HS.PT_VAT));
		View.tv_PT_NC.setText("NC : "+String.valueOf(HS.PT_NC)+" %");
		View.tv_PT_C1.setText("C1 : "+String.valueOf(HS.PT_C1));
		View.tv_PT_NC1.setText("NC1 : "+String.valueOf(HS.PT_NC1)+" %");
		View.tv_TEN_HESO.setText(""+String.valueOf(HS.TEN_HESO));
	        View.btn_CHON_HESO.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					activity.CAP_NHAT_HESO(HS);
					
				}
			});
		return ConvertView;
	}
	


}
