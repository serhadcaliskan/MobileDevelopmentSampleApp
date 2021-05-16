package tr.edu.yildiz.serhadcaliskan;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import tr.edu.yildiz.serhadcaliskan.database.AppDatabaseQuestion;


public class ListQuestionsAdapter extends RecyclerView.Adapter<ListQuestionsAdapter.ViewHolder> {

    private Question[] localDataSet;
    private AppDatabaseQuestion dbQuestion;
    private Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView questionText;
        private final RadioGroup choicesRadioGroup;
        private final Button updateQuestionButton;
        private Question question;
        private ImageView imageView;
        public ViewHolder(View view) {
            super(view);

            questionText = view.findViewById(R.id.questionTextExamQuestion);

            choicesRadioGroup= view.findViewById(R.id.examQuestionChoices);

            imageView=view.findViewById(R.id.attachmentImageView);
            updateQuestionButton=view.findViewById(R.id.updateQuestion);
        }

        public TextView getQuestionTextView() {
            return questionText;
        }

        public RadioGroup getChoicesRadioGroup(){
            return choicesRadioGroup;
        }

        public ImageView getImageView() {
            return imageView;
        }

        public Question getQuestion() {
            return question;
        }

        public Button getUpdateQuestionButton() {
            return updateQuestionButton;
        }

        public void setQuestion(Question question) {
            this.question = question;
        }
    }


    public ListQuestionsAdapter(Question[] questionSet, AppDatabaseQuestion db,Context con) {

        localDataSet = questionSet;
        context=con;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_show_question, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        viewHolder.setQuestion(localDataSet[position]);
        viewHolder.getQuestionTextView().setText(localDataSet[position].question);
        String[] answers={localDataSet[position].answer1,localDataSet[position].answer2,
                localDataSet[position].answer3,localDataSet[position].answer4,localDataSet[position].answer5};

        for(int i=1;i<=5;i++){

           RadioButton choice= (RadioButton) viewHolder.getChoicesRadioGroup().getChildAt(i-1);

           choice.setText(answers[i-1]);

           choice.setClickable(false);

           if(localDataSet[position].trueAnswer==i){

               choice.setChecked(true);
           }

        }

        viewHolder.getUpdateQuestionButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,UpdateQuestionActivity.class);
                intent.putExtra("question_id",viewHolder.question.id);
                context.startActivity(intent);
            }
        });

        viewHolder.imageView.setImageBitmap(BitmapOperations.StringToBitMap(viewHolder.getQuestion().questionAttachment));

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return localDataSet.length;
    }


}