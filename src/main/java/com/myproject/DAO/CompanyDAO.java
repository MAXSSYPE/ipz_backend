package com.myproject.DAO;

import com.myproject.Units.Company;
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
 */

public class CompanyDAO {

    final JdbcTemplate jdbcTemplate = new JdbcTemplate(WebConfig.posgresqlDataSource());

    public Company getById(int id) throws SQLException {
        return mapRow(jdbcTemplate.queryForObject(
                "SELECT id, name FROM company WHERE id=?", ResultSet.class));
    }

    public List<Company> getAll() {

        String sql = "SELECT * FROM company";

        List<Company> companies = new ArrayList<>();

        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);

        for (Map row : rows) {
            Company company = new Company();

            company.setId(((Integer) row.get("id")).intValue());
            company.setName((String) row.get("name"));
            companies.add(company);
        }

        return companies;
    }


    public int create(Company company) {
        return jdbcTemplate.update(
                "INSERT INTO company VALUES (?, ?)", company.getId(), company.getName());
    }

    public boolean add(Company company) throws SQLException {
        if (getById(company.getId()) == null) {
            create(company);
        } else {
            update(company);
        }
        return true;
    }

    public boolean addAll(List<Company> companies) throws SQLException {
        for (Company company : companies) {
            add(company);
        }
        return true;
    }

    public int update(Company company) {
        return jdbcTemplate.update(
                "UPDATE company SET name = ? WHERE id = ?", company.getId(), company.getName());
    }

    public int delete(Company company) {
        return jdbcTemplate.update(
                "DELETE FROM company WHERE id = ?", company.getId());
    }


    public Company getByName(String companyName) throws SQLException {
        return mapRow(jdbcTemplate.queryForObject(
                "SELECT id, name FROM company WHERE name=?", ResultSet.class));
    }

    public Company mapRow(ResultSet rs) throws SQLException {
        Company company = new Company();

        company.setId(rs.getInt("id"));
        company.setName(rs.getString("name"));
        return company;
    }
}
