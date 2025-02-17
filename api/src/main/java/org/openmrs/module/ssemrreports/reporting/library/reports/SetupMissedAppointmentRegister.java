package org.openmrs.module.ssemrreports.reporting.library.reports;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.openmrs.module.ssemrreports.manager.SSEMRDataExportManager;
import org.openmrs.module.ssemrreports.reporting.library.cohorts.BaseCohortQueries;
import org.openmrs.module.ssemrreports.reporting.library.datasets.MissedAppointmentDatasetDefinition;
import org.openmrs.module.ssemrreports.reporting.utils.SSEMRReportUtils;
import org.openmrs.module.ssemrreports.reporting.utils.constants.reports.tb.TbReportsConstants;
import org.openmrs.module.ssemrreports.reporting.utils.constants.templates.tb.TbTemplatesConstants;
import org.openmrs.module.reporting.ReportingException;
import org.openmrs.module.reporting.evaluation.parameter.Mapped;
import org.openmrs.module.reporting.report.ReportDesign;
import org.openmrs.module.reporting.report.definition.ReportDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SetupMissedAppointmentRegister extends SSEMRDataExportManager {
	
	private final MissedAppointmentDatasetDefinition missedAppointmentDatasetDefinition;
	
	private final BaseCohortQueries baseCohortQueries;
	
	@Autowired
	public SetupMissedAppointmentRegister(MissedAppointmentDatasetDefinition missedAppointmentDatasetDefinition,
	    BaseCohortQueries baseCohortQueries) {
		this.missedAppointmentDatasetDefinition = missedAppointmentDatasetDefinition;
		this.baseCohortQueries = baseCohortQueries;
	}
	
	@Override
	public String getExcelDesignUuid() {
		return TbTemplatesConstants.TB_MISSED_APPOINTMENT_TEMPLATE_UUID;
	}
	
	@Override
	public String getUuid() {
		return TbReportsConstants.TB_MISSED_APPOINTMENT_REGISTER_UUID;
	}
	
	@Override
	public String getName() {
		return "Missed appointments on Date";
	}
	
	@Override
	public String getDescription() {
		return "Missed appointments on Date Report";
	}
	
	@Override
	public ReportDefinition constructReportDefinition() {
		ReportDefinition rd = new ReportDefinition();
		rd.setUuid(getUuid());
		rd.setName(getName());
		rd.setDescription(getDescription());
		rd.addParameters(missedAppointmentDatasetDefinition.getParameters());
		rd.addDataSetDefinition("MAR",
		    Mapped.mapStraightThrough(missedAppointmentDatasetDefinition.constructMissedAppointmentRegisterDefinition()));
		rd.setBaseCohortDefinition(SSEMRReportUtils.map(baseCohortQueries.getPatientsWhoMissedAppointmentByDays(0),
		    "startDate=${startDate},endDate=${endDate+23h},location=${location}"));
		return rd;
	}
	
	@Override
	public String getVersion() {
		return "1.0-SNAPSHOT";
	}
	
	@Override
	public List<ReportDesign> constructReportDesigns(ReportDefinition reportDefinition) {
		ReportDesign reportDesign = null;
		try {
			reportDesign = createXlsReportDesign(reportDefinition, "marRegister.xls", "Missed Appointment Register Report",
			    getExcelDesignUuid(), null);
			Properties props = new Properties();
			props.put("repeatingSections", "sheet:1,row:4,dataset:MAR");
			props.put("sortWeight", "5000");
			reportDesign.setProperties(props);
		}
		catch (IOException e) {
			throw new ReportingException(e.toString());
		}
		
		return Arrays.asList(reportDesign);
	}
}
