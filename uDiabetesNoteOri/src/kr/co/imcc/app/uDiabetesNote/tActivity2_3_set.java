package kr.co.imcc.app.uDiabetesNote;

import android.app.*;
import android.content.*;
import android.os.*;
import android.view.*;
import android.webkit.*;
import android.webkit.WebSettings.RenderPriority;
import android.widget.*;

public class tActivity2_3_set extends Activity {

	private ActionBar actionBar;
	private WebView webView;
	private ProgressDialog progressDialog;
    String strURL;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.layer6_diabete_common);
        
        actionBar = getActionBar();
		actionBar.setTitle("고지혈증 추천 식단");
		getActionBar().setHomeButtonEnabled(true);
		getActionBar().setDisplayHomeAsUpEnabled(true); 
        
        Intent intent = getIntent(); 
        
        String strTitle ="";
        
        switch (intent.getExtras().getInt("diabtete"))
        {
			case 1:
				getActionBar().setTitle("고지혈증 추천 / 피해야 할 음식");
				strURL = "http://sk0619.dothome.co.kr/hmenu.html";
				break;
			case 2:
				getActionBar().setTitle("고지혈증 추천식단");
				strURL = "http://sk0619.dothome.co.kr/hmenu.html";
				break;
			case 3:
				getActionBar().setTitle("고지혈증 식습관 가이드");
				strURL = "http://sk0619.dothome.co.kr/hguide.html";
				break;
			case 4:
				getActionBar().setTitle("고지혈증 주의사항");
				strURL = "http://sk0619.dothome.co.kr/hcaution.html";
				break;
	
			default:
				break;
		}
        
      /*  TextView title = (TextView) findViewById(R.id.title);
        title.setText(strTitle);
        */
        webView = (WebView) findViewById(R.id.webview);
        webView.setHorizontalScrollBarEnabled(false);
        //  webView.getSettings().setSupportZoom(true);
        //webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE); 
        webView.getSettings().setRenderPriority(RenderPriority.HIGH);
              
        WebSettings webSettings = webView.getSettings();
        webSettings.setSavePassword(false);
        webSettings.setSaveFormData(false);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDefaultZoom(WebSettings.ZoomDensity.FAR);
                         
        webView.loadUrl(strURL);
        
        final Activity activity = this;
        
        webView.setWebViewClient(new WebViewClient(){
        public void onLoadResource (WebView view, String url) {
                
        	if (progressDialog == null) {
                    progressDialog = new ProgressDialog(activity);
                    progressDialog.setMessage("Page Loading...");
                    progressDialog.show();
                }
            }
 
            public void onPageFinished(WebView view, String url) {
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                    progressDialog = null;
                }
            }
 
            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(activity, "�ε�����:"+description, Toast.LENGTH_SHORT).show();
            }
 
        });
                  
        //webView.setWebViewClient(new WebClient());
        webView.setVerticalScrollbarOverlay(true); 

	    	   
	    // TODO Auto-generated method stub
        
     /*   Button button1 = (Button) findViewById(R.id.button_layer_back);

		button1.setOnClickListener(new OnClickListener() 
		{ // back
			@Override
			public void onClick(View v) 
			{											
				try
				{	
					String url = webView.getUrl();	
					if(url.equals(strURL))
					{
						onBackPressed();
					}
					else
					{
						webView.goBack();
					}	
				}
				catch(Exception ex)
				{
					onBackPressed();
				}
			}
		});*/
	}
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	      case android.R.id.home:
	        onBackPressed();
	    }
	    return true;
	}
	public boolean onKeyDown( int KeyCode, KeyEvent event )//�ϵ����Ű ����
	{ 
		if( event.getAction() == KeyEvent.ACTION_DOWN )
		{
			if( KeyCode == KeyEvent.KEYCODE_BACK )
			{	
				try
				{	
					String url = webView.getUrl();	
					if(url.equals(strURL))
					{
						onBackPressed();
					}
					else
					{
						webView.goBack();
					}	
				}
				catch(Exception ex)
				{
					onBackPressed();
				}		
				return false;
				// ���⼭ ���ϰ��� �߿��ѵ�; ���ϰ��� true �̳� false �̳Ŀ� ���� �ൿ�� �޶�����.
				// true �ϰ�� back ��ư�� �⺻������ ���Ḧ �����ϰ� �ȴ�.
				// ������ false �� ��� back ��ư�� �⺻������ ���� �ʴ´�.
				// back ��ư�� �������� ������� �ʰ� �ϰ� �ʹٸ� ���⼭ false �� �����ϸ� �ȴ�.
				// back ��ư�� �⺻������ ������ ���ø����̼��� ������ ����� ���⶧����
				// ���� �����ϴ� ����� �����ؾ��Ѵ�.		
			} 
		}	 
		return super.onKeyDown( KeyCode, event );
	}
	
	/*class WebClient extends WebViewClient 
	{	
		@Override		
		public boolean shouldOverrideUrlLoading(WebView view, String url) 
		{		
			view.loadUrl(url);	
			return true;	
		}		
	}*/	
}
