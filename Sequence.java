public class Sequence {
    Type type;
    LList<Character> seq;

    enum Type{
        DNA, RNA, EMPTY
    }

    public Sequence() {
    }

    public Sequence(Type type, LList<Character> seq){
        this.type = type;
        this.seq = seq;
    }

    public Sequence(Type type) {
        this.type = type;
    }

    public void setType(Type type) {
        this.type = type;
    }
    public Type getType() {
        return type;
    }

    public void setList(LList<Character> seq) {
        this.seq = seq;
    }
    public String getSeq() {
        return seq.toString();
    }

    public LList<Character> getList() {
        return seq;
    }

    public int getLength() {
        return seq.length();
    }

}
