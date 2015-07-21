package hnks.kitsoft.vn.object;

import hnks.kitsoft.utils.Utils;
import hnks.kitsoft.vn.Memory;

import java.io.DataInputStream;
import java.io.DataOutputStream;

import android.database.Cursor;

public class Obj_HSO_TOADO {
	
	public String MA_DVIQLY;
	public String MA_YCAU_KNAI;
	public int STT;
	public String TEN_DIEM;
	public double    X;
	public double    Y;
	public String THOI_GIAN;

	public Obj_HSO_TOADO(   String MA_DVIQLY,
							String MA_YCAU_KNAI,
							int STT,
							String TEN_DIEM,
							double    X,
							double    Y,
							String THOI_GIAN) {
		this.MA_DVIQLY = MA_DVIQLY;
		this.MA_YCAU_KNAI  = MA_YCAU_KNAI;
		this.STT = STT;
		this.TEN_DIEM = TEN_DIEM;
		this.X = X;
		this.Y = Y;
		this.THOI_GIAN = THOI_GIAN;
	}
	public Obj_HSO_TOADO() {
		
	}
	public void sendObject(DataOutputStream dos) {
		try {
			
			dos.writeUTF(Memory.N2S(MA_DVIQLY));
			dos.writeUTF(Memory.N2S(MA_YCAU_KNAI));
			dos.writeInt(STT);
			dos.writeUTF(Memory.N2S(TEN_DIEM));
			dos.writeDouble(X);
			dos.writeDouble(Y);
			dos.writeUTF(Memory.N2S(THOI_GIAN));
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public void readObject(DataInputStream din) {
		try {
			MA_DVIQLY = din.readUTF();
			MA_YCAU_KNAI = din.readUTF();
			STT = din.readInt();
			TEN_DIEM = din.readUTF();
			X = din.readDouble();
			Y = din.readDouble();
			THOI_GIAN= din.readUTF();

		} catch (Exception e) {
			// TODO: handle exception
		}

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
	public String getTEN_DIEM() {
		return TEN_DIEM;
	}
	public void setTEN_DIEM(String tEN_DIEM) {
		TEN_DIEM = tEN_DIEM;
	}
	public double getX() {
		return X;
	}
	public void setX(double x) {
		X = x;
	}
	public double getY() {
		return Y;
	}
	public void setY(double y) {
		Y = y;
	}
	public String getTHOI_GIAN() {
		return THOI_GIAN;
	}
	public void setTHOI_GIAN(String tHOI_GIAN) {
		THOI_GIAN = tHOI_GIAN;
	}
	public int getSTT() {
		return STT;
	}
	public void SET_OBJ(Cursor cur) {
		MA_DVIQLY=cur.getString(cur.getColumnIndex(Utils.MA_DVIQLY));
		MA_YCAU_KNAI=cur.getString(cur.getColumnIndex(Utils.MA_YCAU_KNAI));
		STT=cur.getInt(cur.getColumnIndex(Utils.STT));
		TEN_DIEM=cur.getString(cur.getColumnIndex(Utils.TEN_DIEM));
		X=cur.getDouble(cur.getColumnIndex(Utils.X));
		Y=cur.getDouble(cur.getColumnIndex(Utils.Y));
		THOI_GIAN=cur.getString(cur.getColumnIndex(Utils.THOI_GIAN));
		
	}
	
}
