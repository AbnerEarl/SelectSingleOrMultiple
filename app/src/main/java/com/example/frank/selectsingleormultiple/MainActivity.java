package com.example.frank.selectsingleormultiple;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private String[]provinces=new String[]{"湖北省","江苏省","浙江省","辽宁省","福建省","恩施州"};
    private ButtonOnClick buttonOnClick=new ButtonOnClick(1);
    private ListView lv=null;

    private void showListDialog(){
        new AlertDialog.Builder(this).setTitle("选择省份").setItems(provinces, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                final AlertDialog alertDialog=new AlertDialog.Builder(MainActivity.this).setMessage("您已经选择了："+which+"："+provinces[which]).show();
                android.os.Handler handler=new android.os.Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        alertDialog.dismiss();
                    }
                },5*1000);
            }
        }).show();

    }

    private class ButtonOnClick implements DialogInterface.OnClickListener{
        private int index;

        public ButtonOnClick(int index){
            this.index=index;
        }

        @Override
        public void onClick(DialogInterface dialog, int which) {
            if (which>=0){
                index=which;
            }
            else {
                if (which==DialogInterface.BUTTON_POSITIVE){
                    final AlertDialog alertDialog= new AlertDialog.Builder(MainActivity.this).setMessage("您已经选择了："+index+":"+provinces[index]).show();
                    android.os.Handler handler=new android.os.Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            alertDialog.dismiss();
                        }
                    },5*1000);
                }
                else if (which==DialogInterface.BUTTON_NEGATIVE){
                    final AlertDialog alertDialog= new AlertDialog.Builder(MainActivity.this).setMessage("您什么都未选择！").show();
                    android.os.Handler handler=new android.os.Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            alertDialog.dismiss();
                        }
                    },5*1000);
                }
            }
        }

    }

    private void showSingleChoiceDialog(){
       new AlertDialog.Builder(this).setTitle("选择省份").setSingleChoiceItems(provinces,1,buttonOnClick).setPositiveButton("确定",buttonOnClick).setNegativeButton("取消",buttonOnClick).show();

    }

    private void showMultiChoiceDialog(){
        AlertDialog ad=new AlertDialog.Builder(this).setIcon(R.drawable.vv).setTitle("选择省份").setMultiChoiceItems(provinces, new boolean[]{false, true, false, true, false, false}, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {

            }
        }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int count=lv.getCount();
                String s="您已经选择了：";
                for (int i=0;i<provinces.length;i++){
                    if (lv.getCheckedItemPositions().get(i))
                        s+=i+"："+lv.getAdapter().getItem(i);
                }

                if (lv.getCheckedItemPositions().size()>0){
                    final AlertDialog alertDialog= new AlertDialog.Builder(MainActivity.this).setMessage(s).show();
                    android.os.Handler handler=new android.os.Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            alertDialog.dismiss();
                        }
                    },5*1000);
                }
                else {
                    final AlertDialog alertDialog=new AlertDialog.Builder(MainActivity.this).setMessage("您为选择任何省份！").show();
                    android.os.Handler handler=new android.os.Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            alertDialog.dismiss();
                        }
                    },5*1000);
                }
            }
        }).setNegativeButton("取消",null).create();

        lv=ad.getListView();
        ad.show();

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button one=(Button)findViewById(R.id.button);
        Button two=(Button)findViewById(R.id.button2);
        Button three=(Button)findViewById(R.id.button3);


        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button:{
                showListDialog();break;
            }
            case R.id.button2:{
                showSingleChoiceDialog();break;
            }
            case R.id.button3:{
                showMultiChoiceDialog();break;
            }
        }
    }
}
