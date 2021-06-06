package controller;

import dao.DAO;
import dao.DaoImpl;
import entity.User;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ExcelController {

    public void toExcel(List listForExport) throws IOException {
        List<User> list = listForExport;
        DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy");

        // Создаем лист -вкладку sheet
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Список пользователей");

        // Инициализируем вкладку
        int rownum = 0;
        Cell cell;
        HSSFFont font = workbook.createFont();
        font.setBold(true);
        HSSFCellStyle style = workbook.createCellStyle();
        style.setFont(font);
        Row row = sheet.createRow(rownum);

        /* ИНИЦИАЛИЗИРУЕМ CELLы */
        // Столбик id
        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("id");
        cell.setCellStyle(style);
        // login
        cell = row.createCell(1, CellType.STRING);
        cell.setCellValue("login");
        cell.setCellStyle(style);
        // password
        cell = row.createCell(2, CellType.STRING);
        cell.setCellValue("password");
        cell.setCellStyle(style);
        // accessLvl
        cell = row.createCell(3, CellType.STRING);
        cell.setCellValue("accessLvl");
        cell.setCellStyle(style);
        // dateOfCreation
        cell = row.createCell(4, CellType.STRING);
        cell.setCellValue("dateOfCreation");
        cell.setCellStyle(style);
        // dateOfModification
        cell = row.createCell(5, CellType.STRING);
        cell.setCellValue("dateOfModification");
        cell.setCellStyle(style);

        /* ЗАПОЛНЯЕМ ТАБЛИЦУ */
        for (User usr : list) {
            rownum++;
            row = sheet.createRow(rownum);

            // id (A)
            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue(usr.getId());
            // login (B)
            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue(usr.getLogin());
            // password (C)
            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue(usr.getPassword());
            // accessLvl (D)
            cell = row.createCell(3, CellType.STRING);
            cell.setCellValue(usr.getAccessLvl().toString());
            // dateOfCreation (C)
            cell = row.createCell(4, CellType.STRING);
            cell.setCellValue(formatterDate.format(usr.getDateOfCreation()));
            // dateOfModification (D)
            cell = row.createCell(5, CellType.STRING);
            cell.setCellValue(formatterDate.format(usr.getDateOfModification()));
        }

        // Выгружаем файл
        File file = new File("/ListUser.xls");
        file.getParentFile().mkdirs();

        FileOutputStream outFile = new FileOutputStream(file);
        workbook.write(outFile);
        System.out.println("Created file: " + file.getAbsolutePath());
    }
}
