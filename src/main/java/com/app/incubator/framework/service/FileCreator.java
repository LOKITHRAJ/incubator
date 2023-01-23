package com.app.incubator.framework.service;

import com.app.incubator.framework.domain.Configuration;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.*;

@Service
public class FileCreator {


    public void createFileObject(Configuration configuration) {

        if (!configuration.getStatus().isValid()) {
            return;
        }

        System.out.println(configuration);

        try {

            Map<String, Object[]> map = new HashMap<>();
            Set<String> hashSet = new LinkedHashSet<>();

            configuration.getTasks().forEach(task -> {
                hashSet.addAll((task.getResponseMapping().getExcelExtraction().getColumnsMeta().keySet()));
            });

            map.put("1", new Object[]{hashSet});

            createExcelFile(map);

        } catch (Exception e) {
            e.printStackTrace();
            configuration.getStatus().setValid(false);
        }

    }

    private void createExcelFile(Map<String, Object[]> appData) {


        // workbook object
        XSSFWorkbook workbook = new XSSFWorkbook();

        // spreadsheet object
        XSSFSheet spreadsheet = workbook.createSheet("App Data ");

        // creating a row object
        XSSFRow row;

        // This data needs to be written (Object[])
        // Map<String, Object[]> appData = new LinkedHashMap<>();

        // appData.put("1", new Object[]{"ID", "TITLE", "DESCRIPTION"});

       // appData.put("2", map.values().toArray(new Object[0]));

        Set<String> keyId = appData.keySet();

        int rowid = 0;

        for (String key : keyId) {

            row = spreadsheet.createRow(rowid++);
            Object[] objectArr = appData.get(key);
            int cellid = 0;

            for (Object obj : objectArr) {
                Cell cell = row.createCell(cellid++);
                cell.setCellValue(obj.toString());
            }
        }

        try {
            FileOutputStream out = new FileOutputStream(new File("appData.xlsx"));

            workbook.write(out);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
