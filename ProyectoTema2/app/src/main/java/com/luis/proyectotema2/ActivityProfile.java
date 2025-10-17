package com.luis.proyectotema2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ActivityProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Buscar elemento vista
        EditText usernameText = findViewById(R.id.editTextUsername);

        // Recuperar datos de la primera actividad
        String usuario = getIntent().getStringExtra("username");
        String contraseña = getIntent().getStringExtra("password");

        // Setear dato en editText
        usernameText.setText(usuario);
        Log.d("Pass", contraseña);

        // --- Botón para abrir navegador ---
        Button botonNavegador = findViewById(R.id.buttonNavegador);
        botonNavegador.setOnClickListener(v -> {
            Uri uri = Uri.parse("https://unendo.es/");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        });

        // --- Botón para crear alarma ---
        Button botonAlarmas = findViewById(R.id.buttonAlarmas);
        botonAlarmas.setOnClickListener(v -> {
            Intent intentAlarma = new Intent(AlarmClock.ACTION_SET_ALARM)
                    .putExtra(AlarmClock.EXTRA_MESSAGE, "Recreo")
                    .putExtra(AlarmClock.EXTRA_HOUR, 11)
                    .putExtra(AlarmClock.EXTRA_MINUTES, 15);

            startActivity(intentAlarma);
        });
        ImageView imagenViewFoto = findViewById(R.id.imageViewProfile);
        // crear intent result launcher para recibir foto capturada
        ActivityResultLauncher<Intent> launcherFoto = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK) {
                        // Dato recibido correctamente
                       imagenViewFoto.setImageURI(result.getData().getData());


                    } else {
                        Toast.makeText(this, "Error al capturar la foto", Toast.LENGTH_SHORT).show();
                    }


                });

        imagenViewFoto.setOnClickListener(v -> {
            Intent misco = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            launcherFoto.launch(misco);


        });
    }
}