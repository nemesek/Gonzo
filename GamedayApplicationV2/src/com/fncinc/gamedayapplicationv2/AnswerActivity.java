package com.fncinc.gamedayapplicationv2;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

public class AnswerActivity extends Activity {

	public static String SEND_ANSWER = Main.SERVER_URL + Main.URL_COMMAND + "command=postQuestionAnswer";
	
	private String question;
	private Integer questionID;
	private ArrayList<String> answers;
	private ArrayList<Integer> answerCount;
	private ArrayList<Integer> answerIDS;
	private int answerCountSum = 1;
	
	protected boolean answered = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_answer);
		
		question = getIntent().getExtras().getString("question");
		questionID = getIntent().getExtras().getInt("questionID");
		
		ArrayList<String> tempAnswers = getIntent().getExtras().getStringArrayList("answers");
		
		answers = new ArrayList<String>();
		answerCount = new ArrayList<Integer>();
		answerIDS = new ArrayList<Integer>();
		
		for(int i=0;i<tempAnswers.size();i++){
			String[] temp = tempAnswers.get(i).split("\\|");
			answers.add(temp[0]);
			answerCount.add(Integer.parseInt(temp[1]));
			answerIDS.add(Integer.parseInt(temp[2]));
		}
		
		for(Integer i: answerCount){
			answerCountSum+=i;
		}
		
		Log.d("appMessage", question);
		
		LinearLayout answerView = (LinearLayout) findViewById(R.id.viewQuestion);
		
		LayoutParams lp = new LayoutParams(
    		    LayoutParams.FILL_PARENT, 
    		    LayoutParams.WRAP_CONTENT,
    		    1.0f
    	    );
		lp.setMargins(5, 5, 5, 5);
		
		for(int i=0;i<answers.size();i++){
			String s = answers.get(i);

			LinearLayout tl = new LinearLayout(this);
			tl.setOrientation(LinearLayout.HORIZONTAL);
			
			TextView tv = new TextView(this);
			tv.setText(Integer.toString(i+1)+".");
			tv.setTextSize(20.0f);
			//tv.setLayoutParams(lp);
			
			Button bv = new Button(this);
			bv.setText(s);
			bv.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					doAnswerSelect(v);
				}
			});
			bv.setPadding(3, 3, 3, 3);
			LayoutParams tLp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
			tLp.setMargins(5, 5, 5, 5);
			bv.setLayoutParams(tLp);
			//bv.setLayoutParams(lp);

			tl.addView(tv);
			tl.addView(bv);
			
			answerView.addView(tl);
		}
		
		((Button) findViewById(R.id.back)).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				returnToQuestions();
			}
		});
	}

	protected void doAnswerSelect(View v) {
		
		LinearLayout questionView = (LinearLayout) findViewById(R.id.viewQuestion);
		questionView.removeAllViews();
		
		
		Button clicked = (Button) v;
		String answerText = (String) clicked.getText();
		
		answered = true;
		
		AsyncRequest request = new AsyncRequest();
		request.execute(answerText);
	}
	
	protected void doAfterAnswerSelect(){
		
		LinearLayout answerView = (LinearLayout) findViewById(R.id.viewQuestion);
		
		LayoutParams lp = new LayoutParams(
    		    LayoutParams.FILL_PARENT, 
    		    LayoutParams.WRAP_CONTENT,
    		    1.0f
    	    );
		lp.setMargins(5, 5, 5, 5);
		
		for(int i=0;i<answers.size();i++){
			String s = answers.get(i);

			LinearLayout tl = new LinearLayout(this);
			tl.setOrientation(LinearLayout.HORIZONTAL);
			
			TextView tv = new TextView(this);
			tv.setText(Integer.toString(i+1)+".) ");
			tv.setTextSize(20.0f);
			//tv.setLayoutParams(lp);
			
			TextView bv = new TextView(this);
			bv.setText(s);
			//bv.setLayoutParams(lp);

			tl.addView(tv);
			tl.addView(bv);
			
			answerView.addView(tl);
			
			
			LinearLayout tl1 = new LinearLayout(this);
			tl1.setOrientation(LinearLayout.HORIZONTAL);
			
			TextView tev = new TextView(this);
			int width = (int) ((answerCount.get(i)*100)/answerCountSum)*4;
			LayoutParams temp = new LayoutParams(width,LayoutParams.WRAP_CONTENT);
			temp.setMargins(25, 8, 0, 8);
			tev.setLayoutParams(temp);
			tev.setBackgroundColor(Color.RED);
			tev.setText(" "+answerCount.get(i));
			
			TextView lblPercent = new TextView(this);
			lblPercent.setText((int) ((answerCount.get(i)*100)/answerCountSum) + "%");
			lblPercent.setLayoutParams(temp);
			
			
			tl1.addView(tev);
			tl1.addView(lblPercent);
			
			answerView.addView(tl1);
		}
		
		
		
		for(int i=0;i<answers.size();i++){
			
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.answer, menu);
		return true;
	}

	private void returnToQuestions() {
		Intent returnIntent = new Intent();
		returnIntent.putExtra("answered", answered);
		setResult(RESULT_OK, returnIntent);
		finish();
	}
	
	@Override
	public void onBackPressed() {
		returnToQuestions();
		super.onBackPressed();
	}
	
	
	
	private class AsyncRequest extends AsyncTask<String, Integer, String> {
		
		private Integer output = -1;
		
		public AsyncRequest() {
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			return;
		}

		protected void onPostExecute(String result) {
			doAfterAnswerSelect();
			return;
		}

		@Override
		protected String doInBackground(String... params) {

			for (String s : params) {
				Log.d("appMessage", s);
			}
			
			String answerID = "";
			for(int i=0;i<answers.size();i++){
				
				if(answers.get(i).equals(params[0])){
					answerID = Integer.toString(answerIDS.get(i));
					break;
				}
			}
			
			
			
			try {
				DefaultHttpClient httpClient = new DefaultHttpClient();
				HttpGet httpGet = new HttpGet(SEND_ANSWER+"&questionID="+questionID+"&answerID="+answerID);

				HttpResponse httpResponse = httpClient.execute(httpGet);
				HttpEntity httpEntity = httpResponse.getEntity();
				Integer output = Integer.parseInt(EntityUtils.toString(httpEntity));
				
				for(int i=0;i<answers.size();i++){
					
					if(answers.get(i).equals(params[0])){
						Log.d("appMessage", "was "+answerCount.get(i));
						answerCount.set(i, output);
						//answerCountSum+=1;
						Log.d("appMessage", "now "+answerCount.get(i));
						break;
						
					}
				}
				
				
				
			} catch (IOException e) {
				e.printStackTrace();
			}

			return null;
		}
	}

	public void showToast(String msg){
		Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
	}
	
}
