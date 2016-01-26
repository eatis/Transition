package com.doth.transition;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class CustomizedDialog extends DialogFragment {
    private View.OnClickListener okButtonClickListener = null;
    Dialog dialog;
    Long mInput = 0L;
    TextView tvInput;
    Button[] btNo = new Button[10];    //0～9までのボタン
    Button btClear;
    Button btOk;
    Button btClose;

    public static CustomizedDialog newInstance() {
        CustomizedDialog fragment = new CustomizedDialog();
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //XMLとの紐付け
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialog, null, false);
        tvInput = (TextView) view.findViewById(R.id.tv_input);
        btNo[0] = (Button) view.findViewById(R.id.bt_no0);
        btNo[1] = (Button) view.findViewById(R.id.bt_no1);
        btNo[2] = (Button) view.findViewById(R.id.bt_no2);
        btNo[3] = (Button) view.findViewById(R.id.bt_no3);
        btNo[4] = (Button) view.findViewById(R.id.bt_no4);
        btNo[5] = (Button) view.findViewById(R.id.bt_no5);
        btNo[6] = (Button) view.findViewById(R.id.bt_no6);
        btNo[7] = (Button) view.findViewById(R.id.bt_no7);
        btNo[8] = (Button) view.findViewById(R.id.bt_no8);
        btNo[9] = (Button) view.findViewById(R.id.bt_no9);
        btClear = (Button) view.findViewById(R.id.bt_clear);
        btOk = (Button) view.findViewById(R.id.bt_ok);
        btClose = (Button) view.findViewById(R.id.bt_close);

        //ダイアログの作成
        dialog = new Dialog(getActivity());
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        dialog.setContentView(view);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        //数字ボタン押下時の処理
        for (int i = 0; i < 10; i++) {
            btNo[i].setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    //入力された数値を元に一時的な値を取得
                    Long tmp = Long.parseLong(String.valueOf(mInput) + ((Button) v).getText().toString());
                    //一時的な値が1,000,000,000以上の場合はアラートを表示
                    if (tmp >= 1000000000) {
                        new AlertDialog.Builder(getActivity())
                                .setMessage(getText(R.string.err_msg))
                                .setPositiveButton(getText(R.string.ok), null)
                                .show();
                    } else {
                        mInput = tmp;
                        tvInput.setText(String.format("%1$,3d", mInput));
                    }
                    //OKボタンの活性化（mValueが0以外の場合のみ活性）
                    if (mInput == 0) {
                        btOk.setEnabled(false);
                    } else {
                        btOk.setEnabled(true);
                    }
                }
            });
        }

        //クリアボタン押下時の処理
        btClear.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mInput = 0L;
                tvInput.setText("");
                btOk.setEnabled(false);
            }
        });

        //クローズボタン押下時はダイアログを消す
        btClose.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        //OKボタンのリスナー
        btOk.setOnClickListener(okButtonClickListener);

        return dialog;
    }

    // ダイアログの横幅、高さ、表示位置を設定
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        lp.width = (int) (metrics.widthPixels * 0.8);//横幅を80%
        //lp.height = (int) (metrics.heightPixels * 0.8);//高さを80%
        //lp.x = 100; //表示位置を指定した分、右へ移動
        //lp.y = 200; //表示位置を指定した分、下へ移動
        dialog.getWindow().setAttributes(lp);
    }

    public void setOnOkButtonClickListener(View.OnClickListener listener) {
        this.okButtonClickListener = listener;
    }

    //ダイアログ内の値を返すメソッド
    public Long getInputValue() {
        return mInput;
    }
}