package sioon.vo;



import java.time.LocalDateTime;
import java.util.Objects;

public class NoticeVO {
	
	private int ntNo;
	private String ntTitle;
	private String ntCont;
	private String ntMember;
	private String ntDate;
	
	
	public NoticeVO() {
	}
	
	public NoticeVO(int ntNo) {
		this.ntNo = ntNo;
	}

	public NoticeVO(int ntNo, String ntTitle, String ntMember, String ntCont, String ntDate) {
		this.ntNo = ntNo;
		this.ntTitle = ntTitle;
		this.ntMember = ntMember;
		this.ntCont = ntCont;
		this.ntDate = ntDate;
	}

	public NoticeVO(String ntTitle, String ntcont) {
		this.ntTitle = ntTitle;
		this.ntCont = ntCont;
	}

	public int getNtNo() {
		return ntNo;
	}
	public void setNtNo(int ntNo) {
		this.ntNo = ntNo;
	}
	public String getNtMember() {
		return ntMember;
	}
	
	public void setNtMember(String ntMember) {
		this.ntMember = ntMember;
	}
	public String getNtTitle() {
		return ntTitle;
	}
	public void setNtTitle(String ntTitle) {
		this.ntTitle = ntTitle;
	}
	public String getNtCont() {
		return ntCont;
	}
	public void setNtCont(String ntCont) {
		this.ntCont = ntCont;
	}
	public String getNtDate() {
		return ntDate;
	}
	public void setNtDate(String ntDate) {
		this.ntDate = ntDate;
	}

	@Override
	public int hashCode() {
		return Objects.hash(ntCont, ntDate, ntNo, ntTitle);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NoticeVO other = (NoticeVO) obj;
		return Objects.equals(ntCont, other.ntCont) && Objects.equals(ntDate, other.ntDate) && ntNo == other.ntNo
				&& Objects.equals(ntTitle, other.ntTitle);
	}

	@Override
	public String toString() {
		return "NoticeVO [ntNo=" + ntNo + ", ntTitle=" + ntTitle + ", ntCont=" + ntCont + ", ntDate=" + ntDate + "]";
	}
}
