package com.example.ordereats.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.ordereats.domain.User;
import com.example.ordereats.domain.Utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class SQLLITE_OpenHelper extends SQLiteOpenHelper {


    private final Context context;

    public SQLLITE_OpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        // Crear la tabla "users" en la base de datos
        String query = "CREATE TABLE users(_ID Integer primary key " +
                "autoincrement, Name text, Lastname text, Phone text," +
                "Email text, Password text, Active int);";
        database.execSQL(query);
    }

    public void abrir(){
        // Abrir la conexión a la base de datos
        this.getWritableDatabase();
    }

    public void cerrar(){
        // Cerrar la conexión a la base de datos
        this.close();
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        // Eliminar la tabla "users" si existe y volver a crearla
        db.execSQL("DROP TABLE IF EXISTS users");
        onCreate(db);
    }

    public void createUser(String name, String lastname, String phone, String email, String password, int active) {
        // Verificar si el correo electrónico ya existe en la base de datos
        if (isEmailExists(email)) {
            // El correo electrónico ya está registrado, mostrar un mensaje de error o tomar la acción correspondiente
            return;
        }

        ContentValues values = new ContentValues();
        values.put("Name", name);
        values.put("Lastname", lastname);
        values.put("Phone", phone);
        values.put("Email", email);
        values.put("Password", password);
        values.put("Active", active);
        // Insertar el nuevo usuario en la tabla "users"
        this.getWritableDatabase().insert("users", null, values);
    }

    public boolean isEmailExists(String email) {
        // Verificar si el correo electrónico ya existe en la base de datos
        String[] columns = {"Email"};
        String selection = "Email=?";
        String[] selectionArgs = {email};
        Cursor cursor = this.getReadableDatabase().query("users", columns, selection, selectionArgs, null, null, null);
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }


    public void getUsers(){
        // Obtener los usuarios de la base de datos
        ContentValues values = new ContentValues();
    }

    public String hashPassword(String password) {
        try {
            // Crear instancia de MessageDigest con algoritmo SHA-256
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            // Calcular el hash de la contraseña
            byte[] hashedBytes = messageDigest.digest(password.getBytes());// Calcular el hash de la contraseña
            StringBuilder stringBuilder = new StringBuilder(); // Convertir los bytes del hash a una representación en hexadecimal
            // Convertir los bytes del hash a una representación en hexadecimal
            for (byte b : hashedBytes) {
                stringBuilder.append(String.format("%02x", b));
            }
            return stringBuilder.toString();// Retornar el hash como una cadena de texto
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Método para validar el usuario
    public Cursor validateUser(String email, String password) {
        String hashedPassword = hashPassword(password);// Calcular el hash de la contraseña proporcionada
        if (hashedPassword == null) {
            // Ocurrió un error al calcular el hash de la contraseña
            return null;
        }

        Cursor mCursor = null;
        mCursor = this.getReadableDatabase().query(Utils.TUSERS,
                new String[]{Utils.ID, Utils.NAME, Utils.LASTNAME, Utils.PHONE, Utils.ACTIVE},
                Utils.EMAIL + " = ? AND " + Utils.PASSWORD + " = ?", new String[]{email, hashedPassword},
                null, null, null);
        return mCursor;
    }


    public ArrayList<User> showUsers() {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<User> userArrayList = new ArrayList<>();
        User user = null;
        Cursor userCursor = null;

        // Obtener todos los usuarios de la tabla "users"
        userCursor = db.rawQuery("SELECT * FROM users", null);
        if (userCursor.moveToFirst()) {
            do {
                // Crear un objeto User y establecer sus propiedades
                user = new User();
                user.setUser_Name(userCursor.getString(1));
                user.setUser_Lastname(userCursor.getString(2));
                user.setUser_Phone(userCursor.getString(3));
                user.setUser_Email(userCursor.getString(4));
                user.setUser_Password(userCursor.getString(5)); // string
                user.setUser_Active(userCursor.getInt(6)); // int

                // Agregar el usuario a la lista de usuarios
                userArrayList.add(user);
            } while (userCursor.moveToNext());
        }
        userCursor.close();
        return userArrayList;
    }

}
