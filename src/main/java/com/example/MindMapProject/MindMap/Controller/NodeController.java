package com.example.MindMapProject.MindMap.Controller;

import com.example.MindMapProject.MindMap.Models.Node;
import com.example.MindMapProject.MindMap.Services.NodeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NodeController {

    private NodeService nodeService;

    public NodeController(NodeService nodeService) {
        this.nodeService = nodeService;
    }

    @PostMapping("/startProject")
    public Node CreateMindMap(@RequestParam String desc){
        return nodeService.CreateMindMap(desc);
    }

    @PostMapping("/createNode/{id}")
    public Node createNode(@PathVariable int id, @RequestParam String desc){
        return nodeService.CreateNode(id,desc);
    }

    @GetMapping("/getSubTree/{id}")
    public List<Node> getSubtree(@PathVariable int id){
        return nodeService.getSubtree(id);
    }

    @DeleteMapping("/deleteNode/{id}")
    public ResponseEntity<String> deleteNode(@PathVariable int id) {
        if (nodeService.deleteNode(id)) {
            return ResponseEntity.ok("Node deleted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Node not found.");
        }
    }

    @PatchMapping("/updateNodeDesc/{id}")
    public ResponseEntity<String> updateNodeDesc(@PathVariable int id ,@RequestParam String desc){
        if (nodeService.changeNodeDesc(id, desc)) {
            return ResponseEntity.ok("Node description updated successfully.");
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Node not found.");
        }

    }

    @PatchMapping("/updateNodeParId/{id}")
    public ResponseEntity<String> updateNodePar(@PathVariable int id , @RequestParam int parId){
        if (nodeService.changeNodePar(id,parId)) {
            return ResponseEntity.ok("Node description updated successfully.");
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Node not found.");
        }

    }
}
