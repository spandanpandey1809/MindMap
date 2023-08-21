package com.example.MindMapProject.MindMap.Repository;

import com.example.MindMapProject.MindMap.Models.Node;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NodeRepository extends JpaRepository<Node,Integer> {
    public List<Node> findByParId(int id);
}
