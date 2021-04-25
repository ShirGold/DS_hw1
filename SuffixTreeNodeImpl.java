public class SuffixTreeNodeImpl extends SuffixTreeNode {

    public SuffixTreeNodeImpl(){super();}

    public SuffixTreeNodeImpl(CharLinkedList chars, SuffixTreeNode node){
        this.chars = chars;
        this.parent = node;
    }

    public SuffixTreeNode search(char c){
        return binarySearch(c, 0, (this.numOfChildren-1));
    }

    public SuffixTreeNode binarySearch(char target, int left, int right){
        if (right < 0){
            return null;
        }
        while (left <= right) {
            int mid = (left + right) / 2;
            if (target == this.children[mid].chars.first.getChar()) {
                return this.children[mid];
            }
            if (target > this.children[mid].chars.first.getChar()){
                left = mid+1;
            }
            else{
                right = mid-1;
            }
        }
        return null;
    }

    public void shiftChildren(int until){
        SuffixTreeNode[] new_children = new SuffixTreeNode[this.children.length];
        for (int i=this.numOfChildren;i>=0;i--){
            if (i>until)
                new_children[i] = this.children[i-1];
            else
                new_children[i] = this.children[i];
        }
        this.children = new_children;
    }

    public void addChild(SuffixTreeNode node){
        int until = 0;
        for (int i=0; i<this.numOfChildren;i++){
            if(this.children[i] == null)
                break;
            if (this.children[i].chars.first.getChar() > node.chars.first.getChar())
                break;
            until++;
        }
        this.shiftChildren(until);
        node.totalWordLength = this.totalWordLength+node.chars.size();
        this.children[until] = node;
        node.parent = this;
        this.numOfChildren++;
        if (this.numOfChildren>1){
            SuffixTreeNode curr_par = this;
            while (curr_par != null){
                curr_par.descendantLeaves++;
                curr_par = curr_par.parent;
            }
        }
        else
            this.descendantLeaves=1;
    }

    public void addSuffix(char[] word, int from){
        if (from == word.length)
            return;
        char c = word[from];
        SuffixTreeNode child = search(c);
        SuffixTreeNodeImpl node = new SuffixTreeNodeImpl();
        node.chars = new CharLinkedListImpl(c);
        from++;
        if (child==null) {
            if (this.parent != null || c!='$') {
                this.addChild(node);
                node.addSuffix(word, from);
            }
        }
        else {
            child.addSuffix(word, from);
        }
    }

    public void compress(){
        if (this.numOfChildren == 1){
            if (this.parent != null){
                SuffixTreeNode only_child = this.children[0];
                this.chars.add(only_child.chars.first.getChar());
                this.children = only_child.children;
                this.numOfChildren = only_child.numOfChildren;
                this.totalWordLength = only_child.totalWordLength;
                this.compress();
            }
        }
        else {
            for (int i = 0; i < this.numOfChildren; i++) {
                if (this.children[i] != null) {
                    this.children[i].parent = this;
                    this.children[i].compress();
                }
            }
        }
    }

    public int numOfOccurrences(char[] subword, int from){
        if (from == subword.length)
            return this.descendantLeaves;

        SuffixTreeNode child = this.search(subword[from]);
        if (child == null)
            return 0;
        else {
            ICharLinkedListNode char_node = child.chars.first;
            while (char_node != null){
                if (char_node.getChar() != subword[from])
                    return 0;
                else {
                    from++;
                    if (from==subword.length)
                        return child.descendantLeaves;
                }
                char_node = char_node.getNext();
            }
            return child.numOfOccurrences(subword, from);
        }
    }
}
