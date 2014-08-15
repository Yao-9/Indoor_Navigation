package indoor_navigation_test;

import android.test.InstrumentationTestCase;

import com.zbar.lib.util.HTTPUtil;

import org.json.JSONObject;

import java.util.HashMap;

public class HTTPUtilTest extends InstrumentationTestCase {
    HTTPUtil testedObject = new HTTPUtil();

    public void test_PostData() throws Exception {
        testedObject.setJSONUpload(packAsJSON("roomNum", "4250", "qr", "55203"));
        testedObject.setIsJSONUploadValid(true);
        boolean res = testedObject.postURL("http://warm-headland-2583.herokuapp.com/");
        assertEquals(res, true);
    }

    public JSONObject packAsJSON(String key1, String value1, String key2, String value2) {
        HashMap<String, String> res = new HashMap<String, String>();
        res.put(key1, value1);
        res.put(key2, value2);
        return new JSONObject(res);
    }
}
