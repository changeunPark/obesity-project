package kr.co.imcc.app.uDiabetesNote;

import java.util.*;

import org.xml.sax.*;
import org.xml.sax.helpers.*;

public class GetVitalsignHeightHandler extends DefaultHandler {

	boolean hasdate = false;
	boolean hastime = false;
	boolean hasheight = false;
	boolean hasweight = false;
	boolean hasdeptflag = false;

	StringBuilder date;
	StringBuilder time;
	StringBuilder height;
	StringBuilder weight;
	StringBuilder deptflag;

	int startdate;
	int dateLength;

	int starttime;
	int timeLength;

	int startheight;
	int heightLength;
	
	int startweight;
	int weightLength;

	int startdeptflag;
	int deptflagLength;

	public GetVitalsignHeightHandler() {
		
		date = new StringBuilder();
		time = new StringBuilder();
		height = new StringBuilder();
		weight = new StringBuilder();
		deptflag = new StringBuilder();
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes atts) 
	{
		if (localName.equals("Date")) {
			hasdate = true;
		} else if (localName.equals("Time")) {
			hastime = true;
		} else if (localName.equals("Height")) {
			hasheight = true;
		} else if (localName.equals("Weight")) {
			hasweight = true;
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
		} else if (localName.equals("Height")) {
			hasheight = false;
		} else if (localName.equals("Weight")) {
			hasweight = false;
		} else if (localName.equals("DeptFlag")) {
			hasdeptflag = false;
		}
		
		if (localName.equals("Vitalsign")) {
			HashMap<String, String> item = new HashMap<String, String>();

			item.put("date", date.toString());
			item.put("time", time.toString());
			item.put("height", height.toString());
			item.put("weight", weight.toString());
			item.put("deptflag", deptflag.toString());

			// test = item.get("PatientID");
			// Log.d("1", item.get("UserType"));
			// Log.d("2", item.get("InformationDisclosure"));
			// Log.d("3", item.get("PatientName"));
			// Log.d("4", item.get("ResidentID"));
			// Log.d("5", item.get("PatientID"));
			// Log.d("info", "result2: "+GetLoginInfoHandler.test);
			tActivity1_1.ArrayListVitalsignHeight.add(item);
			tActivity3_1_input.ArrayListVitalsignHeight.add(item);

			date.delete(startdate, dateLength);
			time.delete(starttime, timeLength);
			height.delete(startheight, heightLength);
			weight.delete(startweight, weightLength);
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
	public void characters(char[] chars, int start, int leng) 
	{
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
		} else if (hasheight) {
			startheight = start;
			heightLength = leng;

			hasheight = false;
			height.append(chars, start, leng);
		} else if (hasweight) {
			startweight = start;
			weightLength = leng;

			hasweight = false;
			weight.append(chars, start, leng);
		} else if (hasdeptflag) {
			startdeptflag = start;
			deptflagLength = leng;

			hasdeptflag = false;
			deptflag.append(chars, start, leng);
		}
	}
}
