package xueshengguanli;

import java.sql.Connection;
import java.sql.DriverManager;

public class Jdbc {
	public static Connection CONN(){
		   String driverName = "com.mysql.jdbc.Driver";   //加载JDBC驱动
		   String dbURL = "jdbc:mysql://localhost:3306/graduation_324_student?useUnicode=true&characterEncoding=utf8";   //连接服务器和数据库test
		   String userName = "root";   //默认用户名
		   String userPwd = "123456";   //密码
		   Connection dbConn=null;
		   try {
		   Class.forName(driverName);
		   dbConn = DriverManager.getConnection(dbURL, userName, userPwd);
		   System.out.println("Connection Successful!");   //如果连接成功 控制台输出Connection Successful!
		   } catch (Exception e) {
		   e.printStackTrace();
		   }
		   return dbConn;
	}
}
