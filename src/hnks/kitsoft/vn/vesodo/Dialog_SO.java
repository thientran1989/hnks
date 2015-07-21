package hnks.kitsoft.vn.vesodo;

import hnks.kitsoft.vn.R;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class Dialog_SO extends Dialog {
	Context context;
	public static Button btn_Dong;
	public static Button btn_OK;
	public static Spinner spn_SO;
	public static EditText edt_NHAP_SO;
	int h;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.dialog_so);
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

		btn_Dong = (Button) findViewById(R.id.btn_DONG_dialog_so);
		btn_OK = (Button) findViewById(R.id.btn_OK_dialog_so);
		edt_NHAP_SO = (EditText) findViewById(R.id.edt_NHAP_SO_dialog_so);
		spn_SO=(Spinner) findViewById(R.id.spn_SO_dialog_so);

	}

	public Dialog_SO(Context context) {
		super(context);
		this.context = context;
	}

}
