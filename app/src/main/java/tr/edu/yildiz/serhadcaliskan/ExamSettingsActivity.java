package tr.edu.yildiz.serhadcaliskan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.slider.Slider;

public class ExamSettingsActivity extends AppCompatActivity {

    private Slider difficultySlider;
    private Slider pointSlider;
    private Slider durationSlider;
    private Button saveButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_settings);

        SharedPreferences settings = getSharedPreferences("exam_settings",
                Context.MODE_PRIVATE);
        int difficulty = settings.getInt("difficulty", 5);
        int point= settings.getInt("point",10);
        int duration=settings.getInt("duration",15);

        initializeComponents(difficulty,point,duration);
        initializeListeners();
    }

    private void initializeComponents(int difficulty,int point,int duration){

        difficultySlider=findViewById(R.id.difficultySlider);
        pointSlider=findViewById(R.id.pointSlider);
        durationSlider=findViewById(R.id.durationSlider);

        difficultySlider.setValue(difficulty);
        pointSlider.setValue(point);
        durationSlider.setValue(duration);

        saveButton=findViewById(R.id.savePreferencesButton);
    }

    private void initializeListeners(){

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveSettings();
                Toast.makeText(getApplicationContext(), "Saved Successfully", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void saveSettings(){
        SharedPreferences settings = getSharedPreferences("exam_settings",
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt("difficulty", (int) difficultySlider.getValue());
        editor.putInt("point",(int)pointSlider.getValue());
        editor.putInt("duration",(int)durationSlider.getValue());
        editor.commit();

    }

}