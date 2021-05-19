package com.muneiah.resetpassword;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText mail, pass;
    private FirebaseAuth mAuth;
    ProgressDialog progressDialog;
    String name;
    String password;
    private StorageReference mStorageRef;
    private static final int RESULT_LOAD_IMAGE = 1;
    private List<String> fileNameList;
    private List<String> fileDoneList;
    UploadListAdapter uploadListAdapter;
    RecyclerView upLoadList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mail = findViewById(R.id.editText);
        pass = findViewById(R.id.editText2);
        upLoadList=findViewById(R.id.upload_list);
        uploadListAdapter = new UploadListAdapter(fileNameList, fileDoneList);
        mAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        mStorageRef = FirebaseStorage.getInstance().getReference();
        fileNameList = new ArrayList<>();
        fileDoneList = new ArrayList<>();

    }

    public void resetpassword(View view) {
        // Initialize Firebase Auth
        Intent intent = new Intent(this, PassordResetActivity.class);
        startActivity(intent);

    }


    public void userLogin(View view) {
        String email = mail.getText().toString();
        String password = pass.getText().toString();
        progressDialog.setTitle("Authanticating...");
        progressDialog.setMessage("Please Wait.. loading");
        progressDialog.show();
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("TAG", "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            progressDialog.dismiss();
                            Toast.makeText(MainActivity.this, "successs " + user.getEmail().toString(), Toast.LENGTH_SHORT).show();
                            // updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("TAG", "signInWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }

                        // ...
                    }
                });
    }

    public void userSignUp(View view) {
        String email = mail.getText().toString();
        String password = pass.getText().toString();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.i("TAG", "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(MainActivity.this, "successs " + user.getEmail().toString(), Toast.LENGTH_SHORT).show();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.i("TAG", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }

    public void signOut(View view) {
        FirebaseAuth.getInstance().signOut();
    }

    public void upload_File(View view) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), RESULT_LOAD_IMAGE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        progressDialog.setMessage("Uploading..");
        progressDialog.setCancelable(false);
        progressDialog.show();

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK) {

            if (data.getClipData() != null) {

                int totalItemsSelected = data.getClipData().getItemCount();

                for (int i = 0; i < totalItemsSelected; i++) {

                    Uri fileUri = data.getClipData().getItemAt(i).getUri();

                    String fileName = getFileName(fileUri);

                    fileNameList.add(fileName);
                    fileDoneList.add("uploading");
                    uploadListAdapter.notifyDataSetChanged();

                    StorageReference fileToUpload = mStorageRef.child("Images")
                            .child(fileName);

                    final int finalI = i;
                    fileToUpload.putFile(fileUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        progressDialog.dismiss();
                            fileDoneList.remove(finalI);
                            fileDoneList.add(finalI, "done");

                            uploadListAdapter.notifyDataSetChanged();
                            uploadListAdapter = new UploadListAdapter(fileNameList, fileDoneList);
                            upLoadList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                            upLoadList.setHasFixedSize(true);
                            upLoadList.setAdapter(uploadListAdapter);

                        }
                    });

                }

                //Toast.makeText(MainActivity.this, "Selected Multiple Files", Toast.LENGTH_SHORT).show();

            } else if (data.getData() != null) {

                Toast.makeText(MainActivity.this, "Selected Single File", Toast.LENGTH_SHORT).show();

            }

        }

    }

    public String getFileName(Uri uri) {
        String result = null;
        if (uri.getScheme().equals("content")) {
            Cursor cursor = getContentResolver().query(uri, null, null, null, null);
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            } finally {
                cursor.close();
            }
        }
        if (result == null) {
            result = uri.getPath();
            int cut = result.lastIndexOf('/');
            if (cut != -1) {
                result = result.substring(cut + 1);
            }
        }
        return result;
    }
}
