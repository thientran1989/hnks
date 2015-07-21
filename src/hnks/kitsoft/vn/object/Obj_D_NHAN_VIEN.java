package hnks.kitsoft.vn.object;

import hnks.kitsoft.utils.Utils;

import android.database.Cursor;

public class Obj_D_NHAN_VIEN {

	public long idNhanVien;
	public String MaNhanVien;
	public String TenNhanVien;
	public String UserName;
	public String MatKhau;
	public String MaDV = "";
	public String IPLocal = "";
	public String IPServer = "";
	public Obj_D_NHAN_VIEN() {
	}
	public Obj_D_NHAN_VIEN(long idNhanVien, String MaNhanVien,
			String TenNhanVien, String UserName, String MatKhau,
			String MADV,String IPLocal, String IPServer) {
		this.idNhanVien = idNhanVien;
		this.MaNhanVien = MaNhanVien;
		this.TenNhanVien = TenNhanVien;
		this.UserName = UserName;
		this.MatKhau = MatKhau;
		this.MaDV = MADV;
		this.IPLocal = IPLocal;
		this.IPServer = IPServer;
	}
	public String getMaNhanVien() {
		return MaNhanVien;
	}
	public void setMaNhanVien(String maNhanVien) {
		MaNhanVien = maNhanVien;
	}
	public String getTenNhanVien() {
		return TenNhanVien;
	}
	public void setTenNhanVien(String tenNhanVien) {
		TenNhanVien = tenNhanVien;
	}
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	public String getMatKhau() {
		return MatKhau;
	}
	public void setMatKhau(String matKhau) {
		MatKhau = matKhau;
	}
	public String getMaDV() {
		return MaDV;
	}
	public void setMaDV(String maDV) {
		MaDV = maDV;
	}
	public String getIPLocal() {
		return IPLocal;
	}
	public void setIPLocal(String iPLocal) {
		IPLocal = iPLocal;
	}
	public String getIPServer() {
		return IPServer;
	}
	public void setIPServer(String iPServer) {
		IPServer = iPServer;
	}
	public long getIdNhanVien() {
		return idNhanVien;
	}
	public void SET_OBJ(Cursor cur) {
		idNhanVien=cur.getInt(cur.getColumnIndex(Utils.idNhanVien));
		MaNhanVien=cur.getString(cur.getColumnIndex(Utils.MaNhanVien));
		TenNhanVien=cur.getString(cur.getColumnIndex(Utils.TenNhanVien));
		UserName=cur.getString(cur.getColumnIndex(Utils.UserName));
		MatKhau=cur.getString(cur.getColumnIndex(Utils.MatKhau));
		MaDV=cur.getString(cur.getColumnIndex(Utils.MaDV));
		IPLocal=cur.getString(cur.getColumnIndex(Utils.IPLocal));
		IPServer=cur.getString(cur.getColumnIndex(Utils.IPServer));
		
	}
	

}
