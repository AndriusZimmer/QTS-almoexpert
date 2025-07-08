package br.edu.ifrs;

public class LoginPage {

	private String pathMensagemSenhaIncorreta = "/html/body/app-root/app-container/div/app-login/div/div/div[2]/div/div";
	private String pathBotaoLogin = "/html/body/app-root/app-container/div/app-login/div/div/div[2]/div/form/button";
	private String pathMensagemCampoVazio = "/html/body/app-root/app-container/div/app-login/p-toast/div/p-toastitem/div/div/div/div[2]";
	private String msgCampoVazio = "Um ou mais campos obrigatórios não preenchidos.";
	private String msgSenhaIncorreta = "Usuário ou senha inválidos.";
	private String pathNotificacaoHome = "/html/body/app-root/app-container/main/div/app-home/div/div[1]/table/thead/tr/th";
	private String msgNotificacaoHome = "Notificações - Itens";
	private String pathBotaoLogout = "//*[@id=\"navbarSupportedContent\"]/ul[2]/li[2]/span/i";
	private DSL dsl = new DSL();
	
	public void setEmail(String email) {
		dsl.escrever("email", email);
	}
	
	public void setSenha(String senha) {
		dsl.escrever("senha", senha);
	}
	
	public String getEmail() {
		return dsl.obterValorCampo("email");
	}
	
	public String getSenha() {
		return dsl.obterValorCampo("senha");
	}

	public String getURL() {
		return dsl.obterUrl();
	}
	
	public String getPathMensagemSenhaIncorreta() {
		return pathMensagemSenhaIncorreta;
	}

	public String getPathMensagemCampoVazio() {
		return pathMensagemCampoVazio;
	}

	public String getPathBotaoLogout() {
		return pathBotaoLogout;
	}
	
	public String getPathBotaoLogin() {
		return pathBotaoLogin;
	}
	
	public String getMsgSenhaIncorreta() {
		return msgSenhaIncorreta;
	}

	public String getMsgNotificacaoHome() {
		return msgNotificacaoHome;
	}

	public String getMsgCampoVazio() {
		return msgCampoVazio;
	}

	public String getPathNotificacaoHome() {
		return pathNotificacaoHome;
	}
	
	public void logar() {
		dsl.clicarBotao(pathBotaoLogin);
	}

	public void logout() {
		dsl.clicarBotao(pathBotaoLogout);
	}
}
