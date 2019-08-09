package com.study.translatorgame;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class WordsAdapter extends RecyclerView.Adapter<WordsAdapter.WordsViewHolder> {
    private ArrayList<Word> words;
    private OnWordClickListener clickListener;

    public WordsAdapter(ArrayList<Word> words) {
        this.words = words;
    }

    public void setClickListener(OnWordClickListener clickListener) {
        this.clickListener = clickListener;
    }

    interface OnWordClickListener {
        void onWordClick(int position);
        void onLongClick(int position);
    }

    @NonNull
    @Override
    public WordsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.word_item, viewGroup, false);
        return new WordsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WordsViewHolder wordsViewHolder, int i) {
        Word word = words.get(i);
        wordsViewHolder.textViewTitle.setText(word.getName());
        wordsViewHolder.textViewDescription.setText(word.getTranslation());
    }

    @Override
    public int getItemCount() {
        return words.size();
    }

    class WordsViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewTitle;
        private TextView textViewDescription;

        public WordsViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewDescription = itemView.findViewById(R.id.textViewDescription);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (clickListener != null) clickListener.onWordClick(getAdapterPosition());
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (clickListener != null) clickListener.onLongClick(getAdapterPosition());
                    return true;
                }
            });
        }
    }
}
