package info.fahri.aplikasicurhat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class FormCurhatActivity extends AppCompatActivity {

    EditText edtCurhat;
    FirebaseFirestore fireDb;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_curhat);
        edtCurhat = findViewById(R.id.edtCurhat);

        user = FirebaseAuth.getInstance().getCurrentUser();
        fireDb = FirebaseFirestore.getInstance();
    }

    public void postCurhat(View v){
        String userId = user.getUid();
        String email = user.getEmail();
        String konten = edtCurhat.getText().toString();
            fireDb.collection("curhat").document()
                    .set(new Curhat(userId, email, konten))
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w("Store_error", e.getMessage());
                        }
                    });

            finish();
    }
}
