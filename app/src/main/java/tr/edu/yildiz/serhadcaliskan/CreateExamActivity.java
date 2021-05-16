package tr.edu.yildiz.serhadcaliskan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.List;

import tr.edu.yildiz.serhadcaliskan.database.AppDatabaseQuestion;

public class CreateExamActivity extends AppCompatActivity {

    private Button createAndShareButton;
    private CreateExamAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_exam);
        RecyclerView questionsRecyclerView=findViewById(R.id.examQuestionsRecyclerView);

        AppDatabaseQuestion db= Room.databaseBuilder(getApplicationContext(),
                AppDatabaseQuestion.class, "question").allowMainThreadQueries().build();

        List<Question> questions=db.questionDao().getQuestionsOfUser(LoggedInUser.getInstance().getUser().email);

        Question[] questionsArray=new Question[questions.size()];
        questionsArray=questions.toArray(questionsArray);
        adapter = new CreateExamAdapter(questionsArray);

        questionsRecyclerView.setAdapter(adapter);
        // Set layout manager to position the items
        questionsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        initializeComponents();
        initializeListeners();
    }

    private void initializeComponents(){

        createAndShareButton=findViewById(R.id.createAndSendButton);
    }
    private void initializeListeners(){

        createAndShareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.getSelectedQuestions();
            }
        });
    }

}