package com.bank.model;

public class Cards {
	
	private int id;
	private int idUser;
	private String card;
	private int type;
	private String desc;
	
	public Cards(){
		super();
	}
	
	public Cards(int id, int idUser, String card, int type, String desc){
		this.id=id;
		this.idUser=idUser;
		this.card=card;
		this.type=type;
		this.desc=desc;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	public String getCard() {
		return card;
	}
	public void setCard(String card) {
		this.card = card;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	

}
