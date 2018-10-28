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
	
	//����������������ɾ�����еĲ���
	public static void operUpdate(String sql,List<Object> obj){		//obj�����е�Ԫ�ؾ���׼���滻sql����е�ռλ����
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
	
	
	//�����˷��ͷ����ͷ�����ƣ������˲�ѯ��ͨ�÷���
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
			ResultSetMetaData rsd=rs.getMetaData();		//���ڻ�ȡResultSet�������е����ͺ�������Ϣ�����Եõ�����
			//�еĸ������е���������				
			while(rs.next()){
				T t=cls.newInstance();		//����Class���һ��ʵ�����
				for(int i=0;i<rsd.getColumnCount();i++){
					String colname=rsd.getColumnName(i+1);		//�������
					Object value=rs.getObject(colname);
					Field field=cls.getDeclaredField(colname);	//�õ�ʵ������һ������
					field.setAccessible(true); 	//��˽���������ÿɷ���Ȩ
					field.set(t, value);		//�������˽�����Ը�ֵ
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
