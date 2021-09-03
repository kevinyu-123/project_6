package eun;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Scanner;
import dto.MemDTO;

public class EunDB {
	private String url = "jdbc:oracle:thin:@210.221.253.215:1521:xe";
	private String id = "five";
	private String pwd = "oracle";

	public EunDB() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public MemDTO search(String id) { // 검색기능메소드
		String sql = "select * from memmanage where id = '" + id + "'";
		MemDTO dto = null;
		try {
			Connection con = DriverManager.getConnection(url, this.id, pwd);
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				dto = new MemDTO();
				dto.setId(rs.getString("id"));
				dto.setName(rs.getString("name"));
				dto.setAge(rs.getInt("age"));
				dto.setNum(rs.getString("num"));
				dto.setAddress(rs.getString("address"));
				dto.setGender(rs.getString("gender"));
				dto.setInfo(rs.getString("info"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}

	public int alterData(String id, String gender, String name, int age, String num, String address, String info) {
		int result = 0;
		String sql = "update memmanage set name= ? ,age=? ,gender=?,num=?,address=?,info=? where id =? ";
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = DriverManager.getConnection(url, this.id, pwd);
			ps = con.prepareStatement(sql);

			ps.setString(7, id);
			ps.setString(1, name);
			ps.setInt(2, age);
			ps.setString(3, gender);
			ps.setString(4, num);
			ps.setString(5, address);
			ps.setString(6, info);

			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null)
					ps.close();
				if (con != null)
					con.close();

			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}

	public void update() {
		String id, name, gender, address, info,num;
		int age;
		int result = 0;
		Scanner input = new Scanner(System.in);
		ArrayList<MemDTO> arr = new ArrayList<MemDTO>();
		EunDB db = new EunDB();

		System.out.print("수정하려면 id 입력하세요 : ");
		id = input.next();
		MemDTO dto = db.search(id);
		if (dto != null) {
			System.out.println("-----------" + id + "님의 저장된 정보---------");
			System.out.println("학생 이름 : " + dto.getName());
			System.out.println("학생 나이 : " + dto.getAge());
			System.out.println("학생 전화번호 : " + dto.getNum());
			System.out.println("학생 성별 : " + dto.getGender());
			System.out.println("학생 주소: " + dto.getAddress());
			System.out.println("학생 info: " + dto.getInfo());
			System.out.println("---------------------------------");

			System.out.println("수정할 아이디 입력");
			id = input.next();
			System.out.println("수정(변경)할 이름");
			name = input.next();
			System.out.println("수정(변경)할 나이");
			age = input.nextInt();
			System.out.println("수정(변경)할 성별");
			gender = input.next();
			System.out.println("수정(변경)할 전화번호");
			num = input.next();
			System.out.println("수정(변경)할 주소");
			address = input.next();
			System.out.println("수정(변경)할 info");
			info = input.next();

			result = db.alterData(id, gender, name, age, num, address, info);

			if (result == 1) {
				System.out.println("정상적으로 수정 되었습니다");
			} else {
				System.out.println("(수정실패)");
			}
		} else {
			System.out.println("해당 아이디는 존재하지 않습니다");
		}
	}
}

