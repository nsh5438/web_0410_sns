package kr.hs.dgsw.dgsw_sns.Repository;

import kr.hs.dgsw.dgsw_sns.Domain.Content;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContentRep extends JpaRepository<Content, Long> {
}
