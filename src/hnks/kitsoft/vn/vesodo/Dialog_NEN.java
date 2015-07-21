package hnks.kitsoft.vn.vesodo;

import hnks.kitsoft.vn.R;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ListView;

public class Dialog_NEN extends Dialog{
	//public static Button[] key;
	Context context;
	public static Button btn_Dong;
	public static ListView lv_NEN;
	int h;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.dialog_nen);
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

		btn_Dong = (Button) findViewById(R.id.btn_DONG_dialog_nen);
		lv_NEN = (ListView)findViewById(R.id.lv_SO_dialog_nen);

	}

	public Dialog_NEN(Context context) {
		super(context);
		this.context = context;
	}


}
