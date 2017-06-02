package app.android.test.june2nd;

import android.app.Activity;
import android.widget.Toast;

/**
 * Created by 202-18 on 2017-06-02.
 */

public class MyMethodClass extends Activity {

    // 각종 함수
    public void sToast(String ob) {

        Toast.makeText(this, ob, Toast.LENGTH_SHORT).show();
    }

    public void lToast(String ob) {

        Toast.makeText(this, ob, Toast.LENGTH_LONG).show();

    }


}
