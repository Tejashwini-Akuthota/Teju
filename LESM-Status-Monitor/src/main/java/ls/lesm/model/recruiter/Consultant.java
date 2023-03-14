package ls.lesm.model.recruiter;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ls.lesm.model.MasterEmployeeDetails;


@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Consultant {
	
	@Id
	//@GeneratedValue(generator = "cond_id_gen",strategy = GenerationType.AUTO)
	private String conId;
	
	private String consultanName;
	
	private String email;
	
	private Long mobileNo;
	
	private String employeeType;
	
	private LocalDate joiningDate;
	
	private LocalDate contractEndAt;
	
	private String clientName;
	
	private long clientBilling;
	
	private long salary;
	
	private boolean isExit;
	
	private LocalDate exitAt;
	
	@JsonIgnore
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="emp_rec_id")
	private MasterEmployeeDetails masterEmployeeDetails;
	
	

}
