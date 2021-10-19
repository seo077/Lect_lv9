import controller.StudentManager;

public class Main {
public static void main(String[] args) {
	
	StudentManager.getInstance().run();
	//학생관리 프로그램
	
	//조상 클래스 :School(abstract) : [변수]과이름, 과인원 --> 학과들에 상속
	//부전공 마크인터페이스 생성 - > 부전공 가능 객체만 상속
	
	//Student클래스 : 이름,학번,전공,부전공,수강중인 과목,성적
	//Subject 객체
	
	//StudentManager 클래스
	//:기능
	//ㄴ학생 추가
	//ㄴ학생 삭제
	//ㄴ정렬
	//ㄴ수정
	//ㄴ학생 정보 조회
}
}
