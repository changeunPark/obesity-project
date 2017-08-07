package kr.co.imcc.app.uDiabetesNote;

import java.util.*;

import android.app.*;
import android.content.*;
import android.database.*;
import android.database.sqlite.*;

public class SetNoti extends android.app.Application {

//	static int i=0;
	DBHelpepr dbHelpepr = null;
	SQLiteDatabase sqLiteDatabase = null;
	public static Context contxt;
	
	public SetNoti(Context context) {
		// TODO Auto-generated constructor stub
		contxt = context;
		dbHelpepr = new DBHelpepr(contxt);
		sqLiteDatabase = dbHelpepr.getWritableDatabase();
		
		setAlarm();
	}
	
	
	public static Context getContext(){
		return contxt;
	}
	
	protected void finalize(){
		
        sqLiteDatabase.close();
        dbHelpepr.close();
		
	}	
	
//	static int id=0;
//	int now;
	
	public void setAlarm(){
		Calendar cal = Calendar.getInstance();
		String sqlAlarm = null;
		Cursor cursorAlarm = null;
		
        sqlAlarm = "select checknm, alarm, checkdate from DIABETESCHECKCYCLE";
        cursorAlarm = sqLiteDatabase.rawQuery(sqlAlarm, null);
        
        if (cursorAlarm.getCount() > 0) {
			
        	cursorAlarm.moveToFirst();
			
			do {
				
				if(cursorAlarm.getString(0).equals("��ȭ������") && !cursorAlarm.getString(1).equals("0")){
					
					Intent intent1 = new Intent(contxt, kr.co.imcc.app.uDiabetesNote.AlarmService1.class);
				    String checkdate = cursorAlarm.getString(2);
				    
//				    Toast.makeText(ActivityConfig_5.this, "�˻翹����: " + checkdate  ,	Toast.LENGTH_SHORT).show();
					if(cursorAlarm.getString(1).equals("1")){
				    	intent1.setAction("plane");
				    	intent1.putExtra("title", "����");
				    	intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				    	
//				    	if(checkdateInt)
					    cal.setTimeInMillis(System.currentTimeMillis());   
					    cal.clear();
						cal.set(Integer.parseInt(checkdate.substring(0, 4)), Integer.parseInt(checkdate.substring(5, 7))-1, Integer.parseInt(checkdate.substring(8, 10))-1, 8, 0);
						
						long dayDiff = 0;
						long result = (cal.getTimeInMillis() - Calendar.getInstance().getTimeInMillis())/1000;
						dayDiff = result/(60*60*24);
						
//						Log.d("��¥����:", dayDiff + "ok2");
						
						if(dayDiff>=0) {							
							PendingIntent pending = PendingIntent.getBroadcast(contxt, 0, intent1, PendingIntent.FLAG_UPDATE_CURRENT);
							AlarmManager am = (AlarmManager)contxt.getSystemService(Context.ALARM_SERVICE);
						    am.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pending);
						    
						}
												
					
					}else if(cursorAlarm.getString(1).equals("2")){
						intent1.setAction("plane");
						intent1.putExtra("title", "��");
						cal.setTimeInMillis(System.currentTimeMillis());   
					    cal.clear();
						cal.set(Integer.parseInt(checkdate.substring(0, 4)), Integer.parseInt(checkdate.substring(5, 7))-1, Integer.parseInt(checkdate.substring(8, 10))-2, 8, 0);
						
						long dayDiff = 0;
						long result = (cal.getTimeInMillis() - Calendar.getInstance().getTimeInMillis())/1000;
						dayDiff = result/(60*60*24);
						
//						Log.d("��¥����:", dayDiff + "");
						
						if(dayDiff>=0) {							
							PendingIntent pending = PendingIntent.getBroadcast(contxt, 0, intent1, PendingIntent.FLAG_UPDATE_CURRENT);
							AlarmManager am = (AlarmManager)contxt.getSystemService(contxt.ALARM_SERVICE);
						    am.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pending);							
						}
						
					}else if(cursorAlarm.getString(1).equals("3")){
						intent1.setAction("plane");
						intent1.putExtra("title", "������");
						cal.setTimeInMillis(System.currentTimeMillis());   
					    cal.clear();
						cal.set(Integer.parseInt(checkdate.substring(0, 4)), Integer.parseInt(checkdate.substring(5, 7))-1, Integer.parseInt(checkdate.substring(8, 10))-3, 8, 0);
						
						long dayDiff = 0;
						long result = (cal.getTimeInMillis() - Calendar.getInstance().getTimeInMillis())/1000;
						dayDiff = result/(60*60*24);
						
//						Log.d("��¥����:", dayDiff + "");
						
						if(dayDiff>=0) {							
							PendingIntent pending = PendingIntent.getBroadcast(contxt, 0, intent1, PendingIntent.FLAG_UPDATE_CURRENT);
							AlarmManager am = (AlarmManager)contxt.getSystemService(contxt.ALARM_SERVICE);
						    am.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pending);							
						}
						
					}			
					
					
				
				}else if(cursorAlarm.getString(0).equals("�ܹ鴢�˻�") && !cursorAlarm.getString(1).equals("0")){
					Intent intent2 = new Intent(contxt, AlarmService2.class);
				    
				    String checkdate = cursorAlarm.getString(2);
//				    Toast.makeText(ActivityConfig_5.this, "�˻翹����: " + checkdate  ,	Toast.LENGTH_SHORT).show();
					if(cursorAlarm.getString(1).equals("1")){
						intent2.setAction("plane");
						intent2.putExtra("title", "����");
					    cal.setTimeInMillis(System.currentTimeMillis());   
					    cal.clear();
						cal.set(Integer.parseInt(checkdate.substring(0, 4)), Integer.parseInt(checkdate.substring(5, 7))-1, Integer.parseInt(checkdate.substring(8, 10))-1, 8, 0);
						
						long dayDiff = 0;
						long result = (cal.getTimeInMillis() - Calendar.getInstance().getTimeInMillis())/1000;
						dayDiff = result/(60*60*24);
						
//						Log.d("��¥����:", dayDiff + "");
						
						if(dayDiff>=0) {		
							PendingIntent pending = PendingIntent.getBroadcast(contxt, 0, intent2, PendingIntent.FLAG_UPDATE_CURRENT);
						    AlarmManager am = (AlarmManager)contxt.getSystemService(contxt.ALARM_SERVICE);
						    am.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pending);
						}
						
					}else if(cursorAlarm.getString(1).equals("2")){
						intent2.setAction("plane");
						intent2.putExtra("title", "��");
						cal.setTimeInMillis(System.currentTimeMillis());   
					    cal.clear();
						cal.set(Integer.parseInt(checkdate.substring(0, 4)), Integer.parseInt(checkdate.substring(5, 7))-1, Integer.parseInt(checkdate.substring(8, 10))-2, 8, 0);
						
						long dayDiff = 0;
						long result = (cal.getTimeInMillis() - Calendar.getInstance().getTimeInMillis())/1000;
						dayDiff = result/(60*60*24);
						
//						Log.d("��¥����:", dayDiff + "");
						
						if(dayDiff>=0) {		
							PendingIntent pending = PendingIntent.getBroadcast(contxt, 0, intent2, PendingIntent.FLAG_UPDATE_CURRENT);
						    AlarmManager am = (AlarmManager)contxt.getSystemService(contxt.ALARM_SERVICE);
						    am.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pending);
						}
						
					}else if(cursorAlarm.getString(1).equals("3")){
						intent2.setAction("plane");
						intent2.putExtra("title", "������");
						cal.setTimeInMillis(System.currentTimeMillis());   
					    cal.clear();
						cal.set(Integer.parseInt(checkdate.substring(0, 4)), Integer.parseInt(checkdate.substring(5, 7))-1, Integer.parseInt(checkdate.substring(8, 10))-3, 8, 0);
						
						long dayDiff = 0;
						long result = (cal.getTimeInMillis() - Calendar.getInstance().getTimeInMillis())/1000;
						dayDiff = result/(60*60*24);
						
//						Log.d("��¥����:", dayDiff + "");
						
						if(dayDiff>=0) {		
							PendingIntent pending = PendingIntent.getBroadcast(contxt, 0, intent2, PendingIntent.FLAG_UPDATE_CURRENT);
						    AlarmManager am = (AlarmManager)contxt.getSystemService(contxt.ALARM_SERVICE);
						    am.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pending);
						}
						
					}			

					
				}else if(cursorAlarm.getString(0).equals("���������˻�") && !cursorAlarm.getString(1).equals("0")){
					Intent intent3 = new Intent(contxt, AlarmService3.class);
				    
				    String checkdate = cursorAlarm.getString(2);
//				    Toast.makeText(ActivityConfig_5.this, "�˻翹����: " + checkdate  ,	Toast.LENGTH_SHORT).show();
					if(cursorAlarm.getString(1).equals("1")){
						intent3.setAction("plane");
						intent3.putExtra("title", "����");
					    cal.setTimeInMillis(System.currentTimeMillis());   
					    cal.clear();
						cal.set(Integer.parseInt(checkdate.substring(0, 4)), Integer.parseInt(checkdate.substring(5, 7))-1, Integer.parseInt(checkdate.substring(8, 10))-1, 8, 0);
						
						long dayDiff = 0;
						long result = (cal.getTimeInMillis() - Calendar.getInstance().getTimeInMillis())/1000;
						dayDiff = result/(60*60*24);
						
//						Log.d("��¥����:", dayDiff + "");
						
						if(dayDiff>=0) {	
							PendingIntent pending = PendingIntent.getBroadcast(contxt, 0, intent3, PendingIntent.FLAG_UPDATE_CURRENT);
						    AlarmManager am = (AlarmManager)contxt.getSystemService(contxt.ALARM_SERVICE);
						    am.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pending);
						}
						
					}else if(cursorAlarm.getString(1).equals("2")){
						intent3.setAction("plane");
						intent3.putExtra("title", "��");
						cal.setTimeInMillis(System.currentTimeMillis());   
					    cal.clear();
						cal.set(Integer.parseInt(checkdate.substring(0, 4)), Integer.parseInt(checkdate.substring(5, 7))-1, Integer.parseInt(checkdate.substring(8, 10))-2, 8, 0);
						
						long dayDiff = 0;
						long result = (cal.getTimeInMillis() - Calendar.getInstance().getTimeInMillis())/1000;
						dayDiff = result/(60*60*24);
						
//						Log.d("��¥����:", dayDiff + "");
						
						if(dayDiff>=0) {	
							PendingIntent pending = PendingIntent.getBroadcast(contxt, 0, intent3, PendingIntent.FLAG_UPDATE_CURRENT);
						    AlarmManager am = (AlarmManager)contxt.getSystemService(contxt.ALARM_SERVICE);
						    am.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pending);
						}
						
					}else if(cursorAlarm.getString(1).equals("3")){
						intent3.setAction("plane");
						intent3.putExtra("title", "������");
						cal.setTimeInMillis(System.currentTimeMillis());   
					    cal.clear();
						cal.set(Integer.parseInt(checkdate.substring(0, 4)), Integer.parseInt(checkdate.substring(5, 7))-1, Integer.parseInt(checkdate.substring(8, 10))-3, 8, 0);
						
						long dayDiff = 0;
						long result = (cal.getTimeInMillis() - Calendar.getInstance().getTimeInMillis())/1000;
						dayDiff = result/(60*60*24);
						
//						Log.d("��¥����:", dayDiff + "");
						
						if(dayDiff>=0) {	
							PendingIntent pending = PendingIntent.getBroadcast(contxt, 0, intent3, PendingIntent.FLAG_UPDATE_CURRENT);
						    AlarmManager am = (AlarmManager)contxt.getSystemService(contxt.ALARM_SERVICE);
						    am.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pending);
						}
					}			

					
				}else if(cursorAlarm.getString(0).equals("����ũ����Ƽ��") && !cursorAlarm.getString(1).equals("0")){
					Intent intent4 = new Intent(contxt, AlarmService4.class);
				    
				    String checkdate = cursorAlarm.getString(2);
//				    Toast.makeText(ActivityConfig_5.this, "�˻翹����: " + checkdate  ,	Toast.LENGTH_SHORT).show();
					if(cursorAlarm.getString(1).equals("1")){
						intent4.setAction("plane");
						intent4.putExtra("title", "����");
					    cal.setTimeInMillis(System.currentTimeMillis());   
					    cal.clear();
						cal.set(Integer.parseInt(checkdate.substring(0, 4)), Integer.parseInt(checkdate.substring(5, 7))-1, Integer.parseInt(checkdate.substring(8, 10))-1, 8, 0);
						
						long dayDiff = 0;
						long result = (cal.getTimeInMillis() - Calendar.getInstance().getTimeInMillis())/1000;
						dayDiff = result/(60*60*24);
						
//						Log.d("��¥����:", dayDiff + "");
						
						if(dayDiff>=0) {	
							PendingIntent pending = PendingIntent.getBroadcast(contxt, 0, intent4, PendingIntent.FLAG_UPDATE_CURRENT);
						    AlarmManager am = (AlarmManager)contxt.getSystemService(contxt.ALARM_SERVICE);
						    am.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pending);
						}
						
					}else if(cursorAlarm.getString(1).equals("2")){
						intent4.setAction("plane");
						intent4.putExtra("title", "��");
						cal.setTimeInMillis(System.currentTimeMillis());   
					    cal.clear();
						cal.set(Integer.parseInt(checkdate.substring(0, 4)), Integer.parseInt(checkdate.substring(5, 7))-1, Integer.parseInt(checkdate.substring(8, 10))-2, 8, 0);
						
						long dayDiff = 0;
						long result = (cal.getTimeInMillis() - Calendar.getInstance().getTimeInMillis())/1000;
						dayDiff = result/(60*60*24);
						
//						Log.d("��¥����:", dayDiff + "");
						
						if(dayDiff>=0) {	
							PendingIntent pending = PendingIntent.getBroadcast(contxt, 0, intent4, PendingIntent.FLAG_UPDATE_CURRENT);
						    AlarmManager am = (AlarmManager)contxt.getSystemService(contxt.ALARM_SERVICE);
						    am.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pending);
						}
						
					}else if(cursorAlarm.getString(1).equals("3")){
						intent4.setAction("plane");
						intent4.putExtra("title", "������");
						cal.setTimeInMillis(System.currentTimeMillis());   
					    cal.clear();
						cal.set(Integer.parseInt(checkdate.substring(0, 4)), Integer.parseInt(checkdate.substring(5, 7))-1, Integer.parseInt(checkdate.substring(8, 10))-3, 8, 0);
						
						long dayDiff = 0;
						long result = (cal.getTimeInMillis() - Calendar.getInstance().getTimeInMillis())/1000;
						dayDiff = result/(60*60*24);
						
//						Log.d("��¥����:", dayDiff + "");
						
						if(dayDiff>=0) {	
							PendingIntent pending = PendingIntent.getBroadcast(contxt, 0, intent4, PendingIntent.FLAG_UPDATE_CURRENT);
						    AlarmManager am = (AlarmManager)contxt.getSystemService(contxt.ALARM_SERVICE);
						    am.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pending);
						}
						
					}			

					
				}else if(cursorAlarm.getString(0).equals("�Ȱ��˻�") && !cursorAlarm.getString(1).equals("0")){
					Intent intent5 = new Intent(contxt, AlarmService5.class);
				    
				    String checkdate = cursorAlarm.getString(2);
//				    Toast.makeText(ActivityConfig_5.this, "�˻翹����: " + checkdate  ,	Toast.LENGTH_SHORT).show();
					if(cursorAlarm.getString(1).equals("1")){
						intent5.setAction("plane");
						intent5.putExtra("title", "����");
					    cal.setTimeInMillis(System.currentTimeMillis());   
					    cal.clear();
						cal.set(Integer.parseInt(checkdate.substring(0, 4)), Integer.parseInt(checkdate.substring(5, 7))-1, Integer.parseInt(checkdate.substring(8, 10))-1, 8, 0);
						
						long dayDiff = 0;
						long result = (cal.getTimeInMillis() - Calendar.getInstance().getTimeInMillis())/1000;
						dayDiff = result/(60*60*24);
						
//						Log.d("��¥����:", dayDiff + "");
						
						if(dayDiff>=0) {
							PendingIntent pending = PendingIntent.getBroadcast(contxt, 0, intent5, PendingIntent.FLAG_UPDATE_CURRENT);
						    AlarmManager am = (AlarmManager)contxt.getSystemService(contxt.ALARM_SERVICE);
						    am.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pending);
						}
						
					}else if(cursorAlarm.getString(1).equals("2")){
						intent5.setAction("plane");
						intent5.putExtra("title", "��");
						cal.setTimeInMillis(System.currentTimeMillis());   
					    cal.clear();
						cal.set(Integer.parseInt(checkdate.substring(0, 4)), Integer.parseInt(checkdate.substring(5, 7))-1, Integer.parseInt(checkdate.substring(8, 10))-2, 8, 0);
						
						long dayDiff = 0;
						long result = (cal.getTimeInMillis() - Calendar.getInstance().getTimeInMillis())/1000;
						dayDiff = result/(60*60*24);
						
//						Log.d("��¥����:", dayDiff + "");
						
						if(dayDiff>=0) {
							PendingIntent pending = PendingIntent.getBroadcast(contxt, 0, intent5, PendingIntent.FLAG_UPDATE_CURRENT);
						    AlarmManager am = (AlarmManager)contxt.getSystemService(contxt.ALARM_SERVICE);
						    am.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pending);
						}
						
					}else if(cursorAlarm.getString(1).equals("3")){
						intent5.setAction("plane");
						intent5.putExtra("title", "������");
						cal.setTimeInMillis(System.currentTimeMillis());   
					    cal.clear();
						cal.set(Integer.parseInt(checkdate.substring(0, 4)), Integer.parseInt(checkdate.substring(5, 7))-1, Integer.parseInt(checkdate.substring(8, 10))-3, 8, 0);
						
						long dayDiff = 0;
						long result = (cal.getTimeInMillis() - Calendar.getInstance().getTimeInMillis())/1000;
						dayDiff = result/(60*60*24);
						
//						Log.d("��¥����:", dayDiff + "");
						
						if(dayDiff>=0) {
							PendingIntent pending = PendingIntent.getBroadcast(contxt, 0, intent5, PendingIntent.FLAG_UPDATE_CURRENT);
						    AlarmManager am = (AlarmManager)contxt.getSystemService(contxt.ALARM_SERVICE);
						    am.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pending);
						}
						
					}			

				}

			} while (cursorAlarm.moveToNext());
			
		}
			
        cursorAlarm.close();
        
	}
	
	
}
