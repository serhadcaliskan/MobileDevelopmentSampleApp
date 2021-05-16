package tr.edu.yildiz.serhadcaliskan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity {

    private Button createQuestionButton;
    private Button showQuestionsButton;
    private Button createExamButton;
    private Button examSettingsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        initializeComponents();
        initializeListeners();

    }

    private void initializeComponents(){

        createQuestionButton=findViewById(R.id.createQuestionMenuButton);
        showQuestionsButton=findViewById(R.id.showQuestionsMenuButton);
        createExamButton=findViewById(R.id.createExamMenuButton);
        examSettingsButton=findViewById(R.id.examSettingsMenuButton);

    }

    private void initializeListeners(){

        createQuestionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MenuActivity.this,ShareQuestionActivity.class);

                startActivity(intent);
            }
        });

        showQuestionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MenuActivity.this,ListQuestionsActivity.class);

                startActivity(intent);
            }
        });

        createExamButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MenuActivity.this,CreateExamActivity.class);

                startActivity(intent);
            }
        });

        examSettingsButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MenuActivity.this,ExamSettingsActivity.class);

                startActivity(intent);
            }

        });


    }
}
