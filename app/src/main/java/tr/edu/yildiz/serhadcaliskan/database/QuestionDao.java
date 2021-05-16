package tr.edu.yildiz.serhadcaliskan.database;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import tr.edu.yildiz.serhadcaliskan.Question;

@Dao
public interface QuestionDao {
    @Query("SELECT * FROM question")
    List<Question> getAll();

    @Query("SELECT * FROM question WHERE owner_of_the_question LIKE :email")
    List<Question> getQuestionsOfUser(String email);

    @Insert
    void insertQuestion(Question question);


    @Delete
    void deleteQuestion(Question question);

    @Update
    void updateQuestion(Question question);

    @Query("SELECT * FROM question where id like:id")
    Question findQuestionWithId(int id);
}
