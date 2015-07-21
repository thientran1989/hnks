package hnks.kitsoft.vn.custom_dialog;

import hnks.kitsoft.vn.R;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class Dialog_listview_ghichu extends Dialog{
	public static EditText edt_ghichu;
	public static ListView lv_ghichu;
	public static Button btn_ok;
	Context ctx;
	public int max_length;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.dialog_listview_ghichu);
		set_key();
          

	}
	public void set_key() {

		edt_ghichu = (EditText) findViewById(R.id.edt_ghichu_dialog_ghichu);
		lv_ghichu = (ListView) findViewById(R.id.lv_ghichu_dialog_ghichu);
		btn_ok = (Button) findViewById(R.id.btn_ok_dialog_ghichu);
	}

	public Dialog_listview_ghichu(Context context,int max_length) {
		super(context);
		this.ctx = context;
		this.max_length = max_length;
	}


}
