package tr.edu.yildiz.serhadcaliskan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import tr.edu.yildiz.serhadcaliskan.database.AppDatabaseUser;

public class SignInActivity extends AppCompatActivity {

    private EditText emailText;
    private EditText passwordText;
    private Button loginButton;
    private Button signupButton;



    private AppDatabaseUser db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        initializeComponents();
        defineListeners();
        db= Room.databaseBuilder(getApplicationContext(),
                AppDatabaseUser .class, "user").allowMainThreadQueries().build();

    }

    public void initializeComponents(){
        emailText = findViewById(R.id.editTextEmailAddress);
        passwordText = findViewById(R.id.editTextPassword);
        loginButton=findViewById(R.id.loginButton);
        signupButton=findViewById(R.id.signupButton);

    }


    public void defineListeners(){
        loginButton.setOnClickListener(new View.OnClickListener() {
            int loginAttempt=0;

            @Override
            public void onClick(View v) {
                String email= emailText.getText().toString();
                String password= passwordText.getText().toString();


                //db.userDao().findByMail(email);
                if(loginAttempt<=3) {
                        User currentUser=db.userDao().findByMail(email);
                        if (currentUser!=null&& currentUser.getPassword().equals(password)) {
                            LoggedInUser.getInstance().logInUser(currentUser);
                            Toast.makeText(getApplicationContext(), "Logged in succesfully", Toast.LENGTH_LONG).show();
                            loginAttempt=0;
                            Intent intent=new Intent(SignInActivity.this,MenuActivity.class);
                            startActivity(intent);


                        }else{

                            Toast.makeText(getApplicationContext(), "Wrong e-mail or password", Toast.LENGTH_LONG).show();

                        }

                    loginAttempt++;
                }
                else{
                    loginButton.setEnabled(false);
                    Toast.makeText(getApplicationContext(), "You tried to log-in more than 3 times", Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(SignInActivity.this,SignUpActivity.class);
                    startActivity(intent);
                }



            }
        });

        signupButton.setOnClickListener(new View.OnClickListener(){


            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SignInActivity.this,SignUpActivity.class);

                startActivity(intent);
            }
        });


    }

}