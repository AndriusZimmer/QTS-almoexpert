package br.edu.ifrs;

import java.util.UUID;

import org.junit.*;
import org.openqa.selenium.By;


public class TesteLocais {

    private DSL dsl;
    private static LoginPage login = new LoginPage();
    private static LocaisPage locais = new LocaisPage();

    private static final String localCriar = "Local-AP" + UUID.randomUUID().toString().substring(0, 6);
    private static final String localCriarCancelar = "Local-AP" + UUID.randomUUID().toString().substring(0, 6);
    private static final String localTest = "testAP";
    private static final String localTestSecundaria = "testAP2";
    private static final String localCriarInativa = "Local-AP" + UUID.randomUUID().toString().substring(0, 6);
    private static final String localDesativar = "Local-AP" + UUID.randomUUID().toString().substring(0, 6);
    private static final String localAtivar = "Local-AP" + UUID.randomUUID().toString().substring(0, 6);
    private static final String localEditOriginal = "Local-AP" + UUID.randomUUID().toString().substring(0, 6);
    private static final String localEditAlerada = "Local-AP" + UUID.randomUUID().toString().substring(0, 6);
    private static final String localInexistente = "Local-AP??????";

    @Before
    public void inicializar() {
        DriverFactory.getDriver().get("http://35.209.123.161/front");
        dsl = new DSL();
        if (dsl.obterUrl().equals("http://35.209.123.161/front/login")) {
            login.setEmail("andrius.zimmer@gmail.com");
            login.setSenha("pinas");
            login.logar();
        }
        locais.acessarLocal();
    }

    @Test
    public void test1_ProcurarLocalInexistente() {
        locais.setDescricao(localInexistente);
        locais.filtrar();
        Assert.assertEquals(locais.getMsgLocalNaoEncontrado(), dsl.obterTexto(By.xpath(locais.getPathLocalNaoEncontrado())));

    }

    @Test
    public void test2_ProcurarLocalExistente() {
        locais.setDescricao(localTest);
        locais.filtrar();
        Assert.assertEquals(localTest, dsl.obterTexto(By.xpath(locais.getPathPrimeiroDaLista())));
    }

    @Test
    public void test3_CancelarCriacaoLocal() {
        locais.novo();
        locais.setDescricaoNovo(localCriarCancelar);
        dsl.waitExplicito();
        locais.voltar();
        locais.setDescricao(localCriarCancelar);
        locais.filtrar();
        Assert.assertNotEquals(localCriarCancelar, dsl.obterTexto(By.xpath(locais.getPathLocalNaoEncontrado())));
    }

    @Test
    public void test4_CriarLocalAtiva() {
        locais.novo();
        locais.selecionarAtivoAdd();
        locais.setDescricaoNovo(localCriar);
        locais.salvar();
        locais.selecionarAtivo();
        locais.setDescricao(localCriar);
        locais.filtrar();
        Assert.assertEquals(localCriar, dsl.obterTexto(By.xpath(locais.getPathPrimeiroDaLista())));
    }


    @Test
    public void test5_CriarLocalInativa() {
        locais.novo();
        locais.selecionarInativoAdd();
        locais.setDescricaoNovo(localCriarInativa);
        locais.salvar();
        locais.selecionarInativo();
        locais.setDescricao(localCriarInativa);
        locais.filtrar();
        Assert.assertEquals(localCriarInativa, dsl.obterTexto(By.xpath(locais.getPathPrimeiroDaLista())));

    }

    @Test
    public void test6_CriarLocalExistente() {
        locais.novo();
        locais.selecionarAtivoAdd();
        locais.setDescricaoNovo(localTest);
        locais.salvar();
        Assert.assertEquals(locais.getMsgJaExiste(), dsl.obterTexto(By.xpath(locais.getPathJaExiste())));
    }

    @Test
    public void test7_DesativarLocal() {
        locais.novo();
        locais.selecionarAtivoAdd();
        locais.setDescricaoNovo(localDesativar);
        locais.salvar();
        locais.selecionarAtivo();
        locais.setDescricao(localDesativar);
        locais.filtrar();
        locais.desativarPrimeiro();
        dsl.waitExplicito();
        locais.confirmarDesativar();
        dsl.waitExplicito();
        locais.selecionarInativo();
        locais.setDescricao(localDesativar);
        locais.filtrar();
        Assert.assertEquals(localDesativar, dsl.obterTexto(By.xpath(locais.getPathPrimeiroDaLista())));
    }

    @Test
    public void test8_AtivarLocal() {
        locais.novo();
        locais.selecionarInativoAdd();
        locais.setDescricaoNovo(localAtivar);
        locais.salvar();
        locais.selecionarInativo();
        locais.setDescricao(localAtivar);
        locais.filtrar();
        locais.editarPrimeiro();
        locais.selecionarAtivoAdd();
        locais.salvar();
        locais.selecionarAtivo();
        locais.setDescricao(localAtivar);
        locais.filtrar();
        Assert.assertEquals(localAtivar, dsl.obterTexto(By.xpath(locais.getPathPrimeiroDaLista())));
    }

    @Test
    public void test9_EditarLocalJaExiste() {
        locais.setDescricao(localTest);
        locais.filtrar();
        locais.editarPrimeiro();
        locais.selecionarAtivoAdd();
        locais.setDescricaoNovo(localTestSecundaria);
        locais.salvar();
        Assert.assertEquals(locais.getMsgJaExiste(), dsl.obterTexto(By.xpath(locais.getPathJaExiste())));
    }

    @Test
    public void test10_EditarLocal() {
        locais.novo();
        locais.selecionarAtivoAdd();
        locais.setDescricaoNovo(localEditOriginal);
        locais.salvar();
        locais.selecionarAtivo();
        locais.setDescricao(localEditOriginal);
        locais.filtrar();
        locais.editarPrimeiro();
        locais.selecionarAtivoAdd();
        locais.setDescricaoNovo(localEditAlerada);
        locais.salvar();
        locais.selecionarAtivo();
        locais.setDescricao(localEditAlerada);
        locais.filtrar();
        Assert.assertEquals(localEditAlerada, dsl.obterTexto(By.xpath(locais.getPathPrimeiroDaLista())));
    }

    @AfterClass
    public static void encerrar() {
        DriverFactory.killDriver();
    }
}

