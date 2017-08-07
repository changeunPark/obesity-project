package kr.co.imcc.app.uDiabetesNote;

import android.app.*;
import android.content.*;
import android.os.*;
import android.view.*;
import android.widget.*;

public class tActivity4_3_2 extends Activity {

	private ActionBar actionBar;

	String[] badanswer = { " · 일생활상이 괴롭고 귀찮게 느껴진다.\n",
			" · 요즘 식욕이 없다.\n", " · 다른이가 내 기분을 풀어줄거라 생각하지 않는다.\n",
			" · 어떠한 일에도 집중을 할 수가 없다.\n", " · 지금 행복하지 않다.\n", " · 지금 우울하다.\n",
			" · 지금 모든게 다 힘들다.\n", " · 지금 내가 뭘해야될지 모르겠다.\n",
			" · 내인생이 다 헛된것이라고 생각한다.\n",
			" · 보통사람들과 다르다고 생각한다.\n"};

	String[] normalanswer;

	String[] goodanswer = { " · 일생활상이 괴롭고 귀찮게 느껴지지 않는다.\n",
			" · 요즘 식욕이 없지 않다.\n", " · 다른이가 내 기분을 풀어줄거라 생각한다.\n",
			" · 어떠한 일에도 집중을 할 수가 있다.\n", " · 지금 행복하다.\n", " · 지금 우울하지 않다.\n",
			" · 지금 모든게 다 힘들지 않다.\n", " · 지금 내가 뭘해야될지 알고있다.\n",
			" · 내인생이 다 헛된것이라고 생각하지 않는다.\n",
			" · 보통사람들과 같다고 생각한다..\n"};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tactivity1_6_2);

		actionBar = getActionBar();
		actionBar.setTitle("우울증에 의한 식이추천");
		getActionBar().setHomeButtonEnabled(true);
		getActionBar().setDisplayHomeAsUpEnabled(true);

		TextView choicequestion[] = new TextView[10];
		choicequestion[0] = (TextView) findViewById(R.id.choice0);
		/*choicequestion[1] = (TextView) findViewById(R.id.choice1);
		choicequestion[2] = (TextView) findViewById(R.id.choice2);
		choicequestion[3] = (TextView) findViewById(R.id.choice3);
		choicequestion[4] = (TextView) findViewById(R.id.choice4);
		choicequestion[5] = (TextView) findViewById(R.id.choice5);
		choicequestion[6] = (TextView) findViewById(R.id.choice6);
		choicequestion[7] = (TextView) findViewById(R.id.choice7);
		choicequestion[8] = (TextView) findViewById(R.id.choice8);
		choicequestion[9] = (TextView) findViewById(R.id.choice9);
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
		String temp="";
		int choicenum1=0;
		int choicenum2=0;
		int choicesum=0;
		// a,b,c,d 값을 receiveresult에 저장
		for (int i = 0; i < 5; i++) {

			receiveresult[(2 * i)] = receivechoice1[i];

			receiveresult[(2 * i) + 1] = receivechoice2[i];

		}
		for (int i = 0; i < 10; i++) {
			if(receiveresult[i].equals("a")){
				receiveresult[i]=badanswer[i];
				choicenum1++;
			}
			else if(receiveresult[i].equals("d")){
				receiveresult[i]=goodanswer[i];
				choicenum2++;
			}
			else
			{	
				continue;
			}
			temp=temp +receiveresult[i];
		}
		
		choicequestion[0].setText(temp);

		commnetresult.setText(receivecomment);

		int imagenum = (int) (Math.random() * 5);

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
			resultimage.setImageResource(R.drawable.refood5);
			imagetext.setText("헬스 전문가와 영영사가 추천하는, 식습관에 따른 오늘의 메뉴는 삼계탕입니다.");
		} else {
			resultimage.setImageResource(R.drawable.refood4);
			imagetext.setText("헬스 전문가와 영영사가 추천하는, 식습관에 따른 오늘의 메뉴는 비빔밥입니다.");
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