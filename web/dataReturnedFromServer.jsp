<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<meta name="viewport" content="width=device-width, initial-scale=1.0">


<html>
<head>
    <title>Title</title>
</head>
<body>
    <table>
        <tr>
            <th align="left">Country</th>
            <th align="left">Confirmed</th>
            <th align="left">Recovered</th>
            <th align="left">Critical</th>
            <th align="left">Deaths</th>
        </tr>
        <c:forEach items="${result}"  var="element" >
            <tr>
                <td align="left">${element.get("country")}</td>
                <td bgcolor="#b22222" align="left">${element.get("confirmed")}</td>
                <td bgcolor="green" align="left">${element.get("recovered")}</td>
                <td bgcolor="#ffd700" align="left">${element.get("critical")}</td>
                <td bgcolor="#808080" align="left">${element.get("deaths")}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
