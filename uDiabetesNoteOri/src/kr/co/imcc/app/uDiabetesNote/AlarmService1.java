package kr.co.imcc.app.uDiabetesNote;

import android.app.*;
import android.content.*;
import android.util.Log;

public class AlarmService1 extends BroadcastReceiver {
	
	
	@Override
	public void onReceive(Context context, Intent intent) {
		
		
		if(intent.getAction().equals("android.intent.action.BOOT_COMPLETED")){
			Log.d("��¥����:", "ok");		
			
			SetNoti noti =  new SetNoti(context);
			noti.finalize();
			
		}else{
			
			Log.d("��¥����:", "ok3");
			String checkday = "";
			checkday = intent.getStringExtra("title");		
			String message = "��ȭ������ �˻簡 " + checkday + " �����Ǿ� �ֽ��ϴ�.";		
			
			NotificationManager notificationMgr = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
			Notification notification = new Notification(R.drawable.note, message, System.currentTimeMillis());
			// notification.sound = ringtoneManager.getRingtoneUri(1);
			notification.flags |= Notification.FLAG_AUTO_CANCEL;
			PendingIntent contentIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
			notification.setLatestEventInfo(context, "uDiabetesNote �˻�˸� ����", message, contentIntent);
			notificationMgr.notify(1, notification);
			
		}
		
	}	
	

}
