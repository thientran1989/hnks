package hnks.kitsoft.vn.object;

public class DM_Company {
	private Long idCom;
	private String softName="KhaoSat";
	private String tenCom="";
	private String diaChi="";
	private String dienThoai="";
	private String ma_Dviqly="";
	private String ipNgoai="";
	private String ipTrong="";
	private String ngayHetHan="";
	
	public DM_Company(String softName, String tenCom, String diaChi, String dienThoai) {
		super();
		this.softName=softName;
		this.tenCom = tenCom;
		this.diaChi = diaChi;
		this.dienThoai = dienThoai;
	}
	public DM_Company() {
		super();
	}
	public long getIdCom() {
		return idCom;
	}
	public void setIdCom(long idCom) {
		this.idCom = idCom;
	}
	public String getSoftName() {
		return softName;
	}

	public void setSoftName(String softName) {
		this.softName = softName;
	}
	public String getTenCom() {
		return tenCom;
	}
	public void setTenCom(String tenCom) {
		this.tenCom = tenCom;
	}
	public String getDiaChi() {
		return diaChi;
	}
	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
	public String getDienThoai() {
		return dienThoai;
	}
	public void setDienThoai(String dienThoai) {
		this.dienThoai = dienThoai;
	}

	
	public String getMa_Dviqly() {
		return ma_Dviqly;
	}
	public void setMa_Dviqly(String ma_Dviqly) {
		this.ma_Dviqly = ma_Dviqly;
	}
	public String getIpNgoai() {
		return ipNgoai;
	}
	public void setIpNgoai(String ipNgoai) {
		this.ipNgoai = ipNgoai;
	}
	public String getIpTrong() {
		return ipTrong;
	}
	public void setIpTrong(String ipTrong) {
		this.ipTrong = ipTrong;
	}
	public String getNgayHetHan() {
		return ngayHetHan;
	}
	public void setNgayHetHan(String ngayHetHan) {
		this.ngayHetHan = ngayHetHan;
	}
	
}
