package org.openmrs.module.ssemrreports.reporting.library.cohorts;

import java.util.Date;
import java.util.List;

import org.openmrs.Location;
import org.openmrs.module.ssemrreports.reporting.library.queries.CommonQueries;
import org.openmrs.module.ssemrreports.reporting.library.queries.PnsRegisterQueries;
import org.openmrs.module.reporting.cohort.definition.CohortDefinition;
import org.openmrs.module.reporting.cohort.definition.SqlCohortDefinition;
import org.openmrs.module.reporting.evaluation.parameter.Parameter;
import org.springframework.stereotype.Component;

@Component
public class BaseCohortQueries {
	
	public CohortDefinition getPatientsWhoQualifiesForAgivenEncounter(List<Integer> encounterIds) {
		SqlCohortDefinition cd = new SqlCohortDefinition();
		cd.setName("Base patients who will constitute any set");
		cd.addParameter(new Parameter("startDate", "startDate", Date.class));
		cd.addParameter(new Parameter("endDate", "endDate", Date.class));
		cd.addParameter(new Parameter("location", "location", Location.class));
		
		cd.setQuery(CommonQueries.getBasePatientsBasedOnEncounter(encounterIds));
		
		return cd;
	}
	
	public CohortDefinition getPatientsWhoMissedAppointmentByDays(int days) {
		SqlCohortDefinition cd = new SqlCohortDefinition();
		cd.setName("Missed appointment patient set");
		cd.addParameter(new Parameter("startDate", "startDate", Date.class));
		cd.addParameter(new Parameter("endDate", "endDate", Date.class));
		cd.addParameter(new Parameter("location", "location", Location.class));
		cd.setQuery(CommonQueries.getMissedAppointmentPatientSetByDays(days));
		return cd;
	}
	
	public CohortDefinition getPatientsEnrolledIntoProgramByEndOfReportingPeriod(int programId) {
		SqlCohortDefinition cd = new SqlCohortDefinition();
		cd.setName("Number of patients enrolled into program by end of reporting period");
		cd.addParameter(new Parameter("endDate", "endDate", Date.class));
		cd.addParameter(new Parameter("location", "location", Location.class));
		cd.setQuery(CommonQueries.getPatientsInProgramByEndOfReportingPeriod(programId));
		
		return cd;
	}
	
	public CohortDefinition getPatientsWhoHaveEncountersForPnsReports(List<Integer> list) {
		SqlCohortDefinition cd = new SqlCohortDefinition();
		cd.setName("Number of patients with encounters for PNS reports");
		cd.addParameter(new Parameter("endDate", "endDate", Date.class));
		cd.addParameter(new Parameter("location", "location", Location.class));
		cd.setQuery(PnsRegisterQueries.getPnsRegisterBaseQuery(list));
		
		return cd;
	}
}
