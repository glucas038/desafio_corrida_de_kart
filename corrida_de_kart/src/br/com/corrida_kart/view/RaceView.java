package br.com.corrida_kart.view;

import br.com.corrida_kart.model.service.RaceModel;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class RaceView {

    private static final String WINDOW_TITLE = "Corrida de Kart";
    private static final String ERROR_DIALOG_TITLE = "Erro";
    private static final String CLOSING_PROGRAM = "O programa será encerrado.";
    private static final int TOTAL_PAGES = 3;

    private final JFrame frame;
    private final RaceModel raceModel;

    public RaceView() {
        this.frame = new JFrame(WINDOW_TITLE);
        this.raceModel = new RaceModel();
        selectAndProcessLogFile();
    }

    public void show() {
        frame.setVisible(true);
    }

    private void selectAndProcessLogFile() {
        boolean validFile;

        do {
            String filePath = getFilePathFromUser();

            if (filePath != null) {
                validFile = processLogFile(filePath);
            } else {
                gracefullyExit();
                return;
            }

        } while (!validFile);
    }

    private String getFilePathFromUser() {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Arquivos de Log (.log)", "log");
        fileChooser.setFileFilter(filter);

        int result = fileChooser.showOpenDialog(frame);

        if (result == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile().getAbsolutePath();
        } else {
            return null;
        }
    }

    private boolean processLogFile(String filePath) {
        if (hasLogExtension(filePath)) {
            try {
                raceModel.processLogFile(filePath);
                navigateThroughPages();
                return true;
            } catch (Exception e) {
                showErrorMessage("Erro ao processar o arquivo de log:\n" + e.getMessage());
            }
        } else {
            showErrorMessage("Selecione um arquivo com extensão .log");
        }
        return false;
    }

    private boolean hasLogExtension(String filePath) {
        return filePath.toLowerCase().endsWith(".log");
    }

    private void navigateThroughPages() {
        int currentPage = 1;
        while (currentPage <= TOTAL_PAGES) {
            currentPage = showRacingResults(currentPage);
        }
        gracefullyExit();
    }

    private int showRacingResults(int currentPage) {
        String[] options;

        switch (currentPage) {
            case 1:
                options = new String[]{"Próximo", "Fechar"};
                break;
            case TOTAL_PAGES:
                options = new String[]{"Voltar", "Fechar"};
                break;
            default:
                options = new String[]{"Voltar", "Próximo", "Fechar"};
                break;
        }

        int option = JOptionPane.showOptionDialog(
                frame,
                raceModel.getScreenMessage(currentPage),
                WINDOW_TITLE,
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[0]
        );

        if (option == 0) {
            currentPage = (currentPage == 1) ? currentPage + 1 : currentPage - 1;
        } else if (option == 1 && currentPage < TOTAL_PAGES) {
            currentPage++;
        } else {
            gracefullyExit();
        }

        return currentPage;
    }

    private void gracefullyExit() {
        showInformationMessage(CLOSING_PROGRAM);
        System.exit(0);
    }

    private void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(
                frame,
                message,
                ERROR_DIALOG_TITLE,
                JOptionPane.ERROR_MESSAGE
        );
    }

    private void showInformationMessage(String message) {
        JOptionPane.showMessageDialog(
                frame,
                message,
                WINDOW_TITLE,
                JOptionPane.INFORMATION_MESSAGE
        );
    }
}
