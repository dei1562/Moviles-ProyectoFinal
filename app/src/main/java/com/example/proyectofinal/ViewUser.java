package com.example.proyectofinal;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class ViewUser extends AppCompatActivity implements View.OnClickListener {

    String id, hold_name;

    EditText et_id, et_name, et_username, et_password;
    TextView tv_id, tv_name, tv_username, tv_password;

    Button btn_up, btn_delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_user);

        Intent intent = getIntent();
        id = intent.getStringExtra(Config.BASE_ID);

        et_id = (EditText) findViewById(R.id.et_id);
        tv_id = (TextView) findViewById(R.id.tv_id);

        et_id.setText(id);
        tv_id.setText(id);
        et_id.setEnabled(false);

        cast();
        register();
        getUser();
    }

    private void cast() {
        et_name = (EditText) findViewById(R.id.et_name);
        et_username = (EditText) findViewById(R.id.et_username);
        et_password = (EditText) findViewById(R.id.et_password);

        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_username = (TextView) findViewById(R.id.tv_username);
        tv_password = (TextView) findViewById(R.id.tv_password);
    }

    private void register() {
        btn_up.setOnClickListener(this);
        btn_delete.setOnClickListener(this);
    }

    private void getUser() {
        class GetUser extends AsyncTask<Void, Void, String> {
            ProgressDialog progressDialog;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressDialog = progressDialog.show(ViewUser.this, "Obteniendo datos...", "Por favor espere...", false, false);
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(Config.URL_GetUser, id);
                return s;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
                showUser(s);
            }
        }

        GetUser gu = new GetUser();
        gu.execute();
    }

    private void showUser(String json) {
        JSONObject jsonObject;
        try {
            jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray(Config.TAG_ARRAY);
            JSONObject js = result.getJSONObject(0);

            String name = js.getString(Config.TAG_NAME);
            String username = js.getString(Config.TAG_UNAME);
            String password = js.getString(Config.TAG_PASS);

            tv_name.setText(name);
            tv_username.setText(username);
            tv_password.setText(password);
            hold_name = tv_name.getText().toString();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {

    }
}
