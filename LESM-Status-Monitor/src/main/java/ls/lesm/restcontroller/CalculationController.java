package ls.lesm.restcontroller;

import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ls.lesm.model.DateRange;
import ls.lesm.model.MasterEmployeeDetails;
import ls.lesm.model.Sub_Profit;
import ls.lesm.repository.DesignationsRepository;
import ls.lesm.repository.MasterEmployeeDetailsRepository;
import ls.lesm.repository.Sub_ProfitRepository;
import ls.lesm.service.impl.BusinessCalculation;
import ls.lesm.service.impl.CalculationsUptoDate;
import ls.lesm.service.impl.CountryHeadCalculation;
import ls.lesm.service.impl.DefaultCalculationsServiceImpl;
import ls.lesm.service.impl.GeneralManagerCalculation;
import ls.lesm.service.impl.LeadCalculation;
import ls.lesm.service.impl.ManagerCalculation;
import ls.lesm.service.impl.ManagingDirectorCalculation;
import ls.lesm.service.impl.VicePresidentCalculation;

@RestController
@RequestMapping("/Total")
@CrossOrigin("*")
public class CalculationController {

	@Autowired
	BusinessCalculation businessCalculation;

	@Autowired
	LeadCalculation leadCalculation;

	@Autowired
	ManagerCalculation managerCalculation;

	@Autowired
	GeneralManagerCalculation generalManagerCalculation;

	@Autowired
	CountryHeadCalculation countryHeadCalculation;

	@Autowired
	ManagingDirectorCalculation managingDirectorCalculation;

	@Autowired
	MasterEmployeeDetailsRepository masterEmployeeDetailsRepository;

	@Autowired
	Sub_ProfitRepository sub_ProfitRepository;

	@Autowired
	VicePresidentCalculation vicePresidentCalculation;

	@Autowired
	DesignationsRepository designationsRepository;

	@Autowired
	CalculationsUptoDate calculationsUptoDate;

	@Autowired
	DefaultCalculationsServiceImpl defaultCalculationsServiceImpl;

	@PostMapping("/consultant/{id}")
	public Double s(@PathVariable int id, @RequestBody DateRange dateRange)

	{

		Double profit_or_loss = calculationsUptoDate.lesmCalculations(id, dateRange.getFromDate(),
				dateRange.getToDate());
		// Double profit_or_loss = businessCalculation.Employee_cal(id,
		// dateRange.getFromDate(), dateRange.getToDate());

		return profit_or_loss;

	}

//	
//	@PostMapping("/lead")
//	public Double lead( Principal principal)
//	{
//		String emp1=principal.getName();
//		MasterEmployeeDetails emp2=this.masterEmployeeDetailsRepository.findByLancesoft(emp1);
//		int id=emp2.getEmpId();
//		Double profit_or_loss=leadCalculation.lead_cal(id);
//	       Optional<Sub_Profit> sp = sub_ProfitRepository.findBymasterEmployeeDetails_Id(id);
//
//	        Sub_Profit spt = sp.get();
//
//	        Double subprofit = spt.getSubprofit();
//
//	        return (subprofit);
//		
//		//return profit_or_loss;
//		
//	}

	@PostMapping("/lead")
	public ResponseEntity<Double> lead(Principal principal, @RequestBody DateRange dateRange) {

		try {

			String emp1 = principal.getName();
			MasterEmployeeDetails emp2 = this.masterEmployeeDetailsRepository.findByLancesoft(emp1);
			int id = emp2.getEmpId();
			leadCalculation.lead_cal(id, dateRange.getFromDate(), dateRange.getToDate());
			Optional<Sub_Profit> sp = sub_ProfitRepository.findBymasterEmployeeDetails_Id(id);

			Sub_Profit spt = sp.get();

			Double subprofit = spt.getSubprofit();

			// return (subprofit);
			return new ResponseEntity<Double>((double) (int) (double) subprofit, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Double>(1.0, HttpStatus.OK);
		}

		// return profit_or_loss;

	}

	@PostMapping("/managerCalculation")
	public ResponseEntity<Double> managerCalculation(Principal principal, @RequestBody DateRange dateRange) {
		try {

			String loggedIn = principal.getName();
			MasterEmployeeDetails employee = this.masterEmployeeDetailsRepository.findByLancesoft(loggedIn);
			int id = employee.getEmpId();
			managerCalculation.manager_cal(id, dateRange.getFromDate(), dateRange.getToDate());

			Optional<Sub_Profit> sp = sub_ProfitRepository.findBymasterEmployeeDetails_Id(id);

			Sub_Profit spt = sp.get();

			Double subprofit = spt.getSubprofit();

			return new ResponseEntity<Double>((double) (int) (double) subprofit, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Double>(1.0, HttpStatus.OK);
		}

		// return profit_or_loss;

	}

	@PostMapping("/generalManagerCalculation")
	public ResponseEntity<Double> generalManagerCalculationManagerCalculation(Principal principal,
			@RequestBody DateRange dateRange) {
		try {
			String loggedIn = principal.getName();
			MasterEmployeeDetails employee = this.masterEmployeeDetailsRepository.findByLancesoft(loggedIn);
			int id = employee.getEmpId();
			generalManagerCalculation.generalManagercal(id, dateRange.getFromDate(), dateRange.getToDate());

			Optional<Sub_Profit> sp = sub_ProfitRepository.findBymasterEmployeeDetails_Id(id);

			Sub_Profit spt = sp.get();

			Double subprofit = spt.getSubprofit();

			return new ResponseEntity<Double>((double) (int) (double) subprofit, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Double>(1.0, HttpStatus.OK);
		}

		// return profit_or_loss;

	}

//	@PostMapping("/countryHeadCalculation/{id}")
//	public ResponseEntity<Double>  countryHeadCalculation(@PathVariable int id, @RequestBody DateRange dateRange) {
////		String loggedIn = principal.getName();
////		MasterEmployeeDetails employee = this.masterEmployeeDetailsRepository.findByLancesoft(loggedIn);
////		int id = employee.getEmpId();
//		countryHeadCalculation.countryHeadCal(id, dateRange.getFromDate(), dateRange.getToDate());
//
//		Optional<Sub_Profit> sp = sub_ProfitRepository.findBymasterEmployeeDetails_Id(id);
//
//		Sub_Profit spt = sp.get();
//
//		Double subprofit = spt.getSubprofit();
//
//		return  new ResponseEntity<Double>(subprofit, HttpStatus.OK);
//
//		// return profit_or_loss;
//
//	}

	@PostMapping("/countryHeadCalculation")
	public ResponseEntity<Double> countryHeadCalculation(Principal principal, @RequestBody DateRange dateRange) {

		try {

			String loggedIn = principal.getName();
			MasterEmployeeDetails employee = this.masterEmployeeDetailsRepository.findByLancesoft(loggedIn);
			int id = employee.getEmpId();
			countryHeadCalculation.countryHeadCal(id, dateRange.getFromDate(), dateRange.getToDate());

			Optional<Sub_Profit> sp = sub_ProfitRepository.findBymasterEmployeeDetails_Id(id);

			Sub_Profit spt = sp.get();

			Double subprofit = spt.getSubprofit();

			return new ResponseEntity<Double>((double) (int) (double) subprofit, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Double>(1.0, HttpStatus.OK);
		}
		// return profit_or_loss;

	}

	@PostMapping("/managingDirectorCalculation")
	public ResponseEntity<Double> managingDirectorCalculation(Principal principal, @RequestBody DateRange dateRange) {
		try {
			String loggedIn = principal.getName();
			MasterEmployeeDetails employee = this.masterEmployeeDetailsRepository.findByLancesoft(loggedIn);
			int id = employee.getEmpId();
			managingDirectorCalculation.managingDirectorCal(id, dateRange.getFromDate(), dateRange.getToDate());
			Optional<Sub_Profit> sp = sub_ProfitRepository.findBymasterEmployeeDetails_Id(id);

			Sub_Profit spt = sp.get();

			Double subprofit = spt.getSubprofit();

			return new ResponseEntity<Double>((double) (int) (double) subprofit, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Double>(1.0, HttpStatus.OK);
		}

	}

//	@GetMapping("/managingDirectorCalculation/{id}")
//	public Double managingDirectorCalculation(@PathVariable int id, @RequestBody DateRange dateRange) {
////		String loggedIn = principal.getName();
////		MasterEmployeeDetails employee = this.masterEmployeeDetailsRepository.findByLancesoft(loggedIn);
////		int id = employee.getEmpId();
//		managingDirectorCalculation.managingDirectorCal(id, dateRange.getFromDate(), dateRange.getToDate());
//		Optional<Sub_Profit> sp = sub_ProfitRepository.findBymasterEmployeeDetails_Id(id);
//
//		Sub_Profit spt = sp.get();
//
//		Double subprofit = spt.getSubprofit();
//
//		return (subprofit);
//	}

	// add ons
	@PostMapping("/vicePresident")
	public ResponseEntity<Double> vicePresident(Principal principal, @RequestBody DateRange dateRange) {
		try {
			String loggedIn = principal.getName();
			MasterEmployeeDetails employee = this.masterEmployeeDetailsRepository.findByLancesoft(loggedIn);
			int id = employee.getEmpId();
			vicePresidentCalculation.vicePresident(id, dateRange.getFromDate(), dateRange.getToDate());

			Optional<Sub_Profit> sp = sub_ProfitRepository.findBymasterEmployeeDetails_Id(id);

			Sub_Profit spt = sp.get();

			Double subprofit = spt.getSubprofit();

			return new ResponseEntity<Double>((double) (int) (double) subprofit, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Double>(1.0, HttpStatus.OK);
		}

	}

	@GetMapping("/SuperAdminDefaultCalculator")

	public void superAdminDefaultCalculation() {

		try {
			defaultCalculationsServiceImpl.defaultCalculationCall();
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

}