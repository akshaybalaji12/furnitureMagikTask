package project.akshay.furnituremagiktask;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.MyViewHolder> {

    public ArrayList<Product> productArrayList;

    public ProductListAdapter(ArrayList<Product> productArrayList) {
        this.productArrayList = productArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.layout_product_details,viewGroup,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, int i) {

        myViewHolder.productPrice.setText(productArrayList.get(i).getTotalPrice());
        myViewHolder.productName.setText(productArrayList.get(i).getProductName());
        myViewHolder.productID.setText(String.valueOf(productArrayList.get(i).getId()));
        myViewHolder.productQuantity.setText(productArrayList.get(i).getQuantity());

    }

    @Override
    public int getItemCount() {
        return productArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView productID;
        TextView productName;
        TextView productQuantity;
        TextView productPrice;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            productID = itemView.findViewById(R.id.product_id);
            productName = itemView.findViewById(R.id.product_name);
            productQuantity = itemView.findViewById(R.id.product_quantity);
            productPrice = itemView.findViewById(R.id.product_price);

        }
    }
}
