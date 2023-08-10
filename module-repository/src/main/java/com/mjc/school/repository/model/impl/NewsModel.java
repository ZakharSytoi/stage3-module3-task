package com.mjc.school.repository.model.impl;

import com.mjc.school.repository.model.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "NEWS")
@Getter
@Setter
@NoArgsConstructor
@EntityListeners({AuditingEntityListener.class})
public class NewsModel implements BaseEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String title;
    @Column(length = 400)
    private String content;
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createDate;
    @Column
    @LastModifiedDate
    private LocalDateTime lastUpdatedDate;
    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "authorId")
    private AuthorModel author;
    @ManyToMany(cascade = {CascadeType.PERSIST}, fetch = FetchType.EAGER)
    @JoinTable(name = "news_tags",
            joinColumns = @JoinColumn(name = "newsId"),
            inverseJoinColumns = @JoinColumn(name = "tagId"))
    private List<TagModel> tags = new LinkedList<>();

    public NewsModel(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "NewsModel{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", createDate=" + createDate +
                ", lastUpdatedDate=" + lastUpdatedDate +
                ", author=" + author +
                '}';
    }
}
