/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arackiralama.model;

public class Arac {
	public int id;
	public String plakaNo;
	public String model;
	public String marka;
	public Arac(int id, String plakaNo, String model, String marka, String renk) {
		super();
		this.id = id;
		this.plakaNo = plakaNo;
		this.model = model;
		this.marka = marka;
		this.renk = renk;
	}
	public Arac(String plakaNo, String model, String marka, String renk) {
		super();
		this.plakaNo = plakaNo;
		this.model = model;
		this.marka = marka;
		this.renk = renk;
	}
	public String renk;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPlakaNo() {
		return plakaNo;
	}
	public void setPlakaNo(String plakaNo) {
		this.plakaNo = plakaNo;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getMarka() {
		return marka;
	}
	public void setMarka(String marka) {
		this.marka = marka;
	}
	public String getRenk() {
		return renk;
	}
	public void setRenk(String renk) {
		this.renk = renk;
	}


}

