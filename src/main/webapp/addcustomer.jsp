<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
<title>Add Customer</title>
<link type="text/css" rel="stylesheet" href="css/style.css">

</head>

<body>
  <%@include file="adminnavbar.jsp" %>
  
    <h3 style="text-align: center;"><u>Add Customer</u></h3>
    <div class="form-container">

<form:form modelAttribute="customer" method="post" action="insertcustomer">

<table>

<tr>
<td><label>Name</label></td>
<td>
<form:input path="name" required="required"></form:input>
</td>
</tr>
<tr>
<td><label>Gender</label></td>
<td>
<form:radiobutton path="gender" value="Male" required="required"/>Male
<form:radiobutton path="gender" value="Female" required="required"/>Female
</td>
</tr>
<tr>
<td><label>Email ID</label></td>
<td><form:input path="email" required="required"/></td>
</tr>
<tr>
<td><label>Password</label></td>
<td><form:password path="password" required="required"/></td>
</tr>
<tr>
<td><label>Address</label></td>
<td>
<form:textarea path="address" required="required"/>
</td>
</tr>
<tr>
<td><label>Contact No</label></td>
<td><form:input path="contactno" required="required"/></td>
</tr>
<tr>
<td colspan="2" class="button-container">
<input type="submit" value="Add"/>
<input type="reset" value="Clear"/>
</td>
</tr>
</table>
</form:form>


</div>
</body>
</html>

 
