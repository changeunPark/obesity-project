package kr.co.imcc.app.uDiabetesNote;

import android.app.*;
import android.os.*;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.*;

public class tActivityInfo extends Activity {
	/** Called when the activity is first created. */

	private ActionBar actionBar;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.config);

		actionBar = getActionBar();
		actionBar.setTitle("사용자 정보");
		getActionBar().setHomeButtonEnabled(true);
		getActionBar().setDisplayHomeAsUpEnabled(true);

		final EditText editname = (EditText)findViewById(R.id.info_name);
		Button btnconfirm = (Button) findViewById(R.id.confrim);
		Button btnedit = (Button) findViewById(R.id.edit);

		btnconfirm.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
			}

		});

		btnedit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

			}

		});
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			onBackPressed();
		}
		return true;
	}
}
