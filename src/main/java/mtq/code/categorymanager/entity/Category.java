package mtq.code.categorymanager.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;

@Entity
@Transactional
@Data
@NoArgsConstructor
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cate_id")
    private Long cateId;
    @Column(name = "cate_name", nullable = false, unique = true)
    private String cateName;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_cate_id", referencedColumnName = "cate_id")
    private List<Product> product;
}

