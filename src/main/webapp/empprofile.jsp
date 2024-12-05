<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@page import="com.klef.jfsd.springboot.model.Employee"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
Employee e = (Employee)session.getAttribute("employee");
if(e==null){
	response.sendRedirect("empsessionexpiry");
	return;
}
%>
<html>
<head>
<meta charset="UTF-8">
<title>Employee Profile</title>
</head>
<body>
<%@include file="empnavbar.jsp" %>
<h3 style="text-align: center;"><u>My Profile</u></h3>

<div class="card">
    <div class="card-content">
        <p><b>ID:</b> <%=e.getId() %></p>
        <p><b>Name:</b> <%=e.getName() %></p>
        <p><b>Gender:</b> <%=e.getGender() %></p>
        <p><b>Date of Birth:</b> <%=e.getDateofbirth() %></p>
        <p><b>Department:</b> <%=e.getDepartment() %></p>
        <p><b>Salary:</b> <%=e.getSalary() %></p>
        <p><b>Location:</b> <%=e.getLocation() %></p>
        <p><b>Email:</b> <%=e.getEmail() %></p>
        <p><b>Contact:</b> <%=e.getContact() %></p>
        <p><b>Status:</b> <%=e.getStatus() %></p>
    </div>
</div>

</body>
</html>