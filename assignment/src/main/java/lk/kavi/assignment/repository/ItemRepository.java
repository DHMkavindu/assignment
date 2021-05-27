package lk.kavi.assignment.repository;

import lk.kavi.assignment.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    @Query("select i from Item i where i.item_description like %?1")
    Item itemByDescription(String description);
}
