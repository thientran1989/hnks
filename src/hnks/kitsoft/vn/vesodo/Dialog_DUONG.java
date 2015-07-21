package hnks.kitsoft.vn.vesodo;

import hnks.kitsoft.vn.R;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

public class Dialog_DUONG extends Dialog{
	public static Button[] key;
	Context context;

	public static Button btn_Xong,btn_Dong;
	public static ImageView iv_MAU1;
	int h;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.dialog_duongdi);
		set_key();
		setListener();
	}

	// them ky tu vao edittext
	public void setListener() {

		btn_Dong.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				dismiss();

			}
		});

	}

	public void set_key() {

		btn_Dong = (Button) findViewById(R.id.btn_DONG_dialog_duongdi);
		btn_Xong = (Button) findViewById(R.id.btn_XONG_dialog_duongdi);
		iv_MAU1 = (ImageView)findViewById(R.id.iv_mau1_dialog_duongdi);
		

	}

	public Dialog_DUONG(Context context) {
		super(context);
		this.context = context;
	}

}
