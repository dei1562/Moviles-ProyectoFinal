package com.example.proyectofinal;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    public List<User> userList = null;
    public Context context = null;

    public RecyclerViewAdapter(Context context, List<User> userList) {
        this.context = context;
        this.userList = userList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView id, name, uname, pass;
        CardView cardView;

        public ViewHolder(final View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.card_view);
            id = (TextView) itemView.findViewById(R.id.tv_id);
            name = (TextView) itemView.findViewById(R.id.tv_name);
            uname = (TextView) itemView.findViewById(R.id.tv_uname);
            pass = (TextView) itemView.findViewById(R.id.tv_pass);

            cardView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), ViewUser.class);
                    intent.putExtra(Config.BASE_ID, id.getText().toString());
                    v.getContext().startActivity(intent);
                }
            });
        }
    }

    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View childView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);
        return new ViewHolder(childView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String name = userList.get(position).getName();
        String id = userList.get(position).getId();
        String uname = userList.get(position).getUname();
        String pass = userList.get(position).getPass();
        holder.id.setText(id);
        holder.name.setText(name);
        holder.uname.setText(uname);
        holder.pass.setText(pass);
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }
}
