package sioon.util;


public interface View {
	int HOME = 0; //메인화면
	int JOIN = 1; //회원가입
	int LOGIN = 2; //로그인
	int SEARCH= 4; //아이디,비밀번호 찾기
	int SERVICE = 9; //고객센터
	int SERVICE_NUM = 8; //고객센터 전화번호
	int SERVICE_QNA = 7; //고객센터 QNA
	
	int MAIN = 11; //메인화면
	int SENDNB = 12; //쪽지보내기
	int NOTEBOX = 13; //쪽지함
	int SENDNOTEBOX = 21; // 보낸쪽지함
	int RECIVENOTEBOX = 20; // 받은쪽지함
	int PAY = 14; //결제
	int NOTICE = 15; //공지사항
	int LOGOUT = 16; //로그아웃
	int NOTICE_BOARD = 17;	//공지사항 게시판
	int NOTICE_BOARDLIST = 19; //공지 게시글 보기
	
	
	int ADMIN_NOTICE_BOARD = 30;	//공지 게시글 보기
	int ADMIN_NOTICE_BOARDLIST = 31; //공지사항 게시판
	int ADMIN_NOTICE_INSERT =32; // 공지사항 추가
	int ADMIN_NOTICE_UPDATE=33; //공지사항 수정
	int ADMIN_NOTICE_DELETE=34;

	
	int EXIT = 99;
}