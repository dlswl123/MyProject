package myproject;

public class InOutVo {
	private String ioName;
	private int ledNo;
	
	public InOutVo() {
		super();
	} // 기본생성자

	public InOutVo(String ioName, int ledNo) {
		super();
		this.ioName = ioName;
		this.ledNo = ledNo;
	}

	public String getIoName() {
		return ioName;
	}

	public void setIoName(String ioName) {
		this.ioName = ioName;
	}

	public int getLedno() {
		return ledNo;
	}

	public void setLedNo(int ledNo) {
		this.ledNo = ledNo;
	}

	@Override
	public String toString() {
		return "InOutVo [ioName=" + ioName + ", ledNo=" + ledNo + "]";
	}

} // class
