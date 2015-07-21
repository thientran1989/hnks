package hnks.kitsoft.vn.object;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ObjConfig implements Serializable{
	public String MA_DVIQLY="";
	public double PT_TT=0;
	public double PT_C=0;
	public double PT_C1=0;
	public double PT_TL=0;
	public double PT_K=0;
	public double PT_VAT=0;
	public String f0="";
	public byte[] f1=null;
	public byte[] f2=null;
	public byte[] f3=null;
	public String USER_VT="";
	public String PASS_VT="";
	public double PT_NC=0;
	public double PT_NC1=0;
	public ObjConfig() {
		super();
	}
	public double getPT_TT() {
		return PT_TT;
	}
	public void setPT_TT(double pT_TT) {
		PT_TT = pT_TT;
	}
	public double getPT_C() {
		return PT_C;
	}
	public void setPT_C(double pT_C) {
		PT_C = pT_C;
	}
	public double getPT_C1() {
		return PT_C1;
	}
	public void setPT_C1(double pT_C1) {
		PT_C1 = pT_C1;
	}
	public double getPT_TL() {
		return PT_TL;
	}
	public void setPT_TL(double pT_TL) {
		PT_TL = pT_TL;
	}
	public double getPT_K() {
		return PT_K;
	}
	public void setPT_K(double pT_K) {
		PT_K = pT_K;
	}
	public double getPT_VAT() {
		return PT_VAT;
	}
	public void setPT_VAT(double pT_VAT) {
		PT_VAT = pT_VAT;
	}
	public String getUSER_VT() {
		return USER_VT;
	}
	public void setUSER_VT(String uSER_VT) {
		USER_VT = uSER_VT;
	}
	public double getPT_NC() {
		return PT_NC;
	}
	public void setPT_NC(double pT_NC) {
		PT_NC = pT_NC;
	}
	public double getPT_NC1() {
		return PT_NC1;
	}
	public void setPT_NC1(double pT_NC1) {
		PT_NC1 = pT_NC1;
	}
	
	
}
