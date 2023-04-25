package com.example.projet_android.Adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.bumptech.glide.Glide;
import com.example.projet_android.R;
import com.example.projet_android.ShopActivity;
import com.example.projet_android.room.CmandiniDatabase;
import com.example.projet_android.room.Product;

import java.io.File;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    List<Product> products;
    CmandiniDatabase db;

    public ProductAdapter(List<Product> products, CmandiniDatabase db) {
        this.products = products;
        this.db = db;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_product,parent,false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.Name.setText(products.get(position).getName());
            holder.Price.setText(products.get(position).getPrice()+"");
            holder.address.setText(products.get(position).getAddress());
            int idCateg = products.get(position).getIdCategory();
            String nameCateg = db.categoryDao().getCategoryById(idCateg).getName();
            holder.category.setText(nameCateg);
            String photo = products.get(position).getPhoto();
            if(photo.contains("http"))
                Glide.with(holder.itemView.getContext())
                    .load(photo)
                    .into(holder.Pic);
            else
            {
                File imgFile = new File(photo);
                if(imgFile.exists()){
                    Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                    holder.Pic.setImageBitmap(myBitmap);
                }
            }
          /*  int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier("product1", "drawable", holder.itemView.getContext().getPackageName());
            holder.Pic.setImageResource(drawableResourceId);*/
    }

    @Override
    public int getItemCount() {
        return products.size();
    }
    public void updateProductList(List<Product> productList) {
        this.products = productList;
        notifyDataSetChanged();
    }
    class ViewHolder extends RecyclerView.ViewHolder{
        TextView Name, Price, address, category;
        ImageView Pic;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Name = itemView.findViewById(R.id.ProductName);
            Pic = itemView.findViewById(R.id.ProductPic);
            Price = itemView.findViewById(R.id.ProductPrice);
            address = itemView.findViewById(R.id.ProductPlace);
            category = itemView.findViewById(R.id.ProductCategory);

        }
    }
}
