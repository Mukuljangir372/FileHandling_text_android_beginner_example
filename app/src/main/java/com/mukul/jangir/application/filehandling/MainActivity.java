package com.mukul.jangir.application.filehandling;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class MainActivity extends AppCompatActivity {
    //FileOutputStream is used for writing data
    //here we are going to save and read data from internal storage
   private FileOutputStream fileOutputStream;
   private String file_name = "example.txt";
   private EditText editText;
   private TextView textView;
   private Button save_button,load_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        save_button = (Button) findViewById(R.id.save_button);
        editText = (EditText) findViewById(R.id.editText);
        load_button = (Button) findViewById(R.id.load_button);
        textView = (TextView) findViewById(R.id.textView);


        save_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = editText.getText().toString();
                try {
                    //writing file to internal storage
                    fileOutputStream = openFileOutput(file_name, Context.MODE_PRIVATE);
                    fileOutputStream.write(text.getBytes());
                    Toast.makeText(getApplicationContext(), getFilesDir()+""+file_name, Toast.LENGTH_SHORT).show();
                    fileOutputStream.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
     load_button.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             //reading saved data from internal storage
             try {
                 FileInputStream fileInputStream = openFileInput(file_name);
                 int c;
                 String result = "";
                 while ((c = fileInputStream.read())!=-1) {
                     result += Character.toString((char)c);

                 }
                 textView.setText(result);
                 fileInputStream.close();

             } catch (FileNotFoundException e) {
                 e.printStackTrace();
             } catch (IOException e) {
                 e.printStackTrace();
             }
         }
     });
    }
}
