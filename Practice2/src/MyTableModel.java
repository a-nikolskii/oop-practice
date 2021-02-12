
import javax.swing.table.DefaultTableModel;


public class MyTableModel  extends DefaultTableModel {
   
    public MyTableModel (){
        super(new String[] {"Название", "Цена за шт.", "Количество","Стоимость"}, 1);
        super.setValueAt("Итого",getRowCount() - 1, 2);
        super.setValueAt("0.0", getRowCount() - 1, 3);
} 

    
    @Override
    public boolean isCellEditable(int row, int column) {
        return !(column != 0 || row == getRowCount() - 1);
 }
 
}
