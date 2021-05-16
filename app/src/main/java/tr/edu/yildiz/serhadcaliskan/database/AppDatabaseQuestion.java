package tr.edu.yildiz.serhadcaliskan.database;


import androidx.room.Database;
import androidx.room.RoomDatabase;

import tr.edu.yildiz.serhadcaliskan.Question;


@Database(entities = {Question.class}, version = 1)
public abstract class AppDatabaseQuestion extends RoomDatabase {

    public abstract QuestionDao questionDao();


}
