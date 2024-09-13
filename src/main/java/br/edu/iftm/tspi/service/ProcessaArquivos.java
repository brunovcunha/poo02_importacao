package br.edu.iftm.tspi.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProcessaArquivos {

    private final String PATH = "importacao\\arquivos\\";

    private final String PROCESSADOS = "importacao\\processados\\";

    private String prefix;

    private ProcessaLinha processaLinha;

    public ProcessaArquivos(String prefix, ProcessaLinha processaLinha) {
        this.prefix = prefix;
        this.processaLinha = processaLinha;
    }


    public void processa() throws Exception {
        List<Path> arquivosAProcessar = getArquivosAProcessar(prefix);
        for (Path arquivoAProcessar : arquivosAProcessar) {
            processaArquivo(arquivoAProcessar);
            moveArquivoProcessado(arquivoAProcessar);
        }
    }

    private List<Path> getArquivosAProcessar(String prefix) throws Exception {
        Path dir = Paths.get(PATH);

        List<Path> fileList = new ArrayList<>();
        Files.newDirectoryStream(dir, prefix).forEach(fileList::add);
        Collections.sort(fileList);

        return fileList;
    }

    private void processaArquivo(Path arquivoAProcessar) throws Exception {
        processaLinha.processa(Files.readAllLines(arquivoAProcessar));
    }

    private void moveArquivoProcessado(Path arquivoAProcessar) throws Exception {
        Path dir = Paths.get(PROCESSADOS + arquivoAProcessar.getFileName());
        Files.move(arquivoAProcessar, dir);
    }

}
