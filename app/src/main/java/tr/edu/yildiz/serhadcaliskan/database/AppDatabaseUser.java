package tr.edu.yildiz.serhadcaliskan.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import tr.edu.yildiz.serhadcaliskan.User;


@Database(entities = {User.class}, version = 1)
public abstract class AppDatabaseUser extends RoomDatabase {
    public abstract UserDao userDao();
}