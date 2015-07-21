package hnks.kitsoft.vn.custom_dialog;

import hnks.kitsoft.vn.R;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import android.widget.EditText;
import android.widget.ListView;

public class Dialog_TRAM extends Dialog{

	Context context;
	public static EditText edt_TRAM;
	public static ListView lv_TRAM;
	CAPNHAT_HSO ctx;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.dialog_tram);
		set_key();

	}
	public void set_key() {

		edt_TRAM = (EditText) findViewById(R.id.edt_TRAM_dialog_tram);
		lv_TRAM = (ListView) findViewById(R.id.lv_TRAM_dialog_tram);
	}

	public Dialog_TRAM(Context context) {
		super(context);
		this.context = context;
	}


}
