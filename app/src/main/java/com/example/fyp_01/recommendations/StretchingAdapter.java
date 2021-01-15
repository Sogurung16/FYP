package com.example.fyp_01.recommendations;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fyp_01.R;
import com.example.fyp_01.database.DatabaseHelper;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class StretchingAdapter extends RecyclerView.Adapter<StretchingAdapter.ViewHolder> {
    ArrayList<StretchingModel> stretchingModels;
    Context context;

    public StretchingAdapter(Context context, ArrayList<StretchingModel> stretchingModels) {
        this.context = context;
        this.stretchingModels = stretchingModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Create View
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activities_stretching_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        StretchingModel stretchingModel = stretchingModels.get(position);

        holder.mTextView.setText(stretchingModel.getStretchingActivitiesName());
        holder.mImageView.setImageBitmap(stretchingModel.getStretchingActivitiesImage());
    }

    @Override
    public int getItemCount() {
        return stretchingModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //Initialize Variables
        ImageView mImageView;
        TextView mTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //Assign Variable
            mImageView = itemView.findViewById(R.id.imageView);
            mTextView = itemView.findViewById(R.id.textView);
        }
    }
}
