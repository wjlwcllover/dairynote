package com.dairybook.app;

import java.util.List;

import com.dairybook.app.R.id;

import android.R.integer;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class DairyAdapter extends ArrayAdapter<Diary> {

	public DairyAdapter(Context context, int textViewResourceId, List<Diary> objects) {
		super(context, textViewResourceId, objects);
		resourceId = textViewResourceId;
	}



	private int resourceId;

	 

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		Diary diary = getItem(position);
		View view = LayoutInflater.from(getContext()).inflate(resourceId, null);

		// ImageView imageView = (ImageView)
		// view.findViewById(R.id.weather_image_list);
		TextView titleTextView = (TextView) view
				.findViewById(R.id.text_title_list);
		TextView contentTextView = (TextView) view
				.findViewById(R.id.text_content_list);
		TextView dateTextView = (TextView) view.findViewById(R.id.date_list);

		titleTextView.setText(diary.getTitleString());
		contentTextView.setText(diary.getContentString());
		dateTextView.setText(diary.getDate()  );

		return view;
	}

}
