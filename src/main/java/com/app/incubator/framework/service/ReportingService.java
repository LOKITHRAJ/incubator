package com.app.incubator.framework.service;

import com.app.incubator.framework.domain.Configuration;
import com.app.incubator.framework.thread.TaskExecutorService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;


@Service
public class ReportingService {

    @Autowired
    ValidationService validationService;

    @Autowired
    TaskExecutorService taskExecutorService;

    @Autowired
    DataMappingService dataMappingService;

    @Autowired
    NotificationService notificationService;

    @Autowired
    FileCreator fileCreator;

    public String getAppData() {

        //Blank workbook
        XSSFWorkbook workbook = new XSSFWorkbook();

        //Create a blank sheet
        XSSFSheet sheet = workbook.createSheet("App Data");

        //This data needs to be written (Object[])
        Map<String, Object[]> data = new TreeMap<String, Object[]>();
        data.put("2", new Object[] {"Amit", "Shukla"});
        data.put("3", new Object[] {"Lokesh", "Gupta"});
        data.put("4", new Object[] {"John", "Adwards"});
        data.put("5", new Object[] {"Brian", "Schultz"});

        //Iterate over data and write to sheet
        Set<String> keyset = data.keySet();
        int rownum = 0;
        for (String key : keyset)
        {
            Row row = sheet.createRow(rownum++);
            Object [] objArr = data.get(key);
            int cellNum = 0;
            for (Object obj : objArr)
            {
                Cell cell = row.createCell(cellNum++);
                if(obj instanceof String)
                    cell.setCellValue((String)obj);
                else if(obj instanceof Integer)
                    cell.setCellValue((Integer)obj);
            }
        }

        try {
            //Write the workbook in file system
//            FileOutputStream out = new FileOutputStream(resource.getFile());
//            workbook.write(out);
//            out.close();

        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return "Successfully on disk.";
    }


    public Configuration apply(Configuration configuration) {

        // 1. Validate the config
        validationService.validate(configuration);

        // 2 Create thread based on Tasks
        taskExecutorService.execute(configuration);

        // 3 Mapping the Data
        dataMappingService.map(configuration);

        // 4 Create File Extra based on file
        fileCreator.createFileObject(configuration);

        // 5.Notify in @Async
        notificationService.notify(configuration);

        return configuration;
    }
}
