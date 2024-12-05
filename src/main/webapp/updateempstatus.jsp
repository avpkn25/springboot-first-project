<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Update Employee Status</title>
    <style>
        table {
            width: 100%;
            max-width: 1500px;
            margin: 20px auto;
            border-collapse: collapse;
            table-layout: auto;
            background-color: #fff;
        }

        table, th, td {
            border: 2px solid black;
        }

        th, td {
            padding: 10px;
            text-align: center;
            word-wrap: break-word;
        }

        th {
            background-color: black;
            color: white;
        }

        tr:nth-child(even) {
            background-color: #f2f2f2;
        }

        tr:nth-child(odd) {
            background-color: #fff;
        }

         a {
		    text-decoration: none;
		    font-size: 16px;
		    border-radius: 5px;
		    transition: background-color 0.3s ease, color 0.3s ease;
		    margin: 0 6px;
		}

        .accept {
            color: #28a745; /* White text color */
        }

        .reject {
            color: #fb4651; /* White text color */
        }

        
    </style>
</head>
<body>
    <%@include file="adminnavbar.jsp" %>
    <h3 style="text-align: center;"><u>Update Employee Status</u></h3>
    <div class="center-message">
        Total Employees = ${count}
    </div>
    <table>
        <tr>
            <th>ID</th>
            <th>NAME</th>
            <th>GENDER</th>
            <th>DATE OF BIRTH</th>
            <th>DEPARTMENT</th>
            <th>SALARY</th>
            <th>LOCATION</th>
            <th>EMAIL</th>
            <th>CONTACT</th>
            
            <th>ACTION</th>
        </tr>
        <c:forEach items="${emplist}" var="emp">
            <tr>
                <td><c:out value="${emp.id}"/></td>
                <td><c:out value="${emp.name}"/></td>
                <td><c:out value="${emp.gender}"/></td>
                <td><c:out value="${emp.dateofbirth}"/></td>
                <td><c:out value="${emp.department}"/></td>
                <td><c:out value="${emp.salary}"/></td>
                <td><c:out value="${emp.location}"/></td>
                <td><c:out value="${emp.email}"/></td>
                <td><c:out value="${emp.contact}"/></td>
                
                <td>
                    <a href='<c:url value="updatestatus?id=${emp.id}&status=Accepted"></c:url>' class="accept">
                        &#10003; Accept
                    </a>
                    <a href='<c:url value="updatestatus?id=${emp.id}&status=Rejected"></c:url>' class="reject">
                        &#10007; Reject
                    </a>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
