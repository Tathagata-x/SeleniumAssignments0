package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.microsoft.playwright.Page;

public class TestUtils {

    private static final Properties prop = new Properties();
    public static String TestDataSheetPath;

    static {
        try (FileInputStream config = new FileInputStream("src/resource/java/com/qa/config/config.properties")) {
            prop.load(config);
            TestDataSheetPath = prop.getProperty("testdata_path");
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config", e);
        }
    }

    public static Object[][] getTestData(String sheetName) throws IOException {
        try (FileInputStream fis = new FileInputStream(TestDataSheetPath); 
             Workbook workbook = WorkbookFactory.create(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                throw new IllegalArgumentException("Sheet '" + sheetName + "' not found");
            }

            int rowCount = sheet.getLastRowNum();
            Row headerRow = sheet.getRow(0);

            if (headerRow == null) {
                throw new IllegalStateException("Header row (row 0) is missing");
            }

            int colCount = headerRow.getLastCellNum();
            Object[][] data = new Object[rowCount][colCount];

            for (int i = 0; i < rowCount; i++) {
                Row currentRow = sheet.getRow(i + 1); // Data starts from row 1

                for (int k = 0; k < colCount; k++) {
                    Cell cell = currentRow != null ? currentRow.getCell(k, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL) : null;
                    data[i][k] = (cell != null) ? getCellValueAsString(cell) : "";
                }
            }

            return data;
        }
    }

    // Helper method to handle different cell types
    private static String getCellValueAsString(Cell cell) {
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue().trim();
            case NUMERIC:
                return String.valueOf((int) cell.getNumericCellValue());
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            default:
                return "";
        }
    }

    public static void takeScreenshot(Page page, String screenshotName) {
        try {
            Path dir = Paths.get("./Screenshots");
            if (!Files.exists(dir)) {
                Files.createDirectories(dir);
            }

            String timestamp = new SimpleDateFormat("MMddyyyyHHmmss").format(new Date());
            String filePath = dir.resolve(screenshotName + "_" + timestamp + ".png").toString();

            page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(filePath)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}