package br.edu.ifrs;

import java.util.UUID;

import org.junit.*;
import org.openqa.selenium.By;


public class TesteCategoria {

    private DSL dsl;
    private static LoginPage login = new LoginPage();
    private static CategoriaPage categoria = new CategoriaPage();

    private static final String CategoriaCriar = "Categoria-AP" + UUID.randomUUID().toString().substring(0, 6);
    private static final String CategoriaCriarCancelar = "Categoria-AP" + UUID.randomUUID().toString().substring(0, 6);
    private static final String CategoriaTest = "testAPprimario";
    private static final String CategoriaTestSecundaria = "testAPsecundaria";
    private static final String CategoriaCriarInativa = "Categoria-AP" + UUID.randomUUID().toString().substring(0, 6);
    private static final String CategoriaDesativar = "Categoria-AP" + UUID.randomUUID().toString().substring(0, 6);
    private static final String CategoriaAtivar = "Categoria-AP" + UUID.randomUUID().toString().substring(0, 6);
    private static final String CategoriaEditOriginal = "Categoria-AP" + UUID.randomUUID().toString().substring(0, 6);
    private static final String CategoriaEditAlerada = "Categoria-AP" + UUID.randomUUID().toString().substring(0, 6);
    private static final String CategoriaInexistente = "Categoria-AP??????";

    @Before
    public void inicializar() {
        DriverFactory.getDriver().get("http://35.209.123.161/front");
        dsl = new DSL();
        if (dsl.obterUrl().equals("http://35.209.123.161/front/login")) {
            login.setEmail("andrius.zimmer@gmail.com");
            login.setSenha("pinas");
            login.logar();
        }
        categoria.acessarCategoria();
    }

    @Test
    public void test1_ProcurarCategoriaInexistente() {
        categoria.setDescricao(CategoriaInexistente);
        categoria.filtrar();
        Assert.assertEquals(categoria.getMsgCategoriaNaoEncontrada(), dsl.obterTexto(By.xpath(categoria.getPathCategoriaNaoEncontrada())));

    }

    @Test
    public void test2_ProcurarCategoriaExistente() {
        categoria.setDescricao(CategoriaTest);
        categoria.filtrar();
        Assert.assertEquals(CategoriaTest, dsl.obterTexto(By.xpath(categoria.getPathPrimeiroDaLista())));
    }

    @Test
    public void test3_CancelarCriacaoCategoria() {
        categoria.novo();
        categoria.setDescricaoNovo(CategoriaCriarCancelar);
        dsl.waitExplicito();
        categoria.voltar();
        categoria.setDescricao(CategoriaCriarCancelar);
        categoria.filtrar();
        Assert.assertNotEquals(CategoriaCriarCancelar, dsl.obterTexto(By.xpath(categoria.getPathCategoriaNaoEncontrada())));
    }

    @Test
    public void test4_CriarCategoriaAtiva() {
        categoria.novo();
        categoria.selecionarAtivoAdd();
        categoria.setDescricaoNovo(CategoriaCriar);
        categoria.salvar();
        categoria.selecionarAtivo();
        categoria.setDescricao(CategoriaCriar);
        categoria.filtrar();
        Assert.assertEquals(CategoriaCriar, dsl.obterTexto(By.xpath(categoria.getPathPrimeiroDaLista())));
    }


    @Test
    public void test5_CriarCategoriaInativa() {
        categoria.novo();
        categoria.selecionarInativoAdd();
        categoria.setDescricaoNovo(CategoriaCriarInativa);
        categoria.salvar();
        categoria.selecionarInativo();
        categoria.setDescricao(CategoriaCriarInativa);
        categoria.filtrar();
        Assert.assertEquals(CategoriaCriarInativa, dsl.obterTexto(By.xpath(categoria.getPathPrimeiroDaLista())));

    }

    @Test
    public void test6_CriarCategoriaExistente() {
        categoria.novo();
        categoria.selecionarAtivoAdd();
        categoria.setDescricaoNovo(CategoriaTest);
        categoria.salvar();
        Assert.assertEquals(categoria.getMsgJaExiste(), dsl.obterTexto(By.xpath(categoria.getPathJaExiste())));
    }

    @Test
    public void test7_DesativarCategoria() {
        categoria.novo();
        categoria.selecionarAtivoAdd();
        categoria.setDescricaoNovo(CategoriaDesativar);
        categoria.salvar();
        categoria.selecionarAtivo();
        categoria.setDescricao(CategoriaDesativar);
        categoria.filtrar();
        categoria.desativarPrimeiro();
        dsl.waitExplicito();
        categoria.confirmarDesativar();
        dsl.waitExplicito();
        categoria.selecionarInativo();
        categoria.setDescricao(CategoriaDesativar);
        categoria.filtrar();
        Assert.assertEquals(CategoriaDesativar, dsl.obterTexto(By.xpath(categoria.getPathPrimeiroDaLista())));
    }

    @Test
    public void test8_AtivarCategoria() {
        categoria.novo();
        categoria.selecionarInativoAdd();
        categoria.setDescricaoNovo(CategoriaAtivar);
        categoria.salvar();
        categoria.selecionarInativo();
        categoria.setDescricao(CategoriaAtivar);
        categoria.filtrar();
        categoria.editarPrimeiro();
        categoria.selecionarAtivoAdd();
        categoria.salvar();
        categoria.selecionarAtivo();
        categoria.setDescricao(CategoriaAtivar);
        categoria.filtrar();
        Assert.assertEquals(CategoriaAtivar, dsl.obterTexto(By.xpath(categoria.getPathPrimeiroDaLista())));
    }

    @Test
    public void test9_EditarCategoriaJaExiste() {
        categoria.setDescricao(CategoriaTest);
        categoria.filtrar();
        categoria.editarPrimeiro();
        categoria.selecionarAtivoAdd();
        categoria.setDescricaoNovo(CategoriaTestSecundaria);
        categoria.salvar();
        Assert.assertEquals(categoria.getMsgJaExiste(), dsl.obterTexto(By.xpath(categoria.getPathJaExiste())));
    }

    @Test
    public void test10_EditarCategoria() {
        categoria.novo();
        categoria.selecionarAtivoAdd();
        categoria.setDescricaoNovo(CategoriaEditOriginal);
        categoria.salvar();
        categoria.selecionarAtivo();
        categoria.setDescricao(CategoriaEditOriginal);
        categoria.filtrar();
        categoria.editarPrimeiro();
        categoria.selecionarAtivoAdd();
        categoria.setDescricaoNovo(CategoriaEditAlerada);
        categoria.salvar();
        categoria.selecionarAtivo();
        categoria.setDescricao(CategoriaEditAlerada);
        categoria.filtrar();
        Assert.assertEquals(CategoriaEditAlerada, dsl.obterTexto(By.xpath(categoria.getPathPrimeiroDaLista())));
    }

    @AfterClass
    public static void encerrar() {
        DriverFactory.killDriver();
    }
}


