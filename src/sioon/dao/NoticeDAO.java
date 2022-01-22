package sioon.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import sioon.service.LoginedCustomer;
import sioon.vo.NoticeVO;
import oracle.jdbc.driver.OracleDriver;

public class NoticeDAO {
	private static NoticeDAO instance = new NoticeDAO();

	public static NoticeDAO getinstance() {
		return instance;
	}

	public NoticeDAO() {
	};

	public List<NoticeVO> selectAll() throws Exception {
		List<NoticeVO> list = new ArrayList<>();
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.45.65:1521:xe", "MarryWeb",
				"7777");
		Statement statement = connection.createStatement();
		// 쿼리 실행할때는 executeQuery()를 사용
		ResultSet resultSet = statement.executeQuery(
				"SELECT NT_CODE, NT_TITLE, NT_CONT, NT_MEMBER, TO_CHAR(NT_DATE,'YYYY-MM-DD') NT_DATE FROM NOTICE");
		while (resultSet.next()) {
			int no = resultSet.getInt("NT_CODE");
			String title = resultSet.getString("NT_TITLE");
			String writer = resultSet.getString("NT_MEMBER");
			String contents = resultSet.getString("NT_CONT");
			String registerDate = resultSet.getString("NT_DATE");
			// public NoticeVO(int ntNo, String ntTitle, String ntMember, String ntCont,
			// LocalDateTime ntDate) {
			NoticeVO vo = new NoticeVO(no, title, writer, contents, registerDate);
			list.add(vo);
		} // end while
		return list;

	}

	public List<NoticeVO> selectNo(int noticeNo) throws Exception {
		List<NoticeVO> list = new ArrayList<NoticeVO>();
		DriverManager.registerDriver(new OracleDriver());
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.45.65:1521:xe", "MarryWeb",
				"7777");
		StringBuilder builder = new StringBuilder();
		builder.append(" SELECT * FROM NOTICE");
		builder.append(" WHERE NT_CODE = ?");
		PreparedStatement statement = connection.prepareStatement(builder.toString());
		statement.setInt(1, noticeNo);
		ResultSet resultSet = statement.executeQuery();
		while (resultSet.next()) {
			list.add(make(resultSet));

		}
		resultSet.close();
		statement.close();
		connection.close();
		
		return list;
	}

	public NoticeVO make(ResultSet resultSet) throws Exception {
		NoticeVO foundNoticeVO = new NoticeVO();

		foundNoticeVO.setNtNo(resultSet.getInt("NT_CODE"));
		foundNoticeVO.setNtTitle(resultSet.getString("NT_TITLE"));
		foundNoticeVO.setNtCont(resultSet.getString("NT_CONT"));
		foundNoticeVO.setNtMember(resultSet.getString("NT_MEMBER"));
		foundNoticeVO.setNtDate(resultSet.getString("NT_DATE"));

		return foundNoticeVO;
	}


	public int insertNotice(NoticeVO vo) throws Exception {

		DriverManager.registerDriver(new OracleDriver());
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.45.65:1521:xe", "MarryWeb",
				"7777");
		StringBuilder builder = new StringBuilder();
		builder.append("INSERT INTO NOTICE(NT_CODE, NT_TITLE, NT_CONT, NT_MEMBER, NT_DATE)");
		builder.append("VALUES ((NOTICE_SEQ.NEXTVAL),?,?,?, SYSDATE)");
		// insert,update, delete: executeUpdate()를 사용
		PreparedStatement statement = connection.prepareStatement(builder.toString());
		statement.setString(1, vo.getNtTitle());
		statement.setString(2, vo.getNtCont());
		statement.setString(3, LoginedCustomer.getInstance().getLoginedCustomer().getCustomerId());
		int executeUpdate = statement.executeUpdate();
		statement.close();
		connection.close();
		return executeUpdate;
	}

	public int updateNotice(NoticeVO vo) throws Exception {
		DriverManager.registerDriver(new OracleDriver());
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.45.65:1521:xe", "MarryWeb",
				"7777");
		StringBuilder builder = new StringBuilder();
		builder.append("UPDATE NOTICE");
		builder.append(" SET NT_TITLE = ?,");
		builder.append(" NT_CONT = ?,");
		builder.append(" NT_DATE = SYSDATE ");
		builder.append(" WHERE ");
		builder.append(" NT_CODE = ?");

		PreparedStatement statement = connection.prepareStatement(builder.toString());
		statement.setString(1, vo.getNtTitle());
		statement.setString(2, vo.getNtCont());
		statement.setInt(3, vo.getNtNo());
		int executeUpdate = statement.executeUpdate();
		statement.close();
		connection.close();
		return executeUpdate;
	}

	// 상세 공지
	public NoticeVO notiBoardDetail(int noteNum) throws ClassNotFoundException, SQLException {
		NoticeVO noticeVO = new NoticeVO();
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.45.65:1521:xe", "MarryWeb",
				"7777");
		Statement statement = connection.createStatement();
		// 쿼리 실행할때는 executeQuery()를 사용
		ResultSet resultSet = statement.executeQuery(
				"SELECT NT_CODE, NT_TITLE, NT_CONT, NT_MEMBER, TO_CHAR(NT_DATE,'YYYY-MM-DD') NT_DATE FROM NOTICE WHERE NT_CODE = "
						+ noteNum);
		while (resultSet.next()) {
			int no = resultSet.getInt("NT_CODE");
			String title = resultSet.getString("NT_TITLE");
			String writer = resultSet.getString("NT_MEMBER");
			String contents = resultSet.getString("NT_CONT");
			String registerDate = resultSet.getString("NT_DATE");
			// public NoticeVO(int ntNo, String ntTitle, String ntMember, String ntCont,
			// LocalDateTime ntDate) {
			noticeVO = new NoticeVO(no, title, writer, contents, registerDate);

		} // end while
		return noticeVO;

	}

	public int notiDelete(int deleteNo) throws Exception {
		DriverManager.registerDriver(new OracleDriver());
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.45.65:1521:xe", "MarryWeb",
				"7777");
		StringBuilder builder = new StringBuilder(); // StringBuffer
		builder.append("	DELETE");
		builder.append("	FROM NOTICE");
		builder.append("	WHERE");
		builder.append("	NT_CODE = ?");

		PreparedStatement statement = connection.prepareStatement(builder.toString());
		statement.setInt(1, deleteNo);

		int executeUpdate = statement.executeUpdate();
		statement.close();
		connection.close();
		return executeUpdate;

	}

	public int notiSelectNum(int selectNum) throws SQLException {
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.45.65:1521:xe", "MarryWeb",
				"7777");
		StringBuilder builder = new StringBuilder();
		builder.append(" SELECT COUNT(*) FROM NOTICE WHERE NT_CODE = ?");

		PreparedStatement statement = connection.prepareStatement(builder.toString());
		statement.setInt(1, selectNum);
		ResultSet rs = statement.executeQuery();

		int count = 0;
		while (rs.next()) {
			count = rs.getInt("COUNT(*)");
		}

		statement.close();
		connection.close();
		return count;
	}

}
