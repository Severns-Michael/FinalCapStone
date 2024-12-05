package com.techelevator.dao;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

import com.techelevator.exception.DaoException;
import com.techelevator.model.*;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class JdbcUserDao implements UserDao {

    private final JdbcTemplate jdbcTemplate;


    public JdbcUserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User getUserById(int userId) {
        User user = new User();
//        String sql = "SELECT user_id, username, password_hash, role FROM users WHERE user_id = ?";
//        try {
//            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
//            if (results.next()) {
//                user = mapRowToUser(results);
//            }
//        } catch (CannotGetJdbcConnectionException e) {
//            throw new DaoException("Unable to connect to server or database", e);
//        }
        String sql = "SELECT user_id, username, password_hash, role FROM users where user_id=?";
        String yesSql="select distinct users.user_id, users_trait_yes.trait_id as yes_trait_id, trait.trait_name as yes_trait_name  from users  full join users_trait_yes ON users_trait_yes.user_id = users.user_id full join trait ON trait.trait_id = users_trait_yes.trait_id  where users.user_id=?";
        String noSql="select distinct users.user_id, users_trait_no.trait_id as no_trait_id, trait.trait_name as no_trait_name  from users  full join users_trait_no ON users_trait_no.user_id = users.user_id full join trait ON trait.trait_id = users_trait_no.trait_id  where users.user_id=?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql,userId);
            while (results.next()) {
                user = mapRowToUser(results);
                SqlRowSet yesResults= jdbcTemplate.queryForRowSet(yesSql,user.getId());
                SqlRowSet noResults=jdbcTemplate.queryForRowSet(noSql,user.getId());
                List<Trait> yesTraits=new ArrayList<>();
                List<Trait> noTraits=new ArrayList<>();
                while(yesResults.next()){
                    Trait yesTrait=new Trait(yesResults.getInt("yes_trait_id"),yesResults.getString("yes_trait_name"));
                    yesTraits.add(yesTrait);
                }
                while(noResults.next()){
                    Trait noTrait=new Trait(noResults.getInt("no_trait_id"),noResults.getString("no_trait_name"));
                    noTraits.add(noTrait);
                }
                user.setYesTraits(yesTraits);
                user.setNoTraits(noTraits);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return user;
    }

    @Override
    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT user_id, username, password_hash, role FROM users";
        String yesSql="select distinct users.user_id, users_trait_yes.trait_id as yes_trait_id, trait.trait_name as yes_trait_name  from users  full join users_trait_yes ON users_trait_yes.user_id = users.user_id full join trait ON trait.trait_id = users_trait_yes.trait_id  where users.user_id=?";
        String noSql="select distinct users.user_id, users_trait_no.trait_id as no_trait_id, trait.trait_name as no_trait_name  from users  full join users_trait_no ON users_trait_no.user_id = users.user_id full join trait ON trait.trait_id = users_trait_no.trait_id  where users.user_id=?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                User user = mapRowToUser(results);
                SqlRowSet yesResults= jdbcTemplate.queryForRowSet(yesSql,user.getId());
                SqlRowSet noResults=jdbcTemplate.queryForRowSet(noSql,user.getId());
                List<Trait> yesTraits=new ArrayList<>();
                List<Trait> noTraits=new ArrayList<>();
                while(yesResults.next()){
                    Trait yesTrait=new Trait(yesResults.getInt("yes_trait_id"),yesResults.getString("yes_trait_name"));
                    yesTraits.add(yesTrait);
                }
                while(noResults.next()){
                    Trait noTrait=new Trait(noResults.getInt("no_trait_id"),noResults.getString("no_trait_name"));
                    noTraits.add(noTrait);
                }
                user.setYesTraits(yesTraits);
                user.setNoTraits(noTraits);
                users.add(user);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return users;
    }

    @Override
    public User getUserByUsername(String username) {
        if (username == null) throw new IllegalArgumentException("Username cannot be null");
        User user = null;
        String sql = "SELECT user_id, username, password_hash, role FROM users WHERE username = LOWER(TRIM(?));";
        try {
            SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, username);
            if (rowSet.next()) {
                user = mapRowToUser(rowSet);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return user;
    }

    @Override
    public User createUser(RegisterUserDto user) {
        User newUser = null;
        String insertUserSql = "INSERT INTO users (username, password_hash, role) values (LOWER(TRIM(?)), ?, ?) RETURNING user_id";
        String password_hash = new BCryptPasswordEncoder().encode(user.getPassword());
        String ssRole = user.getRole().toUpperCase().startsWith("ROLE_") ? user.getRole().toUpperCase() : "ROLE_" + user.getRole().toUpperCase();
        try {
            int newUserId = jdbcTemplate.queryForObject(insertUserSql, int.class, user.getUsername(), password_hash, ssRole);
            newUser = getUserById(newUserId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return newUser;
    }

    @Override
    public List<Trait> getYesTraits(int userId) {
        List<Trait> yesTraits = new ArrayList<>();
        String sql="select trait.trait_id,trait.trait_name " +
                "from users_trait_yes " +
                "inner join trait ON trait.trait_id = users_trait_yes.trait_id " +
                "where users_trait_yes.user_id=?";
        SqlRowSet results=jdbcTemplate.queryForRowSet(sql, userId);
        while(results.next()){
            Trait temp=new Trait();
            temp.setTraitId(results.getInt("trait_id"));
            temp.setTraitName(results.getString("trait_name"));
            yesTraits.add(temp);
        }
        return yesTraits;
    }

    @Override
    public List<Trait> getNoTraits(int userId) {
        List<Trait> noTraits = new ArrayList<>();
        String sql="select trait.trait_id,trait.trait_name " +
                "from users_trait_no " +
                "inner join trait ON trait.trait_id = users_trait_no.trait_id " +
                "where users_trait_no.user_id=?";
        SqlRowSet results=jdbcTemplate.queryForRowSet(sql, userId);
        while(results.next()){
            Trait temp=new Trait();
            temp.setTraitId(results.getInt("trait_id"));
            temp.setTraitName(results.getString("trait_name"));
            noTraits.add(temp);
        }
        return noTraits;
    }

    @Override
    public List<Trait> setUserYesTraits(List<Trait> yesTraits, Principal principal) throws DaoException {
        String sqlDelete="delete from users_trait_yes where user_id=?";
        String sqlInsert="insert into users_trait_yes(user_id,trait_id) values (?,?)";
        try {
            jdbcTemplate.update(sqlDelete,getUserByUsername(principal.getName()).getId());
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to DB",e);
        }
        for(Trait t:yesTraits){
            try {
                jdbcTemplate.update(sqlInsert,getUserByUsername(principal.getName()).getId(),t.getTraitId());
            } catch (CannotGetJdbcConnectionException e) {
                throw new DaoException("Unable to connect to DB",e);
            }
        }
        return getUserByUsername(principal.getName()).getYesTraits();
    }

    @Override
    public List<Trait> setUserNoTraits(List<Trait> noTraits, Principal principal) throws DaoException {
        String sqlDelete="delete from users_trait_no where user_id=?";
        String sqlInsert="insert into users_trait_no(user_id,trait_id) values (?,?)";
        try {
            jdbcTemplate.update(sqlDelete,getUserByUsername(principal.getName()).getId());
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to DB",e);
        }
        for(Trait t:noTraits){
            try {
                jdbcTemplate.update(sqlInsert,getUserByUsername(principal.getName()).getId(),t.getTraitId());
            } catch (CannotGetJdbcConnectionException e) {
                throw new DaoException("Unable to connect to DB",e);
            }
        }
        return getUserByUsername(principal.getName()).getYesTraits();
    }

    @Override
    public List<Swiped> getAllSwiped(int userId) throws DaoException {
        List<Swiped> swiped = new ArrayList<>();
        String sql = "select * from user_swipe_breeds where user_id=?";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
            while (results.next()) {
                Swiped swipe = new Swiped();
                swipe.setUserId(results.getInt("user_id"));
                swipe.setBreedId(results.getInt("breed_id"));
                swipe.setImg(results.getString("img"));
                swipe.setYes(results.getBoolean("is_yes"));
                swiped.add(swipe);
            }
        }
        catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return swiped;


    }

    @Override
    public Swiped addSwiped(Swiped swiped) throws DaoException {
        String sql = "insert into user_swipe_breeds (user_id, breed_id, img, is_yes) values (?,?,?,?)";
        try {
            jdbcTemplate.update(sql,swiped.getUserId(),swiped.getBreedId(),swiped.getImg(),swiped.isYes());
        }
        catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return swiped;

    }

    @Override
    public List<Breed> getBreedUserHasntSwiped(int userId) throws DaoException {
        List<Breed> breeds = new ArrayList<>();
        String sql = "select * from breed where breed_id not in (select breed_id from user_swipe_breeds where user_id=?)";
            return breeds;
//        try {
//
//        }
    }


    private User mapRowToUser(SqlRowSet rs) {
        User user = new User();
        user.setId(rs.getInt("user_id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password_hash"));
        user.setAuthorities(Objects.requireNonNull(rs.getString("role")));
        user.setActivated(true);
        return user;
    }




//    @Override
//    public List<Dog> getSwipedYesDogs( int id) {
//        List<Dog> swipedYesDogs = new ArrayList<>();
//        String sql = "select users.user_id, users.username, d.dog_id,dog_name,breed_id,age, size, img from users " +
//                "inner join public.users_dog_yes udy on users.user_id = udy.user_id " +
//                "inner join public.dog d on d.dog_id = udy.dog_id " +
//                "where udy.user_id = ?";
//        try {
//            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
//            while (results.next()) {
//                Dog dog = new Dog();
//                dog.setDogId(results.getInt("dog_id"));
//                dog.setDogName(results.getString("dog_name"));
//                dog.setBreedId(results.getInt("breed_id"));
//                dog.setImg(results.getString("img"));
//                dog.setAge(results.getInt("age"));
//                dog.setSize(results.getInt("size"));
//                swipedYesDogs.add(dog);
//
//
//            }
//
//        } catch (CannotGetJdbcConnectionException e) {
//            throw new DaoException("Unable to connect to server or database", e);
//        }
//        return swipedYesDogs;
//    }
//
//    @Override
//    public List<Dog> getSwipedNoDogs(int userId) {
//        List<Dog> swipedNoDogs = new ArrayList<>();
//        String sql = "select users.user_id, users.username, d.dog_id,dog_name,breed_id,age, size, img from users " +
//                "inner join public.users_dog_no udn on users.user_id = udn.user_id " +
//                "inner join public.dog d on d.dog_id = udn.dog_id " +
//                "where udn.user_id = ?";
//        try {
//            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
//            while (results.next()) {
//                Dog dog = new Dog();
//                dog.setDogId(results.getInt("dog_id"));
//                dog.setDogName(results.getString("dog_name"));
//                dog.setBreedId(results.getInt("breed_id"));
//                dog.setImg(results.getString("img"));
//                dog.setAge(results.getInt("age"));
//                dog.setSize(results.getInt("size"));
//                swipedNoDogs.add(dog);
//
//
//            }
//
//        } catch (CannotGetJdbcConnectionException e) {
//            throw new DaoException("Unable to connect to server or database", e);
//        }
//        return swipedNoDogs;
//    }
//    @Override
//    public List<Dog> setSwipedYesDogs(List<Dog> favoriteDogs, Principal principal) throws DaoException {
//        return List.of();
//    }
//
//    @Override
//    public List<Dog> setSwipedNoDogs(List<Dog> favoriteDogs, Principal principal) throws DaoException {
//        return List.of();
//    }


}
