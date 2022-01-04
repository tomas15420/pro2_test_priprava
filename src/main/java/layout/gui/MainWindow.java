package layout.gui;

import layout.measure.Record;
import layout.measure.RecordList;
import layout.model.MeasureTableModel;
import layout.saver.RecordSaverCsv;
import layout.saver.RecordSaverJson;
import layout.saver.RecordSaverXML;
import layout.saver.RecordsSaver;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.DateTimeException;
import java.time.LocalTime;

public class MainWindow extends JFrame {
    private JTextField tfRecordTime = new JTextField();
    private JTextField tfRecordedTime = new JTextField();

    private JButton btnAdd = new JButton("Přidat");
    private JButton btnClose = new JButton("Zavřít");

    RecordList recordList = new RecordList();
    MeasureTableModel measureTableModel = new MeasureTableModel(recordList);
    private JTable table = new JTable(measureTableModel);

    public MainWindow(){
        super("Test layoutu");

        recordList.addRecord(new Record(120, LocalTime.of(12,15,30)));
        recordList.addRecord(new Record(90, LocalTime.of(14,21,12)));
        recordList.addRecord(new Record(60, LocalTime.of(13,35,35)));

        measureTableModel.refresh();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(new Dimension(600,480));

        createButtonEvents();

        add(createMenu(),BorderLayout.NORTH);

        JPanel mainPanel = new JPanel(new BorderLayout());
        add(mainPanel,BorderLayout.CENTER);

        //Přidání záznamu Panel
        JPanel addRecordPanel = new JPanel();
        addRecordPanel.setLayout(new BorderLayout());
        addRecordPanel.setBorder(BorderFactory.createTitledBorder("Přidání záznamu"));

        //Ovládací prvky
        JPanel controlsPanel = new JPanel();
        controlsPanel.setLayout(new GridLayout(3,2,10,10));
        addRecordPanel.add(controlsPanel);

        controlsPanel.add(new JLabel("Čas měření"));
        controlsPanel.add(new JLabel("Doba jízdy"));

        controlsPanel.add(tfRecordTime);
        tfRecordTime.setToolTipText("Čas ve formátu: hh:mm:ss");
        controlsPanel.add(tfRecordedTime);
        tfRecordedTime.setToolTipText("sekundy");

        controlsPanel.add(btnAdd);
        btnAdd.setToolTipText("Přidání záznamu do tabulky");
        controlsPanel.add(btnClose);

        mainPanel.add(addRecordPanel,BorderLayout.NORTH);

        //Tabulka
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int sel = table.getSelectedRow();
                if(sel != -1){
                    Record rec = recordList.getRecordList().get(sel);
                    tfRecordTime.setText(""+rec.getLocalTime());
                    tfRecordedTime.setText(""+rec.getTime());
                }
            }
        });
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Seznam rychlostí"));
        mainPanel.add(scrollPane,BorderLayout.CENTER);
    }

    private void createButtonEvents(){
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(tfRecordedTime.getText().length() > 0 && tfRecordTime.getText().length() > 0){
                    LocalTime time = null;
                    int recordedTime = 0;
                    try{
                        time = LocalTime.parse(tfRecordTime.getText());
                    }
                    catch (DateTimeException exception){
                        exception.printStackTrace();
                    }
                    try{
                        recordedTime = Integer.parseInt(tfRecordedTime.getText());
                    }
                    catch (NumberFormatException exception){
                        exception.printStackTrace();
                    }
                    if(time != null && recordedTime != 0){
                        recordList.addRecord(new Record(recordedTime,time));
                        measureTableModel.refresh();
                    }
                }
            }
        });

        btnClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new CanvasWindow();
                frame.setVisible(true);
            }
        });
    }

    private void clearInput(){
        tfRecordedTime.setText("");
        tfRecordTime.setText("");
    }

    private JMenuBar createMenu(){
        JMenuBar menu = new JMenuBar();
        JMenu file = new JMenu("Soubor");

        file.add(new AbstractAction("Nový") {
            @Override
            public void actionPerformed(ActionEvent e) {
                recordList.clear();
                clearInput();
                measureTableModel.refresh();
            }
        });

        file.add(new AbstractAction("Otevřít") {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new RecordFileChooser();
                if(fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
//                    RecordsSaver saver = new RecordSaverCsv();
//                    recordList.setRecordList(saver.loadRecords(fileChooser.getSelectedFile().toString()));
//                    RecordsSaver saver = new RecordSaverXML();
//                    recordList.setRecordList(saver.loadRecords(fileChooser.getSelectedFile().toString()));
                    RecordsSaver saver = new RecordSaverJson();
                    recordList.setRecordList(saver.loadRecords(fileChooser.getSelectedFile().toString()));

                    measureTableModel.refresh();
                }
            }
        });

        file.add(new AbstractAction("Uložit") {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new RecordFileChooser();
                if(fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION){
                    RecordsSaver saver = new RecordSaverCsv();
                    saver.saveRecords(fileChooser.getSelectedFile().toString(),recordList.getRecordList());
                    saver = new RecordSaverXML();
                    saver.saveRecords(fileChooser.getSelectedFile().toString(),recordList.getRecordList());
                    saver = new RecordSaverJson();
                    saver.saveRecords(fileChooser.getSelectedFile().toString(),recordList.getRecordList());
                }
            }
        });

        file.add(new AbstractAction("Zavřít") {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        menu.add(file);
        return menu;
    }
}
