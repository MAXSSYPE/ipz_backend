package com.myproject.DAO;

import com.myproject.Units.User;
import com.myproject.WebConfig;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserDAO {

    final JdbcTemplate jdbcTemplate = new JdbcTemplate(WebConfig.posgresqlDataSource());

    public User getById(int id) throws SQLException {
        return mapRow(jdbcTemplate.queryForObject(
                "SELECT id, name, password FROM users WHERE id=?", ResultSet.class));
    }

    public List<User> getAll() {

        String sql = "SELECT * FROM users";

        List<User> users = new ArrayList<>();

        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);

        for (Map row : rows) {
            User user = new User();

            user.setId(((Integer) row.get("id")).intValue());
            user.setName((String) row.get("name"));
            user.setPassword(row.get("password").toString());
            users.add(user);
        }

        return users;
    }


    public int create(User user) {
        return jdbcTemplate.update(
                "INSERT INTO users VALUES (?, ?, ?)", user.getId(), user.getName(), user.getPassword());
    }

    public boolean add(User user) throws SQLException {
        if (getById(user.getId()) == null) {
            create(user);
        } else {
            update(user);
        }
        return true;
    }

    public boolean addAll(List<User> companies) throws SQLException {
        for (User User : companies) {
            add(User);
        }
        return true;
    }

    public int update(User user) {
        return jdbcTemplate.update(
                "UPDATE users SET name = ? WHERE id = ?", user.getId(), user.getName());
    }

    public int delete(User user) {
        return jdbcTemplate.update(
                "DELETE FROM users WHERE id = ?", user.getId());
    }


    public User getByName(String UserName) throws SQLException {
        return mapRow(jdbcTemplate.queryForObject(
                "SELECT id, name, password FROM users WHERE name=?", ResultSet.class));
    }

    public User mapRow(ResultSet rs) throws SQLException {
        User User = new User();

        User.setId(rs.getInt("id"));
        User.setName(rs.getString("name"));
        User.setPassword(rs.getString("password"));
        return User;
    }
}
