package test;

import models.*;
import org.junit.jupiter.api.Test;

import java.rmi.RemoteException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class LojaTest {

    private Loja loja = mock(Loja.class);

    @Test
    void testaSeFuncionarioNaoExiste() throws RemoteException {
        Loja l = new Loja();
        Funcionario funcionario = new Funcionario("2020121000", "70048318418", "Daniel Cabral", "2500");
        assertThat(l.logar(funcionario)).isEqualTo(false);
    }

    @Test
    void testaSeFuncionarioExiste() throws RemoteException {
        Loja l = new Loja();
        Funcionario funcionario = new Funcionario("232", "232", "Daniel Cabral", "2500");
        assertThat(l.logar(funcionario)).isEqualTo(true);
    }

    //Testes de cadastro de produto
    //Tamanho, peso ou preço negativo ou nulo
    //
    @Test
    void testaSeAlimentoTemPrecoEPesoNegativo() throws RemoteException {
        Loja l = new Loja();
        Alimento produto = new Alimento(Produto.gerarCodigo(), "Feijão", "Alimento", -20, -20);
        assertThat(l.adicionarProduto(produto)).isEqualTo(false);
    }

    @Test
    void testaSeAlimentoCadastra() throws RemoteException {
        Loja l = new Loja();
        Alimento produto = new Alimento("alimento123", "Feijão", "Alimento", 7, 1);
        assertThat(l.adicionarProduto(produto)).isEqualTo(true);
        l.apagarProduto(produto.getCodigo(), "Alimento");
    }

    @Test
    void testaSeEletronicoTemPrecoNegativo() throws RemoteException {
        Loja l = new Loja();
        Eletronico produto = new Eletronico(Produto.gerarCodigo(), "Celular S8", "Eletronico", -2000, "Samsung");
        assertThat(l.adicionarProduto(produto)).isEqualTo(false);
    }

    @Test
    void testaSeEletronicoCadastra() throws RemoteException {
        Loja l = new Loja();
        Eletronico produto = new Eletronico("eletronico123", "Celular S8", "Eletronico", 1200, "Samsung");
        assertThat(l.adicionarProduto(produto)).isEqualTo(true);
        l.apagarProduto(produto.getCodigo(), "Eletronico");
    }

    @Test
    void testaSeRoupaTemPrecoNegativo() throws RemoteException {
        Loja l = new Loja();
        Roupa produto = new Roupa(Produto.gerarCodigo(), "Camisa Hering", "Roupa", -25, "M");
        assertThat(l.adicionarProduto(produto)).isEqualTo(false);
    }

    @Test
    void testaSeRoupaCadastra() throws RemoteException {
        Loja l = new Loja();
        Roupa produto = new Roupa("roupa123", "Camisa Hering", "Roupa", 34, "M");
        assertThat(l.adicionarProduto(produto)).isEqualTo(true);
        l.apagarProduto(produto.getCodigo(), "Roupa");
    }

    @Test
    void testaApagarAlimentoComCodigoNulo() throws RemoteException {
        Loja l = new Loja();
        Alimento produto = new Alimento(null, "Feijão", "Alimento", 34, 1);
        assertThat(l.apagarProduto(produto.getCodigo(), "Alimento")).isEqualTo(false);
    }
    @Test
    void testaApagarAlimentoComCodigoValido() throws RemoteException {
        Loja l = new Loja();
        Alimento produto = new Alimento("alimento123", "Feijão", "Alimento", 34, 1);
        l.adicionarProduto(produto);
        assertThat(l.apagarProduto(produto.getCodigo(), "Alimento")).isEqualTo(true);
    }

    @Test
    void testaApagarEletronicoComCodigoNulo() throws RemoteException {
        Loja l = new Loja();
        Eletronico produto = new Eletronico(null, "Celular S5", "Eletronico", 2500, "Samsung");
        assertThat(l.apagarProduto(produto.getCodigo(), "Eletronico")).isEqualTo(false);
    }
    @Test
    void testaApagarEletronicoComCodigoValido() throws RemoteException {
        Loja l = new Loja();
        Eletronico produto = new Eletronico("eletronico123", "Celular S5", "Eletronico", 2500, "Samsung");
        l.adicionarProduto(produto);
        assertThat(l.apagarProduto(produto.getCodigo(), "Eletronico")).isEqualTo(true);
    }

    @Test
    void testaApagarRoupaComCodigoNulo() throws RemoteException {
        Loja l = new Loja();
        Roupa produto = new Roupa(null, "Camiseta", "Roupa", 34, "G");
        assertThat(l.apagarProduto(produto.getCodigo(), "Roupa")).isEqualTo(false);
    }

    @Test
    void testaApagarRoupaComCodigoValido() throws RemoteException {
        Loja l = new Loja();
        Roupa produto = new Roupa("roupa123", "Camiseta", "Roupa", 25, "M");
        l.adicionarProduto(produto);
        assertThat(l.apagarProduto(produto.getCodigo(), "Roupa")).isEqualTo(true);
    }

    @Test
    void testaPesquisaAlimentoComStringVazia() throws RemoteException {
        Loja l = new Loja();
        assertThat(l.pesquisarAlimento("")).isEqualTo(null);
    }

    @Test
    void testaPesquisaAlimentoInexistente() throws RemoteException {
        Loja l = new Loja();
        assertThat(l.pesquisarAlimento("2121212").size()).isEqualTo(0);
    }
    @Test
    void testaPesquisaAlimentoExistente() throws RemoteException {
        Loja l = new Loja();
        Alimento produto = new Alimento("alimento123", "Feijão", "Alimento", 34, 1);
        l.adicionarProduto(produto);
        assertThat(l.pesquisarAlimento("Feijão").size()).isGreaterThan(0);
        l.apagarProduto(produto.getCodigo(), "Alimento");
    }

    @Test
    void testaPesquisaEletronicoComStringVazia() throws RemoteException {
        Loja l = new Loja();
        assertThat(l.pesquisarEletronico("")).isEqualTo(null);
    }

    @Test
    void testaPesquisaEletronicoInexistente() throws RemoteException {
        Loja l = new Loja();
        assertThat(l.pesquisarEletronico("2121212").size()).isEqualTo(0);
    }
    @Test
    void testaPesquisaEletronicoExistente() throws RemoteException {
        Loja l = new Loja();
        Eletronico produto = new Eletronico("eletronico123", "Celular S5", "Eletronico", 2500, "Samsung");
        l.adicionarProduto(produto);
        assertThat(l.pesquisarEletronico("Celular S5").size()).isGreaterThan(0);
        l.apagarProduto(produto.getCodigo(), "Eletronico");
    }

    @Test
    void testaPesquisaRoupaComStringVazia() throws RemoteException {
        Loja l = new Loja();
        assertThat(l.pesquisarEletronico("")).isEqualTo(null);
    }

    @Test
    void testaPesquisaRoupaInexistente() throws RemoteException {
        Loja l = new Loja();
        assertThat(l.pesquisarRoupa("2121212").size()).isEqualTo(0);
    }
    @Test
    void testaPesquisaRoupaExistente() throws RemoteException {
        Loja l = new Loja();
        Roupa produto = new Roupa("roupa123", "Camiseta", "Roupa", 25, "M");
        l.adicionarProduto(produto);
        assertThat(l.pesquisarRoupa("Camiseta").size()).isGreaterThan(0);
        l.apagarProduto(produto.getCodigo(), "Eletronico");
    }

    @Test
    void testaSeAlteraAlimentoComCodigoNulo() throws RemoteException {
        Loja l = new Loja();
        Alimento produto = new Alimento(null, "Feijão", "Alimento", 34, 1);
        assertThat(l.alterarProduto(produto)).isEqualTo(false);
    }
    @Test
    void testaSeAlteraAlimentoComCodigoValido() throws RemoteException {
        Loja l = new Loja();
        Alimento produto = new Alimento("alimento123", "Feijão", "Alimento", 34, 1);
        l.adicionarProduto(produto);
        produto = new Alimento("alimento123", "Feijão Preto", "Alimento", 60, 10);
        assertThat(l.alterarProduto(produto)).isEqualTo(true);
        l.apagarProduto(produto.getCodigo(), "Alimento");
    }

    @Test
    void testaSeAlteraEletronicoComCodigoNulo() throws RemoteException {
        Loja l = new Loja();
        Eletronico produto = new Eletronico(null, "Celular S5", "Eletronico", 2500, "Samsung");
        assertThat(l.alterarProduto(produto)).isEqualTo(false);
    }
    @Test
    void testaSeAlteraEletronicoComCodigoValido() throws RemoteException {
        Loja l = new Loja();
        Eletronico produto = new Eletronico("eletronico123", "Celular S5", "Eletronico", 2500, "Samsung");
        l.adicionarProduto(produto);
        produto = new Eletronico("eletronico123", "Celular Redmi note 10", "Eletronico", 1500, "Xiaomi");
        assertThat(l.alterarProduto(produto)).isEqualTo(true);
        l.apagarProduto(produto.getCodigo(), "Eletronico");
    }

    @Test
    void testaSeAlteraRoupaComCodigoNulo() throws RemoteException {
        Loja l = new Loja();
        Roupa produto = new Roupa(null, "Camiseta", "Roupa", 25, "M");
        assertThat(l.alterarProduto(produto)).isEqualTo(false);
    }

    @Test
    void testaSeAlteraRoupaComCodigoValido() throws RemoteException {
        Loja l = new Loja();
        Roupa produto = new Roupa("roupa123", "Camiseta", "Roupa", 25, "M");
        l.adicionarProduto(produto);
        produto = new Roupa("roupa123", "Camiseta Homem Aranha", "Roupa", 99, "GG");
        assertThat(l.alterarProduto(produto)).isEqualTo(true);
        l.apagarProduto(produto.getCodigo(), "Eletronico");
    }
}
