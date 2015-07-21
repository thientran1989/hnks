package hnks.kitsoft.vn.object;

import hnks.kitsoft.utils.Utils;
import hnks.kitsoft.vn.Memory;

import java.io.DataInputStream;
import java.io.DataOutputStream;

import android.database.Cursor;

public class Obj_MAU_CANVAS {
	public String MA_DVIQLY;
	public int id_MAU;
	public String TEN_MAU;
	public int LENH;
	public float X1;
	public float Y1;
	public float X2;
	public float Y2;
	public float X3;
	public float Y3;
	public String CHU;
	public int HINH;
	public int THUOC_TINH;

	public Obj_MAU_CANVAS(String MA_DVIQLY, int id_MAU, String tEN_MAU, int lENH, float x1,
			float y1, float x2, float y2, float x3, float y3, String chu,int hINH, int thuocTinh) {
		super();
		this.MA_DVIQLY=MA_DVIQLY;
		this.id_MAU = id_MAU;
		TEN_MAU = tEN_MAU;
		LENH = lENH;
		X1 = x1;
		Y1 = y1;
		X2 = x2;
		Y2 = y2;
		X3 = x3;
		Y3 = y3;
		CHU = chu;
		HINH =hINH;
		THUOC_TINH=thuocTinh;
	}
	public void SET_OBJ(Cursor cur){
		MA_DVIQLY = cur.getString(cur.getColumnIndex(Utils.MA_DVIQLY));
		id_MAU = cur.getInt(cur.getColumnIndex(Utils.id_MAU));
		TEN_MAU = cur.getString(cur.getColumnIndex(Utils.TEN_MAU));
		LENH = cur.getInt(cur.getColumnIndex(Utils.LENH));
		X1 = cur.getFloat(cur.getColumnIndex(Utils.X1));
		Y1 = cur.getFloat(cur.getColumnIndex(Utils.Y1));
		X2 = cur.getFloat(cur.getColumnIndex(Utils.X2));
		Y2 = cur.getFloat(cur.getColumnIndex(Utils.Y2));
		X3 = cur.getFloat(cur.getColumnIndex(Utils.X3));
		Y3 = cur.getFloat(cur.getColumnIndex(Utils.Y3));
		CHU = cur.getString(cur.getColumnIndex(Utils.CHU));
		HINH = cur.getInt(cur.getColumnIndex(Utils.HINH));
		THUOC_TINH = cur.getInt(cur.getColumnIndex(Utils.THUOC_TINH));
	}
	public Obj_MAU_CANVAS (){
		
	}
	public void sendObject(DataOutputStream dos) {
		try {
			dos.writeUTF(Memory.N2S(MA_DVIQLY));
			dos.writeInt(id_MAU);
			dos.writeUTF(Memory.N2S(TEN_MAU));
			dos.writeInt(LENH);
			dos.writeFloat(X1);
			dos.writeFloat(Y1);
			dos.writeFloat(X2);
			dos.writeFloat(Y2);
			dos.writeFloat(X3);
			dos.writeFloat(Y3);
			dos.writeUTF(Memory.N2S(CHU));
			dos.writeInt(HINH);
			dos.writeInt(THUOC_TINH);
		} catch (Exception e) {}
	}
	public void readObject(DataInputStream din) {
		try {
			MA_DVIQLY = din.readUTF();
			id_MAU = din.readInt();
			TEN_MAU= din.readUTF();
			LENH= din.readInt();
			X1=din.readFloat();
			Y1=din.readFloat();
			X2=din.readFloat();
			Y2=din.readFloat();
			X3=din.readFloat();
			Y3=din.readFloat();
			CHU=din.readUTF();
			HINH=din.readInt();
			THUOC_TINH=din.readInt();
		} catch (Exception e) {}
	}
	
	

}
