package kr.co.imcc.app.uDiabetesNote;

import java.text.*;
import java.util.*;

import org.xml.sax.*;
import org.xml.sax.helpers.*;

import android.content.*;
import android.database.*;
import android.util.Log;

public class GetDiabetesAlertHandler extends DefaultHandler {

	boolean haslastcheckdate = false;
	boolean haschecknm = false;
	boolean hascheckcycle = false;

	// static long HB_COUNT = 0;

	String sqlDiabetesAlertList = "";
	Cursor cursorDiabetesAlertList;

	StringBuilder lastcheckdate;
	StringBuilder checknm;
	StringBuilder checkcycle;

	int startlastcheckdate;
	int lastcheckdateLength;

	int startchecknm;
	int checknmLength;

	int startcheckcycle;
	int checkcycleLength;

	public GetDiabetesAlertHandler() {

		lastcheckdate = new StringBuilder();
		checknm = new StringBuilder();
		checkcycle = new StringBuilder();
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes atts) {
		if (localName.equals("LastCheckDate")) {
			haslastcheckdate = true;
		} else if (localName.equals("CheckNm")) {
			haschecknm = true;
		} else if (localName.equals("CheckCycle")) {
			hascheckcycle = true;
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) {
		
		if (localName.equals("LastCheckDate")) {
			haslastcheckdate = false;
		} else if (localName.equals("CheckNm")) {
			haschecknm = false;
		} else if (localName.equals("CheckCycle")) {
			hascheckcycle = false;
		} 
		
		if (localName.equals("DiabetesCheckCycle")) {
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);
			Date date = new Date();
			int currdate = Integer.parseInt(dateFormat.format(date));
			
			
			Calendar calendar = Calendar.getInstance();
			calendar.set(Integer.parseInt(lastcheckdate.toString().substring(0, 4)), Integer.parseInt(lastcheckdate.toString().substring(4, 6)), Integer.parseInt(lastcheckdate.toString().substring(6, 8)));
			calendar.add(calendar.MONTH, 3);
			String checkdatetemp = makeDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
			int checkdate = Integer.parseInt(checkdatetemp.replace("/", ""));
			
			Log.d("dddfdffd111", checknm.toString());
			
			if(checknm.toString().equals("��ȭ������")) {
				
				sqlDiabetesAlertList = "select checknm, hospital, checkdate from DIABETESCHECKCYCLE where checknm is '��ȭ������'";
				cursorDiabetesAlertList = ActivityConfig_5.sqLiteDatabase.rawQuery(sqlDiabetesAlertList, null);
				
				ContentValues cv = new ContentValues();
				cv.put("lastcheckdate", lastcheckdate.toString().substring(0, 4)+"/"+lastcheckdate.toString().substring(4, 6)+"/" + lastcheckdate.toString().substring(6, 8));
				cv.put("checkdate", (checkdate+"").toString().substring(0, 4)+"/"+(checkdate+"").toString().substring(4, 6)+"/" + (checkdate+"").toString().substring(6, 8));
				cv.put("checknm", checknm.toString());
				cv.put("checkcycle", checkcycle.toString());
				cv.put("hospital", "Y");
				cv.put("alarm", "0");
				
				if (cursorDiabetesAlertList.getCount() > 0) { //tvConnect1		
					
					cursorDiabetesAlertList.moveToFirst();
					
					if(cursorDiabetesAlertList.getString(1).equals("Y") || (Integer.parseInt(cursorDiabetesAlertList.getString(2).replace("/", "")) < currdate) ){
						ActivityConfig_5.sqLiteDatabase.update("DIABETESCHECKCYCLE", cv, "checknm=?", new String[] { checknm.toString() });
					}
				}else{
					ActivityConfig_5.sqLiteDatabase.insert("DIABETESCHECKCYCLE", "", cv);
				}
				
			}else if(checknm.toString().equals("�ܹ鴢�˻�")){
				
				sqlDiabetesAlertList = "select checknm, hospital, checkdate from DIABETESCHECKCYCLE where checknm is '�ܹ鴢�˻�'";
				cursorDiabetesAlertList = ActivityConfig_5.sqLiteDatabase.rawQuery(sqlDiabetesAlertList, null);
				
				ContentValues cv = new ContentValues();
				cv.put("lastcheckdate", lastcheckdate.toString().substring(0, 4)+"/"+lastcheckdate.toString().substring(4, 6)+"/" + lastcheckdate.toString().substring(6, 8));
				cv.put("checkdate", (checkdate+"").toString().substring(0, 4)+"/"+(checkdate+"").toString().substring(4, 6)+"/" + (checkdate+"").toString().substring(6, 8));
				cv.put("checknm", checknm.toString());
				cv.put("checkcycle", checkcycle.toString());
				cv.put("hospital", "Y");
				cv.put("alarm", "0");
				
				if (cursorDiabetesAlertList.getCount() > 0) { //tvConnect1		
					
					cursorDiabetesAlertList.moveToFirst();
					
					if(cursorDiabetesAlertList.getString(1).equals("Y") || (Integer.parseInt(cursorDiabetesAlertList.getString(2).replace("/", "")) < currdate) ){
						ActivityConfig_5.sqLiteDatabase.update("DIABETESCHECKCYCLE", cv, "checknm=?", new String[] { checknm.toString() });
					}
					
				}else{
					ActivityConfig_5.sqLiteDatabase.insert("DIABETESCHECKCYCLE", "", cv);
				}
				
			}else if(checknm.toString().equals("���������˻�")){
				
				sqlDiabetesAlertList = "select checknm, hospital, checkdate from DIABETESCHECKCYCLE where checknm is '���������˻�'";
				cursorDiabetesAlertList = ActivityConfig_5.sqLiteDatabase.rawQuery(sqlDiabetesAlertList, null);
				
				ContentValues cv = new ContentValues();
				cv.put("lastcheckdate", lastcheckdate.toString().substring(0, 4)+"/"+lastcheckdate.toString().substring(4, 6)+"/" + lastcheckdate.toString().substring(6, 8));
				cv.put("checkdate", (checkdate+"").toString().substring(0, 4)+"/"+(checkdate+"").toString().substring(4, 6)+"/" + (checkdate+"").toString().substring(6, 8));
				cv.put("checknm", checknm.toString());
				cv.put("checkcycle", checkcycle.toString());
				cv.put("hospital", "Y");
				cv.put("alarm", "0");
				
				if (cursorDiabetesAlertList.getCount() > 0) { //tvConnect1		
					
					cursorDiabetesAlertList.moveToFirst();
					
					if(cursorDiabetesAlertList.getString(1).equals("Y") || (Integer.parseInt(cursorDiabetesAlertList.getString(2).replace("/", "")) < currdate) ){
						ActivityConfig_5.sqLiteDatabase.update("DIABETESCHECKCYCLE", cv, "checknm=?", new String[] { checknm.toString() });
					}
				}else{
					ActivityConfig_5.sqLiteDatabase.insert("DIABETESCHECKCYCLE", "", cv);
				}
				
			}else if(checknm.toString().equals("����ũ����Ƽ��")){
				
				sqlDiabetesAlertList = "select checknm, hospital, checkdate from DIABETESCHECKCYCLE where checknm is '����ũ����Ƽ��'";
				cursorDiabetesAlertList = ActivityConfig_5.sqLiteDatabase.rawQuery(sqlDiabetesAlertList, null);
				
				ContentValues cv = new ContentValues();
				cv.put("lastcheckdate", lastcheckdate.toString().substring(0, 4)+"/"+lastcheckdate.toString().substring(4, 6)+"/" + lastcheckdate.toString().substring(6, 8));
				cv.put("checkdate", (checkdate+"").toString().substring(0, 4)+"/"+(checkdate+"").toString().substring(4, 6)+"/" + (checkdate+"").toString().substring(6, 8));
				cv.put("checknm", checknm.toString());
				cv.put("checkcycle", checkcycle.toString());
				cv.put("hospital", "Y");
				cv.put("alarm", "0");
				
				if (cursorDiabetesAlertList.getCount() > 0) { //tvConnect1		
					
					cursorDiabetesAlertList.moveToFirst();
					
					if(cursorDiabetesAlertList.getString(1).equals("Y") || (Integer.parseInt(cursorDiabetesAlertList.getString(2).replace("/", "")) < currdate) )
					{
						ActivityConfig_5.sqLiteDatabase.update("DIABETESCHECKCYCLE", cv, "checknm=?", new String[] { checknm.toString() });
					}
				}
				else
				{
					ActivityConfig_5.sqLiteDatabase.insert("DIABETESCHECKCYCLE", "", cv);
				}
				
			}else if(checknm.toString().equals("�Ȱ��˻�")){
				
				sqlDiabetesAlertList = "select checknm, hospital, checkdate from DIABETESCHECKCYCLE where checknm is '�Ȱ��˻�'";
				cursorDiabetesAlertList = ActivityConfig_5.sqLiteDatabase.rawQuery(sqlDiabetesAlertList, null);
				
				ContentValues cv = new ContentValues();
				cv.put("lastcheckdate", lastcheckdate.toString().substring(0, 4)+"/"+lastcheckdate.toString().substring(4, 6)+"/" + lastcheckdate.toString().substring(6, 8));
				cv.put("checkdate", (checkdate+"").toString().substring(0, 4)+"/"+(checkdate+"").toString().substring(4, 6)+"/" + (checkdate+"").toString().substring(6, 8));
				cv.put("checknm", checknm.toString());
				cv.put("checkcycle", checkcycle.toString());
				cv.put("hospital", "Y");
				cv.put("alarm", "0");
				
				if (cursorDiabetesAlertList.getCount() > 0) { //tvConnect1		
					
					cursorDiabetesAlertList.moveToFirst();
					
					if(cursorDiabetesAlertList.getString(1).equals("Y") || (Integer.parseInt(cursorDiabetesAlertList.getString(2).replace("/", "")) < currdate) ){
						ActivityConfig_5.sqLiteDatabase.update("DIABETESCHECKCYCLE", cv, "checknm=?", new String[] { checknm.toString() });
					}
				}else{
					ActivityConfig_5.sqLiteDatabase.insert("DIABETESCHECKCYCLE", "", cv);
				}
				
			}
			
//			String startDate = "19990101";			
//			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);
//			Date date = new Date();
//			String endDate = dateFormat.format(date);
			
			// test = item.get("PatientID");
			// Log.d("1", item.get("UserType"));
			// Log.d("2", item.get("InformationDisclosure"));
			// Log.d("3", item.get("PatientName"));
			// Log.d("4", item.get("ResidentID"));
			// Log.d("5", item.get("PatientID"));
			// Log.d("info", "result2: "+GetLoginInfoHandler.test);
			// NotificationService.ArrayListDiabetesAlert.add(item);

			lastcheckdate.delete(startlastcheckdate, lastcheckdateLength);
			checknm.delete(startchecknm, checknmLength);
			checkcycle.delete(startcheckcycle, checkcycleLength);

			// HashMap<String, String> item1 = (HashMap<String, String>)
			// ActivityLogin.ArrayListLoginInfo.get(0);
			// Log.d("info", "result6: "+item1.get("UserType").toString());
			// Log.d("info",
			// "result7: "+item1.get("InformationDisclosure").toString());
			// Log.d("info", "result8: "+item1.get("PatientName").toString());
			// Log.d("info", "result9: "+item1.get("ResidentID").toString());
			// Log.d("info", "result10: "+item1.get("PatientID").toString());
		}
	}

	@Override
	public void characters(char[] chars, int start, int leng) {

		if (haslastcheckdate) {
			startlastcheckdate = start;
			lastcheckdateLength = leng;

			haslastcheckdate = false;
			lastcheckdate.append(chars, start, leng);
		} else if (haschecknm) {
			startchecknm = start;
			checknmLength = leng;

			haschecknm = false;
			checknm.append(chars, start, leng);
		} else if (hascheckcycle) {
			startcheckcycle = start;
			checkcycleLength = leng;

			hascheckcycle = false;
			checkcycle.append(chars, start, leng);
		}
	}

	public String makeDate(int year, int month, int day) {

		String strMonth = "";
		String strDay = "";

		if (month + 1 < 10) {
			strMonth = "0" + (month + 1);
		} else {
			strMonth = "" + (month + 1);
		}

		if (day < 10) {
			strDay = "0" + day;
		} else {
			strDay = "" + day;
		}

		return year + "/" + strMonth + "/" + strDay;
	}
}
