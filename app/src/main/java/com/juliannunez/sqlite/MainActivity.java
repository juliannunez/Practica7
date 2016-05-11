package com.juliannunez.sqlite;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText eId, eCantidad, ePrecio;
    Button bGuardar, bModificar, bEliminar, bConsultar, bVender, bMostrar, bGanancia, bLimpiar, bOk;
    TextView tDisplay;
    AutoCompleteTextView eNombre;
    String cadena2;
    int cantidad, ganancias1, ganancias2, temp;
    private Cursor c;
    private static final String[] NOMBRES = new String[] {
            "Iron_Man", "Viuda_Negra", "Capitan_America", "Hulk", "Bruja_Escarlata", "SpiderMan"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eId = (EditText)findViewById(R.id.e1);
        eNombre = (AutoCompleteTextView)findViewById(R.id.e2);
        eCantidad = (EditText)findViewById(R.id.e3);
        ePrecio =(EditText)findViewById(R.id.e4);
        bGuardar = (Button)findViewById(R.id.b1);
        bEliminar = (Button)findViewById(R.id.b2);
        bModificar = (Button)findViewById(R.id.b3);
        bVender = (Button)findViewById(R.id.b4);
        bConsultar = (Button)findViewById(R.id.b5);
        bMostrar = (Button)findViewById(R.id.b6);
        bGanancia = (Button)findViewById(R.id.b7);
        bLimpiar = (Button)findViewById(R.id.b8);
        bOk  = (Button)findViewById(R.id.b9);
        tDisplay = (TextView)findViewById(R.id.display);
        ganancias2 = 0;


        cadena2 = "";
        temp = 0;

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, NOMBRES);
        eNombre.setAdapter(adapter);

        cargarInventario(1, "Iron_Man", 10, 10000);
        cargarInventario(2, "Viuda_Negra", 10, 12000);
        cargarInventario(3, "Capitan_America", 10, 15000);
        cargarInventario(4, "Hulk", 10, 12000);
        cargarInventario(5, "Bruja_Escarlata", 10, 15000);
        cargarInventario(6, "SpiderMan", 10, 10000);

        eId.setEnabled(false);
        eId.setVisibility(View.INVISIBLE);
        eNombre.setEnabled(false);
        eNombre.setVisibility(View.INVISIBLE);
        eCantidad.setEnabled(false);
        eCantidad.setVisibility(View.INVISIBLE);
        ePrecio.setEnabled(false);
        ePrecio.setVisibility(View.INVISIBLE);

        bOk.setVisibility(View.INVISIBLE);
        bOk.setEnabled(false);

        bGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                temp = 1;
                eId.setEnabled(true);
                eId.setVisibility(View.VISIBLE);
                eNombre.setEnabled(true);
                eNombre.setVisibility(View.VISIBLE);
                eCantidad.setEnabled(true);
                eCantidad.setVisibility(View.VISIBLE);
                ePrecio.setEnabled(true);
                ePrecio.setVisibility(View.VISIBLE);

                bOk.setVisibility(View.VISIBLE);
                bOk.setEnabled(true);

            }
        });

        bConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                temp = 2;

                eId.setEnabled(false);
                eId.setVisibility(View.INVISIBLE);
                eNombre.setEnabled(true);
                eNombre.setVisibility(View.VISIBLE);
                eCantidad.setEnabled(false);
                eCantidad.setVisibility(View.INVISIBLE);
                ePrecio.setEnabled(false);
                ePrecio.setVisibility(View.INVISIBLE);

                bOk.setVisibility(View.VISIBLE);
                bOk.setEnabled(true);

            }
        });

        bModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                temp = 3;
                eId.setEnabled(false);
                eId.setVisibility(View.INVISIBLE);
                eNombre.setEnabled(true);
                eNombre.setVisibility(View.VISIBLE);
                eCantidad.setEnabled(true);
                eCantidad.setVisibility(View.VISIBLE);
                ePrecio.setEnabled(false);
                ePrecio.setVisibility(View.INVISIBLE);

                bOk.setVisibility(View.VISIBLE);
                bOk.setEnabled(true);

            }
        });

        bEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                temp = 4;
                eId.setEnabled(false);
                eId.setVisibility(View.INVISIBLE);
                eNombre.setEnabled(true);
                eNombre.setVisibility(View.VISIBLE);
                eCantidad.setEnabled(false);
                eCantidad.setVisibility(View.INVISIBLE);
                ePrecio.setEnabled(false);
                ePrecio.setVisibility(View.INVISIBLE);

                bOk.setVisibility(View.VISIBLE);
                bOk.setEnabled(true);
            }
        });

        bMostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eId.setEnabled(false);
                eId.setVisibility(View.INVISIBLE);
                eNombre.setEnabled(false);
                eNombre.setVisibility(View.INVISIBLE);
                eCantidad.setEnabled(false);
                eCantidad.setVisibility(View.INVISIBLE);
                ePrecio.setEnabled(false);
                ePrecio.setVisibility(View.INVISIBLE);

                bOk.setVisibility(View.INVISIBLE);
                bOk.setEnabled(false);
                mostrar(v);
            }
        });

        bVender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                temp = 6;
                eId.setEnabled(false);
                eId.setVisibility(View.INVISIBLE);
                eNombre.setEnabled(true);
                eNombre.setVisibility(View.VISIBLE);
                eCantidad.setEnabled(false);
                eCantidad.setVisibility(View.INVISIBLE);
                ePrecio.setEnabled(false);
                ePrecio.setVisibility(View.INVISIBLE);

                bOk.setVisibility(View.VISIBLE);
                bOk.setEnabled(true);
            }
        });

        bGanancia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eId.setEnabled(false);
                eId.setVisibility(View.INVISIBLE);
                eNombre.setEnabled(false);
                eNombre.setVisibility(View.INVISIBLE);
                eCantidad.setEnabled(false);
                eCantidad.setVisibility(View.INVISIBLE);
                ePrecio.setEnabled(false);
                ePrecio.setVisibility(View.INVISIBLE);

                bOk.setVisibility(View.INVISIBLE);
                bOk.setEnabled(false);
                ganancias(v);
            }
        });

        bLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limpiar(v);
            }
        });

        bOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (temp){

                    case 1: guardar(v);
                        bOk.setVisibility(View.INVISIBLE);
                        bOk.setEnabled(false);
                        break;
                    case 2: consultar(v);
                        bOk.setVisibility(View.INVISIBLE);
                        bOk.setEnabled(false);
                        break;
                    case 3: modificar(v);
                        bOk.setVisibility(View.INVISIBLE);
                        bOk.setEnabled(false);
                        break;
                    case 4: eliminar(v);
                        bOk.setVisibility(View.INVISIBLE);
                        bOk.setEnabled(false);
                        break;
                    case 5: mostrar(v);
                        break;
                    case 6: vender(v);
                        bOk.setVisibility(View.INVISIBLE);
                        bOk.setEnabled(false);
                        break;
                    case 7: ganancias(v);
                        break;
                }
            }
        });

        eNombre.setOnDismissListener(new AutoCompleteTextView.OnDismissListener() {
            @Override
            public void onDismiss() {
                InputMethodManager in = (InputMethodManager) getSystemService(getApplicationContext().INPUT_METHOD_SERVICE);
                in.hideSoftInputFromWindow(getCurrentFocus().getApplicationWindowToken(), 0);
            }
        });

    }

    public void guardar (View view){
        BaseDeDatos estudiante = new BaseDeDatos(this, "basededatos", null, 1 );
        SQLiteDatabase bd = estudiante.getWritableDatabase();

        String nombre = eNombre.getText().toString();
        String identificacion = eId.getText().toString();
        String cantidad = eCantidad.getText().toString();
        String precio = ePrecio.getText().toString();

        ContentValues registro = new ContentValues();
        registro.put("identificacion", identificacion);
        registro.put("nombre", nombre);
        registro.put("cantidad", cantidad);
        registro.put("precio", precio);

        if(nombre.equals("") ||identificacion.equals("") || cantidad.equals("") || precio.equals("")){
            Toast.makeText(this, "Llene todos los campos",Toast.LENGTH_SHORT).show();
        } else{

            c = bd.rawQuery("select identificacion, cantidad, precio from estudiantes where nombre='" + nombre +"'", null);

            if (c.moveToFirst() == true) {
                Toast.makeText(this, "Este peluchito ya existe", Toast.LENGTH_SHORT).show();
            } else {
                bd.insert("estudiantes", null, registro);
                bd.close();
                Toast.makeText(this, "Se guardaron los datos", Toast.LENGTH_SHORT).show();
                eNombre.setText("");
                eId.setText("");
                eCantidad.setText("");
                ePrecio.setText("");
            }
        }
    }

    public  void consultar (View view){

        BaseDeDatos estudiante = new BaseDeDatos(this, "basededatos", null, 1);
        SQLiteDatabase bd = estudiante.getWritableDatabase();

        String nombre = eNombre.getText().toString();

        c = bd.rawQuery("select identificacion, cantidad, precio from estudiantes where nombre='" + nombre + "'", null);

        if(c.moveToFirst()==true){
            tDisplay.setText("ID: " + c.getString(0) + "\nNombre: " + nombre + "\nCantidad: " + c.getString(1) + "\nPrecio: " + c.getString(2));
        } else {
            Toast.makeText(this, "No existe peluchito con ese nombre",Toast.LENGTH_SHORT).show();
        }
        bd.close();
    }

    public void modificar (View v){
        BaseDeDatos estudiante = new BaseDeDatos(this,"basededatos", null, 1);
        SQLiteDatabase bd = estudiante.getWritableDatabase();


        String nombre = eNombre.getText().toString();
        String cantidad = eCantidad.getText().toString();
        int cantidad2 = Integer.parseInt(cantidad);

        if(cantidad2<=5 && cantidad2>=0){

            NotificationCompat.Builder builder = new NotificationCompat.Builder(this);

            builder.setContentTitle("Alerta").setContentText("Quedan solo "+cantidad2+" peluches")
                    .setTicker("Se acaban los peluches").setSmallIcon(R.mipmap.ic_launcher);

            Intent notIntent = new Intent(MainActivity.this, MainActivity.class);

            PendingIntent contIntent = PendingIntent.getActivity(MainActivity.this, 0, notIntent, 0);

            builder.setContentIntent(contIntent);

            NotificationManager nm = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
            nm.notify(1, builder.build());


        } else if(cantidad2<0){
            Toast.makeText(getApplicationContext(), "NO se puede hacer esa modificacion", Toast.LENGTH_SHORT).show();
        }

        ContentValues registro = new ContentValues();
        registro.put("cantidad", cantidad);

        int cant = bd.update("estudiantes", registro, "nombre='" + nombre + "'", null);
        bd.close();

        if (cant==1){
            Toast.makeText(this, "Se modificaron los datos", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "No existe ese peluchito, agregue uno :3", Toast.LENGTH_SHORT).show();
        }

    }

    public void eliminar (View v){
        BaseDeDatos estudiante = new BaseDeDatos(this,"basededatos", null, 1);
        SQLiteDatabase bd = estudiante.getWritableDatabase();

        String nombre = eNombre.getText().toString();

        int cant = bd.delete("estudiantes", "nombre='" + nombre + "'", null);

        eNombre.setText("");
        eId.setText("");
        eCantidad.setText("");
        ePrecio.setText("");

        tDisplay.setText("");


        if (cant==1){
            Toast.makeText(this, "Se eliminaron los datos", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "No tenemos en el momento :'(", Toast.LENGTH_SHORT).show();
        }

    }

    public void mostrar (View view) {

        BaseDeDatos estudiante = new BaseDeDatos(this, "basededatos", null, 1);
        SQLiteDatabase bd = estudiante.getWritableDatabase();

        String cadena = "";

        c = bd.rawQuery("select * from estudiantes", null);


            while (c.moveToNext()) {
                cadena +="ID: "+c.getString(0);
                cadena += "\nNombre: "+ c.getString(1);
                cadena += "\nCantidad: "+ c.getString(2);
                cadena += "\nPrecio: "+ c.getString(3);
                cadena += "\n";
            }

            tDisplay.setText(cadena);
         /*else{
            tDisplay.setText("No hay NADA en la base de datos");*/


    }

    public void vender(View view){

        BaseDeDatos estudiante = new BaseDeDatos(this, "basededatos", null, 1);
        SQLiteDatabase bd = estudiante.getWritableDatabase();

        String nombre = eNombre.getText().toString();
        c = bd.rawQuery("select identificacion, cantidad, precio from estudiantes where nombre='" + nombre + "'", null);

        if(c.moveToFirst()==true){
            cantidad = Integer.parseInt(c.getString(1));
            if(cantidad-1>=1){

                SharedPreferences prefs =
                        getSharedPreferences("MisPreferencias",getApplicationContext().MODE_PRIVATE);

                SharedPreferences.Editor editor = prefs.edit();

                cantidad = cantidad -1;
                if(cantidad<=5){

                    NotificationCompat.Builder builder = new NotificationCompat.Builder(this);

                    builder.setContentTitle("Alerta").setContentText("Quedan solo "+cantidad+" peluches")
                            .setTicker("Se acaban los peluches").setSmallIcon(R.mipmap.ic_launcher);

                    Intent notIntent = new Intent(MainActivity.this, MainActivity.class);

                    PendingIntent contIntent = PendingIntent.getActivity(MainActivity.this, 0, notIntent, 0);

                    builder.setContentIntent(contIntent);

                    NotificationManager nm = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
                    nm.notify(1, builder.build());


                }

                ganancias1 = Integer.parseInt(c.getString(2));
                ganancias2 = prefs.getInt("ganancias", 0);
                ganancias2 = ganancias2 + ganancias1;

                ContentValues registro = new ContentValues();
                registro.put("cantidad", cantidad);
                int cant = bd.update("estudiantes", registro, "nombre='" + nombre + "'", null);

                editor.putInt("ganancias", ganancias2);
                editor.commit();
                bd.close();
                Toast.makeText(getApplicationContext(), "Venta realizada", Toast.LENGTH_SHORT).show();
                tDisplay.setText("");
            } else{
                Toast.makeText(this, "No quedan los suficientes peluchitos",Toast.LENGTH_SHORT).show();
            }


        } else {
            Toast.makeText(this, "No existe peluchito con ese nombre",Toast.LENGTH_SHORT).show();
        }
    }

    public void ganancias(View view){
        SharedPreferences prefs =
                getSharedPreferences("MisPreferencias",getApplicationContext().MODE_PRIVATE);

        int gdefinitiva = prefs.getInt("ganancias", 0);

        tDisplay.setText("Las ganancias son de: "+gdefinitiva+" pesos");



    }

    public void cargarInventario(int id, String nombre, int cantidad, int precio){

        BaseDeDatos estudiante = new BaseDeDatos(this, "basededatos", null, 1 );
        SQLiteDatabase bd = estudiante.getWritableDatabase();

        ContentValues registro = new ContentValues();
        registro.put("identificacion", id);
        registro.put("nombre", nombre);
        registro.put("cantidad", cantidad);
        registro.put("precio", precio);
        bd.insert("estudiantes", null, registro);
        bd.close();
    }

    public void limpiar(View view){

        tDisplay.setText("");
        eId.setText("");
        eNombre.setText("");
        eCantidad.setText("");
        ePrecio.setText("");
    }


}
