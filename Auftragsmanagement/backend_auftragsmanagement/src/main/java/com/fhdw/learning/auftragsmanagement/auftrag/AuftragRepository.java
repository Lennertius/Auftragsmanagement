package com.fhdw.learning.auftragsmanagement.auftrag;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface AuftragRepository extends JpaRepository<Auftrag, Long> {
    @Modifying
    @Query("delete from AuftragPosition p where p.auftrag.id = :auftragId")
    void deleteByAuftragId(@Param("auftragId") Long auftragId);


}
