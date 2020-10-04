// IT19170176
// FERNANDO W.N.D
// CarMart Notices

package com.example.vehicleappnotices;

import android.app.Activity;
import android.app.Instrumentation;

import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.assertNotNull;

public class AddnoticeTest {
    // IT19170176 - FERNANDO W.N.D -CarMart Notices
    @Rule
    //main activity is launched
    public ActivityTestRule<ShowRecords> mActivityTestRule= new ActivityTestRule<ShowRecords>(ShowRecords.class);
    private ShowRecords mActivity = null;
    Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(EditRecordActivity.class.getName(),null,false);


    @Before
    // IT19170176 - FERNANDO W.N.D -CarMart Notices
    public void setUp() throws Exception {
        mActivity = mActivityTestRule.getActivity();
    }
    @Test
    // IT19170176 - FERNANDO W.N.D -CarMart Notices -7
    public void testLaunchOfShowNoticesOnButtonClick(){
        assertNotNull(mActivity.findViewById(R.id.editBtn));
        onView(withId(R.id.editBtn)).perform(click());
        Activity secondActivity =getInstrumentation().waitForMonitorWithTimeout(monitor,50000000);

        assertNotNull(secondActivity);
        //secondActivity.finish();
    }






 /*   public void DeleteRecords() throws CursorIndexOutOfBoundsException{
        String id;
        DatabaseHelper ui1 = new DatabaseHelper(String);
        ui1.
        //create data
        String id = "1";
        boolean result = DatabaseHelper.deleteInfo( id);
        assertTrue(result);
    }
*/
    @After
    // IT19170176 - FERNANDO W.N.D -CarMart Notices
    public void tearDown() throws Exception {
        mActivity=null;
    }
}



