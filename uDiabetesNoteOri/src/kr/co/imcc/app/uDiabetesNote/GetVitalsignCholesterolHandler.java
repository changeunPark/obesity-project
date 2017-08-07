package kr.co.imcc.app.uDiabetesNote;

import java.util.*;

import org.xml.sax.*;
import org.xml.sax.helpers.*;

public class GetVitalsignCholesterolHandler extends DefaultHandler {

	boolean hasdate = false;
	boolean hastime = false;
	boolean hastotalchol = false;
	boolean hashdl = false;
	boolean hasldl = false;

	StringBuilder date;
	StringBuilder time;
	StringBuilder totalchol;
	StringBuilder hdl;
	StringBuilder ldl;

	int startdate;
	int dateLength;

	int starttime;
	int timeLength;

	int starttotalchol;
	int totalcholLength;
	
	int starthdl;
	int hdlLength;
	
	int startldl;
	int ldlLength;


	public GetVitalsignCholesterolHandler() {
		
		date = new StringBuilder();
		time = new StringBuilder();
		totalchol = new StringBuilder();
		hdl = new StringBuilder();
		ldl = new StringBuilder();
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes atts) {
		if (localName.equals("Date")) {
			hasdate = true;
		} else if (localName.equals("Time")) {
			hastime = true;
		} else if (localName.equals("TotalCholesterol")) {
			hastotalchol = true;
		} else if (localName.equals("HDL")) {
			hashdl = true;
		} else if (localName.equals("LDL")) {
			hasldl = true;
		} 
	}

	@Override
	public void endElement(String uri, String localName, String qName) {
		
		if (localName.equals("Date")) {
			hasdate = false;
		} else if (localName.equals("Time")) {
			hastime = false;
		} else if (localName.equals("TotalCholesterol")) {
			hastotalchol = false;
		} else if (localName.equals("HDL")) {
			hashdl = false;
		} else if (localName.equals("LDL")) {
			hasldl = false;
		} 
		
		if (localName.equals("Vitalsign")) {
			HashMap<String, String> item = new HashMap<String, String>();

			item.put("date", date.toString());
			item.put("time", time.toString());
			item.put("totalchol", totalchol.toString());
			item.put("hdl", hdl.toString());
			item.put("ldl", ldl.toString());

			// test = item.get("PatientID");
			// Log.d("1", item.get("UserType"));
			// Log.d("2", item.get("InformationDisclosure"));
			// Log.d("3", item.get("PatientName"));
			// Log.d("4", item.get("ResidentID"));
			// Log.d("5", item.get("PatientID"));
			// Log.d("info", "result2: "+GetLoginInfoHandler.test);
//			tActivity1_4.ArrayListVitalsignCholesterol.add(item);
			Activity3_CVD.ArrayListVitalsignCholesterol.add(item);

			date.delete(startdate, dateLength);
			time.delete(starttime, timeLength);
			totalchol.delete(starttotalchol, totalcholLength);
			hdl.delete(starthdl, hdlLength);
			ldl.delete(startldl, ldlLength);

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
		} else if (hastotalchol) {
			starttotalchol = start;
			totalcholLength = leng;

			hastotalchol = false;
			totalchol.append(chars, start, leng);
		} else if (hashdl) {
			starthdl = start;
			hdlLength = leng;

			hashdl = false;
			hdl.append(chars, start, leng);
		} else if (hasldl) {
			startldl = start;
			ldlLength = leng;

			hasldl = false;
			ldl.append(chars, start, leng);
		} 
	}
}
