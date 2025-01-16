package com.precojusto.granja.services;

import com.precojusto.granja.model.Customer;
import com.precojusto.granja.model.Duck;
import com.precojusto.granja.model.Sale;
import com.precojusto.granja.repositories.DuckRepository;
import com.precojusto.granja.repositories.SaleRepository;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExcelReportService {
    @Autowired
    private SaleRepository saleRepository;
    @Autowired
    private DuckRepository duckRepository;
    public ByteArrayInputStream generateExcelReport(List<Duck> ducks) throws IOException {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Gerenciamento de Patos");

            Row titleRow = sheet.createRow(0);
            Cell titleCell = titleRow.createCell(0);

            titleCell.setCellValue("Gerenciamento de Patos");

            CellStyle titleStyle = workbook.createCellStyle();
            Font titleFont = workbook.createFont();
            titleFont.setBold(true);
            titleFont.setFontHeightInPoints((short) 32);
            titleStyle.setFont(titleFont);

            titleCell.setCellStyle(titleStyle);

            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 5));

            for (int i = 0; i <= 4; i++) {
                sheet.autoSizeColumn(i);
            }

            Row header = sheet.createRow(1);
            CellStyle cellStyle = workbook.createCellStyle();
            Font cellFont = workbook.createFont();
            cellFont.setBold(true);
            cellFont.setFontHeightInPoints((short) 16);
            cellStyle.setFont(cellFont);
            cellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
            cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            Cell nameCell = header.createCell(0);
            nameCell.setCellValue("Nome");
            nameCell.setCellStyle(cellStyle);

            Cell statusCell = header.createCell(1);
            statusCell.setCellValue("Status");
            statusCell.setCellStyle(cellStyle);

            Cell customerCell = header.createCell(2);
            customerCell.setCellValue("Cliente");
            customerCell.setCellStyle(cellStyle);

            Cell customerTypeCell = header.createCell(3);
            customerTypeCell.setCellValue("Tipo do Cliente");
            customerTypeCell.setCellStyle(cellStyle);

            Cell valueCell = header.createCell(4);
            valueCell.setCellValue("Valor");
            valueCell.setCellStyle(cellStyle);

            int rowIndex = 2;
            for (Duck duck : ducks) {
                if (duck.getMother() == null) {
                    rowIndex = getDuckHierarchy(duck, sheet, rowIndex, 0);
                }
            }
            for (int i = 0; i <= 4; i++) sheet.autoSizeColumn(i);

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        }
    }

    private int getDuckHierarchy(Duck duck, Sheet sheet, int rowIndex, int level) {
        Row row = sheet.createRow(rowIndex++);
        Cell nameCell = row.createCell(0);

        StringBuilder indentedName = new StringBuilder();
        for (int i = 0; i < level; i++) {
            indentedName.append("        ");
        }
        indentedName.append(duck.getName());
        nameCell.setCellValue(indentedName.toString());

        CellStyle style = sheet.getWorkbook().createCellStyle();
        Font font = sheet.getWorkbook().createFont();
        style.setFont(font);
        nameCell.setCellStyle(style);

        Cell statusCell = row.createCell(1);
        statusCell.setCellValue(duck.getAvailable() ? "Disponível" : "Vendido");

        if (!duck.getAvailable()) {
            Sale sale = findSaleByDuck(duck);
            if (sale != null) {
                Customer customer = sale.getCustomer();
                row.createCell(2).setCellValue(customer.getName());
                row.createCell(3).setCellValue(customer.getHasDiscount() ? "com Desconto" : "sem Desconto");
                row.createCell(4).setCellValue("R$ " + duck.getSaleValue());
            }
        }

        List<Duck> children = getChildren(duck);
        for (Duck child : children) {
            rowIndex = getDuckHierarchy(child, sheet, rowIndex, level + 1);
        }

        return rowIndex;
    }

    private List<Duck> getChildren(Duck mother) {
        return duckRepository.findAllByMother(mother);
    }

    private Sale findSaleByDuck(Duck duck) {
        List<Duck> duckToFind = new ArrayList<>();
        duckToFind.add(duck);
        return saleRepository.findByDucks(duckToFind);
    }
}
