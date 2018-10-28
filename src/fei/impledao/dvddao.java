package fei.impledao;

import java.util.List;

import fei.customer.dvds;

public interface dvddao {
	public void adddvd(dvds d);
	public void deletedvd(int id);
	public void updatedvd(dvds d);
	public dvds querydvd1(int id);
	public dvds querydvd2(String dname);
	public List<dvds> querydvd3();
	public List<dvds> querydvd4(int status);
}
