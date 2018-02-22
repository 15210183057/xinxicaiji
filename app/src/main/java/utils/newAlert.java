package utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.a123456.zhonggu.FrameActivity;
import com.example.a123456.zhonggu.R;

/**
 * Created by 123456 on 2018/2/13.
 */

public class newAlert extends AlertDialog{
    private Button button1,button2;
    private int i;
    public newAlert(Context context,int i) {
        super(context);
        this.i=i;
        setCanceledOnTouchOutside(false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myxiajiadialog);
        button1=findViewById(R.id.btn_ok);
        button2=findViewById(R.id.btn_canle);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setAction("deleteitem");
                intent.putExtra("i",""+i);
                getContext().sendBroadcast(intent);
                dismiss();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }
}
