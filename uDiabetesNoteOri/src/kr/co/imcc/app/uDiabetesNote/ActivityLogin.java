package kr.co.imcc.app.uDiabetesNote;

import java.text.*;
import java.util.*;

import org.xml.sax.*;

import android.app.*;
import android.content.*;
import android.net.*;
import android.os.*;
import android.util.*;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.*;

public class ActivityLogin extends Activity {
	/** Called when the activity is first created. */

	static boolean LOGIN_FLAG = false;

	static EditText etLoginID;
	static EditText etLoginPWD;
	static String strLoginID="";
	static String strPatientID="";
	
	private SharedPreferences pref = null; 

	public static ArrayList<HashMap<String, String>> ArrayListLoginInfo = new ArrayList<HashMap<String, String>>();
	public static HashMap<String, String> item = new HashMap<String, String>();
	
	CheckBox chbID;
	CheckBox chbPWD;
	
	Handler handler = new Handler();
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.login);
		
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
		
		chbID = (CheckBox) findViewById(R.id.checkbox_id_save);
		chbPWD = (CheckBox) findViewById(R.id.checkbox_pwd_save);		

		Button button_login = (Button) findViewById(R.id.button_login);
		etLoginID = (EditText) findViewById(R.id.edittext_login_id);
		etLoginPWD = (EditText) findViewById(R.id.edittext_login_pwd);

//		etLoginID.setText("naya");
//		etLoginPWD.setText("test1234");
		
		pref = getSharedPreferences("kr.co.imcc.app.uDiabetesNote.activitylogin",Activity.MODE_PRIVATE); 

		etLoginID.setText(pref.getString("id", "")); 
		etLoginPWD.setText(pref.getString("pwd", "")); 
		
		if(!etLoginID.getText().toString().trim().equals("")){
			chbID.setChecked(true);
		}
		
		if(!etLoginPWD.getText().toString().trim().equals("")){
			chbPWD.setChecked(true);
		}
		

		button_login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				SharedPreferences.Editor editor = pref.edit(); 
				
				if(chbID.isChecked()) {
					editor.putString("id", etLoginID.getText().toString()); 
				}else{
					editor.putString("id", "");
				}
				
				if(chbPWD.isChecked()){
					editor.putString("pwd", etLoginPWD.getText().toString()); 
				}else{
					editor.putString("pwd", "");
				}
		        
		        editor.commit(); 
				
				if(etLoginID.getText().toString().equals("") || etLoginPWD.getText().toString().equals("")){
					Toast.makeText(getApplicationContext(), "�׸��� �Էµ��� �ʾҽ��ϴ�.", Toast.LENGTH_SHORT).show();
				}else{
					
					ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
					NetworkInfo mobile = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
					NetworkInfo wifi = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
							
					if (mobile.isConnected() || wifi.isConnected()){
						
						HospitalInterface hospitalInterface = new HospitalInterface();
						strLoginID = etLoginID.getText().toString();//���̵� ����
						
						
						StringBuffer strInputXMLBuffer = new StringBuffer();

						Calendar cal = Calendar.getInstance();
						cal.setTime(new Date(System.currentTimeMillis()));
						String tempDate1 = new SimpleDateFormat("MMdd").format(cal.getTime());
						String tempDate2 = new SimpleDateFormat("ddMM").format(cal.getTime());
					    String date = Integer.toString(((Integer.parseInt(tempDate1)) * (Integer.parseInt(tempDate2))));
						
								
						strInputXMLBuffer.append("<?xml version='1.0' encoding='UTF-8'?><Request>");
						strInputXMLBuffer.append("<Type>Login</Type>");
						// strInputXMLBuffer.append("<HospitalID>10</HospitalID>");
						// strInputXMLBuffer.append("<LoginID>gildong</LoginID>");
						// strInputXMLBuffer.append("<Password>test1234</Password>");
						strInputXMLBuffer.append("<LoginID>" + etLoginID.getText().toString() + "</LoginID>");
						strInputXMLBuffer.append("<ClientCD>" + "7" + "</ClientCD>");
						strInputXMLBuffer.append("<AccessCD>" + date  + "</AccessCD>");
						strInputXMLBuffer.append("<Password>" + etLoginPWD.getText().toString() + "</Password>");
						// strInputXMLBuffer.append("<DeviceRegID>A0D0C0E0F0A0B0</DeviceRegID>");
						strInputXMLBuffer.append("</Request>");
						
						//�������� ������Ʈ
						String result = hospitalInterface.getLoginDataWithThread(strInputXMLBuffer.toString());
						
						String strStatus = "-1";
						GetLoginInfoHandler handler = null;
						
						if(!result.equals("")) {			
							handler = new GetLoginInfoHandler();
							strStatus = result.substring(result.indexOf("statusCode")+12, result.indexOf("'>"));					
						}
											
						Log.d("login", strStatus);					
						
						if(strStatus.equals("100")){
							
							LOGIN_FLAG = true;
							
							Toast.makeText(getApplicationContext(), "�α��ο� �����Ͽ����ϴ�.", Toast.LENGTH_SHORT).show();
							
							//���Ϲ��� XML��Ʈ�� �Ľ�
							try {
								Xml.parse(result, handler);
							} catch (SAXException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
															
							item.put("LoginID", etLoginID.getText().toString());			
							strPatientID = item.get("PatientID").toString().trim();//ȯ�ڹ�ȣ����
																					
							startService(new Intent(ActivityLogin.this, NotificationService.class));
							
							Intent intent = getIntent(); // �� ��Ƽ��Ƽ�� �����ϰ� �� ����Ʈ�� ȣ��				
							setResult(RESULT_OK, intent); // �߰� ������ ���� �� �ٽ� ����Ʈ�� ��ȯ�մϴ�.
							finish(); // ��Ƽ��Ƽ ����						
						}
						else
						{							
							LOGIN_FLAG = false;						
							Toast.makeText(getApplicationContext(), "�α��ο� �����Ͽ����ϴ�.", Toast.LENGTH_SHORT).show();
							
							editor.putString("id", "");
							editor.putString("pwd", "");
							editor.commit(); 
							
							Intent intent = getIntent(); // �� ��Ƽ��Ƽ�� �����ϰ� �� ����Ʈ�� ȣ��				
							setResult(RESULT_CANCELED,intent); // �߰� ������ ���� �� �ٽ� ����Ʈ�� ��ȯ�մϴ�.
							finish(); // ��Ƽ��Ƽ ����
						}
					}
					else
					{
						Toast.makeText(getApplicationContext(), "��Ʈ��ũ ���ῡ �����Ͽ����ϴ�. ��Ʈ��ũ ���¸� Ȯ���� �ּ���.", Toast.LENGTH_SHORT).show();
						finish();
					}				
				}			
			}
		});
	}		
}


