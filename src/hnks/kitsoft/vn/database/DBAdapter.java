package hnks.kitsoft.vn.database;

import hnks.kitsoft.utils.LabelFull;
import hnks.kitsoft.utils.Utils;
import hnks.kitsoft.vn.object.Obj_CANVAS;
import hnks.kitsoft.vn.object.Obj_DANHMUC_TRAM;
import hnks.kitsoft.vn.object.Obj_D_MAU;
import hnks.kitsoft.vn.object.Obj_D_NHAN_VIEN;
import hnks.kitsoft.vn.object.Obj_D_NHOM_VTU;
import hnks.kitsoft.vn.object.Obj_D_VATTU;
import hnks.kitsoft.vn.object.Obj_GHICHU;
import hnks.kitsoft.vn.object.Obj_HSO_CHIETTINH;
import hnks.kitsoft.vn.object.Obj_HSO_HINH;
import hnks.kitsoft.vn.object.Obj_HSO_TOADO;
import hnks.kitsoft.vn.object.Obj_HSO_VATTU_CTIET;
import hnks.kitsoft.vn.object.Obj_LIENKET_VATTU;
import hnks.kitsoft.vn.object.Obj_MAU_CANVAS;
import hnks.kitsoft.vn.object.Obj_MAU_HESO;
import hnks.kitsoft.vn.object.Obj_nhap_lieu;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import com.thtsoftlib.function.ThtDatabase;

public class DBAdapter {
	private DatabaseHelper mDbHelper;
	private static SQLiteDatabase mDB;

	// Tao Bang Khoa
	private static final String CREATE_NHANVIEN = "create table "
			+ LabelFull.TABLE_D_NHAN_VIEN+"("
			+ Utils.idNhanVien+" INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ Utils.MaNhanVien+" TEXT,"
			+ Utils.TenNhanVien+" TEXT,"
			+ Utils.UserName+" TEXT," 
			+ Utils.MatKhau+" TEXT," 
			+ Utils.MaDV+" TEXT,"
			+ Utils.IPLocal+" TEXT,"
			+ Utils.IPServer+" TEXT" 
			+ ");";
	private static final String CREATE_D_MAU = "create table "
			+ LabelFull.TABLE_D_MAU+"("
			+Utils.MAU +" TEXT,"
			+Utils.MA_DVIQLY +" TEXT,"
			+Utils.MA_VTU +" TEXT,"
			+Utils.SO_LUONG +" double"
			+ ");";
	private static final String CREATE_D_NHOM_VTU = "create table "
			+ LabelFull.TABLE_D_NHOM_VTU+"("
			+ Utils.NHOM+" INTEGER,"
			+ Utils.TEN_NHOM+" TEXT,"
			+ Utils.MA_DVIQLY+" TEXT"
			+ ")";
	private static final String CREATE_D_VATTU = "create table "
			+ LabelFull.TABLE_D_VATTU+"("
			+ Utils.MA_DVIQLY+" TEXT,"
			+ Utils.NHOM+" INTEGER,"
			+ Utils.MA_LOAI_CPHI+" TEXT,"
			+ Utils.MA_VTU +" TEXT,"
			+ Utils.TEN_VTU +" TEXT,"
			+ Utils.DINH_MUC_VT +" double,"
			+ Utils.DON_GIA +" INTEGER,"
			+ Utils.DVI_TINH +" TEXT,"
			+ Utils.KHU_VUC +" TEXT,"
			+ Utils.MA_DINHMUC +" TEXT,"
			+Obj_D_VATTU.tag_K1NC+" double,"
			+Obj_D_VATTU.tag_K2NC+" double,"
			+Obj_D_VATTU.tag_KMTC+" double,"
			+Obj_D_VATTU.tag_DG_NC+" INTEGER,"
			+Obj_D_VATTU.tag_DG_MTC+" INTEGER,"
			+Obj_D_VATTU.tag_DON_GIA_TG+" INTEGER"
			+ ")";
	private static final String CREATE_HSO_CHIETTINH = "create table "
			+ LabelFull.TABLE_HSO_CHIETTINH+"("
			+Utils.MA_DVIQLY +" TEXT,"
			+Utils.MA_YCAU_KNAI +" TEXT,"
			+Utils.TEN_KHANG +" TEXT,"
			+Utils.TEN_KHANG_KS +" TEXT,"
			+Utils.SO_NHA +" TEXT,"
			+Utils.SO_NHA_KS +" TEXT,"
			+Utils.DUONG_PHO +" TEXT,"
			+Utils.DUONG_PHO_KS +" TEXT,"
			+Utils.DIEN_THOAI +" TEXT,"
			+Utils.DIEN_THOAI_KS +" TEXT,"
			+Utils.NGAY_YCAU +" TEXT,"
			+Utils.BB_KSAT +" TEXT,"
			+Utils.MA_GCST +" TEXT,"
			+Utils.MA_GCSP +" TEXT,"
			+Utils.MA_LOAI_HSO +" TEXT,"
			+Utils.THOI_GIAN +" TEXT,"
			+Utils.DA_CHUYEN +" INTEGER,"
			+Utils.GHI_CHU +" TEXT,"
			+Utils.PT_TT +" double,"
			+Utils.PT_C +" double,"
			+Utils.PT_TL +" double,"
			+Utils.PT_K +" double,"
			+Utils.PT_VAT +" double,"
			+Utils.PT_NC +" double,"
			+Utils.PT_C1 +" double,"
			+Utils.PT_NC1 +" double,"
			+Utils.TENTRAM_BIENAP +" TEXT,"
			+Utils.MA_TRAM +" TEXT,"
			+Utils.CONG_SUAT_TRAM +" TEXT,"
			+Utils.DONGDIEN_DINHMUC +" TEXT,"
			+Utils.TAI_MAX +" TEXT,"
			+Utils.CONGTO_TRAI +" TEXT,"
			+Utils.CONGTO_PHAI +" TEXT,"
			+Utils.KC_CONGTO_DEN_TRAM +" TEXT,"
			+Utils.KC_CONGTO_DEN_LUOI +" TEXT,"
			+Utils.TRU +" TEXT,"
			+Utils.CONGSUAT_THUC_TE_KW +" TEXT,"
			+Utils.CONGSUAT_THUC_TE_A +" TEXT,"
			+Utils.MUC_DICH_SU_DUNG +" TEXT,"
			+Utils.MA_KHACHHANG +" TEXT,"
			+Utils.CONGSUAT_DENGHI_A +" TEXT,"
			+Utils.CONGSUAT_DENGHI_V +" TEXT,"
			+Utils.CONGSUAT_DENGHI_PHA +" TEXT,"
			+Utils.LY_DO +" TEXT,"
			+Utils.TINHTRANG_CONG_TO +" TEXT,"
			+Utils.TINHTRANG_CONGTO_CO +" TEXT,"
			+Utils.TINHTRANG_TRONGAI +" TEXT,"
			+Utils.VITRITREO +" TEXT,"
			+Utils.LOAI_TAI +" TEXT,"
			+Utils.MASO_STT +" TEXT,"
			+Utils.DA_XONG +" INTEGER, "
			+Utils.SO_HO +" TEXT,"
			+Utils.NGAY_KS +" TEXT,"
			+Utils.Xt +" double,"
			+Utils.Yt +" double,"
			+Utils.Xp +" double,"
			+Utils.Yp +" double,"
			+Utils.THUYET_TRINH +" TEXT,"
			+Utils.LOAI_XAY_DUNG +" TEXT,"
			+Utils.LOAI_NHA +" TEXT,"
			+Utils.DC_GANDIEN +" TEXT,"
			+Utils.PHUTAI_A +" TEXT,"
			+Utils.PHUTAI_B +" TEXT,"
			+Utils.PHUTAI_C +" TEXT,"
			+Utils.DV_CAP_VTU +" TEXT,"
			+Utils.DV_THICONG +" TEXT,"
			+Utils.DV_THIETKE +" TEXT,"
			+Utils.TTRANG_KO_CONGTO +" TEXT"
			+ ")";

	private static final String CREATE_HSO_VATTU_CTIET = "create table "
			+ LabelFull.TABLE_HSO_VATTU_CTIET+"("
			+ Utils.MA_DVIQLY+" TEXT,"
			+ Utils.MA_LOAI_TTOAN+" INTEGER,"
			+ Utils.MA_YCAU_KNAI+" TEXT,"
			+ Utils.MA_VTU+" TEXT,"
			+ Utils.TEN_VTU+" TEXT,"
			+ Utils.DON_GIA+" INTEGER,"
			+ Utils.SO_LUONG+" double,"
			+ Utils.THOI_GIAN+" TEXT,"
			+ Utils.MA_LOAI_CPHI+" TEXT,"
			+Obj_HSO_VATTU_CTIET.tag_K1NC+" double,"
			+Obj_HSO_VATTU_CTIET.tag_K2NC+" double,"
			+Obj_HSO_VATTU_CTIET.tag_KMTC+" double,"
			+Obj_HSO_VATTU_CTIET.tag_DG_NC+" INTEGER,"
			+Obj_HSO_VATTU_CTIET.tag_DG_MTC+" INTEGER"
			+ ")";
	private static final String CREATE_HSO_HINH = "create table "
			+ LabelFull.TABLE_HSO_HINH+"("
			+ Utils.MA_DVIQLY+" TEXT,"
			+ Utils.MA_YCAU_KNAI+" TEXT,"
			+ Utils.STT+" INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ Utils.TEN_HINH+" TEXT,"
			+ Utils.HINH+" Blob,"
			+ Utils.THOI_GIAN+" TEXT,"
			+ Utils.DA_CHUYEN+" INTEGER,"
			+ Utils.MA_LOAI_HINH+" TEXT"
			+ ")";
	private static final String CREATE_HSO_LOAI_HINH = "create table "
			+ LabelFull.TABLE_HSO_LOAI_HINH+"("
			+ Utils.MA_LOAI_HINH+" TEXT,"
			+ Utils.TEN_LOAI_HINH+" TEXT"
			+ ")";
	private static final String CREATE_HSO_TOADO = "create table "
			+ LabelFull.TABLE_HSO_TOADO+"("
			+ Utils.MA_DVIQLY+" TEXT,"
			+ Utils.MA_YCAU_KNAI+" TEXT,"
			+ Utils.STT+" INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ Utils.TEN_DIEM+" TEXT,"
			+ Utils.X+" double,"
			+ Utils.Y+" double,"
			+ Utils.THOI_GIAN+" TEXT"
			+ ")";
	private static final String CREATE_HSO_GHICHU = "create table "
			+ LabelFull.TABLE_GHICHU+"("
			+ Utils.MA_DVIQLY+" TEXT,"
			+ Utils.MA_YCAU_KNAI+" TEXT,"
			+ Utils.STT+" INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ Utils.GHI_CHU+" TEXT,"
			+ Utils.THOI_GIAN+" TEXT,"
			+ Utils.DA_CHUYEN+" INTEGER,"
			+ Utils.THUOC_TINH+" INTEGER"
			+ ")";

	private static final String CREATE_HSO_CANVAS = "create table "
			+ LabelFull.TABLE_CANVAS+"("
			+ Utils.MA_DVIQLY+" TEXT,"
			+ Utils.MA_YCAU_KNAI+" TEXT,"
			+ Utils.STT+" INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ Utils.TEN+" TEXT,"
			+ Utils.LENH+" INTEGER,"
			+ Utils.X1+" double,"
			+ Utils.Y1+" double,"
			+ Utils.X2+" double,"
			+ Utils.Y2+" double,"
			+ Utils.X3+" double,"
			+ Utils.Y3+" double,"
			+ Utils.HINH+" INTEGER,"
			+ Utils.THUOC_TINH+" INTEGER"
			+ ")";
	private static final String CREATE_MAU_CANVAS = "create table "
			+ LabelFull.TABLE_MAU_CANVAS+"("
			+ Utils.MA_DVIQLY+" TEXT,"
			+ Utils.id_MAU+" INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ Utils.TEN_MAU+" TEXT,"
			+ Utils.LENH+" INTEGER,"
			+ Utils.X1+" double,"
			+ Utils.Y1+" double,"
			+ Utils.X2+" double,"
			+ Utils.Y2+" double,"
			+ Utils.X3+" double,"
			+ Utils.Y3+" double,"
			+ Utils.CHU+" TEXT,"
			+ Utils.HINH+" INTEGER,"
			+ Utils.THUOC_TINH+" INTEGER"
			+")";

	private static final String CREATE_LIENKET_VATTU = "create table "
			+ LabelFull.TABLE_LIENKET_VATTU+"("
			+ Utils.MA_VTU+" TEXT,"
			+ Utils.MA_NC_THAO+" TEXT,"
			+ Utils.MA_NC_LAP+" TEXT"
			+ ")";
	private static final String CREATE_DANHMUC_TRAM = "create table "
			+ LabelFull.TABLE_DANHMUC_TRAM+"("
			+ Utils.MA_DVIQLY+" TEXT,"
			+ Utils.MA_TRAM+" TEXT,"
			+ Utils.TEN_TRAM+" TEXT,"
			+ Utils.DINH_DANH+" TEXT, "
			+ Utils.CSUAT_TRAM+" TEXT,"
			+ Utils.MA_CAPDA_RA+" TEXT,"
			+ Utils.DONGDIEN+" TEXT,"
			+ Utils.X+" double,"
			+ Utils.Y+" double,"
			+ Utils.GHICHU+" TEXT"
			+ ")";
	private static final String CREATE_MAU_HESO = "create table "
			+ LabelFull.TABLE_MAU_HESO+"("
			+ Utils.MA_DVIQLY+" TEXT,"
			+ Utils.id_HESO+" INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ Utils.TEN_HESO+" TEXT,"
			+ Utils.PT_TT+" double,"
			+ Utils.PT_C+" double,"
			+ Utils.PT_TL+" double,"
			+ Utils.PT_K+" double,"
			+ Utils.PT_VAT+" double,"
			+ Utils.PT_NC+" double,"
			+ Utils.PT_C1+" double,"
			+ Utils.PT_NC1+" double"
			+ ")";
	private static final String CREATE_NHAP_LIEU = "create table "
			+ LabelFull.TABLE_NHAP_LIEU+"("
			+ Utils.STT+" INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ Utils.nhap_lieu+" TEXT,"
			+ Utils.loai_nhap_lieu+" INTEGER"
			+ ")";
	private final Context mContext;
	private static final int DATABASE_VERSION = 4;
	private static final String DATABASE_NAME = "ChietTinh";

//	private static void NangCap(String cmd) {
//		try {
//			mDB.execSQL(cmd);
//		} catch (Exception e) {
//			//int j = 0;
//		}
//	}

	public DBAdapter open() {
		mDbHelper = new DatabaseHelper(mContext, DATABASE_NAME, null,
				DATABASE_VERSION);
		mDB = mDbHelper.getWritableDatabase();

//		NangCap("ALTER TABLE HSO_CHIETTINH ADD COLUMN DA_XONG INTEGER NULL");
//		NangCap("ALTER TABLE HSO_CHIETTINH ADD COLUMN SO_HO String NULL");
//		NangCap("ALTER TABLE HSO_CHIETTINH ADD COLUMN NGAY_KS String NULL");
//		NangCap("ALTER TABLE HSO_CHIETTINH ADD COLUMN Xt double DEFAULT 0 NULL");
//		NangCap("ALTER TABLE HSO_CHIETTINH ADD COLUMN Yt double DEFAULT 0 NULL");
//		NangCap("ALTER TABLE HSO_CHIETTINH ADD COLUMN Xp double DEFAULT 0 NULL");
//		NangCap("ALTER TABLE HSO_CHIETTINH ADD COLUMN Yp double DEFAULT 0 NULL");
//
//		NangCap(CREATE_LIENKET_VATTU);
//		NangCap(CREATE_DANHMUC_TRAM);

		return this;
	}

	public void close() {
		mDbHelper.close();
		mDB.close();
	}

	public DBAdapter(Context mContext) {
		this.mContext = mContext;
	}

	private static class DatabaseHelper extends SQLiteOpenHelper {
		DatabaseHelper(Context context, String Name, CursorFactory factory,
				int version) {
			super(context, Name, factory, version);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(CREATE_NHANVIEN);
			db.execSQL(CREATE_D_MAU);
			db.execSQL(CREATE_D_NHOM_VTU);
			db.execSQL(CREATE_D_VATTU);
			db.execSQL(CREATE_HSO_CHIETTINH);
			db.execSQL(CREATE_HSO_VATTU_CTIET);
			db.execSQL(CREATE_HSO_HINH);
			db.execSQL(CREATE_HSO_LOAI_HINH);
			db.execSQL(CREATE_HSO_TOADO);
			db.execSQL(CREATE_HSO_GHICHU);
			db.execSQL(CREATE_HSO_CANVAS);
			db.execSQL(CREATE_MAU_CANVAS);
			db.execSQL(CREATE_LIENKET_VATTU);
			db.execSQL(CREATE_DANHMUC_TRAM);
			db.execSQL(CREATE_MAU_HESO);
			db.execSQL(CREATE_NHAP_LIEU);
			

		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("DROP TABLE IF EXISTS "+LabelFull.TABLE_D_NHAN_VIEN);
			db.execSQL("DROP TABLE IF EXISTS "+LabelFull.TABLE_HSO_LOAI_HINH);
			db.execSQL("DROP TABLE IF EXISTS "+LabelFull.TABLE_D_NHOM_VTU);
			db.execSQL("DROP TABLE IF EXISTS "+LabelFull.TABLE_D_VATTU);
			db.execSQL("DROP TABLE IF EXISTS "+LabelFull.TABLE_HSO_CHIETTINH);
			db.execSQL("DROP TABLE IF EXISTS "+LabelFull.TABLE_HSO_VATTU_CTIET);
			db.execSQL("DROP TABLE IF EXISTS "+LabelFull.TABLE_HSO_HINH);
			db.execSQL("DROP TABLE IF EXISTS "+LabelFull.TABLE_HSO_LOAI_HINH);
			db.execSQL("DROP TABLE IF EXISTS "+LabelFull.TABLE_HSO_TOADO);
			db.execSQL("DROP TABLE IF EXISTS "+LabelFull.TABLE_GHICHU);
			db.execSQL("DROP TABLE IF EXISTS "+LabelFull.TABLE_CANVAS);
			db.execSQL("DROP TABLE IF EXISTS "+LabelFull.TABLE_MAU_CANVAS);
			db.execSQL("DROP TABLE IF EXISTS "+LabelFull.TABLE_LIENKET_VATTU);
			db.execSQL("DROP TABLE IF EXISTS "+LabelFull.TABLE_DANHMUC_TRAM);
			db.execSQL("DROP TABLE IF EXISTS "+LabelFull.TABLE_MAU_HESO);
			db.execSQL("DROP TABLE IF EXISTS "+LabelFull.TABLE_NHAP_LIEU);
			onCreate(db);

		}
	}

	// NhanVien
	public static void Insert_nhanVien(Obj_D_NHAN_VIEN DNV) {
		mDB.beginTransaction();
		try {
			ContentValues initVal = new ContentValues();
			initVal.put(Utils.MaNhanVien, DNV.MaNhanVien);
			initVal.put(Utils.TenNhanVien, DNV.TenNhanVien);
			initVal.put(Utils.UserName, DNV.UserName);
			initVal.put(Utils.MatKhau, DNV.MatKhau);
			initVal.put(Utils.MaDV, DNV.MaDV);
			initVal.put(Utils.IPLocal, DNV.IPLocal);
			initVal.put(Utils.IPServer, DNV.IPServer);
			mDB.insert(LabelFull.TABLE_D_NHAN_VIEN, null, initVal);
			mDB.setTransactionSuccessful();
		} catch (Exception e) {
			
		}finally{
			mDB.endTransaction();
		}
		
	}
	// HSO_CHIETTINH
		public static void Insert_HSO_CHIETTINH(Obj_HSO_CHIETTINH HSCT) {
			mDB.beginTransaction();
			try {
				ContentValues initVal = new ContentValues();
				initVal.put(Utils.MA_DVIQLY, HSCT.MA_DVIQLY);
				initVal.put(Utils.MA_YCAU_KNAI, HSCT.MA_YCAU_KNAI);
				initVal.put(Utils.TEN_KHANG, HSCT.TEN_KHANG);
				initVal.put(Utils.TEN_KHANG_KS, HSCT.TEN_KHANG_KS);
				initVal.put(Utils.SO_NHA, HSCT.SO_NHA);
				initVal.put(Utils.SO_NHA_KS, HSCT.SO_NHA_KS);
				initVal.put(Utils.DUONG_PHO, HSCT.DUONG_PHO);
				initVal.put(Utils.DUONG_PHO_KS, HSCT.DUONG_PHO_KS);
				initVal.put(Utils.DIEN_THOAI, HSCT.DIEN_THOAI);
				initVal.put(Utils.DIEN_THOAI_KS, HSCT.DIEN_THOAI_KS);
				initVal.put(Utils.NGAY_YCAU, HSCT.NGAY_YCAU);
				initVal.put(Utils.BB_KSAT, HSCT.BB_KSAT);
				initVal.put(Utils.MA_GCST, HSCT.MA_GCST);
				initVal.put(Utils.MA_GCSP, HSCT.MA_GCSP);
				initVal.put(Utils.MA_LOAI_HSO, HSCT.MA_LOAI_HSO);
				initVal.put(Utils.THOI_GIAN, HSCT.THOI_GIAN);
				initVal.put(Utils.DA_CHUYEN, HSCT.DA_CHUYEN);
				initVal.put(Utils.GHI_CHU, HSCT.GHI_CHU);
				initVal.put(Utils.PT_TT, HSCT.PT_TT);
				initVal.put(Utils.PT_C, HSCT.PT_C);
				initVal.put(Utils.PT_TL, HSCT.PT_TL);
				initVal.put(Utils.PT_K, HSCT.PT_K);
				initVal.put(Utils.PT_VAT, HSCT.PT_VAT);
				initVal.put(Utils.PT_NC, HSCT.PT_NC);
				initVal.put(Utils.PT_C1, HSCT.PT_C1);
				initVal.put(Utils.PT_NC1, HSCT.PT_NC1);
				initVal.put(Utils.TENTRAM_BIENAP, HSCT.TENTRAM_BIENAP);
				initVal.put(Utils.MA_TRAM, HSCT.MA_TRAM);
				initVal.put(Utils.CONG_SUAT_TRAM, HSCT.CONG_SUAT_TRAM);
				initVal.put(Utils.DONGDIEN_DINHMUC, HSCT.DONGDIEN_DINHMUC);
				initVal.put(Utils.TAI_MAX, HSCT.TAI_MAX);
				initVal.put(Utils.CONGTO_TRAI, HSCT.CONGTO_TRAI);
				initVal.put(Utils.CONGTO_PHAI, HSCT.CONGTO_PHAI);
				initVal.put(Utils.KC_CONGTO_DEN_TRAM, HSCT.KC_CONGTO_DEN_TRAM);
				initVal.put(Utils.KC_CONGTO_DEN_LUOI, HSCT.KC_CONGTO_DEN_LUOI);
				initVal.put(Utils.TRU, HSCT.TRU);
				initVal.put(Utils.CONGSUAT_THUC_TE_KW, HSCT.CONGSUAT_THUC_TE_KW);
				initVal.put(Utils.CONGSUAT_THUC_TE_A, HSCT.CONGSUAT_THUC_TE_A);
				initVal.put(Utils.MUC_DICH_SU_DUNG, HSCT.MUC_DICH_SU_DUNG);
				initVal.put(Utils.MA_KHACHHANG, HSCT.MA_KHACHHANG);
				initVal.put(Utils.CONGSUAT_DENGHI_A, HSCT.CONGSUAT_DENGHI_A);
				initVal.put(Utils.CONGSUAT_DENGHI_V, HSCT.CONGSUAT_DENGHI_V);
				initVal.put(Utils.CONGSUAT_DENGHI_PHA, HSCT.CONGSUAT_DENGHI_PHA);
				initVal.put(Utils.LY_DO, HSCT.LY_DO);
				initVal.put(Utils.TINHTRANG_CONG_TO, HSCT.TINHTRANG_CONG_TO);
				initVal.put(Utils.TINHTRANG_CONGTO_CO, HSCT.TINHTRANG_CONGTO_CO);
				initVal.put(Utils.TINHTRANG_TRONGAI, HSCT.TINHTRANG_TRONGAI);
				initVal.put(Utils.VITRITREO, HSCT.VITRITREO);
				initVal.put(Utils.LOAI_TAI, HSCT.LOAI_TAI);
				initVal.put(Utils.MASO_STT, HSCT.MASO_STT);
				initVal.put(Utils.DA_XONG, HSCT.DA_XONG);
				initVal.put(Utils.SO_HO, HSCT.SO_HO);
				initVal.put(Utils.NGAY_KS, HSCT.NGAY_KS);
				initVal.put(Utils.Xt, HSCT.Xt);
				initVal.put(Utils.Yt, HSCT.Yt);
				initVal.put(Utils.Xp, HSCT.Xp);
				initVal.put(Utils.Yp, HSCT.Yp);
				initVal.put(Utils.THUYET_TRINH, HSCT.THUYET_TRINH);
				initVal.put(Utils.LOAI_XAY_DUNG, HSCT.LOAI_XAY_DUNG);
				initVal.put(Utils.LOAI_NHA, HSCT.LOAI_NHA);
				initVal.put(Utils.DC_GANDIEN, HSCT.DC_GANDIEN);
				initVal.put(Utils.PHUTAI_A, HSCT.PHUTAI_A);
				initVal.put(Utils.PHUTAI_B, HSCT.PHUTAI_B);
				initVal.put(Utils.PHUTAI_C, HSCT.PHUTAI_C);
				initVal.put(Utils.DV_CAP_VTU, HSCT.DV_CAP_VTU);
				initVal.put(Utils.DV_THICONG, HSCT.DV_THICONG);
				initVal.put(Utils.DV_THIETKE, HSCT.DV_THIETKE);
				initVal.put(Utils.TTRANG_KO_CONGTO, HSCT.TTRANG_KO_CONGTO);
				mDB.insert(LabelFull.TABLE_HSO_CHIETTINH, null, initVal);
				mDB.setTransactionSuccessful();
			} catch (Exception e) {
				
			}finally{
				mDB.endTransaction();
			}
			
		}
		// D_MAU
		public static void Insert_D_MAU(Obj_D_MAU DM) {
			mDB.beginTransaction();
//			delete_MAU_VATTU(DM.getMAU());
			try {
				ContentValues initVal = new ContentValues();
				initVal.put(Utils.MAU, DM.MAU);
				initVal.put(Utils.MA_DVIQLY, DM.MA_DVIQLY);
				initVal.put(Utils.MA_VTU, DM.MA_VTU);
				initVal.put(Utils.SO_LUONG, DM.SO_LUONG);
				mDB.insert(LabelFull.TABLE_D_MAU, null, initVal);
				mDB.setTransactionSuccessful();
			} catch (Exception e) {
				
			}finally{
				mDB.endTransaction();
			}
			
		}
		// D_NHOM_VTU
		public static void Insert_D_NHOM_VTU(Obj_D_NHOM_VTU DNVT) {
			mDB.beginTransaction();
			try {
				ContentValues initVal = new ContentValues();
				initVal.put(Utils.NHOM, DNVT.NHOM);
				initVal.put(Utils.TEN_NHOM, DNVT.TEN_NHOM);
				initVal.put(Utils.MA_DVIQLY, DNVT.MA_DVIQLY);
				mDB.insert(LabelFull.TABLE_D_NHOM_VTU, null, initVal);
				mDB.setTransactionSuccessful();
			} catch (Exception e) {
				
			}finally{
				mDB.endTransaction();
			}
			
		}
		// D_VATTU
		public static void Insert_D_VATTU(Obj_D_VATTU VT) {
			mDB.beginTransaction();
			try {
				ContentValues initVal = new ContentValues();
				initVal.put(Utils.MA_DVIQLY, VT.MA_DVIQLY);
				initVal.put(Utils.NHOM, VT.NHOM);
				initVal.put(Utils.MA_LOAI_CPHI, VT.MA_LOAI_CPHI);
				initVal.put(Utils.MA_VTU, VT.MA_VTU);
				initVal.put(Utils.TEN_VTU, VT.TEN_VTU);
				initVal.put(Utils.DINH_MUC_VT, VT.DINH_MUC_VT);
				initVal.put(Utils.DON_GIA, VT.DON_GIA);
				initVal.put(Utils.DVI_TINH, VT.DVI_TINH);
				initVal.put(Utils.KHU_VUC, VT.KHU_VUC);
				initVal.put(Utils.MA_DINHMUC, VT.MA_DINHMUC);
				mDB.insert(LabelFull.TABLE_D_VATTU, null, initVal);
				mDB.setTransactionSuccessful();
			} catch (Exception e) {
				
			}finally{
				mDB.endTransaction();
			}
//			
		}
		// HSO_VATTU_CTIET
		public static void Insert_HSO_VATTU_CTIET(Obj_HSO_VATTU_CTIET HSVTCT) {
			mDB.beginTransaction();
			try {
				ContentValues initVal = new ContentValues();
				initVal.put(Utils.MA_DVIQLY, HSVTCT.MA_DVIQLY);
				initVal.put(Utils.MA_LOAI_TTOAN, HSVTCT.MA_LOAI_TTOAN);
				initVal.put(Utils.MA_YCAU_KNAI, HSVTCT.MA_YCAU_KNAI);
				initVal.put(Utils.MA_VTU, HSVTCT.MA_VTU);
				initVal.put(Utils.TEN_VTU, HSVTCT.TEN_VTU);
				initVal.put(Utils.DON_GIA, HSVTCT.DON_GIA);
				initVal.put(Utils.SO_LUONG, HSVTCT.SO_LUONG);
				initVal.put(Utils.THOI_GIAN, HSVTCT.THOI_GIAN);
				initVal.put(Utils.MA_LOAI_CPHI, HSVTCT.MA_LOAI_CPHI);
				mDB.insert(LabelFull.TABLE_HSO_VATTU_CTIET, null, initVal);
				mDB.setTransactionSuccessful();
			} catch (Exception e) {
				
			}finally{
				mDB.endTransaction();
			}
			
		}
		// HSO_HINH
		public void Insert_HSO_HINH(Obj_HSO_HINH H) {
			mDB.beginTransaction();
			try {
				ContentValues initVal = new ContentValues();
				initVal.put(Utils.MA_DVIQLY, H.MA_DVIQLY);
				initVal.put(Utils.MA_YCAU_KNAI, H.MA_YCAU_KNAI);
				initVal.put(Utils.TEN_HINH, H.TEN_HINH);
				initVal.put(Utils.HINH, H.HINH);
				initVal.put(Utils.THOI_GIAN, H.THOI_GIAN);
				initVal.put(Utils.DA_CHUYEN, H.DA_CHUYEN);
				initVal.put(Utils.MA_LOAI_HINH, H.MA_LOAI_HINH);
				mDB.insert(LabelFull.TABLE_HSO_HINH, null, initVal);
				mDB.setTransactionSuccessful();
			} catch (Exception e) {
				
			}finally{
				mDB.endTransaction();
			}
			
		}
		// HSO_TOADO
		public static void Insert_HSO_TOADO(Obj_HSO_TOADO TD) {
			mDB.beginTransaction();
			try {
				ContentValues initVal = new ContentValues();
				initVal.put(Utils.MA_DVIQLY, TD.MA_DVIQLY);
				initVal.put(Utils.MA_YCAU_KNAI, TD.MA_YCAU_KNAI);
				initVal.put(Utils.TEN_DIEM, TD.TEN_DIEM);
				initVal.put(Utils.X, TD.X);
				initVal.put(Utils.Y, TD.Y);
				initVal.put(Utils.THOI_GIAN, TD.THOI_GIAN);
				mDB.insert(LabelFull.TABLE_HSO_TOADO, null, initVal);
				mDB.setTransactionSuccessful();
			} catch (Exception e) {
				
			}finally{
				mDB.endTransaction();
			}
			
		}
		// HSO_LOAI_HINH
		public static void Insert_HSO_LOAI_HINH(String MA_LOAI_HINH,
				String TEN_LOAI_HINH) {
			mDB.beginTransaction();
			try {
				ContentValues initVal = new ContentValues();
				initVal.put(Utils.MA_LOAI_HINH, MA_LOAI_HINH);
				initVal.put(Utils.TEN_LOAI_HINH, TEN_LOAI_HINH);
				mDB.insert(LabelFull.TABLE_HSO_LOAI_HINH, null, initVal);
				mDB.setTransactionSuccessful();
			} catch (Exception e) {
				
			}finally{
				mDB.endTransaction();
			}
			
		}
		// danh muc tram
		public static void Insert_DANHMUC_TRAM(Obj_DANHMUC_TRAM DMT) {
			mDB.beginTransaction();
			try {
				ContentValues initVal = new ContentValues();
				initVal.put(Utils.MA_DVIQLY, DMT.MA_DVIQLY);
				initVal.put(Utils.MA_TRAM, DMT.MA_TRAM);
				initVal.put(Utils.TEN_TRAM, DMT.TEN_TRAM);
				initVal.put(Utils.DINH_DANH, DMT.DINH_DANH);
				initVal.put(Utils.CSUAT_TRAM, DMT.CSUAT_TRAM);
				initVal.put(Utils.MA_CAPDA_RA, DMT.MA_CAPDA_RA);
				initVal.put(Utils.DONGDIEN, DMT.DONGDIEN);
				initVal.put(Utils.X, DMT.X);
				initVal.put(Utils.Y, DMT.Y);
				initVal.put(Utils.GHICHU, DMT.GHICHU);
				mDB.insert(LabelFull.TABLE_DANHMUC_TRAM, null, initVal);
				mDB.setTransactionSuccessful();
			} catch (Exception e) {
				
			}finally{
				mDB.endTransaction();
			}
			
		}
		// lien ket
		public static void Insert_LIENKET(Obj_LIENKET_VATTU LK) {
			mDB.beginTransaction();
			try {
				ContentValues initVal = new ContentValues();
				initVal.put(Utils.MA_VTU, LK.MA_VTU);
				initVal.put(Utils.MA_NC_THAO, LK.MA_NC_THAO);
				initVal.put(Utils.MA_NC_LAP, LK.MA_NC_LAP);
				mDB.insert(LabelFull.TABLE_LIENKET_VATTU, null, initVal);
				mDB.setTransactionSuccessful();
			} catch (Exception e) {
				
			}finally{
				mDB.endTransaction();
			}
			
		}
		// HSO_GHICHU
		public static long Insert_HSO_GHICHU(Obj_GHICHU GC) {
			ContentValues initVal = new ContentValues();
			initVal.put(Utils.MA_DVIQLY, GC.MA_DVIQLY);
			initVal.put(Utils.MA_YCAU_KNAI, GC.MA_YCAU_KNAI);
			initVal.put(Utils.GHI_CHU, GC.GHI_CHU);
			initVal.put(Utils.THOI_GIAN, GC.THOI_GIAN);
			initVal.put(Utils.DA_CHUYEN, GC.DA_CHUYEN);
			initVal.put(Utils.THUOC_TINH, GC.THUOC_TINH);
			return mDB.insert(LabelFull.TABLE_GHICHU, null, initVal);
		}
		// HSO_CANVAS
		public static void Insert_HSO_CANVAS(Obj_CANVAS CVS) {
			mDB.beginTransaction();
			try {
				ContentValues initVal = new ContentValues();
				initVal.put(Utils.MA_DVIQLY, CVS.MA_DVIQLY);
				initVal.put(Utils.MA_YCAU_KNAI, CVS.MA_YCAU_KNAI);
				initVal.put(Utils.TEN, CVS.TEN);
				initVal.put(Utils.LENH, CVS.LENH);
				initVal.put(Utils.X1, CVS.X1);
				initVal.put(Utils.Y1, CVS.Y1);
				initVal.put(Utils.X2, CVS.X2);
				initVal.put(Utils.Y2, CVS.Y2);
				initVal.put(Utils.X3, CVS.X3);
				initVal.put(Utils.Y3, CVS.Y3);
				initVal.put(Utils.HINH, CVS.HINH);
				initVal.put(Utils.THUOC_TINH, CVS.THUOC_TINH);
				mDB.insert(LabelFull.TABLE_CANVAS, null, initVal);
				mDB.setTransactionSuccessful();
			} catch (Exception e) {
				
			}finally{
				mDB.endTransaction();
			}
			
		}
		// mau canvas
		public static void Insert_MAU_CANVAS(Obj_MAU_CANVAS MCV) {
			mDB.beginTransaction();
			try {
				ContentValues initVal = new ContentValues();
				initVal.put(Utils.MA_DVIQLY, MCV.MA_DVIQLY);
				initVal.put(Utils.TEN_MAU, MCV.TEN_MAU);
				initVal.put(Utils.LENH, MCV.LENH);
				initVal.put(Utils.X1, MCV.X1);
				initVal.put(Utils.Y1, MCV.Y1);
				initVal.put(Utils.X2, MCV.X2);
				initVal.put(Utils.Y2, MCV.Y2);
				initVal.put(Utils.X3, MCV.X3);
				initVal.put(Utils.Y3, MCV.Y3);
				initVal.put(Utils.CHU, MCV.CHU);
				initVal.put(Utils.HINH, MCV.HINH);
				mDB.insert(LabelFull.TABLE_MAU_CANVAS, null, initVal);
				mDB.setTransactionSuccessful();
			} catch (Exception e) {
				
			}finally{
				mDB.endTransaction();
			}
			
		}
		// MAU_HESO
		public static void Insert_MAU_HESO(Obj_MAU_HESO MHS) {
			mDB.beginTransaction();
			try {
				ContentValues initVal = new ContentValues();
				initVal.put(Utils.MA_DVIQLY, MHS.MA_DVIQLY);
				initVal.put(Utils.TEN_HESO, MHS.TEN_HESO);
				initVal.put(Utils.PT_TT, MHS.PT_TT);
				initVal.put(Utils.PT_C, MHS.PT_C);
				initVal.put(Utils.PT_TL, MHS.PT_TL);
				initVal.put(Utils.PT_K, MHS.PT_K);
				initVal.put(Utils.PT_VAT, MHS.PT_VAT);
				initVal.put(Utils.PT_NC, MHS.PT_NC);
				initVal.put(Utils.PT_C1, MHS.PT_C1);
				initVal.put(Utils.PT_NC1, MHS.PT_NC1);
				mDB.insert(LabelFull.TABLE_MAU_HESO, null, initVal);
				mDB.setTransactionSuccessful();
			} catch (Exception e) {
				
			}finally{
				mDB.endTransaction();
			}
			
		}
		// nhap lieu
		public static void Insert_NHAP_LIEU(Obj_nhap_lieu NL) {
			mDB.beginTransaction();
			try {
				if (NL.nhap_lieu.length()>0 & NL.loai_nhap_lieu>0){
					ContentValues initVal = new ContentValues();
					initVal.put(Utils.nhap_lieu,NL.nhap_lieu);
					initVal.put(Utils.loai_nhap_lieu,NL.loai_nhap_lieu);
					mDB.insert(LabelFull.TABLE_NHAP_LIEU, null, initVal);
				}
				mDB.setTransactionSuccessful();
			} catch (Exception e) {
				
			}finally{
				mDB.endTransaction();
			}
			
			
		}
		
		/*
		 * update
		 */

	public long update_nhanVien(Obj_D_NHAN_VIEN DNV) {
		ContentValues initVal = new ContentValues();
		initVal.put(Utils.UserName, DNV.UserName);
		if(!DNV.equals("QT")){
			initVal.put(Utils.MatKhau, DNV.MatKhau);
		}
		initVal.put(Utils.MaDV, DNV.MaDV);
		initVal.put(Utils.IPLocal, DNV.IPLocal);
		initVal.put(Utils.IPServer, DNV.IPServer);
		return mDB.update(LabelFull.TABLE_D_NHAN_VIEN, initVal, null, null);
	}
	public long update_D_MAU(String MAU, String MA_DVIQLY, String MA_VTU,
			double SO_LUONG) {
		ContentValues initVal = new ContentValues();
		initVal.put(Utils.MAU, MAU);
		initVal.put(Utils.MA_DVIQLY, MA_DVIQLY);
		initVal.put(Utils.MA_VTU, MA_VTU);
		initVal.put(Utils.SO_LUONG, SO_LUONG);
		return mDB.update(LabelFull.TABLE_D_MAU, initVal, "MAU=?",
				new String[] { String.valueOf(MAU) });
	}
	public long update_D_NHOM_VTU(int NHOM, String TEN_NHOM) {
		ContentValues initVal = new ContentValues();
		initVal.put("TEN_NHOM", TEN_NHOM);
		return mDB.update(LabelFull.TABLE_D_NHOM_VTU, initVal, "NHOM=?",
				new String[] { String.valueOf(NHOM) });
	}
	public long update_D_VATTU(String MA_DVIQLY, int NHOM, String MA_LOAI_CPHI,
			String MA_VTU, String TEN_VTU, double DINH_MUC_VT, int DON_GIA,
			String DVI_TINH, String KHU_VUC, String MA_DINHMUC) {
		ContentValues initVal = new ContentValues();
		initVal.put(Utils.MA_DVIQLY, MA_DVIQLY);
		initVal.put(Utils.NHOM, NHOM);
		initVal.put(Utils.MA_LOAI_CPHI, MA_LOAI_CPHI);
		initVal.put(Utils.MA_VTU, MA_VTU);
		initVal.put(Utils.TEN_VTU, TEN_VTU);
		initVal.put(Utils.DINH_MUC_VT, DINH_MUC_VT);
		initVal.put(Utils.DON_GIA, DON_GIA);
		initVal.put(Utils.DVI_TINH, DVI_TINH);
		initVal.put(Utils.KHU_VUC, KHU_VUC);
		initVal.put(Utils.MA_DINHMUC, MA_DINHMUC);
		return mDB.update(LabelFull.TABLE_D_VATTU, initVal, "MA_VTU=?",
				new String[] { String.valueOf(MA_VTU) });
	}
	public long update_HSO_CHIETTINH(Obj_HSO_CHIETTINH HSCT) {
		ContentValues initVal = new ContentValues();
		initVal.put(Utils.TEN_KHANG_KS, HSCT.TEN_KHANG_KS);
		initVal.put(Utils.SO_NHA_KS, HSCT.SO_NHA_KS);
		initVal.put(Utils.DUONG_PHO_KS, HSCT.DUONG_PHO_KS);
		initVal.put(Utils.DIEN_THOAI_KS, HSCT.DIEN_THOAI_KS);
		initVal.put(Utils.BB_KSAT, HSCT.BB_KSAT);
		initVal.put(Utils.MA_GCST, HSCT.MA_GCST);
		initVal.put(Utils.MA_GCSP, HSCT.MA_GCSP);
		initVal.put(Utils.MA_LOAI_HSO, HSCT.MA_LOAI_HSO);
		initVal.put(Utils.THOI_GIAN, HSCT.THOI_GIAN);
		initVal.put(Utils.DA_CHUYEN, Utils.TT_CHUA_CHUYEN);
		initVal.put(Utils.GHI_CHU, HSCT.GHI_CHU);
		initVal.put(Utils.PT_TT, HSCT.PT_TT);
		initVal.put(Utils.PT_C, HSCT.PT_C);
		initVal.put(Utils.PT_TL, HSCT.PT_TL);
		initVal.put(Utils.PT_K, HSCT.PT_K);
		initVal.put(Utils.PT_VAT, HSCT.PT_VAT);
		initVal.put(Utils.PT_NC, HSCT.PT_NC);
		initVal.put(Utils.PT_C1, HSCT.PT_C1);
		initVal.put(Utils.PT_NC1, HSCT.PT_NC1);
		initVal.put(Utils.TENTRAM_BIENAP, HSCT.TENTRAM_BIENAP);
		initVal.put(Utils.MA_TRAM, HSCT.MA_TRAM);
		initVal.put(Utils.CONG_SUAT_TRAM, HSCT.CONG_SUAT_TRAM);
		initVal.put(Utils.DONGDIEN_DINHMUC, HSCT.DONGDIEN_DINHMUC);
		initVal.put(Utils.TAI_MAX, HSCT.TAI_MAX);
		initVal.put(Utils.CONGTO_TRAI, HSCT.CONGTO_TRAI);
		initVal.put(Utils.CONGTO_PHAI, HSCT.CONGTO_PHAI);
		initVal.put(Utils.KC_CONGTO_DEN_TRAM, HSCT.KC_CONGTO_DEN_TRAM);
		initVal.put(Utils.KC_CONGTO_DEN_LUOI, HSCT.KC_CONGTO_DEN_LUOI);
		initVal.put(Utils.TRU, HSCT.TRU);
		initVal.put(Utils.CONGSUAT_THUC_TE_KW, HSCT.CONGSUAT_THUC_TE_KW);
		initVal.put(Utils.CONGSUAT_THUC_TE_A, HSCT.CONGSUAT_THUC_TE_A);
		initVal.put(Utils.MUC_DICH_SU_DUNG, HSCT.MUC_DICH_SU_DUNG);
		initVal.put(Utils.MA_KHACHHANG, HSCT.MA_KHACHHANG);
		initVal.put(Utils.CONGSUAT_DENGHI_A, HSCT.CONGSUAT_DENGHI_A);
		initVal.put(Utils.CONGSUAT_DENGHI_V, HSCT.CONGSUAT_DENGHI_V);
		initVal.put(Utils.CONGSUAT_DENGHI_PHA, HSCT.CONGSUAT_DENGHI_PHA);
		initVal.put(Utils.LY_DO, HSCT.LY_DO);
		initVal.put(Utils.TINHTRANG_CONG_TO, HSCT.TINHTRANG_CONG_TO);
		initVal.put(Utils.TINHTRANG_CONGTO_CO, HSCT.TINHTRANG_CONGTO_CO);
		initVal.put(Utils.TINHTRANG_TRONGAI, HSCT.TINHTRANG_TRONGAI);
		initVal.put(Utils.VITRITREO, HSCT.VITRITREO);
		initVal.put(Utils.LOAI_TAI, HSCT.LOAI_TAI);
		initVal.put(Utils.MASO_STT, HSCT.MASO_STT);
		initVal.put(Utils.DA_XONG, HSCT.DA_XONG);
		initVal.put(Utils.SO_HO, HSCT.SO_HO);
		initVal.put(Utils.NGAY_KS, HSCT.NGAY_KS);
		initVal.put(Utils.Xt, HSCT.Xt);
		initVal.put(Utils.Yt, HSCT.Yt);
		initVal.put(Utils.Xp, HSCT.Xp);
		initVal.put(Utils.Yp, HSCT.Yp);
		initVal.put(Utils.THUYET_TRINH, HSCT.THUYET_TRINH);
		initVal.put(Utils.LOAI_XAY_DUNG, HSCT.LOAI_XAY_DUNG);
		initVal.put(Utils.LOAI_NHA, HSCT.LOAI_NHA);
		initVal.put(Utils.DC_GANDIEN, HSCT.DC_GANDIEN);
		initVal.put(Utils.PHUTAI_A, HSCT.PHUTAI_A);
		initVal.put(Utils.PHUTAI_B, HSCT.PHUTAI_B);
		initVal.put(Utils.PHUTAI_C, HSCT.PHUTAI_C);
		initVal.put(Utils.DV_CAP_VTU, HSCT.DV_CAP_VTU);
		initVal.put(Utils.DV_THICONG, HSCT.DV_THICONG);
		initVal.put(Utils.DV_THIETKE, HSCT.DV_THIETKE);
		initVal.put(Utils.TTRANG_KO_CONGTO, HSCT.TTRANG_KO_CONGTO);
		return mDB.update(LabelFull.TABLE_HSO_CHIETTINH, initVal, Utils.MA_YCAU_KNAI+"=?",
				new String[] { HSCT.MA_YCAU_KNAI });
	}
		public long update_HSO_VATTU_CTIET(Obj_HSO_VATTU_CTIET VTCT) {
			ContentValues initVal = new ContentValues();
			initVal.put(Utils.MA_VTU, VTCT.MA_VTU);
			initVal.put(Utils.TEN_VTU, VTCT.TEN_VTU);
			initVal.put(Utils.DON_GIA, VTCT.DON_GIA);
			initVal.put(Utils.SO_LUONG, VTCT.SO_LUONG);
			initVal.put(Utils.THOI_GIAN, VTCT.THOI_GIAN);
			return mDB.update(LabelFull.TABLE_HSO_VATTU_CTIET, initVal, Utils.MA_YCAU_KNAI+"=? and "+Utils.MA_LOAI_TTOAN+"=? and "+Utils.MA_VTU+"=?",
					new String[] {VTCT.MA_YCAU_KNAI,String.valueOf(VTCT.MA_LOAI_TTOAN),VTCT.MA_VTU });
		}
		public long update_HSO_HINH(Obj_HSO_HINH H) {
			ContentValues initVal = new ContentValues();
			initVal.put(Utils.MA_DVIQLY, H.MA_DVIQLY);
			initVal.put(Utils.MA_YCAU_KNAI, H.MA_YCAU_KNAI);
			initVal.put(Utils.TEN_HINH, H.TEN_HINH);
			initVal.put(Utils.HINH, H.HINH);
			initVal.put(Utils.THOI_GIAN, H.THOI_GIAN);
			initVal.put(Utils.DA_CHUYEN, H.DA_CHUYEN);
			initVal.put(Utils.MA_LOAI_HINH, H.MA_LOAI_HINH);
			return mDB.update(LabelFull.TABLE_HSO_HINH, initVal, Utils.STT+"=?",
					new String[] { String.valueOf(H.STT) });
		}

		public long update_TEN_HINH_HSO_HINH(String MA_YCAU_KNAI, int STT,
				String TEN_HINH) {
			ContentValues initVal = new ContentValues();
			initVal.put("MA_YCAU_KNAI", MA_YCAU_KNAI);
			initVal.put("STT", STT);
			initVal.put("TEN_HINH", TEN_HINH);
			return mDB.update(LabelFull.TABLE_HSO_HINH, initVal, "STT=? and MA_YCAU_KNAI = ?",
					new String[] { String.valueOf(STT), MA_YCAU_KNAI });
		}
		public long update_HSO_LOAI_HINH(String MA_LOAI_HINH, String TEN_LOAI_HINH) {
			ContentValues initVal = new ContentValues();
			initVal.put("MA_LOAI_HINH", MA_LOAI_HINH);
			initVal.put("TEN_LOAI_HINH", TEN_LOAI_HINH);
			return mDB.update("HSO_LOAI_HINH", initVal, "MA_LOAI_HINH=?",
					new String[] { MA_LOAI_HINH });
		}
		public long update_HSO_TOADO(Obj_HSO_TOADO TD) {
			ContentValues initVal = new ContentValues();
			initVal.put(Utils.TEN_DIEM, TD.TEN_DIEM);
			initVal.put(Utils.X, TD.X);
			initVal.put(Utils.Y, TD.Y);
			initVal.put(Utils.THOI_GIAN, TD.THOI_GIAN);
			return mDB.update(LabelFull.TABLE_HSO_TOADO, initVal, "STT=? and "+Utils.MA_YCAU_KNAI+"=?",
					new String[] { String.valueOf(TD.STT),TD.MA_YCAU_KNAI });
		}
		public long update_HSO_GHICHU(String MA_DVIQLY, String MA_YCAU_KNAI,
				int STT, String GHI_CHU, String THOI_GIAN, int DA_CHUYEN,
				int THUOC_TINH) {
			ContentValues initVal = new ContentValues();
			initVal.put("MA_DVIQLY", MA_DVIQLY);
			initVal.put("MA_YCAU_KNAI", MA_YCAU_KNAI);
			initVal.put("STT", STT);
			initVal.put("GHI_CHU", GHI_CHU);
			initVal.put("THOI_GIAN", THOI_GIAN);
			initVal.put("DA_CHUYEN", DA_CHUYEN);
			initVal.put("THUOC_TINH", THUOC_TINH);
			return mDB.update("HSO_GHICHU", initVal, "MA_YCAU_KNAI = ? and STT=?",
					new String[] { MA_YCAU_KNAI, String.valueOf(STT) });
		}
		public long update_HSO_CANVAS(Obj_CANVAS CV) {
			ContentValues initVal = new ContentValues();
			initVal.put(Utils.TEN, CV.TEN);
			initVal.put(Utils.LENH, CV.LENH);
			initVal.put(Utils.X1, CV.X1);
			initVal.put(Utils.Y1, CV.Y1);
			initVal.put(Utils.X2, CV.X2);
			initVal.put(Utils.Y2, CV.Y2);
			initVal.put(Utils.X3, CV.X3);
			initVal.put(Utils.Y3, CV.Y3);
			return mDB.update(LabelFull.TABLE_CANVAS, initVal, "MA_YCAU_KNAI = ? and STT=?",
					new String[] { CV.MA_YCAU_KNAI, String.valueOf(CV.STT) });
		}

		public long update_HSO_CANVAS_TEN(String MA_DVIQLY, String MA_YCAU_KNAI,
				int STT, String TEN) {
			ContentValues initVal = new ContentValues();
			initVal.put("TEN", TEN);
			return mDB.update(LabelFull.TABLE_CANVAS, initVal, "MA_YCAU_KNAI = ? and STT=?",
					new String[] { MA_YCAU_KNAI, String.valueOf(STT) });
		}

		public long update_TOADO_CANVAS_DAU(String MA_YCAU_KNAI, int STT, Float X1,
				Float Y1) {
			ContentValues initVal = new ContentValues();
			initVal.put("MA_YCAU_KNAI", MA_YCAU_KNAI);
			initVal.put("STT", STT);
			initVal.put("X1", X1);
			initVal.put("Y1", Y1);
			return mDB.update(LabelFull.TABLE_CANVAS, initVal, "MA_YCAU_KNAI = ? and STT=?",
					new String[] { MA_YCAU_KNAI, String.valueOf(STT) });
		}

		public long update_TOADO_CANVAS_CUOI(String MA_YCAU_KNAI, int STT,
				Float X3, Float Y3) {
			ContentValues initVal = new ContentValues();
			initVal.put("MA_YCAU_KNAI", MA_YCAU_KNAI);
			initVal.put("STT", STT);
			initVal.put("X3", X3);
			initVal.put("Y3", Y3);
			return mDB.update(LabelFull.TABLE_CANVAS, initVal, "MA_YCAU_KNAI = ? and STT=?",
					new String[] { MA_YCAU_KNAI, String.valueOf(STT) });
		}
		// update he so cho khach hang
		public long update_HSO_CHIETTINH_HESO(String MA_YCAU_KNAI, double PT_TT,
				double PT_C, double PT_TL, double PT_K, double PT_VAT,
				double PT_NC, double PT_C1, double PT_NC1) {
			ContentValues initVal = new ContentValues();
			initVal.put("MA_YCAU_KNAI", MA_YCAU_KNAI);
			initVal.put("PT_TT", PT_TT);
			initVal.put("PT_C", PT_C);
			initVal.put("PT_TL", PT_TL);
			initVal.put("PT_K", PT_K);
			initVal.put("PT_VAT", PT_VAT);
			initVal.put("PT_NC", PT_NC);
			initVal.put("PT_C1", PT_C1);
			initVal.put("PT_NC1", PT_NC1);
			return mDB.update("HSO_CHIETTINH", initVal, "MA_YCAU_KNAI=?",
					new String[] { String.valueOf(MA_YCAU_KNAI) });
		}

	
	/*
	 * delete
	 */
	
	public void Xoa_HSO_CHIETTINH_BY_MA_YCAU_KNAI(String MA_YCAU_KNAI) {
		mDB.delete(LabelFull.TABLE_HSO_CHIETTINH, "MA_YCAU_KNAI =?",
				new String[] { MA_YCAU_KNAI });
	}
	public void Xoa_HSO_TOADO_BY_MA_YCAU_KNAI(String MA_YCAU_KNAI) {
		mDB.delete(LabelFull.TABLE_HSO_TOADO, "MA_YCAU_KNAI =?",
				new String[] { MA_YCAU_KNAI });
	}
	public boolean delete_VTU_CHON(Obj_HSO_VATTU_CTIET VTCT) {
		return mDB.delete(
				LabelFull.TABLE_HSO_VATTU_CTIET,
				Utils.MA_VTU+"=? and "+Utils.MA_YCAU_KNAI+" = ? and "+Utils.MA_LOAI_TTOAN+" = ?",
				new String[] { VTCT.MA_VTU, VTCT.MA_YCAU_KNAI,
						String.valueOf(VTCT.MA_LOAI_TTOAN) }) > 0;
	}

	public boolean delete_VTU_BY_MA_YCAU_KNAI(String MA_YCAU_KNAI,
			int MA_LOAI_TTOAN) {
		if (MA_LOAI_TTOAN < 0)
			return mDB.delete(LabelFull.TABLE_HSO_VATTU_CTIET, "MA_YCAU_KNAI =?",
					new String[] { MA_YCAU_KNAI }) > 0;
		else
			return mDB.delete(LabelFull.TABLE_HSO_VATTU_CTIET,
					"MA_YCAU_KNAI =? and MA_LOAI_TTOAN = ?", new String[] {
							MA_YCAU_KNAI, String.valueOf(MA_LOAI_TTOAN) }) > 0;
	}
	public boolean delete_HSO_HINH(Obj_HSO_HINH H) {
		ContentValues initVal = new ContentValues();
		initVal.put(Utils.STT, H.STT);
		initVal.put(Utils.MA_YCAU_KNAI, H.MA_YCAU_KNAI);
		return mDB.delete(LabelFull.TABLE_HSO_HINH, "STT=? and MA_YCAU_KNAI = ?",
				new String[] { String.valueOf(H.STT), H.MA_YCAU_KNAI }) > 0;
	}

	public boolean delete_HSO_HINH_BY_MA_YCAU_KNAI(String MA_YCAU_KNAI,
			String MA_LOAI_HINH) {
		return mDB.delete(LabelFull.TABLE_HSO_HINH, "MA_YCAU_KNAI =? and MA_LOAI_HINH =?",
				new String[] { MA_YCAU_KNAI, MA_LOAI_HINH }) > 0;
	}

	public boolean delete_HSO_HINH_BY_MA_YCAU_KNAI(String MA_YCAU_KNAI) {
		return mDB.delete(LabelFull.TABLE_HSO_HINH, "MA_YCAU_KNAI =?",
				new String[] { MA_YCAU_KNAI }) > 0;
	}
	public boolean delete_HSO_TOADO(Obj_HSO_TOADO TD) {
		ContentValues initVal = new ContentValues();
		initVal.put(Utils.STT, TD.STT);
		initVal.put(Utils.MA_YCAU_KNAI, TD.MA_YCAU_KNAI);
		return mDB.delete(LabelFull.TABLE_HSO_TOADO, "STT=? and MA_YCAU_KNAI = ?",
				new String[] { String.valueOf(TD.STT), TD.MA_YCAU_KNAI }) > 0;
	}

	public boolean delete_HSO_TOADO_BY_MA_YCAU_KNAI(String MA_YCAU_KNAI) {
		return mDB.delete(LabelFull.TABLE_HSO_TOADO, "MA_YCAU_KNAI =? ",
				new String[] { MA_YCAU_KNAI }) > 0;
	}
	public boolean delete_HSO_GHICHU_BY_MA_YCAU_KNAI(String MA_YCAU_KNAI) {
		return mDB.delete(LabelFull.TABLE_GHICHU, "MA_YCAU_KNAI =? ",
				new String[] { MA_YCAU_KNAI }) > 0;
	}
	public boolean delete_HSO_CANVAS_BY_MA_YCAU_KNAI(String MA_YCAU_KNAI) {
		return mDB.delete(LabelFull.TABLE_CANVAS, "MA_YCAU_KNAI =? ",
				new String[] { MA_YCAU_KNAI }) > 0;
	}

	public boolean delete_HSO_CANVAS(Obj_CANVAS HSCV) {
		ContentValues initVal = new ContentValues();
		initVal.put(Utils.STT, HSCV.STT);
		initVal.put(Utils.MA_YCAU_KNAI, HSCV.MA_YCAU_KNAI);
		return mDB.delete(LabelFull.TABLE_CANVAS, "STT=? and MA_YCAU_KNAI = ?",
				new String[] { String.valueOf(HSCV.STT), HSCV.MA_YCAU_KNAI }) > 0;
	}
	public static boolean delete_MAU_VATTU(String mau) {
		return mDB.delete(LabelFull.TABLE_D_MAU, Utils.MAU+"=?",
				new String[] {mau}) > 0;
	}
	public boolean delete_NHAP_LIEU(Obj_nhap_lieu NL) {
		return mDB.delete(LabelFull.TABLE_NHAP_LIEU, "STT=?",
				new String[] { String.valueOf(NL.STT) }) > 0;
	}
	public boolean delete_MAU_CANVAS(Obj_MAU_CANVAS MCV) {
		return mDB.delete(LabelFull.TABLE_MAU_CANVAS, Utils.TEN_MAU+"=?",
				new String[] { String.valueOf(MCV.TEN_MAU) }) > 0;
	}
	
	public static void delete_ALL_D_VATTU() {
		mDB.delete(LabelFull.TABLE_D_VATTU,null,null);
	}
	public static void delete_ALL_D_MAU() {
		mDB.execSQL("DELETE FROM " + LabelFull.TABLE_D_MAU + " WHERE not MAU like 'MTB_%'");
	}
	
	public static void delete_ALL_DANHMUC_TRAM() {
		mDB.delete(LabelFull.TABLE_DANHMUC_TRAM,null,null);
	}
	
	public static void delete_ALL_D_NHOM_VTU() {
		mDB.delete(LabelFull.TABLE_D_NHOM_VTU,null,null);
	}
	
	public static void delete_ALL_LIENKET_VATTU() {
		mDB.delete(LabelFull.TABLE_LIENKET_VATTU,null,null);
	}
	
	public static void delete_ALL_MAU_CANVAS() {
		mDB.delete(LabelFull.TABLE_MAU_CANVAS,null,null);
	}
	public static void delete_ALL_MAU_HESO() {
		mDB.delete(LabelFull.TABLE_MAU_HESO,null,null);
	}
/*
	 * cursor
	 */
//	public Cursor getNhanVien() {
//		return mDB.rawQuery("SELECT * FROM "+Table.TABLE_D_NHAN_VIEN, null);
//	}
//
//	public Cursor getAllNhanVien() {
//		return mDB.rawQuery(
//				"SELECT idNhanVien as _id, TenNhanVien FROM "+Table.TABLE_D_NHAN_VIEN, null);
//	}
//	public Cursor getAll_D_MAU_SPN() {
//		return mDB.rawQuery("SELECT DISTINCT MAU as _id, MAU FROM D_MAU", null);
//	}
//
//	public Cursor get_D_MAU(String MAU) {
//		return mDB.rawQuery("SELECT * FROM D_MAU WHERE MAU = '" + MAU + "'",
//				null);
//	}
//
//	public Cursor getAll_D_MAU2() {
//		return mDB.rawQuery("SELECT * FROM D_MAU", null);
//	}
//
//	public Cursor getAll_D_NHOM_VTU(String MA_DVIQLY) {
//		return mDB.rawQuery(
//				"SELECT NHOM as _id, TEN_NHOM FROM D_NHOM_VTU WHERE MA_DVIQLY ='"
//						+ MA_DVIQLY + "'", null);
//	}
//
//	public Cursor get_D_NHOM_VTU(int NHOM, String MA_DVIQLY) {
//		return mDB.rawQuery("SELECT * FROM D_NHOM_VTU WHERE NHOM=" + NHOM
//				+ " and MA_DVIQLY ='" + MA_DVIQLY + "'", null);
//	}
//
//	public Cursor getAll_D_VATTU_SPN() {
//		return mDB.rawQuery("SELECT MA_VTU as _id, TEN_VTU FROM D_VATTU", null);
//	}
//
//	public Cursor get_D_VATTU_BY_NHOM_SPN(int NHOM) {
//		return mDB
//				.rawQuery(
//						"SELECT MA_VTU as _id,TEN_VTU FROM D_VATTU WHERE NHOM= "
//								+ NHOM, null);
//	}
//
//	public Cursor getAll_D_VATTU() {
//		return mDB.rawQuery("SELECT * FROM D_VATTU", null);
//	}
//
//	public Cursor get_D_VATTU_BY_NHOM(int NHOM) {
//		return mDB.rawQuery("SELECT * FROM D_VATTU WHERE NHOM=" + NHOM, null);
//	}
//	public Cursor get_HSO_CHIETTINH(String MA_YCAU_KNAI) {
//		return mDB.rawQuery(
//				"SELECT * FROM "+Table.TABLE_HSO_CHIETTINH+" WHERE MA_YCAU_KNAI = '"
//						+ MA_YCAU_KNAI + "'", null);
//	}
//
//	public Cursor getAll_HSO_CHIETTINH(String MA_DVIQLY) {
//		return mDB.rawQuery("SELECT * FROM "+Table.TABLE_HSO_CHIETTINH+" WHERE MA_DVIQLY = '"
//				+ MA_DVIQLY + "'", null);
//	}
//	public Cursor get_VATTU_BY_MA_YCAU_KNAI(String MA_YCAU_KNAI,
//			int MA_LOAI_TTOAN) {
//		if (MA_LOAI_TTOAN == -1)
//			return mDB.rawQuery(
//					"SELECT * FROM HSO_VATTU_CTIET WHERE MA_YCAU_KNAI= '"
//							+ MA_YCAU_KNAI + "' ORDER BY MA_LOAI_TTOAN ", null);
//		else
//			return mDB.rawQuery(
//					"SELECT * FROM HSO_VATTU_CTIET WHERE MA_YCAU_KNAI= '"
//							+ MA_YCAU_KNAI + "' and MA_LOAI_TTOAN= "
//							+ MA_LOAI_TTOAN + " ORDER BY MA_YCAU_KNAI DESC",
//					null);
//	}
//
//	public Cursor get_HSO_VATTU_BY_MA_LOAI_CPHI(String MA_YCAU_KNAI,
//			int MA_LOAI_TTOAN, String MA_LOAI_CPHI) {
//		return mDB.rawQuery(
//				"SELECT * FROM HSO_VATTU_CTIET WHERE MA_YCAU_KNAI= '"
//						+ MA_YCAU_KNAI + "' and MA_LOAI_TTOAN= "
//						+ MA_LOAI_TTOAN + " and MA_LOAI_CPHI= '" + MA_LOAI_CPHI
//						+ "' ORDER BY MA_YCAU_KNAI DESC", null);
//	}
//
//	public Cursor get_HSO_VATTU_CTIET(String MA_YCAU_KNAI, int MA_LOAI_TTOAN,
//			String MA_VTU) {
//		return mDB.rawQuery(
//				"SELECT * FROM HSO_VATTU_CTIET WHERE MA_YCAU_KNAI ='"
//						+ MA_YCAU_KNAI + "' and MA_VTU = '" + MA_VTU
//						+ "' and MA_LOAI_TTOAN = " + MA_LOAI_TTOAN, null);
//	}
//	public Cursor get_HSO_HINH(int STT, String MA_YCAU_KNAI) {
//		return mDB.rawQuery("SELECT * FROM "+Table.TABLE_HSO_HINH+" WHERE STT= " + STT
//				+ " and MA_YCAU_KNAI= '" + MA_YCAU_KNAI + "'", null);
//	}
//	public Cursor getAll_HSO_LOAI_HINH() {
//		return mDB.rawQuery("SELECT * FROM "+Table.TABLE_HSO_LOAI_HINH, null);
//	}
//
//	public Cursor getAll_LOAI_HINH_SPN() {
//		return mDB.rawQuery(
//				"SELECT MA_LOAI_HINH as _id, TEN_LOAI_HINH FROM "+Table.TABLE_HSO_LOAI_HINH,
//				null);
//	}
//	public Cursor get_HSO_TOADO_BY_MA_YCAU_KNAI(String MA_YCAU_KNAI) {
//		return mDB.rawQuery("SELECT * FROM HSO_TOADO WHERE MA_YCAU_KNAI = '"
//				+ MA_YCAU_KNAI + "'" + " ORDER BY STT DESC", null);
//	}
//
//	public Cursor get_HSO_TOADO(int STT, String MA_YCAU_KNAI) {
//		return mDB.rawQuery("SELECT * FROM HSO_TOADO WHERE STT= " + STT
//				+ " and MA_YCAU_KNAI= '" + MA_YCAU_KNAI + "'", null);
//	}
//	public Cursor getAll_HSO_GHICHU() {
//		return mDB.rawQuery("SELECT * FROM HSO_GHICHU", null);
//	}
//
//	public Cursor get_HSO_GHICHU_BY_MA_YCAU_KNAI(String MA_YCAU_KNAI) {
//		return mDB.rawQuery("SELECT * FROM HSO_GHICHU WHERE MA_YCAU_KNAI = '"
//				+ MA_YCAU_KNAI + "'" + " ORDER BY STT DESC", null);
//	}
//
//	public Cursor get_HSO_GHICHU(int STT, String MA_YCAU_KNAI) {
//		return mDB.rawQuery("SELECT * FROM HSO_GHICHU WHERE STT= " + STT
//				+ " and MA_YCAU_KNAI= '" + MA_YCAU_KNAI + "'", null);
//	}
//
//	public Cursor get_HSO_GHICHU_BY_THUOC_TINH(int THUOC_TINH) {
//		return mDB.rawQuery("SELECT * FROM HSO_GHICHU WHERE THUOC_TINH = "
//				+ THUOC_TINH + " ORDER BY STT DESC", null);
//	}
//	public Cursor get_HSO_CANVAS_BY_LENH(int LENH) {
//		return mDB.rawQuery("SELECT * FROM HSO_CANVAS WHERE LENH = " + LENH,
//				null);
//	}
//
//	public Cursor get_HSO_CANVAS(int STT, String MA_YCAU_KNAI) {
//		return mDB.rawQuery("SELECT * FROM HSO_CANVAS WHERE STT= " + STT
//				+ " and MA_YCAU_KNAI= '" + MA_YCAU_KNAI + "'", null);
//	}
//
//	public Cursor get_MAU_CANVAS() {
//		return mDB.rawQuery("SELECT * FROM MAU_CANVAS", null);
//	}
//	public Cursor SET_MAU_HESO_SPN(String MA_DVIQLY) {
//		return mDB.rawQuery(
//				"SELECT id_HESO as _id, TEN_HESO FROM MAU_HESO where MA_DVIQLY='"
//						+ MA_DVIQLY + "'", null);
//	}
	/*
	 * list
	 */
	public static List<Obj_HSO_CHIETTINH> get_list_hoso(Obj_D_NHAN_VIEN DNV) {
		Cursor c = null;
		List<Obj_HSO_CHIETTINH> arr_DM=null;
		mDB.beginTransaction();
		try {
			c = mDB.rawQuery("SELECT * FROM "+LabelFull.TABLE_HSO_CHIETTINH+" where "+Utils.MA_DVIQLY+"='" + DNV.getMaDV() + "'", null);
			c.moveToFirst();
			arr_DM = new ArrayList<Obj_HSO_CHIETTINH>();
			while (!c.isAfterLast()) {
				Obj_HSO_CHIETTINH DM = new Obj_HSO_CHIETTINH();
				DM.set_obj(c);
				arr_DM.add(DM);
				c.moveToNext();
			}
			c.close();
			mDB.setTransactionSuccessful();
		} catch (Exception e) {
			
		}finally{
			mDB.endTransaction();
		}
		
		return arr_DM;
	}
	// ho so vat tu chi tiet
	public static List<Obj_HSO_VATTU_CTIET> get_list_vattu_chitiet(String MA_YCAU_KNAI,
			int MA_LOAI_TTOAN) {
		Cursor c = null;
		List<Obj_HSO_VATTU_CTIET> arr_DM=null;
		mDB.beginTransaction();
		try {
			if (MA_LOAI_TTOAN == -1)
				c= mDB.rawQuery(
						"SELECT * FROM "+LabelFull.TABLE_HSO_VATTU_CTIET+" WHERE MA_YCAU_KNAI= '"
								+ MA_YCAU_KNAI + "' ORDER BY MA_LOAI_TTOAN ", null);
			else
				c= mDB.rawQuery(
						"SELECT * FROM "+LabelFull.TABLE_HSO_VATTU_CTIET+" WHERE MA_YCAU_KNAI= '"
								+ MA_YCAU_KNAI + "' and MA_LOAI_TTOAN= "
								+ MA_LOAI_TTOAN + " ORDER BY MA_YCAU_KNAI DESC",
						null);
			c.moveToFirst();
			arr_DM = new ArrayList<Obj_HSO_VATTU_CTIET>();
			while (!c.isAfterLast()) {
				Obj_HSO_VATTU_CTIET DM = new Obj_HSO_VATTU_CTIET();
				DM.set_obj(c);
				arr_DM.add(DM);
				c.moveToNext();
			}
			c.close();
			mDB.setTransactionSuccessful();
		} catch (Exception e) {
			
		}finally{
			mDB.endTransaction();
		}
		return arr_DM;
	}
	public static List<Obj_HSO_VATTU_CTIET> get_list_vattu_chitiet_khachhang(String MA_YCAU_KNAI) {
		Cursor c = null;
			c= mDB.rawQuery(
					"SELECT * FROM "+LabelFull.TABLE_HSO_VATTU_CTIET+" WHERE MA_YCAU_KNAI= '"
							+ MA_YCAU_KNAI + "' ORDER BY MA_LOAI_TTOAN ", null);
		c.moveToFirst();
		List<Obj_HSO_VATTU_CTIET> arr_DM = new ArrayList<Obj_HSO_VATTU_CTIET>();
		while (!c.isAfterLast()) {
			Obj_HSO_VATTU_CTIET DM = new Obj_HSO_VATTU_CTIET();
			DM.set_obj(c);
			arr_DM.add(DM);
			c.moveToNext();
		}
		c.close();
		return arr_DM;
	}
	// vat tu cua mau
	public List<Obj_D_MAU> get_ARR_VATTU_BY_MAU(String MAU) {
		Cursor c = null;
		c = mDB.rawQuery("SELECT * FROM "+LabelFull.TABLE_D_MAU+" where MAU='" + MAU + "'", null);
		c.moveToFirst();
		List<Obj_D_MAU> arr_DM = new ArrayList<Obj_D_MAU>();
		while (!c.isAfterLast()) {
			Obj_D_MAU DM = new Obj_D_MAU();
			DM.SET_OBJ(c);
			arr_DM.add(DM);
			c.moveToNext();
		}
		c.close();
		return arr_DM;
	}
	public List<Obj_D_VATTU> get_list_all_vattu(String MADV) {
		Cursor c = null;
		List<Obj_D_VATTU> arr_DM = null;
		mDB.beginTransaction();
		try {
			c = mDB.rawQuery("SELECT * FROM "+LabelFull.TABLE_D_VATTU+" where "+Utils.MA_DVIQLY +" ='"+MADV+"'", null);
			c.moveToFirst();
			arr_DM = new ArrayList<Obj_D_VATTU>();
			while (!c.isAfterLast()) {
				Obj_D_VATTU DM = new Obj_D_VATTU();
				DM.SET_OBJ(c);
				arr_DM.add(DM);
				c.moveToNext();
			}
			c.close();
			mDB.setTransactionSuccessful();
		} catch (Exception e) {
			
		}finally{
			mDB.endTransaction();
		}
		
		return arr_DM;
	}


	public List<Obj_HSO_HINH> get_HSO_HINH_BY_MA_YCAU_KNAI(String MA_YCAU_KNAI,
			String MA_LOAI_HINH) {
		Cursor c = null;
		if (MA_LOAI_HINH.length() == 0)
			c = mDB.rawQuery("SELECT * FROM "+LabelFull.TABLE_HSO_HINH+" WHERE MA_YCAU_KNAI = '"
					+ MA_YCAU_KNAI + "' ORDER BY STT DESC", null);
		else
			c = mDB.rawQuery("SELECT * FROM "+LabelFull.TABLE_HSO_HINH+" WHERE MA_YCAU_KNAI = '"
					+ MA_YCAU_KNAI + "' and MA_LOAI_HINH = '" + MA_LOAI_HINH
					+ "' ORDER BY STT DESC", null);
		c.moveToFirst();
		List<Obj_HSO_HINH> arr_BAI_HAT = new ArrayList<Obj_HSO_HINH>();
		while (!c.isAfterLast()) {
			Obj_HSO_HINH MTT = new Obj_HSO_HINH();
			MTT.SET_OBJ(c);
			;
			arr_BAI_HAT.add(MTT);
			c.moveToNext();
		}
		c.close();
		return arr_BAI_HAT;
	}
	public List<Obj_MAU_HESO> get_ARR_HESO(String MA_DVIQLY) {
		Cursor c = null;
		c = mDB.rawQuery("SELECT * FROM "+LabelFull.TABLE_MAU_HESO+" where MA_DVIQLY='" + MA_DVIQLY
				+ "'", null);
		c.moveToFirst();
		List<Obj_MAU_HESO> list_mauhs = new ArrayList<Obj_MAU_HESO>();
		while (!c.isAfterLast()) {
			Obj_MAU_HESO MHS = new Obj_MAU_HESO();
			MHS.SET_OBJ(c);
			list_mauhs.add(MHS);
			c.moveToNext();
		}
		c.close();
		return list_mauhs;
	}
	public static List<Obj_CANVAS> get_list_HSO_CANVAS(String MA_YCAU_KNAI) {
		Cursor c = null;
		c = mDB.rawQuery("SELECT * FROM "+LabelFull.TABLE_CANVAS+" WHERE MA_YCAU_KNAI = '"
				+ MA_YCAU_KNAI + "' ORDER BY STT DESC", null);
		c.moveToFirst();
		List<Obj_CANVAS> arr_BAI_HAT = new ArrayList<Obj_CANVAS>();
		while (!c.isAfterLast()) {
			Obj_CANVAS MTT = new Obj_CANVAS();
			MTT.SET_OBJ(c);
			arr_BAI_HAT.add(MTT);
			c.moveToNext();
		}
		c.close();
		return arr_BAI_HAT;
	}
	public static List<Obj_MAU_CANVAS> get_list_MAU_CANVAS(String MA_DVIQLY) {
		Cursor c = null;
		c = mDB.rawQuery("SELECT * FROM "+LabelFull.TABLE_MAU_CANVAS+" where MA_DVIQLY='"
				+ MA_DVIQLY + "'  GROUP BY TEN_MAU", null);

		c.moveToFirst();

		List<Obj_MAU_CANVAS> arr_MAU_CANVAS = new ArrayList<Obj_MAU_CANVAS>();
		while (!c.isAfterLast()) {
			Obj_MAU_CANVAS MTT = new Obj_MAU_CANVAS();
			MTT.TEN_MAU = c.getString(0);
			MTT.SET_OBJ(c);
			arr_MAU_CANVAS.add(MTT);
			c.moveToNext();
		}
		c.close();
		return arr_MAU_CANVAS;
	}

	public List<Obj_MAU_CANVAS> get_PAINT_OF_MAU(String TEN_MAU) {
		Cursor c = null;
		c = mDB.rawQuery("SELECT * FROM "+LabelFull.TABLE_MAU_CANVAS+" where TEN_MAU= '" + Utils.replace_special_char(TEN_MAU)
				+ "'", null);
		c.moveToFirst();
		List<Obj_MAU_CANVAS> arr_BAI_HAT = new ArrayList<Obj_MAU_CANVAS>();
		while (!c.isAfterLast()) {
			Obj_MAU_CANVAS MTT = new Obj_MAU_CANVAS();
			MTT.SET_OBJ(c);
			arr_BAI_HAT.add(MTT);
			c.moveToNext();
		}
		c.close();
		return arr_BAI_HAT;
	}
	public List<Obj_LIENKET_VATTU> get_list_vattu_lienket() {
		Cursor c = null;
		c = mDB.rawQuery("SELECT * FROM "+LabelFull.TABLE_LIENKET_VATTU, null);
		c.moveToFirst();
		List<Obj_LIENKET_VATTU> arr_LK = new ArrayList<Obj_LIENKET_VATTU>();
		while (!c.isAfterLast()) {
			Obj_LIENKET_VATTU LK = new Obj_LIENKET_VATTU();
			LK.SET_OBJ(c);
			arr_LK.add(LK);
			c.moveToNext();
		}
		c.close();
		return arr_LK;
	}

	public List<Obj_LIENKET_VATTU> get_ARR_LIENKET(String MA_VTU) {
		Cursor c = null;
		c = mDB.rawQuery("SELECT * FROM "+LabelFull.TABLE_LIENKET_VATTU+" where MA_VTU ='" + MA_VTU
				+ "'", null);
		c.moveToFirst();
		List<Obj_LIENKET_VATTU> arr_LK = new ArrayList<Obj_LIENKET_VATTU>();
		while (!c.isAfterLast()) {
			Obj_LIENKET_VATTU LK = new Obj_LIENKET_VATTU();
			LK.SET_OBJ(c);
			arr_LK.add(LK);
			c.moveToNext();
		}
		c.close();
		return arr_LK;
	}

	

	public List<Obj_DANHMUC_TRAM> get_ARR_TRAM(String MA_DVIQLY) {
		Cursor c = null;
		c = mDB.rawQuery("SELECT * FROM "+LabelFull.TABLE_DANHMUC_TRAM+" where MA_DVIQLY ='"
				+ MA_DVIQLY + "'", null);
		c.moveToFirst();
		List<Obj_DANHMUC_TRAM> arr_LK = new ArrayList<Obj_DANHMUC_TRAM>();
		while (!c.isAfterLast()) {
			Obj_DANHMUC_TRAM LK = new Obj_DANHMUC_TRAM();
			LK.SET_OBJECT(c);
			arr_LK.add(LK);
			c.moveToNext();
		}
		c.close();
		return arr_LK;
	}
	// nhom vat tu
	public List<Obj_D_NHOM_VTU> get_list_nhom_vattu(String MA_DVIQLY) {
		Cursor c = null;
		c = mDB.rawQuery("SELECT * FROM "+LabelFull.TABLE_D_NHOM_VTU+" where MA_DVIQLY ='"
				+ MA_DVIQLY + "'", null);
		c.moveToFirst();
		List<Obj_D_NHOM_VTU> arr_nhom_vt = new ArrayList<Obj_D_NHOM_VTU>();
		while (!c.isAfterLast()) {
			Obj_D_NHOM_VTU nhom_vt = new Obj_D_NHOM_VTU();
			nhom_vt.SET_OBJECT(c);
			arr_nhom_vt.add(nhom_vt);
			c.moveToNext();
		}
		c.close();
		return arr_nhom_vt;
	}
	// mau vat tu
	public List<Obj_D_MAU> get_list_mau_vattu(String MA_DVIQLY) {
		Cursor c = null;
		c = mDB.rawQuery("SELECT * FROM "+LabelFull.TABLE_D_MAU+" where MA_DVIQLY ='"
				+ MA_DVIQLY + "'", null);
		c.moveToFirst();
		List<Obj_D_MAU> arr_nhom_vt = new ArrayList<Obj_D_MAU>();
		while (!c.isAfterLast()) {
			Obj_D_MAU nhom_vt = new Obj_D_MAU();
			nhom_vt.SET_OBJ(c);
			arr_nhom_vt.add(nhom_vt);
			c.moveToNext();
		}
		c.close();
		return arr_nhom_vt;
	}
	// danh sach hinh
		public static List<Obj_HSO_HINH> get_list_hinh(String MA_YC) {
			Cursor c = null;
			c = mDB.rawQuery("SELECT * FROM "+LabelFull.TABLE_HSO_HINH+" where "+Utils.MA_YCAU_KNAI+" ='"
					+ MA_YC + "' ORDER BY STT DESC", null);
			c.moveToFirst();
			List<Obj_HSO_HINH> arr_hinh = new ArrayList<Obj_HSO_HINH>();
			while (!c.isAfterLast()) {
				Obj_HSO_HINH hinh = new Obj_HSO_HINH();
				hinh.SET_OBJ(c);
				arr_hinh.add(hinh);
				c.moveToNext();
			}
			c.close();
			return arr_hinh;
		}
	// danh sach toa do
		public static List<Obj_HSO_TOADO> get_list_toado(String MA_YC) {
			Cursor c = null;
			c = mDB.rawQuery("SELECT * FROM "+LabelFull.TABLE_HSO_TOADO+" where "+Utils.MA_YCAU_KNAI+" ='"
					+ MA_YC + "'", null);
			c.moveToFirst();
			List<Obj_HSO_TOADO> arr_toado = new ArrayList<Obj_HSO_TOADO>();
			while (!c.isAfterLast()) {
				Obj_HSO_TOADO toado = new Obj_HSO_TOADO();
				toado.SET_OBJ(c);
				arr_toado.add(toado);
				c.moveToNext();
			}
			c.close();
			return arr_toado;
		}
		// ghi chu
		public static List<Obj_GHICHU> get_list_ghichu(String MA_YC) {
			Cursor c = null;
			c = mDB.rawQuery("SELECT * FROM "+LabelFull.TABLE_GHICHU+" where "+Utils.MA_YCAU_KNAI+" ='"
					+ MA_YC + "'", null);
			c.moveToFirst();
			List<Obj_GHICHU> arr_toado = new ArrayList<Obj_GHICHU>();
			while (!c.isAfterLast()) {
				Obj_GHICHU toado = new Obj_GHICHU();
				toado.SET_OBJ(c);
				arr_toado.add(toado);
				c.moveToNext();
			}
			c.close();
			return arr_toado;
		}
		// nhap lieu
		public String [] get_list_nhap_lieu(int loai_nhap_lieu) {
			Cursor c = null;
			c = mDB.rawQuery("SELECT * FROM "+LabelFull.TABLE_NHAP_LIEU +" where "+Utils.loai_nhap_lieu+" = "+loai_nhap_lieu+" ORDER BY STT DESC", null);
			c.moveToFirst();
			List<String> arr_nhap_lieu = new ArrayList<String>();
			while (!c.isAfterLast()) {
				String values = ThtDatabase.get_cursor_string(c, Utils.nhap_lieu);
				arr_nhap_lieu.add(values);
				c.moveToNext();
			}
			c.close();
			return arr_nhap_lieu.toArray(new String[arr_nhap_lieu.size()]);
		}
		public List<Obj_nhap_lieu> get_list_NL(int loai_nhap_lieu) {
			Cursor c = null;
			c = mDB.rawQuery("SELECT * FROM "+LabelFull.TABLE_NHAP_LIEU +" where "+Utils.loai_nhap_lieu+" = "+loai_nhap_lieu+" ORDER BY STT DESC", null);
			c.moveToFirst();
			List<Obj_nhap_lieu> arr_nhaplieu = new ArrayList<Obj_nhap_lieu>();
			while (!c.isAfterLast()) {
				Obj_nhap_lieu nhaplieu = new Obj_nhap_lieu();
				nhaplieu.set_obj(c);
				arr_nhaplieu.add(nhaplieu);
				c.moveToNext();
			}
			c.close();
			return arr_nhaplieu;
		}
	/*
	 * object
	 */
	public Obj_HSO_CHIETTINH get_HSO(String ma_yc) {
		Cursor c = null;
		c = mDB.rawQuery("SELECT * FROM "+LabelFull.TABLE_HSO_CHIETTINH+" where "+Utils.MA_YCAU_KNAI+" ='"+ma_yc+"'", null);
		c.moveToFirst();
		Obj_HSO_CHIETTINH HS = new Obj_HSO_CHIETTINH();
		HS.set_obj(c);
		c.close();
		return HS;
	}
	public Obj_HSO_HINH get_HSO_HINH(Obj_HSO_HINH H) {
		Cursor c = null;
		c = mDB.rawQuery("SELECT * FROM "+LabelFull.TABLE_HSO_HINH, null);
		c.moveToFirst();
		Obj_HSO_HINH MTT = new Obj_HSO_HINH();
		MTT.SET_OBJ(c);
		;
		c.close();
		return MTT;
	}
	public Obj_CANVAS get_MAX_STT_CANVAS() {
		Cursor c = mDB
				.rawQuery(
						"SELECT * FROM "+LabelFull.TABLE_CANVAS+" WHERE STT=(SELECT max(STT) FROM "+LabelFull.TABLE_CANVAS+")",
						null);
		c.moveToFirst();
		Obj_CANVAS HD = new Obj_CANVAS();
		HD.SET_OBJ(c);
		return HD;
	}
	public Obj_HSO_TOADO get_MAX_STT_TOADO() {
		Cursor c = mDB
				.rawQuery(
						"SELECT * FROM "+LabelFull.TABLE_HSO_TOADO+" WHERE STT=(SELECT max(STT) FROM "+LabelFull.TABLE_HSO_TOADO+")",
						null);
		c.moveToFirst();
		Obj_HSO_TOADO TD = new Obj_HSO_TOADO();
		TD.SET_OBJ(c);
		return TD;
	}
	public Obj_HSO_HINH get_MAX_STT_HINHANH() {
		Cursor c = mDB
				.rawQuery(
						"SELECT * FROM "+LabelFull.TABLE_HSO_HINH+" WHERE STT=(SELECT max(STT) FROM "+LabelFull.TABLE_HSO_HINH+")",
						null);
		c.moveToFirst();
		Obj_HSO_HINH TD = new Obj_HSO_HINH();
		TD.SET_OBJ(c);
		return TD;
	}
	public Obj_HSO_HINH get_MAX_STT_HINHANH_OF_KH(String MAYC) {
		Cursor c = mDB
				.rawQuery(
						"SELECT * FROM "+LabelFull.TABLE_HSO_HINH+" WHERE STT=(SELECT max(STT) FROM "+LabelFull.TABLE_HSO_HINH+" where "+Utils.MA_YCAU_KNAI+"= '"+MAYC+"' and "+Utils.MA_LOAI_HINH+"='BD')",
						null);
		c.moveToFirst();
		Obj_HSO_HINH TD = new Obj_HSO_HINH();
		TD.SET_OBJ(c);
		return TD;
	}
	public Obj_MAU_HESO get_HESO(String MA_DVIQLY, int id_HESO) {
		Cursor c = mDB.rawQuery("SELECT * FROM "+LabelFull.TABLE_MAU_HESO+" WHERE MA_DVIQLY='"
				+ MA_DVIQLY + "' and id_HESO=" + id_HESO, null);
		c.moveToFirst();
		Obj_MAU_HESO HS = new Obj_MAU_HESO();
		HS.SET_OBJ(c);
		return HS;
	}
	public Obj_D_NHAN_VIEN get_nhanvien() {
		Cursor c = mDB.rawQuery("SELECT * FROM "+LabelFull.TABLE_D_NHAN_VIEN+" WHERE "+Utils.idNhanVien+" =1", null);
		c.moveToFirst();
		Obj_D_NHAN_VIEN HS = new Obj_D_NHAN_VIEN();
		HS.SET_OBJ(c);
		return HS;
	}
	// vat tu
	public Obj_D_VATTU get_vattu(String MA_VTU) {
		Cursor c = mDB.rawQuery("SELECT * FROM "+LabelFull.TABLE_D_VATTU+" WHERE MA_VTU = '" + MA_VTU
				+ "'", null);
		c.moveToFirst();
		Obj_D_VATTU HS = new Obj_D_VATTU();
		HS.SET_OBJ(c);
		return HS;
	}
	/*
	 * so luong
	 */
	// ho so
	public int get_soluong_hoso_by_donvi(String MADV) {
		Cursor c = null;
		c = mDB.rawQuery("SELECT COUNT (*) FROM "+LabelFull.TABLE_HSO_CHIETTINH+" where "+Utils.MA_DVIQLY+"= '"
				+ MADV + "'", null);
		c.moveToFirst();
		int count = c.getInt(0);
		return count;
	}
	public int get_soluong_vat_tu_cua_mau(String mau) {
		Cursor c = null;
		c = mDB.rawQuery("SELECT COUNT (*) FROM "+LabelFull.TABLE_D_MAU+" where "+Utils.MAU+"= '"
				+ mau + "'", null);
		c.moveToFirst();
		int count = c.getInt(0);
		return count;
	}
	public int get_SL_khachhang() {
		return ThtDatabase.get_so_luong(mDB, LabelFull.TABLE_HSO_CHIETTINH);
	}
	public int get_SL_nhanvien() {
		return ThtDatabase.get_so_luong(mDB, LabelFull.TABLE_D_NHAN_VIEN);
	}
	public int get_SL_vattu() {
		return ThtDatabase.get_so_luong(mDB, LabelFull.TABLE_D_VATTU);
	}
	//lien ket vat tu
	public int get_SL_lienket_vattu() {
		return ThtDatabase.get_so_luong(mDB, LabelFull.TABLE_LIENKET_VATTU);
	}
	// mau he so
	public int get_SL_MAU_HESO() {
		return ThtDatabase.get_so_luong(mDB, LabelFull.TABLE_MAU_HESO);
	}
	// mau vat tu
	public int get_SL_MAU_VATTU() {
		return ThtDatabase.get_so_luong(mDB, LabelFull.TABLE_D_MAU);
	}
	// nhom vat tu
	public int get_SL_NHOM_VATTU() {
		return ThtDatabase.get_so_luong(mDB, LabelFull.TABLE_D_NHOM_VTU);
	}
	// danh muc tram
	public int get_soluong_tram() {
		return ThtDatabase.get_so_luong(mDB, LabelFull.TABLE_DANHMUC_TRAM);
	}
	// nhap lieu
	public int get_soluong_nhap_lieu() {
		return ThtDatabase.get_so_luong(mDB, LabelFull.TABLE_NHAP_LIEU);
	}
	// he so
	public int get_soluong_he_so() {
		return ThtDatabase.get_so_luong(mDB, LabelFull.TABLE_MAU_HESO);
	}
	// vat tu
	public int get_sl_vattu(int ma_loai_tt,String ma_yc) {
		int count =0;
		Cursor c = null;
		try {
			c = mDB.rawQuery("SELECT COUNT (*) FROM "+LabelFull.TABLE_HSO_VATTU_CTIET+" where "+Utils.MA_LOAI_TTOAN+"= "
					+ ma_loai_tt+" and "+Utils.MA_YCAU_KNAI+" ='"+ma_yc+"'", null);
			c.moveToFirst();
			count = c.getInt(0);
		} catch (Exception e) {
			
		}
		
		c.close();
		return count;
	}
	// user
	public int get_soluong_user(String manv,String pass) {
		int count =0;
		Cursor c = null;
		try {
			c = mDB.rawQuery("SELECT COUNT (*) FROM "+LabelFull.TABLE_D_NHAN_VIEN+" where "+Utils.MaNhanVien+"= '"
					+ manv+"' and "+Utils.MatKhau+" ='"+pass+"'", null);
			c.moveToFirst();
			count = c.getInt(0);
		} catch (Exception e) {
			
		}
		c.close();
		return count;
	}
	//hinh anh
	public int get_sl_hinhanh(String ma_yc) {
		int count =0;
		Cursor c = null;
		try {
			c = mDB.rawQuery("SELECT COUNT (*) FROM "+LabelFull.TABLE_HSO_HINH+" where "+Utils.MA_YCAU_KNAI+" ='"+ma_yc+"'", null);
			c.moveToFirst();
			count = c.getInt(0);
		} catch (Exception e) {
			
		}
		c.close();
		return count;
	}
	public int get_sl_hinhanh_bando(String ma_yc) {
		int count =0;
		Cursor c = null;
		try {
			c = mDB.rawQuery("SELECT COUNT (*) FROM "+LabelFull.TABLE_HSO_HINH+" where "+Utils.MA_YCAU_KNAI+" ='"+ma_yc+"' and "+Utils.MA_LOAI_HINH+"='BD'", null);
			c.moveToFirst();
			count = c.getInt(0);
		} catch (Exception e) {
			
		}
		c.close();
		return count;
	}
	// canvas
	public int get_sl_canvas(String ma_yc) {
		int count =0;
		Cursor c = null;
		try {
			c = mDB.rawQuery("SELECT COUNT (*) FROM "+LabelFull.TABLE_CANVAS, null);
			c.moveToFirst();
			count = c.getInt(0);
		} catch (Exception e) {
			
		}
		c.close();
		return count;
	}
	// toa do
	public int get_sl_toado(String ma_yc) {
		int count =0;
		Cursor c = null;
		try {
			c = mDB.rawQuery("SELECT COUNT (*) FROM "+LabelFull.TABLE_HSO_TOADO+" where "+Utils.MA_YCAU_KNAI+" ='"+ma_yc+"'", null);
			c.moveToFirst();
			count = c.getInt(0);
		} catch (Exception e) {
			
		}
		c.close();
		return count;
	}
	public int get_SL_MAU_CANVAS(String TEN_MAU) {
		Cursor c = null;
		c = mDB.rawQuery("SELECT COUNT (*) FROM "+LabelFull.TABLE_MAU_CANVAS+" where TEN_MAU= '"
				+ TEN_MAU + "'", null);
		c.moveToFirst();
		int count = c.getInt(0);
		return count;
	}
	
	// spinner
    public List<String> set_spn_nhom_vattu(){
        return ThtDatabase.get_data_for_spinner(mDB, LabelFull.TABLE_D_NHOM_VTU, Utils.NHOM, Utils.TEN_NHOM);
    }
    public List<String> set_spn_mau_vattu(){
        return ThtDatabase.get_data_for_spinner(mDB, LabelFull.TABLE_D_MAU, Utils.MAU, Utils.MAU);
    }
	/*
	 * kiem tra ton tai chua
	 */
	// kiem tra vat tu da chon chua
	
	public boolean ton_tai_vattu_dachon(String ma_yc,String ma_vt,int maloai_tt) {
		int count = 0;
		Cursor c = null;
		try {
			c = mDB.rawQuery("SELECT COUNT (*) FROM "
					+LabelFull.TABLE_HSO_VATTU_CTIET+" where "+Utils.MA_VTU+"= '"
					+ ma_vt + "' and "+Utils.MA_YCAU_KNAI+" ='"+ma_yc+"' and "+Utils.MA_LOAI_TTOAN+" ="+maloai_tt, null);
			c.moveToFirst();
			count = c.getInt(0);
		} catch (Exception e) {
		}
		if (count>0){
			return true;
		}else{
			return false;
		}
	}
	public boolean ton_tai_vattu (String ma_dv,String ma_vt) {
		int count = 0;
		Cursor c = null;
		try {
			c = mDB.rawQuery("SELECT COUNT (*) FROM "
					+LabelFull.TABLE_D_VATTU+" where "+Utils.MA_VTU+"= '"
					+ ma_vt + "' and "+Utils.MA_DVIQLY+" ='"+ma_dv+"'", null);
			c.moveToFirst();
			count = c.getInt(0);
		} catch (Exception e) {
		}
		if (count>0){
			return true;
		}else{
			return false;
		}
	}
	public boolean ton_tao_nen (String ma_yc,int l) {
		int count = 0;
		Cursor c = null;
		try {
			c = mDB.rawQuery("SELECT COUNT (*) FROM "
					+LabelFull.TABLE_CANVAS+" where "+Utils.MA_YCAU_KNAI+"= '"
					+ ma_yc + "' and "+Utils.LENH+" ="+l, null);
			c.moveToFirst();
			count = c.getInt(0);
		} catch (Exception e) {
		}
		if (count>0){
			return true;
		}else{
			return false;
		}
	}
	public boolean chua_ton_tai_nhap_lieu (Obj_nhap_lieu NL) {
		int count = 0;
		Cursor c = null;
		try {
			c = mDB.rawQuery("SELECT COUNT (*) FROM "
					+LabelFull.TABLE_NHAP_LIEU+" where "+Utils.nhap_lieu+"= '"
					+ NL.nhap_lieu + "' and "+Utils.loai_nhap_lieu+" ="+NL.loai_nhap_lieu, null);
			c.moveToFirst();
			count = c.getInt(0);
		} catch (Exception e) {
		}
		if (count>0){
			return false;
		}else{
			return true;
		}
	}
	public boolean chua_ton_tai_mau_vat_tu (String ten_mau) {
		int count = 0;
		Cursor c = null;
		try {
			c = mDB.rawQuery("SELECT COUNT (*) FROM "
					+LabelFull.TABLE_D_MAU+" where "+Utils.MAU+"= '"
					+ ten_mau + "'", null);
			c.moveToFirst();
			count = c.getInt(0);
		} catch (Exception e) {
		}
		if (count>0){
			return false;
		}else{
			return true;
		}
	}

}
