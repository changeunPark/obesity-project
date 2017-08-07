package kr.co.imcc.app.uDiabetesNote;

import android.app.*;
import android.os.*;
import android.view.*;


public class tActivity1_8_1 extends Activity{

	
    private ActionBar actionBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tactivity1_8_1);

        
        actionBar = getActionBar();
      		actionBar.setTitle("식습관 식이추천");
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

