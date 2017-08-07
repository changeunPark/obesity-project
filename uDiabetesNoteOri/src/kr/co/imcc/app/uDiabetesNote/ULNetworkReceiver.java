package kr.co.imcc.app.uDiabetesNote;

import android.app.*;
import android.content.*;
import android.net.*;
import android.util.Log;

public class ULNetworkReceiver extends BroadcastReceiver {

	static boolean NETWORK_STATUS;
	static boolean NETWORK_LIVE;
	
	private Activity activity;

	public ULNetworkReceiver() {
		super();
	}
	
	public ULNetworkReceiver(Activity activity) {
		  this.activity = activity;
	}


	@Override
	public void onReceive(Context context, Intent intent) {

		
		String action = intent.getAction();

		// ��Ʈ���� ������ �Ͼ���� �߻��ϴ� �κ�
		if (action.equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
			try {
				ConnectivityManager connectivityManager = (ConnectivityManager) context
						.getSystemService(Context.CONNECTIVITY_SERVICE);
				NetworkInfo mobile = connectivityManager
						.getNetworkInfo(connectivityManager.TYPE_MOBILE);
				NetworkInfo wifi = connectivityManager
						.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
//
//				if (_wifi_network != null) {
//
//					/** 3G, WIFI �� �� �ϳ��� ���� ��� */
//					if (_wifi_network != null && activeNetInfo != null) {
//						Toast.makeText(context,
//								"���ӻ��� : " + activeNetInfo.getTypeName(),
//								Toast.LENGTH_SHORT).show();
//
////						tActivity1_4.button_hosp.setClickable(true);
////						tActivity1_1.test();//button_hosp.setClickable(true);
////						tActivity1_2.button_hosp.setClickable(true);
////						tActivity1_3.button_hosp.setClickable(true);
////						Activity3_1.button_hosp.setClickable(true);
////						Activity3_2.button_hosp.setClickable(true);
////						tActivity3_1.button_hosp.setClickable(true);
////						ActivityConfig_1.button_hosp.setClickable(true);
////						ActivityConfig_3.button_hosp.setClickable(true);
//						
//						NETWORK_STATUS = true;
//					}
//					/** 3G, WIFI �� �� ���� ��� */
//					else {
//						Toast.makeText(context, "���ӻ��� : 3G/wifi �Ұ���",
//								Toast.LENGTH_SHORT).show();
//
//						NETWORK_STATUS = false;
//						
////						tActivity1_4.button_hosp.setClickable(false);
////						tActivity1_1.button_hosp.setClickable(false);
////						tActivity1_2.button_hosp.setClickable(false);
////						tActivity1_3.button_hosp.setClickable(false);
////						Activity3_1.button_hosp.setClickable(false);
////						Activity3_2.button_hosp.setClickable(false);
////						tActivity3_1.button_hosp.setClickable(false);
////						ActivityConfig_1.button_hosp.setClickable(false);
////						ActivityConfig_3.button_hosp.setClickable(false);
//					}
//
//				}
//				 final String status = chkStatus(this.activity);
				 if(!mobile.isConnected() && !wifi.isConnected() ) {
					 
					 NETWORK_LIVE = false;
//					 Toast.makeText(context, "���ӻ��� : 3G/wifi �Ұ���11", Toast.LENGTH_SHORT).show();
					 
//					 if(activity.findViewById(R.id.button_layer1_hosp)!=null){
////						 activity.findViewById(R.id.button_layer1_hosp).setClickable(false);	
//						 
//						 activity.findViewById(R.id.button_layer1_hosp).setOnClickListener(null);
//						 activity.findViewById(R.id.button_layer1_hosp).setOnClickListener(new OnClickListener() {
//							
//							@Override
//							public void onClick(View v) {
//								Toast.makeText(activity, "��Ʈ��ũ�� ����Ǿ� ���� �ʽ��ϴ�. ��Ʈ��ũ ������ Ȯ���� �ֽʽÿ�." ,	Toast.LENGTH_SHORT).show();
//							}
//						});
//					 }
//					 
//					 if(activity.findViewById(R.id.button_2_1_hosp)!=null){
////						 activity.findViewById(R.id.button_2_1_hosp).setClickable(false);	
//						 
//						 activity.findViewById(R.id.button_2_1_hosp).setOnClickListener(null);
//						 activity.findViewById(R.id.button_2_1_hosp).setOnClickListener(new OnClickListener() {
//							
//							@Override
//							public void onClick(View v) {
//								Toast.makeText(activity, "��Ʈ��ũ�� ����Ǿ� ���� �ʽ��ϴ�. ��Ʈ��ũ ������ Ȯ���� �ֽʽÿ�." ,	Toast.LENGTH_SHORT).show();
//							}
//						});
//					 }
//					 
//					 if(activity.findViewById(R.id.button_2_2_hosp)!=null){
////						 activity.findViewById(R.id.button_2_2_hosp).setClickable(false);	
//						 
//						 activity.findViewById(R.id.button_2_2_hosp).setOnClickListener(null);
//						 activity.findViewById(R.id.button_2_2_hosp).setOnClickListener(new OnClickListener() {
//							
//							@Override
//							public void onClick(View v) {
//								Toast.makeText(activity, "��Ʈ��ũ�� ����Ǿ� ���� �ʽ��ϴ�. ��Ʈ��ũ ������ Ȯ���� �ֽʽÿ�." ,	Toast.LENGTH_SHORT).show();
//							}
//						});
//					 }
//					 
//					 if(activity.findViewById(R.id.button_2_3_hosp)!=null){
////						 activity.findViewById(R.id.button_2_3_hosp).setClickable(false);	
//						 
//						 activity.findViewById(R.id.button_2_3_hosp).setOnClickListener(null);
//						 activity.findViewById(R.id.button_2_3_hosp).setOnClickListener(new OnClickListener() {
//							
//							@Override
//							public void onClick(View v) {
//								Toast.makeText(activity, "��Ʈ��ũ�� ����Ǿ� ���� �ʽ��ϴ�. ��Ʈ��ũ ������ Ȯ���� �ֽʽÿ�." ,	Toast.LENGTH_SHORT).show();
//							}
//						});
//					 }
//					 
//					 if(activity.findViewById(R.id.button_3_1_hosp)!=null){
////						 activity.findViewById(R.id.button_3_1_hosp).setClickable(false);	 
//						 
//						 activity.findViewById(R.id.button_3_1_hosp).setOnClickListener(null);
//						 activity.findViewById(R.id.button_3_1_hosp).setOnClickListener(new OnClickListener() {
//							
//							@Override
//							public void onClick(View v) {
//								Toast.makeText(activity, "��Ʈ��ũ�� ����Ǿ� ���� �ʽ��ϴ�. ��Ʈ��ũ ������ Ȯ���� �ֽʽÿ�." ,	Toast.LENGTH_SHORT).show();
//							}
//						});
//					 }
//					 
//					 if(activity.findViewById(R.id.button_3_2_hosp)!=null){
////						 activity.findViewById(R.id.button_3_2_hosp).setClickable(false);	
//						 
//						 activity.findViewById(R.id.button_3_2_hosp).setOnClickListener(null);
//						 activity.findViewById(R.id.button_3_2_hosp).setOnClickListener(new OnClickListener() {
//							
//							@Override
//							public void onClick(View v) {
//								Toast.makeText(activity, "��Ʈ��ũ�� ����Ǿ� ���� �ʽ��ϴ�. ��Ʈ��ũ ������ Ȯ���� �ֽʽÿ�." ,	Toast.LENGTH_SHORT).show();
//							}
//						});
//					 }
//					 
//					 if(activity.findViewById(R.id.button_5_1_hosp)!=null){
////						 activity.findViewById(R.id.button_5_1_hosp).setClickable(false);	
//						 
//						 activity.findViewById(R.id.button_5_1_hosp).setOnClickListener(null);
//						 activity.findViewById(R.id.button_5_1_hosp).setOnClickListener(new OnClickListener() {
//							
//							@Override
//							public void onClick(View v) {
//								Toast.makeText(activity, "��Ʈ��ũ�� ����Ǿ� ���� �ʽ��ϴ�. ��Ʈ��ũ ������ Ȯ���� �ֽʽÿ�." ,	Toast.LENGTH_SHORT).show();
//							}
//						});
//					 }
//					 
//					 if(activity.findViewById(R.id.button_config_1_hosp)!=null){
////						 activity.findViewById(R.id.button_config_1_hosp).setClickable(false);	 
//						 
//						 activity.findViewById(R.id.button_config_1_hosp).setOnClickListener(null);
//						 activity.findViewById(R.id.button_config_1_hosp).setOnClickListener(new OnClickListener() {
//							
//							@Override
//							public void onClick(View v) {
//								Toast.makeText(activity, "��Ʈ��ũ�� ����Ǿ� ���� �ʽ��ϴ�. ��Ʈ��ũ ������ Ȯ���� �ֽʽÿ�." ,	Toast.LENGTH_SHORT).show();
//							}
//						});
//					 }
//					 
//					 if(activity.findViewById(R.id.button_config_3_hosp)!=null){
////						 activity.findViewById(R.id.button_config_3_hosp).setClickable(false);	
//						 
//						 activity.findViewById(R.id.button_config_3_hosp).setOnClickListener(null);
//						 activity.findViewById(R.id.button_config_3_hosp).setOnClickListener(new OnClickListener() {
//							
//							@Override
//							public void onClick(View v) {
//								Toast.makeText(activity, "��Ʈ��ũ�� ����Ǿ� ���� �ʽ��ϴ�. ��Ʈ��ũ ������ Ȯ���� �ֽʽÿ�." ,	Toast.LENGTH_SHORT).show();
//							}
//						});
//					 }
					 
					 
				 } else {
					 
					 NETWORK_LIVE = true;
//					 Toast.makeText(context, "���ӻ���11 : " , Toast.LENGTH_SHORT).show();
//					 
//					 if(activity.findViewById(R.id.button_layer1_hosp)!=null){
//						 
////						 activity.findViewById(R.id.button_layer1_hosp).setClickable(true);	
//						 activity.findViewById(R.id.button_layer1_hosp).setOnClickListener(null);
//						 activity.findViewById(R.id.button_layer1_hosp).setOnClickListener(new OnClickListener() {
//								
//								@Override
//								public void onClick(View v) {
//								}
//							});
//					 }
//					 
//					 if(activity.findViewById(R.id.button_2_1_hosp)!=null){
////						 activity.findViewById(R.id.button_2_1_hosp).setClickable(true);
//						 activity.findViewById(R.id.button_2_1_hosp).setOnClickListener(null);
//						 activity.findViewById(R.id.button_2_1_hosp).setOnClickListener(new OnClickListener() {
//								
//								@Override
//								public void onClick(View v) {
//								}
//							});
//					 }
//					 
//					 if(activity.findViewById(R.id.button_2_2_hosp)!=null){
////						 activity.findViewById(R.id.button_2_2_hosp).setClickable(true);	
//						 activity.findViewById(R.id.button_2_2_hosp).setOnClickListener(null);
//						 activity.findViewById(R.id.button_2_2_hosp).setOnClickListener(new OnClickListener() {
//								
//								@Override
//								public void onClick(View v) {
//								}
//							});
//					 }
//					 
//					 if(activity.findViewById(R.id.button_2_3_hosp)!=null){
////						 activity.findViewById(R.id.button_2_3_hosp).setClickable(true);
//						 activity.findViewById(R.id.button_2_3_hosp).setOnClickListener(null);
//						 activity.findViewById(R.id.button_2_3_hosp).setOnClickListener(new OnClickListener() {
//								
//								@Override
//								public void onClick(View v) {
//								}
//							});
//					 }
//					 
//					 if(activity.findViewById(R.id.button_3_1_hosp)!=null){
////						 activity.findViewById(R.id.button_3_1_hosp).setClickable(true);	
//						 activity.findViewById(R.id.button_3_1_hosp).setOnClickListener(null);
//						 activity.findViewById(R.id.button_3_1_hosp).setOnClickListener(new OnClickListener() {
//								
//								@Override
//								public void onClick(View v) {
//								}
//							});
//					 }
//					 
//					 if(activity.findViewById(R.id.button_3_2_hosp)!=null){
////						 activity.findViewById(R.id.button_3_2_hosp).setClickable(true);	
//						 activity.findViewById(R.id.button_3_2_hosp).setOnClickListener(null);
//						 activity.findViewById(R.id.button_3_2_hosp).setOnClickListener(new OnClickListener() {
//								
//								@Override
//								public void onClick(View v) {
//								}
//							});
//					 }
//					 
//					 if(activity.findViewById(R.id.button_5_1_hosp)!=null){
////						 activity.findViewById(R.id.button_5_1_hosp).setClickable(true);
//						 activity.findViewById(R.id.button_5_1_hosp).setOnClickListener(null);
//						 activity.findViewById(R.id.button_5_1_hosp).setOnClickListener(new OnClickListener() {
//								
//								@Override
//								public void onClick(View v) {
//								}
//							});
//					 }
//					 
//					 if(activity.findViewById(R.id.button_config_1_hosp)!=null){
////						 activity.findViewById(R.id.button_config_1_hosp).setClickable(true);	
//						 activity.findViewById(R.id.button_config_1_hosp).setOnClickListener(null);
//						 activity.findViewById(R.id.button_config_1_hosp).setOnClickListener(new OnClickListener() {
//								
//								@Override
//								public void onClick(View v) {
//								}
//							});
//					 }
//					 
//					 if(activity.findViewById(R.id.button_config_3_hosp)!=null){
////						 activity.findViewById(R.id.button_config_3_hosp).setClickable(true);	 
//						 activity.findViewById(R.id.button_config_3_hosp).setOnClickListener(null);
//						 activity.findViewById(R.id.button_config_3_hosp).setOnClickListener(new OnClickListener() {
//								
//								@Override
//								public void onClick(View v) {
//								}
//							});
//					 }
				 }
			} catch (Exception e) {

				//Log.d("ULNetworkReceiver", e.getMessage());
			}
		}
	}
	
	/**
	 * ���� ��� ��Ʈ��ũ ���� ��ȯ
	 * @param activity
	 * @return strConnType "wifi/mobile/No Network"
	 */
	public static final String CONN_STAT_WIFI = "wifi";		// ��������
	public static final String CONN_STAT_MOBILE = "mobile";	// ����� (3g)
	public static final String CONN_STAT_NONE = "No network";	// ������� ����
	
	public static String chkStatus(Activity activity) {
		String strConnType = CONN_STAT_NONE;
		try {
			ConnectivityManager connMgr = (ConnectivityManager)activity.getSystemService(Context.CONNECTIVITY_SERVICE);
			android.net.NetworkInfo wifi = connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
			android.net.NetworkInfo mobile = connMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

			if (wifi.isAvailable()) {
				strConnType = CONN_STAT_WIFI;
			} else if (mobile.isAvailable()) {
				strConnType = CONN_STAT_MOBILE;
			} else {
				strConnType = CONN_STAT_NONE;
			}
		} catch (Exception ex) {
//			Toast.makeText(activity, "������ �߻��Ǿ����ϴ�. �����ڿ� �����ٶ��ϴ�.!!!" + "\n" + ex.toString(), Toast.LENGTH_SHORT).show();
			Log.d("Err_getPhoneNumber ", ex.toString());
		}
		Log.d("Phone Number ", strConnType);
		return strConnType;
	}
}
