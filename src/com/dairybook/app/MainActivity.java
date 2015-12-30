package com.dairybook.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dairybook.app.R.id;

import android.os.Bundle;
import android.R.anim;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private Button editdairyButton;

	private ListView listView;

	private String[] data = { "A", "A", "A", "A", "A", "A", "A", "A", "A", "A",
			"A", "A", "A", "A", "A", "A", "A", "A", "A", "A", "A", "A", "A",
			"A", "A", "A", "A", "A", "A", "A", "A", "A", "A", "A", "A", "A",
			"A" };

	private String[] itemMap = new String[2];
	private List<String[]> list = new ArrayList<String[]>();

	private DairyHelper dairyHelper = new DairyHelper(this, "Dairy.db", null, 1);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);

		EditText editText = (EditText) findViewById(R.id.test);
		listView = (ListView) findViewById(R.id.list_view);

		SQLiteDatabase database = dairyHelper.getWritableDatabase();
		Cursor cursor = database.query("dairy", null, null, null, null, null,
				null);

		 
		if (cursor.moveToFirst()) {
			do {
				itemMap[0] = cursor.getString(cursor
						.getColumnIndex("dairy_title"));
				itemMap[1] = cursor.getString(cursor
						.getColumnIndex("dairy_content"));

				editText.append(itemMap[0] + itemMap[1]);
				list.add(itemMap);
			} while (cursor.moveToNext());
		}

		
		

		
		editdairyButton = (Button) findViewById(R.id.edit_dairy);
		editdairyButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent intent = new Intent(MainActivity.this,
						EditNoteActivity.class);
				startActivity(intent);
			}
		});
	}
}
