package hnks.kitsoft.vn.list;

import java.util.List;
import hnks.kitsoft.utils.Variables;
import hnks.kitsoft.vn.R;
import hnks.kitsoft.vn.database.DBAdapter;
import hnks.kitsoft.vn.object.Obj_CANVAS;
import hnks.kitsoft.vn.vesodo.Ve_So_Do_Activity;
import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class Lst_HSO_CANVAS extends BaseAdapter {

	private Ve_So_Do_Activity activity;
	List<Obj_CANVAS> ARR;
	DBAdapter mdb;

	public Lst_HSO_CANVAS(List<Obj_CANVAS> ARR, Ve_So_Do_Activity ctx,
			String MA_YCAU_KNAI) {
		super();
		this.ARR = ARR;
		this.activity = ctx;
	}

	public int getCount() {
		return ARR.size();
	}

	public Obj_CANVAS getItem(int pos) {
		return ARR.get(pos);
	}

	public long getItemId(int pos) {
		return ARR.get(pos).STT;
	}

	public static class ViewHolder {
		public TextView tv_TEN;
		public TextView btn_XOA_PAINT_CHON;
		public TextView btn_SUA_PAINT_CHON;
		public ImageView imb_LOAI_CANVAS;
	}

	@SuppressLint("InflateParams") public View getView(final int pos, View ConvertView, ViewGroup parent) {
		final ViewHolder View;
		LayoutInflater inflater = activity.getLayoutInflater();

		if (ConvertView == null) {
			View = new ViewHolder();
			ConvertView = inflater.inflate(R.layout.row_canvas, null);
			View.tv_TEN = (TextView) ConvertView
					.findViewById(R.id.tv_TEN_row_canvas);
			View.imb_LOAI_CANVAS = (ImageView) ConvertView
					.findViewById(R.id.imb_LOAI_CANVAS_row_canvas);
			
			View.btn_XOA_PAINT_CHON = (TextView) ConvertView
					.findViewById(R.id.btn_XOA_row_canvas);
			View.btn_SUA_PAINT_CHON = (TextView) ConvertView
					.findViewById(R.id.btn_SUA_row_canvas);
			ConvertView.setTag(View);
		} else {
			View = (ViewHolder) ConvertView.getTag();
		}
		// khai bao hoa don
		final Obj_CANVAS CV = ARR.get(pos);
		if (CV.LENH == Variables.VE_DUONG_THANG) {
			View.imb_LOAI_CANVAS.setImageResource(R.drawable.icon_line);
		} else if (CV.LENH == Variables.VE_2LINE) {
			View.imb_LOAI_CANVAS.setImageResource(R.drawable.twoline);
		}
		else if (CV.LENH == Variables.VE_TEXT) {
			View.imb_LOAI_CANVAS.setImageResource(R.drawable.text);
		} else if (CV.LENH == Variables.VE_HINH_CHU_NHAT) {
			View.imb_LOAI_CANVAS.setImageResource(R.drawable.icon_kdt);
		}
		// tram
		else if (CV.LENH == Variables.VE_TRAM_1PHA_HHUU) {
			View.imb_LOAI_CANVAS.setImageResource(R.drawable.cv_tru_btlt_105_hhuu);
		}
		else if (CV.LENH == Variables.VE_TRAM_1PHA_SLAP) {
			View.imb_LOAI_CANVAS.setImageResource(R.drawable.cv_tru_btlt_105_slap);
		}
		else if (CV.LENH == Variables.VE_TRAM_3PHA_HHUU) {
			View.imb_LOAI_CANVAS.setImageResource(R.drawable.cv_tram_3pha_hhuu_48);
		}
		else if (CV.LENH == Variables.VE_TRAM_3PHA_SLAP) {
			View.imb_LOAI_CANVAS.setImageResource(R.drawable.cv_tram_3pha_slap_48);
		}
		else if (CV.LENH == Variables.VE_TRAM_3PHA_NEN_HHUU) {
			View.imb_LOAI_CANVAS.setImageResource(R.drawable.cv_tram_3pha_nen_hhuu_48);
		}
		else if (CV.LENH == Variables.VE_TRAM_3PHA_NEN_SLAP) {
			View.imb_LOAI_CANVAS.setImageResource(R.drawable.cv_tram_3pha_nen_slap_48);
		}
		else if (CV.LENH == Variables.VE_TRAM_3PHA_NHA_HHUU) {
			View.imb_LOAI_CANVAS.setImageResource(R.drawable.cv_tram_3pha_nha_hhuu_48);
		}
		else if (CV.LENH == Variables.VE_TRAM_3PHA_NHA_SLAP) {
			View.imb_LOAI_CANVAS.setImageResource(R.drawable.cv_tram_3pha_nha_slap_48);
		}
		// thap sat
		else if (CV.LENH == Variables.VE_THAPSAT_10_HHUU) {
			View.imb_LOAI_CANVAS.setImageResource(R.drawable.cv_thapsat_10_hhuu);
		}
		else if (CV.LENH == Variables.VE_THAPSAT_10_SLAP) {
			View.imb_LOAI_CANVAS.setImageResource(R.drawable.cv_thapsat_10_slap);
		}
		else if (CV.LENH == Variables.VE_THAPSAT_12_HHUU) {
			View.imb_LOAI_CANVAS.setImageResource(R.drawable.cv_thapsat_12m_hhuu);
		}
		else if (CV.LENH == Variables.VE_THAPSAT_12_SLAP) {
			View.imb_LOAI_CANVAS.setImageResource(R.drawable.cv_thapsat_12m_slap);
		}
		// cong to treo
		else if (CV.LENH == Variables.VE_CONGTO_TREO_NHA) {
			View.imb_LOAI_CANVAS.setImageResource(R.drawable.cv_congto_treo_nha_32);
		}
		else if (CV.LENH == Variables.VE_CONGTO_TREO_TRU_BTLT_105_HHUU) {
			View.imb_LOAI_CANVAS.setImageResource(R.drawable.cv_congto_treo_tru_btlt_105_hhuu);
		}
		else if (CV.LENH == Variables.VE_CONGTO_TREO_TRU_BTLT_105_SLAP) {
			View.imb_LOAI_CANVAS.setImageResource(R.drawable.cv_congto_treo_tru_btlt_105_slap);
		}
		else if (CV.LENH == Variables.VE_CONGTO_TREO_TRU_BTLT_12_HHUU) {
			View.imb_LOAI_CANVAS.setImageResource(R.drawable.cv_congto_treo_tru_bttl_12_32);
		}
		else if (CV.LENH == Variables.VE_CONGTO_TREO_TRU_BTV_65_HHUU) {
			View.imb_LOAI_CANVAS.setImageResource(R.drawable.cv_congto_treo_tru_btv_65_hhuu);
		}
		else if (CV.LENH == Variables.VE_CONGTO_TREO_TRU_BTV_65_SLAP) {
			View.imb_LOAI_CANVAS.setImageResource(R.drawable.cv_congto_treo_tru_btv_65_slap);
		}
		// tru
		else if (CV.LENH == Variables.VE_TRU_BTLT_12_HHUU) {
			View.imb_LOAI_CANVAS.setImageResource(R.drawable.cv_tru_btlt_12_hhuu);
		}
		else if (CV.LENH == Variables.VE_TRU_BTLT_12_SLAP) {
			View.imb_LOAI_CANVAS.setImageResource(R.drawable.cv_tru_btlt_12_slap);
		}
		else if (CV.LENH == Variables.VE_TRU_BTLT_14_HHUU) {
			View.imb_LOAI_CANVAS.setImageResource(R.drawable.cv_tru_btlt_14_hhuu);
		}
		else if (CV.LENH == Variables.VE_TRU_BTLT_14_SLAP) {
			View.imb_LOAI_CANVAS.setImageResource(R.drawable.cv_tru_btlt_14_slap);
		}
		else if (CV.LENH == Variables.VE_TRU_BTLT_20_HHUU) {
			View.imb_LOAI_CANVAS.setImageResource(R.drawable.cv_tru_btlt_20_hhuu);
		}
		else if (CV.LENH == Variables.VE_TRU_BTLT_20_SLAP) {
			View.imb_LOAI_CANVAS.setImageResource(R.drawable.cv_tru_btlt_20_slap);
		}
		else if (CV.LENH == Variables.VE_TRU_BTLT_75_HHUU) {
			View.imb_LOAI_CANVAS.setImageResource(R.drawable.cv_tru_btlt_75_hhuu);
		}
		else if (CV.LENH == Variables.VE_TRU_BTLT_75_SLAP) {
			View.imb_LOAI_CANVAS.setImageResource(R.drawable.cv_tru_btlt_75_slap);
		}
		else if (CV.LENH == Variables.VE_TRU_BTLT_85_HHUU) {
			View.imb_LOAI_CANVAS.setImageResource(R.drawable.cv_tru_btlt_85_hhuu);
		}
		else if (CV.LENH == Variables.VE_TRU_BTLT_85_SLAP) {
			View.imb_LOAI_CANVAS.setImageResource(R.drawable.cv_tru_btlt_85_slap);
		}
		else if (CV.LENH == Variables.VE_TRU_BTLT_105_HHUU) {
			View.imb_LOAI_CANVAS.setImageResource(R.drawable.cv_tru_btlt_105_hhuu);
		} 
		else if (CV.LENH == Variables.VE_TRU_BTLT_105_HHUU) {
			View.imb_LOAI_CANVAS.setImageResource(R.drawable.cv_tru_btlt_105_hhuu);
		}
		else if (CV.LENH == Variables.VE_TRU_BTLT_105_SLAP) {
			View.imb_LOAI_CANVAS.setImageResource(R.drawable.cv_tru_btlt_105_slap);
		}
		else if (CV.LENH == Variables.VE_TRU_BTV_11_HHUU) {
			View.imb_LOAI_CANVAS.setImageResource(R.drawable.cv_tru_btv_11_hhuu);
		}
		else if (CV.LENH == Variables.VE_TRU_BTV_11_SLAP) {
			View.imb_LOAI_CANVAS.setImageResource(R.drawable.cv_tru_btv_11_slap);
		}
		else if (CV.LENH == Variables.VE_TRU_BTV_65_HHUU) {
			View.imb_LOAI_CANVAS.setImageResource(R.drawable.cv_tru_btv_65_hhuu);
		}
		else if (CV.LENH == Variables.VE_TRU_BTV_65_SLAP) {
			View.imb_LOAI_CANVAS.setImageResource(R.drawable.cv_tru_btv_65_slap);
		}
		else if (CV.LENH == Variables.VE_TRU_BTV_75_HHUU) {
			View.imb_LOAI_CANVAS.setImageResource(R.drawable.cv_tru_btv_75_hhuu);
		}
		else if (CV.LENH == Variables.VE_TRU_BTV_75_SLAP) {
			View.imb_LOAI_CANVAS.setImageResource(R.drawable.cv_tru_btv_75_slap);
		}
		else if (CV.LENH == Variables.VE_TRU_BTV_85_HHUU) {
			View.imb_LOAI_CANVAS.setImageResource(R.drawable.cv_tru_btv_85_hhuu);
		}
		else if (CV.LENH == Variables.VE_TRU_BTV_85_SLAP) {
			View.imb_LOAI_CANVAS.setImageResource(R.drawable.cv_tru_btv_85_slap);
		}
		else if (CV.LENH == Variables.VE_TRU_GO_HHUU) {
			View.imb_LOAI_CANVAS.setImageResource(R.drawable.cv_tru_go_hhuu);
		}
		else if (CV.LENH == Variables.VE_TRU_GO_SLAP) {
			View.imb_LOAI_CANVAS.setImageResource(R.drawable.cv_tru_go_slap);
		}
		else if (CV.LENH == Variables.VE_TRU_NHOM_HHUU) {
			View.imb_LOAI_CANVAS.setImageResource(R.drawable.cv_tru_nhom_hhuu);
		}
		else if (CV.LENH == Variables.VE_TRU_NHOM_SLAP) {
			View.imb_LOAI_CANVAS.setImageResource(R.drawable.cv_tru_nhom_slap);
		}
		else if (CV.LENH == Variables.VE_NHA) {
			View.imb_LOAI_CANVAS.setImageResource(R.drawable.cv_nha_32);
		} else if (CV.LENH == Variables.VE_LINE_DUT_NET) {
			View.imb_LOAI_CANVAS.setImageResource(R.drawable.icon_dashed_line);
		} else if (CV.LENH == Variables.VE_SONG) {
		} else if (CV.LENH == Variables.VE_CAU) {
			View.imb_LOAI_CANVAS.setImageResource(R.drawable.icon_cau);
		} else if (CV.LENH == Variables.VE_HINH) {
		} else if (CV.LENH == Variables.VE_HINH_Z_NGANG) {
			View.imb_LOAI_CANVAS.setImageResource(R.drawable.icon_z_ngang);
		} else if (CV.LENH == Variables.VE_HINH_Z_NGANG_DUT) {
			View.imb_LOAI_CANVAS.setImageResource(R.drawable.icon_dashed_z);
		} else if (CV.LENH == Variables.VE_NEN) {
		}else if (CV.LENH == Variables.VE_HINH_CONGTO) {
			View.imb_LOAI_CANVAS.setImageResource(R.drawable.cv_cong_to_32);
		}else if (CV.LENH == Variables.VE_MUI_TEN) {
			View.imb_LOAI_CANVAS.setImageResource(R.drawable.icon_arrow_black_right);
		}else if (CV.LENH == Variables.VE_KDT) {
			View.imb_LOAI_CANVAS.setImageResource(R.drawable.icon_kdt);
		}
		View.tv_TEN.setText(CV.TEN);
		if (CV.CHON==1){
			View.tv_TEN.setTextColor(Color.BLUE);
		}else {
			View.tv_TEN.setTextColor(Color.BLACK);
		
		}
		View.btn_XOA_PAINT_CHON.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				activity.XOA_CANVAS(CV,pos);
			}
		});
		View.btn_SUA_PAINT_CHON.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				activity.show_dialog_sua_ten(CV);
			}
		});
		View.imb_LOAI_CANVAS.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
//				for (Obj_CANVAS OBJ : ARR) {
//					OBJ.CHON=0;
//				}
//				HD.CHON=1;
//				notifyDataSetChanged();
//				POS = pos;
//				STT=HD.STT;
//				activity.CHON_PAINT();
				activity.show_dialog_sua_ten(CV);
				
			}
		});

		return ConvertView;
	}


}
