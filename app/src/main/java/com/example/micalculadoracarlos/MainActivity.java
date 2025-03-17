package com.example.micalculadoracarlos;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText inputOne, inputTwo;
    TextView result;
    Button calculate;
    Spinner operationSpinner;
    String selectedOperation = "Suma";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputOne = findViewById(R.id.inputOne);
        inputTwo = findViewById(R.id.inputTwo);
        result = findViewById(R.id.result);
        calculate = findViewById(R.id.calculate);
        operationSpinner = findViewById(R.id.operationSpinner);

        // Configurar Spinner con opciones de operaciones
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.operations_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        operationSpinner.setAdapter(adapter);

        operationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedOperation = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                selectedOperation = "Suma";
            }
        });

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                procesar();
            }
        });
    }

    private void procesar() {
        String num1 = inputOne.getText().toString();
        String num2 = inputTwo.getText().toString();

        if (num1.isEmpty() || num2.isEmpty()) {
            Toast.makeText(this, "Error: Ingrese ambos números", Toast.LENGTH_LONG).show();
            return;
        }

        double number1 = Double.parseDouble(num1);
        double number2 = Double.parseDouble(num2);
        double resultado = 0;

        switch (selectedOperation) {
            case "Suma":
                resultado = number1 + number2;
                break;
            case "Resta":
                resultado = number1 - number2;
                break;
            case "Multiplicación":
                resultado = number1 * number2;
                break;
            case "División":
                if (number2 == 0) {
                    Toast.makeText(this, "Error: División por cero", Toast.LENGTH_LONG).show();
                    return;
                }
                resultado = number1 / number2;
                break;
        }

        result.setText(String.valueOf(resultado));
    }

}