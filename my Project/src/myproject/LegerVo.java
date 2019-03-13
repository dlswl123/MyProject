package myproject;

public class LegerVo {
	private int ledNo;
	private String ledName;
	private int ledMoney;
	private String ledType;
	private String ledDay;
	private String ledMemo;
	
	public LegerVo() {
		super();
	}
	
	public LegerVo(int ledNo, String ledName, int ledMoney, String ledType, String ledDay, String ledMemo) {
		super();
		this.ledNo = ledNo;
		this.ledName = ledName;
		this.ledMoney = ledMoney;
		this.ledType = ledType;
		this.ledDay = ledDay;
		this.ledMemo = ledMemo;
	}
	public int getLedNo() {
		return ledNo;
	}
	public void setLedno(int ledNo) {
		this.ledNo = ledNo;
	}
	public String getLedName() {
		return ledName;
	}
	public void setLedName(String ledName) {
		this.ledName = ledName;
	}
	public int getLedMoney() {
		return ledMoney;
	}
	public void setLedMoney(int ledMoney) {
		this.ledMoney = ledMoney;
	}
	public String getLedType() {
		return ledType;
	}
	public void setLedType(String ledType) {
		this.ledType = ledType;
	}
	public String getLedDay() {
		return ledDay;
	}
	public void setLedDay(String ledDay) {
		this.ledDay = ledDay;
	}
	public String getLedMemo() {
		return ledMemo;
	}
	public void setLedMemo(String ledMemo) {
		this.ledMemo = ledMemo;
	}
	
	@Override
	public String toString() {
		return "LegerVo [ledNo=" + ledNo + ", ledName=" + ledName + ", ledMoney=" + ledMoney + ", ledType=" + ledType
				+ ", ledDay=" + ledDay + ", ledMemo=" + ledMemo + "]";
	}
	
}
