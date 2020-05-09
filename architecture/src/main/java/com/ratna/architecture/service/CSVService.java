package com.ratna.architecture.service;

import java.io.PrintWriter;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opencsv.CSVWriter;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvException;
import com.ratna.architecture.filereaders.ArchitectureCSVReader;
import com.ratna.architecture.testmodel.Audience;
import com.ratna.architecture.transferobjects.ArchitectureResponse;
import com.ratna.architecture.utility.ArchitectureUtility;

@Service
public class CSVService {

	@Autowired
	ArchitectureCSVReader csvReader;
	private Logger logger = LoggerFactory.getLogger(CSVService.class);

	public ArchitectureResponse fetchCsvDetails() {
		logger.info(ArchitectureUtility.enteredInto("fetchCsvDetails"));
		logger.info(ArchitectureUtility.exitedFrom("fetchCsvDetails"));
		return csvReader.csvReader();
	}

	@SuppressWarnings("unchecked")
	public void downloadCSVFile(PrintWriter writer) {
		logger.info(ArchitectureUtility.enteredInto("downloadCSVFile"));
		try {
			List<Audience> response = (List<Audience>) csvReader.downloadCSVFile().getResponse();
			ColumnPositionMappingStrategy<Audience> mapStrategy = new ColumnPositionMappingStrategy<>();

			mapStrategy.setType(Audience.class);

			String[] columns = new String[] { "id", "name" };
			mapStrategy.setColumnMapping(columns);

			StatefulBeanToCsv<Audience> btcsv = new StatefulBeanToCsvBuilder<Audience>(writer)
					.withQuotechar(CSVWriter.NO_QUOTE_CHARACTER).withMappingStrategy(mapStrategy).withSeparator(',')
					.build();

			btcsv.write(response);

		} catch (CsvException ex) {

			logger.error("Error mapping Bean to CSV", ex);
		}
		logger.info(ArchitectureUtility.exitedFrom("downloadCSVFile"));
	}
}