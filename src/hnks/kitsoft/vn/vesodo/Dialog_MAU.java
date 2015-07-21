package hnks.kitsoft.vn.vesodo;

import hnks.kitsoft.vn.R;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import android.widget.EditText;
import android.widget.ListView;

public class Dialog_MAU extends Dialog{
	Context context;
	public static ListView lv_MAU_CANVAS;
	public static EditText edt_search;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.dialog_mau);
		lv_MAU_CANVAS = (ListView)findViewById(R.id.lv_MAU_CANVAS_dialog_mau);
		edt_search = (EditText)findViewById(R.id.edt_search_dialog_mau_canvas);

	}

	public Dialog_MAU(Context context) {
		super(context);
		this.context = context;
	}

}
