package hnks.kitsoft.vn.camera;

import java.io.ByteArrayInputStream;

import com.thtsoftlib.function.Tht_Screen;

import hnks.kitsoft.utils.Variables;
import hnks.kitsoft.vn.R;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class DisplayImageActivity extends Activity {
	ImageView imageDetail;
	int imageId;
	Bitmap theImage;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Tht_Screen.hide_title(this);
		setContentView(R.layout.camera_display);
		imageDetail = (ImageView) findViewById(R.id.imageView1);
		if (Variables.HINH_CHON!=null){
			try {
				byte[] outImage = Variables.HINH_CHON.getHINH();
				ByteArrayInputStream imageStream = new ByteArrayInputStream(outImage);
				Bitmap theImage = BitmapFactory.decodeStream(imageStream);
				imageDetail.setImageBitmap(theImage);
			} catch (Exception e) {
				
			}
		}
	}
	public void thoat(View v){
		finish();
	}

}
