package hnks.kitsoft.vn.object;

import hnks.kitsoft.utils.Utils;
import hnks.kitsoft.vn.Memory;

import java.io.DataInputStream;
import java.io.DataOutputStream;

import com.thtsoftlib.function.ThtDatabase;

import android.database.Cursor;

public class Obj_GHICHU {
	public String MA_DVIQLY;
	public String MA_YCAU_KNAI;
	public int STT;
	public String GHI_CHU;
	public String THOI_GIAN;
	public int DA_CHUYEN;
	public int THUOC_TINH;

	public Obj_GHICHU(String MA_DVIQLY, String MA_YCAU_KNAI, int STT,
			String GHI_CHU, String THOI_GIAN, int DA_CHUYEN, int THUOC_TINH) {
		this.MA_DVIQLY = MA_DVIQLY;
		this.MA_YCAU_KNAI = MA_YCAU_KNAI;
		this.STT = STT;
		this.GHI_CHU = GHI_CHU;
		this.THOI_GIAN= THOI_GIAN;
		this.DA_CHUYEN = DA_CHUYEN;
		this.THUOC_TINH = THUOC_TINH;
	}
	public Obj_GHICHU() {
		// TODO Auto-generated constructor stub
	}
	public void sendObject(DataOutputStream dos) {
		try {
			dos.writeUTF(Memory.N2S(MA_DVIQLY));
			dos.writeUTF(Memory.N2S(MA_YCAU_KNAI));
			dos.writeInt(STT);
			dos.writeUTF(Memory.N2S(GHI_CHU));
			dos.writeUTF(Memory.N2S(THOI_GIAN));
			dos.writeInt(DA_CHUYEN);
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
			GHI_CHU = din.readUTF();
			THOI_GIAN = din.readUTF();
			DA_CHUYEN = din.readInt();
			THUOC_TINH = din.readInt();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public void SET_OBJ(Cursor c) {
		MA_DVIQLY = ThtDatabase.get_cursor_string(c, Utils.MA_DVIQLY);
		MA_YCAU_KNAI = ThtDatabase.get_cursor_string(c, Utils.MA_YCAU_KNAI);;
		STT = ThtDatabase.get_cursor_int(c, Utils.STT);;
		GHI_CHU = ThtDatabase.get_cursor_string(c, Utils.GHI_CHU);;
		THOI_GIAN = ThtDatabase.get_cursor_string(c, Utils.THOI_GIAN);;
		DA_CHUYEN = ThtDatabase.get_cursor_int(c, Utils.DA_CHUYEN);;
		THUOC_TINH = ThtDatabase.get_cursor_int(c, Utils.THUOC_TINH);;
		
	}
}
