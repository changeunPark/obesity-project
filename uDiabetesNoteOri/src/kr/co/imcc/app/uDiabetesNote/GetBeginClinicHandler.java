package kr.co.imcc.app.uDiabetesNote;

import java.util.*;

import org.xml.sax.*;
import org.xml.sax.helpers.*;

public class GetBeginClinicHandler extends DefaultHandler {

	boolean hasrecorddate = false;
	boolean hasbloodpressure = false;
	boolean hasheight = false;
	boolean hasweight = false;

	StringBuilder recorddate;
	StringBuilder bloodpressure;
	StringBuilder height;
	StringBuilder weight;

	int startrecorddate;
	int recorddateLength;

	int startbloodpressure;
	int bloodpressureLength;

	int startheight;
	int heightLength;
	
	int startweight;
	int weightLength;
	

	public GetBeginClinicHandler() {
		
		recorddate = new StringBuilder();
		bloodpressure = new StringBuilder();
		height = new StringBuilder();
		weight = new StringBuilder();
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes atts) {
		if (localName.equals("RecordDate")) {
			hasrecorddate = true;
		} else if (localName.equals("BloodPressure")) {
			hasbloodpressure = true;
		} else if (localName.equals("Height")) {
			hasheight = true;
		} else if (localName.equals("Weight")) {
			hasweight = true;
		} 
	}

	@Override
	public void endElement(String uri, String localName, String qName) {
		
		if (localName.equals("RecordDate")) {
			hasrecorddate = false;
		} else if (localName.equals("BloodPressure")) {
			hasbloodpressure = false;
		} else if (localName.equals("Height")) {
			hasheight = false;
		} else if (localName.equals("Weight")) {
			hasweight = false;
		} 
		
		if (localName.equals("BeginClinic")) {
			HashMap<String, String> item = new HashMap<String, String>();

			item.put("recorddate", recorddate.toString());
			item.put("bloodpressure", bloodpressure.toString());
			item.put("height", height.toString());
			item.put("weight", weight.toString());

			// test = item.get("PatientID");
			// Log.d("1", item.get("UserType"));
			// Log.d("2", item.get("InformationDisclosure"));
			// Log.d("3", item.get("PatientName"));
			// Log.d("4", item.get("ResidentID"));
			// Log.d("5", item.get("PatientID"));
			// Log.d("info", "result2: "+GetLoginInfoHandler.test);
			ActivityConfig_3.ArrayListBeginClinic.add(item);

			recorddate.delete(startrecorddate, recorddateLength);
			bloodpressure.delete(startbloodpressure, bloodpressureLength);
			height.delete(startheight, heightLength);
			weight.delete(startweight, weightLength);

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

		if (hasrecorddate) {
			startrecorddate = start;
			recorddateLength = leng;

			hasrecorddate = false;
			recorddate.append(chars, start, leng);
		} else if (hasbloodpressure) {
			startbloodpressure = start;
			bloodpressureLength = leng;

			hasbloodpressure = false;
			bloodpressure.append(chars, start, leng);
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
		} 
	}
}
