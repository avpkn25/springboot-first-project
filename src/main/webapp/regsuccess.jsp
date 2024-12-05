<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registration Success</title>
    
</head>
<body>
    <%@include file="mainnavbar.jsp" %> <!-- Include the navbar -->
    <div class="success-container">
        <p class="success-message"><c:out value="${message}"></c:out></p>
        <br><br>
        <a class="login-link" href="emplogin">Login Here</a>
    </div>
</body>
</html>
