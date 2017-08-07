package kr.co.imcc.app.uDiabetesNote;



public class Fuzzy {
	
	static int sex;
	static int age;
	static double total = 0;
	static double hdl = 0;
	static double sbp = 0;
	static String dia = "";
	static String smo = "";
	
	static double Less_Mid_Age = 0;
	static double Mid_Age = 0;
	static double Very_Mid_Age = 0;
	static double Less_Old = 0;
	static double Old = 0;
	
	static double total_Very_Low = 0;
	static double total_Low = 0;
	static double total_Mid = 0;
	static double total_High = 0;
	static double total_Very_High = 0;
	
	static double sbp_Very_Low = 0;
	static double sbp_Low = 0;
	static double sbp_Mid = 0;
	static double sbp_High = 0;
	static double sbp_Very_High = 0;
	
	
	static double hdl_Low = 0;
	static double hdl_Mid = 0;
	static double hdl_High = 0;
	

	public static void main(String argv[]){
		
		//System.out.println(age + " "+total);
		
		
		
		System.out.println(total_Very_Low+" "+ total_Low+" "+ total_High+" "+total_Very_High);
	}
	
	public static double equation(double a1, double a2, double x){
		
		double z = x;
		
		double z1 =a1; //1
		double z2 =a2; //0
		
		double y =0;
		double a =0;
		double b =0;
		
		
		a = 1/(z1+(-z2));
		b = (-(z1*a))+1;
		
		y = a*z+b;
		return y;
	}
	
	public static void fuzzy(){
		
	}
	
	public static void fuzzy(int iSex, int iAge, double iTotal, double ihdl, double isbp, String idiabetes, String ismoker){
					
		sex = iSex;
		age = iAge;
		total = iTotal;
		hdl = ihdl;
		sbp = isbp;
		dia = idiabetes;
		smo = ismoker;
		
		
		Less_Mid_Age = 0;
		Mid_Age = 0;
		Very_Mid_Age = 0;
		Less_Old = 0;
		Old = 0;
		
		total_Very_Low = 0;
		total_Low = 0;
		total_Mid = 0;
		total_High = 0;
		total_Very_High = 0;
		
		sbp_Low = 0;
		sbp_Mid = 0;
		sbp_High = 0;
		sbp_Very_High = 0;
		
		
		hdl_Low = 0;
		hdl_Mid = 0;
		hdl_High = 0;
		
		
		
		// Fuzzy ����� �Լ�
		
		//===================================== Age

		if(age<=50)
			Less_Mid_Age = 1.0; 								
		else if(age>50 && age<55)
			Less_Mid_Age = equation(50,55,age); 		
		else{
			Less_Mid_Age = 0.0;
		}

		
		if(age>50 && age<55)
			Mid_Age = equation(55,50,age);		
		else if(age == 55)  
			Mid_Age = 1.0;							
		else if(age>55 && age<60)
			Mid_Age = equation(55,60,age);		
		else{
			Mid_Age = 0.0;
		}
		
		
		if(age>55 && age<60)
			Very_Mid_Age = equation(60,55,age);		
		else if(age == 60)  
			Very_Mid_Age = 1.0;							
		else if(age>60 && age<65)
			Very_Mid_Age = equation(60,65,age);		
		else{
			Very_Mid_Age = 0.0;
		}
		
		if(age>60 && age<65)
			Less_Old = equation(65,60,age);		
		else if(age == 65) 
			Less_Old = 1.0;								
		else if(age>65 && age<70)
			Less_Old = equation(65,70,age);		
		else{
			Less_Old = 0.0;
		}
		
		if(age>65 && age<70)
			Old = equation(70,65,age);		
		else if(age >= 70)  
			Old = 1.0;								
		else{
			Old = 0.0;
		}

		
		
		//===================================== Total colesterolrlaw
		
		if(total<=160)
			total_Very_Low = 1.0; 								// fuzzy_b VL
		else if(total>160 && total<190)
			total_Very_Low = equation(160,190,total); 	// fuzzy_b VL
		else{
			total_Very_Low = 0.0;
		}

		
		if(total>160 && total<190)
			total_Low = equation(190,160,total);		// fuzzy_b L
		else if(total ==190)  
			total_Low = 1.0;								// fuzzy_b L
		else if(total>190 && total<220)
			total_Low = equation(190,220,total);		// fuzzy_b L
			
		else{
			total_Low = 0.0;
		}
		
		if(total>190 && total<220)
			total_Mid = equation(220,190,total);		// fuzzy_b M
		else if(total ==220)  
			total_Mid = 1.0;								// fuzzy_b M
		else if(total>220 && total<250)
			total_Mid = equation(220,250,total);		// fuzzy_b M
		else{
			total_Mid = 0.0;
		}
		
		if(total>220 && total<250)
			total_High = equation(250,220,total);		// fuzzy_b M
		else if(total ==250)  
			total_High = 1.0;								// fuzzy_b M
		else if(total>250 && total<280)
			total_High = equation(250,280,total);		// fuzzy_b M
		else{
			total_High = 0.0;
		}
		
		if(total>250 && total<280)
			total_Very_High = equation(280,250,total);		// fuzzy_b H
		else if(total >= 280)  
			total_Very_High = 1.0;								// fuzzy_b H
		else{
			total_Very_High = 0.0;
		}
		
	//	System.out.println(total_Very_Low +" "+total_Low+" "+total_Mid+" "+total_High+" "+total_Very_High);
	
		
		//===================================== sbp
		
		if(sbp<=110)
			sbp_Very_Low = 1.0; 								// fuzzy_c VL
		else if(sbp>110 && sbp<120)
			sbp_Very_Low = equation(110,120,sbp); 	// fuzzy_c VL
		else{
			sbp_Very_Low = 0.0;
		}

		if(sbp>110 && sbp<120)
			sbp_Low = equation(120,110,sbp);		// fuzzy_c L
		else if(sbp == 120)  
			sbp_Low = 1.0;								// fuzzy_c L
		else if(sbp>120 && sbp<130)
			sbp_Low = equation(120,130,sbp);		// fuzzy_c L
		else{
			sbp_Low = 0.0;
		}
		
		
		if(sbp>120 && sbp<130)
			sbp_Mid = equation(130,120,sbp);		// fuzzy_c L
		else if(sbp == 130)  
			sbp_Mid = 1.0;								// fuzzy_c L
		else if(sbp>130 && sbp<140)
			sbp_Mid = equation(130,140,sbp);		// fuzzy_c L
		else{
			sbp_Mid = 0.0;
		}
		
		if(sbp>130 && sbp<140)
			sbp_High = equation(140,130,sbp);		// fuzzy_c M
		else if(sbp == 140)  
			sbp_High = 1.0;								// fuzzy_c M
		else if(sbp>140 && sbp<150)
			sbp_High = equation(140,150,sbp);		// fuzzy_c M
		else{
			sbp_High = 0.0;
		}
		
	
		if(sbp>140 && sbp<150)
			sbp_Very_High = equation(150,140,sbp);		// fuzzy_c H
		else if(sbp >= 150)  
			sbp_Very_High = 1.0;								// fuzzy_c H
		else{
			sbp_Very_High = 0.0;
		}

		//===================================== hdl
		
		if(hdl<=35)
			hdl_Low = 1.0; 								// fuzzy_c VL
		else if(hdl>35 && hdl<45)
			hdl_Low = equation(35,45,hdl); 	// fuzzy_c VL
		else{
			hdl_Low = 0.0;
		}

		
		if(hdl>35 && hdl<45)
			hdl_Mid = equation(45,35,hdl);		// fuzzy_c L
		else if(hdl == 45)  
			hdl_Mid = 1.0;								// fuzzy_c L
		else if(hdl>45 && hdl<55)
			hdl_Mid = equation(45,55,hdl);		// fuzzy_c L
		else{
			hdl_Mid = 0.0;
		}
		
	
		if(hdl>45 && hdl<55)
			hdl_High = equation(55,45,hdl);		// fuzzy_c H
		else if(hdl >= 55)  
			hdl_High = 1.0;								// fuzzy_c H
		else{
			hdl_High = 0.0;
		}
		
	//	System.out.println("Rule1 Fuzzy : "+		Less_Mid_Age+" "+Mid_Age+" "+Very_Mid_Age+" "+Less_Old+" "+Old+"   "+total_Very_Low+" "+total_Low+" "+total_High+" "+total_Very_High+"   "+		sbp_Low+" "+sbp_Mid+" "+sbp_High+" "+sbp_Very_High+"   "+		hdl_Low+" "+hdl_Mid+" "+hdl_High);
		
		
	}
}