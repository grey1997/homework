<%@ taglib uri="/WEB-INF/common-tags.tld" prefix="common" %>

<%
    String name = "Grey";
    String message = "Your request had been processed!";
    pageContext.setAttribute("name",name);
    pageContext.setAttribute("message",message);
%>
<common:commonTitle name="name" message="message" />&nbsp;

<html>
<body>
<h2>Hello World!</h2>
</body>
</html>