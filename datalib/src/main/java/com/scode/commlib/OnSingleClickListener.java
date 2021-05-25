package com.scode.commlib;

import android.os.SystemClock;
import android.view.View;

import java.util.concurrent.TimeUnit;

/***
 * 중복 클릭을 막아주는 클릭리스너
 * 기본 중복 클릭 거절 시간은 10초
 * 설정하고싶다면 생성자에서 Second를 넣으면 시간만큼 입력 거부
 */
public abstract class OnSingleClickListener implements View.OnClickListener {
    //중복클릭시간차이
    //마지막으로 클릭한 시간
    private long mLastClickTime;

    public abstract void onSingleClick(View v);

    private long interval = TimeUnit.SECONDS.toMillis(3);

    public OnSingleClickListener(){}
    public OnSingleClickListener(int sec)
    {
        interval = TimeUnit.SECONDS.toMillis(sec);
    }

    @Override
    public final void onClick(View v) {
        //현재 클릭한 시간
        long currentClickTime= SystemClock.uptimeMillis();
        //이전에 클릭한 시간과 현재시간의 차이
        long elapsedTime=currentClickTime-mLastClickTime;
        //마지막클릭시간 업데이트
        mLastClickTime=currentClickTime;

        //내가 정한 중복클릭시간 차이를 안넘었으면 클릭이벤트 발생못하게 return
        if(elapsedTime<=interval)
            return;
        //중복클릭시간 아니면 이벤트 발생
        onSingleClick(v);
    }
}
