package com.fncinc.gamedayapplicationv2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import eu.erikw.PullToRefreshListView;
import eu.erikw.PullToRefreshListView.OnRefreshListener;

public class PollActivity extends Activity {

	private final int ANSWER_ACTIVITY_CODE = 1;

	public static String getDataURL = Main.SERVER_URL + Main.URL_COMMAND + "command=getQuestionData";
	private JSONObject dataObject = null;
	protected ArrayAdapter<String> adapter;
	protected ArrayList<String> list;
	protected HashMap<String, ArrayList<String>> questionMap = new HashMap<String, ArrayList<String>>();
	protected HashMap<String, Integer> questionIDMap = new HashMap<String, Integer>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_poll);

		AsyncRequest request = new AsyncRequest();
		request.execute(getDataURL);

		/*
		 * String[] values = new String[] { "Android", "iPhone", "WindowsMobile", "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X", "Linux", "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux", "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux", "OS/2", "Android", "iPhone", "WindowsMobile" };
		 * 
		 * final ArrayList<String> list = new ArrayList<String>(); for (int i = 0; i < values.length; ++i) { list.add(values[i]); }
		 */
		PullToRefreshListView listview = (PullToRefreshListView) findViewById(R.id.questionList);

		list = new ArrayList<String>();
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
		listview.setAdapter(adapter);
		listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
				Log.d("appMessage", Integer.toString(position));
				
				String item = (String) parent.getItemAtPosition(position+1);
				// showToast(item);
				//Log.d("appMessage", item);
				doQuestion(item);

				/*
				 * view.animate().setDuration(2000).alpha(0).withEndAction(new Runnable() {
				 * 
				 * @Override public void run() { list.remove(item); adapter.notifyDataSetChanged(); view.setAlpha(1); } });
				 */
			}

		});

		listview.setOnRefreshListener(new OnRefreshListener() {

			@Override
			public void onRefresh() {
				list.clear();
				questionMap.clear();
				questionIDMap.clear();
				AsyncRequest request = new AsyncRequest();
				request.execute(getDataURL, "refreshing");
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.poll, menu);
		return true;
	}

	protected void doQuestion(String question) {
		Intent intent = new Intent(this, AnswerActivity.class);
		Bundle mBundle = new Bundle();
		mBundle.putString("question", question);
		mBundle.putStringArrayList("answers", questionMap.get(question));
		mBundle.putInt("questionID", questionIDMap.get(question));
		intent.putExtras(mBundle);
		startActivityForResult(intent, ANSWER_ACTIVITY_CODE);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		if (requestCode == ANSWER_ACTIVITY_CODE) {

			if (resultCode == RESULT_OK) {
				boolean result = data.getBooleanExtra("answered", false);
				// showToast(Boolean.toString(result));
				AsyncRequest request = new AsyncRequest();
				request.execute(getDataURL);

			}
			if (resultCode == RESULT_CANCELED) {
				// Write your code if there's no result
			}
		}
	}

	private class AsyncRequest extends AsyncTask<String, Integer, String> {

		private boolean refreshCallback = false;

		public AsyncRequest() {

		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			return;
		}

		protected void onPostExecute(String result) {

			if (dataObject == null) {
			} else {

				Iterator<String> it = dataObject.keys();

				list.clear();
				questionMap.clear();
				questionIDMap.clear();
				
				while (it.hasNext()) {
					String key = it.next();

					try {
						JSONArray answers = dataObject.getJSONObject(key).getJSONArray("answers");
						ArrayList<String> tempAnswers = new ArrayList<String>();
						for (int i = 0; i < answers.length(); i++) {
							JSONObject answer = answers.getJSONObject(i);
							tempAnswers.add(answer.getString("answer") + "|" + answer.getString("numResponses") + "|" + answer.getString("answerID"));
						}

						String question = dataObject.getJSONObject(key).getString("question");
						String questionID = dataObject.getJSONObject(key).getString("questionID");

						
						
						list.add(question);
						questionMap.put(question, tempAnswers);
						questionIDMap.put(question, Integer.parseInt(questionID));
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
				((PullToRefreshListView) findViewById(R.id.questionList)).setClickable(true);
				adapter.notifyDataSetChanged();

				if (refreshCallback) {
					((PullToRefreshListView) findViewById(R.id.questionList)).onRefreshComplete();
				}

			}

			return;
		};

		@Override
		protected String doInBackground(String... params) {

			if (params.length == 2) {
				if (params[1].equals("refreshing")) {
					refreshCallback = true;
				}
			}

			String output = "";
			try {
				DefaultHttpClient httpClient = new DefaultHttpClient();
				HttpGet httpGet = new HttpGet(params[0]);

				HttpResponse httpResponse = httpClient.execute(httpGet);
				HttpEntity httpEntity = httpResponse.getEntity();
				output = EntityUtils.toString(httpEntity);
				dataObject = new JSONObject(output);

			} catch (IOException e) {
				e.printStackTrace();
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return null;
		}
	}

	public void showToast(String msg) {
		Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
	}

}
