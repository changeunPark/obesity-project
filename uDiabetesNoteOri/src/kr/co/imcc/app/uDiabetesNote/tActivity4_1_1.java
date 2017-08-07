package kr.co.imcc.app.uDiabetesNote;

import android.app.*;
import android.content.*;
import android.os.*;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.*;

public class tActivity4_1_1 extends Activity {
	/** Called when the activity is first created. */

	private ProgressBar progressbar;

	String question1[] = { "1. 나는 하루에 3끼니 식사한다.", "3. 나는 정해진 시간에 식사한다.",
			"5. 나는 과식을 하지 않는다.", "7. 나는 단백질 반찬을 매끼 먹는다.",
			"9. 나는 우유나 유제품을 매일 먹는다.", "" };

	String question2[] = { "2. 나는 아침 식사를 제대로 먹는다.", "4. 나는 여유있게 천천히 식사한다.",
			"6. 나는 곡류음식을 매끼 먹는다.", "8. 나는 채소반찬을 매끼 먹는다.", "10. 나는 과일을 매일 먹는다.",
			"" };

	String resultanswer;

	int[] point = new int[20];

	// String[] resultpoint = new String[10];

	String[] resultpoint1 = new String[20];
	String[] resultpoint2 = new String[20];

	static int position = 0;
	int totalSum = 0;
	private ActionBar actionBar;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.tactivity1_6_1);

		actionBar = getActionBar();
		actionBar.setTitle("비만식습관에 의한  식이추천");
		getActionBar().setDisplayHomeAsUpEnabled(true);
		position = 0;
		totalSum = 0;

		// Button btClose = (Button) findViewById(R.id.button_4_1_close);
		final TextView tvQuestion1 = (TextView) findViewById(R.id.textview_4_1_1);
		final TextView tvQuestion2 = (TextView) findViewById(R.id.textview_4_1_2);

		final ImageButton btPrev = (ImageButton) findViewById(R.id.button_layer4_1_prev);
		final ImageButton btNext = (ImageButton) findViewById(R.id.button_layer4_1_next);

		final TextView tvCurrent = (TextView) findViewById(R.id.textview_layer4_1_current);

		final RadioGroup rgSelect1 = (RadioGroup) findViewById(R.id.radiogroup_4_1_1);
		final RadioButton rb1_1 = (RadioButton) findViewById(R.id.radio_4_1_1);
		final RadioButton rb1_2 = (RadioButton) findViewById(R.id.radio_4_1_2);
		final RadioButton rb1_3 = (RadioButton) findViewById(R.id.radio_4_1_3);
		final RadioButton rb1_4 = (RadioButton) findViewById(R.id.radio_4_1_4);

		final RadioGroup rgSelect2 = (RadioGroup) findViewById(R.id.radiogroup_4_1_2);
		final RadioButton rb2_1 = (RadioButton) findViewById(R.id.radio_4_2_1);
		final RadioButton rb2_2 = (RadioButton) findViewById(R.id.radio_4_2_2);
		final RadioButton rb2_3 = (RadioButton) findViewById(R.id.radio_4_2_3);
		final RadioButton rb2_4 = (RadioButton) findViewById(R.id.radio_4_2_4);

		progressbar = (ProgressBar) findViewById(R.id.ProgressBar);
		final TextView progresstext= (TextView)findViewById(R.id.progresstext);
		progressbar.setProgress(0);

		tvQuestion1.setText(question1[0]);
		tvQuestion2.setText(question2[0]);

		btPrev.setVisibility(View.INVISIBLE);

		tvCurrent.setText(question1[0].substring(0, 1) + "/5");

		btNext.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				if ((rb1_1.isChecked() || rb1_2.isChecked()
						|| rb1_3.isChecked() || rb1_4.isChecked())
						&& (rb2_1.isChecked() || rb2_2.isChecked()
								|| rb2_3.isChecked() || rb2_4.isChecked()))

				{
					if (rb1_1.isChecked() || rb1_2.isChecked()
							|| rb1_3.isChecked() || rb1_4.isChecked()) {

						for (int i = 0; i < 5; i++) {
							if (position == i) {
								if (rb1_1.isChecked()) {
									resultpoint1[i] = "a";
								} else if (rb1_2.isChecked()) {
									resultpoint1[i] = "b";
								} else if (rb1_3.isChecked()) {
									resultpoint1[i] = "c";
								} else {
									resultpoint1[i] = "d";
								}
							}

						}

						if (rb1_1.isChecked()) {
							point[position] = 0;
						} else if (rb1_2.isChecked()) {
							point[position] = 1;
						} else if (rb1_3.isChecked()) {
							point[position] = 2;
						} else if (rb1_4.isChecked()) {
							point[position] = 3;
						}

					}

					else {
						Toast.makeText(getApplicationContext(), "보기를 선택해주세요.",
								5).show();
					}

					if (rb2_1.isChecked() || rb2_2.isChecked()
							|| rb2_3.isChecked() || rb2_4.isChecked()) {

						for (int i = 0; i < 5; i++) {
							if (position == i) {
								if (rb2_1.isChecked()) {
									resultpoint2[i] = "a";
								} else if (rb2_2.isChecked()) {
									resultpoint2[i] = "b";
								} else if (rb2_3.isChecked()) {
									resultpoint2[i] = "c";
								} else {
									resultpoint2[i] = "d";
								}
							}

						}

						if (rb2_1.isChecked()) {
							point[position] = 0;
						} else if (rb2_2.isChecked()) {
							point[position] = 1;
						} else if (rb2_3.isChecked()) {
							point[position] = 2;
						} else if (rb2_4.isChecked()) {
							point[position] = 3;
						}

					}

					else {

						Toast.makeText(getApplicationContext(), "보기를 선택해주세요.",
								5).show();
					}

					position++;

					if (position == 1) {
						btPrev.setVisibility(View.VISIBLE);
						btNext.setVisibility(View.VISIBLE);
						tvQuestion1.setText(question1[position]);
						tvQuestion2.setText(question2[position]);
						tvCurrent.setText((position + 1) + "/5");
						rgSelect1.clearCheck();
						rgSelect2.clearCheck();
					}

					else if (position == 5) {
						btPrev.setVisibility(View.INVISIBLE);
						btNext.setVisibility(View.INVISIBLE);
						progressbar.setVisibility(View.VISIBLE);
						progresstext.setVisibility(View.VISIBLE);
						thread.start();
					} else {
						tvQuestion1.setText(question1[position]);
						tvQuestion2.setText(question2[position]);
						tvCurrent.setText((position + 1) + "/5");
						rgSelect1.clearCheck();
						rgSelect2.clearCheck();
					}

					for (int i = 0; i < 5; i++) {
						totalSum += point[i];
					}

					if (0 <= totalSum && totalSum <= 12) { //
						resultanswer = "귀하의 식습관은 불량한 수준이어서 개선이 필요합니다.";

					} else if (13 <= totalSum && totalSum <= 18) {
						resultanswer = "귀하의 식습관 수준은 건강 상의 이점을 최대한 누릴 수 있을 만큼  크게 높은 것은 아닙니다.";

					} else if (19 <= totalSum && totalSum <= 24) {
						resultanswer = "귀하께서는 양호한 식습관을 유지하고 계십니다.";
					} else if (totalSum >= 25 && totalSum < 31) {
						resultanswer = "귀하께서는 매우 양호한 식습관을 하고 계십니다. 현 생활을 지속적으로 유지하시어 활력있는 삶을 영위하시기 바랍니다.";
					}

				} else {
					Toast.makeText(getApplicationContext(), "보기를 선택해주세요.", 5)
							.show();
				}

			}

		});


		btPrev.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				position--;

				if (position == 0) { 
					btPrev.setVisibility(View.INVISIBLE);

				} else {
					btPrev.setVisibility(View.VISIBLE);
					btNext.setVisibility(View.VISIBLE);
				}

				tvQuestion1.setText(question1[position]);
				tvQuestion2.setText(question2[position]);

				// 수정필요
				tvCurrent.setText((position + 1) + "/5");
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

					if (i == 59) {
						Intent intent = new Intent(
								tActivity4_1_1.this,
								kr.co.imcc.app.uDiabetesNote.tActivity4_1_2.class);
						intent.putExtra("result", resultanswer);
						intent.putExtra("choicepoint1", resultpoint1);
						intent.putExtra("choicepoint2", resultpoint2);
						startActivity(intent);
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

		}
		return true;
	}
	/*
	 * @Override public void onBackPressed() { super.onBackPressed();
	 * 
	 * Intent i = new Intent( tActivity4_1_1.this,
	 * kr.co.imcc.app.uDiabetesNote.Activity4_menu.class); //
	 * i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	 * 
	 * startActivity(i);
	 * 
	 * finish();
	 * 
	 * }
	 */
}