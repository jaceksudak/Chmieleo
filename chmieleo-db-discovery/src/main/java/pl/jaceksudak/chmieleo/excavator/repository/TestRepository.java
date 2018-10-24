package pl.jaceksudak.chmieleo.excavator.repository;

import lombok.extern.slf4j.Slf4j;
import pl.jaceksudak.chmieleo.excavator.entity.Shop;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@Slf4j
public class TestRepository {

    @PersistenceContext(unitName = "chmieleodiscoverypu")
    private EntityManager em;

    public void save(Shop shop) {
        log.info("test Repo");
        em.persist(shop);
    }
}
