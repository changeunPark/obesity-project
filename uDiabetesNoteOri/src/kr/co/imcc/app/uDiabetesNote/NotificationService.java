package kr.co.imcc.app.uDiabetesNote;

import java.util.*;

import android.app.*;
import android.content.*;
import android.os.*;
import android.util.Log;

public class NotificationService extends Service {
	private NotificationManager notificationMgr;

	private static final String METHOD_NAME = "Get_NotifyAll";
	private static final String NAMESPACE = "http://M.Gilhospital.com/";
	private static final String URL = "http://mobile.gilhospital.com:8801/MobileWebService.asmx";
	private static final String SOAP_ACTION = NAMESPACE + METHOD_NAME;

	private Timer timer = new Timer();
	private static long UPDATE_INTERVAL = 100000000;
	
	public static ArrayList<HashMap<String, String>> ArrayListDiabetesAlert = new ArrayList<HashMap<String, String>>();

	// XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
	// static data/shared references, etc.
	// XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
	public static ServiceUpdateUIListener UI_UPDATE_LISTENER;
//	private static tMainActivity MAIN_ACTIVITY;

	// XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
	// hooks into other activities
	// XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
//	public static void setMainActivity(tMainActivity activity) {
//		MAIN_ACTIVITY = activity;
//	}

	public static void setUpdateListener(ServiceUpdateUIListener l) {
		UI_UPDATE_LISTENER = l;
	}

	public interface ServiceUpdateUIListener {

		public void updateUI(Hashtable<String, Hashtable<String, String>> data);

	}// end interface

	
	
	@Override
	public void onCreate() {
		super.onCreate();
//		 _startService();

		// if(tMainActivity.Time.equals("")){
		// lastSendTime = "2000-01-01 �ㅽ??1:00:00";
		// getLastSendTime();
		// }else{
		// lastSendTime = tMainActivity.Time;
		// Log.d("lastTime", lastSendTime);
		// }
		

		notificationMgr = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		// displayNotificationMessage("starting Background Service");
		Thread thr = new Thread(null, new ServiceWorker(), "BackgroundService");
		thr.start();
	}

	class ServiceWorker implements Runnable {
		public void run() {
			_startService();
		}
	}

//	public static String lastSendTime;
//
//	public void getLastSendTime() {
//
//		try {
//			SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
//			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
//			envelope.dotNet = true;
//
//			request.addProperty("strToEmplNo", tMainActivity.Login_ID);
//			envelope.setOutputSoapObject(request);
//
//			HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
//			androidHttpTransport.call(SOAP_ACTION, envelope);
//
//			SoapObject result = (SoapObject) envelope.bodyIn;
//			String resultData = result.getProperty(0).toString();
//			Log.d("kkkkkkkkkkkkkkkkkkkkkkkkkkk", resultData);
//
//			sampleHandler = new MySampleHandler(NotificationService.this);
//			Xml.parse(resultData, sampleHandler);
//
//			HashMap<String, Object> item = (HashMap<String, Object>) sampleHandler.arrayList.get(0);
//			lastSendTime = (String) item.get("SEND_DATE");
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

//	MySampleHandler sampleHandler;

	private void _startService() {
		
		timer.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				try {
					
//					HospitalInterface hospitalInterface = new HospitalInterface();
//
//					String result = hospitalInterface.getDiabetesAlertWithThread("DiabetesRsrvAlert", ActivityLogin.strPatientID);
//
//					Log.d("test", "resultDiabetes: " + result);
//					GetDiabetesAlertHandler handler = new GetDiabetesAlertHandler();
//					try {
//						Xml.parse(result, handler);
//					} catch (SAXException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//					
//					notiDiabetes();
					
					
				} catch (Exception ex) {
//					displayNotificationMessage2();
					Log.d(getClass().getSimpleName(), ex.toString());
				}
			}
		}, 0, UPDATE_INTERVAL);
		Log.i(getClass().getSimpleName(), "Timer started!!!");
	}
	
	public void notiDiabetes(){
		
		for(int i=0; i<ArrayListDiabetesAlert.size();i++){
			
			HashMap<String, String> item = (HashMap<String, String>) ArrayListDiabetesAlert.get(i);
			String rsrvMessage = item.get("rsrvdate").toString().substring(0, 4) + "년 " 
			                  + item.get("rsrvdate").toString().substring(5, 7) + "월 "
			                  + item.get("rsrvdate").toString().substring(8, 10) + "일  "
			                  + item.get("rsrvtime").toString().substring(0, 2) + "시"
			                  + item.get("rsrvtime").toString().substring(3, 5) + "분 진료예약";
			                  
			displayNotificationMessage(rsrvMessage);
		}
		
//		displayNotificationMessage("1111");
//		displayNotificationMessage("2222");//
	}

	@Override
	public void onDestroy() {
		// displayNotificationMessage("stopping Background Service");
		super.onDestroy();
	}

	@Override
	public void onStart(Intent intent, int startId) {
		super.onStart(intent, startId);
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
	
	public static String flag = "";
	public static String notifyName="";
	public static String message="";

//	private void displayDialogNotification() {
//		
//		flag = "1";
//		
//		
//		int totalMsgCount = sampleHandler.arrayList.size();
//
//		HashMap<String, Object> item = (HashMap<String, Object>) sampleHandler.arrayList.get(0);
//		notifyName = (String) item.get("NOTIFY_NM");
//		message = (String) item.get("MESSAGE");
//		seqNum = (String) item.get("SEQ");
//		Log.d("ttttttttttttttt", "ttttttttttttttt");
////		Log.d("seqqqqqqqqqqqqqqq", seqNum);
//		
//		Intent i = new Intent(this, DialogNotification.class);		
//		i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
////		i.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
//
////		i.putExtra("NOTIFY_NM", notifyName);
////		i.putExtra("MESSAGE", message);
////		i.putExtra("notiURL", "http://mobile.gilhospital.com:8800/Announce/AnnounceView.aspx?SEQ=" + seqNum
////				+ "&isApp=Y");
//
//		startActivity(i);
//	}
	
	static int lastId=0;
	
	private void displayNotificationMessage(String strRsrvMessage) {
		
		UPDATE_INTERVAL = 3600000;
		
		String message = strRsrvMessage;

		long[] vib = { 200, 2000 };

		Notification notification = new Notification(R.drawable.note, message, System.currentTimeMillis());
		// notification.sound = ringtoneManager.getRingtoneUri(1);
		notification.flags |= Notification.FLAG_AUTO_CANCEL;
//		notification.flags |= Notification.FLAG_INSISTENT;
		// notification.flags |= Notification.FLAG_ONLY_ALERT_ONCE;
		// notification.vibrate = vib;

//		Intent intent = new Intent(this, tMainActivity.class);
//		seqNum = (String) item.get("SEQ");
//		intent.putExtra(""+seqNum, "http://mobile.gilhospital.com:8800/Announce/AnnounceView.aspx?SEQ=" + seqNum
//				+ "&isApp=Y");
		PendingIntent contentIntent = PendingIntent.getActivity(this, 0, null, 0);
		notification.setLatestEventInfo(this, "uDiabetesNote 예약 알림서비스", message, contentIntent);
		notificationMgr.notify(lastId, notification);

		Vibrator vibrator = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
		vibrator.vibrate(1000);
	}

	
}
