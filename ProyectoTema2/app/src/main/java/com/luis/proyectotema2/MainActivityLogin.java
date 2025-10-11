package com.luis.proyectotema2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivityLogin extends AppCompatActivity {

    final String USER = "admin";
    final String PASS= "12345";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Acceder a los elementos del layout
        Button botonLogin = findViewById(R.id.buttonLogin);
        EditText editTextUsuario = findViewById(R.id.editTextUser);
        EditText editTextPass = findViewById(R.id.editTextPass);

        // 1. Lamda
        botonLogin.setOnClickListener( v -> {

            //Toast toast = Toast.makeText(this, "Boton clicado", Toast.LENGTH_SHORT);
            //toast.show();
            //String msg = editTextUsuario.getText() + " : " + editTextPass.getText();

            //Toast.makeText(this, msg , Toast.LENGTH_SHORT).show();


            // pasar de una actividad a otra
            Intent intent = new Intent(this, ActivityProfile.class);
            String user = editTextUsuario.getText().toString();
            String pass = editTextPass.getText().toString();

            if(user.equals(USER) && pass.equals(PASS)){
                startActivity(intent);
            }
            else{
                Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
            }

        });

/*
        // 2. Sobrescribiendo listener
        botonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Prueba", "Boton clicado");
            }
         });
            // 3. creando listener
            View.OnClickListener listener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("Prueba", "Boton clicado");

                }
            };
        botonLogin.setOnClickListener(listener);
      /*
        // Ejemplo de lamda
        ArrayList<String> paises = new ArrayList<>(Arrays.asList("España","Francia", "Alemania", "Italia", "Portugal"));

        for(String pais: paises){
            Log.d("Prueba", pais);
        }

        paises.forEach(String pais -> Log.d("Prueba", pais));

*/
    }
/* otra manera de hacerlo
    public void botonCliclado(View view) {
        Log.d("Prueba","Boton clicado");
        }

 */
}
