package additionalFunction;

import com.sun.xml.internal.ws.wsdl.writer.document.Message;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.io.*;
import java.util.ArrayList;

public class workWithFiles {

    //загрузить xls файл с выбором файла
    public static int downloadFile(DefaultTableModel tableModel, ArrayList<Object[]> matrix) {
        int countColums = 0;
        JFileChooser fileopen = new JFileChooser();
        int ret = fileopen.showDialog(null, "Choose xls file");
        if (ret == JFileChooser.APPROVE_OPTION) {
            File file = fileopen.getSelectedFile();
            try {
                POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(file));
                HSSFWorkbook wb = new HSSFWorkbook(fs);
                HSSFSheet sheet = wb.getSheetAt(0);
                HSSFRow row;
                HSSFCell cell;

                int rows; // No of rows
                rows = sheet.getPhysicalNumberOfRows();

                int cols = 0; // No of columns
                int tmp = 0;

                // This trick ensures that we get the data properly even if it doesn't start from first few rows
                for (int i = 0; i < 10 || i < rows; i++) {
                    row = sheet.getRow(i);
                    if (row != null) {
                        tmp = sheet.getRow(i).getPhysicalNumberOfCells();
                        if (tmp > cols) cols = tmp;
                    }
                }
                cols++;
                countColums = cols;
                for (int i = 0; i < cols; i++)
                    tableModel.addColumn(i);

                for (int r = 0; r < rows; r++) {
                    ArrayList<String> arrayList = new ArrayList<>();
                    arrayList.add((r+1)+" главная компонента");
                    row = sheet.getRow(r);
                    System.out.println();
                    if (row != null) {
                        for (int c = 0; c < cols; c++) {
                            cell = row.getCell((short) c);
                            if (cell != null) {
                                arrayList.add("" + cell);
                            }
                        }
                    }
                    tableModel.addRow(arrayList.toArray());
                    matrix.add(arrayList.toArray());
                }
            } catch (Exception ioe) {
                ioe.printStackTrace();
            }

        }
        return countColums;
    }
    //загрузить xls файл без выбора файла
    public static int downloadExampleFile(DefaultTableModel tableModel, ArrayList<Object[]> matrix){
        int countColums = 0;
        try {
            POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream("K:\\Информационный_отдел\\Липатов_Александр\\прога\\example2.xls"));
            HSSFWorkbook wb = new HSSFWorkbook(fs);
            HSSFSheet sheet = wb.getSheetAt(0);
            HSSFRow row;
            HSSFCell cell;

            int rows; // No of rows
            rows = sheet.getPhysicalNumberOfRows();

            int cols = 0; // No of columns
            int tmp = 0;

            // This trick ensures that we get the data properly even if it doesn't start from first few rows
            for (int i = 0; i < 10 || i < rows; i++) {
                row = sheet.getRow(i);
                if (row != null) {
                    tmp = sheet.getRow(i).getPhysicalNumberOfCells();
                    if (tmp > cols) cols = tmp;
                }
            }
            cols++;
            countColums = cols;
            for (int i = 0; i < cols; i++)
                tableModel.addColumn(i);

            for (int r = 0; r < rows; r++) {
                ArrayList<String> arrayList = new ArrayList<>();
                arrayList.add((r+1)+" главная компонента");
                row = sheet.getRow(r);
                System.out.println();
                if (row != null) {
                    for (int c = 0; c < cols; c++) {
                        cell = row.getCell((short) c);
                        if (cell != null) {
                            arrayList.add("" + cell);
                        }
                    }
                }
                tableModel.addRow(arrayList.toArray());
                matrix.add(arrayList.toArray());
            }
        } catch (Exception ioe) {
            ioe.printStackTrace();
        }
        return countColums;
    }

    //ToDO
    //загрузка вектора
    public static void downloadVector(){

    }

    //ToDO
    //Сохранение данных в файл
    public static void saveFile(JTable table)  throws IOException{
        TableModel model = table.getModel();
        FileWriter out = new FileWriter("D:\\result.xls");
        String groupExport = "";
        for (int i = 0; i < (model.getColumnCount()); i++) {//* disable export from TableHeaders
            groupExport = String.valueOf(model.getColumnName(i));
            out.write(String.valueOf(groupExport) + "\t");
        }
        out.write("\n");
        for (int i = 0; i < model.getRowCount(); i++) {
            for (int j = 0; j < (model.getColumnCount()); j++) {
                if (model.getValueAt(i, j) == null) {
                    out.write("null" + "\t");
                } else {
                    groupExport = String.valueOf(model.getValueAt(i, j));
                    out.write(String.valueOf(groupExport) + "\t");
                }
            }
            out.write("\n");
        }
        out.close();

    }



}
