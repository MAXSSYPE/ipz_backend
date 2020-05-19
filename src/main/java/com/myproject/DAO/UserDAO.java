package com.myproject.DAO;

import com.myproject.Units.User;
import com.myproject.WebConfig;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Dudka Maxym
 * @version 12.0.2
 * Class of user dao
 */

public class UserDAO {

    final JdbcTemplate jdbcTemplate = new JdbcTemplate(WebConfig.posgresqlDataSource());

    /**
     * @param id
     * @return
     * @throws SQLException
     */
    public User getById(int id) throws SQLException {
        return mapRow(jdbcTemplate.queryForObject(
                "SELECT id, name, password FROM users WHERE id=?", ResultSet.class));
    }

    /**
     * @return
     */
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


    /**
     * @param user
     * @return
     */
    public int create(User user) {
        return jdbcTemplate.update(
                "INSERT INTO users VALUES (?, ?, ?)", user.getId(), user.getName(), user.getPassword());
    }

    /**
     * @param user
     * @return
     * @throws SQLException
     */
    public boolean add(User user) throws SQLException {
        if (getById(user.getId()) == null) {
            create(user);
        } else {
            update(user);
        }
        return true;
    }

    /**
     * @param companies
     * @return
     * @throws SQLException
     */
    public boolean addAll(List<User> companies) throws SQLException {
        for (User User : companies) {
            add(User);
        }
        return true;
    }

    /**
     * @param user
     * @return
     */
    public int update(User user) {
        return jdbcTemplate.update(
                "UPDATE users SET name = ? WHERE id = ?", user.getId(), user.getName());
    }

    /**
     * @param user
     * @return
     */
    public int delete(User user) {
        return jdbcTemplate.update(
                "DELETE FROM users WHERE id = ?", user.getId());
    }


    /**
     * @param UserName
     * @return
     * @throws SQLException
     */
    public User getByName(String UserName) throws SQLException {
        return mapRow(jdbcTemplate.queryForObject(
                "SELECT id, name, password FROM users WHERE name=?", ResultSet.class));
    }

    /**
     * @param rs
     * @return
     * @throws SQLException
     */
    public User mapRow(ResultSet rs) throws SQLException {
        User User = new User();

        User.setId(rs.getInt("id"));
        User.setName(rs.getString("name"));
        User.setPassword(rs.getString("password"));
        return User;
    }
}
