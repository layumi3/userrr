package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {
    int quantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

/*
* method is called when the order button is clicked*/
    public void submitOrder(View view){

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("geo:47.6, -122.3"));
        if(intent.resolveActivity(getPackageManager())!= null){
            startActivity(intent);
        }

        EditText nameField = (EditText) findViewById(R.id.name_field);
        String name = nameField.getText().toString();
        // Figure out if the user wants whipped cream topping
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();

        // Figure out if the user wants whipped cream topping
        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = chocolateCheckBox.isChecked();

        // Calculate the price
        int price = calculatePrice();

        // Display the order summary on the screen
        String message = createOrderSummary(name, price, hasWhippedCream, hasChocolate);
        displayMessage(message);
    }
/*calculate method
* */
    private int calculatePrice(){
        int price = quantity * 5;
        return price;
    }
    /*method increment, once the button clicked*/
    public void increment(View view){
        quantity = quantity +1;
        if(quantity==100){
            Toast.makeText(this,"You cannot order more than 100 cup coffees",Toast.LENGTH_SHORT).show();
            return;
        }
        displayQuantity(quantity);
    }
    /*method decrement, once the button clicked*/
    public void decrement(View view){
        if(quantity==1){
            Toast.makeText(this,"You cannot order less than 1 cup coffee",Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity -1;
        displayQuantity(quantity);
    }

/*
* method displays the given quantity value on screen
* */
    private void displayQuantity(int number){
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText(""+number);
    }

    /*method display the given price on screen
    * not use anymore*/
    private void  displayPrice(int number){
        TextView priceTextView = (TextView) findViewById(R.id.order_summary_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }

    private void displayMessage(String message){
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

    public String createOrderSummary(String name, int price, boolean addWhippedCream, boolean addChocolate){
        String priceMessage = "Name: " + name;
        priceMessage += "\nAdd whipped cream? " + addWhippedCream;
        priceMessage += "\nAdd chocolate? " + addChocolate;
        priceMessage += "\nQuantity: " + quantity;
        priceMessage += "\nTotal: $" + price;
        priceMessage += "\nThank you!";
        return priceMessage;
    }

}
