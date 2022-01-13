package technical.servlet;

import technical.model.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "AddToCartServlet", value = "/AddToCartServlet")
public class AddToCartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try(PrintWriter out = response.getWriter()) {
//            create an array list and initialise
            ArrayList<Cart> cartList = new ArrayList<>();

            int id = Integer.parseInt(request.getParameter("id"));
            Cart cm = new Cart();
            cm.setId(id);
            cm.setQuantity(1);

            HttpSession session = request.getSession();
            ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");

            if(cart_list == null) {
                cartList.add(cm);
                session.setAttribute("cart-list", cartList);
                response.sendRedirect("index.jsp");
            }else{
                cartList = cart_list;
                boolean exist = false;

                for (Cart c:cartList) {
                    if (c.getId() == id) {
                        exist = true;
                        out.print("<h3> style= 'color:crimson; text-align:center'>Item already exist in Cart. <a href='cart.jsp'> Go to the cart page</a></h3>");
                    }

                    }
                if (!exist) {
                    cartList.add(cm);
                    response.sendRedirect("index.jsp");
                }
            }

            for (Cart c:cart_list) {
                out.println(c.getId());
            }
        }

    }
}
