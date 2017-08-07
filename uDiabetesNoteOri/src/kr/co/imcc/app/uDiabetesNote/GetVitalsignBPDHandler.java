//���а���

package kr.co.imcc.app.uDiabetesNote;

import org.xml.sax.*;
import org.xml.sax.helpers.*;

import android.content.*;

public class GetVitalsignBPDHandler extends DefaultHandler {

	static long BlOODPRESSUREBPD_COUNT = 0;
	
	boolean hasdate = false;
	boolean hastime = false;
	boolean hasbpdiastolic = false;
	boolean hasbpsystolic = false;
	boolean hasdeptflag = false;

	StringBuilder date;
	StringBuilder time;
	StringBuilder bpdiastolic;
	StringBuilder bpsystolic;
	StringBuilder deptflag;

	int startdate;
	int dateLength;

	int starttime;
	int timeLength;

	int startbpdiastolic;
	int bpdiastolicLength;
	
	int startdeptflag;
	int deptflagLength;
	
	int startbpsystolic;
	int bpsystolicLength;
	

	public GetVitalsignBPDHandler() {
		BlOODPRESSUREBPD_COUNT = 0;
		date = new StringBuilder();
		time = new StringBuilder();
		bpdiastolic = new StringBuilder();
		bpsystolic = new StringBuilder();
		deptflag = new StringBuilder();
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes atts) {
		if (localName.equals("Date")) {
			hasdate = true;
		} else if (localName.equals("Time")) {
			hastime = true;
		} else if (localName.equals("BPDiastolic")) {
			hasbpdiastolic = true;
		} else if (localName.equals("BPSystolic")) {
			hasbpsystolic = true;
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
		} else if (localName.equals("BPDiastolic")) {
			hasbpdiastolic = false;
		} else if (localName.equals("BPSystolic")) {
			hasbpsystolic = false;
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
			cv.put("bpdiastolic", bpdiastolic.toString());
			cv.put("bpsystolic", bpsystolic.toString());
			cv.put("memo", "");
			
			if (deptflag.toString().equals("H"))
			{
				cv.put("hospital", "Y");
			} 
			else 
			{
				cv.put("hospital", "N");
			}
			//�̿ϱ�
			BlOODPRESSUREBPD_COUNT = ActivityBloodPressure.sqLiteDatabase.insert("BlOODPRESSUREBPD", "", cv);//����̽� ��� ����
			
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
			bpdiastolic.delete(startbpdiastolic, bpdiastolicLength);
			bpsystolic.delete(startbpsystolic, bpsystolicLength);
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
		} 
		else if (hastime) {
			starttime = start;
			timeLength = leng;

			hastime = false;
			time.append(chars, start, leng);
		} 
		else if (hasbpdiastolic) {
			startbpdiastolic = start;
			bpdiastolicLength = leng;

			hasbpdiastolic = false;
			bpdiastolic.append(chars, start, leng);
		}
		else if (hasbpsystolic) {
			startbpsystolic = start;
			bpsystolicLength = leng;

			hasbpsystolic = false;
			bpsystolic.append(chars, start, leng);
		} 
		else if (hasdeptflag) {
			startdeptflag = start;
			deptflagLength = leng;

			hasdeptflag = false;
			deptflag.append(chars, start, leng);
		}
	}
}
