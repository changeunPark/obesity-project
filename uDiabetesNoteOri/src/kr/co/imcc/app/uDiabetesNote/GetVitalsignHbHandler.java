package kr.co.imcc.app.uDiabetesNote;

import org.xml.sax.*;
import org.xml.sax.helpers.*;

import android.content.*;

public class GetVitalsignHbHandler extends DefaultHandler {

	static long HB_COUNT = 0;
	
	boolean hasdate = false;
	boolean hastime = false;
	boolean haspercent = false;
	boolean hasdeptflag = false;

	StringBuilder date;
	StringBuilder time;
	StringBuilder percent;
	StringBuilder deptflag;

	int startdate;
	int dateLength;

	int starttime;
	int timeLength;

	int startpercent;
	int percentLength;
	
	int startdeptflag;
	int deptflagLength;
	

	public GetVitalsignHbHandler() {
		HB_COUNT = 0;
		date = new StringBuilder();
		time = new StringBuilder();
		percent = new StringBuilder();
		deptflag = new StringBuilder();
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes atts) {
		if (localName.equals("Date")) {
			hasdate = true;
		} else if (localName.equals("Time")) {
			hastime = true;
		} else if (localName.equals("Percent")) {
			haspercent = true;
		} else if (localName.equals("DeptFlag")) {
			hasdeptflag = true;
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) {
		
		if (localName.equals("Date")) {
			hasdate = false;
		} else if (localName.equals("Time")) {
			hastime = false;
		} else if (localName.equals("Percent")) {
			haspercent = false;
		} else if (localName.equals("DeptFlag")) {
			hasdeptflag = false;
		}
		
		if (localName.equals("HBA1C")) {
			
			Log.d("1", date.toString());
			Log.d("2", time.toString());
			Log.d("3", percent.toString());
			
			
			ContentValues cv = new ContentValues();
			cv.put("date", date.toString().replace("-",	"/"));
			cv.put("time", time.toString());
			cv.put("hb", percent.toString());
			cv.put("memo", "");

			if (deptflag.toString().equals("H"))
			{
				cv.put("hospital", "Y");
			} 
			else 
			{
				cv.put("hospital", "N");
			}
			
			HB_COUNT = Activity1_hb.sqLiteDatabase.insert("HBA1C", "", cv);
			
//			Log.d("result: ", tActivity1_4.sqLiteDatabase.insert("BLOODSUGAR", "", cv) +"");
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
			percent.delete(startpercent, percentLength);
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
		} else if (haspercent) {
			startpercent = start;
			percentLength = leng;

			haspercent = false;
			percent.append(chars, start, leng);
		} else if (hasdeptflag) {
			startdeptflag = start;
			deptflagLength = leng;

			hasdeptflag = false;
			deptflag.append(chars, start, leng);
		}
	}
}
