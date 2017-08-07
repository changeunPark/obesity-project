package kr.co.imcc.app.uDiabetesNote;

import android.app.*;
import android.content.*;
import android.os.*;
import android.view.*;
import android.view.View.OnClickListener;
import android.webkit.*;
import android.widget.*;

public class Activity3 extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.layer3);

		Button button1 = (Button) findViewById(R.id.button_layer3_1);
		Button button2 = (Button) findViewById(R.id.button_layer3_2);
		Button button3 = (Button) findViewById(R.id.button_layer3_3);

		button1.setOnClickListener(new OnClickListener() { // 심장혈관 질환 위험도

			@Override
			public void onClick(View v) {

				Intent intent = new Intent(Activity3.this,
						kr.co.imcc.app.uDiabetesNote.tActivity1_4.class);
				startActivity(intent);

			}
		});
		
		button2.setOnClickListener(new OnClickListener() { // 뇌졸증 위험도

			@Override
			public void onClick(View v) {

				Intent intent = new Intent(Activity3.this,
						kr.co.imcc.app.uDiabetesNote.Activity3_CVD.class);
				startActivity(intent);

			}
		});

		button3.setOnClickListener(new OnClickListener() { // 뇌졸증 위험도

			@Override
			public void onClick(View v) {

				Intent intent = new Intent(Activity3.this,
						kr.co.imcc.app.uDiabetesNote.Activity3_2.class);
				startActivity(intent);

			}
		});
		
		Button button_back = (Button) findViewById(R.id.button_layer3_back);

		button_back.setOnClickListener(new OnClickListener() { // back

			@Override
			public void onClick(View v) {
				onBackPressed();
			}
		});
		
		String strContent = "<div><h3>심장·뇌혈관 질환 위험도 란?</h3></div>" +
				"<div><p align=\"justify\">심장/뇌혈관 질환 위험도는 대규모 인구집단을 장기추적하여 얻어진 통계적 모형에 근거한 " +
				"예측값으로서 실제 질병발생 위험도와는 차이가 있을 수 있습니다. </br>본 심장/뇌혈관 질환 발생 위험율은 나이, " +
				"수축기 혈압, 고밀도 콜레스테롤, 총 콜레스테롤,당뇨, 흡연, 과거 심혈관 질환 여부, 심방세동 및 심전도상 " +
				"좌심실비대의 위험인자의 여부가 향후 심장/뇌혈관 질환 발생 위험율에 미치는 영향을 계산한 것입니다. 단지, " +
				"최상의 건강한 생활습관을 가진 경우와의 상대적인 위험도임을 밝혀둡니다.</P><div>";
		
		
		WebView mywb = (WebView) findViewById(R.id.webview);
		mywb.getSettings().setDefaultTextEncodingName("utf-8");
		
		if(android.os.Build.VERSION.SDK_INT > 15)
		{
			mywb.loadData("<html><body>" + strContent + "</body></html>", "text/html; charset=UTF-8",  null);
		}			
		else
		{	
			mywb.loadData("<html><body>" + strContent + "</body></html>", "text/html", "utf-8");
		}	
	}
}


