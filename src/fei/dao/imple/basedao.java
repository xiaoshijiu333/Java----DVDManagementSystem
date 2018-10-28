package fei.dao.imple;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSetMetaData;

import fei.customer.users;

public class basedao {
	private static String driver;
	private static String url;
	private static String user;
	private static String password;
	
	
	static{
		driver="com.mysql.jdbc.Driver";
		url="jdbc:mysql://localhost:3306/mysqldvd";
		user="root";
		password="123456";
	}
		
	
	
	public static Connection open(){
		try {
			Class.forName(driver);
			return DriverManager.getConnection(url,user,password);
		} catch (Exception e) {		
			e.printStackTrace();
		}
		return null;
		
	}
	
	public static void close(Connection conn){
		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	//这个方法可以完成增删改所有的操作
	public static void operUpdate(String sql,List<Object> obj){		//obj集合中的元素就是准备替换sql语句中的占位符？
		Connection conn=open();
		try {
			PreparedStatement pstmt=conn.prepareStatement(sql);
			if(obj!=null){
				for(int i=0;i<obj.size();i++){
				pstmt.setObject(i+1, obj.get(i));
				}
			}		
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			close(conn);
		}
	}
	
	
	//利用了泛型方法和反射机制，定义了查询的通用方法
	public static <T> List<T> operQuery(String sql,List<Object> obj,Class<T> cls){
		Connection conn=open();
		List<T> list=new ArrayList<T>();
		try {
			PreparedStatement pstmt=conn.prepareStatement(sql);
			if(obj!=null){
				for(int i=0;i<obj.size();i++){
					pstmt.setObject(i+1, obj.get(i));
				}
			}
			ResultSet rs=pstmt.executeQuery();
			ResultSetMetaData rsd=rs.getMetaData();		//用于获取ResultSet对象中列的类型和属性信息，可以得到列名
			//列的个数，列的数据类型				
			while(rs.next()){
				T t=cls.newInstance();		//创建Class类的一个实体对象
				for(int i=0;i<rsd.getColumnCount();i++){
					String colname=rsd.getColumnName(i+1);		//获得列名
					Object value=rs.getObject(colname);
					Field field=cls.getDeclaredField(colname);	//得到实体对象的一个属性
					field.setAccessible(true); 	//给私有属性设置可访问权
					field.set(t, value);		//给对象的私有属性赋值
				}	
				list.add(t);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			close(conn);
		}
		return list;
	}
}
