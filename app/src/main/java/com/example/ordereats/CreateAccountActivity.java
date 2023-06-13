package com.example.ordereats;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ordereats.data.SQLLITE_OpenHelper;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CreateAccountActivity extends AppCompatActivity {

    private EditText txt_name;
    private EditText txt_lastname;
    private EditText txt_phone;
    private EditText txt_email;
    private EditText txt_pass;

    private Button newUser;

    private SQLLITE_OpenHelper helperUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        // Obtener referencias a los elementos de la interfaz de usuario
        txt_name = findViewById(R.id.txtName);
        txt_lastname = findViewById(R.id.txt_lastname);
        txt_phone = findViewById(R.id.txtPhone);
        txt_email = findViewById(R.id.txtEmail);
        txt_pass = findViewById(R.id.txtPass);

        // Obtener referencia al botón de creación de usuario
        newUser = findViewById(R.id.btnCreate);

        // Configurar el evento onClick para el botón de creación de usuario
        newUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Verificar si los campos están validados correctamente
                if (validateFields()) {
                    // Obtener los valores de los campos de texto
                    String name = txt_name.getText().toString();
                    String lastname = txt_lastname.getText().toString();
                    String phone = txt_phone.getText().toString();
                    String email = txt_email.getText().toString();
                    String password = txt_pass.getText().toString();

                    // Aplicar hash a la contraseña
                    String hashedPassword = hashPassword(password);

                    // Verificar si el correo electrónico ya está registrado
                    helperUser = new SQLLITE_OpenHelper(CreateAccountActivity.this, "dbordereats",
                            null, 1);
                    if (helperUser.isEmailExists(email)) {
                        // Mostrar mensaje de correo electrónico ya registrado
                        Toast.makeText(CreateAccountActivity.this, "El correo electrónico ya está registrado", Toast.LENGTH_SHORT).show();
                    } else {
                        // Crear el usuario en la base de datos
                        helperUser.createUser(name, lastname, phone, email, hashedPassword, 1);
                        // Mostrar mensaje de usuario registrado correctamente
                        Toast.makeText(CreateAccountActivity.this, "Cliente registrado correctamente", Toast.LENGTH_SHORT).show();
                        // Redirigir a la actividad MainActivity
                        navigateToMainActivity();
                    }
                    // Cerrar la conexión a la base de datos
                    helperUser.close();
                }
            }
        });
    }

    // Método para validar los campos
    private boolean validateFields() {
        // Obtener los valores de los campos de texto y eliminar espacios en blanco
        String name = txt_name.getText().toString().trim();
        String lastname = txt_lastname.getText().toString().trim();
        String phone = txt_phone.getText().toString().trim();
        String email = txt_email.getText().toString().trim();
        String password = txt_pass.getText().toString().trim();

        return true;
    }

    private String hashPassword(String password) {
        try {
            // Crear instancia de MessageDigest con algoritmo SHA-256
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            // Convertir la contraseña a bytes
            byte[] passwordBytes = password.getBytes(StandardCharsets.UTF_8);
            // Aplicar el hash a los bytes de la contraseña
            byte[] hashedBytes = digest.digest(passwordBytes);
            // Convertir los bytes hasheados a formato hexadecimal
            StringBuilder builder = new StringBuilder();
            for (byte b : hashedBytes) {
                builder.append(String.format("%02x", b));
            }
            return builder.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }


    // Método para redirigir a MainActivity
    private void navigateToMainActivity() {
        Intent intent = new Intent(CreateAccountActivity.this, Menu.class);
        startActivity(intent);
        finish();
    }

}