package kr.co.imcc.app.uDiabetesNote;

import android.app.*;
import android.content.*;
import android.database.*;
import android.database.sqlite.*;
import android.os.*;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.*;

public class tActivity3_2 extends Activity {
    /** Called when the activity is first created. */
	
	DBHelpepr dbHelpepr = null;
	public static SQLiteDatabase sqLiteDatabase = null;
	
	String sqlPersonalInfo= "";
	Cursor cursorStandardValue;
	
	String sqlPersonalInfo1= "";
	Cursor cursorPersonalInfo1;
	
	String sqlDeletePersonalInfo= "";
	
	EditText etMax;
	EditText etTime;
	
    private ActionBar actionBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.config_6);
        
        actionBar = getActionBar();
		actionBar.setTitle("운동량 목표치 설정");
		getActionBar().setDisplayHomeAsUpEnabled(true); 
        
        
        
        dbHelpepr = new DBHelpepr(this);
		sqLiteDatabase = dbHelpepr.getWritableDatabase();
		
		etMax = (EditText) findViewById(R.id.edittext_config_6_max);
		etTime = (EditText) findViewById(R.id.edittext_config_6_time);
		
       /* Button button1 = (Button) findViewById(R.id.button_config_6_back);

		button1.setOnClickListener(new OnClickListener() { // back

			@Override
			public void onClick(View v) 
			{
				onBackPressed();
			}
		});*/
		
		ImageButton btSave = (ImageButton) findViewById(R.id.button_input);
		
		btSave.setOnClickListener(new OnClickListener() 
		{		
			@Override
			public void onClick(View v) 
			{				
				if(etMax.getText().toString().equals(""))
				{
/*					//Toast.makeText(getApplicationContext(), "�׸��� ��� �Է��� �ּ���.", Toast.LENGTH_SHORT).show();
					AlertDialog alert = new AlertDialog.Builder(tActivity3_2.this)					
					.setTitle("�˸�")
					.setMessage("���ϸ�ǥĮ�θ��� �Էµ��� �ʾҽ��ϴ�")
					.setPositiveButton( "Ȯ��", new DialogInterface.OnClickListener()
					{
						@Override
						public void onClick(DialogInterface dialog, int which)
						{
							dialog.dismiss();
						}
					})
					.show();*/
					Toast.makeText(getApplicationContext(), "일일 목표 운동시간을 설정해주세요.", Toast.LENGTH_SHORT).show();

					
				}
				else if(etTime.getText().toString().equals(""))
				{
					/*//Toast.makeText(getApplicationContext(), "�׸��� ��� �Է��� �ּ���.", Toast.LENGTH_SHORT).show();
					AlertDialog alert = new AlertDialog.Builder(tActivity3_2.this)					
					.setTitle("�˸�")
					.setMessage("���ϸ�ǥĮ�θ��� �Էµ��� �ʾҽ��ϴ�")
					.setPositiveButton( "Ȯ��", new DialogInterface.OnClickListener()
					{
						@Override
						public void onClick(DialogInterface dialog, int which)
						{
							dialog.dismiss();
						}
					})
					.show();*/
					
					Toast.makeText(getApplicationContext(), "일일 목표 운동시간을 설정해주세요", Toast.LENGTH_SHORT).show();

				}
				else
				{						
					sqlPersonalInfo1 = "select * from STANDARDVALUE";
					cursorPersonalInfo1 = sqLiteDatabase.rawQuery(sqlPersonalInfo1, null);
					cursorPersonalInfo1.moveToFirst();		
					
					if(cursorPersonalInfo1.getCount()>0){ //update
						
						String _id = cursorPersonalInfo1.getString(0);
						
						try 
						{
							updateDB(_id, etMax.getText().toString(), etTime.getText().toString());
						} 
						catch (Exception e) 
						{
							// TODO Auto-generated catch block
							e.printStackTrace();
						}						
						Toast.makeText(getApplicationContext(), "값이 저장되었습니다.", Toast.LENGTH_SHORT).show();					
					}				
				}						
			}
		});
		
		/*Button btDelete = (Button) findViewById(R.id.button_config_6_delete);

		btDelete.setOnClickListener(new OnClickListener() { // delete

			@Override
			public void onClick(View v) 
			{										
				Toast.makeText(getApplicationContext(), "�����Ǿ����ϴ�.", Toast.LENGTH_SHORT).show();
				
				etMax.setText("");
				
				sqlPersonalInfo1 = "select _id ,bloodsugar_target_max, bloodsugar_target_min from STANDARDVALUE";
				cursorPersonalInfo1 = sqLiteDatabase.rawQuery(sqlPersonalInfo1, null);
				cursorPersonalInfo1.moveToFirst();		
				
				if(cursorPersonalInfo1.getCount()>0)//update
				{ 					
					String _id = cursorPersonalInfo1.getString(0);
					
					try 
					{
						updateDB(_id, etMax.getText().toString());
					} 
					catch (Exception e) 
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
				etMax.setText("");
				etTime.setText("");
				
				sqlPersonalInfo1 = "select * from STANDARDVALUE";
				cursorPersonalInfo1 = sqLiteDatabase.rawQuery(sqlPersonalInfo1, null);
				cursorPersonalInfo1.moveToFirst();		
				
				if(cursorPersonalInfo1.getCount()>0){ //update
					
					String _id = cursorPersonalInfo1.getString(0);
					
					try 
					{
						updateDB(_id, "0", "0");
					} 
					catch (Exception e) 
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}						
					Toast.makeText(getApplicationContext(), "�����Ǿ����ϴ�.", Toast.LENGTH_SHORT).show();								
				}
				else
				{ //
//					Toast.makeText(getApplicationContext(), "��ϵ� ���� �ִ�ġ �� �ּ�ġ�� �����ϴ�.", Toast.LENGTH_SHORT).show();
				}			
			}
		});
		*/
		
		sqlPersonalInfo = "select * from STANDARDVALUE";
		cursorStandardValue = sqLiteDatabase.rawQuery(sqlPersonalInfo, null);
		
		if(cursorStandardValue.getCount()>0)
		{ 			
			cursorStandardValue.moveToFirst();		
			
			etTime.setText(cursorStandardValue.getString(4));
			etTime.setSelection(etTime.getText().length());//Ŀ����ġ �ǵڷ�
			etMax.setText(cursorStandardValue.getString(3));
			etMax.setSelection(etMax.getText().length());//Ŀ����ġ �ǵڷ�
		}		
    }
    public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	      case android.R.id.home:
	        onBackPressed();
	    }
	    return true;
	}	
	
    public void updateDB(String _id, String calorie_standard, String extime_standard) throws Exception
    {		
		ContentValues cv = new ContentValues();
		
		cv.put("calorie_standard", calorie_standard);
		cv.put("extime_standard", extime_standard);
		
		sqLiteDatabase.update("STANDARDVALUE", cv, "_id=?", new String[] { _id });
//		Toast.makeText(getApplicationContext(), sqLiteDatabase.update("STANDARDVALUE", cv, "name=?", new String[] { name })+"update", Toast.LENGTH_SHORT).show();		
	}
}