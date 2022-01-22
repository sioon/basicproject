package sioon.service;

import java.util.List;

import sioon.util.ScannerUtil;
import sioon.util.View;
import sioon.dao.NoticeDAO;
import sioon.vo.NoticeVO;

public class AdminService {
	private AdminService() {
	}

	private static AdminService instance;

	public static AdminService getInstance() {
		if (instance == null) {
			instance = new AdminService();
		}
		return instance;
	}

	UserService userService = UserService.getInstance();
	MainService mainService = MainService.getInstance();

	public static int adminNotiBoard() throws Exception {
		NoticeDAO nd = NoticeDAO.getinstance();
		List<NoticeVO> list = nd.selectAll();
		System.out.println("==============================================");
		System.out.println("|번호 |       제목              |   작성자       |    작성일        |");
		for (NoticeVO vo : list) {
			System.out.printf("%d .   %s \t \t%s \t   %s |\n", vo.getNtNo(), vo.getNtTitle(), vo.getNtMember(),
					vo.getNtDate());
		}
		System.out.println("==============================================");
		System.out.println("| 101.추가  | 102.수정  | 103.삭제    |    0.나가기        | ");
		System.out.println("==============================================");
		System.out.println("번호 입력>");
		int noteNum = ScannerUtil.nextint();
		switch (noteNum) {
		case 101:
			return View.ADMIN_NOTICE_INSERT;
		case 102:
			return View.ADMIN_NOTICE_UPDATE;
		case 103:
			return View.ADMIN_NOTICE_DELETE;
		case 0:
			return View.MAIN;
		default:
			// noteNum : 사용자가 선택한 번호
			AdminService.aminNotiBoardDetail(noteNum);
			return View.ADMIN_NOTICE_BOARDLIST;
//			return View.NOTICE_BOARD;
		}
//		return View.NOTICE_BOARDLIST;
	}

	public static int aminNotiBoardDetail(int noteNum) throws Exception {
		NoticeDAO nd = NoticeDAO.getinstance();
		List<NoticeVO> list = nd.selectNo(noteNum);
		for (NoticeVO noticeVO : list) {
			if (noteNum == noticeVO.getNtNo()) {
				System.out.println("==============================================");
				System.out.println(" 제목:" + noticeVO.getNtTitle());
				System.out.println(" 작성자:" + noticeVO.getNtMember());
				System.out.println(" 내용: " + noticeVO.getNtCont());
				System.out.println(" 작성일 : " + noticeVO.getNtDate());
				System.out.println("==============================================");
				System.out.println(" 0.나가기");
			}
			int no = ScannerUtil.nextint();
			switch (no) {
			case 0:
				return View.ADMIN_NOTICE_BOARDLIST;
			default:
				System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
				return View.ADMIN_NOTICE_BOARD;
			}
		}
		System.out.println("잘못된 번호입니다. 다시 입력해주세요.");
		return noteNum;
	}

	public static int adminNoticeInsert() throws Exception {

		NoticeDAO nd = NoticeDAO.getinstance();
		System.out.println("공지사항 제목을 입력해주십시오:");
		String ntTitle = ScannerUtil.nextLine();
		System.out.println("내용을 입력해주세요: ");
		String ntcont = ScannerUtil.nextLine();
		System.out.println("공지사항이 추가되었습니다.");
		nd.insertNotice(new NoticeVO(ntTitle, ntcont));
		return View.ADMIN_NOTICE_BOARDLIST;
	}

	public static int adminNotiUpdate() throws Exception {
		NoticeDAO nd = NoticeDAO.getinstance();
		System.out.println("=======================");
		System.out.println(" 수정할 공지번호를 입력해주세요 : ");
		int ntnum = ScannerUtil.nextint();
		int count = nd.notiSelectNum(ntnum);
		if (count == 1) {
			System.out.println("수정할 제목을 입력해주세요 : ");
			String title = ScannerUtil.nextLine();
			System.out.println("수정할 내용을 입력해주세요 :");
			String cont = ScannerUtil.nextLine();
			NoticeVO vo = new NoticeVO();
			vo.setNtNo(ntnum);
			vo.setNtTitle(title);
			vo.setNtCont(cont);
			nd.updateNotice(vo);
			System.out.println("수정이 완료되었습니다!");
			return View.ADMIN_NOTICE_BOARDLIST;
		} else {
			System.out.println("없는 공지 번호입니다.");
			return View.ADMIN_NOTICE_BOARDLIST;
		}
	}

	public static int adminNotiDelete() throws Exception {
		// boolean ntrun = true;
		NoticeDAO nd = NoticeDAO.getinstance();
		System.out.println("=======================");
		System.out.println(" 삭제할 공지 번호를 입력해주세요: ");
		int ntnum = ScannerUtil.nextint();
		int count = nd.notiSelectNum(ntnum);

		if (count == 1) {

			System.out.println("정말로 삭제하시겠습니까?");
			System.out.println("| 1.네  | 2.아니요.|");
			int yes = ScannerUtil.nextint();

			if (yes == 1) {
				NoticeDAO.getinstance().notiDelete(ntnum);
				System.out.println("삭제 되었습니다.");
				System.out.println("공지게시판으로 돌아갑니다.");
				return View.ADMIN_NOTICE_BOARDLIST;

			} else if (yes == 2) {
				System.out.println("공지사항 게시판으로 돌아갑니다.");
				return View.ADMIN_NOTICE_BOARDLIST;
			} else {
				System.out.println("잘못된 입력입니다.");
				System.out.println("공지게시판으로 돌아갑니다.");
				return View.ADMIN_NOTICE_BOARDLIST;
			}
		} else {
			System.out.println("없는 공지번호 입니다.");
		}
		return View.ADMIN_NOTICE_BOARDLIST;
	}
}
