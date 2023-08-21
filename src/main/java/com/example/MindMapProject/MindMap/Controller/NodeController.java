package com.example.MindMapProject.MindMap.Controller;

import com.example.MindMapProject.MindMap.Models.Node;
import com.example.MindMapProject.MindMap.Services.NodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class NodeController {

    private NodeService nodeService;

    public NodeController(NodeService nodeService) {
        this.nodeService = nodeService;
    }

    @PostMapping("/startProject")
    public Node CreateMindMap(){
        return nodeService.CreateMindMap();
    }

    @PostMapping("/createNode/{id}")
    public Node createNode(@PathVariable int id){
        return nodeService.CreateNode(id);
    }

    @GetMapping("/getSubTree/{id}")
    public List<Node> getSubtree(@PathVariable int id){
        return nodeService.getSubtree(id);
    }
}
