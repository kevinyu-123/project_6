package han;

import java.util.Scanner;
import dto.MemDTO;

public class addMembership { //회원 추가 메소드
	private Scanner sc = new Scanner(System.in);
	private addMembershipDB db = new addMembershipDB();
	private MemDTO dto = new MemDTO();
	private int check;
	public void display() {
		System.out.println("회원 추가 기능입니다.");
		System.out.println("가입할 ID를 입력해 주세요");
		dto.setId(sc.next()); 
		sc.nextLine();
		check = db.membershipIdCheckDB(dto.getId());
		if(check == 0) {
			System.out.println("이름을 입력해 주세요");
			dto.setName(sc.next());
			sc.nextLine();
			System.out.println("나이를 입력해 주세요");
			dto.setAge(sc.nextInt());
			sc.nextLine();
			System.out.println("성별를 입력해 주세요");
			dto.setGender(sc.next());
			sc.nextLine();
			System.out.println("전화번호를 입력해 주세요");
			dto.setNum(sc.next());
			sc.nextLine();
			System.out.println("주소를 입력해 주세요");
			dto.setAddress(sc.nextLine());
			System.out.println("추가사항을 입력해 주세요");
			dto.setInfo(sc.nextLine());
			check = db.addMembershipDB(dto.getId(),dto.getName(),dto.getAge(),dto.getGender(),
					dto.getNum(),dto.getAddress(),dto.getInfo());
			if(check ==1) {
				System.out.println("회원 추가 완료");
			}else System.out.println("회원 추가 실패");
		}else System.out.println("이미 존재하는 ID입니다."); 
	}
}
