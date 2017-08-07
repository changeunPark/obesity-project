package kr.co.imcc.app.uDiabetesNote;

import android.app.*;
import android.content.*;
import android.database.*;
import android.database.sqlite.*;
import android.os.*;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.*;

public class ActivityConfig_11 extends Activity {
    /** Called when the activity is first created. */
	
	DBHelpepr dbHelpepr = null;
	public static SQLiteDatabase sqLiteDatabase = null;
	
	Cursor cursorStandardValue;
	
	Cursor cursorStandardValue1;
	
	String sqlDeletePersonalInfo= "";
	
	EditText etMax;
	EditText etMin;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.config_2);
        
        dbHelpepr = new DBHelpepr(this);
		sqLiteDatabase = dbHelpepr.getWritableDatabase();		
		
		etMax = (EditText) findViewById(R.id.edittext_config_2_max);
		etMin = (EditText) findViewById(R.id.edittext_config_2_min);
        
//		�ּ�ó���ϴ� ������
        /*Button button1 = (Button) findViewById(R.id.button_config_2_back);

		button1.setOnClickListener(new OnClickListener() { // back

			@Override
			public void onClick(View v) {

				onBackPressed();

			}
		});*/
		
		Button btSave = (Button) findViewById(R.id.button_config_2_save);
		
		btSave.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				if(etMax.getText().toString().equals("")){
//					Toast.makeText(getApplicationContext(), "�׸��� ��� �Է��� �ּ���.", Toast.LENGTH_SHORT).show();
					AlertDialog alert = new AlertDialog.Builder(ActivityConfig_11.this)					
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
				}else if(etMin.getText().toString().equals("")){
					
					AlertDialog alert = new AlertDialog.Builder(ActivityConfig_11.this)					
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
					.show();					
				}
				else if(Integer.parseInt(etMax.getText().toString()) <= Integer.parseInt(etMin.getText().toString())){
//					Toast.makeText(getApplicationContext(), "�ִ�ġ�� �ּ�ġ���� Ŀ���մϴ�.", Toast.LENGTH_SHORT).show();
					AlertDialog alert = new AlertDialog.Builder(ActivityConfig_11.this)					
					.setTitle("�˸�")
					.setMessage("�����ִ�ġ�� �ּ�ġ���� Ŀ���մϴ�.")
					.setPositiveButton( "Ȯ��", new DialogInterface.OnClickListener()
					{
						@Override
						public void onClick(DialogInterface dialog, int which)
						{
							dialog.dismiss();
						}
					})
					.show();
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
					Toast.makeText(getApplicationContext(), "����Ǿ����ϴ�.", Toast.LENGTH_SHORT).show();
					/*
					else
					{ 
						Toast.makeText(getApplicationContext(), "����� ������ ���� ����� �ּ���.", Toast.LENGTH_SHORT).show();
					}
					*/	
				}						
			}
		});
		
//		Button btDelete = (Button) findViewById(R.id.button_config_2_delete);
		//������ư
		/*btDelete.setOnClickListener(new OnClickListener() {
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
    
    public void updateDB(String _id, String bloodsugar_target_max, String bloodsugar_target_min) throws Exception{	
		ContentValues cv = new ContentValues();
//		cv.put("name", name);
//		cv.put("sex", sex);
//		cv.put("age", age);
//		cv.put("smoke", smoke);
//		cv.put("diabetes", diabetes);
		cv.put("bloodsugar_target_max", bloodsugar_target_max);
		cv.put("bloodsugar_target_min", bloodsugar_target_min);
		
		sqLiteDatabase.update("STANDARDVALUE", cv, "_id=?", new String[] { _id });
//		Toast.makeText(getApplicationContext(), sqLiteDatabase.update("PERSONALINFO", cv, "name=?", new String[] { name })+"update", Toast.LENGTH_SHORT).show();		
	}
}

