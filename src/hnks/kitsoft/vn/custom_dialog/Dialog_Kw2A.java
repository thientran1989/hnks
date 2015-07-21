package hnks.kitsoft.vn.custom_dialog;

import hnks.kitsoft.vn.R;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class Dialog_Kw2A extends Dialog{
	public static EditText edt_congsuat,edt_PF,edt_KQ;
	public static RadioButton rdo_1pha,rdo_3pha;
	public static Button btn_ok,btn_dong;
	Context ctx;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.dialog_kw2_a);
		set_key();
          

	}
	public void set_key() {

		edt_congsuat = (EditText) findViewById(R.id.edt_CS_kw2a);
		edt_PF = (EditText) findViewById(R.id.edt_PF_kw2a);
		edt_KQ = (EditText) findViewById(R.id.edt_KQ_kw2a);
		btn_ok = (Button) findViewById(R.id.btn_LUU_kw2a);
		btn_dong = (Button) findViewById(R.id.btn_DONG_kw2a);
		rdo_1pha = (RadioButton) findViewById(R.id.rdo_1pha);
		rdo_3pha = (RadioButton) findViewById(R.id.rdo_3pha);
	}

	public Dialog_Kw2A(Context context) {
		super(context);
		this.ctx = context;
	}


}
