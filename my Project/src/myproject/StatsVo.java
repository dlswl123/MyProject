package myproject;

public class StatsVo {
	private String ledname;
	private int ledmoney;
	public StatsVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public StatsVo(String ledname, int ledmoney) {
		super();
		this.ledname = ledname;
		this.ledmoney = ledmoney;
	}
	public String getLedname() {
		return ledname;
	}
	public void setLedname(String ledname) {
		this.ledname = ledname;
	}
	public int getLedmoney() {
		return ledmoney;
	}
	public void setLedmoney(int ledmoney) {
		this.ledmoney = ledmoney;
	}
	@Override
	public String toString() {
		return "StatsVo [ledname=" + ledname + ", ledmoney=" + ledmoney + "]";
	}
	
} // class
