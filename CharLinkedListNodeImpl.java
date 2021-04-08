public class CharLinkedListNodeImpl implements ICharLinkedListNode{

    private char c;
    private ICharLinkedListNode next;

    public CharLinkedListNodeImpl(char val) {
        this.c = val;
        this.next = null;
    }

    public char getChar() {
        return this.c;
    }

    public ICharLinkedListNode getNext() {
        return this.next;
    }

    public void setNext(ICharLinkedListNode next) {
        this.next = next;
    }
}
