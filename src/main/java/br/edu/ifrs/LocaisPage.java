package br.edu.ifrs;

public class LocaisPage {
    private DSL dsl = new DSL();
    private String pathBotaoLocais = "//*[@id=\"navbarSupportedContent\"]/ul[1]/li[4]/a/i";
    private String pathBotaoLimpar = "/html/body/app-root/app-container/main/div/app-local/form/div/div[3]/div/button[1]";
    private String pathBotaoFiltrar = "/html/body/app-root/app-container/main/div/app-local/form/div/div[3]/div/button[2]";
    private String pathBotaoNovo = "/html/body/app-root/app-container/main/div/app-local/form/div/div[3]/div/button[3]";
    private String pathBotaoSalvar = "//*[@id=\"cadastrar_local\"]/div/div/form/div[2]/button[2]";
    private String pathBotaoVoltar = "//*[@id=\"cadastrar_local\"]/div/div/form/div[2]/button[1]";
    private String pathJaExiste = "/html/body/app-root/app-container/main/div/app-local/p-toast/div/p-toastitem/div/div/div/div[2]";
    private String pathLocalNaoEncontrado = "/html/body/app-root/app-container/main/div/app-local/p-toast/div/p-toastitem/div/div/div/div[2]";
    private String pathPrimeiroDaLista = "/html/body/app-root/app-container/main/div/app-local/div[2]/table/tbody/tr/td[2]";
    private String pathPrimeiroEditar = "/html/body/app-root/app-container/main/div/app-local/div[2]/table/tbody/tr/td[3]";
    private String pathPrimeiroDesativar = "/html/body/app-root/app-container/main/div/app-local/div[2]/table/tbody/tr/td[4]/i";
    private String pathConfirmarDesativar = "//*[@id=\"confirmModal\"]/div/div/div[3]/button[2]";
    private String pathCancelarDesativar = "//*[@id=\"confirmModal\"]/div/div/div[3]/button[1]";
    private String msgLocalNaoEncontrado = "Local não encontrado.";
    private String msgJaExiste = "Já existe um Local com o mesmo nome!";

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

    public void acessarLocal() {
        dsl.clicarBotao(pathBotaoLocais);
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

    public String getPathLocalNaoEncontrado() {
        return pathLocalNaoEncontrado;
    }

    public String getMsgLocalNaoEncontrado() {
        return msgLocalNaoEncontrado;
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


