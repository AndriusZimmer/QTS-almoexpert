package br.edu.ifrs;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

import org.junit.*;
import org.openqa.selenium.By;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class TesteLogin {
	
	private DSL dsl;
	private static LoginPage page = new LoginPage();
	
	@Parameter
	public String email;
	@Parameter(value=1)
	public String senha;
	@Parameter(value=2)
	public String localResposta;
	@Parameter(value=3)
	public String resposta;

	@Before
	public void inicializar() {
		DriverFactory.getDriver().get("http://35.209.123.161/front");
		dsl = new DSL();
	}
	
	
	@Parameters
	public static Collection<Object[]> getCollection() {
		return Arrays.asList(new Object[][] {
			{"andrius.zimmer@gmail.com", "pinas", page.getPathNotificacaoHome(), page.getMsgNotificacaoHome()},
			{"andrius.zimmer@gmail.com", "senha_incorreta", page.getPathMensagemSenhaIncorreta(), page.getMsgSenhaIncorreta()},
			{"email_incorreto@gmail.com", "pinas", page.getPathMensagemSenhaIncorreta(), page.getMsgSenhaIncorreta()},
			{"", "pinas", page.getPathMensagemCampoVazio(), page.getMsgCampoVazio()},
			{"andrius.zimmer@gmail.com", "", page.getPathMensagemCampoVazio(), page.getMsgCampoVazio()},
			{"", "", page.getPathMensagemCampoVazio(), page.getMsgCampoVazio()}
		});
	}
	
	@Test
	public void deveValidarRegrasNegocioLogin() throws IOException {
		page.setEmail(email);
		page.setSenha(senha);
		page.logar();	
		Assert.assertEquals(resposta, dsl.obterTexto(By.xpath(localResposta)));
	}

	@After
	public void finalizar() {
		if (Objects.equals(resposta, page.getMsgNotificacaoHome())){
			page.logout();
		}
	}

	@AfterClass
	public static void encerrar() {
		DriverFactory.killDriver();
	}
}
