package hnks.kitsoft.vn.list;

import hnks.kitsoft.utils.Utils;
import hnks.kitsoft.vn.Ac_xem_vattu;
import hnks.kitsoft.vn.R;
import hnks.kitsoft.vn.object.Obj_D_VATTU;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.thtsoftlib.function.Thtcovert;

public class Adapter_D_VATTU_BY_MAU extends BaseAdapter{

	private Ac_xem_vattu activity;
	List<Obj_D_VATTU> list_all_vattu = new ArrayList<Obj_D_VATTU>();
	List<Obj_D_VATTU> list_vattu = new ArrayList<Obj_D_VATTU>();

	public Adapter_D_VATTU_BY_MAU(List<Obj_D_VATTU> list_all_vattu, Ac_xem_vattu ctx) {
		super();
		this.activity = ctx;
		this.list_all_vattu = list_all_vattu;
		list_vattu = new ArrayList<Obj_D_VATTU>(this.list_all_vattu);
	}
	public void get_search_vattu(String key){
		list_vattu.clear();
		if (key.length()>0){
			
			for (Obj_D_VATTU DVT : list_all_vattu) {
				if (AccentRemover.LoaiBoDauTV(DVT.getTEN_VTU()).toLowerCase().contains(AccentRemover.LoaiBoDauTV(key).toLowerCase())
						|| AccentRemover.LoaiBoDauTV(DVT.getMA_VTU()).toLowerCase().contains(AccentRemover.LoaiBoDauTV(key).toLowerCase())
						|| AccentRemover.LoaiBoDauTV(DVT.getMA_LOAI_CPHI()).toLowerCase().contains(AccentRemover.LoaiBoDauTV(key).toLowerCase())){
					list_vattu.add(DVT);
				}
			}
		}else{
			list_vattu= new ArrayList<Obj_D_VATTU>(this.list_all_vattu);
		}
		
	}
	public int getCount() {
		return  list_vattu.size();
	}

	public Obj_D_VATTU getItem(int pos) {
		return list_vattu.get(pos);
	}

	public long getItemId(int pos) {
		return 0;
	}

	public static class ViewHolder {
		public TextView tv_maloai_cp;
		public TextView tv_MA_VATTU;
		public TextView tvTenVatTu;
		public TextView tvGia;

	}

	@SuppressLint({ "InflateParams", "ResourceAsColor" }) public View getView(int pos, View ConvertView, ViewGroup parent) {
		ViewHolder View;
		LayoutInflater inflater = activity.getLayoutInflater();
		if (ConvertView == null) {
			View = new ViewHolder();
			ConvertView = inflater.inflate(R.layout.row_xem_vattu, null);
			View.tv_maloai_cp = (TextView) ConvertView
					.findViewById(R.id.tv_loai_cp_row_xem_vattu);
			View.tv_MA_VATTU = (TextView) ConvertView
					.findViewById(R.id.tv_maVT_row_xem_vattu);
			View.tvTenVatTu = (TextView) ConvertView
					.findViewById(R.id.tvTenVT_row_xem_vattu);
			View.tvGia = (TextView) ConvertView
					.findViewById(R.id.tvGiaVT_row_xem_vattu);
			ConvertView.setTag(View);
		} else {
			View = (ViewHolder) ConvertView.getTag();
		}
		// khai bao hoa don
		final Obj_D_VATTU VT = getItem(pos);
		View.tv_MA_VATTU.setText(VT.getMA_VTU());
		View.tvTenVatTu.setText(VT.getTEN_VTU());
		View.tvGia.setText(Thtcovert.int_format_tien(VT.getDON_GIA()));
		if (VT.getMA_LOAI_CPHI().equals(Utils.VAT_LIEU)){
			View.tv_maloai_cp.setText("VL");
			View.tv_maloai_cp.setBackgroundResource(R.drawable.roud_button_ok);
		}else if (VT.getMA_LOAI_CPHI().equals(Utils.NHAN_CONG)){
			View.tv_maloai_cp.setText("NC");
			View.tv_maloai_cp.setBackgroundResource(R.drawable.roud_button_xoa);
		}else if (VT.getMA_LOAI_CPHI().equals(Utils.VAN_CHUYEN)){
			View.tv_maloai_cp.setText("VC");
			View.tv_maloai_cp.setBackgroundResource(R.drawable.rounded_cyan);
		}else if (VT.getMA_LOAI_CPHI().equals(Utils.MAY_THI_CONG)){
			View.tv_maloai_cp.setText("M");
			View.tv_maloai_cp.setBackgroundResource(R.drawable.rounded_cam);
		}
		

		return ConvertView;

	}

}
