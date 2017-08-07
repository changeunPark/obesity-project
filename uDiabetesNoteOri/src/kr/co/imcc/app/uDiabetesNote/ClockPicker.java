/*
 * Copyright (C) 2008 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package kr.co.imcc.app.uDiabetesNote;

import android.app.*;
import android.content.*;
import android.os.*;
import android.view.*;
import android.widget.*;

/**
 * Clock face picker for the Alarm Clock application.
 */
public class ClockPicker extends Activity implements
        AdapterView.OnItemSelectedListener, AdapterView.OnItemClickListener {

    private LayoutInflater mFactory;
    private Gallery mGallery;

    private SharedPreferences mPrefs;
    private View mClock;
    private ViewGroup mClockLayout;
    private int mPosition;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        mFactory = LayoutInflater.from(this);
        setContentView(R.layout.clockpicker);

        mGallery = (Gallery) findViewById(R.id.gallery);
        mGallery.setAdapter(new ClockAdapter());
        mGallery.setOnItemSelectedListener(this);
        mGallery.setOnItemClickListener(this);

        mPrefs = getSharedPreferences(AlarmClock.PREFERENCES, 0);
        int face = mPrefs.getInt(AlarmClock.PREF_CLOCK_FACE, 0);
        if (face < 0 || face >= AlarmClock.CLOCKS.length) face = 0;

        mClockLayout = (ViewGroup) findViewById(R.id.clock_layout);
        mClockLayout.setOnClickListener(new View.OnClickListener() {
                @Override
				public void onClick(View v) {
                    selectClock(mPosition);
                }
        });

        mGallery.setSelection(face, false);
    }

    @Override
	public void onItemSelected(AdapterView parent, View v, int position, long id) {
        if (mClock != null) {
            mClockLayout.removeView(mClock);
        }
        mClock = mFactory.inflate(AlarmClock.CLOCKS[position], null);
        mClockLayout.addView(mClock, 0);
        mPosition = position;
    }

    @Override
	public void onItemClick(AdapterView parent, View v, int position, long id) {
        selectClock(position);
    }

    private synchronized void selectClock(int position) {
        SharedPreferences.Editor ed = mPrefs.edit();
        ed.putInt(AlarmClock.PREF_CLOCK_FACE, position);
        ed.commit();

        setResult(RESULT_OK);
        finish();
    }

    @Override
	public void onNothingSelected(AdapterView parent) {
    }

    class ClockAdapter extends BaseAdapter {

        public ClockAdapter() {
        }

        @Override
		public int getCount() {
            return AlarmClock.CLOCKS.length;
        }

        @Override
		public Object getItem(int position) {
            return position;
        }

        @Override
		public long getItemId(int position) {
            return position;
        }

        @Override
		public View getView(final int position, View convertView, ViewGroup parent) {
            View clock = mFactory.inflate(AlarmClock.CLOCKS[position], null);
            return clock;
        }

    }
}


