package kr.co.imcc.app.uDiabetesNote;

import android.app.*;
import android.os.*;
import android.view.*;
import android.view.View.OnClickListener;
import android.webkit.*;
import android.webkit.WebSettings.RenderPriority;
import android.widget.*;

public class Activity6_6 extends Activity {

	
	private WebView webView;
	private Handler mHandler = new Handler();
	private ProgressDialog progressDialog;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.layer6_diabete_common);
        
        webView = (WebView) findViewById(R.id.webview);
        webView.setHorizontalScrollBarEnabled(false);
        //webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE); 
        webView.getSettings().setRenderPriority(RenderPriority.HIGH);
              
        WebSettings webSettings = webView.getSettings();
        webSettings.setSavePassword(false);
        webSettings.setSaveFormData(false);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setSupportZoom(false);
        webSettings.setDefaultZoom(WebSettings.ZoomDensity.FAR);
                         
        webView.loadUrl("http://211.253.219.149:8638/Default.aspx");
        
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
        
        Button button1 = (Button) findViewById(R.id.button_layer6_6_back);

		button1.setOnClickListener(new OnClickListener() 
		{ // back
			@Override
			public void onClick(View v) 
			{											
				try
				{	
					String url = webView.getUrl();	
					if(url.equals("http://211.253.219.149:8638/Default.aspx"))
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
		});
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
					if(url.equals("http://211.253.219.149:8638/Default.aspx"))
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
