package sioon.service;

import java.util.List;

import sioon.service.LoginedCustomer;
import sioon.util.ScannerUtil;
import sioon.util.View;
import sioon.dao.AdminDAO;
import sioon.dao.NoticeDAO;
import sioon.vo.NoticeVO;

public class MainService {
	private static MainService instance = new MainService();

	public static MainService getInstance() {
		if (instance == null) {
			instance = new MainService();
		}
		return instance;
	}

	private MainService() {
	}

	private UserService us = UserService.getInstance();
	private AdminDAO ad = AdminDAO.getInstance();
	

	public static int notice() throws Exception {
		AdminDAO admin = AdminDAO.getInstance();
		System.out.println("오작교 공지사항 입니다.");
		System.out.println("=============================");
		System.out.println("| 1. 공지사항 게시판 | 2. 홈으로 이동 |");
		System.out.println("=============================");
		System.out.print("번호 입력 > ");
		int serviceNum = Integer.parseInt(ScannerUtil.nextLine());
		switch (serviceNum) {
		case 1:
			int no = admin.adminAuthority();
			if (no == 0) {
				// 일반회원 화면
				return View.NOTICE_BOARDLIST;
			} else {
				// 관리자 화면
				return View.ADMIN_NOTICE_BOARDLIST;
			}

		case 2:
			// 홈으로 이동
			return View.MAIN;
		}
		return 0;

	}

	public static int notiBoard() throws Exception {
		NoticeDAO nd = NoticeDAO.getinstance();
		List<NoticeVO> list = nd.selectAll();
		System.out.println("==============================================");
		System.out.println("|번호   |       제목               |   작성자      |   작성일       |");
		for (NoticeVO vo : list) {
			System.out.printf("%d .   %s \t \t%s \t   %s |\n", vo.getNtNo(), vo.getNtTitle(), vo.getNtMember(),
					vo.getNtDate());
		}
		System.out.println("==============================================");
		System.out.println("|                                |    0.나가기  | ");
		System.out.println("==============================================");
		System.out.println("번호 입력>");
		int noteNum = ScannerUtil.nextint();
		switch (noteNum) {
		case 0:
			return View.MAIN;
		default:
			// noteNum : 사용자가 선택한 번호
			notiBoardDetail(noteNum);
//			return View.NOTICE_BOARD;
		}
		return View.NOTICE_BOARDLIST;
	}

	public static int notiBoardDetail(int noteNum) throws Exception {
		NoticeDAO nd = NoticeDAO.getinstance();
		NoticeVO noticeVO = nd.notiBoardDetail(noteNum);
		System.out.println("==============================================");
		System.out.println("제목:" + noticeVO.getNtTitle());
		System.out.println("작성자:" + noticeVO.getNtMember());
		System.out.println("내용: " + noticeVO.getNtCont());
		System.out.println("작성일 : " + noticeVO.getNtDate());
		System.out.println("==============================================");
		System.out.println(" 0.나가기");
		int no = ScannerUtil.nextint();
		switch (no) {
		case 0:
			return View.NOTICE_BOARDLIST;
		default:
			System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
			return View.NOTICE_BOARD;
		}
	}

	public int logout() {
		LoginedCustomer.getInstance().setLoginedCustomer(null);
		System.out.println("로그아웃 되었습니다.");
		return View.HOME;
	}
}