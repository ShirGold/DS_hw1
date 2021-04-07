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
}
