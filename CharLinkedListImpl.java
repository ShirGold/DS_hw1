public class CharLinkedListImpl extends CharLinkedList {

    protected ICharLinkedListNode curr;


    /**
     * Constructs a new empty character linked list
     */
    public CharLinkedListImpl() {
        super();
    }

    /**
     * Constructs a new linked list with one character
     */
    public CharLinkedListImpl(char c) {
        super();
        this.curr = new CharLinkedListNodeImpl(c);
        this.first = this.curr;
        this.last = this.curr;
    }

    /**
     * Adds a new character to the end of the list
     * @param c Character to add
     */
    public void add(char c) {
        CharLinkedListNodeImpl newChar = new CharLinkedListNodeImpl(c);
        if (this.first == null) {
            this.first = newChar;
            this.last = newChar;
        }
        else {
            this.last.setNext(newChar);
            this.last = newChar;
        }
    }

    /**
     * Getter for the first character
     * @return The first character in the list
     */
    public char firstChar() {
        if (first == null) {
            return (Character) null;
        }
        return this.first.getChar();
    }

    /**
     * Calculates the size of the list
     * @return The number of characters in the list
     */
    public int size() {
        int size = 0;
        curr = this.first;
        while (curr != null) {
            size += 1;
            curr = curr.getNext();
        }
        return size;
    }

    /**
     * Appends a list to the end of this list
     * @param toAppend The list to be appended at the end of this list
     */
    public void append(CharLinkedList toAppend) {
        if (this.first == null) {
            this.first = toAppend.first;
        }
        else {
            this.last = toAppend.first;
        }
        this.last = toAppend.last;
    }
}
