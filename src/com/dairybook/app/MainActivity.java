package com.dairybook.app;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends Activity {

	private Button editdairyButton;

	private ListView listViewSum;

	private DairyHelper dairyHelper = new DairyHelper(this, "Dairy.db", null, 1);

	private List<Diary> list = new ArrayList<Diary>();
	private DairyAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);

		list = loadDiaries();
		listViewSum = (ListView) findViewById(R.id.list_view);

		adapter = new DairyAdapter(this, R.layout.list_item, list);
		listViewSum.setAdapter(adapter);

		listViewSum.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				Diary diary = list.get(position);

				Intent intent = new Intent(MainActivity.this,
						EditNoteActivity.class);

				intent.putExtra("id", "" + diary.getDairyId());
				startActivity(intent);

			}
		});

		editdairyButton = (Button) findViewById(R.id.edit_dairy);
		editdairyButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent intent2 = new Intent(MainActivity.this,
						EditNoteActivity.class);

				intent2.putExtra("id", "true");
				startActivity(intent2);
			}
		});
	}

	// 将数据库中的 条目显示到 主界面中
	public List<Diary> loadDiaries() {

		List<Diary> listDiary = new ArrayList<Diary>();

		SQLiteDatabase database = dairyHelper.getWritableDatabase();
		Cursor cursor = database.query("dairy", null, null, null, null, null,
				null);

		if (cursor.moveToFirst()) {
			do {

				Diary diary = new Diary();
				diary.setDate(cursor.getString(cursor
						.getColumnIndex("dairy_date")));
				diary.setContentString(cursor.getString(cursor
						.getColumnIndex("dairy_content")));
				diary.setTitleString(cursor.getString(cursor
						.getColumnIndex("dairy_title")));
				diary.setDairyId(cursor.getInt(cursor.getColumnIndex("id")));
				listDiary.add(diary);
			} while (cursor.moveToNext());
		}

		cursor.close();
		return listDiary;
	}

}
