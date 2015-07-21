package hnks.kitsoft.vn;

import hnks.kitsoft.vn.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.thtsoftlib.function.Tht_Screen;


public class Ac_giaodien_quantri extends Activity{
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Tht_Screen.hide_title(this);
		setContentView(R.layout.giaodien_quantri);
	}
	public void to_ds_vattu(View v){
		Intent i = new Intent(Ac_giaodien_quantri.this,DanhSach_VatTu.class);
		startActivity(i);
	}
	public void to_cauhinh_heso(View v){
		Intent i = new Intent(Ac_giaodien_quantri.this,CauHinh_HeSo.class);
		startActivity(i);
	}
	public void to_cauhinh_chuky(View v){
		Intent i = new Intent(Ac_giaodien_quantri.this,Ac_Cauhinh_Chuky.class);
		startActivity(i);
	}
}
