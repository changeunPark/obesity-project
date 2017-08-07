package kr.co.imcc.app.uDiabetesNote;


import java.util.*;

import org.xml.sax.*;
import org.xml.sax.helpers.*;


public class GetLoginInfoHandler extends DefaultHandler {
	
	
	boolean hasUserType = false;
	boolean hasInformationDisclosure = false;
//	boolean hasPatientInfo = false;
	boolean hasLoginID = false;
	
	boolean hasPatientName = false;//ȯ���̸�
	boolean hasPatientID = false;//ȯ�ڹ�ȣ
	boolean hasKeyCD = false;//Ű�ڵ�
	
	StringBuilder UserType;
	StringBuilder InformationDisclosure;
//	StringBuilder PatientInfo;
	StringBuilder PatientName;
	StringBuilder LoginID;
	StringBuilder PatientID;
	StringBuilder KeyCD;
	
	int startUserType;
	int UserTypeLength;

	int startInformationDisclosure;
	int InformationDisclosureLength;

//	int startPatientInfo;
//	int PatientInfoLength;
	
	int startPatientName;
	int PatientNameLength;
	
	int startLoginID;
	int LoginIDLength;
	
	int startPatientID;
	int PatientIDLength;
	
	int startKeyCD;
	int KeyCDLength;

	public GetLoginInfoHandler() {
		
		ActivityLogin.ArrayListLoginInfo.clear();
		
		UserType = new StringBuilder();
		InformationDisclosure = new StringBuilder();
//		PatientInfo = new StringBuilder();
		PatientName = new StringBuilder();
		LoginID = new StringBuilder();
		PatientID = new StringBuilder();
		KeyCD = new StringBuilder();
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes atts) {
		if (localName.equals("UserType")) {
			hasUserType = true;
		} else if (localName.equals("InformationDisclosure")) {
			hasInformationDisclosure = true;
		} 
//		else if (localName.equals("PatientInfo")) {
//			hasPatientInfo = true;
//		}
		else if (localName.equals("PatientName")) {
			hasPatientName = true;
		} else if (localName.equals("LoginID")) {
			hasLoginID = true;
		} else if (localName.equals("PatientID")) {
			hasPatientID = true;
		} else if (localName.equals("KeyCD")) {
			hasKeyCD = true;
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) {
		if (localName.equals("Response")) {
			HashMap<String, String> item = new HashMap<String, String>();

			item.put("UserType", UserType.toString());
			item.put("InformationDisclosure", InformationDisclosure.toString());
//			item.put("PatientInfo", PatientInfo.toString());
			item.put("PatientName", PatientName.toString());
			item.put("LoginID", LoginID.toString());
			item.put("PatientID", PatientID.toString());
			item.put("KeyCD", PatientID.toString());
			
//			test = item.get("PatientID");
//			Log.d("1", item.get("UserType"));
//			Log.d("2", item.get("InformationDisclosure"));
//			Log.d("3", item.get("PatientName"));
//			Log.d("4", item.get("LoginID"));
//			Log.d("5", item.get("PatientID"));
//			Log.d("info", "result2: "+GetLoginInfoHandler.test);
			ActivityLogin.item = item; //�������߰�

			UserType.delete(startUserType, UserTypeLength);
			InformationDisclosure.delete(startInformationDisclosure, InformationDisclosureLength);
//			PatientInfo.delete(startPatientInfo, PatientInfoLength);
			PatientName.delete(startPatientName, PatientNameLength);
			LoginID.delete(startLoginID, LoginIDLength);
			PatientID.delete(startPatientID, PatientIDLength);
			KeyCD.delete(startPatientID, PatientIDLength);
			
//			HashMap<String, String> item1 = (HashMap<String, String>) ActivityLogin.ArrayListLoginInfo.get(0);
//    		Log.d("info", "result6: "+item1.get("UserType").toString());
//    		Log.d("info", "result7: "+item1.get("InformationDisclosure").toString());
//    		Log.d("info", "result8: "+item1.get("PatientName").toString());
//    		Log.d("info", "result9: "+item1.get("LoginID").toString());
//    		Log.d("info", "result10: "+item1.get("PatientID").toString());
		}
	}

	@Override
	public void characters(char[] chars, int start, int leng) {

		if (hasUserType) {
			startUserType = start;
			UserTypeLength = leng;

			hasUserType = false;
			UserType.append(chars, start, leng);
		} else if (hasInformationDisclosure) {
			startInformationDisclosure = start;
			InformationDisclosureLength = leng;

			hasInformationDisclosure = false;
			InformationDisclosure.append(chars, start, leng);
		} 
//		else if (hasPatientInfo) {
//			startPatientInfo = start;
//			PatientInfoLength = leng;
//
//			hasPatientInfo = false;
//			PatientInfo.append(chars, start, leng);
//		} 
		else if (hasPatientName) {
			startPatientName = start;
			PatientNameLength = leng;

			hasPatientName = false;
			PatientName.append(chars, start, leng);
		} else if (hasLoginID) {
			startLoginID = start;
			LoginIDLength = leng;

			hasLoginID = false;
			LoginID.append(chars, start, leng);
		} else if (hasPatientID) {
			startPatientID = start;
			PatientIDLength = leng;

			hasPatientID = false;
			PatientID.append(chars, start, leng);
		}
		else if (hasKeyCD) {
			startKeyCD = start;
			KeyCDLength = leng;

			hasKeyCD = false;
			KeyCD.append(chars, start, leng);
		}
	}
}