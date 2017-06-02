package app.android.test.june2nd;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

public class MySoundService extends Service {

    // 문자열만 가능 !!
    public static final String MESSAGE_KEY = "message_key";

    // 미디어 플레이어 변수 선언
    private MediaPlayer m_mediaPlayer;


    // 기본 생성자
    public MySoundService() {

    }

    // 서비스 클래스에서의 main() 함수
    // 액티비티 클래스에서 startService() / stopService() 함수 실행시 실행됨
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.v("*MySoundService*", "onStartCommand() 실행");

        // activity 클래스에서 전달한 값을 확인
        boolean bVlaue = intent.getBooleanExtra(MySoundService.MESSAGE_KEY, false);

        if(bVlaue == true) {
            Log.v("*MySoundService*", "startService()가 호출 됨");
            Log.v("*MySoundService*", "MESSEGE_KEY : " + bVlaue);

            // 음악 파일 객체 생성과 파일 재생 준비
            m_mediaPlayer = MediaPlayer.create(this, R.raw.city_of_stars);

            // start() 함수를 실행해서 음악 재생 시작
            m_mediaPlayer.start();

            Log.v("*MySoundService*", "음악 재생 시작");

        } /*else {
            Log.v("*MySoundService*", "startService()가 호출 됨");
            Log.v("*MySoundService*", "MESSEGE_KEY : " + bVlaue);

            // stop() 함수 실행
            m_mediaPlayer.stop();

            Log.v("*MySoundService*", "음악 재생 중지");
        }*/

        /*
            강사가 하려고 했던 위의 방식을 사용할 경우 startService() 함수로만 사용!
            값을 넘기는 내용으로 !!!
         */

        // 서비스가 완전히 종료됐을 때 반환하는 값
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.v("*MySoundService*", "onDestory()");

        /*
            테스트 결과, 액티비티 클래스에서 stopService() 실행 시 작동시킬 명령문은
            onDestroy() 함수에 적으면 됨 -> 이게 제일 나중에 실행되는 명령문이니까!!!

         */
        // 미디어 플레이어를 종료 후 release() 함수를 호출 해서 시스템 자원을 해제
        // 해제 안하면 미디어가 플레이 될 때 마다 새로운 객체가 생성됨
        // 멈출 때는 하기의 3가지 명령문을 작성해서 할것

        m_mediaPlayer.stop();
        m_mediaPlayer.release();
        m_mediaPlayer = null;

    }

    // 액티비티 클래스 & 서비스 클래스와 데이터를 주고 받을 떄 사용
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        // throw new UnsupportedOperationException("Not yet implemented");
        return null;
    }
}
