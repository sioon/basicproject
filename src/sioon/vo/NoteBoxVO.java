package sioon.vo;


import java.time.LocalDateTime;
import java.util.Objects;

public class NoteBoxVO {
	private String nBCode;
	private String nBTitle;
	private String nBBody;
	private LocalDateTime nBDate;
	private String nBMember;
	private String nBSender;

	public NoteBoxVO() {
	}

	public NoteBoxVO(String nBTitle, String nBBody, String nBMember) {
		this.nBTitle = nBTitle;
		this.nBBody = nBBody;
		this.nBMember = nBMember;
	}

	public NoteBoxVO(String nBCode, String nBTitle, String nBBody, LocalDateTime nBDate, String nBMember,
			String nBSender) {
		this.nBCode = nBCode;
		this.nBTitle = nBTitle;
		this.nBBody = nBBody;
		this.nBDate = nBDate;
		this.nBMember = nBMember;
		this.nBSender = nBSender;
	}

	public String getnBCode() {
		return nBCode;
	}

	public void setnBCode(String nBCode) {
		this.nBCode = nBCode;
	}

	public String getnBTitle() {
		return nBTitle;
	}

	public void setnBTitle(String nBTitle) {
		this.nBTitle = nBTitle;
	}

	public String getnBBody() {
		return nBBody;
	}

	public void setnBBody(String nBBody) {
		this.nBBody = nBBody;
	}

	public LocalDateTime getnBDate() {
		return nBDate;
	}

	public void setnBDate(LocalDateTime nBDate) {
		this.nBDate = nBDate;
	}

	public String getnBMember() {
		return nBMember;
	}

	public void setnBMember(String nBMember) {
		this.nBMember = nBMember;
	}

	public String getnBSender() {
		return nBSender;
	}

	public void setnBSender(String nBSender) {
		this.nBSender = nBSender;
	}

	@Override
	public int hashCode() {
		return Objects.hash(nBBody, nBCode, nBDate, nBMember, nBSender, nBTitle);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NoteBoxVO other = (NoteBoxVO) obj;
		return Objects.equals(nBBody, other.nBBody) && Objects.equals(nBCode, other.nBCode)
				&& Objects.equals(nBDate, other.nBDate) && Objects.equals(nBMember, other.nBMember)
				&& Objects.equals(nBSender, other.nBSender) && Objects.equals(nBTitle, other.nBTitle);
	}

	@Override
	public String toString() {
		return "NoteBoxVO [nBCode=" + nBCode + ", nBTitle=" + nBTitle + ", nBBody=" + nBBody + ", nBDate=" + nBDate
				+ ", nBMember=" + nBMember + ", nBSender=" + nBSender + "]";
	}
}