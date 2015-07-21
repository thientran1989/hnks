package hnks.kitsoft.vn.list;

import java.util.ArrayList;
import java.util.List;

import hnks.kitsoft.utils.Utils;
import hnks.kitsoft.utils.Variables;
import hnks.kitsoft.vn.DSHS_CHIETTINH_Activity;
import hnks.kitsoft.vn.R;
import hnks.kitsoft.vn.database.DBAdapter;
import hnks.kitsoft.vn.object.Obj_HSO_CHIETTINH;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

public class Lst_HSO_CHIETTINH extends BaseAdapter {
	private DSHS_CHIETTINH_Activity activity;
	public List<Obj_HSO_CHIETTINH> list_hoso;
	public List<Obj_HSO_CHIETTINH> list_all_hoso;
	public static String MA_DVIQLY_ChonLoadLai="";
	public static String MA_YCAU_KNAI_ChonLoadLai="";
	DBAdapter mdb;

	public Lst_HSO_CHIETTINH(DBAdapter mdb,List<Obj_HSO_CHIETTINH> list_all_hoso, DSHS_CHIETTINH_Activity ctx) {
		super();
		this.activity = ctx;
		this.mdb = mdb;
		this.list_all_hoso=list_all_hoso;
		list_hoso = new ArrayList<Obj_HSO_CHIETTINH>(this.list_all_hoso);
	}
	@SuppressLint("DefaultLocale") public void get_SEARCH(String KEY) {
		list_hoso.clear();
		if (KEY.length() > 0) {
			for (Obj_HSO_CHIETTINH obj_KH : list_all_hoso) {
				KEY = AccentRemover.LoaiBoDauTV(KEY).toLowerCase();
				if (AccentRemover.LoaiBoDauTV((obj_KH.MA_YCAU_KNAI)).toLowerCase().contains(KEY)
						||AccentRemover.LoaiBoDauTV((obj_KH.TEN_KHANG)).toLowerCase().contains(KEY)
						||AccentRemover.LoaiBoDauTV((obj_KH.SO_NHA)).toLowerCase().contains(KEY)
						||AccentRemover.LoaiBoDauTV((obj_KH.DUONG_PHO)).toLowerCase().contains(KEY))
					list_hoso.add(obj_KH);
			}
		}else{
			list_hoso = new ArrayList<Obj_HSO_CHIETTINH>(this.list_all_hoso);
		}
		
	}
	public void get_hso_da_ks(int tt) {
		list_hoso.clear();
		if (tt==0){
			list_hoso = new ArrayList<Obj_HSO_CHIETTINH>(this.list_all_hoso);
		}else if (tt==1){
			if (list_all_hoso.size() > 0) {
				for (Obj_HSO_CHIETTINH obj_KH : list_all_hoso) {
					if (obj_KH.DA_XONG==Utils.TT_DA_XONG)
						list_hoso.add(obj_KH);
				}
			}
		}else if (tt==2){
			if (list_all_hoso.size() > 0) {
				for (Obj_HSO_CHIETTINH obj_KH : list_all_hoso) {
					if (obj_KH.DA_XONG==Utils.TT_CHUA_XONG &mdb.get_list_vattu_chitiet_khachhang(obj_KH.getMA_YCAU_KNAI()).size()==0)
						list_hoso.add(obj_KH);
				}
			}
		}else if (tt==3){
			if (list_all_hoso.size() > 0) {
				for (Obj_HSO_CHIETTINH obj_KH : list_all_hoso) {
					if (mdb.get_list_vattu_chitiet_khachhang(obj_KH.getMA_YCAU_KNAI()).size()>0
							& obj_KH.getDA_XONG()==Utils.TT_CHUA_XONG){
						list_hoso.add(obj_KH);
					}
						
				}
			}
		}
	}

	public int getCount() {
		int k=list_hoso.size();
		return k;
	}

	public Obj_HSO_CHIETTINH getItem(int pos) {
		return list_hoso.get(pos);
	}

	public static class ViewHolder {
		public TextView tv_MA_YCAU_KNAI;
		public TextView tv_TEN_YCAU_KNAI;
		public TextView tv_DIACHI_YCAU_KNAI;
		public Button btn_STT;
		public Button btnXoaHS;
		public TextView tv_stt;
		public TextView tv_tt_xong;
	}

	@SuppressLint({ "InflateParams" }) public View getView(final int pos, View ConvertView, ViewGroup parent) {
		ViewHolder View;
		LayoutInflater inflater = activity.getLayoutInflater();
		if (ConvertView == null) {
			View = new ViewHolder();
			ConvertView = inflater.inflate(R.layout.row_hso_chiettinh, null);
			View.tv_MA_YCAU_KNAI = (TextView) ConvertView
					.findViewById(R.id.tv_MA_YCAU_KNAI_row_hso_chiettinh);
			View.tv_TEN_YCAU_KNAI = (TextView) ConvertView
					.findViewById(R.id.tv_TEN_YCAU_KNAI_row_hso_chiettinh);
			View.tv_DIACHI_YCAU_KNAI = (TextView) ConvertView
					.findViewById(R.id.tv_DIACHI_YCAU_KNAI_row_hso_chiettinh);
			View.btn_STT = (Button) ConvertView
					.findViewById(R.id.btn_STT_row_hso_ctinh);
			View.btnXoaHS = (Button) ConvertView
					.findViewById(R.id.btnXoaHS_row_hso_ctinh);
			View.tv_stt = (TextView) ConvertView
					.findViewById(R.id.tv_STT_row_hso_ctinh);
			View.tv_tt_xong = (TextView) ConvertView
					.findViewById(R.id.tv_tt_xong_row_hsochiettinh);
			ConvertView.setTag(View);
		} else {
			View = (ViewHolder) ConvertView.getTag();
		}
		// khai bao hoa don
		final Obj_HSO_CHIETTINH KH = list_hoso.get(pos);
		View.tv_tt_xong.setTextColor(Color.BLACK);
		View.tv_stt.setText(String.valueOf(pos+1));
		View.tv_MA_YCAU_KNAI.setText(KH.MA_YCAU_KNAI);
		View.tv_TEN_YCAU_KNAI.setText(KH.TEN_KHANG);
		View.tv_DIACHI_YCAU_KNAI.setText(KH.SO_NHA+", "+KH.DUONG_PHO);
		View.btnXoaHS.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Variables.HSCT_CHON = KH;
				xoa_ho_so(KH);
			}
		});
		if (KH.DA_XONG==Utils.TT_DA_XONG){
			View.btn_STT.setText("X");
			View.btn_STT.setBackgroundResource(R.drawable.roud_button_green);
			View.tv_tt_xong.setText("  đã  \n xong ");
			View.tv_tt_xong.setTextColor(Color.RED);
		}else if (mdb.get_list_vattu_chitiet_khachhang(KH.getMA_YCAU_KNAI()).size()==0
				& KH.DA_XONG==Utils.TT_CHUA_XONG){
			View.btn_STT.setText("KS");
			View.btn_STT.setBackgroundResource(R.drawable.roud_button_ok);
			View.tv_tt_xong.setText(" chưa \n xong ");
		}else if (mdb.get_list_vattu_chitiet_khachhang(KH.getMA_YCAU_KNAI()).size()>0
				& KH.getDA_XONG()==Utils.TT_CHUA_XONG){
			View.btn_STT.setText("Đ");
			View.btn_STT.setBackgroundResource(R.drawable.rounded_cam);
			View.tv_tt_xong.setText(" đang \n ks");
		}
		
		View.btn_STT.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Variables.HSCT_CHON = getItem(pos);
				activity.to_intent_khachhang();
			}
		});
		
		// click item trong list de sua hoa don
//		ConvertView.setOnCreateContextMenuListener(new OnCreateContextMenuListener() {
//			@Override
//			public void onCreateContextMenu(ContextMenu menu,
//					android.view.View v, ContextMenuInfo menuInfo) {
//					menu.add((int)1, CONTEXT_MENU_UPDATE, ContextMenu.NONE, "Load lại dữ liệu của Hồ Sơ này");
//					MA_DVIQLY_ChonLoadLai=KH.MA_DVIQLY;
//					MA_YCAU_KNAI_ChonLoadLai=KH.MA_YCAU_KNAI;
//			}
//		});
		return ConvertView;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}
	public void xoa_ho_so(final Obj_HSO_CHIETTINH HSCT) {
		final Dialog dialog = new Dialog(activity, R.style.FullHeightDialog);
		dialog.setContentView(R.layout.dialog_yes_no);
		dialog.setCancelable(true);
		TextView message = (TextView) dialog
				.findViewById(R.id.tvmessagedialogtext);
		message.setText(activity.getString(R.string.xoa_ho_so_chon)+" "+HSCT.getTEN_KHANG()+" "+activity.getString(R.string.ko));
		Button yes = (Button) dialog.findViewById(R.id.bmessageDialogYes);
		yes.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				try {
					mdb.delete_HSO_CANVAS_BY_MA_YCAU_KNAI(HSCT.getMA_YCAU_KNAI());
					mdb.delete_HSO_HINH_BY_MA_YCAU_KNAI(HSCT.MA_YCAU_KNAI);
					mdb.delete_HSO_TOADO_BY_MA_YCAU_KNAI(HSCT.MA_YCAU_KNAI);
					mdb.delete_VTU_BY_MA_YCAU_KNAI(HSCT.MA_YCAU_KNAI, -1); //-1 la Xoa het theo MA_YCAU_KNAI
					mdb.delete_HSO_GHICHU_BY_MA_YCAU_KNAI(HSCT.MA_YCAU_KNAI);
					mdb.Xoa_HSO_CHIETTINH_BY_MA_YCAU_KNAI(HSCT.MA_YCAU_KNAI);
					list_hoso.remove(HSCT);
					notifyDataSetChanged();
				} catch (Exception e) {
					
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
