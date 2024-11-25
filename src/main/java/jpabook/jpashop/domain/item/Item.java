package jpabook.jpashop.domain.item;

import lombok.Getter;
import lombok.Setter;
import jpabook.jpashop.domain.Category;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
//객체 지향 프로그래밍에서 상속 관계에 있는 클래스들을 데이터베이스 테이블로 어떻게 매핑할지
//단일 테이블 전략 ->  상속 관계에 있는 모든 클래스들이 하나의 테이블에 저장 후 dtype과 같은 구분컬럼으로 구분함
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//구분컬럼을 지정함
@DiscriminatorColumn(name = "dtype")
@Getter @Setter
//상위 클래스는 공통 속성의 모음일 뿐 객체화할 일이 없기 때문에 추상 클래스로 만들 것을 권장
public abstract class Item {
    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;

    private int price;

    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<Category>();
}
