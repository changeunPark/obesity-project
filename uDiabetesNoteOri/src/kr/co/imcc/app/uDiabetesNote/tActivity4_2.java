package kr.co.imcc.app.uDiabetesNote;

import android.app.*;
import android.content.*;
import android.os.*;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.*;

public class tActivity4_2 extends Activity {
	/** Called when the activity is first created. */
	private ActionBar actionBar;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.tactivity1_6);

		actionBar = getActionBar();
		actionBar.setTitle("스트레스에 의한  식이추천");
		getActionBar().setDisplayHomeAsUpEnabled(true);

		// Button button_back = (Button)
		// findViewById(R.id.button_layer4_stress_back);
		ImageButton btTestStart = (ImageButton) findViewById(R.id.button_layer4_1);
		TextView title = (TextView) findViewById(R.id.layout_title);

		title.setText("스트레스에 의한 식이추천");
		/*
		 * button_back.setOnClickListener(new OnClickListener() { // back
		 * 
		 * @Override public void onClick(View v) { onBackPressed(); } });
		 */

		btTestStart.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(tActivity4_2.this,
						kr.co.imcc.app.uDiabetesNote.tActivity4_2_1.class);
				startActivity(intent);
				finish();
			}
		});

	}

	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			finish();

		}
		return true;
	}
}