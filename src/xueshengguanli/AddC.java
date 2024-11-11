package xueshengguanli;

import java.awt.*;

import javax.swing.*;

import java.sql.*;
import java.awt.event.*;

public class AddC extends JPanel implements ActionListener{
	JTextField 课号,课名;
	JButton 录入;
	
	public AddC(){
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			}catch(Exception e){
				System.err.println("不能设置外观:   "+e);
			}
	
		课号=new JTextField(12);
		课名=new JTextField(12);
		录入=new JButton("录入");
		录入.addActionListener(this);
	
		Box box1=Box.createHorizontalBox();//横放box
		Box box2=Box.createHorizontalBox();
		Box box3=Box.createHorizontalBox();
		Box box4=Box.createHorizontalBox();
		box1.add(new JLabel("课号:"));
		box1.add(课号);
		box2.add(new JLabel("课名:"));
		box2.add(课名);
		box4.add(录入);
		Box boxH=Box.createVerticalBox();//竖放box
		boxH.add(box1);
		boxH.add(box2);
		boxH.add(box3);
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
			if(课号.getText().equals("")||课名.getText().equals("")){
				JOptionPane.showMessageDialog(this,"学生信息请填满再录入！" );
			}
			Statement stmt=null;
			ResultSet rs=null,rs1=null;
			String sql,sql1;
		    sql1="select * from C where Cno='"+课号.getText()+"'";
		    sql="insert into C (Cno,Cname) values('"+课号.getText()+"','"+课名.getText()+"')";
		    try{
		    	Connection dbConn1=jd.CONN();
		    	stmt=(Statement)dbConn1.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
		    	rs1=stmt.executeQuery(sql1);
		    	if(rs1.next()){
		    		JOptionPane.showMessageDialog(this,"该科目以存在，无法添加");
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
