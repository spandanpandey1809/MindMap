package com.example.MindMapProject.MindMap.Services;

import com.example.MindMapProject.MindMap.Models.Node;
import com.example.MindMapProject.MindMap.Repository.NodeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NodeService {
    private NodeRepository nodeRepository;


    public NodeService(NodeRepository nodeRepository) {
        this.nodeRepository = nodeRepository;
    }

    public Node CreateMindMap(String desc){
        Node node = new Node();
        node.setParId(-1);
        node.setDescription(desc);

        return nodeRepository.save(node);
    }

    public Node CreateNode(int id,String desc){
        Node node = new Node();
        node.setParId(id);
        node.setDescription(desc);
        return  nodeRepository.save(node);
    }

    public List<Node> getSubtree(int id){
        List<Node> subTree = new ArrayList<>();
        Node node = nodeRepository.findById(id).orElse(null);
        subTree.add(node);
        traverse(id,subTree);
        return subTree;
    }

    public void traverse(int id, List<Node> subTree){
        List<Node> children = nodeRepository.findByParId(id);
        subTree.addAll(children);
        for(Node child : children){
            traverse(child.getId(),subTree);
        }
    }

    public boolean deleteNode(int id) {
       Node node = nodeRepository.findById(id).orElse(null);
        if(node != null){
            recursiveDelete(id);
            return true;
        }else{
            return false;
        }
    }

    public void recursiveDelete(int id){
        List<Node> children = nodeRepository.findByParId(id);
        for(Node child : children){
            recursiveDelete(child.getId());
        }
        nodeRepository.deleteById(id);
    }

    public boolean changeNodeDesc(int id,String desc){
        Node node = nodeRepository.findById(id).orElse(null);
        if(node != null){
            node.setDescription(desc);
            nodeRepository.save(node);
            return true;
        }else return false;
    }

    public boolean changeNodePar(int id, int parId){
        Node node = nodeRepository.findById(id).orElse(null);
        if(node != null){
            node.setParId(parId);
            nodeRepository.save(node);
            return true;
        }else{
            return false;
        }
    }

}
