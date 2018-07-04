package com.example.nttr.multitext_test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    String crlf = "";   // 改行
    boolean boolIsLong = false; // 出力行の長短切り替え
    final String strPrompt = ">> ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        crlf = System.getProperty("line.separator");    // 改行の取得

        // テキストを１行追加
        Button btnAddText = (Button) findViewById(R.id.btnAddText);
        btnAddText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // テキスト追記後(onClickメソッド終了後)、自動スクロール
                // https://groups.google.com/forum/#!search/scrollview$20runnable/androidbrasil-dev/urxfE2XTetM/x_XXhsjjBwAJ
                final ScrollView scrollView = (ScrollView) findViewById(R.id.scrollView);
                scrollView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        scrollView.fullScroll(ScrollView.FOCUS_DOWN);
                    }
                }, 100);

                TextView textMultiLine = (TextView) findViewById(R.id.textMultiLine);
                //String curText = "";
                String curText = (String) textMultiLine.getText();
                // 空でなければ改行追加
                if (!curText.equals("")) {
                    curText += crlf; // 改行追加
                }
                // テキストの追加
                // 長短交互に出力
                if (boolIsLong) {
                    textMultiLine.setText(curText + strPrompt + "これはとてもとてもとてもとてもとてもとてもとてもとてもとてもとても長い１行分のテキストです。");
                    //textMultiLine.append((String)(crlf + strPrompt + "これはとてもとてもとてもとてもとてもとてもとてもとてもとてもとても長い１行分のテキストです。"));
                } else {
                    textMultiLine.setText(curText + strPrompt + "これは１行分のテキストです。");
                    //textMultiLine.append((String)(crlf + strPrompt + "これは１行分のテキストです。"));
                }

                // 交互のフラグ
                boolIsLong = !boolIsLong;
                //ScrollView scrollView = (ScrollView) findViewById(R.id.scrollView);

                //scrollView.fullScroll(View.FOCUS_DOWN);

//                curText = (String) textMultiLine.getText();
//                textMultiLine.setText(curText + crlf);

            }
        });

        // テキストをクリア
        Button btnClear = (Button) findViewById(R.id.btnClear);
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView textMultiLine = (TextView) findViewById(R.id.textMultiLine);
                textMultiLine.setText("");
            }
        });

    }

}

// 複数行テキストを縦スクロールバー付で表示することをテストするアプリ
// 2017/12/13 Ver0.00 tamano 新規

// スクロールバーの参考
// https://youkey.jimdo.com/android開発に役立つ小ネタ/textviewをスクロールさせる/
// →ScrollViewを使う
// スクロールバーの表示の参考
// http://piyopiyocs.blog115.fc2.com/blog-entry-180.html
// →fadeScrollbars のチェックを外す(false)
// 改行の参考
// https://detail.chiebukuro.yahoo.co.jp/qa/question_detail/q1112054071
// crlf = System.getProperty("line.separator");
