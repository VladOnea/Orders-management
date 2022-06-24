package presentation;

import javax.swing.*;
import java.lang.reflect.Field;
import java.util.List;

public class AbstractTable<T> {
    public JTable createTable(List<T> objects){
        Class type=objects.get(0).getClass();
        Field[] fields=type.getDeclaredFields();
        String[][] data=new String[objects.size()][fields.length];
        String[] columns=new String[fields.length];

        for(int i=0;i<fields.length;i++){
            fields[i].setAccessible(true);
            columns[i]=fields[i].getName();
        }
        for(int i=0;i<objects.size();i++){
            for(int j=0;j<fields.length;j++){
                try {
                    data[i][j]=fields[j].get(objects.get(i)).toString();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        JTable table=new JTable(data,columns);
        table.setVisible(true);
        return table;
    }
}
