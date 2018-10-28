package fei.shituceng;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import fei.customer.users;
import fei.yewu.usersyewu;
import fei.yewu.useryewuimpl;

public class registerView extends JFrame {
	private JPanel jp_main=null;
	private JPanel jp[]=null;
	private JLabel jb_uname=null;
	private JLabel jb_upass=null;
	private JLabel jb_upassconfirm=null;
	private JTextField jt_uname=null;
	private JPasswordField jp_upass=null;
	private JPasswordField jp_upassconfirm=null;
	private JButton jb_confirm=null;
	private JButton jb_back=null;
	private usersyewu uyewu=null;
	private users u=null;
	
	
	public registerView(){
		uyewu=new useryewuimpl();
		
		this.setTitle("用户注册窗口");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setBounds(150,150,400,300);
		
		jp_main=new JPanel(new GridLayout(5,1,0,10));
		jp=new JPanel[5];
		for(int i=0;i<5;i++){
			jp[i]=new JPanel();		
		}
		jb_uname=new JLabel("用户名：     ");
		jb_upass=new JLabel("输入密码： ");
		jb_upassconfirm=new JLabel("确认密码： ");
		jt_uname=new JTextField(15);
		jp_upass=new JPasswordField(15);
		jp_upassconfirm=new JPasswordField(15);
		jb_confirm=new JButton("确认提交");
		jb_confirm.setFocusPainted(false);
		jb_confirm.setBackground(Color.orange);
		
		jb_confirm.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String uname=jt_uname.getText().trim();
				String upass=String.valueOf(jp_upass.getPassword());
				String upassconfirm=String.valueOf(jp_upassconfirm.getPassword());
				u=new users(uname, upass, 0);
				if(u.getUname().equals("")){
					JOptionPane.showMessageDialog(registerView.this,"请输入用户名");			
				}else if(u.getUpass().equals("")){
					JOptionPane.showMessageDialog(registerView.this, "请输入密码");
				}else if(upassconfirm.equals("")){
					JOptionPane.showMessageDialog(registerView.this, "请输入确认密码");
				}else if(!upass.equals(upassconfirm)){
					JOptionPane.showMessageDialog(registerView.this,"两次输入密码不一样");
				}										
				else{
					int flag1=JOptionPane.showConfirmDialog(registerView.this, "是否确定注册","确认信息", JOptionPane.YES_NO_OPTION);						
					if(flag1==JOptionPane.YES_OPTION){
						int value=uyewu.register(u);
						if(value==1){
							JOptionPane.showMessageDialog(registerView.this,"注册成功");
							registerView.this.dispose();		//跳出了其他窗口，关闭（毁坏）当前窗口
							new loginView();
						}else{
							int flag2=JOptionPane.showConfirmDialog(registerView.this, "用户名已经存在，注册失败，跳转到登录界面或者重新注册","提示信息", JOptionPane.YES_NO_OPTION);
							if(flag2==JOptionPane.YES_OPTION){
								registerView.this.dispose();
								new loginView();
							}else{
								jt_uname.setText("");
							}
						}
					}
					else{
						JOptionPane.showMessageDialog(registerView.this,"注册失败");
					}	
				}
			}
		});
		
		
		jb_back=new JButton("退出");
		jb_back.setFocusPainted(false);
		jb_back.setBackground(Color.orange);
		
		jb_back.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int flag=JOptionPane.showConfirmDialog(registerView.this, "是否确定退出","确定信息", JOptionPane.YES_NO_OPTION);
				if(flag==JOptionPane.YES_OPTION){
					registerView.this.dispose();
					new loginView();
				}	
			}
		});
		
		
		for(int i=0;i<5;i++){
			jp_main.add(jp[i]);
		}
		jp[1].add(jb_uname);
		jp[1].add(jt_uname);
		jp[2].add(jb_upass);
		jp[2].add(jp_upass);
		jp[3].add(jb_upassconfirm);
		jp[3].add(jp_upassconfirm);
		jp[4].add(jb_confirm);
		jp[4].add(jb_back);
		
		this.getContentPane().add(jp_main);
		this.setVisible(true);
	}
}
