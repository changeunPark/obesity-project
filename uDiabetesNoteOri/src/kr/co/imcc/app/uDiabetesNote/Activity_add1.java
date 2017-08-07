package kr.co.imcc.app.uDiabetesNote;


import android.app.*;
import android.os.*;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.*;

public class Activity_add1 extends Activity {
	/** Called when the activity is first created. */

	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_add1);
		

		Button button_back = (Button) findViewById(R.id.button_layer2_back);

		button_back.setOnClickListener(new OnClickListener() { // back

			@Override
			public void onClick(View v) {

				onBackPressed();

			}
		});
	}
	
}