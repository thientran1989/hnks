package hnks.kitsoft.vn.list;

import java.util.List;

import com.thtsoftlib.function.Thtcovert;

import hnks.kitsoft.utils.Utils;
import hnks.kitsoft.vn.R;
import hnks.kitsoft.vn.TAB_VATTU_CHIPHI;
import hnks.kitsoft.vn.object.Obj_HSO_VATTU_CTIET;
import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Lst_HSO_VATTU_DACHON extends BaseAdapter {

	private TAB_VATTU_CHIPHI activity;
	List<Obj_HSO_VATTU_CTIET> list_vattu_chon;

	public Lst_HSO_VATTU_DACHON(List<Obj_HSO_VATTU_CTIET> list_vattu_chon,
			TAB_VATTU_CHIPHI ctx) {
		super();
		this.list_vattu_chon = list_vattu_chon;
		this.activity = ctx;
	}

	public int getCount() {
		return list_vattu_chon.size();
	}
	public int get_soluong_loai(String loai) {
		int soluong=0;
		for (Obj_HSO_VATTU_CTIET VTCT : list_vattu_chon) {
			if (VTCT.getMA_LOAI_CPHI().equals(loai)){
				soluong = soluong+1;
			}
		}
		return soluong;
	}

	public Obj_HSO_VATTU_CTIET getItem(int pos) {
		return list_vattu_chon.get(pos);
	}

	public long getItemId(int pos) {
		return 0;
	}

	public static class ViewHolder {
		public TextView TenVatTu;
		public TextView GiaVatTu;
		public TextView SoLuongVatTu;
		public TextView SoTien;
		public TextView tv_THOI_GIAN;
		public Button btn_SUA;
		public ImageView imb_XOA;
		public TextView tv_VUOT_MUC;
	}

	@SuppressLint("InflateParams")
	public View getView(int pos, View ConvertView, ViewGroup parent) {
		ViewHolder View;
		LayoutInflater inflater = activity.getLayoutInflater();
		if (ConvertView == null) {
			View = new ViewHolder();
			ConvertView = inflater.inflate(R.layout.row_hso_vattu_dachon, null);
			View.TenVatTu = (TextView) ConvertView
					.findViewById(R.id.tvTenVT_row_dachon);
			View.GiaVatTu = (TextView) ConvertView
					.findViewById(R.id.tvGiaVT_row_dachon);
			View.SoLuongVatTu = (TextView) ConvertView
					.findViewById(R.id.tvSo_Luong_row_dachon);
			View.SoTien = (TextView) ConvertView
					.findViewById(R.id.tvSo_Tien_row_dachon);
			View.tv_THOI_GIAN = (TextView) ConvertView
					.findViewById(R.id.tv_THOI_GIAN_row_dachon);
			View.btn_SUA = (Button) ConvertView
					.findViewById(R.id.btn_SUA_SOLUONG_row_dachon);
			View.imb_XOA = (ImageView) ConvertView
					.findViewById(R.id.iMB_XOA_row_dachon);
			View.tv_VUOT_MUC = (TextView) ConvertView
					.findViewById(R.id.tv_VUOT_MUC_row_dachon);
			ConvertView.setTag(View);
		} else {
			View = (ViewHolder) ConvertView.getTag();
		}
		// khai bao hoa don
		final Obj_HSO_VATTU_CTIET HD = getItem(pos);

		View.TenVatTu.setText(HD.TEN_VTU);
		View.GiaVatTu.setText(String.format("%,d", HD.DON_GIA) + " X ");
		View.SoLuongVatTu.setText(String.valueOf(HD.SO_LUONG) + " = ");
		View.SoTien
				.setText(String.valueOf(String.format(
						"%,d",
						((int) (Double.parseDouble(String.valueOf(HD.DON_GIA)) * HD.SO_LUONG)))));
		View.tv_THOI_GIAN.setText(HD.getTHOI_GIAN());

		View.imb_XOA.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				activity.xoa_vat_tu(HD);
			}
		});
		View.btn_SUA.setText(Thtcovert.double_to_String(HD.getSO_LUONG()));
		View.btn_SUA.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				activity.sua_vat_tu(HD.MA_VTU);
			}
		});
		if (HD.getMA_LOAI_CPHI().equals(Utils.VAT_LIEU)){
			View.btn_SUA.setBackgroundResource(R.drawable.roud_button_ok);
		}else if (HD.getMA_LOAI_CPHI().equals(Utils.NHAN_CONG)){
			View.btn_SUA.setBackgroundResource(R.drawable.roud_button_xoa);
		}else if (HD.getMA_LOAI_CPHI().equals(Utils.VAN_CHUYEN)){
			View.btn_SUA.setBackgroundResource(R.drawable.rounded_cyan);
		}else if (HD.getMA_LOAI_CPHI().equals(Utils.MAY_THI_CONG)){
			View.btn_SUA.setBackgroundResource(R.drawable.rounded_cam);
		}

		return ConvertView;
	}

}
