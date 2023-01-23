package com.app.incubator.utils;

import com.app.incubator.controller.DemoController;
import com.app.incubator.framework.domain.Configuration;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Runner {

    public static void main(String[] args) throws IOException {

       // 1. Object to Json
       // objectToJson();
        // classObjectToJson();

       // 2. Schema validation
        // schemaValidation();

        writeDataIntoExcel();


    }

    private static void writeDataIntoExcel() throws IOException {

        // workbook object
        XSSFWorkbook workbook = new XSSFWorkbook();

        // spreadsheet object
        XSSFSheet spreadsheet  = workbook.createSheet(" Student Data ");

        // creating a row object
        XSSFRow row;

        // This data needs to be written (Object[])
        Map<String, Object[]> studentData  = new TreeMap<String, Object[]>();

        studentData.put(
                "1",
                new Object[] { "Roll No", "NAME", "Year" });

        studentData.put("2", new Object[] { "128", "Aditya",
                "2nd year" });

        Set<String> keyid = studentData.keySet();

        int rowid = 0;

        // writing the data into the sheets...

        for (String key : keyid) {

            row = spreadsheet.createRow(rowid++);
            Object[] objectArr = studentData.get(key);
            int cellid = 0;

            for (Object obj : objectArr) {
                Cell cell = row.createCell(cellid++);
                cell.setCellValue((String)obj);
            }
        }

        // .xlsx is the format for Excel Sheets...
        // writing the workbook into the file...
        FileOutputStream out = new FileOutputStream(new File("GFGsheet.xlsx"));

        workbook.write(out);
        out.close();

    }

    private static void schemaValidation() throws IOException {

        InputStream inputStream = DemoController.class.getClassLoader().getResourceAsStream("schema/config-schema.json");
        JsonSchema schema = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V4).getSchema(inputStream);

        ObjectMapper mapper = new ObjectMapper();
        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.KEBAB_CASE);
        JsonNode jsonNode = mapper.readTree(mapper.writeValueAsString(new Configuration()));

        Set<ValidationMessage> errors = schema.validate(jsonNode);

        errors.forEach(v -> {
            System.out.println("Validation Error*****");
            System.out.println(v);
        });

      /*  ObjectMapper mapper = new ObjectMapper();
        JsonSchemaFactory factory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V4);
        JsonSchema jsonSchema = factory.getSchema(
                Runner.class.getResourceAsStream("config-schema.json"));
        JsonNode jsonNode = mapper.readTree(
                Runner.class.getResourceAsStream("extract.json"));
        Set<ValidationMessage> errors = jsonSchema.validate(jsonNode);
        System.out.println(errors);*/
    }
    private static void objectToJson() {

        ObjectMapper mapper = new ObjectMapper();
        Configuration config = new Configuration();

        try {
            mapper.writeValue(new File("extract.json"), config);
            String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(config);
            System.out.println(jsonString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static void classObjectToJson() {

        ObjectMapper mapper = new ObjectMapper();
        Configuration config = new Configuration();

        Map<String, String> columns = new HashMap<>();
        columns.put("a","apple");
        columns.put("b","balls");

        try {
            String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(columns);
            System.out.println(jsonString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
