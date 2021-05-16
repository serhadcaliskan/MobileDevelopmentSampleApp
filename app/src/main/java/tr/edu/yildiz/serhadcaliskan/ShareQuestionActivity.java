package tr.edu.yildiz.serhadcaliskan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import tr.edu.yildiz.serhadcaliskan.database.AppDatabaseQuestion;
import tr.edu.yildiz.serhadcaliskan.database.AppDatabaseUser;


public class ShareQuestionActivity extends AppCompatActivity {

    private EditText questionText;

    private Button attachFileButton;
    private ImageView attachedQuestionImageView;


    private static final int PICK_FROM_GALLERY = 101;
    private Uri imageURI = null;
    private Bitmap imageBitmap=null;
    private String imageBitmapString=null;
    private AppDatabaseQuestion db;

    private EditText choice1;

    private EditText choice2;


    private EditText choice3;

    private EditText choice4;


    private EditText choice5;


    private RadioGroup choicesRadioGroup;

    private Button createQuestionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_question);
        initializeComponents();
        defineListeners();
        db= Room.databaseBuilder(getApplicationContext(),
                AppDatabaseQuestion.class, "question").allowMainThreadQueries().build();
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_FROM_GALLERY && resultCode == RESULT_OK) {
            imageURI = data.getData();
            try {
                imageBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageURI);
                imageBitmapString=BitmapOperations.BitMapToString(imageBitmap);
                attachedQuestionImageView.setImageBitmap(imageBitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }


    public void pickImage() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        intent.putExtra("return-data", true);
        startActivityForResult(Intent.createChooser(intent, "Select a file to attach"), PICK_FROM_GALLERY);

    }

    public void initializeComponents(){


        questionText=findViewById(R.id.questionEditText);

        attachFileButton=findViewById(R.id.attachButton);
        attachedQuestionImageView=findViewById(R.id.attachedQuestionImageView);




        choice1=findViewById(R.id.choice1EditText);

        choice2=findViewById(R.id.choice2EditText);


        choice3=findViewById(R.id.choice3EditText);


        choice4=findViewById(R.id.choice4EditText);


        choice5=findViewById(R.id.choice5EditText);

        choicesRadioGroup=findViewById(R.id.choicesRadioGroup);

        createQuestionButton=findViewById(R.id.createQuestionButton);
    }
    public void defineListeners(){

        attachFileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickImage();

            }
        });
        createQuestionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(checkStatus()){
                    db.questionDao().insertQuestion(createQuestion());
                    Log.d("Checked", String.valueOf(choicesRadioGroup.getCheckedRadioButtonId()));
                }
            }
        });


    }

    public boolean checkStatus(){
        if(questionText.getText().toString().isEmpty()){

            Toast.makeText(getApplicationContext(), "Please Enter A Question Text", Toast.LENGTH_LONG).show();
            return false;

        }
        else if(!(!choice1.getText().toString().isEmpty()&&!choice2.getText().toString().isEmpty()
                &&!choice3.getText().toString().isEmpty()&&!choice4.getText().toString().isEmpty()
                &&!choice5.getText().toString().isEmpty())){


            Toast.makeText(getApplicationContext(), "Please Enter All Choices", Toast.LENGTH_LONG).show();
            return false;

        }


        else if(choicesRadioGroup.getCheckedRadioButtonId()==-1){


            Toast.makeText(getApplicationContext(), "Please Choose A Correct Answer", Toast.LENGTH_LONG).show();
            return false;

        }
        else{

            return true;

        }

    }

    Question createQuestion(){
        int trueAnswer=-1;
        int buttonId=choicesRadioGroup.getCheckedRadioButtonId();

        if(buttonId==R.id.radioButton1){

            trueAnswer=1;
        }
        else if(buttonId==R.id.radioButton2){
            trueAnswer=2;

        }
        else if(buttonId==R.id.radioButton3){
            trueAnswer=3;

        }
        else if(buttonId==R.id.radioButton4){
            trueAnswer=4;

        }
        else if (buttonId==R.id.radioButton5){
            trueAnswer=5;

        }

        return new Question((LoggedInUser.getInstance().getUser()).email,questionText.getText().toString(),
                choice1.getText().toString(),choice2.getText().toString(),choice3.getText().toString(),
                choice4.getText().toString(),choice5.getText().toString(),trueAnswer,imageBitmapString);
    }
}

