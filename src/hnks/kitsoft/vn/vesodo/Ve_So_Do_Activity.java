package hnks.kitsoft.vn.vesodo;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import hnks.kitsoft.utils.Custom_Toast;
import hnks.kitsoft.utils.Length_text;
import hnks.kitsoft.utils.Utils;
import hnks.kitsoft.utils.Variables;
import hnks.kitsoft.vn.Memory;
import hnks.kitsoft.vn.R;
import hnks.kitsoft.vn.custom_dialog.Dialog_listview_ghichu;
import hnks.kitsoft.vn.database.DBAdapter;
import hnks.kitsoft.vn.list.Adapter_nhap_lieu;
import hnks.kitsoft.vn.list.Lst_HSO_CANVAS;
import hnks.kitsoft.vn.list.Lst_MAU_CANVAS;
import hnks.kitsoft.vn.list.Lst_NEN;
import hnks.kitsoft.vn.object.Obj_CANVAS;
import hnks.kitsoft.vn.object.Obj_HSO_HINH;
import hnks.kitsoft.vn.object.Obj_MAU_CANVAS;
import hnks.kitsoft.vn.object.Obj_nhap_lieu;
import tht.library.crouton.Style;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.thtsoftlib.function.ThtShow;
import com.thtsoftlib.function.Tht_Screen;
import com.thtsoftlib.function.Thtcovert;

public class Ve_So_Do_Activity extends Activity implements OnTouchListener {

	static ListView lv_canvas;
	public static int LENH_VE;
	Button btn_UNDO;
	Button btn_REDO;
	public static Button btn_TRAI, btn_PHAI, btn_LEN, btn_XUONG;
	DrawingView drawView;
	// TinhToan tt;
	public static Lst_HSO_CANVAS mAdapter;
	public static String S_VE;
	static DBAdapter mdb;
	public static List<Obj_CANVAS> list_canvas = null;
	List<Obj_MAU_CANVAS> ARR_MCV;
	int w, h;
	List<Obj_HSO_HINH> ARR_H;
	Dialog_listview_ghichu dialog_GC;

	public static String MA_DVIQLY = "";
	public static String MA_YCAU_KNAI = "";
	public static List<Obj_CANVAS> ARR_XOA = new ArrayList<Obj_CANVAS>();
	public int LEN = 1, XUONG = 2, TRAI = 3, PHAI = 4;
	private Handler mHandler = new Handler();
	Dialog_MAU dialog_MAU;
	Dialog_NEN dialog_nen;
	String S_alert = "";
	int ve_danhdau = 0;
	View ln_tram, ln_tru, ln_khac;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Tht_Screen.hide_title(this);
		setContentView(R.layout.ve_so_do);

		w = (Tht_Screen.get_screen_width(this) / 10) * 9;
		h = (Tht_Screen.get_screen_heigth(this) / 10) * 9;

		mdb = new DBAdapter(this);
		mdb.open();
		dialog_nen = new Dialog_NEN(Ve_So_Do_Activity.this);
		// btn_UNDO = (Button) findViewById(R.id.btn_UNDO);
		// btn_REDO = (Button) findViewById(R.id.btn_REDO);
		btn_TRAI = (Button) findViewById(R.id.btn_TRAI);
		btn_PHAI = (Button) findViewById(R.id.btn_PHAI);
		btn_LEN = (Button) findViewById(R.id.btn_LEN);
		btn_XUONG = (Button) findViewById(R.id.btn_XUONG);
		ln_khac = (LinearLayout) findViewById(R.id.ln_khac_vesodo);
		ln_tram = (LinearLayout) findViewById(R.id.ln_tram_vesodo);
		ln_tru = (LinearLayout) findViewById(R.id.ln_tru_vesodo);
		try {
			ln_khac.setVisibility(View.VISIBLE);
			ln_tram.setVisibility(View.GONE);
			ln_tru.setVisibility(View.GONE);
		} catch (Exception e) {

		}

		try {
			MA_YCAU_KNAI = Variables.HSCT_CHON.getMA_YCAU_KNAI();
			MA_DVIQLY = Variables.DNV.MaDV;
			LENH_VE = Variables.MOVE;
			if (list_canvas != null) {
				list_canvas.clear();
			}
			list_canvas = DBAdapter.get_list_HSO_CANVAS(MA_YCAU_KNAI);
			drawView = (DrawingView) findViewById(R.id.drawing);
			lv_canvas = (ListView) findViewById(R.id.lv_CANVAS_HISTORY_ve_so_do);
			if (list_canvas != null) {
				mAdapter = new Lst_HSO_CANVAS(list_canvas,
						Ve_So_Do_Activity.this, MA_YCAU_KNAI);
				lv_canvas.setAdapter(mAdapter);
			} else {
				Custom_Toast.show_red_toast(Ve_So_Do_Activity.this,
						"Sơ đồ rỗng");
			}

		} catch (Exception e) {
			ThtShow.show_crouton_toast(this, e.toString(), Style.ALERT);
		}
		lv_canvas.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {

			}
		});

	}

	// luu hinh lai
	public void luu_hinh(View v) {
		final Dialog dialog = new Dialog(this, R.style.FullHeightDialog);
		dialog.setContentView(R.layout.dialog_yes_no);
		dialog.setCancelable(true);
		TextView message = (TextView) dialog
				.findViewById(R.id.tvmessagedialogtext);
		message.setText(getString(R.string.ban_co_muon_luu_so_do));
		Button yes = (Button) dialog.findViewById(R.id.bmessageDialogYes);
		yes.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				try {
					drawView.setDrawingCacheEnabled(true);
					Bitmap bitmap = drawView.getDrawingCache();
					ByteArrayOutputStream stream = new ByteArrayOutputStream();
					bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
					byte imageInByte[] = stream.toByteArray();
					Obj_HSO_HINH H = new Obj_HSO_HINH();
					H.setDA_CHUYEN(Utils.TT_CHUA_CHUYEN);
					H.setHINH(imageInByte);
					H.setMA_DVIQLY(MA_DVIQLY);
					H.setMA_LOAI_HINH("SD");
					H.setMA_YCAU_KNAI(Variables.HSCT_CHON.MA_YCAU_KNAI);
					H.setTEN_HINH("hình vẽ sơ đồ ");
					H.setTHOI_GIAN(Memory.getNow());
					mdb.Insert_HSO_HINH(H);
					drawView.destroyDrawingCache();
					Custom_Toast.show_blue_toast(Ve_So_Do_Activity.this,
							"đã lưu thành công !");

					finish();
				} catch (Exception e) {
					ThtShow.show_crouton_toast(Ve_So_Do_Activity.this,
							e.toString(), tht.library.crouton.Style.ALERT);
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

	// xoa het so do
	public void xoa_het_so_do(View v) {
		final Dialog dialog = new Dialog(this, R.style.FullHeightDialog);
		dialog.setContentView(R.layout.dialog_yes_no);
		dialog.setCancelable(true);
		TextView message = (TextView) dialog
				.findViewById(R.id.tvmessagedialogtext);
		message.setText(getString(R.string.xoa_tat_ca_so_do));
		Button yes = (Button) dialog.findViewById(R.id.bmessageDialogYes);
		yes.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				try {
					mdb.delete_HSO_CANVAS_BY_MA_YCAU_KNAI(MA_YCAU_KNAI);
					list_canvas = mdb.get_list_HSO_CANVAS(MA_YCAU_KNAI);
					mAdapter = new Lst_HSO_CANVAS(list_canvas,
							Ve_So_Do_Activity.this, MA_YCAU_KNAI);
					lv_canvas.setAdapter(mAdapter);
					drawView.VE_LAI();
					ARR_XOA.clear();
				} catch (Exception e) {
					ThtShow.show_crouton_toast(Ve_So_Do_Activity.this,
							e.toString(), tht.library.crouton.Style.ALERT);
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

	// ve khu dat trong
	public void ve_khu_dat_trong(View v) {
		LENH_VE = Variables.VE_KDT;
		drawView.setErase(false);
	}

	// TRAM
	public void ve_tram_1pha_hhuu(View v) {
		LENH_VE = Variables.VE_TRAM_1PHA_HHUU;
		drawView.setErase(false);
	}

	public void ve_tram_1pha_slap(View v) {
		LENH_VE = Variables.VE_TRAM_1PHA_SLAP;
		drawView.setErase(false);
	}

	// ve_tram_3pha_hhuu
	public void ve_tram_3pha_hhuu(View v) {
		LENH_VE = Variables.VE_TRAM_3PHA_HHUU;
		drawView.setErase(false);
	}

	// ve_tram_3pha_slap
	public void ve_tram_3pha_slap(View v) {
		LENH_VE = Variables.VE_TRAM_3PHA_SLAP;
		drawView.setErase(false);
	}

	// ve_tram_3pha_nen_hhuu
	public void ve_tram_3pha_nen_hhuu(View v) {
		LENH_VE = Variables.VE_TRAM_3PHA_NEN_HHUU;
		drawView.setErase(false);
	}

	// ve_tram_3pha_nen_slap
	public void ve_tram_3pha_nen_slap(View v) {
		LENH_VE = Variables.VE_TRAM_3PHA_NEN_SLAP;
		drawView.setErase(false);
	}

	// ve_tram_3pha_nha_hhuu
	public void ve_tram_3pha_nha_hhuu(View v) {
		LENH_VE = Variables.VE_TRAM_3PHA_NHA_HHUU;
		drawView.setErase(false);
	}

	// ve_tram_3pha_nha_slap
	public void ve_tram_3pha_nha_slap(View v) {
		LENH_VE = Variables.VE_TRAM_3PHA_NHA_SLAP;
		drawView.setErase(false);
	}

	// thap sat
	public void ve_thapsat_10_hhuu(View v) {
		LENH_VE = Variables.VE_THAPSAT_10_HHUU;
		drawView.setErase(false);
	}

	public void ve_thapsat_10_slap(View v) {
		LENH_VE = Variables.VE_THAPSAT_10_SLAP;
		drawView.setErase(false);
	}

	public void ve_thapsat_12_hhuu(View v) {
		LENH_VE = Variables.VE_THAPSAT_12_HHUU;
		drawView.setErase(false);
	}

	public void ve_thapsat_12_slap(View v) {
		LENH_VE = Variables.VE_THAPSAT_12_SLAP;
		drawView.setErase(false);
	}

	// TRU
	// ve tru
	public void ve_tru_btlt_12_hhuu(View v) {
		LENH_VE = Variables.VE_TRU_BTLT_12_HHUU;
		drawView.setErase(false);
	}

	public void ve_tru_btlt_12_slap(View v) {
		LENH_VE = Variables.VE_TRU_BTLT_12_SLAP;
		drawView.setErase(false);
	}

	public void ve_tru_btlt_14_hhuu(View v) {
		LENH_VE = Variables.VE_TRU_BTLT_14_HHUU;
		drawView.setErase(false);
	}

	public void ve_tru_btlt_14_slap(View v) {
		LENH_VE = Variables.VE_TRU_BTLT_14_SLAP;
		drawView.setErase(false);
	}

	public void ve_tru_btlt_20_hhuu(View v) {
		LENH_VE = Variables.VE_TRU_BTLT_20_HHUU;
		drawView.setErase(false);
	}

	public void ve_tru_btlt_20_slap(View v) {
		LENH_VE = Variables.VE_TRU_BTLT_20_SLAP;
		drawView.setErase(false);
	}

	public void ve_tru_btlt_75_hhuu(View v) {
		LENH_VE = Variables.VE_TRU_BTLT_75_HHUU;
		drawView.setErase(false);
	}

	public void ve_tru_btlt_75_slap(View v) {
		LENH_VE = Variables.VE_TRU_BTLT_75_SLAP;
		drawView.setErase(false);
	}

	public void ve_tru_btlt_85_hhuu(View v) {
		LENH_VE = Variables.VE_TRU_BTLT_85_HHUU;
		drawView.setErase(false);
	}

	public void ve_tru_btlt_85_slap(View v) {
		LENH_VE = Variables.VE_TRU_BTLT_85_SLAP;
		drawView.setErase(false);
	}

	// ve_tru_btlt_105_hhuu
	public void ve_tru_btlt_105_hhuu(View v) {
		LENH_VE = Variables.VE_TRU_BTLT_105_HHUU;
		drawView.setErase(false);
	}

	public void ve_tru_btlt_105_slap(View v) {
		LENH_VE = Variables.VE_TRU_BTLT_105_SLAP;
		drawView.setErase(false);
	}

	public void ve_tru_btv_11_hhuu(View v) {
		LENH_VE = Variables.VE_TRU_BTV_11_HHUU;
		drawView.setErase(false);
	}

	public void ve_tru_btv_11_slap(View v) {
		LENH_VE = Variables.VE_TRU_BTV_11_SLAP;
		drawView.setErase(false);
	}

	public void ve_tru_btv_65_hhuu(View v) {
		LENH_VE = Variables.VE_TRU_BTV_65_HHUU;
		drawView.setErase(false);
	}

	public void ve_tru_btv_65_slap(View v) {
		LENH_VE = Variables.VE_TRU_BTV_65_SLAP;
		drawView.setErase(false);
	}

	public void ve_tru_btv_75_hhuu(View v) {
		LENH_VE = Variables.VE_TRU_BTV_75_HHUU;
		drawView.setErase(false);
	}

	public void ve_tru_btv_75_slap(View v) {
		LENH_VE = Variables.VE_TRU_BTV_75_SLAP;
		drawView.setErase(false);
	}

	public void ve_tru_btv_85_hhuu(View v) {
		LENH_VE = Variables.VE_TRU_BTV_85_HHUU;
		drawView.setErase(false);
	}

	public void ve_tru_btv_85_slap(View v) {
		LENH_VE = Variables.VE_TRU_BTV_85_SLAP;
		drawView.setErase(false);
	}

	public void ve_tru_go_hhuu(View v) {
		LENH_VE = Variables.VE_TRU_GO_HHUU;
		drawView.setErase(false);
	}

	public void ve_tru_go_slap(View v) {
		LENH_VE = Variables.VE_TRU_GO_SLAP;
		drawView.setErase(false);
	}

	public void ve_tru_nhom_hhuu(View v) {
		LENH_VE = Variables.VE_TRU_NHOM_HHUU;
		drawView.setErase(false);
	}

	public void ve_tru_nhom_slap(View v) {
		LENH_VE = Variables.VE_TRU_NHOM_SLAP;
		drawView.setErase(false);
	}

	/*
	 * ve cong to treo
	 */
	public void ve_congto_treo_nha(View v) {
		LENH_VE = Variables.VE_CONGTO_TREO_NHA;
		drawView.setErase(false);
	}

	public void ve_congto_treo_tru_btlt_105_hhuu(View v) {
		LENH_VE = Variables.VE_CONGTO_TREO_TRU_BTLT_105_HHUU;
		drawView.setErase(false);
	}

	public void ve_congto_treo_tru_btlt_105_slap(View v) {
		LENH_VE = Variables.VE_CONGTO_TREO_TRU_BTLT_105_SLAP;
		drawView.setErase(false);
	}

	public void ve_congto_treo_tru_bttl_12(View v) {
		LENH_VE = Variables.VE_CONGTO_TREO_TRU_BTLT_12_HHUU;
		drawView.setErase(false);
	}

	public void ve_congto_treo_tru_btv_65_hhuu(View v) {
		LENH_VE = Variables.VE_CONGTO_TREO_TRU_BTV_65_HHUU;
		drawView.setErase(false);
	}

	public void ve_congto_treo_tru_btv_65_slap(View v) {
		LENH_VE = Variables.VE_CONGTO_TREO_TRU_BTV_65_SLAP;
		drawView.setErase(false);
	}

	// chon loai
	public void chon_tram(View v) {
		try {
			ln_khac.setVisibility(View.GONE);
			ln_tram.setVisibility(View.VISIBLE);
			ln_tru.setVisibility(View.GONE);
		} catch (Exception e) {

		}
	}

	public void chon_tru(View v) {
		try {
			ln_khac.setVisibility(View.GONE);
			ln_tram.setVisibility(View.GONE);
			ln_tru.setVisibility(View.VISIBLE);
		} catch (Exception e) {

		}
	}

	public void chon_khac(View v) {
		try {
			ln_khac.setVisibility(View.VISIBLE);
			ln_tram.setVisibility(View.GONE);
			ln_tru.setVisibility(View.GONE);
		} catch (Exception e) {

		}
	}

	// so do mau
	public void so_do_mau(View v) {
		TAO_MAU_BAN_DAU();
		SHOW_DIALOG_MAU();
	}

	// ve line dut
	public void ve_line_dut(View v) {
		LENH_VE = Variables.VE_LINE_DUT_NET;
		drawView.setErase(false);
	}

	// ve line
	public void ve_line(View v) {
		LENH_VE = Variables.VE_DUONG_THANG;
		drawView.setErase(false);
	}

	// ve hai line
	public void ve_hai_line(View v) {
		LENH_VE = Variables.VE_2LINE;
		drawView.setErase(false);
	}

	// ve cau
	public void ve_cau(View v) {
		LENH_VE = Variables.VE_CAU;
		drawView.setErase(false);
	}

	// ve chu
	public void ve_text(View v) {
		drawView.setErase(false);
		show_dialog_ve_text();
	}

	// ve hinh chu nhat
	public void ve_diem(View v) {
		LENH_VE = Variables.VE_TRU_BTLT_105_SLAP;
		drawView.setErase(false);
	}

	// ve hinh chu nhat
	public void ve_hinh_chu_nhat(View v) {
		LENH_VE = Variables.VE_HINH_CHU_NHAT;
		drawView.setErase(false);
	}

	// ve hinh z ngang
	public void ve_hinh_z_ngang(View v) {
		LENH_VE = Variables.VE_HINH_Z_NGANG;
		drawView.setErase(false);
	}

	// ve hinh z ngang dut
	public void ve_hinh_z_ngang_dut(View v) {
		LENH_VE = Variables.VE_HINH_Z_NGANG_DUT;
		drawView.setErase(false);
	}

	// ve cot
	public void ve_hinh_cot(View v) {
		LENH_VE = Variables.VE_COT;
		drawView.setErase(false);
	}

	// ve cot ngang
	public void ve_hinh_cot_ngang(View v) {
		LENH_VE = Variables.VE_COT_NGANG;
		drawView.setErase(false);
	}

	// ve hop
	public void ve_hinh_hop(View v) {
		LENH_VE = Variables.VE_HOP;
		drawView.setErase(false);
	}

	// ve hop ngang
	public void ve_hinh_hop_ngang(View v) {
		LENH_VE = Variables.VE_HOP_NGANG;
		drawView.setErase(false);
	}

	// chon nen
	public void chon_nen(View v) {
		if (mdb.get_sl_hinhanh_bando(Variables.HSCT_CHON.getMA_YCAU_KNAI()) > 0) {
			LENH_VE = Variables.VE_NEN;
			drawView.setErase(false);
			SHOW_DIALOG_NEN();
		} else {
			ThtShow.show_crouton_toast(Ve_So_Do_Activity.this,
					"Chưa chụp hình bản đồ", Style.ALERT);
		}

	}

	// chon duong
	public void chon_duong(View v) {
		LENH_VE = Variables.VE_DUONG;
		drawView.setErase(false);
		SHOW_DIALOG_DUONG();
	}

	// ve mui ten
	public void ve_mui_ten(View v) {
		LENH_VE = Variables.VE_MUI_TEN;
		drawView.setErase(false);
	}

	// ve cong to
	public void ve_cong_to(View v) {
		LENH_VE = Variables.VE_HINH_CONGTO;
		drawView.setErase(false);
	}

	// ra lenh ve
	public void ve_nha(View v) {
		LENH_VE = Variables.VE_NHA;
		drawView.setErase(false);
	}

	// tien toi
	public void tien_toi(View v) {
		if (ARR_XOA.size() > 0) {
			Obj_CANVAS CV_XOA = ARR_XOA.get(0);
			ARR_XOA.remove(0);
			mAdapter.notifyDataSetChanged();
			list_canvas.add(0, CV_XOA);
			DBAdapter.Insert_HSO_CANVAS(CV_XOA);
			VE_LAI_TAT_CA();
		}
	}

	// tro lai
	public void tro_lai(View v) {
		if (list_canvas.size() > 0) {
			Obj_CANVAS CV_XOA = list_canvas.get(0);
			list_canvas.remove(0);
			mdb.delete_HSO_CANVAS(CV_XOA);
			mAdapter.notifyDataSetChanged();
			ARR_XOA.add(0, CV_XOA);
			VE_LAI_TAT_CA();
		}
	}

	// luu mau
	public void luu_mau(View v) {
		final Dialog dialog = new Dialog(this, R.style.FullHeightDialog);
		dialog.setContentView(R.layout.dialog_yes_no);
		dialog.setCancelable(true);
		TextView message = (TextView) dialog
				.findViewById(R.id.tvmessagedialogtext);
		message.setText(getString(R.string.ban_co_muon_luu_mau));
		Button yes = (Button) dialog.findViewById(R.id.bmessageDialogYes);
		yes.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				try {
					LUU_MAU();
					Custom_Toast.show_blue_toast(Ve_So_Do_Activity.this,
							"đã lưu mẫu thành công !");
				} catch (Exception e) {
					ThtShow.show_crouton_toast(Ve_So_Do_Activity.this,
							e.toString(), tht.library.crouton.Style.ALERT);
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

	public void show_alert_list(final Context c, String title,
			final List<String> list) {
		CharSequence[] items = list.toArray(new CharSequence[list.size()]);
		AlertDialog.Builder builder = new AlertDialog.Builder(c);
		builder.setTitle(title);
		builder.setItems(items, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int item) {
				S_alert = list.get(item);
			}
		});
		AlertDialog alert = builder.create();
		alert.show();

	}

	public void XOA_HET() {
		mdb.delete_HSO_CANVAS_BY_MA_YCAU_KNAI(MA_YCAU_KNAI);
		ARR_XOA.clear();
		drawView.VE_LAI();
	}

	public void ve_lai_nen() {
		if (list_canvas != null) {
			for (Obj_CANVAS CV : list_canvas) {
				if (CV.getLENH() == Variables.VE_NEN) {
					mdb.delete_HSO_CANVAS(CV);
					list_canvas.remove(CV);
				}
			}
		}
		Obj_CANVAS CV = new Obj_CANVAS();
		CV.setCHON(0);
		CV.setLENH(Variables.VE_NEN);
		CV.setMA_DVIQLY(Ve_So_Do_Activity.MA_DVIQLY);
		CV.setMA_YCAU_KNAI(Ve_So_Do_Activity.MA_YCAU_KNAI);
		CV.setTEN("nen");
		CV.setX1(0);
		CV.setX2(0);
		CV.setX3(0);
		CV.setY1(0);
		CV.setY2(0);
		CV.setY3(0);
		CV.setTHUOC_TINH(0);
		CV.setHINH(0);
		DBAdapter.Insert_HSO_CANVAS(CV);
		Obj_CANVAS newcv = mdb.get_MAX_STT_CANVAS();
		list_canvas.add(0, newcv);
		dialog_nen.dismiss();
		mAdapter.notifyDataSetChanged();
		VE_LAI_TAT_CA();
	}

	public void VE_MAU_1() {
		int W = drawView.getWidth();
		int H = drawView.getHeight();
		String TEN_MAU = "MAU1";
		/**
		 * MAU 1
		 */
		if (mdb.get_PAINT_OF_MAU(TEN_MAU).size() == 0) {
			// them 2 line duong di
			Obj_MAU_CANVAS MCV_2L1 = new Obj_MAU_CANVAS(MA_DVIQLY, 0, TEN_MAU,
					Variables.VE_2LINE, (float) 10, H / 2, 0, 0, W + 50, H / 2,
					"", 0, 0);
			// ve tram 1 (1)
			Obj_MAU_CANVAS MCV_TR1 = new Obj_MAU_CANVAS(MA_DVIQLY, 0, TEN_MAU,
					Variables.VE_TRAM_3PHA_HHUU, (float) 0, 0, 0, 0, 25,
					(H / 2) + 80, "", 0, 0);
			// tru 1 (2)
			Obj_MAU_CANVAS MCV_T1 = new Obj_MAU_CANVAS(MA_DVIQLY, 0, TEN_MAU,
					Variables.VE_TRU_BTLT_12_SLAP, (float) 0, 0, 0, 0, W / 5,
					(H / 2) + 80, "", 0, 0);
			// tru 2 (3)
			Obj_MAU_CANVAS MCV_T2 = new Obj_MAU_CANVAS(MA_DVIQLY, 0, TEN_MAU,
					Variables.VE_TRU_BTLT_12_SLAP, (float) 0, 0, 0, 0,
					(W / 5) * 2, (H / 2) + 80, "", 0, 0);
			// tru 3 (4)
			Obj_MAU_CANVAS MCV_T3 = new Obj_MAU_CANVAS(MA_DVIQLY, 0, TEN_MAU,
					Variables.VE_TRU_BTLT_12_SLAP, (float) 0, 0, 0, 0,
					(W / 5) * 3, (H / 2) + 80, "", 0, 0);
			// diem dau noi (5)
			Obj_MAU_CANVAS MCV_D1 = new Obj_MAU_CANVAS(MA_DVIQLY, 0, TEN_MAU,
					Variables.VE_TRU_BTLT_105_SLAP, (float) 0, 0, 0, 0,
					(W / 5) * 4, (H / 2) + 80, "", 0, 0);
			// ve nha (6)
			Obj_MAU_CANVAS MCV_N1 = new Obj_MAU_CANVAS(MA_DVIQLY, 0, TEN_MAU,
					Variables.VE_NHA, (float) 0, 0, 0, 0, W - 50, (H / 2) + 80,
					"", 0, 0);
			// noi diem (1) va (2)
			Obj_MAU_CANVAS MCV_L1 = new Obj_MAU_CANVAS(MA_DVIQLY, 0, TEN_MAU,
					Variables.VE_DUONG_THANG, MCV_TR1.X3, MCV_TR1.Y3, 0, 0,
					MCV_T1.X3, MCV_T1.Y3, "", 0, 0);
			// noi diem (2) va (3)
			Obj_MAU_CANVAS MCV_L2 = new Obj_MAU_CANVAS(MA_DVIQLY, 0, TEN_MAU,
					Variables.VE_DUONG_THANG, MCV_T1.X3, MCV_T1.Y3, 0, 0,
					MCV_T2.X3, MCV_T2.Y3, "", 0, 0);
			// noi diem (3) va (4)
			Obj_MAU_CANVAS MCV_L3 = new Obj_MAU_CANVAS(MA_DVIQLY, 0, TEN_MAU,
					Variables.VE_DUONG_THANG, MCV_T2.X3, MCV_T2.Y3, 0, 0,
					MCV_T3.X3, MCV_T3.Y3, "", 0, 0);
			// noi diem (4) va (5)
			Obj_MAU_CANVAS MCV_L4 = new Obj_MAU_CANVAS(MA_DVIQLY, 0, TEN_MAU,
					Variables.VE_DUONG_THANG, MCV_T3.X3, MCV_T3.Y3, 0, 0,
					MCV_D1.X3, MCV_D1.Y3, "", 0, 0);
			// noi diem (5) va (6)
			Obj_MAU_CANVAS MCV_L5 = new Obj_MAU_CANVAS(MA_DVIQLY, 0, TEN_MAU,
					Variables.VE_LINE_DUT_NET, MCV_D1.X3, MCV_D1.Y3, 0, 0,
					MCV_N1.X3, MCV_N1.Y3, "", 0, 0);

			DBAdapter.Insert_MAU_CANVAS(MCV_2L1);
			DBAdapter.Insert_MAU_CANVAS(MCV_TR1);
			DBAdapter.Insert_MAU_CANVAS(MCV_T1);
			DBAdapter.Insert_MAU_CANVAS(MCV_T2);
			DBAdapter.Insert_MAU_CANVAS(MCV_T3);
			DBAdapter.Insert_MAU_CANVAS(MCV_D1);
			DBAdapter.Insert_MAU_CANVAS(MCV_N1);
			DBAdapter.Insert_MAU_CANVAS(MCV_L1);
			DBAdapter.Insert_MAU_CANVAS(MCV_L2);
			DBAdapter.Insert_MAU_CANVAS(MCV_L3);
			DBAdapter.Insert_MAU_CANVAS(MCV_L4);
			DBAdapter.Insert_MAU_CANVAS(MCV_L5);
			/**
			 * HET MAU 1
			 */
		}

	}

	public void VE_MAU_2() {
		/**
		 * MAU 2
		 */
		int W = drawView.getWidth();
		int H = drawView.getHeight();
		String TEN_MAU = "MAU2";
		if (mdb.get_PAINT_OF_MAU(TEN_MAU).size() == 0) {
			// them 2 line duong di
			Obj_MAU_CANVAS MCV_2L1 = new Obj_MAU_CANVAS(MA_DVIQLY, 0, TEN_MAU,
					Variables.VE_2LINE, (float) 10, H / 2, 0, 0, W + 50, H / 2,
					"", 0, 0);
			// ve tram 1 (1)
			Obj_MAU_CANVAS MCV_TR1 = new Obj_MAU_CANVAS(MA_DVIQLY, 0, TEN_MAU,
					Variables.VE_TRAM_3PHA_HHUU, (float) 0, 0, 0, 0, 25,
					(H / 2) - 80, "", 0, 0);
			// tru 1 (2)
			Obj_MAU_CANVAS MCV_T1 = new Obj_MAU_CANVAS(MA_DVIQLY, 0, TEN_MAU,
					Variables.VE_TRU_BTLT_12_SLAP, (float) 0, 0, 0, 0, W / 6,
					(H / 2) - 80, "", 0, 0);
			// tru 2 (3)
			Obj_MAU_CANVAS MCV_T2 = new Obj_MAU_CANVAS(MA_DVIQLY, 0, TEN_MAU,
					Variables.VE_TRU_BTLT_12_SLAP, (float) 0, 0, 0, 0,
					(W / 6) * 2, (H / 2) - 80, "", 0, 0);
			// tru 3 (4-1)
			Obj_MAU_CANVAS MCV_T3 = new Obj_MAU_CANVAS(MA_DVIQLY, 0, TEN_MAU,
					Variables.VE_TRU_BTLT_12_SLAP, (float) 0, 0, 0, 0,
					(W / 6) * 3, (H / 2) - 80, "", 0, 0);
			// tru 4 (4-2)
			Obj_MAU_CANVAS MCV_T4 = new Obj_MAU_CANVAS(MA_DVIQLY, 0, TEN_MAU,
					Variables.VE_TRU_BTLT_12_SLAP, (float) 0, 0, 0, 0,
					(W / 6) * 4, (H / 2) + 80, "", 0, 0);
			// diem dau noi (5)
			Obj_MAU_CANVAS MCV_D1 = new Obj_MAU_CANVAS(MA_DVIQLY, 0, TEN_MAU,
					Variables.VE_TRU_BTLT_105_SLAP, (float) 0, 0, 0, 0,
					(W / 6) * 5, (H / 2) + 80, "", 0, 0);
			// ve nha (6)
			Obj_MAU_CANVAS MCV_N1 = new Obj_MAU_CANVAS(MA_DVIQLY, 0, TEN_MAU,
					Variables.VE_NHA, (float) 0, 0, 0, 0, W - 50, (H / 2) + 80,
					"", 0, 0);
			// noi diem (1) va (2)
			Obj_MAU_CANVAS MCV_L1 = new Obj_MAU_CANVAS(MA_DVIQLY, 0, TEN_MAU,
					Variables.VE_DUONG_THANG, MCV_TR1.X3, MCV_TR1.Y3, 0, 0,
					MCV_T1.X3, MCV_T1.Y3, "", 0, 0);
			// noi diem (2) va (3)
			Obj_MAU_CANVAS MCV_L2 = new Obj_MAU_CANVAS(MA_DVIQLY, 0, TEN_MAU,
					Variables.VE_DUONG_THANG, MCV_T1.X3, MCV_T1.Y3, 0, 0,
					MCV_T2.X3, MCV_T2.Y3, "", 0, 0);
			// noi diem (3) va (4)
			Obj_MAU_CANVAS MCV_L3 = new Obj_MAU_CANVAS(MA_DVIQLY, 0, TEN_MAU,
					Variables.VE_DUONG_THANG, MCV_T2.X3, MCV_T2.Y3, 0, 0,
					MCV_T3.X3, MCV_T3.Y3, "", 0, 0);
			// noi diem (4-1) va (4-2)
			Obj_MAU_CANVAS MCV_L6 = new Obj_MAU_CANVAS(MA_DVIQLY, 0, TEN_MAU,
					Variables.VE_DUONG_THANG, MCV_T3.X3, MCV_T3.Y3, 0, 0,
					MCV_T4.X3, MCV_T4.Y3, "", 0, 0);
			// noi diem (4-2) va (5)
			Obj_MAU_CANVAS MCV_L4 = new Obj_MAU_CANVAS(MA_DVIQLY, 0, TEN_MAU,
					Variables.VE_DUONG_THANG, MCV_T4.X3, MCV_T4.Y3, 0, 0,
					MCV_D1.X3, MCV_D1.Y3, "", 0, 0);
			// noi diem (5) va (6)
			Obj_MAU_CANVAS MCV_L5 = new Obj_MAU_CANVAS(MA_DVIQLY, 0, TEN_MAU,
					Variables.VE_LINE_DUT_NET, MCV_D1.X3, MCV_D1.Y3, 0, 0,
					MCV_N1.X3, MCV_N1.Y3, "", 0, 0);

			DBAdapter.Insert_MAU_CANVAS(MCV_2L1);
			DBAdapter.Insert_MAU_CANVAS(MCV_TR1);
			DBAdapter.Insert_MAU_CANVAS(MCV_T1);
			DBAdapter.Insert_MAU_CANVAS(MCV_T2);
			DBAdapter.Insert_MAU_CANVAS(MCV_T3);
			DBAdapter.Insert_MAU_CANVAS(MCV_T4);
			DBAdapter.Insert_MAU_CANVAS(MCV_D1);
			DBAdapter.Insert_MAU_CANVAS(MCV_N1);
			DBAdapter.Insert_MAU_CANVAS(MCV_L1);
			DBAdapter.Insert_MAU_CANVAS(MCV_L2);
			DBAdapter.Insert_MAU_CANVAS(MCV_L3);
			DBAdapter.Insert_MAU_CANVAS(MCV_L4);
			DBAdapter.Insert_MAU_CANVAS(MCV_L5);
			DBAdapter.Insert_MAU_CANVAS(MCV_L6);
			/**
			 * HET MAU 2
			 */
		}
	}

	public void VE_MAU_3() {

		// ve duong di

		/**
		 * MAU 3
		 */
		int W = drawView.getWidth();
		int H = drawView.getHeight();
		String TEN_MAU = "MAU3";
		if (mdb.get_PAINT_OF_MAU(TEN_MAU).size() == 0) {
			// them 2 line duong di
			Obj_MAU_CANVAS MCV_2L1 = new Obj_MAU_CANVAS(MA_DVIQLY, 0, TEN_MAU,
					Variables.VE_SONG, (float) 10, H / 2, 0, 0, W, H / 2, "",
					0, 0);
			//
			// ve tram 1 (1)
			Obj_MAU_CANVAS MCV_TR1 = new Obj_MAU_CANVAS(MA_DVIQLY, 0, TEN_MAU,
					Variables.VE_TRAM_3PHA_HHUU, (float) 0, 0, 0, 0, 25,
					(H / 2) - 80, "", 0, 0);
			// tru 1 (2)
			Obj_MAU_CANVAS MCV_T1 = new Obj_MAU_CANVAS(MA_DVIQLY, 0, TEN_MAU,
					Variables.VE_TRU_BTLT_12_SLAP, (float) 0, 0, 0, 0, W / 6,
					(H / 2) - 80, "", 0, 0);
			// tru 2 (3)
			Obj_MAU_CANVAS MCV_T2 = new Obj_MAU_CANVAS(MA_DVIQLY, 0, TEN_MAU,
					Variables.VE_TRU_BTLT_12_SLAP, (float) 0, 0, 0, 0,
					(W / 6) * 2, (H / 2) - 80, "", 0, 0);
			// tru 3 (4-1)
			Obj_MAU_CANVAS MCV_T3 = new Obj_MAU_CANVAS(MA_DVIQLY, 0, TEN_MAU,
					Variables.VE_TRU_BTLT_12_SLAP, (float) 0, 0, 0, 0,
					(W / 6) * 3, (H / 2) - 80, "", 0, 0);
			// tru 4 (4-2)
			Obj_MAU_CANVAS MCV_T4 = new Obj_MAU_CANVAS(MA_DVIQLY, 0, TEN_MAU,
					Variables.VE_TRU_BTLT_12_SLAP, (float) 0, 0, 0, 0,
					(W / 6) * 4, (H / 2) + 80, "", 0, 0);
			// diem dau noi (5)
			Obj_MAU_CANVAS MCV_D1 = new Obj_MAU_CANVAS(MA_DVIQLY, 0, TEN_MAU,
					Variables.VE_TRU_BTLT_105_SLAP, (float) 0, 0, 0, 0,
					(W / 6) * 5, (H / 2) + 80, "", 0, 0);
			// ve nha (6)
			Obj_MAU_CANVAS MCV_N1 = new Obj_MAU_CANVAS(MA_DVIQLY, 0, TEN_MAU,
					Variables.VE_NHA, (float) 0, 0, 0, 0, W - 50, (H / 2) + 80,
					"", 0, 0);
			// noi diem (1) va (2)
			Obj_MAU_CANVAS MCV_L1 = new Obj_MAU_CANVAS(MA_DVIQLY, 0, TEN_MAU,
					Variables.VE_DUONG_THANG, MCV_TR1.X3, MCV_TR1.Y3, 0, 0,
					MCV_T1.X3, MCV_T1.Y3, "", 0, 0);
			// noi diem (2) va (3)
			Obj_MAU_CANVAS MCV_L2 = new Obj_MAU_CANVAS(MA_DVIQLY, 0, TEN_MAU,
					Variables.VE_DUONG_THANG, MCV_T1.X3, MCV_T1.Y3, 0, 0,
					MCV_T2.X3, MCV_T2.Y3, "", 0, 0);
			// noi diem (3) va (4)
			Obj_MAU_CANVAS MCV_L3 = new Obj_MAU_CANVAS(MA_DVIQLY, 0, TEN_MAU,
					Variables.VE_DUONG_THANG, MCV_T2.X3, MCV_T2.Y3, 0, 0,
					MCV_T3.X3, MCV_T3.Y3, "", 0, 0);
			// noi diem (4-1) va (4-2)
			Obj_MAU_CANVAS MCV_L6 = new Obj_MAU_CANVAS(MA_DVIQLY, 0, TEN_MAU,
					Variables.VE_DUONG_THANG, MCV_T3.X3, MCV_T3.Y3, 0, 0,
					MCV_T4.X3, MCV_T4.Y3, "", 0, 0);
			// noi diem (4-2) va (5)
			Obj_MAU_CANVAS MCV_L4 = new Obj_MAU_CANVAS(MA_DVIQLY, 0, TEN_MAU,
					Variables.VE_DUONG_THANG, MCV_T4.X3, MCV_T4.Y3, 0, 0,
					MCV_D1.X3, MCV_D1.Y3, "", 0, 0);
			// noi diem (5) va (6)
			Obj_MAU_CANVAS MCV_L5 = new Obj_MAU_CANVAS(MA_DVIQLY, 0, TEN_MAU,
					Variables.VE_LINE_DUT_NET, MCV_D1.X3, MCV_D1.Y3, 0, 0,
					MCV_N1.X3, MCV_N1.Y3, "", 0, 0);

			DBAdapter.Insert_MAU_CANVAS(MCV_2L1);
			DBAdapter.Insert_MAU_CANVAS(MCV_TR1);
			DBAdapter.Insert_MAU_CANVAS(MCV_T1);
			DBAdapter.Insert_MAU_CANVAS(MCV_T2);
			DBAdapter.Insert_MAU_CANVAS(MCV_T3);
			DBAdapter.Insert_MAU_CANVAS(MCV_T4);
			DBAdapter.Insert_MAU_CANVAS(MCV_D1);
			DBAdapter.Insert_MAU_CANVAS(MCV_N1);
			DBAdapter.Insert_MAU_CANVAS(MCV_L1);
			DBAdapter.Insert_MAU_CANVAS(MCV_L2);
			DBAdapter.Insert_MAU_CANVAS(MCV_L3);
			DBAdapter.Insert_MAU_CANVAS(MCV_L4);
			DBAdapter.Insert_MAU_CANVAS(MCV_L5);
			DBAdapter.Insert_MAU_CANVAS(MCV_L6);
			/**
			 * HET MAU 2
			 */
		}

	}

	public void VE_DUONG_1() {

		// ve duong di

		/**
		 * DUONG 1
		 */
		int W = drawView.getWidth();
		int H = drawView.getHeight();
		String TEN_MAU = "DUONG1";
		if (mdb.get_PAINT_OF_MAU(TEN_MAU).size() == 0) {
			// them 2 line duong di
			Obj_MAU_CANVAS MCV_2L_NGANG_1 = new Obj_MAU_CANVAS(MA_DVIQLY, 0,
					TEN_MAU, Variables.VE_2LINE, (float) 10, H / 2, 0, 0,
					W / 2 - 15, H / 2, "", 0, 0);

			Obj_MAU_CANVAS MCV_2L_NGANG_2 = new Obj_MAU_CANVAS(MA_DVIQLY, 0,
					TEN_MAU, Variables.VE_2LINE, W / 2 + 15, H / 2, 0, 0,
					W - 10, H / 2, "", 0, 0);

			Obj_MAU_CANVAS MCV_2L_DUNG_1 = new Obj_MAU_CANVAS(MA_DVIQLY, 0,
					TEN_MAU, Variables.VE_2LINE, (float) W / 2, 10, 0, 0,
					W / 2, H / 2 - 15, "", 0, 0);

			Obj_MAU_CANVAS MCV_2L_DUNG_2 = new Obj_MAU_CANVAS(MA_DVIQLY, 0,
					TEN_MAU, Variables.VE_2LINE, W / 2, H / 2 + 15, 0, 0,
					W / 2, H - 10, "", 0, 0);

			Obj_MAU_CANVAS MCV_TRON = new Obj_MAU_CANVAS(MA_DVIQLY, 0, TEN_MAU,
					Variables.VE_TRU_BTLT_105_HHUU, 0, 0, 0, 0, W / 2, H / 2,
					"", 0, 0);
			//

			DBAdapter.Insert_MAU_CANVAS(MCV_2L_NGANG_1);
			DBAdapter.Insert_MAU_CANVAS(MCV_2L_NGANG_2);
			DBAdapter.Insert_MAU_CANVAS(MCV_2L_DUNG_1);
			DBAdapter.Insert_MAU_CANVAS(MCV_2L_DUNG_2);
			DBAdapter.Insert_MAU_CANVAS(MCV_TRON);

			/**
			 * HET MAU 2
			 */
		}

	}

	public void XOA_CANVAS(Obj_CANVAS HSCV, int pos) {
		mdb.delete_HSO_CANVAS(HSCV);
		list_canvas.remove(pos);
		mAdapter.notifyDataSetChanged();
		VE_LAI_TAT_CA();
	}

	public void VE_LAI_TAT_CA() {
		if (list_canvas.size() > 0) {
			drawView.setErase(false);
			drawView.VE_LAI();
			drawView.ve_lai_all(list_canvas);
		} else {
			drawView.VE_LAI();
		}
	}

	public void TINH_TIEN(int HUONG, int POS) {
		if (list_canvas.size() > 0) {
			Obj_CANVAS CV = list_canvas.get(POS);
			int DO_LECH = 10;
			for (int i = 0; i < list_canvas.size(); i++) {
				Obj_CANVAS CV_LIST = list_canvas.get(i);
				if (CV_LIST.X3 == CV.X3 & CV_LIST.Y3 == CV.Y3) {
					if (CV_LIST.LENH == Variables.VE_2LINE) {
						if (HUONG == TRAI) {
							mdb.update_TOADO_CANVAS_DAU(MA_YCAU_KNAI,
									CV_LIST.STT, CV.X1 - DO_LECH, CV.Y1);
							mdb.update_TOADO_CANVAS_CUOI(MA_YCAU_KNAI,
									CV_LIST.STT, CV.X3 - DO_LECH, CV.Y3);
						} else if (HUONG == PHAI) {
							mdb.update_TOADO_CANVAS_DAU(MA_YCAU_KNAI,
									CV_LIST.STT, CV.X1 + DO_LECH, CV.Y1);
							mdb.update_TOADO_CANVAS_CUOI(MA_YCAU_KNAI,
									CV_LIST.STT, CV.X3 + DO_LECH, CV.Y3);
						} else if (HUONG == LEN) {
							mdb.update_TOADO_CANVAS_DAU(MA_YCAU_KNAI,
									CV_LIST.STT, CV.X1, CV.Y1 - DO_LECH);
							mdb.update_TOADO_CANVAS_CUOI(MA_YCAU_KNAI,
									CV_LIST.STT, CV.X3, CV.Y3 - DO_LECH);
						} else if (HUONG == XUONG) {
							mdb.update_TOADO_CANVAS_DAU(MA_YCAU_KNAI,
									CV_LIST.STT, CV.X1, CV.Y1 + DO_LECH);
							mdb.update_TOADO_CANVAS_CUOI(MA_YCAU_KNAI,
									CV_LIST.STT, CV.X3, CV.Y3 + DO_LECH);
						}

					} else {

						if (HUONG == TRAI) {
							if (CV.X3 - DO_LECH < 0) {
								mdb.update_TOADO_CANVAS_CUOI(MA_YCAU_KNAI,
										CV_LIST.STT, Float.parseFloat("30"),
										CV.Y3);

							} else {
								mdb.update_TOADO_CANVAS_CUOI(MA_YCAU_KNAI,
										CV_LIST.STT, CV.X3 - DO_LECH, CV.Y3);
							}

						} else if (HUONG == PHAI) {
							if (CV.X3 + DO_LECH > DrawingView.drawCanvas
									.getWidth()) {
								mdb.update_TOADO_CANVAS_CUOI(
										MA_YCAU_KNAI,
										CV_LIST.STT,
										(float) (DrawingView.drawCanvas
												.getWidth() - 30) + DO_LECH,
										CV.Y3);
							} else {
								mdb.update_TOADO_CANVAS_CUOI(MA_YCAU_KNAI,
										CV_LIST.STT, CV.X3 + DO_LECH, CV.Y3);
							}

						} else if (HUONG == LEN) {
							if (CV.Y3 + DO_LECH < 0) {
								mdb.update_TOADO_CANVAS_CUOI(MA_YCAU_KNAI,
										CV_LIST.STT, CV.X3,
										Float.parseFloat("30") - DO_LECH);
							} else {
								mdb.update_TOADO_CANVAS_CUOI(MA_YCAU_KNAI,
										CV_LIST.STT, CV.X3, CV.Y3 - DO_LECH);
							}

						} else if (HUONG == XUONG) {
							if (CV.Y3 + DO_LECH > DrawingView.drawCanvas
									.getHeight()) {
								mdb.update_TOADO_CANVAS_CUOI(MA_YCAU_KNAI,
										CV_LIST.STT, CV.X3,
										(float) (DrawingView.drawCanvas
												.getHeight() - 30));
							} else {
								mdb.update_TOADO_CANVAS_CUOI(MA_YCAU_KNAI,
										CV_LIST.STT, CV.X3, CV.Y3 + DO_LECH);
							}

						}
					}
				}
				if (CV_LIST.X1 == CV.X3 & CV_LIST.Y1 == CV.Y3) {
					if (CV_LIST.LENH != Variables.VE_2LINE) {
						if (HUONG == TRAI) {
							if (CV.X3 - DO_LECH < 0) {
								mdb.update_TOADO_CANVAS_DAU(MA_YCAU_KNAI,
										CV_LIST.STT, Float.parseFloat("30"),
										CV.Y3);

							} else {
								mdb.update_TOADO_CANVAS_DAU(MA_YCAU_KNAI,
										CV_LIST.STT, CV.X3 - DO_LECH, CV.Y3);
							}

						} else if (HUONG == PHAI) {
							if (CV.X3 + DO_LECH > DrawingView.drawCanvas
									.getWidth()) {
								mdb.update_TOADO_CANVAS_DAU(
										MA_YCAU_KNAI,
										CV_LIST.STT,
										(float) (DrawingView.drawCanvas
												.getWidth() - 30) + DO_LECH,
										CV.Y3);
							} else {
								mdb.update_TOADO_CANVAS_DAU(MA_YCAU_KNAI,
										CV_LIST.STT, CV.X3 + DO_LECH, CV.Y3);
							}
						} else if (HUONG == LEN) {

							if (CV.Y3 + DO_LECH < 0) {
								mdb.update_TOADO_CANVAS_DAU(MA_YCAU_KNAI,
										CV_LIST.STT, CV.X3,
										Float.parseFloat("30"));
							} else {
								mdb.update_TOADO_CANVAS_DAU(MA_YCAU_KNAI,
										CV_LIST.STT, CV.X3, CV.Y3 - DO_LECH);
							}

						} else if (HUONG == XUONG) {

							if (CV.Y3 + DO_LECH > DrawingView.drawCanvas
									.getHeight()) {
								mdb.update_TOADO_CANVAS_DAU(MA_YCAU_KNAI,
										CV_LIST.STT, CV.X3,
										(float) (DrawingView.drawCanvas
												.getHeight() - 30));
							} else {
								mdb.update_TOADO_CANVAS_DAU(MA_YCAU_KNAI,
										CV_LIST.STT, CV.X3, CV.Y3 + DO_LECH);
							}

						}

					}
				}
			}
			VE_LAI_TAT_CA();
			// if (HUONG == TRAI) {
			// drawView.VE_DANH_DAU(CV.X1 - DO_LECH, CV.Y1, CV.X3 - DO_LECH,
			// CV.Y3, CV.LENH);
			// } else if (HUONG == PHAI) {
			// drawView.VE_DANH_DAU(CV.X1 + DO_LECH, CV.Y1, CV.X3 + DO_LECH,
			// CV.Y3, CV.LENH);
			// } else if (HUONG == LEN) {
			// drawView.VE_DANH_DAU(CV.X1, CV.Y1 - DO_LECH, CV.X3, CV.Y3
			// - DO_LECH, CV.LENH);
			// } else if (HUONG == XUONG) {
			// drawView.VE_DANH_DAU(CV.X1, CV.Y1 + DO_LECH, CV.X3, CV.Y3
			// + DO_LECH, CV.LENH);
			// }
		}
	}

	public void CHON_PAINT(Obj_CANVAS mCV) {
		// list_canvas = mdb.get_HSO_CANVAS_BY_MA_YCAU_KNAI(MA_YCAU_KNAI);
		// KIEM_TRA_LICH_SU();
		//
		// if (list_canvas.size() > 0) {
		// L = Variables.KO_VE;
		// drawView.setErase(false);
		// drawView.VE_LAI();
		// VE_LAI_TAT_CA();
		// Obj_CANVAS CV = list_canvas.get(Lst_HSO_CANVAS.POS);
		// drawView.VE_DANH_DAU(CV.X1, CV.Y1, CV.X3, CV.Y3, CV.LENH);
		//
		// }
	}

	public void TINH_TIEN_LONG1(int HUONG, int POS) {
		if (list_canvas.size() > 0) {
			Obj_CANVAS CV = list_canvas.get(POS);
			int DO_LECH = 10;
			for (int i = 0; i < list_canvas.size(); i++) {
				Obj_CANVAS CV_LIST = list_canvas.get(i);
				if (CV_LIST.X3 == CV.X3 & CV_LIST.Y3 == CV.Y3) {
					if (CV_LIST.LENH == Variables.VE_2LINE) {
						if (HUONG == TRAI) {
							mdb.update_TOADO_CANVAS_DAU(MA_YCAU_KNAI,
									CV_LIST.STT, CV.X1 - DO_LECH, CV.Y1);
							mdb.update_TOADO_CANVAS_CUOI(MA_YCAU_KNAI,
									CV_LIST.STT, CV.X3 - DO_LECH, CV.Y3);
						} else if (HUONG == PHAI) {
							mdb.update_TOADO_CANVAS_DAU(MA_YCAU_KNAI,
									CV_LIST.STT, CV.X1 + DO_LECH, CV.Y1);
							mdb.update_TOADO_CANVAS_CUOI(MA_YCAU_KNAI,
									CV_LIST.STT, CV.X3 + DO_LECH, CV.Y3);
						} else if (HUONG == LEN) {
							mdb.update_TOADO_CANVAS_DAU(MA_YCAU_KNAI,
									CV_LIST.STT, CV.X1, CV.Y1 - DO_LECH);
							mdb.update_TOADO_CANVAS_CUOI(MA_YCAU_KNAI,
									CV_LIST.STT, CV.X3, CV.Y3 - DO_LECH);
						} else if (HUONG == XUONG) {
							mdb.update_TOADO_CANVAS_DAU(MA_YCAU_KNAI,
									CV_LIST.STT, CV.X1, CV.Y1 + DO_LECH);
							mdb.update_TOADO_CANVAS_CUOI(MA_YCAU_KNAI,
									CV_LIST.STT, CV.X3, CV.Y3 + DO_LECH);
						}

					} else {

						if (HUONG == TRAI) {
							if (CV.X3 - DO_LECH > 0) {
								mdb.update_TOADO_CANVAS_CUOI(MA_YCAU_KNAI,
										CV_LIST.STT, CV.X3 - DO_LECH, CV.Y3);
							}

						} else if (HUONG == PHAI) {
							mdb.update_TOADO_CANVAS_CUOI(MA_YCAU_KNAI,
									CV_LIST.STT, CV.X3 + DO_LECH, CV.Y3);
						} else if (HUONG == LEN) {
							mdb.update_TOADO_CANVAS_CUOI(MA_YCAU_KNAI,
									CV_LIST.STT, CV.X3, CV.Y3 - DO_LECH);
						} else if (HUONG == XUONG) {
							mdb.update_TOADO_CANVAS_CUOI(MA_YCAU_KNAI,
									CV_LIST.STT, CV.X3, CV.Y3 + DO_LECH);
						}
					}
				}
				if (CV_LIST.X1 == CV.X3 & CV_LIST.Y1 == CV.Y3) {
					if (CV_LIST.LENH != Variables.VE_2LINE) {
						if (HUONG == TRAI) {
							mdb.update_TOADO_CANVAS_DAU(MA_YCAU_KNAI,
									CV_LIST.STT, CV.X3 - DO_LECH, CV.Y3);
						} else if (HUONG == PHAI) {
							mdb.update_TOADO_CANVAS_DAU(MA_YCAU_KNAI,
									CV_LIST.STT, CV.X3 + DO_LECH, CV.Y3);
						} else if (HUONG == LEN) {
							mdb.update_TOADO_CANVAS_DAU(MA_YCAU_KNAI,
									CV_LIST.STT, CV.X3, CV.Y3 - DO_LECH);
						} else if (HUONG == XUONG) {
							mdb.update_TOADO_CANVAS_DAU(MA_YCAU_KNAI,
									CV_LIST.STT, CV.X3, CV.Y3 + DO_LECH);
						}

					}
				}
			}
			VE_LAI_TAT_CA();
			// if (HUONG == TRAI) {
			// drawView.VE_DANH_DAU(CV.X1, CV.Y1, CV.X3 - DO_LECH, CV.Y3,
			// CV.LENH);
			// } else if (HUONG == PHAI) {
			// drawView.VE_DANH_DAU(CV.X1, CV.Y1, CV.X3 + DO_LECH, CV.Y3,
			// CV.LENH);
			// } else if (HUONG == LEN) {
			// drawView.VE_DANH_DAU(CV.X1, CV.Y1, CV.X3, CV.Y3 - DO_LECH,
			// CV.LENH);
			// } else if (HUONG == XUONG) {
			// drawView.VE_DANH_DAU(CV.X1, CV.Y1, CV.X3, CV.Y3 + DO_LECH,
			// CV.LENH);
			// }
		}
	}

	@SuppressLint("ClickableViewAccessibility")
	@Override
	public boolean onTouch(View v, MotionEvent event) {

		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			if (mHandler != null)
				return true;
			mHandler = new Handler();
			if (v.getId() == R.id.btn_TRAI) {
				mHandler.postDelayed(mAction_TRAI, 50);
			} else if (v.getId() == R.id.btn_PHAI) {
				mHandler.postDelayed(mAction_PHAI, 50);
			} else if (v.getId() == R.id.btn_LEN) {
				mHandler.postDelayed(mAction_LEN, 50);
			} else if (v.getId() == R.id.btn_XUONG) {
				mHandler.postDelayed(mAction_XUONG, 50);
			}
			break;
		case MotionEvent.ACTION_UP:
			if (mHandler == null)
				return true;
			if (v.getId() == R.id.btn_TRAI) {
				mHandler.removeCallbacks(mAction_TRAI);
			} else if (v.getId() == R.id.btn_PHAI) {
				mHandler.removeCallbacks(mAction_PHAI);
			} else if (v.getId() == R.id.btn_LEN) {
				mHandler.removeCallbacks(mAction_LEN);
			} else if (v.getId() == R.id.btn_XUONG) {
				mHandler.removeCallbacks(mAction_XUONG);
			}

			mHandler = null;
			break;
		}
		return false;
	}

	Runnable mAction_TRAI = new Runnable() {
		@Override
		public void run() {
			System.out.println("Performing action...");
			// TINH_TIEN(TRAI, STT_CHON);
			mHandler.postDelayed(this, 50);
		}
	};
	Runnable mAction_PHAI = new Runnable() {
		@Override
		public void run() {
			System.out.println("Performing action...");
			// TINH_TIEN(PHAI, STT_CHON);
			mHandler.postDelayed(this, 50);
		}
	};
	Runnable mAction_LEN = new Runnable() {
		@Override
		public void run() {
			System.out.println("Performing action...");
			// TINH_TIEN(LEN, STT_CHON);
			mHandler.postDelayed(this, 50);
		}
	};
	Runnable mAction_XUONG = new Runnable() {
		@Override
		public void run() {
			System.out.println("Performing action...");
			// TINH_TIEN(XUONG, STT_CHON);
			mHandler.postDelayed(this, 50);
		}
	};

	public void LUU_MAU() {
		if (list_canvas.size() > 5) {
			for (int i = 0; i < list_canvas.size(); i++) {
				Obj_CANVAS CV = list_canvas.get(i);
				Obj_MAU_CANVAS MCV = new Obj_MAU_CANVAS(
						MA_DVIQLY,
						0,
						Utils.replace_special_char(Variables.HSCT_CHON
								.getTENTRAM_BIENAP())
								+ "-"
								+ Utils.replace_special_char(Variables.HSCT_CHON
										.getTEN_KHANG())
								+ "("
								+ (Memory.LAY_TIME_TOI_PHUT()) + ")", CV.LENH,
						CV.X1, CV.Y1, CV.X2, CV.Y2, CV.X3, CV.Y3, CV.TEN, 0, 0);
				DBAdapter.Insert_MAU_CANVAS(MCV);
			}
		} else {
			Custom_Toast.show_yellow_toast(Ve_So_Do_Activity.this,
					"Chỉ lưu khi vẽ trên 5 nét");
		}
	}

	public void TAO_MAU_BAN_DAU() {
		List<Obj_MAU_CANVAS> ARR_MCV = mdb.get_list_MAU_CANVAS(MA_DVIQLY);
		if (ARR_MCV.size() == 0) {
			VE_MAU_1();
			VE_MAU_2();
			VE_MAU_3();
		}
	}

	// them net ve
	public static void them_net_ve(Obj_CANVAS mCV) {
		DBAdapter.Insert_HSO_CANVAS(mCV);
		Obj_CANVAS new_cv = mdb.get_MAX_STT_CANVAS();
		list_canvas.add(0, new_cv);
		mAdapter.notifyDataSetChanged();
		LENH_VE = Variables.MOVE;
	}

	public void show_dialog_sua_ten(final Obj_CANVAS CV) {
		final List<Obj_nhap_lieu> list_nhap_lieu = mdb
				.get_list_NL(Variables.NL_CANVAS);
		Adapter_nhap_lieu mAdapter_TT = new Adapter_nhap_lieu(list_nhap_lieu,
				Ve_So_Do_Activity.this, mdb);
		dialog_GC = new Dialog_listview_ghichu(Ve_So_Do_Activity.this,
				Length_text.ML_TEN);
		dialog_GC.show();
		Dialog_listview_ghichu.lv_ghichu.setAdapter(mAdapter_TT);
		// neu la tram
		if (CV.getLENH() == Variables.VE_TRAM_1PHA_HHUU
				|| CV.getLENH() == Variables.VE_TRAM_1PHA_SLAP
				|| CV.getLENH() == Variables.VE_TRAM_3PHA_HHUU
				|| CV.getLENH() == Variables.VE_TRAM_3PHA_NEN_HHUU
				|| CV.getLENH() == Variables.VE_TRAM_3PHA_NEN_SLAP
				|| CV.getLENH() == Variables.VE_TRAM_3PHA_NHA_HHUU
				|| CV.getLENH() == Variables.VE_TRAM_3PHA_NHA_SLAP
				|| CV.getLENH() == Variables.VE_TRAM_3PHA_SLAP) {
			if (CV.getTEN().equals("")) {
				Dialog_listview_ghichu.edt_ghichu.setText(Variables.HSCT_CHON
						.getTENTRAM_BIENAP());
			} else {
				Dialog_listview_ghichu.edt_ghichu.setText(CV.getTEN());
			}
		}
		// tru
		else if (CV.getLENH() == Variables.VE_TRU_BTLT_105_HHUU
				|| CV.getLENH() == Variables.VE_TRU_BTLT_105_SLAP
				|| CV.getLENH() == Variables.VE_TRU_BTLT_12_HHUU
				|| CV.getLENH() == Variables.VE_TRU_BTLT_12_SLAP
				|| CV.getLENH() == Variables.VE_TRU_BTLT_14_HHUU
				|| CV.getLENH() == Variables.VE_TRU_BTLT_14_SLAP
				|| CV.getLENH() == Variables.VE_TRU_BTLT_20_HHUU
				|| CV.getLENH() == Variables.VE_TRU_BTLT_20_SLAP
				|| CV.getLENH() == Variables.VE_TRU_BTLT_75_HHUU
				|| CV.getLENH() == Variables.VE_TRU_BTLT_75_SLAP
				|| CV.getLENH() == Variables.VE_TRU_BTLT_85_HHUU
				|| CV.getLENH() == Variables.VE_TRU_BTLT_85_SLAP
				|| CV.getLENH() == Variables.VE_TRU_BTV_11_HHUU
				|| CV.getLENH() == Variables.VE_TRU_BTV_11_SLAP
				|| CV.getLENH() == Variables.VE_TRU_BTV_65_HHUU
				|| CV.getLENH() == Variables.VE_TRU_BTV_65_SLAP
				|| CV.getLENH() == Variables.VE_TRU_BTV_75_HHUU
				|| CV.getLENH() == Variables.VE_TRU_BTV_75_SLAP
				|| CV.getLENH() == Variables.VE_TRU_BTV_85_HHUU
				|| CV.getLENH() == Variables.VE_TRU_BTV_85_SLAP
				|| CV.getLENH() == Variables.VE_TRU_GO_HHUU
				|| CV.getLENH() == Variables.VE_TRU_GO_SLAP
				|| CV.getLENH() == Variables.VE_TRU_NHOM_HHUU
				|| CV.getLENH() == Variables.VE_TRU_NHOM_SLAP) {
			if (CV.getTEN().equals("")) {
				Dialog_listview_ghichu.edt_ghichu.setText(Variables.HSCT_CHON
						.getTRU());
			} else {
				Dialog_listview_ghichu.edt_ghichu.setText(CV.getTEN());
			}
		} else if (CV.getLENH() == Variables.VE_LINE_DUT_NET) {
			if (CV.getTEN().equals("")) {
				Dialog_listview_ghichu.edt_ghichu.setText(Variables.HSCT_CHON
						.getKC_CONGTO_DEN_LUOI());
			} else {
				Dialog_listview_ghichu.edt_ghichu.setText(CV.getTEN());
			}
		} else if (CV.getLENH() == Variables.VE_DUONG_THANG) {
			if (CV.getTEN() != null) {
				if (CV.getTEN().equals("")) {
					String kc = "";
					try {
						double a = Thtcovert
								.String_to_double(Variables.HSCT_CHON
										.getKC_CONGTO_DEN_LUOI());
						double b = Thtcovert
								.String_to_double(Variables.HSCT_CHON
										.getKC_CONGTO_DEN_TRAM());
						kc = Thtcovert.double_to_String(b - a);
						Dialog_listview_ghichu.edt_ghichu.setText(kc);
					} catch (Exception e) {
					}

				} else {
					Dialog_listview_ghichu.edt_ghichu.setText(CV.getTEN());
				}
			} else {
				Dialog_listview_ghichu.edt_ghichu.setText("");
			}
		} else {
			Dialog_listview_ghichu.edt_ghichu.setText(CV.getTEN());
		}
		// het tru
		Dialog_listview_ghichu.edt_ghichu
				.setSelection(Dialog_listview_ghichu.edt_ghichu.getText()
						.length());
		Dialog_listview_ghichu.lv_ghichu
				.setOnItemClickListener(new OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						Dialog_listview_ghichu.edt_ghichu
								.setText(list_nhap_lieu.get(arg2).nhap_lieu);
						Dialog_listview_ghichu.edt_ghichu
								.setSelection(Dialog_listview_ghichu.edt_ghichu
										.getText().length());
					}
				});
		Dialog_listview_ghichu.btn_ok.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (Dialog_listview_ghichu.edt_ghichu.getText().length() <= dialog_GC.max_length) {
					try {
						CV.setTEN(Dialog_listview_ghichu.edt_ghichu.getText()
								.toString());
						mdb.update_HSO_CANVAS(CV);
						VE_LAI_TAT_CA();
						mAdapter.notifyDataSetChanged();
						Obj_nhap_lieu NL = new Obj_nhap_lieu(
								Dialog_listview_ghichu.edt_ghichu.getText()
										.toString(), Variables.NL_CANVAS);
						if (mdb.chua_ton_tai_nhap_lieu(NL)) {
							DBAdapter.Insert_NHAP_LIEU(NL);
						}
					} catch (Exception e) {

					}

					dialog_GC.dismiss();
				} else {
					Length_text.alert_length(Ve_So_Do_Activity.this,
							dialog_GC.max_length);
				}

			}
		});
		WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
		lp.copyFrom(dialog_GC.getWindow().getAttributes());
		lp.width = Tht_Screen.get_screen_width_percent(Ve_So_Do_Activity.this,
				90);
		lp.height = Tht_Screen.get_screen_heigth_percent(
				Ve_So_Do_Activity.this, 90);
		dialog_GC.getWindow().setAttributes(lp);
	}

	public void show_dialog_ve_text() {
		final List<Obj_nhap_lieu> list_nhap_lieu = mdb
				.get_list_NL(Variables.NL_CANVAS);
		Adapter_nhap_lieu mAdapter_TT = new Adapter_nhap_lieu(list_nhap_lieu,
				Ve_So_Do_Activity.this, mdb);
		dialog_GC = new Dialog_listview_ghichu(Ve_So_Do_Activity.this,
				Length_text.ML_TEN);
		dialog_GC.show();
		Dialog_listview_ghichu.lv_ghichu.setAdapter(mAdapter_TT);
		Dialog_listview_ghichu.edt_ghichu.setText("");
		Dialog_listview_ghichu.edt_ghichu
				.setSelection(Dialog_listview_ghichu.edt_ghichu.getText()
						.length());
		Dialog_listview_ghichu.lv_ghichu
				.setOnItemClickListener(new OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						Dialog_listview_ghichu.edt_ghichu
								.setText(list_nhap_lieu.get(arg2).nhap_lieu);
						Dialog_listview_ghichu.edt_ghichu
								.setSelection(Dialog_listview_ghichu.edt_ghichu
										.getText().length());
					}
				});
		Dialog_listview_ghichu.btn_ok.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (Dialog_listview_ghichu.edt_ghichu.getText().length() <= dialog_GC.max_length) {
					try {
						S_VE = Dialog_listview_ghichu.edt_ghichu.getText()
								.toString();
						if (S_VE.length() > 0) {
							LENH_VE = Variables.VE_TEXT;
						} else {
							LENH_VE = Variables.MOVE;
							ThtShow.show_crouton_toast(Ve_So_Do_Activity.this,
									"Chưa tạo chữ để vẽ !",
									Style.ALERT);
						}
					} catch (Exception e) {

					}
					Obj_nhap_lieu NL = new Obj_nhap_lieu(
							Dialog_listview_ghichu.edt_ghichu.getText()
									.toString(), Variables.NL_CANVAS);
					if (mdb.chua_ton_tai_nhap_lieu(NL)) {
						DBAdapter.Insert_NHAP_LIEU(NL);
					}
					dialog_GC.dismiss();
				} else {
					Length_text.alert_length(Ve_So_Do_Activity.this,
							dialog_GC.max_length);
				}

			}
		});
		WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
		lp.copyFrom(dialog_GC.getWindow().getAttributes());
		lp.width = Tht_Screen.get_screen_width_percent(Ve_So_Do_Activity.this,
				90);
		lp.height = Tht_Screen.get_screen_heigth_percent(
				Ve_So_Do_Activity.this, 90);
		dialog_GC.getWindow().setAttributes(lp);
	}

	public void SHOW_DIALOG_MAU() {

		dialog_MAU = new Dialog_MAU(Ve_So_Do_Activity.this);
		dialog_MAU.show();
		WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
		lp.copyFrom(dialog_MAU.getWindow().getAttributes());
		lp.width = w;
		lp.height = h;
		dialog_MAU.getWindow().setAttributes(lp);

		ARR_MCV = DBAdapter.get_list_MAU_CANVAS(MA_DVIQLY);
		final Lst_MAU_CANVAS Madapter_MCV = new Lst_MAU_CANVAS(ARR_MCV, this,
				mdb);
		Dialog_MAU.edt_search.setText(Variables.HSCT_CHON.getTENTRAM_BIENAP());
		Dialog_MAU.edt_search.setSelection(Dialog_MAU.edt_search.length());
		Madapter_MCV.get_SEARCH_MAU(Dialog_MAU.edt_search.getText().toString());
		Dialog_MAU.lv_MAU_CANVAS.setAdapter(Madapter_MCV);
		Dialog_MAU.edt_search.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				Madapter_MCV.get_SEARCH_MAU(Dialog_MAU.edt_search.getText()
						.toString());

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub

			}
		});
		Dialog_MAU.lv_MAU_CANVAS
				.setOnItemClickListener(new OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {

					}
				});
	}

	public void VE_MAU(String TEN_MAU) {
		try {
			List<Obj_MAU_CANVAS> ARR_M = mdb.get_PAINT_OF_MAU(TEN_MAU);
			if (ARR_M.size() > 0) {
				drawView.setErase(false);
				for (int i = 0; i < ARR_M.size(); i++) {
					Obj_MAU_CANVAS HD = ARR_M.get(i);
					Float x1 = HD.X1;
					Float y1 = HD.Y1;
					Float x3 = HD.X3;
					Float y3 = HD.Y3;
					String TEN = HD.CHU;
					int LENH = HD.LENH;

					// luu lai
					Obj_CANVAS CV = new Obj_CANVAS(MA_DVIQLY, MA_YCAU_KNAI, 0,
							TEN, LENH, x1, y1, 0, 0, x3, y3, 0, 0);
					DBAdapter.Insert_HSO_CANVAS(CV);

				}
				list_canvas = mdb.get_list_HSO_CANVAS(MA_YCAU_KNAI);
				mAdapter = new Lst_HSO_CANVAS(list_canvas,
						Ve_So_Do_Activity.this, MA_YCAU_KNAI);
				lv_canvas.setAdapter(mAdapter);
				if (list_canvas.size() > 0) {
					LENH_VE = Variables.KO_VE;
					drawView.setErase(false);
					drawView.VE_LAI();
					VE_LAI_TAT_CA();
				}
				dialog_MAU.dismiss();
			}
		} catch (Exception e) {
			Custom_Toast.show_red_toast(Ve_So_Do_Activity.this,
					"Xảy ra lỗi \n" + e.toString());
		}
	}

	public void SHOW_DIALOG_DUONG() {

		final Dialog_DUONG dialog_duong = new Dialog_DUONG(
				Ve_So_Do_Activity.this);
		dialog_duong.show();
		WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
		lp.copyFrom(dialog_duong.getWindow().getAttributes());
		lp.width = w;
		lp.height = h;
		dialog_duong.getWindow().setAttributes(lp);

		Dialog_DUONG.btn_Xong.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				dialog_duong.dismiss();
			}
		});
		Dialog_DUONG.btn_Dong.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				dialog_duong.dismiss();

			}
		});
		Dialog_DUONG.iv_MAU1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				VE_DUONG_1();
				dialog_duong.dismiss();
			}
		});

	}

	public void SHOW_DIALOG_NEN() {

		dialog_nen.show();
		WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
		lp.copyFrom(dialog_nen.getWindow().getAttributes());
		lp.width = w;
		lp.height = h;
		dialog_nen.getWindow().setAttributes(lp);

		List<Obj_HSO_HINH> ARR_H = mdb.get_HSO_HINH_BY_MA_YCAU_KNAI(
				Variables.HSCT_CHON.MA_YCAU_KNAI, "BD");
		Lst_NEN madapter_H = new Lst_NEN(ARR_H, Ve_So_Do_Activity.this);
		Dialog_NEN.lv_NEN.setAdapter(madapter_H);

	}

}
