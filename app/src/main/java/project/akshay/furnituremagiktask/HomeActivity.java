package project.akshay.furnituremagiktask;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.Objects;

public class HomeActivity extends AppCompatActivity {

    RecyclerView productDetailsList;
    ArrayList<Product> productDetails = new ArrayList<>();
    LinearLayoutManager manager;
    ProductListAdapter productListAdapter;
    Button addProductButton;
    RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Objects.requireNonNull(getSupportActionBar()).setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar_layout);

        addProductButton = findViewById(R.id.addProductButton);

        DatabaseManager databaseManager = new DatabaseManager(this);

        relativeLayout = findViewById(R.id.relLayout);
        productDetailsList = findViewById(R.id.savedItemsList);
        productDetails = databaseManager.getAllProducts();
        productListAdapter = new ProductListAdapter(productDetails);
        manager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);

        productDetailsList.setAdapter(productListAdapter);
        productDetailsList.setLayoutManager(manager);

        AppUtilities.getSnapHelper().attachToRecyclerView(productDetailsList);

        addProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),AddProductActivity.class));
                overridePendingTransition(R.anim.slide_from_right,R.anim.slide_to_left);
            }
        });

        if(!productDetails.isEmpty()) {
            Snackbar.make(relativeLayout,getResources().getString(R.string.instruction),Snackbar.LENGTH_SHORT).show();
        }

    }
}
