package tr.edu.yildiz.serhadcaliskan;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.telephony.PhoneNumberUtils;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import java.io.IOException;

import tr.edu.yildiz.serhadcaliskan.database.AppDatabaseUser;

public class SignUpActivity extends AppCompatActivity{

    private ImageView profilePicture;
    private ImageButton attachProfilePicture;
    private EditText name;
    private EditText surname;
    private EditText email;
    private EditText phoneNumber;
    private EditText birthday;
    private EditText passwordInitial;
    private EditText passwordAgain;
    private Button signUp;

    private static final int PICK_FROM_GALLERY = 101;
    private Uri imageURI = null;
    private Bitmap imageBitmap=null;
    private String imageBitmapString=null;

    private AppDatabaseUser db;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        initializeComponents();
        defineListeners();
        db= Room.databaseBuilder(getApplicationContext(),
                AppDatabaseUser.class, "user").allowMainThreadQueries().build();


    }

    private void initializeComponents(){
        profilePicture=findViewById(R.id.profilePictureImageView);
        attachProfilePicture=findViewById(R.id.profilePictureImageAttachButton);
        name=findViewById(R.id.editTextTextName);
        surname=findViewById(R.id.editTextTextSurname);
        email=findViewById(R.id.editTextTextEmailAddress);
        phoneNumber=findViewById(R.id.editTextPhone);
        birthday=findViewById(R.id.editTextBirthday);
        passwordInitial=findViewById(R.id.editTextTextPassword);
        passwordAgain=findViewById(R.id.editTextTextPasswordReEnter);
        signUp=findViewById(R.id.signUpButton);


    }
    private void defineListeners(){
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(checkUserData()){

                    db.userDao().insertUser(getInfo());

                }
            }
        });

        attachProfilePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                pickImage();

            }
        });

    }

    private boolean checkUserData(){



        if(!passwordInitial.getText().toString().isEmpty()&&!passwordInitial.getText().toString().equals(passwordAgain.getText().toString())){
            Toast.makeText(getApplicationContext(), "Passwords aren't matched", Toast.LENGTH_LONG).show();
            return false;
        }
        else if(!isValidEmail(email.getText().toString())){
            Toast.makeText(getApplicationContext(), "Please Enter a Valid E-Mail", Toast.LENGTH_LONG).show();
            return false;

        }
        else if(!isValidPhoneNumber(phoneNumber.getText().toString())){
            Toast.makeText(getApplicationContext(), "Please Enter a Valid Phone Number", Toast.LENGTH_LONG).show();
            return false;

        }
        else if(name.getText().toString().isEmpty()||surname.getText().toString().isEmpty()||
                birthday.getText().toString().isEmpty()){

            Toast.makeText(getApplicationContext(), "Please Enter All Information", Toast.LENGTH_LONG).show();
            return false;

        }
        else if(imageBitmapString==null){
            Toast.makeText(getApplicationContext(), "Please Select A Profile Picture", Toast.LENGTH_LONG).show();
            return false;

        }
        else if(db.userDao().findByMail(email.getText().toString())!=null){

            Toast.makeText(getApplicationContext(), "This User Already Signed", Toast.LENGTH_LONG).show();
            return false;
        }

        else{
            Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG).show();
            return true;
        }

    }


    private User getInfo(){


        return new User(name.getText().toString(),surname.getText().toString(),email.getText().toString(),
                passwordInitial.getText().toString(),phoneNumber.getText().toString(),birthday.getText().toString(),imageBitmapString);
    }


    private static boolean isValidEmail(String email) {

        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();

    }
    private static boolean isValidPhoneNumber(String phoneNumber){

        return !TextUtils.isEmpty(phoneNumber)&&PhoneNumberUtils.isGlobalPhoneNumber(phoneNumber);

    }

    public void pickImage() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        intent.putExtra("return-data", true);
        startActivityForResult(Intent.createChooser(intent, "Select a file to attach"), PICK_FROM_GALLERY);

    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_FROM_GALLERY && resultCode == RESULT_OK) {
            imageURI = data.getData();
            try {
                imageBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageURI);
                imageBitmapString=BitmapOperations.BitMapToString(imageBitmap);
                profilePicture.setImageBitmap(imageBitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }
}
