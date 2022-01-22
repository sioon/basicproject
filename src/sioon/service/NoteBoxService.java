package sioon.service;



import java.util.ArrayList;
import java.util.List;

import sioon.util.ScannerUtil;
import sioon.util.View;
import sioon.dao.NoteBoxDAO;
import sioon.vo.NoteBoxVO;

public class NoteBoxService {
	private NoteBoxService() {

	}

	private static NoteBoxService instance;

	public static NoteBoxService getInstance() {
		if (instance == null) {
			instance = new NoteBoxService();
		}
		return instance;
	}

	List<NoteBoxVO> notes = new ArrayList<>();
	private NoteBoxDAO noteBoxDAO = NoteBoxDAO.getInstance();

	public int send() throws Exception {

		System.out.println("제목 > ");
		String nBTitle = ScannerUtil.nextLine();
		System.out.println("내용 > ");
		String nBBody = ScannerUtil.nextLine();

		System.out.println("받는 ID > ");
		String nBMember = ScannerUtil.nextLine();
		if (noteBoxDAO.selectMemId(nBMember)) {
			noteBoxDAO.insertNoteBox(new NoteBoxVO(nBTitle, nBBody, nBMember));
		} else {
			System.out.println("올바르지 않은 아이디입니다.");
		}
		return View.MAIN;
	}

	public int viewNoteBox() {

		System.out.println("=================================");
		System.out.println("1. 받은 쪽지 | 2. 보낸 쪽지 | 3. 홈으로 이동");
		System.out.println("=================================");
		System.out.print("번호 입력 >");
		int noteBoxNum = Integer.parseInt(ScannerUtil.nextLine());

		switch (noteBoxNum) {
		case 1:
			return View.RECIVENOTEBOX;
		case 2:
			return View.SENDNOTEBOX;
		case 3:
			return View.MAIN;
		default:
			System.out.println("번호를 잘못 입력하셨습니다.");
			return View.NOTEBOX;
		}
	}

	public int viewSendNoteBox() throws Exception {
		System.out.println("=========================================");
		List<NoteBoxVO> notes = NoteBoxDAO.getInstance()
				.selectSendNoteBox(LoginedCustomer.getInstance().getLoginedCustomer().getCustomerId());
		System.out.println("쪽지 번호 |     제목          |       내용");
		for (NoteBoxVO note : notes) {
			System.out.println(note.getnBCode() + "    |      "  + note.getnBTitle() + "         |       " + note.getnBBody());
		}
		System.out.println("=========================================");

		return View.NOTEBOX;
	}

	public int viewReceiveNoteBox() throws Exception {
		System.out.println("=========================================");
		List<NoteBoxVO> notes = NoteBoxDAO.getInstance()
				.selectReceiveNoteBox(LoginedCustomer.getInstance().getLoginedCustomer().getCustomerId());
		System.out.println("쪽지 번호 |     제목          |       내용");
		for (NoteBoxVO note : notes) {
			System.out.println(note.getnBCode() + "    |      " + note.getnBTitle() + "         |       " + note.getnBBody());
		}
		System.out.println("=========================================");
		return View.NOTEBOX;
	}
}