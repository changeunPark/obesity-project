package kr.co.imcc.app.uDiabetesNote;

import java.io.*;

import org.apache.http.*;
import org.apache.http.client.*;
import org.apache.http.client.methods.*;
import org.apache.http.entity.*;
import org.apache.http.impl.client.*;

import android.util.Log;

public class HospitalInterface {

	// public HospitalInterface(String param1, String param2, String param3,
	// String param4, String param5, String type) {
	//
	// // type - 1:login, 2:�����Ƿ�����, 3:Ȱ��¡��
	//
	// if (type.equals("1")) {
	// loginCheck(param1, param2, param3, param4, param5);
	// } else if (type.equals("2")) {
	//
	// personalInform(param1, param2, param3, param4);
	// } else { // 3
	// vitalInform(param1, param2, param3, param4, param5);
	// }
	//
	// }
	//
	// public void loginCheck(String type, String hospitalID, String loginID,
	// String password, String deviceRegID) {
	//
	// getLoginDataWithThread(type, hospitalID, loginID, password, deviceRegID);
	//
	// }
	//
	// public void personalInform(String type, String hospitalID, String
	// loginID,
	// String patientID) {
	// getPersonalInfoWithThread(type, hospitalID, loginID, patientID);
	// }
	//
	// public void vitalInform(String Type, String LoginID, String PatientID,
	// String StartDate, String EndDate) {
	//
	// }

	// Handler handler = new Handler();
	String test = null;
	HttpClient httpclient;

	public String getLoginDataWithThread(String strXml) 
	{
		// new Thread() {
		//
		// public void run() {

		//String strURL = "http://211.253.219.149/PHR/CCR";
		String strURL = "https://ucare.gilhospital.com/gateway.aspx";
		BufferedReader reader = null;


		try {
			HttpPost httppost = new HttpPost(strURL);
			
			InputStream inputStream = new ByteArrayInputStream(
					strXml.getBytes("UTF-8"));
			InputStreamEntity entity = new InputStreamEntity(inputStream,
					strXml.length());
			httppost.setEntity(entity);
			httppost.setHeader("Content-type", "text/xml; charset=ISO-8859-1");		
			
			this.httpclient = new DefaultHttpClient();
			this.httpclient = WebClientDevWrapper.wrapClient(httpclient);
										
			HttpResponse response = httpclient.execute(httppost);

			HttpEntity httpEntity = response.getEntity();

			if (httpEntity != null) 
			{
				reader = new BufferedReader(new InputStreamReader(
						httpEntity.getContent()));
				//int read;
				
				StringBuffer buf = new StringBuffer();
						
				String temp = null;
				while ((temp = reader.readLine()) != null) 
				{
					Log.d("test", "MyResult: " + temp);
					buf.append(temp);	
				}
				
				test = buf.toString().trim();
								
				//builder.append(buff, 0, buff.length-1);			
			}

			// myResult = builder.toString();

			// GetLoginInfoHandler handler = new GetLoginInfoHandler();
			// Xml.parse(builder.toString(), handler);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			// HashMap<String, String> item = (HashMap<String, String>)
			// ActivityLogin.ArrayListLoginInfo
			// .get(0);
			// new HospitalInterface("BasicPersonalInformation", "10",
			// ActivityLogin.etLoginID.getText().toString(), item
			// .get("PatientID").toString(), "", "2");
			// Log.d("info",
			// "result11: "+item.get("PatientName").toString());
			// HashMap<String, String> item = (HashMap<String, String>)
			// ActivityLogin.ArrayListLoginInfo.get(0);
			// Log.d("info",
			// "result1: "+item.get("PatientName").toString());
		}

		// handler.post(new Runnable() {
		//
		// @Override
		// public void run() {
		// try {
		//
		//
		//
		//
		//
		// // textView.setText(builder.toString());
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		// }
		// });
		// }
		// }.start();
		
		if(test.equals(null)){
			return "";	
		}else{
			return test;
		}		
	}

	
	
	
	
	// Handler handler1 = new Handler();
	StringBuilder builder1;

	public String getPersonalInfoWithThread(final String type,
			final String hospitalID, final String loginID,
			final String patientID) {
		// new Thread() {
		//
		// public void run() {

//		String strURL = "http://211.253.219.112:8800/CCR2/CCR.aspx";
		String strURL = "http://211.253.219.159:8800/CCR/CCR.aspx";
		BufferedReader reader = null;
		builder1 = new StringBuilder();

		StringBuffer strInputXMLBuffer = new StringBuffer();

		Log.d("info111", patientID);

		strInputXMLBuffer
				.append("<?xml version='1.0' encoding='UTF-8'?><Request>");
		strInputXMLBuffer.append("<Type>" + type + "</Type>");
		strInputXMLBuffer.append("<HospitalID>" + hospitalID + "</HospitalID>");
		strInputXMLBuffer.append("<LoginID>" + loginID + "</LoginID>");//gildonghong
		strInputXMLBuffer.append("<PatientID>" + patientID + "</PatientID>");
		strInputXMLBuffer.append("</Request>");
		
		HttpPost httppost = new HttpPost(strURL);

		try {
			InputStream inputStream = new ByteArrayInputStream(
					strInputXMLBuffer.toString().getBytes("UTF-8"));
			InputStreamEntity entity = new InputStreamEntity(inputStream,
					strInputXMLBuffer.length());
			httppost.setEntity(entity);
			httppost.setHeader("Content-type", "text/xml; charset=ISO-8859-1");

			HttpClient httpclient = new DefaultHttpClient();

			HttpResponse response = httpclient.execute(httppost);

			HttpEntity httpEntity = response.getEntity();

			if (httpEntity != null) {
				reader = new BufferedReader(new InputStreamReader(
						httpEntity.getContent()));
				int read;
				char[] buff = new char[1024];

				while ((read = reader.read(buff)) != -1) {
					builder1.append(buff, 0, read);
				}
			}

			// myResult = builder.toString();
			Log.d("PHRTestClient", "myResult1" + builder1.toString());

			// GetPersonalInfoHandler handler = new GetPersonalInfoHandler();
			// Xml.parse(builder1.toString(), handler);

			// ActivityConfig_1.setPersonalInfo();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		// handler1.post(new Runnable() {
		//
		// @Override
		// public void run() {
		// try {
		//
		//
		// // textView.setText(builder.toString());
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		// }
		// });

		// }
		// }.start();
		
		if(builder1.toString().equals(null)){
			return "";	
		}else{
			return builder1.toString();
		}

		//return "";
	}

	StringBuilder builder2;

	public String getVitalSignWithThread(final String type,
			final String loginID, final String patientID,
			final String startDate, final String endDate) {
		// new Thread() {
		//
		// public void run() {

//		String strURL = "http://211.253.219.112:8800/CCR2/CCR.aspx";
		String strURL = "http://211.253.219.159:8800/CCR/CCR.aspx";
		BufferedReader reader = null;
		builder2 = new StringBuilder();

		StringBuffer strInputXMLBuffer = new StringBuffer();

		// Log.d("info111", patientID);

		strInputXMLBuffer.append("<?xml version='1.0' encoding='UTF-8'?><Request>");
		strInputXMLBuffer.append("<Type>" + type + "</Type>");
		strInputXMLBuffer.append("<LoginID>" + loginID + "</LoginID>");
		strInputXMLBuffer.append("<PatientID>" + patientID + "</PatientID>"); //11595383, 5109079, 25305963 , 28954083, 11595383, 26832933
		strInputXMLBuffer.append("<StartDate>" + startDate + "</StartDate>");
		strInputXMLBuffer.append("<EndDate>" + endDate + "</EndDate>");
		strInputXMLBuffer.append("</Request>");
		
		Log.d("request:", strInputXMLBuffer.toString());

		HttpPost httppost = new HttpPost(strURL);

		try {
			InputStream inputStream = new ByteArrayInputStream(
					strInputXMLBuffer.toString().getBytes("UTF-8"));
			InputStreamEntity entity = new InputStreamEntity(inputStream,
					strInputXMLBuffer.length());
			httppost.setEntity(entity);
			httppost.setHeader("Content-type", "text/xml; charset=ISO-8859-1");

			HttpClient httpclient = new DefaultHttpClient();

			HttpResponse response = httpclient.execute(httppost);

			HttpEntity httpEntity = response.getEntity();

			if (httpEntity != null) {
				reader = new BufferedReader(new InputStreamReader(
						httpEntity.getContent()));
				int read;
				char[] buff = new char[1024];

				while ((read = reader.read(buff)) != -1) {
					builder2.append(buff, 0, read);
				}
			}

			// myResult = builder.toString();
			Log.d("PHRTestClient", "myResult2" + builder2.toString());

			// GetPersonalInfoHandler handler = new GetPersonalInfoHandler();
			// Xml.parse(builder1.toString(), handler);

			// ActivityConfig_1.setPersonalInfo();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		// handler1.post(new Runnable() {
		//
		// @Override
		// public void run() {
		// try {
		//
		//
		// // textView.setText(builder.toString());
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		// }
		// });

		// }
		// }.start();
		if(builder2.toString().equals(null)){
			return "";	
		}else{
			return builder2.toString();
		}
		
//		return "";

	}
	
	
	StringBuilder builder3;

	public String getDiabetesAlertWithThread(final String type, final String patientID) {
		// new Thread() {
		//
		// public void run() {

//		String strURL = "http://211.253.219.112:8800/CCR2/CCR.aspx";
		String strURL = "http://211.253.219.159:8800/CCR/CCR.aspx";
		BufferedReader reader = null;
		builder3 = new StringBuilder();

		StringBuffer strInputXMLBuffer = new StringBuffer();

		// Log.d("info111", patientID);

		strInputXMLBuffer.append("<?xml version='1.0' encoding='UTF-8'?><Request>");
		strInputXMLBuffer.append("<Type>" + type + "</Type>");
		strInputXMLBuffer.append("<PatientID>" + patientID + "</PatientID>"); // 13705303
		strInputXMLBuffer.append("</Request>");

		HttpPost httppost = new HttpPost(strURL);

		try {
			InputStream inputStream = new ByteArrayInputStream(
					strInputXMLBuffer.toString().getBytes("UTF-8"));
			InputStreamEntity entity = new InputStreamEntity(inputStream,
					strInputXMLBuffer.length());
			httppost.setEntity(entity);
			httppost.setHeader("Content-type", "text/xml; charset=ISO-8859-1");

			HttpClient httpclient = new DefaultHttpClient();

			HttpResponse response = httpclient.execute(httppost);

			HttpEntity httpEntity = response.getEntity();

			if (httpEntity != null) {
				reader = new BufferedReader(new InputStreamReader(
						httpEntity.getContent()));
				int read;
				char[] buff = new char[1024];

				while ((read = reader.read(buff)) != -1) {
					builder3.append(buff, 0, read);
				}
			}

			// myResult = builder.toString();
			Log.d("PHRTestClient", "myResult2" + builder3.toString());

			// GetPersonalInfoHandler handler = new GetPersonalInfoHandler();
			// Xml.parse(builder1.toString(), handler);

			// ActivityConfig_1.setPersonalInfo();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		if(builder3.toString().equals(null)){
			return "";	
		}else{
			return builder3.toString();
		}
//		return "";
	}
	
	
	StringBuilder builder4;

	public String getBeginClinicWithThread(final String type,
			final String loginID, final String patientID,
			final String startDate, final String deptCD) {
		// new Thread() {
		//
		// public void run() {

//		String strURL = "http://211.253.219.112:8800/CCR2/CCR.aspx";
		String strURL = "http://211.253.219.159:8800/CCR/CCR.aspx";
		BufferedReader reader = null;
		builder4 = new StringBuilder();

		StringBuffer strInputXMLBuffer = new StringBuffer();

		// Log.d("info111", patientID);

		strInputXMLBuffer.append("<?xml version='1.0' encoding='UTF-8'?><Request>");
		strInputXMLBuffer.append("<Type>" + type + "</Type>");
		strInputXMLBuffer.append("<LoginID>" + loginID + "</LoginID>");
		strInputXMLBuffer.append("<PatientID>" + patientID + "</PatientID>"); //5109079, 25305963, 25204913
		strInputXMLBuffer.append("<Date>" + startDate + "</Date>");
		strInputXMLBuffer.append("<DeptCD>" + deptCD + "</DeptCD>");
		strInputXMLBuffer.append("</Request>");

		HttpPost httppost = new HttpPost(strURL);

		try {
			InputStream inputStream = new ByteArrayInputStream(
					strInputXMLBuffer.toString().getBytes("UTF-8"));
			InputStreamEntity entity = new InputStreamEntity(inputStream,
					strInputXMLBuffer.length());
			httppost.setEntity(entity);
			httppost.setHeader("Content-type", "text/xml; charset=ISO-8859-1");

			HttpClient httpclient = new DefaultHttpClient();

			HttpResponse response = httpclient.execute(httppost);

			HttpEntity httpEntity = response.getEntity();

			if (httpEntity != null) {
				reader = new BufferedReader(new InputStreamReader(
						httpEntity.getContent()));
				int read;
				char[] buff = new char[1024];

				while ((read = reader.read(buff)) != -1) {
					builder4.append(buff, 0, read);
				}
			}

			// myResult = builder.toString();
			Log.d("PHRTestClient", "builder4" + builder4.toString());

			// GetPersonalInfoHandler handler = new GetPersonalInfoHandler();
			// Xml.parse(builder1.toString(), handler);

			// ActivityConfig_1.setPersonalInfo();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		if(builder4.toString().equals(null)){
			return "";	
		}else{
			return builder4.toString();
		}
		
		//return "";

	}
}
