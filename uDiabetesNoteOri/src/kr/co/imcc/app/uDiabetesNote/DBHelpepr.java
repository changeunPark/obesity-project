package kr.co.imcc.app.uDiabetesNote;

import android.content.*;
import android.database.sqlite.*;



public class DBHelpepr extends SQLiteOpenHelper {
	

	private final static String db_file_name = "blooddata.db";
	private final static int db_version = 1;

	public DBHelpepr(Context context) {
		super(context, db_file_name, null, db_version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

	}
	
	@Override
	public synchronized void close() {
		super.close();
	}
	
	@Override
	public void onOpen(SQLiteDatabase db) 
	{	
		super.onOpen(db);
		
//		db.execSQL("drop table if exists BLOODSUGAR");
		db.execSQL("create table if not exists BLOODSUGAR" + 
				"(_id integer primary key autoincrement," +
				"date varchar(60) not null,"+
				"time varchar(60) not null,"+
				"bloodsugar varchar(60) not null,"+
				"memo varchar(255),"+    
				"hospital varchar(60) not null)");
		
//		db.execSQL("drop table if exists BLOODSUGAR"); �̿ϱ�
		db.execSQL("create table if not exists BlOODPRESSUREBPD" + 
				"(_id integer primary key autoincrement," +
				"date varchar(60) not null,"+
				"time varchar(60) not null,"+
				"bpdiastolic varchar(60) not null,"+//�̿ϱ�
				"bpsystolic varchar(60) not null,"+//�����
				"memo varchar(255),"+    
				"hospital varchar(60) not null)");
		
//		db.execSQL("drop table if exists BLOODSUGAR");�����
//		db.execSQL("create table if not exists BlOODPRESSUREBPS" + 
//				"(_id integer primary key autoincrement," +
//				"date varchar(60) not null,"+
//				"time varchar(60) not null,"+				
//				"memo varchar(255),"+    
//				"hospital varchar(60) not null)");
		
//		db.execSQL("drop table if exists HBA1C");
		db.execSQL("create table if not exists HBA1C" + 
				"(_id integer primary key autoincrement," +
				"date varchar(60) not null,"+
				"time varchar(60) not null,"+
				"hb varchar(60) not null,"+
				"memo varchar(255),"+    
				"hospital varchar(60) not null)");
		
//		db.execSQL("drop table if exists PERSONALINFO");
		db.execSQL("create table if not exists PERSONALINFO" + 
				"(_id integer primary key autoincrement," +
				"name varchar(60) not null,"+
				"sex varchar(60) not null,"+
				"age varchar(60) not null,"+
				"smoke varchar(60),"+
				"diabetes varchar(60),"+
				"bloodsugar_target_max varchar(60),"+
				"bloodsugar_target_min varchar(60))");
		
//		db.execSQL("drop table if exists STANDARDVALUE");
		db.execSQL("create table if not exists STANDARDVALUE" + 
				"(_id integer primary key autoincrement," +				
				"bloodsugar_target_max varchar(60),"+
				"bloodsugar_target_min varchar(60),"+
				"calorie_standard varchar(60)," +
				"extime_standard varchar(60))");
		
//		db.execSQL("drop table if exists BEGININFO");
		db.execSQL("create table if not exists BEGININFO" + 
				"(firstdate varchar(60)," +
				"height varchar(60),"+
				"weight varchar(60),"+
				"bmi varchar(60),"+
				"waist varchar(60),"+
				"hba1c varchar(60),"+
				"bpd varchar(60),"+
				"bps varchar(60))");
		
//		db.execSQL("drop table if exists EXCERCISE");
		db.execSQL("create table if not exists EXCERCISE" + 
				"(_id integer primary key autoincrement," +
				"date varchar(60) not null,"+
				"time varchar(60) not null,"+
				"kind varchar(60) not null,"+
				"extime varchar(60) not null,"+
				"weight varchar(60) not null,"+
				"memo varchar(255),"+    
				"calorie varchar(60) not null)");
		
//		db.execSQL("drop table if exists DIABETESCHECKCYCLE");
		db.execSQL("create table if not exists DIABETESCHECKCYCLE" + 
				"(_id integer primary key autoincrement," +
				"lastcheckdate varchar(60) not null,"+
				"checkdate varchar(60) not null,"+
				"checknm varchar(60) not null,"+
				"checkcycle varchar(60) not null,"+	
				"hospital varchar(60) not null,"+	
				"alarm varchar(60) not null)");
		
		ContentValues cv = new ContentValues();
		
		cv.put("calorie_standard", "100");
		cv.put("extime_standard", "30");
		cv.put("bloodsugar_target_max", "150");
		cv.put("bloodsugar_target_min", "80");
		
		db.insert("STANDARDVALUE", "", cv);		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) 
	{
		db.execSQL("drop table if exists BLOODSUGAR");
		onCreate(db);
	}	
}