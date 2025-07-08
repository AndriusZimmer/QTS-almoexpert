package br.edu.ifrs;

import org.openqa.selenium.By;

public class MarcaPage {
    private DSL dsl = new DSL();
    private String pathBotaoMarca = "//*[@id=\"navbarSupportedContent\"]/ul[1]/li[5]/a/i";
    private String pathBotaoLimpar = "/html/body/app-root/app-container/main/div/app-marca/form/div/div[3]/div/button[1]";
    private String pathBotaoFiltrar = "/html/body/app-root/app-container/main/div/app-marca/form/div/div[3]/div/button[2]";
    private String pathBotaoNovo = "/html/body/app-root/app-container/main/div/app-marca/form/div/div[3]/div/button[3]";
    private String pathBotaoSalvar = "//*[@id=\"cadastrar_marca\"]/div/div/form/div[2]/button[2]";
    private String pathBotaoVoltar = "//*[@id=\"cadastrar_marca\"]/div/div/form/div[2]/button[1]";
    private String pathJaExiste = "/html/body/app-root/app-container/main/div/app-marca/p-toast/div/p-toastitem/div/div/div/div[2]";
    private String pathMarcaNaoEncontrada = "/html/body/app-root/app-container/main/div/app-marca/p-toast/div/p-toastitem/div/div/div/div[2]";
    private String pathPrimeiroDaLista = "/html/body/app-root/app-container/main/div/app-marca/div[2]/table/tbody/tr/td[2]";
    private String pathPrimeiroEditar = "/html/body/app-root/app-container/main/div/app-marca/div[2]/table/tbody/tr/td[3]";
    private String pathPrimeiroDesativar = "/html/body/app-root/app-container/main/div/app-marca/div[2]/table/tbody/tr/td[4]";
    private String pathConfirmarDesativar = "//*[@id=\"confirmModal\"]/div/div/div[3]/button[2]";
    private String pathCancelarDesativar = "//*[@id=\"confirmModal\"]/div/div/div[3]/button[1]";
    private String msgMarcaNaoEncontrada = "Marca não encontrada.";
    private String msgJaExiste = "Já existe uma Marca com o mesmo nome!";

    public void setDescricao(String descricao) {
        dsl.escrever("filtro_nome", descricao);
    }

    public void setDescricaoNovo(String descricao) {
        dsl.escrever("nome", descricao);
    }

    public void limpar() {
        dsl.clicarBotao(pathBotaoLimpar);
    }

    public void filtrar() {
        dsl.clicarBotao(pathBotaoFiltrar);
    }

    public void acessarMarca() {
        dsl.clicarBotao(pathBotaoMarca);
    }

    public void novo() {
        dsl.clicarBotao(pathBotaoNovo);
    }

    public void salvar() {
        dsl.clicarBotao(pathBotaoSalvar);
        dsl.waitExplicito();
    }

    public void voltar() {
        dsl.clicarBotao(pathBotaoVoltar);
        dsl.waitExplicito();
    }

    public void selecionarAtivoAdd() {
        dsl.selecionarCombo("status", "\uD83D\uDFE2 Ativo");
    }

    public void selecionarInativoAdd() {
        dsl.selecionarCombo("status", "\uD83D\uDD34 Inativo");
    }

    public void selecionarAtivo() {
        dsl.selecionarCombo("filtro_status", "\uD83D\uDFE2 Ativo");
    }

    public void selecionarInativo() {
        dsl.selecionarCombo("filtro_status", "\uD83D\uDD34 Inativo");
    }

    public void editarPrimeiro() {
        dsl.clicarBotao(pathPrimeiroEditar);
    }

    public void desativarPrimeiro() {
        dsl.clicarBotao(pathPrimeiroDesativar);
    }

    public void confirmarDesativar() {
        dsl.clicarBotao(pathConfirmarDesativar);
    }

    public void cancelarDesativar() {
        dsl.clicarBotao(pathCancelarDesativar);
    }

    public String getPathMarcaNaoEncontrada() {
        return pathMarcaNaoEncontrada;
    }

    public String getMsgMarcaNaoEncontrada() {
        return msgMarcaNaoEncontrada;
    }

    public String getPathPrimeiroDaLista() {
        return pathPrimeiroDaLista;
    }

    public String getPathBotaoFiltrar() {
        return pathBotaoFiltrar;
    }

    public String getPathJaExiste() {
        return pathJaExiste;
    }

    public String getMsgJaExiste() {
        return msgJaExiste;
    }


}


