package com.mjc.school.repository.model.impl;

import com.mjc.school.repository.model.BaseEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "TAG")
@Data
@NoArgsConstructor
public class TagModel implements BaseEntity<Long> {

    public TagModel(Long id){
        this.id = id;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToMany(cascade = {CascadeType.PERSIST}, mappedBy = "tags", fetch=FetchType.LAZY)
    private List<NewsModel> newsModel = new LinkedList<>();
}
