package Mini_Project_1;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class PhoneBook {
	private static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {
		while (true) {
			System.out.println();
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
		PhoneBookDAO dao = new PhoneBookDAOImpl();
		List<PhoneBookVO> list = dao.getName();
		for (PhoneBookVO phonebook : list) {
			if (phonebook != null) {
				System.out.print(phonebook.getId() + ".");
				System.out.print(phonebook.getName() + " ");
				System.out.print(phonebook.getHp() + " ");
				System.out.println(phonebook.getTel() + " ");
				System.out.println();
			} else {
				System.out.println("Not Found");
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

		PhoneBookVO vo = new PhoneBookVO(null, name, hp, tel);
		PhoneBookDAO dao = new PhoneBookDAOImpl();
		boolean success = dao.insert(vo);
		System.out.println();
		System.out.println("[등록되었습니다.]");

	}

	private static void DeletePhoneNum() {
		System.out.println("<3. 삭제>");
		System.out.print(">번호: ");
		int Num = scan.nextInt();

		PhoneBookDAO dao = new PhoneBookDAOImpl();
		System.out.println();
		System.out.println("[삭제되었습니다.]");
	}

	private static void SearchList() {
		System.out.println("<4.검색>");
		System.out.print(">이름: ");
		String search = scan.next();
		
		PhoneBookDAO dao = new PhoneBookDAOImpl();
		List<PhoneBookVO> list = dao.search(search);
		
		Iterator<PhoneBookVO> it = list.iterator();
		
		while (it.hasNext()) {
			PhoneBookVO vo = it.next();
			System.out.printf("%d\t%s\t%s\t%s%n",
					vo.getId(),
					vo.getName(),
					vo.getHp(),
					vo.getTel());
		}

	}
		
	private static void selectAll() {
		PhoneBookDAO dao = new PhoneBookDAOImpl();
		List<PhoneBookVO> list = dao.getName();
		
		//	Iterator
		Iterator<PhoneBookVO> it = list.iterator();
		
		System.out.println("========== Author List");
		
		while(it.hasNext()) {
			PhoneBookVO item = it.next();	//	iterator에서 요소 추출
			System.out.printf("%d\t%s\t%s\t%s%n", 
					item.getId(), 
					item.getName(), 
					item.getHp(),
					item.getTel());
		}
	}

}
