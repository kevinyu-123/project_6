package ys;

	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.util.ArrayList;

	import dto.MemDTO;

	public class DBClass {
		private String url = "jdbc:oracle:thin:@210.221.253.215:1521:xe";
		private String id = "five";
		private String pwd = "oracle";
		
		public DBClass() {
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
			} catch (ClassNotFoundException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		
		public ArrayList<MemDTO> getList() {
			ArrayList<MemDTO> list = new ArrayList<MemDTO>();
			
			try {
				Connection con = DriverManager.getConnection(url, id, pwd);
//				System.out.println("연결 되나요? ?");
				
				String sql = "SELECT * FROM memmanage";
				PreparedStatement ps = con.prepareStatement(sql);
				
				ResultSet rs = ps.executeQuery();
				while(rs.next()) {
					MemDTO mem = new MemDTO();
					mem.setId(rs.getString("id"));
					mem.setName(rs.getString("name"));
					mem.setAge(rs.getInt("age"));
					mem.setGender(rs.getString("gender"));
					mem.setNum(rs.getString("num"));
					mem.setAddress(rs.getString("address"));
					mem.setInfo(rs.getString("info"));
					
					list.add(mem);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return list;
		}
		
		public int deleteDB (String id) {
			String sql = "DELETE FROM memmanage WHERE id = ? ";
			int result = 0;
			
			try {
				Connection con = DriverManager.getConnection(url, this.id, pwd);
//				System.out.println("연결되나요? 2 222");
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, id);
				
				result = ps.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return result;
		}
	}

