package hnks.kitsoft.vn.object;

import java.io.Serializable;

@SuppressWarnings("serial")
public class UserLogin implements Serializable  {
	public String MA_DVIQLY="";
	public String tenDangNhap="";
	public String matKhau="";
	public String cmdLenh="";
	public String MA_BPHAN="";
	public String privilege="";
	public String Ten_NV="";

	public String Param1="";
	public String Param2="";

	public UserLogin() {
		super();
	}
//	public void copy(UserLogin ul) {
//		MA_DVIQLY=ul.MA_DVIQLY;
//		tenDangNhap=ul.tenDangNhap;
//		matKhau=ul.matKhau;
//		cmdLenh=ul.cmdLenh;
//		MA_BPHAN=ul.MA_BPHAN;
//		privilege=ul.privilege;
//		Ten_NV=ul.Ten_NV;
//	}

	public String getMA_DVIQLY() {
		return MA_DVIQLY;
	}

	public void setMA_DVIQLY(String mA_DVIQLY) {
		MA_DVIQLY = mA_DVIQLY;
	}

	public String getTenDangNhap() {
		return tenDangNhap;
	}

	public void setTenDangNhap(String tenDangNhap) {
		this.tenDangNhap = tenDangNhap;
	}

	public String getMatKhau() {
		return matKhau;
	}

	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}

	public String getCmdLenh() {
		return cmdLenh;
	}

	public void setCmdLenh(String cmdLenh) {
		this.cmdLenh = cmdLenh;
	}

	public String getMA_BPHAN() {
		return MA_BPHAN;
	}

	public void setMA_BPHAN(String mA_BPHAN) {
		MA_BPHAN = mA_BPHAN;
	}

	public String getPrivilege() {
		return privilege;
	}

	public void setPrivilege(String privilege) {
		this.privilege = privilege;
	}

	public String getTen_NV() {
		return Ten_NV;
	}

	public void setTen_NV(String ten_NV) {
		Ten_NV = ten_NV;
	}

	public String getParam1() {
		return Param1;
	}

	public void setParam1(String param1) {
		Param1 = param1;
	}

	public String getParam2() {
		return Param2;
	}

	public void setParam2(String param2) {
		Param2 = param2;
	}
	
	
}
