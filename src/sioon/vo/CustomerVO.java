package sioon.vo;



import java.time.LocalDateTime;
import java.util.Objects;

public class CustomerVO{
	private String customerId;
	private String customerPw;
	private String name;
	private int age;
	private String phone;
	private String mail;
	private String regno;
	private String jnder;
	private String marry;
	private int post;
	private String address;
	private String accurateaddress;
	private String job;
	private int ntqty;
	private String hint;
	private LocalDateTime informationDate;

	public CustomerVO() {
	}

	public CustomerVO(String customerId, String customerPw) {
		this.customerId = customerId;
		this.customerPw = customerPw;
	}

	public CustomerVO(String customerId, String customerPw, String name, int age, String phone, String mail,
			String regno, String jnder, String marry, int post, String address, String accurateaddress, String job,
			String hint) {
		this.customerId = customerId;
		this.customerPw = customerPw;
		this.name = name;
		this.age = age;
		this.phone = phone;
		this.mail = mail;
		this.regno = regno;
		this.jnder = jnder;
		this.marry = marry;
		this.post = post;
		this.address = address;
		this.accurateaddress = accurateaddress;
		this.job = job;
		this.hint = hint;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCustomerPw() {
		return customerPw;
	}

	public void setCustomerPw(String customerPw) {
		this.customerPw = customerPw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getRegno() {
		return regno;
	}

	public void setRegno(String regno) {
		this.regno = regno;
	}

	public String getJnder() {
		return jnder;
	}

	public void setJnder(String jnder) {
		this.jnder = jnder;
	}

	public String getMarry() {
		return marry;
	}

	public void setMarry(String marry) {
		this.marry = marry;
	}

	public int getPost() {
		return post;
	}

	public void setPost(int post) {
		this.post = post;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAccurateaddress() {
		return accurateaddress;
	}

	public void setAccurateaddress(String accurateaddress) {
		this.accurateaddress = accurateaddress;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public int getNtqty() {
		return ntqty;
	}

	public void setNtqty(int ntqty) {
		this.ntqty = ntqty;
	}

	public String getHint() {
		return hint;
	}

	public void setHint(String hint) {
		this.hint = hint;
	}

	public LocalDateTime getInformationDate() {
		return informationDate;
	}

	public void setInformationDate(LocalDateTime informationDate) {
		this.informationDate = informationDate;
	}

	@Override
	public int hashCode() {
		return Objects.hash(accurateaddress, address, age, customerId, customerPw, hint, informationDate, jnder, job,
				mail, marry, name, ntqty, phone, post, regno);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CustomerVO other = (CustomerVO) obj;
		return Objects.equals(accurateaddress, other.accurateaddress) && Objects.equals(address, other.address)
				&& age == other.age && Objects.equals(customerId, other.customerId)
				&& Objects.equals(customerPw, other.customerPw) && Objects.equals(hint, other.hint)
				&& Objects.equals(informationDate, other.informationDate) && Objects.equals(jnder, other.jnder)
				&& Objects.equals(job, other.job) && Objects.equals(mail, other.mail)
				&& Objects.equals(marry, other.marry) && Objects.equals(name, other.name) && ntqty == other.ntqty
				&& Objects.equals(phone, other.phone) && post == other.post && Objects.equals(regno, other.regno);
	}

	@Override
	public String toString() {
		return "CustomerVO [customerId=" + customerId + ", customerPw=" + customerPw + ", name=" + name + ", age=" + age
				+ ", phone=" + phone + ", mail=" + mail + ", regno=" + regno + ", jnder=" + jnder + ", marry=" + marry
				+ ", post=" + post + ", address=" + address + ", accurateaddress=" + accurateaddress + ", job=" + job
				+ ", ntqty=" + ntqty + ", hint=" + hint + ", informationDate=" + informationDate + "]";
	}
}