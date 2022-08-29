package com.yxh.weshare.utils.commentTree;

import com.yxh.weshare.bean.bo.CommentTreeNodeBo;
import com.yxh.weshare.bean.pojo.Comment;
import com.yxh.weshare.service.CommentService;
import com.yxh.weshare.utils.converter.TypeConverterUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Xinhao Yi
 * @date 2022/8/2 14:48
 * @description:
 */
public class CommentTreeGenerator {

    private List<CommentTreeNodeBo> commentTreeNodeList;

    //构造器  Constructors
    public CommentTreeGenerator(List<CommentTreeNodeBo> commentTreeNodeList) {
        this.commentTreeNodeList = new ArrayList<>();
        this.commentTreeNodeList.addAll(commentTreeNodeList);
    }



    //根节点的parentId 是 -1
    //The parentId of the root node is -1
    private List<CommentTreeNodeBo> getRootNodeList() {
        List<CommentTreeNodeBo> res = new ArrayList<>();
        for (CommentTreeNodeBo commentTreeNodeBo : this.commentTreeNodeList) {
            if (-1 == commentTreeNodeBo.getWsCommentParentId()){
                res.add(commentTreeNodeBo);
            }
        }
        return res;
    }


    private CommentTreeNodeBo generateCommentTree(CommentTreeNodeBo root) {
        for (CommentTreeNodeBo commentTreeNodeBo : this.commentTreeNodeList) {
            if (root.getWsCommentId().equals(commentTreeNodeBo.getWsCommentParentId())){
                root.getChildrenList().add(generateCommentTree(commentTreeNodeBo));
            }
        }
        return root;
    }


    //结果是森林的所有根节点
    // The result is all the root nodes of the forest
    public List<CommentTreeNodeBo> generateCommentForest() {
        //先拿到所有根节点
        // Get all root nodes first
        List<CommentTreeNodeBo> rootNodeList = this.getRootNodeList();

        List<CommentTreeNodeBo> res = new ArrayList<>();

        for (CommentTreeNodeBo root : rootNodeList) {
            CommentTreeNodeBo commentTreeNodeBo = generateCommentTree(root);
            res.add(commentTreeNodeBo);
        }
        return res;
    }

    //前序遍历菜单树
    // Pre-order traversal of the menu tree
    private List<CommentTreeNodeBo> preOrderTraversal(CommentTreeNodeBo root){
        //这里用linklist 装比较好
        // Here it is better to use linklist
        List<CommentTreeNodeBo> res = new LinkedList<>();
        preOrderTraversalAuxiliary(root, res);
        return res;
    }

    private void preOrderTraversalAuxiliary(CommentTreeNodeBo root, List<CommentTreeNodeBo> res){
        if (null == root){
            return;
        }
        res.add(root);
        for (CommentTreeNodeBo childNode : root.getChildrenList()) {
            preOrderTraversalAuxiliary(childNode, res);
        }
    }

    public List<CommentTreeNodeBo> generateTwoLevelForestRootNodesForView(List<CommentTreeNodeBo> rootList){
        for (CommentTreeNodeBo commentRoot : rootList) {
            List<CommentTreeNodeBo> preOrderTraversalList = preOrderTraversal(commentRoot);
            preOrderTraversalList.remove(0);
            //删除原有的childrenList中全部元素
            // Delete all elements of the original childrenList
            commentRoot.getChildrenList().clear();
            commentRoot.getChildrenList().addAll(preOrderTraversalList);
        }
        return rootList;
    }



}
