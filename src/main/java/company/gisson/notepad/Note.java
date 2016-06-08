package company.gisson.notepad;

import java.io.Serializable;

/**
 * Created by jorge on 07/06/16.
 */
public class Note implements Serializable{

    private String _title, _content;
    private long _id;

    public Note( int id ){
        _id = id;
    }

    public Note( long id, String title, String content ){
        _id = id;
        _title = title;
        _content = content;
    }

    public Note( long id, String content ){
        _id = id;
        _content = content;
    }

    public long get_id() {
        return _id;
    }

    public String get_title() {
        return _title;
    }

    public String get_content() {
        return _content;
    }

    public void set_content(String _content) {
        this._content = _content;
    }

    public void set_title(String _title) {
        this._title = _title;
    }

    public boolean equals( Note n ){
        return ((_content.equals(n.get_content())) && (_title.equals(n.get_title()))) ? true : false;
    }
}
