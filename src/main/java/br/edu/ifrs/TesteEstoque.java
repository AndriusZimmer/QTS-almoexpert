package br.edu.ifrs;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.*;
import org.openqa.selenium.By;

public class TesteEstoque {

    private DSL dsl;
    private static LoginPage login = new LoginPage();
    private static EstoquePage estoque = new EstoquePage();
    private static final String EstoqueCriar = "Estoque-AP" + UUID.randomUUID().toString().substring(0, 6);
    private static final String EstoqueTest = "testAPprimario";
    private static final String EstoqueDesativar = "Estoque-AP" + UUID.randomUUID().toString().substring(0, 6);
    private static final String EstoqueAtivar = "Estoque-AP" + UUID.randomUUID().toString().substring(0, 6);
    private static final String EstoqueItem = "Item1";
    private static final String EstoqueSubItem = "SubItem1";
    private int randomYear = ThreadLocalRandom.current().nextInt(1000, 9000);
    LocalDateTime agora = LocalDateTime.now();
    LocalDateTime horario = agora.withYear(randomYear);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy'T'HH:mm");
    DateTimeFormatter formatterCut = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    String dataHoraFormated = horario.format(formatter);
    String dataHoraCut = horario.format(formatterCut);

    @Before
    public void inicializar() {
        DriverFactory.getDriver().get("http://35.209.123.161/front");
        dsl = new DSL();
        if (dsl.obterUrl().equals("http://35.209.123.161/front/login")) {
            login.setEmail("andrius.zimmer@gmail.com");
            login.setSenha("pinas");
            login.logar();
        }
        estoque.acessarEstoque();
    }

    @Test
    public void test1_ProcurarEstoqueExistente() {
        estoque.selecionarItem(EstoqueItem);
        estoque.filtrar();
        Assert.assertEquals(EstoqueItem, dsl.obterTexto(By.xpath(estoque.getPathPrimeiroItemDaLista())));
    }

    @Test
    public void test2_CriarEstoque() {
        estoque.novo();
        estoque.selecionarItemNovo(EstoqueItem);
        estoque.selecionarSubItemNovo(EstoqueSubItem);
        estoque.selecionarEntrada();
        estoque.adicionarData(dataHoraFormated);
        estoque.adicionarQuantidade();
        estoque.adicionarObservacao();
        estoque.salvar();
        dsl.waitExplicito();
        estoque.selecionarItem(EstoqueItem);
        estoque.procurarData(dataHoraCut);
        estoque.filtrar();
        Assert.assertEquals(EstoqueItem, dsl.obterTexto(By.xpath(estoque.getPathPrimeiroItemDaLista())));
    }

    @Test
    public void test6_ExcluirEstoque() {
        estoque.novo();
        estoque.selecionarItemNovo(EstoqueItem);
        estoque.selecionarSubItemNovo(EstoqueSubItem);
        estoque.selecionarEntrada();
        estoque.adicionarData(dataHoraFormated);
        estoque.adicionarQuantidade();
        estoque.adicionarObservacao();
        estoque.salvar();
        dsl.waitExplicito();
        estoque.selecionarItem(EstoqueItem);
        estoque.procurarData(dataHoraCut);
        estoque.filtrar();
        estoque.deletarPrimeiro();
        estoque.confirmarDeletar();
        dsl.waitExplicito();
        Assert.assertEquals(estoque.getMsgMovimentacaoNaoEncontrada(), dsl.obterTexto(By.xpath(estoque.getPathMovimentacaoNaoEncontrada())));
    }




    @AfterClass
    public static void encerrar() {
        DriverFactory.killDriver();
    }
}
