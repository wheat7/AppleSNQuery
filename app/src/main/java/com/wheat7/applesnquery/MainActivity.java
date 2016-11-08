package com.wheat7.applesnquery;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;


import com.show.api.ShowApiRequest;


import org.json.JSONObject;

import java.util.ArrayList;

import java.util.List;

public class MainActivity extends Activity {

    private EditText editText;

    private Button queryButton;

    private List<Result> resultList = new ArrayList<>();

    protected Handler mHandler =  new Handler();


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);


        editText = (EditText) findViewById(R.id.edit_text);
        queryButton = (Button) this.findViewById(R.id.query_button);
        queryButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

                final String sn = editText.getText().toString().toUpperCase();

                new Thread() {
                    //在新线程中发送网络请求
                    public void run() {
                        String appid = "26831";//要替换成自己的
                        String secret = "56f7cbb14e594f949e403e2c836c4a55";//要替换成自己的
                        String res = new ShowApiRequest(" http://route.showapi.com/864-1", appid, secret)
                                .addTextPara("sn", sn)
                                .post();
                        parseJOSN(res);
                        mHandler.post(new Thread(){
                            public void run(){
                                ListView listView = (ListView) findViewById(R.id.list_view);
                                ResultAdapter adapter = new ResultAdapter(MainActivity.this, R.layout.result_dispaly, resultList);
                                listView.setAdapter(adapter);
                            }
                        });
                    }
                }.start();

            }
        });
    }

    private void parseJOSN(String jsonData) {
        try {
            JSONObject jsonObjectResBody = new JSONObject(jsonData);
            String Body = jsonObjectResBody.getString("showapi_res_body");
            JSONObject jsonObject = new JSONObject(Body);
            String phone_model = jsonObject.getString("phone_model");
            Result phoneModel = new Result("设备型号", phone_model);
            resultList.add(phoneModel);
            //Log.d("m", phone_model);
            String made_area = jsonObject.getString("made_area");
            Result madeArea = new Result("生产地点", made_area);
            resultList.add(madeArea);
            //Log.d("m", made_area);
            String color = jsonObject.getString("color");
            Result Color = new Result("颜色", color);
            resultList.add(Color);
            String active = jsonObject.getString("active");
            Result Active = new Result("激活状态",active);
            resultList.add(Active);
            String serial_number = jsonObject.getString("serial_number");
            String start_date = jsonObject.getString("start_date");
            String end_date = jsonObject.getString("end_date");
            String size = jsonObject.getString("size");
            Result Size = new Result("内存大小",size);
            resultList.add(Size);
            String tele_support = jsonObject.getString("tele_support");
            String tele_support_status = jsonObject.getString("tele_support_status");
            String warranty = jsonObject.getString("warranty");
            String warranty_status = jsonObject.getString("warranty_status");
            Result warrantyStatus = new Result("是否过保",warranty_status);
            resultList.add(warrantyStatus);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
