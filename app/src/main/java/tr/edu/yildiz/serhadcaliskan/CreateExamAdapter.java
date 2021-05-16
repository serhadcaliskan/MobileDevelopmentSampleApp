package tr.edu.yildiz.serhadcaliskan;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class CreateExamAdapter extends RecyclerView.Adapter<CreateExamAdapter.ViewHolder> {

    private Question[] localDataSet;

    private ArrayList<Question> checkedQuestions=new ArrayList<Question>();

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView questionText;
        private final RadioGroup choicesRadioGroup;
        private final CheckBox isSelectedCheckBox;
        private final ImageView attachment;
        private Question question;

        public ViewHolder(View view) {
            super(view);

            questionText = view.findViewById(R.id.questionTextExamQuestion);

            choicesRadioGroup = view.findViewById(R.id.examQuestionChoices);

            isSelectedCheckBox = view.findViewById(R.id.selectionCheckBox);

            attachment=view.findViewById(R.id.attachmentImageView);
        }

        public TextView getQuestionTextView() {
            return questionText;
        }

        public RadioGroup getChoicesRadioGroup() {
            return choicesRadioGroup;
        }

        public ImageView getAttachment() {
            return attachment;
        }

        public Question getQuestion() {
            return question;
        }

        public void setQuestion(Question question) {
            this.question = question;
        }
    }


    public CreateExamAdapter(Question[] questionSet) {

        localDataSet = questionSet;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_create_exam_question, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        viewHolder.setQuestion(localDataSet[position]);
        viewHolder.getQuestionTextView().setText(localDataSet[position].question);
        String[] answers = {localDataSet[position].answer1, localDataSet[position].answer2,
                localDataSet[position].answer3, localDataSet[position].answer4, localDataSet[position].answer5};

        for (int i = 1; i <= 5; i++) {

            RadioButton choice = (RadioButton) viewHolder.getChoicesRadioGroup().getChildAt(i - 1);

            choice.setText(answers[i - 1]);

            choice.setClickable(false);

            if (localDataSet[position].trueAnswer == i) {

                choice.setChecked(true);
            }

        }

        viewHolder.isSelectedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

             @Override
             public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                 if(isChecked){

                    checkedQuestions.add(viewHolder.question);

                 }
                 else{

                     checkedQuestions.remove(viewHolder.question);

                 }

             }
         });

        viewHolder.getAttachment().setImageBitmap(BitmapOperations.StringToBitMap(viewHolder.getQuestion().questionAttachment));
    }

    @Override
    public int getItemCount() {
        return localDataSet.length;
    }

    public ArrayList<Question> getSelectedQuestions(){
        return checkedQuestions;

    }
}