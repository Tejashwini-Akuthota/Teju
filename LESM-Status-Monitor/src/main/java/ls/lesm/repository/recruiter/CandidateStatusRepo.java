package ls.lesm.repository.recruiter;

import org.springframework.data.jpa.repository.JpaRepository;

import ls.lesm.model.recruiter.CandidateStatus;

public interface CandidateStatusRepo extends JpaRepository<CandidateStatus, Integer> {

}
