package company.gisson.notepad;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import company.gisson.notepad.Exception.NoteAlreadyExistsException;
import company.gisson.notepad.Exception.NoteNotFoundException;
import company.gisson.notepad.Exception.NotepadException;

/**
 * Created by jorge on 07/06/16.
 */
public class NotepadManager implements Serializable{

    private List<Note> _notes;
    private static long _id;

    public NotepadManager(){
        _id = 0;
        _notes = new ArrayList<Note>();
    }

    public NotepadManager( List<Note> n ){
        _notes = n;
        _id = 0;
    }

    public void addNote(String content, String title){
        Note n = new Note( _id, title, content );
        _notes.add( n );
        _id ++;
    }
    // FIXME: need to check if id already exists in list or it might not be unique
    public void addNote( String content ){
        _notes.add( new Note( _id, content ) );
        _id ++;
    }

    public void addNote( Note n ){
        if( _notes.contains(n))
            throw new NoteAlreadyExistsException(n.get_id());
        else
            _notes.add(n);
    }

    public void removeNote( Note n){
        for( Note i : _notes ){
            if( i.equals(n)) {
                _notes.remove(i);
                break;
            }
        }
        throw new NoteNotFoundException(n.get_id());
    }

    public Note getNote( Note n ){
        Note returnNote=null;
        for(int i=0;i<_notes.size();i++){
            if( _notes.get(i).equals(n)){
                returnNote = _notes.get(i);
                break;
            }
        }
        if( returnNote==null)
            throw new NoteNotFoundException(n.get_id());
        return returnNote;
    }

    public Note getNote( int i ){
        if( i >= _notes.size() )
            throw new NoteNotFoundException((long)i);
        return _notes.get(i);
    }

    public List<Note> get_notes() {
        return _notes;
    }

    public List<String> getAllContents(){
        List<String> result = new ArrayList<String>();
        for( Note e : _notes ){
            result.add(e.get_content());
        }
        return result;
    }
}
