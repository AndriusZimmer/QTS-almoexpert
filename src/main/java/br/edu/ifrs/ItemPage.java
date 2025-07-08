package br.edu.ifrs;

public class ItemPage {
    private DSL dsl = new DSL();
    private String pathBotaoItem = "//*[@id=\"navbarSupportedContent\"]/ul[1]/li[2]/a/i";
    private String pathBotaoLimpar = "/html/body/app-root/app-container/main/div/app-lista-elemento/form/div/div[4]/div/button[1]";
    private String pathBotaoFiltrar = "/html/body/app-root/app-container/main/div/app-lista-elemento/form/div/div[4]/div/button[2]";
    private String pathBotaoNovo = "/html/body/app-root/app-container/main/div/app-lista-elemento/form/div/div[4]/div/a";
    private String pathBotaoSalvar = "/html/body/app-root/app-container/main/div/app-cadastro-elemento/form/div[2]/button";
    private String pathBotaoVoltar = "/html/body/app-root/app-container/main/div/app-cadastro-elemento/form/div[2]/a";
    private String pathJaExiste = "/html/body/app-root/app-container/main/div/app-cadastro-elemento/p-toast/div/p-toastitem/div/div/div/div[2]";
    private String pathItemNaoEncontrado = "/html/body/app-root/app-container/main/div/app-lista-elemento/p-toast/div/p-toastitem/div/div/div/div[2]";
    private String pathPrimeiroNomeDaLista = "/html/body/app-root/app-container/main/div/app-lista-elemento/div/table/tbody/tr/td[3]";
    private String pathPrimeiroEditar = "/html/body/app-root/app-container/main/div/app-lista-elemento/div/table/tbody/tr[1]/td[4]/i";
    private String pathPrimeiroDesativar = "/html/body/app-root/app-container/main/div/app-lista-elemento/div/table/tbody/tr[1]/td[5]/i";
    private String pathConfirmarDesativar = "//*[@id=\"confirmModal\"]/div/div/div[3]/button[2]";
    private String pathCancelarDesativar = "//*[@id=\"confirmModal\"]/div/div/div[3]/button[1]";
    private String pathCodigoNovo = "/html/body/app-root/app-container/main/div/app-cadastro-elemento/form/div[1]/div[1]/div/div/div[2]/input";
    private String pathNomeNovo = "/html/body/app-root/app-container/main/div/app-cadastro-elemento/form/div[1]/div[1]/div/div/div[3]/input";
    private String pathSubitens = "//*[@id=\"nav-subitem-tab\"]";
    private String pathNovoSubitem = "//*[@id=\"nav-subitem\"]/div/form/div/div[4]/div/button[3]";
    private String pathSalvarSubitem = "/html/body/app-root/app-container/main/div/app-cadastro-elemento/div/div/div/form/div[2]/button[2]";
    private String msgItemNaoEncontrado = "Elemento não encontrado.";
    private String msgJaExiste = "Error: Já existe um Elemento com o mesmo código!";

    public void setNome(String nome) {
        dsl.escrever("filtro_nome", nome);
    }

    public void setCodigoNovo(String codigo) {
        dsl.escreverXpath(pathCodigoNovo, codigo);
    }

    public void setNomeNovo(String nome) {
        dsl.escreverXpath(pathNomeNovo, nome);
    }

    public void cadastrarSubitem(){
        dsl.selecionarCombo("estado","\uD83D\uDD34 Fechado");
        dsl.escrever("codigo", "Subitem-AP");
        dsl.escrever("nome", "SubitemNome-AP");
        dsl.selecionarCombo("localId", "Local-AP");
        dsl.escrever("quantidade", "5");
        dsl.selecionarCombo("marcaId", "Marca-AP");
        dsl.escrever("validade","1500-05-21");
        dsl.clicarBotao(pathSalvarSubitem);
    }

    public void selecionarUM() {
        dsl.selecionarCombo("unidadeMedidaId", "UnidadeMedida-AP");
    }

    public void selecionarCategoria(){
        dsl.selecionarCombo("categoriaId", "Categoria-AP");
    }

    public void setValor(float valor) {
        dsl.escrever("quantidadeMinima", String.valueOf(valor));
    }

    public void setPolicia() {
        dsl.clicarCheck("monitoradoPF");
    }

    public void setExercito(){
        dsl.clicarCheck("exercito");
    }

    public void setObservacoes(String observacoes) {
        dsl.escrever("observacoes", observacoes);
    }

    public void subitem() {
        dsl.clicarBotao(pathSubitens);
    }

    public void novoSubitem() {
        dsl.clicarBotao(pathNovoSubitem);
    }

    public void limpar() {
        dsl.clicarBotao(pathBotaoLimpar);
    }

    public void filtrar() {
        dsl.clicarBotao(pathBotaoFiltrar);
    }

    public void acessarItem() {
        dsl.clicarBotao(pathBotaoItem);
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

    public String getPathItemNaoEncontrado() {
        return pathItemNaoEncontrado;
    }

    public String getMsgItemNaoEncontrado() {
        return msgItemNaoEncontrado;
    }

    public String getPathPrimeiroNomeDaLista() {
        return pathPrimeiroNomeDaLista;
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


