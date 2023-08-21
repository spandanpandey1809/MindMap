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

    public Node CreateMindMap(){
        Node node = new Node();
        node.setParId(-1);
        return nodeRepository.save(node);
    }

    public Node CreateNode(int id){
        Node node = new Node();
        node.setParId(id);
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
}
