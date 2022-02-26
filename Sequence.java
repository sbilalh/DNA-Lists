/**
 * CSC 172 - Lab 4
 * Bilal Hussain (shussa11@u.rochester.edu)
 * Sammy Potter (spott14@u.rochester.edu)
 * 26 February 2022
 * See README.txt
 */

public class Sequence {
    Type type;
    LList<Character> seq;

    //creating 3 enumeration values
    enum Type{
        DNA, RNA, EMPTY
    }

    //constructors
    public Sequence() {
    }
    public Sequence(Type type, LList<Character> seq){
        this.type = type;
        this.seq = seq;
    }
    public Sequence(Type type) {
        this.type = type;
    }

    //setter & getter for Type
    public void setType(Type type) {
        this.type = type;
    }
    public Type getType() {
        return type;
    }

    //setter and getters for sequence
    public void setList(LList<Character> seq) {
        this.seq = seq;
    }
    //returns seq as String
    public String getSeq() {
        return seq.toString();
    }
    //returns seq as LList
    public LList<Character> getList() {
        return seq;
    }

    //returns the length of the sequence
    public int getLength() {
        return seq.length();
    }
}
