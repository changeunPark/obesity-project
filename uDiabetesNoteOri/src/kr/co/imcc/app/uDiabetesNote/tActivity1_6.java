package kr.co.imcc.app.uDiabetesNote;

import android.app.*;
import android.content.*;
import android.os.*;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.*;

public class tActivity1_6 extends Activity {
	/** Called when the activity is first created. */
	
    private ActionBar actionBar;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tactivity1_6);


        actionBar = getActionBar();
        
		actionBar.setTitle("식습관에 의한 식이추천");
		getActionBar().setDisplayHomeAsUpEnabled(true); 
        
		ImageButton btTestStart = (ImageButton) findViewById(R.id.button_layer4_1);
		TextView title = (TextView)findViewById(R.id.layout_title);
		
		title.setText("식습관에 의한 식이추천");
		
		btTestStart.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(tActivity1_6.this,
						kr.co.imcc.app.uDiabetesNote.tActivity1_6_1.class);				
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