package kr.co.imcc.app.uDiabetesNote;

import java.util.*;

import org.xml.sax.*;
import org.xml.sax.helpers.*;

public class GetPersonalInfoHandler extends DefaultHandler {

	boolean hasPatientName = false;
	boolean hasGender = false;
	boolean hasAge = false;

	StringBuilder PatientName;
	StringBuilder Gender;
	StringBuilder Age;

	int startPatientName;
	int PatientNameLength;

	int startGender;
	int GenderLength;

	int startAge;
	int AgeLength;

	public GetPersonalInfoHandler() {
		PatientName = new StringBuilder();
		Gender = new StringBuilder();
		Age = new StringBuilder();
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes atts) {
		if (localName.equals("PatientName")) {
			hasPatientName = true;
		} else if (localName.equals("Gender")) {
			hasGender = true;
		} else if (localName.equals("Age")) {
			hasAge = true;
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) {
		if (localName.equals("Response")) {
			HashMap<String, String> item = new HashMap<String, String>();

			item.put("PatientName", PatientName.toString());
			item.put("Gender", Gender.toString());
			item.put("Age", Age.toString());

			// test = item.get("PatientID");
			// Log.d("1", item.get("UserType"));
			// Log.d("2", item.get("InformationDisclosure"));
			// Log.d("3", item.get("PatientName"));
			// Log.d("4", item.get("ResidentID"));
			// Log.d("5", item.get("PatientID"));
			// Log.d("info", "result2: "+GetLoginInfoHandler.test);
			ActivityConfig_1.ArrayListPersonalInfo.add(item);

			PatientName.delete(startPatientName, PatientNameLength);
			Gender.delete(startGender, GenderLength);
			Age.delete(startAge, AgeLength);

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

		if (hasPatientName) {
			startPatientName = start;
			PatientNameLength = leng;

			hasPatientName = false;
			PatientName.append(chars, start, leng);
		} else if (hasGender) {
			startGender = start;
			GenderLength = leng;

			hasGender = false;
			Gender.append(chars, start, leng);
		} else if (hasAge) {
			startAge = start;
			AgeLength = leng;

			hasAge = false;
			Age.append(chars, start, leng);
		}
	}
}
