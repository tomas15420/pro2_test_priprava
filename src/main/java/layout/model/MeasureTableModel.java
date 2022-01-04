package layout.model;

import layout.measure.RecordList;

import javax.swing.table.AbstractTableModel;

public class MeasureTableModel extends AbstractTableModel {

    public RecordList recordList;
    public MeasureTableModel(RecordList recordList){
        this.recordList = recordList;
    }

    String[] colNames = {"Číslo měření","Čas měření","Doba jízdy","Rychlost km/h","Rychlost m/s"};

    @Override
    public int getRowCount() {
        return recordList.getRecordList().size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex){
            case 0: return rowIndex+1;
            case 1: return recordList.getRecordList().get(rowIndex).getLocalTime();
            case 2: return recordList.getRecordList().get(rowIndex).getTime();
            case 3: return String.format("%.02f",recordList.getRecordList().get(rowIndex).getSpeedKMH());
            case 4: return String.format("%.02f",recordList.getRecordList().get(rowIndex).getSpeedMS());
        }
        return null;
    }

    @Override
    public String getColumnName(int column) {
        return colNames[column];
    }

    public void refresh(){
        fireTableDataChanged();
    }
}
