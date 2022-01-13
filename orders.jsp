<%@ page import="technical.connection.DbCon" %>
<%@ page import="technical.model.*" %>
<%@ page import="java.util.*" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% User auth = (User) request.getSession().getAttribute("auth");
    if (auth !=null) {
        request.setAttribute("auth",auth);
    }
//Using the array list to show that there is something in the cart
    ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
    if(cart_list != null) {
        request.setAttribute("cart_list", cart_list);

    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Orders Page</title>
    <%@include file="includes/head.jsp" %>
</head>
<body>
<%@include file="includes/navbar.jsp" %>
</h1>

<%@include file="includes/footer.jsp" %>
</body>
</html>