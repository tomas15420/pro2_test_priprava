package layout.gui;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.io.File;

public class RecordFileChooser extends JFileChooser {
    public RecordFileChooser(){
        super(System.getProperty("user.dir"));

        addChoosableFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                if(f.isDirectory() || f.getName().endsWith(".txt")){
                    return true;
                }
                return false;
            }

            @Override
            public String getDescription() {
                return ".txt file";
            }
        });
    }

    @Override
    public int showOpenDialog(Component parent) throws HeadlessException {
        setDialogTitle("Vyberte soubor pro otevření");
        setApproveButtonText("Otevřít");
        return super.showOpenDialog(parent);
    }

    @Override
    public int showSaveDialog(Component parent) throws HeadlessException {
        setDialogTitle("Zadejte název souboru pro uložení");
        setApproveButtonText("Uložit");
        return super.showSaveDialog(parent);
    }
}
