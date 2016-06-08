package company.gisson.notepad.Exception;

/**
 * Created by jorge on 07/06/16.
 */
public class NoteAlreadyExistsException extends NotepadException {

    private long _id;

    public NoteAlreadyExistsException( long id ){
        _id = id;
    }

    @Override
    public String toString(){
        return "Note with id "+_id+" already exists!";
    }

}
