package hnks.kitsoft.vn;

import java.io.ByteArrayOutputStream;
import java.util.List;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.GoogleMap.OnMapLongClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerDragListener;
import com.google.android.gms.maps.GoogleMap.SnapshotReadyCallback;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.thtsoftlib.function.ThtCanvas;
import com.thtsoftlib.function.ThtTime;
import com.thtsoftlib.function.Tht_Screen;
import com.thtsoftlib.function.Thtcovert;
import hnks.kitsoft.utils.Utils;
import hnks.kitsoft.utils.Variables;
import hnks.kitsoft.vn.R;
import hnks.kitsoft.vn.database.DBAdapter;
import hnks.kitsoft.vn.list.Lst_DANHSACH_MAP;
import hnks.kitsoft.vn.list.Lst_TOA_DO;
import hnks.kitsoft.vn.object.Obj_HSO_CHIETTINH;
import hnks.kitsoft.vn.object.Obj_HSO_HINH;
import hnks.kitsoft.vn.object.Obj_HSO_TOADO;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Bitmap.Config;
import android.graphics.Paint.Style;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("NewApi")
public class Ban_Do_Activity extends Activity implements
		OnMapLongClickListener, OnMapClickListener, OnMarkerDragListener {

	// // Google Map
	private GoogleMap googleMap;
	Button btn_DANG, btn_HIEN;
	ImageView iv_CHUP_ANH;
	View screen;
	int DANG;
	int DANG_BAN_DO = 0;
	int DANG_VE_TINH = 1;
	DBAdapter tt;
	Lst_TOA_DO mAdapter;
	int STT;
	String label;
	double X, Y;
	Lst_DANHSACH_MAP mAdapter2;
	ListView lv_DS;
	int AN_HIEN = 1;
	MarkerOptions marker;
	Marker melbourne = null;
	TextView tv_KC;
	String KC_DIEM;
	Obj_HSO_CHIETTINH HSCT = null;
	List<Obj_HSO_TOADO> list_toado;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Tht_Screen.hide_title(this);
		setContentView(R.layout.map);

		tt = new DBAdapter(this);
		tt.open();

		HSCT = Variables.HSCT_CHON;
		list_toado = tt.get_list_toado(HSCT.getMA_YCAU_KNAI());
		btn_DANG = (Button) findViewById(R.id.btn_VE_TINH_map);
		btn_HIEN = (Button) findViewById(R.id.btn_HIEN_DS_map);
		btn_HIEN.setText("Ẩn danh sách");
		iv_CHUP_ANH = (ImageView) findViewById(R.id.iv_CHUP_ANH_map);
		screen = (View) findViewById(R.id.screen);
		lv_DS = (ListView) findViewById(R.id.lv_DS_map);
		tv_KC = (TextView) findViewById(R.id.tv_KC_map);
		tv_KC.setText("Không tính khoảng cách");

		mAdapter2 = new Lst_DANHSACH_MAP(this, list_toado);
		lv_DS.setAdapter(mAdapter2);
		btn_DANG.setText("Vệ Tinh");

		btn_DANG.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				// Changing map type
				if (DANG == DANG_BAN_DO) {

					DANG = DANG_VE_TINH;
					btn_DANG.setText("Bản đồ");
					googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
				} else {
					DANG = DANG_BAN_DO;
					btn_DANG.setText("Vệ Tinh");
					googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
				}

			}
		});
		btn_HIEN.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (AN_HIEN == 1) {
					lv_DS.setVisibility(View.GONE);
					AN_HIEN = 0;
					btn_HIEN.setText("Hiện danh sách");
				} else {
					lv_DS.setVisibility(View.VISIBLE);
					AN_HIEN = 1;
					btn_HIEN.setText("Ẩn danh sách");
				}

			}
		});
		iv_CHUP_ANH.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				SnapshotReadyCallback callback = new SnapshotReadyCallback() {
					Bitmap bitmap;

					@Override
					public void onSnapshotReady(Bitmap snapshot) {
						// TODO Auto-generated method stub
						bitmap = snapshot;
						try {
							Bitmap newBitmap = null;
							bitmap = ResizedBitmap(bitmap, Tht_Screen
									.get_screen_heigth(Ban_Do_Activity.this));

							Config config = bitmap.getConfig();
							if (config == null) {
								config = Bitmap.Config.ARGB_8888;
							}
							newBitmap = Bitmap.createBitmap(
									Tht_Screen
											.get_screen_width(Ban_Do_Activity.this),
									Tht_Screen
											.get_screen_heigth(Ban_Do_Activity.this),
									config);
							Canvas canvas = new Canvas(newBitmap);

							Paint paint = new Paint();
							paint.setColor(Color.RED);
							paint.setStrokeWidth(get_do_lon_chu_thap(newBitmap));
							paint.setStyle(Style.FILL);
							paint.setXfermode(new PorterDuffXfermode(
									PorterDuff.Mode.SRC_OVER));

							canvas.drawBitmap(bitmap, 0, 0, paint);

							paint.setColor(Color.RED);
							paint.setStrokeWidth(get_do_lon_chu_thap(bitmap));
							paint.setTextSize(get_do_lon_chu_thap(bitmap) * 5);
							paint.setStyle(Style.FILL);
							paint.setXfermode(new PorterDuffXfermode(
									PorterDuff.Mode.SRC_OVER));

							Paint paint2 = new Paint();
							paint2.setColor(Color.RED);
							paint2.setStrokeWidth(get_do_lon_chu_thap(newBitmap));
							paint2.setTextSize(get_do_lon_chu_thap(newBitmap) * 3);
							paint2.setStyle(Style.FILL);
							paint2.setXfermode(new PorterDuffXfermode(
									PorterDuff.Mode.SRC_OVER));

							// some more settings...
							String TEN = Variables.HSCT_CHON.getMA_YCAU_KNAI()
									+ " : "
									+ Variables.HSCT_CHON.getTEN_KHANG();
							String DC = Variables.HSCT_CHON.getSO_NHA() + ", "
									+ Variables.HSCT_CHON.getDUONG_PHO();
							// ve them text ten dia chi kh
							ThtCanvas.ve_text_on_canvas(canvas, newBitmap,
									paint, TEN, 1, 1, 0);
							ThtCanvas.ve_text_on_canvas(canvas, newBitmap,
									paint2, DC, 2, 0, 0);
							ThtCanvas.ve_text_on_canvas(
									canvas,
									newBitmap,
									paint,
									"Tên NV : "
											+ Variables.DNV.getTenNhanVien(),
									3, 0, (float) 0.25);
							ThtCanvas.ve_text_on_canvas(
									canvas,
									newBitmap,
									paint2,
									"Ngày giờ : "
											+ ThtTime
													.get_curent_full_date_time(),
									4, 0, (float) 0.25);
							String toado="không có thông tin tọa độ !";
							if (list_toado.size()>0){
								Obj_HSO_TOADO mTD = list_toado.get(0);
								toado = "Tọa độ : ( "+Thtcovert.double_to_String(mTD.getX())+","+
								Thtcovert.double_to_String(mTD.getY())+" )";
							}
							ThtCanvas.ve_text_on_canvas(
									canvas,
									newBitmap,
									paint2,toado,
									5, 0, (float) 0.25);
							
							ByteArrayOutputStream stream = new ByteArrayOutputStream();
							newBitmap.compress(Bitmap.CompressFormat.JPEG, 70,
									stream);
							byte imageInByte[] = stream.toByteArray();

							Obj_HSO_HINH H = new Obj_HSO_HINH();
							H.setHINH(imageInByte);
							H.setDA_CHUYEN(Utils.TT_CHUA_CHUYEN);
							H.setMA_DVIQLY(HSCT.getMA_DVIQLY());
							H.setMA_LOAI_HINH("BD");
							H.setMA_YCAU_KNAI(HSCT.getMA_YCAU_KNAI());
							H.setTEN_HINH("Bản đồ vị trí");
							H.setTHOI_GIAN(Memory.getNow());
							tt.Insert_HSO_HINH(H);
							Toast.makeText(getApplicationContext(),
									"Đã chụp bản đồ !", Toast.LENGTH_LONG)
									.show();
							Intent i = new Intent();
							setResult(RESULT_OK, i);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				};

				googleMap.snapshot(callback);

				//

			}
		});
		lv_DS.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// double x = tt.get_arr_HSO_TOADO_BY_MA_YCAU_KNAI(
				// HSO_CHIETTINH_Activity.MA_YCAU_KNAI).get(arg2).X;
				// double y = tt.get_arr_HSO_TOADO_BY_MA_YCAU_KNAI(
				// HSO_CHIETTINH_Activity.MA_YCAU_KNAI).get(arg2).Y;
				// String D1 = tt.get_arr_HSO_TOADO_BY_MA_YCAU_KNAI(
				// HSO_CHIETTINH_Activity.MA_YCAU_KNAI).get(arg2).TEN_DIEM;
				if (arg2 < mAdapter2.getCount() - 1) {
					// double x1 = tt.get_arr_HSO_TOADO_BY_MA_YCAU_KNAI(
					// HSO_CHIETTINH_Activity.MA_YCAU_KNAI).get(arg2 + 1).X;
					// double y1 = tt.get_arr_HSO_TOADO_BY_MA_YCAU_KNAI(
					// HSO_CHIETTINH_Activity.MA_YCAU_KNAI).get(arg2 + 1).Y;
					// String D2 = tt.get_arr_HSO_TOADO_BY_MA_YCAU_KNAI(
					// HSO_CHIETTINH_Activity.MA_YCAU_KNAI).get(arg2 +
					// 1).TEN_DIEM;
					//
					// LatLng LatLng1 = new LatLng(x, y);
					// LatLng LatLng2 = new LatLng(x1, y1);
					//
					// String KC = String.format("%.2f",
					// getDistance(LatLng1, LatLng2));
					// KC_DIEM = "Khoảng cách từ " + D2 + " đến " + D1 +
					// " là : "
					// + KC + " m";
				} else {
					KC_DIEM = "";
				}
				double TONG_KC = 0;
				for (int i = 0; i < mAdapter2.getCount() - 1; i++) {
					//
					// double x2 = tt.get_arr_HSO_TOADO_BY_MA_YCAU_KNAI(
					// HSO_CHIETTINH_Activity.MA_YCAU_KNAI).get(i).X;
					// double y2 = tt.get_arr_HSO_TOADO_BY_MA_YCAU_KNAI(
					// HSO_CHIETTINH_Activity.MA_YCAU_KNAI).get(i).Y;
					// double x1 = tt.get_arr_HSO_TOADO_BY_MA_YCAU_KNAI(
					// HSO_CHIETTINH_Activity.MA_YCAU_KNAI).get(i + 1).X;
					// double y1 = tt.get_arr_HSO_TOADO_BY_MA_YCAU_KNAI(
					// HSO_CHIETTINH_Activity.MA_YCAU_KNAI).get(i + 1).Y;
					//
					// LatLng LatLng1 = new LatLng(x2, y2);
					// LatLng LatLng2 = new LatLng(x1, y1);
					//
					// double KC = getDistance(LatLng1, LatLng2);
					// TONG_KC += KC;

				}
				Toast.makeText(getApplicationContext(),
						String.valueOf(TONG_KC), Toast.LENGTH_LONG).show();
				tv_KC.setText(KC_DIEM + " . Tổng khoảng cách : "
						+ String.format("%.2f", TONG_KC) + " m");
				// int STT = tt.get_arr_HSO_TOADO_BY_MA_YCAU_KNAI(
				// HSO_CHIETTINH_Activity.MA_YCAU_KNAI).get(arg2).STT;
				// String T = tt.get_arr_HSO_TOADO_BY_MA_YCAU_KNAI(
				// HSO_CHIETTINH_Activity.MA_YCAU_KNAI).get(arg2).TEN_DIEM;

				googleMap.clear();
				ADD_MARKER();
//				VE_LINE_MAP(Color.RED);
				// LatLng MELBOURNE = new LatLng(x, y);
				// BitmapDescriptor icon = BitmapDescriptorFactory
				// .fromResource(R.drawable.location_chon);
				// melbourne = googleMap.addMarker(new MarkerOptions()
				// .position(MELBOURNE).title(T).icon(icon));
				// melbourne.showInfoWindow();

				// Move the camera to last position with a zoom level
				// CameraPosition cameraPosition = new CameraPosition.Builder()
				// .target(new LatLng(x, y)).zoom(30).build();
				//
				// googleMap.animateCamera(CameraUpdateFactory
				// .newCameraPosition(cameraPosition));

			}
		});

		try {
			// Loading map
			initilizeMap();

			googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

			// googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
			// googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
			// googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
			// googleMap.setMapType(GoogleMap.MAP_TYPE_NONE);

			// Showing / hiding your current location
			// googleMap.setMyLocationEnabled(true);

			// Enable / Disable zooming controls
			googleMap.getUiSettings().setZoomControlsEnabled(false);

			// Enable / Disable my location button
			googleMap.getUiSettings().setMyLocationButtonEnabled(true);

			// Enable / Disable Compass icon
			googleMap.getUiSettings().setCompassEnabled(true);

			// Enable / Disable Rotate gesture
			googleMap.getUiSettings().setRotateGesturesEnabled(true);

			// Enable / Disable zooming functionality
			googleMap.getUiSettings().setZoomGesturesEnabled(true);

			ADD_MARKER();
			VE_LINE_MAP(Color.BLUE);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// @Override
	// protected void onResume() {
	// super.onResume();
	// initilizeMap();
	// }

	/**
	 * function to load map If map is not created it will create it for you
	 * */
	private void initilizeMap() {
		if (googleMap == null) {
			googleMap = ((MapFragment) getFragmentManager().findFragmentById(
					R.id.map)).getMap();
			googleMap.setOnMarkerDragListener(this);
			googleMap.setOnMapLongClickListener(this);
			googleMap.setOnMapClickListener(this);

			// check if map is created successfully or not
			if (googleMap == null) {
				Toast.makeText(getApplicationContext(),
						"Sorry! unable to create maps", Toast.LENGTH_SHORT)
						.show();
			}
		}
	}

	// moi them drag
	@Override
	public void onMarkerDrag(Marker arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onMarkerDragEnd(Marker arg0) {
		// TODO Auto-generated method stub
//		LatLng dragPosition = arg0.getPosition();
//		double dragLat = dragPosition.latitude;
//		double dragLong = dragPosition.longitude;
//		Log.i("info", "on drag end :" + dragLat + " dragLong :" + dragLong);
//		// tt.Sua_TOADO_HSO_TOADO(Integer.parseInt(arg0.getSnippet()),
//		// HSO_CHIETTINH_Activity.MA_YCAU_KNAI, dragLat, dragLong);
//		VE_LINE_MAP(R.color.Red);
//		// Tab_BanDo_KhachHang.mAdapter.notifyDataSetChanged();
//		Toast.makeText(getApplicationContext(),
//				"Đã cập nhật... " + label + " !", Toast.LENGTH_LONG).show();
	}

	@Override
	public void onMarkerDragStart(Marker arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onMapClick(LatLng arg0) {
		// TODO Auto-generated method stub
		googleMap.animateCamera(CameraUpdateFactory.newLatLng(arg0));
	}

	@Override
	public void onMapLongClick(LatLng arg0) {

	}

	public void VE_LINE_MAP(int c) {
		if (list_toado.size() > 1) {
			Obj_HSO_TOADO HD2 = null;
			Obj_HSO_TOADO HD1 = null;
			for (int i = 0; i < list_toado.size() - 1; i++) {
				HD1 = list_toado.get(i + 1);
				HD2 = list_toado.get(i);
				double lat1 = HD1.X;
				double lng1 = HD1.Y;
				double lat2 = HD2.X;
				double lng2 = HD2.Y;
				googleMap.addPolyline(new PolylineOptions()
						.add(new LatLng(lat2, lng2), new LatLng(lat1, lng1))
						.width(5).color(c));

			}
		}

	}
	public void ADD_MARKER() {

		if (list_toado.size() > 0) {

			Obj_HSO_TOADO HD = null;

			for (int i = 0; i < list_toado.size(); i++) {
				HD = list_toado.get(i);
				double lat = HD.X;
				double lng = HD.Y;
				STT = HD.STT;

				label = HD.TEN_DIEM;

				BitmapDescriptor icon = BitmapDescriptorFactory
						.fromResource(R.drawable.cv_nha_32);

				marker = new MarkerOptions().position(new LatLng(lat, lng))
						.icon(icon).draggable(true).title(label)
						.snippet(String.valueOf(STT));

				googleMap.addMarker(marker);

				// Move the camera to last position with a zoom level
				CameraPosition cameraPosition = new CameraPosition.Builder()
						.target(new LatLng(list_toado.get(0).X, list_toado
								.get(0).Y)).zoom(20).build();

				googleMap.animateCamera(CameraUpdateFactory
						.newCameraPosition(cameraPosition));

			}

		}else{
			if (TAB_BAN_DO.your_location!=null){
				BitmapDescriptor icon = BitmapDescriptorFactory
						.fromResource(R.drawable.location);
				marker = new MarkerOptions().position(TAB_BAN_DO.your_location)
						.icon(icon).draggable(true).title("Vị trí hiện tại");
				googleMap.addMarker(marker);
				CameraPosition cameraPosition = new CameraPosition.Builder()
						.target(TAB_BAN_DO.your_location).zoom(20).build();
				googleMap.animateCamera(CameraUpdateFactory
						.newCameraPosition(cameraPosition));
			}
			
		}

	}

	public double getDistance(LatLng LatLng1, LatLng LatLng2) {
		double distance = 0;
		Location locationA = new Location("A");
		locationA.setLatitude(LatLng1.latitude);
		locationA.setLongitude(LatLng1.longitude);
		Location locationB = new Location("B");
		locationB.setLatitude(LatLng2.latitude);
		locationB.setLongitude(LatLng2.longitude);
		distance = locationA.distanceTo(locationB);
		return distance;

	}

	public Bitmap ResizedBitmap(Bitmap image, int max_h) {
		float width = image.getWidth();
		float height = image.getHeight();
		float bitmapRatio = max_h / height;
		if (bitmapRatio > 0) {
			width = (width * bitmapRatio);
			height = max_h;
		} else {
			// height = maxSize;
			// width = (int) (height * bitmapRatio);
		}
		return Bitmap
				.createScaledBitmap(image, (int) width, (int) height, true);
	}

	public int get_do_lon_chu_thap(Bitmap bit) {
		int dolon = 0;
		if (bit != null) {
			try {
				dolon = bit.getHeight() / 100;
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return dolon;

	}

}
