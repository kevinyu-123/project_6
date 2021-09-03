package main;

import java.util.ArrayList;
import java.util.Scanner;
import han.addMembership;
import dto.MemDTO;
import hj.Db;
import ys.DBClass;
import eun.EunDB;

public class mainClass {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		ArrayList<MemDTO> list = new ArrayList<MemDTO>();
		String id;
		int num;
		Db hj = new Db();
		addMembership add = new addMembership();
		DBClass db = new DBClass();
		Scanner input = new Scanner(System.in);
		int result = 0;
		EunDB dbee = new EunDB();
		while (true) {
			System.out.println("1.추가 2.전체보기 3.검색 4.수정 5.삭제 6.공지 및 공지 보기");
			num = input.nextInt();
			switch (num) {
			case 1:
				// 회원 add 기능 추가
				add.display();
				break;
			case 2:
				// 회원 전체보기 기능 추가
				System.out.println("전체 회원을 보여줍니다.");
				list = hj.findAll();
				hj.show(list);
			case 3:
				break;
			case 4:
				dbee.update();
				break;
			case 5:
				// 회원 삭제기능 추가
				System.out.println("=== 삭제하실 아이디를 입력 해 주세요 ===");
				id = scan.next();
				result = db.deleteDB(id);
				if (result == 1) {
					System.out.println("정상적으로 삭제되었습니다.");
				} else {
					System.out.println("존재하는 아이디가 없어서 삭제가 불가능 합니다.");
				}
				break;
			case 6:
				break;
			}
		}
	}
}