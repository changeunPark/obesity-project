package kr.co.imcc.app.uDiabetesNote;

import android.app.*;
import android.os.*;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.*;

public class tActivity1_8 extends Activity {

	private ActionBar actionBar;

	private ProgressBar progressbar; // ProgressBar

	static int position = 0;
	String question[] = { "1.나는 하루에 3끼니 식사한다.", "2.나는 아침 식사를 제대로 먹는다.",
			"3.나는 정해진 시간에 식사한다.", "4.나는 여유있게 천천히 식사한다.", "5.나는 과식을 하지 않는다.",
			"6.나는 곡류음식을 매끼 먹는다.", "7.나는 단백질 반찬을 매끼 먹는다.", "8.나는 채소반찬을 매끼 먹는다.",
			"9.나는 우유나 유제품을 매일 먹는다.", "10.나는 과일을 매일 먹는다.", "설문이 종료 되었습니다.\n계산을 위해 버튼을 눌러주세요."};

	String goodanswer[] = { "1.나는 하루에 3끼니 식사한다.", "2.나는 아침 식사를 제대로 먹는다.",
			"3.나는 정해진 시간에 식사한다.", "4.나는 여유있게 천천히 식사한다.", "5.나는 과식을 하지 않는다.",
			"6.나는 곡류음식을 매끼 먹는다.", "7.나는 단백질 반찬을 매끼 먹는다.", "8.나는 채소반찬을 매끼 먹는다.",
			"9.나는 우유나 유제품을 매일 먹는다.", "10.나는 과일을 매일 먹는다." };

	String badanswer[] = { "1.나는 하루에 3끼니 식사를 하지 않는다.",
			"2.나는 아침 식사를 제대로 먹지 않는다.", "3.나는 정해진 시간에 식사를 하지 않는다.",
			"4.나는 여유있게 천천히 식사를 하지 않는다.", "5.나는 과식을  한다.",
			"6.나는 곡류음식을 매끼 먹지 않는다.", "7.나는 단백질 반찬을 매끼 먹지 않는다.",
			"8.나는 채소반찬을 매끼 먹지 않는다.", "9.나는 우유나 유제품을 매일 먹지 않는다.",
			"10.나는 과일을 매일 먹지 않는다." };

	String normalanswer[] = { "1.나는 하루에 3끼니 식사한다.", "2.나는 아침 식사를 제대로 먹는다.",
			"3.나는 정해진 시간에 식사한다.", "4.나는 여유있게 천천히 식사한다.", "5.나는 과식을 하지 않는다.",
			"6.나는 곡류음식을 매끼 먹는다.", "7.나는 단백질 반찬을 매끼 먹는다.", "8.나는 채소반찬을 매끼 먹는다.",
			"9.나는 우유나 유제품을 매일 먹는다.", "10.나는 과일을 매일 먹는다." };

	int[] point = new int[10];

	int[] pointresult = new int[10];

	int[] goodpoint = new int[10];
	int[] normalpoint = new int[10];
	int[] badpoint = new int[10];

	int pointsum = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tactivity1_8);

		position = 0;
		pointsum = 0;
		actionBar = getActionBar();
		actionBar.setTitle("식습관 식이추천");
		getActionBar().setHomeButtonEnabled(true);
		getActionBar().setDisplayHomeAsUpEnabled(true);

		progressbar = (ProgressBar) findViewById(R.id.ProgressBar);
		progressbar.setProgress(0);

		// Thread 시작

		final TextView tquestion = (TextView) findViewById(R.id.tquestion);

		final RadioGroup rgSelect = (RadioGroup) findViewById(R.id.radiogroup);

		final RadioButton rb1 = (RadioButton) findViewById(R.id.radio_1);
		final RadioButton rb2 = (RadioButton) findViewById(R.id.radio_2);
		final RadioButton rb3 = (RadioButton) findViewById(R.id.radio_3);

		final Button btnpre = (Button) findViewById(R.id.btn_pre);
		final Button btnconfirm = (Button) findViewById(R.id.btn_confrim);
		final TextView pagenum = (TextView) findViewById(R.id.page_num);
		final Button btnnext = (Button) findViewById(R.id.btn_next);

		final TextView result[] = new TextView[30];
		result[0] = (TextView) findViewById(R.id.result0);
		result[1] = (TextView) findViewById(R.id.result1);
		result[2] = (TextView) findViewById(R.id.result2);
		result[3] = (TextView) findViewById(R.id.result3);
		result[4] = (TextView) findViewById(R.id.result4);
		result[5] = (TextView) findViewById(R.id.result5);
		result[6] = (TextView) findViewById(R.id.result6);
		result[7] = (TextView) findViewById(R.id.result7);
		result[8] = (TextView) findViewById(R.id.result8);
		result[9] = (TextView) findViewById(R.id.result9);

		

		
		
		btnpre.setOnClickListener(new OnClickListener() {

			
			
			@Override
			public void onClick(View v) {

				
				if(position==1)
				{
					btnpre.setVisibility(View.INVISIBLE);
					
				}
				
				
				if (rb1.isChecked() || rb2.isChecked() || rb3.isChecked()) {

				}

				position--;
				// TODO Auto-generated method stub
				tquestion.setText(question[position]);
				pagenum.setText((position+1) + "/10");
			}
		});

		btnconfirm.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				if(position==0)
				{
				rb1.setVisibility(View.VISIBLE);
				rb2.setVisibility(View.VISIBLE);
				rb3.setVisibility(View.VISIBLE);
				btnconfirm.setVisibility(View.INVISIBLE);
				pagenum.setVisibility(View.VISIBLE);
				btnnext.setVisibility(View.VISIBLE);
				}
				
				else if(position==10){
					thread.start();
				}
				
				if (rb1.isChecked() || rb2.isChecked() || rb3.isChecked()) {
				}

//				
				/*
				 * Intent intent = new Intent(tActivity1_6.this,
				 * kr.co.imcc.app.uDiabetesNote.tActivity1_6_1.class);
				 * startActivity(intent); finish();
				 */

				// TODO Auto-generated method stub

				tquestion.setText(question[position]);
				pagenum.setText((position+1) + "/10");
				

				
			}

		});
		btnnext.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				btnpre.setVisibility(View.VISIBLE);

				if (rb1.isChecked() || rb2.isChecked() || rb3.isChecked()) {

					if (rb1.isChecked()) {
						point[position] = 5;
						goodpoint[position] = 3;
						pointresult[position] = 1;
						/*
						 * tresult0.setText(goodanswer[position]);
						 * tresult1.setText(goodanswer[position]);
						 * tresult2.setText(goodanswer[position]);
						 * tresult3.setText(goodanswer[position]);
						 * tresult4.setText(goodanswer[position]);
						 * tresult5.setText(goodanswer[position]);
						 * tresult6.setText(goodanswer[position]);
						 * tresult7.setText(goodanswer[position]);
						 * tresult8.setText(goodanswer[position]);
						 * tresult9.setText(goodanswer[position]);
						 */
					} else if (rb2.isChecked()) {
						point[position] = 3;
						pointresult[position] = 2;
						/*
						 * tresult0.setText(question[position]);
						 * tresult1.setText(question[position]);
						 * tresult2.setText(question[position]);
						 * tresult3.setText(question[position]);
						 * tresult4.setText(question[position]);
						 * tresult5.setText(question[position]);
						 * tresult6.setText(question[position]);
						 * tresult7.setText(question[position]);
						 * tresult8.setText(question[position]);
						 * tresult9.setText(question[position]);
						 */

					} else if (rb3.isChecked()) {
						point[position] = 1;
						badpoint[position] = 1;
						pointresult[position] = 3;
						/*
						 * tresult0.setText(badanswer[position]);
						 * tresult1.setText(badanswer[position]);
						 * tresult2.setText(badanswer[position]);
						 * tresult3.setText(badanswer[position]);
						 * tresult4.setText(badanswer[position]);
						 * tresult5.setText(badanswer[position]);
						 * tresult6.setText(badanswer[position]);
						 * tresult7.setText(badanswer[position]);
						 * tresult8.setText(badanswer[position]);
						 * tresult9.setText(badanswer[position]);
						 */
					}
				}

				for (int i = 0; i < 10; i++) {
					if (pointresult[position] == 1) {
						result[i].setText(goodanswer[i]);
					} else if (pointresult[position] == 3) {

						result[i].setText(badanswer[i]);
					}

					else {
						result[i].setText(normalanswer[i]);
					}
					pointsum += point[i];
				}

				
				position++;
				rgSelect.clearCheck();

				
				// TODO Auto-generated method stub
				tquestion.setText(question[position]);
				pagenum.setText((position+1) + "/10");
				if(position==10){

					rb1.setVisibility(View.INVISIBLE);
					rb2.setVisibility(View.INVISIBLE);
					rb3.setVisibility(View.INVISIBLE);
					btnconfirm.setVisibility(View.VISIBLE);
					btnconfirm.setText("식습관에 따른 식이추천");
					btnpre.setVisibility(View.INVISIBLE);
					btnnext.setVisibility(View.INVISIBLE);
					pagenum.setVisibility(View.INVISIBLE);
					
				}
			}
		});
	}

	Thread thread = new Thread(new Runnable() {

		public void run() {

			// 1초에 한 번씩 Handler를 호출한다.(총 60번)
			for (int i = 0; i < 60; i++) {
				try {
					Thread.sleep(50);
					handler.sendMessage(handler.obtainMessage());

					/*
					 * if(i==59) { Intent intent = new Intent(tActivity1_6.this,
					 * kr.co.imcc.app.uDiabetesNote.tActivity1_6_1.class);
					 * startActivity(intent); finish(); }
					 */

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
