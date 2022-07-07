package com.example.lab15_first;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{
    ListView listal;
    TextView informacióntxt, preciotxt, igvprecio;
    EditText preciob, productob;
    ArrayList<String> productosarray = new ArrayList<String>();
    ArrayList<Double> precioarray = new ArrayList<Double>();
    ArrayList<Double> igvprecioarray = new ArrayList<Double>();
    ArrayAdapter<String> adaptador_productos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        preciob = findViewById(R.id.precio);
        productob = findViewById(R.id.productos);
        listal = findViewById(R.id.listado);
        informacióntxt = findViewById(R.id.leyenda);
        preciotxt = findViewById(R.id.preciotxt);
        igvprecio = findViewById(R.id.igvpreciotxt);
        productosarray.add("leche");
        precioarray.add(23.0);
        igvprecioarray.add(23.0);

        adaptador_productos = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, productosarray);
        listal.setAdapter(adaptador_productos);

        listal.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                informacióntxt.setText(productosarray.get(i).toString());
                preciotxt.setText(precioarray.get(i).toString());
                igvprecio.setText(igvprecioarray.get(i).toString());
            }
        });
    }

    public void Met_agregar(View view) {
        double price = Double.parseDouble(preciob.getText().toString());
        String product_adding = productob.getText().toString();
        double final_price = 0;
        if (price >= 100){
            final_price = price + (price*0.18);
            precioarray.add(price);
            igvprecioarray.add(final_price);
            productosarray.add(product_adding);
            adaptador_productos.notifyDataSetChanged();
            Toast toast1 = Toast.makeText(getApplicationContext(),
                    "Aplicamos un igv de 18%, nuevo precio "+final_price, Toast.LENGTH_SHORT);
            toast1.show();
            preciob.setText("");
            productob.setText("");
        }if (price <= 0){
            Toast toast1 = Toast.makeText(getApplicationContext(),
                            "Ingrese un precio válido", Toast.LENGTH_SHORT);
            toast1.show();
            preciob.setText("");
            productob.setText("");
        }if (0 < price && price < 100){
            igvprecioarray.add(price);
            precioarray.add(price);
            productosarray.add(product_adding);
            adaptador_productos.notifyDataSetChanged();
            Toast toast1 = Toast.makeText(getApplicationContext(),
                    "Producto agregado", Toast.LENGTH_SHORT);
            toast1.show();
            preciob.setText("");
            productob.setText("");
        }
    }
}