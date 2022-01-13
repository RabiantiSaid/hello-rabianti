package technical.servlet;

import technical.connection.DbCon;
import technical.doc.UserDoc;
import technical.model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("login.jsp");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try(PrintWriter out = response.getWriter()) {
            String email = request.getParameter("login-email");
            String password = request.getParameter("login-password");

            try{
                UserDoc udoc = new UserDoc(DbCon.getConnection());
                User user = udoc.userLogin(email, password);

//                check if user object is null or not
                if(user != null) {
                    request.getSession().setAttribute("auth", user);
                    response.sendRedirect("index.jsp");
                }else {
                    out.print("user login failed");
                }
            }catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
