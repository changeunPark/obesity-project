package kr.co.imcc.app.uDiabetesNote;

import android.app.*;
import android.os.*;
import android.view.*;

public class tActivity2_4_4 extends Activity {

	private ActionBar actionBar;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.tactivity2_4_4);

		actionBar = getActionBar();
		actionBar.setTitle("당뇨병 대처방법");
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
