package fei.shituceng;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import fei.customer.users;
import fei.yewu.usersyewu;
import fei.yewu.useryewuimpl;

public class loginView extends JFrame{
	
	//定义组件
	private JPanel jp_main=null;
	private JPanel jp_left=null;
	private JPanel jp_right=null;
	private JLabel jb_uname=null;
	private JLabel jb_upass=null;
	private JLabel jb_type=null;
	private JLabel jb_img=null;
	private JTextField jt_uname=null;
	private JPasswordField jp_upass=null;
	private JComboBox<String> jc_type=null;
	private JButton jb_login=null;
	private JButton jb_register=null;
	private ImageIcon right_jp=null;
	private users u=null;
	private users us=null;
	private usersyewu uyewu=null;
	
	public loginView(){
		this.setTitle("用户登录窗口");
		this.setBounds(150, 150,400,200);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		
		uyewu=new useryewuimpl();
		
		//初始化组件
		jp_main=new JPanel(new GridLayout(1, 2));		//设置主面板的布局管理器是1行2列的网格型的
		right_jp=new ImageIcon(this.getClass().getResource("1.png"));
		jb_img=new JLabel(right_jp);	//图片要先放到Jlable中，才能放到Jpanel中
		jp_left=new JPanel();
		jp_right=new JPanel(new GridLayout(4, 2, 10, 10)); //设置右侧面板的布局管理器是4行2列的网格型的，列与列之间间距10，行与行之间间距10
		jb_uname=new JLabel("用户名: ",JLabel.CENTER);
		jb_upass=new JLabel("密码: ",JLabel.CENTER);
		jb_type=new JLabel("类型: ",JLabel.CENTER);
		jt_uname=new JTextField();
		jp_upass=new JPasswordField();
		String str[]={"普通用户","管理员"};
		jc_type=new JComboBox<String>(str);
		jc_type.setBackground(Color.orange);
		jb_login=new JButton("登录");
		jb_login.setFocusPainted(false);
		
		jb_login.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String uname=jt_uname.getText().trim();
				String upass=String.valueOf(jp_upass.getPassword());
				int type=jc_type.getSelectedIndex();
				u=new users(uname, upass, type);
				if(u.getUname().equals("")){
					JOptionPane.showMessageDialog(loginView.this,"请输入用户名");			
				}else if(u.getUpass().equals("")){
					JOptionPane.showMessageDialog(loginView.this, "请输入密码");
				}else{
					us=uyewu.login(u);
					if(us!=null){
						if(us.getType()==0){
							new Useroperation(us);
						}else{
							new Adminoperation(us);
						}
						loginView.this.dispose();		//跳出了其他窗口，关闭（毁坏）当前窗口
					}else{
						JOptionPane.showMessageDialog(loginView.this,"请输入正确的用户名或密码或检查类型");
					}
				}
			}
		});
		
		jb_login.setBackground(Color.orange);
		jb_register=new JButton("去注册");
		jb_register.setFocusPainted(false);	
		jb_register.setBackground(Color.orange);
		
		jb_register.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new registerView();
				loginView.this.dispose();
			}
		});
		
		
		//添加组件
		jp_main.add(jp_left);
		jp_main.add(jp_right);
		jp_left.add(jb_img);
		jp_right.add(jb_uname);
		jp_right.add(jt_uname);
		jp_right.add(jb_upass);
		jp_right.add(jp_upass);
		jp_right.add(jb_type);
		jp_right.add(jc_type);
		jp_right.add(jb_login);
		jp_right.add(jb_register);
		
		
		//将主面板添加到容器中
		this.getContentPane().add(jp_main);
		this.setVisible(true);
	}
}
