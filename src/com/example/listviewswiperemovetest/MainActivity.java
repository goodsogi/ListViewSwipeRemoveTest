package com.example.listviewswiperemovetest;

import java.util.ArrayList;
import java.util.Arrays;
import android.os.Bundle;
import android.app.Activity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private ArrayAdapter<String> adapter;
	private ArrayList<String> lst;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ListView listView = (ListView) findViewById(R.id.mylist);
		String[] values = new String[] { "Android", "iPhone", "WindowsMobile",
				"Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
				"Linux", "OS/2" };
		lst = new ArrayList<String>();
		lst.addAll(Arrays.asList(values));
		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, android.R.id.text1, lst);

		SwipeListViewTouchListener touchListener = new SwipeListViewTouchListener(
				listView, new SwipeListViewTouchListener.OnSwipeCallback() {
					@Override
					public void onSwipeLeft(ListView listView,
							int[] reverseSortedPositions) {
						// Log.i(this.getClass().getName(),
						// "swipe left : pos="+reverseSortedPositions[0]);
						// TODO : YOUR CODE HERE FOR LEFT ACTION
					}

					@Override
					public void onSwipeRight(ListView listView,
							int[] reverseSortedPositions) {
						// Log.i(ProfileMenuActivity.class.getClass().getName(),
						// "swipe right : pos="+reverseSortedPositions[0]);
						// TODO : YOUR CODE HERE FOR RIGHT ACTION
						adapter.remove(adapter
								.getItem(reverseSortedPositions[0]));
						adapter.notifyDataSetChanged();
						Toast.makeText(
								MainActivity.this,
								"swiped to right!!" + "pos: "
										+ reverseSortedPositions[0],
								Toast.LENGTH_LONG).show();
					}
				}, true, // example : left action = dismiss
				false); // example : right action without dismiss animation
		listView.setOnTouchListener(touchListener);
		// Setting this scroll listener is required to ensure that during
		// ListView scrolling,
		// we don't look for swipes.
		listView.setOnScrollListener(touchListener.makeScrollListener());
		listView.setAdapter(adapter);

	}

}
