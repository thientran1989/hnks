package hnks.kitsoft.vn.object;

import hnks.kitsoft.utils.Utils;
import hnks.kitsoft.vn.Memory;

import java.io.DataInputStream;
import java.io.DataOutputStream;

import android.database.Cursor;

public class Obj_CANVAS {

	
	public String MA_DVIQLY;
	public String MA_YCAU_KNAI;
	public int STT;
	public String TEN;
	public int LENH;
	public float X1;
	public float Y1;
	public float X2;
	public float Y2;
	public float X3;
	public float Y3;
	public int HINH;
	public int THUOC_TINH;
	public int CHON=0;

	public Obj_CANVAS(String MA_DVIQLY, String MA_YCAU_KNAI, int STT,
			String TEN,
	int LENH,
	float X1,
	float Y1,
	float X2,
	float Y2,
	float X3,
	float Y3,int HINH,int THUOC_TINH) {
		this.MA_DVIQLY = MA_DVIQLY;
		this.MA_YCAU_KNAI = MA_YCAU_KNAI;
		this.STT = STT;
		this.TEN = TEN;
		this.LENH= LENH;
		this.X1 = X1;
		this.Y1 = Y1;
		this.X2= X2;
		this.Y2= Y2;
		this.X3 = X3;
		this.Y3 = Y3;
		this.HINH = HINH;
		this.THUOC_TINH =THUOC_TINH;
	}
	public Obj_CANVAS(){
		
	}
	public void SET_OBJ(Cursor cur){
		MA_DVIQLY = cur.getString(cur.getColumnIndex(Utils.MA_DVIQLY));
		MA_YCAU_KNAI = cur.getString(cur.getColumnIndex(Utils.MA_YCAU_KNAI));
		STT = cur.getInt(cur.getColumnIndex(Utils.STT));
		TEN = cur.getString(cur.getColumnIndex(Utils.TEN));
		LENH= cur.getInt(cur.getColumnIndex(Utils.LENH));
		X1 = cur.getFloat(cur.getColumnIndex(Utils.X1));
		Y1 = cur.getFloat(cur.getColumnIndex(Utils.Y1));
		X2= cur.getFloat(cur.getColumnIndex(Utils.X2));
		Y2= cur.getFloat(cur.getColumnIndex(Utils.Y2));
		X3 = cur.getFloat(cur.getColumnIndex(Utils.X3));
		Y3 = cur.getFloat(cur.getColumnIndex(Utils.Y3));
		HINH = cur.getInt(cur.getColumnIndex(Utils.HINH));
		THUOC_TINH = cur.getInt(cur.getColumnIndex(Utils.THUOC_TINH));
	}
	public String getMA_DVIQLY() {
		return MA_DVIQLY;
	}
	public void setMA_DVIQLY(String mA_DVIQLY) {
		MA_DVIQLY = mA_DVIQLY;
	}
	public String getMA_YCAU_KNAI() {
		return MA_YCAU_KNAI;
	}
	public void setMA_YCAU_KNAI(String mA_YCAU_KNAI) {
		MA_YCAU_KNAI = mA_YCAU_KNAI;
	}
	public String getTEN() {
		return TEN;
	}
	public void setTEN(String tEN) {
		TEN = tEN;
	}
	public int getLENH() {
		return LENH;
	}
	public void setLENH(int lENH) {
		LENH = lENH;
	}
	public float getX1() {
		return X1;
	}
	public void setX1(float x1) {
		X1 = x1;
	}
	public float getY1() {
		return Y1;
	}
	public void setY1(float y1) {
		Y1 = y1;
	}
	public float getX2() {
		return X2;
	}
	public void setX2(float x2) {
		X2 = x2;
	}
	public float getY2() {
		return Y2;
	}
	public void setY2(float y2) {
		Y2 = y2;
	}
	public float getX3() {
		return X3;
	}
	public void setX3(float x3) {
		X3 = x3;
	}
	public float getY3() {
		return Y3;
	}
	public void setY3(float y3) {
		Y3 = y3;
	}
	public int getHINH() {
		return HINH;
	}
	public void setHINH(int hINH) {
		HINH = hINH;
	}
	public int getTHUOC_TINH() {
		return THUOC_TINH;
	}
	public void setTHUOC_TINH(int tHUOC_TINH) {
		THUOC_TINH = tHUOC_TINH;
	}
	public int getCHON() {
		return CHON;
	}
	public void setCHON(int cHON) {
		CHON = cHON;
	}
	public int getSTT() {
		return STT;
	}
	public void sendObject(DataOutputStream dos) {
		try {
			dos.writeUTF(Memory.N2S(MA_DVIQLY));
			dos.writeUTF(Memory.N2S(MA_YCAU_KNAI));
			dos.writeInt(STT);
			dos.writeUTF(Memory.N2S(TEN));
			dos.writeInt(LENH);
			dos.writeFloat(X1);
			dos.writeFloat(Y1);
			dos.writeFloat(X2);
			dos.writeFloat(Y2);
			dos.writeFloat(X3);
			dos.writeFloat(Y3);
			dos.writeInt(HINH);
			dos.writeInt(THUOC_TINH);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public void readObject(DataInputStream din) {
		try {
			MA_DVIQLY = din.readUTF();
			MA_YCAU_KNAI = din.readUTF();
			STT = din.readInt();
			TEN= din.readUTF();
			LENH= din.readInt();
			X1=din.readFloat();
			Y1=din.readFloat();
			X2=din.readFloat();
			Y2=din.readFloat();
			X3=din.readFloat();
			Y3=din.readFloat();
			HINH=din.readInt();
			THUOC_TINH=din.readInt();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}


	

}
