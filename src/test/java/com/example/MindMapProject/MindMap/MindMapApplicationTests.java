package com.example.MindMapProject.MindMap;

import com.example.MindMapProject.MindMap.Models.Node;
import com.example.MindMapProject.MindMap.Repository.NodeRepository;
import com.example.MindMapProject.MindMap.Services.NodeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MindMapApplicationTests {
	@Autowired
	private NodeService nodeService;
	@Autowired
	private NodeRepository nodeRepository;

	@Test
	public void validateCreateNodeResponse(){
		String description = "First Test";
		Node responseNode = nodeService.CreateNode(1, description);
		Node parentNode = nodeRepository.findById(1).orElse(null);
		if(parentNode == null){
			assertEquals(null, responseNode);
		}
		else{
			assertEquals(description, responseNode.getDescription());
			assertEquals(parentNode.getPath() + ">" + responseNode.getId(),responseNode.getPath());
		}

	}


	@Test
	public void validateGetSubTreeResponse(){
		int id = 1;
		List<Node> nodeList = nodeService.getSubtree(id);
		Node node = nodeRepository.findById(id).orElse(null);
		if(node == null){
			assertEquals(0, nodeList.size());
		}
		else{
			assertTrue(nodeList.size() > 0);
		}
	}

}
