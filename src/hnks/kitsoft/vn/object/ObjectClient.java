package hnks.kitsoft.vn.object;

import hnks.kitsoft.vn.Main_Activity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class ObjectClient implements Serializable {
	public String Command="";  //Lenh da co ben UserLogin nen khong can o day nua
	public String Param1="";
	public String Param2="";
	public String Param3="";
	public String Param4="";
	public String Param5="";
	public List<Obj_HSO_CHIETTINH> lstObjHSO_CTINH=new ArrayList<Obj_HSO_CHIETTINH>();
	public Obj_HSO_CHIETTINH objHoSo=null; //Chi can Update 1 Object
	public ObjConfig objConfig=null; //Chi can Update 1 Object
	public List<Obj_HSO_HINH> lstHSO_HINH=new ArrayList<Obj_HSO_HINH>();
	public UserLogin Usr=null;

	public Obj_D_VATTU oDVT;
	public D_CHUKY oCK;
	public Obj_D_NHOM_VTU oNhomVT;

	public ObjectClient() {
		super();
		Param5 = Main_Activity.IMEI;
	}

	public String getCommand() {
		return Command;
	}

	public void setCommand(String command) {
		Command = command;
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

	public String getParam3() {
		return Param3;
	}

	public void setParam3(String param3) {
		Param3 = param3;
	}

	public String getParam4() {
		return Param4;
	}

	public void setParam4(String param4) {
		Param4 = param4;
	}

	public String getParam5() {
		return Param5;
	}

	public void setParam5(String param5) {
		Param5 = param5;
	}

	public List<Obj_HSO_CHIETTINH> getLstObjHSO_CTINH() {
		return lstObjHSO_CTINH;
	}

	public void setLstObjHSO_CTINH(List<Obj_HSO_CHIETTINH> lstObjHSO_CTINH) {
		this.lstObjHSO_CTINH = lstObjHSO_CTINH;
	}

	public Obj_HSO_CHIETTINH getObjHoSo() {
		return objHoSo;
	}

	public void setObjHoSo(Obj_HSO_CHIETTINH objHoSo) {
		this.objHoSo = objHoSo;
	}

	public ObjConfig getObjConfig() {
		return objConfig;
	}

	public void setObjConfig(ObjConfig objConfig) {
		this.objConfig = objConfig;
	}

	public List<Obj_HSO_HINH> getLstHSO_HINH() {
		return lstHSO_HINH;
	}

	public void setLstHSO_HINH(List<Obj_HSO_HINH> lstHSO_HINH) {
		this.lstHSO_HINH = lstHSO_HINH;
	}

	public UserLogin getUsr() {
		return Usr;
	}

	public void setUsr(UserLogin usr) {
		Usr = usr;
	}
	public Obj_D_VATTU getoDVT() {
		return oDVT;
	}

	public void setoDVT(Obj_D_VATTU oDVT) {
		this.oDVT = oDVT;
	}

	public D_CHUKY getoCK() {
		return oCK;
	}

	public void setoCK(D_CHUKY oCK) {
		this.oCK = oCK;
	}

	public Obj_D_NHOM_VTU getoNhomVT() {
		return oNhomVT;
	}

	public void setoNhomVT(Obj_D_NHOM_VTU oNhomVT) {
		this.oNhomVT = oNhomVT;
	}
	
}
