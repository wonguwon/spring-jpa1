package jpabook.jpashop.repository;

import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository{

    private final EntityManager em;

    public void save(Item item) {
        if (item.getId() == null) { //새롭게 생성하는 객체
            em.persist(item);
        } else {//이미 등록된 걸 가져오는 것(유사 업데이트) -> 모든값을 변경하기 때문에 사용x
            em.merge(item);
        }
    }

    public Item findOne(Long id) {
        return em.find(Item.class, id);
    }

    public List<Item> findAll() {
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }
}
