package kh.com.servlet.freeboard;
//package com.kh.servlet;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.json.simple.JSONObject;
//
//import com.kh.common.Common;
//import com.kh.dao.FreeBoardDAO;
//
//@WebServlet("/BoardCreateServlet")
//public class BoardCreateServlet extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//	}
//	
//	protected void doOptions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		Common.corsResSet(response);
//	}
//
//	@SuppressWarnings("unchecked")
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		request.setCharacterEncoding("utf-8");
//		Common.corsResSet(response);
//		StringBuffer sb = Common.reqStringBuff(request);
//		JSONObject jsonObj = Common.getJsonObj(sb);
//		
//		String getFb_category = (String)jsonObj.get("fb_category");
//		String getFb_title = (String)jsonObj.get("fb_title");
//		String getFb_content = (String)jsonObj.get("fb_content");
//		
//		FreeBoardDAO dao = new FreeBoardDAO();
//		boolean rstComplete = dao.boardWrite(getFb_category, getFb_title, getFb_content, getFb_c_date, getFb_u_date, getFb_recommend, getFb_hit );
//		
//		PrintWriter out = response.getWriter();
//		JSONObject resJson = new JSONObject();
//		if(rstComplete) resJson.put("result", "OK");
//		else resJson.put("result", "NOK");
//		out.print(resJson);
//	}
//}
