package fei.customer;

public class record2 {
	private int id;
	private String uname;	//借的人的名字
	private String dname;	//借的DVD的名字
	private String lendtime;
	private String returntime;
	
	public record2(){
		
	}
	
	public record2(int id, String uname, String dname, String lendtime,
			String returntime) {
		super();
		this.id = id;
		this.uname = uname;
		this.dname = dname;
		this.lendtime = lendtime;
		this.returntime = returntime;
	}
		
	public record2(String uname, String dname, String lendtime,
			String returntime) {
		super();
		this.uname = uname;
		this.dname = dname;
		this.lendtime = lendtime;
		this.returntime = returntime;
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	public String getLendtime() {
		return lendtime;
	}
	public void setLendtime(String lendtime) {
		this.lendtime = lendtime;
	}
	public String getReturntime() {
		return returntime;
	}
	public void setReturntime(String returntime) {
		this.returntime = returntime;
	}
	

}
