import com.acme.credvarejo.ado.cliente.RepositorioCliente;
import com.acme.credvarejo.ado.conta.InterfaceRepositorioContaCrediario;
import com.acme.credvarejo.ado.conta.InterfaceRepositorioMovimentoCrediario;
import com.acme.credvarejo.ado.conta.RepositorioContaCrediario;
import com.acme.credvarejo.ado.conta.RepositorioMovimentoCrediario;
import com.acme.credvarejo.cliente.Cliente;
import com.acme.credvarejo.cliente.ControladorCliente;
import com.acme.credvarejo.cliente.Cpf;
import java.util.Date;

import com.acme.credvarejo.conta.*;
import com.acme.credvarejo.contaCrediario.ContaCrediarioEspecial;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TestDocuments {

    //1. Testes Sobre CPF -------------------------------------------------------------------------------------
    Cpf cpf = new Cpf(1667427601);
    @Test
    public void isCpf_False() {
        assertEquals(false, cpf.isCPF("11111111111"));
    }
    @Test
    public void isCpf_True(){
        assertEquals(true, cpf.isCPF("97795427014"));
    }
    @Test
    public void imprimirCpf(){
        assertEquals("166.742.760-14", cpf.imprimeCPF("16674276014"));
    }
    @Test
    public void getNumero(){
        assertEquals(1667427601, cpf.getNumero());
    }
    //--------------------------------------------------------------------------------------------------------


    //2. Testes de Clientes ----------------------------------------------------------------------------------
    Date data = new Date();
    Cliente clienteTest = new Cliente(cpf, "Carlos Sales", 23, data, 5000, 1);
    RepositorioCliente repo = new RepositorioCliente(0);
    ControladorCliente controllerCliente = new ControladorCliente(repo);
    @Test
    public void incluirCliente(){
        controllerCliente.incluir(clienteTest);
    }
    @Test
    public void getChave(){
        String cpf = String.valueOf((clienteTest.getCpf().getNumero()));
        assertEquals(cpf, clienteTest.getChave());
    }
    //--------------------------------------------------------------------------------------------------------

    //3. Testes Identificador da Conta -----------------------------------------------------------------------
    IdentificadorContaCrediario identificarTest = new IdentificadorContaCrediario(23685852L);
    @Test
    public void verificarValidadeDigito(){
        assertEquals(false, identificarTest.verificarValidadeDigito(23685852));
    }
    //--------------------------------------------------------------------------------------------------------

    //4. Conta Crediaria -------------------------------------------------------------------------------------
    ContaCrediarioEspecial contaCrediarioEspecial = new ContaCrediarioEspecial(identificarTest, clienteTest, 40, 1000, 25, false, 0.5, 0);
    @Test
    public void isAtiva(){
        assertEquals(false, contaCrediarioEspecial.isAtiva());
    }
    @Test
    public void isAtiva2(){
        contaCrediarioEspecial.reativar();
        assertEquals(true, contaCrediarioEspecial.isAtiva());
    }

    InterfaceRepositorioContaCrediario repositorioConta = new RepositorioContaCrediario();
    ControladorContaCrediario controllerContaCrediario =  new ControladorContaCrediario(repositorioConta);

    @Test
    public void incluir(){
        controllerContaCrediario.inserir(clienteTest, 5000, 12122022);
    }

    InterfaceRepositorioMovimentoCrediario repositorioMovimentoCrediario = new RepositorioMovimentoCrediario();
    ControladorMovimentoCrediario controllerMovimentoCrediario = new ControladorMovimentoCrediario(repositorioMovimentoCrediario);
    IdentificadorContaCrediario identificadorCliente = new IdentificadorContaCrediario(clienteTest.getCpf().getNumero());





}
