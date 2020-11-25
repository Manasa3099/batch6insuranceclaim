
package com.insurance.claim.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.insurance.claim.dto.PolicyDetails;
import com.insurance.claim.exception.PolicyNotFoundException;
import com.insurance.claim.service.PolicyService;
import com.insurance.claim.service.PolicyServiceImpl;

@WebServlet({ "/ClaimQuestionsHandler", "/QuestionHandler" })
public class ClaimQuestionsHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ClaimQuestionsHandler() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();

		PolicyService pService = new PolicyServiceImpl();

		ServletContext conText = getServletContext();
		long policyNumber = (long) conText.getAttribute("policyNumber");
//		long claimNumber = (long) conText.getAttribute("claimNumber");

		for (int i = 1; i < 5; i++) {
			int quesId = i;
			String ans = request.getParameter(Integer.toString(i));
			PolicyDetails details = new PolicyDetails(policyNumber, quesId, ans);
			try {
				pService.insertPolicyDetails(details);
			} catch (PolicyNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		out.print("<div class='container' style='margin-top: 50px;'");
		out.print("<div class='card-header alert alert-info'>");
		out.print("<div class='card-header alert alert-info'>");
		out.print("<h2 style='color: red;'>" + "Claim Has Been Created Successfully" + "</h2>");
		out.print("</div>");
		out.print("</div>");
	}
}
