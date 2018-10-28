package fei.impledao;

import java.util.List;

import fei.customer.users;

public interface usersdao {
	public void adduser(users u);
	public void deleteuser(int id);
	public void updateuser(users u);
	public users queryuser1(int id);
	public users queryuser2(String uname);
	public List<users> queryuser3();

}
