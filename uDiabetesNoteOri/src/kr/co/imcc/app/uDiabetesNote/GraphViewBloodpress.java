package kr.co.imcc.app.uDiabetesNote;

import java.util.*;

import android.content.*;
import android.graphics.*;
import android.util.*;
import android.util.Log;
import android.view.*;

public class GraphViewBloodpress extends View {

	// public static boolean BAR = true;
	// public static boolean LINE = false;
	//
	// private Paint paint;
	// private float[] values;
	// private String[] horlabels;
	// private String[] verlabels;
	// private String title;
	// private boolean type;
	
	private ScaleGestureDetector mScaleDetector; 
	private float mScaleFactor = 1.f; 
	
	private float height1;
	private float width1;
	private float minWidth1;

	private float h;
	private float w;
	private float marginLeft = 50.0f;
	private float marginRight = 100.0f;
	private float marginTop = 5.0f;
	private float marginBottom = 50.0f;
	private float strokeWidth = 3.0f;	
	
	private float[] vals;// = new float[]{10.0f, 20.0f, 5.0f, 110.0f, 50.0f};
	private float[] zvals;
	private ArrayList<String> xlab;
	private ArrayList<String> xval;

	
	private float maxSugar;
	private float minSugar;
	
	public GraphViewBloodpress(Context context, float[] values, float[] zvalue,  ArrayList<String> xvalue, 
			ArrayList<String> xlabel, ArrayList<String> ylabel, float height, float width, float max, float min) {
		super(context);
		
		mScaleDetector = new ScaleGestureDetector(context, new ScaleListener()); 
		
//		Log.d("height", getHeight()+ " " + getWidth());
		
		height1 = height;
		width1 = width;
		minWidth1 = width;

		vals = values;//수축기값
		zvals = zvalue;//이완기값
		xlab = xlabel;//날짜라벨
		xval = xvalue;//날짜값
		
		maxSugar = max;
		minSugar = min;	
		
		
		// if (values == null)
		// values = new float[0];
		// else
		// this.values = values;
		// if (title == null)
		// title = "";
		// else
		// this.title = title;
		// if (horlabels == null)
		// this.horlabels = new String[0];
		// else
		// this.horlabels = horlabels;
		// if (verlabels == null)
		// this.verlabels = new String[0];
		// else
		// this.verlabels = verlabels;
		// this.type = type;
		// paint = new Paint();

	}

	@Override
	protected void onDraw(Canvas canvas) {
		
//		canvas.save();    
//		canvas.scale(mScaleFactor, 1f);
	
		
		//if (height1 > getHeight()) {
			h = getHeight() - marginTop - marginBottom;//그래프 자체 높이 
		//} else {
		//	h = height1;
		//}

		if (width1 > getWidth()) {
			w = getWidth() - marginLeft - marginRight;//그래프 자체 너비
		} else if(width1 < minWidth1){
			w = minWidth1;
		}
		else {
			w = width1;
		}
		
		Paint pen = new Paint(Paint.ANTI_ALIAS_FLAG);

		pen.setColor(0xFFFFFFFF);
		pen.setStrokeWidth(strokeWidth);
		pen.setStyle(Paint.Style.STROKE);
//		canvas.drawLine(marginLeft, marginTop, marginLeft, marginTop + h, pen); // y축
		canvas.drawLine(marginLeft, marginTop + h, marginLeft + w + 50, marginTop + h, pen); // x축
		
		Paint txtPen = new Paint(Paint.ANTI_ALIAS_FLAG);
		txtPen.setColor(0xFFFFFFFF);	    
		txtPen.setTextSize(15);
		txtPen.setTextAlign(Paint.Align.LEFT);
		
//		canvas.drawText("혈당(mg/dL)", marginLeft + 10, marginTop + 10, txtPen); //y축 타이틀
		canvas.drawText("", marginLeft + w + 60, marginTop + h, txtPen); //x축 타이틀
		
		Paint dashPen = new Paint();		
		dashPen.setAntiAlias(true);
		dashPen.setAntiAlias(false);
		dashPen.setColor(0xFF6CBACC);
		dashPen.setStrokeWidth(1);		
		//dashPen.setPathEffect(new DashPathEffect(new float[] {10, 10 }, 0));
		
		Paint txtPen1 = new Paint(Paint.ANTI_ALIAS_FLAG);
		txtPen1.setColor(0xFFFFFFFF);	    
		txtPen1.setTextSize(15);
		txtPen1.setTextAlign(Paint.Align.LEFT);
		
		Paint pen1 = new Paint(Paint.ANTI_ALIAS_FLAG);
		pen1.setColor(0xFFDE6668);
		pen1.setStrokeWidth(1);
		pen1.setStyle(Paint.Style.STROKE);
		
		Paint lowpen = new Paint(Paint.ANTI_ALIAS_FLAG);
		lowpen.setColor(0xFF1CB6B6);
		lowpen.setStrokeWidth(3);
		lowpen.setStyle(Paint.Style.STROKE);
		
		int cHeight = (int)((h-20)/12);//가로눈금선 갯수set
		
		for(int i=1; i<13; i++){ //가로눈금선 그리기, 루프횟수는 몇개를 표시하느냐의 값
			canvas.drawLine(marginLeft + 3,marginTop + h - i*cHeight, marginLeft + w + 40, marginTop + h - i*cHeight, dashPen);
			//canvas.drawText(i*50+"", marginLeft - 30, marginTop + h - i*cHeight + 5, txtPen1);
		}
		
		int cWidth = 96;
		
		for(int j=1; j< (xlab.size()+1); j++){ //세로눈금선
			canvas.drawLine(marginLeft + cWidth*j,marginTop + h - 3, marginLeft + cWidth*j, marginTop + h - 12*cHeight - cHeight/3, dashPen);
			
			//if(j==1 || j==xlab.size()){
			canvas.drawText(xlab.get(j-1).toString(), marginLeft + cWidth*j - cWidth/2, marginTop + h + 20, txtPen1);	
			//}		
			//Log.d("date1: ", xlab.get(j-1).toString());			
		}
		
		
		if(vals.length>1)
		{			
			for(int k=1; k<vals.length; k++)
			{		
				String date1 = xval.get(k-1).toString().substring(0, 10);//날짜
				String timehour1 = xval.get(k-1).toString().substring(10, 12);//시간
				String timemin1 = xval.get(k-1).toString().substring(13, 15);//분
				float time1 = Float.parseFloat(timehour1) + Float.parseFloat(timemin1)/60.0f;				
//				Log.d("date1: ", date1);
				int position1 = 0;  
				
				for(int p1=0; p1<xlab.size(); p1++)
				{
					if(date1.equals(xlab.get(p1).toString()))
					{
						position1 = p1+1;
						break;
					}
				}
				
				String date2 = xval.get(k).toString().substring(0, 10);
				String timehour2 = xval.get(k).toString().substring(10, 12);
				String timemin2 = xval.get(k).toString().substring(13, 15);
				float time2 = Float.parseFloat(timehour2) + Float.parseFloat(timemin2)/60.0f;	
				
				int position2 = 0;  
				
				for(int p2=0; p2<xlab.size(); p2++)
				{
					if(date2.equals(xlab.get(p2).toString()))
					{
						position2 = p2+1;
						break;
					}
				}		
				canvas.drawLine(marginLeft + cWidth*position1 + time1*4 ,marginTop + h - (h-20)*vals[k-1]/240, 
						marginLeft + cWidth*position2 + time2*4, marginTop + h - (h-20)*vals[k]/240, pen1);	//수축기 선
				
				canvas.drawLine(marginLeft + cWidth*position1 + time1*4 ,marginTop + h - (h-20)*zvals[k-1]/240, 
						marginLeft + cWidth*position2 + time2*4, marginTop + h - (h-20)*zvals[k]/240, lowpen);	//이완기 선				
			}
		}
				
		Paint pen2 = new Paint(Paint.ANTI_ALIAS_FLAG);

		pen2.setColor(0xFFDE6668);
		pen2.setStrokeWidth(3);
		pen2.setStyle(Paint.Style.STROKE);
		
		Paint lowpen2 = new Paint(Paint.ANTI_ALIAS_FLAG);

		lowpen2.setColor(0xFF1CB6B6);
		lowpen2.setStrokeWidth(3);
		lowpen2.setStyle(Paint.Style.STROKE);
		

		for(int m=1; m<vals.length+1; m++){		
			canvas.drawCircle(SearchPosition(m), marginTop + h - (h-20)*vals[m-1]/240, 5, pen2);//수축기 포인트	
			
			canvas.drawCircle(SearchPosition(m), marginTop + h - (h-20)*zvals[m-1]/240, 5, lowpen2);	//이완기 포인트
		}
		
		
		Paint pen3 = new Paint(Paint.ANTI_ALIAS_FLAG);

		pen3.setColor(Color.argb(70, 255, 0, 0));
//		pen2.setStrokeWidth(3);
//		pen2.setStyle(Paint.Style.STROKE);
		
		canvas.drawRect(marginLeft + 3, marginTop + h - 12*cHeight,  marginLeft + cWidth*xlab.size(), marginTop + h - (h-20)*maxSugar/240, pen3);
		canvas.drawRect(marginLeft + 3, marginTop + h - 3,  marginLeft + cWidth*xlab.size(), marginTop + h - (h-20)*minSugar/240, pen3);
		
		pen3.setColor(Color.argb(70, 255, 255, 0));
		canvas.drawRect(marginLeft + 3, 0,  marginLeft + cWidth*xlab.size(), marginTop + h - 12*cHeight, pen3);		
	}
	
	public float SearchPosition(int m) 
	{
		int cWidth = 96;
		
		String date1 = "";
		String timehour1 = "";
		String timemin1 = "";
		int position1 = 0;  
		float time1 = 0.0f;
		
		date1 = xval.get(m-1).toString().substring(0, 10);
		timehour1 = xval.get(m-1).toString().substring(10, 12);
		timemin1 = xval.get(m-1).toString().substring(13, 15);
		time1 = Float.parseFloat(timehour1) + Float.parseFloat(timemin1)/60.0f;				
						
		for(int p1=0; p1<xlab.size(); p1++)
		{
			if(date1.equals(xlab.get(p1).toString()))//가로선 라벨값들과 해당하는 데이터의 날짜값과 비교
			{
				position1 = p1+1;//해당하는 데이터의 위치(순서) 값
				break;
			}
		}
									
		float test = marginLeft + cWidth * position1 + time1 * 4; // 좌표
		Log.d("position", "position : " + position1);
		Log.d("position", "position" + test);
		
		return test;
	}
	
	
	static int NONE = 0;
	static int DRAG = 1;
	static int ZOOM = 2;
	
	int mode = NONE;
	
	int posX1=0, posX2=0, posY1=0, posY2=0;
	
	float oldDist = 1f;
	float newDist = 1f;
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		
		 mScaleDetector.onTouchEvent(event);
		 return true; 
		
//		int act = event.getAction();
//		String strMsg="";
//		
////		Toast.makeText(getContext(), "ok", Toast.LENGTH_SHORT).show();
//		
//		switch(act & MotionEvent.ACTION_MASK){
//		
//		case MotionEvent.ACTION_DOWN:
//			Toast.makeText(getContext(), "down", Toast.LENGTH_SHORT).show();
//			posX1 = (int)event.getX();
//			posY1 = (int)event.getY();
//			
//			mode = DRAG;
//			break;
//		
//		case MotionEvent.ACTION_MOVE:
//			
//			Toast.makeText(getContext(), "move", Toast.LENGTH_SHORT).show();
//			if(mode==DRAG){
//				posX2 = (int)event.getX();
//				posY2 = (int)event.getY();
//				
//				if(Math.abs(posX2 - posX1)>20 || Math.abs(posY2 - posY1)>20){
//					posX1 = posX2;
//					posY1 = posY2;
//					strMsg = "drag";
//					
//					Toast.makeText(getContext(), strMsg, Toast.LENGTH_SHORT).show();
//				}
//				
//			} else if(mode==ZOOM){
//				 newDist = spacing(event);
//				 
//				 if(newDist - oldDist > 20) {//zoom in
//					 oldDist = newDist;
//					 strMsg = "zoom in";
//					 Toast.makeText(getContext(), strMsg, Toast.LENGTH_SHORT).show();
//				 }else if(oldDist - newDist > 20){//zoom out
//					 oldDist = newDist;
//					 strMsg = "zoom out";
//					 Toast.makeText(getContext(), strMsg, Toast.LENGTH_SHORT).show();
//				 }
//				
//			}	
//			break;
//		case MotionEvent.ACTION_UP:
//		case MotionEvent.ACTION_POINTER_UP:
//			mode = NONE;
//			break;
//		case MotionEvent.ACTION_POINTER_DOWN:
//			Toast.makeText(getContext(), "pointer_down", Toast.LENGTH_SHORT).show();
//			mode = ZOOM;
//			
//			newDist = spacing(event);
//			oldDist = spacing(event);
//			break;
//		case MotionEvent.ACTION_CANCEL:
//		default:
//			break;			
//		}
//		
//		
//		return super.onTouchEvent(event);
	}
	
	private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {   
		@Override     
		public boolean onScale(ScaleGestureDetector detector) {  
			
			
			mScaleFactor *= detector.getScaleFactor();          // Don't let the object get too small or too large.       
			mScaleFactor = Math.max(0.1f, Math.min(mScaleFactor, 5.0f)); 
			
//			Toast.makeText(getContext(), mScaleFactor+"", Toast.LENGTH_SHORT).show();
			
			if(detector.getScaleFactor()<1.0f){
				
				width1 = width1 - 100;			
				ActivityBloodPressure.GraphView.invalidate();
				
			}else{
				
				width1 = width1 + 100;			
				ActivityBloodPressure.GraphView.invalidate();
				
			}
			
			invalidate();        
			return true;     
		}
	}
	
	private float spacing(MotionEvent event){
		float x = event.getX(0) - event.getX(1);
		float y = event.getY(0) - event.getY(1);
		
		return FloatMath.sqrt(x*x+y*y);
	}

	// private float getMax() {
	// float largest = Integer.MIN_VALUE;
	// for (int i = 0; i < values.length; i++)
	// if (values[i] > largest)
	// largest = values[i];
	// return largest;
	// }
	//
	// private float getMin() {
	// float smallest = Integer.MAX_VALUE;
	// for (int i = 0; i < values.length; i++)
	// if (values[i] < smallest)
	// smallest = values[i];
	// return smallest;
	// }

}
