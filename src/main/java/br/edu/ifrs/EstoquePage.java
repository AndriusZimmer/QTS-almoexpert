package br.edu.ifrs;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.openqa.selenium.By;

public class EstoquePage {
    private DSL dsl = new DSL();

    private String pathBotaoEstoque = "//*[@id=\"navbarSupportedContent\"]/ul[1]/li[7]/a/i";
    private String pathFiltrar = "/html/body/app-root/app-container/main/div/app-movimentacao/form/div/div[5]/div/button[2]";
    private String pathNovo = "/html/body/app-root/app-container/main/div/app-movimentacao/form/div/div[5]/div/button[3]";
    private String pathPrimeiroItemLista = "/html/body/app-root/app-container/main/div/app-movimentacao/div[2]/table/tbody/tr[1]/td[1]";
    private String pathPrimeiroDelet = "/html/body/app-root/app-container/main/div/app-movimentacao/div[2]/table/tbody/tr[1]/td[8]/i";
    private String pathSalvarNovo = "//*[@id=\"cadastrar_movimentacao\"]/div/div/form/div[2]/button[2]";
    private String pathVoltar = "//*[@id=\"cadastrar_movimentacao\"]/div/div/form/div[2]/button[1]";
    private String msgMovimentacaoNaoEncontrada = "Movimentação excluída com sucesso!";
    private String pathMovimentacaoNaoEncontrada = "/html/body/app-root/app-container/main/div/app-movimentacao/p-toast/div/p-toastitem/div/div/div/div[2]";
    private String pathConfirmarExclusao = "/html/body/app-root/app-container/main/div/app-movimentacao/app-confirm-modal/div/div/div/div[3]/button[2]";

    public void acessarEstoque() {
        dsl.clicarBotao(pathBotaoEstoque);
    }

    public void selecionarItem(String item) {
        dsl.selecionarCombo("filtro_elemento", item);
    }

    public void filtrar() {
        dsl.clicarBotao(pathFiltrar);
    }

    public void novo() {
        dsl.clicarBotao(pathNovo);
    }

    public void deletarPrimeiro() {
        dsl.clicarBotao(pathPrimeiroDelet);
    }

    public void confirmarDeletar() {
        dsl.clicarBotao(pathConfirmarExclusao);
    }

    public void selecionarItemNovo(String item) {
        dsl.selecionarCombo("cadastro_elemento", item);
    }

    public void selecionarSubItemNovo(String Subitem) {
        dsl.selecionarCombo("cadastro_elementoItem", Subitem);
    }

    public void selecionarEntrada(){
        dsl.selecionarCombo("cadastroTipo", "Entrada");
    }

    public void selecionarSaida(){
        dsl.selecionarCombo("cadastroTipo", "Saída");
    }

    public void adicionarData(String data){
        dsl.escrever("cadastroData", data);
    }

    public void procurarData(String data){
        dsl.escrever("dataInicial", data);
        dsl.escrever("dataFinal", data);
    }

    public void adicionarQuantidade(){
        dsl.escrever("cadastroQuantidade", "9");
    }

    public void adicionarObservacao(){
        dsl.escrever("observacoes", "Observações foram feitas para serem obseravadas");
    }

    public void salvar(){
        dsl.clicarBotao(pathSalvarNovo);
    }

    public void voltar(){
        dsl.clicarBotao(pathVoltar);
    }

    public String getPathPrimeiroItemDaLista() {
        return pathPrimeiroItemLista;
    }

    public String getMsgMovimentacaoNaoEncontrada() {
        return msgMovimentacaoNaoEncontrada;
    }

    public String getPathMovimentacaoNaoEncontrada() {
        return pathMovimentacaoNaoEncontrada;
    }
}


