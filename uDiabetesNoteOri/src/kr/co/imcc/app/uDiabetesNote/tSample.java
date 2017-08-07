package kr.co.imcc.app.uDiabetesNote;

import android.app.*;
import android.os.*;
import android.view.*;


public class tSample extends Activity{

	
    private ActionBar actionBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tactivity_sample);

        
        actionBar = getActionBar();
      		actionBar.setTitle("샘플");
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

