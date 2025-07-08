package br.edu.ifrs;

import java.util.UUID;

import org.junit.*;
import org.openqa.selenium.By;


public class TesteMarca {

    private DSL dsl;
    private static LoginPage login = new LoginPage();
    private static MarcaPage marca = new MarcaPage();

    private static final String MarcaCriar = "Marca-AP" + UUID.randomUUID().toString().substring(0, 6);
    private static final String MarcaCriarCancelar = "Marca-AP" + UUID.randomUUID().toString().substring(0, 6);
    private static final String MarcaTest = "testAPprimario";
    private static final String MarcaTestSecundaria = "testAPsecundaria";
    private static final String MarcaCriarInativa = "Marca-AP" + UUID.randomUUID().toString().substring(0, 6);
    private static final String MarcaDesativar = "Marca-AP" + UUID.randomUUID().toString().substring(0, 6);
    private static final String MarcaAtivar = "Marca-AP" + UUID.randomUUID().toString().substring(0, 6);
    private static final String MarcaEditOriginal = "Marca-AP" + UUID.randomUUID().toString().substring(0, 6);
    private static final String MarcaEditAlerada = "Marca-AP" + UUID.randomUUID().toString().substring(0, 6);
    private static final String MarcaInexistente = "Marca-AP??????";

    @Before
    public void inicializar() {
        DriverFactory.getDriver().get("http://35.209.123.161/front");
        dsl = new DSL();
        if (dsl.obterUrl().equals("http://35.209.123.161/front/login")) {
            login.setEmail("andrius.zimmer@gmail.com");
            login.setSenha("pinas");
            login.logar();
        }
        marca.acessarMarca();
    }

    @Test
    public void test1_ProcurarMarcaInexistente() {
        marca.setDescricao(MarcaInexistente);
        marca.filtrar();
        Assert.assertEquals(marca.getMsgMarcaNaoEncontrada(), dsl.obterTexto(By.xpath(marca.getPathMarcaNaoEncontrada())));

    }

    @Test
    public void test2_ProcurarMarcaExistente() {
        marca.setDescricao(MarcaTest);
        marca.filtrar();
        Assert.assertEquals(MarcaTest, dsl.obterTexto(By.xpath(marca.getPathPrimeiroDaLista())));
    }

    @Test
    public void test3_CancelarCriacaoMarca() {
        marca.novo();
        marca.setDescricaoNovo(MarcaCriarCancelar);
        dsl.waitExplicito();
        marca.voltar();
        marca.setDescricao(MarcaCriarCancelar);
        marca.filtrar();
        Assert.assertNotEquals(MarcaCriarCancelar, dsl.obterTexto(By.xpath(marca.getPathMarcaNaoEncontrada())));
    }

    @Test
    public void test4_CriarMarcaAtiva() {
        marca.novo();
        marca.selecionarAtivoAdd();
        marca.setDescricaoNovo(MarcaCriar);
        marca.salvar();
        marca.selecionarAtivo();
        marca.setDescricao(MarcaCriar);
        marca.filtrar();
        Assert.assertEquals(MarcaCriar, dsl.obterTexto(By.xpath(marca.getPathPrimeiroDaLista())));
    }


    @Test
    public void test5_CriarMarcaInativa() {
        marca.novo();
        marca.selecionarInativoAdd();
        marca.setDescricaoNovo(MarcaCriarInativa);
        marca.salvar();
        marca.selecionarInativo();
        marca.setDescricao(MarcaCriarInativa);
        marca.filtrar();
        Assert.assertEquals(MarcaCriarInativa, dsl.obterTexto(By.xpath(marca.getPathPrimeiroDaLista())));

    }

    @Test
    public void test6_CriarMarcaExistente() {
        marca.novo();
        marca.selecionarAtivoAdd();
        marca.setDescricaoNovo(MarcaTest);
        marca.salvar();
        Assert.assertEquals(marca.getMsgJaExiste(), dsl.obterTexto(By.xpath(marca.getPathJaExiste())));
    }

    @Test
    public void test7_DesativarMarca() {
        marca.novo();
        marca.selecionarAtivoAdd();
        marca.setDescricaoNovo(MarcaDesativar);
        marca.salvar();
        marca.selecionarAtivo();
        marca.setDescricao(MarcaDesativar);
        marca.filtrar();
        marca.desativarPrimeiro();
        dsl.waitExplicito();
        marca.confirmarDesativar();
        dsl.waitExplicito();
        marca.selecionarInativo();
        marca.setDescricao(MarcaDesativar);
        marca.filtrar();
        Assert.assertEquals(MarcaDesativar, dsl.obterTexto(By.xpath(marca.getPathPrimeiroDaLista())));
    }

    @Test
    public void test8_AtivarMarca() {
        marca.novo();
        marca.selecionarInativoAdd();
        marca.setDescricaoNovo(MarcaAtivar);
        marca.salvar();
        marca.selecionarInativo();
        marca.setDescricao(MarcaAtivar);
        marca.filtrar();
        marca.editarPrimeiro();
        marca.selecionarAtivoAdd();
        marca.salvar();
        marca.selecionarAtivo();
        marca.setDescricao(MarcaAtivar);
        marca.filtrar();
        Assert.assertEquals(MarcaAtivar, dsl.obterTexto(By.xpath(marca.getPathPrimeiroDaLista())));
    }

    @Test
    public void test9_EditarMarcaJaExiste() {
        marca.setDescricao(MarcaTest);
        marca.filtrar();
        marca.editarPrimeiro();
        marca.selecionarAtivoAdd();
        marca.setDescricaoNovo(MarcaTestSecundaria);
        marca.salvar();
        Assert.assertEquals(marca.getMsgJaExiste(), dsl.obterTexto(By.xpath(marca.getPathJaExiste())));
    }

    @Test
    public void test10_EditarMarca() {
        marca.novo();
        marca.selecionarAtivoAdd();
        marca.setDescricaoNovo(MarcaEditOriginal);
        marca.salvar();
        marca.selecionarAtivo();
        marca.setDescricao(MarcaEditOriginal);
        marca.filtrar();
        marca.editarPrimeiro();
        marca.selecionarAtivoAdd();
        marca.setDescricaoNovo(MarcaEditAlerada);
        marca.salvar();
        marca.selecionarAtivo();
        marca.setDescricao(MarcaEditAlerada);
        marca.filtrar();
        Assert.assertEquals(MarcaEditAlerada, dsl.obterTexto(By.xpath(marca.getPathPrimeiroDaLista())));
    }

    @AfterClass
    public static void encerrar() {
        DriverFactory.killDriver();
    }
}


