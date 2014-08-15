package indoor_navigation_test;

import android.test.ActivityInstrumentationTestCase2;

import com.zbar.lib.SendActivity;

import org.json.JSONObject;

public class SendActivityTest extends ActivityInstrumentationTestCase2<SendActivity> {

    private SendActivity testActivity;

    public SendActivityTest(String pkg, Class<SendActivity> activityClass) {
        super(pkg, activityClass);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        testActivity = getActivity();
    }

    public void test_packAsJSON() throws Exception {
        String key1 = "roomNumber";
        String value1 = "4250";
        String key2 = "QrCode";
        String value2 = "5578";
        JSONObject res = testActivity.packAsJSON(key1, value1, key2, value2);
        assertEquals(res.getString(key1), value1);
        assertEquals(res.getString(key2), value2);
    }
}
