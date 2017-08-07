package kr.co.imcc.app.uDiabetesNote;

import android.app.*;
import android.content.*;

public class AlarmService3 extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		
		if(intent.getAction().equals("android.intent.action.BOOT_COMPLETED")){
			
			SetNoti noti =  new SetNoti(context);			
			noti.finalize();
			
		}else{
			String checkday = "";
			checkday = intent.getStringExtra("title");		
			String message = "�������� �˻簡 " + checkday + " �����Ǿ� �ֽ��ϴ�.";		
			
			NotificationManager notificationMgr = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
			Notification notification = new Notification(R.drawable.note, message, System.currentTimeMillis());
			// notification.sound = ringtoneManager.getRingtoneUri(1);
			notification.flags |= Notification.FLAG_AUTO_CANCEL;
			PendingIntent contentIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
			notification.setLatestEventInfo(context, "uDiabetesNote �˻�˸� ����", message, contentIntent);
			notificationMgr.notify(3, notification);
		}
		

	}

}
