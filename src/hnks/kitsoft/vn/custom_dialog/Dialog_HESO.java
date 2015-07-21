package hnks.kitsoft.vn.custom_dialog;

import hnks.kitsoft.vn.R;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import android.widget.ListView;
import android.widget.TextView;

public class Dialog_HESO extends Dialog{

	Context context;
	public static ListView lv_HESO;
	public static TextView tv_PT_TT, tv_PT_C, tv_PT_TL, tv_PT_K, tv_PT_VAT, tv_PT_NC, 
							tv_PT_C1, tv_PT_NC1;
	CAPNHAT_HSO ctx;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.dialog_heso);
		set_key();

	}
	public void set_key() {

		lv_HESO = (ListView) findViewById(R.id.lv_HESO_dialog_heso);
		tv_PT_TT =(TextView)findViewById(R.id.tv_PT_TT_dialog_heso);
		tv_PT_C =(TextView)findViewById(R.id.tv_PT_C_dialog_heso);
		tv_PT_TL =(TextView)findViewById(R.id.tv_PT_TL_dialog_heso);
		tv_PT_K =(TextView)findViewById(R.id.tv_PT_K_dialog_heso);
		tv_PT_VAT =(TextView)findViewById(R.id.tv_PT_VAT_dialog_heso);
		tv_PT_NC=(TextView)findViewById(R.id.tv_PT_NC_dialog_heso);
		tv_PT_C1 =(TextView)findViewById(R.id.tv_PT_C1_dialog_heso);
		tv_PT_NC1 =(TextView)findViewById(R.id.tv_PT_NC1_dialog_heso);
		
	}

	public Dialog_HESO(Context context) {
		super(context);
		this.context = context;
	}


}
