package kr.co.imcc.app.uDiabetesNote;

import java.text.*;

import android.app.*;
import android.os.*;
import android.text.*;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.*;


public class Activity3_CVD_Result extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.layer3_cvd_result);
		
		int gender = 0;
		
		if(Activity3_CVD.radio.get("Gender").toString() == "F")
		{
			gender = 2;
		}
		else
		{
			gender = 1;
		}
				
		start(gender, 		 
				 Integer.parseInt(Activity3_CVD.radio.get("Age").toString()),
				 Double.parseDouble(Activity3_CVD.radio.get("TotalChol").toString()), 
				 Double.parseDouble(Activity3_CVD.radio.get("HDL").toString()),  
				 Double.parseDouble(Activity3_CVD.radio.get("SBP").toString()), 
				 Activity3_CVD.radio.get("Diabetes").toString(), 
				 Activity3_CVD.radio.get("smoke").toString());
		
		//�������赵	
		String strResultTotal = "";
		String strName = Activity3_CVD.radio.get("Name").toString();
		
		if(result_max == max_L)
		{
			strResultTotal = "����";
		}
		else if(result_max == max_M)
		{
			strResultTotal = "����";
		}
		else if(result_max == max_H)
		{
			strResultTotal = "����";
		}
		else if(result_max == max_VH)
		{
			strResultTotal = "�ſ����";
		}
		
		TextView result_total = (TextView) findViewById(R.id.result_total);
		result_total.setText(Html.fromHtml("���� PHR ���� �̿��� �߿��� <font color=#FFA500>"+strName+"</font>"
				+"���� 30�� �� �������� ��ȯ�� ���� Ȯ�� ������ <font color=#FFA500>"+strResultTotal+"</font>"
				+"�� �ش�˴ϴ�."));
		
		//����					
		String strResultSBP = "";
		if(sbp_max == max_L)
		{
			strResultSBP = "����";
		}
		else if(sbp_max == max_M)
		{
			strResultSBP = "����";
		}
		else if(sbp_max == max_H)
		{
			strResultSBP = "����";
		}
		else if(sbp_max == max_VH)
		{
			strResultSBP = "�޿����";
		}
			
		TextView result_SBP = (TextView) findViewById(R.id.result_SBPl);
		result_SBP.setText(Html.fromHtml("SBP ��ġ�� ���� ������ [120mmHg] �̸�," 
		+ " <font color=#FFA500>"+strName+"</font>"
		+"���� ����� ������ ��ġ��"
		+" <font color=#FFA500>"+Activity3_CVD.radio.get("SBP").toString()+"</font>"
		+"mmHg �Դϴ�. PHR ���� �̿��� �߿��� Ȯ�� ������"
		+" <font color=#FFA500>"+strResultSBP+"</font>"
		+"�� �ش� �˴ϴ�."));
		
		//�ݷ���Ʈ��		
		String strResultCol = "";
		if(total_max == max_L)
		{
			strResultCol = "����";
		}
		else if(total_max == max_M)
		{
			strResultCol = "����";
		}
		else if(total_max == max_H)
		{
			strResultCol = "����";
		}
		else if(total_max == max_VH)
		{
			strResultCol = "�ſ����";
		}	
		
		TextView result_col = (TextView) findViewById(R.id.result_col);
		result_col.setText(Html.fromHtml("�� �ݷ���Ʈ�� ��ġ�� ���� ������ [200mg/dl ����]�̸�," 
		+ " <font color=#FFA500>"+strName+"</font>"
		+"���� ����� ������ ��ġ��"
		+" <font color=#FFA500>"+Activity3_CVD.radio.get("TotalChol").toString()+"</font>"
		+"mmHg �Դϴ�. PHR ���� �̿��� �߿��� Ȯ�� ������"
		+" <font color=#FFA500>"+strResultCol+"</font>"
		+"�� �ش� �˴ϴ�."));
		
		//HDL
		String strResultHDL = "";
		if(hdl_max == max_L)
		{
			strResultHDL = "����";
		}
		else if(hdl_max == max_M)
		{
			strResultHDL = "����";
		}
		else if(hdl_max == max_H)
		{
			strResultHDL = "����";
		}
		else if(hdl_max == max_VH)
		{
			strResultHDL = "�ſ����";
		}
		
		TextView result_HDL = (TextView) findViewById(R.id.result_HDL);
		result_HDL.setText(Html.fromHtml("HDL ��ġ�� ���� ������ [45-60mg/dl ����]�̸�," 
		+ " <font color=#FFA500>"+strName+"</font>"
		+"���� ����� ������ ��ġ��"
		+" <font color=#FFA500>"+Activity3_CVD.radio.get("HDL").toString()+"</font>"
		+"mmHg �Դϴ�. PHR ���� �̿��� �߿��� Ȯ�� ������"
		+" <font color=#FFA500>"+strResultHDL+"</font>"
		+"�� �ش� �˴ϴ�."));
		
/*		//HDL
		TextView hdl_low = (TextView) findViewById(R.id.hdl_L);
		TextView hdl_mode = (TextView) findViewById(R.id.hdl_M);
		TextView hdl_hig = (TextView) findViewById(R.id.hdl_H);
		TextView hdl_very = (TextView) findViewById(R.id.hdl_VH);
		
		hdl_low.setText(hdl_L);
		hdl_mode.setText(hdl_M);
		hdl_hig.setText(hdl_H);
		hdl_very.setText(hdl_VH);
		
		if(hdl_max == max_L)
		{
			hdl_low.setTextColor(getResources().getColor(R.color.orange));
		}
		else if(hdl_max == max_M)
		{
			hdl_mode.setTextColor(getResources().getColor(R.color.orange));
		}
		else if(hdl_max == max_H)
		{
			hdl_hig.setTextColor(getResources().getColor(R.color.orange));
		}
		else if(hdl_max == max_VH)
		{
			hdl_very.setTextColor(getResources().getColor(R.color.orange));
		}
		
		
		//��
		TextView smo_low = (TextView) findViewById(R.id.smo_L);
		TextView smo_mode = (TextView) findViewById(R.id.smo_M);
		TextView smo_hig = (TextView) findViewById(R.id.smo_H);
		TextView smo_very = (TextView) findViewById(R.id.smo_VH);
		
		smo_low.setText(smo_L);
		smo_mode.setText(smo_M);
		smo_hig.setText(smo_H);
		smo_very.setText(smo_VH);
		
		if(smo_max == max_L)
		{
			smo_low.setTextColor(getResources().getColor(R.color.orange));
		}
		else if(smo_max == max_M)
		{
			smo_mode.setTextColor(getResources().getColor(R.color.orange));
		}
		else if(smo_max == max_H)
		{
			smo_hig.setTextColor(getResources().getColor(R.color.orange));
		}
		else if(smo_max == max_VH)
		{
			smo_very.setTextColor(getResources().getColor(R.color.orange));
		}
		
		
		//�索
		TextView dia_low = (TextView) findViewById(R.id.dia_L);
		TextView dia_mode = (TextView) findViewById(R.id.dia_M);
		TextView dia_hig = (TextView) findViewById(R.id.dia_H);
		TextView dia_very = (TextView) findViewById(R.id.dia_VH);
		
		dia_low.setText(dia_L);
		dia_mode.setText(dia_M);
		dia_hig.setText(dia_H);
		dia_very.setText(dia_VH);
		
		if(dia_max == max_L)
		{
			dia_low.setTextColor(getResources().getColor(R.color.orange));
		}
		else if(dia_max == max_M)
		{
			dia_mode.setTextColor(getResources().getColor(R.color.orange));
		}
		else if(dia_max == max_H)
		{
			dia_hig.setTextColor(getResources().getColor(R.color.orange));
		}
		else if(dia_max == max_VH)
		{
			dia_very.setTextColor(getResources().getColor(R.color.orange));
		}*/
		

	
		Button button_back = (Button) findViewById(R.id.button_layer3_back);

		button_back.setOnClickListener(new OnClickListener() { // back

			@Override
			public void onClick(View v) {
				
				onBackPressed();

			}
		});

	}
	

	// �� �׸� ���� int xxx_max; ���� �߰� 
	// 1=Low, 2=Moderate, 3=High, 4=VeryHigh �� �ǹ��մϴ�. 
	private static final int max_L = 1;
	private static final int max_M = 2;
	private static final int max_H = 3;
	private static final int max_VH = 4;
	
	static String age_L;
	static String age_M;
	static String age_H;
	static String age_VH;
	static int age_max;
	
	
	static String total_L;
	static String total_M;
	static String total_H;
	static String total_VH;
	static int total_max;
	
	static String hdl_L;
	static String hdl_M;
	static String hdl_H;
	static String hdl_VH;
	static int hdl_max;
	
	
	static String sbp_L;
	static String sbp_M;
	static String sbp_H;
	static String sbp_VH;
	static int sbp_max;
	
	static String smo_L;
	static String smo_M;
	static String smo_H;
	static String smo_VH;
	static int smo_max;
	
	static String dia_L;
	static String dia_M;
	static String dia_H;
	static String dia_VH;
	static int dia_max;

	
	// ���Ӱ� �߰��� ������� 1
	static String Low_1;
	static String Moderate_1;
	static String High_1;
	static String Very_High_1;
	static int result1_max;
	
	// ���Ӱ� �߰��� ������� 2
	static String Low_2;
	static String Moderate_2;
	static String High_2;
	static String Very_High_2;
	static int result2_max;
	
	// ���� �ƿ�ǲ
	static String Low;
	static String Moderate;
	static String High;
	static String Very_High;
	static int result_max;
	

public static void start(int iSex, int iAge, double iTotal, double iHDL, double iSBP, String iDiabetes, String iSmoker){
						
		int sex = iSex;
		int age = iAge;
		double total = iTotal;
		double hdl = iHDL;
		double sbp = iSBP;
		String dia = iDiabetes;
		String smo = iSmoker;
		
			
		Fuzzy.sex = 0;
		Fuzzy.age = 0;
		Fuzzy.total = 0;
		Fuzzy.hdl = 0;
		Fuzzy.sbp = 0;
		Fuzzy.dia = "";
		Fuzzy.smo = "";

		Fuzzy.Less_Mid_Age = 0;
		Fuzzy.Mid_Age = 0;
		Fuzzy.Very_Mid_Age = 0;
		Fuzzy.Less_Old = 0;
		Fuzzy.Old = 0;

		Fuzzy.total_Very_Low = 0;
		Fuzzy.total_Low = 0;
		Fuzzy.total_Mid = 0;
		Fuzzy.total_High = 0;
		Fuzzy.total_Very_High = 0;

		Fuzzy.sbp_Very_Low = 0;
		Fuzzy.sbp_Low = 0;
		Fuzzy.sbp_Mid = 0;
		Fuzzy.sbp_High = 0;
		Fuzzy.sbp_Very_High = 0;


		Fuzzy.hdl_Low = 0;
		Fuzzy.hdl_Mid = 0;
		Fuzzy.hdl_High = 0;
		
		for(int i =0; i<=3;i++){
			
			Rule1.sex[i] =0;
			Rule1.age[i] =0;
			Rule1.total[i] =0;
			Rule1.sbp[i] =0;
			Rule1.hdl[i] =0;
			Rule1.dia[i] =0;
			Rule1.smo[i] =0;

			Rule2.sex0[i] =0;
			Rule2.age0[i] =0;
			Rule2.total0[i] =0;
			Rule2.sbp0[i] =0;
			Rule2.hdl0[i] =0;
			Rule2.dia0[i] =0;
			Rule2.smo0[i] =0;				
		}
		
		DS.DSalgorithm(sex, age, total, hdl, sbp, dia, smo);
		
		
		//Low = 		(DS.m3sex_Low + DS.m3age_Low +		 DS.m3total_Low+ 		DS.m3HDL_Low + 		DS.m3SBP_Low + DS.m3Dia_Low + DS.m3Smo_Low)/7;
		//Moderate = 	(DS.m3sex_Moderate + DS.m3age_Moderate+	 DS.m3total_Moderate +	DS.m3SBP_Moderate +	DS.m3HDL_Moderate +DS.m3Dia_Moderate+DS.m3Smo_Moderate)/7;
		//High =		(DS.m3sex_High + DS.m3age_High +	 DS.m3total_High +		DS.m3SBP_High+		DS.m3HDL_High+DS.m3Dia_High +DS.m3Smo_High )/7; 
		
		
		//Low = 		DS.m3age_Low DS.m3total_Low ;//+ 		DS.m3HDL_Low + 		DS.m3SBP_Low)/4;// + DS.m3Dia_Low + DS.m3Smo_Low)/5;
		//Moderate = DS.m3age_Moderate	DS.m3total_Moderate;// +	DS.m3SBP_Moderate +	DS.m3HDL_Moderate)/4;// +DS.m3Dia_Moderate+DS.m3Smo_Moderate)/5;//
		//High =	 DS.m3age_High	DS.m3total_High;// +		DS.m3SBP_High+		DS.m3HDL_High)/4;//+DS.m3Dia_High +DS.m3Smo_High )/5;
				
		
		//���� �ƿ�ǲ			
		//����Դϴ�. 
		
	
		// �� ������ ���� ���� ���� ��� �� �� ���� ���
		DecimalFormat format = new DecimalFormat();
		format.applyLocalizedPattern("0.#");
		
		Low_1 = format.format(Rule1.Low*100);
		Moderate_1 = format.format(Rule1.Moderate*100);
		High_1 = format.format(Rule1.High*100);
		Very_High_1 =format.format(Rule1.Very_High*100);
		
		
		Low_2 = format.format(Rule2.Low*100);
		Moderate_2 = format.format(Rule2.Moderate*100);
		High_2 = format.format(Rule2.High*100);
		Very_High_2 =format.format(Rule2.Very_High*100);
		
		
		Low = format.format(DS.m3_Low*100);
		Moderate = format.format(DS.m3_Moderate*100);
		High = format.format(DS.m3_High*100);
		Very_High =format.format(DS.m3_Very_High*100);
		
		age_L = format.format(Rule1.age[0]*100);
		age_M = format.format(Rule1.age[1]*100);
		age_H = format.format(Rule1.age[2]*100);
		age_VH = format.format(Rule1.age[3]*100);
		age_max = Maxcal(Rule1.age[0],Rule1.age[1],Rule1.age[2],Rule1.age[3]);
		
		total_L = format.format(Rule1.total[0]*100);
		total_M = format.format(Rule1.total[1]*100);
		total_H = format.format(Rule1.total[2]*100);
		total_VH = format.format(Rule1.total[3]*100);
		total_max = Maxcal(Rule1.total[0],Rule1.total[1],Rule1.total[2],Rule1.total[3]);
		
		
		hdl_L = format.format(Rule1.hdl[0]*100);
		hdl_M = format.format(Rule1.hdl[1]*100);
		hdl_H = format.format(Rule1.hdl[2]*100);
		hdl_VH = format.format(Rule1.hdl[3]*100);		
		hdl_max = Maxcal(Rule1.hdl[0],Rule1.hdl[1],Rule1.hdl[2],Rule1.hdl[3]);

		
		sbp_L = format.format(Rule1.sbp[0]*100);
		sbp_M = format.format(Rule1.sbp[1]*100);
		sbp_H = format.format(Rule1.sbp[2]*100);
		sbp_VH = format.format(Rule1.sbp[3]*100);
		sbp_max = Maxcal(Rule1.sbp[0],Rule1.sbp[1],Rule1.sbp[2],Rule1.sbp[3]);
		
		
		smo_L = format.format(Rule1.smo[0]*100);
		smo_M = format.format(Rule1.smo[1]*100);
		smo_H = format.format(Rule1.smo[2]*100);
		smo_VH = format.format(Rule1.smo[3]*100);
		smo_max = Maxcal(Rule1.smo[0],Rule1.smo[1],Rule1.smo[2],Rule1.smo[3]);

		
		dia_L = format.format(Rule1.dia[0]*100);
		dia_M = format.format(Rule1.dia[1]*100);
		dia_H = format.format(Rule1.dia[2]*100);
		dia_VH = format.format(Rule1.dia[3]*100);
		dia_max = Maxcal(Rule1.dia[0],Rule1.dia[1],Rule1.dia[2],Rule1.dia[3]);
				
		
		result1_max = Maxcal(Rule1.Low, Rule1.Moderate, Rule1.High, Rule1.Very_High);
		result2_max = Maxcal(Rule2.Low, Rule2.Moderate, Rule2.High, Rule2.Very_High);
		result_max= Maxcal(DS.m3_Low, DS.m3_Moderate, DS.m3_High, DS.m3_Very_High); //���� �ƿ�ǲ ���� ���� ���� ���
		
			
		System.out.println(age_max+" Age, L:"+age_L+", M:"+age_M+", H:"+age_H+", VH:"+age_VH);
		System.out.println(total_max+" Total-Co, L:"+total_L+", M:"+total_M+", H:"+total_H+", VH:"+total_VH);
		System.out.println(hdl_max+" HDL, L:"+hdl_L+", M:"+hdl_M+", H:"+hdl_H+", VH:"+hdl_VH);
		System.out.println(sbp_max+" sbp, L:"+sbp_L+", M:"+sbp_M+", H:"+sbp_H+", VH:"+sbp_VH);
		System.out.println(smo_max+" Smoke, L:"+smo_L+", M:"+smo_M+", H:"+smo_H+", VH:"+smo_VH);
		System.out.println(dia_max+" Diabetes, L:"+dia_L+", M:"+dia_M+", H:"+dia_H+", VH:"+dia_VH);
		
		System.out.println(result_max+"\t"+ Low + " "+Moderate+" " + High+" "+Very_High);
	}
	
	public static int Maxcal(double L, double M, double H, double VH){
		
		double temp_Low = L;
		double temp_Moderate = M;
		double temp_High =H;
		double temp_Very_High =VH;
		
		int max = 0;

		if(temp_Low > temp_Moderate){
			if(temp_Low > temp_High){
				if(temp_Low>temp_Very_High){
					max = 1;
				}
				else{
					max = 4;
				}
			}
			else{
				if(temp_High>temp_Very_High){
					max = 3;
					
				}
				else{
					max = 4;
				}
			}
		}
		else{
			if(temp_Moderate>temp_High){
				if(temp_Moderate>temp_Very_High){
					max =2;
				}
				else{
					max =4;
				}
				
			}
			else{
				if(temp_High>temp_Very_High){
					max = 3;		
				}
				else{
					max = 4;
				}
			}
		}		
		return max;
	}
		
}