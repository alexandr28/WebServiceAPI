package com.acampdev.webserviceapi.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.acampdev.webserviceapi.Models.Film;
import com.acampdev.webserviceapi.R;

import java.util.List;

public class FilmsAdapter extends RecyclerView.Adapter<FilmsAdapter.ViewHolder> {
    List<Film> filmList;
    Context context;
    public FilmsAdapter(List<Film> filmList,Context context){
        this.filmList=filmList;
        this.context=context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.film,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        viewHolder.title.setText(filmList.get(position).getTitle());
        viewHolder.episode_id.setText(String.valueOf(filmList.get(position).getEpisode_id()));
        viewHolder.opening.setText(filmList.get(position).getOpening_crawl());
        viewHolder.director.setText(filmList.get(position).getDirector());
        viewHolder.produce.setText(filmList.get(position).getProducer());
        viewHolder.url.setText(filmList.get(position).getUrl());
        viewHolder.created.setText(filmList.get(position).getCreated());
        viewHolder.edited.setText(filmList.get(position).getEdited());
    }

    @Override
    public int getItemCount() {
        return filmList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title,episode_id,opening,director,produce,url,created,edited;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.title);
            episode_id=itemView.findViewById(R.id.episode_id);
            opening=itemView.findViewById(R.id.opening_crawl);
            director=itemView.findViewById(R.id.director);
            produce=itemView.findViewById(R.id.producer);
            url=itemView.findViewById(R.id.url);
            created=itemView.findViewById(R.id.created);
            edited=itemView.findViewById(R.id.edited);
        }
    }
}
