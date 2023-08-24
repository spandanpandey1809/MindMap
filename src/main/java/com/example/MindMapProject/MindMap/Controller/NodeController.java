package com.example.MindMapProject.MindMap.Controller;

import com.example.MindMapProject.MindMap.Models.Node;
import com.example.MindMapProject.MindMap.Repository.NodeRepository;
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
    public ResponseEntity<?> createNode(@PathVariable int id, @RequestParam String desc) {
        try {
            Node createdNode = nodeService.CreateNode(id, desc);

            if (createdNode == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parent node not found");
            }

            return ResponseEntity.ok(createdNode);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred");
        }
    }

    @GetMapping("/getSubTree/{id}")
    public ResponseEntity<?> getSubtree(@PathVariable int id) {
        try {
            List<Node> subtree = nodeService.getSubtree(id);
            System.out.println(subtree);
            if (subtree.size()==0) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Subtree not found");
            }
            return ResponseEntity.ok(subtree);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred");
        }
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

    @GetMapping("/getLeafNodes/{id}")
    public ResponseEntity<?> getLeafList(@PathVariable int id){
        List<String> req = nodeService.getLeafNodes(id);
        if(req.isEmpty())return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Node not found.");
        return ResponseEntity.ok(nodeService.getLeafNodes(id));
    }
}
