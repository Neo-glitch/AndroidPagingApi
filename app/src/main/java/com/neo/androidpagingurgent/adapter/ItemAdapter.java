package com.neo.androidpagingurgent.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.neo.androidpagingurgent.R;
import com.neo.androidpagingurgent.network.model.StackApiResponse;
import com.neo.androidpagingurgent.network.model.StackApiResponse.Item;
import com.squareup.picasso.Picasso;



// 3. Paged List Adapter
public class ItemAdapter extends PagedListAdapter<Item, ItemAdapter.MyViewHolder> {


    public ItemAdapter() {
        super(diffCallback);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Item item = getItem(position);   // gets item obj at this pos
        if (item != null){
            Picasso.get().load(item.owner.profile_image).into(holder.mImageView);
            holder.mTextView.setText(item.owner.display_name);
        }
    }

    private static DiffUtil.ItemCallback<Item> diffCallback = new DiffUtil.ItemCallback<Item>() {
        @Override
        public boolean areItemsTheSame(@NonNull Item oldItem, @NonNull Item newItem) {
            return oldItem.answer_id == newItem.answer_id;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Item oldItem, @NonNull Item newItem) {
            return oldItem.equals(newItem);
        }
    };

    class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView mImageView;
        TextView mTextView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.imageView);
            mTextView = itemView.findViewById(R.id.textViewName);
        }
    }
}
