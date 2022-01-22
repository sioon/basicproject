package sioon.service;



import sioon.vo.CustomerVO;

public class LoginedCustomer {
	//�̱�������
	private static LoginedCustomer instance = new LoginedCustomer();
	public static LoginedCustomer getInstance() {
		return instance;
	}
	private LoginedCustomer() {}
	
	private CustomerVO loginedCustomer;
	
	public CustomerVO getLoginedCustomer() {
		return loginedCustomer;
	}
	public void setLoginedCustomer(CustomerVO loginedCustomer) {
		this.loginedCustomer = loginedCustomer;
	}
	@Override
	public String toString() {
		return "LoginedCustomer [loginedCustomer=" + loginedCustomer + "]";
	}
	
	
}
