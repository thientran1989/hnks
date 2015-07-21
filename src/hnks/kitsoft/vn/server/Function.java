package hnks.kitsoft.vn.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import hnks.kitsoft.utils.LabelFull;
import hnks.kitsoft.vn.object.Obj_CANVAS;
import hnks.kitsoft.vn.object.Obj_GHICHU;
import hnks.kitsoft.vn.object.Obj_HSO_CHIETTINH;
import hnks.kitsoft.vn.object.Obj_HSO_HINH;
import hnks.kitsoft.vn.object.Obj_HSO_TOADO;
import hnks.kitsoft.vn.object.Obj_HSO_VATTU_CTIET;
import hnks.kitsoft.vn.object.Obj_MAU_CANVAS;
import hnks.kitsoft.vn.object.ObjectClient;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;


public class Function {
	
	// send 2 server
	public static String alldata2server(ObjectClient mOC,
			List<Obj_CANVAS> mL_canvas, 
			Obj_HSO_CHIETTINH hschiettinh,
			List<Obj_HSO_TOADO> mL_toado,
			List<Obj_HSO_VATTU_CTIET> mL_vtuchitiet,
			List<Obj_MAU_CANVAS> mL_maucanvas,
			List<Obj_GHICHU> mL_ghichu,
			List<Obj_HSO_HINH> mL_hinh) {
		String KQ = "[]";
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		JsonObject JO = new JsonObject();
		try {
			JO.addProperty(LabelFull.TABLE_ObjectClient,gson.toJson(mOC));
			if (hschiettinh!=null){
				JO.addProperty(LabelFull.TABLE_HSO_CHIETTINH,gson.toJson(hschiettinh));
			}
			if (mL_canvas!=null){
				JO.addProperty(LabelFull.TABLE_CANVAS, gson.toJson(mL_canvas));
			}
			if (mL_toado!=null){
				JO.addProperty(LabelFull.TABLE_HSO_TOADO, gson.toJson(mL_toado));
			}
			if (mL_vtuchitiet!=null){
				JO.addProperty(LabelFull.TABLE_HSO_VATTU_CTIET, gson.toJson(mL_vtuchitiet));
			}
			if (mL_maucanvas!=null){
				JO.addProperty(LabelFull.TABLE_MAU_CANVAS, gson.toJson(mL_maucanvas));
			}
			if (mL_ghichu!=null){
				JO.addProperty(LabelFull.TABLE_GHICHU, gson.toJson(mL_ghichu));
			}
			if (mL_hinh!=null){
				JO.addProperty(LabelFull.TABLE_HSO_HINH, gson.toJson(mL_hinh));
			}
			KQ = JO.toString();
		} catch (Exception e) {

		}
		return KQ;
	}
	
	public static String byte_to_String(DataInputStream dis) {
		String s = "";
		int size_json = 0;
		byte[] data = null;
		try {
			size_json = dis.readInt();
			data = new byte[size_json];
			dis.readFully(data);
			s = new String(data, "UTF-8");
		} catch (Exception e) {
			s=LabelFull.ErrorWhileReadDataInputStream;
		}
		return s;
	}

	public static void write_String_to_byte(DataOutputStream dos, String json) {
		byte[] data;
		try {
			data = json.getBytes("UTF-8");
			try {
				dos.writeInt(data.length);
				dos.write(data);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

}
