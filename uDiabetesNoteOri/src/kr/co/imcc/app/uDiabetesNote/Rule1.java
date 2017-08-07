package kr.co.imcc.app.uDiabetesNote;


public class Rule1 {

	static double sex[]= new double[4];
	static double age[]= new double[4];
	static double total[] = new double[4];
	static double sbp[] = new double[4];
	static double hdl[] = new double[4];
	static double dia[] = new double[4];
	static double smo[] = new double[4];
	
	
	static double Low=0;
	static double Moderate=0;
	static double High=0;
	static double Very_High=0;
	
	
	public static void Rule(){
	
		Fuzzy fz = new Fuzzy();
		
		
		if(Fuzzy.sex == 1){
			
			sex[3] = 1;
			sex[2] = 1;
			//sex[1] += 0.0;
			//sex[0] += 0.0;
			
			age[0] += Fuzzy.Less_Mid_Age;
			age[1] += Fuzzy.Mid_Age;			
			age[2] += Fuzzy.Very_Mid_Age;
			age[2] += Fuzzy.Less_Old;
			age[3] += Fuzzy.Old;
			
			total[0] += Fuzzy.total_Very_Low;
			total[1] += Fuzzy.total_Low;
			total[2] += Fuzzy.total_Mid;
			total[2] += Fuzzy.total_High;
			total[3] += Fuzzy.total_Very_High;
			
			sbp[0] += Fuzzy.sbp_Very_Low;
			sbp[1] += Fuzzy.sbp_Low;
			sbp[1] += Fuzzy.sbp_Mid;
			sbp[2] += Fuzzy.sbp_High;
			sbp[2] += Fuzzy.sbp_Very_High;

			hdl[0] += Fuzzy.hdl_High;
			hdl[0] += Fuzzy.hdl_Mid;
			hdl[1] += Fuzzy.hdl_Low;
			hdl[2] = 0;
			hdl[3] = 0;
			
			
			if(Fuzzy.smo == "Y"){
				smo[0] = 0.0;
				smo[1] = 0.0;
				smo[2] = 0.45;
				smo[3] = 0.55;
			}
			if(Fuzzy.smo == "N"){
				smo[0] = 0.6;
				smo[1] = 0.4;
				smo[2] = 0;
				smo[3] = 0;
			}
			
			if(Fuzzy.dia == "N"){
				dia[0] = 0.6;
				dia[1] = 0.4;
				dia[2] = 0;
				dia[2] = 0;
			}
			if(Fuzzy.dia == "Y"){
				dia[0] = 0;
				dia[1] = 0;
				dia[2] = 0.45;
				dia[3] = 0.55;
			}
		}
		
		
		if(Fuzzy.sex == 2){
			age[0] += Fuzzy.Less_Mid_Age;
			age[1] += Fuzzy.Mid_Age;
			age[2] += Fuzzy.Very_Mid_Age;
			age[2] += Fuzzy.Less_Old;
			age[2] += Fuzzy.Old;
			
			total[0] += Fuzzy.total_Very_Low;
			total[1] += Fuzzy.total_Low;
			total[1] += Fuzzy.total_Mid;
			total[2] += Fuzzy.total_High;
			total[2] += Fuzzy.total_Very_High;
			
			sbp[0] += Fuzzy.sbp_Very_Low;
			sbp[1] += Fuzzy.sbp_Low;
			sbp[2] += Fuzzy.sbp_Mid;
			sbp[2] += Fuzzy.sbp_High;
			sbp[2] += Fuzzy.sbp_Very_High;
			
			//sex[0] = 0.5;
			//sex[1] = 0.5;
			//sex[2] = 0.0;
			//sex[3] = 0.0;
			
			hdl[0] += Fuzzy.hdl_High;
			hdl[0] += Fuzzy.hdl_Mid;
			hdl[1] += Fuzzy.hdl_Low;
			hdl[2] = 0;
			hdl[3] = 0;
			
			
			if(Fuzzy.smo == "Y"){
				smo[0] = 0.0;
				smo[1] = 0.0;
				smo[2] = 1;
				smo[3] = 0;
			}
			if(Fuzzy.smo == "N"){
				smo[0] = 0.7;
				smo[1] = 0.3;
				smo[2] = 0;
				smo[3] = 0;
			}
			
			if(Fuzzy.dia == "N"){
				dia[0] = 0.7;
				dia[1] = 0.3;
				dia[2] = 0;
				dia[2] = 0;
			}
			if(Fuzzy.dia == "Y"){
				dia[0] = 0;
				dia[1] = 0;
				dia[2] = 1;
				dia[3] = 0.0;
			}

		}
		

		
		/*
		for(int i = 0; i<=2; i++){
			age2[i] *= 0.4;
			total2[i] *= 0.1;
			sbp2[i] *= 0.2;
			hdl[i] *=0.1;
			dia2[i] *=0.1;
			smo2[i] *=0.1;
		}
		*/
		
	// System.out.println("Rule1 age : "+ age1[0]+" "+age1[1]+" "+age1[2]);
	// System.out.println("Rule1 total : "+ total1[0]+" "+ total1[1]+" "+total1[2]);
	// System.out.println("Rule1 dia : "+ dia1[0]+" "+ dia1[1]+" "+ dia1[2]);
		
		
		Low =		(	sex[0]	+	age[0]	+	total[0]	+	hdl[0]	+	sbp[0]	+	dia[0]	+	smo[0]	)/7;
		Moderate =	(	sex[1]	+	age[1]	+	total[1]	+	hdl[1]	+	sbp[1]	+	dia[1]	+	smo[1]	)/7;
		High =		(	sex[2]	+	age[2]	+	total[2]	+	hdl[2]	+	sbp[2]	+	dia[2]	+	smo[2]	)/7;
		Very_High=	(	sex[3]	+	age[3]	+	total[3]	+	hdl[3]	+	sbp[3]	+	dia[3]	+	smo[3]	)/7;

		
		
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
		
		
		
		//System.out.println(aa);//+"\t"+Low+"\t"+Moderate+"\t"+High+"\t"+Very_High+"\t");
		
		
	}
}
