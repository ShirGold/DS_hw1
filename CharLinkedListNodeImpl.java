public class CharLinkedListNodeImpl implements ICharLinkedListNode{

    private char val;
    private ICharLinkedListNode next;

    public CharLinkedListNodeImpl(char val) {
        this.val = val;
        this.next = null;
    }

    public char getChar() {
        return this.val;
    }

    public ICharLinkedListNode getNext() {
        return this.next;
    }

    public void setNext(ICharLinkedListNode next) {
        this.next = next;
    }
}
