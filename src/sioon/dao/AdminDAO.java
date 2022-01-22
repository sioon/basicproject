package sioon.dao;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import sioon.service.LoginedCustomer;

public class AdminDAO {

	private static AdminDAO instance = new AdminDAO();

	public static AdminDAO getInstance() {
		return instance;
	}

	private AdminDAO() {
	}
	
	public int adminAuthority () throws Exception {
		//List<AdminVO> list = new ArrayList<>();
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.45.65:1521:xe", "MarryWeb",
				"7777");
		StringBuilder builder = new StringBuilder();
		builder.append(" SELECT NVL(MEM_ADMIN,'0') FROM MEMBER WHERE MEM_ID = ?");

		PreparedStatement statement = connection.prepareStatement(builder.toString());
		statement.setString(1, LoginedCustomer.getInstance().getLoginedCustomer().getCustomerId());
		
		ResultSet rs = statement.executeQuery();
	
		int no = 0;
		
		while(rs.next()) {
			no = rs.getInt("NVL(MEM_ADMIN,'0')");
		}
		
		
		statement.close();
		connection.close();
		return no;
 }
	
}
