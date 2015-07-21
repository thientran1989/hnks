package hnks.kitsoft.vn.dokhoangcach;

import static hnks.kitsoft.vn.dokhoangcach.CameraHelper.cameraAvailable;
import static hnks.kitsoft.vn.dokhoangcach.CameraHelper.getCameraInstance;
import static hnks.kitsoft.vn.dokhoangcach.MediaHelper.getOutputMediaFile;
import static hnks.kitsoft.vn.dokhoangcach.MediaHelper.saveToFile;

import java.io.File;
import java.text.DecimalFormat;

import com.thtsoftlib.function.Tht_Screen;

import hnks.kitsoft.vn.R;
import android.app.Activity;
import android.content.Intent;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

@SuppressWarnings("deprecation")
public class CameraActivity extends Activity implements PictureCallback ,SensorEventListener{

	public static final String EXTRA_IMAGE_PATH = "hnks.kitsoft.vn.dokhoangcach.CameraActivity.EXTRA_IMAGE_PATH";

	private Camera camera;
	private CameraPreview cameraPreview;

	// la ban
		double roll;
		TextView tv_live_angle;
		double default_h = 1.5;
		double goccamera;
		double goc_tinhtien;
		double meterConversion = 1609.00;
		double earthRadius = 3958.75;
		double azimuth;
		double pitch;
		// khai bao cua sensor
		SensorManager sensorManager;
		private Sensor sensorAccelerometer;
		private Sensor sensorMagneticField;

		private float[] valuesAccelerometer;
		private float[] valuesMagneticField;

		private float[] matrixR;
		private float[] matrixI;
		private float[] matrixValues;

		TextView readL;
		Compass myCompass;
		Direction direction;
		int w, h;
		String path;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Tht_Screen.hide_title(this);
		setContentView(R.layout.activity_camera);
		setResult(RESULT_CANCELED);
		camera = getCameraInstance();
		if(cameraAvailable(camera)){
			try {
				initCameraPreview();
			} catch (Exception e) {
			}
			
		} else {
			finish();
		}

		// la ban
				w = (getWindowManager().getDefaultDisplay().getWidth());
				h = (getWindowManager().getDefaultDisplay().getHeight());
				
				tv_live_angle = (TextView) findViewById(R.id.tv_live_angle);
				myCompass = (Compass) findViewById(R.id.mycompass);
				direction = (Direction) findViewById(R.id.imageview1);
				sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
				sensorAccelerometer = sensorManager
						.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
				sensorMagneticField = sensorManager
						.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
				valuesAccelerometer = new float[3];
				valuesMagneticField = new float[3];

				matrixR = new float[9];
				matrixI = new float[9];
				matrixValues = new float[3];
				
				
				Thread t = new Thread() {

					@Override
					public void run() {
						try {
							while (!isInterrupted()) {
								Thread.sleep(2000);
								runOnUiThread(new Runnable() {
									@Override
									public void run() {
										double kc=(default_h)
												* Math.tan(Math
														.toRadians(Math
																.abs(roll)));
										if (0<kc & kc<10){
											kc = kc + (kc*0.05);
										}
										else if(kc >10 & kc <30){
											kc = kc + (kc*0.15);
										} else if(kc >30){
											kc = kc + (kc*0.25);
										}
										tv_live_angle
										.setText("Khoảng cách: "+String.format(
														"%.2f",kc)+" m");
										
										myCompass.update(matrixValues[0],
												matrixValues[2]);

									
									}
								});
							}
						} catch (InterruptedException e) {
						}
					}
				};
				t.start();
				// end la ban
	}
	private void initCameraPreview() {
		cameraPreview = (CameraPreview) findViewById(R.id.camera_preview);
		cameraPreview.init(camera);
	}

	@FromXML
	public void onCaptureClick(View button){
		camera.takePicture(null, null, this);
	}

	@Override
	public void onPictureTaken(byte[] data, Camera camera) {
		path = savePictureToFileSystem(data);
		setResult(path);
		releaseCamera();
		finish();
	}

	private static String savePictureToFileSystem(byte[] data) {
		File file = getOutputMediaFile();
		saveToFile(data, file);
		return file.getAbsolutePath();
	}

	private void setResult(String path) {
		Intent intent = new Intent();
		intent.putExtra(EXTRA_IMAGE_PATH, path);
		setResult(RESULT_OK, intent);
	}
	private void releaseCamera() {
		if(camera != null){
			camera.release();
			camera = null;
		}
	}
	public void onSensorChanged(SensorEvent event) {
		// la ban
					switch (event.sensor.getType()) {
					case Sensor.TYPE_ACCELEROMETER:
						for (int i = 0; i < 3; i++) {
							valuesAccelerometer[i] = event.values[i];
						}
						break;
					case Sensor.TYPE_MAGNETIC_FIELD:
						for (int i = 0; i < 3; i++) {
							valuesMagneticField[i] = event.values[i];
						}
						break;
					}

					boolean success = SensorManager.getRotationMatrix(matrixR, matrixI,
							valuesAccelerometer, valuesMagneticField);

					if (success) {
						SensorManager.getOrientation(matrixR, matrixValues);

						azimuth = Math.toDegrees(matrixValues[0]);
						pitch = Math.toDegrees(matrixValues[1]);
						roll = Math.toDegrees(matrixValues[2]);
						goccamera = roll;
						goc_tinhtien = azimuth;

					}
	}
	@Override
	protected void onDestroy() {
		super.onDestroy();
		releaseCamera();
	}

	@Override
	protected void onPause() {
		super.onPause();
		sensorManager.unregisterListener(this, sensorAccelerometer);
		sensorManager.unregisterListener(this, sensorMagneticField);
	}

	@Override
	protected void onResume() {
		super.onResume();
		sensorManager.registerListener(this, sensorAccelerometer,
				SensorManager.SENSOR_DELAY_NORMAL);
		sensorManager.registerListener(this, sensorMagneticField,
				SensorManager.SENSOR_DELAY_NORMAL);
	}

	@Override
	protected void onRestart() {
		super.onRestart();
	}

	@Override
	protected void onStop() {
		super.onStop();
	}

	@Override
	protected void onStart() {
		super.onStart();
	}

	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		
	}

	double roundTwoDecimals(double d) {
		DecimalFormat twoDForm = new DecimalFormat("#.##");
		return Double.valueOf(twoDForm.format(d));
	}
	
 
}
