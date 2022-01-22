package sioon.controller;



import java.util.Scanner;

import sioon.util.ScannerUtil;
import sioon.util.View;
import sioon.service.AdminService;
import sioon.service.MainService;
import sioon.service.NoteBoxService;
import sioon.service.UserService;

public class Controller {
	public static void main(String[] args) throws Exception {

		System.out.println("결혼정보회사 \"오작교\" 에 오신걸 환영합니다 !");
		new Controller().start();
	}

	public void start() throws Exception {
		NoteBoxService noteBoxService = NoteBoxService.getInstance();
		UserService userService = UserService.getInstance();
		MainService mainService = MainService.getInstance();
		int view = View.HOME;

		while (true) {
			switch (view) {
			case View.HOME:
				view = home();
				break;
			case View.JOIN:
				view = userService.join();
				break;
			case View.LOGIN:
				view = userService.login();
				break;
			case View.MAIN:
				view = userService.main();
				break;
			case View.SEARCH:
				view = userService.searchId();
				break;
			case View.SERVICE:
				view = userService.service();
				break;
			case View.SERVICE_NUM:
				view = userService.serviceNum();
				break;
			case View.SERVICE_QNA:
				view = userService.serviceQna();
				break;
			case View.NOTICE:
				view = MainService.notice();
				break;
			case View.NOTICE_BOARD: // 공지 상세
				view = MainService.notiBoardDetail(0);
				break;
			case View.ADMIN_NOTICE_BOARD:
				view = AdminService.aminNotiBoardDetail(0);
				break;
			case View.NOTICE_BOARDLIST:
				view = MainService.notiBoard();
				break;
			case View.ADMIN_NOTICE_BOARDLIST: // 공지 목록
				view = AdminService.adminNotiBoard();
				break;
			case View.ADMIN_NOTICE_INSERT: // 공지 추가
				view = AdminService.adminNoticeInsert();
				break;
			case View.ADMIN_NOTICE_UPDATE: // 공지 수정
				view = AdminService.adminNotiUpdate();
				break;
			case View.ADMIN_NOTICE_DELETE: // 공지 삭제
				view = AdminService.adminNotiDelete();
				break;
			case View.SENDNB:
				view = noteBoxService.send();
				break;
			case View.NOTEBOX: // 쪽지함
				view = noteBoxService.viewNoteBox();
				break;
			case View.SENDNOTEBOX:
				view = noteBoxService.viewSendNoteBox();
				break;
			case View.RECIVENOTEBOX:
				view = noteBoxService.viewReceiveNoteBox();
				break;
			case View.LOGOUT:
				view = mainService.logout();
				break;
			case View.EXIT:
				System.out.println("저희 오작교를 이용해주셔서 감사합니다 !");
				System.out.println("좋은 하루 보내시길 바랍니다.");
				return;

			}
		}
	}

	private int home() throws Exception {

		Scanner scanner = new Scanner(System.in);

		System.out.println("===================♥♡◟( ˘ ³˘)◞ ♡ ◟(˘◡˘∗)◞♡♥===============♥♥♥♥==");
		System.out.println("| 1. 회원가입 | 2. 로그인 | 3. ID, PW 찾기 | 9. 고객센터 | 0. 프로그램 종료|");
		System.out.println("=======================================================♥♥♥♥==");
		System.out.print("번호입력 > ");
		int homeMenu = Integer.parseInt(ScannerUtil.nextLine());

		switch (homeMenu) {
		case 1:
			return View.JOIN;
		case 2:
			return View.LOGIN;
		case 3:
			return View.SEARCH;
		case 9:
			return View.SERVICE;
		case 0:
			return View.EXIT;
		default:
			System.out.println("번호를 잘못 입력했습니다. 다시 입력해주세요.");
		}
		return View.HOME;

	}
}