package com.practice.g_i_arc.timhortons;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

//import android.support.v7.

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    private int quantity, price = 0;

    private String customer = "";

    //Toppings
    private String whipped_cream = "";
    private String chocolate = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Calculates the price of the order based on the current quantity.
     *
     * @return the price
     */
    private void calculatePrice(int quantity) {
        //minimum amount for 1 cup of coffee with no additives
        int basePrice = 5;
        //Add $1 if the user wants whipped cream
        if (whipped_cream == ""+R.string.true_) basePrice += 1;
        //Add $2 if the user wants chocolate
        if (chocolate == ""+R.string.true_) basePrice += 2;
        //set price
        price = quantity * basePrice;
    }

    /**
     * @param price the price of the order
     * @return a summary that shows name of the person ordering,
     * the amount of cups being ordered with the price and a thank you message
     */
    private String createOrderSummary(int price) {
        String name = getString(R.string.order_summary_name,customer);//R.string.name + customer;
        String amount = "\n" + getString(R.string.order_summary_quantity,quantity);
        String total = "\n"  + getString(R.string.order_summary_price, NumberFormat.getCurrencyInstance().format(price));
        String whippedCream = "\n" + getString(R.string.order_summary_add_whipped_cream,whipped_cream);
        String chocolateTopping = "\n" + getString(R.string.order_summary_add_chocolate,chocolate);
        return name + whippedCream + chocolateTopping + amount + total + "\n " + R.string.thank_you;
    }

    /**
     * This method increases the number of coffee cups
     *
     * @param view
     */
    public void increment(View view) {
        if (quantity == 100) {
            //error message
            Toast.makeText(this, R.string.increment_toast, Toast.LENGTH_SHORT).show();
            return;
        }

        quantity++;
        displayQuantity(quantity);
    }

    /**
     * This method reduces the number of coffee cups
     *
     * @param view
     */
    public void decrement(View view) {
        if (quantity == 1) {
            //error message
            Toast.makeText(this, R.string.decrement_toast, Toast.LENGTH_SHORT).show();
            //exit method as there's nothing to show
            return;
        }
        quantity--;
        displayQuantity(quantity);
    }


    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        EditText nameField = (EditText) findViewById(R.id.customer_name);
        customer = nameField.getText().toString();

        calculatePrice(quantity);
//        displayMessage(createOrderSummary(price));

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT,"Just Java Order for "+ customer);
        intent.putExtra(Intent.EXTRA_TEXT, createOrderSummary(price));
        if(intent.resolveActivity(getPackageManager())!=null) startActivity(intent);
    }

    protected void drinkTea(View view) {
        ImageView teaImage = (ImageView) findViewById(R.id.cup_image_view);
        teaImage.setImageResource(R.drawable.tea_cup);
    }

    protected void drinkCoffee(View view) {
        ImageView coffeeImage = (ImageView) findViewById(R.id.cup_image_view);
        coffeeImage.setImageResource(R.drawable.coffee_cup);
    }

    /**
     * Used to check the state of the whipped cream checkbox
     *
     * @param view the state of the checkbox view
     */
    public void toppings(View view) {
        /*
        // For multiple checkboxes
        boolean checked = ((CheckBox) view).isChecked();
        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.checkbox_whipped_cream:
                if (checked) whipped_cream = "true";
                else whipped_cream = "false";
            case R.id.checkbox_chocolate:
                if (checked) chocolate = "true";
                else chocolate = "false";
            default:
                break;
        }
        */
        CheckBox checkboxWhippedCream = (CheckBox) findViewById(R.id.checkbox_whipped_cream);
        if (checkboxWhippedCream.isChecked()) whipped_cream = "" + R.string.true_;
        else whipped_cream = ""+R.string.false_;
        CheckBox checkboxChocolate = (CheckBox) findViewById(R.id.checkbox_chocolate);
        if (checkboxChocolate.isChecked()) chocolate = "" + R.string.true_;
        else chocolate = ""+R.string.false_;
    }


    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * displays given text for price
     *
     * @param message
     */
    private void displayMessage(String message) {
        TextView msgTextView = (TextView) findViewById(R.id.order_summary_text_view);
        msgTextView.setText(message);
    }
}