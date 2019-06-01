package additionalFunction;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class workWithArray {

    //Получение столбца таблицы
    public static  ArrayList<Double> takeVerticalColumn(@NotNull ArrayList<Object[]> arrayList, int column) {
        ArrayList<Double> doubleArrayList = new ArrayList<>();
        for (int j = 0; j < arrayList.size(); j++) {
            doubleArrayList.add(Double.parseDouble("" + arrayList.get(j)[column]));
        }
        return doubleArrayList;
    }

    //Изменение знака у определенного столбца
    public static void changeSing(ArrayList<Object[]> matrix, int column){
        for(int i = 0; i < matrix.size();i++){
            matrix.get(i)[column]=String.valueOf(Double.parseDouble(matrix.get(i)[column].toString())*(-1));
        }

    }


}
