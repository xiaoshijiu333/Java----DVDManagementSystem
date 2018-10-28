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
	
	//�������
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
		this.setTitle("�û���¼����");
		this.setBounds(150, 150,400,200);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		
		uyewu=new useryewuimpl();
		
		//��ʼ�����
		jp_main=new JPanel(new GridLayout(1, 2));		//���������Ĳ��ֹ�������1��2�е������͵�
		right_jp=new ImageIcon(this.getClass().getResource("1.png"));
		jb_img=new JLabel(right_jp);	//ͼƬҪ�ȷŵ�Jlable�У����ܷŵ�Jpanel��
		jp_left=new JPanel();
		jp_right=new JPanel(new GridLayout(4, 2, 10, 10)); //�����Ҳ����Ĳ��ֹ�������4��2�е������͵ģ�������֮����10��������֮����10
		jb_uname=new JLabel("�û���: ",JLabel.CENTER);
		jb_upass=new JLabel("����: ",JLabel.CENTER);
		jb_type=new JLabel("����: ",JLabel.CENTER);
		jt_uname=new JTextField();
		jp_upass=new JPasswordField();
		String str[]={"��ͨ�û�","����Ա"};
		jc_type=new JComboBox<String>(str);
		jc_type.setBackground(Color.orange);
		jb_login=new JButton("��¼");
		jb_login.setFocusPainted(false);
		
		jb_login.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String uname=jt_uname.getText().trim();
				String upass=String.valueOf(jp_upass.getPassword());
				int type=jc_type.getSelectedIndex();
				u=new users(uname, upass, type);
				if(u.getUname().equals("")){
					JOptionPane.showMessageDialog(loginView.this,"�������û���");			
				}else if(u.getUpass().equals("")){
					JOptionPane.showMessageDialog(loginView.this, "����������");
				}else{
					us=uyewu.login(u);
					if(us!=null){
						if(us.getType()==0){
							new Useroperation(us);
						}else{
							new Adminoperation(us);
						}
						loginView.this.dispose();		//�������������ڣ��رգ��ٻ�����ǰ����
					}else{
						JOptionPane.showMessageDialog(loginView.this,"��������ȷ���û����������������");
					}
				}
			}
		});
		
		jb_login.setBackground(Color.orange);
		jb_register=new JButton("ȥע��");
		jb_register.setFocusPainted(false);	
		jb_register.setBackground(Color.orange);
		
		jb_register.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new registerView();
				loginView.this.dispose();
			}
		});
		
		
		//������
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
		
		
		//���������ӵ�������
		this.getContentPane().add(jp_main);
		this.setVisible(true);
	}
}
