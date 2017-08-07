package kr.co.imcc.app.uDiabetesNote;

import java.util.*;

import org.xml.sax.*;
import org.xml.sax.helpers.*;

public class GetVitalsignWaistHandler extends DefaultHandler {

	boolean hasdate = false;
	boolean hastime = false;
	boolean haswaist = false;
	boolean haship = false;

	StringBuilder date;
	StringBuilder time;
	StringBuilder waist;
	StringBuilder hip;

	int startdate;
	int dateLength;

	int starttime;
	int timeLength;

	int startwaist;
	int waistLength;
	
	int starthip;
	int hipLength;


	public GetVitalsignWaistHandler() {
		
		date = new StringBuilder();
		time = new StringBuilder();
		waist = new StringBuilder();
		hip = new StringBuilder();
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes atts) {
		if (localName.equals("Date")) {
			hasdate = true;
		} else if (localName.equals("Time")) {
			hastime = true;
		} else if (localName.equals("Waist")) {
			haswaist = true;
		} else if (localName.equals("Hip")) {
			haship = true;
		} 
	}

	@Override
	public void endElement(String uri, String localName, String qName) {
		
		if (localName.equals("Date")) {
			hasdate = false;
		} else if (localName.equals("Time")) {
			hastime = false;
		} else if (localName.equals("Waist")) {
			haswaist = false;
		} else if (localName.equals("Hip")) {
			haship = false;
		} 
		
		if (localName.equals("Vitalsign")) {
			HashMap<String, String> item = new HashMap<String, String>();

			item.put("date", date.toString());
			item.put("time", time.toString());
			item.put("waist", waist.toString());
			item.put("hip", hip.toString());

			// test = item.get("PatientID");
			// Log.d("1", item.get("UserType"));
			// Log.d("2", item.get("InformationDisclosure"));
			// Log.d("3", item.get("PatientName"));
			// Log.d("4", item.get("ResidentID"));
			// Log.d("5", item.get("PatientID"));
			// Log.d("info", "result2: "+GetLoginInfoHandler.test);
			tActivity1_3.ArrayListVitalsignWaist.add(item);

			date.delete(startdate, dateLength);
			time.delete(starttime, timeLength);
			waist.delete(startwaist, waistLength);
			hip.delete(starthip, hipLength);

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
		} else if (haswaist) {
			startwaist = start;
			waistLength = leng;

			haswaist = false;
			waist.append(chars, start, leng);
		} else if (haship) {
			starthip = start;
			hipLength = leng;

			haship = false;
			hip.append(chars, start, leng);
		} 
	}
}
