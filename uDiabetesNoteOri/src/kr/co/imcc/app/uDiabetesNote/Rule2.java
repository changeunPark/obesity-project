package kr.co.imcc.app.uDiabetesNote;


public class Rule2 {

	static double sex0[]= new double[4];
	static double age0[]= new double[4];
	static double total0[] = new double[4];
	static double sbp0[] = new double[4];
	static double hdl0[] = new double[4];
	static double dia0[] = new double[4];
	static double smo0[] = new double[4];
	
	static double Low=0;
	static double Moderate=0;
	static double High=0;
	static double Very_High=0;
	
	public static void Rule(int iSex, int iAge, double iTotal, double iHDL, double iSBP, String iDiabetes, String iSmoker){

		int sex = iSex;
		int age = iAge;
		double total = iTotal;
		double hdl = iHDL;
		double sbp = iSBP;
		String dia = iDiabetes;
		String smo = iSmoker;

		 double L_count = 0;
		 double M_count = 0;
		 double H_count = 0;
		 double VH_count = 0;
		 
		 
		//Rule 1 for  High 
		 if( sex == 1 && age > 64 && total <= 166 && dia == "N" && smo == "N" ){H_count += 1;}
		//Rule 2 for  High 
		 if( sex == 1 && age > 64 && hdl > 46 && sbp <= 124 && dia == "N" && smo == "N" ){H_count += 1;}
		//Rule 3 for  High 
		 if( sex == 1 && age <= 64 && dia == "N" && smo == "Y" ){H_count += 1;}
		//Rule 4 for  High 
		 if( sex == 1 && age <= 63 && hdl <= 53 && sbp > 118 && sbp <= 130 && dia == "N" ){H_count += 1;} 
		//Rule 5 for  High 
		 if( age <= 67 && sbp > 130 ){H_count += 1;}
		//Rule 6 for  High 
		 if( age > 67 ){H_count += 1;}

		//Rules for Low - contains 3 rule(s)


		//Rule 1 for  Low 
		 if( age <= 64 && hdl > 42 && sbp <= 118 && dia == "N" && smo == "N" ){L_count += 1;}
		//Rule 2 for  Low 
		 if( sex == 2 && hdl > 42 && sbp <= 124 && dia == "N" && smo == "N" ){L_count += 1;}
		//Rule 3 for  Low 
		 if( sex == 2 && age <= 67 && sbp <= 130 ){L_count += 1;}


		//Rules for Moderate - contains 4 rule(s)
		//Rule 1 for  Moderate 
		 if( sex == 2 && age > 67 && sbp > 124 && sbp <= 130 && dia == "N" ){M_count += 1;}
		//Rule 2 for  Moderate 
		 if( age > 67 && hdl > 42 && sbp <= 130 && smo == "Y" ){M_count += 1;}
		//Rule 3 for  Moderate 
		 if( sex == 1 && age <= 64 && hdl <= 42 && sbp <= 118 && smo == "N" ){M_count += 1;}
		//Rule 4 for  Moderate 
		 if( sex == 1 && age <= 64 && sbp > 118 && dia == "N" && smo == "N" ){M_count += 1;}


		//Rules for Very_High - contains 8 rule(s)
		//Rule 1 for  Very_High 
		 if( sex == 1 && age > 64 && total > 166 && sbp > 124 ){VH_count += 1;} 
		//Rule 2 for  Very_High 
		 if( sex == 1 && age > 64 && dia == "Y" ){VH_count += 1;} 
		//Rule 3 for  Very_High 
		 if( sex == 1 && age > 64 && smo == "Y" ){VH_count += 1;}
		//Rule 4 for  Very_High 
		 if( sex == 1 && age > 64 && total > 214 && hdl <= 46 ){VH_count += 1;}
		//Rule 5 for  Very_High 
		 if( sex == 1 && total <= 169 && dia == "Y" ){VH_count += 1;}
		//Rule 6 for  Very_High 
		 if( sex == 1 && age > 55 && sbp > 130 ){VH_count += 1;}
		//Rule 7 for  Very_High 
		 if( sex == 1 && age > 70 && hdl <= 46 ){VH_count += 1;}
		//Rule 8 for  Very_High 
		 if( age > 67 && sbp > 130 ){VH_count += 1;}
		 
		double sum = L_count + M_count + H_count + VH_count; 
		 
		 if(sum==0){
			
			 Low = 0;
			 Moderate = 0;
			 High = 1;
			 Very_High = 0;
		 }
		 else{
			 Low = L_count / sum;
			 Moderate = M_count / sum;
			 High = H_count / sum;
			 Very_High = VH_count / sum;	 
		 }
		 
		 String aa = "";
		 
		 
			if(Low > Moderate){
				if(Low > High){
					if(Low>Very_High){
						aa = "Low";
					}
					else{
						aa = "Very_High";
					}
				}
				else{
					if(High>Very_High){
						aa = "High";
						
					}
					else{
						aa = "Very_High";
					}
				}
			}
			else{
				if(Moderate>High){
					if(Moderate>Very_High){
						aa ="Moderate";
					}
					else{
						aa ="Very_High";
					}
					
				}
				else{
					if(High>Very_High){
						aa = "High";
						
					}
					else{
						aa = "Very_High";
					}
				}
			}
		 
		// System.out.println(aa);//+"\t"+Low+"\t"+Moderate+"\t"+High+"\t"+Very_High);
	}
}
