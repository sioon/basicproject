package sioon.service;



import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import sioon.service.LoginedCustomer;
import sioon.util.ScannerUtil;
import sioon.util.View;
import sioon.dao.JoinDAO;
import sioon.dao.NoteBoxDAO;
import sioon.vo.CustomerVO;

public class UserService {
	private UserService() {
	}

	private static UserService instance;

	public static UserService getInstance() {
		if (instance == null) {
			instance = new UserService();
		}
		return instance;
	}

	Scanner scanner = new Scanner(System.in);
	List<CustomerVO> customers = new ArrayList<>();
	private JoinDAO joinDAO = JoinDAO.getInstance();

	private NoteBoxDAO noteBoxDAO = NoteBoxDAO.getInstance();

	public int join() throws Exception {

		boolean idRun = true;
	      String customerId = null;
	      System.out.print("회원가입 ID(영어,숫자 사용 3~16글자)  > ");
	      // customerId = ScannerUtil.nextLine();

	      while (idRun) {
	         customerId = "^$";
	         String idre = "^[a-zA-Z0-9]{3,16}$";
	         Pattern re1 = Pattern.compile(idre);
	         Matcher re2 = re1.matcher(customerId);
	         while (re2.matches() == false) {
	            customerId = ScannerUtil.nextLine();
	            idre = "^[a-zA-Z0-9]{3,16}$";
	            re1 = Pattern.compile(idre);
	            re2 = re1.matcher(customerId);
	            if (re2.matches() == false) {
	               System.out.println("올바른 형식이 아닙니다.\t");
	               System.out.print("아이디를 재입력해주시오>");
	            } else if (noteBoxDAO.selectMemId(customerId)) {
	               System.out.println("중복된 아이디입니다.");
	               System.out.print("아이디를 재입력해주시오>");   
	            } else {
	               idRun = false;
	            }
	         }
	      }

	      boolean pwRun = true;
	      String customerPw = null;
	      String checkCustomerPw = null;
	         
	       System.out.println();   
	         System.out.println("비밀번호는 영문자+숫자+특수문자를 포함하여 8~20자리로 작성하여주세요.");
	         System.out.print("비밀번호를 입력하세요 > ");
	           customerPw = "";
	           String pwnc ="^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!%*#?&])[A-Za-z[0-9]$@$!%*#?&]{8,20}$";
	         Pattern pwnc1 = Pattern.compile(pwnc);
	         Matcher pwnc2 = pwnc1.matcher(customerPw);
	         
	         while(pwnc2.matches() == false) {
	            customerPw = ScannerUtil.nextLine();
	            pwnc ="^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!%*#?&])[A-Za-z[0-9]$@$!%*#?&]{8,20}$";
	            pwnc1 = Pattern.compile(pwnc);
	            pwnc2 = pwnc1.matcher(customerPw);
	            if(pwnc2.matches() == false) {
	               System.out.println("올바른 비밀번호 형식이 아닙니다.");   
	               System.out.println("다시 입력하여 주십시오.");
	            }
	         }
	            while(true) {
	               System.out.println("비밀번호를 다시 한번 더 입력해주세요 >");
	               String customerPwConform = ScannerUtil.nextLine();
	               if(!customerPw.equals(customerPwConform)) {
	                  System.out.println("올바른 비밀번호 형식이 아닙니다.");   
	                  System.out.println("다시 입력하여 주십시오.");
	               } else {
	                  break;
	               }

	      }
		System.out.print("이름  > ");
		String name = ScannerUtil.nextLine();

		int age = 0;
		System.out.print("나이  > ");
		while (true) {
			try {
				age = Integer.parseInt(ScannerUtil.nextLine());
				break;
			} catch (java.lang.NumberFormatException e) {
				System.out.print("숫자만 입력가능합니다. 다시입력해주세요. > ");
				continue;
			}
		}

		System.out.print("핸드폰 번호 (010-0000-0000)"); // 정규식
		String phone = " ";
		String phonenc = "^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$";
		Pattern phonenc1 = Pattern.compile(phonenc);
		Matcher phonenc2 = phonenc1.matcher(phone);
		while (phonenc2.matches() == false) {
			phone = ScannerUtil.nextLine();
			phonenc = "^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$";
			phonenc1 = Pattern.compile(phonenc);
			phonenc2 = phonenc1.matcher(phone);
			if (phonenc2.matches() == false) {
				System.out.println("전화번호 형식이 잘못되었습니다.");
				System.out.println("다시 입력하여 주십시오.");
			}
		}
		System.out.print("메일  > ");
		String mail = ScannerUtil.nextLine();

		System.out.print("주민등록번호 > "); // 정규식
		String regno = ScannerUtil.nextLine();

		System.out.print("성별 (여성/남성) > ");
		String jender = ScannerUtil.nextLine();

		System.out.print("결혼 여부 (초혼/재혼) > ");
		String marry = ScannerUtil.nextLine();

		System.out.print("우편번호 > ");
		int post = Integer.parseInt(ScannerUtil.nextLine());

		System.out.print("주소 > ");
		String add = ScannerUtil.nextLine();

		System.out.print("상세 주소 > ");
		String dao = ScannerUtil.nextLine();

		System.out.print("직업 > ");
		String job = ScannerUtil.nextLine();

		System.out.print("계정 찾기 힌트(별명) > ");
		String hint = ScannerUtil.nextLine();

		joinDAO.insertCustomer(new CustomerVO(customerId, customerPw, name, age, phone, mail, regno, jender, marry,
				post, add, dao, job, hint));

		return View.HOME;

	}

	public int login() throws Exception {
		// 싱글톤패턴
		LoginedCustomer loginedCustomer = LoginedCustomer.getInstance();
		String loginId = null;
		String loginPw = null;
		boolean idRun = true;
		CustomerVO foundCustomer = new CustomerVO(loginId, loginPw);
		while (idRun) {
			System.out.print("로그인 ID > ");
			loginId = scanner.nextLine();
			if (noteBoxDAO.getInstance().selectMemId(loginId)) {
				foundCustomer.setCustomerId(loginId);
				idRun = false;
			} else {
				System.out.println("로그인 ID가 틀렸습니다. 다시 입력해주세요.");
			}
		}

		boolean pwRun = true;
		while (pwRun) {
			System.out.print("로그인 PW > ");
			loginPw = scanner.nextLine();
			if (loginPw.equals(joinDAO.savePw(loginId))) {
				foundCustomer.setCustomerPw(loginPw);
				pwRun = false;
			} else {
				System.out.println("로그인 PW가 틀렸습니다. 다시 입력해주세요.");
			}
		}
		LoginedCustomer.getInstance().setLoginedCustomer(foundCustomer);

		List<CustomerVO> customers = joinDAO.selectCustomers(foundCustomer);

		if (customers.size() < 1) {
			System.out.println("ID 또는 PW를 확인해주세요.");
		} else {
			System.out.println(customers.get(0).getName() + "님 로그인 되었습니다.");
//				foundCustomer = loginedCustomer;
			// loginedCustomer 객체는 싱글톤 패턴으로 공유되고 있음
			loginedCustomer.setLoginedCustomer(foundCustomer);
		}
		return View.MAIN;

	}// end login()

	public int main() {
		System.out.println("============================================");
		System.out.println("| 1. 쪽지발송 | 2. 쪽지함 | 3. 공지사항 | 4. 로그아웃  |");
		System.out.println("============================================");
		System.out.print("번호 입력 > ");
		int mainMenu = Integer.parseInt(scanner.nextLine());
		switch (mainMenu) {
		case 1:
			return View.SENDNB;
		case 2:
			return View.NOTEBOX;
		case 3:
			return View.NOTICE;
		case 4:
			return View.LOGOUT;
		default:
			System.out.println("번호를 잘못 입력하셨습니다.");
			return View.MAIN;
		}
	}

	public int searchId() throws Exception {
	      JoinDAO joinDAO = JoinDAO.getInstance();

	      System.out.println("회원 정보를 입력해주세요.");
	      System.out.print("이름 > ");
	      String name = scanner.nextLine();
	      System.out.print("힌트 입력 > ");
	      String hint = scanner.nextLine();
	      
	      CustomerVO customer = joinDAO.getFoundIdAndPw(name, hint);
	      System.out.println("아이디 > " + customer.getCustomerId());
	      System.out.println("비밀번호 > " + customer.getCustomerPw());
	      return View.HOME;
	   }

	public int service() {
		System.out.println("고객센터 화면입니다.");
		System.out.println("=========================================");
		System.out.println("| 1. 서비스전화번호 | 2. QnA게시판 | 3. 홈으로 이동|");
		System.out.println("=========================================");
		System.out.print("번호 입력 > ");
		int homeMenu = Integer.parseInt(ScannerUtil.nextLine());
		switch (homeMenu) {
		case 1:
			return View.SERVICE_NUM;
		case 2:
			return View.SERVICE_QNA;
		case 3:
			return View.HOME;
		}
		return View.HOME;
	}

	public int serviceNum() {
		System.out.println("고객센터 서비스 전화번호입니다.");
		System.out.println("전화번호 > 010-8342-7973");
		System.out.println("-----------------------");
		System.out.println("0.나가기");
		int noteNum = Integer.parseInt(ScannerUtil.nextLine());
		switch (noteNum) {
		case 0:
			return View.SERVICE;

		default:
			return View.SERVICE;

		}
	}

	public int serviceQna() {
		System.out.println("------------------------------------------");
		System.out.println("1.아이디를 모르겠어요!");
		System.out.println("2.비밀번호가 기억이 안납니다!");
		System.out.println("3.쪽지는 어떻게 보내나요?");
		System.out.println("4.결제 방법은 어떻게 되나요?");
		System.out.println("5.고객센터 전화번호를 알고싶습니다.");
		System.out.println("0.나가기");
		System.out.println("------------------------------------------");
		System.out.println("입력>");
		int sc = ScannerUtil.nextint();
		switch (sc) {
		case 1:
			System.out.println("------------------------------------------");
			System.out.println("제목: 아이디를 모르겠어요!");
			System.out.println("답변: ID/PW란에가서 아이디를 찾으시면됩니다.");
			System.out.println("0.나가기");
			System.out.println("------------------------------------------");
			System.out.println("입력>");
			String sc1 = ScannerUtil.nextLine();
			return View.SERVICE_QNA;
		case 2:
			System.out.println("------------------------------------------");
			System.out.println("제목: 비밀번호가 기억이 안납니다.");
			System.out.println("답변: ID/PW란에가서 아이디를 찾으시면 됩니다.");
			System.out.println("------------------------------------------");
			System.out.println("나가시려면 아무키나 눌러주세요!");
			sc1 = ScannerUtil.nextLine();
			return View.SERVICE_QNA;
		case 3:
			System.out.println("------------------------------------------");
			System.out.println("제목: 쪽지는 어떻게 보내나요?");
			System.out.println("답변: 로그인 후 > 쪽지함 > 쪽지 보내기에 들어가시면 됩니다.");
			System.out.println("------------------------------------------");
			System.out.println("나가시려면 아무키나 눌러주세요!");
			sc1 = ScannerUtil.nextLine();
			return View.SERVICE_QNA;
		case 0:
			return View.SERVICE;

		}
		return View.SERVICE;
	}

}
