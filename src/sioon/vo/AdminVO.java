package sioon.vo;


public class AdminVO {

	private int adminAuthority;

	public AdminVO() {
	}

	public AdminVO(int adminAuthority) {
		this.adminAuthority = adminAuthority;
	}

	public int getAdminAuthority() {
		return adminAuthority;
	}

	public void setAdminAuthority(int adminAuthority) {
		this.adminAuthority = adminAuthority;
	}

	@Override
	public String toString() {
		return "adminDAO [adminAuthority=" + adminAuthority + "]";
	}
}
