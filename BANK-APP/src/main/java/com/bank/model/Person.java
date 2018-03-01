package com.bank.model;

import java.util.Date;

import javax.validation.constraints.Size;

public class Person {
    private int id;
    private String user;
    private String mail;
    private String card;
    private Double founds;
    private String acount;
    
//    @Size(min=10, message="Enter at least 10 Characters...")
//    private String desc;

 
    public Person() {
    		super();
    }
    
    public Person(int id, String user, String mail, String card,
            Double founds, String acount) {
        super();
        this.id = id;
        this.user = user;
        this.mail = mail;
        this.card = card;
        this.founds = founds;
        this.acount = acount;
    }

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

	public String getCard() {
		return card;
	}

	public void setCard(String card) {
		this.card = card;
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

	@Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Person other = (Person) obj;
        if (id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return String.format(
                "Person [id=%s, user=%s, desc=%s, targetDate=%s, isDone=%s]", id,
                user);
    }

}