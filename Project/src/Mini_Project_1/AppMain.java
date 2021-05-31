package Mini_Project_1;

import java.util.Scanner;

public class AppMain {
	private static Scanner scan = new Scanner(System.in);
	private static PhoneNum[] PhoneArray = new PhoneNum[100];

	public static void main(String[] args) {
		while (true) {
			System.err.println();
			System.out.println("*********************************");
			System.out.println("*\t전화번호 관리 프로그램\t*");
			System.out.println("*********************************");
			System.out.println("1.리스트 2.등록 3.삭제 4.검색 5.종료");
			System.out.println("---------------------------------");
			System.out.print(">메뉴번호: ");
			int SelectNum = scan.nextInt();
			System.out.println();
			
			if (SelectNum == 1) {
				PersonalInfoList();
			} else if (SelectNum == 2) {
				CreatePhoneNum();
			} else if (SelectNum == 3) {
				DeletePhoneNum();
			} else if (SelectNum == 4) {
				SearchList();
			} else if (SelectNum == 5) {
				System.out.println("*************************");
				System.out.println("*\t 감사합니다\t*");
				System.out.println("*************************");
				break;
			} else {
				System.out.println("[다시입력해주세요]");
				continue;
			}
		}

	}
	
	private static void PersonalInfoList() {
		System.out.println("<1.리스트>");
		for (int i = 0; i < PhoneArray.length; i++) {
			PhoneNum List = PhoneArray[i];
			if (List != null) {
				System.out.println(List.getName());
				System.out.println(List.getHp());
				System.out.println(List.getTel());
				System.out.println();
			}
		}
		
	}
	
	private static void CreatePhoneNum() {
		System.out.println("<2. 등록>");
		
		System.out.print(">이름: ");
		String name = scan.next();
		
		System.out.print(">휴대전화: ");
		String hp = scan.next();
		
		System.out.print(">집전화: ");
		String tel = scan.next();
		
		System.out.println();
		
		PhoneNum CreateInfo = new PhoneNum(name, hp, tel);
		for (int i = 0; i < PhoneArray.length; i++) {
			if (PhoneArray[i] == null)
				PhoneArray[i] = CreateInfo;
			System.out.println("[등록되었습니다.]");
			break;
		}
	}
	
	private static void DeletePhoneNum() {
		System.out.println("<3. 삭제>");
		System.out.println(">번호: ");
		int SelectNum = scan.nextInt();
		PhoneNum SearchInfo = findList(name);
		if (SearchInfo != null)
			System.out.println("[삭제되었습니다.]");
	}
	
	private static void SearchList() {
		System.out.println("<4.검색>");
		System.out.println(">이름: ");
		String search = scan.next();
		PhoneNum SearchInfo = findList(name);
		if (SearchInfo != null)
			System.out.println(SearchInfo.getName());
			System.out.println(SearchInfo.getHp());
			System.out.println(SearchInfo.getTel());
			System.out.println();
		
	}
	
	private static PhoneNum findList(String name) {
		PhoneNum SearchInfo = null;
		for (int i = 0; i < PhoneArray.length; i++) {
			String dbname = PhoneArray[i].getName();
			if (dbname.equals(name)) {
				SearchInfo = PhoneArray[i];
				break;
			}
		}
		return SearchInfo;
	}
}
