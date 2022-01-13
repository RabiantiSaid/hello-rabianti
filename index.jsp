<%@ page import="technical.connection.DbCon" %>
<%@ page import="technical.model.*" %>
<%@ page import="technical.doc.ProductDoc" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.*" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% User auth = (User) request.getSession().getAttribute("auth");
if (auth !=null) {
    request.setAttribute("auth",auth);
}

// create inistantion of product
ProductDoc pd = new ProductDoc(DbCon.getConnection());
List<Product> products = pd.getAllProducts();


    ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
    if(cart_list != null) {
        request.setAttribute("cart_list", cart_list);

    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Welcome to Shopping Cart!</title>
    <%@include file="includes/head.jsp" %>
</head>
<body>
    <%@include file="includes/navbar.jsp" %>

    <div class="container">
        <div class="card-header my-3">All Products</div>
        <div class="row">
            <%
            if (!products.isEmpty()) {
                for (Product p:products) {%>
                    <div class="col-md-3 my-3">
                    <div class="card w-100" style="width: 18rem;">
                        <img class="card-img-top" src="product-images/<%= p.getImage()  %>" alt="Card image cap">
                        <div class="card-body">
                            <h5 class="card-title"><%= p.getName() %></h5>
                            <h6 class="price"><%= p.getPrice() %></h6>
                            <h6 class="category"><%= p.getCategory()%></h6>
                            <div class="mt-3 d-flex justify-content-between">
                                <a href="AddToCartServlet?id=<%= p.getId()%>" class="btn btn-dark">Add to Cart</a>
                                <a href="#" class="btn btn-primary">Buy Now</a>
                            </div>
                        </div>
                    </div>
                    </div>
                <%  }
            }
            %>

        </div>
    </div>

    <%@include file="includes/footer.jsp" %>
</body>
</html>