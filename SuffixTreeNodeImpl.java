public class SuffixTreeNodeImpl extends SuffixTreeNode {

    public SuffixTreeNodeImpl(){super();}

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
        SuffixTreeNode[] new_children = new SuffixTreeNode[this.totalWordLength];
        for (int i=0;i<this.totalWordLength;i++){
            if (i<until)
                new_children[i] = this.children[i];
            else
                new_children[i] = this.children[i-1];
        }
        this.children = new_children;
    }

    public void addChild(SuffixTreeNode node){
        if (this.numOfChildren == 0){
            this.children[0] = node;
            this.numOfChildren++;
            return;
        }
        int until = 0;
        for (int i=0; i<this.totalWordLength;i++){
            until++;
            if(this.children[i] == null)
                break;
            if (this.children[i].chars.first.getChar() > node.chars.first.getChar())
                break;
        }
        shiftChildren(until);
        this.children[until-1] = node;
    }
}
