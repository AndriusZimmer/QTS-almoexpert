package br.edu.ifrs;

import java.util.UUID;

import org.junit.*;
import org.openqa.selenium.By;

public class TesteItem {

    private DSL dsl;
    private static LoginPage login = new LoginPage();
    private static ItemPage item = new ItemPage();
    private static final String ItemCriar = "Item-AP" + UUID.randomUUID().toString().substring(0, 6);
    private static final String ItemCriarCancelar = "Item-AP" + UUID.randomUUID().toString().substring(0, 6);
    private static final String ItemTest = "testAPprimario";
    private static final String ItemDesativar = "Item-AP" + UUID.randomUUID().toString().substring(0, 6);
    private static final String ItemAtivar = "Item-AP" + UUID.randomUUID().toString().substring(0, 6);
    private static final String ItemInexistente = "Item-AP??????";

    @Before
    public void inicializar() {
        DriverFactory.getDriver().get("http://35.209.123.161/front");
        dsl = new DSL();
        if (dsl.obterUrl().equals("http://35.209.123.161/front/login")) {
            login.setEmail("andrius.zimmer@gmail.com");
            login.setSenha("pinas");
            login.logar();
        }
        item.acessarItem();
    }

    @Test
    public void test1_ProcurarItemInexistente() {
        item.setNome(ItemInexistente);
        item.filtrar();
        Assert.assertEquals(item.getMsgItemNaoEncontrado(), dsl.obterTexto(By.xpath(item.getPathItemNaoEncontrado())));

    }

    @Test
    public void test2_ProcuraritemExistente() {
        item.setNome(ItemTest);
        item.filtrar();
        Assert.assertEquals(ItemTest, dsl.obterTexto(By.xpath(item.getPathPrimeiroNomeDaLista())));
    }

    @Test
    public void test3_CancelarCriacaoitem() {
        item.novo();
        item.selecionarAtivoAdd();
        item.selecionarUM();
        item.setCodigoNovo("AP" + UUID.randomUUID().toString().substring(0, 6));
        item.setNomeNovo(ItemCriarCancelar);
        item.selecionarCategoria();
        item.setValor(5.6f);
        item.setExercito();
        item.setPolicia();
        item.setObservacoes("TesteTeste");
        dsl.waitExplicito();
        item.voltar();
        item.setNome(ItemCriarCancelar);
        item.filtrar();
        Assert.assertNotEquals(ItemCriarCancelar, dsl.obterTexto(By.xpath(item.getPathItemNaoEncontrado())));
    }

    @Test
    public void test4_CriarItem() {
        item.novo();
        item.selecionarAtivoAdd();
        item.selecionarUM();
        item.setCodigoNovo("AP" + UUID.randomUUID().toString().substring(0, 6));
        item.setNomeNovo(ItemCriar);
        item.selecionarCategoria();
        item.setValor(5.6f);
        item.setExercito();
        item.setPolicia();
        item.setObservacoes("TesteTeste");
        item.salvar();
        item.subitem();
        item.novoSubitem();
        item.cadastrarSubitem();
        dsl.waitExplicito();
        item.salvar();
        item.voltar();
        item.selecionarAtivo();
        item.setNome(ItemCriar);
        item.filtrar();
        Assert.assertEquals(ItemCriar, dsl.obterTexto(By.xpath(item.getPathPrimeiroNomeDaLista())));
    }

    @Test
    public void test5_CriaritemExistente() {
        item.novo();
        item.selecionarAtivoAdd();
        item.selecionarUM();
        item.setCodigoNovo(ItemTest);
        item.setNomeNovo(ItemTest);
        item.selecionarCategoria();
        item.setValor(5.6f);
        item.setExercito();
        item.setPolicia();
        item.setObservacoes("TesteTeste");
        item.salvar();
        Assert.assertEquals(item.getMsgJaExiste(), dsl.obterTexto(By.xpath(item.getPathJaExiste())));
    }

    @Test
    public void test6_Desativaritem() {
        item.novo();
        item.selecionarAtivoAdd();
        item.selecionarUM();
        item.setCodigoNovo("AP" + UUID.randomUUID().toString().substring(0, 6));
        item.setNomeNovo(ItemDesativar);
        item.selecionarCategoria();
        item.setValor(5.6f);
        item.setExercito();
        item.setPolicia();
        item.setObservacoes("TesteTeste");
        item.salvar();
        item.subitem();
        item.novoSubitem();
        item.cadastrarSubitem();
        dsl.waitExplicito();
        item.salvar();
        item.voltar();
        item.selecionarAtivo();
        item.setNome(ItemDesativar);
        item.filtrar();
        item.desativarPrimeiro();
        dsl.waitExplicito();
        item.confirmarDesativar();
        dsl.waitExplicito();
        item.selecionarInativo();
        item.setNome(ItemDesativar);
        item.filtrar();
        Assert.assertEquals(ItemDesativar, dsl.obterTexto(By.xpath(item.getPathPrimeiroNomeDaLista())));
    }

    @Test
    public void test7_Ativaritem() {
        item.novo();
        item.selecionarInativoAdd();
        item.selecionarUM();
        item.setCodigoNovo("AP" + UUID.randomUUID().toString().substring(0, 6));
        item.setNomeNovo(ItemAtivar);
        item.selecionarCategoria();
        item.setValor(5.6f);
        item.setExercito();
        item.setPolicia();
        item.setObservacoes("TesteTeste");
        item.salvar();
        item.subitem();
        item.novoSubitem();
        item.cadastrarSubitem();
        dsl.waitExplicito();
        item.salvar();
        item.voltar();
        item.selecionarInativo();
        item.setNome(ItemAtivar);
        item.filtrar();
        item.editarPrimeiro();
        item.selecionarAtivoAdd();
        item.salvar();
        item.voltar();
        item.selecionarAtivo();
        item.setNome(ItemAtivar);
        item.filtrar();
        Assert.assertEquals(ItemAtivar, dsl.obterTexto(By.xpath(item.getPathPrimeiroNomeDaLista())));
    }



    @AfterClass
    public static void encerrar() {
        DriverFactory.killDriver();
    }
}
