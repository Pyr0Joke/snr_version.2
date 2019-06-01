package additionalFunction;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class commonWork {
    public static void commonCalculateSNR(DefaultTableModel tableModel, ArrayList<String> meanWhileList, ArrayList<String> ABSmeanWhile,
                                          ArrayList<String> varianceList, ArrayList<String> SNRList, int columnCount, ArrayList<Object[]> matrix, JTable table1){
        //Очищаем списки
        meanWhileList.clear();
        ABSmeanWhile.clear();
        varianceList.clear();
        SNRList.clear();
        //добавляем названия столбцов в таблицу
        meanWhileList.add("Мат ожидание");
        ABSmeanWhile.add("Модуль мат. ожидания");
        varianceList.add("Дисперсия");
        SNRList.add("SNR");

        //проводим рассчеты по всей таблице
        for (int i = 1; i < columnCount; i++) {
            //получаем мат ожидание
            String meanwhile = String.valueOf(workWithMath.meanWhile(workWithArray.takeVerticalColumn(matrix, i)));
            System.out.println(meanwhile);
            //заносим его в таблицу
            meanWhileList.add(meanwhile);
            //получаем модуль мат ожидания
            String abs = String.valueOf(Math.abs(Double.parseDouble(meanwhile)));
            System.out.println(abs);
            //заносим его в таблицу
            ABSmeanWhile.add(abs);
            //получаем дисперсию
            String variance = String.valueOf(workWithMath.variance(workWithArray.takeVerticalColumn(matrix, i), Double.parseDouble(meanwhile)));
            System.out.println(variance);
            //заносим ее в таблицу
            varianceList.add(variance);
            //получем SNR
            String SNR = String.valueOf(workWithMath.SNR(Double.parseDouble(abs), Double.parseDouble(variance)));
            System.out.println(SNR);
            //заносим ее в таблицу
            SNRList.add(SNR);
        }
        //добавляем строки которые мы заполнили данными выше
        tableModel.addRow(meanWhileList.toArray());
        tableModel.addRow(ABSmeanWhile.toArray());
        tableModel.addRow(varianceList.toArray());
        tableModel.addRow(SNRList.toArray());
        //округляем данные в САМОЙ ТАБЛИЦЕ
        workWithTable.tableTwoDecimals(tableModel);
        //перекрашиваем таблицу
        workWithTable.renderTable(table1);
    }
}
