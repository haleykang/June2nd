package app.android.test.june2nd;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    // 1. 전역 변수
    Intent m_intent;

    // 2. 재정의 함수
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    } // end of onCreate()

    // 3. 사용자 정의 함수
    // 3-1. onClick()
    public void onClick(View v) {

        switch(v.getId()) {
            case R.id.toTestSoundService:
                moveActivity(TestSoundServiceActivity.class);
                break;

        }

    } // end of onClick()

    // 3-2. Intent - startActivity 축약 함수
    public void moveActivity(Class<?> cls) {

        m_intent = new Intent(this, cls);
        startActivity(m_intent);

    } // end of moveActivity


} // end of MainActivity

