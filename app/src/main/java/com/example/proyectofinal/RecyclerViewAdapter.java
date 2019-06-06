package com.example.proyectofinal;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    public List<User> userList = null;
    public Context context = null;

    public RecyclerViewAdapter(Context context, List<User> userList) {
        this.context = context;
        this.userList = userList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView id, title, resumen, url, type, views;
        CardView cardView;
        ImageView image;

        public ViewHolder(final View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.card_view);
            id = (TextView) itemView.findViewById(R.id.tv_id);
            title = (TextView) itemView.findViewById(R.id.tv_title);
            resumen = (TextView) itemView.findViewById(R.id.tv_resumen);
            url = (TextView) itemView.findViewById(R.id.tv_url);
            type = (TextView) itemView.findViewById(R.id.tv_type);
            views = (TextView) itemView.findViewById(R.id.tv_views);
            image = (ImageView) itemView.findViewById(R.id.avatar);

            /*cardView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), ViewUser.class);
                    intent.putExtra(Config.BASE_ID, id.getText().toString());
                    v.getContext().startActivity(intent);
                }
            });*/
        }
    }

    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View childView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);
        return new ViewHolder(childView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String id = userList.get(position).getId();
        String title = userList.get(position).getTitle();
        String resumen = userList.get(position).getResumen();
        String url = userList.get(position).getUrl();
        String type = "Type: ".concat(userList.get(position).getType());
        String views = "Views: ".concat(userList.get(position).getViews());
        String avatar = userList.get(position).getAvatar();
        holder.id.setText(id);
        holder.title.setText(title);
        holder.resumen.setText(resumen);
        holder.url.setText(url);
        holder.type.setText(type);
        holder.views.setText(views);

        Picasso.get().load(avatar).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }
}
