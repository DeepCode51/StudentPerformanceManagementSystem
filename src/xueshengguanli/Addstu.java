package xueshengguanli;

import java.awt.*;

import javax.swing.*;

import java.sql.*;
import java.awt.event.*;


public  class Addstu extends JPanel implements ActionListener{
	JTextField 学号,姓名,系别,性别,年龄;
	JButton 录入;
	
public Addstu(){
	try {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	}catch(Exception e){
		System.err.println("不能设置外观:   "+e);
	}
	
		学号=new JTextField(12);
		姓名=new JTextField(12);
		系别=new JTextField(12);
		性别=new JTextField(12);
		年龄=new JTextField(12);
		录入=new JButton("录入");
		录入.addActionListener(this);
	
		Box box1=Box.createHorizontalBox();//横放box
		Box box2=Box.createHorizontalBox();
		Box box3=Box.createHorizontalBox();
		Box box5=Box.createHorizontalBox();
		Box box6=Box.createHorizontalBox();
		Box box4=Box.createHorizontalBox();
		box1.add(new JLabel("学号:"/*,JLabel.CENTER*/));
		box1.add(学号);
		box2.add(new JLabel("姓名:"/*,JLabel.CENTER*/));
		box2.add(姓名);
		box3.add(new JLabel("系别:"/*,JLabel.CENTER*/));
		box3.add(系别);
		box5.add(new JLabel("性别:"/*,JLabel.CENTER*/));
		box5.add(性别);
		box6.add(new JLabel("年龄:"/*,JLabel.CENTER*/));
		box6.add(年龄);
		box4.add(录入);
		Box boxH=Box.createVerticalBox();//竖放box
		boxH.add(box1);
		boxH.add(box2);
		boxH.add(box3);
		boxH.add(box5);
		boxH.add(box6);
		boxH.add(box4);
		boxH.add(Box.createVerticalGlue());
		JPanel messPanel=new JPanel();
		messPanel.add(boxH);
		setLayout(new BorderLayout());
		add(messPanel,BorderLayout.CENTER);
		validate();
	}
	public void actionPerformed(ActionEvent c){
		Jdbc jd=new Jdbc();
		Object obj=c.getSource();
		if(obj==录入){
			if(学号.getText().equals("")||姓名.getText().equals("")||系别.getText().equals("")){
				JOptionPane.showMessageDialog(this,"学生信息请填满再录入！" );
			}
			Statement stmt=null;
			ResultSet rs1=null;
			String sql,sql1;
		    sql1="select * from S where Sno='"+学号.getText()+"'";
		    sql="insert into S (Sno,Sname,sx,Ssex,Sage) values('"+学号.getText()+"','"+姓名.getText()+"','"+系别.getText()+"','"+性别.getText()+"','"+年龄.getText()+"')";
		    try{
		    	Connection dbConn1=jd.CONN();
		    	stmt=(Statement)dbConn1.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
		    	rs1=stmt.executeQuery(sql1);
		    	if(rs1.next()){
		    		JOptionPane.showMessageDialog(this,"该学号以存在，无法添加");
		    	}else{
		    		stmt.executeUpdate(sql);	
		    		JOptionPane.showMessageDialog(this,"添加成功");
		    	}		
		    	rs1.close();
		    	stmt.close();
		    }catch(SQLException e){
		    	System.out.print("SQL Exception occur.Message is:"+e.getMessage());
		    }
		}
	}


}
