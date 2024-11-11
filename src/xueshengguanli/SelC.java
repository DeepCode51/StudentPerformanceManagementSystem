package xueshengguanli;

import java.sql.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.util.*;

public class SelC extends JFrame {
	Vector rowData, columnNames;
	Statement stmt=null;
    String sql=null;
    JTable jt = null;
    JScrollPane jsp = null;
    PreparedStatement ps=null;
    ResultSet rs=null;
public SelC(String sql1){
		Jdbc jd=new Jdbc();
		columnNames = new Vector();
		// 设置列名
		columnNames.add("课号");
		columnNames.add("课名");

		rowData=new Vector();
		sql=sql1;
		try{
			Connection dbConn1=jd.CONN();
			stmt=(Statement)dbConn1.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs=stmt.executeQuery(sql);

    while(rs.next()){
    	Vector hang = new Vector();
    	hang.add(rs.getString("Cno"));System.out.print(rs.getString("Cno"));
    	hang.add(rs.getString("Cname"));System.out.print(rs.getString("Cname"));
    	rowData.add(hang);}
    	jt=new JTable(rowData,columnNames);
    	jsp=new JScrollPane(jt);
    	this.add(jsp);
    	this.setSize(400,300);
    	this.setVisible(true);
	}catch(SQLException e1){
		System.out.print("SQL Exception occur.Message is:"+e1.getMessage());
   }
}


}
