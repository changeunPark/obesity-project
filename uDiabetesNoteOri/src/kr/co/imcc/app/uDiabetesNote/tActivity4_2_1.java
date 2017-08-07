package kr.co.imcc.app.uDiabetesNote;

import android.app.*;
import android.content.*;
import android.os.*;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.*;

public class tActivity4_2_1 extends Activity {
	/** Called when the activity is first created. */

	private ProgressBar progressbar;

	String question1[] = { "1. 나는 감정 기복이 심하다.", "3. 나는 내 몸 가운데 쑤시는 곳이 있다.",
			"5. 나는 자신감이 없고 자기비하를 잘한다.", "7. 나는 쉽게 피로가 쌓인다.",
			"9. 나는 자주 폭식을 한다."};

	String question2[] = { "2. 나는 며칠사이에 피부에 염증이 많이 생겼다.", "4. 나는 잠을 잘 못 들거나 깊은 잠을 못 자고 자주 잠에서 깬다.",
			"6. 나는 항상 초조하다.", "8. 나는 한가지 일에 집중을 잘 못한다.", "10. 나는 건망증이 심하다."};
	/*String question[] = { "1. 나는 감정 기복이 심하다.",
			"2. 나는 며칠사이에 피부에 염증이 많이 생겼다.",
			"3. 나는 내 몸 가운데 쑤시는 곳이 있다.",
			"4. 나는 잠을 잘 못 들거나 깊은 잠을 못 자고 자주 잠에서 깬다.",
			"5. 나는 자신감이 없고 자기비하를 잘한다.", 
			"6. 나는 항상 초조하다.",
			"7. 나는 쉽게 피로가 쌓인다.", 
			"8. 나는 한가지 일에 집중을 잘 못한다.",
			"9. 나는 자주 폭식을 한다.",
			"10. 나는 건망증이 심하다."
			};*/
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
		actionBar.setTitle("스트레스에 의한  식이추천");
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

					if (0 <= totalSum && totalSum <= 15) { //
						resultanswer = "스트레스를 정상적으로 해소하고 있습니다.";

					} else if (16 <= totalSum && totalSum <= 20) {
						resultanswer = "스트레스를 조금 받고 있습니다. 스트레스를 줄여주세요.";

					} else if (21 <= totalSum && totalSum <= 25) {
						resultanswer = "스트레스가를 심하게 받고 있습니다. 구체적인 스트레스 해소 방법이 필요합니다.";
					} else if (26<=totalSum) {
						resultanswer = "스트레스로 인해 정신척, 육체적 고통을 받고 있습니다. 빠른 시일내에 의사에게 진료를 받아보세요.";
					}

					/*if(0<=totalSum && totalSum<=15){
						tvQuestion.setText("스트레스를 정상적으로 해소하고 있습니다.");
					}else if(16<=totalSum && totalSum<=20){
						tvQuestion.setText("스트레스를 조금 받고 있습니다. 스트레스를 줄여주세요.");
					}else if(21<=totalSum && totalSum<=25){
						tvQuestion.setText("스트레스가를 심하게 받고 있습니다. 구체적인 스트레스 해소 방법이 필요합니다.");
					}else if(26<=totalSum){
						tvQuestion.setText("스트레스로 인해 정신척, 육체적 고통을 받고 있습니다. 빠른 시일내에 의사에게 진료를 받아보세요.");
					}
					*/
					
					
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
								tActivity4_2_1.this,
								kr.co.imcc.app.uDiabetesNote.tActivity4_2_2.class);
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
			finish();

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

/*package kr.co.imcc.app.uDiabetesNote;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class tActivity4_2_1 extends Activity {
	*//** Called when the activity is first created. *//*

	String question[] = { "1. 나는 감정 기복이 심하다.",
			"2. 나는 며칠사이에 피부에 염증이 많이 생겼다.",
			"3. 나는 내 몸 가운데 쑤시는 곳이 있다.",
			"4. 나는 잠을 잘 못 들거나 깊은 잠을 못 자고 자주 잠에서 깬다.",
			"5. 나는 자신감이 없고 자기비하를 잘한다.", 
			"6. 나는 항상 초조하다.",
			"7. 나는 쉽게 피로가 쌓인다.", 
			"8. 나는 한가지 일에 집중을 잘 못한다.",
			"9. 나는 자주 폭식을 한다.",
			"10. 나는 건망증이 심하다."
			};

	int[] point = new int[] { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1 };
	static int position = 0;
	int totalSum=0;
    private ActionBar actionBar;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.layer4_stress_1);
		 actionBar = getActionBar();
			actionBar.setTitle("스트레스 자가진단");
			getActionBar().setDisplayHomeAsUpEnabled(true); 
		position = 0;
		totalSum = 0;		

//		Button btClose = (Button) findViewById(R.id.button_4_stress_1_close);
		final TextView tvQuestion = (TextView) findViewById(R.id.textview_4_stress_1_1);

		final ImageButton btPrev = (ImageButton) findViewById(R.id.button_layer4_stress_1_prev);
		final ImageButton btNext = (ImageButton) findViewById(R.id.button_layer4_stress_1_next);

		final TextView tvCurrent = (TextView) findViewById(R.id.textview_layer4_stress_1_current);

		final RadioGroup rgSelect = (RadioGroup) findViewById(R.id.radiogroup_4_stress_1_1);
		final RadioButton rb1 = (RadioButton) findViewById(R.id.radio_4_stress_1_1);
		final RadioButton rb2 = (RadioButton) findViewById(R.id.radio_4_stress_1_2);
		final RadioButton rb3 = (RadioButton) findViewById(R.id.radio_4_stress_1_3);
		final RadioButton rb4 = (RadioButton) findViewById(R.id.radio_4_stress_1_4);
		
//		Button button_back = (Button) findViewById(R.id.button_layer4_stress_1_back);

		button_back.setOnClickListener(new OnClickListener() { // back

			@Override
			public void onClick(View v) {
				
//				onBackPressed();
				finish();

			}
		});

		btClose.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				point = new int[] { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
						-1, -1, -1, -1, -1, -1, -1, -1, -1 };
				
				position = 0;
				totalSum = 0;			

				finish();

			}
		});

		tvQuestion.setText(question[0]);
		btPrev.setVisibility(View.INVISIBLE);
		tvCurrent.setText(question[0].substring(0, 1) + "/10");

		btNext.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				if (rb1.isChecked() || rb2.isChecked() || rb3.isChecked()
						|| rb4.isChecked()) {
					// Toast.makeText(getApplicationContext(), "����",
					// Toast.LENGTH_SHORT).show();

					
					if (rb1.isChecked()) {
						point[position] = 0;
					} else if (rb2.isChecked()) {
						point[position] = 1;
					} else if (rb3.isChecked()) {
						point[position] = 2;
					} else if (rb4.isChecked()) {
						point[position] = 3;
					}

//					Toast.makeText(getApplicationContext(),
//							point[position] + "", Toast.LENGTH_SHORT).show();
					rgSelect.clearCheck();

					position++;

					if (point[position] != -1) {

						
						if (point[position] == 0) {
							rgSelect.check(R.id.radio_4_stress_1_1);
						} else if (point[position] == 1) {
							rgSelect.check(R.id.radio_4_stress_1_2);
						} else if (point[position] == 2) {
							rgSelect.check(R.id.radio_4_stress_1_3);
						} else if (point[position] == 3) {
							rgSelect.check(R.id.radio_4_stress_1_4);
						}
					}

					if (position == 0) {
						btPrev.setVisibility(View.INVISIBLE);

					} else if (position == 8) {
						btNext.setBackgroundResource(R.drawable.next);
//						btNext.setText("�������");
					} else if (position == 9) {
						btNext.setBackgroundResource(R.drawable.next);
//						btNext.setText("�������");
					} else {
						btPrev.setVisibility(View.VISIBLE);
						btNext.setVisibility(View.VISIBLE);
					}

					if (position == 10) {
						
						btPrev.setVisibility(View.INVISIBLE);
						btNext.setVisibility(View.INVISIBLE);
						rb1.setVisibility(View.INVISIBLE);
						rb2.setVisibility(View.INVISIBLE);
						rb3.setVisibility(View.INVISIBLE);
						rb4.setVisibility(View.INVISIBLE);
						tvCurrent.setVisibility(View.INVISIBLE);
						
						for(int i=0; i<10; i++){
							totalSum += point[i];
						}
						
						if(0<=totalSum && totalSum<=15){
							tvQuestion.setText("스트레스를 정상적으로 해소하고 있습니다.");
						}else if(16<=totalSum && totalSum<=20){
							tvQuestion.setText("스트레스를 조금 받고 있습니다. 스트레스를 줄여주세요.");
						}else if(21<=totalSum && totalSum<=25){
							tvQuestion.setText("스트레스가를 심하게 받고 있습니다. 구체적인 스트레스 해소 방법이 필요합니다.");
						}else if(26<=totalSum){
							tvQuestion.setText("스트레스로 인해 정신척, 육체적 고통을 받고 있습니다. 빠른 시일내에 의사에게 진료를 받아보세요.");
						}

					} else {
						tvQuestion.setText(question[position]);

						if (position > 8) {
							tvCurrent.setText(question[position]
									.substring(0, 2) + "/10");
						} else {
							tvCurrent.setText(question[position]
									.substring(0, 1) + "/10");
						}

					}
				} else {

//					Toast.makeText(getApplicationContext(), "�׸��� ���õ��� �ʾҽ��ϴ�.",
//							Toast.LENGTH_SHORT).show();
					AlertDialog alert = new AlertDialog.Builder(tActivity4_2_1.this)					
					.setTitle("�˸�")
					.setMessage("�׸��� ���õ��� �ʾҽ��ϴ�.")
					.setPositiveButton( "Ȯ��", new DialogInterface.OnClickListener()
					{
						@Override
						public void onClick(DialogInterface dialog, int which)
						{
							dialog.dismiss();
						}
					})
					.show();
					Toast.makeText(getApplicationContext(), "보기를 선택해주세요.", 1000).show();

				}

			}
		});

		btPrev.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				position--;

				
				if (point[position] == 0) {
					rgSelect.check(R.id.radio_4_stress_1_1);
				} else if (point[position] == 1) {
					rgSelect.check(R.id.radio_4_stress_1_2);
				} else if (point[position] == 2) {
					rgSelect.check(R.id.radio_4_stress_1_3);
				} else if (point[position] == 3) {
					rgSelect.check(R.id.radio_4_stress_1_4);
				}

//				Toast.makeText(getApplicationContext(), point[position] + "",
//						Toast.LENGTH_SHORT).show();

				if (position == 0) {
					btPrev.setVisibility(View.INVISIBLE);

				} else if (position == 8) {
					btNext.setBackgroundResource(R.drawable.next);
//					btNext.setText("�������");
				} else if (position == 9) {
					btNext.setBackgroundResource(R.drawable.next);
//					btNext.setText("�������");
				} else {
					btPrev.setVisibility(View.VISIBLE);
					btNext.setVisibility(View.VISIBLE);
//					btNext.setText("����");
				}

				tvQuestion.setText(question[position]);

				if (position > 8) {
					tvCurrent.setText(question[position].substring(0, 2)
							+ "/10");
				} else {
					tvCurrent.setText(question[position].substring(0, 1)
							+ "/10");
				}

			}
		});

	}
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	      case android.R.id.home:
	        onBackPressed();
	        finish();
	        
	    }
	    return true;
	}
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		
		Intent i = new Intent(
				tActivity4_2_1.this,
				kr.co.imcc.app.uDiabetesNote.Activity4_menu.class);
		// i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

		startActivity(i);		

		finish();
		
	}
}*/