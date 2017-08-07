package kr.co.imcc.app.uDiabetesNote;

import org.xml.sax.*;
import org.xml.sax.helpers.*;

import android.content.*;

public class GetVitalsignBloodSugarHandler extends DefaultHandler {

	static long BLOODSUGAR_COUNT = 0;
	
	boolean hasdate = false;
	boolean hastime = false;
	boolean hasbloodsugar = false;
	boolean hasdeptflag = false;

	StringBuilder date;
	StringBuilder time;
	StringBuilder bloodsugar;
	StringBuilder deptflag;

	int startdate;
	int dateLength;

	int starttime;
	int timeLength;

	int startbloodsugar;
	int bloodsugarLength;
	
	int startdeptflag;
	int deptflagLength;
	

	public GetVitalsignBloodSugarHandler() 
	{
		BLOODSUGAR_COUNT = 0;
		date = new StringBuilder();
		time = new StringBuilder();
		bloodsugar = new StringBuilder();
		deptflag = new StringBuilder();
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes atts) 
	{
		if (localName.equals("Date")) 
		{
			hasdate = true;
		} 
		else if (localName.equals("Time")) 
		{
			hastime = true;
		} 
		else if (localName.equals("BloodSugar")) 
		{
			hasbloodsugar = true;
		} 
		else if (localName.equals("DeptFlag")) 
		{
			hasdeptflag = true;
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) {
		
		if (localName.equals("Date")) {
			hasdate = false;
		} else if (localName.equals("Time")) {
			hastime = false;
		} else if (localName.equals("BloodSugar")) {
			hasbloodsugar = false;
		} else if (localName.equals("DeptFlag")) {
			hasdeptflag = false;
		}
		
		if (localName.equals("Vitalsign")) {
			
			ContentValues cv = new ContentValues();
			cv.put("date", date.toString().replace("-", "/"));
			
			if(time.toString().length() < 5)
			{
				cv.put("time", time.toString() +"0");
			}
			else
			{
				cv.put("time", time.toString());
			}
			cv.put("bloodsugar", bloodsugar.toString());
			cv.put("memo", "");
			
			if (deptflag.toString().equals("H"))
			{
				cv.put("hospital", "Y");
			} 
			else 
			{
				cv.put("hospital", "N");
			}
								
			BLOODSUGAR_COUNT = tActivity3_3.sqLiteDatabase.insert("BLOODSUGAR", "", cv);//����̽� ��� ����
			
//			Log.d("result: ", ActivityBloodPressure.sqLiteDatabase.insert("BLOODSUGAR", "", cv) +"");
//			HashMap<String, String> item = new HashMap<String, String>();
//
//			item.put("date", date.toString());
//			item.put("time", time.toString());
//			item.put("bpdiastolic", bpdiastolic.toString());
//			item.put("bpsystolic", bpsystolic.toString());
//			item.put("pulse", pulse.toString());
//			item.put("bodytemperature", bodytemperature.toString());
//			item.put("deptflag", deptflag.toString());
//
//			// test = item.get("PatientID");
//			// Log.d("1", item.get("UserType"));
//			// Log.d("2", item.get("InformationDisclosure"));
//			// Log.d("3", item.get("PatientName"));
//			// Log.d("4", item.get("ResidentID"));
//			// Log.d("5", item.get("PatientID"));
//			// Log.d("info", "result2: "+GetLoginInfoHandler.test);
//			Activity3_1.ArrayListVitalsignBloodPressure.add(item);

			date.delete(startdate, dateLength);
			time.delete(starttime, timeLength);
			bloodsugar.delete(startbloodsugar, bloodsugarLength);
			deptflag.delete(startdeptflag, deptflagLength);
		}
	}

	@Override
	public void characters(char[] chars, int start, int leng) {

		if (hasdate) {
			startdate = start;
			dateLength = leng;

			hasdate = false;
			date.append(chars, start, leng);
		} else if (hastime) {
			starttime = start;
			timeLength = leng;

			hastime = false;
			time.append(chars, start, leng);
		} else if (hasbloodsugar) {
			startbloodsugar = start;
			bloodsugarLength = leng;

			hasbloodsugar = false;
			bloodsugar.append(chars, start, leng);
		} else if (hasdeptflag) {
			startdeptflag = start;
			deptflagLength = leng;

			hasdeptflag = false;
			deptflag.append(chars, start, leng);
		}
	}
}
