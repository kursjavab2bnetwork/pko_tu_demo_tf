package pl.b2b.net.tf.demo.utils;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jxl.Cell;
import jxl.CellType;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class DataLoader {

    private Object[][] convertToTabObject(List<List<Object>> list) {
        Object[][] tab = new Object[list.size()][9];

        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.get(i).size(); j++) {
                tab[i][j] = list.get(i).get(j);
            }
        }
        return tab;
    }

    public Object[][] read(String excelPath) throws IOException {
        List<List<Object>> readFile = new ArrayList<>();
        List<Object> readRow = new ArrayList<>();
        File inputWorkbook = new File(excelPath);
        Workbook w;
        try {
            w = Workbook.getWorkbook(inputWorkbook);
            // Get the first sheet
            Sheet sheet = w.getSheet(0);
            //loop over first 10 column and lines
            for (int j = 2; j < sheet.getRows(); j++) {
                for (int i = 1; i < sheet.getColumns(); i++) {
                    Cell cell = sheet.getCell(i, j);
                    CellType type = cell.getType();
                    readRow.add(cell.getContents());
                }
                readFile.add(readRow);
                readRow = new ArrayList<>();
            }

        } catch (BiffException e) {
            e.printStackTrace();
        }
        return convertToTabObject(readFile);
    }
}
