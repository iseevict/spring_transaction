package sb.tr.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;
import sb.tr.entity.Member;
import sb.tr.repository.MemberRepository;

@Service
@RequiredArgsConstructor // 생성자 주입
public class TransactionService {

    private final TransactionTemplate tt;
    private final MemberRepository memberRepository;

    @Autowired
    private TransactionService self;

    // TransactionTemplate 테스트 (예외 X)
    @Scheduled(initialDelay = 1000, fixedRate = Long.MAX_VALUE)
    public void transactionTemplateTest1() {
        tt.executeWithoutResult(status -> {
            memberRepository.save(getMember(1));

            memberRepository.save(getMember(2));
        });
    }

    // TransactionTemplate 테스트 (예외 O)
    @Scheduled(initialDelay = 1000, fixedRate = Long.MAX_VALUE)
    public void transactionTemplateTest2() {
        tt.executeWithoutResult(status -> {
            memberRepository.save(getMember(3));

            memberRepository.save(getMember(4));

            throw new RuntimeException("강제");
        });
    }

    // @Transactional 테스트 (상황 여러개)
    @Scheduled(initialDelay = 1000, fixedRate = Long.MAX_VALUE)
    @Transactional
    public void transactionTemplateTest3() {
        memberRepository.save(getMember(4));
        memberRepository.save(getMember(5));
        throw new RuntimeException("강제");
    }


    @Scheduled(initialDelay = 1000, fixedRate = Long.MAX_VALUE)
    public void transactionTemplateTest4() {
        self.transactionTemplateTest5();
    }

    @Transactional
    public void transactionTemplateTest5() {
        memberRepository.save(getMember(8));
        memberRepository.save(getMember(9));
        throw new RuntimeException("강제");
    }

    private static Member getMember(int i) {
        return Member.builder()
                .name("Test" + i)
                .pwd("test" + i)
                .status("0")
                .build();
    }
}
