package fei.customer;

public class dvds {
	private int id;
	private String dname;
	private int status;
	private int count;
	
	//需要一个无参的构造方法
	public dvds(){
		
	}
	
	public dvds(int id, String dname, int count,int status) {
		super();
		this.id = id;
		this.dname = dname;
		this.status = status;
		this.count = count;
	}
		
	public dvds(String dname, int count,int status) {
		super();
		this.dname = dname;
		this.status = status;
		this.count = count;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	

}
