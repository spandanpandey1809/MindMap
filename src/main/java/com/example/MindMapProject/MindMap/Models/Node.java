package com.example.MindMapProject.MindMap.Models;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "Node_Table")
public class Node {
    @Id
    @GeneratedValue
    int id;
    int parId;
    String description;
    String path;
}
