package kr.co.imcc.app.uDiabetesNote;



public class DS {
	
	
	static double m3_Null = 0;
	static double m3_Low = 0;
	static double m3_Moderate = 0;
	static double m3_High = 0;
	static double m3_Very_High = 0;
	
	static double m3age_Null = 0;
	static double m3age_Low = 0;
	static double m3age_Moderate = 0;
	static double m3age_High = 0;
	static double m3age_Very_High = 0;
	
	static double m3total_Null = 0;
	static double m3total_Low = 0;
	static double m3total_Moderate = 0;
	static double m3total_High = 0;
	static double m3total_Very_High = 0;
	
	static double m3SBP_Null = 0;
	static double m3SBP_Low = 0;
	static double m3SBP_Moderate = 0;
	static double m3SBP_High = 0;
	static double m3SBP_Very_High = 0;
	
	static double m3HDL_Null = 0;
	static double m3HDL_Low = 0;
	static double m3HDL_Moderate = 0;
	static double m3HDL_High = 0;
	static double m3HDL_Very_High = 0;
	
	static double m3Dia_Null = 0;
	static double m3Dia_Low = 0;
	static double m3Dia_Moderate = 0;
	static double m3Dia_High = 0;
	static double m3Dia_Very_High = 0;
	

	
	
	public static void DSalgorithm(int i, int iAge, double iTotal, double iHDL, double iSBP, String iDiabetes, String iSmoker){
		
		int sex = i;
		int age = iAge;
		double total = iTotal;
		double hdl = iHDL;
		double sbp = iSBP;
		String dia = iDiabetes;
		String smo = iSmoker;
		
		Fuzzy.fuzzy(sex, age, total, hdl, sbp, dia, smo);
		
		Rule1.Rule(); //����
		
		Rule2.Rule(sex, age, total, hdl, sbp, dia, smo); //
		
		DScal();
		
	}
	
	public static void DScal(){

		double m3[] = new double[25];



		double m1_Null = 0;
		double m1_Low = 0;
		double m1_Moderate = 0;
		double m1_High = 0;
		double m1_Very_High = 0;

		double m2_Null = 0;
		double m2_Low = 0;
		double m2_Moderate = 0;
		double m2_High = 0;
		double m2_Very_High = 0;

		 m1_Null = 0;
		 m1_Low =Rule1.Low;
		 m1_Moderate =Rule1.Moderate;
		 m1_High =Rule1.High;
		 m1_Very_High =Rule1.Very_High;

		 m2_Null = 0;
		 m2_Low =Rule2.Low;
		 m2_Moderate = Rule2.Moderate;
		 m2_High =Rule2.High;
		 m2_Very_High =Rule2.Very_High;

	//	 System.out.println("Rule1 :\t"+m1_Low+"\t"+m1_Moderate+"\t"+m1_High+"\t"+m1_Very_High+"\t"+m1_Null);
		// System.out.println("Rule2 :\t"+m2_Low+"\t"+m2_Moderate+"\t"+m2_High+"\t"+m2_Very_High+"\t"+m2_Null);
		 
		 m1_Null = 1 - (m1_Low + m1_Moderate + m1_High + m1_Very_High);
		 m2_Null = 1 - (m2_Low + m2_Moderate + m2_High + m2_Very_High);
		 
		 
		 if(	m1_Low	!= 0 && m2_Low !=0){		m3[0]	 = 	m1_Low	 * m2_Low;		}	else{	m3[0]	 = 0;	}
		 if(	m1_Low	!= 0 && m2_Moderate !=0){		m3[1]	 = 	m1_Low	 * m2_Moderate;		}	else{	m3[1]	 = 0;	}
		 if(	m1_Low	!= 0 && m2_High !=0){		m3[2]	 = 	m1_Low	 * m2_High;		}	else{	m3[2]	 = 0;	}
		 if(	m1_Low	!= 0 && m2_Very_High !=0){		m3[3]	 = 	m1_Low	 * m2_Very_High;		}	else{	m3[3]	 = 0;	}
		 if(	m1_Low	!= 0 && m2_Null !=0){		m3[4]	 = 	m1_Low	 * m2_Null;		}	else{	m3[4]	 = 0;	}
		 if(	m1_Moderate	!= 0 && m2_Low !=0){		m3[5]	 = 	m1_Moderate	 * m2_Low;		}	else{	m3[5]	 = 0;	}
		 if(	m1_Moderate	!= 0 && m2_Moderate !=0){		m3[6]	 = 	m1_Moderate	 * m2_Moderate;		}	else{	m3[6]	 = 0;	}
		 if(	m1_Moderate	!= 0 && m2_High !=0){		m3[7]	 = 	m1_Moderate	 * m2_High;		}	else{	m3[7]	 = 0;	}
		 if(	m1_Moderate	!= 0 && m2_Very_High !=0){		m3[8]	 = 	m1_Moderate	 * m2_Very_High;		}	else{	m3[8]	 = 0;	}
		 if(	m1_Moderate	!= 0 && m2_Null !=0){		m3[9]	 = 	m1_Moderate	 * m2_Null;		}	else{	m3[9]	 = 0;	}
		 if(	m1_High	!= 0 && m2_Low !=0){		m3[10]	 = 	m1_High	 * m2_Low;		}	else{	m3[10]	 = 0;	}
		 if(	m1_High	!= 0 && m2_Moderate !=0){		m3[11]	 = 	m1_High	 * m2_Moderate;		}	else{	m3[11]	 = 0;	}
		 if(	m1_High	!= 0 && m2_High !=0){		m3[12]	 = 	m1_High	 * m2_High;		}	else{	m3[12]	 = 0;	}
		 if(	m1_High	!= 0 && m2_Very_High !=0){		m3[13]	 = 	m1_High	 * m2_Very_High;		}	else{	m3[13]	 = 0;	}
		 if(	m1_High	!= 0 && m2_Null !=0){		m3[14]	 = 	m1_High	 * m2_Null;		}	else{	m3[14]	 = 0;	}
		 if(	m1_Very_High	!= 0 && m2_Low !=0){		m3[15]	 = 	m1_Very_High	 * m2_Low;		}	else{	m3[15]	 = 0;	}
		 if(	m1_Very_High	!= 0 && m2_Moderate !=0){		m3[16]	 = 	m1_Very_High	 * m2_Moderate;		}	else{	m3[16]	 = 0;	}
		 if(	m1_Very_High	!= 0 && m2_High !=0){		m3[17]	 = 	m1_Very_High	 * m2_High;		}	else{	m3[17]	 = 0;	}
		 if(	m1_Very_High	!= 0 && m2_Very_High !=0){		m3[18]	 = 	m1_Very_High	 * m2_Very_High;		}	else{	m3[18]	 = 0;	}
		 if(	m1_Very_High	!= 0 && m2_Null !=0){		m3[19]	 = 	m1_Very_High	 * m2_Null;		}	else{	m3[19]	 = 0;	}
		 if(	m1_Null	!= 0 && m2_Low !=0){		m3[20]	 = 	m1_Null	 * m2_Low;		}	else{	m3[20]	 = 0;	}
		 if(	m1_Null	!= 0 && m2_Moderate !=0){		m3[21]	 = 	m1_Null	 * m2_Moderate;		}	else{	m3[21]	 = 0;	}
		 if(	m1_Null	!= 0 && m2_High !=0){		m3[22]	 = 	m1_Null	 * m2_High;		}	else{	m3[22]	 = 0;	}
		 if(	m1_Null	!= 0 && m2_Very_High !=0){		m3[23]	 = 	m1_Null	 * m2_Very_High;		}	else{	m3[23]	 = 0;	}
		 if(	m1_Null	!= 0 && m2_Null !=0){		m3[24]	 = 	m1_Null	 * m2_Null;		}	else{	m3[24]	 = 0;	}


		
		
		for(int i=0 ; i<=24; i++){
			//System.out.print(m3[i]+"\t");
		}
	//	System.out.println();
		
		m3_Null = m3[1] + m3[2]+ m3[3] + +m3[5] + +m3[7]+m3[8]+m3[10]+m3[11]+m3[13]+m3[15]+m3[16]+m3[17];

		
		if(m3_Null >=1){
			m3_Low =0;
			m3_Moderate =0;
			m3_High = 0;
			System.out.println("total : "+m3total_Low+" "+m3total_Moderate+" "+m3total_High);

		}
		
		else{
		m3_Low =(m3[0]+m3[4]+m3[20]) * (1 / (1 - m3_Null));
		m3_Moderate = (m3[6]+m3[9]+m3[21]) * (1 / (1 - m3_Null));
		m3_High = (m3[12]+m3[14]+m3[22]) * (1 / (1 - m3_Null));
		m3_Very_High = (m3[18]+m3[19]+m3[23]) * (1 / (1 - m3_Null));
		}

		//System.out.println(": "+m3_Low+" "+m3_Moderate+" "+m3_High+" "+m3_Very_High+" "+m3_Null);
		}
}
