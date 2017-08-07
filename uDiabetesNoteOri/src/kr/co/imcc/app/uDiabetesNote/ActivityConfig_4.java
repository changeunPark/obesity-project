package kr.co.imcc.app.uDiabetesNote;

import java.text.*;
import java.util.*;

import android.app.*;
import android.content.*;
import android.content.res.*;
import android.database.*;
import android.net.*;
import android.os.*;
import android.view.*;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnClickListener;
import android.widget.*;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;

public class ActivityConfig_4 extends Activity implements OnItemClickListener {
	/** Called when the activity is first created. */

	final static String PREFERENCES = "AlarmClock";
	final static String PREF_CLOCK_FACE = "face";
	final static String PREF_SHOW_CLOCK = "show_clock";

	/** Cap alarm count at this number */
	final static int MAX_ALARM_COUNT = 12;

	/**
	 * This must be false for production. If true, turns on logging, test code,
	 * etc.
	 */
	final static boolean DEBUG = false;

	private SharedPreferences mPrefs;
	private LayoutInflater mFactory;
	private ViewGroup mClockLayout;
	private View mClock = null;
	private ListView mAlarmsList;
	private Cursor mCursor;

	private String mAm, mPm;

	/**
	 * Which clock face to show
	 */
	private int mFace = -1;

	/*
	 * FIXME: it would be nice for this to live in an xml config file.
	 */
	final static int[] CLOCKS = { R.layout.clock_basic_bw,
			R.layout.clock_googly, R.layout.clock_droid2,
			R.layout.clock_droids, R.layout.digital_clock };

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// setContentView(R.layout.config_4);

		String[] ampm = new DateFormatSymbols().getAmPmStrings();
		mAm = ampm[0];
		mPm = ampm[1];

		mFactory = LayoutInflater.from(this);
		mPrefs = getSharedPreferences(PREFERENCES, 0);
		mCursor = Alarms.getAlarmsCursor(getContentResolver());

		updateLayout();

		Button button1 = (Button) findViewById(R.id.button_config_4_back);

		button1.setOnClickListener(new OnClickListener() { // back

			@Override
			public void onClick(View v) 
			{
				onBackPressed();
			}
		});

		Button addAlarm = (Button) findViewById(R.id.button_config_4_1);

		addAlarm.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Uri uri = Alarms.addAlarm(getContentResolver());
				// FIXME: scroll to new item?
				String segment = uri.getPathSegments().get(1);
				int newId = Integer.parseInt(segment);
				// if (Log.LOGV) {
				// Log.v("In AlarmClock, new alarm id = " + newId);
				// }
				// 
				
				Intent intent = new Intent(ActivityConfig_4.this,
						kr.co.imcc.app.uDiabetesNote.SetAlarm.class);
				intent.putExtra(Alarms.ALARM_ID, newId);
				startActivity(intent);
			}
		});

	}

	private void updateLayout() {
		setContentView(R.layout.config_4);
		mAlarmsList = (ListView) findViewById(R.id.alarms_list);		
		mAlarmsList.setAdapter(new AlarmTimeAdapter(this, mCursor));
		mAlarmsList.setVerticalScrollBarEnabled(true);
		mAlarmsList.setOnItemClickListener(this);
		mAlarmsList.setOnCreateContextMenuListener(this);

	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		updateLayout();
		inflateClock();
	}

	@Override
	protected void onResume() {
		super.onResume();

		int face = mPrefs.getInt(PREF_CLOCK_FACE, 0);
		if (mFace != face) {
			if (face < 0 || face >= AlarmClock.CLOCKS.length) {
				mFace = 0;
			} else {
				mFace = face;
			}
			inflateClock();
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		ToastMaster.cancelToast();
		mCursor.deactivate();
	}

	protected void inflateClock() {
		if (mClock != null) {
			mClockLayout.removeView(mClock);
		}

		LayoutInflater.from(this).inflate(CLOCKS[mFace], mClockLayout);
		mClock = findViewById(R.id.clock);

		TextView am = (TextView) findViewById(R.id.am);
		TextView pm = (TextView) findViewById(R.id.pm);

		if (am != null) {
			am.setText(mAm);
		}
		if (pm != null) {
			pm.setText(mPm);
		}
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View view,
			ContextMenuInfo menuInfo) {
		// Inflate the menu from xml.
		getMenuInflater().inflate(R.menu.context_menu, menu);

		// Use the current item to create a custom view for the header.
		final AdapterContextMenuInfo info = (AdapterContextMenuInfo) menuInfo;
		final Cursor c = (Cursor) mAlarmsList.getAdapter().getItem(
				info.position);
		final Alarm alarm = new Alarm(c);

		// Construct the Calendar to compute the time.
		final Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, alarm.hour);
		cal.set(Calendar.MINUTE, alarm.minutes);
		final String time = Alarms.formatTime(this, cal);

		// Inflate the custom view and set each TextView's text.
		final View v = mFactory.inflate(R.layout.context_menu_header, null);
		TextView textView = (TextView) v.findViewById(R.id.header_time);
		textView.setText(time);
		textView = (TextView) v.findViewById(R.id.header_label);
		textView.setText(alarm.label);

		// Set the custom view on the menu.
		menu.setHeaderView(v);
		// Change the text to "disable" if the alarm is already enabled.
		if (alarm.enabled) {
			menu.findItem(R.id.enable_alarm).setTitle(R.string.disable_alarm);
		}
	}

	@Override
	public void onItemClick(AdapterView parent, View v, int pos, long id) {
		Intent intent = new Intent(this, SetAlarm.class);
		intent.putExtra(Alarms.ALARM_ID, (int) id);
		startActivity(intent);
	}

	private class AlarmTimeAdapter extends CursorAdapter {
		public AlarmTimeAdapter(Context context, Cursor cursor) {
			super(context, cursor);
		}

		@Override
		public View newView(Context context, Cursor cursor, ViewGroup parent) {
			View ret = mFactory.inflate(R.layout.alarm_time, parent, false);

			((TextView) ret.findViewById(R.id.am)).setText(mAm);
			((TextView) ret.findViewById(R.id.pm)).setText(mPm);
			
			Log.d("AM", mAm);
			Log.d("PM", mPm);
			

			DigitalClock digitalClock = (DigitalClock) ret
					.findViewById(R.id.digitalClock);
			digitalClock.setLive(false);
			// if (Log.LOGV) Log.v("newView " + cursor.getPosition());
			return ret;
		}

		@Override
		public void bindView(View view, Context context, Cursor cursor) {
			final Alarm alarm = new Alarm(cursor);

			CheckBox onButton = (CheckBox) view.findViewById(R.id.alarmButton);
			onButton.setChecked(alarm.enabled);
			onButton.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					boolean isChecked = ((CheckBox) v).isChecked();
					Alarms.enableAlarm(ActivityConfig_4.this, alarm.id,
							isChecked);
					if (isChecked) {
						SetAlarm.popAlarmSetToast(ActivityConfig_4.this,
								alarm.hour, alarm.minutes, alarm.daysOfWeek);
					}
				}
			});

			DigitalClock digitalClock = (DigitalClock) view
					.findViewById(R.id.digitalClock);

			// set the alarm text
			final Calendar c = Calendar.getInstance();
			c.set(Calendar.HOUR_OF_DAY, alarm.hour);
			c.set(Calendar.MINUTE, alarm.minutes);
			digitalClock.updateTime(c);

			// Set the repeat text or leave it blank if it does not repeat.
			TextView daysOfWeekView = (TextView) digitalClock
					.findViewById(R.id.daysOfWeek);
			final String daysOfWeekStr = alarm.daysOfWeek.toString(
					ActivityConfig_4.this, false);
			if (daysOfWeekStr != null && daysOfWeekStr.length() != 0) {
				daysOfWeekView.setText(daysOfWeekStr);
				daysOfWeekView.setVisibility(View.VISIBLE);
			} else {
				daysOfWeekView.setVisibility(View.GONE);
			}

			// Display the label
			TextView labelView = (TextView) digitalClock
					.findViewById(R.id.label);
			if (alarm.label != null && alarm.label.length() != 0) {
				labelView.setText(alarm.label);
				labelView.setVisibility(View.VISIBLE);
			} else {
				labelView.setVisibility(View.GONE);
			}
		}
	};

	@Override
	public boolean onContextItemSelected(final MenuItem item) {
		final AdapterContextMenuInfo info = (AdapterContextMenuInfo) item
				.getMenuInfo();
		final int id = (int) info.id;
		switch (item.getItemId()) {
		case R.id.delete_alarm:
			// Confirm that the alarm will be deleted.
			new AlertDialog.Builder(this)
					.setTitle(getString(R.string.delete_alarm))
					.setMessage(getString(R.string.delete_alarm_confirm))
					.setPositiveButton(android.R.string.ok,
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface d, int w) {
									Alarms.deleteAlarm(ActivityConfig_4.this, id);
								}
							}).setNegativeButton(android.R.string.cancel, null)
					.show();
			return true;

		case R.id.enable_alarm:
			final Cursor c = (Cursor) mAlarmsList.getAdapter().getItem(
					info.position);
			final Alarm alarm = new Alarm(c);
			Alarms.enableAlarm(this, alarm.id, !alarm.enabled);
			if (!alarm.enabled) {
				SetAlarm.popAlarmSetToast(this, alarm.hour, alarm.minutes,
						alarm.daysOfWeek);
			}
			return true;

		default:
			break;
		}
		return super.onContextItemSelected(item);
	}
}