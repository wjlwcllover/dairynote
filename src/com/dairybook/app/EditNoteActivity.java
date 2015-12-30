package com.dairybook.app;

import com.dairybook.app.R.id;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class EditNoteActivity extends Activity {

	private ImageButton weatherImageButton;
	private EditText titleEditText;
	private EditText dateEditText;
	private EditText contentEditText;

	private Button saveButton;
	private Button cancelButton;

	private DairyHelper dairyHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.dairy_write);

		weatherImageButton = (ImageButton) findViewById(R.id.weather_image);
		titleEditText = (EditText) findViewById(R.id.edit_title);
		dateEditText = (EditText) findViewById(R.id.edit_date);
		contentEditText = (EditText) findViewById(R.id.edit_content);
		saveButton = (Button) findViewById(R.id.save_button);
		cancelButton = (Button) findViewById(R.id.back_button);

		// 当打开笔记编辑器的时候就要 创建这个 数据库
		dairyHelper = new DairyHelper(this, "Dairy.db", null, 1);

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
			}
		});
	}

}
