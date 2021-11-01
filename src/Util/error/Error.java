package Util.error;

import Util.position;

abstract public class Error extends RuntimeException{
    public position pos;
    private String message;

    public Error(String msg, position pos) {
        this.pos = pos;
        this.message = msg;
    }

    public String toString() {
        return message + ": " + pos.toString();
    }
}
