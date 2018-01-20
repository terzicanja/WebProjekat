package yt.model;

import java.util.Date;

public class Korisnik {
	public enum TipKorisnika {USER, ADMIN};
	
	private String username;
	private String password;
	private String ime;
	private String prezime;
	private String email;
	private String opisKanala;
	private Date datumRegistracije;
	private TipKorisnika tip;
	private boolean blockedUser;
	private int pratioci; //ovde ce vrv trebati menjati u listu
	private int likeVideo;
	private int dislikeVideo;
	private int likeComment; //sta ce ovo ovde?????
	private int dislikeComment;
	
	public Korisnik() {
		username = "";
		password = "";
	}
	
	public Korisnik(String username, String password, String ime, String prezime, String email, String opisKanala,
			Date datumRegistracije, TipKorisnika tip, boolean blockedUser, int pratioci, int likeVideo,
			int dislikeVideo, int likeComment, int dislikeComment) {
		super();
		this.username = username;
		this.password = password;
		this.ime = ime;
		this.prezime = prezime;
		this.email = email;
		this.opisKanala = opisKanala;
		this.datumRegistracije = datumRegistracije;
		this.tip = tip;
		this.blockedUser = blockedUser;
		this.pratioci = pratioci;
		this.likeVideo = likeVideo;
		this.dislikeVideo = dislikeVideo;
		this.likeComment = likeComment;
		this.dislikeComment = dislikeComment;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getIme() {
		return ime;
	}
	public void setIme(String ime) {
		this.ime = ime;
	}
	public String getPrezime() {
		return prezime;
	}
	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getOpisKanala() {
		return opisKanala;
	}
	public void setOpisKanala(String opisKanala) {
		this.opisKanala = opisKanala;
	}
	public Date getDatumRegistracije() {
		return datumRegistracije;
	}
	public void setDatumRegistracije(Date datumRegistracije) {
		this.datumRegistracije = datumRegistracije;
	}
	public TipKorisnika getTip() {
		return tip;
	}
	public void setTip(TipKorisnika tip) {
		this.tip = tip;
	}
	public boolean isBlockedUser() {
		return blockedUser;
	}
	public void setBlockedUser(boolean blockedUser) {
		this.blockedUser = blockedUser;
	}
	public int getPratioci() {
		return pratioci;
	}
	public void setPratioci(int pratioci) {
		this.pratioci = pratioci;
	}
	public int getLikeVideo() {
		return likeVideo;
	}
	public void setLikeVideo(int likeVideo) {
		this.likeVideo = likeVideo;
	}
	public int getDislikeVideo() {
		return dislikeVideo;
	}
	public void setDislikeVideo(int dislikeVideo) {
		this.dislikeVideo = dislikeVideo;
	}
	public int getLikeComment() {
		return likeComment;
	}
	public void setLikeComment(int likeComment) {
		this.likeComment = likeComment;
	}
	public int getDislikeComment() {
		return dislikeComment;
	}
	public void setDislikeComment(int dislikeComment) {
		this.dislikeComment = dislikeComment;
	}
	@Override
	public String toString() {
		return "Korisnik [username=" + username + ", password=" + password + ", ime=" + ime + ", prezime=" + prezime
				+ ", email=" + email + ", opisKanala=" + opisKanala + ", datumRegistracije=" + datumRegistracije
				+ ", tip=" + tip + ", blockedUser=" + blockedUser + ", pratioci=" + pratioci + ", likeVideo="
				+ likeVideo + ", dislikeVideo=" + dislikeVideo + ", likeComment=" + likeComment + ", dislikeComment="
				+ dislikeComment + "]";
	}
	
	

}
