package kr.co.imcc.app.uDiabetesNote;

import android.app.*;
import android.content.*;
import android.os.*;
import android.view.*;
import android.webkit.*;
import android.widget.*;

public class tActivity1_4_1 extends Activity {
	/** Called when the activity is first created. */

	private ActionBar actionBar;
	// TextView indexobesity, indexbmi, indexabdomen, indexrisk;
	TextView index1, index2, index3, index4, indexresult, textresult, textset;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tactivity1_4_1);

		actionBar = getActionBar();
		actionBar.setTitle("비만위험도 지수 측정");
		getActionBar().setHomeButtonEnabled(true);
		getActionBar().setDisplayHomeAsUpEnabled(true);

		Intent intent = getIntent();

		String refamily = intent.getExtras().getString("overfamily");
		String resex = intent.getExtras().getString("oversex");
		String reage = intent.getExtras().getString("overage");

		String recheight = intent.getExtras().getString("overheight");
		String recweight = intent.getExtras().getString("overweight");
		String recwaist = intent.getExtras().getString("overwaist");
		String recass = intent.getExtras().getString("overass");

		String overWeightPercent = intent.getExtras().getString(
				"overWeightPercent");
		String rebmi = intent.getExtras().getString("bmi");
		String reabdomen = intent.getExtras().getString("abdomen");
		String reresult = intent.getExtras().getString("result");
		WebView mywb = (WebView) findViewById(R.id.webview);
		mywb.getSettings().setDefaultTextEncodingName("utf-8");
		
		// 결과
		String strContent = "<div style=\"font-weight:bold; font-size:20px;\"><p align=\"justify\">"
				+ "비만도는 "
				+ "<font color=#FFA500>"
				+ overWeightPercent
				+ "</font>"
				+ "%, BMI지수는 "
				+ "<font color=#FFA500>"
				+ rebmi
				+ "</font>"
				+ "입니다. 또한 복부위험도 지수는 "
				+ "<font color=#FFA500>"
				+ reabdomen
				+ "</font>"
				+ "입니다. 비만가족력을 "
				+ "<font color=#FFA500>"
				+ refamily
				+"입니다."
				+ "</P></div>"
				+ "<div style=\"font-size:15px;\"><p align=\"justify\">";

		if (android.os.Build.VERSION.SDK_INT > 15) {
			mywb.loadData("<html><body>" + strContent + "</body></html>",
					"text/html; charset=UTF-8", null);
		} else {
			mywb.loadData("<html><body>" + strContent + "</body></html>",
					"text/html", "utf-8");
		}

		/*
		 * textset =(TextView)findViewById(R.id.textset);
		 * 
		 * textset.setText("비만도는 "+overWeightPercent+"%, BMI지수는 "+rebmi+
		 * "입니다. 또한 복부위험도 지수는 "+reabdomen+"입니다." + " 비만가족력을"+refamily+"있습니다.");
		 */

		textresult = (TextView) findViewById(R.id.result);
		indexresult = (TextView) findViewById(R.id.indexresult);

		textresult.setText("당신은 " + reage + "살 " + resex + "이고, 비만가족력을"
				+ refamily + "있습니다.\n" + "당신의 키는 " + recheight + "cm, 몸무게 "
				+ recweight + "kg, 허리둘레 " + recwaist + ", 엉덩이둘레 " + recass
				+ "입니다.");

		indexresult.setText(reresult);

		// 이상체중
		// String idealWeight = intent.getExtras().getString("idealWeight");
		// 비만도
		// 분류
		// String overWeight = intent.getExtras().getString("overWeight");

		/*
		 * indexobesity = (TextView)findViewById(R.id.index1_1); indexbmi =
		 * (TextView)findViewById(R.id.index1_2); indexabdomen =
		 * (TextView)findViewById(R.id.index2_1); indexrisk =
		 * (TextView)findViewById(R.id.index3_1);
		 * indexobesity.setText(overWeightPercent); indexbmi.setText(rebmi);
		 * indexabdomen.setText(reabdomen);
		 */

		// TextView result = (TextView)findViewById(R.id.receiveindex);
		/*
		 * WebView mywb = (WebView) findViewById(R.id.webview);
		 * 
		 * mywb.getSettings().setDefaultTextEncodingName("utf-8"); String user =
		 * "당신";
		 * 
		 * String strContent =
		 * "<div style=\"font-weight:bold; font-size:20px;\"><p align=\"justify\">"
		 * +"<br>" + user + "은 키 " + "<font color=#FFA500>" + recheight
		 * +"</font>" + "cm, 몸무게 " + "<font color=#FFA500>" + recweight
		 * +"</font>" + "kg이며" +"<br>" +"허리둘레  " + "<font color=#FFA500>" +
		 * recwaist + "</font>" +", 엉덩이둘레 " +"<font color=#FFA500>" + recass
		 * +"</font>" +"입니다." + "</P>";
		 * 
		 * if(android.os.Build.VERSION.SDK_INT > 15) {
		 * mywb.loadData("<html><body>" + strContent + "</body></html>",
		 * "text/html; charset=UTF-8", null); } else {
		 * mywb.loadData("<html><body>" + strContent + "</body></html>",
		 * "text/html", "utf-8"); }
		 */
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			onBackPressed();
			finish();

		}
		return true;
	}
}
