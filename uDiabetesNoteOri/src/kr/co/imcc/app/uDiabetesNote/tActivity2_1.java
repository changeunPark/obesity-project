package kr.co.imcc.app.uDiabetesNote;

import android.app.*;
import android.os.*;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.*;

public class tActivity2_1 extends Activity implements OnClickListener {
	/** Called when the activity is first created. */
	
	ImageButton next, start;
	ImageButton foodbtn1,foodbtn2,foodbtn3,foodbtn4;
	TextView question,mratingtext1, mratingtext2,imgquestion, nexttext;
	RatingBar mratingbar1, mratingbar2;
	ViewFlipper view;
	LinearLayout x;
	int position=0;
	
	float ratingscore1=0, ratingscore2=0;
	int foodbtnnum=0;
	int foodchoicenum=0;
	
    private ActionBar actionBar;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tactivity2_1_img);
		

        actionBar = getActionBar();
		actionBar.setTitle("음식중독 치료하기");
		getActionBar().setHomeButtonEnabled(true);
		getActionBar().setDisplayHomeAsUpEnabled(true); 
		
		
		x =(LinearLayout)findViewById(R.id.main_bg);
//		prev = (ImageButton) findViewById(R.id.prev);
		start = (ImageButton) findViewById(R.id.start);
//		restart = (ImageButton) findViewById(R.id.restart);
		next = (ImageButton) findViewById(R.id.next);
		
		view = (ViewFlipper) findViewById(R.id.viewFlipper1);
		
		question=(TextView)findViewById(R.id.question);
		
		foodbtn1 = (ImageButton)findViewById(R.id.foodimgbtn1);
		foodbtn2 = (ImageButton)findViewById(R.id.foodimgbtn2);
		foodbtn3 = (ImageButton)findViewById(R.id.foodimgbtn3);
		foodbtn3 = (ImageButton)findViewById(R.id.foodimgbtn4);

		
//		RadioGroup foodradio = (RadioGroup)findViewById(R.id.radiofood);
//		prev.setOnClickListener(this);
		start.setOnClickListener(this);
//		restart.setOnClickListener(this);
		next.setOnClickListener(this);
		
		nexttext=(TextView)findViewById(R.id.nexttext);
		
		
		mratingtext1 =(TextView)findViewById(R.id.ratingtext1);
		mratingbar1=(RatingBar)findViewById(R.id.ratingbar1);
		
		mratingtext2 =(TextView)findViewById(R.id.ratingtext2);
		mratingbar2=(RatingBar)findViewById(R.id.ratingbar2);
		
		/*Intent intent = getIntent();
		imgresultstr = intent.getExtras().getString("param");
		imgresultnum = Integer.parseInt(imgresultstr);
		
		*/
//		���̾ƿ�6, 12 �����ùٸ� ���θ����ߵȴ�. ����
		mratingbar1.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener()
		{
			public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser){
				ratingscore1=ratingBar.getRating();
				int num1 = (int) ratingscore1;
				if(ratingscore1==0)
				{
				Toast.makeText(tActivity2_1.this, String.valueOf(ratingBar.getRating()),     
						  Toast.LENGTH_LONG).show();
				}
				switch(num1%10)
				{
				case 1:
					mratingtext1.setText("먹고 싶지 않아요.");
					break;
				case 2:
					mratingtext1.setText("별로 안 먹고 싶어요.");
					break;
				case 3:
					mratingtext1.setText("그저 그래요.");
					break;
				case 4:
					mratingtext1.setText("조금 먹고 싶어요.");
					break;
				case 5:
					mratingtext1.setText("당장 먹고 싶어요.");
					break;
				}
			}
		});
		
		mratingbar2.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener()
		{
			public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser){
				ratingscore2=ratingBar.getRating();
				int num2 = (int) ratingscore2;
				
				switch(num2%10)
				{
				case 1:
					mratingtext2.setText("먹고 싶지 않아요.");
					break;
				case 2:
					mratingtext2.setText("별로 안 먹고 싶어요.");
					break;
				case 3:
					mratingtext2.setText("그저 그래요.");
					break;
				case 4:
					mratingtext2.setText("조금 먹고 싶어요.");
					break;
				case 5:
					mratingtext2.setText("당장 먹고 싶어요.");
					break;
				}
			}
		});
		
	
	}
	
	public void clickFood(View v){
		
		switch(v.getId()){
		case R.id.foodimgbtn1:
			view.setDisplayedChild(3);
			next.setVisibility(View.VISIBLE);
			nexttext.setVisibility(View.VISIBLE);
			foodbtnnum=foodbtnnum+1;
			break;
		case R.id.foodimgbtn2:
			view.setDisplayedChild(4);
			next.setVisibility(View.VISIBLE);
			nexttext.setVisibility(View.VISIBLE);
			foodbtnnum=foodbtnnum+2;
			break;
		case R.id.foodimgbtn3:
			view.setDisplayedChild(5);
			next.setVisibility(View.VISIBLE);
			nexttext.setVisibility(View.VISIBLE);
			foodbtnnum=foodbtnnum+3;
			break;
		case R.id.foodimgbtn4:
			view.setDisplayedChild(6);
			next.setVisibility(View.VISIBLE);
			nexttext.setVisibility(View.VISIBLE);
			foodbtnnum=foodbtnnum+4;
			break;
		}
		foodchoicenum++;
	}
		
			
//			startActivity(page1);
			
			/*if(imgresultnum!=1)
			{
				imgquestion.setText("����");
				next.setVisibility(View.GONE);
				prev.setVisibility(View.GONE);;
			}*/
			
			
//				view.showNext();			
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
//		���� ���ý� ù��° ������
		switch(foodbtnnum)
		{
		case 1:
			view.setDisplayedChild(6);
			foodbtnnum=0;
			break;
		case 2:
			view.setDisplayedChild(6);
			foodbtnnum=0;
			break;
		case 3:
			view.setDisplayedChild(6);
			foodbtnnum=0;
			break;
		case 4:
			view.setDisplayedChild(6);
			foodbtnnum=0;
			break;
		}
		
		switch(v.getId())
		{
		case R.id.prev:
			view.showPrevious();
//			start.setVisibility(View.GONE);
			position--;
		break;
		
		case R.id.next:
			view.showNext();
//			start.setVisibility(View.GONE);
			position++;
			break;

		case R.id.start:
			start.setVisibility(View.GONE);
			next.setVisibility(View.VISIBLE);
			nexttext.setVisibility(View.VISIBLE);
//			prev.setVisibility(View.VISIBLE);
			view.setDisplayedChild(1);
			position=0;
			position++;
			break;

		case R.id.restart:
			view.setDisplayedChild(0);
//			restart.setVisibility(View.GONE);
			start.setVisibility(View.VISIBLE);
			next.setVisibility(View.GONE);
			nexttext.setVisibility(View.GONE);
//			prev.setVisibility(View.GONE);
			position=0;
			break;
			
		}
//		ù��° ���� ���� ������
		switch(position)
		{
		case 0:
			next.setVisibility(View.GONE);
			nexttext.setVisibility(View.GONE);
//			prev.setVisibility(View.GONE);
			start.setVisibility(View.VISIBLE);
			break;
/*		case 1:
			x.setBackgroundResource(R.drawable.think);
			break;*/
	
		case 2: //���� ������
			next.setVisibility(View.GONE);
			nexttext.setVisibility(View.GONE);
//			prev.setVisibility(View.GONE);
			break;
			
		case 6://�ι�° ���ļ���
			next.setVisibility(View.GONE);
			nexttext.setVisibility(View.GONE);
		case 12: // ���� �ߵ��ƴҽ� ������ ������
//			next.setVisibility(View.GONE);
			break;
		}
			
		switch(foodchoicenum)//�ι�° ���ļ�å
		{
		case 2:
			view.setDisplayedChild(11);
			break;
		}
		
		if(ratingscore1>0&&ratingscore1<4)
		{
			view.setDisplayedChild(13);
			next.setVisibility(View.GONE);
			nexttext.setVisibility(View.GONE);
//			prev.setVisibility(View.GONE);
			start.setVisibility(View.GONE);
			nexttext.setVisibility(View.GONE);
//			restart.setVisibility(View.VISIBLE);
			ratingscore1=0;
		}
		
		if(ratingscore2>0) // �����ߵ��� ������������
		{
			view.showNext();
			next.setVisibility(View.GONE);
			nexttext.setVisibility(View.GONE);
//			restart.setVisibility(View.VISIBLE);
		}
		/*if(position==0)
		{
			start.setVisibility(View.VISIBLE);
			next.setVisibility(View.GONE);
			prev.setVisibility(View.GONE);
			ratingscore=5;
			position=0;
		}*/
		/*
//		�ٽý����ϱ�
		if(position==10)
		{
			next.setVisibility(View.GONE);
			prev.setVisibility(View.GONE);
			start.setVisibility(View.GONE);
			restart.setVisibility(View.VISIBLE);
		}
		break;
		
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
		
		/*
		if (v == prev) {
			// ����
			view.setAutoStart(true);
			view.setFlipInterval(4000);
			view.startFlipping();
			view.showPrevious();
			start.setVisibility(View.GONE);
			position--;
		}
		
		else if (v == next) {
			// ����
//			view.stopFlipping();
			view.showNext();
			start.setVisibility(View.GONE);

			if(foodpage1==1)
			{
				view.setDisplayedChild(7);

			} 
			
			position++;
			
			if(ratingscore<3)
			{
				ratingscore=0;
				view.setDisplayedChild(11);
				next.setVisibility(View.GONE);
				prev.setVisibility(View.GONE);
				start.setVisibility(View.GONE);
				restart.setVisibility(View.VISIBLE);
				position=0;
				
			}
			
			//��ư�Ⱥ��̰��ϱ�
			if(position==10)
			{
				view.setAutoStart(true);
				view.setFlipInterval(1000);
				view.startFlipping();
				Intent page = new Intent(getApplicationContext(),tActivity2_1.class);
				startActivity(page);
//				question.setText("�ȳ�");
				next.setVisibility(View.GONE);
				prev.setVisibility(View.GONE);
				start.setVisibility(View.GONE);
				restart.setVisibility(View.VISIBLE);
			}
			
		}
		else if(v==start)
		{
			next.setVisibility(View.VISIBLE);
			prev.setVisibility(View.VISIBLE);
			start.setVisibility(View.GONE);
			ratingscore=5;
			position=0;
			view.showNext();
			position++;
		}
		else if(v==restart)
		{
			view.setDisplayedChild(0);
			restart.setVisibility(View.GONE);
			start.setVisibility(View.VISIBLE);
			next.setVisibility(View.GONE);
			prev.setVisibility(View.GONE);
			ratingscore=5;
			position=0;
		}
		
		if(position==0)
		{
			start.setVisibility(View.VISIBLE);
			next.setVisibility(View.GONE);
			prev.setVisibility(View.GONE);
			ratingscore=5;
			position=0;
		}
		
	}*/


//���� �׷� ���ý� ����Ʈ
	/*RadioGroup.OnCheckedChangeListener mRadioCheck =
			new RadioGroup.OnCheckedChangeListener() {
		public void onCheckedChanged(RadioGroup group,int checkedId){
			if(group.getId()==R.id.radiofood){
				switch(checkedId){
				case R.id.food0:
					break;
				}
				Intent page = new Intent(getApplicationContext(),tActivity2_1.class);
				startActivity(page);
			}
		}
			};*/
//String questiontext[] ={"10,20,30","10,","22"};
/*	
switch(v.getId())
{
case R.id.prev:
	view.showPrevious();
	start.setVisibility(View.GONE);
	position--;

case R.id.next:
	view.showNext();
	start.setVisibility(View.GONE);
	position++;
	
	if(position==8)
	{
		view.setAutoStart(true);
		view.setFlipInterval(1000);
		view.startFlipping();
		Intent page = new Intent(getApplicationContext(),tActivity2_1.class);
		startActivity(page);
	}
case R.id.start:
	next.setVisibility(View.VISIBLE);
	prev.setVisibility(View.VISIBLE);
	start.setVisibility(View.GONE);
	view.showNext();
	position++;
	
	if(position==0)
	{
		start.setVisibility(View.VISIBLE);
		next.setVisibility(View.GONE);
		prev.setVisibility(View.GONE);
		position=0;
	}			
	
}*/


/*package kr.co.imcc.app.uDiabetesNote;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ViewFlipper;

public class tActivity2_1_img extends Activity{
	
//    private ActionBar actionBar;
			
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.tactivity2_1_img);


		 actionBar = getActionBar();
			actionBar.setTitle("�����ߵ� ġ���ϱ�");
			getActionBar().setHomeButtonEnabled(true);
			getActionBar().setDisplayHomeAsUpEnabled(true); 

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
	
	
	*/