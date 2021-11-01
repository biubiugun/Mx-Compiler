package Util.error;
import Util.position;

public class SemanticError extends Error{
    public SemanticError(String msg,position pos){
        super("[SemanticError]: " + msg,pos);
    }
}
