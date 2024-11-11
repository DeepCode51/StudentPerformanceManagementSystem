package xueshengguanli;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;


public class User extends JFrame{
private JLabel use,password;
private JTextField k1;//用户名输入框
private JPasswordField k2;//密码输入框
private JButton b1,b2;

//登录窗口
public User(JFrame f){
	
	super("系统登录");
	Container c=getContentPane();
	c.setLayout(new FlowLayout());
	use=new JLabel("用户名:");
	use.setFont(new Font("Serif",Font.PLAIN,20));
	password=new JLabel("密码:");
	password.setFont(new Font("Serif",Font.PLAIN,20));
	k1=new JTextField(12);
	k2=new JPasswordField(12);
	b1=new JButton("登录");
	b2=new JButton("退出");

	//	设置登录方法
	BHandler b=new BHandler();
	EXIT d=new EXIT();
	b1.addActionListener(b);
	b2.addActionListener(d);
	
		//添加控件
	c.add(use);
	c.add(k1);
	c.add(password);
	c.add(k2);
	c.add(b1);
	c.add(b2);
	
	setBounds(600,300,250,150);
	setVisible(true);
	setResizable(false);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
}
//主函数
public static void main(String[] args) {
	User f1=new User(new JFrame());
	}
//登录按钮方法
private class BHandler implements ActionListener{
	Jdbc jd=new Jdbc();
		public void actionPerformed(ActionEvent event){
			if(k1.getText().equals("")||k2.getText().equals("")){
				JOptionPane.showMessageDialog(User.this,"用户名密码不能为空！" );
			}else{
				Statement stmt=null;
				ResultSet rs=null;
				String sql;
  			    sql="select * from admin where username='"+k1.getText()+"'";
  			    try{
  			    	Connection dbConn1=jd.CONN();
					stmt=(Statement)dbConn1.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
					rs=stmt.executeQuery(sql);					
					if(rs.next()){
						String xm=rs.getString("password");
						if(k2.getText().equals(xm.trim())){JOptionPane.showMessageDialog(User.this,"登录成功");
						dispose();
						new Menu();//管理窗口																
					}else{
						JOptionPane.showMessageDialog(User.this,"密码错误");}
					}else{
						JOptionPane.showMessageDialog(User.this,"用户名错误");}
						rs.close();
						stmt.close();
  			    }catch(SQLException e){
				   JOptionPane.showMessageDialog(User.this,"SQL Exception occur.Message is:"+e.getMessage());
				 }
			}			
		}
	}
//退出方法结束
private class EXIT implements ActionListener{
	public void actionPerformed(ActionEvent even){
		System.exit(0);
	}
}


}//父类结束
