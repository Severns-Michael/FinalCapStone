package com.techelevator.dao;

import java.security.Principal;
import java.util.*;

import com.techelevator.exception.DaoException;
import com.techelevator.model.*;
import org.springframework.dao.DataAccessException;
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
    public User createAdmin(RegisterUserDto user) {
        User newUser = null;
        String insertUserSql = "INSERT INTO users (username, password_hash, role) values (LOWER(TRIM(?)), ?, ROLE_ADMIN) RETURNING user_id";
        String password_hash = new BCryptPasswordEncoder().encode(user.getPassword());
        try {
            int newUserId = jdbcTemplate.queryForObject(insertUserSql, int.class, user.getUsername(), password_hash);
            newUser = getUserById(newUserId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return newUser;
    }

    @Override
    public void promoteUser(User user) {
        String promoteSql="update users set role='ROLE_ADMIN' where user_id=?";
        try {
            jdbcTemplate.update(promoteSql, user.getId());
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
    }

    @Override
    public void demoteUser(User user) {
        String demoteSql="update users set role='ROLE_USER' where user_id=?";
        try {
            jdbcTemplate.update(demoteSql,user.getId());
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
    }

    @Override
    public void deleteUser(User user) {
        int userId=user.getId();
        String delSql="delete from users_trait_no WHERE user_id=?; " +
                "delete from users_trait_yes where user_id=?; " +
                "delete from user_swipe_breeds where user_id=?; " +
                "delete from users where user_id=?;";
        try {
            jdbcTemplate.update(delSql,userId,userId,userId,userId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
    }


    @Override
    public List<Trait> getYesTraits(int userId) {
        List<Trait> yesTraits = new ArrayList<>();
        String sql = "select trait.trait_id,trait.trait_name " +
                "from users_trait_yes " +
                "inner join trait ON trait.trait_id = users_trait_yes.trait_id " +
                "where users_trait_yes.user_id=?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
        while (results.next()) {
            Trait temp = new Trait();
            temp.setTraitId(results.getInt("trait_id"));
            temp.setTraitName(results.getString("trait_name"));
            yesTraits.add(temp);
        }
        return yesTraits;
    }

    @Override
    public List<Trait> getNoTraits(int userId) {
        List<Trait> noTraits = new ArrayList<>();
        String sql = "select trait.trait_id,trait.trait_name " +
                "from users_trait_no " +
                "inner join trait ON trait.trait_id = users_trait_no.trait_id " +
                "where users_trait_no.user_id=?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
        while (results.next()) {
            Trait temp = new Trait();
            temp.setTraitId(results.getInt("trait_id"));
            temp.setTraitName(results.getString("trait_name"));
            noTraits.add(temp);
        }
        return noTraits;
    }

    @Override
    public List<Trait> setUserYesTraits(List<Trait> yesTraits, Principal principal) throws DaoException {
        String sqlDelete = "delete from users_trait_yes where user_id=?";
        String sqlInsert = "insert into users_trait_yes(user_id,trait_id) values (?,?)";
        try {
            jdbcTemplate.update(sqlDelete, getUserByUsername(principal.getName()).getId());
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to DB", e);
        }
        for (Trait t : yesTraits) {
            try {
                jdbcTemplate.update(sqlInsert, getUserByUsername(principal.getName()).getId(), t.getTraitId());
            } catch (CannotGetJdbcConnectionException e) {
                throw new DaoException("Unable to connect to DB", e);
            }
        }
        return getUserByUsername(principal.getName()).getYesTraits();
    }

    @Override
    public List<Trait> setUserNoTraits(List<Trait> noTraits, Principal principal) throws DaoException {
        String sqlDelete = "delete from users_trait_no where user_id=?";
        String sqlInsert = "insert into users_trait_no(user_id,trait_id) values (?,?)";
        try {
            jdbcTemplate.update(sqlDelete, getUserByUsername(principal.getName()).getId());
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to DB", e);
        }
        for (Trait t : noTraits) {
            try {
                jdbcTemplate.update(sqlInsert, getUserByUsername(principal.getName()).getId(), t.getTraitId());
            } catch (CannotGetJdbcConnectionException e) {
                throw new DaoException("Unable to connect to DB", e);
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
                swipe.setSwipeYes(results.getBoolean("is_yes"));
                swiped.add(swipe);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return swiped;

    }

    @Override
    public Swiped addSwiped(Swiped swiped) throws DaoException {
        String sql = "insert into user_swipe_breeds (user_id, breed_id, img, is_yes) values (?,?,?,?)";
        try {
            jdbcTemplate.update(sql, swiped.getUserId(), swiped.getBreedId(), swiped.getImg(), swiped.getSwipeYes());
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return swiped;

    }

    @Override
    public List<Breed> getBreedUserHasntSwiped(int userId) throws DaoException {
        List<Breed> breeds = new ArrayList<>();
        String sql = "SELECT positive_breed.breed_id, breed.breed_name, breed.sub_breed, breed.official_name " +
                "FROM " +
                "( " +
                "    SELECT DISTINCT bt.breed_id " +
                "    FROM breed_trait AS bt " +
                "    left JOIN users_trait_yes AS user_yes ON bt.trait_id = user_yes.trait_id " +
                "    LEFT OUTER JOIN ( " +
                "        SELECT bt.trait_id, SUM(CASE WHEN is_yes THEN 5 ELSE -5 END) AS trait_count_adjusted " +
                "        FROM user_swipe_breeds " +
                "        INNER JOIN public.breed_trait bt ON user_swipe_breeds.breed_id = bt.breed_id " +
                "        where user_id=? " +
                "        GROUP BY bt.trait_id " +
                "        HAVING SUM(CASE WHEN is_yes THEN 5 ELSE -15 END) > -5 " +
                "    ) AS table_one ON bt.trait_id = table_one.trait_id " +
                "        where (user_yes.user_id=? or user_yes.user_id is null) " +
                ") AS positive_breed " +
                "LEFT JOIN " +
                "( " +
                "    SELECT bt.breed_id " +
                "    FROM breed_trait AS bt " +
//                "    INNER JOIN users_trait_no AS user_no ON bt.trait_id = user_no.trait_id " +
                "    RIGHT JOIN ( " +
                "        SELECT bt.trait_id, SUM(CASE WHEN is_yes THEN 5 ELSE -5 END) AS trait_count_adjusted " +
                "        FROM user_swipe_breeds " +
                "        INNER JOIN public.breed_trait bt ON user_swipe_breeds.breed_id = bt.breed_id " +
                "        where user_id=? " +
                "        GROUP BY bt.trait_id " +
                "        HAVING SUM(CASE WHEN is_yes THEN 5 ELSE -15 END) <= -5 " +
                "    ) AS table_one ON bt.trait_id = table_one.trait_id " +
                "    LEFT JOIN users_trait_no as user_no on table_one.trait_id = user_no.trait_id " +
                ") AS negative_breed ON positive_breed.breed_id = negative_breed.breed_id " +
                "INNER JOIN breed ON breed.breed_id = positive_breed.breed_id " +
                "WHERE negative_breed.breed_id IS NULL ";


        try {
            SqlRowSet rs = jdbcTemplate.queryForRowSet(sql, userId,userId,userId);
            while (rs.next()) {
                Breed breed = new Breed();
                breed.setBreedId(rs.getInt("breed_id"));
                breed.setBreedName(rs.getString("breed_name"));
                breed.setSubBreed(rs.getString("sub_breed"));
                breed.setOfficialName(rs.getString("official_name"));
                breeds.add(breed);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        Collections.shuffle(breeds);
        return breeds;
    }

    @Override
    public List<Dog> getPotentialDogs(int userId) throws DaoException {
        List<Dog> dogs = new ArrayList<>();
        String sql = "Select positive_breed.breed_id, adopt.dog_name, adopt.age, adopt.size, adopt.img, adopt.agency_id, adopt.dog_id, adopt.gender " +
                "FROM " +
                "( " +
                "    SELECT DISTINCT bt.breed_id " +
                "    FROM breed_trait AS bt " +
                "    left JOIN users_trait_yes AS user_yes ON bt.trait_id = user_yes.trait_id " +
                "    LEFT OUTER JOIN ( " +
                "        SELECT bt.trait_id, SUM(CASE WHEN is_yes THEN 5 ELSE -5 END) AS trait_count_adjusted " +
                "        FROM user_swipe_breeds " +
                "        INNER JOIN public.breed_trait bt ON user_swipe_breeds.breed_id = bt.breed_id " +
                "        where user_id=? " +
                "        GROUP BY bt.trait_id " +
                "        HAVING SUM(CASE WHEN is_yes THEN 5 ELSE -15 END) > -5 " +
                "    ) AS table_one ON bt.trait_id = table_one.trait_id " +
                "        where (user_yes.user_id=? or user_yes.user_id is null) " +
                ") AS positive_breed " +
                "LEFT JOIN " +
                "( " +
                "    SELECT bt.breed_id " +
                "    FROM breed_trait AS bt " +
//                "    INNER JOIN users_trait_no AS user_no ON bt.trait_id = user_no.trait_id " + //remove inner join
                "    RIGHT JOIN ( " + //change to right join NOT RIGHT OUTER
                "        SELECT bt.trait_id, SUM(CASE WHEN is_yes THEN 5 ELSE -5 END) AS trait_count_adjusted " +
                "        FROM user_swipe_breeds " +
                "        INNER JOIN public.breed_trait bt ON user_swipe_breeds.breed_id = bt.breed_id " +
                "        where user_id=? " +
                "        GROUP BY bt.trait_id " +
                "        HAVING SUM(CASE WHEN is_yes THEN 5 ELSE -15 END) <= -5 " +
                "    ) AS table_one ON table_one.trait_id = bt.trait_id " + //move inner join below this line as LEFT JOIN on table_one to bt
                "    LEFT JOIN users_trait_no as user_no on table_one.trait_id = user_no.trait_id " +
                ") AS negative_breed ON positive_breed.breed_id = negative_breed.breed_id " +
                "INNER JOIN breed ON breed.breed_id = positive_breed.breed_id " +
                "INNER JOIN ( " +
                "select dog_id, breed_id, dog_name, age, size, img, gender, agency_id from dog " +
                ") as adopt on breed.breed_id=adopt.breed_id " +
                "WHERE negative_breed.breed_id IS NULL";
        try {
            SqlRowSet rs = jdbcTemplate.queryForRowSet(sql, userId,userId,userId);
            while (rs.next()) {
                Dog dog = new Dog();
                dog.setDogId(rs.getInt("dog_id"));
                dog.setDogName(rs.getString("dog_name"));
                dog.setBreedId(rs.getInt("breed_id"));
                dog.setAge(rs.getInt("age"));
                dog.setSize(rs.getInt("size"));
                dog.setImg(rs.getString("img"));
                dog.setGender(rs.getInt("gender"));
                dog.setAgencyId(rs.getInt("agency_id"));
                dogs.add(dog);
            }
        }  catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        Collections.shuffle(dogs);
        return dogs;
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

}
