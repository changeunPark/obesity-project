package kr.co.imcc.app.uDiabetesNote;

import android.app.*;
import android.content.*;
import android.os.*;
import android.view.*;
import android.webkit.*;

public class tActivity1_3_1 extends Activity 
{
	
    private ActionBar actionBar;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
//		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.tactivity1_3_1);
		
		actionBar = getActionBar();
 		actionBar.setTitle("복부 비만도 측정");
 		getActionBar().setDisplayHomeAsUpEnabled(true); 
		
        Intent intent = getIntent(); 
		String strOverWeight = intent.getExtras().getString("overWeight");
		String strWHR = intent.getExtras().getString("whr");
		
		WebView mywb = (WebView) findViewById(R.id.webview);
		mywb.getSettings().setDefaultTextEncodingName("utf-8");
		String user = "당신";
		
		String strContent = "<div style=\"font-weight:bold; font-size:20px;\"><p align=\"justify\">"
				+"<br>"
				+ user
				+ "의 허리·엉덩이 둘레비는 "
				+ "<font color=#FFA500>" 
				+ strWHR
				+"</font>"
				+"입니다."
				+ "<br>"
				+ "당신은 "
				+ "<font color=#FFA500>"
				+ strOverWeight
				+"</font>" 
				+ "입니다."
				+ "</P></div>";
		
		if(android.os.Build.VERSION.SDK_INT > 15)
		{
			mywb.loadData("<html><body>" + strContent + "</body></html>", "text/html; charset=UTF-8",  null);
		}			
		else
		{	
			mywb.loadData("<html><body>" + strContent + "</body></html>", "text/html", "utf-8");
		}	
		
		/*Button button1 = (Button) findViewById(R.id.button_layer3_back);

		button1.setOnClickListener(new OnClickListener() 
		{ // back
			@Override
			public void onClick(View v) 
			{
				onBackPressed();
			}
		});*/
	}		public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	      case android.R.id.home:
	        onBackPressed();
	    }
	    return true;
	}
}
