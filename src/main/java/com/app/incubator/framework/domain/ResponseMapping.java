package com.app.incubator.framework.domain;

import lombok.Data;

@Data
public class ResponseMapping {

    private String responsePayload;

    private ExtractType extractType;

    private ExcelExtraction excelExtraction;

    private PdfExtraction pdfExtraction;

}
