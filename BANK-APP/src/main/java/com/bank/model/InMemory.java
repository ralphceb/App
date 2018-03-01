package com.bank.model;

import org.springframework.stereotype.Component;

@Component
public class InMemory {
	 private int id;
	    private String user;
	    private String mail;
	    private Double founds;
	    private String acount;
		private int idUser;
		private String card;
		private int type;
		private String desc;
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getUser() {
			return user;
		}
		public void setUser(String user) {
			this.user = user;
		}
		public String getMail() {
			return mail;
		}
		public void setMail(String mail) {
			this.mail = mail;
		}
		public Double getFounds() {
			return founds;
		}
		public void setFounds(Double founds) {
			this.founds = founds;
		}
		public String getAcount() {
			return acount;
		}
		public void setAcount(String acount) {
			this.acount = acount;
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
