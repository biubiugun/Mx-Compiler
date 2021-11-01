package Util.error;
import Util.position;

public class SyntaxError extends Error{

    public SyntaxError(String msg,position pos){
        super("[SyntaxError]: " + msg,pos);
    }
}
