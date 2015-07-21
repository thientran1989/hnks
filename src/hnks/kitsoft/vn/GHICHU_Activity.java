package hnks.kitsoft.vn;

import java.util.List;

import hnks.kitsoft.utils.Variables;
import hnks.kitsoft.vn.R;
import hnks.kitsoft.vn.database.DBAdapter;
import hnks.kitsoft.vn.list.Lst_GHICHU_BY_KH;
import hnks.kitsoft.vn.object.Obj_GHICHU;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class GHICHU_Activity extends Activity{
	
	DBAdapter tt;
	ListView lv_GHICHU;
	EditText  edt_GHICHU;
	Button btn_GUI_GHI_CHU;
	int CHUA_CHUYEN = 0;
	int DA_CHUYEN = 1;
	int OF_NHAN_VIEN = 0;
	int OF_TRUNG_TAM = 1;
	Lst_GHICHU_BY_KH mAdapter;
	List<Obj_GHICHU> list_ghichu;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ghi_chu);
		tt = new DBAdapter(this);
		tt.open();
		list_ghichu = tt.get_list_ghichu(Variables.HSCT_CHON.getMA_YCAU_KNAI());

		lv_GHICHU = (ListView)findViewById(R.id.lv_GHICHU_ghi_chu);
		edt_GHICHU = (EditText)findViewById(R.id.edt_GHICHU_ghi_chu);
		btn_GUI_GHI_CHU = (Button)findViewById(R.id.btn_GUI_GHICHU_ghi_chu);
		
		mAdapter = new Lst_GHICHU_BY_KH(this, list_ghichu);
		lv_GHICHU.setAdapter(mAdapter);
		
		btn_GUI_GHI_CHU.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String GHI_CHU = edt_GHICHU.getText().toString();
				Obj_GHICHU GC = new Obj_GHICHU(Variables.DNV.MaDV,Variables.HSCT_CHON.getMA_YCAU_KNAI(), 0, GHI_CHU,
						Memory.getNow(), CHUA_CHUYEN, OF_NHAN_VIEN);
				DBAdapter.Insert_HSO_GHICHU(GC);
				mAdapter.notifyDataSetChanged();
				edt_GHICHU.setText("");
				
			}
		});
	}

	

}
