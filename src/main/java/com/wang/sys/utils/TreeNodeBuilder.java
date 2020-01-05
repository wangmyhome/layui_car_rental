package com.wang.sys.utils;


import java.util.ArrayList;
import java.util.List;

public class TreeNodeBuilder {

    /**
     * @Description 将简单的集合构造有层级关系的集合
     * @Author shanpeng
     * @Date  15:44
     * @Param [nodes, topPid]
     * @return java.util.List<com.wang.sys.utils.TreeNode>
     **/
    public static List<TreeNode> builder(List<TreeNode> nodes,Integer topPid){
        List<TreeNode> treeNodes = new ArrayList<>();
        for (TreeNode n1 : nodes) {
            if (n1.getPid() == topPid){
                treeNodes.add(n1);
            }
            for (TreeNode n2 : nodes){
                if (n2.getPid() == n1.getId()){
                    n1.getChildren().add(n2);
                }
            }
        }
        return treeNodes;
    }
}

