package tr.edu.yildiz.serhadcaliskan;

import android.graphics.Bitmap;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Question {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id;

    @ColumnInfo(name="owner_of_the_question")
    public String owner;

    @ColumnInfo(name="question")
    public String question;

    @ColumnInfo(name="answer_1")
    public String answer1;

    @ColumnInfo(name="answer_2")
    public String answer2;

    @ColumnInfo(name="answer_3")
    public String answer3;

    @ColumnInfo(name="answer_4")
    public String answer4;

    @ColumnInfo(name="answer_5")
    public String answer5;

    @ColumnInfo(name="true_answer")
    public int trueAnswer;

    @ColumnInfo(name="question_attachment")
    public String questionAttachment;

    public Question(String owner, String question, String answer1, String answer2, String answer3, String answer4, String answer5, int trueAnswer,String questionAttachment) {
        this.owner = owner;
        this.question = question;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.answer4 = answer4;
        this.answer5 = answer5;
        this.trueAnswer = trueAnswer;
        this.questionAttachment=questionAttachment;
    }
    @Ignore
    public Question(int id, String owner, String question, String answer1, String answer2, String answer3, String answer4, String answer5, int trueAnswer, String questionAttachment) {
        this.id = id;
        this.owner = owner;
        this.question = question;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.answer4 = answer4;
        this.answer5 = answer5;
        this.trueAnswer = trueAnswer;
        this.questionAttachment = questionAttachment;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", owner='" + owner + '\'' +
                ", question='" + question + '\'' +
                ", answer1='" + answer1 + '\'' +
                ", answer2='" + answer2 + '\'' +
                ", answer3='" + answer3 + '\'' +
                ", answer4='" + answer4 + '\'' +
                ", answer5='" + answer5 + '\'' +
                ", trueAnswer=" + trueAnswer +
                ", questionAttachment='" + questionAttachment + '\'' +
                '}';
    }
}
