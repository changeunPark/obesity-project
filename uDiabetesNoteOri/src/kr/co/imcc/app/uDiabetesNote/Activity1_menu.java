package kr.co.imcc.app.uDiabetesNote;

import android.app.*;
import android.content.*;
import android.os.*;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.*;

public class Activity1_menu extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.layer1_menu);

		Button button1 = (Button) findViewById(R.id.button_layer1_menu_1);
		Button button2 = (Button) findViewById(R.id.button_layer1_menu_2);

		button1.setOnClickListener(new OnClickListener() { // �������

			@Override
			public void onClick(View v) {
				tActivity3_3.termflag = 0;
				Intent intent = new Intent(Activity1_menu.this,
						kr.co.imcc.app.uDiabetesNote.tActivity3_3.class);
				startActivity(intent);

			}
		});

		button2.setOnClickListener(new OnClickListener() { // ��ȭ������ ����
			
			@Override
			public void onClick(View v) {
				Activity1_hb.termflag = 0;
				Intent intent = new Intent(Activity1_menu.this,
						kr.co.imcc.app.uDiabetesNote.Activity1_hb.class);
				startActivity(intent);

			}
		});
//		
		Button button_back = (Button) findViewById(R.id.button_layer1_menu_back);

		button_back.setOnClickListener(new OnClickListener() { // back

			@Override
			public void onClick(View v) {

				onBackPressed();

			}
		});

	}
}