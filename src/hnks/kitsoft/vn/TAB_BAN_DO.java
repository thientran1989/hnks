package hnks.kitsoft.vn;

import java.util.List;

import hnks.kitsoft.utils.Length_text;
import hnks.kitsoft.utils.Utils;
import hnks.kitsoft.utils.Variables;
import hnks.kitsoft.vn.R;
import hnks.kitsoft.vn.custom_dialog.Dialog_listview_ghichu;
import hnks.kitsoft.vn.database.DBAdapter;
import hnks.kitsoft.vn.list.Adapter_nhap_lieu;
import hnks.kitsoft.vn.list.Lst_TOA_DO;
import hnks.kitsoft.vn.object.Obj_HSO_TOADO;
import hnks.kitsoft.vn.object.Obj_nhap_lieu;
import tht.library.crouton.Style;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.thtsoftlib.function.ThtShow;
import com.thtsoftlib.function.Tht_Screen;

public class TAB_BAN_DO extends Activity  implements ConnectionCallbacks,
OnConnectionFailedListener, LocationListener, com.google.android.gms.location.LocationListener{
	ListView lv_TOA_DO;
	TextView tv_X;
	DBAdapter mdb;
	Lst_TOA_DO mAdapter;
	int LENH;
	double x = Double.parseDouble("0"), y = Double.parseDouble("0");
	int w, h;
	List<Obj_HSO_TOADO> list_toado;
	Dialog_listview_ghichu dialog_GC ;
	public static LatLng your_location;
	
	// ban do
	// LogCat tag
		private static final String TAG = TAB_BAN_DO.class.getSimpleName();

		private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 1000;

		private Location mLastLocation;

		// Google client to interact with Google API
		private GoogleApiClient mGoogleApiClient;

		// boolean flag to toggle periodic location updates
		private boolean mRequestingLocationUpdates = false;

		private LocationRequest mLocationRequest;

		// Location updates intervals in sec
		private static int UPDATE_INTERVAL = 10000; // 10 sec
		private static int FATEST_INTERVAL = 5000; // 5 sec
		private static int DISPLACEMENT = 10; // 10 meters

		// UI elements
		private Button btnShowLocation, btnStartLocationUpdates;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Tht_Screen.hide_title(this);
		setContentView(R.layout.tab_bando);
		btnShowLocation = (Button) findViewById(R.id.btnShowLocation);
		btnStartLocationUpdates = (Button) findViewById(R.id.btnLocationUpdates);
		mdb = new DBAdapter(this);
		mdb.open();
		LENH = Variables.THEM;
		
		
		// ban do

		// First we need to check availability of play services
		if (checkPlayServices()) {

			// Building the GoogleApi client
			buildGoogleApiClient();

			createLocationRequest();
		}

		// Show location button click listener
		btnShowLocation.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				displayLocation();
			}
		});

		// Toggling the periodic location updates
		btnStartLocationUpdates.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				togglePeriodicLocationUpdates();
			}
		});

		list_toado = DBAdapter.get_list_toado(Variables.HSCT_CHON.getMA_YCAU_KNAI());
		w = (Tht_Screen.get_screen_width(this) / 10) * 7;
		h = (Tht_Screen.get_screen_heigth(this) / 10) * 5;
		lv_TOA_DO = (ListView) findViewById(R.id.lv_TOADO_tab_bando);
		tv_X = (TextView) findViewById(R.id.tv_X_tab_bando);
		tv_X.setText("Đang chờ toạ độ....");
		mAdapter = new Lst_TOA_DO(this, list_toado);
		lv_TOA_DO.setAdapter(mAdapter);

		// update toa do
		x = 0;
		y = 0;
		
	}
	public void open_map(View v){
		your_location = new LatLng(x, y);
		Intent it = new Intent(TAB_BAN_DO.this,
				Ban_Do_Activity.class);
		startActivityForResult(it, Utils.REQ_CODE);
	}

	public void xoa_het_toa_do(View v) {
		final Dialog dialog = new Dialog(this, R.style.FullHeightDialog);
		dialog.setContentView(R.layout.dialog_yes_no);
		dialog.setCancelable(true);
		TextView message = (TextView) dialog
				.findViewById(R.id.tvmessagedialogtext);
		message.setText(getString(R.string.xoa_het_diem));
		Button yes = (Button) dialog.findViewById(R.id.bmessageDialogYes);
		yes.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				try {
					mdb.Xoa_HSO_TOADO_BY_MA_YCAU_KNAI(Variables.HSCT_CHON
							.getMA_YCAU_KNAI());
					list_toado.clear();
					mAdapter.notifyDataSetChanged();
					ThtShow.show_crouton_toast(TAB_BAN_DO.this,
							getString(R.string.da_xoa_het_diem), Style.INFO);
				} catch (Exception e) {
					ThtShow.show_crouton_toast(TAB_BAN_DO.this,
							e.toString(), Style.ALERT);
				}
				dialog.dismiss();
			}
		});
		Button no = (Button) dialog.findViewById(R.id.bmessageDialogNo);
		no.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				dialog.dismiss();
			}
		});
		dialog.show();
	}
	public void xoa_1_diem(final Obj_HSO_TOADO TD) {
		final Dialog dialog = new Dialog(this, R.style.FullHeightDialog);
		dialog.setContentView(R.layout.dialog_yes_no);
		dialog.setCancelable(true);
		TextView message = (TextView) dialog
				.findViewById(R.id.tvmessagedialogtext);
		message.setText(getString(R.string.xoa_diem_dang_chon));
		Button yes = (Button) dialog.findViewById(R.id.bmessageDialogYes);
		yes.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				try {
					 mdb.delete_HSO_TOADO(TD);
					 list_toado.remove(TD);
					 mAdapter.notifyDataSetChanged();
				} catch (Exception e) {
					ThtShow.show_crouton_toast(TAB_BAN_DO.this,
							e.toString(), Style.ALERT);
				}
				dialog.dismiss();
			}
		});
		Button no = (Button) dialog.findViewById(R.id.bmessageDialogNo);
		no.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				dialog.dismiss();
			}
		});
		dialog.show();
	}
	public void show_dialog_dat_ten(View v) {
		LENH = Variables.THEM;
		final List<Obj_nhap_lieu> list_nhap_lieu = mdb.get_list_NL(Variables.NL_VITRI);
	 	Adapter_nhap_lieu mAdapter_TT = new Adapter_nhap_lieu(list_nhap_lieu, TAB_BAN_DO.this, mdb);
	 	dialog_GC = new Dialog_listview_ghichu(TAB_BAN_DO.this,Length_text.ML_TEN_DIEM);
		dialog_GC.show();
		Dialog_listview_ghichu.lv_ghichu.setAdapter(mAdapter_TT);
		Dialog_listview_ghichu.edt_ghichu.setText("");
		Dialog_listview_ghichu.lv_ghichu.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Dialog_listview_ghichu.edt_ghichu.setText(list_nhap_lieu.get(arg2).nhap_lieu);
				Dialog_listview_ghichu.edt_ghichu.setSelection(Dialog_listview_ghichu.edt_ghichu.getText().length());
			}
		});
		Dialog_listview_ghichu.btn_ok.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (LENH == Variables.THEM) {
					if (Dialog_listview_ghichu.edt_ghichu.getText().length()<=dialog_GC.max_length){
						try {
							Obj_HSO_TOADO TD = new Obj_HSO_TOADO();
							TD.setMA_DVIQLY(Variables.DNV.MaDV);
							TD.setMA_YCAU_KNAI(Variables.HSCT_CHON
									.getMA_YCAU_KNAI());
							TD.setTEN_DIEM(Dialog_listview_ghichu.edt_ghichu.getText().toString());
							TD.setTHOI_GIAN(Memory.getNow());
							TD.setX(x);
							TD.setY(y);
							DBAdapter.Insert_HSO_TOADO(TD);
							TD = mdb.get_MAX_STT_TOADO();
							list_toado.add(0, TD);
							mAdapter.notifyDataSetChanged();
							Intent i = new Intent();
							setResult(RESULT_OK, i);
							Obj_nhap_lieu NL = new Obj_nhap_lieu(Dialog_listview_ghichu.edt_ghichu.getText().toString(), Variables.NL_VITRI);
							if (mdb.chua_ton_tai_nhap_lieu(NL)){
								DBAdapter.Insert_NHAP_LIEU(NL);
							}
							mAdapter.notifyDataSetChanged();
							dialog_GC.dismiss();
							LENH =0;
						} catch (Exception e) {
							ThtShow.show_crouton_toast(
									TAB_BAN_DO.this, e.toString(),
									Style.ALERT);
						}
					}else{
						Length_text.alert_length(TAB_BAN_DO.this, dialog_GC.max_length);
					}
					
				}

					
			}
		});
		WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
		lp.copyFrom(dialog_GC.getWindow().getAttributes());
		lp.width = Tht_Screen.get_screen_width_percent(TAB_BAN_DO.this, 90);
		lp.height = Tht_Screen.get_screen_heigth_percent(TAB_BAN_DO.this, 90);
		dialog_GC.getWindow().setAttributes(lp);
	}
	 public void SHOW_DIALOG_SUA_TEN_DIEM(final Obj_HSO_TOADO HSH) {
		 LENH = Variables.SUA;
		 final List<Obj_nhap_lieu> list_nhap_lieu = mdb.get_list_NL(Variables.NL_VITRI);
		 	Adapter_nhap_lieu mAdapter_TT = new Adapter_nhap_lieu(list_nhap_lieu, TAB_BAN_DO.this, mdb);
			dialog_GC = new Dialog_listview_ghichu(TAB_BAN_DO.this,Length_text.ML_TEN_DIEM);
			dialog_GC.show();
			Dialog_listview_ghichu.lv_ghichu.setAdapter(mAdapter_TT);
			Dialog_listview_ghichu.edt_ghichu.setText(HSH.getTEN_DIEM());
			Dialog_listview_ghichu.edt_ghichu.setSelection(Dialog_listview_ghichu.edt_ghichu.getText().length());
			Dialog_listview_ghichu.lv_ghichu.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
						long arg3) {
					Dialog_listview_ghichu.edt_ghichu.setText(list_nhap_lieu.get(arg2).nhap_lieu);
					Dialog_listview_ghichu.edt_ghichu.setSelection(Dialog_listview_ghichu.edt_ghichu.getText().length());
				}
			});
			Dialog_listview_ghichu.btn_ok.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					if (Dialog_listview_ghichu.edt_ghichu.getText().length()<=dialog_GC.max_length){
						HSH.setTEN_DIEM(Dialog_listview_ghichu.edt_ghichu.getText().toString());
						mdb.update_HSO_TOADO(HSH);
						Obj_nhap_lieu NL = new Obj_nhap_lieu(Dialog_listview_ghichu.edt_ghichu.getText().toString(), Variables.NL_VITRI);
						if (mdb.chua_ton_tai_nhap_lieu(NL)){
							DBAdapter.Insert_NHAP_LIEU(NL);
						}
						mAdapter.notifyDataSetChanged();
						dialog_GC.dismiss();
						LENH =0;
					}else{
						Length_text.alert_length(TAB_BAN_DO.this, dialog_GC.max_length);
					}
						
				}
			});
			WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
			lp.copyFrom(dialog_GC.getWindow().getAttributes());
			lp.width = Tht_Screen.get_screen_width_percent(TAB_BAN_DO.this, 90);
			lp.height = Tht_Screen.get_screen_heigth_percent(TAB_BAN_DO.this, 90);
			dialog_GC.getWindow().setAttributes(lp);
		}
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == Utils.REQ_CODE) {
			if (resultCode == RESULT_OK) {
				Intent i = new Intent();
				setResult(RESULT_OK, i);
			}

		}

		super.onActivityResult(requestCode, resultCode, data);
	}
	
	// ban do
	@Override
	protected void onStart() {
		super.onStart();
		if (mGoogleApiClient != null) {
			mGoogleApiClient.connect();
		}
	}

	@Override
	protected void onResume() {
		super.onResume();

		checkPlayServices();

		// Resuming the periodic location updates
		if (mGoogleApiClient.isConnected() && mRequestingLocationUpdates) {
			startLocationUpdates();
		}
	}

	@Override
	protected void onStop() {
		super.onStop();
		if (mGoogleApiClient.isConnected()) {
			mGoogleApiClient.disconnect();
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
		stopLocationUpdates();
	}

	/**
	 * Method to display the location on UI
	 * */
	private void displayLocation() {

		mLastLocation = LocationServices.FusedLocationApi
				.getLastLocation(mGoogleApiClient);

		if (mLastLocation != null) {
			double latitude = mLastLocation.getLatitude();
			double longitude = mLastLocation.getLongitude();

			tv_X.setText(latitude + ", " + longitude);

		} else {

			tv_X.setText("(Couldn't get the location. Make sure location is enabled on the device)");
		}
	}

	/**
	 * Method to toggle periodic location updates
	 * */
	private void togglePeriodicLocationUpdates() {
		if (!mRequestingLocationUpdates) {
			// Changing the button text
//			btnStartLocationUpdates
//					.setText(getString(R.string.btn_stop_location_updates));

			mRequestingLocationUpdates = true;

			// Starting the location updates
			startLocationUpdates();

			Log.d(TAG, "Periodic location updates started!");

		} else {
			// Changing the button text
//			btnStartLocationUpdates
//					.setText(getString(R.string.btn_start_location_updates));

			mRequestingLocationUpdates = false;

			// Stopping the location updates
			stopLocationUpdates();

			Log.d(TAG, "Periodic location updates stopped!");
		}
	}

	/**
	 * Creating google api client object
	 * */
	protected synchronized void buildGoogleApiClient() {
		mGoogleApiClient = new GoogleApiClient.Builder(this)
				.addConnectionCallbacks(this)
				.addOnConnectionFailedListener(this)
				.addApi(LocationServices.API).build();
	}

	/**
	 * Creating location request object
	 * */
	protected void createLocationRequest() {
		mLocationRequest = new LocationRequest();
		mLocationRequest.setInterval(UPDATE_INTERVAL);
		mLocationRequest.setFastestInterval(FATEST_INTERVAL);
		mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
		mLocationRequest.setSmallestDisplacement(DISPLACEMENT);
	}

	/**
	 * Method to verify google play services on the device
	 * */
	private boolean checkPlayServices() {
		int resultCode = GooglePlayServicesUtil
				.isGooglePlayServicesAvailable(this);
		if (resultCode != ConnectionResult.SUCCESS) {
			if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
				GooglePlayServicesUtil.getErrorDialog(resultCode, this,
						PLAY_SERVICES_RESOLUTION_REQUEST).show();
			} else {
				Toast.makeText(getApplicationContext(),
						"This device is not supported.", Toast.LENGTH_LONG)
						.show();
				finish();
			}
			return false;
		}
		return true;
	}

	/**
	 * Starting the location updates
	 * */
	protected void startLocationUpdates() {

		LocationServices.FusedLocationApi.requestLocationUpdates(
				mGoogleApiClient, mLocationRequest, this);

	}

	/**
	 * Stopping location updates
	 */
	protected void stopLocationUpdates() {
		LocationServices.FusedLocationApi.removeLocationUpdates(
				mGoogleApiClient, this);
	}

	/**
	 * Google api callback methods
	 */
	@Override
	public void onConnectionFailed(ConnectionResult result) {
		Log.i(TAG, "Connection failed: ConnectionResult.getErrorCode() = "
				+ result.getErrorCode());
	}

	@Override
	public void onConnected(Bundle arg0) {

		// Once connected with google api, get the location
		displayLocation();

		if (mRequestingLocationUpdates) {
			startLocationUpdates();
		}
	}

	@Override
	public void onConnectionSuspended(int arg0) {
		mGoogleApiClient.connect();
	}

	@Override
	public void onLocationChanged(Location location) {
		// Assign the new location
		mLastLocation = location;

		Toast.makeText(getApplicationContext(), "Location changed!",
				Toast.LENGTH_SHORT).show();

		// Displaying the new location on UI
		displayLocation();
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}

		
	

}
