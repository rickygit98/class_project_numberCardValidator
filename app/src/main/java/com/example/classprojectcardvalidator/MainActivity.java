package com.example.classprojectcardvalidator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {
            
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        TextInputLayout txtInputNumber = (TextInputLayout) findViewById(R.id.txtInputNumber);
        Button btnCheck = (Button) findViewById(R.id.checkButton);


        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the Input Card Number
                String inputNumber = txtInputNumber.getEditText().getText().toString().trim();

                // Process with Luhn Algorithm
                // 1. Convert String to Integer
                Integer[] numberArray = new Integer[inputNumber.length()];

                for (int i = 0; i < inputNumber.length() ; i++){
                    numberArray[i] = Integer.parseInt(inputNumber.substring(i,i+1));
                }

                // 2. Double each other digit from right, if greater than 9, mod 10 and 1
                for (int i = numberArray.length - 2 ; i>= 0 ; i = i-2){
                    int tempValue = numberArray[i];
                    tempValue = tempValue * 2;
                    if (tempValue > 9){
                        tempValue = tempValue % 10 + 1;
                    }
                    numberArray[i] = tempValue;
                }

                // 3. SUM up all digits in number Array
                int total = 0;
                for (int i = 0; i< numberArray.length; i++){
                    total += numberArray[i];
                }
                
                // 4. If number is a multiple of 10, it is valid number
                if(total % 10 == 0){
                    Toast.makeText(MainActivity.this, "Valid Number Card", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "Not Valid Number", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}