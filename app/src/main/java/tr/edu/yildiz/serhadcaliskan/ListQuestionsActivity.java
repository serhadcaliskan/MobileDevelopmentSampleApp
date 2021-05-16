package tr.edu.yildiz.serhadcaliskan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;

import java.util.List;

import tr.edu.yildiz.serhadcaliskan.database.AppDatabaseQuestion;


public class ListQuestionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_questions);

        RecyclerView questionsRecyclerView=findViewById(R.id.listQuestionsRecyclerView);

        AppDatabaseQuestion db= Room.databaseBuilder(getApplicationContext(),
                AppDatabaseQuestion.class, "question").allowMainThreadQueries().build();

        List<Question> questions=db.questionDao().getQuestionsOfUser(LoggedInUser.getInstance().getUser().email);

        Question[] questionsArray=new Question[questions.size()];
        questionsArray=questions.toArray(questionsArray);
        ListQuestionsAdapter adapter = new ListQuestionsAdapter(questionsArray,db,ListQuestionsActivity.this);

        // Attach the adapter to the recyclerview to populate items
        questionsRecyclerView.setAdapter(adapter);
        // Set layout manager to position the items
        questionsRecyclerView.setLayoutManager(new LinearLayoutManager(this));




    }
}