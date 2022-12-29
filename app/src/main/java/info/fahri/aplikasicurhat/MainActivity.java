package info.fahri.aplikasicurhat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    EditText edtEmail, edtPassword;
    FirebaseUser user;
    FirebaseAuth mAuth;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

        preferences = this.getSharedPreferences(getString(R.string.shared_key), Context.MODE_PRIVATE);

        if (user !=null){
            startActivity(new Intent(this, DashboardActivity.class)
                    .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
        }
    }

    public void login(View v){
        String emailUser = edtEmail.getText().toString();
        String password = edtPassword.getText().toString();
        if(emailUser.length() > 10) {

            preferences.edit().putString(getString(R.string.emailUser_key
            ), emailUser).apply();

            mAuth.signInWithEmailAndPassword(emailUser,password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                startActivity(new Intent(getApplicationContext(), DashboardActivity.class)
                                        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
                            }else {
                                Log.w("error_auth", "error login", task.getException());
                            }
                        }
                    });
        }else
            Toast.makeText(this, "Silahkan Masukkan Username dan password yang benar", Toast.LENGTH_LONG).show();

    }

    public void toRegister(View v){
        startActivity(new Intent(this, RegisterActivity.class));
    }
}
