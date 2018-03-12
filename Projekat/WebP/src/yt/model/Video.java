package yt.model;

import java.util.Date;

public class Video {
	public enum TipVidea {PUBLIC, UNLISTED, PRIVATE};
	//proba za git
	private String id;
	private String url;
	private String naslov;
	private String opisVidea;
	private TipVidea tipVidea;
	private boolean komentariDozvoljeni;
	private boolean likeAllowed;
	private int numberOfLikes;
	private int numberOfDislikes;
	private boolean blokiran;
	private int views;
	private Date vremeKreiranja;
	private Korisnik vlasnik;
	
	public Video(String id, String url, String naslov, String opisVidea, TipVidea tipVidea, boolean komentariDozvoljeni,
			boolean likeAllowed, int numberOfLikes, int numberOfDislikes, boolean blokiran, int views,
			Date vremeKreiranja, Korisnik vlasnik) {
		super();
		this.id = id;
		this.url = url;
		this.naslov = naslov;
		this.opisVidea = opisVidea;
		this.tipVidea = tipVidea;
		this.komentariDozvoljeni = komentariDozvoljeni;
		this.likeAllowed = likeAllowed;
		this.numberOfLikes = numberOfLikes;
		this.numberOfDislikes = numberOfDislikes;
		this.blokiran = blokiran;
		this.views = views;
		this.vremeKreiranja = vremeKreiranja;
		this.vlasnik = vlasnik;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getNaslov() {
		return naslov;
	}

	public void setNaslov(String naslov) {
		this.naslov = naslov;
	}

	public String getOpisVidea() {
		return opisVidea;
	}

	public void setOpisVidea(String opisVidea) {
		this.opisVidea = opisVidea;
	}

	public TipVidea getTipVidea() {
		return tipVidea;
	}

	public void setTipVidea(TipVidea tipVidea) {
		this.tipVidea = tipVidea;
	}

	public boolean isKomentariDozvoljeni() {
		return komentariDozvoljeni;
	}

	public void setKomentariDozvoljeni(boolean komentariDozvoljeni) {
		this.komentariDozvoljeni = komentariDozvoljeni;
	}

	public boolean isLikeAllowed() {
		return likeAllowed;
	}

	public void setLikeAllowed(boolean likeAllowed) {
		this.likeAllowed = likeAllowed;
	}

	public int getNumberOfLikes() {
		return numberOfLikes;
	}

	public void setNumberOfLikes(int numberOfLikes) {
		this.numberOfLikes = numberOfLikes;
	}

	public int getNumberOfDislikes() {
		return numberOfDislikes;
	}

	public void setNumberOfDislikes(int numberOfDislikes) {
		this.numberOfDislikes = numberOfDislikes;
	}

	public boolean isBlokiran() {
		return blokiran;
	}

	public void setBlokiran(boolean blokiran) {
		this.blokiran = blokiran;
	}

	public int getViews() {
		return views;
	}

	public void setViews(int views) {
		this.views = views;
	}

	public Date getVremeKreiranja() {
		return vremeKreiranja;
	}

	public void setVremeKreiranja(Date vremeKreiranja) {
		this.vremeKreiranja = vremeKreiranja;
	}

	public Korisnik getVlasnik() {
		return vlasnik;
	}

	public void setVlasnik(Korisnik vlasnik) {
		this.vlasnik = vlasnik;
	}

	@Override
	public String toString() {
		return "Video [id=" + id + ", url=" + url + ", naslov=" + naslov + ", opisVidea=" + opisVidea + ", tipVidea="
				+ tipVidea + ", komentariDozvoljeni=" + komentariDozvoljeni + ", likeAllowed=" + likeAllowed
				+ ", numberOfLikes=" + numberOfLikes + ", numberOfDislikes=" + numberOfDislikes + ", blokiran="
				+ blokiran + ", views=" + views + ", vremeKreiranja=" + vremeKreiranja + ", vlasnik=" + vlasnik + "]";
	}
	
	
	
	
	
	
	

}
