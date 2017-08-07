package kr.co.imcc.app.uDiabetesNote;

import android.app.*;
import android.content.*;
import android.database.*;
import android.database.sqlite.*;
import android.os.*;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.*;

public class tActivity3_4 extends Activity {
    /** Called when the activity is first created. */
	
	DBHelpepr dbHelpepr = null;
	public static SQLiteDatabase sqLiteDatabase = null;
	
	Cursor cursorStandardValue;
	
	Cursor cursorStandardValue1;
	
	String sqlDeletePersonalInfo= "";
	
	EditText etMax;
	EditText etMin;
	
    private ActionBar actionBar;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.config_2);
    
        actionBar = getActionBar();
		actionBar.setTitle("혈당관리 목표치 설정");
		getActionBar().setDisplayHomeAsUpEnabled(true); 
	
        
        dbHelpepr = new DBHelpepr(this);
		sqLiteDatabase = dbHelpepr.getWritableDatabase();		
		
		etMax = (EditText) findViewById(R.id.edittext_config_2_max);
		etMin = (EditText) findViewById(R.id.edittext_config_2_min);
        
        /*Button button1 = (Button) findViewById(R.id.button_config_2_back);

		button1.setOnClickListener(new OnClickListener() { // back

			@Override
			public void onClick(View v) {

				onBackPressed();

			}
		});*/
		
		ImageButton btSave = (ImageButton) findViewById(R.id.button_config_2_save);
		
		btSave.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				if(etMax.getText().toString().equals("")){
					
/*//					Toast.makeText(getApplicationContext(), "�׸��� ��� �Է��� �ּ���.", Toast.LENGTH_SHORT).show();
					AlertDialog alert = new AlertDialog.Builder(tActivity1_5.this)					
					.setTitle("�˸�")
					.setMessage("�����ִ�ġ�� �Էµ��� �ʾҽ��ϴ�.")
					.setPositiveButton( "Ȯ��", new DialogInterface.OnClickListener()
					{
						@Override
						public void onClick(DialogInterface dialog, int which)
						{
							dialog.dismiss();
						}
					})
					.show();
					*/
					
					Toast.makeText(getApplicationContext(), "혈당 최대치를 설정해주세요.", Toast.LENGTH_SHORT).show();

				}else if(etMin.getText().toString().equals("")){
					/*
					AlertDialog alert = new AlertDialog.Builder(tActivity1_5.this)					
					.setTitle("�˸�")
					.setMessage("�����ּ�ġ�� �Էµ��� �ʾҽ��ϴ�.")
					.setPositiveButton( "Ȯ��", new DialogInterface.OnClickListener()
					{
						@Override
						public void onClick(DialogInterface dialog, int which)
						{
							dialog.dismiss();
						}
					})
					.show();	*/
					
					Toast.makeText(getApplicationContext(), "혈당 최소치를 설정해주세요.", Toast.LENGTH_SHORT).show();

				}
				
				else
				{									
					String sqlStandardValue = "select * from STANDARDVALUE";
					cursorStandardValue1 = sqLiteDatabase.rawQuery(sqlStandardValue, null);
					cursorStandardValue1.moveToFirst();		
									
					String _id = cursorStandardValue1.getString(0);
					
					try {
						updateDB(_id, etMax.getText().toString(), etMin.getText().toString());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Toast.makeText(getApplicationContext(), "값이 저장되었습니다.", Toast.LENGTH_SHORT).show();
					/*
					else
					{ 
						Toast.makeText(getApplicationContext(), "����� ������ ���� ����� �ּ���.", Toast.LENGTH_SHORT).show();
					}
					*/	
				}						
			}
		});
		
		/*Button btDelete = (Button) findViewById(R.id.button_config_2_delete);
		//������ư
		btDelete.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {																
				String sqlStandardValue = "select * from STANDARDVALUE";
				cursorStandardValue1 = sqLiteDatabase.rawQuery(sqlStandardValue, null);
				cursorStandardValue1.moveToFirst();		
				
				if(cursorStandardValue1.getCount()>0){ //update
					
					String _id = cursorStandardValue1.getString(0);
					
					try {
						updateDB(_id, "150", "80");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}	
					etMax.setText("150");
					etMax.setSelection(etMax.getText().length());//Ŀ����ġ �ǵڷ�
					etMin.setText("80");
					etMin.setSelection(etMin.getText().length());//Ŀ����ġ �ǵڷ�
					Toast.makeText(getApplicationContext(), "�⺻������ �ǵ��Ƚ��ϴ�.", Toast.LENGTH_SHORT).show();
				}
				else
				{ //
//					Toast.makeText(getApplicationContext(), "��ϵ� ���� �ִ�ġ �� �ּ�ġ�� �����ϴ�.", Toast.LENGTH_SHORT).show();
				}				
			}
		});*/
		
		
		String sqlStandardValue = "select * from STANDARDVALUE";
		cursorStandardValue = sqLiteDatabase.rawQuery(sqlStandardValue, null);
		
		if(cursorStandardValue.getCount()>0)
		{ 		
			cursorStandardValue.moveToFirst();		
			
			etMax.setText(cursorStandardValue.getString(1));
			etMax.setSelection(etMax.getText().length());//Ŀ����ġ �ǵڷ�
			etMin.setText(cursorStandardValue.getString(2));
			etMin.setSelection(etMin.getText().length());//Ŀ����ġ �ǵڷ�
		}
    }
    
    public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	      case android.R.id.home:
	        onBackPressed();
	    }
	    return true;
	}	
    
    public void updateDB(String _id, String bloodsugar_target_max, String bloodsugar_target_min) throws Exception{	
		ContentValues cv = new ContentValues();
		cv.put("bloodsugar_target_max", bloodsugar_target_max);
		cv.put("bloodsugar_target_min", bloodsugar_target_min);
		
		sqLiteDatabase.update("STANDARDVALUE", cv, "_id=?", new String[] { _id });
	}
}


