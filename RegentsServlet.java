package com.ts.examprep;

import java.io.IOException;

import javax.jdo.PersistenceManager;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import org.mortbay.log.Log;

import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.logging.*;

import com.ts.questions.LoadDB;
import com.ts.questions.Quiz;
import com.ts.questions.ReguserSummary;
import com.ts.questions.StaticObjects;
import com.ts.questions.TestInCategory;
import com.ts.questions.TestSummary;

@SuppressWarnings("serial")
public class RegentsServlet extends HttpServlet {
	int counter = 0;
	// PersistenceManager pm;
	public ArrayList<Quiz> cellsList = new ArrayList<Quiz>();
	public ArrayList<Quiz> fruits = new ArrayList<Quiz>();
	public ArrayList<Quiz> digestiveSystemList1 = new ArrayList<Quiz>();
	public ArrayList<Quiz> digestiveSystemList2 = new ArrayList<Quiz>();
	public ArrayList<Quiz> enzymesList = new ArrayList<Quiz>();
	List<Quiz> cells = new ArrayList<Quiz>();
	String category = "cells";
	LoadDB ldb;
	private static Logger logger = Logger.getLogger(RegentsServlet.class
			.getName());

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	
		
		logger.log(Level.WARNING, "REG servlet init", "");
		String initial = config.getInitParameter("initial");
		ldb = new LoadDB();
		try {
			
			ldb.loadAllQuestionsFromFile(cellsList, "class_fruit.txt");

			
//		ldb.loadAllQuestionsFromFile(cellsList, "class_Dairy.txt");
		//ldb.loadAllQuestionsFromFile(digestiveSystemList1,"class_EntertainmentArtisticEvents.txt");
		
		//ldb.loadAllQuestionsFromFile(digestiveSystemList2,"digestiveSystem2.txt");
		//ldb.loadAllQuestionsFromFile(enzymesList, "enzymes.txt");
		
	//	ldb.loadAllQuestionsFromFile(cellsList, "beverage.txt");
		//ldb.loadAllQuestionsFromFile(digestiveSystemList1,"digestiveSystem1.txt");
		//ldb.loadAllQuestionsFromFile(digestiveSystemList2,"digestiveSystem2.txt");
		//ldb.loadAllQuestionsFromFile(enzymesList, "enzymes.txt");
		
		//ldb.loadAllQuestionsFromFile(cellsList, "beverage.txt");
		//ldb.loadAllQuestionsFromFile(digestiveSystemList1,"digestiveSystem1.txt");
		//ldb.loadAllQuestionsFromFile(digestiveSystemList2,"digestiveSystem2.txt");
		//ldb.loadAllQuestionsFromFile(enzymesList, "enzymes.txt");
		
	//	ldb.deleteExistingData(); // ldb.deleteTestExistingData();
	//	logger.log(Level.WARNING, "all deleted", "");
	//		ldb.insertDB(cellsList);
//			ldb.insertDB(digestiveSystemList1);
//
//			ldb.insertDB(digestiveSystemList2);
//			ldb.insertDB(enzymesList);
//			ldb.insertDB(cellsList);
//			
//			ldb.insertDB(digestiveSystemList1);
//			ldb.insertDB(digestiveSystemList2);
//			ldb.insertDB(enzymesList);
//			ldb.insertDB(cellsList);
//			
//			ldb.insertDB(digestiveSystemList1);
//			ldb.insertDB(digestiveSystemList2);
//			ldb.insertDB(enzymesList);
//			ldb.insertDB(cellsList);

			
			
			// ldb.setQuizTable(); all done while insert
			logger.log(Level.WARNING, "INIT "+cellsList.size(), "");
			// load the exam file in the database
			// set aval quizes
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// pm.close();
		}
	}
	public void doGet1(HttpServletRequest req, HttpServletResponse resp)
	throws IOException {
		UTIL.debugParams(req);
		logger.log(Level.INFO, "request getContextPath"+req.getContextPath());
//		Enumeration enm = req.getParameterNames();
//		
//		while(enm.hasMoreElements()){
//			String str = (String)enm.nextElement();
//			logger.log(Level.INFO,"--"+str+"--"+req.getParameter(str));
//		}
	}
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		//UTIL.debugParams(req);
		
		//logger.log(Level.INFO, "request getContextPath"+req.getContextPath());
	/*
		if (!UTIL.srtValid((String) req.getSession().getAttribute(
				StaticObjects.TESTS_SET))) 
		{
			setAttributes(req);
		}
		if(UTIL.isFieldValid(req, "inClassTestRelease") && UTIL.isFieldValid(req, "releaseTest"))
		{
			req.getSession().removeAttribute(StaticObjects.TESTS_SET);
			String testToRelease = req.getParameter("inClassTestRelease");
			ldb = new LoadDB();
			ldb.releaseTest(testToRelease);
			setAttributes(req);
			
		}
		else if(UTIL.isFieldValid(req, "inClassTestRelease") && UTIL.isFieldValid(req, "removeTest"))
		{
			req.getSession().removeAttribute(StaticObjects.TESTS_SET);
			String testToRelease = req.getParameter("inClassTestRelease").trim();
			ldb = new LoadDB();
			ldb.removeTest(testToRelease);
			setAttributes(req);
		}
		else if(req.getParameter("initpg_quizcategory") != null)
		{
			setAttributes(req);
		}
	*/
	//	logger.log(Level.INFO, "IS_ATTR_SET"+ req.getSession().getAttribute("IS_ATTR_SET"), "--");

		Page page = new Page();
		page.setPageCounts(req);
		setUserChoice(req);

		try {
			String forwardURL = "/jspresources/quiz_question.jsp";
			logger.log(Level.INFO, "request getContextPath"+req.getContextPath());
			

			
			if(req.getParameter("rupage")!= null && req.getParameter("rupage").equals("rupage"))
			{
				forwardURL = "/rujspresources/quiz_question.jsp";
			}

			if (req.getParameter("mainmenu") != null
					|| req.getParameter("initpg") != null) {
				if(req.getParameter("initpg") != null)
				{
					req.getSession().removeAttribute(StaticObjects.TESTS_SET);
				}
				//setAttributes(req);
				forwardURL = "/jspresources/form_quizselection.jsp";
				if(req.getParameter("rupage")!= null && req.getParameter("rupage").equals("rupage"))
				{
					forwardURL = "/rujspresources/form_quizselection.jsp";
				}
			} else if (req.getParameter("addToUserSummary") != null) {
				saveQuizResults(req);
				forwardURL = "/jspresources/summary.jsp";
				if(req.getParameter("rupage")!= null && req.getParameter("rupage").equals("rupage"))
				{
					forwardURL = "/rujspresources/summary.jsp";
				}

			}
			else if (req.getParameter("resetcounts") != null) {
				resetCounts(req);
				forwardURL = "/jspresources/summary.jsp";
				if(req.getParameter("rupage")!= null && req.getParameter("rupage").equals("rupage"))
				{
					forwardURL = "/rujspresources/summary.jsp";
				}
			} 
			else if(req.getParameter("summary") != null &&
					req.getSession().getAttribute(StaticObjects.INCLASSONLY) != null &&
					req.getSession().getAttribute(StaticObjects.INCLASSONLY).equals("true"))
			{
				saveQuizResults(req);
				forwardURL = "/jspresources/summary.jsp";
				if(req.getParameter("rupage")!= null && req.getParameter("rupage").equals("rupage"))
				{
					forwardURL = "/rujspresources/summary.jsp";
				}
			}
			else if(req.getParameter("summary") != null)
			{
				forwardURL = "/jspresources/summary.jsp";
				if(req.getParameter("rupage")!= null && req.getParameter("rupage").equals("rupage"))
				{
					forwardURL = "/rujspresources/summary.jsp";
				}
			}
			else if (req.getParameter("welcome") != null) 
			{
				forwardURL = "/jspresources/index.jsp";
				if(req.getParameter("rupage")!= null && req.getParameter("rupage").equals("rupage"))
				{
					forwardURL = "/rujspresources/index.jsp";
				}
			} 
			logger.log(Level.INFO," forwardURL"+forwardURL);
			resp.sendRedirect(resp.encodeRedirectURL(forwardURL));
			
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp) {
		try {
			doGet(req, resp);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setAttributes(HttpServletRequest req) {

		if (!UTIL.srtValid((String) req.getSession().getAttribute(
				StaticObjects.TESTS_SET))) {
			logger.log(Level.WARNING, ": GETTING DATA FROM DB 1:" , "");
			Hashtable<String, ArrayList> thistestQuizzes = new Hashtable<String, ArrayList>();
			logger.log(Level.WARNING,"--1-");
			ldb.selectQuizFromCloud(thistestQuizzes);
			logger.log(Level.WARNING,"--2-");
			req.getSession().setAttribute(StaticObjects.ALLTESTS, thistestQuizzes);
			logger.log(Level.INFO,"--3-");
		}

		logger.log(Level.WARNING, req.getParameter("initpg_quizcategory")	+ "ALLTESTS hashtable size is", "");
		Hashtable<String, ArrayList> alltests = (Hashtable<String, ArrayList>) req
				.getSession().getAttribute(StaticObjects.ALLTESTS);
		logger.log(Level.WARNING, "::" + alltests.size(), "");

		List<Quiz> current_test_questions = new ArrayList<Quiz>();
		String current_category = "fruits";
		
		logger.log(Level.WARNING, ":current_category:" + current_category, "");
		current_test_questions = alltests.get(current_category);
	//	logger.log(Level.WARNING, ":current_test_questions.size():" + current_test_questions.size(), "");
		
		if (req.getParameter("initpg_quizcategory") != null && UTIL.srtValid((String) req.getParameter("initpg_quizcategory"))) 
		{
			current_category = (String) req.getParameter("initpg_quizcategory");
			// define what is the in class only stat here + set it in attribute
			LoadDB ldb = new LoadDB();
			
			TestInCategory testincategory = ldb.getTestByTestname(current_category);
			if(testincategory.getInClassOnly())
			{
				req.getSession().setAttribute(StaticObjects.INCLASSONLY, "true");
			}
			else
			{
				req.getSession().setAttribute(StaticObjects.INCLASSONLY, "false");
			}
			
		}

		current_test_questions = alltests.get(current_category);

		logger.log(Level.WARNING,"here1");
		req.getSession().setAttribute(StaticObjects.CURRENT_TESTNAME, current_category);
		//req.getSession().setAttribute(StaticObjects.CELLS,current_test_questions);
		logger.log(Level.WARNING,"current_test_questions.size()"+current_test_questions.size());
		Integer size = new Integer(current_test_questions.size());
		req.getSession().setAttribute(StaticObjects.MAX_Q_CELLS, size);
		req.getSession().setAttribute(StaticObjects.COUNTER, new Integer(0));
		req.getSession().setAttribute(StaticObjects.TESTS_SET,
				StaticObjects.TESTS_SET);

		// String category = "cells";
		// if (UTIL.srtValid(req.getParameter("initpg_quizcategory") )) {
		// req.getSession().setAttribute("category",
		// req.getParameter("initpg_quizcategory"));
		// // get corresponding quiz array and set it for the session
		// category = (String) req.getParameter("initpg_quizcategory");
		//
		// }
		//
		// if (req.getSession().getAttribute("IS_ATTR_SET") == null) {
		// setAttributes(req, category);
		//
		// } else if(req.getParameter("initpg_quizcategory") != null
		// && !req.getParameter("initpg_quizcategory").trim().equals(""))
		// {
		// System.out.println("category"+req.getParameter("initpg_quizcategory"));
		// resetAttibutes(req, req.getParameter("initpg_quizcategory"));
		// logger.log(Level.WARNING,
		// "reset attributes for category "+req.getParameter("initpg_quizcategory"),
		// "--");
		// }

		// req.getSession().setAttribute("IS_ATTR_SET", "IS_ATTR_SET");
		// System.out.println("SETTING ATTRIBUTES category" + category);

		// Hashtable<String, ArrayList> allquizzes = new Hashtable<String,
		// ArrayList>();

		// String firsttestcategory = ldb.selectQuizFromCloud(allquizzes);
		// if (!UTIL.srtValid(category)) {
		// category = firsttestcategory;
		// System.out.println("seting category to firsttestcategory"
		// + firsttestcategory);
		// }

		// req.getSession().setAttribute(StaticObjects.ALLTESTS, allquizzes);
		// List<Quiz> cells1 = new ArrayList<Quiz>();
		// if (UTIL.srtValid(category)) {
		// cells1 = allquizzes.get(category);
		// }
		//
		// req.getSession().setAttribute(StaticObjects.CELLS, cells1);
		// Integer size = new Integer(cells1.size());
		// req.getSession().setAttribute(StaticObjects.MAX_Q_CELLS, size);
		// req.getSession().setAttribute(StaticObjects.COUNTER, new Integer(0));

	}

	public void resetAttibutes(HttpServletRequest req, String category) {

		Hashtable<String, ArrayList> allquizzes = (Hashtable) req.getSession()
				.getAttribute(StaticObjects.ALLTESTS);
		List<Quiz> cells1 = new ArrayList<Quiz>();
		if (UTIL.srtValid(category)) {
			cells1 = allquizzes.get(category);
		}

		req.getSession().setAttribute(StaticObjects.CELLS, cells1);
		Integer size = new Integer(cells1.size());
		//System.out.println("reset attr MAX size::" + size);
		req.getSession().setAttribute(StaticObjects.MAX_Q_CELLS, size);
		req.getSession().setAttribute(StaticObjects.COUNTER, new Integer(0));

	}

	public void setUserChoice(HttpServletRequest req) {
		//logger.log(Level.WARNING, "setUserChoice"+ req.getParameter("btnradio_choice"), "");
		if (req.getParameter("btnradio_choice") != null
				&& !req.getParameter("btnradio_choice").trim().equals("")) {
			// get a quiz from array

			int quiz_number = Integer.valueOf((String) req
					.getParameter(StaticObjects.COUNTER));
			List<Quiz> qlist = (List<Quiz>) req.getSession().getAttribute(
					StaticObjects.CELLS);
			//logger.log(Level.WARNING, "setUserChoice req get param counter::"+ req.getParameter(StaticObjects.COUNTER), "");
			//logger.log(Level.WARNING, "setUserChoice quiz_number"+quiz_number, "");
			Quiz qq = qlist.get(quiz_number);
			qq.setUser_choice(req.getParameter("btnradio_choice"));
			req.getSession().setAttribute(StaticObjects.CELLS, qlist);
		}
	}

	public void saveQuizResults(HttpServletRequest req) {
		//logger.log(Level.WARNING, "SAVING TO USER SUMMARY", "");
		TestSummary testsummary = new TestSummary();
		testsummary.calulateSummary((List<Quiz>) req.getSession().getAttribute(
				StaticObjects.CELLS));

		ReguserSummary rs = new ReguserSummary();
		
		String testname_category = "";
		if(req.getSession().getAttribute(StaticObjects.CURRENT_TESTNAME) != null)
		{
			testname_category=(String)req.getSession().getAttribute(StaticObjects.CURRENT_TESTNAME);
		}
//System.out.println("userename-"+req.getSession().getAttribute("username")+"- test category -"+testname_category);
		rs.setUsername((String) req.getSession().getAttribute("username"));
		rs.setPercentage(testsummary.getPercentage());
		rs.setScore(testsummary.getScore());
		rs.setQuestions_rightanswer(testsummary.getQuestions_rightanswer());
		// rs.setUserAnswers((List<Quiz>)
		// req.getSession().getAttribute(StaticObjects.CELLS));
		rs.setTest_category(testname_category);
		rs.setTestDate(new Date());
		rs.saveYourQuizes();
		PersistenceManager pm = PMF.get().getPersistenceManager();
		pm.makePersistent(rs);

	}

	public void resetCounts(HttpServletRequest req) {
		List<Quiz> quizlist = (List<Quiz>) req.getSession().getAttribute(
				StaticObjects.CELLS);
		for (int i = 0; i < quizlist.size(); i++) {
			Quiz qq = quizlist.get(i);
			qq.user_choice = "";
		}
	}

}
