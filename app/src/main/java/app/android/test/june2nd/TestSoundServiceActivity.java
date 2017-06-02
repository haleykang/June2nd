package app.android.test.june2nd;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TestSoundServiceActivity extends AppCompatActivity {

    // 1. 전역 변수
    private TextView m_titleSoundService;
    private Button m_startSoundServiceBt, m_endSoundServiceBt;

    private Intent m_intent;

    private MyMethodClass mCls = new MyMethodClass();


    // 2. 재정의 함수
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_sound_service);
        myLog("onCreate() 시작");

        this.m_titleSoundService = (TextView)this.findViewById(R.id.titleSoundService);
        this.m_startSoundServiceBt = (Button)this.findViewById(R.id.startSoundServiceBt);
        this.m_endSoundServiceBt = (Button)this.findViewById(R.id.endSoundServiceBt);

        // this : 이 클래스에서, MySoundService.class : 이 클래스로 데이터 전달 !!!
        // 암튼 데이터 & 정보 & 이동 방향이 this -> MySoundService.class로 이동
        this.m_intent = new Intent(this, MySoundService.class);


        // 시작 버튼 클릭
        this.m_startSoundServiceBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myLog("시작 버튼 클릭");

                // putExtra() : 서비스 클래스의 onStartCommand() 함수로 true 값을 전달
                m_intent.putExtra(MySoundService.MESSAGE_KEY, true);

                // startService() : 서비스 클래스 실행 시작
                startService(m_intent);

                // 사용자가 음악 재생을 중지한 후에 시작할 수 있도록 버튼 클릭을 비활성화
                m_startSoundServiceBt.setClickable(false);
                m_endSoundServiceBt.setClickable(true);

            } // end of onClick()

        });  // end of m_startSoundServiceBt

        // 종료 버튼 클릭
        this.m_endSoundServiceBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myLog("종료 버튼 클릭");

                // putExtra() : 서비스 클래스의 onStartCommand() 함수로 false 값을 전달
                //m_intent.putExtra(MySoundService.MESSAGE_KEY, false);

                // stopService() : 서비스 중지
                stopService(m_intent);

                // 사용자가 시작 버튼 활성화, 중지 버튼 비활성화
                m_startSoundServiceBt.setClickable(true);
                m_endSoundServiceBt.setClickable(false);

                // 선생이 stopService 하라니까 안됨
                // startService 하니까 되는데.. 뭐징
                //startService(m_intent);
                // stopService 하는 경우 onDestroy() 함수가 실행되는듯..? 해보자
                // 된다 !!!


                /*
                    최종 정리
                    stopService()를 사용할 경우 putExtra() 함수를 써서 값을 넘기지 않아도 됨
                    stopService()는 말 그대로 서비스 클래스를 종료시키는 함수!
                    stopService() - 서비스 클래스의 onDestory() 함수 연결

                    startService() - 서비스 클래스의 onStartCommand()와 연결
                    onStartCommand() 함수는 액티비티 클래스의 onCreate() 같이 서비스 클래스가 시작될 때
                    제일 먼저 실행되는 명령문 인듯
                    따라서 putExtra() 함수를 사용해서 값을 넘기면 그걸 활용해서 작동시킬 명령문을
                    쓸 수 있음

                 */


            } // end of onClick()

        }); // end of m_endSoundServiceBt


    } // end of onCreate();

    // 3. 사용자 정의 함수
    public void myLog(String ob) {
        Log.v("*TestSoundService*", ob);
    }


} // end of TestSoundServiceActiviy
