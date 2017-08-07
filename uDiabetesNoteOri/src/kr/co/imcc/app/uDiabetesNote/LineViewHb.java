package kr.co.imcc.app.uDiabetesNote;

import java.util.*;

import android.content.*;
import android.graphics.*;
import android.util.*;
import android.view.*;

public class LineViewHb extends View {

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
	private ArrayList<String> xlab;
	private ArrayList<String> xval;
	
	private float maxSugar;
	private float minSugar;
	
	
	public LineViewHb(Context context, float[] values, ArrayList<String> xvalue, ArrayList<String> xlabel,
			ArrayList<String> ylabel, float height, float width, float max, float min) {
		super(context);
		
		mScaleDetector = new ScaleGestureDetector(context, new ScaleListener()); 
		
//		Log.d("height", getHeight()+ " " + getWidth());
		
		height1 = height;
		width1 = width;
		minWidth1 = width;

		vals = values;
		xlab = xlabel;
		xval = xvalue;
		
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
			h = getHeight() - marginTop - marginBottom;
		//} else {
		//	h = height1;
		//}

		if (width1 > getWidth()) {
			w = getWidth() - marginLeft - marginRight;
		} else if(width1 < minWidth1){
			w = minWidth1;
		}
		else {
			w = width1;
		}
		
		int cHeight = (int)((h-20)/28);
		
		Paint pen = new Paint(Paint.ANTI_ALIAS_FLAG);

		pen.setColor(0xFFFFFFFF);
		pen.setStrokeWidth(strokeWidth);
		pen.setStyle(Paint.Style.STROKE);
		canvas.drawLine(marginLeft, marginTop + h - 28*cHeight, marginLeft, marginTop + h, pen); //
		
		
		for(int i=0; i<29; i++){ //���� ���μ�
			//canvas.drawLine(marginLeft + 3,marginTop + h - i*cHeight, marginLeft + w + 40, marginTop + h - i*cHeight, dashPen);
			canvas.drawLine(marginLeft - 8, marginTop + h - i*cHeight, marginLeft + 8, marginTop + h - i*cHeight, pen); // y��
		}
		
		
			//canvas.drawLine(marginLeft - 3, marginTop + h, marginLeft + w + 50, marginTop + h, pen); // x��
		
		Paint txtPen = new Paint(Paint.ANTI_ALIAS_FLAG);
		txtPen.setColor(0xFFFFFFFF);	    
		txtPen.setTextSize(15);
		txtPen.setTextAlign(Paint.Align.LEFT);
		
		//canvas.drawText("��ȭ������(%)", 1, marginTop + h + 33, txtPen); //y�� Ÿ��Ʋ		
		/*txtPen.setColor(0xFF3380A0);
		txtPen.setTextSize(20);*/
		canvas.drawText("��ȭ������(%)",1, marginTop + 17, txtPen); //y�� Ÿ��Ʋ
		
		//canvas.drawText("��¥", marginLeft + w + 60, marginTop + h, txtPen); //x�� Ÿ��Ʋ
		
		Paint dashPen = new Paint();		
		dashPen.setAntiAlias(true);
		dashPen.setAntiAlias(false);
		dashPen.setColor(0xFFFFFFFF);
		dashPen.setStrokeWidth(3);		
		dashPen.setPathEffect(new DashPathEffect(new float[] {10, 10 }, 0));
		
		Paint txtPen1 = new Paint(Paint.ANTI_ALIAS_FLAG);
		txtPen1.setColor(0xFFFFFFFF);	    
		txtPen1.setTextSize(15);
		txtPen1.setTextAlign(Paint.Align.LEFT);
		
		Paint pen1 = new Paint(Paint.ANTI_ALIAS_FLAG);

		pen1.setColor(0xFFFFFFFF);
		pen1.setStrokeWidth(3);
		pen1.setStyle(Paint.Style.STROKE);
		
		//int cHeight = (int)((h-20)/10);
		
		for(int i=0; i<29; i++){//�Ҽ� ���μ�
			//canvas.drawLine(marginLeft + 3,marginTop + h - i*cHeight, marginLeft + w + 40, marginTop + h - i*cHeight, dashPen);
			if(i%2==0){
				canvas.drawText(i*0.5+"", marginLeft - 40, marginTop + h - i*cHeight + 5, txtPen1);	
			}
			
		}
		
		int cWidth = 96;
		
//		for(int j=1; j< (xlab.size()+1); j++){ //���μ�
//			canvas.drawLine(marginLeft + cWidth*j,marginTop + h - 3, marginLeft + cWidth*j, marginTop + h - 10*cHeight - cHeight/3, dashPen);
//			
//			//if(j==1 || j==xlab.size()){
//			canvas.drawText(xlab.get(j-1).toString(), marginLeft + cWidth*j - cWidth/2, marginTop + h + 20, txtPen1);	
//			//}		
//			//Log.d("date1: ", xlab.get(j-1).toString());
//				
//		}
//		
//		
//		if(vals.length>1){
//			
//			for(int k=1; k<vals.length; k++){	
//				
//				String date1 = xval.get(k-1).toString().substring(0, 10);
//				String timehour1 = xval.get(k-1).toString().substring(10, 12);
//				String timemin1 = xval.get(k-1).toString().substring(13, 15);
//				float time1 = Float.parseFloat(timehour1) + Float.parseFloat(timemin1)/60.0f;				
//				Log.d("date1: ", date1);
//				int position1 = 0;  
//				
//				for(int p1=0; p1<xlab.size(); p1++){
//					if(date1.equals(xlab.get(p1).toString())){
//						position1 = p1+1;
//						break;
//					}
//				}
//				
//				String date2 = xval.get(k).toString().substring(0, 10);
//				String timehour2 = xval.get(k).toString().substring(10, 12);
//				String timemin2 = xval.get(k).toString().substring(13, 15);
//				float time2 = Float.parseFloat(timehour2) + Float.parseFloat(timemin2)/60.0f;	
//				
//				int position2 = 0;  
//				
//				for(int p2=0; p2<xlab.size(); p2++){
//					if(date2.equals(xlab.get(p2).toString())){
//						position2 = p2+1;
//						break;
//					}
//				}
//				
//				canvas.drawLine(marginLeft + cWidth*position1 + time1*4 ,marginTop + h - (h-20)*vals[k-1]/500, marginLeft + cWidth*position2 + time2*4, marginTop + h - (h-20)*vals[k]/500, pen1);	
//			}
//		}
//		
//		
//		Paint pen2 = new Paint(Paint.ANTI_ALIAS_FLAG);
//
//		pen2.setColor(Color.YELLOW);
//		pen2.setStrokeWidth(3);
//		pen2.setStyle(Paint.Style.STROKE);
//
//		for(int m=1; m<vals.length+1; m++){	
//			
//			String date1 = xval.get(m-1).toString().substring(0, 10);
//			String timehour1 = xval.get(m-1).toString().substring(10, 12);
//			String timemin1 = xval.get(m-1).toString().substring(13, 15);
//			float time1 = Float.parseFloat(timehour1) + Float.parseFloat(timemin1)/60.0f;				
//			
//			int position1 = 0;  
//			
//			for(int p1=0; p1<xlab.size(); p1++){
//				if(date1.equals(xlab.get(p1).toString())){
//					position1 = p1+1;
//					break;
//				}
//			}
//			
//			canvas.drawCircle(marginLeft + cWidth*position1 + time1*4, marginTop + h - (h-20)*vals[m-1]/500, 5, pen2);	
//		}
//		
//		
//		Paint pen3 = new Paint(Paint.ANTI_ALIAS_FLAG);
//
//		pen3.setColor(Color.argb(70, 255, 0, 0));
//		pen2.setStrokeWidth(3);
//		pen2.setStyle(Paint.Style.STROKE);
//		
//		canvas.drawRect(marginLeft + 3, marginTop + h - 10*cHeight,  marginLeft + cWidth*xlab.size(), marginTop + h - (h-20)*maxSugar/500, pen3);
//		canvas.drawRect(marginLeft + 3, marginTop + h - 3,  marginLeft + cWidth*xlab.size(), marginTop + h - (h-20)*minSugar/500, pen3);
		

		// float border = 20;
		// float horstart = border * 2;
		// float height = getHeight();
		// float width = getWidth() - 1;
		// float max = getMax();
		// float min = getMin();
		// float diff = max - min;
		// float graphheight = height - (2 * border);
		// float graphwidth = width - (2 * border);
		//
		// paint.setTextAlign(Align.LEFT);
		// int vers = verlabels.length - 1;
		// for (int i = 0; i < verlabels.length; i++) {
		// paint.setColor(Color.DKGRAY);
		// float y = ((graphheight / vers) * i) + border;
		// canvas.drawLine(horstart, y, width, y, paint);
		// paint.setColor(Color.WHITE);
		// canvas.drawText(verlabels[i], 0, y, paint);
		// }
		// int hors = horlabels.length - 1;
		// for (int i = 0; i < horlabels.length; i++) {
		// paint.setColor(Color.DKGRAY);
		// float x = ((graphwidth / hors) * i) + horstart;
		// canvas.drawLine(x, height - border, x, border, paint);
		// paint.setTextAlign(Align.CENTER);
		// if (i==horlabels.length-1)
		// paint.setTextAlign(Align.RIGHT);
		// if (i==0)
		// paint.setTextAlign(Align.LEFT);
		// paint.setColor(Color.WHITE);
		// canvas.drawText(horlabels[i], x, height - 4, paint);
		// }
		//
		// paint.setTextAlign(Align.CENTER);
		// canvas.drawText(title, (graphwidth / 2) + horstart, border - 4,
		// paint);
		//
		// if (max != min) {
		// paint.setColor(Color.LTGRAY);
		// if (type == BAR) {
		// float datalength = values.length;
		// float colwidth = (width - (2 * border)) / datalength;
		// for (int i = 0; i < values.length; i++) {
		// float val = values[i] - min;
		// float rat = val / diff;
		// float h = graphheight * rat;
		// canvas.drawRect((i * colwidth) + horstart, (border - h) +
		// graphheight, ((i * colwidth) + horstart) + (colwidth - 1), height -
		// (border - 1), paint);
		// }
		// } else {
		// float datalength = values.length;
		// float colwidth = (width - (2 * border)) / datalength;
		// float halfcol = colwidth / 2;
		// float lasth = 0;
		// for (int i = 0; i < values.length; i++) {
		// float val = values[i] - min;
		// float rat = val / diff;
		// float h = graphheight * rat;
		// if (i > 0)
		// canvas.drawLine(((i - 1) * colwidth) + (horstart + 1) + halfcol,
		// (border - lasth) + graphheight, (i * colwidth) + (horstart + 1) +
		// halfcol, (border - h) + graphheight, paint);
		// lasth = h;
		// }
		// }
		// }
		//canvas.restore(); 
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
				tActivity3_3.graphView.invalidate();
				
			}else{
				
				width1 = width1 + 100;			
				tActivity3_3.graphView.invalidate();
				
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
