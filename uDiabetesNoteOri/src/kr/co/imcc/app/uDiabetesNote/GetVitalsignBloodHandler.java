package kr.co.imcc.app.uDiabetesNote;

import java.util.*;

import org.xml.sax.*;
import org.xml.sax.helpers.*;

public class GetVitalsignBloodHandler extends DefaultHandler {

	boolean hasdate = false;
	boolean hastime = false;
	boolean hasbpdiastolic = false;
	boolean hasbpsystolic = false;
	boolean haspulse = false;
	boolean hasbodytemperature = false;
	boolean hasdeptflag = false;

	StringBuilder date;
	StringBuilder time;
	StringBuilder bpdiastolic;
	StringBuilder bpsystolic;
	StringBuilder pulse;
	StringBuilder bodytemperature;	
	StringBuilder deptflag;

	int startdate;
	int dateLength;

	int starttime;
	int timeLength;

	int startbpdiastolic;
	int bpdiastolicLength;
	
	int startbpsystolic;
	int bpsystolicLength;
	
	int startpulse;
	int pulseLength;
	
	int startbodytemperature;
	int bodytemperatureLength;

	int startdeptflag;
	int deptflagLength;

	public GetVitalsignBloodHandler() {
		
		date = new StringBuilder();
		time = new StringBuilder();
		bpdiastolic = new StringBuilder();
		bpsystolic = new StringBuilder();
		pulse = new StringBuilder();
		bodytemperature = new StringBuilder();
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
		} else if (localName.equals("Pulse")) {
			haspulse = true;
		} else if (localName.equals("BodyTemperature")) {
			hasbodytemperature = true;
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
		} else if (localName.equals("Pulse")) {
			haspulse = false;
		} else if (localName.equals("BodyTemperature")) {
			hasbodytemperature = false;
		} else if (localName.equals("DeptFlag")) {
			hasdeptflag = false;
		}
		
		if (localName.equals("Vitalsign")) {
			HashMap<String, String> item = new HashMap<String, String>();

			item.put("date", date.toString());
			item.put("time", time.toString());
			item.put("bpdiastolic", bpdiastolic.toString());
			item.put("bpsystolic", bpsystolic.toString());
			item.put("pulse", pulse.toString());
			item.put("bodytemperature", bodytemperature.toString());
			item.put("deptflag", deptflag.toString());

			// test = item.get("PatientID");
			// Log.d("1", item.get("UserType"));
			// Log.d("2", item.get("InformationDisclosure"));
			// Log.d("3", item.get("PatientName"));
			// Log.d("4", item.get("ResidentID"));
			// Log.d("5", item.get("PatientID"));
			// Log.d("info", "result2: "+GetLoginInfoHandler.test);
//			tActivity1_4.ArrayListVitalsignBloodPressure.add(item);
			Activity3_CVD.ArrayListVitalsignBloodPressure.add(item);

			date.delete(startdate, dateLength);
			time.delete(starttime, timeLength);
			bpdiastolic.delete(startbpdiastolic, bpdiastolicLength);
			bpsystolic.delete(startbpsystolic, bpsystolicLength);
			pulse.delete(startpulse, pulseLength);
			bodytemperature.delete(startbodytemperature, bodytemperatureLength);
			deptflag.delete(startdeptflag, deptflagLength);

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
		} else if (hasbpdiastolic) {
			startbpdiastolic = start;
			bpdiastolicLength = leng;

			hasbpdiastolic = false;
			bpdiastolic.append(chars, start, leng);
		} else if (hasbpsystolic) {
			startbpsystolic = start;
			bpsystolicLength = leng;

			hasbpsystolic = false;
			bpsystolic.append(chars, start, leng);
		}  else if (haspulse) {
			startpulse = start;
			pulseLength = leng;

			haspulse = false;
			pulse.append(chars, start, leng);
		} else if (hasbodytemperature) {
			startbodytemperature = start;
			bodytemperatureLength = leng;

			hasbodytemperature = false;
			bodytemperature.append(chars, start, leng);
		} else if (hasdeptflag) {
			startdeptflag = start;
			deptflagLength = leng;

			hasdeptflag = false;
			deptflag.append(chars, start, leng);
		}
	}
}
