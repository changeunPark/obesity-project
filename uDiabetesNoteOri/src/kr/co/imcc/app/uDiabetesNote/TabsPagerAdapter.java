package kr.co.imcc.app.uDiabetesNote;

import android.support.v4.app.*;

public class TabsPagerAdapter extends FragmentPagerAdapter {
 
    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }
 
    @Override
    public Fragment getItem(int index) {
 
        switch (index) {
        case 0:
            // Top Rated fragment activity
            return new Tab1();
        case 1:
            // Games fragment activity
            return new Tab2();
        case 2:
            // Movies fragment activity
            return new Tab3();
            
        case 3:
        	return new Tab4();
        }
 
        return null;
    }
 
    @Override
    public int getCount() {
        // get item count - equal to number of tabs
        return 4;
    }
 
}
