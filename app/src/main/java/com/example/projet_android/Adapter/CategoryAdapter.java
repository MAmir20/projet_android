package com.example.projet_android.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.projet_android.R;
import com.example.projet_android.room.Category;

import java.util.List;


public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    List<Category> categories;
    private OnItemClickListener onItemClickListener;

    // Define the interface for the click events
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    // Set the listener for the click events
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_category,parent,false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.categoryName.setText(categories.get(position).getName());
        String picUrl = "";
        switch (position) {
            case 0:
                picUrl = "vehicle";
                break;
            case 1:
                picUrl = "real_estate";
                break;
            case 2:
                    picUrl = "multimedia";
                break;
            case 3:
                picUrl = "home";
                break;
            case 4:
                picUrl = "clothes";
                break;
            case 5:
                picUrl = "hobbies";
                break;
            case 6:
                picUrl = "jobs";
                break;
            case 7:
                picUrl = "business";
                break;
            case 8:
                picUrl = "others";
                break;
        }

        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(picUrl, "drawable", holder.itemView.getContext().getPackageName());
        holder.categoryPic.setImageResource(drawableResourceId);

       /* Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.categoryPic);*/
    }
    @Override
    public int getItemCount() {
        return categories.size();
    }

    public CategoryAdapter(List<Category> categories) {

        this.categories = categories;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView categoryName;
        ImageView categoryPic;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryName = itemView.findViewById(R.id.categoryName);
            categoryPic = itemView.findViewById(R.id.categoryPic);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (onItemClickListener != null) {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    onItemClickListener.onItemClick(v, position);
                }
            }
        }
    }
}
