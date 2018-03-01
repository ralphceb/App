package com.bank.service;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.model.Cards;
import com.bank.model.InMemory;
import com.bank.model.Movements;
import com.bank.model.Person;

@Service
public class H2Service {

	private  final String DB_DRIVER = "org.h2.Driver";
    private  final String DB_CONNECTION = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
    private  final String DB_USER = "";
    private  final String DB_PASSWORD = "";
    
    @Autowired
    private InMemory inMemoryData;

    public void cardType(String desc){
    	if(desc.equals("CREDIT")){
    		inMemoryData.setType(1);
    	}else{
    		inMemoryData.setType(2);
    	}
    }
    
    public Person findPerson(){
    	String query =  getProperties().getProperty("SELECT.PERSON.ID");
    	Connection connection = getDBConnection();
    	PreparedStatement select = null;
    	Person p = new Person();
    	try{
    		select = connection.prepareStatement(query);
    		select.setInt(1, 1);
    		 ResultSet rs = select.executeQuery();
	               rs.next();         	
            	p.setUser(rs.getString("name"));
            	p.setAcount(rs.getString("acount"));
            	p.setFounds(rs.getDouble("founds"));           
            select.close();
    	}catch(SQLException e){
    			e.printStackTrace();
    		}
    	
    	return p;
    }
    
    public List<Cards> findCards(String user){
    	String query = getProperties().getProperty("SELECT.CARD");  
    	Connection connection = getDBConnection();
    	PreparedStatement select = null;
    	
    	List<Cards> allCards = new ArrayList<Cards>();
    	try{
    		select = connection.prepareStatement(query);
    		select.setString(1, user);
    		 ResultSet rs = select.executeQuery();
    		 
    		 while (rs.next()) {	
    			Cards c = new Cards();
            	c.setId(rs.getInt("id"));
            	c.setIdUser(rs.getInt("idUser"));
            	c.setCard(rs.getString("card"));
            	c.setType(rs.getInt("type"));
            	c.setDesc(rs.getString("desc"));
            	allCards.add(c);
    		 }
            select.close();
    	}catch(SQLException e){
    			e.printStackTrace();
    		}
    	
    	return allCards;
    }
    
    private Properties getProperties(){
    	Properties prop = new Properties();
    	try {
			prop.load(H2Service.class.getClassLoader().getResourceAsStream("db.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
    	return prop;
    }
    
    public List<Movements> findAll() throws SQLException{
    	String selectQuery = getProperties().getProperty("SELECT.MOV");  
    	Connection connection = getDBConnection();
    	PreparedStatement selectPreparedStatement = null;
    	
    	selectPreparedStatement = connection.prepareStatement(selectQuery);
        ResultSet rs = selectPreparedStatement.executeQuery();        
        List<Movements> filteredMoves = new ArrayList<Movements>();
        while (rs.next()) {
        	Movements m = new Movements();
        	m.setName(rs.getString("name"));
        	m.setDesc(rs.getString("desc"));
        	m.setType(rs.getString("type"));
        	m.setAmount(rs.getDouble("amount"));
        	m.setDate(rs.getString("date"));
        	filteredMoves.add(m);
        }
        selectPreparedStatement.close();
        return filteredMoves;
    }
    
    public void movRegisterDeposit(String user,Person person) throws SQLException{
    	String insertMov = getProperties().getProperty("INSERT.MOV");
    	String selectPerson = getProperties().getProperty("SELECT.PERSON,MAIL"); 
    	String updatePerson = getProperties().getProperty("UPDATE.PERSON"); 
    	Calendar today = Calendar.getInstance();
    	Connection connection = getDBConnection();
    	PreparedStatement insertStatement = null;
    	PreparedStatement selectStatement = null;
    	PreparedStatement updateStatement = null;
    	Person p = new Person();
    	Date date = new Date();
    	String modifiedDate= new SimpleDateFormat("yyyy-MM-dd").format(date);
    	try{
    		selectStatement = connection.prepareStatement(selectPerson);
    		selectStatement.setString(1, user);
    		ResultSet rs = selectStatement.executeQuery();    		
    		rs.next();
    		p.setId(rs.getInt("id"));
    		p.setAcount(rs.getString("acount"));
    		p.setCard(rs.getString("card"));
    		p.setFounds(rs.getDouble("founds"));
    		p.setMail(rs.getString("mail"));
    		p.setUser(rs.getString("name"));
    		selectStatement.close();
    		
    		updateStatement = connection.prepareStatement(updatePerson);
    		updateStatement.setDouble(1,person.getFounds()+p.getFounds());
    		updateStatement.setString(2, user);
    		updateStatement.executeUpdate();
    	
    		updateStatement.close();
    		
	    	insertStatement = connection.prepareStatement(insertMov);
	    	insertStatement.setInt(1, p.getId());
	    	insertStatement.setString(2, inMemoryData.getType()==1?"CREDIT":"DEBIT");
	    	insertStatement.setString(3, "DEPOSIT");
	    	insertStatement.setString(4, p.getUser());
	    	insertStatement.setDouble(5, person.getFounds());
	    	insertStatement.setString(6, modifiedDate);
	        insertStatement.executeUpdate();
	        insertStatement.close();
	        
	    }catch (SQLException e) {
	            System.out.println("Exception Message " + e.getLocalizedMessage());
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            connection.close();
	        }
    	
    }
    
    public void movRegisterWithdraw(String user,Person person) throws SQLException{
    	String insertMov = getProperties().getProperty("INSERT.MOV");
    	String selectPerson = getProperties().getProperty("SELECT.PERSON,MAIL"); 
    	String updatePerson = getProperties().getProperty("UPDATE.PERSON"); 
    	Calendar today = Calendar.getInstance();
    	Connection connection = getDBConnection();
    	PreparedStatement insertStatement = null;
    	PreparedStatement selectStatement = null;
    	PreparedStatement updateStatement = null;
    	Person p = new Person();
    	Date date = new Date();
    	String modifiedDate= new SimpleDateFormat("yyyy-MM-dd").format(date);
    	try{
    		selectStatement = connection.prepareStatement(selectPerson);
    		selectStatement.setString(1, user);
    		ResultSet rs = selectStatement.executeQuery();
    		rs.next();
    		p.setId(rs.getInt("id"));
    		p.setAcount(rs.getString("acount"));
    		p.setCard(rs.getString("card"));
    		p.setFounds(rs.getDouble("founds"));
    		p.setMail(rs.getString("mail"));
    		p.setUser(rs.getString("name"));
    		selectStatement.close();
    		
    		updateStatement = connection.prepareStatement(updatePerson);
    		updateStatement.setDouble(1,p.getFounds()-person.getFounds());
    		updateStatement.setString(2, user);
    		updateStatement.executeUpdate();
    	
    		updateStatement.close();
	    	insertStatement = connection.prepareStatement(insertMov);
	    	insertStatement.setInt(1, p.getId());
	    	insertStatement.setString(2, inMemoryData.getType()==1?"CREDIT":"DEBIT");
	    	insertStatement.setString(3, "WITHDRAW");
	    	insertStatement.setString(4, p.getUser());
	    	insertStatement.setDouble(5, person.getFounds());
	    	insertStatement.setString(6, modifiedDate);
	        insertStatement.executeUpdate();
	        

	    	}catch (SQLException e) {
	            System.out.println("Exception Message " + e.getLocalizedMessage());
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            connection.close();
	        }
    	
    }
    
    public void initDB() throws SQLException{
    	 Connection connection = getDBConnection();
         PreparedStatement createPreparedStatement = null;
         PreparedStatement insertPreparedStatement = null;
         
         String createTablePerson = getProperties().getProperty("CREATE.PERSON");
         String createTableCards = getProperties().getProperty("CREATE.CARDS"); 
         String createTableMov = getProperties().getProperty("CREATE.MOV"); 
          
         
         String insertPerson = getProperties().getProperty("INSERT.PERSON");  
         String insertCard = getProperties().getProperty("INSERT.CARDS"); 
         try{
        	 createPreparedStatement = connection.prepareStatement(createTablePerson);
             createPreparedStatement.executeUpdate();
             createPreparedStatement = connection.prepareStatement(createTableCards);
             createPreparedStatement.executeUpdate();
             createPreparedStatement = connection.prepareStatement(createTableMov);
             createPreparedStatement.executeUpdate();
             createPreparedStatement.close();        	 
        	 
	         insertPreparedStatement = connection.prepareStatement(insertPerson);
	         insertPreparedStatement.setInt(1, 1);
	         insertPreparedStatement.setString(2, "Rafael Ceballos");
	         insertPreparedStatement.setString(3, "1234567893214569");
	         insertPreparedStatement.setString(4, "rceballos@gmail.com");
	         insertPreparedStatement.setDouble(5, 0.00);
	         insertPreparedStatement.setString(6, "4679851347");
	         insertPreparedStatement.executeUpdate();
	         
	         insertPreparedStatement = connection.prepareStatement(insertCard);
	         insertPreparedStatement.setInt(1, 1);
	         insertPreparedStatement.setInt(2, 1);
	         insertPreparedStatement.setString(3, "1234567893214569");
	         insertPreparedStatement.setInt(4, 1);
	         insertPreparedStatement.setString(5, "CREDIT");
	         insertPreparedStatement.executeUpdate();
	         
	         insertPreparedStatement = connection.prepareStatement(insertCard);
	         insertPreparedStatement.setInt(1, 2);
	         insertPreparedStatement.setInt(2, 1);
	         insertPreparedStatement.setString(3, "7834596893214569");
	         insertPreparedStatement.setInt(4, 2);
	         insertPreparedStatement.setString(5, "DEBIT");
	         insertPreparedStatement.executeUpdate();
	         insertPreparedStatement.close();
         }catch (SQLException e) {
             System.out.println("Exception Message " + e.getLocalizedMessage());
         } catch (Exception e) {
             e.printStackTrace();
         } finally {
             connection.close();
         }
    }

    public Connection getDBConnection() {
        Connection dbConnection = null;
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
            return dbConnection;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return dbConnection;
    }
}
