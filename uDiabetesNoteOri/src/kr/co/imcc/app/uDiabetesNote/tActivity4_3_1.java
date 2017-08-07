package kr.co.imcc.app.uDiabetesNote;

import android.app.*;
import android.content.*;
import android.os.*;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.*;

public class tActivity4_3_1 extends Activity {
	/** Called when the activity is first created. */

	private ProgressBar progressbar;

	String question1[] = { "1. 나는 평소에는 아무렇지도 않던 일들이 괴롭고 귀찮게 느껴진다.", "3. 나는 다른 사람이 내 기분을 풀어줄거라 생각하지 않는다.",
			"5. 나는 지금 행복하다.", "7. 나는 지금 모든게 다 힘들다.",
			"9. 나는 지금까지 내인생이 다 헛된것이라고 생각한다."};

	String question2[] = { "2. 나는 요즘 식욕이 없다.", "4. 나는 어떠한 일에도 집중을 할 수가 없다.",
			"6. 나는 지금 우울하다.", "8. 나는 지금 내가 뭘해야될지 모르겠다.", "10. 나는 보통사람들과 같은 능력을 가지고 있다고 생각한다." };

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
		actionBar.setTitle("우울증에 의한  식이추천");
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
						resultanswer = "귀하께서는 긍정적인 생활을 하고 계십니다.";

					} else if (13 <= totalSum && totalSum <= 18) {
						resultanswer = "귀하께서는 정상적인생활을 하고 계십니다.";

					} else if (19 <= totalSum && totalSum <= 24) {
						resultanswer = "귀하께서는 우울증을 앓고 있다고 판단됩니다. 증상이 심해진다면 의사의 진료를 받아보세요.";
					} else if (totalSum >= 25 && totalSum < 31) {
						resultanswer = "귀하께서는 심한 우울증을 앓고 있다고 판단됩니다.  빠른 시일 내에 의사의 진료를 받아보세요.";
					}

					/*if(0<=totalSum && totalSum<=15){
						tvQuestion.setText("당신의 정상적인 사람입니다.");
					}else if(16<=totalSum && totalSum<=23){
						tvQuestion.setText("당신 가벼운 우울증을 앓고 있다고 판단됩니다. 증상이 심해진다면 의사의 진료를 받아보세요.");
					}else if(24<=totalSum){
						tvQuestion.setText("당신의 심한 우울증을 앓고 있다고 판단됩니다.  빠른 시일 내에 의사의 진료를 받아보세요.");
					}*/
					
					
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
								tActivity4_3_1.this,
								kr.co.imcc.app.uDiabetesNote.tActivity4_3_2.class);
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

public class tActivity4_3_1 extends Activity {
	*//** Called when the activity is first created. *//*

	String question[] = { 
			"1. 나는 평소에는 아무렇지도 않던 일들이 괴롭고 귀찮게 느껴진다.",
			"2. 나는 요즘 식욕이 없다.",
			"3. 나는 다른 사람이 내 기분을 풀어줄거라 생각하지 않는다.",
			"4. 나는 어떠한 일에도 집중을 할 수가 없다.",
			"5. 나는 지금 행복하다.",
			"6. 나는 지금 우울하다.",
			"7. 나는 지금 모든게 다 힘들다.",
			"8. 나는 지금 내가 뭘해야될지 모르겠다.",
			"9. 나는 지금까지 내인생이 다 헛된것이라고 생각한다.",
			"10. 나는 보통사람들과 같은 능력을 가지고 있다고 생각한다.",
			"11. 나는 잠을 쉽게 들지 못한다.",
			"12. 나는 두려움을 느낀다.",
			"13. 나는 말하는 것이 귀찮아졌다.",
			"14. 나는 외로움을 자주 느낀다.",
			"15. 나는 인생에 불만 없이 잘지내 왔다.",
			"16. 나는 다른사람들이 나를 차별하는거 같다.",
			"17. 나는 갑자기 눈물이 떨어진 적이 있다.",
			"18. 나는 갑자기 가슴이 먹먹해진 적이 있다.",
			"19. 나는 다른사람들이 나를 싫어하는 것 같다고 생각해본적이 있다.",
			"20. 나는 내가 앞으로 무엇을 해야될지 모르겠다."
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
		setContentView(R.layout.layer4_1);
		actionBar = getActionBar();
		actionBar.setTitle("우울증 자가진단");
		getActionBar().setDisplayHomeAsUpEnabled(true); 
		position = 0;
		totalSum = 0;		

//		Button btClose = (Button) findViewById(R.id.button_4_1_close);
		final TextView tvQuestion = (TextView) findViewById(R.id.textview_4_1_1);

		final ImageButton btPrev = (ImageButton) findViewById(R.id.button_layer4_1_prev);
		final ImageButton btNext = (ImageButton) findViewById(R.id.button_layer4_1_next);

		final TextView tvCurrent = (TextView) findViewById(R.id.textview_layer4_1_current);

		final RadioGroup rgSelect = (RadioGroup) findViewById(R.id.radiogroup_4_1_1);
		final RadioButton rb1 = (RadioButton) findViewById(R.id.radio_4_1_1);
		final RadioButton rb2 = (RadioButton) findViewById(R.id.radio_4_1_2);
		final RadioButton rb3 = (RadioButton) findViewById(R.id.radio_4_1_3);
		final RadioButton rb4 = (RadioButton) findViewById(R.id.radio_4_1_4);
		
//		Button button_back = (Button) findViewById(R.id.button_layer4_1_back);

		button_back.setOnClickListener(new OnClickListener() { // back

			@Override
			public void onClick(View v) {
				
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
		tvCurrent.setText(question[0].substring(0, 1) + "/20");

		btNext.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				if (rb1.isChecked() || rb2.isChecked() || rb3.isChecked()
						|| rb4.isChecked()) {
					// Toast.makeText(getApplicationContext(), "����",
					// Toast.LENGTH_SHORT).show();

					if (position == 4 || position == 9 || position == 14) {
						if (rb1.isChecked()) {
							point[position] = 3;
						} else if (rb2.isChecked()) {
							point[position] = 2;
						} else if (rb3.isChecked()) {
							point[position] = 1;
						} else if (rb4.isChecked()) {
							point[position] = 0;
						}
					} else {
						if (rb1.isChecked()) {
							point[position] = 0;
						} else if (rb2.isChecked()) {
							point[position] = 1;
						} else if (rb3.isChecked()) {
							point[position] = 2;
						} else if (rb4.isChecked()) {
							point[position] = 3;
						}
					}

//					Toast.makeText(getApplicationContext(),
//							point[position] + "", Toast.LENGTH_SHORT).show();
					rgSelect.clearCheck();

					position++;

					if (point[position] != -1) {

						if (position == 4 || position == 9 || position == 14) {
							if (point[position] == 3) {
								rgSelect.check(R.id.radio_4_1_1);
							} else if (point[position] == 2) {
								rgSelect.check(R.id.radio_4_1_2);
							} else if (point[position] == 1) {
								rgSelect.check(R.id.radio_4_1_3);
							} else if (point[position] == 0) {
								rgSelect.check(R.id.radio_4_1_4);
							}
						} else {
							if (point[position] == 0) {
								rgSelect.check(R.id.radio_4_1_1);
							} else if (point[position] == 1) {
								rgSelect.check(R.id.radio_4_1_2);
							} else if (point[position] == 2) {
								rgSelect.check(R.id.radio_4_1_3);
							} else if (point[position] == 3) {
								rgSelect.check(R.id.radio_4_1_4);
							}
						}
					}

					if (position == 0) {
						btPrev.setVisibility(View.INVISIBLE);

					} else if (position == 18) {
						btNext.setBackgroundResource(R.drawable.next);
//						btNext.setText("�������");
					} else if (position == 19) {
						btNext.setBackgroundResource(R.drawable.next);
//						btNext.setText("�������");
					} else {
						btPrev.setVisibility(View.VISIBLE);
						btNext.setVisibility(View.VISIBLE);
					}

					if (position == 20) {
						
						btPrev.setVisibility(View.INVISIBLE);
						btNext.setVisibility(View.INVISIBLE);
						rb1.setVisibility(View.INVISIBLE);
						rb2.setVisibility(View.INVISIBLE);
						rb3.setVisibility(View.INVISIBLE);
						rb4.setVisibility(View.INVISIBLE);
						tvCurrent.setVisibility(View.INVISIBLE);
						
						for(int i=0; i<20; i++){
							totalSum += point[i];
						}
						
						if(0<=totalSum && totalSum<=15){
							tvQuestion.setText("당신의 정상적인 사람입니다.");
						}else if(16<=totalSum && totalSum<=23){
							tvQuestion.setText("당신 가벼운 우울증을 앓고 있다고 판단됩니다. 증상이 심해진다면 의사의 진료를 받아보세요.");
						}else if(24<=totalSum){
							tvQuestion.setText("당신의 심한 우울증을 앓고 있다고 판단됩니다.  빠른 시일 내에 의사의 진료를 받아보세요.");
						}

					} else {
						tvQuestion.setText(question[position]);

						if (position > 8) {
							tvCurrent.setText(question[position]
									.substring(0, 2) + "/20");
						} else {
							tvCurrent.setText(question[position]
									.substring(0, 1) + "/20");
						}

					}
				} else {

//					Toast.makeText(getApplicationContext(), "�׸��� ���õ��� �ʾҽ��ϴ�.",
//							Toast.LENGTH_SHORT).show();
					AlertDialog alert = new AlertDialog.Builder(tActivity4_3_1.this)					
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

				if (position == 4 || position == 9 || position == 14) {
					if (point[position] == 3) {
						rgSelect.check(R.id.radio_4_1_1);
					} else if (point[position] == 2) {
						rgSelect.check(R.id.radio_4_1_2);
					} else if (point[position] == 1) {
						rgSelect.check(R.id.radio_4_1_3);
					} else if (point[position] == 0) {
						rgSelect.check(R.id.radio_4_1_4);
					}
				} else {
					if (point[position] == 0) {
						rgSelect.check(R.id.radio_4_1_1);
					} else if (point[position] == 1) {
						rgSelect.check(R.id.radio_4_1_2);
					} else if (point[position] == 2) {
						rgSelect.check(R.id.radio_4_1_3);
					} else if (point[position] == 3) {
						rgSelect.check(R.id.radio_4_1_4);
					}
				}

//				Toast.makeText(getApplicationContext(), point[position] + "",
//						Toast.LENGTH_SHORT).show();

				if (position == 0) {
					btPrev.setVisibility(View.INVISIBLE);

				} else if (position == 18) {
					btNext.setBackgroundResource(R.drawable.next);
//					btNext.setText("�������");
				} else if (position == 19) {
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
							+ "/20");
				} else {
					tvCurrent.setText(question[position].substring(0, 1)
							+ "/20");
				}

			}
		});

	}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		
		Intent i = new Intent(
				tActivity4_3_1.this,
				kr.co.imcc.app.uDiabetesNote.Activity4_menu.class);
		// i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

		startActivity(i);		

		finish();
		
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	      case android.R.id.home:
	        onBackPressed();
	        finish();
	        
	    }
	    return true;
	}
}*/