package kr.co.imcc.app.uDiabetesNote;

import android.app.*;
import android.content.*;
import android.os.*;
import android.view.*;
import android.webkit.*;

public class tActivity1_1_1 extends Activity 
{
	
    private ActionBar actionBar;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
//		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.tactivity1_1_1);
		
		actionBar = getActionBar();
		actionBar.setTitle("비만도 측정");
		getActionBar().setHomeButtonEnabled(true);
		getActionBar().setDisplayHomeAsUpEnabled(true); 
		
        Intent intent = getIntent(); 
		String idealWeight = intent.getExtras().getString("idealWeight");
		String overWeightPercent = intent.getExtras().getString("overWeightPercent");
		String overWeight = intent.getExtras().getString("overWeight");
		
		WebView mywb = (WebView) findViewById(R.id.webview);
		mywb.getSettings().setDefaultTextEncodingName("utf-8");
		String user = "당신";
//		결과
		String strContent = "<div style=\"font-weight:bold; font-size:20px;\"><p align=\"justify\">"
				+"<br>"
				+ user
				+ "의 비만도는 "
				+ "<font color=#FFA500>" 
				+ overWeightPercent 
				+"</font>"
				+ "%로 "
				+ "<font color=#FFA500>"
				+ overWeight
				+"</font>" 
				+ "이며 적정체중은 "
				+ "<font color=#FFA500>"
				+ idealWeight
				+ "</font>"
				+"kg 입니다." 
				+ "</P></div>"
				+ "<div style=\"font-size:15px;\"><p align=\"justify\">"
				+ "비만은 체지방의 절대량이나 비율이 증가한 상태를 말하며 비만도는 비만의 정도를 말합니다.</P></div>";
	
		
		if(android.os.Build.VERSION.SDK_INT > 15)
		{
			mywb.loadData("<html><body>" + strContent + "</body></html>", "text/html; charset=UTF-8",  null);
		}			
		else
		{	
			mywb.loadData("<html><body>" + strContent + "</body></html>", "text/html", "utf-8");
		}	
		
	}	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	      case android.R.id.home:
	        onBackPressed();
	        finish();
	        
	    }
	    return true;
	}		
}
