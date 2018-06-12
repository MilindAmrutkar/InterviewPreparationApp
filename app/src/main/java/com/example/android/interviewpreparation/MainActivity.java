package com.example.android.interviewpreparation;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.QuickContactBadge;
import android.widget.Toast;

import com.example.android.interviewpreparation.model.Quiz;
import com.example.android.interviewpreparation.utilities.NetworkUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private ArrayList<Quiz> dataStructureArrayList = new ArrayList<>();
    private GridView gridView;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = findViewById(R.id.interview_section);
        imageView = findViewById(R.id.lcoIcon);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://courses.learncodeonline.in/"));
                startActivity(intent);
                //Toast.makeText(MainActivity.this, "Learn Code Online", Toast.LENGTH_SHORT).show();
            }
        });

        gridView.setAdapter(new ImageAdapter(this));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                switch (position) {
                    case 0:
                        if(NetworkUtils.isOnline(MainActivity.this)) {
                            URL dataStructureURL = NetworkUtils.buildUrl();
                            new DataStructureTask().execute(dataStructureURL);
                        } else {
                            Toast.makeText(MainActivity.this, "Internet connectivity issue. Switch on the Internet", Toast.LENGTH_SHORT).show();
                        }
                        break;

                    case 1:
                        Toast.makeText(MainActivity.this, "Coming soon..", Toast.LENGTH_SHORT).show();
                        break;

                    case 2:
                        Toast.makeText(MainActivity.this, "Coming soon..", Toast.LENGTH_SHORT).show();
                        break;

                    case 3:
                        Toast.makeText(MainActivity.this, "Coming soon..", Toast.LENGTH_SHORT).show();
                        break;


                }

            }
        });

    }

    private class DataStructureTask extends AsyncTask<URL, Void, String> {

        @Override
        protected String doInBackground(URL... urls) {
            URL dataStructureURL = urls[0];
            Log.i(TAG, "doInBackground: dataStructureURL: " + dataStructureURL);
            String dataStructureResults = null;
            try {
                dataStructureResults = NetworkUtils.getResponseFromHttpUrl(dataStructureURL);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return dataStructureResults;
        }

        @Override
        protected void onPostExecute(String dataStructureResults) {
            if (dataStructureResults != null && !dataStructureResults.equals("")) {
                //Log.i(TAG, "onPostExecute: dataStructureResults: " + dataStructureResults);
                dataStructureArrayList = parseJSON(dataStructureResults);

                Intent intent = new Intent(MainActivity.this, DataStructureActivity.class);
                intent.putExtra("quiz", dataStructureArrayList);
                startActivity(intent);
            } else {
                Log.i(TAG, "onPostExecute: Empty");
            }
        }
    }

    private ArrayList<Quiz> parseJSON(String dataStructureResults) {
        if (dataStructureArrayList != null || !dataStructureArrayList.isEmpty()) {
            dataStructureArrayList.clear();
        }

        if (dataStructureResults != null) {
            //Log.i(TAG, "parseJSON: dataStructureResults: " + dataStructureResults);
            try {
                Quiz quiz;
                JSONObject rootObject = new JSONObject(dataStructureResults);

                JSONArray questions = rootObject.getJSONArray("questions");

                for (int i = 0; i < questions.length(); i++) {
                    JSONObject q = questions.getJSONObject(i);
                    String question = q.getString("question");
                    String answer = q.getString("Answer");

                    quiz = new Quiz();
                    quiz.setQuestion(question);
                    quiz.setAnswer(answer);

                    dataStructureArrayList.add(quiz);

                }

               /* for (Quiz quizInIterator : dataStructureArrayList) {
                    Log.i(TAG, "\nquestion: " + quizInIterator.getQuestion() + "\n answer: " + quizInIterator.getAnswer());
                }*/

                return dataStructureArrayList;

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
