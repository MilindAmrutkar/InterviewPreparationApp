package com.example.android.interviewpreparation;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.android.interviewpreparation.model.Quiz;

import java.util.ArrayList;

public class DataStructureActivity extends AppCompatActivity {

    private static final String TAG = DataStructureActivity.class.getSimpleName();
    private ArrayList<Quiz> dataStructureArrayList;
    private ListView listView;
    private ImageView lcoImageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_structure);

        listView = findViewById(R.id.quizList);

        lcoImageView = findViewById(R.id.lcoIcon);

        lcoImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://courses.learncodeonline.in/"));
                startActivity(intent);
                //Toast.makeText(MainActivity.this, "Learn Code Online", Toast.LENGTH_SHORT).show();
            }
        });

        dataStructureArrayList = new ArrayList<>();

        if(dataStructureArrayList!=null || !dataStructureArrayList.isEmpty()) {
            dataStructureArrayList.clear();
        }

        Intent intentThatStartedThisActivity = getIntent();
        if(intentThatStartedThisActivity.hasExtra("quiz")) {
            dataStructureArrayList = intentThatStartedThisActivity.getParcelableArrayListExtra("quiz");

        }

        if(dataStructureArrayList != null) {
            DataStructureListAdapter adapter = new DataStructureListAdapter(this, dataStructureArrayList);
            listView.setAdapter(adapter);
            adapter.setNotifyOnChange(true);
        } else {
            Toast.makeText(this, "Nothing in the Data Structure ArrayList", Toast.LENGTH_SHORT).show();
        }
    }
}
