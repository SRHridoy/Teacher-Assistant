package com.elitcoder.teacherassistant.ShortNotes;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.elitcoder.teacherassistant.R;

import java.util.ArrayList;
import java.util.List;

public class ShortNotesActivity extends AppCompatActivity {

    //For Shared Pref:
    private static final String SPREF_NAME = "SNotesPref";
    private static final String KEY_NOTE_COUNT = "SNotesCount";

    //For layout modification :
    private LinearLayout topicContainer;
    private List<Topic> topicList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_short_notes);

        topicContainer = findViewById(R.id.topicsContainer);
        Button btnSave = findViewById(R.id.btnSave);

        topicList = new ArrayList<>();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveTopics();
            }
        });
        loadTopicsFromSPref();
        displayTopics();
    }

    private void displayTopics() {
        for (Topic topic:topicList){
            createTopics(topic);
        }
    }

    private void loadTopicsFromSPref() {
        SharedPreferences sharedPreferences = getSharedPreferences(SPREF_NAME, MODE_PRIVATE);
        int topicCount = sharedPreferences.getInt(KEY_NOTE_COUNT,0);

        for (int i = 0; i < topicCount; i++){
            String title = sharedPreferences.getString("topic_title"+i,"");
            String keypoints = sharedPreferences.getString("key_points"+i,"");

            Topic topic = new Topic();
            topic.setTitle(title);
            topic.setKeypoints(keypoints);

            topicList.add(topic);
        }
    }

    private void saveTopics() {
        EditText edtTitle = findViewById(R.id.edtPrevTitle);
        EditText edtKeypoints = findViewById(R.id.edtPrevKeypoints);

        String title = edtTitle.getText().toString();
        String keypoints = edtKeypoints.getText().toString();

        if(!title.isEmpty() && !keypoints.isEmpty()){
            Topic topic = new Topic();
            topic.setTitle(title);
            topic.setKeypoints(keypoints);

            topicList.add(topic);
            saveTopicsToSPref();
            createTopics(topic);
            clearInput();
        }
    }

    private void clearInput() {
        EditText edtTitle = findViewById(R.id.edtPrevTitle);
        EditText edtKeypoints = findViewById(R.id.edtPrevKeypoints);

        edtTitle.getText().clear();
        edtKeypoints.getText().clear();
    }

    private void createTopics(final Topic topic) {
        View topicView = getLayoutInflater().inflate(R.layout.topic_raw,null);
        TextView txtTitle = topicView.findViewById(R.id.txtTitle); // Find views within topicView
        TextView txtKeypoints = topicView.findViewById(R.id.txtKeypoints); // Find views within topicView

        txtTitle.setText(topic.getTitle());
        txtKeypoints.setText(topic.getKeypoints());

        topicView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                showDeleteOptions(topic);
                return true;
            }
        });
        topicContainer.addView(topicView);
    }

    private void showDeleteOptions(final Topic topic) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete this topic.");
        builder.setMessage("Are you sure to delete this topic?");
        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteTopicAndRefresh(topic);
            }
        });
        builder.setNegativeButton("Cancel",null);
        builder.show();
    }

    private void deleteTopicAndRefresh(Topic topic){
        topicList.remove(topic);
        saveTopicsToSPref();
        refreshNoteViews();
    }

    private void refreshNoteViews() {
        topicContainer.removeAllViews();
        displayTopics();
    }


    private void saveTopicsToSPref() {
        SharedPreferences sharedPreferences = getSharedPreferences(SPREF_NAME,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt(KEY_NOTE_COUNT, topicList.size());
        for(int i = 0; i < topicList.size(); i++){
            Topic topic = topicList.get(i);
            editor.putString("topic_title"+i, topic.getTitle());
            editor.putString("key_points"+i, topic.getKeypoints());
        }
        editor.apply();
    }
}