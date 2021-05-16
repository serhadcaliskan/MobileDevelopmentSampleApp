package tr.edu.yildiz.serhadcaliskan.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import tr.edu.yildiz.serhadcaliskan.User;

@Dao
public interface UserDao {
    @Query("SELECT * FROM user")
    List<User> getAll();

    @Query("SELECT * FROM user WHERE email LIKE :email")
    User findByMail(String email);

    @Insert
    void insertUser(User user);

    @Delete
    void deleteUser(User user);
}