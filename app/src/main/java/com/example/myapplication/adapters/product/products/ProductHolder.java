package com.example.myapplication.adapters.product.products;

import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.interfaces.HolderItemCallback;
import com.example.myapplication.models.Product;
import com.example.myapplication.product_catigoy;
import com.example.myapplication.product_properaties_activity;

public class ProductHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private CardView productCard;
    private TextView productName;
    private TextView productDescription;


    private Product product;

    private HolderItemCallback<Product> holderItemCallback;

    public ProductHolder(@NonNull View itemView) {
        super(itemView);

        findViews();
    }

    private void findViews() {
        productCard = itemView.findViewById(R.id.item_product_card);
        productName = itemView.findViewById(R.id.item_product_name);
        productDescription = itemView.findViewById(R.id.item_product_description);


        productCard.setOnClickListener(this);

    }

    public void setData(Product product) {
        this.product = product;
        productName.setText(product.getName());
        productDescription.setText(product.getDescription());
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.item_product_card) {


                Intent intent = new Intent(itemView.getContext(), product_properaties_activity.class);
            intent.putExtra("productId", product.getId());

                itemView.getContext().startActivity(intent);


        }
    }
}
