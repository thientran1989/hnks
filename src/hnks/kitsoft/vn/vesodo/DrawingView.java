package hnks.kitsoft.vn.vesodo;

import java.io.ByteArrayInputStream;
import java.util.List;

import hnks.kitsoft.utils.Custom_Toast;
import hnks.kitsoft.utils.Variables;
import hnks.kitsoft.vn.R;
import hnks.kitsoft.vn.database.DBAdapter;
import hnks.kitsoft.vn.object.Obj_CANVAS;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.thtsoftlib.function.ThtCanvas;
public class DrawingView extends View {

	
	private Path drawPath;
	public static Paint drawPaint,
						Dased_Paint,
						TextPaint,
						TextPaint_RED,
						canvasPaint,
						Paint_DANH_DAU,
						Nen_Paint;
	// canvas
	static Canvas drawCanvas;
	// canvas bitmap
	public static Bitmap canvasBitmap;
	// brush sizes
	private float brushSize;
	// erase flag
	private boolean erase = false;
	float x1, y1, x2, y2, x3, y3;
	int FINGER_RANGE = 0;
	float DO_LECH_CHUAN = 0;
	Context ctx;
	int DO_LON_TRON = 0;
	DBAdapter mdb;
	// title
	String title_tru ="";
	String title_tram="";
	String title_diem="";
	String title_line="";
	int do_lon_mui_ten =20;
	
	public DrawingView(Context context, AttributeSet attrs) {
		super(context, attrs);
		setupDrawing();
		ctx = context;
		mdb = new DBAdapter(context);
		setFocusable(true);

	}

	// setup drawing
	private void setupDrawing() {
		// brushSize = getResources().getInteger(R.integer.medium_size);
		brushSize = Variables.size_stroke_paint;
		DO_LON_TRON = Variables.size_stroke_point;
		FINGER_RANGE = Variables.size_finger_range;
		DO_LECH_CHUAN = FINGER_RANGE / 2;
		int text_size = Variables.size_text;
		int text_stroke = Variables.size_troke_text;
		
		drawPath = new Path();
		drawPaint = new Paint();
		drawPaint.setColor(Color.BLACK);
		drawPaint.setAntiAlias(true);
		drawPaint.setStrokeWidth(brushSize);
		drawPaint.setStyle(Paint.Style.STROKE);
		drawPaint.setStrokeJoin(Paint.Join.ROUND);
		drawPaint.setStrokeCap(Paint.Cap.ROUND);
		canvasPaint = new Paint(Paint.DITHER_FLAG);

		Dased_Paint = new Paint();
		Dased_Paint.setColor(Color.BLACK);
		Dased_Paint.setAntiAlias(true);
		Dased_Paint.setStrokeWidth(brushSize);
		Dased_Paint.setStyle(Paint.Style.STROKE);
		Dased_Paint.setStrokeJoin(Paint.Join.ROUND);
		Dased_Paint.setStrokeCap(Paint.Cap.ROUND);
		DashPathEffect dashPath = new DashPathEffect(new float[] { 10, 7 }, 1);
		Dased_Paint.setPathEffect(dashPath);

		TextPaint = new Paint();
		TextPaint.setColor(Color.BLACK); // Text Color
		TextPaint.setStrokeWidth(text_stroke); // Text Size
		TextPaint.setTextSize(text_size);
		TextPaint.setStyle(Style.FILL);
		TextPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER));
		
		TextPaint_RED = new Paint();
		TextPaint_RED.setColor(Color.RED); // Text Color
		TextPaint_RED.setStrokeWidth(text_stroke); // Text Size
		TextPaint_RED.setTextSize(text_size);
		TextPaint_RED.setStyle(Style.FILL);
		TextPaint_RED.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER));

		Paint_DANH_DAU = new Paint();
		Paint_DANH_DAU.setColor(Color.RED);
		Paint_DANH_DAU.setAntiAlias(true);
		Paint_DANH_DAU.setStrokeWidth(5);
		Paint_DANH_DAU.setStyle(Style.FILL);
		Paint_DANH_DAU.setStyle(Paint.Style.STROKE);
		Paint_DANH_DAU.setStrokeCap(Paint.Cap.ROUND);

		Nen_Paint = new Paint();
		Nen_Paint.setColor(Color.WHITE);
		Nen_Paint.setAntiAlias(true);
		Nen_Paint.setStyle(Style.FILL);
		Nen_Paint.setStrokeWidth(50);
		Nen_Paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER));
	}

	// size assigned to view
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		
		canvasBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
		drawCanvas = new Canvas(canvasBitmap);
		// ve lai list
		if (Ve_So_Do_Activity.list_canvas!=null){
			try {
				VE_LAI();
				Function_ve.VE_LAI_ALL(ctx, Ve_So_Do_Activity.list_canvas, drawCanvas, drawPaint, Dased_Paint, Nen_Paint, TextPaint, h, do_lon_mui_ten);
			} catch (Exception e) {
				Custom_Toast.show_red_toast((Activity) ctx,
						"Lỗi : \n"+e.toString());
			}
			
		}
		
	}

	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawPath(drawPath, drawPaint);
		canvas.drawBitmap(canvasBitmap, 0, 0, canvasPaint);
		
		if (x2 != 0 & y2 != 0) {
			if (Ve_So_Do_Activity.LENH_VE == Variables.VE_DUONG_THANG) {
				Function_ve.VE_DUONG_THANG(canvas, x1, y1, x2, y2, title_line, drawPaint, Nen_Paint, TextPaint);
			} else if (Ve_So_Do_Activity.LENH_VE == Variables.VE_LINE_DUT_NET) {
				Function_ve.VE_DUONG_DUT_NET(canvas, x1, y1, x2, y2, title_line, Dased_Paint, Nen_Paint, TextPaint);
			} else if (Ve_So_Do_Activity.LENH_VE == Variables.VE_2LINE) {
				Function_ve.VE_2_DUONG_THANG(canvas, x1, y1, x2, y2, drawPaint, DO_LECH_CHUAN);
			} else if (Ve_So_Do_Activity.LENH_VE == Variables.MOVE) {
				Function_ve.VE_DUONG_TRON_FILL(canvas, x2, y2, DO_LON_TRON, TextPaint);
			}
			else if (Ve_So_Do_Activity.LENH_VE == Variables.VE_NHA) {
				Function_ve.VE_NHA(canvas, x2, y2, ctx);
			} 
			// ve cong to treo
			else if (Ve_So_Do_Activity.LENH_VE == Variables.VE_CONGTO_TREO_NHA) {
				Function_ve.VE_CONGTO_TREO_NHA(canvas, x2, y2, ctx);
			}
			else if (Ve_So_Do_Activity.LENH_VE == Variables.VE_CONGTO_TREO_TRU_BTLT_105_HHUU) {
				Function_ve.VE_CONGTO_TREO_TRU_BTLT_105_HHUU(canvas, x2, y2, ctx);
			}
			else if (Ve_So_Do_Activity.LENH_VE == Variables.VE_CONGTO_TREO_TRU_BTLT_105_SLAP) {
				Function_ve.VE_CONGTO_TREO_TRU_BTLT_105_SLAP(canvas, x2, y2, ctx);
			}
			else if (Ve_So_Do_Activity.LENH_VE == Variables.VE_CONGTO_TREO_TRU_BTLT_12_HHUU) {
				Function_ve.VE_CONGTO_TREO_TRU_BTLT_12_HHUU(canvas, x2, y2, ctx);
			}
			else if (Ve_So_Do_Activity.LENH_VE == Variables.VE_CONGTO_TREO_TRU_BTV_65_HHUU) {
				Function_ve.VE_CONGTO_TREO_TRU_BTV_65_HHUU(canvas, x2, y2, ctx);
			}
			else if (Ve_So_Do_Activity.LENH_VE == Variables.VE_CONGTO_TREO_TRU_BTV_65_SLAP) {
				Function_ve.VE_CONGTO_TREO_TRU_BTV_65_SLAP(canvas, x2, y2, ctx);
			}
			// tram
			else if (Ve_So_Do_Activity.LENH_VE == Variables.VE_TRAM_1PHA_HHUU) {
				Function_ve.VE_TRAM_1_PHA_HHUU(canvas, x2, y2, title_tram, ctx, Nen_Paint, TextPaint);
			}
			else if (Ve_So_Do_Activity.LENH_VE == Variables.VE_TRAM_1PHA_SLAP) {
				Function_ve.VE_TRAM_1_PHA_SLAP(canvas, x2, y2, title_tram, ctx, Nen_Paint, TextPaint);
			}
			else if (Ve_So_Do_Activity.LENH_VE == Variables.VE_TRAM_3PHA_HHUU) {
				Function_ve.VE_TRAM_3_PHA_HHUU(canvas, x2, y2, title_tram, ctx, Nen_Paint, TextPaint);
			}
			else if (Ve_So_Do_Activity.LENH_VE == Variables.VE_TRAM_3PHA_SLAP) {
				Function_ve.VE_TRAM_3_PHA_SLAP(canvas, x2, y2, title_tram, ctx, Nen_Paint, TextPaint);
			}
			else if (Ve_So_Do_Activity.LENH_VE == Variables.VE_TRAM_3PHA_NEN_HHUU) {
				Function_ve.VE_TRAM_3_PHA_NEN_HHUU(canvas, x2, y2, title_tram, ctx, Nen_Paint, TextPaint);
			}
			else if (Ve_So_Do_Activity.LENH_VE == Variables.VE_TRAM_3PHA_NEN_SLAP) {
				Function_ve.VE_TRAM_3_PHA_NEN_SLAP(canvas, x2, y2, title_tram, ctx, Nen_Paint, TextPaint);
			}
			else if (Ve_So_Do_Activity.LENH_VE == Variables.VE_TRAM_3PHA_NHA_HHUU) {
				Function_ve.VE_TRAM_3_PHA_NHA_HHUU(canvas, x2, y2, title_tram, ctx, Nen_Paint, TextPaint);
			}
			else if (Ve_So_Do_Activity.LENH_VE == Variables.VE_TRAM_3PHA_NHA_SLAP) {
				Function_ve.VE_TRAM_3_PHA_NHA_SLAP(canvas, x2, y2, title_tram, ctx, Nen_Paint, TextPaint);
			}
			// thap sat
			else if (Ve_So_Do_Activity.LENH_VE == Variables.VE_THAPSAT_10_HHUU) {
				Function_ve.VE_THAPSAT_10_HHUU(canvas, x2, y2, title_tru, Nen_Paint, TextPaint, ctx);
			}
			else if (Ve_So_Do_Activity.LENH_VE == Variables.VE_THAPSAT_10_SLAP) {
				Function_ve.VE_THAPSAT_10_SLAP(canvas, x2, y2, title_tru, Nen_Paint, TextPaint, ctx);
			}
			else if (Ve_So_Do_Activity.LENH_VE == Variables.VE_THAPSAT_12_HHUU) {
				Function_ve.VE_THAPSAT_12_HHUU(canvas, x2, y2, title_tru, Nen_Paint, TextPaint, ctx);
			}
			else if (Ve_So_Do_Activity.LENH_VE == Variables.VE_THAPSAT_12_SLAP) {
				Function_ve.VE_THAPSAT_12_SLAP(canvas, x2, y2, title_tru, Nen_Paint, TextPaint, ctx);
			}
			// tru
			else if (Ve_So_Do_Activity.LENH_VE == Variables.VE_TRU_BTLT_12_HHUU) {
				Function_ve.VE_TRU_BTLT_12_HHUU(canvas, x2, y2, title_tru, Nen_Paint, TextPaint, ctx);
			}
			else if (Ve_So_Do_Activity.LENH_VE == Variables.VE_TRU_BTLT_12_SLAP) {
				Function_ve.VE_TRU_BTLT_12_SLAP(canvas, x2, y2, title_tru, Nen_Paint, TextPaint, ctx);
			}
			else if (Ve_So_Do_Activity.LENH_VE == Variables.VE_TRU_BTLT_14_HHUU) {
				Function_ve.VE_TRU_BTLT_14_HHUU(canvas, x2, y2, title_tru, Nen_Paint, TextPaint, ctx);
			}
			else if (Ve_So_Do_Activity.LENH_VE == Variables.VE_TRU_BTLT_14_SLAP) {
				Function_ve.VE_TRU_BTLT_14_SLAP(canvas, x2, y2, title_tru, Nen_Paint, TextPaint, ctx);
			}
			else if (Ve_So_Do_Activity.LENH_VE == Variables.VE_TRU_BTLT_20_HHUU) {
				Function_ve.VE_TRU_BTLT_20_HHUU(canvas, x2, y2, title_tru, Nen_Paint, TextPaint, ctx);
			}
			else if (Ve_So_Do_Activity.LENH_VE == Variables.VE_TRU_BTLT_20_SLAP) {
				Function_ve.VE_TRU_BTLT_20_SLAP(canvas, x2, y2, title_tru, Nen_Paint, TextPaint, ctx);
			}
			else if (Ve_So_Do_Activity.LENH_VE == Variables.VE_TRU_BTLT_75_HHUU) {
				Function_ve.VE_TRU_BTLT_75_HHUU(canvas, x2, y2, title_tru, Nen_Paint, TextPaint, ctx);
			}
			else if (Ve_So_Do_Activity.LENH_VE == Variables.VE_TRU_BTLT_75_SLAP) {
				Function_ve.VE_TRU_BTLT_75_SLAP(canvas, x2, y2, title_tru, Nen_Paint, TextPaint, ctx);
			}
			else if (Ve_So_Do_Activity.LENH_VE == Variables.VE_TRU_BTLT_85_HHUU) {
				Function_ve.VE_TRU_BTLT_85_HHUU(canvas, x2, y2, title_tru, Nen_Paint, TextPaint, ctx);
			}
			else if (Ve_So_Do_Activity.LENH_VE == Variables.VE_TRU_BTLT_85_SLAP) {
				Function_ve.VE_TRU_BTLT_85_SLAP(canvas, x2, y2, title_tru, Nen_Paint, TextPaint, ctx);
			}
			else if (Ve_So_Do_Activity.LENH_VE == Variables.VE_TRU_BTLT_105_HHUU) {
				Function_ve.VE_TRU_BTLT_105_HHUU(canvas, x2, y2, "", Nen_Paint, TextPaint, ctx);
			}
			else if (Ve_So_Do_Activity.LENH_VE == Variables.VE_TRU_BTLT_105_SLAP) {
				Function_ve.VE_TRU_BTLT_105_SLAP(canvas, x2, y2, "", Nen_Paint, TextPaint, ctx);
			}
			// btv
			else if (Ve_So_Do_Activity.LENH_VE == Variables.VE_TRU_BTV_11_HHUU) {
				Function_ve.VE_TRU_BTV_11_HHUU(canvas, x2, y2, title_tru, Nen_Paint, TextPaint, ctx);
			}
			else if (Ve_So_Do_Activity.LENH_VE == Variables.VE_TRU_BTV_11_SLAP) {
				Function_ve.VE_TRU_BTV_11_SLAP(canvas, x2, y2, "", Nen_Paint, TextPaint, ctx);
			}
			else if (Ve_So_Do_Activity.LENH_VE == Variables.VE_TRU_BTV_65_HHUU) {
				Function_ve.VE_TRU_BTV_65_HHUU(canvas, x2, y2, title_tru, Nen_Paint, TextPaint, ctx);
			}
			else if (Ve_So_Do_Activity.LENH_VE == Variables.VE_TRU_BTV_65_SLAP) {
				Function_ve.VE_TRU_BTV_65_SLAP(canvas, x2, y2, "", Nen_Paint, TextPaint, ctx);
			}
			else if (Ve_So_Do_Activity.LENH_VE == Variables.VE_TRU_BTV_75_HHUU) {
				Function_ve.VE_TRU_BTV_75_HHUU(canvas, x2, y2, title_tru, Nen_Paint, TextPaint, ctx);
			}
			else if (Ve_So_Do_Activity.LENH_VE == Variables.VE_TRU_BTV_75_SLAP) {
				Function_ve.VE_TRU_BTV_75_SLAP(canvas, x2, y2, "", Nen_Paint, TextPaint, ctx);
			}
			else if (Ve_So_Do_Activity.LENH_VE == Variables.VE_TRU_BTV_85_HHUU) {
				Function_ve.VE_TRU_BTV_85_HHUU(canvas, x2, y2, title_tru, Nen_Paint, TextPaint, ctx);
			}
			else if (Ve_So_Do_Activity.LENH_VE == Variables.VE_TRU_BTV_85_SLAP) {
				Function_ve.VE_TRU_BTV_85_SLAP(canvas, x2, y2, "", Nen_Paint, TextPaint, ctx);
			}
			else if (Ve_So_Do_Activity.LENH_VE == Variables.VE_TRU_GO_HHUU) {
				Function_ve.VE_TRU_GO_HHUU(canvas, x2, y2, title_tru, Nen_Paint, TextPaint, ctx);
			}
			else if (Ve_So_Do_Activity.LENH_VE == Variables.VE_TRU_GO_SLAP) {
				Function_ve.VE_TRU_GO_SLAP(canvas, x2, y2, "", Nen_Paint, TextPaint, ctx);
			}
			else if (Ve_So_Do_Activity.LENH_VE == Variables.VE_TRU_NHOM_HHUU) {
				Function_ve.VE_TRU_NHOM_HHUU(canvas, x2, y2, title_tru, Nen_Paint, TextPaint, ctx);
			}
			else if (Ve_So_Do_Activity.LENH_VE == Variables.VE_TRU_NHOM_SLAP) {
				Function_ve.VE_TRU_NHOM_SLAP(canvas, x2, y2, "", Nen_Paint, TextPaint, ctx);
			}
			
			// het tru
			else if (Ve_So_Do_Activity.LENH_VE == Variables.VE_HINH_CONGTO) {
				Function_ve.VE_TREO_CONGTO(canvas, x2, y2, ctx);
			}   else if (Ve_So_Do_Activity.LENH_VE == Variables.VE_TEXT) {
				canvas.translate(0, 0);
				String S =Ve_So_Do_Activity.S_VE;
				Function_ve.VE_TEXT(canvas,S, x2, y2, Nen_Paint, TextPaint);
			} else if (Ve_So_Do_Activity.LENH_VE == Variables.VE_CAU) {
				Function_ve.VE_CAU(canvas, x2, y2, title_diem, ctx, Nen_Paint, TextPaint);
			}else if (Ve_So_Do_Activity.LENH_VE == Variables.DI_CHUYEN) {
			} else if (Ve_So_Do_Activity.LENH_VE == Variables.VE_MUI_TEN) {
				ThtCanvas.ve_mui_ten(canvas, drawPaint, x1, y1, x2, y2, do_lon_mui_ten);
			} else if (Ve_So_Do_Activity.LENH_VE == Variables.VE_HINH_Z_NGANG) {
				Function_ve.VE_HINH_Z_NGANG(canvas, x2, y2, ctx);
			} else if (Ve_So_Do_Activity.LENH_VE == Variables.VE_HINH_Z_NGANG_DUT) {
				Function_ve.VE_HINH_Z_NGANG_DUT(canvas, x2, y2, ctx);
			}else if (Ve_So_Do_Activity.LENH_VE == Variables.VE_KDT) {
				Function_ve.VE_KDT(canvas, x2, y2, ctx);
			}

		}

	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec),
				MeasureSpec.getSize(heightMeasureSpec));
	}

	@SuppressLint("ClickableViewAccessibility") @Override
	public boolean onTouchEvent(MotionEvent event) {
		// respond to down, move and up events
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			x1 = event.getX();
			y1 = event.getY();
			break;
		case MotionEvent.ACTION_MOVE:
			x2 = event.getX();
			y2 = event.getY();

			if (Ve_So_Do_Activity.LENH_VE == Variables.VE_TU_DO) {
				drawPath.lineTo(x2, y2);
			}
			break;
		case MotionEvent.ACTION_UP:
			x3 = event.getX();
			y3 = event.getY();
			if (Ve_So_Do_Activity.LENH_VE == Variables.VE_TU_DO) {
				drawPath.lineTo(x3, y3);
				drawCanvas.drawPath(drawPath, drawPaint);

			} else if (Ve_So_Do_Activity.LENH_VE == Variables.VE_DUONG_THANG
					|| Ve_So_Do_Activity.LENH_VE == Variables.VE_LINE_DUT_NET) {
				PointF near_a = Function_tinhtoan_ve.KT_DIEM_GAN_NHAT(Ve_So_Do_Activity.list_canvas, x1, y1, DO_LECH_CHUAN);
				PointF near_b = Function_tinhtoan_ve.KT_DIEM_GAN_NHAT(Ve_So_Do_Activity.list_canvas, x3, y3, DO_LECH_CHUAN);
				if (Math.abs(near_a.x - near_b.x) > DO_LECH_CHUAN
						|| Math.abs(near_a.y - near_b.y) > DO_LECH_CHUAN) {
					if (near_b.x==x3 & near_b.y==y3){
						PointF near_thang = Function_tinhtoan_ve.LAM_THANG(x1, y1, x3, y3, DO_LECH_CHUAN);
						THEM_CANVAS(near_a.x, near_a.y, near_thang.x,near_thang.y, Ve_So_Do_Activity.LENH_VE,
								"", 0, 0);
					}else{
						THEM_CANVAS(near_a.x, near_a.y, near_b.x,near_b.y, Ve_So_Do_Activity.LENH_VE,
								"", 0, 0);
					}
					
				}
			} else if (Ve_So_Do_Activity.LENH_VE == Variables.MOVE) {
				move_to_point_KT(Ve_So_Do_Activity.list_canvas, x1, y1, x3, y3, DO_LECH_CHUAN);
				Function_ve.VE_LAI_ALL(ctx, Ve_So_Do_Activity.list_canvas, drawCanvas,
						drawPaint, Dased_Paint, Nen_Paint, TextPaint, DO_LECH_CHUAN,do_lon_mui_ten);
			} else if (Ve_So_Do_Activity.LENH_VE == Variables.VE_2LINE
					|| Ve_So_Do_Activity.LENH_VE == Variables.VE_MUI_TEN) {
				PointF point = Function_tinhtoan_ve.LAM_THANG(x1, y1, x3, y3, DO_LECH_CHUAN);
				if (Math.abs(x3 - x1) > DO_LECH_CHUAN
						|| Math.abs(y3 - y1) > DO_LECH_CHUAN) {
					THEM_CANVAS(x1, y1, point.x, point.y, Ve_So_Do_Activity.LENH_VE, "",
							0, 0);
				}
			}
			// tram
			else if (Ve_So_Do_Activity.LENH_VE == Variables.VE_TRAM_1PHA_HHUU) {
				PointF near_a = Function_tinhtoan_ve.KT_DIEM_GAN_NHAT(Ve_So_Do_Activity.list_canvas, x3, y3, DO_LECH_CHUAN);
				THEM_CANVAS(0, 0,  near_a.x, near_a.y, Ve_So_Do_Activity.LENH_VE,title_tram,
						R.drawable.cv_tram_1pha_hhuu, 1);
			}
			else if (Ve_So_Do_Activity.LENH_VE == Variables.VE_TRAM_1PHA_SLAP) {
				PointF near_a = Function_tinhtoan_ve.KT_DIEM_GAN_NHAT(Ve_So_Do_Activity.list_canvas, x3, y3, DO_LECH_CHUAN);
				THEM_CANVAS(0, 0,  near_a.x, near_a.y, Ve_So_Do_Activity.LENH_VE,title_tram,
						R.drawable.cv_tram_1pha_slap, 1);
			}
			else if (Ve_So_Do_Activity.LENH_VE == Variables.VE_TRAM_3PHA_HHUU) {
				PointF near_a = Function_tinhtoan_ve.KT_DIEM_GAN_NHAT(Ve_So_Do_Activity.list_canvas, x3, y3, DO_LECH_CHUAN);
				THEM_CANVAS(0, 0,  near_a.x, near_a.y, Ve_So_Do_Activity.LENH_VE,title_tram,
						R.drawable.cv_tram_3pha_hhuu_48, 1);
			}
			else if (Ve_So_Do_Activity.LENH_VE == Variables.VE_TRAM_3PHA_SLAP) {
				PointF near_a = Function_tinhtoan_ve.KT_DIEM_GAN_NHAT(Ve_So_Do_Activity.list_canvas, x3, y3, DO_LECH_CHUAN);
				THEM_CANVAS(0, 0,  near_a.x, near_a.y, Ve_So_Do_Activity.LENH_VE,title_tram,
						R.drawable.cv_tram_3pha_slap_48, 1);
			}
			else if (Ve_So_Do_Activity.LENH_VE == Variables.VE_TRAM_3PHA_NEN_HHUU) {
				PointF near_a = Function_tinhtoan_ve.KT_DIEM_GAN_NHAT(Ve_So_Do_Activity.list_canvas, x3, y3, DO_LECH_CHUAN);
				THEM_CANVAS(0, 0,  near_a.x, near_a.y, Ve_So_Do_Activity.LENH_VE,title_tram,
						R.drawable.cv_tram_3pha_nen_hhuu_48, 1);
			}
			else if (Ve_So_Do_Activity.LENH_VE == Variables.VE_TRAM_3PHA_NEN_SLAP) {
				PointF near_a = Function_tinhtoan_ve.KT_DIEM_GAN_NHAT(Ve_So_Do_Activity.list_canvas, x3, y3, DO_LECH_CHUAN);
				THEM_CANVAS(0, 0,  near_a.x, near_a.y, Ve_So_Do_Activity.LENH_VE,title_tram,
						R.drawable.cv_tram_3pha_nen_slap_48, 1);
			}
			else if (Ve_So_Do_Activity.LENH_VE == Variables.VE_TRAM_3PHA_NHA_HHUU) {
				PointF near_a = Function_tinhtoan_ve.KT_DIEM_GAN_NHAT(Ve_So_Do_Activity.list_canvas, x3, y3, DO_LECH_CHUAN);
				THEM_CANVAS(0, 0,  near_a.x, near_a.y, Ve_So_Do_Activity.LENH_VE,title_tram,
						R.drawable.cv_tram_3pha_nha_hhuu_48, 1);
			}
			else if (Ve_So_Do_Activity.LENH_VE == Variables.VE_TRAM_3PHA_NHA_SLAP) {
				PointF near_a = Function_tinhtoan_ve.KT_DIEM_GAN_NHAT(Ve_So_Do_Activity.list_canvas, x3, y3, DO_LECH_CHUAN);
				THEM_CANVAS(0, 0,  near_a.x, near_a.y, Ve_So_Do_Activity.LENH_VE,title_tram,
						R.drawable.cv_tram_3pha_nha_slap_48, 1);
			}
			// ve cong to treo
			else if (Ve_So_Do_Activity.LENH_VE == Variables.VE_CONGTO_TREO_NHA
					|| Ve_So_Do_Activity.LENH_VE == Variables.VE_CONGTO_TREO_TRU_BTLT_105_HHUU
					|| Ve_So_Do_Activity.LENH_VE == Variables.VE_CONGTO_TREO_TRU_BTLT_105_SLAP
					|| Ve_So_Do_Activity.LENH_VE == Variables.VE_CONGTO_TREO_TRU_BTLT_12_HHUU
					|| Ve_So_Do_Activity.LENH_VE == Variables.VE_CONGTO_TREO_TRU_BTV_65_HHUU 
					|| Ve_So_Do_Activity.LENH_VE == Variables.VE_CONGTO_TREO_TRU_BTV_65_SLAP) {
				PointF near_a = Function_tinhtoan_ve.KT_DIEM_GAN_NHAT(Ve_So_Do_Activity.list_canvas, x3, y3, DO_LECH_CHUAN);
				THEM_CANVAS(0, 0,near_a.x, near_a.y, Ve_So_Do_Activity.LENH_VE, "", 0, 0);
			}
			// thap sat
			else if (Ve_So_Do_Activity.LENH_VE == Variables.VE_THAPSAT_10_HHUU) {
				PointF near_a = Function_tinhtoan_ve.KT_DIEM_GAN_NHAT(Ve_So_Do_Activity.list_canvas, x3, y3, DO_LECH_CHUAN);
				THEM_CANVAS(0, 0,  near_a.x, near_a.y, Ve_So_Do_Activity.LENH_VE,title_tram,
						R.drawable.cv_thapsat_10_hhuu, 1);
			}
			else if (Ve_So_Do_Activity.LENH_VE == Variables.VE_THAPSAT_10_SLAP) {
				PointF near_a = Function_tinhtoan_ve.KT_DIEM_GAN_NHAT(Ve_So_Do_Activity.list_canvas, x3, y3, DO_LECH_CHUAN);
				THEM_CANVAS(0, 0,  near_a.x, near_a.y, Ve_So_Do_Activity.LENH_VE,title_tram,
						R.drawable.cv_thapsat_10_slap, 1);
			}
			else if (Ve_So_Do_Activity.LENH_VE == Variables.VE_THAPSAT_12_HHUU) {
				PointF near_a = Function_tinhtoan_ve.KT_DIEM_GAN_NHAT(Ve_So_Do_Activity.list_canvas, x3, y3, DO_LECH_CHUAN);
				THEM_CANVAS(0, 0,  near_a.x, near_a.y, Ve_So_Do_Activity.LENH_VE,title_tram,
						R.drawable.cv_thapsat_12m_hhuu, 1);
			}
			else if (Ve_So_Do_Activity.LENH_VE == Variables.VE_THAPSAT_12_SLAP) {
				PointF near_a = Function_tinhtoan_ve.KT_DIEM_GAN_NHAT(Ve_So_Do_Activity.list_canvas, x3, y3, DO_LECH_CHUAN);
				THEM_CANVAS(0, 0,  near_a.x, near_a.y, Ve_So_Do_Activity.LENH_VE,title_tram,
						R.drawable.cv_thapsat_12m_slap, 1);
			}
			// tru
			else if (Ve_So_Do_Activity.LENH_VE == Variables.VE_TRU_BTLT_12_HHUU
					|| Ve_So_Do_Activity.LENH_VE == Variables.VE_TRU_BTLT_12_SLAP
					|| Ve_So_Do_Activity.LENH_VE == Variables.VE_TRU_BTLT_14_HHUU
					|| Ve_So_Do_Activity.LENH_VE == Variables.VE_TRU_BTLT_14_SLAP
					|| Ve_So_Do_Activity.LENH_VE == Variables.VE_TRU_BTLT_20_HHUU
					|| Ve_So_Do_Activity.LENH_VE == Variables.VE_TRU_BTLT_20_SLAP
					|| Ve_So_Do_Activity.LENH_VE == Variables.VE_TRU_BTLT_75_HHUU
					|| Ve_So_Do_Activity.LENH_VE == Variables.VE_TRU_BTLT_75_SLAP
					|| Ve_So_Do_Activity.LENH_VE == Variables.VE_TRU_BTLT_85_HHUU
					|| Ve_So_Do_Activity.LENH_VE == Variables.VE_TRU_BTLT_85_SLAP
					|| Ve_So_Do_Activity.LENH_VE == Variables.VE_TRU_BTLT_105_HHUU
					|| Ve_So_Do_Activity.LENH_VE == Variables.VE_TRU_BTLT_105_SLAP
					|| Ve_So_Do_Activity.LENH_VE == Variables.VE_TRU_BTV_11_HHUU
					|| Ve_So_Do_Activity.LENH_VE == Variables.VE_TRU_BTV_11_SLAP
					|| Ve_So_Do_Activity.LENH_VE == Variables.VE_TRU_BTV_65_HHUU
					|| Ve_So_Do_Activity.LENH_VE == Variables.VE_TRU_BTV_65_SLAP
					|| Ve_So_Do_Activity.LENH_VE == Variables.VE_TRU_BTV_75_HHUU
					|| Ve_So_Do_Activity.LENH_VE == Variables.VE_TRU_BTV_75_SLAP
					|| Ve_So_Do_Activity.LENH_VE == Variables.VE_TRU_BTV_85_HHUU
					|| Ve_So_Do_Activity.LENH_VE == Variables.VE_TRU_BTV_85_SLAP
					|| Ve_So_Do_Activity.LENH_VE == Variables.VE_TRU_GO_HHUU
					|| Ve_So_Do_Activity.LENH_VE == Variables.VE_TRU_GO_SLAP
					|| Ve_So_Do_Activity.LENH_VE == Variables.VE_TRU_NHOM_HHUU
					|| Ve_So_Do_Activity.LENH_VE == Variables.VE_TRU_NHOM_SLAP) {
				PointF near_a = Function_tinhtoan_ve.KT_DIEM_GAN_NHAT(Ve_So_Do_Activity.list_canvas, x3, y3, DO_LECH_CHUAN);
				THEM_CANVAS(0, 0, near_a.x, near_a.y, Ve_So_Do_Activity.LENH_VE,title_tru, 0, 0);
			}
			// het tru
			else if (Ve_So_Do_Activity.LENH_VE == Variables.VE_KDT) {
				PointF near_a = Function_tinhtoan_ve.KT_DIEM_GAN_NHAT(Ve_So_Do_Activity.list_canvas, x3, y3, DO_LECH_CHUAN);
				THEM_CANVAS(0, 0, near_a.x, near_a.y,Ve_So_Do_Activity.LENH_VE, "",
						R.drawable.khu_dat_trong, 1);
			} else if (Ve_So_Do_Activity.LENH_VE == Variables.VE_HINH_Z_NGANG) {
				THEM_CANVAS(0, 0, x3, y3, Ve_So_Do_Activity.LENH_VE, "",
						R.drawable.z_ngang_bontam, 1);
			} else if (Ve_So_Do_Activity.LENH_VE == Variables.VE_HINH_Z_NGANG_DUT) {
				THEM_CANVAS(0, 0, x3, y3, Ve_So_Do_Activity.LENH_VE, "",
						R.drawable.dashed_z, 1);
			} else if (Ve_So_Do_Activity.LENH_VE == Variables.VE_TEXT) {
				String TEXT = Ve_So_Do_Activity.S_VE;
				if (TEXT.trim().length() > 0) {
					THEM_CANVAS(0, 0, x3, y3, Ve_So_Do_Activity.LENH_VE, TEXT, 0, 0);
				}
			} else if (Ve_So_Do_Activity.LENH_VE == Variables.VE_NHA) {
				PointF near_a = Function_tinhtoan_ve.KT_DIEM_GAN_NHAT(Ve_So_Do_Activity.list_canvas, x3, y3, DO_LECH_CHUAN);
				THEM_CANVAS(0, 0,near_a.x, near_a.y, Ve_So_Do_Activity.LENH_VE, "", 0, 0);
			} else if (Ve_So_Do_Activity.LENH_VE == Variables.VE_MUI_TEN) {
				ThtCanvas.ve_mui_ten(drawCanvas, drawPaint, x1, y1, x3, y3, 10);
				THEM_CANVAS(x1, y1, x3, y3, Ve_So_Do_Activity.LENH_VE, "", 0, 0);
			} else if (Ve_So_Do_Activity.LENH_VE == Variables.VE_CAU) {
				THEM_CANVAS(0, 0, x3, y3, Ve_So_Do_Activity.LENH_VE, "Cầu", 0, 0);
			}else if (Ve_So_Do_Activity.LENH_VE == Variables.VE_HINH_CONGTO) {
				THEM_CANVAS(0, 0, x3, y3, Ve_So_Do_Activity.LENH_VE, "", 0, 0);
			}else if (Ve_So_Do_Activity.LENH_VE == Variables.VE_TRU_BTV_65_SLAP ){
				THEM_CANVAS(x1, y1, x3, y3, Ve_So_Do_Activity.LENH_VE, "", 0, 0);
			}else if (Ve_So_Do_Activity.LENH_VE == Variables.VE_COT
					|| Ve_So_Do_Activity.LENH_VE == Variables.VE_COT_NGANG) {
				THEM_CANVAS(x1, y1, x3, y3, Ve_So_Do_Activity.LENH_VE, "", 0, 0);
			}else if (Ve_So_Do_Activity.LENH_VE == Variables.VE_HOP
					|| Ve_So_Do_Activity.LENH_VE == Variables.VE_HOP_NGANG) {
				THEM_CANVAS(x1, y1, x3, y3, Ve_So_Do_Activity.LENH_VE, "", 0, 0);
			}
			
			// ve lai het
			VE_LAI();
			Function_ve.VE_LAI_ALL(ctx, Ve_So_Do_Activity.list_canvas, drawCanvas, 
					drawPaint, Dased_Paint, Nen_Paint, TextPaint, DO_LECH_CHUAN,do_lon_mui_ten);
			x2 = 0;
			y2 = 0;
			drawPath.reset();
			break;
		}
		// redraw
		invalidate();
		return true;

	}

	// update color
	public void setColor() {
		invalidate();
		drawPaint.setColor(Color.BLACK);
	}

	// set erase true or false
	public void setErase(boolean isErase) {
		erase = isErase;
		if (erase)
			drawPaint
					.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
		else
			drawPaint.setXfermode(null);
	}

	// start new drawing
	public void startNew() {
		drawCanvas.drawColor(0, PorterDuff.Mode.CLEAR);
		invalidate();
	}
	public void VE_NEN() {
		if (mdb.ton_tao_nen(Ve_So_Do_Activity.MA_YCAU_KNAI,Variables.VE_NEN)){
			if (mdb.get_sl_hinhanh_bando(Variables.HSCT_CHON.getMA_YCAU_KNAI())>0){
				try {
					byte[] outImage = mdb.get_MAX_STT_HINHANH_OF_KH(Ve_So_Do_Activity.MA_YCAU_KNAI).HINH;
					ByteArrayInputStream imageStream = new ByteArrayInputStream(outImage);
					Bitmap theImage = BitmapFactory.decodeStream(imageStream);
					drawCanvas.drawBitmap(theImage,0,0, null);
				} catch (Exception e) {
					
				}
			}
		}else{
			
		}
//		ThtShow.show_toast(ctx, Thtcovert.int_to_String(mdb.get_sl_canvas(Ve_So_Do_Activity.MA_YCAU_KNAI)));
	}

	public void VE_LAI() {
		drawCanvas.drawColor(0, PorterDuff.Mode.CLEAR);
		invalidate();
		VE_NEN();
	}

	public static Bitmap drawableToBitmap(Drawable drawable) {
		if (drawable instanceof BitmapDrawable) {
			return ((BitmapDrawable) drawable).getBitmap();
		}
		Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(),
				drawable.getIntrinsicHeight(), Config.ARGB_8888);
		Canvas canvas = new Canvas(bitmap);
		drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
		drawable.draw(canvas);

		return bitmap;
	}

	// di chuyen doi tuong
		public void move_to_point_KT(List<Obj_CANVAS> list_canvas,float x_from,float y_from,float x_to,float y_to,double DO_LECH_CHUAN){
			PointF to_point = new PointF();
			PointF choose_point = new PointF();
			Obj_CANVAS CV_TO = new Obj_CANVAS();
			choose_point = Function_tinhtoan_ve.KT_DIEM_GAN_NHAT(list_canvas, x_from, y_from, DO_LECH_CHUAN);
			to_point = Function_tinhtoan_ve.KT_DIEM_GAN_NHAT(list_canvas, x_to, y_to, DO_LECH_CHUAN);
			for (Obj_CANVAS CV : list_canvas) {
				// neu chon diem dau
				if (CV.getX1()==choose_point.x & CV.getY1()==choose_point.y){
					if (CV.LENH == Variables.VE_TEXT || CV.LENH == Variables.VE_MUI_TEN
						||CV.LENH == Variables.VE_2LINE || CV.LENH == Variables.VE_CAU
						|| CV.LENH == Variables.VE_HINH_CONGTO){
						if (CV.LENH == Variables.VE_2LINE ||CV.LENH == Variables.VE_MUI_TEN){
							PointF P_thang = Function_tinhtoan_ve.LAM_THANG(CV.getX3(), CV.getY3(), x_to, y_to, DO_LECH_CHUAN);
							CV.setX1(P_thang.x);
							CV.setY1(P_thang.y);
						}else{
								CV.setX1(x_to);
								CV.setY1(y_to);
						}
						
					}else{
						if (CV.getX1()!=to_point.x & CV.getY1()!=to_point.y){
							CV.setX1(to_point.x);
							CV.setY1(to_point.y);
						}else{
								CV.setX1(x_to);
								CV.setY1(y_to);
						}
					}
					mdb.update_HSO_CANVAS(CV);
				}
				// neu chon diem cuoi
				if (CV.getX3()==choose_point.x & CV.getY3()==choose_point.y){
					if (CV.LENH == Variables.VE_TEXT || CV.LENH == Variables.VE_MUI_TEN
					  ||CV.LENH == Variables.VE_2LINE || CV.LENH == Variables.VE_CAU
					  || CV.LENH == Variables.VE_HINH_CONGTO){
						if (CV.LENH == Variables.VE_2LINE ||CV.LENH == Variables.VE_MUI_TEN){
							PointF P_thang = Function_tinhtoan_ve.LAM_THANG(CV.getX1(), CV.getY1(), x_to, y_to, DO_LECH_CHUAN);
							CV.setX3(P_thang.x);
							CV.setY3(P_thang.y);
						}else{
							CV.setX3(x_to);
							CV.setY3(y_to);
						}
						
					}else{
						if (CV.getX3()!=to_point.x & CV.getY3()!=to_point.y){
							for (Obj_CANVAS obj_CANVAS : list_canvas) {
								if (obj_CANVAS.getX3()==to_point.x & obj_CANVAS.getY3()==to_point.y){
									CV_TO = obj_CANVAS;
									break;
								}
							}
							if (CV_TO.getLENH()== Variables.VE_TEXT || CV_TO.getLENH() == Variables.VE_MUI_TEN
									||CV_TO.getLENH() == Variables.VE_2LINE || CV_TO.getLENH() == Variables.VE_CAU
									|| CV.LENH == Variables.VE_HINH_CONGTO){
								CV.setX3(x_to);
								CV.setY3(y_to);
							}else{
								CV.setX3(to_point.x);
								CV.setY3(to_point.y);
							}
							
						}else{
								CV.setX3(x_to);
								CV.setY3(y_to);
						}
					}
					
					mdb.update_HSO_CANVAS(CV);
				}
			}
			Ve_So_Do_Activity.mAdapter.notifyDataSetChanged();
			
		}

	public void THEM_CANVAS(float x1, float y1, float x3, float y3, int L,
			String T, int draw, int THUOC_TINH) {
		Obj_CANVAS CV = new Obj_CANVAS();
			CV.setCHON(0);
			CV.setLENH(L);
			CV.setMA_DVIQLY(Ve_So_Do_Activity.MA_DVIQLY);
			CV.setMA_YCAU_KNAI(Ve_So_Do_Activity.MA_YCAU_KNAI);
			CV.setTEN(T);
			CV.setX1(x1);
			CV.setX2(0);
			CV.setX3(x3);
			CV.setY1(y1);
			CV.setY2(0);
			CV.setY3(y3);
		if (L == Variables.VE_HINH) {
			CV.setHINH(draw);
			CV.setTHUOC_TINH(1);
		} else {
			CV.setTHUOC_TINH(0);
			CV.setHINH(0);
		}
		try {
			Ve_So_Do_Activity.them_net_ve(CV);
		} catch (Exception e) {
			
		}
		

	}
	public int get_do_lon_chu(Bitmap bit){
		 int dolon =0;
		 if (bit!=null){
			 try {
				dolon = bit.getHeight()/10;	
				} catch (Exception e) {
					
				}
		 }
		 return dolon;
		
	 }
	public void ve_lai_all(List<Obj_CANVAS> list_canvas){
		Function_ve.VE_LAI_ALL(ctx, list_canvas, drawCanvas, drawPaint, 
				Dased_Paint, Nen_Paint, TextPaint, DO_LECH_CHUAN,do_lon_mui_ten);
	}

}
