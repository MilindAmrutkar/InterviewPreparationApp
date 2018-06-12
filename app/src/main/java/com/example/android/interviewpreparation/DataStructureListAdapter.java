package com.example.android.interviewpreparation;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.example.android.interviewpreparation.model.Quiz;
import java.util.ArrayList;

/**
 * Created by Milind Amrutkar on 11-06-2018.
 */
public class DataStructureListAdapter extends ArrayAdapter<Quiz> {
   DataStructureListAdapter(Activity context, ArrayList<Quiz> dataStructureArrayList) {
       super(context, 0, dataStructureArrayList);
   }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
       Quiz quiz;
       if(position != -1) {
           quiz = getItem(position);
       } else {
           quiz = null;
       }

       if(convertView == null) {
           convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_list_item, parent, false);
       }

       TextView tvQuestion = convertView.findViewById(R.id.tvQuestion);
       TextView tvAnswer = convertView.findViewById(R.id.tvAnswer);

       if(quiz != null) {
           tvQuestion.setText(quiz.getQuestion());
           tvAnswer.setText(quiz.getAnswer());
       }

       return convertView;
    }
}
