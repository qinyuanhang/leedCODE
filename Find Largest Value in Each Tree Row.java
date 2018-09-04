Find Largest Value in Each Tree Row

You need to find the largest value in each row of a binary tree.

Example:
Input: 

          1
         / \
        3   2
       / \   \  
      5   3   9 

Output: [1, 3, 9]





solution one:


按层遍历二叉树，用queue，把每一层的node里的数值作为一个list，最后把每一层的list选最大de。

class Solution {
    public List<Integer> largestValues(TreeNode root) {
        List res = new ArrayList<Integer>();
        if (root == null) return res;
        return findLargests(findLayer(root));   
    }
    //solution method: uese queue to scan tree, store node value by layer
    //@param: TreeNode
    //@return: layers as list of list<Integer> 
    private List findLayer(TreeNode root){
        Queue<TreeNode> toStoreLayer = new LinkedList<TreeNode>();
        List res = new ArrayList<List>();
        toStoreLayer.add(root);
        while (toStoreLayer.size() > 0) {
            List layer = new ArrayList<Integer>();
            int layerSize = toStoreLayer.size();
            for (int i = 0; i < layerSize; i ++){
                TreeNode tempNode = toStoreLayer.poll();
                layer.add(tempNode.val);
                if (tempNode.left != null) {
                    toStoreLayer.add(tempNode.left);
                }
                if (tempNode.right != null) {
                    toStoreLayer.add(tempNode.right);
                } 
            }
            res.add(layer);
        }
        return res;
    }
               
   //find each layer's largest val
   //@param: list of list
   //@return: list of integer   
    private List findLargests(List<List> lists){
        List res = new ArrayList<Integer>();
        for(List list : lists){
            res.add(Collections.max(list));
        }
        return res;
    } 
}

solution two: 用dfs，

class Solution {
    public List<Integer> largestValues(TreeNode root) {
        List res = new ArrayList<Integer>();
        go(root, res, 0);    
        return res;
    }
    
    //scan in dep stratagy
    
    private void go(TreeNode node, List<Integer> res, int depth){
        if(node == null){
            return;
        }
            if(res.size() == depth) {
                res.add(node.val); 
            }else{ 
                res.set(depth, Math.max(res.get(depth), node.val));
            }               
        go(node.left, res, depth + 1);
        go(node.right, res, depth + 1);
    }    
}




