package kr.co.imcc.app.uDiabetesNote;

import android.app.*;
import android.os.*;

public class tActivityexplain extends Activity {
	/** Called when the activity is first created. */
	
    private ActionBar actionBar;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.tactivity_explain);
		
		 actionBar = getActionBar();
			actionBar.setTitle("����");
			getActionBar().setHomeButtonEnabled(true);
			getActionBar().setDisplayHomeAsUpEnabled(true); 
	} 
	
}