package com.example.proyectofinal;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ViewAllUser extends AppCompatActivity {

    RecyclerViewAdapter adapter;
    List<User> userList;
    RecyclerView recyclerView;
    String JSON_STRING;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_all_user);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        getJson();
    }

    public void getJson() {
        class GetJson extends AsyncTask<Void, Void, String> {
            ProgressDialog progressDialog;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressDialog = progressDialog.show(ViewAllUser.this, "Obteniendo datos...", "Por favor espere...", false, false);
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendgetRequest(Config.URL_Display);
                return s;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                progressDialog.dismiss();
                JSON_STRING = s;
                showUser();
            }
        }

        GetJson gj = new GetJson();
        gj.execute();
    }

    private void showUser() {
        JSONObject jsonObject;
        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(Config.TAG_ARRAY);
            userList = new ArrayList<>();

            for (int i = 0; i < result.length(); i++){
                JSONObject j = result.getJSONObject(i);
                User user = new User();
                user.setId(j.optString(Config.TAG_ID));
                user.setName(j.optString(Config.TAG_NAME));
                user.setUname(j.optString(Config.TAG_UNAME));
                user.setPass(j.optString(Config.TAG_PASS));

                userList.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        adapter = new RecyclerViewAdapter(this, userList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
