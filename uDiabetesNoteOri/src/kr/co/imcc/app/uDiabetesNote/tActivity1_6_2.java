package kr.co.imcc.app.uDiabetesNote;

import android.app.*;
import android.content.*;
import android.os.*;
import android.view.*;
import android.widget.*;

public class tActivity1_6_2 extends Activity {

	private ActionBar actionBar;

	int imagenum = (int) (Math.random() * 5);

	String[] badanswer = { " · 하루에 3끼니 식사를 하지 않는다.\n",
			" · 아침 식사를 제대로 먹지 않는다.\n", " · 정해진 시간에 식사를 하지 않는다.\n",
			" · 여유있게 천천히 식사를 하지 않는다.\n", " · 과식을  한다.\n",
			" · 곡류음식을 매끼 먹지 않는다.\n", " · 단백질 반찬을 매끼 먹지 않는다.\n",
			" · 채소반찬을 매끼 먹지 않는다.\n", " · 우유나 유제품을 매일 먹지 않는다.\n",
			" · 과일을 매일 먹지 않는다.\n" };

	String[] normalanswer;

	String[] goodanswer = { " · 하루에 3끼니 식사한다.\n", " · 아침 식사를 제대로 먹는다.\n",
			" · 정해진 시간에 식사한다.\n", " · 여유있게 천천히 식사한다.\n", " · 과식을 하지 않는다.\n",
			" · 곡류음식을 매끼 먹는다.\n", " · 단백질 반찬을 매끼 먹는다.\n", " · 채소반찬을 매끼 먹는다.\n",
			" · 우유나 유제품을 매일 먹는다.\n", " · 과일을 매일 먹는다.\n" };
	String textfoodhabit = "";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tactivity1_6_2);

		actionBar = getActionBar();
		actionBar.setTitle("식습관에 의한 식이추천");
		getActionBar().setHomeButtonEnabled(true);
		getActionBar().setDisplayHomeAsUpEnabled(true);

		TextView choicequestion[] = new TextView[10];
		choicequestion[0] = (TextView) findViewById(R.id.choice0);
		/*
		 * choicequestion[1] = (TextView) findViewById(R.id.choice1);
		 * choicequestion[2] = (TextView) findViewById(R.id.choice2);
		 * choicequestion[3] = (TextView) findViewById(R.id.choice3);
		 * choicequestion[4] = (TextView) findViewById(R.id.choice4);
		 * choicequestion[5] = (TextView) findViewById(R.id.choice5);
		 * choicequestion[6] = (TextView) findViewById(R.id.choice6);
		 * choicequestion[7] = (TextView) findViewById(R.id.choice7);
		 * choicequestion[8] = (TextView) findViewById(R.id.choice8);
		 * choicequestion[9] = (TextView) findViewById(R.id.choice9);
		 */
		TextView commnetresult = (TextView) findViewById(R.id.comment);
		TextView imagetext = (TextView) findViewById(R.id.foodhabit);
		ImageView resultimage = (ImageView) findViewById(R.id.imageview0);

		Intent intent = getIntent();
		String receivecomment = intent.getExtras().get("result").toString();
		String[] receivechoice1 = intent.getStringArrayExtra("choicepoint1");
		String[] receivechoice2 = intent.getStringArrayExtra("choicepoint2");
		String[] receiveresult = new String[20];
		String[] receiveresult2 = new String[20];
		String temp = "";
		int choicenum1 = 0;
		int choicenum2 = 0;
		int choicesum = 0;
		// a,b,c,d 값을 receiveresult에 저장
		for (int i = 0; i < 5; i++) {

			receiveresult[(2 * i)] = receivechoice1[i];

			receiveresult[(2 * i) + 1] = receivechoice2[i];

		}
		for (int i = 0; i < 10; i++) {
			if (receiveresult[i].equals("a")) {
				receiveresult[i] = badanswer[i];
				choicenum1++;
			} else if (receiveresult[i].equals("d")) {
				receiveresult[i] = goodanswer[i];
				choicenum2++;
			} else {
				continue;
			}
			temp = temp + receiveresult[i];
		}

		choicequestion[0].setText(temp);

		commnetresult.setText(receivecomment);

		if (imagenum == 0) {
			resultimage.setImageResource(R.drawable.refood1);
			imagetext.setText("헬스 전문가와 영영사가 추천하는, 식습관에 따른 오늘의 메뉴는 순두부 찌개입니다.");
		} else if (imagenum == 1) {
			resultimage.setImageResource(R.drawable.refood2);
			imagetext.setText("헬스 전문가와 영영사가 추천하는, 식습관에 따른 오늘의 메뉴는 청국장 찌개입니다.");
		}
		else if (imagenum == 2) {
			resultimage.setImageResource(R.drawable.refood3);
			imagetext.setText("헬스 전문가와 영영사가 추천하는, 식습관에 따른 오늘의 메뉴는 콩국수입니다.");
		}
		else if (imagenum == 3) {
			resultimage.setImageResource(R.drawable.refood4);
			imagetext.setText("헬스 전문가와 영영사가 추천하는, 식습관에 따른 오늘의 메뉴는 비빔밥입니다.");
		} else {
			resultimage.setImageResource(R.drawable.refood5);
			imagetext.setText("헬스 전문가와 영영사가 추천하는, 식습관에 따른 오늘의 메뉴는 삼계탕입니다.");
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
