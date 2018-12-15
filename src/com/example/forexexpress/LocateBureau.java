package com.example.forexexpress;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class LocateBureau extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_locate_bureau);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.locate_bureau, menu);
		return true;
	}

}
