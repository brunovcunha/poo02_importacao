import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

public class LinhaContaTest {

    @Test
    public void testaLinhaCabecalho() {

        String linha = "1645";

        String tipo = linha.substring(0,1);
        String lote = linha.substring( 1, 4);
        Assert.assertEquals("1", tipo);
        Assert.assertEquals("645",lote);
    }

    @Test
    public void testaContaInclusao() {
        String linha = "2I06045064343000000090000044408615";

        String inclusaoAlteracao = linha.substring(1,2);
        String cpf = linha.substring(2,13);
        String valorLimite = linha.substring(13,25).trim();
        String diaVencimento = linha.substring(25,27).trim();
        String numeroConta = linha.substring(27,34).trim();
        
        Assert.assertEquals("I", inclusaoAlteracao);
        Assert.assertEquals("06045064343", cpf);
        Assert.assertEquals("000000090000",valorLimite);
        Assert.assertEquals("04", diaVencimento);
        Assert.assertEquals("4408615", numeroConta);
    }

    @Test
    public void testaConversaoInt() {
        String diaVencimento = "04";
        Integer diaVencimentoInt = Integer.parseInt(diaVencimento);
        Assert.assertEquals(Integer.valueOf(4), diaVencimentoInt);
    }

    @Test
    public void testaConversaoDouble() {
        String valorLimite = "000000090000";
        Double valorLimiteDouble = Double.parseDouble(valorLimite) / 100;
        Assert.assertEquals(Double.valueOf(900),valorLimiteDouble);
    }
    
}

