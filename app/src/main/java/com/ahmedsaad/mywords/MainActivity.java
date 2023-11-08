package com.ahmedsaad.mywords;

import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class MainActivity extends AppCompatActivity {

    private static final String FILE_NAME = "WordsApp";

    EditText txtWords, txtFileName;
    TextView txtSize;
    Button btnBig, btnSmall , btnNew, btnSave, btnGetFile;
    CheckBox cbxBold, cbxUnderline;
    Spinner sColor, sFond;
    RadioButton rbLTR ,rbRTL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        txtWords = findViewById( R.id.txtWords );
        txtSize = findViewById( R.id.txtSize );
        btnBig = findViewById( R.id.btnBig );
        btnSmall = findViewById( R.id.btnSmall );
        cbxBold = findViewById( R.id.cbxBold );
        cbxUnderline = findViewById( R.id.cbxUnderline );
        sColor = findViewById( R.id.spinnerColor );
        sFond = findViewById( R.id.spinnerFont );
        rbLTR = findViewById( R.id.rbLTR );
        rbRTL = findViewById( R.id.rbRTL );
        txtFileName = findViewById( R.id.txtFileName );
        btnNew = findViewById( R.id.btnNew );
        btnSave = findViewById( R.id.btnSave );
        btnGetFile = findViewById( R.id.btnGetFile );

        FillColors();
        fillFonts();

        btnBig.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSize( '+' );
                // bigText();
            }

        } );
        btnSmall.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSize( '-' );
                //smallText();
            }
        } );
        cbxBold.setOnCheckedChangeListener( new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                setBold();
            }
        } );

        cbxUnderline.setOnCheckedChangeListener( new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                setUnderline();
            }
        } );
        sColor.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                setOneColor();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        } );

        sFond.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                setOneFont2();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        } );

        rbLTR.setOnCheckedChangeListener( new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                setAlign();
            }
        } );

        rbRTL.setOnCheckedChangeListener( new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                setAlign();
            }
        } );

        btnNew.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newFile();
            }
        } );

        btnSave.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveFile1();
            }
        } );

        btnGetFile.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GetFile();
                //restore();
            }
        } );
    }


    protected void FillColors() {

        String[] strColor = {
                "Black",
                "red",
                "blue",
                "green",
                "gary",
                "orange",
                "navy",
                "brown",
                "pink",
                "yellow"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>( this, android.R.layout.simple_spinner_item, strColor );
        sColor.setAdapter( adapter );
    }

    protected void fillFonts() {

        String[] strColor = {
                "sans-serif",
                "arial",
                "arabtype",
                "impact",
                "andlso"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>( this, android.R.layout.simple_spinner_item, strColor );
        sFond.setAdapter( adapter );
    }

    /*
        protected void bigText(){   //this is btn "+" only

            int size = Integer.parseInt( txtSize.getText().toString() );
            size++;
            txtWords.setTextSize( size );
            txtSize.setText(size + "" );
        }

     */
    protected void setSize(char bigORSmall) {  //this is btn"+","-"

        int size = Integer.parseInt( txtSize.getText() + "" );

        if (bigORSmall == '+') size++;
        else size--;

        if (size > 99) size = 99;
        if (size < 6) size = 6;

        txtWords.setTextSize( size );
        txtSize.setText( size + "" );

    }

    /*
        protected void smallText(){    //this is btn "-" only

            int Size = Integer.parseInt( txtSize.getText().toString());
            Size--;
            txtWords.setTextSize( Size );
            txtSize.setText( Size +"" );

        }
     */
    protected void setBold() {
        if (cbxBold.isChecked()) {
            txtWords.setTypeface( null, Typeface.BOLD );
        } else {
            txtWords.setTypeface( null, Typeface.NORMAL );
        }
        setOneFont2();
    }

    protected void setUnderline() {
        if (cbxUnderline.isChecked()) {
            txtWords.setPaintFlags( Paint.UNDERLINE_TEXT_FLAG );
        } else {
            txtWords.setPaintFlags( Paint.LINEAR_TEXT_FLAG );
        }
        setBold();
    }

    protected void setOneColor() {
        String strColor = sColor.getSelectedItem().toString();
        switch (strColor) {
            case "Black":
                txtWords.setTextColor( getResources().getColor( R.color.Black ) );
                break;
            case "red":
                txtWords.setTextColor( getResources().getColor( R.color.red ) );
                break;
            case "blue":
                txtWords.setTextColor( getResources().getColor( R.color.blue ) );
                break;
            case "green":
                txtWords.setTextColor( getResources().getColor( R.color.green ) );
                break;
            case "gary":
                txtWords.setTextColor( getResources().getColor( R.color.gary ) );
                break;
            case "orange":
                txtWords.setTextColor( getResources().getColor( R.color.orange ) );
                break;
            case "navy":
                txtWords.setTextColor( getResources().getColor( R.color.navy ) );
                break;
            case "brown":
                txtWords.setTextColor( getResources().getColor( R.color.brown ) );
                break;
            case "pink":
                txtWords.setTextColor( getResources().getColor( R.color.pink ) );
                break;
            case "yellow":
                txtWords.setTextColor( getResources().getColor( R.color.yellow ) );
                break;
        }
    }

    protected void setOneFont2() {
        String strFont = sFond.getSelectedItem().toString();
        Typeface Tf;
        if ("sans-serif".equals( strFont ))
            Tf = Typeface.SANS_SERIF;
        else
            Tf = Typeface.createFromAsset( getAssets(), strFont + ".ttf" );
        if (cbxBold.isChecked())
            txtWords.setTypeface( Tf, Typeface.BOLD );
        else
            txtWords.setTypeface( Tf, Typeface.NORMAL );
    }

    /*
         protected void setOneFont(){
             String strFont = sFond.getSelectedItem().toString();
             Typeface tf = Typeface.SANS_SERIF;
             switch (strFont){
                 case "sans-serif":
                     tf = Typeface.SANS_SERIF;
                     break;
                 case   "arial":
                     tf = Typeface.createFromAsset(getAssets(),"arial.ttf");
                     break;
                 case "arabtype":
                     tf = Typeface.createFromAsset(getAssets(),"arabtype.ttf");
                     break;
                 case  "impact":
                     tf = Typeface.createFromAsset( getAssets(), "impact.tff" );
                     break;
                 case  "andlso":
                     tf = Typeface.createFromAsset( getAssets(), "andlso.ttf" );
                     break;
             }
             if (cbxBold.isChecked()) {
                 txtWords.setTypeface( tf, Typeface.BOLD );
             }
             else {
                 txtWords.setTypeface( tf, Typeface.NORMAL );

             }

         }
    */
    protected void setAlign() {

        if (rbLTR.isChecked()) {
            txtWords.setGravity( Gravity.LEFT );
        } else {
            txtWords.setGravity( Gravity.RIGHT );
        }
    }

    protected void newFile() {
        txtWords.setText( "" );
        txtSize.setText( "16" );
        txtWords.setTextSize( 16 );
        cbxBold.setChecked( false );
        cbxUnderline.setChecked( false );
        sColor.setSelection( 2 );
        sFond.setSelection( 4 );
        rbLTR.setChecked( true );
        txtFileName.setText( "FileName" );
        txtWords.requestFocus();
    }

    //this method saveFile
    private void saveFile(){

        if ("".equals( txtFileName.getText().toString().trim() )) {
            Toast.makeText( this, "File Name Is empty!", Toast.LENGTH_LONG ).show();
            txtFileName.requestFocus();
        } else {
            //Stream مجرى
            try {
                FileOutputStream fos = openFileOutput( FILE_NAME, MODE_PRIVATE );
                PrintWriter pw = new PrintWriter( fos );
                pw.write( txtWords.getText().toString() );
                pw.close();

                PrintWriter pwSet = new PrintWriter( fos );
                String strSet =
                        txtSize.getText() +
                                "\n" + cbxBold.isChecked() +
                                "\n" + cbxUnderline.isChecked() +
                                "\n" + sColor.getSelectedItem() +
                                "\n" + sFond.getSelectedItem() +
                                "\n" + rbLTR.isChecked() +
                                "\n" + rbRTL.isChecked();
                pwSet.write( strSet );
                pwSet.close();
                Toast.makeText( this, "File is Saved", Toast.LENGTH_LONG ).show();
            } catch (FileNotFoundException e) {
                Toast.makeText( this, e.getMessage(), Toast.LENGTH_LONG ).show();
            }
        }

    }

    private void restore(){
        try {
            FileInputStream fis = new FileInputStream( FILE_NAME );
            InputStreamReader isr = new InputStreamReader( fis );
            BufferedReader br = new BufferedReader( isr );

            String allText ="";
            String team = "";
            while((team=br.readLine()) !=null) {
                allText += team + "\n";
            }
            fis = new FileInputStream( FILE_NAME );
            isr = new InputStreamReader( fis );
            br = new BufferedReader( isr );

            String[] strSet = new String[7];
            int x = 0;
            while((team = br.readLine() ) !=null){

                strSet[x] = team;
                x++;
            }

            txtSize.setText( strSet[0] );
            txtWords.setTextSize( Integer.parseInt( strSet[0] ) );
            cbxBold.setChecked( Boolean.parseBoolean( strSet[1] ) );
            cbxUnderline.setChecked( Boolean.parseBoolean( strSet[2] ) );
            sColor.setSelection( ((ArrayAdapter<String>) sColor.getAdapter()).getPosition( strSet[3] ) );
            sFond.setSelection( ((ArrayAdapter<String>) sColor.getAdapter()).getPosition( strSet[4] ) );
            if ("true".equals( strSet[5] )) rbLTR.setChecked( true );
            else rbRTL.setChecked( true );

            br.close();
            isr.close();
            fis.close();

            txtWords.setText( allText );
            Toast.makeText( this, "Success is ", Toast.LENGTH_SHORT ).show();

        } catch (FileNotFoundException e) {
            Toast.makeText( this , e.getMessage() , Toast.LENGTH_LONG ).show();
        } catch (IOException e) {
            Toast.makeText( this, e.getMessage(), Toast.LENGTH_SHORT ).show();
        }
        

    }



    protected void saveFile1() {
        if ("".equals( txtFileName.getText().toString().trim() )) {
            Toast.makeText( this, "File Name Is empty!", Toast.LENGTH_LONG ).show();
            txtFileName.requestFocus();
        } else {
            try {
                String strPath = Environment.getExternalStorageDirectory().getPath() + "/WordsApp" ;
                File f = new File( strPath );
                f.mkdir();
                PrintWriter pw = new PrintWriter( strPath + "/" + txtFileName.getText() + ".txt" );
                pw.write( txtWords.getText().toString() );
                pw.close();
                PrintWriter pwSet = new PrintWriter( strPath + "/" + txtFileName.getText() + "Set.txt" );
                String strSet =
                        txtSize.getText() +
                                "\n" + cbxBold.isChecked() +
                                "\n" + cbxUnderline.isChecked() +
                                "\n" + sColor.getSelectedItem() +
                                "\n" + sFond.getSelectedItem() +
                                "\n" + rbLTR.isChecked() +
                                "\n" + rbRTL.isChecked();
                pwSet.write( strSet );
                pwSet.close();
                Toast.makeText( this, "File is Saved ", Toast.LENGTH_LONG ).show();
            } catch (Exception e) {

                Toast.makeText( this, "" + e.getMessage(), Toast.LENGTH_LONG ).show();
            }
        }
    }

    protected void GetFile() {
        try {
            String strPath = Environment.getExternalStorageDirectory().getPath() + "/WordsApp";
            FileReader fr = new FileReader( strPath +"/"+  txtFileName.getText() + ".txt" );
            BufferedReader br = new BufferedReader( fr );
            String strContent = "";
            String strLine = "";
            while ((strLine = br.readLine()) != null) {
                strContent += strLine + "\n";
            }
            fr = new FileReader( strPath +"/"+ txtFileName.getText() + "Set.txt" );
            br = new BufferedReader( fr );

            String[] strSet = new String[7];
            int x = 0;
            while ((strLine = br.readLine()) != null) {
                strSet[x] = strLine;
                x++;
            }
            fr.close();
            br.close();

            txtSize.setText( strSet[0] );
            txtWords.setTextSize( Integer.parseInt( strSet[0] ) );
            cbxBold.setChecked( Boolean.parseBoolean( strSet[1] ) );
            cbxUnderline.setChecked( Boolean.parseBoolean( strSet[2] ) );
            sColor.setSelection( ((ArrayAdapter<String>) sColor.getAdapter()).getPosition( strSet[3] ) );
            sFond.setSelection( ((ArrayAdapter<String>) sColor.getAdapter()).getPosition( strSet[4] ) );
            if ("true".equals( strSet[5] )) rbLTR.setChecked( true );
            else rbRTL.setChecked( true );

            //txtWords.setText( strContent );
        } catch (Exception e) {

            Toast.makeText( this, e.getMessage(), Toast.LENGTH_SHORT ).show();
        }

    }
}



