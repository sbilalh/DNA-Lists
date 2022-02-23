interface LListInterface<T> {
    public void clear();

    public void insert();

    public void append();

    public T remove();

    public void moveToStart();

    public void moveToEnd();

    public void prev();
    
    public void next();

    public int length();

    public int currPos();

    public int moveToPos(int pos);

    public T getValue();

    public String toString();
}