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
 * Class of company dao
 */

public class CompanyDAO {

    final JdbcTemplate jdbcTemplate = new JdbcTemplate(WebConfig.posgresqlDataSource());

    /**
     * @param id
     * @return
     * @throws SQLException
     */
    public Company getById(int id) throws SQLException {
        return mapRow(jdbcTemplate.queryForObject(
                "SELECT id, name FROM company WHERE id=?", ResultSet.class));
    }

    /**
     * @return
     */
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


    /**
     * @param company
     * @return
     */
    public int create(Company company) {
        return jdbcTemplate.update(
                "INSERT INTO company VALUES (?, ?)", company.getId(), company.getName());
    }

    /**
     * @param company
     * @return
     * @throws SQLException
     */
    public boolean add(Company company) throws SQLException {
        if (getById(company.getId()) == null) {
            create(company);
        } else {
            update(company);
        }
        return true;
    }

    /**
     * @param companies
     * @return
     * @throws SQLException
     */
    public boolean addAll(List<Company> companies) throws SQLException {
        for (Company company : companies) {
            add(company);
        }
        return true;
    }

    /**
     * @param company
     * @return
     */
    public int update(Company company) {
        return jdbcTemplate.update(
                "UPDATE company SET name = ? WHERE id = ?", company.getId(), company.getName());
    }

    /**
     * @param company
     * @return
     */
    public int delete(Company company) {
        return jdbcTemplate.update(
                "DELETE FROM company WHERE id = ?", company.getId());
    }


    /**
     * @param companyName
     * @return
     * @throws SQLException
     */
    public Company getByName(String companyName) throws SQLException {
        return mapRow(jdbcTemplate.queryForObject(
                "SELECT id, name FROM company WHERE name=?", ResultSet.class));
    }

    /**
     * @param rs
     * @return
     * @throws SQLException
     */
    public Company mapRow(ResultSet rs) throws SQLException {
        Company company = new Company();

        company.setId(rs.getInt("id"));
        company.setName(rs.getString("name"));
        return company;
    }
}
