package hnks.kitsoft.vn.object;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
public class CallbackResult implements Serializable {
	private String Command="";
	private String ResultString="";
	public List<?> ResultObj=null;
	public String kq2="";

	public UserLogin oUSER=null;
	public List<Obj_D_VATTU> list_vattu=null;
	public List<Obj_D_NHOM_VTU> list_nhom_vattu=null;
	public ObjConfig oCF = null;
	public D_CHUKY chuky=null;

	public CallbackResult() {
		super();
	}
	public String getCommand() {
		return Command;
	}
	public void setCommand(String command) {
		Command = command;
	}
	public String getResultString() {
		return ResultString;
	}
	public void setResultString(String resultString) {
		ResultString = resultString;
	}
	public List<?> getResultObj() {
		return ResultObj;
	}
	public void setResultObj(List<?> resultObj) {
		ResultObj = resultObj;
	}
	public String getKq2() {
		return kq2;
	}
	public void setKq2(String kq2) {
		this.kq2 = kq2;
	}
	public UserLogin getoUSER() {
		return oUSER;
	}
	public void setoUSER(UserLogin oUSER) {
		this.oUSER = oUSER;
	}
	public List<Obj_D_VATTU> getList_vattu() {
		return list_vattu;
	}
	public void setList_vattu(List<Obj_D_VATTU> list_vattu) {
		this.list_vattu = list_vattu;
	}
	public List<Obj_D_NHOM_VTU> getList_nhom_vattu() {
		return list_nhom_vattu;
	}
	public void setList_nhom_vattu(List<Obj_D_NHOM_VTU> list_nhom_vattu) {
		this.list_nhom_vattu = list_nhom_vattu;
	}
	public ObjConfig getoCF() {
		return oCF;
	}
	public void setoCF(ObjConfig oCF) {
		this.oCF = oCF;
	}
	public D_CHUKY getChuky() {
		return chuky;
	}
	public void setChuky(D_CHUKY chuky) {
		this.chuky = chuky;
	}
	
	

}
