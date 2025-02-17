package sb.tr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sb.tr.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
