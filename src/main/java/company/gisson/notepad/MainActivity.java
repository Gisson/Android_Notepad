package company.gisson.notepad;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.util.List;

import company.gisson.notepad.Exception.NoteNotFoundException;

public class MainActivity extends AppCompatActivity {

    private File _directory;
    private PopupWindow pw;
    private NotepadManager _nm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Context context = getApplicationContext();
        _directory = context.getDir("mydir", Context.MODE_PRIVATE);
        loadNotepadManager();
        listNotes(findViewById(R.id.main_activity));

    }


    public void saveNote(View view){
        EditText editText = (EditText) findViewById(R.id.note_text);
        String message = editText.getText().toString();
        String fileName = "NoteNamePlaceHolder";
        _nm.addNote(message, fileName);
        saveNotepadManager();
        listNotes(view);

    }


    @Deprecated
    private void writeToFile(String data, String fileName) {
        try {
            FileOutputStream fout=openFileOutput(fileName ,MODE_PRIVATE);
            Log.e("Notepad","Writing notes.txt on " + getFilesDir().getAbsolutePath());
            fout.write(data.getBytes());
            fout.close();
        }
        catch (IOException e) {
            Log.e("Notepad", "File write failed: " + e.toString());
        }
    }

    @Deprecated
    private String readFromFile(String fileName) throws IOException{
            FileInputStream fin = openFileInput(fileName);
            String result="";
            int aux;
            while( (aux=fin.read()) != -1 ) {
                result += Character.toString((char) aux);
            }
            fin.close();
            return result;




    }

    /*public void initiatePopupWindow(View views) {
        try {
            //We need to get the instance of the LayoutInflater, use the context of this activity
            LayoutInflater inflater = (LayoutInflater) MainActivity.this
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            //Inflate the view from a predefined XML layout
            layout = inflater.inflate(R.layout.popup_layout,
                    (ViewGroup) findViewById(R.id.popup_element));
            // create a 300px width and 470px height PopupWindow
            pw = new PopupWindow(layout, 300, 470, true);
            // display the popup in the center
            pw.showAtLocation(layout, Gravity.CENTER, 0, 0);
            pw.setOutsideTouchable(true);
            pw.setFocusable(true);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/


    public void cancelPopup(View v) {
        pw.dismiss();
    }


    public void saveNotepadManager(){
        try {
            FileOutputStream fos = openFileOutput("NotepadManager.dat", MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream( fos );
            oos.writeObject(_nm);
            oos.close();
            fos.close();
            Log.d("Notepad","Saved Notepad Manager");
        }catch (FileNotFoundException e){
            e.toString();
        }catch( IOException e ){
            e.toString();
        }
    }

    public void loadNotepadManager(){
        try {
            FileInputStream fis = openFileInput("NotepadManager.dat");
            ObjectInputStream ois = new ObjectInputStream( fis );
            _nm = (NotepadManager) ois.readObject();
            ois.close();
            fis.close();
            Log.d("Notepad","Successfully7 loaded Notepad Manager");
        }catch( FileNotFoundException e ){
            _nm = new NotepadManager();
            Log.d("Notepad","New Notepad Manager created");
        }catch( IOException e){
            e.toString();
        }catch (ClassNotFoundException e) {
            Log.d("Notepad","New Notepad Manager created");
            _nm = new NotepadManager();
        }
    }

    public void listNotes( View view ){
        ListView lw = (ListView) findViewById(R.id.note_list);
        List<String> arrayList = _nm.getAllContents();
        ArrayAdapter adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, arrayList);
        List<Note> notes = _nm.get_notes();
        lw.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
