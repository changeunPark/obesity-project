package kr.co.imcc.app.uDiabetesNote;

import android.app.*;
import android.os.*;


public class tIntro extends Activity{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intro);

        Handler handler = new Handler(){
        	public void handleMessage(Message msg){
        		finish();
        	}
        };
        
        handler.sendEmptyMessageDelayed(0,1000);
    }
}

