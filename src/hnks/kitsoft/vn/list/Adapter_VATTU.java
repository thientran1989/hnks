package hnks.kitsoft.vn.list;

import java.util.ArrayList;
import java.util.List;

import hnks.kitsoft.utils.Utils;
import hnks.kitsoft.vn.DanhSach_VatTu;
import hnks.kitsoft.vn.R;
import hnks.kitsoft.vn.object.Obj_D_VATTU;
import hnks.kitsoft.vn.object.Obj_HSO_VATTU_CTIET;
import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

@SuppressLint("DefaultLocale") public class Adapter_VATTU extends BaseAdapter{

    private DanhSach_VatTu activity;
	List<Obj_D_VATTU> list_all_vattu;
	List<Obj_D_VATTU> list_vattu_nhom;
	  
	public Adapter_VATTU(DanhSach_VatTu ctx,List<Obj_D_VATTU> list_all_vattu) {
		super();
		this.activity = ctx;
		this.list_all_vattu =list_all_vattu;
		list_vattu_nhom= new ArrayList<Obj_D_VATTU>(this.list_all_vattu);
	}
	public int getCount() {
		return list_vattu_nhom.size();
	}
	public Obj_D_VATTU getItem(int pos) {
		return list_vattu_nhom.get(pos);
	}
	public long getItemId(int pos) {
		return 0;
	}
	public String get_ma_vattu(int pos) {
		return getItem(pos).getMA_VTU();
	}
	public void get_vattu_nhom(int id_nhom){
		list_vattu_nhom.clear();
		if (id_nhom>0){
			for (Obj_D_VATTU DVT : list_all_vattu) {
				if (DVT.getNHOM()==id_nhom){
					list_vattu_nhom.add(DVT);
				}
			}
		}else{
			list_vattu_nhom= new ArrayList<Obj_D_VATTU>(this.list_all_vattu);
		}
		notifyDataSetChanged();
		
	}
	public void refesh_list(int pos){
		if (pos>-1){
			list_vattu_nhom.remove(pos);
		}
		notifyDataSetChanged();
	}
	public void refesh_phuchoi(Obj_HSO_VATTU_CTIET VTCT,List<Obj_D_VATTU> list_tam){
		int pos=-1;
		if (list_tam!=null){
			if (list_tam.size()>0){
				for (int i = 0; i < list_tam.size(); i++) {
					Obj_D_VATTU DVT = list_tam.get(i);
					if (DVT.getMA_VTU().equals(VTCT.getMA_VTU())){
						list_vattu_nhom.add(0,DVT);
						pos=i;
						break;
					}
				}
				if (pos>-1){
					list_tam.remove(pos);
				}
			}
		}
		 notifyDataSetChanged();
	}

	public void get_search_vattu(String key){
		list_vattu_nhom.clear();
		if (key.length()>0){
			for (Obj_D_VATTU DVT : list_all_vattu) {
				if (AccentRemover.LoaiBoDauTV(DVT.getTEN_VTU()).toLowerCase().contains(AccentRemover.LoaiBoDauTV(key).toLowerCase())
						|| AccentRemover.LoaiBoDauTV(DVT.getMA_VTU()).toLowerCase().contains(AccentRemover.LoaiBoDauTV(key).toLowerCase())
						|| AccentRemover.LoaiBoDauTV(DVT.getMA_LOAI_CPHI()).toLowerCase().contains(AccentRemover.LoaiBoDauTV(key).toLowerCase())){
					list_vattu_nhom.add(DVT);
				}
			}
		}else{
			list_vattu_nhom= new ArrayList<Obj_D_VATTU>(this.list_all_vattu);
		}
		
	}
	public static class ViewHolder {
		public TextView TenVatTu;
		public TextView GiaVatTu;
		public TextView tv_MA_VTU;
		public TextView btn_STT;
		public TextView tv_XOA;
	}
	@SuppressLint("InflateParams") public View getView(final int pos, View ConvertView, ViewGroup parent) {
		ViewHolder View;
		LayoutInflater inflater = activity.getLayoutInflater();
		if (ConvertView==null){
			View=new ViewHolder();
			ConvertView=inflater.inflate(R.layout.row_vattu_them,null);
			View.TenVatTu=(TextView) ConvertView.findViewById(R.id.tvTenVT_row_them_vattu);
			View.GiaVatTu=(TextView) ConvertView.findViewById(R.id.tvGiaVT_row_them_vattu);
			View.tv_MA_VTU=(TextView) ConvertView.findViewById(R.id.tv_ma_vtu_row_them_vattu);
			View.btn_STT=(TextView) ConvertView.findViewById(R.id.btn_SUA_row_them_vattu);
			View.tv_XOA = (TextView) ConvertView.findViewById(R.id.btn_XOA_row_them_vattu);
			ConvertView.setTag(View);
		}
		else {
			View=(ViewHolder)ConvertView.getTag();
		}
		final Obj_D_VATTU HD= list_vattu_nhom.get(pos);
		
		View.TenVatTu.setText(HD.TEN_VTU);
		View.tv_MA_VTU.setText("Mã vật tư : "+HD.getMA_VTU());
		View.GiaVatTu.setText("Giá vật tư : "+String.format("%,d",HD.DON_GIA));
		View.btn_STT.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
			activity.sua_vattu(HD);
			}
		});
		View.tv_XOA.setOnClickListener(new OnClickListener() {
					public void onClick(View v) {
					activity.xoa_vattu(HD,pos);
					}
				});
		if (HD.getMA_LOAI_CPHI().equals(Utils.VAT_LIEU)){
			View.btn_STT.setText(Utils.VAT_LIEU);
			View.btn_STT.setBackgroundResource(R.drawable.roud_button_ok);
		}else if (HD.getMA_LOAI_CPHI().equals(Utils.NHAN_CONG)){
			View.btn_STT.setText(Utils.NHAN_CONG);
			View.btn_STT.setBackgroundResource(R.drawable.roud_button_xoa);
		}else if (HD.getMA_LOAI_CPHI().equals(Utils.VAN_CHUYEN)){
			View.btn_STT.setText(Utils.VAN_CHUYEN);
			View.btn_STT.setBackgroundResource(R.drawable.rounded_cyan);
		}else if (HD.getMA_LOAI_CPHI().equals(Utils.MAY_THI_CONG)){
			View.btn_STT.setText(Utils.MAY_THI_CONG);
			View.btn_STT.setBackgroundResource(R.drawable.rounded_cam);
		}
		return ConvertView;
	}
	



}
