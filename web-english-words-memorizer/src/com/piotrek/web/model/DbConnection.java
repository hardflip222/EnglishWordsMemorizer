package com.piotrek.web.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class DbConnection
{
	
	private static DbConnection dataConnection;
	private DataSource dataSource;
	private Connection conn;
	private Statement statement;
	private PreparedStatement preparedStatement;
	
	
	private DbConnection()
	{
	}
	
	public static DbConnection getInstance()
	{
		if(dataConnection == null)
		{
			dataConnection = new DbConnection();
		}
			return dataConnection;
	}
	
	
	
	public void setDataSource(DataSource dataSource)
	{
		this.dataSource = dataSource;
	}
	
	
	
	
	
	public void open()
	{
		if(conn==null)
		{
			try {
				conn = dataSource.getConnection();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	
	
	public void close()
	{
		if(conn!=null)
		{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	
	public List<Word> getWords()
	{
	  
		List<Word> words = new ArrayList<>();
		
		String sql = "Select * from word";
		
		try {
			statement = conn.createStatement();
			
			ResultSet rs = statement.executeQuery(sql);
			
			while(rs.next())
			{
				words.add(new Word(rs.getInt("id"),rs.getString("english_word"),rs.getString("polish_word")));
			}
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return words;
		
	}
	
	
	public List<Word> getSpecialWords(String key)
	{
	  
		List<Word> words = new ArrayList<>();
		
		String sql = "Select * from word WHERE english_word LIKE '%"+key+"%' OR polish_word LIKE '%"+key+"%' ";
		
		try {
			statement = conn.createStatement();
			
			ResultSet rs = statement.executeQuery(sql);
			
			while(rs.next())
			{
				words.add(new Word(rs.getInt("id"),rs.getString("english_word"),rs.getString("polish_word")));
			}
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return words;
		
	}
	
	public boolean addNewWord(Word word)
	{
		
		boolean ifExists = isTheWordInDataBase(word);
		
		
		if(!ifExists)
		{
		
			String sql = "Insert into Word (english_word, polish_word) values (?, ?)";
		
			try {
				preparedStatement = conn.prepareStatement(sql);
				preparedStatement.setString(1, word.getEnglishWord());
				preparedStatement.setString(2, word.getPolishWord());
			
				return preparedStatement.execute();
			
			
				} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
				}
		}
		
		return false;
		
	}
	
	private boolean isTheWordInDataBase(Word word)
	{
			String sql = "Select count(*) AS NUMBER from word Where english_word = ? AND polish_word = ? ";
			
			try {
				preparedStatement = conn.prepareStatement(sql);
				preparedStatement.setString(1, word.getEnglishWord());
				preparedStatement.setString(2, word.getPolishWord());
			    ResultSet rs = preparedStatement.executeQuery();
			    
			    int number = -1 ;
			    
			    while(rs.next())
			    {
			    	number = rs.getInt("NUMBER");
			    }
			    
			    
			   
			    
			    if(number == 0)
			    	return false;
			    else 
			    	return true;
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return false;
	}
	
	public void deleteWord(int id)
	{
		
		String sql = "DELETE FROM WORD WHERE Id = ?";
		try {
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			preparedStatement.execute();
			preparedStatement.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	public Word getWord(int id)
	{
		String sql = "Select * from WORD where id = ?";
		Word word = null;
		try {
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next())
			{
				word = new Word(rs.getInt("id"), rs.getString("english_word"),rs.getString("polish_word"));
			}
			
			return word;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return word;
		
	}
	
	public void updateWord(Word word)
	{
		String sql = "Update WORD SET english_word = ? , polish_word = ? WHERE id = ?";
		
		
		
		try {
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, word.getEnglishWord());
			preparedStatement.setString(2, word.getPolishWord());
			preparedStatement.setInt(3, word.getId());
		
			preparedStatement.execute();
			
			preparedStatement.close();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Word getRandomWord()
	{
		String sql = "Select max(id) AS MAX FROM WORD";
		Word word = null;
		int max = 0;
		try {
			statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			
			while(rs.next())
			{
				max = rs.getInt("MAX");
			}
			statement.close();
			rs.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 sql  = "Select * from WORD where id = ?";
		while(word == null)
		{
		 
			int rand = (int)(Math.random()*max)+1;
			
		  try {
			preparedStatement = conn.prepareStatement(sql);
		    preparedStatement.setInt(1, rand);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next())
			{
				word = new Word(rs.getInt("id"),rs.getString("english_word"),rs.getString("polish_word"));
			}
		
		  	} catch (SQLException e) {
		  		// TODO Auto-generated catch block
		  		e.printStackTrace();
		  	}
		}
		  return word;
		 
	}
	

}
