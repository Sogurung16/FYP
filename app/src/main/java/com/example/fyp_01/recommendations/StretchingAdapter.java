package com.example.fyp_01.recommendations;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fyp_01.R;

import java.util.ArrayList;

public class StretchingAdapter extends RecyclerView.Adapter<StretchingAdapter.ViewHolder> {
    ArrayList<Model> models;
    Context context;
    StretchingAdapter.OnItemClickListener mListener;

    public StretchingAdapter(Context context, ArrayList<Model> models) {
        this.context = context;
        this.models = models;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Create View
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activities_stretching_item, parent, false);
        return new ViewHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Model model = models.get(position);

        holder.mTextView.setText(model.getActivitiesName());
        holder.mImageView.setImageBitmap(model.getActivitiesImage());
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(StretchingAdapter.OnItemClickListener listener){
        mListener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //Initialize Variables
        ImageView mImageView;
        TextView mTextView;

        public ViewHolder(@NonNull View itemView, final StretchingAdapter.OnItemClickListener listener) {
            super(itemView);
            //Assign Variable
            mImageView = itemView.findViewById(R.id.imageView);
            mTextView = itemView.findViewById(R.id.textView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        int position = getAdapterPosition();
                        if(position!=RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
