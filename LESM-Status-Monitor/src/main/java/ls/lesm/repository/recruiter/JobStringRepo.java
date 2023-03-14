package ls.lesm.repository.recruiter;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ls.lesm.model.MasterEmployeeDetails;
import ls.lesm.model.recruiter.JobString;

public interface JobStringRepo extends JpaRepository<JobString, Integer> {

	List<JobString> findAllByTicketStatus(boolean b);

	List<JobString> findAllByMasterEmployeeDetailsAndTicketStatus(MasterEmployeeDetails loggedInEmp, boolean b);

	List<JobString> findByStringCreatedByAndTicketStatus(String string, boolean flag);

	List<JobString> findByStringCreatedByAndTicketStatusOrMasterEmployeeDetails(String string, boolean flag, MasterEmployeeDetails employee);

	List<JobString> findByStringCreatedByAndTicketStatusAndMasterEmployeeDetails(String lancesoft, boolean flag,
			MasterEmployeeDetails employee);

	List<JobString> findByStringCreatedByOrMasterEmployeeDetailsAndTicketStatus(String lancesoft,
			MasterEmployeeDetails employee, boolean flag);

	List<JobString> findByMasterEmployeeDetailsAndTicketStatus(MasterEmployeeDetails employee, boolean flag);

	//void findAllByMasterEmployeeDetails();

}
