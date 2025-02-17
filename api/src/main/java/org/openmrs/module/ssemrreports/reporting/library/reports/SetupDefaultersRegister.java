package org.openmrs.module.ssemrreports.reporting.library.reports;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.openmrs.module.ssemrreports.manager.SSEMRDataExportManager;
import org.openmrs.module.ssemrreports.reporting.library.cohorts.BaseCohortQueries;
import org.openmrs.module.ssemrreports.reporting.library.datasets.DefaultersDatasetDefinition;
import org.openmrs.module.ssemrreports.reporting.utils.SSEMRReportUtils;
import org.openmrs.module.ssemrreports.reporting.utils.constants.reports.shared.SharedReportConstants;
import org.openmrs.module.ssemrreports.reporting.utils.constants.templates.shared.SharedTemplatesConstants;
import org.openmrs.module.reporting.ReportingException;
import org.openmrs.module.reporting.evaluation.parameter.Mapped;
import org.openmrs.module.reporting.report.ReportDesign;
import org.openmrs.module.reporting.report.definition.ReportDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SetupDefaultersRegister extends SSEMRDataExportManager {
	
	private final DefaultersDatasetDefinition defaultersDatasetDefinition;
	
	private final BaseCohortQueries baseCohortQueries;
	
	@Autowired
	public SetupDefaultersRegister(DefaultersDatasetDefinition defaultersDatasetDefinition,
	    BaseCohortQueries baseCohortQueries) {
		this.defaultersDatasetDefinition = defaultersDatasetDefinition;
		this.baseCohortQueries = baseCohortQueries;
	}
	
	@Override
	public String getExcelDesignUuid() {
		return SharedTemplatesConstants.DEFAULTER_LIST_TEMPLATE;
	}
	
	@Override
	public String getUuid() {
		return SharedReportConstants.DEFAULTER_LIST_UUID;
	}
	
	@Override
	public String getName() {
		return "Patient defaulter list on Date";
	}
	
	@Override
	public String getDescription() {
		return "Missed appointment for 14 days";
	}
	
	@Override
	public ReportDefinition constructReportDefinition() {
		ReportDefinition rd = new ReportDefinition();
		rd.setUuid(getUuid());
		rd.setName(getName());
		rd.setDescription(getDescription());
		rd.addParameters(defaultersDatasetDefinition.getParameters());
		rd.addDataSetDefinition("DAR",
		    Mapped.mapStraightThrough(defaultersDatasetDefinition.constructDefaulterAppointmentRegisterDefinition()));
		rd.setBaseCohortDefinition(SSEMRReportUtils.map(baseCohortQueries.getPatientsWhoMissedAppointmentByDays(13),
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
			reportDesign = createXlsReportDesign(reportDefinition, "darRegister.xls", "Report for listing Defaulters",
			    getExcelDesignUuid(), null);
			Properties props = new Properties();
			props.put("repeatingSections", "sheet:1,row:4,dataset:DAR");
			props.put("sortWeight", "5000");
			reportDesign.setProperties(props);
		}
		catch (IOException e) {
			throw new ReportingException(e.toString());
		}
		
		return Arrays.asList(reportDesign);
	}
}
