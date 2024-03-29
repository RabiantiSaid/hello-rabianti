package technical.doc;

import technical.model.*;
import java.util.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class ProductDoc {
    private Connection con;
    private  String query;
    private PreparedStatement pst;
    private ResultSet rs;

    public ProductDoc(Connection con) {
        super();
        this.con = con;
    }

    public List<Product> getAllProducts() {
       List<Product> products = new ArrayList<>();

       try {
           query = "select * from products";
           pst = this.con.prepareStatement(query);
           rs = pst.executeQuery();

//           check if there is any variable
           while (rs.next()) {
               Product row = new Product();
               row.setId(rs.getInt("id"));
               row.setName(rs.getString("name"));
               row.setCategory(rs.getString("category"));
               row.setPrice(rs.getDouble("price"));
               row.setImage(rs.getString("image"));

               products.add(row);
           }
       }catch (Exception e) {
           e.printStackTrace();
       }
        return products;
    }

    public List<Cart> getCartProducts(ArrayList<Cart> cartList) {
        List<Cart> products = new ArrayList<Cart>();

        try {
            if (cartList.size()>0) {
                for (Cart item:cartList) {
                    query = "select * from products where id=?";
                    pst = this.con.prepareStatement(query);
                    pst.setInt(1,item.getId());
                    rs = pst.executeQuery();
                    while(rs.next()) {
                        Cart row = new Cart();
                        row.setId(rs.getInt("id"));
                        row.setName(rs.getString("name"));
                        row.setCategory(rs.getString("category"));
                        row.setPrice(rs.getDouble("price")*item.getQuantity());
                        row.setQuantity(item.getQuantity());
                        products.add(row);
                    }
                }
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return products;
    }
}
