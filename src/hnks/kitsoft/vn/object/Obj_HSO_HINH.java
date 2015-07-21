package hnks.kitsoft.vn.object;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.Serializable;

import android.database.Cursor;

@SuppressWarnings("serial")
public class Obj_HSO_HINH  implements Serializable{
	public String MA_DVIQLY;
	public String MA_YCAU_KNAI;
	public int STT;
	public String TEN_HINH;
	public byte[] HINH;
	public String THOI_GIAN;
	public int    DA_CHUYEN;
	public String MA_LOAI_HINH;
	public Obj_HSO_HINH(String MA_DVIQLY,
						String MA_YCAU_KNAI,
						int STT,
						String TEN_HINH,
						byte[] HINH,
						String THOI_GIAN,
						int    DA_CHUYEN,
						String MA_LOAI_HINH) {
		
		this.MA_DVIQLY = MA_DVIQLY;
		this.MA_YCAU_KNAI  = MA_YCAU_KNAI;
		this.STT = STT;
		this.TEN_HINH = TEN_HINH;
		this.HINH = HINH;
		this.THOI_GIAN = THOI_GIAN;
		this.DA_CHUYEN = DA_CHUYEN;
		this.MA_LOAI_HINH = MA_LOAI_HINH;
	}
	
	public Obj_HSO_HINH() {
		super();
	}

	public void sendObject(DataOutputStream dos) {
		try {
			dos.writeUTF(MA_DVIQLY);
			dos.writeUTF(MA_YCAU_KNAI);
			dos.writeInt(STT);
			dos.writeUTF(TEN_HINH);
			int sizeHinh=0;
			if (HINH==null) {
				sizeHinh=0;
				dos.writeInt(sizeHinh);
			}else {
				sizeHinh=HINH.length;
				dos.writeInt(sizeHinh);
				dos.write(HINH);
			}
			dos.writeUTF(THOI_GIAN);
			dos.writeInt(DA_CHUYEN);
			dos.writeUTF(MA_LOAI_HINH);
		} catch (Exception e) {
			int j=0;
			j=j+1;
		}
	}

	public void SET_OBJ(Cursor cur){
		MA_DVIQLY = cur.getString(cur.getColumnIndex("MA_DVIQLY"));
		MA_YCAU_KNAI   = cur.getString(cur.getColumnIndex("MA_YCAU_KNAI"));
		STT  = cur.getInt(cur.getColumnIndex("STT"));
		TEN_HINH  = cur.getString(cur.getColumnIndex("TEN_HINH"));
		//Try catch truong hop null
		try {
			HINH = cur.getBlob(cur.getColumnIndex("HINH"));
		} catch (Exception e){HINH=null;}
		THOI_GIAN  = cur.getString(cur.getColumnIndex("THOI_GIAN"));
		DA_CHUYEN  = cur.getInt(cur.getColumnIndex("DA_CHUYEN"));
		MA_LOAI_HINH  = cur.getString(cur.getColumnIndex("MA_LOAI_HINH"));
	}

	public void readObject(DataInputStream din)  {
		try {
			MA_DVIQLY=din.readUTF();
			MA_YCAU_KNAI=din.readUTF();
			STT=din.readInt();
			TEN_HINH=din.readUTF();
			int sizeHinh=din.readInt();
			if (sizeHinh>0) {
				byte[] hinh=new byte[sizeHinh];
				din.read(hinh, 0, sizeHinh);
				HINH=hinh;
			}
			THOI_GIAN=din.readUTF();
			DA_CHUYEN=din.readInt();
			MA_LOAI_HINH=din.readUTF();
		} catch(Exception e) {}
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

	public int getSTT() {
		return STT;
	}

	public void setSTT(int sTT) {
		STT = sTT;
	}

	public String getTEN_HINH() {
		return TEN_HINH;
	}

	public void setTEN_HINH(String tEN_HINH) {
		TEN_HINH = tEN_HINH;
	}

	public byte[] getHINH() {
		return HINH;
	}

	public void setHINH(byte[] hINH) {
		HINH = hINH;
	}

	public String getTHOI_GIAN() {
		return THOI_GIAN;
	}

	public void setTHOI_GIAN(String tHOI_GIAN) {
		THOI_GIAN = tHOI_GIAN;
	}

	public int getDA_CHUYEN() {
		return DA_CHUYEN;
	}

	public void setDA_CHUYEN(int dA_CHUYEN) {
		DA_CHUYEN = dA_CHUYEN;
	}

	public String getMA_LOAI_HINH() {
		return MA_LOAI_HINH;
	}

	public void setMA_LOAI_HINH(String mA_LOAI_HINH) {
		MA_LOAI_HINH = mA_LOAI_HINH;
	}
	


	
}
