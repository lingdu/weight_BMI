package com.example.weight_bmi;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnCalWeight:
                String sheight = this.getEditText(R.id.txtHeight);
                String sweight = this.getEditText(R.id.txtWeight);

                // 验证 输入的性别/身高 是否有效
                if (sheight == null || sheight.trim().length() == 0) {
                    Toast.makeText(this, R.string.height_hint, Toast.LENGTH_SHORT).show();
                    return;
                } else if (sweight == null || sweight.trim().length() == 0) {
                    sweight = "0";
                }

                // System.out.println(sex + " " + sheight + " " + sweight);

                int cm = 80;
                double cmPer = 0.7;
                RadioButton rbFemale = (RadioButton) findViewById(R.id.rbFemale);
                if (rbFemale.isChecked()) {
                    cm = 70;
                    cmPer = 0.6;
                }

                int height = Integer.parseInt(sheight);
                double weight = Double.parseDouble(sweight);

                // 计算标准体重
                double standWeight = (height - cm) * cmPer;
                double lowWeight = standWeight - standWeight * 0.1;
                double hightWeight = standWeight + standWeight * 0.1;

                TextView tvScope = (TextView) findViewById(R.id.tvScope);
                String tvtext = "标准体重:" + String.valueOf(standWeight) + "\n体重的合理范围:" + String.valueOf(lowWeight) + "~"
                        + String.valueOf(hightWeight);

                // 弹出体重的提示信息
                String text = "";
                int color = 0;
                if (weight >= lowWeight && weight <= hightWeight) {
                    text = "亲,是标准体重.继续保持哦...";
                    color = Color.GREEN;
                    showMessage(text);
                } else if (weight >= (standWeight + standWeight * 0.11)
                        && weight <= (standWeight + standWeight * 0.2)) {
                    text = "亲,体重过重...";
                    color = Color.YELLOW;
                    showMessage(text);
                } else if (weight <= (standWeight - standWeight * 0.11)
                        && weight >= (standWeight - standWeight * 0.20)) {
                    text = "亲,体重过轻...";
                    color = Color.YELLOW;
                    showMessage(text);
                } else if (weight > (standWeight + standWeight * 0.2)) {
                    text = "亲,肥胖...要减肥啦..";
                    color = Color.RED;
                    showMessage(text);
                } else if (weight < (standWeight - standWeight * 0.2)) {
                    text = "亲,太瘦...要多吃啦..";
                    color = Color.RED;
                    showMessage(text);
                }

                tvScope.setVisibility(View.VISIBLE);
                tvScope.setText(tvtext + "\n" + text);
                tvScope.setTextColor(color);
                break;
            case R.id.btnAbout:
                TextView tvAboutContent = (TextView) findViewById(R.id.tvAboutContent);
                if (tvAboutContent.getVisibility() == View.VISIBLE) {
                    // 设置控件隐藏
                    tvAboutContent.setVisibility(View.GONE);
                } else {
                    // 设置控件可见
                    tvAboutContent.setVisibility(View.VISIBLE);
                }
                break;
                
            case R.id.btnAuthor:
                TextView tvAuthorContent = (TextView) findViewById(R.id.tvAuthorContent);
                if (tvAuthorContent.getVisibility() == View.VISIBLE) {
                    // 设置控件隐藏
                	tvAuthorContent.setVisibility(View.GONE);
                } else {
                    // 设置控件可见
                	tvAuthorContent.setVisibility(View.VISIBLE);
                }
                break;
        }
    }

    /**
     * 获得文本框的值
     *
     * @param id
     * @return
     */
    private String getEditText(int id) {
        return ((EditText) findViewById(id)).getText().toString();
    }

    /**
     * 显示信息
     *
     * @param message
     */
    private void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
