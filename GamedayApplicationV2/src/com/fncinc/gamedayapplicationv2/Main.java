package com.fncinc.gamedayapplicationv2;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

public class Main extends Activity {

	public static String SERVER_URL = "http://10.15.5.152";
	public static String URL_COMMAND = ":85/app.php?";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);
		
		
		// Layout -------------------------------------------------------
		LinearLayout ll = (LinearLayout) findViewById(R.id.mainLL);
		
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
    		    LayoutParams.FILL_PARENT, 
    		    LayoutParams.FILL_PARENT,
    		    1.0f
    	    );
       
		//create our custom row
		LinearLayout customRow1 = new LinearLayout(this);
		customRow1.setLayoutParams(lp);
		
		View tv = new View(this);
		tv.setLayoutParams(new LayoutParams(0, 1, 1.0f));
		customRow1.addView(tv);
		
		ImageButton pollIcon = new ImageButton(this);
		pollIcon.setImageDrawable(getResources().getDrawable(R.drawable.pollicon));
		pollIcon.setLayoutParams(new LayoutParams(100, 100, 1));
		pollIcon.setScaleType(ScaleType.FIT_CENTER);
		pollIcon.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				pollClick(v);	
			}
		});
		customRow1.addView(pollIcon);
		
		
		View tv1 = new View(this);
		tv1.setLayoutParams(new LayoutParams(0, 1, 1.0f));
		customRow1.addView(tv1);
		
		
		ImageButton foodIcon = new ImageButton(this);
		foodIcon.setImageDrawable(getResources().getDrawable(R.drawable.foodicon));
		foodIcon.setLayoutParams(new LayoutParams(100, 100, 1));
		foodIcon.setScaleType(ScaleType.FIT_CENTER);
		foodIcon.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				foodClick(v);	
			}
		});
		customRow1.addView(foodIcon);
		
		View tv2 = new View(this);
		tv2.setLayoutParams(new LayoutParams(0, 1, 1.0f));
		customRow1.addView(tv2);
		
		ll.addView(customRow1,lp);
		//done creating our custom row
		
		
		//create our custom row
		LinearLayout customRow2 = new LinearLayout(this);
		customRow2.setLayoutParams(lp);
		
		View tv3 = new View(this);
		tv3.setLayoutParams(new LayoutParams(0, 1, 1.0f));
		customRow2.addView(tv3);
		
		ImageButton calendarIcon = new ImageButton(this);
		calendarIcon.setImageDrawable(getResources().getDrawable(R.drawable.calendaricon));
		calendarIcon.setLayoutParams(new LayoutParams(100, 100, 1));
		calendarIcon.setScaleType(ScaleType.FIT_CENTER);
		calendarIcon.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				calendarClick(v);	
			}
		});
		customRow2.addView(calendarIcon);
		
		
		View tv4 = new View(this);
		tv4.setLayoutParams(new LayoutParams(0, 1, 1.0f));
		customRow2.addView(tv4);
		
		
		ImageButton bathroomIcon = new ImageButton(this);
		bathroomIcon.setImageDrawable(getResources().getDrawable(R.drawable.bathroomicon));
		bathroomIcon.setLayoutParams(new LayoutParams(100, 100, 1));
		bathroomIcon.setScaleType(ScaleType.FIT_CENTER);
		bathroomIcon.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				bathroomClick(v);	
			}
		});
		customRow2.addView(bathroomIcon);
		
		View tv5 = new View(this);
		tv5.setLayoutParams(new LayoutParams(0, 1, 1.0f));
		customRow2.addView(tv5);
		
		ll.addView(customRow2,lp);
		//done creating our custom row
		
		
		SharedPreferences settings = getSharedPreferences("GamedaySettings", 0);
		String hostname = settings.getString("hostname", "http://10.15.5.152");
		Main.SERVER_URL = hostname;
		PollActivity.getDataURL = Main.SERVER_URL + Main.URL_COMMAND + "command=getQuestionData";
		AnswerActivity.SEND_ANSWER = Main.SERVER_URL + Main.URL_COMMAND + "command=postQuestionAnswer";
		
		
		/*
		for (int i=0;i<2;i++) {
			
			LinearLayout horizontalLL = new LinearLayout(this);
			horizontalLL.setLayoutParams(lp);

			for(int j=0;j<3;j++){
				View v = new View(this);
				v.setLayoutParams(new LayoutParams(0, 1, 1.0f));
				horizontalLL.addView(v);
				
				Button tempButton = new Button(this);
				tempButton.setText("Testing");
				horizontalLL.addView(tempButton);
			}
			View v = new View(this);
			v.setLayoutParams(new LayoutParams(0, 1, 1.0f));
			horizontalLL.addView(v);
			
			ll.addView(horizontalLL,lp);
		}
		*/
		
		// End Layout ----------------------------------------------------
		TextView mLink = (TextView) findViewById(R.id.link);
		 if (mLink != null) {
		   mLink.setMovementMethod(LinkMovementMethod.getInstance());
		 }
	}
	
	
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.settings:
			startActivity(new Intent(this, MySettings.class));
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
	protected void bathroomClick(View v) {
		//Intent intent = new Intent(this,BathroomActivity.class);
		//startActivity(intent);
		
	}

	protected void calendarClick(View v) {
		// TODO Auto-generated method stub
		
	}

	protected void foodClick(View v) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu, menu);
		return true;
	}

	protected void pollClick(View v) {
		Intent intent = new Intent(this,PollActivity.class);
		startActivity(intent);
	}

}
