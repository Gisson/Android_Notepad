package company.gisson.notepad.Exception;

/**
 * Created by jorge on 07/06/16.
 */
public class NoteNotFoundException extends NotepadException {

    private long _id;

    public NoteNotFoundException(long id ){
        _id = id;
    }

    @Override
    public String toString(){
        return "Note with id "+_id+" already exists!";
    }

}
