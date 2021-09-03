package han;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import dto.MemDTO;

public class addMembershipDB {
	private static final String url = "jdbc:oracle:thin:@210.221.253.215:1521:xe";
	private static final String id = "five";
	private static final String pwd = "oracle";
	String sql ;
	int result = 0;
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	ArrayList<MemDTO> list = new ArrayList<MemDTO>();
	public addMembershipDB() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public int membershipIdCheckDB(String userId) {
		try {
			con = DriverManager.getConnection(url, id, pwd);
			sql = "select * from MEMMANAGE where id = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, userId);
			rs = ps.executeQuery();
			if(rs.next()) {
				return 1;
			}else return 0;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(ps != null) ps.close();
				if(con != null) con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		} 
		return 0;
	}
	public int addMembershipDB(String userId,String userName,int userAge,
			String userGender,String userNum,String userAddress,String userInfo) {
		try {
			con = DriverManager.getConnection(url, id, pwd);
			sql = "insert into MEMMANAGE values(?,?,?,?,?,?,?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, userId);
			ps.setString(2, userName);
			ps.setInt(3, userAge);
			ps.setString(4, userGender);
			ps.setString(5, userNum);
			ps.setString(6, userAddress);
			ps.setString(7, userInfo);
			return result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(ps != null) ps.close();
				if(con != null) con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		} 
		return result;
	}
}
