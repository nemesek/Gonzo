package com.fncinc.gamedayapplicationv2;

import android.os.Bundle;
import android.app.Activity;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MySettings extends Activity {

	public static final String PREFS_NAME = "GamedaySettings";
	SharedPreferences settings;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_settings);

		settings = getSharedPreferences(PREFS_NAME, 0);
		((EditText) findViewById(R.id.hostField)).setText(settings.getString("hostname", "http://"));
		((Button) findViewById(R.id.saveChanges)).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				doSave();
			}
		});
	}

	protected void doSave() {
		String hostname = ((EditText) findViewById(R.id.hostField)).getText().toString();
		Main.SERVER_URL = hostname;
		PollActivity.getDataURL = Main.SERVER_URL + Main.URL_COMMAND + "command=getQuestionData";
		AnswerActivity.SEND_ANSWER = Main.SERVER_URL + Main.URL_COMMAND + "command=postQuestionAnswer";
		SharedPreferences.Editor editor = settings.edit();
		editor.putString("hostname", hostname);
		editor.commit();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.my_settings, menu);
		return true;
	}

}
