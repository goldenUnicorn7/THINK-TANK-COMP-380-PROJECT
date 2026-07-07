package backend.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import backend.db.DBConnection;
import backend.model.Cart;

public class cartDAO {

    public boolean addToCart(Cart cart) {
        String sql = """
                INSERT INTO Cart
                (UserID, CarID, Return_Date, Pickup_Date, estimated_price)
                VALUES (?, ?, ?, ?, ?)
                """;

        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setInt(1, cart.getUserId());
            stmt.setInt(2, cart.getCarId());

            stmt.setDate(3, Date.valueOf(cart.getReturnDate().toLocalDate()));
            stmt.setDate(4, Date.valueOf(cart.getPickupDate().toLocalDate()));

            stmt.setBigDecimal(5, cart.getEstimatedPrice());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public List<Cart> getCartByUserID(int userID) {
        List<Cart> cartList = new ArrayList<>();

        String sql = """
                SELECT CartID, UserID, CarID, Return_Date, Pickup_Date, estimated_price
                FROM Cart
                WHERE UserID = ?
                ORDER BY CartID DESC
                """;

        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setInt(1, userID);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Cart cart = new Cart();

                    cart.setCartId(rs.getInt("CartID"));
                    cart.setUserId(rs.getInt("UserID"));
                    cart.setCarId(rs.getInt("CarID"));

                    cart.setReturnDate(
                            rs.getDate("Return_Date").toLocalDate().atStartOfDay()
                    );

                    cart.setPickupDate(
                            rs.getDate("Pickup_Date").toLocalDate().atStartOfDay()
                    );

                    cart.setEstimatedPrice(rs.getBigDecimal("estimated_price"));

                    cartList.add(cart);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cartList;
    }

    public boolean removeFromCart(int cartID) {
        String sql = "DELETE FROM Cart WHERE CartID = ?";

        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setInt(1, cartID);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean clearCartByUserID(int userID) {
        String sql = "DELETE FROM Cart WHERE UserID = ?";

        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setInt(1, userID);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean cartItemExists(int userID, int carID) {
        String sql = """
                SELECT CartID
                FROM Cart
                WHERE UserID = ? AND CarID = ?
                """;

        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setInt(1, userID);
            stmt.setInt(2, carID);

            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public Cart getCartByID(int cartID) {
        String sql = """
                SELECT CartID, UserID, CarID, Return_Date, Pickup_Date, estimated_price
                FROM Cart
                WHERE CartID = ?
                """;

        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setInt(1, cartID);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Cart cart = new Cart();

                    cart.setCartId(rs.getInt("CartID"));
                    cart.setUserId(rs.getInt("UserID"));
                    cart.setCarId(rs.getInt("CarID"));

                    cart.setReturnDate(
                            rs.getDate("Return_Date").toLocalDate().atStartOfDay()
                    );

                    cart.setPickupDate(
                            rs.getDate("Pickup_Date").toLocalDate().atStartOfDay()
                    );

                    cart.setEstimatedPrice(rs.getBigDecimal("estimated_price"));

                    return cart;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}