package hnks.kitsoft.vn.vesodo;

import java.util.List;

import com.thtsoftlib.function.ThtCanvas;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import hnks.kitsoft.utils.Variables;
import hnks.kitsoft.vn.R;
import hnks.kitsoft.vn.object.Obj_CANVAS;

public class Function_ve {

	// ve vong thang
	public static void VE_DUONG_THANG(Canvas can, float x1, float y1, float x2,
			float y2, String T, Paint paint, Paint Nen_Paint, Paint TextPaint) {
		can.drawLine(x1, y1, x2, y2, paint);
		if (Math.abs(y2 - y1) > Math.abs(x2 - x1)) {
			VE_TEXT_RED(can, T, (x1 + x2) / 2 + (T.length() * 4),
					(y1 + y2) / 2, Nen_Paint, TextPaint);
		} else {
			VE_TEXT_RED(can, T, (x1 + x2) / 2 - (T.length() * 6),
					(y1 + y2) / 2 - 15, Nen_Paint, TextPaint);
		}
	}

	public static void VE_DUONG_DUT_NET(Canvas can, float x1, float y1,
			float x2, float y2, String T, Paint Dased_Paint, Paint Nen_Paint,
			Paint TextPaint) {
		can.drawLine(x1, y1, x2, y2, Dased_Paint);
		if (Math.abs(y2 - y1) > Math.abs(x2 - x1)) {
			VE_TEXT_RED(can, T, (x1 + x2) / 2 + (T.length() * 4),
					(y1 + y2) / 2, Nen_Paint, TextPaint);
		} else {
			VE_TEXT_RED(can, T, (x1 + x2) / 2 - (T.length() * 6),
					(y1 + y2) / 2 - 15, Nen_Paint, TextPaint);
		}
	}

	public static void VE_2_DUONG_THANG(Canvas can, float x1, float y1,
			float x3, float y3, Paint drawPaint, float DO_LECH_CHUAN) {
		if ((Math.abs(y3 - y1)) < (Math.abs(x3 - x1))) {
			can.drawLine(x1, y1 - 15, x3, y3 - 15, drawPaint);
			can.drawLine(x1, y1 + 15, x3, y3 + 15, drawPaint);
		} else {
			can.drawLine(x1 - 15, y1, x3 - 15, y3, drawPaint);
			can.drawLine(x1 + 15, y1, x3 + 15, y3, drawPaint);
		}
	}

	public static void VE_CAU(Canvas can, Float x1, Float y1, String T,
			Context ctx, Paint Nen_Paint, Paint TextPaint) {
		VE_HINH(can, x1, y1, R.drawable.cau_bontam, ctx);
		Bitmap icon = BitmapFactory.decodeResource(ctx.getResources(),
				R.drawable.cau_bontam);
		VE_TEXT(can, T, x1 - (T.length() * Variables.size_character), y1
				- (icon.getHeight() / 2) - Variables.size_padding_text,
				Nen_Paint, TextPaint);
	}

	public static void VE_DUONG_TRON_FILL(Canvas can, Float x1, Float y1,
			int DO_LON_TRON, Paint TextPaint) {
		try {
			can.drawCircle(x1, y1, DO_LON_TRON, TextPaint);
		} catch (Exception e) {
			
		}
		

	}

	public static void VE_TEXT(Canvas can, String KC, float x3, float y3,
			Paint Nen_Paint, Paint TextPaint) {
//		can.drawRect(x3, y3 - 20, x3 + (KC.length() * 12), y3 + 5, Nen_Paint);
		try {
			can.drawText(KC, x3, y3, TextPaint);
		} catch (Exception e) {
			// TODO: handle exception
		}
		

	}

	public static void VE_TEXT_RED(Canvas can, String KC, float x3, float y3,
			Paint Nen_Paint, Paint TextPaint) {
//		can.drawRect(x3, y3 - 20, x3 + (KC.length() * 12), y3 + 5, Nen_Paint);
		try {
			can.drawText(KC, x3, y3, TextPaint);
		} catch (Exception e) {
			
		}
		

	}

	public static void VE_DANH_DAU(Canvas drawCanvas, float x1, float y1,
			float x3, float y3, int LENH, Paint Paint_DANH_DAU) {
		if (LENH == Variables.VE_2LINE || LENH == Variables.VE_LINE_DUT_NET
				|| LENH == Variables.VE_DUONG_THANG
				|| LENH == Variables.VE_SONG) {
			drawCanvas.drawLine(x1, y1, x3, y3, Paint_DANH_DAU);
		} else {

			drawCanvas.drawCircle(x3, y3, 5, Paint_DANH_DAU);
		}

	}

	// ve tram
	public static void VE_TRAM(Canvas can, float x1, float y1,
			String T, Context ctx, Paint Nen_Paint, Paint TextPaint,int draw) {
		try {
			VE_HINH(can, x1, y1, draw, ctx);
			Bitmap icon = BitmapFactory.decodeResource(ctx.getResources(),
					R.drawable.cv_tram_1pha_hhuu);
			VE_TEXT(can, T, x1 - (T.length() * Variables.size_character), y1
					- (icon.getHeight() / 2) - Variables.size_padding_text,
					Nen_Paint, TextPaint);
		} catch (Exception e) {
			
		}
		
	}
	public static void VE_TRAM_1_PHA_HHUU(Canvas can, float x1, float y1,
			String T, Context ctx, Paint Nen_Paint, Paint TextPaint) {
		VE_HINH(can, x1, y1, R.drawable.cv_tram_1pha_hhuu, ctx);
		Bitmap icon = BitmapFactory.decodeResource(ctx.getResources(),
				R.drawable.cv_tram_1pha_hhuu);
		VE_TEXT(can, T, x1 - (T.length() * Variables.size_character), y1
				- (icon.getHeight() / 2) - Variables.size_padding_text,
				Nen_Paint, TextPaint);
	}

	public static void VE_TRAM_1_PHA_SLAP(Canvas can, float x1, float y1,
			String T, Context ctx, Paint Nen_Paint, Paint TextPaint) {
		VE_HINH(can, x1, y1, R.drawable.cv_tram_1pha_slap, ctx);
		Bitmap icon = BitmapFactory.decodeResource(ctx.getResources(),
				R.drawable.cv_tram_1pha_slap);
		VE_TEXT(can, T, x1 - (T.length() * Variables.size_character), y1
				- (icon.getHeight() / 2) - Variables.size_padding_text,
				Nen_Paint, TextPaint);
	}

	public static void VE_TRAM_3_PHA_HHUU(Canvas can, float x1, float y1,
			String T, Context ctx, Paint Nen_Paint, Paint TextPaint) {
		VE_HINH(can, x1, y1, R.drawable.cv_tram_3pha_hhuu_48, ctx);
		Bitmap icon = BitmapFactory.decodeResource(ctx.getResources(),
				R.drawable.cv_tram_3pha_hhuu_48);
		VE_TEXT(can, T, x1 - (T.length() * Variables.size_character), y1
				- (icon.getHeight() / 2) - Variables.size_padding_text,
				Nen_Paint, TextPaint);
	}
	public static void VE_TRAM_3_PHA_SLAP(Canvas can, float x1, float y1,
			String T, Context ctx, Paint Nen_Paint, Paint TextPaint) {
		VE_HINH(can, x1, y1, R.drawable.cv_tram_3pha_slap_48, ctx);
		Bitmap icon = BitmapFactory.decodeResource(ctx.getResources(),
				R.drawable.cv_tram_3pha_slap_48);
		VE_TEXT(can, T, x1 - (T.length() * Variables.size_character), y1
				- (icon.getHeight() / 2) - Variables.size_padding_text,
				Nen_Paint, TextPaint);
	}

	public static void VE_TRAM_3_PHA_NEN_HHUU(Canvas can, float x1, float y1,
			String T, Context ctx, Paint Nen_Paint, Paint TextPaint) {
		VE_HINH(can, x1, y1, R.drawable.cv_tram_3pha_nen_hhuu_48, ctx);
		Bitmap icon = BitmapFactory.decodeResource(ctx.getResources(),
				R.drawable.cv_tram_3pha_nen_hhuu_48);
		VE_TEXT(can, T, x1 - (T.length() * Variables.size_character), y1
				- (icon.getHeight() / 2) - Variables.size_padding_text,
				Nen_Paint, TextPaint);
	}

	public static void VE_TRAM_3_PHA_NEN_SLAP(Canvas can, float x1, float y1,
			String T, Context ctx, Paint Nen_Paint, Paint TextPaint) {
		VE_HINH(can, x1, y1, R.drawable.cv_tram_3pha_nen_slap_48, ctx);
		Bitmap icon = BitmapFactory.decodeResource(ctx.getResources(),
				R.drawable.cv_tram_3pha_nen_slap_48);
		VE_TEXT(can, T, x1 - (T.length() * Variables.size_character), y1
				- (icon.getHeight() / 2) - Variables.size_padding_text,
				Nen_Paint, TextPaint);
	}

	public static void VE_TRAM_3_PHA_NHA_HHUU(Canvas can, float x1, float y1,
			String T, Context ctx, Paint Nen_Paint, Paint TextPaint) {
		VE_HINH(can, x1, y1, R.drawable.cv_tram_3pha_nha_hhuu_48, ctx);
		Bitmap icon = BitmapFactory.decodeResource(ctx.getResources(),
				R.drawable.cv_tram_3pha_nha_hhuu_48);
		VE_TEXT(can, T, x1 - (T.length() * Variables.size_character), y1
				- (icon.getHeight() / 2) - Variables.size_padding_text,
				Nen_Paint, TextPaint);
	}

	public static void VE_TRAM_3_PHA_NHA_SLAP(Canvas can, float x1, float y1,
			String T, Context ctx, Paint Nen_Paint, Paint TextPaint) {
		VE_HINH(can, x1, y1, R.drawable.cv_tram_3pha_nha_slap_48, ctx);
		Bitmap icon = BitmapFactory.decodeResource(ctx.getResources(),
				R.drawable.cv_tram_3pha_nha_slap_48);
		VE_TEXT(can, T, x1 - (T.length() * Variables.size_character), y1
				- (icon.getHeight() / 2) - Variables.size_padding_text,
				Nen_Paint, TextPaint);
	}
	
	// thap sat
	//thap sat 10
	public static void VE_THAPSAT_10_HHUU(Canvas can, float x1, float y1,
			String T, Paint Nen_Paint, Paint TextPaint, Context ctx) {
		VE_HINH(can, x1, y1, R.drawable.cv_thapsat_10_hhuu, ctx);
		Bitmap icon = BitmapFactory.decodeResource(ctx.getResources(),
				R.drawable.cv_thapsat_10_hhuu);
		VE_TEXT(can, T, x1 - (T.length() * Variables.size_character), y1
				- (icon.getHeight() / 2) - Variables.size_padding_text,
				Nen_Paint, TextPaint);
	}

	public static void VE_THAPSAT_10_SLAP(Canvas can, float x1, float y1,
			String T, Paint Nen_Paint, Paint TextPaint, Context ctx) {
		VE_HINH(can, x1, y1, R.drawable.cv_thapsat_10_slap, ctx);
		Bitmap icon = BitmapFactory.decodeResource(ctx.getResources(),
				R.drawable.cv_thapsat_10_slap);
		VE_TEXT(can, T, x1 - (T.length() * Variables.size_character), y1
				- (icon.getHeight() / 2) - Variables.size_padding_text,
				Nen_Paint, TextPaint);
	}
	// thap sat 12
	public static void VE_THAPSAT_12_HHUU(Canvas can, float x1, float y1,
			String T, Paint Nen_Paint, Paint TextPaint, Context ctx) {
		VE_HINH(can, x1, y1, R.drawable.cv_thapsat_12m_hhuu, ctx);
		Bitmap icon = BitmapFactory.decodeResource(ctx.getResources(),
				R.drawable.cv_thapsat_12m_hhuu);
		VE_TEXT(can, T, x1 - (T.length() * Variables.size_character), y1
				- (icon.getHeight() / 2) - Variables.size_padding_text,
				Nen_Paint, TextPaint);
	}

	public static void VE_THAPSAT_12_SLAP(Canvas can, float x1, float y1,
			String T, Paint Nen_Paint, Paint TextPaint, Context ctx) {
		VE_HINH(can, x1, y1, R.drawable.cv_thapsat_12m_slap, ctx);
		Bitmap icon = BitmapFactory.decodeResource(ctx.getResources(),
				R.drawable.cv_thapsat_12m_slap);
		VE_TEXT(can, T, x1 - (T.length() * Variables.size_character), y1
				- (icon.getHeight() / 2) - Variables.size_padding_text,
				Nen_Paint, TextPaint);
	}
	// ve tru
	// btlt 12
	public static void VE_TRU_BTLT_12_HHUU(Canvas can, float x1, float y1,
			String T, Paint Nen_Paint, Paint TextPaint, Context ctx) {
		VE_HINH(can, x1, y1, R.drawable.cv_tru_btlt_12_hhuu, ctx);
		Bitmap icon = BitmapFactory.decodeResource(ctx.getResources(),
				R.drawable.cv_tru_btlt_12_hhuu);
		VE_TEXT(can, T, x1 - (T.length() * Variables.size_character), y1
				- (icon.getHeight() / 2) - Variables.size_padding_text,
				Nen_Paint, TextPaint);
	}

	public static void VE_TRU_BTLT_12_SLAP(Canvas can, float x1, float y1,
			String T, Paint Nen_Paint, Paint TextPaint, Context ctx) {
		VE_HINH(can, x1, y1, R.drawable.cv_tru_btlt_12_slap, ctx);
		Bitmap icon = BitmapFactory.decodeResource(ctx.getResources(),
				R.drawable.cv_tru_btlt_12_slap);
		VE_TEXT(can, T, x1 - (T.length() * Variables.size_character), y1
				- (icon.getHeight() / 2) - Variables.size_padding_text,
				Nen_Paint, TextPaint);
	}

	// btlt 14
	public static void VE_TRU_BTLT_14_HHUU(Canvas can, float x1, float y1,
			String T, Paint Nen_Paint, Paint TextPaint, Context ctx) {
		VE_HINH(can, x1, y1, R.drawable.cv_tru_btlt_14_hhuu, ctx);
		Bitmap icon = BitmapFactory.decodeResource(ctx.getResources(),
				R.drawable.cv_tru_btlt_14_hhuu);
		VE_TEXT(can, T, x1 - (T.length() * Variables.size_character), y1
				- (icon.getHeight() / 2) - Variables.size_padding_text,
				Nen_Paint, TextPaint);
	}

	public static void VE_TRU_BTLT_14_SLAP(Canvas can, float x1, float y1,
			String T, Paint Nen_Paint, Paint TextPaint, Context ctx) {
		VE_HINH(can, x1, y1, R.drawable.cv_tru_btlt_14_slap, ctx);
		Bitmap icon = BitmapFactory.decodeResource(ctx.getResources(),
				R.drawable.cv_tru_btlt_14_slap);
		VE_TEXT(can, T, x1 - (T.length() * Variables.size_character), y1
				- (icon.getHeight() / 2) - Variables.size_padding_text,
				Nen_Paint, TextPaint);
	}

	// btlt 20
	public static void VE_TRU_BTLT_20_HHUU(Canvas can, float x1, float y1,
			String T, Paint Nen_Paint, Paint TextPaint, Context ctx) {
		VE_HINH(can, x1, y1, R.drawable.cv_tru_btlt_20_hhuu, ctx);
		Bitmap icon = BitmapFactory.decodeResource(ctx.getResources(),
				R.drawable.cv_tru_btlt_20_hhuu);
		VE_TEXT(can, T, x1 - (T.length() * Variables.size_character), y1
				- (icon.getHeight() / 2) - Variables.size_padding_text,
				Nen_Paint, TextPaint);
	}

	public static void VE_TRU_BTLT_20_SLAP(Canvas can, float x1, float y1,
			String T, Paint Nen_Paint, Paint TextPaint, Context ctx) {
		VE_HINH(can, x1, y1, R.drawable.cv_tru_btlt_20_slap, ctx);
		Bitmap icon = BitmapFactory.decodeResource(ctx.getResources(),
				R.drawable.cv_tru_btlt_20_slap);
		VE_TEXT(can, T, x1 - (T.length() * Variables.size_character), y1
				- (icon.getHeight() / 2) - Variables.size_padding_text,
				Nen_Paint, TextPaint);
	}

	// btlt 75
	public static void VE_TRU_BTLT_75_HHUU(Canvas can, float x1, float y1,
			String T, Paint Nen_Paint, Paint TextPaint, Context ctx) {
		VE_HINH(can, x1, y1, R.drawable.cv_tru_btlt_75_hhuu, ctx);
		Bitmap icon = BitmapFactory.decodeResource(ctx.getResources(),
				R.drawable.cv_tru_btlt_75_hhuu);
		VE_TEXT(can, T, x1 - (T.length() * Variables.size_character), y1
				- (icon.getHeight() / 2) - Variables.size_padding_text,
				Nen_Paint, TextPaint);
	}

	public static void VE_TRU_BTLT_75_SLAP(Canvas can, float x1, float y1,
			String T, Paint Nen_Paint, Paint TextPaint, Context ctx) {
		VE_HINH(can, x1, y1, R.drawable.cv_tru_btlt_75_slap, ctx);
		Bitmap icon = BitmapFactory.decodeResource(ctx.getResources(),
				R.drawable.cv_tru_btlt_75_slap);
		VE_TEXT(can, T, x1 - (T.length() * Variables.size_character), y1
				- (icon.getHeight() / 2) - Variables.size_padding_text,
				Nen_Paint, TextPaint);
	}

	// btlt 85
	public static void VE_TRU_BTLT_85_HHUU(Canvas can, float x1, float y1,
			String T, Paint Nen_Paint, Paint TextPaint, Context ctx) {
		VE_HINH(can, x1, y1, R.drawable.cv_tru_btlt_85_hhuu, ctx);
		Bitmap icon = BitmapFactory.decodeResource(ctx.getResources(),
				R.drawable.cv_tru_btlt_85_hhuu);
		VE_TEXT(can, T, x1 - (T.length() * Variables.size_character), y1
				- (icon.getHeight() / 2) - Variables.size_padding_text,
				Nen_Paint, TextPaint);
	}

	public static void VE_TRU_BTLT_85_SLAP(Canvas can, float x1, float y1,
			String T, Paint Nen_Paint, Paint TextPaint, Context ctx) {
		VE_HINH(can, x1, y1, R.drawable.cv_tru_btlt_85_slap, ctx);
		Bitmap icon = BitmapFactory.decodeResource(ctx.getResources(),
				R.drawable.cv_tru_btlt_85_slap);
		VE_TEXT(can, T, x1 - (T.length() * Variables.size_character), y1
				- (icon.getHeight() / 2) - Variables.size_padding_text,
				Nen_Paint, TextPaint);
	}

	// btlt 105
	public static void VE_TRU_BTLT_105_HHUU(Canvas can, float x1, float y1,
			String T, Paint Nen_Paint, Paint TextPaint, Context ctx) {
		VE_HINH(can, x1, y1, R.drawable.cv_tru_btlt_105_hhuu, ctx);
		Bitmap icon = BitmapFactory.decodeResource(ctx.getResources(),
				R.drawable.cv_tru_btlt_105_hhuu);
		VE_TEXT(can, T, x1 - (T.length() * Variables.size_character), y1
				- (icon.getHeight() / 2) - Variables.size_padding_text,
				Nen_Paint, TextPaint);
	}

	public static void VE_TRU_BTLT_105_SLAP(Canvas can, float x1, float y1,
			String T, Paint Nen_Paint, Paint TextPaint, Context ctx) {
		VE_HINH(can, x1, y1, R.drawable.cv_tru_btlt_105_slap, ctx);
		Bitmap icon = BitmapFactory.decodeResource(ctx.getResources(),
				R.drawable.cv_tru_btlt_105_slap);
		VE_TEXT(can, T, x1 - (T.length() * Variables.size_character), y1
				- (icon.getHeight() / 2) - Variables.size_padding_text,
				Nen_Paint, TextPaint);
	}

	// tru btv 11
	public static void VE_TRU_BTV_11_HHUU(Canvas can, float x1, float y1,
			String T, Paint Nen_Paint, Paint TextPaint, Context ctx) {
		VE_HINH(can, x1, y1, R.drawable.cv_tru_btv_11_hhuu, ctx);
		Bitmap icon = BitmapFactory.decodeResource(ctx.getResources(),
				R.drawable.cv_tru_btv_11_hhuu);
		VE_TEXT(can, T, x1 - (T.length() * Variables.size_character), y1
				- (icon.getHeight() / 2) - Variables.size_padding_text,
				Nen_Paint, TextPaint);
	}

	public static void VE_TRU_BTV_11_SLAP(Canvas can, float x1, float y1,
			String T, Paint Nen_Paint, Paint TextPaint, Context ctx) {
		VE_HINH(can, x1, y1, R.drawable.cv_tru_btv_11_slap, ctx);
		Bitmap icon = BitmapFactory.decodeResource(ctx.getResources(),
				R.drawable.cv_tru_btv_11_slap);
		VE_TEXT(can, T, x1 - (T.length() * Variables.size_character), y1
				- (icon.getHeight() / 2) - Variables.size_padding_text,
				Nen_Paint, TextPaint);
	}

	// tru btv 65
	public static void VE_TRU_BTV_65_HHUU(Canvas can, float x1, float y1,
			String T, Paint Nen_Paint, Paint TextPaint, Context ctx) {
		VE_HINH(can, x1, y1, R.drawable.cv_tru_btv_65_hhuu, ctx);
		Bitmap icon = BitmapFactory.decodeResource(ctx.getResources(),
				R.drawable.cv_tru_btv_65_hhuu);
		VE_TEXT(can, T, x1 - (T.length() * Variables.size_character), y1
				- (icon.getHeight() / 2) - Variables.size_padding_text,
				Nen_Paint, TextPaint);
	}

	public static void VE_TRU_BTV_65_SLAP(Canvas can, float x1, float y1,
			String T, Paint Nen_Paint, Paint TextPaint, Context ctx) {
		VE_HINH(can, x1, y1, R.drawable.cv_tru_btv_65_slap, ctx);
		Bitmap icon = BitmapFactory.decodeResource(ctx.getResources(),
				R.drawable.cv_tru_btv_65_slap);
		VE_TEXT(can, T, x1 - (T.length() * Variables.size_character), y1
				- (icon.getHeight() / 2) - Variables.size_padding_text,
				Nen_Paint, TextPaint);
	}

	// tru btv 75
	public static void VE_TRU_BTV_75_HHUU(Canvas can, float x1, float y1,
			String T, Paint Nen_Paint, Paint TextPaint, Context ctx) {
		VE_HINH(can, x1, y1, R.drawable.cv_tru_btv_75_hhuu, ctx);
		Bitmap icon = BitmapFactory.decodeResource(ctx.getResources(),
				R.drawable.cv_tru_btv_75_hhuu);
		VE_TEXT(can, T, x1 - (T.length() * Variables.size_character), y1
				- (icon.getHeight() / 2) - Variables.size_padding_text,
				Nen_Paint, TextPaint);
	}

	public static void VE_TRU_BTV_75_SLAP(Canvas can, float x1, float y1,
			String T, Paint Nen_Paint, Paint TextPaint, Context ctx) {
		VE_HINH(can, x1, y1, R.drawable.cv_tru_btv_75_slap, ctx);
		Bitmap icon = BitmapFactory.decodeResource(ctx.getResources(),
				R.drawable.cv_tru_btv_75_slap);
		VE_TEXT(can, T, x1 - (T.length() * Variables.size_character), y1
				- (icon.getHeight() / 2) - Variables.size_padding_text,
				Nen_Paint, TextPaint);
	}

	// tru btv 85
	public static void VE_TRU_BTV_85_HHUU(Canvas can, float x1, float y1,
			String T, Paint Nen_Paint, Paint TextPaint, Context ctx) {
		VE_HINH(can, x1, y1, R.drawable.cv_tru_btv_85_hhuu, ctx);
		Bitmap icon = BitmapFactory.decodeResource(ctx.getResources(),
				R.drawable.cv_tru_btv_85_hhuu);
		VE_TEXT(can, T, x1 - (T.length() * Variables.size_character), y1
				- (icon.getHeight() / 2) - Variables.size_padding_text,
				Nen_Paint, TextPaint);
	}

	public static void VE_TRU_BTV_85_SLAP(Canvas can, float x1, float y1,
			String T, Paint Nen_Paint, Paint TextPaint, Context ctx) {
		VE_HINH(can, x1, y1, R.drawable.cv_tru_btv_85_slap, ctx);
		Bitmap icon = BitmapFactory.decodeResource(ctx.getResources(),
				R.drawable.cv_tru_btv_85_slap);
		VE_TEXT(can, T, x1 - (T.length() * Variables.size_character), y1
				- (icon.getHeight() / 2) - Variables.size_padding_text,
				Nen_Paint, TextPaint);
	}

	// tru go
	public static void VE_TRU_GO_HHUU(Canvas can, float x1, float y1, String T,
			Paint Nen_Paint, Paint TextPaint, Context ctx) {
		VE_HINH(can, x1, y1, R.drawable.cv_tru_go_hhuu, ctx);
		Bitmap icon = BitmapFactory.decodeResource(ctx.getResources(),
				R.drawable.cv_tru_go_hhuu);
		VE_TEXT(can, T, x1 - (T.length() * Variables.size_character), y1
				- (icon.getHeight() / 2) - Variables.size_padding_text,
				Nen_Paint, TextPaint);
	}

	public static void VE_TRU_GO_SLAP(Canvas can, float x1, float y1, String T,
			Paint Nen_Paint, Paint TextPaint, Context ctx) {
		VE_HINH(can, x1, y1, R.drawable.cv_tru_go_slap, ctx);
		Bitmap icon = BitmapFactory.decodeResource(ctx.getResources(),
				R.drawable.cv_tru_go_slap);
		VE_TEXT(can, T, x1 - (T.length() * Variables.size_character), y1
				- (icon.getHeight() / 2) - Variables.size_padding_text,
				Nen_Paint, TextPaint);
	}

	// tru nhom
	public static void VE_TRU_NHOM_HHUU(Canvas can, float x1, float y1,
			String T, Paint Nen_Paint, Paint TextPaint, Context ctx) {
		VE_HINH(can, x1, y1, R.drawable.cv_tru_nhom_hhuu, ctx);
		Bitmap icon = BitmapFactory.decodeResource(ctx.getResources(),
				R.drawable.cv_tru_nhom_hhuu);
		VE_TEXT(can, T, x1 - (T.length() * Variables.size_character), y1
				- (icon.getHeight() / 2) - Variables.size_padding_text,
				Nen_Paint, TextPaint);
	}

	public static void VE_TRU_NHOM_SLAP(Canvas can, float x1, float y1,
			String T, Paint Nen_Paint, Paint TextPaint, Context ctx) {
		VE_HINH(can, x1, y1, R.drawable.cv_tru_nhom_slap, ctx);
		Bitmap icon = BitmapFactory.decodeResource(ctx.getResources(),
				R.drawable.cv_tru_nhom_slap);
		VE_TEXT(can, T, x1 - (T.length() * Variables.size_character), y1
				- (icon.getHeight() / 2) - Variables.size_padding_text,
				Nen_Paint, TextPaint);
	}
	/*
	 * 
	 * Cong to treo
	 */
	
	public static void VE_CONGTO_TREO_NHA(Canvas can, Float x1, Float y1, Context ctx) {
		VE_HINH(can, x1, y1, R.drawable.cv_congto_treo_nha_32, ctx);

	}
	public static void VE_CONGTO_TREO_TRU_BTLT_105_HHUU(Canvas can, Float x1, Float y1, Context ctx) {
		VE_HINH(can, x1, y1, R.drawable.cv_congto_treo_tru_btlt_105_hhuu, ctx);

	}
	public static void VE_CONGTO_TREO_TRU_BTLT_105_SLAP(Canvas can, Float x1, Float y1, Context ctx) {
		VE_HINH(can, x1, y1, R.drawable.cv_congto_treo_tru_btlt_105_slap, ctx);

	}
	public static void VE_CONGTO_TREO_TRU_BTLT_12_HHUU(Canvas can, Float x1, Float y1, Context ctx) {
		VE_HINH(can, x1, y1, R.drawable.cv_congto_treo_tru_bttl_12_32, ctx);

	}
	public static void VE_CONGTO_TREO_TRU_BTV_65_HHUU(Canvas can, Float x1, Float y1, Context ctx) {
		VE_HINH(can, x1, y1, R.drawable.cv_congto_treo_tru_btv_65_hhuu, ctx);

	}
	public static void VE_CONGTO_TREO_TRU_BTV_65_SLAP(Canvas can, Float x1, Float y1, Context ctx) {
		VE_HINH(can, x1, y1, R.drawable.cv_congto_treo_tru_btv_65_slap, ctx);

	}

	// ve nha
	public static void VE_NHA(Canvas can, Float x1, Float y1, Context ctx) {
		VE_HINH(can, x1, y1, R.drawable.cv_nha_48, ctx);

	}

	// ve hinh z ngang
	public static void VE_HINH_Z_NGANG(Canvas can, Float x1, Float y1,
			Context ctx) {
		VE_HINH(can, x1, y1, R.drawable.z_ngang_bontam, ctx);

	}

	// VE_HINH_Z_NGANG_DUT
	public static void VE_HINH_Z_NGANG_DUT(Canvas can, Float x1, Float y1,
			Context ctx) {
		VE_HINH(can, x1, y1, R.drawable.dashed_z, ctx);

	}

	// ve cong to
	public static void VE_TREO_CONGTO(Canvas can, float x1, float y1,
			Context ctx) {
		VE_HINH(can, x1, y1, R.drawable.cv_cong_to_32, ctx);
	}

	// ve khu dan cu
	public static void VE_KDT(Canvas can, float x1, float y1, Context ctx) {
		VE_HINH(can, x1, y1, R.drawable.khu_dat_trong, ctx);
	}
	
	// ve COT
	public static void VE_COT(Canvas can, float x1, float y1, Context ctx) {
		VE_HINH(can, x1, y1, R.drawable.ic_cot, ctx);
	}
	// ve cot ngang
	public static void VE_COT_NGANG(Canvas can, float x1, float y1, Context ctx) {
		VE_HINH(can, x1, y1, R.drawable.ic_cot_ngang, ctx);
	}
	// ve hop
	public static void VE_HOP(Canvas can, float x1, float y1, Context ctx) {
		VE_HINH(can, x1, y1, R.drawable.ic_hop, ctx);
	}
	// ve hop ngang
	public static void VE_HOP_NGANG(Canvas can, float x1, float y1, Context ctx) {
		VE_HINH(can, x1, y1, R.drawable.ic_hop_ngang, ctx);
	}

	// ve nen trang
	public static void VE_NEN_TRANG(Canvas can, float x1, float y1, float x2,
			float y2, Paint Nen_Paint) {
		can.drawRect(x1, y1, x2, y2, Nen_Paint);
	}

	public static void VE_HINH(Canvas can, float x3, float y3, int draw,
			Context ctx) {
		BitmapFactory.Options opts = new BitmapFactory.Options();
		opts.inJustDecodeBounds = true;
		Bitmap img = BitmapFactory.decodeResource(ctx.getResources(), draw);
		can.drawBitmap(img, x3 - (float) img.getWidth() / 2,
				y3 - (float) img.getHeight() / 2, null);
		can.drawBitmap(img, x3 - (float) img.getWidth() / 2,
				y3 - (float) img.getHeight() / 2, null);

	}

	// ve lai tat ca
	public static void VE_HINH_THEO_LENH(Context ctx, Canvas canvas, int LENH,
			float x1, float y1, float x3, float y3, String TEN,
			Paint Nen_Paint, Paint TextPaint) {
		if (LENH == Variables.VE_HINH_Z_NGANG) {
			VE_HINH_Z_NGANG(canvas, x3, y3, ctx);
		} else if (LENH == Variables.VE_HINH_Z_NGANG_DUT) {
			VE_HINH_Z_NGANG_DUT(canvas, x3, y3, ctx);
		}
		// tram
		else if (LENH == Variables.VE_TRAM_1PHA_HHUU) {
			VE_TRAM_1_PHA_HHUU(canvas, x3, y3, TEN, ctx, Nen_Paint, TextPaint);
		}
		else if (LENH == Variables.VE_TRAM_1PHA_SLAP) {
			VE_TRAM_1_PHA_SLAP(canvas, x3, y3, TEN, ctx, Nen_Paint, TextPaint);
		}
		else if (LENH == Variables.VE_TRAM_3PHA_HHUU) {
			VE_TRAM_3_PHA_HHUU(canvas, x3, y3, TEN, ctx, Nen_Paint, TextPaint);
		}
		else if (LENH == Variables.VE_TRAM_3PHA_SLAP) {
			VE_TRAM_3_PHA_SLAP(canvas, x3, y3, TEN, ctx, Nen_Paint, TextPaint);
		}
		else if (LENH == Variables.VE_TRAM_3PHA_NEN_HHUU) {
			VE_TRAM_3_PHA_NEN_HHUU(canvas, x3, y3, TEN, ctx, Nen_Paint, TextPaint);
		}
		else if (LENH == Variables.VE_TRAM_3PHA_NEN_SLAP) {
			VE_TRAM_3_PHA_NEN_SLAP(canvas, x3, y3, TEN, ctx, Nen_Paint, TextPaint);
		}
		else if (LENH == Variables.VE_TRAM_3PHA_NHA_HHUU) {
			VE_TRAM_3_PHA_NHA_HHUU(canvas, x3, y3, TEN, ctx, Nen_Paint, TextPaint);
		}
		else if (LENH == Variables.VE_TRAM_3PHA_NHA_SLAP) {
			VE_TRAM_3_PHA_NHA_SLAP(canvas, x3, y3, TEN, ctx, Nen_Paint, TextPaint);
		}
		// thap sat
		else if (LENH == Variables.VE_THAPSAT_10_HHUU) {
			VE_THAPSAT_10_HHUU(canvas, x3, y3, TEN, Nen_Paint, TextPaint, ctx);
		}else if (LENH == Variables.VE_THAPSAT_10_SLAP) {
			VE_THAPSAT_10_SLAP(canvas, x3, y3, TEN, Nen_Paint, TextPaint, ctx);
		}else if (LENH == Variables.VE_THAPSAT_12_HHUU) {
			VE_THAPSAT_12_HHUU(canvas, x3, y3, TEN, Nen_Paint, TextPaint, ctx);
		}else if (LENH == Variables.VE_THAPSAT_12_SLAP) {
			VE_THAPSAT_12_SLAP(canvas, x3, y3, TEN, Nen_Paint, TextPaint, ctx);
		}
		//tru
		else if (LENH == Variables.VE_TRU_BTLT_12_HHUU) {
			VE_TRU_BTLT_12_HHUU(canvas, x3, y3, TEN, Nen_Paint, TextPaint, ctx);
		}
		else if (LENH == Variables.VE_TRU_BTLT_12_SLAP) {
			VE_TRU_BTLT_12_SLAP(canvas, x3, y3, TEN, Nen_Paint, TextPaint, ctx);
		}
		else if (LENH == Variables.VE_TRU_BTLT_14_HHUU) {
			VE_TRU_BTLT_14_HHUU(canvas, x3, y3, TEN, Nen_Paint, TextPaint, ctx);
		}
		else if (LENH == Variables.VE_TRU_BTLT_14_SLAP) {
			VE_TRU_BTLT_14_SLAP(canvas, x3, y3, TEN, Nen_Paint, TextPaint, ctx);
		}
		else if (LENH == Variables.VE_TRU_BTLT_20_HHUU) {
			VE_TRU_BTLT_20_HHUU(canvas, x3, y3, TEN, Nen_Paint, TextPaint, ctx);
		}
		else if (LENH == Variables.VE_TRU_BTLT_20_SLAP) {
			VE_TRU_BTLT_20_SLAP(canvas, x3, y3, TEN, Nen_Paint, TextPaint, ctx);
		}
		else if (LENH == Variables.VE_TRU_BTLT_75_HHUU) {
			VE_TRU_BTLT_75_HHUU(canvas, x3, y3, TEN, Nen_Paint, TextPaint, ctx);
		}
		else if (LENH == Variables.VE_TRU_BTLT_75_SLAP) {
			VE_TRU_BTLT_75_SLAP(canvas, x3, y3, TEN, Nen_Paint, TextPaint, ctx);
		}
		else if (LENH == Variables.VE_TRU_BTLT_85_HHUU) {
			VE_TRU_BTLT_85_HHUU(canvas, x3, y3, TEN, Nen_Paint, TextPaint, ctx);
		}
		else if (LENH == Variables.VE_TRU_BTLT_85_SLAP) {
			VE_TRU_BTLT_85_SLAP(canvas, x3, y3, TEN, Nen_Paint, TextPaint, ctx);
		}
		else if (LENH == Variables.VE_TRU_BTLT_105_HHUU) {
			VE_TRU_BTLT_105_HHUU(canvas, x3, y3, TEN, Nen_Paint, TextPaint, ctx);
		}  else if (LENH == Variables.VE_TRU_BTLT_105_SLAP) {
			VE_TRU_BTLT_105_SLAP(canvas, x3, y3, TEN, Nen_Paint, TextPaint, ctx);
		}
		// btv
		else if (LENH == Variables.VE_TRU_BTV_11_HHUU) {
			VE_TRU_BTV_11_HHUU(canvas, x3, y3, TEN, Nen_Paint, TextPaint, ctx);
		}
		else if (LENH == Variables.VE_TRU_BTV_11_SLAP) {
			VE_TRU_BTV_11_SLAP(canvas, x3, y3, TEN, Nen_Paint, TextPaint, ctx);
		}
		else if (LENH == Variables.VE_TRU_BTV_65_HHUU) {
			VE_TRU_BTV_65_HHUU(canvas, x3, y3, TEN, Nen_Paint, TextPaint, ctx);
		}
		else if (LENH == Variables.VE_TRU_BTV_65_SLAP) {
			VE_TRU_BTV_65_SLAP(canvas, x3, y3, TEN, Nen_Paint, TextPaint, ctx);
		}
		else if (LENH == Variables.VE_TRU_BTV_75_HHUU) {
			VE_TRU_BTV_75_HHUU(canvas, x3, y3, TEN, Nen_Paint, TextPaint, ctx);
		}
		else if (LENH == Variables.VE_TRU_BTV_75_SLAP) {
			VE_TRU_BTV_75_SLAP(canvas, x3, y3, TEN, Nen_Paint, TextPaint, ctx);
		}
		else if (LENH == Variables.VE_TRU_BTV_85_HHUU) {
			VE_TRU_BTV_85_HHUU(canvas, x3, y3, TEN, Nen_Paint, TextPaint, ctx);
		}
		else if (LENH == Variables.VE_TRU_BTV_85_SLAP) {
			VE_TRU_BTV_85_SLAP(canvas, x3, y3, TEN, Nen_Paint, TextPaint, ctx);
		}
		else if (LENH == Variables.VE_TRU_GO_HHUU) {
			VE_TRU_GO_HHUU(canvas, x3, y3, TEN, Nen_Paint, TextPaint, ctx);
		}
		else if (LENH == Variables.VE_TRU_GO_SLAP) {
			VE_TRU_GO_SLAP(canvas, x3, y3, TEN, Nen_Paint, TextPaint, ctx);
		}
		else if (LENH == Variables.VE_TRU_NHOM_HHUU) {
			VE_TRU_NHOM_HHUU(canvas, x3, y3, TEN, Nen_Paint, TextPaint, ctx);
		}
		else if (LENH == Variables.VE_TRU_NHOM_SLAP) {
			VE_TRU_NHOM_SLAP(canvas, x3, y3, TEN, Nen_Paint, TextPaint, ctx);
		}
		// cong to treo
		else if (LENH == Variables.VE_CONGTO_TREO_NHA) {
			VE_CONGTO_TREO_NHA(canvas, x3, y3, ctx);
		}
		else if (LENH == Variables.VE_CONGTO_TREO_TRU_BTLT_105_HHUU) {
			VE_CONGTO_TREO_TRU_BTLT_105_HHUU(canvas, x3, y3, ctx);
		}
		else if (LENH == Variables.VE_CONGTO_TREO_TRU_BTLT_105_SLAP) {
			VE_CONGTO_TREO_TRU_BTLT_105_SLAP(canvas, x3, y3, ctx);
		}
		else if (LENH == Variables.VE_CONGTO_TREO_TRU_BTLT_12_HHUU) {
			VE_CONGTO_TREO_TRU_BTLT_12_HHUU(canvas, x3, y3, ctx);
		}
		else if (LENH == Variables.VE_CONGTO_TREO_TRU_BTV_65_HHUU) {
			VE_CONGTO_TREO_TRU_BTV_65_HHUU(canvas, x3, y3, ctx);
		}
		else if (LENH == Variables.VE_CONGTO_TREO_TRU_BTV_65_SLAP) {
			VE_CONGTO_TREO_TRU_BTV_65_SLAP(canvas, x3, y3, ctx);
		}
		// het tru
		else if (LENH == Variables.VE_NHA) {
			VE_NHA(canvas, x3, y3, ctx);
		} else if (LENH == Variables.VE_CAU) {
			VE_CAU(canvas, x3, y3, TEN, ctx, Nen_Paint, TextPaint);
		} else if (LENH == Variables.VE_HINH_CONGTO) {
			VE_TREO_CONGTO(canvas, x3, y3, ctx);
		} else if (LENH == Variables.VE_KDT) {
			VE_KDT(canvas, x3, y3, ctx);
		}
		// ve ha noi
		else if (LENH == Variables.VE_COT) {
			VE_COT(canvas, x3, y3, ctx);
		}else if (LENH == Variables.VE_COT_NGANG) {
			VE_COT_NGANG(canvas, x3, y3, ctx);
		}else if (LENH == Variables.VE_HOP) {
			VE_HOP(canvas, x3, y3, ctx);
		}else if (LENH == Variables.VE_HOP_NGANG) {
			VE_HOP_NGANG(canvas, x3, y3, ctx);
		}
		
	}

	public static void VE_THEO_LENH(Canvas canvas, int LENH, float x1,
			float y1, float x3, float y3, String TEN, Paint draw_paint,
			Paint Dased_Paint, Paint Nen_Paint, Paint TextPaint,
			float DO_LECH_CHUAN, int do_lon_ten) {
		if (LENH == Variables.VE_DUONG_THANG) {
			VE_DUONG_THANG(canvas, x1, y1, x3, y3, TEN, draw_paint, Nen_Paint,
					TextPaint);
		} else if (LENH == Variables.VE_TEXT) {
			VE_TEXT(canvas, TEN, x3, y3, Nen_Paint, TextPaint);
		} else if (LENH == Variables.VE_2LINE) {
			VE_2_DUONG_THANG(canvas, x1, y1, x3, y3, draw_paint, DO_LECH_CHUAN);
		} else if (LENH == Variables.VE_LINE_DUT_NET) {
			VE_DUONG_DUT_NET(canvas, x1, y1, x3, y3, TEN, Dased_Paint,
					Nen_Paint, TextPaint);
		} else if (LENH == Variables.VE_MUI_TEN) {
			ThtCanvas
					.ve_mui_ten(canvas, draw_paint, x1, y1, x3, y3, do_lon_ten);
			if (Math.abs(y3 - y1) > Math.abs(x3 - x1)) {
//				VE_TEXT_RED(canvas, TEN, (x1 + x3) / 2 + (TEN.length() * 4),
//						(y1 + y3) / 2, Nen_Paint, TextPaint);
				VE_TEXT_RED(canvas, TEN, x1+15,
						y1+5, Nen_Paint, TextPaint);
			} else {
//				VE_TEXT_RED(canvas, TEN, (x1 + x3) / 2 - (TEN.length() * 6),
//						(y1 + y3) / 2 - 15, Nen_Paint, TextPaint);
				VE_TEXT_RED(canvas, TEN, x1,
						y1 + 20, Nen_Paint, TextPaint);
			}
		}
	}

	public static void VE_LAI_ALL(Context ctx, List<Obj_CANVAS> list_canvas,
			Canvas canvas, Paint draw_paint, Paint Dased_Paint,
			Paint Nen_Paint, Paint TextPaint, float DO_LECH_CHUAN,
			int do_lon_ten) {
		if (list_canvas != null) {
			for (Obj_CANVAS HD : list_canvas) {
				try {
					VE_THEO_LENH(canvas, HD.LENH, HD.X1, HD.Y1, HD.X3, HD.Y3,
							HD.TEN, draw_paint, Dased_Paint, Nen_Paint, TextPaint,
							DO_LECH_CHUAN, do_lon_ten);
				} catch (Exception e) {
					
				}
				
			}
			for (Obj_CANVAS HD : list_canvas) {
				VE_HINH_THEO_LENH(ctx, canvas, HD.LENH, HD.X1, HD.Y1, HD.X3,
						HD.Y3, HD.TEN, Nen_Paint, TextPaint);
				;
			}

		}
	}

}
