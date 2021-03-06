package project.akshay.furnituremagiktask;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Objects;

public class AddProductActivity extends AppCompatActivity {

    TextInputEditText prodNameEditText;
    TextInputEditText prodPriceEditText;
    TextView addQuantity;
    TextView subQuantity;
    TextView prodQuantity;
    TextView totalPriceText;
    Button addProduct;
    DatabaseManager databaseManager;
    RelativeLayout relativeLayout;

    int quantityCounter;
    int totalPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        Objects.requireNonNull(getSupportActionBar()).hide();

        relativeLayout = findViewById(R.id.relLayout);
        prodNameEditText = findViewById(R.id.productNameText);
        prodPriceEditText = findViewById(R.id.productPriceText);
        addQuantity = findViewById(R.id.addQuantity);
        subQuantity = findViewById(R.id.subQuantity);
        prodQuantity = findViewById(R.id.product_quantity);
        totalPriceText = findViewById(R.id.product_total_price);
        addProduct = findViewById(R.id.addProductButton);

        databaseManager = new DatabaseManager(this);

        addQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty(Objects.requireNonNull(prodPriceEditText.getText()).toString())) {
                    quantityCounter++;
                    prodQuantity.setText(String.valueOf(quantityCounter));
                    totalPrice = quantityCounter * Integer.parseInt(prodPriceEditText.getText().toString());
                    totalPriceText.setText(String.valueOf(totalPrice));
                }
            }
        });

        subQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(quantityCounter != 0 && !TextUtils.isEmpty(Objects.requireNonNull(prodPriceEditText.getText()).toString())){
                    quantityCounter--;
                    prodQuantity.setText(String.valueOf(quantityCounter));
                    totalPrice = quantityCounter * Integer.parseInt(prodPriceEditText.getText().toString());
                    totalPriceText.setText(String.valueOf(totalPrice));
                }
            }
        });

        addProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String productName = Objects.requireNonNull(prodNameEditText.getText()).toString();
                String productQuantity = String.valueOf(quantityCounter);
                String productPrice = Objects.requireNonNull(prodPriceEditText.getText()).toString();

                if(productName.equals("") || productPrice.equals("") || totalPrice == 0){
                    Snackbar.make(relativeLayout,getResources().getString(R.string.error),Snackbar.LENGTH_SHORT).show();
                } else {
                    Product product = new Product(productName,productQuantity,productPrice,String.valueOf(totalPrice),0);
                    databaseManager.addProduct(product);

                    finishAffinity();
                    startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                    overridePendingTransition(R.anim.slide_from_left,R.anim.slide_to_right);

                }

            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.slide_from_left,R.anim.slide_to_right);
    }
}
