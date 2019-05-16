package com.example.cowrkstest.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.cowrkstest.R;
import com.example.cowrkstest.data.dataEnter;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class recyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private dataEnter dataHolder;

    public recyclerAdapter(dataEnter d) {
        dataHolder = d;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.recycler_item, parent, false);
        return new itemViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int position) {
        //Bind view with data
        itemViewHolder item = (itemViewHolder) viewHolder;
        item.name.setText(dataHolder.getPosts().get(position).getCreator().getName());
        item.company.setText(dataHolder.getPosts().get(position).getCreator().getCompany());
        item.time.setText(
                getDate(Long.parseLong(dataHolder.getPosts().get(position).getArticle().getTime()),
                        "dd/MM hh:mm")
        );
        item.desc.setText(dataHolder.getPosts().get(position).getArticle().getContent());

        Glide.with(item.profilePic.getContext())
                .load(dataHolder.getPosts().get(position).getCreator().getImageUrl())
                .placeholder(R.drawable.ic_person)
                .apply(RequestOptions.circleCropTransform())
                .into(item.profilePic);

        if (dataHolder.getPosts().get(position).getArticle().getImageUrl() == null) {
            item.image.setVisibility(View.GONE);
        } else {
            Glide.with(item.image.getContext())
                    .load(dataHolder.getPosts().get(position).getArticle().getImageUrl())
                    .placeholder(R.drawable.ic_person)
                    .into(item.image);
        }
    }

    @Override
    public int getItemCount() {
        return dataHolder.getPosts().size();
    }

    class itemViewHolder extends RecyclerView.ViewHolder {

        ImageView profilePic;
        ImageView image;
        TextView name;
        TextView company;
        TextView time;
        TextView desc;

        public itemViewHolder(@NonNull View itemView) {
            super(itemView);
            profilePic = (ImageView) itemView.findViewById(R.id.profile_pic);
            image = (ImageView) itemView.findViewById(R.id.image);
            name = (TextView) itemView.findViewById(R.id.name);
            company = (TextView) itemView.findViewById(R.id.company);
            time = (TextView) itemView.findViewById(R.id.time);
            desc = (TextView) itemView.findViewById(R.id.desc);
        }
    }

    private String getDate(long milliSeconds, String dateFormat) {
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return formatter.format(calendar.getTime());
    }

}