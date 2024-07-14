package com.example.dogeye;

import android.content.SharedPreferences;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class HistoryActivity extends AppCompatActivity {

    private ArrayList<String> historyList = new ArrayList<>();
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        TextView historyTitle = findViewById(R.id.history_title);  // Reference the history title
        historyTitle.setText("History");  // Set the title text

        ListView listView = findViewById(R.id.history_list);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, historyList);
        listView.setAdapter(adapter);

        // Load history data from storage
        loadHistory();

        listView.setOnItemClickListener((parent, view, position, id) -> {
            String item = historyList.get(position);
            // Extract data from the item string
            Intent intent = new Intent(HistoryActivity.this, DetailActivity.class);
            intent.putExtra("ITEM_DATA", item);
            startActivity(intent);
        });
    }

    private void loadHistory() {
        SharedPreferences sharedPreferences = getSharedPreferences("PREDICTION_HISTORY", MODE_PRIVATE);
        Set<String> historySet = sharedPreferences.getStringSet("HISTORY", new HashSet<>());

        historyList.clear();
        historyList.addAll(historySet);
        adapter.notifyDataSetChanged();
    }
}
