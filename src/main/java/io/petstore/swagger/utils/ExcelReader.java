package io.petstore.swagger.utils;

import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

public class ExcelReader {

    public static Map<String, String> parseXlsx(String filePath, Integer index, String page) {
        if (index==null){
            index = 1;
        }
        Map<String, String> rowMap = new HashMap<>();

        try (FileInputStream file = new FileInputStream(filePath)) {
            Workbook workbook = WorkbookFactory.create(file);
            Sheet sheet = workbook.getSheet(page);

            Row headerRow = sheet.getRow(0);
            int totalCols = headerRow.getLastCellNum();

            if (index > 0 && index <= sheet.getLastRowNum()) {
                Row currentRow = sheet.getRow(index);

                for (int colIndex = 0; colIndex < totalCols; colIndex++) {
                    String colName = headerRow.getCell(colIndex).getStringCellValue();
                    String cellValue ;
                    Cell cell = currentRow.getCell(colIndex, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);

                    switch (cell.getCellType()) {
                        case STRING:
                            cellValue = cell.getStringCellValue();
                            break;
                        case NUMERIC:
                            cellValue = String.valueOf(cell.getNumericCellValue());
                            break;
                        case BOOLEAN:
                            cellValue = String.valueOf(cell.getBooleanCellValue());
                            break;
                        default:
                            cellValue = "";
                    }

                    rowMap.put(colName, cellValue);
                }
            } else {
                throw new IllegalArgumentException("Index is out of range: " + index + ". Index range have to be > 0 and < max row");
            }

            workbook.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            throw new IllegalArgumentException("Error while parsing Excel file", e);
        }
        return rowMap;
    }

}