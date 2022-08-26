package org.trial.yu_ma.openstatusbar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity {

    //StatusBarManagerは非公開APIなのでリフレクションして使う
    public void openBar() {

            try {
                Object service = getSystemService("statusbar"); //※statusbar に赤い下線でエラーの様に見えるが無視
                Class<?> statusBarManager = Class.forName("android.app.StatusBarManager");
                Method expand = statusBarManager.getMethod("expandSettingsPanel");//※expandNotificationsPanelだと１段目（通知領域）までしか開かない
                expand.invoke(service);
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main); //※アプリ画面は必要ないのでコメントアウト（マニフェストファイルのandroid:themeも変更が必要）

        openBar();

        //Activity画面がない場合finishメソッドが無いと異常終了になる
        this.finish();

    }
}
