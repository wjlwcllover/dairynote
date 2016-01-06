package com.dairybook.app;

import java.util.Date;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;


//应该加上定时自动保存功能
public class EditNoteActivity extends Activity {

	private ImageButton weatherImageButton;
	private EditText titleEditText;
	private EditText dateEditText;
	private EditText contentEditText;

	private Button saveButton;
	private Button cancelButton;

	private DairyHelper dairyHelper;
	private static boolean SAVED = false;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.dairy_write);

		Intent intent = getIntent();
		String data = intent.getStringExtra("id");

		Toast.makeText(EditNoteActivity.this, data, Toast.LENGTH_LONG).show();

		if (data == null) {
			Toast.makeText(EditNoteActivity.this, " there is no data",
					Toast.LENGTH_LONG).show();
		} else if (data.isEmpty())

		{
			Toast.makeText(EditNoteActivity.this, " the data is o length",
					Toast.LENGTH_LONG).show();
		} else {
			Toast.makeText(EditNoteActivity.this, data, Toast.LENGTH_LONG)
					.show();
		}

		weatherImageButton = (ImageButton) findViewById(R.id.weather_image);
		titleEditText = (EditText) findViewById(R.id.edit_title);
		dateEditText = (EditText) findViewById(R.id.edit_date);
		contentEditText = (EditText) findViewById(R.id.edit_content);
		saveButton = (Button) findViewById(R.id.save_button);
		cancelButton = (Button) findViewById(R.id.back_button);

		dairyHelper = new DairyHelper(this, "Dairy.db", null, 1);
		SQLiteDatabase database = dairyHelper.getWritableDatabase();

		if (data.equals("true")) {

			// edit 按钮的触发事件
			Date today = new Date(System.currentTimeMillis());
			dateEditText.setText(today.toLocaleString());
		}
		// 传递进来 这个数据在数据库中对应的 行的数据
		else {
			int id_in_db = Integer.parseInt(data);

			if (data != null) {

				// 查询指定列的内容，然后传递给 视图，显示编辑
				Cursor cursor = database.query("dairy", null, "id = ?",
						new String[] { String.valueOf(id_in_db) }, null, null,
						null);
				if (cursor.moveToFirst()) {

					titleEditText.append(cursor.getString(cursor
							.getColumnIndex("dairy_title")));
					contentEditText.setText(cursor.getString(cursor
							.getColumnIndex("dairy_content")));
					dateEditText.setText(cursor.getString(cursor
							.getColumnIndex("dairy_date")));

				}

				if (cursor != null) {
					cursor.close();

				}

			}

		}

		// 当打开笔记编辑器的时候就要 创建这个 数据库

		saveButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// 保存笔记信息

				SQLiteDatabase database = dairyHelper.getWritableDatabase();
				ContentValues values = new ContentValues();

				// values.put("image_id", weatherImageButton.getId());
				values.put("dairy_title", titleEditText.getText().toString());
				values.put("dairy_content", contentEditText.getText()
						.toString());
				values.put("dairy_date", dateEditText.getText().toString());

				database.insert("dairy", null, values);
				Toast.makeText(EditNoteActivity.this, "baocunn/...",
						Toast.LENGTH_LONG).show();
				SAVED = true;

				
			}
		});

		cancelButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (SAVED == false) {
					Toast.makeText(EditNoteActivity.this, "还没有保存，确定要离开？",
							Toast.LENGTH_LONG).show();
				}else if(SAVED == true)
				{
					SAVED = false;
					Intent intent = new Intent(EditNoteActivity.this,
							MainActivity.class);
					startActivity(intent);
				}

			}
		});

	}
}
