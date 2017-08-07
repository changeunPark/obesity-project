package kr.co.imcc.app.uDiabetesNote;

import java.text.*;

import android.app.*;
import android.content.*;
import android.os.*;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.*;

public class tActivity1_5 extends Activity {
	/** Called when the activity is first created. */

	private ActionBar actionBar;

	private ProgressBar progressbar;

	EditText edit_height, edit_weight, edit_waist, edit_ass, edit_age;
	RadioGroup rgSex, rgChoose;
	RadioButton rmale, rfemale, rYes, rNo;

	static double agenumber;

	// 이상체중
	static double idealWeight;

	// 비만도퍼센트
	static double overWeightPercent;

	static double abdomen;

	static double bmi;

	static double height;

	static double weight;

	static double waist;

	static double ass;

	static String family;
	
	static String sex;
	
	static String strresultindex;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tactivity1_5);

		actionBar = getActionBar();
		actionBar.setTitle("비만위험도에 의한 식이추천");
		getActionBar().setHomeButtonEnabled(true);
		getActionBar().setDisplayHomeAsUpEnabled(true);

		rgChoose = (RadioGroup) findViewById(R.id.radiogroup_3_1_2);
		rYes = (RadioButton) findViewById(R.id.ryes);
		rNo = (RadioButton) findViewById(R.id.rno);

		rgSex = (RadioGroup) findViewById(R.id.radiogroup_3_1_1);
		rmale = (RadioButton) findViewById(R.id.radio_male);
		rfemale = (RadioButton) findViewById(R.id.radio_female);

		edit_age = (EditText) findViewById(R.id.age);

		edit_height = (EditText) findViewById(R.id.insert_height);
		edit_weight = (EditText) findViewById(R.id.insert_weight);
		edit_waist = (EditText) findViewById(R.id.insert_waist);
		edit_ass = (EditText) findViewById(R.id.insert_ass);

		final Button button_result = (Button) findViewById(R.id.button_result);

		progressbar = (ProgressBar) findViewById(R.id.ProgressBar);
		final TextView progresstext = (TextView) findViewById(R.id.progresstext);
		progressbar.setProgress(0);

		button_result.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				if (!rmale.isChecked() && !rfemale.isChecked()) {
					Toast.makeText(getApplicationContext(), "성별을 선택해 주세요.",
							1000).show();
				} else if (edit_height.getText().toString().equals("")) {
					Toast.makeText(getApplicationContext(), "키를 입력해주세요", 1000)
							.show();
				} else if (edit_weight.getText().toString().equals("")) {
					Toast.makeText(getApplicationContext(), "몸무게를 입력해주세요", 1000)
							.show();
				} else if (edit_waist.getText().toString().equals("")) {
					Toast.makeText(getApplicationContext(), "허리둘레를 입력해주세요",
							1000).show();
				} else if (edit_ass.getText().toString().equals("")) {
					Toast.makeText(getApplicationContext(), "엉덩이둘레를 입력해주세요",
							1000).show();
				} else if (!rNo.isChecked() && !rYes.isChecked()) {
					Toast.makeText(getApplicationContext(),
							"가족 중 비만 여부를 선택해 주세요.", 1000).show();
				}

				else {
					button_result.setVisibility(View.INVISIBLE);
					showResult();

					progressbar.setVisibility(View.VISIBLE);

					thread.start();
					progresstext.setVisibility(View.VISIBLE);
				}
			}

		});
	}

	public void showResult() {

		Double agenumber = Double.parseDouble(edit_age.getText().toString());
		// 비만도 계산
		height = Double.parseDouble(edit_height.getText().toString());
		weight = Double.parseDouble(edit_weight.getText().toString());
		waist = Double.parseDouble(edit_waist.getText().toString());
		ass = Double.parseDouble(edit_ass.getText().toString());

		// 결과판별값

		// 비만분류
		final String overWeight = "";
		// BMI계산
		final Double heightMeter = Double.parseDouble(edit_height.getText()
				.toString()) / 100.0;

		int overWeightPercentindex = 0;
		int resultindex = 0;
		int abdomenindex = 0;
		int bmiindex = 0;
		int familyindex = 0;

		String overWeightbmi = "";

		if(rYes.isChecked())
		{
			familyindex=2;
			family = " 가지고 ";
		}
		else if(rNo.isChecked())
		{
			familyindex=1;
			family = " 가지고 있지 않고 ";
		}
		
		if(rmale.isChecked()){
			sex="남자";
			
		}
		else if(rfemale.isChecked()){
			sex="여자";
		}
		
		
		// 비만도 계산
		if (height > 160.0) {
			idealWeight = (height - 100.0) * 0.9;
		} else if (height > 150.0 & height <= 160.0) {
			idealWeight = (height - 150.0) * 0.5 + 50.0;
		} else {
			idealWeight = height - 100.0;
		}

		overWeightPercent = (weight / idealWeight) * 100;

		// 비만분류 계산식
		/*
		 * if(overWeightPercent <= 90.0){ overWeight = "저체중"; }else
		 * if(overWeightPercent>90.0 & overWeightPercent <= 110.0){ overWeight =
		 * "정상"; }else if(overWeightPercent>110.0 & overWeightPercent <= 120.0){
		 * overWeight = "과체중"; }else if(overWeightPercent>120.0){ overWeight =
		 * "비만"; }
		 */

		// BMI계산
		bmi = weight / (heightMeter * heightMeter);
		bmi = ((int) (bmi * 10.0)) / 10.0;

		/*
		 * 
		 * if(bmi>18.5 & bmi <= 22.0){ overWeightbmi = "저체중"; }else if(bmi>22.0
		 * & bmi <= 23.0){ overWeightbmi = "정상체중"; }else if(bmi>23.0 & bmi <=
		 * 25.0){ overWeightbmi = "위험체중"; }else if(bmi>25.0 & bmi <= 30.0){
		 * overWeightbmi = "비만"; }else if(bmi>30.0){ overWeightbmi = "고도비만"; }
		 */

		// 복부비만도 계싼
		abdomen = waist / ass;

		// 비만도 계산식
		if (overWeightPercent <= 90.0) {
			overWeightPercentindex = 1;
		} else if (overWeightPercent > 90.0 & overWeightPercent <= 110.0) {
			overWeightPercentindex = 2;
		} else if (overWeightPercent > 110.0 & overWeightPercent <= 120.0) {
			overWeightPercentindex = 3;
		} else if (overWeightPercent > 120.0) {
			overWeightPercentindex = 4;
		}

		// bmi지수 계산식
		if (bmi > 18.5 & bmi <= 22.0) {
			bmiindex = 1;
		} else if (bmi > 22.0 & bmi <= 23.0) {
			bmiindex = 2;
		} else if (bmi > 23.0 & bmi <= 25.0) {
			bmiindex = 3;
		} else if (bmi > 25.0) {
			bmiindex = 4;
		}

		// 복부비만도 계산식
		if (rmale.isChecked()) { // ����

			if (abdomen > 1.0) {
				abdomenindex = 2;
			} else {
				abdomenindex = 1;
			}

		} else {// ����

			if (abdomen > 0.85) {
				abdomenindex = 2;
			} else {
				abdomenindex = 1;
			}
		}

		// 가족 중 비만경력 계산식

		if (rYes.isChecked()) {
			familyindex = 2;
		} else {
			familyindex = 1;
		}

		resultindex = overWeightPercentindex + bmiindex + abdomenindex
				+ familyindex;
		String strresultindex;
		if (resultindex > 10) {
			strresultindex = "위험";
		} else if (resultindex > 8) {
			strresultindex = "높음";
		} else if (resultindex > 5) {
			strresultindex = "보통";
		} else {
			strresultindex = "낮음";
		}

	}

	public String longDouble2String(int size, double value) {

		NumberFormat nf = NumberFormat.getNumberInstance();

		nf.setMaximumFractionDigits(size);

		nf.setGroupingUsed(false);

		return nf.format(value);
	}

	Thread thread = new Thread(new Runnable() {

		public void run() {

			// 1초에 한 번씩 Handler를 호출한다.(총 60번)
			for (int i = 0; i < 60; i++) {
				try {
					Thread.sleep(50);
					handler.sendMessage(handler.obtainMessage());

					if (i == 59) {
						// 결과 엑티비티 값 전송

						String strage = longDouble2String(1, agenumber);
						String strIdealWeight = longDouble2String(1,idealWeight);
						String strOverWeightPercent = longDouble2String(1,	overWeightPercent);
						String strabdomen = longDouble2String(2, abdomen);
						String strBmi = longDouble2String(1, bmi);
						String strheight = longDouble2String(1, height);
						String strweight = longDouble2String(1, weight);
						String strwaist = longDouble2String(1, waist);
						String strass = longDouble2String(1, ass);

						Intent result = new Intent(tActivity1_5.this,	tActivity1_5_1.class);

						result.putExtra("overage", strage);
						result.putExtra("overfamily", family);
						result.putExtra("oversex",sex);
						result.putExtra("overheight", strheight);
						result.putExtra("overweight", strweight);
						result.putExtra("overwaist", strwaist);
						result.putExtra("overass", strass);
						result.putExtra("overWeightPercent",	strOverWeightPercent);
						result.putExtra("bmi", strBmi);
						result.putExtra("abdomen", strabdomen);
						result.putExtra("result", strresultindex);
						startActivity(result);
						finish();
					}

				} catch (InterruptedException e) {
				}
			}
		}
	});

	// Handler 구현
	Handler handler = new Handler() {

		public void handleMessage(Message msg) {

			// ProgressBar를 1씩 증가한다.
			progressbar.incrementProgressBy(1);

		};
	};

	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			onBackPressed();
			finish();

		}
		return true;
	}
}
