package hnks.kitsoft.vn.list;

import hnks.kitsoft.vn.GHICHU_Activity;
import hnks.kitsoft.vn.R;
import hnks.kitsoft.vn.object.Obj_GHICHU;

import java.util.List;

import android.annotation.SuppressLint;
//import android.drm.DrmStore.RightsStatus;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

public class Lst_GHICHU_BY_KH extends BaseAdapter{

	private GHICHU_Activity activity;
	List<Obj_GHICHU> list_ghichu;

	public Lst_GHICHU_BY_KH(GHICHU_Activity ctx,List<Obj_GHICHU> list_ghichu) {
		super();
		this.activity = ctx;
		this.list_ghichu = list_ghichu;
	}

	public int getCount() {
		return list_ghichu.size();
	}

	public Obj_GHICHU getItem(int pos) {
		return list_ghichu.get(pos);
	}

	public long getItemId(int pos) {
		return getItem(pos).STT;
	}

	public static class ViewHolder {
		public TextView tv_GHICHU;
		public TextView tv_THOI_GIAN;
		public TextView tv_DA_CHUYEN;
	}

	@SuppressLint("RtlHardcoded") @SuppressWarnings("deprecation")
	public View getView(int pos, View ConvertView, ViewGroup parent) {
		ViewHolder View;
		LayoutInflater inflater = activity.getLayoutInflater();
		
		if (ConvertView == null) {
			View = new ViewHolder();
			ConvertView = inflater.inflate(R.layout.row_ghichu, null);
			View.tv_GHICHU = (TextView) ConvertView
					.findViewById(R.id.tv_GHICHU_row_ghichu);
			View.tv_DA_CHUYEN = (TextView) ConvertView
					.findViewById(R.id.tv_DA_CHUYEN_row_ghichu);
			ConvertView.setTag(View);
		} else {
			View = (ViewHolder) ConvertView.getTag();
		}
		// khai bao hoa don
		final Obj_GHICHU HD = getItem(pos);
		View.tv_GHICHU.setText(HD.GHI_CHU);	
		LayoutParams lp = (LayoutParams) View.tv_GHICHU.getLayoutParams();
		View.tv_GHICHU.setBackgroundDrawable(null);
		lp.gravity = Gravity.LEFT;
		
		LayoutParams lp1 = (LayoutParams) View.tv_DA_CHUYEN.getLayoutParams();
//		View.tv_DA_CHUYEN.setBackgroundDrawable(null);
		lp1.gravity = Gravity.LEFT;
		
		if (HD.THUOC_TINH==0){
//			View.tv_THUOC_TINH.setText("ChÆ°a gá»­i");
			View.tv_GHICHU.setBackgroundResource(R.drawable.speech_bubble_green);
			lp.gravity = Gravity.RIGHT;
			lp1.gravity = Gravity.RIGHT;
		}
		else {
//			View.tv_THUOC_TINH.setText("Ä�Ã£ gá»­i");
			View.tv_GHICHU.setBackgroundResource(R.drawable.speech_bubble_orange);
			lp.gravity = Gravity.LEFT;
			lp1.gravity = Gravity.LEFT;
		}
		View.tv_GHICHU.setLayoutParams(lp);
		View.tv_DA_CHUYEN.setLayoutParams(lp1);
		if (HD.DA_CHUYEN==0){
			View.tv_DA_CHUYEN.setText("Chưa gửi : "+HD.THOI_GIAN);
		}
		else {
			View.tv_DA_CHUYEN.setText("Đã gửi : "+HD.THOI_GIAN);
		}
		
		return ConvertView;
	}

}
