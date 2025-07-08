package br.edu.ifrs;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.UUID;

import org.junit.*;
import org.openqa.selenium.By;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;


public class TesteUM {

    private DSL dsl;
    private static LoginPage login = new LoginPage();
    private static MedidaPage medida = new MedidaPage();

   private static final String UnidadeCriar = "UnidadeMedida-AP" + UUID.randomUUID().toString().substring(0, 6);
   private static final String UnidadeCriarCancelar = "UnidadeMedida-AP" + UUID.randomUUID().toString().substring(0, 6);
   private static final String UnidadeTest = "testAP";
   private static final String UnidadeTestSecundaria = "testAP2";
   private static final String UnidadeCriarInativa = "UnidadeMedida-AP" + UUID.randomUUID().toString().substring(0, 6);
   private static final String UnidadeDesativar = "UnidadeMedida-AP" + UUID.randomUUID().toString().substring(0, 6);
   private static final String UnidadeAtivar = "UnidadeMedida-AP" + UUID.randomUUID().toString().substring(0, 6);
   private static final String UnidadeEditOriginal = "UnidadeMedida-AP" + UUID.randomUUID().toString().substring(0, 6);
   private static final String UnidadeEditAlerada = "UnidadeMedida-AP" + UUID.randomUUID().toString().substring(0, 6);
   private static final String UnidadeInexistente = "UnidadeMedida-AP??????";

    @Before
    public void inicializar() {
        DriverFactory.getDriver().get("http://35.209.123.161/front");
        dsl = new DSL();
        if (dsl.obterUrl().equals("http://35.209.123.161/front/login")) {
            login.setEmail("andrius.zimmer@gmail.com");
            login.setSenha("pinas");
            login.logar();
        }
        medida.acessarMedida();
    }

    @Test
    public void test1_ProcurarUnidadeInexistente() {
        medida.setDescricao(UnidadeInexistente);
        medida.filtrar();
        Assert.assertEquals(medida.getMsgMedidaNaoEncontrada(), dsl.obterTexto(By.xpath(medida.getPathMedidaNaoEncontrada())));

    }

    @Test
    public void test2_ProcurarUnidadeExistente() {
        medida.setDescricao(UnidadeTest);
        medida.filtrar();
        Assert.assertEquals(UnidadeTest, dsl.obterTexto(By.xpath(medida.getPathPrimeiroDaLista())));
    }

    @Test
    public void test3_CancelarCriacaoUnidade() {
        medida.novo();
        medida.setDescricaoNovo(UnidadeCriarCancelar);
        dsl.waitExplicito();
        medida.voltar();
        medida.setDescricao(UnidadeCriarCancelar);
        medida.filtrar();
        Assert.assertNotEquals(UnidadeCriarCancelar, dsl.obterTexto(By.xpath(medida.getPathMedidaNaoEncontrada())));
    }

    @Test
    public void test4_CriarUnidadeAtiva() {
        medida.novo();
        medida.selecionarAtivoAdd();
        medida.setDescricaoNovo(UnidadeCriar);
        medida.salvar();
        medida.selecionarAtivo();
        medida.setDescricao(UnidadeCriar);
        medida.filtrar();
        Assert.assertEquals(UnidadeCriar, dsl.obterTexto(By.xpath(medida.getPathPrimeiroDaLista())));
    }

    @Test
    public void test5_CriarUnidadeInativa() {
        medida.novo();
        medida.selecionarInativoAdd();
        medida.setDescricaoNovo(UnidadeCriarInativa);
        medida.salvar();
        medida.selecionarInativo();
        medida.setDescricao(UnidadeCriarInativa);
        medida.filtrar();
        Assert.assertEquals(UnidadeCriarInativa, dsl.obterTexto(By.xpath(medida.getPathPrimeiroDaLista())));

    }

    @Test
    public void test6_CriarUnidadeExistente() {
        medida.novo();
        medida.selecionarAtivoAdd();
        medida.setDescricaoNovo(UnidadeTest);
        medida.salvar();
        Assert.assertEquals(medida.getMsgJaExiste(), dsl.obterTexto(By.xpath(medida.getPathJaExiste())));
    }

    @Test
    public void test7_DesativarUnidade() {
        medida.novo();
        medida.selecionarAtivoAdd();
        medida.setDescricaoNovo(UnidadeDesativar);
        medida.salvar();
        medida.selecionarAtivo();
        medida.setDescricao(UnidadeDesativar);
        medida.filtrar();
        medida.desativarPrimeiro();
        dsl.waitExplicito();
        medida.confirmarDesativar();
        dsl.waitExplicito();
        medida.selecionarInativo();
        medida.setDescricao(UnidadeDesativar);
        medida.filtrar();
        Assert.assertEquals(UnidadeDesativar, dsl.obterTexto(By.xpath(medida.getPathPrimeiroDaLista())));
    }

    @Test
    public void test8_AtivarUnidade() {
        medida.novo();
        medida.selecionarInativoAdd();
        medida.setDescricaoNovo(UnidadeAtivar);
        medida.salvar();
        medida.selecionarInativo();
        medida.setDescricao(UnidadeAtivar);
        medida.filtrar();
        medida.editarPrimeiro();
        medida.selecionarAtivoAdd();
        medida.salvar();
        medida.selecionarAtivo();
        medida.setDescricao(UnidadeAtivar);
        medida.filtrar();
        Assert.assertEquals(UnidadeAtivar, dsl.obterTexto(By.xpath(medida.getPathPrimeiroDaLista())));
    }

    @Test
    public void test9_EditarUnidadeJaExiste() {
        medida.setDescricao(UnidadeTest);
        medida.filtrar();
        medida.editarPrimeiro();
        medida.selecionarAtivoAdd();
        medida.setDescricaoNovo(UnidadeTestSecundaria);
        medida.salvar();
        Assert.assertEquals(medida.getMsgJaExiste(), dsl.obterTexto(By.xpath(medida.getPathJaExiste())));
    }

    @Test
    public void test10_EditarUnidade() {
        medida.novo();
        medida.selecionarAtivoAdd();
        medida.setDescricaoNovo(UnidadeEditOriginal);
        medida.salvar();
        medida.selecionarAtivo();
        medida.setDescricao(UnidadeEditOriginal);
        medida.filtrar();
        medida.editarPrimeiro();
        medida.selecionarAtivoAdd();
        medida.setDescricaoNovo(UnidadeEditAlerada);
        medida.salvar();
        medida.selecionarAtivo();
        medida.setDescricao(UnidadeEditAlerada);
        medida.filtrar();
        Assert.assertEquals(UnidadeEditAlerada, dsl.obterTexto(By.xpath(medida.getPathPrimeiroDaLista())));
    }

    @AfterClass
    public static void encerrar() {
        DriverFactory.killDriver();
    }
}

