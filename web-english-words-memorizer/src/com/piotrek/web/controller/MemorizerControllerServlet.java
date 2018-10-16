package com.piotrek.web.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.piotrek.web.model.DbConnection;
import com.piotrek.web.model.Word;
import com.sun.istack.internal.logging.Logger;

/**
 * Servlet implementation class MemorizerControllerServlet
 */
@WebServlet("/MemorizerControllerServlet")
public class MemorizerControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	private DbConnection dbConnection;
	
	@Resource(name="jdbc/web_english_words_memorizer")
	private DataSource dataSource;
	

	@Override
	public void init() throws ServletException {
		
		super.init();
		dbConnection = dbConnection.getInstance();
		dbConnection.setDataSource(dataSource);
		
		dbConnection.open();
	}
	
	@Override
	public void destroy() {
		
		super.destroy();
		
		dbConnection.close();
		
	}
	
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		
		try
		{
			String command = request.getParameter("command");
			
			
			if(command==null)
			{
				command="LIST";
			}
		
			switch(command)
			{
				case "LIST" : listWords(request,response); break;
				case "ADD"  : addWords(request,response); break;
				case "DELETE" : deleteWord(request,response); break;
				case "GETONE" : getOneWord(request,response); break;
				case "UPDATE" : updateWord(request,response); break;
				case "SEARCH" : searchListWords(request,response); break;
				case "START" : startTheGame(request,response); break;
				case "CHECK" : checkTheWord(request,response); break;
			
				default: listWords(request, response);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	private void listWords(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		
		List<Word> words = dbConnection.getWords();
		
		request.setAttribute("wordsList", words);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("list_words.jsp");
		
		dispatcher.forward(request, response);
	}
	
	private void addWords(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String englishWord = request.getParameter("englishWord");
		String polishWord = request.getParameter("polishWord");
		Word word = new Word(englishWord,polishWord);
		
	    dbConnection.addNewWord(word);
		
		listWords(request,response);
	}
	
	private void deleteWord(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		int wordId = Integer.parseInt(request.getParameter("wordId"));
		
		dbConnection.deleteWord(wordId);
		
		listWords(request,response);
	}
	
	private void getOneWord(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		
		int wordId = Integer.parseInt(request.getParameter("wordId"));
		
		Word word = dbConnection.getWord(wordId);
		
	    request.setAttribute("updatedWord", word);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("update-word.jsp");
		dispatcher.forward(request, response);
		
	}
	
	private void updateWord(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Word word = new Word(Integer.parseInt(request.getParameter("idWord")),request.getParameter("englishWord"),request.getParameter("polishWord"));
		
		dbConnection.updateWord(word);
		
		listWords(request, response);
	}
	
	
	private void searchListWords(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		List<Word> words = dbConnection.getSpecialWords(request.getParameter("key"));
		
		request.setAttribute("wordsList", words);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("list_words.jsp");
		
		dispatcher.forward(request, response);
	}
	
	private void startTheGame(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Word randomWord = dbConnection.getRandomWord();
		request.setAttribute("w", randomWord);
		RequestDispatcher dispatcher = request.getRequestDispatcher("game.jsp");
		dispatcher.forward(request, response);
	}
	
	private void checkTheWord(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String polishWord = request.getParameter("polishWord");
		String answer = request.getParameter("word");
		
		if(polishWord.toLowerCase().equals(answer.toLowerCase()))
		{
			request.setAttribute("ans", "GOOD");
		}
		else
		{
			request.setAttribute("ans", "BAD");
		}
		
		request.setAttribute("correctAnswer", polishWord);
		request.setAttribute("yourAnswer", answer);
		
		startTheGame(request,response);
		
	}
	
	
	

}
