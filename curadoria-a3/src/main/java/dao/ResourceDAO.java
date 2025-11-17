package dao;

import app.DB;
import model.Resource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ResourceDAO {
    public void insert(Resource r) throws SQLException {
        String sql = "INSERT INTO resources (user_id, title, author, category) VALUES (?,?,?,?)";
        try (Connection conn = DB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, r.getUserId());
            ps.setString(2, r.getTitle());
            ps.setString(3, r.getAuthor());
            ps.setString(4, r.getCategory());
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) r.setId(keys.getInt(1));
            }
        }
    }

    public List<Resource> listByUserOrderedByTitle(int userId) throws SQLException {
        List<Resource> list = new ArrayList<>();
        String sql = "SELECT * FROM resources WHERE user_id = ? ORDER BY title ASC";
        try (Connection conn = DB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Resource r = new Resource();
                    r.setId(rs.getInt("id"));
                    r.setUserId(rs.getInt("user_id"));
                    r.setTitle(rs.getString("title"));
                    r.setAuthor(rs.getString("author"));
                    r.setCategory(rs.getString("category"));
                    r.setCreatedAt(rs.getTimestamp("created_at"));
                    list.add(r);
                }
            }
        }
        return list;
    }
}
