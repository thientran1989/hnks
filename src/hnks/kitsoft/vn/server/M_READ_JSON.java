package hnks.kitsoft.vn.server;

import java.lang.reflect.Type;
import java.util.List;

import hnks.kitsoft.utils.LabelFull;
import hnks.kitsoft.vn.object.CallbackResult;
import hnks.kitsoft.vn.object.DM_Company;
import hnks.kitsoft.vn.object.Obj_CANVAS;
import hnks.kitsoft.vn.object.Obj_DANHMUC_TRAM;
import hnks.kitsoft.vn.object.Obj_D_MAU;
import hnks.kitsoft.vn.object.Obj_D_NHOM_VTU;
import hnks.kitsoft.vn.object.Obj_D_VATTU;
import hnks.kitsoft.vn.object.Obj_GHICHU;
import hnks.kitsoft.vn.object.Obj_HSO_CHIETTINH;
import hnks.kitsoft.vn.object.Obj_HSO_TOADO;
import hnks.kitsoft.vn.object.Obj_HSO_VATTU_CTIET;
import hnks.kitsoft.vn.object.Obj_LIENKET_VATTU;
import hnks.kitsoft.vn.object.Obj_MAU_CANVAS;
import hnks.kitsoft.vn.object.Obj_MAU_HESO;
import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

public class M_READ_JSON {

	public static CallbackResult read_callback(JsonObject mJO) {
		CallbackResult mCB = null;
		try {
			Gson gson = new Gson();
			mCB = gson.fromJson(mJO.get(LabelFull.TABLE_callback).getAsString(),CallbackResult.class);
		} catch (Exception e) {

		}
		return mCB;
	}
	
	public static DM_Company read_company(JsonObject mJO) {
		DM_Company mDV = null;
		try {
			Gson gson = new Gson();
			mDV = gson.fromJson(mJO.get("DM_Company").getAsString(),DM_Company.class);
		} catch (Exception e) {

		}
		return mDV;
	}

	// read list
	// 1canvas
	public static List<Obj_CANVAS> read_list_canvas(JsonObject mJO) {
		List<Obj_CANVAS> yourList = null;
		try {
			Type listType = new TypeToken<List<Obj_CANVAS>>() {
			}.getType();
			yourList = new Gson().fromJson(mJO.get(LabelFull.TABLE_CANVAS).getAsString(), listType);
		} catch (Exception e) {

		}

		return yourList;
	}

	// 2d mau
	public static List<Obj_D_MAU> read_list_dmau(JsonObject mJO) {
		List<Obj_D_MAU> yourList = null;
		try {
			Type listType = new TypeToken<List<Obj_D_MAU>>() {
			}.getType();
			yourList = new Gson().fromJson(mJO.get(LabelFull.TABLE_D_MAU).getAsString(), listType);
		} catch (Exception e) {

		}
		return yourList;
	}

	// 3nhom
	public static List<Obj_D_NHOM_VTU> read_list_dnhom(JsonObject mJO) {
		List<Obj_D_NHOM_VTU> yourList = null;
		try {
			Type listType = new TypeToken<List<Obj_D_NHOM_VTU>>() {
			}.getType();
			yourList = new Gson().fromJson(mJO.get(LabelFull.TABLE_D_NHOM_VTU).getAsString(), listType);
		} catch (Exception e) {

		}
		return yourList;
	}

	// 4vat tu
	public static List<Obj_D_VATTU> read_list_dvattu(JsonObject mJO) {
		List<Obj_D_VATTU> yourList = null;
		try {
			Type listType = new TypeToken<List<Obj_D_VATTU>>() {
			}.getType();
			yourList = new Gson().fromJson(mJO.get(LabelFull.TABLE_D_VATTU).getAsString(), listType);
		} catch (Exception e) {

		}
		return yourList;
	}

	// 5danh muc tram
	public static List<Obj_DANHMUC_TRAM> read_list_danhmuctram(JsonObject mJO) {
		List<Obj_DANHMUC_TRAM> yourList = null;
		try {
			Type listType = new TypeToken<List<Obj_DANHMUC_TRAM>>() {
			}.getType();
			yourList = new Gson().fromJson(mJO.get(LabelFull.TABLE_DANHMUC_TRAM).getAsString(), listType);
		} catch (Exception e) {

		}
		return yourList;
	}

	//6ho so chiet tinh
	public static List<Obj_HSO_CHIETTINH> read_list_hschiettinh(JsonObject mJO) {
		List<Obj_HSO_CHIETTINH> yourList = null;
		try {
			Type listType = new TypeToken<List<Obj_HSO_CHIETTINH>>() {
			}.getType();
			yourList = new Gson().fromJson(mJO.get(LabelFull.TABLE_HSO_CHIETTINH).getAsString(), listType);
		} catch (Exception e) {

		}
		return yourList;
	}

	// 7toa do
	public static List<Obj_HSO_TOADO> read_list_toado(JsonObject mJO) {
		List<Obj_HSO_TOADO> yourList = null;
		try {
			Type listType = new TypeToken<List<Obj_HSO_TOADO>>() {
			}.getType();
			yourList = new Gson().fromJson(mJO.get(LabelFull.TABLE_HSO_TOADO).getAsString(), listType);
		} catch (Exception e) {

		}
		return yourList;
	}

	// 8vat tu chi tiet
	public static List<Obj_HSO_VATTU_CTIET> read_list_vattuchitiet(
			JsonObject mJO) {
		List<Obj_HSO_VATTU_CTIET> yourList = null;
		try {
			Type listType = new TypeToken<List<Obj_HSO_VATTU_CTIET>>() {
			}.getType();
			yourList = new Gson().fromJson(mJO.get(LabelFull.TABLE_HSO_VATTU_CTIET)
					.getAsString(), listType);
		} catch (Exception e) {

		}
		return yourList;
	}

	// 9lien ket vat tu
	public static List<Obj_LIENKET_VATTU> read_list_lkvattu(JsonObject mJO) {
		List<Obj_LIENKET_VATTU> yourList = null;
		try {
			Type listType = new TypeToken<List<Obj_LIENKET_VATTU>>() {
			}.getType();
			yourList = new Gson().fromJson(mJO.get(LabelFull.TABLE_LIENKET_VATTU)
					.getAsString(), listType);
		} catch (Exception e) {

		}
		return yourList;
	}

	// 10mau canvas
	public static List<Obj_MAU_CANVAS> read_list_maucanvas(JsonObject mJO) {
		List<Obj_MAU_CANVAS> yourList = null;
		try {
			Type listType = new TypeToken<List<Obj_MAU_CANVAS>>() {
			}.getType();
			yourList = new Gson().fromJson(mJO.get(LabelFull.TABLE_MAU_CANVAS).getAsString(), listType);
		} catch (Exception e) {

		}
		return yourList;
	}

	// 11mau he so
	public static List<Obj_MAU_HESO> read_list_mauheso(JsonObject mJO) {
		List<Obj_MAU_HESO> yourList = null;
		try {
			Type listType = new TypeToken<List<Obj_MAU_HESO>>() {
			}.getType();
			yourList = new Gson().fromJson(mJO.get(LabelFull.TABLE_MAU_HESO).getAsString(), listType);
		} catch (Exception e) {

		}
		return yourList;
	}

	// 12ghi chu
	public static List<Obj_GHICHU> read_list_ghichu(JsonObject mJO) {
		List<Obj_GHICHU> yourList = null;
		try {
			Type listType = new TypeToken<List<Obj_GHICHU>>() {
			}.getType();
			yourList = new Gson().fromJson(mJO.get(LabelFull.TABLE_GHICHU).getAsString(), listType);
		} catch (Exception e) {

		}
		return yourList;
	}

	public static boolean is_accepted(Context c, CallbackResult mCB) {
		boolean accepted = false;
		try {
			if (mCB != null) {
				if (mCB.getResultString().equals("OK")) {
					accepted = true;
				}
			}

		} catch (Exception e) {

		}

		return accepted;
	}
	public static boolean is_new_version(Context c, CallbackResult mCB) {
		boolean new_version = false;
		try {
			if (mCB != null) {
				if (mCB.getResultString().equals(LabelFull.HasNewVersion)) {
					new_version = true;
				}
			}

		} catch (Exception e) {

		}

		return new_version;
	}

}
