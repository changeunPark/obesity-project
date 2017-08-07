package kr.co.imcc.app.uDiabetesNote;

import android.app.*;
import android.os.*;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.*;

public class tActivity2_4_2 extends Activity {

	private ActionBar actionBar;

	String[] day = { "월요일", "화요일", "수요일", "목요일", "금요일" };

	String[] breakfast = { "수수밥, 냉이된장국, 닭살계피볶음, 삼색냉채, 알타리김치",
			"완두밥, 콩나물국, 소고기 가지 볶음, 애호박나물, 나박김치",
			"흑미밥, 무양지국, 애호박볶음, 양배추 초무침, 갓김치",
			"완두밥, 호박새우젓 찌개, 계란찜, 오이나물, 청경채, 겉절이",
			"수수밥, 사태국, 가지과리볶음, 깻잎무침, 포기김치"
	};

	String[] lunch = { "보리밥, 무국, 참가자미 구이, 두부조림, 포기김치",
			"수수밥, 버섯매운탕, 조기구이, 돈채피망볶음, 포기김치",
			"보리밥, 열무된장국, 임연수어구이, 참나물겉절이, 포기김치",
			"율무밥, 아욱된장국, 불낚볶음, 숙주나물, 열무물김치",
			"보리밥, 아욱된장국, 꼬막찜, 계란부추말이, 나박김치"};

	String[] dinner = { "현미밥, 팽이미소국, 돈등심구이, 양상추샐러드, 열무물김치",
			"기장밥, 두부미소국, 갈치구이, 무쌈, 알타리김치",
			"수수밥, 김치오징어 찌개, 돈생강구이, 모듬쌈, 나박김치",
			"기장밥, 미역국, 두부전, 닭살죽순조림, 총각김치",
			"찰흑미밥, 배추된장국, 닭찜, 콩나물겨자채, 깍두기"};
	int position = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.tactivity2_4_2);

		actionBar = getActionBar();
		actionBar.setTitle("당뇨병 추천 식단");
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