public class CharLinkedListNodeImpl implements ICharLinkedListNode{

    private char val;
    private ICharLinkedListNode next;

    public CharLinkedListImpl(char val) {
        this.val = val;
        this.next = null;
    }

    @Override
    public char getChar() {
        return this.val;
    }
}
