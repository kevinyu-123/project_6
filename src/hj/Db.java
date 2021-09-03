package hj;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.MemDTO;

public class Db {
	private String url = "jdbc:oracle:thin:@210.221.253.215:1521:xe";
	private String id = "five";
	private String pwd = "oracle";

	public Db() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<MemDTO> findAll() {
		ArrayList<MemDTO> list = new ArrayList<MemDTO>();
		MemDTO dto = null;
		String sql = "SELECT * FROM memManage";
		try {
			Connection con = DriverManager.getConnection(url, id, pwd);
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				dto = new MemDTO();
				dto.setId(rs.getString("id"));
				dto.setName(rs.getString("name"));
				dto.setAge(rs.getInt("age"));
				dto.setGender(rs.getString("gender"));
				dto.setNum(rs.getString("num"));
				dto.setAddress(rs.getString("address"));
				dto.setInfo(rs.getString("info"));
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public void show(ArrayList<MemDTO> list) {
		for (int i = 0; i < list.size(); i++) {
			System.out.println("ID : " + list.get(i).getId());
			System.out.println("NAME : " + list.get(i).getName());
			System.out.println("AGE : " + list.get(i).getAge());
			System.out.println("GENDER : " + list.get(i).getGender());
			System.out.println("NUM : " + list.get(i).getNum());
			System.out.println("ADDRESS : " + list.get(i).getAddress());
			System.out.println("INFO : " + list.get(i).getInfo());
			System.out.println("------------------------------");
		}
	}
}
