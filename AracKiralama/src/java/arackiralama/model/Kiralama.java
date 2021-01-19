/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arackiralama.model;

import java.sql.Date;

public class Kiralama {
	public int id;
	public Arac arac;
	public Musteri musteri;
	public Date baslangicTarihi;
	public Date bitisTarihi;
	public Kiralama(int id, Arac arac, Musteri musteri, Date baslangicTarihi, Date bitisTarihi) {
		super();
		this.id = id;
		this.arac = arac;
		this.musteri = musteri;
		this.baslangicTarihi = baslangicTarihi;
		this.bitisTarihi = bitisTarihi;
	}
	public Kiralama(Arac arac, Musteri musteri, Date baslangicTarihi, Date bitisTarihi) {
		super();
		this.arac = arac;
		this.musteri = musteri;
		this.baslangicTarihi = baslangicTarihi;
		this.bitisTarihi = bitisTarihi;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Arac getArac() {
		return arac;
	}
	public void setArac(Arac arac) {
		this.arac = arac;
	}
	public Musteri getMusteri() {
		return musteri;
	}
	public void setMusteri(Musteri musteri) {
		this.musteri = musteri;
	}
	public Date getBaslangicTarihi() {
		return baslangicTarihi;
	}
	public void setBaslangicTarihi(Date baslangicTarihi) {
		this.baslangicTarihi = baslangicTarihi;
	}
	public Date getBitisTarihi() {
		return bitisTarihi;
	}
	public void setBitisTarihi(Date bitisTarihi) {
		this.bitisTarihi = bitisTarihi;
	}


}

