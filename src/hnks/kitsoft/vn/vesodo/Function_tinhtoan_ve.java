package hnks.kitsoft.vn.vesodo;

import hnks.kitsoft.vn.object.Obj_CANVAS;

import java.util.List;

import android.graphics.PointF;

public class Function_tinhtoan_ve {
	
	public static PointF KT_DIEM_GAN_NHAT(List<Obj_CANVAS> list_canvas,float x,float y,double DO_LECH_CHUAN) {
		// kiem tra do lech
		PointF near_point = new PointF();
		float DO_LECH_MIN = 1000;
		float KC_X1;
		float KC_X3;
		// xet diem cuoi x3 cua duong thang
		if (list_canvas !=null) {
			for (Obj_CANVAS CV_SS : list_canvas) {
				KC_X3 = (float) TINH_KC(CV_SS.X3, CV_SS.Y3, x, y);
				if (DO_LECH_MIN>KC_X3){
					DO_LECH_MIN = KC_X3;
					near_point.set(CV_SS.X3, CV_SS.Y3);
				}
			}
			for (Obj_CANVAS CV_SS : list_canvas) {
				KC_X1 = (float) TINH_KC(CV_SS.X1, CV_SS.Y1, x, y);
				if (DO_LECH_MIN>KC_X1){
					DO_LECH_MIN = KC_X1;
					near_point.set(CV_SS.X1, CV_SS.Y1);
				}
			}
			
		}
		if (DO_LECH_MIN>DO_LECH_CHUAN){
			near_point.set(x,y);
		}
		return near_point;
	}
	
	// lam thang
	public static PointF LAM_THANG(float x1, float y1, float x3, float y3,double DO_LECH_CHUAN) {
		PointF finall_point = new PointF();
		float x = x3;
		float y = y3;
		if (Math.abs(y3 - y1) < DO_LECH_CHUAN) {
			y =y1;
		}
		if (Math.abs(x3 - x1) < DO_LECH_CHUAN) {
			x=x1;
		}
		finall_point.set(x, y);
		return finall_point;
	}
	
	// tinh khoang cach
	public static double TINH_KC(float x1, float y1, float x3, float y3) {
		double KC = Math.abs((Math.sqrt(((x3 - x1) * (x3 - x1))
				+ ((y3 - y1) * (y3 - y1)))));
		return KC;
	}

}
