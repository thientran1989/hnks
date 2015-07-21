package hnks.kitsoft.utils;

import hnks.kitsoft.vn.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("InflateParams") public class Custom_Toast {
	// Red toast
			public static void show_red_toast(Activity ctx,String show_text) {
				Context context=ctx.getApplicationContext();
				LayoutInflater inflater=ctx.getLayoutInflater();
				
				View customToastroot =inflater.inflate(R.layout.toast_red_toast, null);
				TextView tv_show = (TextView)customToastroot.findViewById(R.id.tv_show_red_alert);
				Toast customtoast=new Toast(context);
				
				customtoast.setView(customToastroot);
				customtoast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL,0, 0);
				customtoast.setDuration(Toast.LENGTH_LONG);
				customtoast.show();
				tv_show.setText(show_text);
			}
			// Yellow toast
			public static void show_yellow_toast(Activity ctx,String show_text) {
				Context context=ctx.getApplicationContext();
				LayoutInflater inflater=ctx.getLayoutInflater();
				
				View customToastroot =inflater.inflate(R.layout.toast_yellow_toast, null);
				TextView tv_show = (TextView)customToastroot.findViewById(R.id.tv_show_yellow_alert);
				Toast customtoast=new Toast(context);		
				customtoast.setView(customToastroot);
				customtoast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL,0, 0);
				customtoast.setDuration(Toast.LENGTH_LONG);
				customtoast.show();
				tv_show.setText(show_text);
			}
			// Blue toast
			public static void show_blue_toast(Activity ctx,String show_text) {
				Context context=ctx.getApplicationContext();
				LayoutInflater inflater=ctx.getLayoutInflater();
				View customToastroot =inflater.inflate(R.layout.toast_blue_toast, null);
				TextView tv_show = (TextView)customToastroot.findViewById(R.id.tv_show_blue_toast);
				Toast customtoast=new Toast(context);
				
				customtoast.setView(customToastroot);
				customtoast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL,0, 0);
				customtoast.setDuration(Toast.LENGTH_LONG);
				customtoast.show();
				tv_show.setText(show_text);
			}
}
