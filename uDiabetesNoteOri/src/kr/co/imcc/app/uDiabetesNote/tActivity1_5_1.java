package kr.co.imcc.app.uDiabetesNote;

import android.app.*;
import android.content.*;
import android.os.*;
import android.view.*;
import android.webkit.*;
import android.widget.*;

public class tActivity1_5_1 extends Activity {
	/** Called when the activity is first created. */

	private ActionBar actionBar;
	TextView index1, index2, index3, index4, indexresult, textresult;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tactivity1_5_1);

		actionBar = getActionBar();
		actionBar.setTitle("비만위험도에 의한 식이추천");
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

		String overWeightPercent = intent.getExtras().getString("overWeightPercent");
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
		// 비만도
		index1 = (TextView) findViewById(R.id.index1);
		// BMI지수
		index2 = (TextView) findViewById(R.id.index2);
		// 복부비만도
		index3 = (TextView) findViewById(R.id.index3);
		// 비만가족력
		index4 = (TextView) findViewById(R.id.index4);

		index1.setText(overWeightPercent);
		index2.setText(rebmi);
		index3.setText(reabdomen);
		index4.setText(refamily);
*/
		/*textresult = (TextView) findViewById(R.id.result);
		indexresult = (TextView) findViewById(R.id.indexresult);

		textresult.setText("당신은 " + reage + "살 " + resex + "이고, 비만가족력을"
				+ refamily + "있습니다.\n" + "당신의 키는 " + recheight + ", 몸무게 "
				+ recweight + ", 허리둘레 " + recwaist + ", 엉덩이둘레 " + recass
				+ "입니다.");
		indexresult.setText(reresult);*/

		TextView imagetext = (TextView) findViewById(R.id.foodhabit);
		ImageView resultimage = (ImageView) findViewById(R.id.resultimage);

		int imagenum = (int) (Math.random() * 5);

		switch (imagenum) {
		case 0:
			resultimage.setImageResource(R.drawable.refood1);
			imagetext.setText("헬스 전문가와 영영사에 의한 식습관에 따른 오늘의 추천메뉴는 순두부찌개입니다.");
			break;
		case 1:
			resultimage.setImageResource(R.drawable.refood2);
			imagetext.setText("헬스 전문가와 영영사에 의한 식습관에 따른 오늘의 추천메뉴는 청국장입니다.");
			break;
		case 2:
			resultimage.setImageResource(R.drawable.refood3);
			imagetext.setText("헬스 전문가와 영영사에 의한 식습관에 따른 오늘의 추천메뉴는 콩국수입니다.");
			break;
		case 3:
			resultimage.setImageResource(R.drawable.refood4);
			imagetext.setText("헬스 전문가와 영영사에 의한 식습관에 따른 오늘의 추천메뉴는 비빔밥입니다.");
			break;
		case 4:
			resultimage.setImageResource(R.drawable.refood5);
			imagetext.setText("헬스 전문가와 영영사에 의한 식습관에 따른 오늘의 추천메뉴는 삼계탕입니다.");
			break;
		}

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
