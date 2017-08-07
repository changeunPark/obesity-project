package kr.co.imcc.app.uDiabetesNote;

import java.util.*;

import android.content.*;
import android.content.res.*;
import android.database.*;
import android.graphics.*;
import android.graphics.Paint.Style;
import android.graphics.drawable.*;
import android.view.*;

public class cal_CalView_3_1 extends View {
	
	protected static int start = 0;
	
	Calendar calendarCheck;
	
	String sqlDataCheck = "";
	Cursor cursorDataCheck;
	
	private float width;    // width of one tile
	private float height;   // height of one tile
	private float height2;   // height of one tile
	private int selX;       // X index of selection
	private int selY;       // Y index of selection
	private Rect tempRect = new Rect();
	private Rect selRect = new Rect();
   
	private int[][] cal = new int[7][6];
	private String[][] lunarcal = new String[7][6];
	
	private int column;
	
	private int penYValue;
	private int textSize;
	private int backColor;
	private int textColor;
	private int gridLineColor1;
	private int gridLineColor2;
	private int selectColor;
	
	private int change;

	private int checkwidthTriangle;
	private int checkheightTriangle;
	
	private static int oldheight;
	
	private static int touchtemp1=0;
	private static int touchtemp2=0;
	
	private final tActivity3_1 Cal;
	
	String sqlStandardValue = "";
	Cursor cursorStandardValue;
	
	String standardCalorie = "";
	String standardTime = "";
	
	public cal_CalView_3_1(Context context) 
	{
		super(context);
		this.Cal = (tActivity3_1) context;
		setFocusable(true);
		setFocusableInTouchMode(true);
		
		calendarCheck = Calendar.getInstance();
		
		sqlStandardValue = "select * from STANDARDVALUE";
		cursorStandardValue = tActivity3_1.sqLiteDatabase.rawQuery(sqlStandardValue, null);
		
		if(cursorStandardValue.getCount()>0)
		{ 			
			cursorStandardValue.moveToFirst();					
			standardCalorie = cursorStandardValue.getString(3);			
			standardTime = cursorStandardValue.getString(4);		
		}
		
		cursorStandardValue.close();
	}
	   
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) 
	{		
//		int nam=0;
//		if( (tActivity3_1.iDate[0] +tActivity3_1.iDate[1]) % 7 != 0)
//			nam = 1;
//		column = ((tActivity3_1.iDate[0] +tActivity3_1.iDate[1]) / 7 ) +nam;
		
		width = w / 7f;
		//height2 = 50;//( h / column) / 1.5f;
		oldheight =h;
      
		getRect(selX, selY, selRect);
		super.onSizeChanged(w, h, oldw, oldh);
	}
	   
	@Override
	protected void onDraw(Canvas canvas) 
	{
		
//		Arrays.fill(cal, 0);
		// �簢�� �ܻ� ���ֱ�	   
//		invalidate(selRect);
		//cal[col][row] = 0;
		for(int j=0; j<7;j++)
		{
			for(int k=0; k<6; k++)
			{
				cal[j][k] = 0;
			}
		}
		
		int nam = 0;
		if((tActivity3_1.iDate[0]+tActivity3_1.iDate[1]) % 7 != 0)	   
			nam =1;
	   
		change = ((tActivity3_1.iDate[0] +tActivity3_1.iDate[1]) / 7 ) +nam;
	
		 // �ﰢ�� üũ �ֱ� ���� �⺻ ��ǥ
	    float trianglex = width - (width / 4);
	    float triangley = 0;
	    
	    int max=0;
	    
		if( change == 5){
			max = 6;
			//height = 50;//(oldheight - height2*2) / (change);
		//	checkwidthTriangle = (int) (width / 6);
			checkheightTriangle = (int) (height / 6);
			triangley = height + ( height / 2 );
		}
		else if( change == 6){
			max=7;
			//height = 50;//(oldheight - height2*2) / (change);
		//	checkwidthTriangle = (int) (width / 5);
			checkheightTriangle = (int) (height / 6);
			triangley = height + ( height / 2 );// + ( height / 8 );
		}
		//checkTriangle = (int) (height / 8);
		checkwidthTriangle = (int) (width / 4);
		//checkheightTriangle = (int) (height / 6);
				
	    // Draw the background
	    Paint background = new Paint();
	    background.setColor(getBackColor());
	    canvas.drawRect(0, 0, getWidth(), getHeight(), background);
	    	    	   	        
	    // Draw the board...
	    // ���� �Ӽ� �����ϱ�
	    Paint foreground = new Paint(Paint.ANTI_ALIAS_FLAG);
	    foreground.setColor(getTextColor());
	    
	    foreground.setTextSize(gettextSize(height));
	    foreground.setTextAlign(Paint.Align.CENTER);
	    
	    // ���� �Ӽ� �����ϱ� 2 ���±���
	    Paint foregroundbottom = new Paint(Paint.ANTI_ALIAS_FLAG);
	    foregroundbottom.setColor(getTextColor());
	    //foregroundbottom.setTextSize(gettextSize( height ) );
	    foregroundbottom.setTextSize((float) (gettextSize( height ) / 1.5 ));
	    foregroundbottom.setTextAlign(Paint.Align.LEFT);	    
      
	    // Define colors for the grid lines 1,2
	    Paint hilite = new Paint();
	    hilite.setColor(getGridLineColor1() );

	    Paint light = new Paint();
	    light.setColor(getGridLineColor2() );
      
	    // ��׶��� ��ǥ
	    float w = width / 2;
	    float h = (float) (height2 / 1.5);
	    
	    // ���ڳֱ�
	    foreground.setTextAlign(Paint.Align.CENTER);
            
	    int dayCounut = 1;      
	    int col, row;
	    col = tActivity3_1.iDate[0];      
	    row = 0;
      
	    // ���� �ֱ� ���� ��ǥ x,y
	    float x = width / 2;
	    float y = height + height * 2/3 - (height/40);
	    //Cal.showToast(0,""+y  + "       "+(height - (height/7)));
	    	    
	    float lunarx = x;
	    float lunary = (float) (height * 1.5);

	    // �ﰢ�� ����
	    Rect triang = new Rect();      
	    Path triangpath = new Path();
	         	    
	    if(!SELECT_FLAG)
	    {   	
	    	if((tActivity3_1.sltDate[0] != calendarCheck.get(Calendar.YEAR) || 
	    			tActivity3_1.sltDate[1] != calendarCheck.get(Calendar.MONTH))) 
	    	{
				SELECT_FLAG = true;
	    		
	    		getRect(tActivity3_1.iDate[0] , 0, selRect);	      		
	    		
	    		String month="";
				String day="";
				
				if(Cal.getDateMonth()<10) {
					month = "0"+Cal.getDateMonth();
				}else{
					month = ""+Cal.getDateMonth();
				}
						
				if(dayCounut<10) {
					day = "0"+dayCounut;
				}else{
					day = ""+dayCounut;
				}
				
				tActivity3_1.SelectedDate2 = Cal.getDateYear()+"/"+month+"/"+day;
				tActivity3_1.SelectedDate = Cal.getDateYear()+"�� "+Cal.getDateMonth()+"�� "+dayCounut+"��";
				
				Cal.showExcerciseList(tActivity3_1.SelectedDate2);
	    	}	  
	    }
	    
	    // Draw the selection...
	    Paint selected = new Paint();
	    selected.setStyle(Style.STROKE);
	    selected.setStrokeWidth(4);
	    selected.setColor(0x7f800000);
	    canvas.drawRect(selRect, selected);
	    	    	       	  
	    for( int b = 0; b < tActivity3_1.iDate[1]; b++)
	    {       	
	    	String numTemp = Integer.toString(dayCounut);
	    	
	    	//�ָ�ǥ��
	    	if((tActivity3_1.iDate[0] + dayCounut) % 7 == 0)
	    	{
	    		foreground.setColor(Color.BLUE);
	    	}
	    	else if((tActivity3_1.iDate[0] + dayCounut) % 7 == 1)
	    	{
	    		foreground.setColor(Color.RED);
	    	}
	    	else 
	    	{
	    		foreground.setColor(getTextColor());
	    		foreground.setTextSize(gettextSize(height));
	    	}
	    	
	    	String month="";
			String day="";
			
			if(Cal.getDateMonth()<10) 
			{
				month = "0"+Cal.getDateMonth();
			}
			else
			{
				month = ""+Cal.getDateMonth();
			}
					
			if(dayCounut<10) 
			{
				day = "0" + dayCounut;
			}
			else
			{
				day = "" + dayCounut;
			}
  				    	   	    		
			sqlDataCheck = "select * from EXCERCISE where date=";
			cursorDataCheck = tActivity3_1.sqLiteDatabase.rawQuery(sqlDataCheck + "'" + Cal.getDateYear()+"/"+month+"/" +day + "'", null);
			
			//���ʱ׸� ����
	    	if(cursorDataCheck.getCount()>0)
	    	{   	    		 			
	    		cursorDataCheck.moveToFirst();					
	    		String useCalorie = cursorDataCheck.getString(7);			
	    		String useTime = cursorDataCheck.getString(4);			
	    			    		
	    		if(Integer.parseInt(useCalorie) >= Integer.parseInt(standardCalorie) && Integer.parseInt(useTime) >= Integer.parseInt(standardTime) )
	    		{
		    		Paint temp = new Paint();
		    		temp.setColor(0x7f9ACD32);
		    	    getRect(col , row, tempRect);	    
		    	    canvas.drawRect(tempRect, temp);	
		    	    //invalidate(tempRect); 
	    		}
	    	        	    	    	    	    	             	
	    		Resources res = getResources();
	    		
//	    		연필이미지 삽입
	    		BitmapDrawable bd = (BitmapDrawable)res.getDrawable(R.drawable.arrow01);
	    		Bitmap bit = bd.getBitmap();
	    		canvas.drawBitmap(bit, x + width * col + 15, y + penYValue-13, null);		   		
	    	}
	    	
	    	cursorDataCheck.close();
	    	
	    	// ������ ��¥ ǥ���ϱ�
	    	if((tActivity3_1.sltDate[0] == calendarCheck.get(Calendar.YEAR) && 
	    			tActivity3_1.sltDate[1] == calendarCheck.get(Calendar.MONTH) && tActivity3_1.iDate[2] == dayCounut)) 
	    	{
	    		foreground.setTextSize(gettextSize(height));
	    		
	    		if(start==0)
	    		{	
	    			SELECT_FLAG = true;
	    			
					if(Cal.getDateMonth()<10) 
					{
						month = "0"+Cal.getDateMonth();
					}
					else
					{
						month = ""+Cal.getDateMonth();
					}
							
					if(dayCounut<10) 
					{
						day = "0"+dayCounut;
					}
					else
					{
						day = ""+dayCounut;
					}
					
					tActivity3_1.SelectedDate2 = Cal.getDateYear()+"/"+month+"/"+day;
					tActivity3_1.SelectedDate = Cal.getDateYear()+"�� "+Cal.getDateMonth()+"�� "+dayCounut+"��";
					
					Cal.showExcerciseList(tActivity3_1.SelectedDate2);
		    		
		    		select(col, row);
	    			
	    			start = 1;
	    		} 		
	    	}
	
	    		    	
	    	//�޷¿� ��� �ֱ�
	    	canvas.drawText(numTemp , x + width * col, y , foreground);
	    		    	   	    	    	    	    			        		    
	    	// ����
	    	String tempy = toString(tActivity3_1.sltDate[0]);
	    	String tempm = toString(tActivity3_1.sltDate[1]);
	    	String tempd = "0";
	    	if ( numTemp.length() == 1)
	    		tempd += numTemp;
	    	else
	    		tempd = numTemp;
	    	
	    	// �� ���� ���� �ֱ�
	    	cal[col][row] = dayCounut;
	    	//	�ﰢ��	
	    	int wow = 0;
	    	if(wow == 1) 
	    	{     		
	    		triang.top = (int) triangley;
		    	
		    	triang.left = (int) (trianglex + width * col);
		    	triang.right = triang.left+checkwidthTriangle;
		    	
		    	triang.bottom = triang.top+checkheightTriangle;
	
		    	triangpath.moveTo(triang.left + triang.width(), triang.top);
		    	triangpath.lineTo(triang.left, triang.bottom);
	          	triangpath.lineTo(triang.right , triang.bottom);
	          	canvas.drawPath(triangpath, foreground);
	    	}
          	
          	col++;
          	/*
          	if( (Cal.iDate[0] + dayCounut) % 7 == 1){
          		foreground.setColor(Color.BLUE);
          	}
           */
          	if((tActivity3_1.iDate[0] + dayCounut) % 7 == 0)
          	{
          		y += height;
          		lunary += height;
          		row++;
          		triangley += height;
          		col = 0;
          	}
          	dayCounut++;
	    }
	    
	       
	    // Draw the minor grid lines
	    for (int i = 0; i < 7; i++) 
	    {
	    	if( i == 0)
	    	{
	    		canvas.drawLine(0, i * height2, getWidth(), i * height2 , light);
	    		canvas.drawLine(0, (i * height2 + 1) , getWidth(), (i * height2 + 1) , hilite);
	    		canvas.drawLine(0, height2 + i * height , getWidth(), height2 + i * height , light);
	    		canvas.drawLine(0, height2 + (i * height + 1)  , getWidth(), height2 +  (i * height + 1) , hilite);
	    		foreground.setColor(Color.RED);
	    		canvas.drawText( Cal.sWeek[i], w + width * i, h, foreground);
	    	}
	    	else if (i == 6) 
	    	{
	    		
	    		if(max==7)
	    		{	    			
	    		canvas.drawLine(0, height2 + i * height , getWidth(), height2 + i * height , light);
	    		canvas.drawLine(0, height2 + (i * height + 1)  , getWidth(), height2 +  (i * height + 1) , hilite);
	    		}
	    		canvas.drawLine(i * width, 0, i * width, height*max , light);
	    		canvas.drawLine(i * width + 1, 0, i * width + 1, height*max , hilite);
	    		foreground.setColor(Color.BLUE);
	    		canvas.drawText( Cal.sWeek[i], w + width * i, h, foreground);
	    	}
	    	else
	    	{
	    		canvas.drawLine(0, height2 + i * height , getWidth(), height2 + i * height , light);
	    		canvas.drawLine(0, height2 + (i * height + 1)  , getWidth(), height2 +  (i * height + 1) , hilite);
	    		canvas.drawLine(i * width, 0, i * width, height*max , light);
	    		canvas.drawLine(i * width + 1, 0, i * width + 1, height*max , hilite);
	    		foreground.setColor(getTextColor());
	    		canvas.drawText( Cal.sWeek[i], w + width * i, h, foreground);
	    	}
	    }
	    	   
	    dayCounut = 1;
	       
      	// ���� �ѷ��ֱ�(���� ���)
      	//if(Cal.iDate[1]!=0)
    	//canvas.drawText(""+33, width /2, y , foreground);
	}
	   
	static boolean SELECT_FLAG = false;
	
	@Override
	public boolean onTouchEvent(MotionEvent event) 
	{
		if (event.getAction() != MotionEvent.ACTION_DOWN)
			return super.onTouchEvent(event);
		
		if(0 <= (int) (event.getX() / width) && (int) (event.getX() / width) <7 &&
				0<= (int) (((event.getY()-height2) / height)) && (int) (((event.getY()-height2) / height)) <6)
		{			
			String month="";
			String day="";
			
			if(Cal.getDateMonth()<10) 
			{
				month = "0"+Cal.getDateMonth();
			}
			else
			{
				month = ""+Cal.getDateMonth();
			}
					
			if(cal[(int) (event.getX() / width)][(int) (((event.getY()-height2) / height))]<10) 
			{
				day = "0"+cal[(int) (event.getX() / width)][(int) (((event.getY()-height2) / height))];
			}
			else
			{
				day = ""+cal[(int) (event.getX() / width)][(int) (((event.getY()-height2) / height))];
			}
			
			tActivity3_1.SelectedDate2 = Cal.getDateYear()+"/"+month+"/"+day;
			
//			Log.d("day", cal[(int) (event.getX() / width)][(int) (((event.getY()-height2) / height))] + "");
			
			if(!day.equals("00"))
			{				
				SELECT_FLAG = true;
				
				tActivity3_1.calSelectColor = getResources().getColor(R.color.cal_selected);
				setSelectColor(tActivity3_1.calSelectColor);	
				tActivity3_1.calendarView.invalidate();
				
				tActivity3_1.SelectedDate = Cal.getDateYear()+"�� "+Cal.getDateMonth()+"�� "+cal[(int) (event.getX() / width)][(int) (((event.getY()-height2) / height))]+"��";
				
				Cal.showExcerciseList(tActivity3_1.SelectedDate2);
				
//				Cal.showToast( Cal.getDateYear(),"" + Cal.getDateMonth()+ " " +
//						cal[(int) (event.getX() / width)][(int) (((event.getY()-height2) / height)) ]);
						
				//Cal.showToast( (int)(event.getX() ), "" + (int)(event.getY() ));
				select((int) (event.getX() / width), (int) (((event.getY()-height2) / height) ));				
			}
			else
			{
//				SELECT_FLAG = false;
			}
		}				
		return true;
	}
	
	public String toString(int a)
	{	
		String temp = Integer.toString(a);
    	if (temp.length() == 1)
    		temp = "0"+ temp;
    	
		return temp;		
	}
   
   private void select(int x, int y) 
   {
	   selX = Math.min(Math.max(x, 0), 7);
	   selY = Math.min(Math.max(y, 0), 7);

	   if(change != selY) 
	   {		   	   
		   //Cal.showToast( touchtemp1 , ""+ touchtemp2 );		   
		   if ( touchtemp1 != cal[selX][selY])
			   touchtemp1 = cal[selX][selY];
		   else if ( touchtemp1 == cal[selX][selY])
			   touchtemp2 = cal[selX][selY];
		   
		   if ( touchtemp1 == touchtemp2) 
		   {			   
			   Cal.startIntent( Cal.getDateYear() , Cal.getDateMonth(), cal[selX][selY], Cal.getDateWeek(Cal.getDateYear(), Cal.getDateMonth(),cal[selX][selY] ));			   
		   }
		   
		   getRect(selX, selY, selRect);
		   	   	   	 
		   invalidate();
		   invalidate(selRect);		   
		   /*
		   Intent i = new Intent(this, GameActivity.class);
		   startActivity(i);
		   */
	   }
   }
   
   
   private void getRect(int x, int y, Rect rect) 
   {
      rect.set((int) (x * width + 3), (int)( height2+ (y * height + 3)), 
    		  (int) (x * width + width), (int) (height2 + (y * height + height)));
   }
   
   public void setTextSize(int size) {
	   textSize = size;
   }
   
   public void setheight(int height) {
	   height2 = height;
	   this.height = height;
   }
   
   public void setpenYValue(int yplus) {
	   penYValue = yplus;
   }
   
   private float gettextSize( float h) {
	   return  (5*h / textSize);
   }
   
   public void setBackColor( int c) {
	   backColor = c;
   }
   
   private int getBackColor() {
	   return backColor;
   }
   
   public void settextColor( int t) {
	   textColor = t;
   }
   
   private int getTextColor() {
	   return textColor;
   }
   
   public void setGridLineColor1 ( int g) {
	   gridLineColor1 = g;
   }
   
   private int getGridLineColor1() {
	   return gridLineColor1;
   }
   
   public void setGridLineColor2 ( int g1) {
	   gridLineColor2 = g1;
   }
   
   private int getGridLineColor2 () {
	   return gridLineColor2;
   }
   
   public void setSelectColor ( int s) {
	   selectColor = s;
   }
   
   private int getSelectColor () {
	   return selectColor;
   }    
}