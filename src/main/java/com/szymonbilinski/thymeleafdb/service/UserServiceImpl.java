package com.szymonbilinski.thymeleafdb.service;

import com.szymonbilinski.thymeleafdb.domain.entity.User;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.time.Year;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    List<User> userList;
    final JdbcTemplate jdbcTemplate;

    public UserServiceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<User> getAllUsers() {
        userList = jdbcTemplate.query("SELECT id_user,name,surname,phone_number,email,password,balance FROM wypozyczalnia.rent_user WHERE id_user < 500", new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet resultSet, int i) throws SQLException {
                User user = new User();

                user.setId(resultSet.getLong("id_user"));
                user.setFirstname(resultSet.getString("name"));
                user.setLastname(resultSet.getString("surname"));
                user.setPhoneNumber(resultSet.getString("phone_number"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getObject("password"));
                user.setBalance(resultSet.getDouble("balance"));

                return user;
            }
        });
        return userList;
    }

    @Override
    public User getUserById(Long id) {
        List<User> user = jdbcTemplate.query("SELECT name,surname,phone_number,email,password,balance FROM wypozyczalnia.rent_user WHERE id_user=?", new Object[]{id}, new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet resultSet, int i) throws SQLException {
                User user = new User();

                user.setFirstname(resultSet.getString("name"));
                user.setLastname(resultSet.getString("surname"));
                user.setPhoneNumber(resultSet.getString("phone_number"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getObject("password"));
                user.setBalance(resultSet.getDouble("balance"));

                return user;
            }
        });
        return user.get(0);
    }

    @Override
    public void editUser(User user,long userId) {
        String SQL = "update wypozyczalnia.rent_user set name=?, surname=?, phone_number=?, email=?, password=?, balance=? WHERE id_user=?";
        jdbcTemplate.update(SQL,user.getFirstname(),user.getLastname(),user.getPhoneNumber(),user.getEmail(),user.getPassword(),user.getBalance(),userId);
        System.out.println("Updated Record with ID = " + userId);
    }

    @Override
    public void deleteUser(long userId) {
        String SQL1 = "delete from wypozyczalnia.rent_user where id_user=?";
        jdbcTemplate.update(SQL1,userId);
        System.out.println("Deleted record with ID = "+userId);
    }

    @Override
    public void addUser(User user) throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/rentbike";
        Connection conn = DriverManager.getConnection(url, "postgres","zaq1@WSX");
        Statement statement = conn.createStatement();
        String query = "SELECT id_user FROM wypozyczalnia.rent_user ORDER BY id_user DESC LIMIT 1";
        ResultSet rs = statement.executeQuery(query);
        rs.next();
        int id = rs.getInt(1);
        String SQL2 = "insert into wypozyczalnia.rent_user(id_user,name,surname,phone_number,email,password,balance) values (?,?,?,?,?,?,?)";
        jdbcTemplate.update(SQL2,id+1,user.getFirstname(),user.getLastname(),user.getPhoneNumber(),user.getEmail(),user.getPassword(),user.getBalance());
        System.out.println("Add new user");
    }
}
