<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=windows-1252"/>
    <title>Request Info</title>
    <style type="text/css">
      body {
      background-color: #ffffff; 
}
    </style>
  </head>
  <body>
    <p>
      <a href="../Shibboleth.sso/Login?target=https://webservices.library.ucla.edu/catalog/headers.jsp">Login</a>
    </p>
    Headers:
    <ul>
      <c:forEach items="${headerValues}" var="h">
        <li>
          <c:out value="${h.key}"/>
          <ul>
            <c:forEach items="${h.value}" var="value">
              <li>
                <c:out value="${value}"/>
              </li>
            </c:forEach>
          </ul>
        </li>
      </c:forEach>
    </ul>
  </body>
</html>