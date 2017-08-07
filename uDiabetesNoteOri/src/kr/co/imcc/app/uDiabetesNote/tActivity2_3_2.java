package kr.co.imcc.app.uDiabetesNote;

import android.app.*;
import android.os.*;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.*;

public class tActivity2_3_2 extends Activity {

	private ActionBar actionBar;

	String[] day = { "월요일", "화요일", "수요일", "목요일", "금요일" };

	String[] breakfast = { "현미멸치죽, 표고버섯조림, 콩나물무침, 배추김치",
			"흑임자죽, 코다리찜, 오이생채, 참나물무침, 나박김치", "콩죽, 고사리나물, 도라지생채, 나박김치, 멸치볶음",
			"고구마죽, 새송이나물, 김구이, 백김치, 달걀찜", "오곡죽, 건새우 아몬드볶음, 나박김치, 비름나물무침" };

	String[] lunch = { "메밀국수, 두부구이 샐러드, 숙주나물, 백김치",
			"취나물밥, 가지찜, 콩나물국, 톳초무침, 감자볶음, 배추김치",
			"굴영양밥, 두부된장국, 쑥갓나물무침, 치즈달걀말이, 백김치",
			"무콩나물밥, 두부버섯전골, 고구마 샐러드, 미나리 김치",
			"해초 새싹 비빔밥, 바지락 맑은탕, 시금치 나물, 배추김치" };

	String[] dinner = { "고구마영양밥, 청국장찌개, 고등어시래기 조림,\n노타리나물, 더덕구이",
			"현미밥, 된장국, 무나물무침, 시래기나물, 백김치", "오곡밥, 버섯들깻국, 죽순표고볶음, 꽃게콩나물찜, 배추김치",
			"흑미밥, 시금치연두부탕, 북어찜, 도라지 생채, 배추김치",
			"굴밥, 청국장 찌개, 참나물 된장 무침, 두부완자, 깍두기" };
	int position = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.tactivity2_3_2);

		actionBar = getActionBar();
		actionBar.setTitle("고지혈증 추천 식단");
		getActionBar().setHomeButtonEnabled(true);
		getActionBar().setDisplayHomeAsUpEnabled(true);

		final ImageButton btnprev = (ImageButton) findViewById(R.id.prev);
		final ImageButton btnnext = (ImageButton) findViewById(R.id.next);

		final TextView viewday = (TextView) findViewById(R.id.day);
		final TextView viewbreak = (TextView) findViewById(R.id.breakfast);
		final TextView viewlunch = (TextView) findViewById(R.id.lunch);
		final TextView viewdinner = (TextView) findViewById(R.id.dinner);
		final ImageButton btnconfirm = (ImageButton) findViewById(R.id.confirm);

		btnprev.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				position--;
				if(position<0)
				{
					position=4;
				}
				viewday.setText(day[position]);
				viewbreak.setText(breakfast[position]);
				viewlunch.setText(lunch[position]);
				viewdinner.setText(dinner[position]);

			}

		});

		btnnext.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				position++;
				if(position>4)
				{
					position=0;
				}
				viewday.setText(day[position]);
				viewbreak.setText(breakfast[position]);
				viewlunch.setText(lunch[position]);
				viewdinner.setText(dinner[position]);
				
			}

		});

		btnconfirm.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				finish();

			}
		});
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

/*
 * package kr.co.imcc.app.uDiabetesNote;
 * 
 * import android.R.string; import android.app.Activity; import
 * android.app.ProgressDialog; import android.os.Bundle; import
 * android.os.Handler; import android.webkit.WebView; import
 * android.view.KeyEvent; import android.view.View; import android.view.Window;
 * import android.view.View.OnClickListener; import android.webkit.WebSettings;
 * import android.webkit.WebChromeClient; import android.webkit.JsResult; import
 * android.webkit.WebSettings.RenderPriority; import
 * android.webkit.WebViewClient; import android.widget.Button; import
 * android.widget.Toast;
 * 
 * public class tActivity2_3_2 extends Activity {
 * 
 * 
 * private WebView webView; private Handler mHandler = new Handler(); private
 * ProgressDialog progressDialog;
 *//** Called when the activity is first created. */
/*
 * @Override public void onCreate(Bundle savedInstanceState) {
 * super.onCreate(savedInstanceState);
 * setContentView(R.layout.layer6_bloodpress_2);
 * 
 * webView = (WebView) findViewById(R.id.webview);
 * webView.setHorizontalScrollBarEnabled(false);
 * //webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
 * webView.getSettings().setRenderPriority(RenderPriority.HIGH);
 * 
 * WebSettings webSettings = webView.getSettings();
 * webSettings.setSavePassword(false); webSettings.setSaveFormData(false);
 * webSettings.setJavaScriptEnabled(true); webSettings.setSupportZoom(true);
 * webSettings.setBuiltInZoomControls(true);
 * webSettings.setDefaultZoom(WebSettings.ZoomDensity.FAR);
 * 
 * webView.loadUrl("http://gilcare.welltizen.com/mobile/guide_pressure_food2.html"
 * );
 * 
 * final Activity activity = this;
 * 
 * webView.setWebViewClient(new WebViewClient(){ public void onLoadResource
 * (WebView view, String url) {
 * 
 * if (progressDialog == null) { progressDialog = new ProgressDialog(activity);
 * progressDialog.setMessage("Page Loading..."); progressDialog.show(); } }
 * 
 * public void onPageFinished(WebView view, String url) { if
 * (progressDialog.isShowing()) { progressDialog.dismiss(); progressDialog =
 * null; } }
 * 
 * @Override public void onReceivedError(WebView view, int errorCode, String
 * description, String failingUrl) { Toast.makeText(activity,
 * "�ε�����:"+description, Toast.LENGTH_SHORT).show(); }
 * 
 * });
 * 
 * //webView.setWebViewClient(new WebClient());
 * webView.setVerticalScrollbarOverlay(true);
 * 
 * 
 * // TODO Auto-generated method stub
 * 
 * Button button1 = (Button) findViewById(R.id.button_layer6_6_back);
 * 
 * button1.setOnClickListener(new OnClickListener() { // back
 * 
 * @Override public void onClick(View v) { try { String url = webView.getUrl();
 * if
 * (url.equals("http://gilcare.welltizen.com/mobile/guide_pressure_food2.html"))
 * { onBackPressed(); } else { webView.goBack(); } } catch(Exception ex) {
 * onBackPressed(); } } }); }
 * 
 * public boolean onKeyDown( int KeyCode, KeyEvent event )//�ϵ����Ű ���� { if(
 * event.getAction() == KeyEvent.ACTION_DOWN ) { if( KeyCode ==
 * KeyEvent.KEYCODE_BACK ) { try { String url = webView.getUrl();
 * if(url.equals("http://gilcare.welltizen.com/mobile/guide_pressure_food2.html"
 * )) { onBackPressed(); } else { webView.goBack(); } } catch(Exception ex) {
 * onBackPressed(); } return false; // ���⼭ ���ϰ��� �߿��ѵ�; ���ϰ��� true �̳�
 * false �̳Ŀ� ���� �ൿ�� �޶�����. // true �ϰ�� back ��ư�� �⺻������ ���Ḧ �����ϰ�
 * �ȴ�. // ������ false �� ��� back ��ư�� �⺻������ ���� �ʴ´�. // back ��ư��
 * �������� ������� �ʰ� �ϰ� �ʹٸ� ���⼭ false �� �����ϸ� �ȴ�. // back ��ư��
 * �⺻������ ������ ���ø����̼��� ������ ����� ���⶧���� // ���� �����ϴ� �����
 * �����ؾ��Ѵ�.
 * 
 * } } return super.onKeyDown( KeyCode, event ); }
 * 
 * class WebClient extends WebViewClient {
 * 
 * @Override public boolean shouldOverrideUrlLoading(WebView view, String url) {
 * view.loadUrl(url); return true; } }
 * 
 * }
 */