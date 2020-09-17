package com.example.myapplication.categories;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.models.Catigory;
import com.example.myapplication.product_catigoy;

public class CategoryHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {
    public TextView categoryName ;
    private Catigory category;
    private CardView categoryItemCard;


    public CategoryHolder(@NonNull View itemView) {
        super(itemView);
        findViews();
    }
    private void findViews(){
        categoryName = itemView.findViewById(R.id.category_name);
        categoryItemCard = itemView.findViewById(R.id.category_item_card);


        categoryItemCard.setOnClickListener(this);
    }

    public void setData(Catigory category) {
        this.category = category;
        categoryName.setText(category.getName());
    }
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.category_item_card) {
            Intent intent = new Intent(itemView.getContext(), product_catigoy.class);
            intent.putExtra("categoryId", category.getId());
            itemView.getContext().startActivity(intent);
        }
    }
}
