package Mini_Project_1;

import java.util.List;

//	설계도
public interface PhoneBookDAO {
	public List<PhoneBookVO> getName();		//	단순 SELECT
	public PhoneBookVO get(Long id);	//	PK로 가져오기
	public boolean insert(PhoneBookVO vo);	//	INSERT
	public boolean delete(Long id);		//	PK로 삭제
	public List<PhoneBookVO> search(String keyword);	//	LIKE 검색

}
